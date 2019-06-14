package com.tetherfi.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.FaxTemplateDetails;

import org.openqa.selenium.By;

public class TmacLoginPage extends BasePage {

	public TmacLoginPage(WebDriver driver){super(driver);}
	
	@FindBy(css=".login_heading")
    private WebElement loginHeading;

	@FindBy(id="agentname")
	private WebElement agentname;
	
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
	
	@FindBy(id="btnTextChat_Answer1000")
	private WebElement answerchat;
	
	@FindBy(id="btnTextChat_Disconnect1000")
	private WebElement disconnectchat;
	
	@FindBy(css=".k-widget .k-multiselect")
	private WebElement selectcompletioncode;
	
	@FindBy(css="ul[id='workcodes1000_listbox']li")
	private List<WebElement> workcodelist;
	
	@FindBy(id="divTabHeader1000")
	private WebElement navigatetotab;
	
	@FindBy(id="btnCloseTab1000")
	private WebElement closetab;

	@FindBy(css=".js-modal-confirm")
	private WebElement confirmLogoutbtn;
	
	@FindBy(id="compose_fax_li")
	private WebElement fax;
	
	@FindBy(css="span[aria-owns='DNISList_listbox']")
	private WebElement FaxLineDropDown;
	
	@FindBy(css="ul[id='DNISList_listbox'] li")
	private List<WebElement> faxLineListBox;
	
	@FindBy(id="faxTemplateRadio")
	private WebElement TemplateRadio;
	
	@FindBy(css="span[aria-owns='faxTemplates_listbox']")
	private WebElement faxTemplateDropdown;
	
	@FindBy(css="ul[id='faxTemplates_listbox'] li")
	private List<WebElement> faxTemplateListBox;
	
	 
	public void loginIntoTmac(String landid, String stn) throws Exception{
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
        return (driver.getWindowHandles().size()==2);
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
	
	 public void logintotmac(String LanId,String Station) throws Exception
	    {
		 	waitForJqueryLoad(driver);
	    	enterValueToTxtField(lanId,LanId);
	    	enterValueToTxtField(station,Station);
	    	clickOn(tmacLogin);
	    }
	 
	 public void logintotmac_WQ(String LanId,String Station) throws Exception
	    {
		 	waitForJqueryLoad(driver);
	    	enterValueToTxtField(lanId,LanId);
	    	enterValueToTxtField(password,Station);
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
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	        selectDropdownFromVisibleTextContains(statusList,status);
	        waitForJqueryLoad(driver);
	    }}
	    
	    public boolean workcodelist(String name) throws Exception
	    {
	    	Thread.sleep(2000);
	    	waitUntilWebElementIsClickable(answerchat);
	    	selectWebElement(navigatetotab);
	    	clickOn(answerchat);
	    	Thread.sleep(1000);
	    	clickOn(disconnectchat);
	    	Thread.sleep(2000);
	    	selectWebElement(selectcompletioncode);
	    	selectDropdownFromVisibleText(workcodelist,name);
	    	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	selectWebElement(closetab);
	    	if(agentname.isDisplayed())
	    	return true;
	    	else 
	    		return false;
	    	
	    }
		public boolean verifyworkcodelist(List<Map<String, String>> arr) {
			Map<String,String> map = new HashMap<String,String>();
			List<Map<String,String>> arr1=new ArrayList<Map<String,String>>();

			waitUntilWebElementIsClickable(answerchat);
	    	selectWebElement(answerchat);
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	selectWebElement(disconnectchat);
	    	selectWebElement(selectcompletioncode);		
	    	for(WebElement ele:workcodelist)
	    	{
				map.put("Name",ele.getText());}
	    	map.remove("");
	    	arr1.add(map);
	    	if(arr1.equals(arr))
	    		return true;
	    	else
	    	return false;
		}
		
		public Boolean VerifyDropdownForCreate(FaxLineConfigDetails faxLineConfigDetails) {
			Boolean Status=false;
			selectWebElement(fax);
			selectWebElement(FaxLineDropDown);
			for(int i=0;i<faxLineListBox.size();i++) {
				String value=faxLineListBox.get(i).getText();
				if(value.equals(faxLineConfigDetails.getFaxLine())) {
					Status= true;
				break;}
			}
			return Status;
		}
		public boolean VerifyDropdownForEdit(FaxLineConfigDetails faxLineConfigDetails) {
			Boolean Status=true;
			selectWebElement(fax);
			selectWebElement(FaxLineDropDown);
			for(int i=0;i<faxLineListBox.size();i++) {
				String value=faxLineListBox.get(i).getText();
				if(value.equals(faxLineConfigDetails.getFaxLine())) {
					Status= false;
				break;}
			}
			return Status;
		}
		public boolean verifyFaxTemplate(FaxTemplateDetails faxTemplateDetails) {
			Boolean Status=false;
			selectWebElement(fax);
			clickOn(TemplateRadio);
			selectWebElement(faxTemplateDropdown);
			for(WebElement ele: faxTemplateListBox)
			{
				if(ele.getText().equals(faxTemplateDetails.getTemplateName())) {
					selectWebElement(ele);
					Status= true;
				break;
				}
			}
			return Status;
		}

}
