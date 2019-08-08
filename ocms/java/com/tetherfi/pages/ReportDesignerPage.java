package com.tetherfi.pages;

import com.tetherfi.utility.FileUploader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReportDesignerPage extends BasePage {

    public ReportDesignerPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement reportDesigner;
    
    public boolean isReportDesignerPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return reportDesigner.isEnabled();
    }
     
}
