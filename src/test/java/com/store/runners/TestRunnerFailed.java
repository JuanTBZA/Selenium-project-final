package com.store.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = { "@target/failedrerun.txt" }, // ejecuta solo los escenarios que fallaron
		glue = "com.store",
		plugin = {"pretty",
				  "summary",
				  "html:target/cucumber-reports/html-report.html",
				  "json:target/cucumber-reports/cucumber.json",
				  "junit:target/cucumber-reports/cucumber.xml",
				  "timeline:target/cucumber-timeline",
				  "rerun:target/failedrerun.txt",
				  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				  "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
		monochrome = false,
		dryRun = false,
		publish = true
		)

public class TestRunnerFailed extends AbstractTestNGCucumberTests {

	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}