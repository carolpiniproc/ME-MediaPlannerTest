#language:en
@myScenarios
Feature: My scenarios
  User wants to view his current scenarios

  Background:
    Given the user is logged in with "planner.mktev.auto" and "!Automation1"

  Scenario:
    Given the planner tab is displayed
    When click on planner tab
    And my scenarios table is displayed
    Then validate if any row contains any action