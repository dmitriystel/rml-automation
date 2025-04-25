package net.rml.auto.website.rml.pages;

import net.rml.auto.ui.pages.BasePage;
import net.rml.auto.ui.utils.WebDriverActions;

/**
 * It represents the Search Result Page.
 */
public class SearchResultPage extends BasePage {



    /**
     * Initializes an instance of {@Link BasePage}
     *
     */
    public SearchResultPage() {
        waitForLoad();
    }

    @Override
    public void waitForLoad() {

    }

    public void goToSrpByUrl(){
        WebDriverActions.goToURL("https://www.uppervalleyhonda.com/search-result-page/used-vehicles?page=1");
    }



}
