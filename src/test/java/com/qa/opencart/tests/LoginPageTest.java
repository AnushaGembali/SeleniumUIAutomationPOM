package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	
	@Test
	public void verifyLoginPageTitle() {
		String actualTitle = loginPage.getloginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void verifyLoginPageURl() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	public void isFrgtPswrdLinkExist() {
		Assert.assertTrue(loginPage.isFrgtPswrdLinkExist());
	}
	
	@Test(priority = Integer.MAX_VALUE)
	public void doLoginTest() {
		String userName = prop.getProperty("username");
		String password = prop.getProperty("password");
		accountsPage = loginPage.doLogin(userName, password);
		System.out.println();
		String actualTitleAfterLogin = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(actualTitleAfterLogin, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void rightMenuTest() {
		boolean areAllRightMenuItemsExist = loginPage.getRightMenuItesm().equals(AppConstants.EXPECTED_LOGIN_AND_REGISTER_PAGE_MENU_ITEMS);
		Assert.assertTrue(areAllRightMenuItemsExist);
	}

}
