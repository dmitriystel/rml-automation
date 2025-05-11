Feature: Check all pagination with one url

  @smoke
  Scenario: 1 - SRP. Check all pagination without the total number of used vehicles.
    Given I go to Search Result Page by "https://www.uppervalleyhonda.com/search-result-page/used-vehicles?page=1" url
    When I open first Vehicle Details Page
    Then Status code of VehicleDetailsPage should be "200"
    And There should not be error message "Unfortunately, we couldn’t find this page" on VehicleDetailsPage
    When I go back to the previous page
    Then Search Result Page is open
    And All pagination items should open on Search Result Page

