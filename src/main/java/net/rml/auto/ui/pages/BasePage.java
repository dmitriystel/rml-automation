package net.rml.auto.ui.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    protected By vehicleCard = By.xpath("//*[@role='listitem' and contains(@aria-label, 'vehicle card')]");


}
