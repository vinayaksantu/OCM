package com.tetherfi.pages;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import  java.util.List;

import com.tetherfi.model.chat.CallBackManagementDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApplicationAccessControlPage extends BasePage {
    public ApplicationAccessControlPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ibox-title h5")
    private WebElement applicationAccessControl;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
    
    public boolean isApplicationAccessControlPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return applicationAccessControl.isEnabled();
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }
}