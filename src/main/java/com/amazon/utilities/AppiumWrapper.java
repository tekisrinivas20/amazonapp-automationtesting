package com.amazon.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.amazon.base.MobileBase;
import com.amazon.drivermanager.DriverManager;
import com.amazon.report.ReportBase;
import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author Srinivas teki
 *
 */
public class AppiumWrapper extends MobileBase {

	/**
	 * method to enter text in the field
	 * 
	 * @param element
	 * @param textToEnter
	 * @param fieldName
	 */
	public void inputText(MobileElement element, String textToEnter, String fieldName) {
		try {
			waitForExpectedElement(element);
			element.sendKeys(textToEnter);
			ReportBase.logger.info("Entered text in " + fieldName);
			ReportBase.test.log(Status.INFO, "Entered text in " + fieldName);
		} catch (NoSuchElementException e) {
			ReportBase.logger.info(fieldName + " : Element not found");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, fieldName + ":unable to enter text");
			getScreenshot();
			Assert.assertTrue(false, fieldName + " : Element not found");
		} catch (Exception e) {
			ReportBase.logger.info(fieldName + ": unable to enter text");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, fieldName + ":unable to enter text");
			getScreenshot();
			Assert.assertTrue(false, fieldName + ":unable to enter text");
		}
	}

	/**
	 * method to tap and enter text in the field
	 * 
	 * @param element
	 * @param textToEnter
	 * @param fieldName
	 */
	public void tapAndinputText(MobileElement element, String textToEnter, String fieldName) {
		try {
			waitForExpectedElement(element);
			element.click();
			element.sendKeys(textToEnter);
			ReportBase.logger.info(fieldName + " : entered text");
			ReportBase.test.log(Status.INFO, fieldName + " : entered text");
		} catch (NoSuchElementException e) {
			ReportBase.logger.info(fieldName + " : Element not found");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, fieldName + ":unable to enter text");
			getScreenshot();
			Assert.assertTrue(false, fieldName + " : Element not found");
		} catch (Exception e) {
			ReportBase.logger.info(fieldName + ": unable to enter text");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, fieldName + ":unable to enter text");
			getScreenshot();
			Assert.assertTrue(false, fieldName + ":unable to enter text");
		}
	}

	/**
	 * method to click on elment
	 * 
	 * @param By-       element
	 * @param fieldName
	 */
	public void tapOnElement(MobileElement element, String fieldName) {
		try {
			waitForExpectedElement(element);
			element.click();
			ReportBase.logger.info("Tapped on " + fieldName);
			ReportBase.test.log(Status.INFO, "Tapped on " + fieldName);
		} catch (NoSuchElementException e) {
			ReportBase.logger.info(fieldName + " : Element not found");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, fieldName + " : Unble to Tap on element");
			ReportBase.test.log(Status.FAIL, e);
			getScreenshot();
			Assert.assertTrue(false, fieldName + " : Element not found");
		} catch (Exception e) {
			ReportBase.logger.info(fieldName + " Unable to Tap on element");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, fieldName + " : Unble to Tap on element");
			ReportBase.test.log(Status.FAIL, e);
			getScreenshot();
			Assert.assertTrue(false, fieldName + " Unable to Tap on element");
		}
	}

	/**
	 * 
	 * Method for explicit wait
	 * 
	 * @param appiumDriver
	 * @param element
	 * @return WebElement
	 */
	public WebElement waitForExpectedElement(MobileElement element) {
		WebDriverWait wait = new WebDriverWait(appiumDriver, 50);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * 
	 * Method for explicit wait
	 * 
	 * @param appiumDriver
	 * @param element
	 * @param timeOutInSeconds
	 * @return WebElement
	 */
	public WebElement waitForExpectedElement(MobileElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(appiumDriver, timeOutInSeconds);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Method for Select the first value from Dropdown Suggestion
	 * 
	 * @param element
	 * @param fieldName
	 */
	public void selectFirstValueFromDropdownSuggestion(MobileElement element, String fieldName) {
		try {
			waitForExpectedElement(element);
			element.click();
			ReportBase.logger.info("Selected first value from Dropdown Suggestion");
			ReportBase.test.log(Status.INFO, "Selected first value from Dropdown Suggestion");
		} catch (NoSuchElementException e) {
			ReportBase.logger.info(fieldName + " : Element not found");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, fieldName + " : Unble to select the element");
			ReportBase.test.log(Status.FAIL, e);
			getScreenshot();
			Assert.assertTrue(false, fieldName + " : Element not found");
		} catch (Exception e) {
			ReportBase.logger.info(fieldName + " Unable to select the element");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, fieldName + " : Unable to select the element");
			ReportBase.test.log(Status.FAIL, e);
			getScreenshot();
			Assert.assertTrue(false, fieldName + " Unable to select the element");
		}
	}

	/**
	 * 
	 * Method for tap if element is displayed
	 * 
	 * @param appiumDriver
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean taponIfElementDisplayed(MobileElement element, long timeOutInSeconds) {
		try {
			WebElement ele = waitForExpectedElement(element);
			if (ele.isDisplayed()) {
				ele.click();
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;

	}

	/**
	 * Method for element dispalyed
	 * 
	 * @param appiumDriver
	 * @param element
	 * @param timeOutInSeconds
	 * @return elementStatus
	 */
	public boolean elementDisplayed(MobileElement element, long timeOutInSeconds) {
		try {
			WebElement ele = waitForExpectedElement(element, timeOutInSeconds);
			if (ele.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * Method for element dispalyed
	 * 
	 * @param appiumDriver
	 * @param element
	 * @return elementStatus
	 */
	public boolean elementDisplayed(MobileElement element) {
		try {
			WebElement ele = waitForExpectedElement(element);
			if (ele.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Don't use if not required
	/**
	 * Method for wait for element
	 * 
	 * @param timeOutInSeconds
	 */
	public void waitForElement(long timeOutInSeconds) {
		try {
			Thread.sleep(timeOutInSeconds * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Don't use if not required
	/**
	 * Method for wait for element
	 */
	public void waitForElement() {
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Method for get text value
	 * 
	 * @param appiumDriver
	 * @param element
	 * @param timeOutInSeconds
	 * @return element text
	 */
	public String getTextValue(MobileElement element, String fieldName, long timeOutInSeconds) {
		String textValue = null;
		try {
			WebElement ele = waitForExpectedElement(element, timeOutInSeconds);
			textValue = ele.getText();
			ReportBase.logger.info(fieldName + " : text value : " + textValue);
			ReportBase.test.log(Status.INFO, fieldName + " : text value : " + textValue);
		} catch (NoSuchElementException e) {
			ReportBase.logger.error(fieldName + " : Element not found");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, fieldName + " : Element Not found");
			getScreenshot();
			Assert.assertTrue(false, fieldName + " Element not found");
		} catch (Exception e) {
			ReportBase.logger.info(fieldName + " : Element not found");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, fieldName + " : Element not found");
			getScreenshot();
			Assert.assertTrue(false, fieldName + " Element not found");
		}
		return textValue;
	}

	/**
	 * Method for scroll till element based on ID and click
	 * 
	 * @param driver
	 * @param elementId
	 */
	public void scrollToElementByIdAndClick(String elementId) {
		appiumDriver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().resourceIdMatches(\"" + elementId + "\"));"))
				.click();
	}

	/**
	 * Method for scroll till element based on Text
	 * 
	 * @param driver
	 * @param elementId
	 */
	public void scrollToElementById(String elementId) {
		appiumDriver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().resourceIdMatches(\"" + elementId + "\"));"));
	}

	/**
	 * Method for capture the screenshot
	 */
	public void getScreenshot() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_HHmmss");

		String fileName = dateFormat.format(date) + ".png";

		File srcFile = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(DriverManager.reportsPath + fileName));
			ReportBase.test.addScreenCaptureFromPath(DriverManager.reportsPath + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Method for scroll down
	 */
	public void scrollDown() {
		// if pressX was zero it didn't work for me
		int pressX = appiumDriver.manage().window().getSize().width / 2;
		// 4/5 of the screen as the bottom finger-press point
		int bottomY = appiumDriver.manage().window().getSize().height * 4 / 5;
		// just non zero point, as it didn't scroll to zero normally
		int topY = appiumDriver.manage().window().getSize().height / 8;
		// scroll with TouchAction by itself
		scroll(pressX, bottomY, pressX, topY);
	}

	/*
	 * Don't forget that it's "natural scroll" where fromY is the point where you
	 * press and toY where you release it
	 */
	/**
	 * Method for scroll
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 */
	@SuppressWarnings("rawtypes")
	private void scroll(int fromX, int fromY, int toX, int toY) {
		TouchAction touchAction = new TouchAction(appiumDriver);
		touchAction.longPress(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();
	}

	/**
	 * Method to get current time
	 * 
	 * @return
	 */
	public String getCurrentTime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_HHmmss");
		return dateFormat.format(date);
	}
}
