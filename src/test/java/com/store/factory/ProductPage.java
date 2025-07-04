package com.store.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

/**
 * Página de productos utilizada para realizar búsquedas y añadir artículos al carrito.
 */
public class ProductPage extends BasePage {

    @FindBy(css = "input[aria-label='Search']")
    private WebElement inputBuscar;

    @FindBy(css = "mat-card")
    private List<WebElement> productos;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Busca un producto por nombre y lo agrega al carrito si está presente.
     */
    public void buscarYAgregarAlCarrito(String nombreProducto) {
        wait.until(ExpectedConditions.elementToBeClickable(inputBuscar)).clear();
        inputBuscar.sendKeys(nombreProducto + Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfAllElements(productos));

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
