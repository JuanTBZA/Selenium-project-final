package com.store.stepDefinitions;

import com.store.factory.HomePage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import static com.store.util.Base.returnDriver;

public class CommonSteps {

    public static WebDriver driver;
    public static HomePage homePage;

    @Given("el usuario accede a Juice Shop")
    public void accederASitio() {
        driver = returnDriver();
        homePage = new HomePage(driver);
        homePage.cerrarPopupInicialSiExiste();
    }
}
