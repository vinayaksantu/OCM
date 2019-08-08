package com.tetherfi.pages;

import com.tetherfi.utility.FileUploader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.Key;
import java.util.List;

public class AdminCallbackPage extends BasePage {

    public AdminCallbackPage(WebDriver driver) {
        super(driver);
    }
    
    @FindBy(css=".ibox-title h5")
    private WebElement adminCallback;

    @FindBy(xpath = "//button[text()='Export to Excel']")
    private WebElement exportBtn;

    public boolean isAdminCallbackPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return adminCallback.isEnabled();
    }
	
	public boolean isExportBtnDisplayed() {
    	return exportBtn.isDisplayed() && exportBtn.isEnabled();
    }
}
