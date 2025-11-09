Feature: Place the order in website and verify the order on confirmation page
  Creating a web purchase order

  Background:
    Given I landed on the Ecom page

  Scenario Outline: Create a order and verify confimation message
    Given Logged in with username <username> and password <password>
    When I add a product <product> to cart
    And checkout and Submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page
    Examples:
      | username  | password | product |
      | omkarp800@gmail.com | Omkar@5137 | ZARA COAT 3|
      | omkpatil@omk.com | Omkar@5137 | ADIDAS ORIGINAL |