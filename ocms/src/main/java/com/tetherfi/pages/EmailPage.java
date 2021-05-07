package com.tetherfi.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailPage extends BasePage {
	
	public EmailPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="#navTabs li")
    private List<WebElement> navTabs;

    @FindBy(css="a[href$='/EmailTemplates/Index']")
    private WebElement emailTemplates;
    
    @FindBy(css="a[href$='/EmailTemplate/Index']")
    private WebElement emailTemplate;

	public boolean isEmailPageDisplayed() {
		boolean status=false;
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        for(WebElement ele:navTabs){
            if(ele.getText().equalsIgnoreCase("Email")){
                if(ele.isEnabled()){
                    status=true;
                }
            }
        }
        return status;
	}

	public void navigateToEmailTemplatesPage() {
		selectWebElement(emailTemplates);
	}
	
	public void navigateToEmailTemplatePage() {
		selectWebElement(emailTemplate);
	}

}
