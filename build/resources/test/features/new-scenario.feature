#language:en
@newScenario
Feature: New scenario
  User wants to create a new scenario

  Background:
    Given the user is logged in with "planner.mktev.auto" and "!Automation1"

  Scenario: User checks My Scenarios list, KPIs available and Conversion Target enable
    And the planner tab is clickable
    When the user clicks on create scenario button
    Then assert that label is displayed with text "Scenario Planner - Create Scenario"
    And conv target field is displayed
    When user selects all KPIs
    Then assert selected KPI text matches
    And assert budget is enabled
    And conv target field is enabled