package com.store.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Representa la página principal de la aplicación.
 */
public class HomePage extends BasePage {

    @FindBy(css = "button[aria-label='Close Welcome Banner']") private WebElement cerrarPopup;
    @FindBy(id = "navbarAccount") private WebElement botonAccount;
    @FindBy(id = "navbarLoginButton") private WebElement botonLogin;

    public HomePage(WebDriver driver) {
        super(driver);
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
        wait.until(ExpectedConditions.elementToBeClickable(By.id("navbarAccount"))).click();
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

    public void irAMisMetodosDePago() {
        // Paso 1: clic en "Orders & Payment"
        WebElement ordersYPagos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Show Orders and Payment Menu']")
        ));
        ordersYPagos.click();

        // Paso 2: clic en "My Payment Options"
        WebElement opcionPago = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Go to saved payment methods page']"))
        );
        opcionPago.click();
    }


}
