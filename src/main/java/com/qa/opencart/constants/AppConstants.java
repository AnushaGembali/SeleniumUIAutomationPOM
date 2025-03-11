package com.qa.opencart.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final List<String> EXPECTED_LOGIN_AND_REGISTER_PAGE_MENU_ITEMS = List.of("Login","Register","Forgotten Password","My Account","Address Book","Wish List","Order History","Downloads","Recurring payments","Reward Points","Returns","Transactions","Newsletter");
	
	public static final String REGISTER_PAGE_TITLE = "Register Account";
	public static final String REGISTER_PAGE_FRACTION_URL = "route=account/register";
	public static final String REGISTRATION_SUCCESS_PAGE_FRACTION_URL = "route=account/success";
	public static final String REGISTRATION_SUCCESS_PAGE_TITLE_AND_HEADER = "Your Account Has Been Created!";
	
	public static final String ACCOUNT_PAGE_TITLE = "My Account11";	
	public static final String ACCOUNT_PAGE_FRACTION_URL = "route=account/account";
	public static final int EXPECTED_ACCOUNTS_PAGE_HEADERS_COUNT = 4; 
	public static final List<String> EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST = List.of("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	
	public static final String SEARCH_PAGE_TITLE_AND_HEADER_FRACTION = "Search"; 
	public static final String SEARCH_PAGE_URL_FRACTION = "search&search="; 
	
	public static final int DEFAULT_SHORT_TIMEOUT = 5;
	public static final int DEFAULT_MEDIUM_TIMEOUT = 10;
	public static final int DEFAULT_LONG_TIMEOUT = 20;
	public static final String REG_SHEET_NAME = "registerpage";
	
	

}
