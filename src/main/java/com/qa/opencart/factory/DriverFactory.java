package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	
	public WebDriver initDriver() {
		
		String browserName = prop.getProperty("browser");
		System.out.println("Browser Name is: " + browserName);
		
		switch(browserName.trim().toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;	
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println(AppError.INVALID_BROWSER_MSG +  browserName + " is invalid");
			throw new BrowserException(AppError.INVALID_BROWSER_MSG);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String url = prop.getProperty("url");
		System.out.println("The URL is : " + url);
		driver.get(url);
		return driver;
	}
	
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("=============== Config file is not found =================");
		} catch (IOException e) {
			System.out.println("=============== Unable to load the Config file =================");
		}
		return prop;
	}
}
