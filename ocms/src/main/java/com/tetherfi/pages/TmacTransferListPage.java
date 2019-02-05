package com.tetherfi.pages;

import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import com.tetherfi.model.tmac.TmacTransferListDetails;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TmacTransferListPage extends BasePage {
    public TmacTransferListPage(WebDriver driver) {
        super(driver);
    }
    
    @FindBy(linkText="OCM")
    private WebElement ocmHeader;
    
    @FindBy(css=".ibox-title h5")
    private WebElement tmacTransferList;
    
    @FindBy(css="#gridDiv2 .search-link")
    private WebElement searchLink;

    @FindBy(css="#gridDiv .search-link")
    private WebElement gridsearchLink;

    @FindBy(css=".modal-footer .button-danger-theme")
    private WebElement searchClearAllBtn;

    @FindBy(css=".clearSearch-link")
    private List<WebElement> clearSearchLink;

    @FindBy(id="gridDivClose")
    private WebElement fullScreenLink;
    
    @FindBy(css="ul[class='k-tabstrip-items k-reset'] li")
    private List<WebElement> TmacTransferListTabs;
    
    @FindBy(css = ".fa-search")
    private WebElement searchBtn;

    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchCol;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css = "#tgrid .k-grid-edit")
    private WebElement editButton;

    @FindBy(css = "#drillgrid .k-grid-edit")
    private WebElement editButton1;

    @FindBy(css = "#1001sFormDropDown .k-i-arrow-60-down")
    private List<WebElement> selectSearchCriteria;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;

    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;

    @FindBy(css = "#1001sAddButton .k-i-add")
    private WebElement searchAddCriteriaBtn;

    @FindBy(xpath = "//div[@id='tabstrip']/ul/li")
    private List<WebElement> navTabs;

    @FindBy(id="create")
    private List<WebElement> addNewTmacTransferListBtn;

    @FindBy(id="createone")
    private List<WebElement> addNewTmacBlindTransferListBtn;

    @FindBy(css="#Name")
    private WebElement tmacTransferListNameTextBox;

    @FindBy(css=".col-lg-6 .k-widget .k-state-default .k-formatted-value")
    private WebElement tmacTransferListAgentExtnsnTextBox;

    @FindBy(css="#Value")
    private WebElement tmacTransferListAgentExtnsnTextBox1;

    @FindBy(css=".ibox-title h5")
    private WebElement tmacTansferList;

    @FindBy(css=".k-grid-update")
    private WebElement SaveButton;

    @FindBy(css=".k-grid-cancel")
    private WebElement CancelButton;
    
    @FindBy(css="span[aria-owns='Type_listbox']")
    private WebElement typeDropdown;

    @FindBy(css="ul[id='Type_listbox'] li")
    private List<WebElement> typeListBox;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css = "#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;

    @FindBy(css=".k-grid-content")
    private List <WebElement> gridContent;

    @FindBy(id = "ModifyReason")
    private WebElement editModifyReasonTextBox;

    @FindBy(css = "#grid .k-grid-CustomDelete")
    private WebElement deleteButton;

    @FindBy(css = "#drillgrid .k-grid-CustomDelete")
    private WebElement deleteButton1;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;
    
    @FindBy(id = "noButton")
    private WebElement deleteNoBtn;

    @FindBy(css="#myform .k-formatted-value")
    private WebElement skillIdTextBox;

    @FindBy(css="#SkillId")
    private WebElement skillIdTextBox1;

    @FindBy(css="#SkillName")
    private WebElement skillNameTextBox;
    
    @FindBy(css=".col-lg-7 .k-dropdown .k-dropdown-wrap .k-input")
    private WebElement skillNameListBox;
    
    @FindBy(css="ul[id='SkillName_listbox'] li")
    private List<WebElement> skillNameListElements;

   @FindBy(css=".col-lg-7 .k-numerictextbox .k-numeric-wrap .k-formatted-value")
   private List<WebElement> skillIdVdnList;
   //@FindBy(xpath="//*[@id='Vdn']/../input")
    //private WebElement skillIdVdn;
   
   @FindBy(css=".k-grid-header th[data-role='columnsorter']")
   private List<WebElement> TmacTransferListTableHeaders;
    
   @FindBy(id="export")
   private WebElement exportToExcelBtn;
   
   @FindBy(id="exportone")
   private WebElement exportToExcelBtnBlindTrans;
   
   @FindBy(css=".k-edit-form-container")
   private WebElement popupContent;
   
   @FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
   
   @FindBy(xpath="//*[@id='createone']/../..//span[@class='k-pager-info k-label']")
	private WebElement itemsBlindTrans;
   
   @FindBy(xpath="//*[@id='myWindow_wnd_title']/../div/a[@aria-label='Close']")
   private WebElement delPopupCloseBtn;
   
   @FindBy(id="myWindow_wnd_title")
   private WebElement delWindow;
   
   @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
   
   @FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize;
   
   @FindBy(xpath="//*[@id='createone']/../..//span[@class='k-input']")
	private WebElement pagerSizeBlindTrans;
   
   @FindBy(id="tgrid")
	private WebElement auditGridContent;
   
   @FindBy(id="tdrillgrid")
	private WebElement auditGridContentBlindTrans;
   
   @FindBy(xpath="//*[@id='grid']/div[5]/a[3]/span")
	private WebElement nextPageIcon;
   
   @FindBy(xpath="//*[@id='drillgrid']/div[5]/a[3]/span")
	private WebElement nextPageIconBlindTrans;
   
   @FindBy(xpath="//a[text()='Name']")
	private WebElement nameColumn;
   
   @FindBy(xpath="//a[text()='Skill Name']")
	private WebElement skillNameColumn;
   
   @FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
   
   @FindBy(xpath="//*[@id='drillgrid']//div[@data-role='droptarget']")
	private WebElement droptargetBlindTrans;
   
   @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
	private WebElement groupbymessage;
	
   @FindBy(xpath="//*[@id='drillgrid']//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
	private WebElement groupbymessageBlindTrans;
   
	@FindBy(xpath="//p[@class='k-reset']")
   private WebElement groupby;
	
	@FindBy(xpath="//*[@id='drillgrid']/div[4]/table/tbody/tr/td/p[@class='k-reset']")
	private WebElement groupbyBlindTrans;
	
	@FindBy(xpath="//*[@id='Value']/..//span//span[@aria-label='Increase value']/span")
	private WebElement AgtExtIncrease;
   
    public boolean isTmacTransferListPageDisplayed() {
        waitForLoad(driver);
        //waitForJqueryLoad(driver);
        return tmacTansferList.isEnabled();
    }
    
    public String getHeaderText(){
        return tmacTransferList.getText();
    }
    public boolean verifySearchLink(){
        return (isWebElementDisplayed(gridsearchLink)&& isWebElementEnabled(gridsearchLink));
    }
    public boolean verifyClearSearchLink(){
        return (isWebElementDisplayed(clearSearchLink.get(0))&& isWebElementEnabled(clearSearchLink.get(0)));
    }
    public boolean verifyFullScreenLink(){
        return (isWebElementDisplayed(fullScreenLink)&& isWebElementEnabled(fullScreenLink));
    }
    public boolean verifyTmacTransferListTabsDisplayed(){
        return TmacTransferListTabs.get(0).isDisplayed()&&TmacTransferListTabs.get(1).isDisplayed();
    }
    public boolean verifyAddNewConsultTransferButton(){
        return addNewTmacTransferListBtn.get(0).isEnabled();
    }
    public boolean verifyAddNewBlindTransferButton(){
        return addNewTmacBlindTransferListBtn.get(0).isEnabled();
    }
    public boolean verifyExportToExcelButton(){
        return exportToExcelBtn.isEnabled();
    }
    public boolean verifyExportToExcelButtonBlindTrans(){
        return exportToExcelBtnBlindTrans.isEnabled();
    }
    private ArrayList getHeadersfromTable(List<WebElement> e){
        ArrayList header=new ArrayList();
        for(int j=0;j<e.size();j++){
            scrollToElement(e.get(j));
            if(!e.get(j).getText().equals("")){header.add(e.get(j).getText());}
        }
        return header;
    }
    
    public boolean verifyTmacConsultTransferDataTableHeaders() {
        ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Name","Agent Extension","Type","Last Changed By","Last Changed On"));
        ArrayList Actual = getHeadersfromTable(TmacTransferListTableHeaders);
        Collections.sort(Expected);Collections.sort(Actual);
        return Actual.equals(Expected);
    }
    
    public boolean verifyTmacBlindTransferDataTableHeaders() {
        ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Skill Name","Skill Id","VDN","Last Changed By","Last Changed On"));
        ArrayList Actual = getHeadersfromTable(TmacTransferListTableHeaders);
        Collections.sort(Expected);Collections.sort(Actual);
        return Actual.equals(Expected);
    }
    
    public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(navTabs);
        waitUntilWebElementListIsClickable(navTabs);
        for(WebElement ele: navTabs){
            if(ele.getText().equalsIgnoreCase(tabname)){selectWebElement(ele);break;}
        }
    }
    
    public void selectNewConsulTransBtn(){
        selectWebElement(addNewTmacTransferListBtn.get(0));
        //waitForJqueryLoad(driver);
    }
    
    public void EnterNameNewConsulTrans(TmacTransferListDetails tmacTransferListDetails){
    	selectWebElement(tmacTransferListNameTextBox);
        enterValueToTxtField(tmacTransferListNameTextBox,tmacTransferListDetails.getName());
    }
    
    public void EnterAgtExtNewConsulTrans(TmacTransferListDetails tmacTransferListDetails){
    	selectWebElement(tmacTransferListAgentExtnsnTextBox);
    	enterValueToTxtFieldWithoutClear(tmacTransferListAgentExtnsnTextBox1,tmacTransferListDetails.getAgentExtension());
    }
    
    public void SelTypeNewConsulTrans(TmacTransferListDetails tmacTransferListDetails){
    	selectWebElement(typeDropdown);
        selectDropdownFromVisibleText(typeListBox,tmacTransferListDetails.getType());
    }
    
    public void SelSaveNewConsulTrans() throws InterruptedException {
    	Thread.sleep(1000);
    	selectWebElement(SaveButton);
    }
    
    public void selectNewBlindTransBtn(){
        selectWebElement(addNewTmacBlindTransferListBtn.get(0));
        //waitForJqueryLoad(driver);
    }
    
    public void selectCancelOnAddNewConsulTransPopUp() throws InterruptedException{
        selectWebElement(CancelButton);
        Thread.sleep(2000);
    }
    
    public void addNewTmacConsultTransferList(TmacTransferListDetails tmacTransferListDetails) {
        selectWebElement(addNewTmacTransferListBtn.get(0));
        selectWebElement(tmacTransferListNameTextBox);
        enterValueToTxtField(tmacTransferListNameTextBox,tmacTransferListDetails.getName());
        selectWebElement(tmacTransferListAgentExtnsnTextBox);
        enterValueToTxtField(tmacTransferListAgentExtnsnTextBox1,tmacTransferListDetails.getAgentExtension());
        selectWebElement(typeDropdown);
        selectDropdownFromVisibleText(typeListBox,tmacTransferListDetails.getType());
        selectWebElement(SaveButton);
    }
    public void searchTmacTransferRecord(String Name,String columnNameListValue,int index) {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,columnNameListValue);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        boolean staleElement = true;
        while(staleElement){
            try{
                enterValueToTxtField(searchTextBox,Name);
                staleElement = false;

            } catch(StaleElementReferenceException e){
                staleElement = true;
            }
        }

        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent.get(index));
    }
    public void editTmacConsultTransferList(TmacTransferListDetails tmacTransferListDetails) {
        searchTmacTransferRecord(tmacTransferListDetails.getName(),"Name",0);
        selectWebElement(editButton);
        selectWebElement(tmacTransferListNameTextBox);
        enterValueToTxtField(tmacTransferListNameTextBox,tmacTransferListDetails.getUpdName());
        int incAgtExt = tmacTransferListDetails.getIncAgtExt();
        for (int i=0; i<incAgtExt; i++) {
        	selectWebElement(AgtExtIncrease);
        }
        //selectWebElement(tmacTransferListAgentExtnsnTextBox);
        //enterValueToTxtField(tmacTransferListAgentExtnsnTextBox1,tmacTransferListDetails.getUpdAgtExt());
        selectWebElement(typeDropdown);
        selectDropdownFromVisibleText(typeListBox,tmacTransferListDetails.getType());
        enterValueToTxtField(editModifyReasonTextBox,tmacTransferListDetails.getModReason());
        selectWebElement(SaveButton);
    }
    public void deleteTmacConsultTransferList(TmacTransferListDetails tmacTransferListDetails) {
        searchTmacTransferRecord(tmacTransferListDetails.getName(),"Name",0);
        selectWebElement(deleteButton);
        enterValueToTxtField(deleteReasonTextBox,tmacTransferListDetails.getDeleteReason());
        selectWebElement(deleteYesBtn);
    }
    
    public void deleteTmacConsultTransferListNoBtn(TmacTransferListDetails tmacTransferListDetails) {
        searchTmacTransferRecord(tmacTransferListDetails.getName(),"Name",0);
        selectWebElement(deleteButton);
        enterValueToTxtField(deleteReasonTextBox,tmacTransferListDetails.getDeleteReason());
        selectWebElement(deleteNoBtn);
    }
    
    public void deleteTmacBlindTransferListNoBtn(TmacTransferListDetails tmacTransferListDetails) {
        searchTmacTransferRecord(tmacTransferListDetails.getSkillName(),"Skill Name",1);
        selectWebElement(deleteButton1);
        enterValueToTxtField(deleteReasonTextBox,tmacTransferListDetails.getDeleteReason());
        selectWebElement(deleteNoBtn);
    }

    public void addNewTmacBlindTransferList(TmacTransferListDetails tmacTransferListDetails) {
        selectWebElement(addNewTmacBlindTransferListBtn.get(0));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(skillNameListBox);
        selectDropdownFromVisibleText(skillNameListElements,tmacTransferListDetails.getSkillName());
        //selectWebElement(skillIdVdnList.get(1));
        enterValueToTxtField(skillIdVdnList.get(1),tmacTransferListDetails.getVdn());
        selectWebElement(SaveButton);
    }
    public void editTmacBlindTransferList(TmacTransferListDetails tmacTransferListDetails) throws InterruptedException {
    	searchTmacTransferRecord(tmacTransferListDetails.getSkillName(),"Skill Name",1);
    	selectWebElement(editButton1);
    	enterValueToTxtFieldWithoutClear(skillIdVdnList.get(1),tmacTransferListDetails.getUpdVdn());
        selectWebElement(skillNameListBox);
        selectDropdownFromVisibleText(skillNameListElements,tmacTransferListDetails.getSkillNameUpdate());
        //selectWebElement(skillIdVdnList.get(1));
        selectWebElement(editModifyReasonTextBox);
        enterValueToTxtField(editModifyReasonTextBox,tmacTransferListDetails.getModReason());
        selectWebElement(SaveButton);
    }

    public void deleteTmacBlindTransferList(TmacTransferListDetails tmacTransferListDetails) {
        searchTmacTransferRecord(tmacTransferListDetails.getSkillName(),"Skill Name",1);
        selectWebElement(deleteButton1);
        enterValueToTxtField(deleteReasonTextBox,tmacTransferListDetails.getDeleteReason());
        selectWebElement(deleteYesBtn);
    }
    
    public String verifyMessage() {
    	if(errorMsg.size()>0){
    		return errorMsg.get(0).getText();
    		}
        else { 
        	waitUntilWebElementIsVisible(successmsg);
        	return successmsg.getText();}
    	}
    
    public String verifyMessage2() {
    	if(errorMsg.size()>0){
    		return errorMsg.get(1).getText();
    		}
        else { 
        	waitUntilWebElementIsVisible(successmsg);
        	return successmsg.getText();}
    	}
    
    public boolean verifyNewRecordCreated(){
        //waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }
    public boolean verifyRecordUpdated(){
       // waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordDeleted(){
        //waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully")){
            return true;
        }else{return false;}
    }

    public boolean verifyAddNewConsulTransPopupContents(){
        return popupContent.isDisplayed()&&SaveButton.isEnabled()&&CancelButton.isEnabled();
    }

	public boolean verifyAddNewBlindTransPopupContents() {
		return popupContent.isDisplayed()&&SaveButton.isEnabled()&&CancelButton.isEnabled();
	}

	public void selectCancelOnAddNewBlindTransPopUp() throws InterruptedException {
		selectWebElement(CancelButton);
        Thread.sleep(1000);
		
	}
	
	public void selectDeleteNoButtonConsulTrans() throws InterruptedException {
		selectWebElement(deleteNoBtn);
        Thread.sleep(1000);
		
	}
	
	public void selectDeleteCloseButtonConsulTrans() throws InterruptedException {
		selectWebElement(delPopupCloseBtn);
        Thread.sleep(1000);	
	}
	
	public boolean deletePopUpNotDisplayed() throws InterruptedException {
		Thread.sleep(1000);
		if(delWindow.isDisplayed())
			return false;
		else
			return true;
	}
	
	public boolean toastMsgDisplayed() {
		return errorMsg.isEmpty();
	}
	
	public boolean addNewCancel(TmacTransferListDetails tmacTransferListDetails) {
		String actualitems=items.getText();
		selectWebElement(addNewTmacTransferListBtn.get(0));
        waitForJqueryLoad(driver);
        selectWebElement(tmacTransferListNameTextBox);
        enterValueToTxtField(tmacTransferListNameTextBox,tmacTransferListDetails.getName());
        selectWebElement(tmacTransferListAgentExtnsnTextBox);
        enterValueToTxtField(tmacTransferListAgentExtnsnTextBox1,tmacTransferListDetails.getAgentExtension());
        selectWebElement(typeDropdown);
        selectDropdownFromVisibleText(typeListBox,tmacTransferListDetails.getType());
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        selectWebElement(CancelButton);
        if(actualitems.equals(items.getText()))
        	return true;
        else 
        	return false;
	}
	
	public boolean addNewCancelBlindTrans(TmacTransferListDetails tmacTransferListDetails) {
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		String actualitems=itemsBlindTrans.getText();
		selectWebElement(addNewTmacBlindTransferListBtn.get(0));
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		EnterSkillNameNewBlindTrans(tmacTransferListDetails);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
        selectWebElement(CancelButton);
        if(actualitems.equals(itemsBlindTrans.getText()))
        	return true;
        else 
        	return false;
	}
	
	public boolean verifyExportToExcelConsul(String filepath) throws InterruptedException {
		final File folder = new File(filepath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("TMACConsultTransfer")) {
		        f.delete();
		    }
		}
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		Boolean Status=verifyExportPageFileDownload(filepath, "TMACConsultTransfer");
		return Status;
	}
	
	public boolean verifyExportToExcelBlind(String filepath) throws InterruptedException {
		final File folder = new File(filepath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("TMACBlindTransfer")) {
		        f.delete();
		    }
		}
		selectWebElement(exportToExcelBtnBlindTrans);
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		Boolean Status=verifyExportPageFileDownload(filepath, "TMACBlindTransfer");
		return Status;
	}
	
	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) throws InterruptedException {
		List<Map<String,String>> UI=getdata(); 
		if(UI.equals(maplist))
		return true;
		else
		return false;
	}
	
	public boolean verifyexportToExcelSheetBlindTrans(List<Map<String, String>> maplist) throws InterruptedException {
		Thread.sleep(2000);
		List<Map<String,String>> UI=getdataBlindTrans(); 
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
		return true;
		else
		return false;
	}
	
	private List<Map<String,String>> getdata() throws InterruptedException{		
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
			while(map.values().remove(""));
			arr.add(map);
		}
		if(k!=pages) {
			nextPageIcon.click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}
	
	private List<Map<String,String>> getdataBlindTrans() throws InterruptedException{
		int item=Integer.valueOf(itemsBlindTrans.getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSizeBlindTrans.getText());
        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
		waitUntilWebElementIsVisible(auditGridContentBlindTrans);
		List<WebElement> rows=auditGridContentBlindTrans.findElements(By.tagName("tr"));
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
			nextPageIconBlindTrans.click();
			waitForJqueryLoad(driver);}
		}
			return arr;
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
	
	public boolean verifyDatabaseBlindTrans(String query) {
		List<Map<String,String>> database=database(query);
		System.out.println(database);
		List<Map<String,String>> UI=gettableBlindTrans(); 
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
				if(headers.get(j).getText().equals("Last Changed On"))
					col=cols.get(j).getText().replaceAll("/", "-");
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
	private List<Map<String,String>> gettableBlindTrans(){
		int item=Integer.valueOf(itemsBlindTrans.getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSizeBlindTrans.getText());
        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
		waitUntilWebElementIsVisible(auditGridContentBlindTrans);
		List<WebElement> rows=auditGridContentBlindTrans.findElements(By.tagName("tr"));
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
				if(headers.get(j).getText().equals("Last Changed On"))
					col=cols.get(j).getText().replaceAll("/", "-");
				else
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			while(map.values().remove(""));
			arr.add(map);
		}
		if(k!=pages) {
		nextPageIconBlindTrans.click();
		waitForJqueryLoad(driver);}
		}
			return arr;
	}
	public boolean groupby() {
		DragandDrop(nameColumn,droptarget);
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
	public boolean groupbyBlindTrans() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DragandDrop(skillNameColumn,droptargetBlindTrans);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupbyBlindTrans.getText().split(": ")[1].equals(groupbymessageBlindTrans.getText()))
		{return true;}
		else
			return false;		
	}
	public void selectFullScreen() {
		selectWebElement(fullScreenLink);
	}
	
	public boolean ocmHeaderDisplayed() throws InterruptedException {
		Thread.sleep(1000);
		try {
			selectWebElement(ocmHeader);
			return true;
		}catch (Exception e){
			return false;
		}
	}
	public boolean ocmHeaderNotDisplayed() throws InterruptedException {
		Thread.sleep(1000);
		try {
			selectWebElement(ocmHeader);
			return false;
		}catch (Exception e){
			return true;
		}
	}

	public void EnterSkillNameNewBlindTrans(TmacTransferListDetails tmacTransferListDetails) {
		selectWebElement(skillNameListBox);
        selectDropdownFromVisibleText(skillNameListElements,tmacTransferListDetails.getSkillName());	
	}

	public void EnterVDNNewBlindTrans(TmacTransferListDetails tmacTransferListDetails) {
		//selectWebElement(skillIdVdnList.get(1));
		enterValueToTxtField(skillIdVdnList.get(1),tmacTransferListDetails.getVdn());
	}
}
