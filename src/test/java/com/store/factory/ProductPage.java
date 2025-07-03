package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "input[aria-label='Search']")
    private WebElement inputBuscar;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void searchAndAddToCart(String nombreProducto) {
        // Buscar el producto
        wait.until(ExpectedConditions.elementToBeClickable(inputBuscar)).clear();
        inputBuscar.sendKeys(nombreProducto + Keys.ENTER);

        // Esperar los resultados
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("mat-card")));

        // Obtener la lista de productos encontrados
        List<WebElement> productos = driver.findElements(By.cssSelector("mat-card"));

        for (WebElement producto : productos) {
            String nombre = producto.getText().toLowerCase();
            if (nombre.contains(nombreProducto.toLowerCase())) {
                WebElement boton = producto.findElement(By.cssSelector("button[aria-label='Add to Basket']"));
                boton.click();
                break;
            }
        }
    }
}
