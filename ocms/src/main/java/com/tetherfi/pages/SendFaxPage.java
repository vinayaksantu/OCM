package com.tetherfi.pages;

import com.tetherfi.utility.FileUploader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SendFaxPage extends BasePage {

    public SendFaxPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement sendFax;
    
    @FindBy(css="#create")
    private WebElement addNewRecdBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
    
    public boolean isSendFaxPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return sendFax.isEnabled();
    }
    
    public boolean isAddBtnDisplayed() {
    	return addNewRecdBtn.isDisplayed() && addNewRecdBtn.isEnabled();
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }
     
}
