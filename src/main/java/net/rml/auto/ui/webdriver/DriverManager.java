package net.rml.auto.ui.webdriver;

import net.rml.auto.ui.config.UiConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

/**
 * Class to manage and perform actions with the Web Driver
 */
public class DriverManager {
    private static DriverManager instance;

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    /**
            * Initializes an instance of {@link DriverManager}.
            */
    private DriverManager() {
        initializeDriver();
    }

    /**
            * Initializes the Singleton Driver Manager instance.
            *
            * @return singleton instance.
            */
    public static DriverManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DriverManager();
        }
        return instance;
    }

    /**
            * Initializes the Web Driver instance.
     */
    public void initializeDriver() {
        webDriver = DriverFactory.getDriver(UiConfig.getInstance().getBrowser());
        webDriver.manage().window().maximize();
        setDefaultTimeWaits();
    }

    /**
            * Quits the Web Driver instance.
     */
    public void quitDriver() {
        this.webDriver.quit();
        this.webDriver = null;
    }

    /**
            * Gets the Web Driver instance.
     *
             * @return Web Driver instance.
     */
    public WebDriver getWebDriver() {
        if (Objects.isNull(webDriver)) {
            initializeDriver();
        }
        return webDriver;
    }

    /**
            * Gets the current web page title.
            *
            * @return current web page title string.
     */
    public String getCurrentPageTitle() {
        return webDriver.getTitle();
    }

    /**
            * Sets implicit time wait.
            *
            * @param implicitTimeWait implicit time wait in seconds.
            */
    public void setImplicitTimeWait(final Duration implicitTimeWait) {
//    public void setImplicitTimeWait(final long implicitTimeWait) {
        webDriver.manage().timeouts().implicitlyWait(implicitTimeWait);
    }

    /**
            * Sets explicit time wait.
            *
            * @param explicitTimeWait explicit time wait in seconds.
            * @param sleepTimeWait    sleep time wait in seconds.
            */
    public void setExplicitTimeWait(final Duration explicitTimeWait, final Duration  sleepTimeWait) {
        webDriverWait = new WebDriverWait(webDriver, explicitTimeWait, sleepTimeWait);
    }

    /**
            * Sets page load time wait.
     *
             * @param pageLoadTimeWait page load time wait in seconds.
     */
    public void setPageLoadTimeWait(final Duration  pageLoadTimeWait) {
        webDriver.manage().timeouts().pageLoadTimeout(pageLoadTimeWait);
    }

    /**
            * Gets the Web Driver Wait instance.
            *
            * @return Web Driver Wait instance.
            */
    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    /**
     * Sets the default time waits from environment configuration file.
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
}
