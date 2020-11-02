Feature: User SignUpTest

  Scenario: User is able to login in to the web-site with remember me option
    Given User is open the homepage
    And User close the popup
    And User close Me Want popup
    And User click Account button
    And User click Login button
    And User clicks Not yet a customer? button
    Then User is redirected to SignUp page
    And User fills the email field
    And User fills the password field
    And User fills repeat password field
    And User click sequrity question dropdown
    And User fill sequrity question answer
    And User clock Registration button
    Then User is successfully registrated - pop up is displayed