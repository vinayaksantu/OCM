package com.tetherfi.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
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

	@FindBy(css = "#toast-container .toast-error .toast-message")
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
	
	@FindBy(css="a[aria-label='Go to the first page']")
    private WebElement firstPageIcon;
    
    @FindBy(css="a[aria-label='Go to the previous page']")
    private WebElement previousPageIcon;
    
    @FindBy(css=".k-pager-numbers .k-state-selected")
    private WebElement pageNumber;
    //@FindBy(css="a[aria-label='Go to the next page']")
    //private List<WebElement> nextPageIcon;
    
    @FindBy(css="a[aria-label='Go to the last page']")
    private WebElement lastPageIcon;
    
    @FindBy(css=".k-pager-sizes .k-icon")
    private WebElement pagerDropdown;
    
    @FindBy(css=".k-animation-container ul li")
    private List<WebElement> pageSizeListBox;
    
    @FindBy(css="th a[class='k-header-column-menu']")
    private List<WebElement> headersDropdown;
    
    @FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
    private List<WebElement> headersColumns;
    
    @FindBy(css="th a[class='k-link']")
    private List<WebElement> headersText;
    
    

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

	public void addnewCountry(AgentTeamMgmtDetails details) throws Exception {
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(details.getLevel());
		enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
		selectWebElement(addTeamNameSaveButton);
	}
		
	public void addnewDivision(AgentTeamMgmtDetails details) throws Exception {
	selectWebElement(addNewAgentTeamMgmtRcrdBtn);
	chooseLevel(details.getLevel()); 
	chooseCountry(details.getCountry());
	enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
	selectWebElement(addTeamNameSaveButton);
	}
	
	public void addnewDepartment(AgentTeamMgmtDetails details) throws Exception {
	selectWebElement(addNewAgentTeamMgmtRcrdBtn);
	chooseLevel(details.getLevel()); 
	chooseCountry(details.getCountry());
	chooseDivision(details.getDivision());
	enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
	selectWebElement(addTeamNameSaveButton);
	}
	
	
	public void addNewAgentTeamManagementRecord(String Level,String Country,String Division,String Department,String Teamname) throws Exception  {
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
			selectWebElement(departmentdropdown);
			selectDropdownFromVisibleText(departmentList,department);
	}

	private void chooseDivision(String division) {
			selectWebElement(divisiondropdown);
			selectDropdownFromVisibleText(divisionList,division);

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

	public void searchAgentTeamManagementRecord(String teamName) throws Exception {
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

	public void editAgentTeamManagementRecord(String oldteamname, String newteamname, String reason) throws Exception {
		searchAgentTeamManagementRecord(oldteamname);
		selectWebElement(editButton);
		selectWebElement(editTeamNameTextBox);
		enterValueToTxtField(editTeamNameTextBox,newteamname);
		enterValueToTxtFieldWithoutClear(editModifyReasonTextBox,reason);
		selectWebElement(editTeamNameSaveButton);
	}

	public Boolean editCancelRecord(String oldteamname, String newteamname, String reason) throws Exception {
		searchAgentTeamManagementRecord(oldteamname);
		selectWebElement(editButton);
		selectWebElement(editTeamNameTextBox);
		enterValueToTxtField(editTeamNameTextBox,newteamname);
		enterValueToTxtFieldWithoutClear(editModifyReasonTextBox,reason);
		selectWebElement(editCancel);
		waitForJqueryLoad(driver);
		if(tabledata.getText().equals(oldteamname))
		{return true;}
		else return false;
	}

	public void deleteAgentTeamManagementRecord(String oldteamname, String reason) throws Exception {
		searchAgentTeamManagementRecord(oldteamname);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		selectWebElement(deleteButton);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtFieldWithoutClear(deleteReasonTextBox,reason);
		selectWebElement(deleteYesBtn);
	}
	public Boolean verifyMessage(){
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

	public boolean addNewCancel(AgentTeamMgmtDetails details) throws Exception {
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

	public void addInvalidRecord2(AgentTeamMgmtDetails details) throws Exception{
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(details.getLevel()); 
		enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
		selectWebElement(addTeamNameSaveButton);
		selectWebElement(addcancel);
	}

	public void addInvalidRecord3(AgentTeamMgmtDetails details) throws Exception {
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(details.getLevel()); 
		chooseCountry(details.getCountry());
		enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
		selectWebElement(addTeamNameSaveButton);	
		selectWebElement(addcancel);
	}

	public void addInvalidRecord4(AgentTeamMgmtDetails details) throws Exception{
		selectWebElement(addNewAgentTeamMgmtRcrdBtn);
		chooseLevel(details.getLevel()); 
		chooseCountry(details.getCountry());
		chooseDivision(details.getDivision());
		enterValueToTxtField(addTeamNameTextBox,details.getTeamName());
		selectWebElement(addTeamNameSaveButton);
		selectWebElement(addcancel);
	}

	public void duplicateRecord(String Level,String Country,String Division,String Department,String Teamname) throws Exception {
		addNewAgentTeamManagementRecord(Level,Country,Division,Department,Teamname);
		try {
			selectWebElement(addcancel);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
	public boolean verifyinvalidsearch(AgentTeamMgmtDetails details) throws Exception {
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
	public boolean verifydeleteNo(String updateTeamName, String deleteReason) throws Exception {
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
	

	public boolean verifyDatabase(String query) {
		List<Map<String,String>> database=database(query);
		System.out.println(database);	
		selectWebElement(lastchangedon);
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
					col=cols.get(j).getText().substring(11);
					}
				else
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			map.remove("");
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
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			map.remove("");
			arr.add(map);
		}
		if(k!=pages) {
			nextPageIcon.click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}

	public boolean verifyExportToExcel(String filepath) {
		final File folder = new File(filepath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("Organizational Structure")) {
		        f.delete();
		    }
		}
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filepath, "Organizational Structure");
		return Status;
	}
		
	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
		List<Map<String,String>> UI=getdata(); 
		System.out.println(UI);
		System.out.println(maplist);
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
                    int totalRows=(gridContent.findElements(By.tagName("tr")).size());
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
        try {for (WebElement ele : headersDropdown) {
            scrollToElement(ele);
            status = false;
            if (!ele.isDisplayed()) {
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
            }
        }} catch (Exception e) {
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
    
    public boolean isAddBtnDisplayed() {
    	return addNewAgentTeamMgmtRcrdBtn.isDisplayed() && addNewAgentTeamMgmtRcrdBtn.isEnabled();
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
    		if(deleteButton.isDisplayed() && deleteButton.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }
    
	private void waitforElementIsClickable(WebElement ele){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(ele));	
	}
	
	public void SortByAscending() {
		selectWebElement(name);		
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void SortByDescending() {
		selectWebElement(name);
		selectWebElement(name);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean ExporttoExcelWithoutData(AgentTeamMgmtDetails agentTeamMgmtDetails) throws Exception {
		searchAgentTeamManagementRecord(agentTeamMgmtDetails.getTeamName());
		waitForJqueryLoad(driver);
		selectWebElement(exporttoexcel);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}
}
