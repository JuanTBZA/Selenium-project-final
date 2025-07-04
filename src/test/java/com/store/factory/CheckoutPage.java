package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "checkoutButton")
    protected WebElement btnCheckout;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void seleccionarDireccionPorIndice(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Select an address')]")));

        List<WebElement> radios = wait.until(ExpectedConditions
                .numberOfElementsToBeMoreThan(By.cssSelector("mat-radio-button input[type='radio']"), index));

        WebElement radioInput = radios.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radioInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioInput);

        WebElement btnContinuar = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Proceed to payment selection']:not([disabled])")));
        btnContinuar.click();
    }

    public void seleccionarMetodoEntregaPorIndice(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Choose a delivery speed')]")));

        List<WebElement> radios = wait.until(ExpectedConditions
                .numberOfElementsToBeMoreThan(By.cssSelector("mat-radio-button"), index));

        WebElement matRadioButton = radios.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", matRadioButton);
        wait.until(ExpectedConditions.elementToBeClickable(matRadioButton)).click();

        WebElement btnContinuar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[.//span[contains(text(),'Continue')]]")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('disabled');", btnContinuar);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnContinuar);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnContinuar);
    }

    public void seleccionarMetodoPagoPorIndice(int index) {
        List<WebElement> radios = wait.until(driver -> {
            List<WebElement> elementos = driver.findElements(By.cssSelector("input[type='radio']"));
            return elementos.size() > index ? elementos : null;
        });

        WebElement tarjeta = radios.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", tarjeta);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tarjeta);

        WebElement btnContinuar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[.//span[contains(text(), 'Continue') or contains(text(), 'Proceed to review')]]")));

        wait.until(driver -> btnContinuar.isEnabled());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnContinuar);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnContinuar);
    }

    public void confirmarYFinalizarOrden() {
        try {
            WebElement btnPagar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkoutButton")));
            wait.until(ExpectedConditions.visibilityOf(btnPagar));
            wait.until(ExpectedConditions.elementToBeClickable(btnPagar));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnPagar);

            if (!btnPagar.isEnabled()) {
                throw new ElementNotInteractableException("El botón de pago está deshabilitado");
            }

            try {
                new Actions(driver).moveToElement(btnPagar).pause(500).click().perform();
            } catch (Exception e1) {
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnPagar);
                } catch (Exception e2) {
                    btnPagar.click();
                }
            }
        } catch (Exception e) {
            throw e;
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
