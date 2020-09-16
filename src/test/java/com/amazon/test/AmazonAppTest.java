package com.amazon.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.amazon.drivermanager.DriverManager;
import com.amazon.report.ReportBase;
import com.amazon.screens.CartScreen;
import com.amazon.screens.LoginScreen;
import com.amazon.screens.SearchScreen;
import com.amazon.utilities.AppiumWrapper;
import com.amazon.utilities.ExcelDataReaderUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author Srinivas teki
 *
 */
public class AmazonAppTest extends DriverManager {
	private ExcelDataReaderUtility excelObj;
	private AppiumWrapper appiumWrapper;

	/**
	 * Constructor
	 * 
	 * @throws Exception
	 */
	public AmazonAppTest() throws Exception {
		appiumWrapper = new AppiumWrapper();
		PropertyConfigurator.configure("log4j.properties");
		ReportBase.logger = Logger.getLogger(AmazonAppTest.class);
		excelObj = new ExcelDataReaderUtility(
				projectPath + File.separator + "testdata" + File.separator + "testdata.xlsx", 0);
		ReportBase.htmlReporter = new ExtentHtmlReporter(
				reportsPath + "SummaryReport_" + appiumWrapper.getCurrentTime() + ".html");
		ReportBase.report = new ExtentReports();
		ReportBase.report.attachReporter(ReportBase.htmlReporter);
		ReportBase.htmlReporter.config().setReportName("Mobile Automation Report");
		ReportBase.htmlReporter.config().setDocumentTitle("Mobile Automation Report");
		ReportBase.htmlReporter.config().setTheme(Theme.DARK);
	}

	/**
	 * 
	 * Launch the App
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public void launchMobileApp() throws Exception {
		try {

			String udid = excelObj.getStringCelldata(1, 1);
			mobileAppLaunch(projectPath + File.separator + "apps" + File.separator + "apk" + File.separator
					+ "Amazon_shopping.apk", udid);
		} catch (Exception ex) {
			ReportBase.logger.info("Please check your Device UDID/ apk path / " + ex);
		}
	}

	/**
	 * 
	 * Login into App
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void loginTest() throws Exception {

		String userName = excelObj.getStringCelldata(1, 2);
		String passWord = excelObj.getStringCelldata(1, 3);
		ReportBase.test = ReportBase.report.createTest("LoginTest");

		LoginScreen loginScreen = new LoginScreen();
		loginScreen.loginApplication(userName, passWord);
	}

	/**
	 * 
	 * Search Results and add item to Cart Verify the selected item in cart
	 * 
	 * @throws Exception
	 */
	@Test(dependsOnMethods = "loginTest", priority = 2)
	public void searchTest() throws Exception {
		String searchData = excelObj.getStringCelldata(1, 0);
		ReportBase.test = ReportBase.report.createTest("SearchTest");

		SearchScreen searchScreen = new SearchScreen();

		searchScreen.searchForItem(searchData);

		int status = searchScreen.pickRandomItem();
		ReportBase.test.log(Status.INFO, "Picked Item : " + (status == 0 ? "None" : status));

		String selectedItemName = searchScreen.getSelectedItemName();
		ReportBase.test.log(Status.INFO, "Selected Item Name : " + selectedItemName);
		ReportBase.logger.info("Selected Item Name : " + selectedItemName);

		String selectedItemPrice = searchScreen.getSelectedItemPrice();
		ReportBase.test.log(Status.INFO, "Selected Item Price: " + selectedItemPrice);
		ReportBase.logger.info("Selected Item Price: " + selectedItemPrice);

		searchScreen.addSelectedItemToCart();
		ReportBase.test.log(Status.INFO, "Tapped on Addd to Cart Button");
		ReportBase.logger.info("Tapped on Addd to Cart Button");

		searchScreen.cartView();

		CartScreen cartScreen = new CartScreen();

		String addedItemNameInCart = cartScreen.verifyingIncartAddedItem();
		String addedItemPriceInCart = cartScreen.verifyingIncartAddedItemPrice();

		cartScreen.compareSearchItemName_andCartItemName(selectedItemName, addedItemNameInCart);
		ReportBase.test.log(Status.INFO, "Item Name in cart : \"" + addedItemNameInCart + "\" and selected Item Name \""
				+ selectedItemName + "\"");
		ReportBase.logger.info("Item Name in cart : \"" + addedItemNameInCart + "\" and selected Item Name \""
				+ selectedItemName + "\"");

		cartScreen.compareSearchItemPrice_andCartItemPrice(selectedItemPrice, addedItemPriceInCart);
		ReportBase.test.log(Status.INFO, "Item Price in cart : \"" + addedItemPriceInCart
				+ "\" and selected Item Price \"" + selectedItemPrice + "\"");
		ReportBase.logger.info("Item Price in cart : \"" + addedItemPriceInCart + "\" and selected Item Price \""
				+ selectedItemPrice + "\"");
		ReportBase.test.log(Status.PASS, "Verified selected item in cart");
		ReportBase.logger.info("Verified selected item in cart");
	}

	@AfterMethod(alwaysRun = true)
	public synchronized void getResult(ITestResult result, Method method) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			ReportBase.test.log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + " Test case FAILED due to above issues:", ExtentColor.RED));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ReportBase.test.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			ReportBase.test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			ReportBase.test.skip(result.getThrowable());
		}
	}

	/**
	 * 
	 * Tear Down Method Kill the extent session and driver instance
	 */
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		getDriver().quit();
		ReportBase.report.flush();
		ReportBase.logger.info("End of execution");
	}
}
