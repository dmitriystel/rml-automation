package net.rml.auto.website.rml.pages;

import net.rml.auto.ui.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminCommonPage  extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(AdminCommonPage.class.getSimpleName());
    @Override
    public void waitForLoad() {
    }

    public void goBackToPreviosPage(){
        webDriver.navigate().back();
    }

}
