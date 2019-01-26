package com.tetherfi.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.tetherfi.model.tmac.AgentTeamMgmtDetails;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AgentTeamManagementPage extends BasePage {
	public AgentTeamManagementPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
	private WebElement agentTeamManagement;

	@FindBy(xpath="//i[@class='fas fa-sitemap']")
	private WebElement aTMImg;

	@FindBy(xpath="//i[@class='fas fa-sync']")
	private WebElement clearsearch;

	@FindBy(xpath="//a[text()='Level Hierarchy']")
	private WebElement levelHierarchy;

	@FindBy(xpath="//a[text()='Name']")
	private WebElement name;

	@FindBy(xpath="//tbody/tr/td[4]")
	private WebElement tabledata;

	@FindBy(xpath="//a[text()='Team ID']")
	private WebElement teamId;

	@FindBy(xpath="//a[text()='Parent ID']")
	private WebElement parentId;

	@FindBy(xpath="//a[text()='Display Hierarchy']")
	private WebElement displayHierarchy;

	@FindBy(xpath="//a[text()='Last Changed By']")
	private WebElement lastchangedby;

	@FindBy(xpath="//a[text()='Last Changed On']")
	private WebElement lastchangedon;

	@FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
	private List<WebElement> tablerecord;

	@FindBy(xpath="//tbody[@role='rowgroup']/tr")
	private List<WebElement> tablerow;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen'")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;

	@FindBy(id = "create")
	private WebElement addNewAgentTeamMgmtRcrdBtn;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
	private WebElement addcancel;

	@FindBy(xpath="//a[@class='k-button k-bare k-button-icon k-window-action']")
	private WebElement addclose;

	@FindBy(className="toast-message")
	//@FindBy(css="#toast-container .toast-message")
	private WebElement successmsg;

	@FindBy(css = "#toast-container .toast-error")
	private List<WebElement> errorMsg;

	@FindBy(id = "Name")
	private WebElement addTeamNameTextBox;

	@FindBy(css = ".k-grid-update")
	private WebElement addTeamNameSaveButton;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
	private WebElement editCancel;

	@FindBy(css = ".k-grid-edit")
	private WebElement editButton;

	@FindBy(css="span[aria-owns='LevelHierarchy_listbox']")
	private WebElement levelHierarchydropdown;

	@FindBy(css="span[aria-owns='1_listbox']")
	private WebElement countrydropdown;

	@FindBy(css="span[aria-owns='2_listbox']")
	private WebElement divisiondropdown;

	@FindBy(css="span[aria-owns='3_listbox']")
	private WebElement departmentdropdown;

	@FindBy(css="ul[id='1_listbox'] li")
	private List<WebElement> countryList;

	@FindBy(xpath="//div/ul[@id='2_listbox']/li")
	private List<WebElement> divisionList;

	@FindBy(css="ul[id='3_listbox'] li")
	private List<WebElement> departmentList;

	@FindBy(css="ul[id='LevelHierarchy_listbox'] li")
	private List<WebElement> levelHierarchyListbox;

	@FindBy(id = "Name")
	private WebElement editTeamNameTextBox;

	@FindBy(id = "ModifyReason")
	private WebElement editModifyReasonTextBox;

	@FindBy(css = ".k-grid-update")
	private WebElement editTeamNameSaveButton;

	@FindBy(css = ".k-grid-cancel")
	private WebElement editTeamNameCancelButton;

	@FindBy(css = ".k-grid-CustomDelete")
	private WebElement deleteButton;

	@FindBy(id = "ModifyReason1")
	private WebElement deleteReasonTextBox;

	@FindBy(id = "yesButton")
	private WebElement deleteYesBtn;

	@FindBy(id = "noButton")
	private WebElement deleteNoBtn;

	@FindBy(css = ".fa-search")
	private WebElement searchBtn;

	@FindBy(id="tGrid")
	private WebElement auditGridContent;

	@FindBy(css = ".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchCol;

	@FindBy(css="ul[id='1001sColumnName_listbox'] li")
	private List<WebElement> columnNameList;

	@FindBy(css = "#1001sFormDropDown .k-i-arrow-60-down")
	private List<WebElement> selectSearchCriteria;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchCriteriaDropDwn;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	@FindBy(css = "#1001sAddButton .k-i-add")
	private WebElement searchAddCriteriaBtn;

	@FindBy(css = "#1001sCloseButton .k-i-close")
	private WebElement searchRemoveFilterBtn;

	@FindBy(css = ".modal-footer .k-button")
	private WebElement searchCloseBtn;

	@FindBy(css = ".modal-footer .button-danger-theme")
	private WebElement searchClearAllBtn;

	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;

	@FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']")
	private WebElement gridContent;

	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;

	@FindBy(xpath="//button[text()='Close']")
	private WebElement closesearch;

	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

	@FindBy(xpath="//input[@data-field='LevelHierarchy']")
	private WebElement LevelHeirarchycheckbox;

	@FindBy(xpath="//input[@data-field='DisplayHierarchy']")
	private WebElement DisplayHeirarchycheckbox;

	@FindBy(xpath="//input[@data-field='Name']")
	private WebElement Namecheckbox;

	@FindBy(xpath="//input[@data-field='LastChangedBy']")
	private WebElement LastChangedBycheckbox;

	@FindBy(xpath="//input[@data-field='LastChangedOn']")
	private WebElement LastChangedOncheckbox;

	@FindBy(xpath="//input[@data-field='TeamID']")
	private WebElement TeamIdcheckbox;

	@FindBy(xpath="//input[@data-field='ParentID']")
	private WebElement ParentIdcheckbox;

	@FindBy(xpath="//th[3]/a/span[@class='k-icon k-i-more-vertical']")
	private WebElement headerColumn;

	@FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
	private WebElement coloumnarrow;

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
	
	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
	
	@FindBy(xpath="//*[@id='grid']/div[5]/a[3]/span")
	private WebElement nextPageIcon;
	
	@FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize;
	
	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
	private WebElement groupbymessage;
	
	@FindBy(xpath="//p[@class='k-reset']")
    private WebElement groupby;


	public boolean isAgentTeamManagementPageDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return agentTeamManagement.isEnabled();
	}
	public boolean verifylogo() 
	{
		if(isElementExist(aTMImg)) 
		{return true;}
		else return false;
	}

	public void addnewCountry(AgentTeamMgmtDetails details) {
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(details.getLevel());
		enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
		selectWebElement(addTeamNameSaveButton);
	}
		
	public void addnewDivision(AgentTeamMgmtDetails details) {
	selectWebElement(addNewAgentTeamMgmtRcrdBtn);
	chooseLevel(details.getLevel()); 
	chooseCountry(details.getCountry());
	enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
	selectWebElement(addTeamNameSaveButton);
	}
	
	public void addnewDepartment(AgentTeamMgmtDetails details) {
	selectWebElement(addNewAgentTeamMgmtRcrdBtn);
	chooseLevel(details.getLevel()); 
	chooseCountry(details.getCountry());
	chooseDivision(details.getDivision());
	enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
	selectWebElement(addTeamNameSaveButton);
	}
	
	
	public void addNewAgentTeamManagementRecord(String Level,String Country,String Division,String Department,String Teamname)  {
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(Level); 
		chooseCountry(Country);
		chooseDivision(Division);
		chooseDepartment(Department);
		enterValueToTxtField(addTeamNameTextBox,Teamname);
		selectWebElement(addTeamNameSaveButton);
	}

	public void chooseLevel(String level){
		try {
			Thread.sleep(1000);
			selectWebElement(levelHierarchydropdown);
			Thread.sleep(2000);
			selectDropdownFromVisibleText(levelHierarchyListbox,level);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void chooseDepartment(String department) {
		try {
			Thread.sleep(1000);
			selectWebElement(departmentdropdown);
			Thread.sleep(1000);
			selectDropdownFromVisibleText(departmentList,department);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void chooseDivision(String division) {
		try {
			Thread.sleep(1000);
			selectWebElement(divisiondropdown);
			Thread.sleep(1000);
			selectDropdownFromVisibleText(divisionList,division);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	}

	private void chooseCountry(String country) {
		try {
			Thread.sleep(1000);
			selectWebElement(countrydropdown);
			Thread.sleep(1000);
			selectDropdownFromVisibleText(countryList,country);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void searchAgentTeamManagementRecord(String teamName) {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Name");
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		enterValueToTxtField(searchTextBox,teamName);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
	}

	public void editAgentTeamManagementRecord(String oldteamname, String newteamname, String reason) {
		searchAgentTeamManagementRecord(oldteamname);
		selectWebElement(editButton);
		selectWebElement(editTeamNameTextBox);
		enterValueToTxtField(editTeamNameTextBox,newteamname);
		enterValueToTxtField(editModifyReasonTextBox,reason);
		selectWebElement(editTeamNameSaveButton);
	}

	public Boolean editCancelRecord(String oldteamname, String newteamname, String reason) {
		searchAgentTeamManagementRecord(oldteamname);
		selectWebElement(editButton);
		selectWebElement(editTeamNameTextBox);
		enterValueToTxtField(editTeamNameTextBox,newteamname);
		enterValueToTxtField(editModifyReasonTextBox,reason);
		selectWebElement(editCancel);
		waitForJqueryLoad(driver);
		if(tabledata.getText().equals(oldteamname))
		{return true;}
		else return false;
	}

	public void deleteAgentTeamManagementRecord(String oldteamname, String reason) {
		searchAgentTeamManagementRecord(oldteamname);
		selectWebElement(deleteButton);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtField(deleteReasonTextBox,reason);
		selectWebElement(deleteYesBtn);
	}
	public Boolean verifyMessage(){
		if (errorMsg.size()>0)
		return false;
		waitUntilWebElementIsVisible(successmsg);
    	if(successmsg.getText().contains("Successfully"))
		return true;
		else
		{return false;}
	}
	
	public boolean errorMessage() {
		if(errorMsg.size()>0)
			return true;
		else
			return false;
	}
	public String duplicateMessage() {
		if(errorMsg.size()>0)
			return errorMsg.get(0).getText();
		else 
			return successmsg.getText();
			
	}

	public boolean verifyLevelHierarchybutton() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=LevelHeirarchycheckbox.isSelected();
		if(Status.equals(levelHierarchy.isDisplayed()))
		{return true;}
		else
			return false;}

	public boolean verifyNamebutton() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=Namecheckbox.isSelected();
		if(Status.equals(name.isDisplayed()))
		{return true;}
		else
			return false;}

	public boolean verifyDisplayHierarchybutton() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=DisplayHeirarchycheckbox.isSelected();
		if(Status.equals(displayHierarchy.isDisplayed()))
			return true;
		else
			return false;}

	public boolean verifylastchangedbybutton() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=LastChangedBycheckbox.isSelected();
		if(Status.equals(lastchangedby.isDisplayed()))
			return true;
		else
			return false;}

	public boolean verifylastchangedonbutton() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=LastChangedOncheckbox.isSelected();
		if(Status.equals(lastchangedon.isDisplayed()))
			return true;
		else
			return false;
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

	public boolean addNewCancel(AgentTeamMgmtDetails details) {
		String actualitems=items.getText();
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(details.getLevel()); 
		chooseCountry(details.getCountry());
		chooseDivision(details.getDivision());
		chooseDepartment(details.getDepartment());
		enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
		selectWebElement(addcancel);
		if(actualitems.equals(items.getText()))
			return true;
		else 
			return false;}

	public void addInvalidRecord(AgentTeamMgmtDetails details){
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(details.getLevel()); 
		chooseCountry(details.getCountry());
		chooseDivision(details.getDivision());
		chooseDepartment(details.getDepartment());
		selectWebElement(addTeamNameSaveButton);
		selectWebElement(addcancel);

	}

	public void addInvalidRecord2(AgentTeamMgmtDetails details){
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(details.getLevel()); 
		enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
		selectWebElement(addTeamNameSaveButton);
		selectWebElement(addcancel);
	}

	public void addInvalidRecord3(AgentTeamMgmtDetails details) {
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(details.getLevel()); 
		chooseCountry(details.getCountry());
		enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
		selectWebElement(addTeamNameSaveButton);	
		selectWebElement(addcancel);
	}

	public void addInvalidRecord4(AgentTeamMgmtDetails details){
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(details.getLevel()); 
		chooseCountry(details.getCountry());
		chooseDivision(details.getDivision());
		enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
		selectWebElement(addTeamNameSaveButton);
		selectWebElement(addcancel);
	}

	public void duplicateRecord(String Level,String Country,String Division,String Department,String Teamname) {
		addNewAgentTeamManagementRecord(Level,Country,Division,Department,Teamname);
		selectWebElement(addcancel);

	}

	public boolean clearAll(AgentTeamMgmtDetails details) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Name");
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		enterValueToTxtField(searchTextBox,details.getTeamName());
		selectWebElement(clearall);
		Thread.sleep(1000);
		if(searchTextBox.isEnabled())
			return true;
		else
			return false;
	}
	public boolean verifyclose() {
		selectWebElement(closesearch);
		if(gridContent.isDisplayed())
			return true;
		else
			return false;	
	}
	public boolean verifyinvalidsearch(AgentTeamMgmtDetails details) {
		searchAgentTeamManagementRecord(details.getTeamName());
		if(norecords.isDisplayed())
			return true; 
		else
			return false;
	}
	public boolean verifyclearsearch() {
		selectWebElement(clearsearch);
		if(gridContent.isDisplayed())
			return true;
		else
			return false;
	}
	public boolean verifydeleteNo(String updateTeamName, String deleteReason) {
		searchAgentTeamManagementRecord(updateTeamName);
		String actualitems=items.getText();
		selectWebElement(deleteButton);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtField(deleteReasonTextBox,deleteReason);
		selectWebElement(deleteNoBtn);
		if(actualitems.equals(items.getText()))
			return true;
		else
			return false;
	}
	public boolean verifyTeamIdenable() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		if(!TeamIdcheckbox.isSelected())
			selectCheckbox(TeamIdcheckbox);
		if(teamId.isDisplayed())
			return true;
		else
			return false;
	}
	public boolean verifyTeamIddisable() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		if(TeamIdcheckbox.isSelected())
			selectCheckbox(TeamIdcheckbox);
		if(teamId.isDisplayed())
			return false;
		else
			return true;
	}
	public boolean verifyParentIdenable() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		if(!ParentIdcheckbox.isSelected())
			selectCheckbox(ParentIdcheckbox);
		if(parentId.isDisplayed())
			return true;
		else
			return false;
	}
	public boolean verifyParentIddisable() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		if(ParentIdcheckbox.isSelected())
			selectCheckbox(ParentIdcheckbox);
		if(parentId.isDisplayed())
			return false;
		else
			return true;
	}

	public boolean verifyDatabase(String query) {
		List<Map<String,String>> database=database(query);
		System.out.println(database);	
		selectWebElement(levelHierarchy);
		List<Map<String,String>> UI=gettable(); 
		System.out.println(UI);
		if(UI.equals(database))
			return true;
		else
			return false;
	}

	private List<Map<String,String>> gettable(){
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
			while(map.values().remove(""));
			arr.add(map);
		}
		if(k!=pages) {
		nextPageIcon.click();
		waitForJqueryLoad(driver);}
		}
			return arr;
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
			while(map.values().remove(""));
			arr.add(map);
		}
		if(k!=pages) {
			nextPageIcon.click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}

	public boolean verifyExportToExcel(String filepath) {
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		Boolean Status=verifyExportPageFileDownload(filepath, "Agent Team Management");
		return Status;
	}
		
	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
		List<Map<String,String>> UI=getdata(); 
		if(UI.equals(maplist))
		return true;
		else
		return false;
	}
	public boolean groupby() {
		DragandDrop(levelHierarchy,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbymessage.getText()))
		{return true;}
		else
			return false;		
	}


}