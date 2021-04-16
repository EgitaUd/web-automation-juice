package com.testing.pageObjects.pages;

import static org.junit.Assert.fail;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/payment/shop")
public class PaymentShopPage extends DeliveryMethodPage{
  // Locators
  // -------------------------------------------------------------------------------------------------------------------
  public static By MY_PAYMENT_OPTIONS_TITLE = text("My Payment Options");
  public static By CONTINUE_BUTTON = css("button[aria-label='Proceed to review']");
  public static By ADD_NEW_CARD_OPTION = text("Add new card");
  public static By CARD_NAME_FIELD = css("input[type='number']"); //not working
  public static By CARD_NUMBER_FIELD = css("input[type='number']");
  public static By EXPIRY_MONTH_FIELD = css("#mat-input-9"); //not working
  public static By EXPIRY_YEAR_FIELD = css("#mat-input-10"); //not working
  public static By ROW_ITEMS = css("body > app-root > div > mat-sidenav-container > mat-sidenav-content > app-payment > mat-card > div > app-payment-method > div > div.ng-star-inserted > mat-table > mat-row");
  // Public methods
  // -------------------------------------------------------------------------------------------------------------------
  public void waitForPageToLoad(){
    getElement(MY_PAYMENT_OPTIONS_TITLE).waitUntilVisible();
    logWeAreOnPage();
  }

  public void performSelectCreditCard(DataTable data){
    String cardNumber = null;
    Boolean found = null;

    for (Map<String, String> map : dataToMap(data)) {
      found = false;
      cardNumber = map.get("Card Number");
      for (WebElement element : getElements(ROW_ITEMS)) {
        if(!element.getText().contains(cardNumber)) continue;
        element.findElement(css("mat-radio-button")).click();
        found = true;
      }
      if(found) break;
    }
    if(!found) fail("Failed to select given card number: " + cardNumber);
  }

  public void setElementValueTo(String elementName, String value) {
    switch(elementName){
      case "EXPIRY_MONTH":
        getElement(EXPIRY_MONTH_FIELD).click();
        getElement(exactText(value)).click();
        break;
      case "EXPIRY_YEAR":
        getElement(EXPIRY_MONTH_FIELD).click();
        getElement(exactText(value)).click();
        break;
      default:
        getElement(elementName).click();
        getElement(elementName).sendKeys(value);
    }
  }
}
