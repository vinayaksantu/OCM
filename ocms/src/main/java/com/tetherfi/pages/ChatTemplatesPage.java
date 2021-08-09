package com.tetherfi.pages;

import com.tetherfi.model.chat.ChatTemplateDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatTemplatesPage extends BasePage {

    public ChatTemplatesPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement chatTemplates;

    @FindBy(xpath="//div[@id='tabstrip']/ul/li")
    private List<WebElement> tabList;

    @FindBy(css="#tgrid .k-grid-content")
    private WebElement gridContent;
    
    @FindBy(css="#tdrillgrid .k-grid-content")
    private WebElement gridContent1;
    
    @FindBy(css="#tdrillgrid2 .k-grid-content")
    private WebElement gridContent2;
    
    @FindBy(id="tdrillgrid")
    private WebElement grid;
    
    @FindBy(id="tdrillgrid2")
    private WebElement grid1;
    
    @FindBy(id="tgrid")
    private WebElement grid2;

    @FindBy(css="#tgridChatDepartment .k-grid-content")
    private WebElement departmentsGridContent;

    @FindBy(css="#tgridChatGroup .k-grid-content")
    private WebElement groupsGridContent;

    @FindBy(xpath="//button[normalize-space()='Add New Text Templates Record']")
    private WebElement addNewChatTemplatesRecordBtn;

    @FindBy(xpath="//button[normalize-space()='Add New Department Record']")
    private WebElement addNewDepartmentRecordBtn;

    @FindBy(xpath="//button[normalize-space()='Add New Group Record']")
    private WebElement addNewGroupRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popupContent;

    @FindBy(css="span[aria-owns='DepartmentName_listbox']")
    private WebElement departmentNameDropdown;

    @FindBy(css="ul[id='DepartmentName_listbox'] li")
    private List<WebElement> departmentNameListbox;

    @FindBy(css="span[aria-owns='GroupName_listbox']")
    private WebElement groupNameDropdown;

    @FindBy(css="ul[id='GroupName_listbox'] li")
    private List<WebElement> groupNameListbox;

    @FindBy(id="Name")
    private WebElement nameTextbox;

    @FindBy(css="span[aria-owns='Enabled_listbox']")
    private WebElement enableDropdown;

    @FindBy(css="ul[id='Enabled_listbox'] li")
    private List<WebElement> enableListbox;

    @FindBy(id="Text")
    private WebElement textTextbox;

    @FindBy(id="StartTime")
    private WebElement startTimeTextbox;
    
    @FindBy(id="EndTime")
    private WebElement endTimeTextbox;

    @FindBy(css=".k-edit-form-container .k-grid-update")
    private WebElement saveBtn;

    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;

    @FindBy(css="#toast-container .toast-success .toast-message")
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

    @FindBy(id="1001sTextToSearch")
    private WebElement searchText;

    @FindBy(css=".modal-footer .k-primary")
    private WebElement searchBtn;
    
    @FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;
		    
	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;
		    
	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;
		    
	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
	private WebElement clearsearch;

    @FindBy(css=".k-grid-edit")
    private WebElement editBtn;

    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;

    @FindBy(css=".k-edit-form-container #ModifyReason")
    private  WebElement modifyReasonTextBox;

    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;

    @FindBy(id="yesButton")
    private WebElement yesBtn;

    @FindBy(id="noButton")
    private WebElement noBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private List<WebElement> exporttoexcel;
    
    @FindBy(xpath="//i[@class='fas fa-comments']")
	private WebElement CTImg;
    
    @FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;
			    
	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;
			    
	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;
			    
	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;		
			    
	@FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
	private List<WebElement> tablerecord;
			   
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private List<WebElement> items;
			    
	@FindBy(id="navbarheader")
	private WebElement header;
			    
	@FindBy(xpath="//tbody/tr/td[6]")
	private WebElement rowdata;
			    
	@FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
	private WebElement coloumnarrow;
			    
	@FindBy(id="tGrid")
	private WebElement auditGridContent;
	
	@FindBy(xpath="//span[@class='k-input']")
	private List<WebElement> pagerSize;
    
	@FindBy(css="a[aria-label='Go to the next page']")
	private List<WebElement> nextPageIcon;
		    
	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;
		    
	@FindBy(css="a[aria-label='Go to the first page']")
	private List<WebElement> firstPageIcon;
		    
	@FindBy(css="a[aria-label='Go to the previous page']")
	private List<WebElement> previousPageIcon;
		
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
				
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
				 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
		
	@FindBy(xpath="//div[@data-role='droptarget']")
	private List<WebElement> droptarget;
	
	@FindBy(css=".k-edit-form-container")
	private WebElement editFormContainer;

	@FindBy(css="div[style='display: block; z-index: 10002; opacity: 0.5;']")
	private WebElement deleteContainer;
	
	@FindBy(xpath="//a[text()='Name']")
	private List<WebElement> name;
	
	@FindBy(xpath="//a[text()='Enabled']")
	private List<WebElement> Enabled;
   
	@FindBy(css=".k-pager-numbers .k-state-selected")
    private List<WebElement> pageNumber;
    
    @FindBy(css="a[aria-label='Go to the last page']")
    private List<WebElement> lastPageIcon;
    
    @FindBy(css=".k-pager-sizes .k-icon")
    private List<WebElement> pagerDropdown;
    
    @FindBy(css="#drillGrid .k-grouping-header")
    private WebElement dragColumnDestination;
    
    @FindBy(css="span[aria-owns='Channel_listbox']")
    private WebElement channelDropdown;
    
    @FindBy(css="ul[id='Channel_listbox'] li")
    private List<WebElement> channelListbox;
    
   

    public boolean isChatTemplatePageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return chatTemplates.isEnabled();
    }
    public boolean isDepartmentTabDisplayed(){
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(departmentsGridContent);
        return departmentsGridContent.isDisplayed();
    }
    public boolean isGroupTabDisplayed(){
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(groupsGridContent);
        return groupsGridContent.isDisplayed();
    }
    
    public boolean VerifyLogo() {
    	if(CTImg.isDisplayed())
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
		{
			return true;
		}
		else 
			return false; 
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
	    	 boolean status=false;
		        try{for(WebElement ele:headersDropdown) {
		        	scrollToElement(ele);
		        	 if (!ele.isDisplayed()) {
			                continue;
		        	 }
			                else {
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
		            break;
		        }
	    }
		        catch (Exception e) {
		            e.printStackTrace();
		        }
		 	        return status;
	    }
	    public boolean verifycolumnsHeaderEnabled(){
	        boolean status=false;
	        try{
	        	for(WebElement ele:headersDropdown) {
	        	scrollToElement(ele);
	        	 if (!ele.isDisplayed()) {
		                continue;
	        	 }
	        	 else {
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
	            break;
	        }
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return status;
	    }
	    
    public void addNewChatTemplatesRecord(ChatTemplateDetails details) throws Exception {
        selectWebElement(addNewChatTemplatesRecordBtn);
        waitUntilWebElementIsVisible(popupContent);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(departmentNameDropdown);
        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
        selectWebElement(groupNameDropdown);
        selectDropdownFromVisibleText(groupNameListbox,details.getGroupName());
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getName());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getEnabled());
        selectWebElement(textTextbox);
        enterValueToTxtField(textTextbox,details.getText());
        selectWebElement(startTimeTextbox);
        enterValueToTxtField(startTimeTextbox,details.getStartTime());
        selectWebElement(endTimeTextbox);
        enterValueToTxtField(endTimeTextbox,details.getEndTime());
        selectWebElement(saveBtn);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    public void searchChatTemplatesRecord(String name) throws Exception  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText,name);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editChatTemplatesRecord(ChatTemplateDetails details) throws Exception {
        searchChatTemplatesRecord(details.getName());
        Thread.sleep(1000);
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getUpdatedEnabled());
        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);
    }
    public void deleteChatTemplatesRecord(String name,String reason) throws Exception {
        searchChatTemplatesRecord(name);
        Thread.sleep(1000);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,reason);
        Thread.sleep(2000);
        selectWebElement(yesBtn);
    }
    public boolean verifyNewRecordCreated(){
        //waitForJqueryLoad(driver);
       // if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }
    public boolean verifyRecordDeleted(){
        waitForJqueryLoad(driver);
        //if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully")){
            return true;
        }else{return false;}
    }
    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        //if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
        {return true;}else{return false;}
    }
    public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(tabList);
        waitUntilWebElementListIsClickable(tabList);
        for(WebElement ele:tabList){
            if(ele.getText().equalsIgnoreCase(tabname)){ele.click();break;}
        }
    }
    public void addNewDepartmentRecord(ChatTemplateDetails details) throws Exception {
        selectWebElement(addNewDepartmentRecordBtn);
        Thread.sleep(1000);
        selectWebElement(channelDropdown);
        selectDropdownFromVisibleText(channelListbox,details.getChannel());
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getDepartmentName());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getDeptEnabled());
        selectWebElement(saveBtn);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    public void searchDepartmentRecord(String name) throws Exception  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText,name);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(departmentsGridContent);
    }
    public void editDepartmentRecord(ChatTemplateDetails details) throws Exception {
        searchDepartmentRecord(details.getDepartmentName());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getUpdatedDeptEnabled());
        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);
    }
    public void deleteDepartmentRecord(String name,String reason) throws Exception {
        searchDepartmentRecord(name);
        Thread.sleep(1000);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,reason);
        Thread.sleep(2000);
        selectWebElement(yesBtn);
    }
    public void addNewGroupRecord(ChatTemplateDetails details) throws Exception {
        selectWebElement(addNewGroupRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(departmentNameDropdown);
        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getGroupName());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getGroupEnabled());
        selectWebElement(saveBtn);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    public void searchGroupRecord(String name) throws Exception  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText,name);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(groupsGridContent);
    }
    public void editGroupRecord(ChatTemplateDetails details) throws Exception {
        searchGroupRecord(details.getGroupName());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getUpdatedGroupEnabled());
        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);
    }
    public void deleteGroupRecord(String name,String reason) throws Exception {
        searchGroupRecord(name);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,reason);
        Thread.sleep(2000);
        selectWebElement(yesBtn);
    }
    public boolean isAddBtnDisplayed() {
    	return addNewChatTemplatesRecordBtn.isDisplayed() && addNewChatTemplatesRecordBtn.isEnabled();
    }
    
    public boolean isEditBtnDisplayed() {
    	Boolean status = false;
    	try {
    		if(editBtn.isDisplayed() && editBtn.isEnabled())
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
    	waitForJqueryLoad(driver);
    	return exporttoexcel.get(2).isDisplayed() && exporttoexcel.get(2).isEnabled();
    }
    
    public String getMessage() throws Exception{
        Thread.sleep(1000);
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        else {return successmsg.getText();}
    }
	public boolean verifyEditFormContainer() {
		 try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        return isElementExist(editFormContainer);
	}
	public void clickOnCancelBtn() {
		selectWebElement(cancelBtn);
	}
	public void clickOnAddRecord() {
		selectWebElement(addNewChatTemplatesRecordBtn);
	}
	public void addRecordWithoutEnabled(ChatTemplateDetails details) throws Exception {
		selectWebElement(addNewDepartmentRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(channelDropdown);
        selectDropdownFromVisibleText(channelListbox,details.getChannel());
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getDepartmentName());
        selectWebElement(saveBtn);		
        selectWebElement(cancelBtn);
	}
	public void addRecordWithoutDepartment(ChatTemplateDetails details) throws Exception {
		selectWebElement(addNewDepartmentRecordBtn);
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(channelDropdown);
        selectDropdownFromVisibleText(channelListbox,details.getChannel());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getDeptEnabled());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}
	public void addNewEmptyRecord(ChatTemplateDetails details) {
		selectWebElement(addNewDepartmentRecordBtn);
		waitForJqueryLoad(driver);
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}
	
	public void addRecordWithoutChannel(ChatTemplateDetails details) throws Exception {
		selectWebElement(addNewDepartmentRecordBtn);
		waitForJqueryLoad(driver);
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getDepartmentName());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getDeptEnabled());
        selectWebElement(saveBtn);	
        selectWebElement(saveBtn);		
        selectWebElement(cancelBtn);
	}		
	
	public void addRecordWithoutDepartmentName(ChatTemplateDetails details) throws Exception {
		selectWebElement(addNewGroupRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getGroupName());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getGroupEnabled());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}
	public void addRecordWithoutName(ChatTemplateDetails details) {
		selectWebElement(addNewGroupRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(departmentNameDropdown);
        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
        selectWebElement(enableDropdown);
        selectDropdownFromVisibleText(enableListbox,details.getGroupEnabled());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}
	public void addRecordWithoutGroupEnabled(ChatTemplateDetails details) throws Exception {
		selectWebElement(addNewGroupRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(departmentNameDropdown);
        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getGroupName());
        selectWebElement(saveBtn);	
        selectWebElement(cancelBtn);
	}
	public void addNewEmptyGroupRecord(ChatTemplateDetails chatTemplatesDetails) {
		selectWebElement(addNewGroupRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(saveBtn);
        selectWebElement(cancelBtn);
	}
	public void clickOnAddGroupRecord() {
		selectWebElement(addNewGroupRecordBtn);		
	}
	public void addNewEmptyChatTemplateRecord(ChatTemplateDetails chatTemplatesDetails) {
		selectWebElement(addNewChatTemplatesRecordBtn);
        waitUntilWebElementIsVisible(popupContent);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(saveBtn);
        selectWebElement(cancelBtn);
	}
	public void addRecordWithoutChatDepartmentName(ChatTemplateDetails details) throws Exception {
		  selectWebElement(addNewChatTemplatesRecordBtn);
	        waitUntilWebElementIsVisible(popupContent);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(nameTextbox);
	        enterValueToTxtField(nameTextbox,details.getName());
	        selectWebElement(enableDropdown);
	        selectDropdownFromVisibleText(enableListbox,details.getEnabled());
	        selectWebElement(textTextbox);
	        enterValueToTxtField(textTextbox,details.getText());
	        selectWebElement(startTimeTextbox);
	        enterValueToTxtField(startTimeTextbox,details.getStartTime());
	        selectWebElement(endTimeTextbox);
	        enterValueToTxtField(endTimeTextbox,details.getEndTime());
	        selectWebElement(saveBtn);
	        try {
	        	selectWebElement(cancelBtn);
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	}
	public void addRecordWithoutGroupName(ChatTemplateDetails details) throws Exception {
		  selectWebElement(addNewChatTemplatesRecordBtn);
	        waitUntilWebElementIsVisible(popupContent);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(departmentNameDropdown);
	        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
	        selectWebElement(nameTextbox);
	        enterValueToTxtField(nameTextbox,details.getName());
	        selectWebElement(enableDropdown);
	        selectDropdownFromVisibleText(enableListbox,details.getEnabled());
	        selectWebElement(textTextbox);
	        enterValueToTxtField(textTextbox,details.getText());
	        selectWebElement(startTimeTextbox);
	        enterValueToTxtField(startTimeTextbox,details.getStartTime());
	        selectWebElement(endTimeTextbox);
	        enterValueToTxtField(endTimeTextbox,details.getEndTime());
	        selectWebElement(saveBtn);
	        selectWebElement(cancelBtn);
	}
	public void addRecordWithoutChatName(ChatTemplateDetails details) throws Exception {
		  selectWebElement(addNewChatTemplatesRecordBtn);
	        waitUntilWebElementIsVisible(popupContent);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(departmentNameDropdown);
	        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
	        selectWebElement(groupNameDropdown);
	        selectDropdownFromVisibleText(groupNameListbox,details.getGroupName());
	        selectWebElement(enableDropdown);
	        selectDropdownFromVisibleText(enableListbox,details.getEnabled());
	        selectWebElement(textTextbox);
	        enterValueToTxtField(textTextbox,details.getText());
	        selectWebElement(startTimeTextbox);
	        enterValueToTxtField(startTimeTextbox,details.getStartTime());
	        selectWebElement(endTimeTextbox);
	        enterValueToTxtField(endTimeTextbox,details.getEndTime());
	        selectWebElement(saveBtn);
	        selectWebElement(cancelBtn);
	}
	public void clickOnAddChatTemplateRecord() {
		// TODO Auto-generated method stub
		
	}
	public void addRecordWithoutEndTime(ChatTemplateDetails details) throws Exception {
		  selectWebElement(addNewChatTemplatesRecordBtn);
	        waitUntilWebElementIsVisible(popupContent);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(departmentNameDropdown);
	        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
	        selectWebElement(groupNameDropdown);
	        selectDropdownFromVisibleText(groupNameListbox,details.getGroupName());
	        selectWebElement(nameTextbox);
	        enterValueToTxtField(nameTextbox,details.getName());
	        selectWebElement(enableDropdown);
	        selectDropdownFromVisibleText(enableListbox,details.getEnabled());
	        selectWebElement(textTextbox);
	        enterValueToTxtField(textTextbox,details.getText());
	        selectWebElement(startTimeTextbox);
	        enterValueToTxtField(startTimeTextbox,details.getStartTime());
	        selectWebElement(saveBtn);
	        try {
	        	selectWebElement(cancelBtn);
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
		
	}
	public void addRecordWithoutStartTime(ChatTemplateDetails details) throws Exception {
		  selectWebElement(addNewChatTemplatesRecordBtn);
	        waitUntilWebElementIsVisible(popupContent);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(departmentNameDropdown);
	        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
	        selectWebElement(groupNameDropdown);
	        selectDropdownFromVisibleText(groupNameListbox,details.getGroupName());
	        selectWebElement(nameTextbox);
	        enterValueToTxtField(nameTextbox,details.getName());
	        selectWebElement(enableDropdown);
	        selectDropdownFromVisibleText(enableListbox,details.getEnabled());
	        selectWebElement(textTextbox);
	        enterValueToTxtField(textTextbox,details.getText());
	        selectWebElement(endTimeTextbox);
	        enterValueToTxtField(endTimeTextbox,details.getEndTime());
	        selectWebElement(saveBtn);
	        try {
	        	selectWebElement(cancelBtn);
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }		
	}
	public void addRecordWithoutText(ChatTemplateDetails details) throws Exception {
		  selectWebElement(addNewChatTemplatesRecordBtn);
	        waitUntilWebElementIsVisible(popupContent);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(departmentNameDropdown);
	        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
	        selectWebElement(groupNameDropdown);
	        selectDropdownFromVisibleText(groupNameListbox,details.getGroupName());
	        selectWebElement(nameTextbox);
	        enterValueToTxtField(nameTextbox,details.getName());
	        selectWebElement(enableDropdown);
	        selectDropdownFromVisibleText(enableListbox,details.getEnabled());
	        selectWebElement(startTimeTextbox);
	        enterValueToTxtField(startTimeTextbox,details.getStartTime());
	        selectWebElement(endTimeTextbox);
	        enterValueToTxtField(endTimeTextbox,details.getEndTime());
	        selectWebElement(saveBtn);
	        try {
	        	selectWebElement(cancelBtn);
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }		
	}
	public void clickOnAddDepartmentRecord() {
		selectWebElement(addNewDepartmentRecordBtn);
		
	}
	public boolean VerifyDepartmentDropdown(ChatTemplateDetails chatTemplateDetails) {
		Boolean Status=false;
		selectWebElement(departmentNameDropdown);
		for(WebElement ele : departmentNameListbox) {
			if(ele.getText().equals(chatTemplateDetails.getDepartmentName())) {
				Status=true;
				break;
			}
		}
		return Status;
	}
	
	public void addRecordWithoutChatEnabled(ChatTemplateDetails details) throws Exception {
		selectWebElement(addNewChatTemplatesRecordBtn);
        waitUntilWebElementIsVisible(popupContent);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(departmentNameDropdown);
        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
        selectWebElement(groupNameDropdown);
        selectDropdownFromVisibleText(groupNameListbox,details.getGroupName());
        selectWebElement(nameTextbox);
        enterValueToTxtField(nameTextbox,details.getName());
        selectWebElement(textTextbox);
        enterValueToTxtField(textTextbox,details.getText());
        selectWebElement(startTimeTextbox);
        enterValueToTxtField(startTimeTextbox,details.getStartTime());
        selectWebElement(endTimeTextbox);
        enterValueToTxtField(endTimeTextbox,details.getEndTime());
        selectWebElement(saveBtn);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e){
        	e.printStackTrace();
        }		
	}
	
	 public boolean verifyDepartmentExportToExcel(String filePath) {
			final File folder = new File(filePath);
			for (final File f : folder.listFiles()) {
			    if (f.getName().startsWith("ChatTemplates")) {
			        f.delete();
			    }
			}
			selectWebElement(exporttoexcel.get(0));
			waitForJqueryLoad(driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Boolean Status=verifyExportPageFileDownload(filePath, "ChatTemplatesDepartment");
			return Status;
		}
		public boolean verifyexportToExcelDepartmentSheet(List<Map<String, String>> maplist) {
			List<Map<String,String>> UI=getDepartmentData(); 
			System.out.println(UI);
			System.out.println(maplist);
			if(UI.equals(maplist))
			return true;
			else
			return false;
		}
		
		private List<Map<String,String>> getDepartmentData(){
			System.out.println(items.get(0).getText());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int item=Integer.valueOf(items.get(0).getText().split("of ")[1].split(" items")[0]);
	        int pagersize=Integer.valueOf(pagerSize.get(0).getText());
	        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
			List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
			for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(grid);
			List<WebElement> rows=grid.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			String col=null;
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				for(int j=1;j<headers.size();j++) {
					//if(headers.get(j).getText().equals("Last Changed On")){
					//	col=cols.get(j).getText().substring(0,10);
						//}
					//else
						col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.get(0).click();
				waitForJqueryLoad(driver);}
			}
				return arr;
		}
		
		private List<Map<String,String>> getGroupsData(){
			System.out.println(items.get(1).getText());
			int item=Integer.valueOf(items.get(1).getText().split("of ")[1].split(" items")[0]);
	        int pagersize=Integer.valueOf(pagerSize.get(1).getText());
	        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
			List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
			for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(grid1);
			List<WebElement> rows=grid1.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			String col=null;
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				for(int j=1;j<headers.size();j++) {
					//if(headers.get(j).getText().equals("Last Changed On")){
					//	col=cols.get(j).getText().substring(0,10);
					//	}
					//else
						col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.get(1).click();
				waitForJqueryLoad(driver);}
			}
				return arr;
		}
		
		public boolean verifyGroupsExportToExcel(String filePath) {
			final File folder = new File(filePath);
			for (final File f : folder.listFiles()) {
			    if (f.getName().startsWith("ChatTemplate")) {
			        f.delete();
			    }
			}
			selectWebElement(exporttoexcel.get(1));
			waitForJqueryLoad(driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Boolean Status=verifyExportPageFileDownload(filePath, "ChatTemplateGroup");
			return Status;
		}
		public boolean verifyexportToExcelGroupsSheet(List<Map<String, String>> maplist) {
			List<Map<String,String>> UI=getGroupsData(); 
			System.out.println(UI);
			System.out.println(maplist);
			if(UI.equals(maplist))
			return true;
			else
			return false;
		}
		
		
		
		private List<Map<String,String>> getData(){
			System.out.println(items.get(2).getText());
			int item=Integer.valueOf(items.get(2).getText().split("of ")[1].split(" items")[0]);
	        int pagersize=Integer.valueOf(pagerSize.get(2).getText());
	        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
			List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
			for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(grid2);
			List<WebElement> rows=grid2.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			String col=null;
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				for(int j=1;j<headers.size();j++) {
					//if(headers.get(j).getText().equals("Last Changed On")){
						//col=cols.get(j).getText().substring(0,10);
						//}
					//else
						col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.get(2).click();
				waitForJqueryLoad(driver);}
			}
				return arr;
		}
		
		public boolean verifyExportToExcel(String filePath) {
			final File folder = new File(filePath);
			for (final File f : folder.listFiles()) {
			    if (f.getName().startsWith("Chat Templates")) {
			        f.delete();
			    }
			}
			selectWebElement(exporttoexcel.get(2));
			waitForJqueryLoad(driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Boolean Status=verifyExportPageFileDownload(filePath, "Chat Templates");
			return Status;
		}
		public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
			List<Map<String,String>> UI=getData(); 
			System.out.println(UI);
			System.out.println(maplist);
			if(UI.equals(maplist))
			return true;
			else
			return false;
		}
		
		public void SortByAscending() {
			selectWebElement(name.get(2));
			selectWebElement(exporttoexcel.get(2));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void SortByDescending() {
			selectWebElement(name.get(2));
			selectWebElement(name.get(2));
			selectWebElement(exporttoexcel.get(2));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void SortByGroupAscending() {
			selectWebElement(name.get(1));
			selectWebElement(exporttoexcel.get(1));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void SortByDepartmentDescending() {
			selectWebElement(name.get(0));
			selectWebElement(name.get(0));
			selectWebElement(exporttoexcel.get(0));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void SortByDepartmentAscending() {
			selectWebElement(name.get(0));
			selectWebElement(exporttoexcel.get(0));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void SortByGroupDescending() {
			selectWebElement(name.get(1));
			selectWebElement(name.get(1));
			selectWebElement(exporttoexcel.get(1));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void dragDepartmentColumntoGroup(String columnname) {
	        List<WebElement> rows = grid.findElements(By.tagName("tr"));
	        List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("th"));
	        for (WebElement ele : columnHeaders) {
	            if (ele.getText().equals(columnname)) {
	                Actions builder = new Actions(driver);
	                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(droptarget.get(0)).release(droptarget.get(0)).build();
	                dragAndDrop.perform();
	            }
	        }
	    }
		
		public void dragGroupsColumntoGroup(String columnname) {
	        List<WebElement> rows = grid1.findElements(By.tagName("tr"));
	        List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("th"));
	        for (WebElement ele : columnHeaders) {
	            if (ele.getText().equals(columnname)) {
	                Actions builder = new Actions(driver);
	                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(droptarget.get(1)).release(droptarget.get(1)).build();
	                dragAndDrop.perform();
	            }
	        }
	    }
		
		public void dragColumntoGroup(String columnname) {
	        List<WebElement> rows = grid2.findElements(By.tagName("tr"));
	        List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("th"));
	        for (WebElement ele : columnHeaders) {
	            if (ele.getText().equals(columnname)) {
	                Actions builder = new Actions(driver);
	                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(droptarget.get(2)).release(droptarget.get(2)).build();
	                dragAndDrop.perform();
	            }
	        }
	    }

	    public boolean verifyDragColumntoGroup(String colname, int i) {

	        return (droptarget.get(i).getText().equals(colname));
	    }
		
		
		 public boolean verifyArrowMoveForPreviousAndNextPage(int i){
		        boolean status=false;
		        if(!nextPageIcon.get(i).getAttribute("class").contains("k-state-disabled")){
		        int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
		        selectWebElement(nextPageIcon.get(i));
		        int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
		        selectWebElement(previousPageIcon.get(i));
		        int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
		        if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
		        }else{
		            System.out.println("previous and next page icon disabled");status=true;
		        }
		        return status;
		    }
		    public boolean verifyArrowMoveForFirstAndLastPage(int i){
		        boolean status=false;
		        if(!lastPageIcon.get(i).getAttribute("class").contains("k-state-disabled")){
		            int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
		            selectWebElement(lastPageIcon.get(i));
		            int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
		            selectWebElement(firstPageIcon.get(i));
		            int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(i)));
		            if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
		        }else{
		            System.out.println("previous and next page icon disabled");status=true;
		        }
		        return status;
		    }
		    public boolean verifyNumberOfItemsPerPage(int z) {
		        boolean status = false;
		        try {
		          //  if (norecords.size() <= 0) {
		                int item = Integer.valueOf(items.get(z).getText().split("of ")[1].split(" items")[0]);
		                selectWebElement(pagerDropdown.get(z));
		                Thread.sleep(1500);
		                for (int i = 0; i < pageSizeListBox.size(); i++) {
		                    if(Integer.valueOf(pageSizeListBox.get(i).getText())>item){continue;}
		                    selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
		                    waitForJqueryLoad(driver);
		                    int totalItems = Integer.valueOf(items.get(z).getText().split("of ")[1].split(" items")[0]);
		                    int pagersize = Integer.valueOf(pagerSize.get(z).getText());
		                    int pages = (totalItems % pagersize == 0) ? item / pagersize : item / pagersize+1;
		                    int totalRows=(gridContent.findElements(By.tagName("tr")).size());
		                    selectWebElement(lastPageIcon.get(z));
		                    waitForJqueryLoad(driver);
		                    int lastPageNumber = Integer.valueOf(pageNumber.get(z).getText());
		                    if (item == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
		                        status = true;
		                    } else {System.out.println(items+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
		                        status = false;
		                        break;
		                    }selectWebElement(pagerDropdown.get(z));Thread.sleep(1500);
		                }
		           // }
		        } catch (Exception e) {
		            e.printStackTrace();
		        } return status;
		    }
		    public boolean verifyTotalNumberOfItemsPerPageDetails(int z){
		        String item = items.get(z).getText();
		        return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
		    }
			public void editChatDepartmentWithoutModifyReason(ChatTemplateDetails details) throws Exception {
				searchDepartmentRecord(details.getDepartmentName());
		        selectWebElement(editBtn);
		        waitForJqueryLoad(driver);
		        try {
		            Thread.sleep(5000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        selectWebElement(enableDropdown);
		        selectDropdownFromVisibleText(enableListbox,details.getUpdatedDeptEnabled());
		        selectWebElement(saveBtn);				
			}
			
			public void clickOnEditButton() {
		        selectWebElement(editBtn);				
			}
			public void editChatTemplateWithoutModifyReason(ChatTemplateDetails details) throws Exception {
				searchChatTemplatesRecord(details.getName());
		        selectWebElement(editBtn);
		        waitForJqueryLoad(driver);
		        try {
		            Thread.sleep(5000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        selectWebElement(enableDropdown);
		        selectDropdownFromVisibleText(enableListbox,details.getUpdatedEnabled());
		        selectWebElement(saveBtn);				
			}
			public void editChatTemplateGroupWithoutModifyReason(ChatTemplateDetails details) throws Exception {
				searchGroupRecord(details.getGroupName());
		        selectWebElement(editBtn);
		        waitForJqueryLoad(driver);
		        try {
		            Thread.sleep(5000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        selectWebElement(enableDropdown);
		        selectDropdownFromVisibleText(enableListbox,details.getUpdatedEnabled());
		        selectWebElement(saveBtn);					
			}
			public boolean VerifyGroupDropdown(ChatTemplateDetails details) throws Exception {
				Boolean Status=false;
				waitForJqueryLoad(driver);
		        waitUntilWebElementIsVisible(popupContent);
		        try {
		            Thread.sleep(5000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        selectWebElement(departmentNameDropdown);
		        selectDropdownFromVisibleText(departmentNameListbox,details.getDepartmentName());
		        selectWebElement(groupNameDropdown);
				for(WebElement ele : groupNameListbox) {
					if(ele.getText().equals(details.getGroupName())) {
						Status=true;
						break;
					}
				}
				return Status;
			}
			public boolean clearAll(ChatTemplateDetails chatTemplateDetails) throws Exception {
				selectWebElement(searchLink);
		        selectWebElement(selectSearchColumn.get(0));
		        selectDropdownFromVisibleText(columnNameList,"Name");
		        selectWebElement(selectSearchColumn.get(1));
		        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		        enterValueToTxtField(searchText,chatTemplateDetails.getName());
		        selectWebElement(clearall);
				if(searchText.isEnabled())
		        	return true;
		        else
				return false;
			}
			public boolean GroupclearAll(ChatTemplateDetails chatTemplateDetails) throws Exception {
				selectWebElement(searchLink);
		        selectWebElement(selectSearchColumn.get(0));
		        selectDropdownFromVisibleText(columnNameList,"Name");
		        selectWebElement(selectSearchColumn.get(1));
		        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		        enterValueToTxtField(searchText,chatTemplateDetails.getGroupName());
		        selectWebElement(clearall);
				if(searchText.isEnabled())
		        	return true;
		        else
				return false;
			}
			public boolean departmentclearAll(ChatTemplateDetails chatTemplateDetails) throws Exception {
				selectWebElement(searchLink);
		        selectWebElement(selectSearchColumn.get(0));
		        selectDropdownFromVisibleText(columnNameList,"Name");
		        selectWebElement(selectSearchColumn.get(1));
		        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		        enterValueToTxtField(searchText,chatTemplateDetails.getDepartmentName());
		        selectWebElement(clearall);
				if(searchText.isEnabled())
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
			public void searchwithoutextsearch() {
				selectWebElement(searchLink);
		        selectWebElement(selectSearchColumn.get(0));
		        selectDropdownFromVisibleText(columnNameList,"Name");
		        selectWebElement(selectSearchColumn.get(1));
		        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
		        selectWebElement(searchBtn);
				selectWebElement(searchClose);
			}
			public boolean verifyGroupclose() {
				selectWebElement(searchClose);
				if(gridContent2.isDisplayed())
					return true;
				else
				return false;
			}
			public boolean verifyDepartmentclose() {
				selectWebElement(searchClose);
				if(gridContent1.isDisplayed())
					return true;
				else
				return false;
			}
			public void clickOnDeleteButton() {
				selectWebElement(deleteBtn);
			}
			public void clickOnDeleteCancelBtn() {
				selectWebElement(noBtn);
			}
			public boolean verifyDeleteContainer() {
				try {
		            Thread.sleep(3000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        return isElementExist(deleteContainer);
			}
			public void deleteChatTempalateWithoutDeleteReasonRecord(ChatTemplateDetails details) throws Exception {
				searchChatTemplatesRecord(details.getName());
		        btnClick(deleteBtn);
		        selectWebElement(deleteReasonTextBox);
		        selectWebElement(yesBtn);
			}
			public void deleteGroupsWithoutDeleteReasonRecord(ChatTemplateDetails details) throws Exception {
				searchGroupRecord(details.getGroupName());
		        btnClick(deleteBtn);
		        selectWebElement(deleteReasonTextBox);
		        selectWebElement(yesBtn);
				
			}
			public void deleteDepartmentWithoutDeleteReasonRecord(ChatTemplateDetails details) throws Exception {
				searchDepartmentRecord(details.getDepartmentName());
		        btnClick(deleteBtn);
		        selectWebElement(deleteReasonTextBox);
		        selectWebElement(yesBtn);				
			}
			public boolean verifyinvalidDepartmentsearchwithwrongdata(ChatTemplateDetails chatTemplateDetails) throws Exception {
				searchDepartmentRecord(chatTemplateDetails.getDepartmentName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			public boolean verifyDepartmentclearsearch() {
				selectWebElement(clearsearch);
				if(gridContent1.isDisplayed())
					return true;
				else
				return false;
			}
			public boolean verifyGroupclearsearch() {
				selectWebElement(clearsearch);
				if(gridContent2.isDisplayed())
					return true;
				else
				return false;
			}
			public boolean verifyinvalidGroupsearchwithwrongdata(ChatTemplateDetails chatTemplateDetails) throws Exception {
				searchGroupRecord(chatTemplateDetails.getGroupName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(norecords.isDisplayed())
					return true; 
					else
						return false;
			}
			public boolean verifyinvalidsearchwithwrongdata(ChatTemplateDetails chatTemplateDetails) throws Exception {
				searchChatTemplatesRecord(chatTemplateDetails.getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(norecords.isDisplayed())
					return true; 
					else
						return false;
			}
			public boolean verifyDepartmentDatabase(String query) {
				List<Map<String,String>> database=database(query);
				System.out.println(database);
				List<Map<String,String>> UI=getDepartmenttable(); 
				System.out.println(UI);
				if(UI.equals(database))
					return true;
				else
					return false;
			}
			private List<Map<String, String>> getDepartmenttable() {
				int item=Integer.valueOf(items.get(0).getText().split("of ")[1].split(" items")[0]);
		        int pagersize=Integer.valueOf(pagerSize.get(0).getText());
		        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
				List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
				for(int k=0;k<=pages;k++){

				waitUntilWebElementIsVisible(grid);
				List<WebElement> rows=grid.findElements(By.tagName("tr"));
				List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
				for(int i=1;i<rows.size();i++) {
					Map<String,String> map = new HashMap<String,String>();
					List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
					String col=null;
					for(int j=1;j<headers.size();j++){
						scrollToElement(headers.get(j));
						if(headers.get(j).getText().equals("Last Changed On")){
							col=cols.get(j).getText().substring(11);
						}
						else if(headers.get(j).getText().equals("Enabled")) {
							if(cols.get(j).getText().equals("Yes"))
								col="1";
							else
								col="0";
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
					nextPageIcon.get(0).click();
					waitForJqueryLoad(driver);}
				}
					return arr;
			}
			public boolean verifyGroupDatabase(String query) {
				List<Map<String,String>> database=database(query);
				System.out.println(database);
				List<Map<String,String>> UI=getGrouptable(); 
				System.out.println(UI);
				if(UI.equals(database))
					return true;
				else
					return false;
			}
			private List<Map<String, String>> getGrouptable() {
				int item=Integer.valueOf(items.get(1).getText().split("of ")[1].split(" items")[0]);
		        int pagersize=Integer.valueOf(pagerSize.get(1).getText());
		        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
				List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
				for(int k=0;k<=pages;k++){

				waitUntilWebElementIsVisible(grid1);
				List<WebElement> rows=grid1.findElements(By.tagName("tr"));
				List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
				for(int i=1;i<rows.size();i++) {
					Map<String,String> map = new HashMap<String,String>();
					List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
					String col=null;
					for(int j=1;j<headers.size();j++){
						scrollToElement(headers.get(j));
						if(headers.get(j).getText().equals("Last Changed On")){
							col=cols.get(j).getText().substring(11);
							}
						else if(headers.get(j).getText().equals("Enabled")) {
							if(cols.get(j).getText().equals("Yes"))
								col="1";
							else
								col="0";
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
					nextPageIcon.get(1).click();
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
			
			public List<Map<String, String>> gettable() {
				int item=Integer.valueOf(items.get(2).getText().split("of ")[1].split(" items")[0]);
		        int pagersize=Integer.valueOf(pagerSize.get(2).getText());
		        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
				List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
				for(int k=0;k<=pages;k++){

				waitUntilWebElementIsVisible(grid2);
				List<WebElement> rows=grid2.findElements(By.tagName("tr"));
				List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
				for(int i=1;i<rows.size();i++) {
					Map<String,String> map = new HashMap<String,String>();
					List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
					String col=null;
					for(int j=1;j<headers.size();j++){
						scrollToElement(headers.get(j));
						if(headers.get(j).getText().equals("Last Changed On")){
							col=cols.get(j).getText().substring(11);
							}
						else if(headers.get(j).getText().equals("Enabled")) {
							if(cols.get(j).getText().equals("Yes"))
								col="1";
							else
								col="0";
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
					nextPageIcon.get(2).click();
					waitForJqueryLoad(driver);}
				}
					return arr;
			}
			
		    
}
