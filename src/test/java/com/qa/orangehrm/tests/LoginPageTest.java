package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.FrameworkConstants;

public class LoginPageTest extends BaseTest {

	@Test
	public void doLoginTest() {
		dashboardPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String welcomeText = dashboardPage.getWelcomeText();
		Assert.assertEquals(welcomeText, FrameworkConstants.WELCOME_TEXT);
	}
}
