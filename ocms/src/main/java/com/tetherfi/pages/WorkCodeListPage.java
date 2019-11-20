package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tetherfi.model.tmac.WorkCodeListDetails;

public class WorkCodeListPage extends BasePage{
	public WorkCodeListPage(WebDriver driver){
		super(driver);
	}
	@FindBy(css = ".ibox-title h5")
	private WebElement workcodelist;
	
	@FindBy(xpath="//i[@class='far fa-clipboard-list']")
	private WebElement wclImg;
	
	@FindBy(xpath="//tr/th[@data-field='WorkLevel']/a/span")
	private WebElement headerColumn;
	
	@FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
    private WebElement coloumnarrow;
	
	@FindBy(xpath="//input[@data-field='WorkLevel']")
	private WebElement worklevelcheckbox;
	
	@FindBy(xpath="//a[text()='Work Level']")
	private WebElement worklevel;
	
	@FindBy(xpath="//input[@data-field='Name']")
	private WebElement namecheckbox;
	
	@FindBy(xpath="//a[text()='Name']")
	private WebElement name;
	
	@FindBy(xpath="//input[@data-field='ParentID']")
	private WebElement parentcheckbox;
	
	@FindBy(xpath="//a[text()='Parent']")
	private WebElement parent;
	
	@FindBy(xpath="//input[@data-field='LastChangedBy']")
	private WebElement lastchangedbycheckbox;
	
	@FindBy(xpath="//a[text()='Last Changed By']")
	private WebElement lastchangedby;
	
	@FindBy(xpath="//input[@data-field='LastChangedOn']")
	private WebElement lastchangedoncheckbox;
	
	@FindBy(xpath="//a[text()='Last Changed On']")
	private WebElement lastchangedon;
	
	@FindBy(xpath="//input[@data-field='TeamID']")
	private WebElement teamidcheckbox;
	
	@FindBy(xpath="//a[text()='Team ID']")
	private WebElement teamid;
	
	@FindBy(xpath="//input[@data-field='WorkCode']")
	private WebElement workcodecheckbox;
	
	@FindBy(xpath="//a[text()='Work Code']")
	private WebElement workcode;
	
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
	
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
	
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(id="navbarheader")
	private WebElement header;
	
	@FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
    private List<WebElement> tablerecord;
   
    @FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
    
    @FindBy(id="create")
    private WebElement addnewrecordbtn;
    
    @FindBy(xpath="//span[@aria-owns='WorkLevel_listbox']")
    private WebElement workleveldropdown;
    
    @FindBy(css="ul[id='WorkLevel_listbox'] li")
    private List<WebElement> worklevellistbox;
    
    @FindBy(id="Name")
    private WebElement addnametextbox;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
    private WebElement savebtn;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
    private WebElement cancelbtn;
    
    @FindBy(css="#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;
    
    @FindBy(className="toast-message")
    private WebElement successmsg;
    
    @FindBy(xpath="//span[@class='k-widget k-dropdowntree k-dropdowntree-clearable']")
    private WebElement TeamNameDropDown;
    
    @FindBy(css=".k-treeview-lines li div")
    private List<WebElement> teamList;
    
    @FindBy(css="span[aria-owns='1_listbox']")
    private WebElement Workgroupdropdown;
    
    @FindBy(css="ul[id='1_listbox'] li")
    private List<WebElement> workgrouplistbox;
    
    @FindBy(css = ".fa-search")
    private WebElement searchBtn;

    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchCol;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;

    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;

    @FindBy(css = "#1001sAddButton .k-i-add")
    private WebElement searchAddCriteriaBtn;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-edit']")
    private WebElement editbtn;
    
    @FindBy(id="ModifyReason")
    private WebElement modifyreasontextbox;
    
    @FindBy(xpath="//tbody/tr/td[4]")
    private WebElement rowdata;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;

    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-CustomDelete']")
    private WebElement deletebtn;
    
	@FindBy(id="ModifyReason1")
	private WebElement deletereasontextbox;
	
	@FindBy(id="noButton")
	private WebElement nobtn;
	
	@FindBy(id="yesButton")
	private WebElement yesbtn;

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
	
	@FindBy(id="tGrid")
	private WebElement auditGridContent;
	
	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
	private WebElement groupbyworkgroup;
    
    @FindBy(xpath="//span[@class='k-input']")
    private WebElement pagerSize;
    
    @FindBy(xpath="//p[@class='k-reset']")
    private WebElement groupby;
    
    @FindBy(css="a[aria-label='Go to the first page']")
    private WebElement firstPageIcon;
    
    @FindBy(css="a[aria-label='Go to the previous page']")
    private WebElement previousPageIcon;
    
    @FindBy(css=".k-pager-numbers .k-state-selected")
    private WebElement pageNumber;
    
    @FindBy(css="a[aria-label='Go to the next page']")
    private WebElement nextPageIcon;
    
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


	
	public boolean isWorkCodeListPageDisplayed() {
		if(workcodelist.getText().equals("Workcode List"))
			return true;
		else
			return false;
	}

	public boolean verifylogo() {
		if(isElementExist(wclImg))
			return true;
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


	public boolean verifygridcontent() {
		int size=tablerecord.size();
		System.out.println(+size);
		String item[]=(items.getText()).split("\\s+");
		int itemno=Integer.parseInt(item[2]);
		System.out.println(itemno);
		if(itemno==size)
		return true;
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
		{
			return true;
		}
		else 
			return false; 
	}

	public void addNewWorkGroup(WorkCodeListDetails details) throws Exception {
		selectWebElement(addnewrecordbtn);
		chooseWorkLevel(details);
		enterValueToTxtField(addnametextbox,details.getName());
		selectWebElement(savebtn);
	}
	
	public boolean addnewWorkGroupCancel(WorkCodeListDetails details) throws Exception {
		String actualitems=items.getText();
		selectWebElement(addnewrecordbtn);
		chooseWorkLevel(details);
		enterValueToTxtField(addnametextbox,details.getName());
		selectWebElement(cancelbtn);
		if(actualitems.equals(items.getText()))
			return true;
		else
			return false;
	}

	private void chooseWorkLevel(WorkCodeListDetails details) {
		try {
			Thread.sleep(1000);
			selectWebElement(workleveldropdown);
			Thread.sleep(2000);
			selectDropdownFromVisibleText(worklevellistbox,details.getWorkLevel());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public boolean verifymessage() {
			waitUntilWebElementIsVisible(successmsg);
	    	if(successmsg.getText().contains("Successfully"))
			return true;
			else
			{return false;}
	}
	public boolean errormessage() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0)
			return false;
		else 
			return true;
	}
	public void addNewWorkCode(WorkCodeListDetails details) throws Exception {
		selectWebElement(addnewrecordbtn);
		chooseWorkLevel(details);
		selectWebElement(TeamNameDropDown);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ChooseTeamHeirarchy(details.getCountry());
        ChooseTeamHeirarchy(details.getDivision());
        ChooseTeamHeirarchy(details.getDepartment());
        ChooseTeamHeirarchy(details.getTeamName());
        chooseWorkgroup(details);
		enterValueToTxtField(addnametextbox,details.getName());
		selectWebElement(savebtn);
	}
	public Boolean addNewWorkCodeCancel(WorkCodeListDetails details) throws Exception {
		String actualitems=items.getText();
		selectWebElement(addnewrecordbtn);
		chooseWorkLevel(details);
        chooseWorkgroup(details);
		enterValueToTxtField(addnametextbox,details.getName());
		selectWebElement(cancelbtn);
		if(actualitems.equals(items.getText()))
			return true;
		else
			return false;
	}
	
	

	private void chooseWorkgroup(WorkCodeListDetails details) {
		try {
			Thread.sleep(1000);
			selectWebElement(Workgroupdropdown);
			Thread.sleep(2000);
			selectDropdownFromVisibleText(workgrouplistbox,details.getWorkGroup());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void ChooseTeamHeirarchy(String team) {
		String[] hrcy=team.split(">");
        for(int i=0;i<hrcy.length;i++){
            for(WebElement e: teamList){
                if(e.getText().equals(hrcy[i]))
                    if(e.findElements(By.className("k-icon")).size()>0)
                    {selectWebElement(e.findElement(By.className("k-icon")));break;}
                    else
                    {selectWebElement(e.findElement(By.className("k-in")));break;}   }}
	}

	public void addRecordWithoutWorklevel(WorkCodeListDetails details) {
		selectWebElement(addnewrecordbtn);
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);
	}

	public void addRecordWithoutWorkGroup(WorkCodeListDetails details) throws Exception {
		selectWebElement(addnewrecordbtn);
		chooseWorkLevel(details);
		enterValueToTxtField(addnametextbox,details.getName());
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);
	}

	public void addRecordWithoutName(WorkCodeListDetails details) {
		selectWebElement(addnewrecordbtn);
		chooseWorkLevel(details);
		chooseWorkgroup(details);
		selectWebElement(savebtn);
		selectWebElement(cancelbtn);
	}

	public void duplicateRecord(WorkCodeListDetails details) throws Exception {
		selectWebElement(addnewrecordbtn);
		chooseWorkLevel(details);
		selectWebElement(TeamNameDropDown);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ChooseTeamHeirarchy(details.getCountry());
        ChooseTeamHeirarchy(details.getDivision());
        ChooseTeamHeirarchy(details.getDepartment());
        ChooseTeamHeirarchy(details.getTeamName());
        chooseWorkgroup(details);
		enterValueToTxtField(addnametextbox,details.getName());
		selectWebElement(savebtn);
		try {
			selectWebElement(cancelbtn);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void searchWorkcodeList(String Name) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,Name);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
	}
	
	public boolean verifySearchIsNotEqualTo(String name) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Name", name);
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
        enterValueToTxtField(searchTextBox,name);		
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
	public boolean verifySearchContains(String name) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
        enterValueToTxtField(searchTextBox,name);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Name").toUpperCase().contains(name.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	public boolean verifySearchDoesNotContains(String name) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
        enterValueToTxtField(searchTextBox,name);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(!map1.get("Name").toLowerCase().contains(name.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	
	public boolean verifySearchStartsWith(String name) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
        enterValueToTxtField(searchTextBox,name);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Name").toLowerCase().startsWith(name.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	
	public boolean verifySearchEndsWith(String name) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
        enterValueToTxtField(searchTextBox,name);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Name").toUpperCase().endsWith(name.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}

	public boolean editWorkcodecancelled(WorkCodeListDetails workcodeListDetails) throws Exception {
		searchWorkcodeList(workcodeListDetails.getName());
		selectWebElement(editbtn);
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		enterValueToTxtField(modifyreasontextbox,workcodeListDetails.getModifyReason());
		selectWebElement(cancelbtn);
		if(rowdata.getText().equals(workcodeListDetails.getName()))
			return true;
		else
		return false;
	}

	public void editworkcodeListRecord(WorkCodeListDetails workcodeListDetails) throws Exception {
		searchWorkcodeList(workcodeListDetails.getName());
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		selectWebElement(editbtn);
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		enterValueToTxtField(addnametextbox,workcodeListDetails.getUpdatedName());
		enterValueToTxtFieldWithoutClear(modifyreasontextbox,workcodeListDetails.getModifyReason());
		selectWebElement(savebtn);
	}

	public boolean clearAll(WorkCodeListDetails details) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getName());
        selectWebElement(clearall);
        if(searchTextBox.isEnabled())
        	return true;
        else
		return false;
	}

	public boolean verifyclose() {
		selectWebElement(searchClose);
		if(gridContent.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean verifyinvalidsearch(WorkCodeListDetails workcodeListDetails) {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        selectWebElement(searchSearchBtn);
        selectWebElement(searchClose);
        if(errorMsg.size()>0)
        	return true;
        else
		return false;
	}
	
	public boolean verifyinvalidsearchwithwrongdata(WorkCodeListDetails workcodeListDetails) throws Exception {
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		searchWorkcodeList(workcodeListDetails.getName());
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

	public boolean verifydeleteNo(WorkCodeListDetails workcodeListDetails) throws Exception {
		searchWorkcodeList(workcodeListDetails.getUpdatedName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(deletebtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtFieldWithoutClear(deletereasontextbox,workcodeListDetails.getDeleteReason());
		selectWebElement(nobtn);
		if(rowdata.getText().equals(workcodeListDetails.getUpdatedName()))
				return true;
		else
		return false;
	}

	public void deleteWorkCodeListRecord(WorkCodeListDetails workcodeListDetails) throws Exception {
		searchWorkcodeList(workcodeListDetails.getUpdatedName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(deletebtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtFieldWithoutClear(deletereasontextbox,workcodeListDetails.getDeleteReason());
		selectWebElement(yesbtn);
	}

	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("Workcode List")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Workcode List");
		return Status;
	}

	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
		List<Map<String,String>> UI=getdata(); 
		System.out.println(maplist);
		System.out.println(UI);
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

	public boolean verifyDatabase(String  query) {
		List<Map<String,String>> database=database(query);
		System.out.println(database);
		List<Map<String,String>> UI=gettable(); 
		System.out.println(UI);
		if(UI.equals(database))
			return true;
		else
			return false;
	}
	
	public List<String> captureDatabase(String query) {
		List<Map<String,String>> database=database(query);
		List<String> values=new ArrayList<String>();
		for (int i=0;i<database.size();i++)
		{
			String lst=database.get(i).remove("WorkCodeName");
			values.add(lst);
		}
		return values;
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
					col=cols.get(j).getText().substring(11);
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
		DragandDrop(worklevel,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyworkgroup.getText()))
		{return true;}
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

		public boolean ExporttoExcelWithoutData(WorkCodeListDetails workcodeListDetails) throws Exception {
			searchWorkcodeList(workcodeListDetails.getName());
			waitForJqueryLoad(driver);
			selectWebElement(exporttoexcel);
			if(errorMsg.get(0).getText().equals("There is no record to export"))
				return true;
			else
			return false;
		}

		public List<Map<String, String>> capturelist() {
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
					if(headers.get(j).getText().equals("Name")){
						col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);}
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
			}
			System.out.println(arr);
				return arr;
		}
		public boolean isAddBtnDisplayed() {
    	return addnewrecordbtn.isDisplayed() && addnewrecordbtn.isEnabled();
    }
    
    public boolean isEditBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(editbtn.isDisplayed() && editbtn.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isDeleteBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(deletebtn.isDisplayed() && deletebtn.isEnabled())
    			status = true;
    	}catch(Exception e) {
    		status = false;
    	}
		return status;
    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }

	public void deleteWorkGroupRecord(WorkCodeListDetails workcodeListDetails) throws Exception {
		searchWorkcodeList(workcodeListDetails.getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(deletebtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtFieldWithoutClear(deletereasontextbox,workcodeListDetails.getDeleteReason());
		selectWebElement(yesbtn);		
	}
}

