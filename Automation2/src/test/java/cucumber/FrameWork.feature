Feature: Title of your feature
  I want to use this template for my feature file
  Background: 
  Given User landed on Ecommerce Page
	@Smoke
  Scenario Outline: Submitting order
    Given Loggin in with <userName> and <password>
    When User add product <productName> to cart
    And User checkout <productName> and submit order
    Then "Thankyou for the order." message is shown in confirmation page

    Examples: 
      | userName          | password     | productName     |
      | Ben0408@gmail.com |  Ben#0408    | ZARA COAT 3     |
      | Gwen0408@gmail.com|  Gwen#0408   | ADIDAS ORIGINAL |