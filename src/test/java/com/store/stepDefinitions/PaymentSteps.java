package com.store.stepDefinitions;

import com.store.factory.HomePage;
import com.store.factory.PaymentPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;

import static com.store.util.Hooks.driver;

public class PaymentSteps {

    private final PaymentPage paymentPage = new PaymentPage(driver);
    HomePage homePage;

    @Y("navega a la sección de métodos de pago")
    public void navegarAMetodosDePago() {
        driver = CommonSteps.driver;
        homePage = CommonSteps.homePage;
        homePage.irASeccionAccount();     // Asegura que el menú esté abierto
        homePage.irAMisMetodosDePago();   // Reutiliza la lógica unificada
    }


    @Y("abre el formulario para añadir nueva tarjeta")
    public void abrirFormularioTarjeta() {
        paymentPage.abrirFormularioNuevaTarjeta();
    }

    @Cuando("completa el formulario de tarjeta con los siguientes datos:")
    public void completarFormularioTarjeta(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        paymentPage.llenarFormulario(
                data.get("nombre"),
                data.get("numero"),
                data.get("mes"),
                data.get("anio")
        );
    }

    @Y("envía el formulario")
    public void enviaElFormulario() {
        paymentPage.enviarFormulario();
    }

    @Entonces("debería ver el mensaje de confirmación del método de pago agregado")
    public void validarMensajeConfirmacion() {
        Assert.assertTrue(paymentPage.mensajeConfirmacionPresente(),
                "No se encontró el mensaje de confirmación del método de pago.");
    }
}
