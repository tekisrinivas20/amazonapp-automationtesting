package com.amazon.base;

import org.openqa.selenium.support.PageFactory;
import com.amazon.drivermanager.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author Srinivas teki
 *
 */
public class MobileBase {
	public AppiumDriver<MobileElement> appiumDriver;
	public MobileBase(){
		appiumDriver = DriverManager.getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
	}
}
