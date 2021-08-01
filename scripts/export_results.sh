#!/bin/bash

#usage example
#./export_results.sh ../build/reports/tests/test s3://platform-test-results-uw2 filename.cz
artifact_folder=$1
results_bucket=$2
filename=$3
tar -czf /tmp/$filename $artifact_folder
aws s3 cp /tmp/$filename $results_bucket
echo "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
echo "Results posted to "$results_bucket"/"$filename
echo "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"