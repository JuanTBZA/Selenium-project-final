package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "mat-icon.mat-icon.notranslate.mat-search_icon-search")
    private WebElement iconoBuscar;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        PageFactory.initElements(driver, this);
    }

    public void searchAndAddToCart(String nombreProducto) {
        System.out.println("\n🔎 Buscando producto: " + nombreProducto);

        // 1. Asegurarse de que el icono de búsqueda esté listo y hacer clic
        wait.until(ExpectedConditions.elementToBeClickable(iconoBuscar)).click();
        System.out.println("✅ Lupa presionada");

        // 2. Esperar que el input esté visible y usable
        WebElement inputBusqueda = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.mat-mdc-input-element")));
        inputBusqueda.click();
        inputBusqueda.clear();
        inputBusqueda.sendKeys(nombreProducto);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        System.out.println("🧾 Escribiendo y presionando ENTER: " + nombreProducto);

        // 3. Esperar que aparezca al menos un mat-card con el nombre del producto
        wait.until(driver -> driver.findElements(By.cssSelector("mat-card"))
                .stream().anyMatch(card -> card.getText().toLowerCase().contains(nombreProducto.toLowerCase())));

        // 4. Clic en el botón “Add to Basket”
        List<WebElement> productos = driver.findElements(By.cssSelector("mat-card"));
        for (WebElement producto : productos) {
            if (producto.getText().toLowerCase().contains(nombreProducto.toLowerCase())) {
                WebElement boton = producto.findElement(By.cssSelector("button[aria-label='Add to Basket']"));
                wait.until(ExpectedConditions.elementToBeClickable(boton)).click();
                System.out.println("🛒 Click en Add to Basket: " + nombreProducto);
                break;
            }
        }

        // 5. Esperar snackbar (mensaje inferior)
        String snackbarSelector = "div.mat-mdc-snack-bar-label";
        boolean confirmado = wait.until(driver -> {
            List<WebElement> snackbars = driver.findElements(By.cssSelector(snackbarSelector));
            return snackbars.stream().anyMatch(s -> s.getText().toLowerCase().contains(nombreProducto.toLowerCase()));
        });

        if (confirmado) {
            System.out.println("✅ Producto agregado: " + nombreProducto);
        } else {
            throw new RuntimeException("❌ No se confirmó producto en snackbar: " + nombreProducto);
        }

        // 6. Luego de confirmar snackbar, volver a hacer clic en el textbox para limpiarlo
        WebElement buscador = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.mat-mdc-input-element")));
        buscador.click();
        buscador.clear();
        System.out.println("🧹 Textbox limpiado para siguiente búsqueda");

        // 7. Espera corta opcional (por estabilidad visual)
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }



    private boolean esperarSnackbarProducto(String producto) {
        try {
            return wait.until(driver -> {
                List<WebElement> snackbars = driver.findElements(By.cssSelector("div.mat-mdc-snack-bar-label"));
                for (WebElement snackbar : snackbars) {
                    String mensaje = snackbar.getText().toLowerCase();
                    if ((mensaje.contains("added") || mensaje.contains("basket")) &&
                            mensaje.contains(producto.toLowerCase())) {
                        System.out.println("✅ Producto agregado: " + mensaje);
                        return true;
                    }
                }
                return false;
            });
        } catch (TimeoutException e) {
            System.out.println("❌ Timeout esperando snackbar de: " + producto);
            return false;
        }
    }

    private boolean isInputBusquedaVisible() {
        try {
            WebElement input = driver.findElement(By.cssSelector("input.mat-mdc-input-element"));
            return input.isDisplayed() && input.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
