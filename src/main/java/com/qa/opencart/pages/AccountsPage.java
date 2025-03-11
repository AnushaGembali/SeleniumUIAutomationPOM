package com.qa.opencart.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.BrowserUtil;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private BrowserUtil brUtil;
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		brUtil = new BrowserUtil(driver);
	}
	
	private By headers = By.cssSelector("div#content>h2");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector(".input-group-btn>button");	
	private By logoutLink = By.xpath("(//a[text()='Logout'])[2]");
	
	
	public String getAccountsPageTitle() {
		String accountsPageTitle = brUtil.doGetPageTitleContains(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("The actual Accounts Page Title is : " + accountsPageTitle);
		return accountsPageTitle;
	}
	
	public String getAccountsPageURL() {
		String accountsPageURL = brUtil.doGetPageURLContains(AppConstants.ACCOUNT_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("The actual Accounts Page URL is : " + accountsPageURL);
		return accountsPageURL;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.doIsElementDisplayed(logoutLink);
	}
	
	public List<String> getAccountsPageHeaders() {
		List<String> accountsPageHeaders = eleUtil.doGetElements(headers, AppConstants.DEFAULT_SHORT_TIMEOUT)
											.stream()
												.map(e -> e.getText())
													 .collect(Collectors.toList());
		System.out.println("The actual account page headers are ====> " + accountsPageHeaders);
		return accountsPageHeaders;
	}
	
	public ProductResultsPage doSearch(String searchValue) {
		eleUtil.doSendKeys(searchField, searchValue, AppConstants.DEFAULT_SHORT_TIMEOUT);
		eleUtil.doClick(searchButton);
		System.out.println("Navigating to Product Results page");
		return new ProductResultsPage(driver, searchValue);
	}

}
