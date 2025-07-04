package com.store.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * Página encargada de la gestión de direcciones guardadas.
 */
public class AddressPage extends BasePage {

    @FindBy(css = "button[aria-label='Add a new address']")
    private WebElement botonAgregarDireccion;

    @FindBy(css = "input[placeholder='Please provide a name.']")
    private WebElement inputNombre;

    @FindBy(css = "input[placeholder='Please provide a street address.']")
    private WebElement inputDireccion;

    @FindBy(css = "input[placeholder='Please provide a city.']")
    private WebElement inputCiudad;

    @FindBy(css = "input[placeholder='Please provide a state.']")
    private WebElement inputEstado;

    @FindBy(css = "input[placeholder='Please provide a ZIP code.']")
    private WebElement inputCodigoPostal;

    @FindBy(css = "mat-select[name='country']")
    private WebElement dropdownPais;

    @FindBy(css = "button#submitButton")
    private WebElement botonEnviar;

    @FindBy(css = "textarea[placeholder='Please provide an address.']")
    private WebElement inputDireccionArea;

    @FindBy(css = "input[placeholder='Please provide a mobile number.']")
    private WebElement inputTelefono;

    @FindBy(css = "input[placeholder='Please provide a country.']")
    private WebElement inputPais;

    public AddressPage(WebDriver driver) {
        super(driver);
    }

    public void clickAgregarNuevaDireccion() {
        wait.until(ExpectedConditions.elementToBeClickable(botonAgregarDireccion)).click();
    }

    public void llenarFormulario(String nombre, String direccion, String ciudad, String estado, String codigo, String pais) {
        inputNombre.sendKeys(nombre);
        inputDireccionArea.sendKeys(direccion);
        inputCiudad.sendKeys(ciudad);
        inputEstado.sendKeys(estado);
        inputCodigoPostal.sendKeys(codigo);
        inputPais.sendKeys(pais);
        inputTelefono.sendKeys("999999999");
    }


    public void enviarFormulario() {
        wait.until(ExpectedConditions.elementToBeClickable(botonEnviar)).click();
    }


    public boolean mensajeDeConfirmacionPresente() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(driver -> {
                List<WebElement> snackbars = driver.findElements(By.cssSelector("div[matsnackbarlabel].mat-mdc-snack-bar-label"));
                for (WebElement snackbar : snackbars) {
                    String mensaje = snackbar.getText().trim();
                    System.out.println("📦 MENSAJE ENCONTRADO: " + mensaje);
                    if (mensaje.matches("(?i)^The address at .+ has been successfully added to your addresses\\.$")) {
                        return true;
                    }
                }
                return false;
            });
        } catch (TimeoutException e) {
            System.out.println("❌ No se encontró ningún mensaje de éxito válido.");
            return false;
        }
    }









}
