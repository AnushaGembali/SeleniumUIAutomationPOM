package com.qa.opencart.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.BrowserUtil;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	

	private WebDriver driver;
	private ElementUtil eleUtil;
	private BrowserUtil brUtil;
	
	public RegisterPage(WebDriver driver){
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		brUtil = new BrowserUtil(driver);
	}
	
	private By rightMenuLinks = By.cssSelector(".list-group>a");
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By mobileNum = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPswrd = By.id("input-confirm");
	private By subscribeYesBtn = By.xpath("(//label[@class='radio-inline']/input)[position()=1]");
	private By subscribeNoBtn = By.xpath("(//label[@class='radio-inline']/input)[position()=2]");
	private By privacyPolCheckBox = By.name("agree");
	private By continueBtn = By.cssSelector(".btn-primary");
	private By registerPageHeader = By.tagName("h1");
	private By registerLink = By.linkText("Register");
	private By logoutLink = By.xpath("(//a[text()='Logout'])[2]");
	private By headerInSuccessPage = By.tagName("h1");

	
	public String getRegisterPageTitle() {
		String registerPageTitle = brUtil.doGetPageTitleContains(AppConstants.REGISTER_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("The actual Register Page Title is : " + registerPageTitle);
		return registerPageTitle;
	}
	
	public String getRegisterPageURL() {
		String registerPageURL = brUtil.doGetPageURLContains(AppConstants.REGISTER_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("The actual Register Page URL is : " + registerPageURL);
		return registerPageURL;
	}
	
	public List<String> getRightMenuItesm() {
		List<String> menuItems = eleUtil.doGetElements(rightMenuLinks, AppConstants.DEFAULT_SHORT_TIMEOUT)
											.stream()
												.map(e -> e.getText())
													 .collect(Collectors.toList());
		System.out.println("The actual right menu items in register page ====> " + menuItems);
		return menuItems;
	}
	
	public String getRegisterPageHeader() {
		String regPageHeaderText = eleUtil.doGetElementText(registerPageHeader, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("The actual register page header is: " + regPageHeaderText);
		return regPageHeaderText;
	}
	
	public boolean doRegister(String firstNameVal, String lastnameVal, String emailIdVal, String mobNum, String pswrd, String subscribe) {
		eleUtil.doSendKeys(firstName, firstNameVal, AppConstants.DEFAULT_LONG_TIMEOUT);
		eleUtil.doSendKeys(lastName, lastnameVal);
		eleUtil.doSendKeys(emailId, emailIdVal);
		eleUtil.doSendKeys(mobileNum, mobNum);
		eleUtil.doSendKeys(password, pswrd);
		eleUtil.doSendKeys(confirmPswrd, pswrd);
		if(subscribe.trim().toLowerCase().equals("yes")) {
			eleUtil.doClick(subscribeYesBtn);
		}
		else {
			eleUtil.doClick(subscribeNoBtn);
		}
		eleUtil.doClick(privacyPolCheckBox);
		eleUtil.doClick(continueBtn);
		
		System.out.println("Navigating to registration success page");
		String successMsg = eleUtil.doGetElementText(headerInSuccessPage, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		if(successMsg.contains(AppConstants.REGISTRATION_SUCCESS_PAGE_TITLE_AND_HEADER)) {
			eleUtil.doClick(logoutLink, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
			System.out.println("Logged out of the Account");
			
			eleUtil.doClick(registerLink, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
			System.out.println("Navigated to Register Page");
			return true;
		}		
		return false;
	}

}
