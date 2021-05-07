package com.tetherfi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.user.UserDetails;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @FindBy(xpath="//div[@class='k-edit-buttons k-state-default']/a[@class='k-button k-button-icontext k-grid-cancel']")
    private WebElement editcancel;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css="#toast-container .toast-error .toast-message")
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

    @FindBy(css=".fa-search")
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
    private List<WebElement> checkallAccess;
    
    @FindBy(id="checkAllReport")
    private WebElement checkAllReport;
    
    @FindBy(id="checkAllDashboard")
    private WebElement checkAllDashboard;
    
    @FindBy(id="checkAllAddAccess")
    private List<WebElement> checkalladdaccess;
    
    @FindBy(id="checkAllEditAccess")
    private List<WebElement> checkalleditaccess;
    
    @FindBy(id="checkAllDeleteAccess")
    private List<WebElement> checkalldeleteaccess;
    
    @FindBy(id="checkAllExportAccess")
    private List<WebElement> checkallexportaccess;
    
    @FindBy(id="checkAllReportExportAccess")
    private WebElement checkAllReportExportAccess;
    
    @FindBy(id="checkAll")
    private List <WebElement> accessAll;
    
    @FindBy(id="createone")
    private List<WebElement> saveaccess;
    
    @FindBy(xpath="//div[@onclick='saveChangeYes()']")
    private WebElement useryesBtn;
    
    @FindBy(id="ModifyReasonUser")
    private WebElement userModifyReasontxtbox;
    
    @FindBy(xpath="//div[@class='modal-dialog modal-lg animated bounceInRight' ]/div/div/div/button[@class='close']")
    private WebElement pbuaclose;
    
    @FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(id="navbarheader")
	private WebElement header;
	
	@FindBy(xpath="//a[text()='User Id']")
	private WebElement userId;
	
	@FindBy(xpath="//table[@class='k-selectable']/tbody/tr")
	private List<WebElement> tablerecord;
	
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
	
	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;
	
	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;
    
	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
	private WebElement clearsearch;

	@FindBy(css=".k-grid-content")
	private WebElement gridcontent;
	
	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;
	
	@FindBy(xpath="//*[@id='grid']/div[5]/a[3]/span")
	private WebElement nextPageIcon;
	
	@FindBy(id="grid")
	private WebElement auditGridContent;
	
	@FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;
	
	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
	
	@FindBy(xpath="//a[text()='User Id']")
	private WebElement UserId;
	
	@FindBy(css="a[aria-label='Go to the previous page']")
	private WebElement previousPageIcon;

	@FindBy(css=".k-pager-sizes .k-icon")
	private WebElement pagerDropdown;
	
	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;
	
	@FindBy(css="a[aria-label='Go to the last page']")
	private WebElement lastPageIcon;
	
	@FindBy(css=".k-pager-numbers .k-state-selected")
	private WebElement pageNumber;
	
	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;
	
	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;
	
	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;
	
	@FindBy(css="a[aria-label='Go to the first page']")
    private WebElement firstPageIcon;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
	
	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
	private WebElement userIdgroupby;
	
	@FindBy(xpath="//span[text()='Reports']")
	private WebElement reportsTab;
	
	@FindBy(xpath="//span[text()='Dashboards']")
	private WebElement dashboardTab;
	
	@FindBy(xpath="//span[text()='Other Applications']")
	private WebElement otherAppsTab;
	
	@FindBy(xpath="//div[@id='gridUserAccessDetails']/div[3]/table/tbody/tr[@role='row']")
	private List <WebElement> adminPagesTableRows;
	
	@FindBy(xpath="//div[@id='gridReportUserAccessDetails']/div[3]/table/tbody/tr[@role='row']")
	private List <WebElement> reportsTableRows;
	
	@FindBy(xpath="//div[@id='gridDashboardAccessDetails']/div[3]/table/tbody/tr[@role='row']")
	private List <WebElement> dashboardTableRows;
	
	@FindBy(xpath="//div[@id='gridOtherApplicationsAccessDetails']/div[3]/table/tbody/tr[@role='row']")
	private List <WebElement> otherAppsTableRows;
	
	@FindBy(xpath="//div[@id='DrillReportNameLbl']/h2")
	private WebElement pagebaseduseraccess;
	
	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel-changes']")
	private List<WebElement> pbuacancel;
	
	@FindBy(id="myWindowUser_wnd_title")
	private WebElement modifypopup;
	
	@FindBy(css=".toast-info .toast-message")
	private WebElement changesMsg;
	
	@FindBy(xpath="//span[text()='Reports']")
	private WebElement navigatetoreports;
	
	@FindBy(xpath="//span[text()='Dashboards']")
	private WebElement navigatetodashboard;
	
	@FindBy(id="checkAllOtherApplications")
    private WebElement checkallotherapplication;
	
	@FindBy(id="checkAllAdminPagesChecker")
	private WebElement checkAllAdminPagesChecker;
	
	@FindBy(xpath="//span[text()='Other Applications']")
	private WebElement navigatetootherapplication;
	
	@FindBy(xpath="//span[text()='Admin Pages Checker']")
	private WebElement navigatetoAdminPagesChecker;
	
	@FindBy(css = ".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchCol;
	
	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchCriteriaDropDwn;
	
	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;
    
    @FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;
    
    @FindBy(css=".k-grid-content")
	private WebElement gridContent;
	
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
	
    public void addNewUserManagementRecord(String userID) throws Exception {
        selectWebElement(addNewUserManagementRecordBtn);
        selectWebElement(userIdTextBox);
        enterValueToTxtField(userIdTextBox,userID);
        selectWebElement(saveBtn);
    }
    public void editUserManagementRecord(String UserID, String reason,String user) throws Exception {
        searchUserManagementRecord(user);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editButton);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtField(userIdTextBox,UserID);
        enterValueToTxtFieldWithoutClear(editModifyReasonTextBox,reason);
        selectWebElement(saveBtn);
        try {
        selectWebElement(editcancel);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    public void searchUserManagementRecord(String UserID) throws Exception {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchTextBox,UserID);		
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
    }
    
    public boolean verifySearchIsNotEqualTo(String userid) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("User Id", userid);
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
        enterValueToTxtField(searchTextBox,userid);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.equals(map))
        	Status= false;
        	else 
        		Status= true;
	}
        return Status;
	
	}
    
    public boolean verifySearchContains(String userid) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
        enterValueToTxtField(searchTextBox,userid);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("User Id").toUpperCase().contains(userid.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	public boolean verifySearchDoesNotContains(String userid) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
        enterValueToTxtField(searchTextBox,userid);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(!map1.get("User Id").toLowerCase().contains(userid.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	
	public boolean verifySearchStartsWith(String userid) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
        enterValueToTxtField(searchTextBox,userid);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("User Id").toLowerCase().startsWith(userid.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	
	public boolean verifySearchEndsWith(String userid) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
        enterValueToTxtField(searchTextBox,userid);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("User Id").toUpperCase().endsWith(userid.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
    public void deleteUserManagementRecord(String username, String reason) throws Exception {
        searchUserManagementRecord(username);
        selectWebElement(deleteBtn);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }
    public boolean verifyNewRecordCreated(){
        //if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        //if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordDeleted(){
        waitForJqueryLoad(driver);
        //if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully")){
            return true;
        }else{return false;}
    }
    
    public Boolean verifyErrorMessage() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0){
			return true;}
			else 
				return false;
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
	
	public void ProvideAccess(String value, String accessLvl) throws Exception {
		Thread.sleep(2000);
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		for(int i=0;i<adminPagesTableRows.size();i++) {
			System.out.println(adminPagesTableRows.get(i).getText());
			if (adminPagesTableRows.get(i).getText().equals(value))
			{	
				WebElement checkAccessStatus=adminPagesTableRows.get(i).findElement(By.id("checkAccess"));
				if(checkAccessStatus.isSelected() == false) {
					WebElement checkAccess=adminPagesTableRows.get(i).findElement(By.id("checkAccess"));
					selectWebElement(checkAccess);
				}
				if(accessLvl.equals("Add")) {
					WebElement AddAccess=adminPagesTableRows.get(i).findElement(By.id("checkAddAccess"));
					selectWebElement(AddAccess);
				}
				if(accessLvl.equals("Edit")) {
					WebElement EditAccess=adminPagesTableRows.get(i).findElement(By.id("checkEditAccess"));
					selectWebElement(EditAccess);
				}
				if(accessLvl.equals("Delete")) {
					WebElement DeleteAccess=adminPagesTableRows.get(i).findElement(By.id("checkDeleteAccess"));
					selectWebElement(DeleteAccess);
				}
				if(accessLvl.equals("Export")) {
					WebElement ExportAccess=adminPagesTableRows.get(i).findElement(By.id("checkExportAccess"));
					selectWebElement(ExportAccess);
				}
				break;
			}
			}
		selectWebElement(saveaccess.get(0));
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
	}
	
	public void ProvideReportAccess(String value, String accessLvl) throws Exception {
		Thread.sleep(2000);
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		navigateToReportsTab();
		Thread.sleep(2000);
		for(int i=0;i<reportsTableRows.size();i++) {
			System.out.println(reportsTableRows.get(i).getText());
			if (reportsTableRows.get(i).getText().equals(value))
			{
				WebElement checkAccess=reportsTableRows.get(i).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				if(accessLvl.equals("Export")) {
					WebElement checkExportAccess=reportsTableRows.get(i).findElement(By.id("checkExportAccess"));
					selectWebElement(checkExportAccess);
				}
				break;
			}
			}
		selectWebElement(saveaccess.get(1));
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
	}
	
	public void clearAccess() throws Exception {
		Thread.sleep(500);
		selectWebElement(checkallAccess.get(0));
		if(checkallAccess.get(0).isSelected()) {
		selectWebElement(checkallAccess.get(0));}
		selectWebElement(saveaccess.get(0));
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
	
	public boolean addNewCancel(String userId) throws Exception {
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
	public void addUserManagementRecord(String userID) throws Exception {
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

	public void DuplicateRecord(String userId) throws Exception {
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

	public boolean editcancel(String UserID, String reason,String user) throws Exception {
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

	public boolean clearAll(UserDetails userDetails) throws Exception {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchTextBox,userDetails.getUserId());		
        selectWebElement(clearall);
        if(searchTextBox.isEnabled())
        	return true;
        else
		return false;
	}

	public boolean verifyclose() {
		selectWebElement(searchClose);
		if(gridcontent.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean verifyclearsearch() {
		selectWebElement(clearsearch);
		if(gridcontent.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean verifyinvalidsearch(UserDetails userDetails) throws Exception {
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		searchUserManagementRecord(userDetails.getUserId());
		if(norecords.isDisplayed())
		return true; 
		else
			return false;
	}

	public boolean verifyDatabase(String query) {
		List<Map<String,String>> database=database(query);
		System.out.println(database);
		List<Map<String,String>> UI=gettable(); 
		System.out.println(UI);
		if(UI.equals(database))
			return true;
		else
			return false;
	}
	private List<Map<String, String>> gettable() {
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.getText());
        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		for(int i=1;i<rows.size();i++) {
			Map<String,String> map = new HashMap<String,String>();
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			String col=null;
			for(int j=1;j<headers.size();j++){
				if(headers.get(j).getText().equals("Last Changed On")){
					col=cols.get(j).getText().substring(10);
					}
				else
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			map.remove("");
			arr.add(map);
		}
		if(k!=pages)
		{
			nextPageIcon.click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}

	public boolean groupby() {
		DragandDrop(UserId,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(userIdgroupby.getText()))
		{return true;}
		else
			return false;	
	}

	public Boolean deleteUserManagementRecordNoBtn(String userId, String deleteReason) throws Exception {
		searchUserManagementRecord(userId);
        selectWebElement(deleteBtn);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtField(deleteReasonTextBox,deleteReason);
        selectWebElement(noBtn);
        if(rowdata.getText().equals(userId))
        	return true;
        else
        	return false;
	}	
	public boolean verifyNumberOfItemsPerPage() {
        boolean status = false;
        try {
          //  if (norecords.size() <= 0) {
                int item = Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
                selectWebElement(pagerDropdown);
                Thread.sleep(1500);
                for (int i = 0; i < pageSizeListBox.size(); i++) {
                    if(Integer.valueOf(pageSizeListBox.get(i).getText())>item){continue;}
                    selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
                    waitForJqueryLoad(driver);
                    int totalItems = Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
                    int pagersize = Integer.valueOf(pagerSize.getText());
                    int pages = (totalItems % pagersize == 0) ? item / pagersize : item / pagersize+1;
                    int totalRows=(gridcontent.findElements(By.tagName("tr")).size());
                    selectWebElement(lastPageIcon);
                    waitForJqueryLoad(driver);
                    int lastPageNumber = Integer.valueOf(pageNumber.getText());
                    if (item == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
                        status = true;
                    } else {System.out.println(items+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
                        status = false;
                        break;
                    }selectWebElement(pagerDropdown);Thread.sleep(1500);
                }
           // }
        } catch (Exception e) {
            e.printStackTrace();
        } return status;
    }
    public boolean verifyTotalNumberOfItemsPerPageDetails(){
        String item = items.getText();
        return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
    }

    public boolean verifyDropDownOfAllHeaders() {
        boolean status = false;
        try {
        	for(int i=0;i<3;i++) {
        		WebElement ele =headersDropdown.get(i);
        				scrollToElement(ele);
        				status = false;
        				if (!ele.isDisplayed()) {
        					System.out.println(i + "\n");
        					continue;
        				} else {
        					selectWebElement(ele);
        					Thread.sleep(1000);
        					if (headersColumns.get(0).getText().equals("Sort Ascending")) {
        						if (headersColumns.get(1).getText().equals("Sort Descending")) {
        							if (headersColumns.get(2).getText().equals("Columns")) {
        								status = true;selectWebElement(ele);
        							}
        						}
        					}
        					if (status) {
        					} else {
        						break;
        					}
        					Thread.sleep(1000);
        				}
        		}
        	} catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    public boolean verifycolumnsHeaderDisabled() {
        boolean status = false;
        WebElement ele = headersDropdown.get(1);
            if (ele.isDisplayed()) {
                try {
                    selectWebElement(ele);
                    Thread.sleep(1000);
                    selectWebElement(headersColumns.get(2));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i =3; i < headersColumns.size(); i++) {
                    System.out.println(headersColumns.get(i).getText());
                    WebElement checkbox = headersColumns.get(i).findElement(By.tagName("input"));
                    if (checkbox.isSelected()) {
                        checkbox.click();
                    } else {
                    }
                    for (WebElement ele1 : headersText) {
                        if (ele1.getText().equals(headersColumns.get(i).getText())) {
                            status = true;
                            break;
                        }
                    }
                    if (!status) {
                        checkbox.click();
                    } else {
                        break;
                    }
                }

            }
        return status;
    }
    public boolean verifycolumnsHeaderEnabled(){
        boolean status=false;
        WebElement ele= headersDropdown.get(1);
            if(ele.isDisplayed()){
                try {
                    selectWebElement(ele);
                    Thread.sleep(1000);
                    selectWebElement(headersColumns.get(2));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 3; i <headersColumns.size(); i++) {
                    WebElement checkbox = headersColumns.get(i).findElement(By.tagName("input"));
                    checkbox.click();
                    if (checkbox.isSelected()) {
                    } else {
                        checkbox.click();
                    }
                    for (WebElement ele1 : headersText) {
                        if (ele1.getText().equals(headersColumns.get(i).getText())) {
                            status = true;
                            break;
                        }
                    }
                    if (status) {
                    } else {
                        break;
                    }
                }
            }
        return status;
    }	
    public boolean verifyArrowMoveForPreviousAndNextPage(){
        boolean status=false;
        if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
        int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
        selectWebElement(nextPageIcon);
        int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
        selectWebElement(previousPageIcon);
        int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
        if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
        }else{
            System.out.println("previous and next page icon disabled");status=true;
        }
        return status;
	}
	public boolean verifyArrowMoveForFirstAndLastPage(){
        boolean status=false;
        if(!lastPageIcon.getAttribute("class").contains("k-state-disabled")){
            int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            selectWebElement(lastPageIcon);
            int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            selectWebElement(firstPageIcon);
            int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
        }else{
            System.out.println("previous and next page icon disabled");status=true;
        }
        return status;
    }
    public boolean verifyExportToExcel(String filePath) {   	
    	final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("User Management")) {
		        f.delete();
		    }
		}
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "User Management");
		return Status;
	}
    public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
		List<Map<String,String>> UI=getdata(); 
		if(UI.equals(maplist))
		return true;
		else
		return false;
	}
	
	private List<Map<String,String>> getdata(){
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.getText());
        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		String col=null;
		for(int i=1;i<rows.size();i++) {
			Map<String,String> map = new HashMap<String,String>();
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			for(int j=1;j<headers.size();j++) {
				if(headers.get(j).getText().equals("Last Changed On")){
					col=cols.get(j).getText().substring(0,10);
					}
				else
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			map.remove("");
			arr.add(map);
		}
		if(k!=pages)
		{
			nextPageIcon.click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}

	public void editInvalidUserManagementRecord(String updatedUserId, String user) throws Exception {
		searchUserManagementRecord(user);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editButton);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtField(userIdTextBox,updatedUserId);
        selectWebElement(saveBtn);	
        selectWebElement(editcancel);
	}

	public void editInvalidUserManagementRecord2(String reason, String userId2) throws Exception {
		searchUserManagementRecord(userId2);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editButton);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtField(editModifyReasonTextBox,reason);
        selectWebElement(saveBtn);		
        selectWebElement(editcancel);
	}

	public void SortByAscending() {
		selectWebElement(userId);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(userId);
		selectWebElement(userId);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean ExporttoExcelWithoutData(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		waitForJqueryLoad(driver);
		selectWebElement(exporttoexcel);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}
	
	public boolean verifypagebaseduseraccess(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(pagebaseduseraccess.isDisplayed()) {
			return true;
		}
		else
		return false;
	}
	
	public boolean verifyclosebutton()
	{
		moveToElement(pbuaclose);
		selectWebElement(pbuaclose);
		if(rowdata.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean verifyaccesscheckbox(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=false;
		//if(!checkallAccess.isEnabled())
		selectWebElement(checkallAccess.get(0));
		if(checkallAccess.get(0).isEnabled()) {
			selectWebElement(checkalladdaccess.get(0));
				if(checkalladdaccess.get(0).isEnabled()) {
					selectWebElement(checkalleditaccess.get(0));
					if(checkalleditaccess.get(0).isEnabled())
					{
						selectWebElement(checkalldeleteaccess.get(0));
						if(checkalldeleteaccess.get(0).isEnabled())
						{
							selectWebElement(checkallexportaccess.get(0));
							if(checkallexportaccess.get(0).isEnabled())
							{
							Status=true;
							}
						}
				
					}
				}
		}
		return Status;
	}

	public boolean verifycancelchanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(checkallAccess.get(0));
		Thread.sleep(500);
		selectWebElement(pbuacancel.get(0));
		Thread.sleep(1000);
		if(checkallAccess.get(0).isSelected())
			return false;
		else
		return true;
	}		
	public boolean verifysavechanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		Boolean Status=false;
		selectWebElement(checkallAccess.get(0));
		selectWebElement(saveaccess.get(0));
		if(modifypopup.isDisplayed())
		{	
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtFieldWithoutClear(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
			Status=true;
		}
		return Status;
	}	
	public boolean verifyunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());									 
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(pbuacancel.get(0));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
	
	public boolean verifyunsuccessfullsavechanges(UserDetails userDetails) throws Exception{
		searchUserManagementRecord(userDetails.getUserId());									 
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(saveaccess.get(0));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
	
	public boolean verifyreportsaccesscheckbox(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoreports);
		Boolean Status=false;
		selectWebElement(checkAllReport);
		if(checkAllReport.isSelected())
		{
			selectWebElement(checkAllReportExportAccess);
			if(checkAllReportExportAccess.isSelected())
			{
				Status=true;
			}
		}
		return Status;
	}

	public boolean verifyReportscancelchanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoreports);
		Thread.sleep(1000);
		selectWebElement(checkAllReport);
		selectWebElement(pbuacancel.get(2));
		Thread.sleep(1000);
		if(checkAllReport.isSelected())
			return false;
		else
		return true;
	}

	public boolean verifyReportssavechanges(UserDetails userDetails) throws Exception {
		Boolean Status =false;
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoreports);
		Thread.sleep(1000);
		selectWebElement(checkAllReport);
		selectWebElement(saveaccess.get(2));
		if(modifypopup.isDisplayed())
		{	Status=true;
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtFieldWithoutClear(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
		}
		return Status;
	}

	public boolean verifyreportsunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetoreports);
		selectWebElement(pbuacancel.get(2));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifyreportsunsuccessfullsavechanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetoreports);
		selectWebElement(saveaccess.get(2));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifydashboardaccesscheckbox(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetodashboard);
		Boolean Status=false;
		selectWebElement(checkAllDashboard);
		if(checkAllDashboard.isSelected())
		{
				Status=true;
		}
		return Status;
	}


	public boolean verifyDashboardcancelchanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetodashboard);
		Thread.sleep(1000);
		selectWebElement(checkAllDashboard);
		selectWebElement(pbuacancel.get(3));
		Thread.sleep(1000);
		if(checkAllDashboard.isSelected())
			return false;
		else
		return true;
	}

	public boolean verifyDashboardsavechanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		selectWebElement(navigatetodashboard);
		waitForJqueryLoad(driver);
		Boolean Status=false;
		selectWebElement(checkAllDashboard);
		selectWebElement(saveaccess.get(3));
		if(modifypopup.isDisplayed())
		{	
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtFieldWithoutClear(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
			Status=true;
		}
		return Status;
	}

	public boolean verifydashboardunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetodashboard);
		selectWebElement(pbuacancel.get(3));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifydashboardunsuccessfullsavechanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetodashboard);
		selectWebElement(saveaccess.get(3));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
	
	public boolean verifyotherapplicationaccesscheckbox(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetootherapplication);
		Boolean Status=false;
		selectWebElement(checkallotherapplication);
		if(checkallotherapplication.isSelected())
			{
				Status=true;
			}
		return Status;
	}


	public boolean verifyotherapplicationcancelchanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetootherapplication);
		Thread.sleep(1000);
		selectWebElement(checkallotherapplication);
		selectWebElement(pbuacancel.get(4));
		Thread.sleep(1000);
		if(checkallotherapplication.isSelected())
			return false;
		else
		return true;
	}

	public boolean verifyotherapplicationsavechanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetootherapplication);
		Thread.sleep(1000);
		waitForJqueryLoad(driver);
		Boolean Status=false;
		selectWebElement(checkallotherapplication);
		selectWebElement(saveaccess.get(4));
		if(modifypopup.isDisplayed())
		{	
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtFieldWithoutClear(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
			Status=true;
		}
		return Status;
	}

	public boolean verifyotherapplicationunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetootherapplication);
		selectWebElement(pbuacancel.get(4));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifyotherapplicationunsuccessfullsavechanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetootherapplication);
		selectWebElement(saveaccess.get(4));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
	
	public void navigateToReportsTab() {
		selectWebElement(reportsTab);
	}

	public void clearReportAccess() {
		selectWebElement(accessAll.get(1));
		if(accessAll.get(1).isSelected()) {
			selectWebElement(accessAll.get(1));}
			selectWebElement(saveaccess.get(1));
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
	
	public void clearDashboardAccess() {
		selectWebElement(accessAll.get(2));
		if(accessAll.get(2).isSelected()) {
			selectWebElement(accessAll.get(2));}
			selectWebElement(saveaccess.get(2));
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
	
	public void clearOtherAppsAccess() {
		selectWebElement(accessAll.get(3));
		if(accessAll.get(3).isSelected()) {
			selectWebElement(accessAll.get(3));}
			selectWebElement(saveaccess.get(3));
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

	public void navigateToDashboardTab() {
		selectWebElement(dashboardTab);
		
	}
	
	public void navigateToOtherAppsTab() {
		selectWebElement(otherAppsTab);
		
	}

	public void ProvideDashboardAccess(String value) throws Exception {
		Thread.sleep(2000);
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		navigateToDashboardTab();
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		for(int i=0;i<dashboardTableRows.size();i++) {
			System.out.println(dashboardTableRows.get(i).getText());
			if (dashboardTableRows.get(i).getText().equals(value))
			{
				WebElement checkAccess=dashboardTableRows.get(i).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				break;
			}
			}
		selectWebElement(saveaccess.get(2));
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
	}
	
	public boolean isAddBtnDisplayed() {
    	return addNewUserManagementRecordBtn.isDisplayed() && addNewUserManagementRecordBtn.isEnabled();
    }
    
    public boolean isEditBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(editButton.isDisplayed() && editButton.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isDeleteBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(deleteBtn.isDisplayed() && deleteBtn.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }

	public void ProvideOtherAppsAccess(String value) throws Exception {
		Thread.sleep(2000);
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		navigateToOtherAppsTab();
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		for(int i=0;i<otherAppsTableRows.size();i++) {
			System.out.println(otherAppsTableRows.get(i).getText());
			if (otherAppsTableRows.get(i).getText().equals(value))
			{
				WebElement checkAccess=otherAppsTableRows.get(i).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				break;
			}
			}
		selectWebElement(saveaccess.get(3));
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
		
	}

	public boolean verifyAdminPagesCheckerunsuccessfullsavechanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetoAdminPagesChecker);
		selectWebElement(saveaccess.get(1));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifyAdminPagesCheckercheckbox(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoAdminPagesChecker);
		Boolean Status=false;
		selectWebElement(checkAllAdminPagesChecker);
		if(checkAllAdminPagesChecker.isSelected())
			{
				Status=true;
			}
		return Status;
	}
	

	public boolean verifyAdminPagesCheckercancelchanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoAdminPagesChecker);
		Thread.sleep(1000);
		selectWebElement(checkAllAdminPagesChecker);
		selectWebElement(pbuacancel.get(1));
		Thread.sleep(1000);
		if(checkAllAdminPagesChecker.isSelected())
			return false;
		else
		return true;
	}
	

	public boolean verifyAdminPagesCheckersavechanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoAdminPagesChecker);
		Thread.sleep(1000);
		waitForJqueryLoad(driver);
		Boolean Status=false;
		selectWebElement(checkAllAdminPagesChecker);
		selectWebElement(saveaccess.get(1));
		if(modifypopup.isDisplayed())
		{	
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtFieldWithoutClear(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
			Status=true;
		}
		return Status;
	}

	public boolean verifyAdminPagesCheckerunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchUserManagementRecord(userDetails.getUserId());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetoAdminPagesChecker);
		selectWebElement(pbuacancel.get(1));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
			return false;
	}
	
}
