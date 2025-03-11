package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
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
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("chrome") String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		if(browserName != null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver();
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
//		registerPage = new RegisterPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
