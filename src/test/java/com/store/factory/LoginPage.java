package com.store.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Página de inicio de sesión de Juice Shop.
 */
public class LoginPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Not yet a customer?')]")
    private WebElement enlaceRegistro;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "loginButton")
    private WebElement loginButton;


    public void ingresarCredenciales(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).clear();
        emailInput.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
        passwordInput.sendKeys(password);

        wait.until(d -> loginButton.isEnabled());
    }

    public void enviarLogin() {
        loginButton.click();
    }



    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void irAFormularioRegistro() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(enlaceRegistro)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", enlaceRegistro);
            wait.until(ExpectedConditions.elementToBeClickable(enlaceRegistro)).click();
        }
    }

    public boolean validarCorreoDesplegado(String emailEsperado) {
        try {
            WebElement botonAccount = wait.until(ExpectedConditions.elementToBeClickable(By.id("navbarAccount")));
            botonAccount.click();

            // Esperar que el overlay del menú esté presente
            WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".mat-mdc-menu-content")));

            // Buscar el botón con el correo visible
            WebElement correoElemento = overlay.findElement(By.xpath(
                    String.format(".//span[contains(text(),'%s')]", emailEsperado)
            ));

            return correoElemento.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }


    public boolean mensajeErrorVisible() {
        try {
            WebElement errorDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.error.ng-star-inserted")));
            return errorDiv.getText().trim().equals("Invalid email or password.");
        } catch (TimeoutException e) {
            return false;
        }
    }



}
