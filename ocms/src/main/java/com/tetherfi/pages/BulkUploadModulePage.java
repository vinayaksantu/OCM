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

public class BulkUploadModulePage extends BasePage {
    public BulkUploadModulePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ibox-title h5")
    private WebElement bulkUploadModule;
    
    @FindBy(css = "#create")
    private WebElement uploadBtn;
    
    public boolean isBulkUploadModulePageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return bulkUploadModule.isEnabled();
    }
    
    public boolean isAddBtnDisplayed() {
    	return uploadBtn.isDisplayed() && uploadBtn.isEnabled();
    }
}