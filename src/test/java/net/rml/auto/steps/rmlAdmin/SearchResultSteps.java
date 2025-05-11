package net.rml.auto.steps.rmlAdmin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.rml.auto.website.rml.pages.SearchResultPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Search Result Page definition class.
 */
public class SearchResultSteps {
    private static final Logger LOGGER = LogManager.getLogger(SearchResultSteps.class.getSimpleName());
    private SearchResultPage searchResultPage;

    public SearchResultSteps() {
        searchResultPage = new SearchResultPage();
    }

    @Given("^I go to Search Result Page by url$")
    public void iGoToSearchResultPageByUrl() {
        searchResultPage.goToSearchResultPageByUrl();
    }



    @When("^I open first Vehicle Details Page$")
    public void openFirstVehicleDetailsPage() {
        searchResultPage.openFirstVehicleDetailsPage();
    }

    @Then("^Search Result Page is open$")
    public void searchResultPageIsOpen() {
        searchResultPage.searchResultPageIsOpen();
    }

    @Then("^All pagination items should open on Search Result Page$")
    public void allPaginationItemsShouldOpenOnSearchResultPage() {

        Assert.assertTrue(searchResultPage.verificationOfAllPaginationItems(), "Pagination is not working correct on Search result page");

    }
}