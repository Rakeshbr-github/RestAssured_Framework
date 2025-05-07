Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being addes successfully using Add place Api
  Given Add place payload "<name>" "<language>" "<address>"
  When  user calls "AddplaceAPI" with "POST" http request
  Then the API call is success with status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"	
  And Verify place_id created maps to "<name>" using "getPlaceAPI"
  
Examples:
    |   name  | Language | address |
    | Rak     | English  |Bengaluru|

@DeletePlace @Regression
Scenario: Verify if delete place functionality is working fine
   Given DeletePlacePayload
  When  user calls "deletePlaceAPI" with "POST" http request
  Then the API call is success with status code 200
  And "status" in response body is "OK"   