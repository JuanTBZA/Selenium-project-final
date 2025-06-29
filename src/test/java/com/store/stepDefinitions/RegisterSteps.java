package com.store.stepDefinitions;

import com.store.factory.HomePage;
import com.store.factory.LoginPage;
import com.store.factory.RegisterPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;

import static com.store.util.Base.returnDriver;

@Slf4j
public class RegisterSteps {

	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	RegisterPage registerPage;

	@Given("el usuario accede a Juice Shop")
	public void accederASitio() {
		driver = returnDriver();
		homePage = new HomePage(driver);
		homePage.cerrarPopupInicialSiExiste();
	}

	@And("navega desde Login hasta el formulario de registro")
	public void irARegistro() {
		homePage.irASeccionLogin();
		loginPage = new LoginPage(driver);
		loginPage.irAFormularioRegistro();
	}

	@When("se registra con:")
	public void completarRegistro(DataTable table) {
		Map<String, String> data = table.asMap();
		registerPage = new RegisterPage(driver);
		registerPage.completarFormulario(
				data.get("email"),
				data.get("contraseña"),
				data.get("repetirPassword"),
				data.get("pregunta"),
				data.get("respuesta")
		);
		registerPage.enviarFormulario();
	}

	@Then("debería ver el mensaje de confirmación {string}")
	public void verificarMensajeRegistro(String esperado) {
		String real = registerPage.obtenerMensajeDeConfirmacionEsperado(esperado);
		Assert.assertTrue(real.contains(esperado), "Mensaje real: " + real);



	}
}
