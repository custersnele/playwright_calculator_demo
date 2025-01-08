Feature: Calculation sum
  As a user
  I want to do calculations
  So that I can verify the results

  Scenario: Sum two numbers
    Given I open the calculator page
    When I set the first number to "5"
    And I set the second number to "3"
    And I select "Sum"
    And I click on Calculate
    Then the result should be "Result: 8"

  Scenario: Sum two numbers with doubling first number
    Given I open the calculator page
    When I set the first number to "5"
    And I set the second number to "3"
    And I check Double the first number
    And I select "Sum"
    And I click on Calculate
    Then the result should be "Result: 13"
