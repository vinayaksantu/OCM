package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ChatPage extends BasePage {
    public ChatPage(WebDriver driver){super(driver);}

    @FindBy(css="#navTabs li")
    private List<WebElement> navTabs;

    @FindBy(css="a[href$='/ChatTemplates/Index']")
    private WebElement chatTemplates;

    @FindBy(css="a[href$='/IvrCallback/Index']")
    private WebElement callBackMgmt;

    @FindBy(css="a[href$='/SoiIntentMapping/Index']")
    private WebElement soiIntentMapping;

    @FindBy(css="a[href$='/ChatIntentSkillMapping/Index']")
    private WebElement intentSkillMapping;
    
    @FindBy(css="a[href$='/ChatMenuDescription/Index']")
    private WebElement chatmenudescription;
    
    @FindBy(css="a[href$='/ChatMenuDescription/Index']")
    private WebElement chatConfigurations;
    
    public boolean isChatPageDisplayed() {
        boolean status=false;
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        for(WebElement ele:navTabs){
            if(ele.getText().equalsIgnoreCase("CHAT")){
                if(ele.isEnabled()){
                    status=true;
                }
            }
        }
        return status;
    }
    public void navigateToChatTemplatesPage(){
        selectWebElement(chatTemplates);
    }
    public void navigateToCallBackMgmtPage(){
        waitUntilWebElementIsClickable(callBackMgmt);
        selectWebElement(callBackMgmt);
    }
    public void navigateToSoiIntentMappingPage(){
        waitUntilWebElementIsClickable(soiIntentMapping);
        selectWebElement(soiIntentMapping);
    }

    public void navigateToChatIntentSkillMappingPage(){
        waitUntilWebElementIsClickable(intentSkillMapping);
        selectWebElement(intentSkillMapping);
    }
	public void navigateToChatMenuDescriptionPage() {
		waitUntilWebElementIsClickable(chatmenudescription);
        selectWebElement(chatmenudescription);
	}
	public void navigateToChatConfigurationsPage() {
		waitUntilWebElementIsClickable(chatConfigurations);
        selectWebElement(chatConfigurations);
	}
}
