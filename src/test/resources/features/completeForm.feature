Feature: Complete the cycles in the form

  Background: Login
    Given user navigate to web site login page

  @completeForm
  Scenario: Complete form in the evalart app
    When user login in the app whit the user "950326" and password "10df2f32286b7120Mi00LTYyMzA1OQ==30e0c83e6c29f1c3"
    And the user can see the login text
    And the user complete the form
    Then validates that the test was successfully completed