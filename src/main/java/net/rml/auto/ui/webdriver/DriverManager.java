package net.rml.auto.ui.webdriver;

import net.rml.auto.ui.config.UiConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
 * Class to manage and perform actions with the Web Driver
 */
public class DriverManager {
    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class.getSimpleName());

    // Thread-local WebDriver and WebDriverWait for thread safety
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> webDriverWait = new ThreadLocal<>();

    // Private static instance to follow Singleton design pattern
    private static DriverManager instance;

    private DriverManager() {
        // Empty constructor to ensure only one instance of DriverManager
    }

    // Get the instance of DriverManager (Singleton pattern)
    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        return instance;
    }

    /*
     * Initializes the WebDriver if it hasn't been initialized yet.
     */
    public void initializeDriver() {
        if (webDriver.get() == null) {
            LOGGER.info("Initializing WebDriver for thread: " + Thread.currentThread().getId());
            WebDriver driver = DriverFactory.getDriver(UiConfig.getInstance().getBrowser());
            driver.manage().window().maximize();
            webDriver.set(driver);
            setDefaultTimeWaits();
        } else {
            LOGGER.warn("WebDriver already initialized for thread: " + Thread.currentThread().getId());
        }
    }

    /*
     * Quits the WebDriver session and cleans up resources.
     */
    public void quitDriver() {
        WebDriver driver = webDriver.get();
        if (driver != null) {
            try {
                LOGGER.info("Attempting to quit WebDriver for thread: " + Thread.currentThread().getId());

                // Close all open browser windows
                for (String windowHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(windowHandle);
                    driver.close();
                    LOGGER.info("Closed browser window with handle: " + windowHandle);
                }

                driver.quit();
                LOGGER.info("✅ WebDriver quit successfully for thread: " + Thread.currentThread().getId());
            } catch (Exception e) {
                LOGGER.error("⚠️ Failed to quit WebDriver for thread: " + Thread.currentThread().getId() + ": " + e.getMessage());
            } finally {
                webDriver.remove(); // Clean up thread-local WebDriver
                webDriverWait.remove(); // Clean up thread-local WebDriverWait
            }
        } else {
            LOGGER.warn("⚠️ WebDriver is null for thread: " + Thread.currentThread().getId() + ", cannot quit.");
        }
    }

    /*
     * Returns the current thread's WebDriver instance.
     * @return WebDriver for the current thread.
     */
    public WebDriver getWebDriver() {
        return webDriver.get();
    }


    /*
     * Returns the current thread's WebDriverWait instance.
     * @return WebDriverWait for the current thread.
     */
    public WebDriverWait getWebDriverWait() {
        return webDriverWait.get();
    }

    /*
     * Sets default time waits for the WebDriver instance based on configuration.
     */
    public void setDefaultTimeWaits() {
        Duration implicitTimeWait = UiConfig.getInstance().getImplicitTime();
        Duration explicitTimeWait = UiConfig.getInstance().getExplicitTime();
        Duration sleepTimeWait = UiConfig.getInstance().getSleepTime();
        Duration pageTimeoutTimeWait = UiConfig.getInstance().getPageLoadTime();
        setImplicitTimeWait(implicitTimeWait);
        setExplicitTimeWait(explicitTimeWait, sleepTimeWait);
        setPageLoadTimeWait(pageTimeoutTimeWait);
    }

    public void setImplicitTimeWait(final Duration implicitTimeWait) {
        WebDriver driver = webDriver.get();
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(implicitTimeWait);
        }
    }

    public void setExplicitTimeWait(final Duration explicitTimeWait, final Duration sleepTimeWait) {
        WebDriver driver = webDriver.get();
        if (driver != null) {
            WebDriverWait wait = new WebDriverWait(driver, explicitTimeWait, sleepTimeWait);
            webDriverWait.set(wait);
        }
    }

    public void setPageLoadTimeWait(final Duration pageLoadTimeWait) {
        WebDriver driver = webDriver.get();
        if (driver != null) {
            driver.manage().timeouts().pageLoadTimeout(pageLoadTimeWait);
        }
    }
}