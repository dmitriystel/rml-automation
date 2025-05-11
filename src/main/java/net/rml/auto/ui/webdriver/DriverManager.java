package net.rml.auto.ui.webdriver;

import net.rml.auto.ui.config.UiConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

/**
 * Class to manage and perform actions with the Web Driver
 */
public class DriverManager {
    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class.getSimpleName());

    // 🔄 Changed from Singleton instance to ThreadLocal (fixes individual run issue)
    private static final ThreadLocal<DriverManager> instance = ThreadLocal.withInitial(DriverManager::new);

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private DriverManager() {
        // Empty constructor
    }

    public static DriverManager getInstance() {
        return instance.get();
    }

    public void initializeDriver() {
        if (webDriver == null) {
            LOGGER.info("Initializing WebDriver...");
            webDriver = DriverFactory.getDriver(UiConfig.getInstance().getBrowser());
            webDriver.manage().window().maximize();
            setDefaultTimeWaits();
        } else {
            LOGGER.warn("WebDriver is already initialized.");
        }
    }

    public void quitDriver() {
        if (webDriver != null) {
            try {
                LOGGER.info("Attempting to quit WebDriver...");

                // Attempt to close all open windows
                for (String windowHandle : webDriver.getWindowHandles()) {
                    webDriver.switchTo().window(windowHandle);
                    webDriver.close();
                    LOGGER.info("Closed browser window with handle: " + windowHandle);
                }

                // Now quit the WebDriver session
                webDriver.quit();
                LOGGER.info("✅ WebDriver quit successfully.");
                webDriver = null; // Important: mark it null
            } catch (Exception e) {
                LOGGER.error("⚠️ Failed to quit WebDriver: " + e.getMessage());
            } finally {
                instance.remove(); // 🔄 Clean up ThreadLocal instance
            }
        } else {
            LOGGER.warn("⚠️ WebDriver is null, cannot quit.");
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public String getCurrentPageTitle() {
        return webDriver != null ? webDriver.getTitle() : "WebDriver not initialized";
    }

    public void setImplicitTimeWait(final Duration implicitTimeWait) {
        if (webDriver != null) {
            webDriver.manage().timeouts().implicitlyWait(implicitTimeWait);
        }
    }

    public void setExplicitTimeWait(final Duration explicitTimeWait, final Duration sleepTimeWait) {
        if (webDriver != null) {
            webDriverWait = new WebDriverWait(webDriver, explicitTimeWait, sleepTimeWait);
        }
    }

    public void setPageLoadTimeWait(final Duration pageLoadTimeWait) {
        if (webDriver != null) {
            webDriver.manage().timeouts().pageLoadTimeout(pageLoadTimeWait);
        }
    }

    public void setDefaultTimeWaits() {
        Duration implicitTimeWait = UiConfig.getInstance().getImplicitTime();
        Duration explicitTimeWait = UiConfig.getInstance().getExplicitTime();
        Duration sleepTimeWait = UiConfig.getInstance().getSleepTime();
        Duration pageTimeoutTimeWait = UiConfig.getInstance().getPageLoadTime();
        setImplicitTimeWait(implicitTimeWait);
        setExplicitTimeWait(explicitTimeWait, sleepTimeWait);
        setPageLoadTimeWait(pageTimeoutTimeWait);
    }
}