package com.qa.opencart.base;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductPage;
import com.qa.opencart.pages.ProductResultsPage;
import com.qa.opencart.pages.RegisterPage;

import io.qameta.allure.Step;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected RegisterPage registerPage;
	protected ProductResultsPage productResultsPage;
	protected ProductPage productPage;
	protected Properties prop;
	protected SoftAssert softAssert;
	
	@Step("Initialize the properties file and launch the browser")
	@Parameters({"browser","browserVersion", "testName"})
	@BeforeTest
	public void setUp(@Optional String browserName, @Optional String browserVersion, @Optional String testName) {
		System.out.println("IN BASE TEST ====> Setting up the driver");
		df = new DriverFactory();
		prop = df.initProp();
		if(browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserVersion", browserVersion);
			prop.setProperty("testName", testName);
		}
		driver = df.initDriver();
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
//		registerPage = new RegisterPage(driver);
	}
	
	@Step("Close the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	@AfterSuite
	public void openHtmlReport() {
		// extent html report
		try {
			File htmlFile = new File("reports/TestExecutionReport.html");
			if (htmlFile.exists()) {
				Desktop.getDesktop().browse(htmlFile.toURI());
			} else {
				System.out.println("Report file not found: " + htmlFile.getAbsolutePath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// allure
		try {
			// Serve the Allure report
			ProcessBuilder builder = new ProcessBuilder("/usr/local/bin/allure", "serve", "allure-results");
			builder.inheritIO();
			Process process = builder.start();
			process.waitFor();

			// The `allure serve` command automatically opens the report in a browser.
			System.out.println("Allure report served successfully.");

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.out.println("Failed to serve Allure report.");
		}

		
	}
}
