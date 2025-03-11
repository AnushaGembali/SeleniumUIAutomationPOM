package com.qa.opencart.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

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
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actualTitleAfterLogin = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(actualTitleAfterLogin, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void rightMenuTest() {
		boolean areAllRightMenuItemsExist = loginPage.getRightMenuItesm().equals(AppConstants.EXPECTED_LOGIN_AND_REGISTER_PAGE_MENU_ITEMS);
		Assert.assertTrue(areAllRightMenuItemsExist);
	}

}
