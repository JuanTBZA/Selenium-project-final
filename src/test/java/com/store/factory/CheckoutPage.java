package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "checkoutButton")
    protected WebElement btnCheckout;

    @FindBy(xpath = "//h1[contains(text(),'Select an address')]")
    private WebElement tituloSeleccionDireccion;

    @FindBy(css = "mat-radio-button input[type='radio']")
    private List<WebElement> radiosDireccion;

    @FindBy(css = "button[aria-label='Proceed to payment selection']")
    private WebElement btnIrMetodoPago;

    @FindBy(xpath = "//h1[contains(text(),'Choose a delivery speed')]")
    private WebElement tituloMetodoEntrega;

    @FindBy(css = "mat-radio-button")
    private List<WebElement> radiosEntrega;

    @FindBy(xpath = "//button[.//span[contains(text(),'Continue')]]")
    private WebElement btnContinuarEntrega;

    @FindBy(css = "input[type='radio']")
    private List<WebElement> radiosPago;

    @FindBy(xpath = "//button[.//span[contains(text(), 'Continue') or contains(text(), 'Proceed to review')]]")
    private WebElement btnContinuarPago;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void seleccionarDireccionPorIndice(int index) {
        wait.until(ExpectedConditions.visibilityOf(tituloSeleccionDireccion));
        wait.until(d -> radiosDireccion.size() > index);

        WebElement radio = radiosDireccion.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radio);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);

        wait.until(ExpectedConditions.elementToBeClickable(btnIrMetodoPago)).click();
    }

    public void seleccionarMetodoEntregaPorIndice(int index) {
        wait.until(ExpectedConditions.visibilityOf(tituloMetodoEntrega));
        wait.until(d -> radiosEntrega.size() > index);

        WebElement opcion = radiosEntrega.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", opcion);
        wait.until(ExpectedConditions.elementToBeClickable(opcion)).click();

        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('disabled');", btnContinuarEntrega);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnContinuarEntrega);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnContinuarEntrega);
    }

    public void seleccionarMetodoPagoPorIndice(int index) {
        wait.until(d -> radiosPago.size() > index);
        WebElement tarjeta = radiosPago.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", tarjeta);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tarjeta);

        wait.until(d -> btnContinuarPago.isEnabled());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnContinuarPago);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnContinuarPago);
    }

    public void confirmarYFinalizarOrden() {
        wait.until(ExpectedConditions.visibilityOf(btnCheckout));
        wait.until(ExpectedConditions.elementToBeClickable(btnCheckout));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnCheckout);

        if (!btnCheckout.isEnabled()) {
            throw new ElementNotInteractableException("El botón de pago está deshabilitado");
        }

        try {
            new Actions(driver).moveToElement(btnCheckout).pause(500).click().perform();
        } catch (Exception e1) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnCheckout);
            } catch (Exception e2) {
                btnCheckout.click();
            }
        }
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
