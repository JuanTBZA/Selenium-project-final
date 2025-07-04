package com.store.stepDefinitions;

import com.store.factory.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

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
            productPage.buscarYAgregarAlCarrito(producto);
        }
    }

    @When("procede al checkout desde el carrito")
    public void procederAlCheckout() {
        cartPage = new CartPage(driver);
        cartPage.abrirCarritoYProcederAlCheckout();
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
}
