package com.testing.pageObjects.pages;

import org.openqa.selenium.By;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/address/create")
public class AddressCreatePage extends AddressSelectPage {
  // Locators
  // -------------------------------------------------------------------------------------------------------------------
  public static By CREATE_AN_ADDRESS_TITLE = text("Add New Address");
  public static By COUNTRY_FIELD = css("input[data-placeholder='Please provide a country.']");
  public static By NAME_FIELD = css("input[data-placeholder='Please provide a name.']");
  public static By MOBILE_NUMBER_FIELD = css("input[data-placeholder='Please provide a mobile number.']");
  public static By ZIP_CODE_FIELD = css("input[data-placeholder='Please provide a ZIP code.']");
  public static By ADDRESS_FIELD = css("textarea[data-placeholder='Please provide an address.']");
  public static By CITY_FIELD = css("input[data-placeholder='Please provide a city.']");
  public static By STATE_FIELD = css("input[data-placeholder='Please provide a state.']");
  // Public methods
  // -------------------------------------------------------------------------------------------------------------------
  public void waitForPageToLoad(){
    getElement(CREATE_AN_ADDRESS_TITLE).waitUntilVisible();
    logWeAreOnPage();
  }

  public void click(String elementName){
    super.click(snakify(elementName));
    if(snakify(elementName).equals("SUBMIT_BUTTON")) getElement(SELECT_AN_ADDRESS_TITLE).waitUntilVisible();
  }
}
