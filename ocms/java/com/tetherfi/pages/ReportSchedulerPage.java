package com.tetherfi.pages;

import com.tetherfi.utility.FileUploader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReportSchedulerPage extends BasePage {

    public ReportSchedulerPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement reportScheduler;
    
    public boolean isReportSchedulerPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return reportScheduler.isEnabled();
    }
     
}
