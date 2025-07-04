package com.store.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page Object que gestiona la sección de métodos de pago. Cada componente
 * relevante del formulario se define como atributo para facilitar su uso.
 */
public class PaymentPage extends BasePage {

    @FindBy(css = "mat-expansion-panel")
    private WebElement panelNuevaTarjeta;

    @FindBy(xpath = "//label[contains(.,'Name')]/following::input[1]")
    private WebElement inputNombre;

    @FindBy(xpath = "//label[contains(.,'Card Number')]/following::input[1]")
    private WebElement inputNumeroTarjeta;

    @FindBy(xpath = "//label[contains(.,'Expiry Month')]/following::select[1]")
    private WebElement selectMesExpiracion;

    @FindBy(xpath = "//label[contains(.,'Expiry Year')]/following::select[1]")
    private WebElement selectAnioExpiracion;

    @FindBy(css = "button[type='submit']")
    private WebElement botonEnviar;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Despliega la sección para agregar una nueva tarjeta.
     */
    public void abrirFormularioNuevaTarjeta() {
        wait.until(ExpectedConditions.elementToBeClickable(panelNuevaTarjeta)).click();
    }

    /**
     * Completa el formulario con los datos de la tarjeta.
     */
    public void llenarFormulario(String nombre, String numero, String mes, String anio) {
        inputNombre.clear();
        inputNombre.sendKeys(nombre);

        inputNumeroTarjeta.clear();
        inputNumeroTarjeta.sendKeys(numero);

        new Select(selectMesExpiracion).selectByVisibleText(mes);
        new Select(selectAnioExpiracion).selectByVisibleText(anio);
    }

    /**
     * Envía el formulario para guardar la tarjeta.
     */
    public void enviarFormulario() {
        wait.until(ExpectedConditions.elementToBeClickable(botonEnviar)).click();
    }

    /**
     * Verifica que aparezca el mensaje de confirmación en pantalla.
     */
    public boolean mensajeConfirmacionPresente() {
        try {
            return wait.until(d -> {
                List<WebElement> snackbars = d.findElements(By.cssSelector("div.mat-mdc-snack-bar-label"));
                for (WebElement snackbar : snackbars) {
                    String texto = snackbar.getText().trim();
                    if (texto.matches("(?i)^Your card ending with \\d{4} has been saved for your convenience\\.?$")) {
                        return true;
                    }
                }
                return false;
            });
        } catch (TimeoutException e) {
            return false;
        }
    }
}
