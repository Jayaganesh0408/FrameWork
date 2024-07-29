Feature: Login error functionality
	@Regression
  Scenario Outline: Verify negative login 
  	Given User landed on Ecommerce Page
    When Loggin in with <userName> and <password>
    Then "Incorrect email or password." popup is shown

     Examples: 
      | userName          | password   |
      | Ben0408@gmail.com |  Ben#08    |
      | Gwen0408@gmail.com|  Gwen#48   |
