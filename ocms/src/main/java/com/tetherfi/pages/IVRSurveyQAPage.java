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

public class IVRSurveyQAPage extends BasePage {
    public IVRSurveyQAPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ibox-title h5")
    private WebElement ivrSurveyQA;
    
    @FindBy(css="#create")
    private WebElement addIvrSurveyQARecdBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
    
    public boolean isAddBtnDisplayed() {
    	return addIvrSurveyQARecdBtn.isDisplayed() && addIvrSurveyQARecdBtn.isEnabled();
    }
    
    public boolean isIVRSurveyQAPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return ivrSurveyQA.isEnabled();
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }

}