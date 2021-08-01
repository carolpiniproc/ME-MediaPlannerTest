#language:en
@login
Feature: Login into the application
  User wants to log in into the application successfully

  Background:
    Given the user has access to "Login Page"

  Scenario Outline: System validates user and password
    Given the user login with "<username>" and "<password>"
    Then the system validates login <status>

    Examples:
      | username           | password     | status |
      | planner.mktev.auto | !Automation1 | true   |
      | test               | xxx123       | false  |