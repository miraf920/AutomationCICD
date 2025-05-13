@tag
Feature: Error validation
  I want to use this template for my feature file
  

  @ErrorValidation
  Scenario Outline: Negative test for Login error
    Given I landed on Ecommerece Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name                        | password  |
      | wrongRonald_Joe@hotmail.com | Qwer123$$ |
