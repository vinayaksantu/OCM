package com.tetherfi.pages;


import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import com.tetherfi.model.tmac.WorkCodeListDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TmacBroadCastMsgPage extends BasePage {

    public TmacBroadCastMsgPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ibox-title h5")
    private WebElement tmacBroadcastMsg;

    @FindBy(id="create")
    private WebElement addnewTmacBroadcastMessage;
   
    @FindBy(xpath="//i[@class='fas fa-broadcast-tower']")
    private WebElement tbmImg;
    
    @FindBy(xpath="//table//tbody/tr/td[4]")
    private WebElement tablerow;
    
    @FindBy(xpath="//span[@class='k-icon k-i-more-vertical']")
    private WebElement headerColumn;
    
    @FindBy(xpath="//input[@data-field='TeamName']")
    private WebElement teamnamecheckbox;
    
    @FindBy(xpath="//input[@data-field='Message']")
    private WebElement messagecheckbox;
    
    @FindBy(xpath="//input[@data-field='Status']")
    private WebElement statuscheckbox;
    
    @FindBy(xpath="//input[@data-field='LastChangedBy']")
    private WebElement LastChangedBycheckbox;
    
    @FindBy(xpath="//input[@data-field='LastChangedOn']")
    private WebElement LastChangedOncheckbox;
   
    @FindBy(xpath="//a[text()='Message']")
    private WebElement message;
   
    @FindBy(xpath="//a[text()='Org. Unit']")
    private List<WebElement> teamName;
   
    @FindBy(xpath="//a[text()='Status']")
    private WebElement status;
    
    @FindBy(xpath="//a[text()='Last Changed By']")
    private WebElement lastchangedby;
    
    @FindBy(xpath="//a[text()='Last Changed On']")
    private WebElement lastchangedon;
   
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
    private WebElement addcancel;
   
    @FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
    private List<WebElement> tablerecord;
   
    @FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
   
    @FindBy(id="navbarheader")
	private WebElement header;
    
    @FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
    private WebElement coloumnarrow;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
	
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
	 
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
	 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;
	 
	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;
 
	@FindBy(xpath="//i[@class='fas fa-sync']")
	private WebElement clearsearch;

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

    @FindBy(css="span[aria-owns='Status_listbox']")
    private WebElement statusDropdown;

    @FindBy(css="ul[id='Status_listbox'] li")
    private List<WebElement> statusListBox;

    @FindBy(css = "#1001sAddButton .k-i-add")
    private WebElement searchAddCriteriaBtn;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css = "#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;

    @FindBy(xpath = "//a[@class='k-button k-button-icontext k-grid-edit']")
    private WebElement editButton;

    @FindBy(id = "ModifyReason")
    private WebElement ModifyReasonTextBox;

    @FindBy(css=".k-grid-update")
    private WebElement SaveButton;

    @FindBy(id="Message")
    private WebElement messageTextbox;

    @FindBy(css="span[aria-owns='TeamName_listbox']")
    private WebElement teamDropdown;

    @FindBy(css="ul[id='TeamName_listbox'] li")
    private List<WebElement> teamListBox;

    @FindBy(css="span[class='k-widget k-dropdowntree k-dropdowntree-clearable']")
    private WebElement TeamNameDropDown;

    @FindBy(css=".k-treeview-lines li div")
    private List<WebElement> teamList;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
    
    @FindBy(id="tGrid")
	private WebElement auditGridContent;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[5]")
	private WebElement groupbymessage;
    
    @FindBy(xpath="//span[@class='k-input']")
    private WebElement pagerSize;
    
    @FindBy(xpath="//*[@id='grid']/div[5]/a[3]/span")
    private WebElement nextPageIcon;
    
    @FindBy(xpath="//p[@class='k-reset']")
    private WebElement groupby;
    
    @FindBy(css="a[aria-label='Go to the first page']")
    private WebElement firstPageIcon;
    
    @FindBy(css="a[aria-label='Go to the previous page']")
    private WebElement previousPageIcon;
    
    @FindBy(css=".k-pager-numbers .k-state-selected")
    private WebElement pageNumber;
    
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

    public boolean isTmacBroadcastMsgPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return tmacBroadcastMsg.isEnabled();
    }

    public void searchTmacBroadcastMsg(String message) throws Exception {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Message");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
        enterValueToTxtField(searchTextBox,message);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    
    public boolean verifySearchIsNotEqualTo(String message) throws Exception {
		Boolean Status=false;
		Map<String, String> map=new HashMap<String,String>() ;
		map.put("Message", message);
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Message");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
        enterValueToTxtField(searchTextBox,message);		
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
    
    public boolean verifySearchContains(String message) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Message");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
        enterValueToTxtField(searchTextBox,message);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Message").toUpperCase().contains(message.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	public boolean verifySearchDoesNotContains(String message) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Message");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
        enterValueToTxtField(searchTextBox,message);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(!map1.get("Message").toLowerCase().contains(message.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	
	public boolean verifySearchStartsWith(String message) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Message");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
        enterValueToTxtField(searchTextBox,message);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Message").toLowerCase().startsWith(message.toLowerCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
	
	public boolean verifySearchEndsWith(String message) throws Exception {
		Boolean Status=false;
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Message");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
        enterValueToTxtField(searchTextBox,message);		
        selectWebElement(searchSearchBtn);
        waitUntilWebElementIsVisible(gridContent);
        List<Map<String,String>> UI=gettable(); 
        for (Map<String,String> map1: UI)
        {   	
			if(map1.get("Message").toUpperCase().endsWith(message.toUpperCase()))
        	Status= true;
        	else 
        		Status= false;
	}
        return Status;
	}
    public void addTmacBroadcastMsg(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
        selectWebElement(addnewTmacBroadcastMessage);
        selectWebElement(TeamNameDropDown);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getCountry());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getDivision());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getDepartment());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getTeamName());
        selectWebElement(messageTextbox);
        enterValueToTxtField(messageTextbox,tmacBroadCastMsgDetails.getMessage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,tmacBroadCastMsgDetails.getStatus());
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(SaveButton);
    }
    public void addTmacBroadcastMsgDivision(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
    	selectWebElement(addnewTmacBroadcastMessage);
    	selectWebElement(TeamNameDropDown);
    	try {
    		Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getCountry());
    	ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getDivision());
    	selectWebElement(messageTextbox);
        enterValueToTxtField(messageTextbox,tmacBroadCastMsgDetails.getMessage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,tmacBroadCastMsgDetails.getStatus());
        selectWebElement(SaveButton);
    }
    
    public void editTmacBroadcastMsg(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
        searchTmacBroadcastMsg(tmacBroadCastMsgDetails.getMessage());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        selectWebElement(messageTextbox);
        enterValueToTxtField(messageTextbox,tmacBroadCastMsgDetails.getUpdatedMessage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,tmacBroadCastMsgDetails.getUpdatedStatus());
        enterValueToTxtFieldWithoutClear(ModifyReasonTextBox,tmacBroadCastMsgDetails.getModifyReason());
        selectWebElement(SaveButton);
   }
    
    public boolean verifyNewRecordCreated(){
		if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully")){
		return true;}
		if(errorMsg.size()>0)
		{return false;}
		else 
		{return false;}
	}


	public boolean verifyRecordDeleted(){
		if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully"))
		{return true;}
		if(errorMsg.size()>0)
		{return false;}
		else 
		{return false;}
	}

	public boolean verifyRecordUpdated(){
		if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
		{return true;}
		if(errorMsg.size()>0){return false;}
		else{return false;}
	}
    
    public boolean verifyErrorMessage() {
		waitUntilWebElementListIsVisible(errorMsg);										
    	if(errorMsg.size()>0) {
    		return true;}
    		else return false;
    	}
    
    private void ChooseTeamHeirarchy(String team){
        String[] hrcy=team.split(">");
        for(int i=0;i<hrcy.length;i++){
            for(WebElement e: teamList){
                if(e.getText().equals(hrcy[i]))
                    if(e.findElements(By.className("k-icon")).size()>0)
                    {selectWebElement(e.findElement(By.className("k-icon")));break;}
                    else
                    {selectWebElement(e.findElement(By.className("k-in")));break;}   }}
    }

	public boolean verifylogo() {
		if(isElementExist(tbmImg))
			return true;
		else
		return false;
	}

	public boolean verifyMessagelabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=messagecheckbox.isSelected();
			if(Status.equals(message.isDisplayed()))
			return true;
			else
			return false;
	}
	public boolean verifyTeamNamelabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=teamnamecheckbox.isSelected();
		if(Status.equals(teamName.get(1).isDisplayed()))
			return true;
		else
		return false;
	}

	public boolean verifyStatuslabel() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		Boolean Status=statuscheckbox.isSelected();
		if(Status.equals(status.isDisplayed()))
			return true;
		else
		return false;
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
	
	public boolean maximizewindow()
	 {
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
	public boolean addNewCancel(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
		String actualitems=items.getText();
		selectWebElement(addnewTmacBroadcastMessage);
        //waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //selectWebElement(teamDropdown);
        //selectDropdownFromVisibleText(teamListBox,tmacBroadCastMsgDetails.getTeamName());
        selectWebElement(TeamNameDropDown);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getCountry());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getDivision());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getDepartment());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getTeamName());
        selectWebElement(messageTextbox);
        enterValueToTxtField(messageTextbox,tmacBroadCastMsgDetails.getMessage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,tmacBroadCastMsgDetails.getStatus());
        selectWebElement(addcancel);
        if(actualitems.equals(items.getText()))
        	return true;
        else 
        	return false;
}

	public boolean clearAll(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Message");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
        enterValueToTxtField(searchTextBox,tmacBroadCastMsgDetails.getMessage());
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

	public boolean verifyinvalidsearch(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
		searchTmacBroadcastMsg(tmacBroadCastMsgDetails.getMessage());
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

	public void duplicateRecord(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
		addTmacBroadcastMsg(tmacBroadCastMsgDetails);
	   try {
		selectWebElement(addcancel);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addInvalidRecord(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
		selectWebElement(addnewTmacBroadcastMessage);
        selectWebElement(TeamNameDropDown);
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getCountry());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getDivision());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getDepartment());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getTeamName());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,tmacBroadCastMsgDetails.getStatus());
        selectWebElement(SaveButton);
        selectWebElement(addcancel);
	}
	public void addInvalidRecord1(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
		selectWebElement(addnewTmacBroadcastMessage);
        selectWebElement(TeamNameDropDown);
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getCountry());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getDivision());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getDepartment());
        ChooseTeamHeirarchy(tmacBroadCastMsgDetails.getTeamName());
        enterValueToTxtField(messageTextbox,tmacBroadCastMsgDetails.getMessage());
        selectWebElement(SaveButton);
        selectWebElement(addcancel);
	}
	public void addInvalidRecord2(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
		selectWebElement(addnewTmacBroadcastMessage);
        enterValueToTxtField(messageTextbox,tmacBroadCastMsgDetails.getMessage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,tmacBroadCastMsgDetails.getStatus());
        selectWebElement(SaveButton);
        selectWebElement(addcancel);
	}

	public boolean editcancel(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
		searchTmacBroadcastMsg(tmacBroadCastMsgDetails.getMessage());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        selectWebElement(messageTextbox);
        enterValueToTxtField(messageTextbox,tmacBroadCastMsgDetails.getUpdatedMessage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,tmacBroadCastMsgDetails.getUpdatedStatus());
        enterValueToTxtFieldWithoutClear(ModifyReasonTextBox,tmacBroadCastMsgDetails.getModifyReason());
        selectWebElement(addcancel);
        waitForJqueryLoad(driver);
        if(tablerow.getText().equals(tmacBroadCastMsgDetails.getMessage())) {
        return true;}
        else
		return false; 
	}
			
	public boolean verifyLastchangedByenable() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		if(!LastChangedBycheckbox.isSelected())
		selectCheckbox(LastChangedBycheckbox);
		if(lastchangedby.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean verifyLastchangedBydisable() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		if(LastChangedBycheckbox.isSelected())
		selectCheckbox(LastChangedBycheckbox);
		if(lastchangedby.isDisplayed())
			return false;
		else
			return true;
	}
	
	public boolean verifyLastchangedonenable() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		if(!LastChangedOncheckbox.isSelected())
		selectCheckbox(LastChangedOncheckbox);
		if(lastchangedon.isDisplayed())
			return true;
		else
			return false;
	}
	public boolean verifyLastchangedondisable() {
		selectWebElement(headerColumn);
		moveToElement(coloumnarrow);
		if(LastChangedOncheckbox.isSelected())
		selectCheckbox(LastChangedOncheckbox);
		if(lastchangedon.isDisplayed())
			return false;
		else
			return true;
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
				/*if(headers.get(j).getText().equals("Last Changed On")){
					col=cols.get(j).getText().substring(10);
					}
				else*/
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
				/*if(headers.get(j).getText().equals("Last Changed On")){
					col=cols.get(j).getText().substring(0,10);
					}
				else*/
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

	public boolean verifyExportToExcel(String filepath) {
		final File folder = new File(filepath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("TMAC Broadcast Message")) {
		        f.delete();
		    }
		}
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filepath, "TMAC Broadcast Message");
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
		DragandDrop(message,droptarget);
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
        selectInvisibleWebElement(nextPageIcon);
        int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
        selectInvisibleWebElement(previousPageIcon);
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
            selectInvisibleWebElement(lastPageIcon);
            int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            selectInvisibleWebElement(firstPageIcon);
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

	public boolean isAddBtnDisplayed() {
    	return addnewTmacBroadcastMessage.isDisplayed() && addnewTmacBroadcastMessage.isEnabled();
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
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }
	
	public void SortByAscending() {
		selectWebElement(teamName.get(1));
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(message);
		selectWebElement(message);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean ExporttoExcelWithoutData(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) throws Exception {
			searchTmacBroadcastMsg(tmacBroadCastMsgDetails.getMessage());
			waitForJqueryLoad(driver);
			selectWebElement(exporttoexcel);
			if(errorMsg.get(0).getText().equals("There is no record to export"))
				return true;
			else
			return false;
	}
}