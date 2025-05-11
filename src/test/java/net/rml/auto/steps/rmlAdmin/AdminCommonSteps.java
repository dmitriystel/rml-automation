package net.rml.auto.steps.rmlAdmin;

import io.cucumber.java.en.Given;
import net.rml.auto.website.rml.pages.AdminCommonPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminCommonSteps {
    private static final Logger LOGGER = LogManager.getLogger(AdminCommonSteps.class.getSimpleName());
    private AdminCommonPage adminCommonPage;

    public AdminCommonSteps() {
        adminCommonPage = new AdminCommonPage();
    }

    @Given("^I go back to the previous page$")
    public void goBackToPreviosPage() {
        adminCommonPage.goBackToPreviosPage();
    }
}
