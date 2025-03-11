package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.SuccessPage;

public class RegisterPageTest extends BaseTest{
	
	private SuccessPage successPage;
	
	@BeforeClass
	public void navigateToRegisterPageTest() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	@Test
	public void verifyRegisterPageTitle() {
		String actualTitle = registerPage.getRegisterPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.REGISTER_PAGE_TITLE);
	}
	
	@Test
	public void verifyRegisterPageURl() {
		String actualURL = registerPage.getRegisterPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.REGISTER_PAGE_FRACTION_URL));
	}
	
	@Test
	public void verifyRightMenuItems() {
		List<String> actualMenuList = registerPage.getRightMenuItesm();
		Assert.assertTrue(actualMenuList.equals(AppConstants.EXPECTED_LOGIN_AND_REGISTER_PAGE_MENU_ITEMS));
	}
	
	@Test
	public void verifyRegisterPageHeader() {
		String actualHeader = registerPage.getRegisterPageHeader();
		Assert.assertEquals(actualHeader, AppConstants.REGISTER_PAGE_TITLE);
	}
	
	private String getRandomEmail() {
		return "anushaB"+ System.currentTimeMillis() +"@gmail.com";
	}
	
	
	@Test(priority = Integer.MAX_VALUE, dataProvider = "getRegistrationData", dataProviderClass = ProductDataProvider.class)
	public void doRegisterTest(String firstName, String lastName, String mobNum, String password, String subscribe) {
		boolean isRegistrationSuccess = registerPage.doRegister(firstName, lastName, getRandomEmail(), mobNum, password, subscribe);
		Assert.assertTrue(isRegistrationSuccess);
	}


}
