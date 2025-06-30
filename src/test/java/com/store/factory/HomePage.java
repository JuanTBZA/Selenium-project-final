package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "button[aria-label='Close Welcome Banner']") private WebElement cerrarPopup;
    @FindBy(id = "navbarAccount") private WebElement botonAccount;
    @FindBy(id = "navbarLoginButton") private WebElement botonLogin;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void cerrarPopupInicialSiExiste() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cerrarPopup)).click();
        } catch (TimeoutException ignored) {
            // Popup no visible
        }
    }

    public void irASeccionLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(botonAccount)).click();
        wait.until(ExpectedConditions.elementToBeClickable(botonLogin)).click();
    }

    public void irASeccionAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement botonAccount = wait.until(ExpectedConditions.elementToBeClickable(By.id("navbarAccount")));
        botonAccount.click();
    }

    public void irAMisDirecciones() {
        // Paso 1: clic en "Orders & Payment"
        WebElement ordersYPagos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Show Orders and Payment Menu']")
        ));
        ordersYPagos.click();

        // Paso 2: clic en "My saved addresses"
        WebElement direccionGuardada = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Go to saved address page']"))
        );
        direccionGuardada.click();
    }

}
