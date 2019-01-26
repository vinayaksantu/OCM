package com.tetherfi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;


public class FaxPage extends BasePage {

    public FaxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#navTabs li")
    private List<WebElement> navTabs;

    @FindBy(css="a[href$='/FaxDnis/Index'] div")
    private WebElement faxLineConfig;


    @FindBy(css="a[href$='/FaxSenders/Index'] div")
    private WebElement faxSenders;



    public boolean isFaxPageDisplayed() {
        boolean status = false;
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        for (WebElement ele : navTabs) {
            if (ele.getText().equalsIgnoreCase("FAX")) {
                if (ele.isEnabled()) {
                    status = true;
                }
            }
        }
        return status;
    }

    public void navigateToFaxLineConfigPage() {
       waitUntilWebElementIsClickable(faxLineConfig);
        selectWebElement(faxLineConfig);
    }

    public void navigateToFaxSendersPage() {
        waitUntilWebElementIsClickable(faxSenders);
        selectWebElement(faxSenders);
    }
}
