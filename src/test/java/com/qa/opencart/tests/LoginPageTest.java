package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.AnnotationTransformer;
import com.qa.opencart.listeners.ExtentReportListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners(value = {ExtentReportListener.class, AnnotationTransformer.class})
@Epic("Epic 100: design open cart login page")
@Feature("Feature 101: login feature")
@Story("Us 120: Features related to open cart login page ")
@Owner("Anusha Bellala")
public class LoginPageTest extends BaseTest{
	
	@Step("Verifying Login Page URL")
	@Severity(SeverityLevel.MINOR)
	@Description("Login Page Title Test..")
	@Feature("Feature 400: title test feature")
	@Test
	public void verifyLoginPageTitle() {
		String actualTitle = loginPage.getloginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Step("Verifying Login Page URL")
	@Severity(SeverityLevel.NORMAL)
	@Description("Login Page URL Test..")
	@Feature("Feature 401: URL test feature")
	@Test
	public void verifyLoginPageURl() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Step("Verifying if forgot password link exist")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void isFrgtPswrdLinkExist() {
		Assert.assertTrue(loginPage.isFrgtPswrdLinkExist());
	}
	
	@Step("Logging into Open Cart Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = Integer.MAX_VALUE)
	public void doLoginTest() {
		String userName = prop.getProperty("username");
		String password = prop.getProperty("password");
		accountsPage = loginPage.doLogin(userName, password);
		System.out.println();
		String actualTitleAfterLogin = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(actualTitleAfterLogin, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	
	@Severity(SeverityLevel.MINOR)
	@Test
	public void rightMenuTest() {
		boolean areAllRightMenuItemsExist = loginPage.getRightMenuItesm().equals(AppConstants.EXPECTED_LOGIN_AND_REGISTER_PAGE_MENU_ITEMS);
		Assert.assertTrue(areAllRightMenuItemsExist);
	}

}
