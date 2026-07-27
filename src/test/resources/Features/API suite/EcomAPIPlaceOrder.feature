Feature: End to End Ecom Order Flow

  Background:
    Given the API client is configured

  @APIe2e @smoke @regression
  Scenario: User is able to login and successfully place an order

    When I send a "POST" request to "loginAPI" with payload "validCredentials"
    Then the response status code should be 200
    And the response should contain key "token"
    And the response should contain key "userId"
    And I save the response field "token" as "authToken"
    And I save the response field "userId" as "userId"

    When I send a "POST" request to "productAPI" using auth token "authToken"
    Then the response status code should be 200
    And the response should match schema "productListSchema"
    And the response message should be "All Products fetched Successfully"
    And I save the response field "products[0].productId" as "productId"

    When I send a "POST" request to "orderAPI" with payload "placeOrderPayload" using auth token "authToken"
    Then the response status code should be 201
    And the response should contain key "productOrderId"
    And the response message should be "Order Placed Successfully"
    And I save the response field "productOrderId" as "orderId"

    When I send a "GET" request to "getOrderAPI" using auth token "authToken" with path param "orderId" as "orderId"
    Then the response status code should be 200
    And the response should match schema "orderDetailsSchema"
    And the response message should be "Orders fetched for customer Successfully"

    When I send a "DELETE" request to "deleteOrderAPI" using auth token "authToken" with path param "orderId" as "orderId"
    Then the response status code should be 200
    And the response message should be "Orders Deleted Successfully"