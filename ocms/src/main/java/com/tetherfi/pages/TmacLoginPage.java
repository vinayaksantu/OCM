package com.tetherfi.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;

public class TmacLoginPage extends BasePage {

	public TmacLoginPage(WebDriver driver){super(driver);}
	
	@FindBy(css=".login_heading")
    private WebElement loginHeading;

    @FindBy(css="span[aria-owns='domainList_listbox']")
    private WebElement domainListDropdown;

    @FindBy(css="ul[id='domainList_listbox'] li")
    private List<WebElement> domainList;

    @FindBy(id="lan_id")
    private WebElement lanID;

    @FindBy(id="station_no")
    private WebElement station;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(id="login_btn")
    private WebElement loginBtn;

    @FindBy(css=".uk-notify-message")
    private WebElement errorMsg;

    @FindBy(css=".uk-modal-content")
    private WebElement warningMsg;

    @FindBy(css=".js-modal-confirm")
    private WebElement confirmSessionTakeOver;

    @FindBy(css=".js-modal-confirm-cancel")
    private WebElement cancelSessionTakeOver;
	
	@FindBy(id="lan_id")
	private WebElement lanId;

	@FindBy(id="login_btn")
	private WebElement tmacLogin;
	
	@FindBy(id="div_main")
	private WebElement main;
	
	@FindBy(id="marquee")
	private WebElement marque;
	 
	@FindBy(id="divAuxStatus")
	private WebElement status;

	@FindBy(id="btn_change_status")
	private WebElement changeStatus;
	 
	@FindBy(css="ul[id='auxstatuslist'] li")
	private List<WebElement> statusList;
	 
	@FindBy(id="agentstatus")
	private WebElement agentStatus;
	    
	@FindBy(id="main_logout_btn")
	private WebElement logoutBtn;

	@FindBy(css=".js-modal-confirm")
	private WebElement confirmLogoutbtn;
	 
	public void loginIntoTmac(String landid, String stn){
        //selectWebElement(domainListDropdown);
        //selectDropdownFromVisibleText(domainList,details.getDomain());
        selectWebElement(lanID);
        enterValueToTxtField(lanID,landid);
        selectWebElement(station);
        enterValueToTxtField(station,stn);
        //selectWebElement(password);
        //enterValueToTxtField(password,details.getPassword());
        selectWebElement(loginBtn);
    }
    public boolean verifyUserLogged(){
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        clickOnConfirmSessionTakeOver();
        return (driver.getWindowHandles().size()==3);
    }
    public String getErrorMessage(){
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(errorMsg);
        return errorMsg.getText();
    }
    public String getWarningMessage(){
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(warningMsg);
        return warningMsg.getText();
    }
    public void clickOnConfirmSessionTakeOver(){
        if(driver.findElements(By.cssSelector(".js-modal-confirm")).size()>0)
        {selectWebElement(confirmSessionTakeOver);
            waitForLoad(driver);
            waitForJqueryLoad(driver);
        }}
    public void clickOnCancelSessionTakeOver(){
        if(driver.findElements(By.cssSelector(".js-modal-confirm-cancel")).size()>0)
        {selectWebElement(cancelSessionTakeOver);
            waitForLoad(driver);
            waitForJqueryLoad(driver);
        }}
    public boolean verifyErrorMessageContains(String text){
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(errorMsg);
        return errorMsg.getText().contains(text);
    }
	
	 public void logintotmac(String LanId,String Station)
	    {
		 	waitForJqueryLoad(driver);
	    	enterValueToTxtField(lanId,LanId);
	    	enterValueToTxtField(station,Station);
	    	clickOn(tmacLogin);
	    	
	    }
	 
	 public boolean isTmacPopUpDisplayed(){
		    return main.isDisplayed();
		    }

	public String broadcastMsg() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitUntilWebElementIsVisible(marque);
		String value=marque.getText();
		return value;
	}
	public Boolean Status()
	{	try {
		Thread.sleep(500);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		if(marque.isDisplayed())
			return false;
		else
		return true;
		
	}
	
	public void userLogout(){
	    if(!getCurrentStatus().equals("10 - Logout")){
	    selectWebElement(changeStatus);
	    selectDropdownFromVisibleText(statusList, "10 - Logout");}
	    waitUntilWebElementIsVisible(logoutBtn);
	    clickOn(logoutBtn);
	    selectWebElement(confirmLogoutbtn);
	    switchBackToParentWindow();
	    }

	    public String getCurrentStatus(){
	        waitUntilWebElementIsVisible(status);
	        return status.getText();
	    }
	    public String getAgentStatus(){
	        waitUntilWebElementIsVisible(agentStatus);
	        return agentStatus.getText();
	    }
	    public void changeStatus(String status){
	        if(!getCurrentStatus().equals(status)){
	        selectWebElement(changeStatus);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectDropdownFromVisibleText(statusList,status);
	        waitForJqueryLoad(driver);
	    }}


}
