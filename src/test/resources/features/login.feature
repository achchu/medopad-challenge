Feature: Login
  Validation of the login feature for perkbox

  Scenario Outline: Verify if all available accounts are visible when signing in with correct email address
    Given I am on medopad login page
    And I enter valid username
    And I enter valid password
    When I click on login
    Then I should see my userName "<userName>" on home page

    Examples:
      | userName |
      | Robert   |
