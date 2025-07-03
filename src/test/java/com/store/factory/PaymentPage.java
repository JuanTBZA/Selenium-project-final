package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class PaymentPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public void abrirFormularioNuevaTarjeta() {
        WebElement panel = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("mat-expansion-panel")));
        panel.click();
    }


    public void llenarFormulario(String name, String cardNumber, String month, String year) {
        System.out.println("🔍 Buscando input con label 'Name'...");
        WebElement inputName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Name')]/following::input[1]")
        ));
        inputName.clear();
        inputName.sendKeys(name);
        System.out.println("✅ Nombre ingresado: " + name);

        System.out.println("🔍 Buscando input con label 'Card Number'...");
        WebElement inputCard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Card Number')]/following::input[1]")
        ));
        inputCard.clear();
        inputCard.sendKeys(cardNumber);
        System.out.println("✅ Número de tarjeta ingresado: " + cardNumber);

        System.out.println("🔍 Buscando select 'Expiry Month'...");
        WebElement selectMes = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Expiry Month')]/following::select[1]")
        ));
        new Select(selectMes).selectByVisibleText(month);
        System.out.println("✅ Mes seleccionado: " + month);

        System.out.println("🔍 Buscando select 'Expiry Year'...");
        WebElement selectAnio = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Expiry Year')]/following::select[1]")
        ));
        new Select(selectAnio).selectByVisibleText(year);
        System.out.println("✅ Año seleccionado: " + year);
    }






    public void enviarFormulario() {
        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        boton.click();
    }

    public boolean mensajeConfirmacionPresente() {
        try {
            return wait.until(driver -> {
                List<WebElement> snackbars = driver.findElements(By.cssSelector("div.mat-mdc-snack-bar-label"));
                for (WebElement snackbar : snackbars) {
                    String texto = snackbar.getText().trim();
                    System.out.println("💬 Snackbar encontrado: '" + texto + "'");
                    if (texto.matches("(?i)^Your card ending with \\d{4} has been saved for your convenience\\.?$")) {
                        return true;
                    }
                }
                return false;
            });
        } catch (TimeoutException e) {
            System.out.println("❌ No apareció el mensaje esperado.");
            return false;
        }
    }


}
