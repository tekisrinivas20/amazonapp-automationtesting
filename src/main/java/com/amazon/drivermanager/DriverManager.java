package com.amazon.drivermanager;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * @author Srinivas teki
 *
 */
public class DriverManager {
	public static String projectPath = System.getProperty("user.dir");
	public static String reportsPath = projectPath + File.separator + "TestReports" + File.separator;
	private static AppiumDriver<MobileElement> appiumDriver;

	/**
	 * 
	 * Method to launch application with desired capabilities
	 * 
	 * @param appPath
	 * @param deviceUDID
	 * @throws Exception
	 */
	public void mobileAppLaunch(String appPath, String deviceUDID) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.UDID, deviceUDID);
		capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "360");
		capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
		capabilities.setCapability("app", appPath);

		if (appPath.contains(".apk")) {
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
			appiumDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} else {
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			appiumDriver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		}
	}

	public static AppiumDriver<MobileElement> getDriver() {
		return appiumDriver;
	}
}
