package net.rml.auto.ui.pages;


import net.rml.auto.ui.webdriver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract class to represent and encapsulate common attributes/actions of base page.
 */
public abstract class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    // main element on search result page
    protected By vehicleCard = By.xpath("//*[@role='listitem' and contains(@aria-label, 'vehicle card')]");

    /**
     * Initializes an instance of {@Link BasePage}
     */
    public BasePage() {
        webDriver = DriverManager.getInstance().getWebDriver();
        webDriverWait = DriverManager.getInstance().getWebDriverWait();
        PageFactory.initElements(DriverManager.getInstance().getWebDriver(), this);
        waitForLoad();
    }

    /**
     * Waits until the page is fully loaded.
     */
    public abstract void waitForLoad();
}
