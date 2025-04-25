package net.rml.auto.core.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

/**
        * It is in charged to read the configuration file.
 */
public class PropertiesReader {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesReader.class.getSimpleName());
    private static final String ENCODING = "UTF-8";

    private Properties properties;

    /**
            * Initializes an instance of {@link PropertiesReader}.
            *
            * @param filepath properties file path String.
     */
    public PropertiesReader(final String filepath) {
        LOGGER.info(String.format("Reading config file from path:%s", filepath));
        this.init(filepath);
    }

    /**
            * Reads the config file.
            *
            * @param filepath config properties file path String.
            */
    private void init(final String filepath) {
        try (InputStreamReader inputFile = new InputStreamReader(new FileInputStream(filepath), ENCODING)) {
            this.properties = new Properties();
            this.properties.load(inputFile);
        } catch (FileNotFoundException e) {
            LOGGER.warn("The properties file couldn't be found", e);
            throw new ExceptionInInitializerError(e);
        } catch (IOException e) {
            LOGGER.warn("A problem of type", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
            * Gets a property value from properties config file.
            *
            * @param propName property name setting String.
     * @return property environment property String value.
     */
    public String getEnv(final String propName) {
        String property = System.getProperty(propName);
        if (Objects.isNull(property)) {
            return properties.getProperty(propName);
        }
        return property;
    }

    /**
     * Helps to set the value for a property.
     *
     * @param property property .
     * @param value    property value.
     */
    public void setEnv(final String property, final String value) {
        properties.setProperty(property, value);
    }
}
