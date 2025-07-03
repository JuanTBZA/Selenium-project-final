package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "button[aria-label='Show the shopping cart']")
    private WebElement botonVerCarrito;

    @FindBy(id = "checkoutButton")
    private WebElement botonCheckout;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openCartAndCheckout() {
        // Abrir el carrito
        wait.until(ExpectedConditions.elementToBeClickable(botonVerCarrito)).click();

        // Hacer clic en Checkout
        wait.until(ExpectedConditions.elementToBeClickable(botonCheckout)).click();
    }
}
