package com.store.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Página de registro de usuarios.
 */
public class RegisterPage extends BasePage {

    @FindBy(id = "emailControl") private WebElement emailInput;
    @FindBy(id = "passwordControl") private WebElement passwordInput;
    @FindBy(id = "repeatPasswordControl") private WebElement repeatPasswordInput;
    @FindBy(name = "securityQuestion") private WebElement preguntaDropdown;
    @FindBy(id = "securityAnswerControl") private WebElement respuestaInput;
    @FindBy(id = "registerButton") private WebElement registerBtn;

    public RegisterPage(WebDriver driver) {
        super(driver);
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
        return wait.until(d -> {
            WebElement snackbar = d.findElement(By.cssSelector(".mat-mdc-snack-bar-label.mdc-snackbar__label"));
            String texto = snackbar.getText().trim();
            return texto.contains(esperado) ? texto : null;
        });
    }

}
