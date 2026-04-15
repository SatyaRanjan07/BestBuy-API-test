Feature: Test create product API

  @post @createProduct
  Scenario Outline: Create a new product
    Given I set base URI as "http://localhost:3030" and I have valid payload with required headers
    When I send post request to "/products"
    Then I should get status code as <statusCode>
    And response should contains "id"
    And response should have product name as "<productName>"
    And response should have product model as "<model>"

    Examples:
      | statusCode | model | productName |
      | 201        | NP12345 | New Product |

    @get @getProductDetails
    Scenario Outline: Fetch data of a product
      Given I have valid payload with required headers
      When I send get request with <id>
      Then I should get the status code as <statusCode>
      And I should get the product id as <id>
      And the response should have product name as "<productName>"
      And the response should have product model as "<model>"

    Examples:
      | statusCode | model   | productName | id      |
      | 200        | NP12345 | New Product | 9999679 |

  @schema
  Scenario Outline: Validating the schema of Post API
    Given I set base URI as "http://localhost:3030" and I have valid payload with required headers
    When I send post request to "/products"
    Then I should get status code as <statusCode>
    And response payload should have correct schema
    Examples:
      | statusCode |
      | 201        |