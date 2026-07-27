Feature: API Login feature


  @smoke @regression
  Scenario: User is able to login successfully with valid credentials

    When I send a "POST" request to "loginAPI" with payload "validLoginCredentials"
    Then the response status code should be 200
    Then the response should contain key "token"
    Then the response should contain key "userId"
    And I save the response field "token" as "authToken"
    And I save the response field "userId" as "userId"