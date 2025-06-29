package com.store.stepDefinitions;

import com.store.factory.HomePage;
import com.store.factory.LoginPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

    @And("abre la página de login desde el menú de cuenta")
    public void abrirLoginDesdeMenu() {
        driver = CommonSteps.driver;
        homePage = CommonSteps.homePage;
        homePage.irASeccionLogin();
        loginPage = new LoginPage(driver);
    }

    @When("inicia sesión con email {string} y contraseña {string}")
    public void iniciarSesion(String email, String password) {
        loginPage.ingresarCredenciales(email, password); // espera el botón habilitado
        loginPage.enviarLogin();                         // hace clic
    }


    @Then("debería ingresar correctamente y ver el mensaje de bienvenida con email {string}")
    public void validarLoginExitoso(String email) {
        Assert.assertTrue(loginPage.validarCorreoDesplegado(email),
                "No se encontró el correo '" + email + "' en el menú desplegable tras el login.");
    }


    @Then("debería ver el mensaje de error")
    public void validarMensajeErrorEnPantalla() {
        Assert.assertTrue(loginPage.mensajeErrorVisible(),
                "No se encontró el mensaje de error 'Invalid email or password.' en pantalla.");
    }




}
