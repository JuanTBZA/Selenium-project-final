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

    public void openCartAndCheckout() {
        // Abrir el carrito
        wait.until(ExpectedConditions.elementToBeClickable(botonVerCarrito)).click();

        // Hacer clic en Checkout
        wait.until(ExpectedConditions.elementToBeClickable(botonCheckout)).click();
    }
}
