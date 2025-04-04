package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

import io.qameta.allure.Step;

public class DriverFactory {

	public static String isHighLight;
	private WebDriver driver;
	private Properties prop;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private OptionsManager optionsManager;
	private String browserName;

	@Step("Launching the browser")
	public WebDriver initDriver() {

		optionsManager = new OptionsManager(this.prop);
		isHighLight = prop.getProperty("highlight");
		boolean runTestsInRemote = Boolean.parseBoolean(prop.getProperty("remote"));
		browserName = prop.getProperty("browser");
//		System.out.println("Browser Name is: " + browserName);
		Log.info(prop.getProperty("testname") + " and browser name is : " + browserName);

		if (runTestsInRemote) {
			init_RemoteWebDriver();
		} else {
			init_WebDriver();
		}

		driver = getDriver();
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		String url = prop.getProperty("url");
		System.out.println("The URL is : " + url);
		getDriver().get(url);
		return getDriver();
	}

	private void init_WebDriver() {

		switch (browserName.trim().toLowerCase()) {
		
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
//			driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
//			driver = new SafariDriver();
			break;
		default:
			System.out.println(AppError.INVALID_BROWSER_MSG + browserName + " is invalid");
			throw new BrowserException(AppError.INVALID_BROWSER_MSG);
		}

	}

	private void init_RemoteWebDriver() {

		String remoteHubURL = prop.getProperty("huburl").trim();
		System.out.println("The automation is running on selenium grid and in the url: " + remoteHubURL);
		System.out.println("Browser Name is: " + browserName);

		try {

			switch (browserName.trim().toLowerCase()) {
			case "chrome":
				tlDriver.set(new RemoteWebDriver(new URL(remoteHubURL), optionsManager.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set(new RemoteWebDriver(new URL(remoteHubURL), optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(remoteHubURL), optionsManager.getEdgeOptions()));
				break;
			default:
				System.out.println("Please pass the right remote browser name");
				throw new BrowserException(AppError.INVALID_BROWSER_MSG);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method returns the driver with threadlocal
	 * 
	 * @return
	 */

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize properties from the config file
	 * 
	 * @return
	 */
	// mvn clean install -Denv="qa"

	public Properties initProp() {

		System.out.println("========== Loading the PROPERTIES FILE ===========");
		FileInputStream ip = null;
		String env = System.getProperty("env");
		System.out.println("The tests will be running in " + env + " environment");
		prop = new Properties();

		try {
			if (env == null) {
				System.out.println("The tests will be running in QA environment, since env is null");
//				ip = new FileInputStream("src/test/resources/config/qa.config.properties");
				env = "qa";
			}
//			else {
			switch (env.toLowerCase().trim()) {
			case ("qa"):
				ip = new FileInputStream("src/test/resources/config/qa.config.properties");
				break;
			case ("dev"):
				ip = new FileInputStream("src/test/resources/config/dev.config.properties");
				break;
			case ("uat"):
				ip = new FileInputStream("src/test/resources/config/uat.config.properties");
				break;
			case ("prod"):
				ip = new FileInputStream("src/test/resources/config/config.properties");
				break;
			default:
				System.out.println("Please pass the right environment name");
				throw new FrameworkException("INVALID ENV NAME");
			}
//				}
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("=============== Config file is not found =================");
		} catch (IOException e) {
			System.out.println("=============== Unable to load the Config file =================");
		}
		return prop;
	}

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		File createDir = new File(System.getProperty("user.dir") + "/screenshot");
		createDir.mkdir();
		String destFilePath = System.getProperty("user.dir") + "/screenshot/" + methodName + "_"
				+ System.currentTimeMillis() + ".png";
		File destFile = new File(destFilePath);
		try {
			FileHandler.copy(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFilePath;
	}
}
