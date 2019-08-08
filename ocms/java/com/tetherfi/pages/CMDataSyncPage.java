package com.tetherfi.pages;

import com.tetherfi.model.user.CmDataSyncDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import  java.util.List;

public class CMDataSyncPage extends BasePage {

    public CMDataSyncPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css=".ibox-title h5")
    private WebElement cmDataSync;

    @FindBy(css="span[aria-owns='option_listbox']")
    private WebElement objTypeDropdown;

    @FindBy(css="ul[id='option_listbox'] li")
    private List<WebElement> objTypeListBox;

    @FindBy(css="#ModifyReason")
    private WebElement modifyReasonTextBox;

    @FindBy(css=".swal-button--confirm")
    private WebElement saveOkBtn;

    @FindBy(css="#syncButton")
    private WebElement startSyncBtn;

    @FindBy(css=".toast-error .toast-message")
    private List<WebElement> errorMsg;

    @FindBy(css=".toast-message")
    private WebElement successMsg;

    public boolean isCMDataSyncPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return cmDataSync.isEnabled();
    }
    public void agentVdnSynchronization(CmDataSyncDetails cmDataSyncDetails) throws Exception{
        selectWebElement(objTypeDropdown);
        selectDropdownFromVisibleText(objTypeListBox,cmDataSyncDetails.getObjectType());
        enterValueToTxtField(modifyReasonTextBox,cmDataSyncDetails.getModifyReason());
        selectWebElement(startSyncBtn);
        selectWebElement(saveOkBtn);
    }
    public boolean verifyAgentVdnSynchronized(String message){
        waitForJqueryLoad(driver);
      if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successMsg, message))
        {return true;}else{return false;}
    }
}
