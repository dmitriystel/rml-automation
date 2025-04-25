package net.rml.auto.ui.config;

import net.rml.auto.core.config.PropertiesReader;
import net.rml.auto.core.config.RmlConfig;
import net.rml.auto.ui.webdriver.DriverType;
//import org.json.simple.JSONObject;

import java.time.Duration;
import java.util.Objects;

/**
        * Class to initialize the environment values for Beast UI.
        */
public final class UiConfig {

    private static final String PROP_FILE = "gradle.properties";
    private static final String BROWSER = "browser";
    private static final String SELENIUM_HUB_URL = "seleniumHubUrl";
    private static final String SELENIUM_HUB_PORT= "seleniumHubPort";
    private static final String DOCKER_RUN = "dockerRun";
    private static final String PORTAL = "Portal";
    private static final String USER = "User";
    private static final String BASE_URL = "BaseUrl";
    private static final String PASSWORD = "Password";
    private static final String IMPLICIT_TIME = "implicitTimeWait";
    private static final String EXPLICIT_TIME = "explicitTimeWait";
    private static final String SLEEP_TIME = "sleepWaitTime";
    private static final String PAGE_LOAD_TIME = "pageLoadTimeWait";

    private PropertiesReader propertiesReader;
    private static UiConfig instance;
//    private JSONObject portalConfig;

    /**
            * Initializes an instance of {@link UiConfig}.
            */
    private UiConfig() {
        propertiesReader = new PropertiesReader(PROP_FILE);
//        portalConfig = RmlConfig.getInstance().getConfigObject(PORTAL);
    }

    /**
            * Initializes the Singleton UI Config instance.
            *
            * @return singleton instance.
            */
    public static synchronized UiConfig getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UiConfig();
        }
        return instance;
    }

    /**
     * Gets the current Browser name.
     *
     * @return Driver type browser value.
     */
    public DriverType getBrowser() {
        return DriverType.valueOf(propertiesReader.getEnv(BROWSER).toUpperCase());
    }

    /**
            * Gets the Selenium Hub URL.
     *
             * @return Selenium Hub URL.
     */
    public String getSeleniumHubUrl() {
        return propertiesReader.getEnv(SELENIUM_HUB_URL);
    }

    /**
            * Gets the Selenium Hub Port.
     *
             * @return Selenium Hub Port.
     */
    public String getSeleniumHubPort() {
        return propertiesReader.getEnv(SELENIUM_HUB_PORT);
    }

    /**
            * Gets the Docker Run attribute, true if execution will be in a docker otherwise false.
            *
            * @return Docker Run attribute.
     */
    public boolean isDockerRun() {
        return Boolean.valueOf(propertiesReader.getEnv(DOCKER_RUN));
    }

//    /**
//            * Gets the username from environment config file.
//     *
//             * @return username string.
//            */
//    public String getUser() {
//        return (String) portalConfig.get(USER);
//    }

//    /**
//            * Gets the password from environment config file.
//     *
//             * @return password string.
//            */
//    public String getPassword() {
//        return (String) portalConfig.get(PASSWORD);
//    }

//    /**
//            * Gets the UI base url value.
//            *
//            * @return base url value.
//     */
//    public String getBaseUrl() {
//        return (String) portalConfig.get(BASE_URL);
//    }

    /**
            * Gets the implicit time wait for web driver default configuration.
     *
             * @return implicit time wait value.
            */
    public Duration getImplicitTime() {
        return Duration.ofSeconds(Long.parseLong(propertiesReader.getEnv(IMPLICIT_TIME)));
    }

    /**
            * Gets the explicit time wait for web driver default configuration.
     *
             * @return explicit time wait value.
            */
    public Duration getExplicitTime() {
        return Duration.ofSeconds(Long.parseLong(propertiesReader.getEnv(EXPLICIT_TIME)));
    }

    /**
            * Gets the sleep time wait for web driver default configuration.
     *
             * @return sleep time wait value.
            */
    public Duration getSleepTime() {
        return Duration.ofSeconds(Long.parseLong(propertiesReader.getEnv(SLEEP_TIME)));
    }

    /**
            * Gets the page load time wait for web driver default configuration.
     *
             * @return page load time wait value.
     */
    public Duration getPageLoadTime() {
        return Duration.ofSeconds(Long.parseLong(propertiesReader.getEnv(PAGE_LOAD_TIME)));
    }
}