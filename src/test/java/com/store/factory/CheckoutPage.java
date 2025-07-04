package com.store.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

/**
 * Página que maneja el proceso de checkout.
 */
public class CheckoutPage extends BasePage {

    @FindBy(css = "button[aria-label='Proceed to delivery method selection']")
    private WebElement btnContinuarDelivery;

    @FindBy(css = "button[aria-label='Proceed to payment selection']")
    private WebElement btnContinuarPago;

    @FindBy(css = "button[aria-label='Proceed to review']")
    private WebElement btnContinuarRevision;

    @FindBy(css = "button[aria-label='Complete your purchase']")
    private WebElement btnPagar;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Selecciona una dirección guardada y avanza al método de entrega.
     */
    public void seleccionarDireccionPorIndice(int index) {
        List<WebElement> direcciones = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("mat-radio-button[name='addressSelection']")));
        direcciones.get(index).click();
        wait.until(ExpectedConditions.elementToBeClickable(btnContinuarDelivery)).click();
    }

    /**
     * Elige un método de entrega y avanza a la selección de pago.
     */
    public void seleccionarMetodoEntregaPorIndice(int index) {
        List<WebElement> metodos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("mat-radio-button[name='deliveryMethodSelection']")));
        metodos.get(index).click();
        wait.until(ExpectedConditions.elementToBeClickable(btnContinuarPago)).click();
    }

    /**
     * Selecciona la tarjeta con el índice indicado y continúa con la revisión.
     */
    public void seleccionarMetodoPagoPorIndice(int index) {
        List<WebElement> tarjetas = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("mat-radio-button[name='paymentMethod']")));
        tarjetas.get(index).click();
        wait.until(ExpectedConditions.elementToBeClickable(btnContinuarRevision)).click();
    }

    /**
     * Finaliza el proceso de compra.
     */
    public void confirmarYFinalizarOrden() {
        wait.until(ExpectedConditions.elementToBeClickable(btnPagar)).click();
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
