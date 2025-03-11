package com.qa.opencart.factory;

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
		headlessValue = System.getProperty("headless") != null ? Boolean.parseBoolean(System.getProperty("headless")) : Boolean.parseBoolean(prop.getProperty("headless"));
		incognitoValue = System.getProperty("incognito") != null ? Boolean.parseBoolean(System.getProperty("incognito")) : Boolean.parseBoolean(prop.getProperty("incognito"));
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
		return eo;
	}
	

}
