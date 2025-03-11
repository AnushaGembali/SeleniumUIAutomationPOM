package com.qa.opencart.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.BrowserUtil;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private BrowserUtil brUtil;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		brUtil = new BrowserUtil(driver);
	}
	
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPswLink = By.linkText("Forgotten Password");
	private By rightMenuLinks = By.cssSelector(".list-group>a");
	private By registerLink = By.linkText("Register");
	
	public String getloginPageTitle() {
		String loginPageTitle = brUtil.doGetPageTitleContains(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Login Page Title is : " + loginPageTitle);
		return loginPageTitle;
	}
	
	public String getLoginPageURL() {
		String loginPageURL = brUtil.doGetPageURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Login Page URL is : " + loginPageURL);
		return loginPageURL;
	}
	
	public AccountsPage doLogin(String userName, String pswrd) {
		System.out.println("The Credentials are UesrName => " + userName + " Password => " + pswrd);
		eleUtil.doSendKeys(email, userName, AppConstants.DEFAULT_LONG_TIMEOUT);
		eleUtil.doSendKeys(password, pswrd);
		eleUtil.doClick(loginBtn);
		String myAccountPageTitle = brUtil.doGetPageTitleContains(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Account Page Title is : " + myAccountPageTitle);
		return new AccountsPage(driver);

	}
	
	public boolean isFrgtPswrdLinkExist() {
		return eleUtil.doIsElementDisplayed(forgotPswLink);
	}
	
	public List<String> getRightMenuItesm() {
		List<String> menuItems = eleUtil.doGetElements(rightMenuLinks, AppConstants.DEFAULT_SHORT_TIMEOUT)
											.stream()
												.map(e -> e.getText())
													 .collect(Collectors.toList());
		return menuItems;
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
