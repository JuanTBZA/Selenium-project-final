package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void seleccionarDireccionPorIndice(int index) {
        List<WebElement> direcciones = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("mat-radio-button[name='addressSelection']")));
        direcciones.get(index).click();

        WebElement btnContinuar = driver.findElement(By.cssSelector("button[aria-label='Proceed to delivery method selection']"));
        wait.until(ExpectedConditions.elementToBeClickable(btnContinuar)).click();
    }

    public void seleccionarMetodoEntregaPorIndice(int index) {
        List<WebElement> metodos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("mat-radio-button[name='deliveryMethodSelection']")));
        metodos.get(index).click();

        WebElement btnContinuar = driver.findElement(By.cssSelector("button[aria-label='Proceed to payment selection']"));
        wait.until(ExpectedConditions.elementToBeClickable(btnContinuar)).click();
    }

    public void seleccionarMetodoPagoPorIndice(int index) {
        List<WebElement> tarjetas = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("mat-radio-button[name='paymentMethod']")));
        tarjetas.get(index).click();

        WebElement btnContinuar = driver.findElement(By.cssSelector("button[aria-label='Proceed to review']"));
        wait.until(ExpectedConditions.elementToBeClickable(btnContinuar)).click();
    }

    public void confirmarYFinalizarOrden() {
        WebElement btnPagar = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Complete your purchase']")));
        btnPagar.click();
    }

    public boolean mensajeDeCompraVisible(String textoEsperado) {
        try {
            WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[contains(text(),'" + textoEsperado + "')]")));
            return mensaje.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
