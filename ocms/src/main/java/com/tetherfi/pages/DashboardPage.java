package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver){super(driver);}

    @FindBy(id="dashboarddiv")
    private WebElement dashboard;

    @FindBy(css="ul[id='dashboardNavigationTabs'] li[style='display:block']")
    private List<WebElement> dashboardTabs;

    public boolean isDashboardPageIsDisplayed() {
        waitForLoad(driver);
       // waitForJqueryLoad(driver);
        return dashboard.isDisplayed();
    }
    public void navighateToTab(String tabname){
        waitUntilWebElementListIsVisible(dashboardTabs);
        waitUntilWebElementListIsClickable(dashboardTabs);
        for(WebElement ele: dashboardTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }

}
