package com.amazon.screens;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.amazon.base.MobileBase;
import com.amazon.utilities.AppiumWrapper;

import io.appium.java_client.MobileElement;

/**
 * @author Srinivas teki
 *
 */
public class HomeScreen extends MobileBase {
	private AppiumWrapper appiumWrapper;
	public HomeScreen() {
		appiumWrapper = new AppiumWrapper();
	}
	@FindBy(how = How.XPATH, using = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.app.Dialog/android.view.View[3]/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.widget.RadioButton")	
	private MobileElement languageButton;
	
	@FindBy(how = How.XPATH, using = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.app.Dialog/android.view.View[3]/android.view.View/android.view.View/android.view.View[8]/android.view.View[2]/android.widget.Button")
	private MobileElement saveChangesButton;
	
	@FindBy(how = How.XPATH, using = ".//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']")
	private MobileElement searchField;
	
	@FindBy(how = How.ID, using = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	private MobileElement searchField1;
	
	@FindBy(how = How.XPATH, using = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView")
	private MobileElement searchItem;
	
	/**
	 * 
	 * Method to click on Search Field
	 */
	public void setsearchField(String searchValue) {
		searchField.click();
	}
	
	/**
	 * Method to verify Login status
	 * @return
	 */
	public boolean loginVerification() {
		return appiumWrapper.elementDisplayed(searchField, 60);
	}
}
