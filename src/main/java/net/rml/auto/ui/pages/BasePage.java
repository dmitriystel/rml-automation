package net.rml.auto.ui.pages;

import net.rml.auto.ui.webdriver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Abstract class to represent and encapsulate common attributes/actions of base page.
 */
public abstract class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    // Locator for vehicle cards on the search result page
    protected By vehicleCard = By.xpath("//*[@role='listitem' and contains(@aria-label, 'vehicle card')]");

    /*
     * Initializes an instance of BasePage and sets up WebDriver and WebDriverWait.
     */
    public BasePage() {
        DriverManager.getInstance().initializeDriver();  // Initializes the WebDriver
        webDriver = DriverManager.getInstance().getWebDriver();  // Get the WebDriver instance
        webDriverWait = DriverManager.getInstance().getWebDriverWait();  // Get the WebDriverWait instance
        PageFactory.initElements(webDriver, this);  // Initialize PageFactory elements
    }

    /**
     * Abstract method to be implemented in child classes to wait for the page to fully load.
     */
    public abstract void waitForLoad();
}