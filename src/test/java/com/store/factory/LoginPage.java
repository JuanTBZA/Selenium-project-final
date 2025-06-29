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

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(css = ".mat-mdc-snack-bar-label.mdc-snackbar__label")
    private WebElement snackbar;

    public void ingresarCredenciales(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(emailInput)).clear();
        emailInput.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
        passwordInput.sendKeys(password);

        // Esperar a que el botón esté habilitado en el DOM
        wait.until(driver -> loginButton.isEnabled());
    }

    public void enviarLogin() {
        loginButton.click();
    }



    public String obtenerMensajeBienvenida() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(snackbar)).getText().trim();
    }


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

    public boolean validarCorreoDesplegado(String emailEsperado) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Hacer clic en "Account"
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement errorDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.error.ng-star-inserted")));
            return errorDiv.getText().trim().equals("Invalid email or password.");
        } catch (TimeoutException e) {
            return false;
        }
    }



}
