package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(),'Not yet a customer?')]")
    private WebElement enlaceRegistro;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void irAFormularioRegistro() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(enlaceRegistro)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", enlaceRegistro);
            wait.until(ExpectedConditions.elementToBeClickable(enlaceRegistro)).click();
        }
    }
}
