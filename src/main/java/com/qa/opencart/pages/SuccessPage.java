package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.BrowserUtil;
import com.qa.opencart.utils.ElementUtil;

public class SuccessPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private BrowserUtil brUtil;

	public SuccessPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		brUtil = new BrowserUtil(driver);
	}
	
	private By headerInSuccessPage = By.tagName("h1");
	private By continueBtn = By.linkText("Continue");
	
	public String getSuccessPageTitle() {
		String sucessPageTitle = brUtil.doGetPageTitleContains(AppConstants.REGISTRATION_SUCCESS_PAGE_TITLE_AND_HEADER, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("The actual Register Success Page Title is : " + sucessPageTitle);
		return sucessPageTitle;
	}
	
	public String getSuccessPageURL() {
		String sucessPageURL = brUtil.doGetPageURLContains(AppConstants.REGISTRATION_SUCCESS_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("The actual Register Success Page URL is : " + sucessPageURL);
		return sucessPageURL;
	}
	
	public String getSuccessPageHeader() {
		String successPageHeaderText = eleUtil.doGetElementText(headerInSuccessPage, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("The actula register suuccess page header is ===> " + successPageHeaderText );
		return successPageHeaderText;
	}
	
	public boolean doesContinueBtnExist() {
		boolean isCntnBtnDisplayed = eleUtil.doIsElementDisplayed(continueBtn);
		return isCntnBtnDisplayed;
	}
	
	public AccountsPage clickContinue() {
		eleUtil.doClick(continueBtn);
		System.out.println("Navigating to ACCOUNTS Page");
		return new AccountsPage(driver);
	}

}
