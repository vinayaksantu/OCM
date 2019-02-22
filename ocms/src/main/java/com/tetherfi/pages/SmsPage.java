package com.tetherfi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;


public class SmsPage extends BasePage {

    public SmsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#navTabs li")
    private List<WebElement> navTabs;

    @FindBy(css="a[href$='/SMSResponseTemplate/Index'] div")
    private WebElement smsResponseTemplate;
    
    @FindBy(css="a[href$='/TextSynonyms/Index'] div")
    private WebElement textSynonyms;

    public boolean isSMSPageDisplayed() {
        boolean status = false;
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        for (WebElement ele : navTabs) {
            if (ele.getText().equalsIgnoreCase("SMS")) {
                if (ele.isEnabled()) {
                    status = true;
                }
            }
        }
        return status;
    }

	public void navigateToSMSResponseTemplatePage() {
		selectWebElement(smsResponseTemplate);
	}

	public void navigateToTextSynonymsPage() {
		selectWebElement(textSynonyms);
	}
}
