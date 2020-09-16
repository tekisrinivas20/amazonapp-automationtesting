package com.amazon.screens;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.amazon.base.MobileBase;
import com.amazon.report.ReportBase;
import com.amazon.utilities.AppiumWrapper;
import com.aventstack.extentreports.Status;

import io.appium.java_client.MobileElement;

/**
 * @author Srinivas teki
 *
 */
public class LoginScreen extends MobileBase {
	private AppiumWrapper appiumWrapper;

	public LoginScreen() {
		appiumWrapper = new AppiumWrapper();
	}

	@FindBy(how = How.ID, using = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private MobileElement signInButton;

	@FindBy(how = How.XPATH, using = ".//android.widget.EditText[@resource-id='ap_email_login']")
	private MobileElement emailORmobileField;

	@FindBy(how = How.XPATH, using = ".//*[@resource-id='continue']")
	private MobileElement continueButton;

	@FindBy(how = How.XPATH, using = "//android.widget.EditText[@resource-id='ap_password']")
	private MobileElement passwordField;

	@FindBy(how = How.XPATH, using = "//android.widget.Button[@resource-id='signInSubmit']")
	private MobileElement loginButton;

	/**
	 * Login application
	 * 
	 * @param emailORmobileNumber
	 * @param password
	 */
	public void loginApplication(String emailORmobileNumber, String password) {
		appiumWrapper.getScreenshot();
		appiumWrapper.tapOnElement(signInButton, "sign In Button");
		appiumWrapper.waitForElement();

		appiumWrapper.inputText(emailORmobileField, emailORmobileNumber, "userName");
		appiumWrapper.tapOnElement(continueButton, "continue Button");

		appiumWrapper.inputText(passwordField, password, "Password");
		appiumWrapper.tapOnElement(loginButton, "login Button");
		verifyLoginStatus();

	}

	/**
	 * Method for verify the Login status
	 */
	public void verifyLoginStatus() {
		HomeScreen homeScreen = new HomeScreen();
		appiumWrapper.waitForElement();
		appiumWrapper.getScreenshot();
		boolean isElement = homeScreen.loginVerification();
		if (isElement == true) {
			ReportBase.test.log(Status.PASS, "User is successfully logged into app");
			ReportBase.logger.info("User successfully logged into app");
			appiumWrapper.getScreenshot();
		} else {
			ReportBase.test.log(Status.FAIL, "User is unable to login into app");
			ReportBase.test.log(Status.FAIL, "Home screen search element not found");
			ReportBase.logger.error("User unable to log into app");
			appiumWrapper.getScreenshot();
		}
	}
}