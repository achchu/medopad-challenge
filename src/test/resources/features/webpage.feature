Feature: Patient Records Page
  Validation of the patient records page

  Scenario Outline: Verify if the doctor can search for a patient record
    Given I am on medopad login page
    And I enter valid username
    And I enter valid password
    And I click on login
    When I enter a "<patientid>" in the search box
    And I click on search button
    Then I should see the "<patientid>" in results page

    Examples:
      | patientid |
      | 001       |

  Scenario Outline: Verify that the doctor can view patient records by clicking on a search result
    Given I am on medopad login page
    And I enter valid username
    And I enter valid password
    And I click on login
    And I enter a "<patientid>" in the search box
    And I click on search button
    When I click on <patientid> in results
    Then I should see the profile page of the patient with "<patientName>"

    Examples:
      | patientid | patientName |
      | 001       | Robbert     |

  Scenario: Verify that the doctor can enter notes in the page of a patient
    Given I am on medopad login page
    And I enter valid username
    And I enter valid password
    And I click on login
    And I enter a patient id in the search box
    And I click on search button
    And I click on a patient id in results
    When I click on add notes
    And I type the notes in the text area
    And I click on save
    Then I should see the notes added to the patients page

