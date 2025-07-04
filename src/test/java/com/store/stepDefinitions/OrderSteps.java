package com.store.stepDefinitions;

import com.store.factory.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.store.util.Base.returnDriver;

public class OrderSteps {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @Given("el usuario agrega los siguientes productos al carrito:")
    public void agregarProductosAlCarrito(DataTable dataTable) {
        driver = CommonSteps.driver;
        productPage = new ProductPage(driver);

        List<Map<String, String>> productos = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> fila : productos) {
            String producto = fila.get("producto");
            productPage.searchAndAddToCart(producto);
        }
    }

    @When("procede al checkout desde el carrito")
    public void procederAlCheckout() {
        driver = CommonSteps.driver; // ← ¡Esto es
        cartPage = new CartPage(driver);
        cartPage.openCartAndCheckout();
    }

    @And("elige la segunda dirección guardada")
    public void elegirSegundaDireccion() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.seleccionarDireccionPorIndice(1);
    }

    @And("selecciona el primer método de entrega disponible")
    public void seleccionarPrimerMetodoEntrega() {
        checkoutPage.seleccionarMetodoEntregaPorIndice(0);
    }

    @And("utiliza la primera tarjeta guardada para el pago")
    public void seleccionarPrimeraTarjetaGuardada() {
        checkoutPage.seleccionarMetodoPagoPorIndice(0);
    }

    @And("confirma y paga la orden")
    public void confirmarYPagarLaOrden() {
        checkoutPage.confirmarYFinalizarOrden();
    }

    @Then("debería ver el mensaje de confirmación de compra {string}")
    public void verificarMensajeDeConfirmacion(String mensajeEsperado) {
        Assert.assertTrue(checkoutPage.mensajeDeCompraVisible(mensajeEsperado),
                "No se encontró el mensaje esperado: " + mensajeEsperado);
    }

    @Given("el usuario agrega 2 productos aleatorios del catálogo al carrito")
    public void agregarProductosAleatorios() {
        WebDriver driver = returnDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar a que haya al menos 2 productos visibles
        List<WebElement> tarjetas = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("mat-card"), 1));

        // Filtrar tarjetas que tengan botón 'Add to Basket'
        List<WebElement> tarjetasConBoton = tarjetas.stream()
                .filter(tarjeta -> !tarjeta.findElements(By.cssSelector("button[aria-label='Add to Basket']")).isEmpty())
                .toList();

        if (tarjetasConBoton.size() < 2) {
            throw new RuntimeException("No hay suficientes productos con botón 'Add to Basket'.");
        }

        // Barajar tarjetas y seleccionar 2
        List<WebElement> aleatorias = new java.util.ArrayList<>(tarjetasConBoton);
        Collections.shuffle(aleatorias);

        for (int i = 0; i < 2; i++) {
            WebElement tarjeta = aleatorias.get(i);
            WebElement boton = tarjeta.findElement(By.cssSelector("button[aria-label='Add to Basket']"));

            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", boton);
                wait.until(ExpectedConditions.elementToBeClickable(boton)).click();

                System.out.println("🛒 Click en producto aleatorio #" + (i + 1));

                // Confirmar por snackbar (opcional)
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.mat-mdc-snack-bar-label")));
            } catch (Exception e) {
                throw new RuntimeException("❌ No se pudo hacer click en producto aleatorio #" + (i + 1) + ": " + e.getMessage());
            }
        }
    }





}
