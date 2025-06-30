package com.store.stepDefinitions;

import com.store.factory.AddressPage;
import com.store.factory.HomePage;
import com.store.factory.LoginPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;

import java.util.Map;

public class AddressSteps {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AddressPage addressPage;


    @And("navega a la sección de direcciones guardadas")
    public void navegarADirecciones() {
        driver = CommonSteps.driver;
        homePage = CommonSteps.homePage;
        homePage.irASeccionAccount();
        homePage.irAMisDirecciones(); // click en Orders & Payment > My saved addresses
    }

    @When("agrega una nueva dirección con los siguientes datos:")
    public void agregarNuevaDireccion(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap();
        addressPage = new AddressPage(driver);
        addressPage.clickAgregarNuevaDireccion();
        addressPage.llenarFormulario(
                data.get("nombre"),
                data.get("dirección"),
                data.get("ciudad"),
                data.get("estado"),
                data.get("código"),
                data.get("país")
        );
        addressPage.enviarFormulario();
    }

    @Then("debería ver el mensaje de confirmación de la direccion agregada")
    public void verificarMensajeExito() {
        Assert.assertTrue(addressPage.mensajeDeConfirmacionPresente(),
                "No se encontró el mensaje de éxito esperado.");
    }



}
