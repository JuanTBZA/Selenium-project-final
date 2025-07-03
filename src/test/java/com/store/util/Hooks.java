package com.store.util;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends Base {

	public static WebDriver driver;
	private Properties prop;
	private Base driverBase;


	//runs before each scenario
	@Before
	public void setup() {
		prop = Reader.getAllProperties();
		driverBase = new Base();
		driver = driverBase.loadDriver(prop.getProperty("browser"));
        driver.get(prop.getProperty("url"));
	}

	@After
	public void tearDown(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				String screenName = scenario.getName().replaceAll(" ", "_");
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", screenName);
			}
		} finally {
	        Reader.cleanUp();
			driver.quit();
		}
	}
}
