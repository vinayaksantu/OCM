package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import javax.swing.Spring;

public class OCMDashboardPage extends BasePage {

    public OCMDashboardPage(WebDriver driver){super(driver);}

    @FindBy(css = "#dashboardNavigationTabs li")
    private List <WebElement> dashboardNavList;
    
    public boolean checkDashboardElementExists(String value) {
		for(WebElement we: dashboardNavList) {
			System.out.println(we.getText());
			if(we.getText().equals(value))
				if(we.isDisplayed())
				return true;
		}
    	return false;
    }

}
