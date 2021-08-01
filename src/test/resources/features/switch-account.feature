#language:en
@switch-account
Feature: Switch account
  User wants to switch to a different client and engagement

  Background:
    When the user is logged in with "planner.mktev.auto" and "!Automation1"

  Scenario Outline: User switch account
    Given the user clicks on the switch account button
    When the user selects "<clientName>" client name
    And the user selects "<engagementName>" engagement name
    And the user clicks on the switch button
    Then header should have text "<clientName>" and "<engagementName>"

    Examples:
      | clientName        | engagementName       |
      | Kaiser Permanente | Kaiser PermanenteTes |
      | Nespresso         | Nespresso            |