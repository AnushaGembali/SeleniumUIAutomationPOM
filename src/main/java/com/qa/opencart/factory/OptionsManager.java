package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions op;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	private boolean headlessValue;
	private boolean incognitoValue;
	 
	
	
	OptionsManager(Properties prop){
		this.prop = prop;
		headlessValue = System.getProperty("headless") != null ? Boolean.parseBoolean(System.getProperty("headless")) : Boolean.parseBoolean(prop.getProperty("headless").trim());
		incognitoValue = System.getProperty("incognito") != null ? Boolean.parseBoolean(System.getProperty("incognito")) : Boolean.parseBoolean(prop.getProperty("incognito").trim());
	}
	
	public ChromeOptions getChromeOptions() {
		op = new ChromeOptions();
		if(headlessValue) {
			System.out.println("...Running in Headless...");
			op.addArguments("--headless");
		}
		if(incognitoValue) {
			System.out.println("...Running in Incognito...");
			op.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			op.setCapability("browserName", "chrome");
			op.setBrowserVersion(prop.getProperty("browserVersion").trim());
			
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("screenResolution", "1920x1080x24");
			selenoidOptions.put("name", prop.getProperty("testName"));
			op.setCapability("selenoid:options", selenoidOptions);
		}
		return op;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(headlessValue) {
			System.out.println("...Running in Headless...");
			fo.addArguments("--headless");
		}
		if(incognitoValue) {
			System.out.println("...Running in Incognito...");
			fo.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
			fo.setBrowserVersion(prop.getProperty("browserVersion").trim());
			
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("screenResolution", "1920x1080x24");
			selenoidOptions.put("name", prop.getProperty("testName"));
			fo.setCapability("selenoid:options", selenoidOptions);
			
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(headlessValue) {
			System.out.println("...Running in Headless...");
			eo.addArguments("--headless");
		}
		if(incognitoValue) {
			System.out.println("...Running in Incognito...");
			eo.addArguments("--inPrivate");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "edge");
			eo.setBrowserVersion(prop.getProperty("browserVersion").trim());
			
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("screenResolution", "1920x1080x24");
			selenoidOptions.put("name", prop.getProperty("testName"));
			eo.setCapability("selenoid:options", selenoidOptions);
		}
		return eo;
	}
	

}
