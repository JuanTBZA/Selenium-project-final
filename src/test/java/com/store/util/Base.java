package com.store.util;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	public int TIME_OUT = 10;
	public int TIME_EXPECTED_CONDITIONS = 15;

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	//WebDriver driver;

    public WebDriver loadDriver(String browser) {


		if (browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver.set(new ChromeDriver(options));

		} else if (browser.equalsIgnoreCase("FIREFOX")) {

			driver.set(new FirefoxDriver());

		} else if (browser.equalsIgnoreCase("EDGE")) {

			driver.set(new EdgeDriver());
		} else {
			System.out.println("Undefined Browser");
		}

		returnDriver().manage().deleteAllCookies();
		returnDriver().manage().window().maximize();
		returnDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIME_OUT));
		returnDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_EXPECTED_CONDITIONS));
		return returnDriver();
	}

	public static synchronized WebDriver returnDriver() {
		return driver.get();
	}
}
