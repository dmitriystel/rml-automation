package net.rml.auto.ui.utils;


import net.rml.auto.soap.OperationUtils;
import net.rml.auto.ui.config.UiConfig;
import net.rml.auto.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

/**
 * Class to manage the common Web Elements actions.
 */
public final class WebDriverActions {

    private static final Logger LOGGER = LogManager.getLogger(WebDriverActions.class.getName());
    private static final Duration DISPLAY_WAIT_TIME = Duration.ofSeconds(3);
    private static final String WEB_ELEMENT_NOT_FOUND = "Web element is not found in current page";
    private static final String ALERT_NOT_PRESENT_MSG = "Alert is not present";
    private static DriverManager webDriver = DriverManager.getInstance();

    /**
     * Private constructor for {@link WebDriverActions}.
     */
    private WebDriverActions() {
        // Default constructor.
    }

    /**
     * Verifies if Web element is visible.
     *
     * @param webElement WebElement.
     * @return True if the element is visible.
     */
    public static boolean isElementVisible(final WebElement webElement) {
        try {
            webDriver.setExplicitTimeWait(DISPLAY_WAIT_TIME,
                    UiConfig.getInstance().getPageLoadTime());
            webDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
            webDriver.setDefaultTimeWaits();
            return true;
        } catch (TimeoutException e) {
            LOGGER.info(WEB_ELEMENT_NOT_FOUND);
            return false;
        }
    }

    /**
     * Verifies if Alert prompt is present and switches to it.
     *
     * @return True if alert is present.
     */
    public static boolean isAlertPresent() {
        try {
            webDriver.getWebDriverWait().until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (NoAlertPresentException e) {
            LOGGER.error(ALERT_NOT_PRESENT_MSG);
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    /**
     * Sets a Input Field.
     *
     * @param webElement WebElement to wait and fill.
     * @param text       Text to fill.
     */
    public static void setInputField(final WebElement webElement, final String text) {
        webDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(text);
    }

    /**
     * Sets a Input Credit Card Number Field with type 'number'.
     *
     * @param webElement WebElement to wait and fill.
     * @param text       Text to fill to Credit Card Number.
     */
    public static void setCCNumberField(final WebElement webElement, final String text) {
        webDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.format("document.getElementById('CreditCardNumber').value='%s';", text));
    }

    public static void setCCNumberFieldByJSEx(final String xpath, final String text) {
        WebElement webl = webDriver.getWebDriver().findElement(By.xpath(xpath));
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        webl.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String formattext = "arguments[0].value='%s'";
        js.executeScript(String.format(formattext, text), webl);
    }

    /**
     * Waits and clicks on a webElement.
     *
     * @param webElement WebElement to wait and click.
     */
    public static void clickElement(final WebElement webElement) {
        webDriver.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * Clicks on a webElement.
     *
     * @param locator WebElement to wait and click.
     */
    public static void clickElement(final By locator) {
        webDriver.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
        webDriver.getWebDriver().findElement(locator).click();
    }

    /**
     * Hovers the mouse on element.
     *
     * @param webElement WebElement to wait and click.
     */
    public static void hoverElement(final WebElement webElement) {
        webDriver.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(webElement));
        Actions builder = new Actions(webDriver.getWebDriver());
        builder.moveToElement(webElement).perform();
    }

    /**
     * Waits until an element is presented DOM and it is visible.
     *
     * @param webElement the webElement to wait until present/visible at DOM.
     **/
    public static void waitUntilElementPresentAndVisible(final WebElement webElement) {
        try {
            webDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
        } catch (WebDriverException e) {
            LOGGER.error(String.format("Element %s is not present and visible: %s", webElement, e.getCause()));
            throw new WebDriverException(String.format("Element %s is not present and visible: %s", webElement, e.getCause()));
        }
    }

    /**
     * Waits until an element is presented DOM and it is visible after a few tries.
     *
     * @param retries number of tries until the element is attached to DOM.
     *
     * @param webElement the webElement to wait until present/visible at DOM.
     **/
    public static void waitUntilElementPresentAnVisibleAfterRetry(int retries, final WebElement webElement) {
        int retry = 1;
        while (retry <= retries) {
            LOGGER.info("Wait until webElement is displayed, #retry :" + retry);
            try {
                WebDriverActions.waitUntilElementPresentAndVisible(webElement);
                return;
            } catch (NoSuchElementException e) {
                LOGGER.warn(String.format("The web element is not present and visible, #retries %s", retry));
            }
        }
    }

    public static void waitUntilElementClickable(final WebElement webElement) {
        try {
            webDriver.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (WebDriverException e) {
            LOGGER.error(String.format("Element is not clickable: %s", e.getCause()));
            throw new WebDriverException("Element is not clickable: ", e.getCause());
        }
    }

    /**
     * Clears and sets a Input Field.
     *
     * @param webElement WebElement to wait and fill.
     * @param text       Text to fill.
     */
    public static void clearAndSetInputField(final WebElement webElement, final String text) {
        webDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * Clears with ctrl a and sets a Input Field.
     *
     * @param webElement WebElement to wait and fill.
     * @param text       Text to fill.
     */
    public static void clearWithCtrlAAndSetInputField(final WebElement webElement, final String text) {
        webDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        webElement.sendKeys(text);
    }

    /**
     * Clears with ctrl a and sets a Input Field and click enter.
     *
     * @param webElement WebElement to wait and fill.
     * @param text       Text to fill.
     */
    public static void clearWithCtrlAAndSetInputFieldAndClickEnter(final WebElement webElement, final String text) {
        webDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        webElement.sendKeys(text);
        webElement.sendKeys(Keys.ENTER);
    }

    /**
     * Clears and sets a Input Field.
     *
     * @param webElement WebElement to wait and fill.
     * @param text       Text to fill.
     */
    public static void selectTextWithCtrAKeywordAndSetInputField(final WebElement webElement, final String text) {
        webDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        webElement.sendKeys(text);
    }

    /**
     * Waits until element not visible.
     *
     * @param locator          locator of webElement.
     * @param timeOutInSeconds the time to update implicit wait.
     **/
    public static void waitUntilElementNotVisible(final By locator, final Duration timeOutInSeconds) {
        try {
            webDriver.setImplicitTimeWait(timeOutInSeconds);
            webDriver.getWebDriverWait()
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (WebDriverException e) {
            throw new WebDriverException(String.format("Element is visible yet:  %s ", e));
        } finally {
            webDriver.setImplicitTimeWait(UiConfig.getInstance().getImplicitTime());
        }
    }

    /**
     * Gets webElement from locator.
     *
     * @param locator locator Of WebElement.
     * @return {@link WebElement}
     */
    public static WebElement getWebElement(final By locator) {
        try {
            return webDriver.getWebDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Gets webElement from locator.
     *
     * @param locator          locator Of WebElement.
     * @param timeOutInSeconds the time to update implicit wait.
     * @return @return {@link WebElement}
     */
    public static WebElement getWebElement(final By locator, final Duration timeOutInSeconds) {
        try {
            webDriver.setImplicitTimeWait(timeOutInSeconds);
            return webDriver.getWebDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        } finally {
            webDriver.setImplicitTimeWait(UiConfig.getInstance().getImplicitTime());
        }
    }

    /**
     * Verifies if Web element is displayed.
     *
     * @param locator          locator Of WebElement.
     * @param timeOutInSeconds the time to update implicit wait.
     * @return True if the element is displayed.
     */
    public static boolean isElementDisplayed(final By locator, final Duration timeOutInSeconds) {
        try {
            webDriver.setImplicitTimeWait(timeOutInSeconds);
            webDriver.getWebDriver().findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            webDriver.setImplicitTimeWait(UiConfig.getInstance().getImplicitTime());
        }
    }

    /**
     * Waits until an element is presented DOM and it is visible.
     *
     * @param locator the webElement to wait until present/visible at DOM.
     * @return webElement visible.
     **/
    public static WebElement waitUntilElementPresentAndVisible(final By locator) {
        long startTime = System.currentTimeMillis();
        try {

            return webDriver.getWebDriverWait().until(ExpectedConditions
                    .visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            LOGGER.error("Element is not present and visible:", e.getCause());
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime) / 1000;
            LOGGER.info(String.format("Total Time waitUntilElementPresentAndVisible: %s second(s)", totalTime));
            throw new TimeoutException("Element is not present and visible:", e.getCause());
        }
    }

    /**
     * Checks if an element is present DOM and it is visible.
     *
     * @param locator the webElement to wait until present/visible at DOM.
     * @return true if element is present and visible. otherwise false
     **/
    public static boolean isElementVisibleAndPresent(final By locator) {
        long startTime = System.currentTimeMillis();
        try {

            WebElement element = webDriver.getWebDriverWait().until(ExpectedConditions
                    .visibilityOfElementLocated(locator));
            return element != null;
        } catch (TimeoutException e) {
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime) / 1000;
            LOGGER.info(String.format("Element is not present and visible, Total Time to waitUntilElementPresentAndVisible: %s second(s)", totalTime));
            return false;
        }
    }

    /**
     * Waits until an element becomes invisible on the page.
     *
     * @param locator       Locator of webElement  to wait for to become invisible.
     * @param timeoutMillis Maximum wait time in milliseconds before timing out.
     * @param pollingMillis Polling interval in milliseconds between each visibility check.
     */
    public static void waitForElementInvisibility(By locator, long timeoutMillis, long pollingMillis) {
        // Create a FluentWait object with the WebDriver, configuring the timeout and polling interval
        FluentWait wait = new FluentWait(webDriver.getWebDriver());

        try {
            wait.withTimeout(Duration.ofMillis(timeoutMillis))    // Sets the maximum wait time
                    .pollingEvery(Duration.ofMillis(pollingMillis))   // Sets the polling interval between checks
                    .ignoring(NoSuchElementException.class)        // Ignores exceptions if the element is not found
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator)); // Wait until the element is invisible
        } catch (TimeoutException e) {
            LOGGER.info(String.format("Element is still visible or was not found after: %s ms.", timeoutMillis));
        }
    }

    /**
     * Waits until an element becomes visible on the page.
     *
     * @param element       webElement  to wait for to become invisible.
     * @param timeoutMillis Maximum wait time in milliseconds before timing out.
     * @param pollingMillis Polling interval in milliseconds between each visibility check.
     */
    public static void waitForElementVisibility(final WebElement element, long timeoutMillis, long pollingMillis) {
        // Create a FluentWait object with the WebDriver, configuring the timeout and polling interval
        FluentWait wait = new FluentWait(webDriver.getWebDriver());

        try {
            wait.withTimeout(Duration.ofMillis(timeoutMillis))    // Sets the maximum wait time
                    .pollingEvery(Duration.ofMillis(pollingMillis))   // Sets the polling interval between checks
                    .ignoring(NoSuchElementException.class)        // Ignores exceptions if the element is not found
                    .until(ExpectedConditions.visibilityOf(element)); // Wait until the element is invisible
        } catch (TimeoutException | StaleElementReferenceException | NullPointerException e) {
            LOGGER.info(String.format("Element is still invisible or was not found: " +  e));
            OperationUtils.sleep(2000);
        }
    }

    /**
     * Moves the scroll bar tills to given element.
     *
     * @param element the webElement where the scroll goes
     * @param toTop   true indicating whether the top of the element is scrolled to the top of the window
     *                otherwise to bottom of the element is scrolled to the bottom of the window
     */
    public static void scrollIntoView(final WebElement element, final boolean toTop) {
        ((JavascriptExecutor) webDriver.getWebDriver())
                .executeScript("arguments[0].scrollIntoView(" + toTop + ");", element);
    }

    /**
     * Moves the scroll bar tills to into view element.
     *
     * @param element the webElement where the scroll goes
     */
    public static void scrollIntoView(final WebElement element) {
        ((JavascriptExecutor) webDriver.getWebDriver())
                .executeScript("arguments[0].scrollIntoView()", element);
    }

    /**
     * Click present element having permanent overlay.
     *
     * @param element the webElement click having permanent overlay
     */
    public static void clickPresentElementHavingPermanentOverlay(final WebElement element) {
        ((JavascriptExecutor) webDriver.getWebDriver())
                .executeScript("arguments[0].click();", element);
    }

    /**
     * Scroll down the web page at the bootom of the page.
     */
    public static void scrollDownAtBottomOfPage() {
        ((JavascriptExecutor) webDriver.getWebDriver())
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Moves and clicks on an element.
     *
     * @param webElement WebElement to wait and click.
     */
    public static void moveAndClickElement(final WebElement webElement) {
        Actions actions = new Actions(webDriver.getWebDriver());
        actions.moveToElement(webElement).click().build().perform();

    }

    /**
     * Moves on an element.
     *
     * @param webElement WebElement to wait and click.
     */
    public static void moveOnElement(final WebElement webElement) {
        Actions actions = new Actions(webDriver.getWebDriver());
        actions.moveToElement(webElement);
    }

    /**
     * Verifies if Web element is displayed.
     *
     * @param webElement WebElement to check if display or not
     * @return True if the element is displayed otherwise false.
     */
    public static boolean isElementDisplayed(final WebElement webElement) {
        try {
            return webElement.isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void goToURL(String url) {
        webDriver.getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        webDriver.getWebDriver().navigate().to(url);
    }

    public static void refreshWindow() {
        webDriver.getWebDriver().navigate().refresh();
    }

    public static void clickEnter(final WebElement webElement) throws InterruptedException {
        webDriver.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(Keys.TAB);
        webElement.sendKeys(Keys.ENTER);
    }

    /**
     * Waits until certain numbers of window will be opened.
     *
     * @param numberOfWindows expected number of windows will be open
     */
    public static void waitForNewWindowOrTab(int numberOfWindows) {
        try {
            webDriver.getWebDriverWait().until(numberOfWindowsToBe(numberOfWindows));
        } catch (WebDriverException e) {
            String errorMessage = String.format("Number of windows is not %s : %s", numberOfWindows, e.getCause());
            LOGGER.error(errorMessage);
            throw new WebDriverException(errorMessage);
        }
    }
}
