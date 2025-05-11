package net.rml.auto.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.rml.auto.ui.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestHooks {
    private static final Logger LOGGER = LogManager.getLogger(TestHooks.class);

    public TestHooks() {
        LOGGER.info("✅ TestHooks constructor called — hook class is loaded.");
    }

    @Before
    public void beforeScenario() {
        LOGGER.info("🔥 BeforeScenario hook: initializing WebDriver...");
        if (DriverManager.getInstance().getWebDriver() == null) {
            DriverManager.getInstance().initializeDriver();
        }
    }

    @After
    public void afterScenario() {
        LOGGER.info("🔥 AfterScenario hook: quitting WebDriver...");
        if (DriverManager.getInstance().getWebDriver() != null) {
            LOGGER.info("✅ WebDriver instance found, quitting...");
            DriverManager.getInstance().quitDriver();
        } else {
            LOGGER.warn("⚠️ WebDriver was null in After hook.");
        }
    }
}