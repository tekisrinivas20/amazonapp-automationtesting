package com.amazon.screens;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.amazon.base.MobileBase;
import com.amazon.utilities.AppiumWrapper;

import io.appium.java_client.MobileElement;

/**
 * @author Srinivas teki
 *
 */
public class CartScreen extends MobileBase {

	private AppiumWrapper appiumWrapper;

	public CartScreen() {
		appiumWrapper = new AppiumWrapper();
	}

	@FindBy(how = How.XPATH, using = "//android.view.View[contains(@resource-id,'sc-item')]/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[1]/android.widget.TextView")
	private MobileElement verifyingIncartAddedItem;

	@FindBy(how = How.XPATH, using = "//android.view.View[contains(@resource-id,'sc-item')]/android.view.View[2]/android.view.View/android.view.View[3]/android.widget.ListView/android.view.View[1]")
	private MobileElement verifyingIncartAddedItemPrice;

	/**
	 * 
	 * method for verify cart Item name
	 * 
	 * @return
	 */
	public String verifyingIncartAddedItem() {
		appiumWrapper.getScreenshot();
		return appiumWrapper.getTextValue(verifyingIncartAddedItem, "verify Item name Incart", 60);
	}

	/**
	 * Method for item price in cart
	 * 
	 * @return
	 */
	public String verifyingIncartAddedItemPrice() {
		return appiumWrapper.getTextValue(verifyingIncartAddedItemPrice, "verify Item price Incart", 60);
	}

	/**
	 * Method for verifying selected item name and cart item name
	 * 
	 * @param searchItemName
	 * @param itemNameInCart
	 */
	public void compareSearchItemName_andCartItemName(String searchItemName, String itemNameInCart) {

		if (itemNameInCart.endsWith("...")) {
			itemNameInCart = itemNameInCart.replace("...", "");
		}
		Assert.assertTrue(searchItemName.contains(itemNameInCart), "Added item not found in cart");
	}

	/**
	 * Method for verifying selected item price and cart item price
	 * 
	 * @param searchItemPrice
	 * @param actualItemPriceInCart
	 */
	public void compareSearchItemPrice_andCartItemPrice(String searchItemPrice, String actualItemPriceInCart) {

		if (!searchItemPrice.equals("") || !searchItemPrice.equals(null)) {
			searchItemPrice = searchItemPrice.substring(7, searchItemPrice.length());
		}
		Assert.assertTrue(actualItemPriceInCart.contains(searchItemPrice),
				"searched item price and cart item price not matched");
	}

}
