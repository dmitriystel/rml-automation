package net.rml.auto.steps.rmlAdmin;
import io.cucumber.java.en.When;
import net.rml.auto.website.rml.pages.SearchResultPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Search Result Page definition class.
 */
public class SearchResultSteps {
    private static final Logger LOGGER = LogManager.getLogger(SearchResultSteps.class.getSimpleName());
    private SearchResultPage searchResultPage;
//    private SoftAssert softAssert;

    public SearchResultSteps(){
        searchResultPage  = new SearchResultPage();
    }

    @When("^I go to Search Result Page by url")
    public void iGoToSRCPageByUrl(){

    }





}
