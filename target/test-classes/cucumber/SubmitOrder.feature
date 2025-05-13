
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  
  Background: 
  Given I landed on Ecommerece Page

  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given Logged in with username <name> and password <password>
    When I add a product <productName> from Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is diaplyed on ConfirmationPage

    Examples: 
      | name                   | password       | productName |
      | Ronald_Joe@hotmail.com | Qwer123$$      | ZARA COAT 3 |
     
