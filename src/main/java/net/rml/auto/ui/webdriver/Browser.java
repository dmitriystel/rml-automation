package net.rml.auto.ui.webdriver;

import org.openqa.selenium.WebDriver;

/**
 * Interface class to support web browsers.
 */
public interface Browser {

    /**
     * Gets a web driver interface of a specific browser.
     *
     * @return Web driver instance.
     */
    WebDriver getDriver();
}