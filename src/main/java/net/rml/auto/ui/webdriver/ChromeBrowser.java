package net.rml.auto.ui.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.rml.auto.core.config.RmlConfig;
import net.rml.auto.core.utils.PathUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
        * Class to instantiate a Chrome web driver.
 */
public class ChromeBrowser implements Browser {

    /**
            * Initializes an instance of {@link ChromeBrowser}.
            */
    public ChromeBrowser() {
    }

    /**
            * {@inheritDoc}
     */
    @Override
    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(getChromeOptions());
    }

    /**
            * Gets chrome options.
     *
             * @return the chrome options
     */
    private ChromeOptions getChromeOptions() {
        // Setting new download directory path
        Map<String, Object> chromePrefs = new HashMap<String, Object>();

        // Use File.separator as it will work on any OS
        chromePrefs.put("download.default_directory",
                PathUtils.getRelativePath(RmlConfig.getInstance().getGeneratedFolder()));

        // Adding capabilities to ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--remote-allow-origins=*");

        return options;
    }
}