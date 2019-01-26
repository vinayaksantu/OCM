package com.tetherfi.pages;


import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    
    @FindBy(xpath="//table//tbody/tr/td[3]")
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
   
    @FindBy(xpath="//a[text()='Team Name']")
    private WebElement teamName;
   
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

    @FindBy(css = "#toast-container .toast-error")
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
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
	private WebElement groupbymessage;
    
    @FindBy(xpath="//span[@class='k-input']")
    private WebElement pagerSize;
    
    @FindBy(xpath="//*[@id='grid']/div[5]/a[3]/span")
    private WebElement nextPageIcon;
    
    @FindBy(xpath="//p[@class='k-reset']")
    private WebElement groupby;
    
    
    

    public boolean isTmacBroadcastMsgPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return tmacBroadcastMsg.isEnabled();
    }

    public void searchTmacBroadcastMsg(String message) {
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
    public void addTmacBroadcastMsg(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
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
        selectWebElement(SaveButton);
    }
    public void addTmacBroadcastMsgDivision(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
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
    
    public void editTmacBroadcastMsg(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
        searchTmacBroadcastMsg(tmacBroadCastMsgDetails.getMessage());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(messageTextbox);
        enterValueToTxtField(messageTextbox,tmacBroadCastMsgDetails.getUpdatedMessage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,tmacBroadCastMsgDetails.getStatus());
        selectWebElement(ModifyReasonTextBox);
        enterValueToTxtField(ModifyReasonTextBox,tmacBroadCastMsgDetails.getModifyReason());
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
		if(Status.equals(teamName.isDisplayed()))
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
	public boolean addNewCancel(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
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

	public boolean clearAll(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
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

	public boolean verifyinvalidsearch(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
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

	public void duplicateRecord(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
		addTmacBroadcastMsg(tmacBroadCastMsgDetails);
		selectWebElement(addcancel);
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
	public void addInvalidRecord1(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
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
	public void addInvalidRecord2(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
		selectWebElement(addnewTmacBroadcastMessage);
        enterValueToTxtField(messageTextbox,tmacBroadCastMsgDetails.getMessage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,tmacBroadCastMsgDetails.getStatus());
        selectWebElement(SaveButton);
        selectWebElement(addcancel);
	}

	public boolean editcancel(TmacBroadCastMsgDetails tmacBroadCastMsgDetails) {
		searchTmacBroadcastMsg(tmacBroadCastMsgDetails.getMessage());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(messageTextbox);
        enterValueToTxtField(messageTextbox,tmacBroadCastMsgDetails.getUpdatedMessage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,tmacBroadCastMsgDetails.getStatus());
        selectWebElement(ModifyReasonTextBox);
        enterValueToTxtField(ModifyReasonTextBox,tmacBroadCastMsgDetails.getModifyReason());
        selectWebElement(addcancel);
        waitForJqueryLoad(driver);
        if(tablerow.getText().equals(tmacBroadCastMsgDetails.getMessage()))
        return true;
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
		List<Map<String,String>> UI=gettable(); 
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

	public boolean verifyExportToExcel(String filepath) {
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
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
}