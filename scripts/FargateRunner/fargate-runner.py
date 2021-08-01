# Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
# except in compliance with the License. A copy of the License is located at
#
#     http://aws.amazon.com/apache2.0/
#
# or in the "license" file accompanying this file. This file is distributed on an "AS IS"
# BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
# License for the specific language governing permissions and limitations under the License.
"""
A Bitbucket Builds template for creating a new ECS Task Definition and
then updating an existing Service running on an existing ECS Cluster
joshcb@amazon.com
v1.0.0
"""
from __future__ import print_function
import os
import sys
import json
import argparse
import boto3
import re
import time
from botocore.exceptions import ClientError

_taskDefinitionfilename = None
_runDefinitionfilename = None
_image = None
_region = None
_runCmdOveride = None

_passStr = None
_failStr = None

#might as well actually use a few basic internal variables to simplify this script
_clientECS = None #Boto3 ECS client
_taskDefinitionjson = None #task definition as python json object
_runDefinitionjson = None #run_task arguments as python json arguments

#updates _taskDefinitionjson based on supplied variables
def update_taskDefinitionjson(image, runCmdOveride = None):
    global _taskDefinitionjson
    _taskDefinitionjson['containerDefinitions'][0]['image'] = image
    #apply runCmdOveride
    if(runCmdOveride):
        runCmdOverideAr = runCmdOveride.strip().split()
        _taskDefinitionjson['containerDefinitions'][0]["entryPoint"] = [runCmdOverideAr[0]]
        _taskDefinitionjson['containerDefinitions'][0]["command"] = runCmdOverideAr[1:]

#Registers task definition. Returns ARN of task definiton
def register_task_definition2():
    global _clientECS
    global _taskDefinitionjson
    try:
        """
        see all parameters here:
        http://boto3.readthedocs.io/en/latest/reference/services/ecs.html#ECS.Client.register_task_definition
        """
        response = _clientECS.register_task_definition(**_taskDefinitionjson)
        return response['taskDefinition']['taskDefinitionArn']
    except ClientError as err:
        print("Failed to update the stack.\n" + str(err))
        return False
    except IOError as err:
        print("IOERROR " + str(err))
        return False
    except KeyError as err:
        print("Unable to retrieve taskDefinitionArn key.\n" + str(err))
        return False

#updates _runDefinitionjson with details from _taskDefinitionjson and runtime arguments    
def update_runDefinitionjson(task_definition_arn):
    global _taskDefinitionjson
    global _runDefinitionjson
    _runDefinitionjson["taskDefinition"] = task_definition_arn

#Executes ecs task in fargate. Returns stripped ARN
def ecs_deploy_task2():
    global _clientECS
    global _runDefinitionjson
    try:
        """
        see all parameters here:
        http://boto3.readthedocs.io/en/latest/reference/services/ecs.html#ECS.Client.update_service
        """
        response = _clientECS.run_task(**_runDefinitionjson)
    except ClientError as err:
        print("Failed to update the stack run_Task.\n" + str(err))
        return False
    if not response:
        print("No task_response")
        return False
    elif not response['tasks']:
        print("No tasks queued")
        print(response)
        return False
    elif not response['tasks'][0]:
        print("No tasks queued")
        print(response)
        return False
    else:
        return response['tasks'][0]['taskArn']


#wait for tests to finish
def wait_for_task(runTaskARN, timeoutMinutes):
    global _clientECS
    waiter = _clientECS.get_waiter('tasks_stopped')
    print("Waiting for tests to finish running.\n")
    waiter.wait(
                tasks=[runTaskARN],
                WaiterConfig={'Delay': 60,'MaxAttempts': timeoutMinutes}
                )

#pulls cloudwatch logs, checks for success/failure
def parseResults(runTaskARN, passStr, failStr, AwsRegion):
    global _taskDefinitionjson
    logGroupName = _taskDefinitionjson['containerDefinitions'][0]['logConfiguration']['options']['awslogs-group']
    logStreamPrefix = logGroupName+"/"+_taskDefinitionjson['containerDefinitions'][0]['logConfiguration']['options']['awslogs-stream-prefix']+"/"
    print(logGroupName)
    print(logStreamPrefix)
    #arn is of format arn:aws:ecs:us-west-2:126581247961:task/default/31cf645310514507b678e7f2f67024ad or
    #arn:aws:ecs:us-west-2:126581247961:task/default/9a1fc5af-6ca7-4856-9ee8-2df5f7b02b6e
    #this regex just rips the last part of this string
    arnStripped = re.search('((\d|\w){8}\-(\d|\w){4}\-(\d|\w){4}-(\d|\w){4}\-(\d|\w){12})|(\d|\w){32}',
                    runTaskARN).group(0)
    print(logStreamPrefix+arnStripped)
    logStringName = logStreamPrefix+arnStripped
    try:
        client = boto3.client('logs', AwsRegion)
    except ClientError as err:
        print("Failed to create boto3 client.\n" + str(err))
        return False
    try:
        logs = client.get_log_events(
            logGroupName=logGroupName,
            logStreamName=logStringName
        )
    except ClientError as err:
        print("Failed to get logs.\n" + str(err))
        return False
    if not logs:
        return False
    else:
        for log_event in logs['events']:
            print(log_event['message'].encode('utf-8'))
            if(passStr and re.search(passStr, log_event['message'])):
                print("Success string found.")
                return True
            elif(failStr and re.search(failStr, log_event['message'])):
                print("Failure string found.")
                return False
    print("Result String not found.")
    return True

#snag logs for task
#ECS task# denotes the logs in the format pipeline-test/MCD_Test/task#
# http://boto3.readthedocs.io/en/latest/reference/services/logs.html#CloudWatchLogs.Client.get_log_events
def get_log_events(log_groupname, log_stingname):
    """
    Gets the log for an ECS Task run
    """
    try:
        client = boto3.client('logs')
    except ClientError as err:
        print("Failed to create boto3 client.\n" + str(err))
        return False
    try:
        response = client.get_log_events(
            logGroupName=log_groupname,
            logStreamName=log_stingname
        )
        return response
    except ClientError as err:
        print("Failed to get logs.\n" + str(err))
        return False

def main():
    global _clientECS
    global _taskDefinitionjson
    global _runDefinitionjson
    " Your favorite wrapper's favorite wrapper "
    parser = argparse.ArgumentParser()

    #ecs container arguments
    parser.add_argument(
        "taskDefinition",
        help="The path to an ECS Task Definition file in JSON format.  " \
            "See this for details:  http://docs.aws.amazon.com/AmazonECS/" \
            "latest/developerguide/task_definition_parameters.html#container_definitions"
    )
    parser.add_argument(
        "runDefinition",
        help="The path to an ECS Start Definition file in JSON format.  " \
            "See this for details:  https://docs.aws.amazon.com/AmazonECS/latest/APIReference/API_StartTask.html" \
            "latest/developerguide/task_definition_parameters.html#container_definitions"
    )
    parser.add_argument("image", help="The Docker Image to be used in the Task")
    parser.add_argument("--region", help="Region to use for boto3 calls. Default is us-west-2", default="us-west-2")
    parser.add_argument("--runCmdOveride", help="If passed in overides runcmd with this cmd")

    #arguments for parsing results from logs
    parser.add_argument("--passStr", help="If passed in script will return success on finding this string in the logs", default=None)
    parser.add_argument("--failStr", help="If passed in script will return failure on finding this string in the logs", default=None)
    parser.add_argument("--timeoutMinutes", help="How long for the script to wait for results. Default 20", default=20)

    args = parser.parse_args()

    _taskDefinitionfilename = args.taskDefinition
    _runDefinitionfilename = args.runDefinition
    _image = args.image
    _region = args.region
    _runCmdOveride = args.runCmdOveride
    _passStr = args.passStr
    _failStr = args.failStr
    _timeoutMinutes = args.timeoutMinutes

    #setup boto3 ecs client
    try:
        _clientECS = boto3.client('ecs', _region)
    except ClientError as err:
        print("Failed to create boto3 client.\n" + str(err))
        return False

    #get _taskDefinitionjson from file
    with open(_taskDefinitionfilename) as taskDefinitionfile:
        _taskDefinitionjson = json.load(taskDefinitionfile)
        print(_taskDefinitionjson)
    taskDefinitionfile.close()

    #get _runDefinitionjson from file
    with open(_runDefinitionfilename) as runDefinitionfile:
        _runDefinitionjson = json.load(runDefinitionfile)
    runDefinitionfile.close()

    #updates _taskDefinitionjson based on supplied variables
    update_taskDefinitionjson(_image, runCmdOveride = _runCmdOveride)
    #Registers task definition. Returns ARN of task defenition
    taskARN = register_task_definition2()
    #updates _runDefinitionjson with details from _taskDefinitionjson and runtime arguments    
    update_runDefinitionjson(taskARN)
    #Executes ecs task in fargate. Returns ARN
    runTaskARN = ecs_deploy_task2()
    #wait for tests to finish
    wait_for_task(runTaskARN, _timeoutMinutes)
    #pulls cloudwatch logs, checks for success/failure
    if(parseResults(runTaskARN, _passStr, _failStr, _region)):
        sys.exit(0)
    else:
        sys.exit(1)

if __name__ == "__main__":
    main()
