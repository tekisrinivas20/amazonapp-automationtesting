package com.amazon.screens;

import java.util.List;

import org.openqa.selenium.By;
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
public class SearchScreen extends MobileBase {
	private AppiumWrapper appiumWrapper;

	public SearchScreen() {
		appiumWrapper = new AppiumWrapper();
	}

	@FindBy(how = How.XPATH, using = ".//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']")
	private MobileElement searchField;

	@FindBy(how = How.XPATH, using = ".//*[@resource-id='com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text']")
	private MobileElement searchDropdownSuggestionLink;

	@FindBy(how = How.XPATH, using = "//android.view.View[@resource-id='title_feature_div']/android.view.View")
	private MobileElement selectedItemName;

	@FindBy(how = How.XPATH, using = "//android.view.View[@resource-id='atfRedesign_priceblock_priceToPay']/android.widget.EditText")
	private MobileElement selectedItemPrice;

	@FindBy(how = How.XPATH, using = "//android.widget.Button[@resource-id='add-to-cart-button']")
	private MobileElement addToCart;

	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@resource-id,'action_bar_cart_count')]")
	private MobileElement cart;

	/**
	 * Method for search for Item and select the value from dropdown
	 * 
	 * @param searchData
	 */
	public void searchForItem(String searchData) {
		appiumWrapper.tapOnElement(searchField, "Search Field");
		appiumWrapper.waitForElement(2);
		appiumWrapper.inputText(searchField, searchData, "Search Field");
		appiumWrapper.waitForElement(2);
		appiumWrapper.selectFirstValueFromDropdownSuggestion(searchDropdownSuggestionLink,
				"search Dropdown Suggestion Link");
		appiumWrapper.waitForElement(1);
	}

	/**
	 * 
	 * This method will not take first and last item randomly will pick the item of
	 * given range
	 * 
	 * @return
	 */
	public int pickRandomItem() {
		int min = 2;
		appiumWrapper.waitForElement();
		List<MobileElement> getItems = appiumDriver.findElements(By.xpath(
				".//android.widget.LinearLayout[@resource-id='com.amazon.mShop.android.shopping:id/list_product_linear_layout']"));
		int maxSize = getItems.size();
		try {
			if (maxSize > 1) {
				int dynamicSize = (int) (Math.random() * ((maxSize - 2) + 1)) + min;
				System.out.println(dynamicSize);
				appiumWrapper.waitForElement();
				if (dynamicSize == maxSize) {
					dynamicSize = dynamicSize - 1;
				}
				MobileElement listItem = getItems.get(dynamicSize);
				for (int i = 0; i < 4; i++) {
					if (listItem.isDisplayed()) {
						appiumWrapper.tapOnElement(listItem, "Search item");
						break;
					} else {
						appiumWrapper.scrollDown();
					}
				}
				return dynamicSize;
			} else {
				appiumWrapper.scrollDown();
				getItems = appiumDriver.findElements(By.xpath(
						".//android.widget.LinearLayout[@resource-id='com.amazon.mShop.android.shopping:id/list_product_linear_layout']"));
				getItems.get(0);
			}
		} catch (Exception e) {
			ReportBase.logger.error("Unable to pick the random item");
			ReportBase.logger.error(e);
			ReportBase.test.log(Status.FAIL, "Unable to pick the random item");
			ReportBase.test.log(Status.FAIL, e);
			e.printStackTrace();
		}
		return maxSize;
	}

	/**
	 * Method for to get the selected Item Name
	 * 
	 * @return selectedItemName
	 */
	public String getSelectedItemName() {
		appiumWrapper.getScreenshot();
		return appiumWrapper.getTextValue(selectedItemName, "selected Item Name", 60);
	}

	/**
	 * Method for to get the selected Item price
	 * 
	 * @return selectedItemPrice
	 */
	public String getSelectedItemPrice() {
		if (appiumWrapper.elementDisplayed(selectedItemPrice, 2) == false) {
			appiumWrapper.scrollDown();
		}
		return appiumWrapper.getTextValue(selectedItemPrice, "selected Item Price", 20);
	}

	/**
	 * 
	 * Method for add selected Item to cart
	 */
	public void addSelectedItemToCart() {
		appiumWrapper.scrollToElementByIdAndClick("add-to-cart-button");
	}

	/**
	 * Method for navigate to Cart Cart Method
	 */
	public void cartView() {
		appiumWrapper.tapOnElement(cart, "cart icon");
	}
}
