package com.store.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Página que representa el carrito de compras.
 */
public class CartPage extends BasePage {

    @FindBy(css = "button[aria-label='Show the shopping cart']")
    private WebElement botonVerCarrito;

    @FindBy(id = "checkoutButton")
    private WebElement botonCheckout;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Abre el carrito y navega directamente al proceso de checkout.
     */
    public void abrirCarritoYProcederAlCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(botonVerCarrito)).click();
        wait.until(ExpectedConditions.elementToBeClickable(botonCheckout)).click();
    }
}
