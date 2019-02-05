package com.tetherfi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserManagementPage extends BasePage {

    public UserManagementPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement userManagement;
    
    @FindBy(xpath="//i[@class='far fa-users-cog']")
    private WebElement uMImg;

    @FindBy(id="create")
    private WebElement addNewUserManagementRecordBtn;

    @FindBy(css =".k-edit-form-container #UserId")
    private WebElement userIdTextBox;

    @FindBy(css=".k-edit-form-container .k-grid-update")
    private WebElement saveBtn;

    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css="#toast-container .toast-error")
    private List<WebElement> errorMsg;

    @FindBy(css=".search-link")
    private WebElement searchLink;

    @FindBy(css=".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchColumn;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchTypeList;

    @FindBy(css=".modal-body .form-inline .form-group .k-textbox")
    private List<WebElement> searchText;

    @FindBy(css=".modal-footer .k-primary")
    private WebElement searchBtn;

    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;

    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;

    @FindBy(id = "ModifyReason")
    private WebElement editModifyReasonTextBox;

    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;

    @FindBy(id="yesButton")
    private WebElement yesBtn;
    
    @FindBy(id="noButton")
    private WebElement noBtn;
    
    @FindBy(xpath="//table[@class='k-selectable']/tbody/tr/td[2]")
    private WebElement rowdata;
    
    @FindBy(xpath="//table[@class='k-selectable']/tbody/tr")
    private WebElement tablerow;
    
    @FindBy(id="DrillReportNameLbl")
    private WebElement PageBasedAccess;
    
    @FindBy(id="checkAll")
    private WebElement checkallAccess;

    @FindBy(tagName="td")
    private List<WebElement> coloums;
    
    @FindBy(id="createone")
    private WebElement saveaccess;
    
    @FindBy(xpath="//div[@onclick='saveChangeYes()']")
    private WebElement useryesBtn;
    
    @FindBy(id="ModifyReasonUser")
    private WebElement userModifyReasontxtbox;
    
    @FindBy(xpath="//div[@class='modal inmodal fade in' ]/div/div/div/button[@class='close']")
    private WebElement pbuaclose;
    
    @FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(id="navbarheader")
	private WebElement header;
	
	@FindBy(xpath="//table[@class='k-selectable']/tbody/tr")
	private List<WebElement> tablerecord;
	
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
    
    public boolean isUserManagementPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return userManagement.isEnabled();
    }
    
    public boolean verifylogo() 
	{
		if(isElementExist(uMImg)) 
		{return true;}
		else return false;
	}
    
    public boolean maximizewindow() {
		selectWebElement(maximize);
		waitForJqueryLoad(driver);
		if(fullscreen.isEnabled())
		return true;
		else 
		{return false;}
	}

	public boolean minimizewindow() {
		selectWebElement(minimize);
		waitForJqueryLoad(driver);
		if(header.isDisplayed())
		return true;
		else 
			return false;}

	public boolean verifygridcontent() {
		int size=tablerecord.size();
		System.out.println(+size);
		String item[]=(items.getText()).split("\\s+");
		int itemno=Integer.parseInt(item[2]);
		System.out.println(itemno);
		if(itemno==size)
			return true;
		else 
			return false;}
	
    public void addNewUserManagementRecord(String userID) {
        selectWebElement(addNewUserManagementRecordBtn);
        selectWebElement(userIdTextBox);
        enterValueToTxtField(userIdTextBox,userID);
        selectWebElement(saveBtn);
    }
    public void editUserManagementRecord(String UserID, String reason,String user) {
        searchUserManagementRecord(user);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editButton);
        enterValueToTxtField(userIdTextBox,UserID);
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        enterValueToTxtField(editModifyReasonTextBox,reason);
        selectWebElement(saveBtn);
    }
    public void searchUserManagementRecord(String UserID) {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),UserID);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
    }
    public void deleteUserManagementRecord(String username, String reason) {
        searchUserManagementRecord(username);
        selectWebElement(deleteBtn);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }
    public boolean verifyNewRecordCreated(){
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordDeleted(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully")){
            return true;
        }else{return false;}
    }
	public boolean isPageBasedUserAccessPageDisplayed() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		if(PageBasedAccess.isDisplayed())
			return true;
		else
		return false;
	}
	
	public void ProvideAccess(String value) throws Exception {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		for(int i=0;i<coloums.size();i++) {
			if (coloums.get(i).getText().equals(value))
			{
				WebElement checkAccess=coloums.get(i-1).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				break;
			}
			}
		selectWebElement(saveaccess);
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
	}
	public void clearAccess() throws Exception {
		Thread.sleep(500);
		selectWebElement(checkallAccess);
		if(checkallAccess.isSelected()) {
		selectWebElement(checkallAccess);}
		selectWebElement(saveaccess);
		try {
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtField(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
		}
		catch(Exception e)
		{
			moveToElement(pbuaclose);
			selectWebElement(pbuaclose);	
		}
		
	}
	public void ProvideAddAccess(String value) throws Exception 
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		for(int i=0;i<coloums.size();i++) {
			if (coloums.get(i).getText().equals(value))
			{
				WebElement checkAccess=coloums.get(i-1).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				WebElement AddAccess=coloums.get(i+1).findElement(By.id("checkAddAccess"));
				selectWebElement(AddAccess);
				break;
			}
			}
		selectWebElement(saveaccess);
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
		
	}
	public void ProvideEditAccess(String value) throws Exception {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		for(int i=0;i<coloums.size();i++) {
			if (coloums.get(i).getText().equals(value))
			{
				WebElement checkAccess=coloums.get(i-1).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				WebElement EditAccess=coloums.get(i+2).findElement(By.id("checkEditAccess"));
				selectWebElement(EditAccess);
				break;
			}
			}
		selectWebElement(saveaccess);
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(2000);
		selectWebElement(useryesBtn);
		
	}
	public void ProvideDeleteAccess(String value) throws Exception {
			Thread.sleep(1000);
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		for(int i=0;i<coloums.size();i++) {
			if (coloums.get(i).getText().equals(value))
			{
				WebElement checkAccess=coloums.get(i-1).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				WebElement DeleteAccess=coloums.get(i+3).findElement(By.id("checkDeleteAccess"));
				selectWebElement(DeleteAccess);
				break;
			}
			}
		selectWebElement(saveaccess);
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
		
	}
	public void ProvideExportAccess(String value) throws Exception {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		for(int i=0;i<coloums.size();i++) {
			if (coloums.get(i).getText().equals(value))
			{
				WebElement checkAccess=coloums.get(i-1).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				WebElement ExportAccess=coloums.get(i+4).findElement(By.id("checkExportAccess"));
				selectWebElement(ExportAccess);
				break;
			}
			}
		selectWebElement(saveaccess);
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
		
	}

	public boolean addNewCancel(String userId) {
		String actualitems=items.getText();
		selectWebElement(addNewUserManagementRecordBtn);
        selectWebElement(userIdTextBox);
        enterValueToTxtField(userIdTextBox,userId);
        selectWebElement(cancelBtn);
        if(actualitems.equals(items.getText()))
        	return true;
        else
		return false;
	}
	public void addUserManagementRecord(String userID) {
        selectWebElement(addNewUserManagementRecordBtn);
        selectWebElement(userIdTextBox);
        enterValueToTxtField(userIdTextBox,userID);
        selectWebElement(saveBtn);
        selectWebElement(cancelBtn);
	}

	public void addinvalidrecord() {
		selectWebElement(addNewUserManagementRecordBtn);
		selectWebElement(saveBtn);
		selectWebElement(cancelBtn);
	}

	public void DuplicateRecord(String userId) {
		selectWebElement(addNewUserManagementRecordBtn);
        selectWebElement(userIdTextBox);
        enterValueToTxtField(userIdTextBox,userId);
        selectWebElement(saveBtn);
        try {
        selectWebElement(cancelBtn);
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

	public boolean editcancel(String UserID, String reason,String user) {
		searchUserManagementRecord(user);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editButton);
        enterValueToTxtField(userIdTextBox,UserID);
        enterValueToTxtField(editModifyReasonTextBox,reason);
        selectWebElement(cancelBtn);
        if(rowdata.getText().equals(user))
        	return true;
        else
        	return false;
        
	}
	
}
