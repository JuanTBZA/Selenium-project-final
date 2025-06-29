package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "emailControl") private WebElement emailInput;
    @FindBy(id = "passwordControl") private WebElement passwordInput;
    @FindBy(id = "repeatPasswordControl") private WebElement repeatPasswordInput;
    @FindBy(name = "securityQuestion") private WebElement preguntaDropdown;
    @FindBy(id = "securityAnswerControl") private WebElement respuestaInput;
    @FindBy(id = "registerButton") private WebElement registerBtn;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void completarFormulario(String email, String password, String repeat, String pregunta, String respuesta) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        repeatPasswordInput.sendKeys(repeat);
        seleccionarPreguntaSeguridad(pregunta);
        respuestaInput.sendKeys(respuesta);
    }

    private void seleccionarPreguntaSeguridad(String pregunta) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("mat-select[name='securityQuestion']")));
        dropdown.click();

        String xpath = String.format("//mat-option//span[contains(text(), \"%s\")]", pregunta);
        WebElement opcion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        opcion.click();
    }

    public void enviarFormulario() {
        wait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
    }

    public String obtenerMensajeDeConfirmacionEsperado(String esperado) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(driver -> {
            WebElement snackbar = driver.findElement(By.cssSelector(".mat-mdc-snack-bar-label.mdc-snackbar__label"));
            String texto = snackbar.getText().trim();
            return texto.contains(esperado) ? texto : null;
        });
    }

}
