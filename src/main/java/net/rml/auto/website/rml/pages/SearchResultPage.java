package net.rml.auto.website.rml.pages;

import net.rml.auto.ui.pages.BasePage;
import net.rml.auto.ui.utils.WebDriverActions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(SearchResultPage.class.getSimpleName());
    private boolean cookiesChecked = false;  // Track if cookies were already checked
    private String paginationItem = "//*[@role='button' and @aria-label='Page %d']";


    @FindBy(xpath = "//button[@aria-label='Allow all cookies']")
    private WebElement closeCookiesBtn;

    @FindBy(xpath = "//*[@role='list' and @aria-label='Vehicles list']")
    private WebElement vehiclesListElement;

    public SearchResultPage() {
    }

    @Override
    public void waitForLoad() {
        allowAllCookies();
        WebDriverActions.isElementDisplayed(vehiclesListElement);
        LOGGER.info("SearchResultPage is open");
    }

    private void allowAllCookies() {
        if (!cookiesChecked) {  // Only check and close cookies if not already done
            try {
                WebDriverActions.isElementVisible(closeCookiesBtn);
                closeCookiesBtn.click();
                LOGGER.info("Closed cookies banner.");
                cookiesChecked = true;  // Mark cookies as checked
            } catch (Exception ignored) {
                LOGGER.info("No cookies banner to close.");
            }
        }
    }

    public void goToSearchResultPageByUrl(final String url) {
        WebDriverActions.goToURL(url);
//        waitForLoad();
    }

    public void openFirstVehicleDetailsPage() {
        WebDriverActions.clickElement(vehiclesListElement);
    }

    public void searchResultPageIsOpen() {
        waitForLoad();
    }

    public boolean verificationOfAllPaginationItems(){
        boolean flag = true;
        VehicleDetailsPage vehicleDetailsPage;
        AdminCommonPage adminCommonPage;
        try {
            waitForLoad();
        int paginationItem = 2;
        do {
            clickNextPaginationItemIfExists(paginationItem);
            openFirstVehicleDetailsPage();
            vehicleDetailsPage = new VehicleDetailsPage();
            if (vehicleDetailsPage.getStatusCode() == 200) {
            } else {
                LOGGER.info(String.format("Wrong status code of VehicleDetailsPage %s for pagination item %d", vehicleDetailsPage.getStatusCode(), paginationItem));
                flag = false;
                break;}
            if (!vehicleDetailsPage.hasErrorMessage("Unfortunately, we couldn’t find this page")) {
            } else {
                LOGGER.info(String.format("There is an error message on VehicleDetailsPage for pagination item %d", paginationItem));
                flag = false;
                break;}
            adminCommonPage = new AdminCommonPage();
            adminCommonPage.goBackToPreviosPage();
            waitForLoad();

            if (!clickNextPaginationItemIfExists(++paginationItem))break;
        } while (true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    private boolean clickNextPaginationItemIfExists(int pageNum) {
        try {

            By test = By.xpath(String.format(paginationItem, pageNum));
            System.out.println("Xpath for pagination item is: " + test);

            WebDriverActions.clickElement(By.xpath(String.format(paginationItem, pageNum)));
            LOGGER.info(String.format("Pagination item %d is open", pageNum));
            return true;
        } catch (Exception e) {
            LOGGER.info(String.format("Pagination item %d not found", pageNum));
            return false;
        }
    }
}