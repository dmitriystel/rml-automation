package net.rml.auto.website.rml.pages;

import net.rml.auto.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class VehicleDetailsPage extends BasePage {
    private String errorMessage = "//h1[contains(text(), '%s')]";

    @Override
    public void waitForLoad() {
    }

    public int getStatusCode() {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(webDriver.getCurrentUrl()).openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Safari/537.36");
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(3000);
        try {
            return conn.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasErrorMessage(final String errorMsgTxt) {
        try {
            WebElement errorMsg = webDriver.findElement(By.xpath( String.format(errorMessage,errorMsgTxt)));
            return errorMsg.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
