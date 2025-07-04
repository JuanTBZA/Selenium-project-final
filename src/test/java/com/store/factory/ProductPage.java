package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "mat-icon.mat-icon.notranslate.mat-search_icon-search")
    private WebElement iconoBuscar;

    @FindBy(css = "input.mat-mdc-input-element")
    private WebElement inputBusqueda;

    @FindBy(css = "mat-card")
    private List<WebElement> tarjetas;

    @FindBy(css = "div.mat-mdc-snack-bar-label")
    private List<WebElement> snackbars;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        PageFactory.initElements(driver, this);
    }

    public void searchAndAddToCart(String nombreProducto) {
        wait.until(ExpectedConditions.elementToBeClickable(iconoBuscar)).click();
        wait.until(ExpectedConditions.elementToBeClickable(inputBusqueda));
        inputBusqueda.click();
        inputBusqueda.clear();
        inputBusqueda.sendKeys(nombreProducto);
        new Actions(driver).sendKeys(Keys.ENTER).perform();

        wait.until(d -> tarjetas.stream()
                .anyMatch(card -> card.getText().toLowerCase().contains(nombreProducto.toLowerCase())));

        for (WebElement tarjeta : tarjetas) {
            if (tarjeta.getText().toLowerCase().contains(nombreProducto.toLowerCase())) {
                WebElement boton = tarjeta.findElement(By.cssSelector("button[aria-label='Add to Basket']"));
                wait.until(ExpectedConditions.elementToBeClickable(boton)).click();
                break;
            }
        }

        wait.until(d -> snackbars.stream()
                .anyMatch(s -> s.getText().toLowerCase().contains(nombreProducto.toLowerCase())));

        inputBusqueda.click();
        inputBusqueda.clear();
    }



    public void agregarProductosAleatorios(int cantidad) {
        List<WebElement> tarjetasPagina = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("mat-card"), cantidad - 1));

        List<WebElement> tarjetasConBoton = tarjetasPagina.stream()
                .filter(t -> !t.findElements(By.cssSelector("button[aria-label='Add to Basket']")).isEmpty())
                .toList();

        if (tarjetasConBoton.size() < cantidad) {
            throw new RuntimeException("No hay suficientes productos con botón 'Add to Basket'.");
        }

        List<WebElement> aleatorias = new ArrayList<>(tarjetasConBoton);
        Collections.shuffle(aleatorias);

        for (int i = 0; i < cantidad; i++) {
            WebElement boton = aleatorias.get(i).findElement(By.cssSelector("button[aria-label='Add to Basket']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", boton);
            wait.until(ExpectedConditions.elementToBeClickable(boton)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mat-mdc-snack-bar-label")));
        }
    }
}
