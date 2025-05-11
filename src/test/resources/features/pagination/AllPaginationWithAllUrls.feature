Feature: Check all pagination with all urls

  @smoke
  Scenario Outline: SRP. Check pagination and vehicle details for each site
    Given I go to Search Result Page by "<baseUrl>" url
    When I open first Vehicle Details Page
     Then Status code of VehicleDetailsPage should be "200"
     And There should not be error message "Unfortunately, we couldn’t find this page" on VehicleDetailsPage
    When I go back to the previous page
    Then Search Result Page is open
    And All pagination items should open on Search Result Page

    Examples:
      | baseUrl                                                                              |
      | https://www.hondaofrutland.com/search-result-page/used-vehicles                      |
      | https://hondaofrutland.sitebuilder.runmylease.com/search-result-page/used-vehicles   |
      | https://www.carstoreusa.com/search-result-page/used-vehicles                         |
      | https://carstoreusa.sitebuilder.runmylease.com/search-result-page/used-vehicles      |
      | https://www.msautodirect.com/search-result-page/used-vehicles                        |
      | https://msautodirect.sitebuilder.runmylease.com/search-result-page/used-vehicles     |
      | https://www.offleasemaryland.com/search-result-page/used-vehicles                    |
      | https://offleasemaryland.sitebuilder.runmylease.com/search-result-page/used-vehicles |
      | https://www.premiervinfast.com/search-result-page/used-vehicles                      |
      | https://premiervinfast.sitebuilder.runmylease.com/search-result-page/used-vehicles   |