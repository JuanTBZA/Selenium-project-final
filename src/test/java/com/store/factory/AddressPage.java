package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class AddressPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

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

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickAgregarNuevaDireccion() {
        wait.until(ExpectedConditions.elementToBeClickable(botonAgregarDireccion)).click();
    }

    public void llenarFormulario(String nombre, String direccion, String ciudad, String estado, String codigo, String pais) {
        // Esperar y llenar campo nombre
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Please provide a name.']")))
                .sendKeys(nombre);

        // Llenar campo dirección (textarea)
        driver.findElement(By.cssSelector("textarea[placeholder='Please provide an address.']")).sendKeys(direccion);

        // Llenar ciudad
        driver.findElement(By.cssSelector("input[placeholder='Please provide a city.']")).sendKeys(ciudad);

        // Llenar estado
        driver.findElement(By.cssSelector("input[placeholder='Please provide a state.']")).sendKeys(estado);

        // Llenar código postal
        driver.findElement(By.cssSelector("input[placeholder='Please provide a ZIP code.']")).sendKeys(codigo);

        // Llenar país (nuevo campo input, no dropdown)
        driver.findElement(By.cssSelector("input[placeholder='Please provide a country.']")).sendKeys(pais);

        // Llenar número de celular ficticio (ya que es requerido también)
        driver.findElement(By.cssSelector("input[placeholder='Please provide a mobile number.']")).sendKeys("999999999");
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
