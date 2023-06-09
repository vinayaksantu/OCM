package com.tetherfi.pages;

import com.tetherfi.model.ivr.FaxApplicationFormDetails;
import com.tetherfi.model.ivr.IvrConfigDetails;
import com.tetherfi.model.ivr.VipListManagementDetails;
import com.tetherfi.model.sms.SmsResponseTemplateDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IvrConfigPage extends BasePage {

    public IvrConfigPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".fa-search")
    private List<WebElement> searchBtn;

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

    @FindBy(css="#toast-container .toast-message")
    private WebElement successmsg;

    @FindBy(css=".ibox-title h5")
    private WebElement ivrConfig;
    
    @FindBy(xpath="//i[@class='fas fa-cog']")
	private WebElement IvrCImg;

    @FindBy(css="#create")
    private WebElement addNewIVRConfigRcrdBtn;

    @FindBy(css="#drillGrid .k-grid-content")
    private WebElement gridContent;

    @FindBy(css="span[aria-owns='Parameter_listbox']")
    private WebElement parameterDropdown;

    @FindBy(css="#Parameter-list .k-list-optionlabel")
    private List<WebElement> selectParameter;

    @FindBy(css="ul[id='Parameter_listbox'] li")
    private List<WebElement> parameterListBox;

    @FindBy(css="span[aria-owns='Value_listbox']")
    private WebElement valueDropdown;

    @FindBy(css="ul[id='Value_listbox'] li")
    private List<WebElement> selectValue;

    @FindBy(css="ul[id='Value_listbox'] li")
    private List<WebElement> valueListBox;

    @FindBy(css=".k-grid-update")
    private WebElement saveButton;

    @FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;

    @FindBy(id = "noButton")
    private WebElement deleteNoBtn;

    @FindBy(css = "#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;
    
    @FindBy(xpath="//div[text()='Please Provide Parameter,Value']")
    private List<WebElement> errorMsg1;
    
    @FindBy(xpath="//div[@class='toast-message']")
    private List<WebElement> errorMsg2;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
    
    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;
    
    @FindBy(id = "ModifyReason")
    private WebElement ModifyReasonTextBox;

    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement editFormContainer;

    @FindBy(css="div[style='display: block; z-index: 10002; opacity: 0.5;']")
    private WebElement deleteContainer;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
    
    @FindBy(xpath="//span[@class='k-input']")
	private List<WebElement> pagerSize;
	    
	@FindBy(css="a[aria-label='Go to the next page']")
	private List<WebElement> nextPageIcon;
	    
	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;
	    
	@FindBy(css="a[aria-label='Go to the first page']")
	private List<WebElement> firstPageIcon;
	    
	@FindBy(css="a[aria-label='Go to the previous page']")
	private WebElement previousPageIcon;
	
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
			
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
			 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
	    
	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
	private WebElement groupbyValue;
	
	@FindBy(xpath="//a[text()='Value']")
	private WebElement Value;
	    
	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;
	    
	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;
	    
	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;
	    
	@FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
	private WebElement clearsearch;
	
	@FindBy(css=".k-pager-numbers .k-state-selected")
	private List<WebElement> pageNumber;
	
	@FindBy(xpath="//a[text()='Parameter']")
	private List<WebElement> parameter;
			    
	@FindBy(css="a[aria-label='Go to the last page']")
	private List<WebElement> lastPageIcon;
		    
	@FindBy(css=".k-pager-sizes .k-icon")
	private List<WebElement>pagerDropdown;
		    
	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;
		    
	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;
		    
	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;
	
	@FindBy(xpath="//li[@id='d21704e7-d980-41b9-b7f1-847bce4d5e7c_mn_active']")
	private List<WebElement> headersColumns1; 
			    
	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;		
		    
	@FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
	private List<WebElement> tablerecord;
		   
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private List<WebElement> items;
		    
	@FindBy(id="navbarheader")
	private WebElement header;
		    
	@FindBy(xpath="//tbody/tr/td[1]")
	private WebElement rowdata;
		    
	@FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
	private WebElement coloumnarrow;
		    
	@FindBy(id="tGrid")
	private WebElement auditGridContent;
		    
	@FindBy(xpath="//a[text()='Wav File']")
	private WebElement WavFile;

	@FindBy(css="#checkerGrid th[data-role='columnsorter']")
	private List<WebElement> approvedDataTableHeaders;
	
	@FindBy(css="ul[class='k-tabstrip-items k-reset'] li")
    private WebElement ivrConfigTab1;
	
	@FindBy(css="ul[class='k-tabstrip-items k-reset'] li")
    private List<WebElement> ivrConfigTab;
	
	@FindBy(css="#tGrid th[data-role='columnsorter']")
	private List<WebElement> auditTrailTableHeaders;
	
	@FindBy(xpath="//button[@id='makeChanges']")
	private WebElement makeIVRConfigChanges;
	
	@FindBy(xpath="//button[@id='create']")
	private WebElement addNewRecordBtn;
	
	@FindBy(id="goToAuditTrail")
	private WebElement goBackBtn;
	
	@FindBy(xpath="//*[@id=\"export\"]/button")
	private WebElement exportToExcelBtn;
	
	@FindBy(css="#drillGrid th[data-role='columnsorter']")
    private List<WebElement> makerTableHeaders;
	
	 @FindBy(id="undoChanges")
	 private WebElement revertBtn;
	 
	 @FindBy(id="undoChangesMakerComments")
	 private WebElement revertMakerComments;
	 
	 @FindBy(id="submitUndoChangesMakerComment")
	 private WebElement revertSubmitMakerComments;
	
	 @FindBy(id="sendForApproval")
	 private WebElement sendForApprovalBtn;
	 
	 @FindBy(id="MakerComments")
	 private WebElement makerComments;
	 
	 @FindBy(id="submitMakerComment")
	 private WebElement submitMakerComments;
	 
	 @FindBy(id="Reject")
	 private WebElement rejectBtn;
	 
	 @FindBy(id="CheckerReason")
	 private WebElement checkerReason;
	 
	 @FindBy(id="approveButton")
	 private WebElement approveYesBtn;
	 
	 @FindBy(id="Approve")
	 private WebElement approveBtn;
	 
	 @FindBy(css="#gridDiv .search-link")
	 private WebElement gridsearchLink;
	 
	 @FindBy(css="#gridDiv2 .search-link")
	  private WebElement searchLink;
	 
	 @FindBy(css=".modal-body .form-inline .form-group .k-select")
	 private List<WebElement> selectSearchColumn;
	 
	 @FindBy(css="ul[id='1001sCriteria_listbox'] li")
	 private List<WebElement> searchTypeList;
	 
	 @FindBy(id="1001sTextToSearch")
	 private WebElement searchText;
	 
	 @FindBy(css="#tcheckerGrid .k-grid-content")
	 private WebElement approvedgridcontent;
	 
	 @FindBy(id="noButton")
	 private WebElement noBtn;
	 
	 @FindBy(css="#drillGrid tbody tr td")
	 private List<WebElement> editrowdata;
	 
	 @FindBy(id="yesButton")
	 private WebElement yesBtn;
	 
	 @FindBy(id = "drillGrid")
	 private WebElement grid;
	 
	 @FindBy(css = "#drillGrid .k-grouping-header")
	 private WebElement dragColumnDestination;
	 
	 @FindBy(xpath="//a[text()='Value']")
	 private List<WebElement> value;
	 
	 @FindBy(id="tdrillgrid")
	 private WebElement tgrid;
	 
    public boolean isIvrConfigPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return ivrConfig.isEnabled();
    }
    public void addNewIvrConfigRecord(IvrConfigDetails details) {
    	selectWebElement(ivrConfigTab.get(1));
    	selectWebElement(makeIVRConfigChanges); 
        selectWebElement(addNewRecordBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(parameterDropdown);
        selectDropdownFromVisibleText(parameterListBox,details.getParameter());
        parameterDropdown.sendKeys(Keys.TAB);
        selectWebElement(valueDropdown);
        selectDropdownFromVisibleText(valueListBox,details.getValue());
        valueDropdown.sendKeys(Keys.TAB);
        selectWebElement(saveButton);
        
    }
    
    public void searchIvrConfigRecord(String Parameter) throws Exception {
        selectWebElement(searchLink);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Parameter");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,Parameter);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
      
    public void editIvrConfigRecord1(IvrConfigDetails details) throws Exception {
    	selectWebElement(ivrConfigTab.get(1));
    	Thread.sleep(1000);
    	selectWebElement(makeIVRConfigChanges);
    	Thread.sleep(1000);
        searchIvrConfigRecord(details.getParameter());
        selectWebElement(editButton);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(parameterDropdown);
        if(details.getParameter().equals("Select Parameter"))
        {selectDropdownFromVisibleText(selectParameter,details.getParameter());}
        else{selectDropdownFromVisibleText(parameterListBox,details.getParameter());}
        parameterDropdown.sendKeys(Keys.TAB);
        selectWebElement(valueDropdown);
        if(details.getValue().equals("Select Value"))
        {selectDropdownFromVisibleText(selectValue,details.getValue());}
        else{selectDropdownFromVisibleText(valueListBox,details.getUpdatedValue());}
        valueDropdown.sendKeys(Keys.TAB);
        selectWebElement(ModifyReasonTextBox);
        enterValueToTxtField(ModifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveButton);
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    
    public void editIvrConfigRecord(IvrConfigDetails details) throws Exception {
    	selectWebElement(ivrConfigTab.get(1));
    	Thread.sleep(1000);
    	selectWebElement(makeIVRConfigChanges);
    	Thread.sleep(1000);
    	searchIvrConfigRecord(details.getParameter());
    	selectWebElement(editButton);
    	waitForJqueryLoad(driver);		
		Thread.sleep(1000);
		selectWebElement(valueDropdown);
		selectDropdownFromVisibleText(valueListBox,details.getUpdatedValue());
		selectWebElement(ModifyReasonTextBox);
        enterValueToTxtFieldWithoutClear(ModifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveButton);
    }
    
    
   
    public void deleteIvrConfigRecord(IvrConfigDetails details) throws Exception {
    	selectWebElement(ivrConfigTab.get(1));
    	Thread.sleep(1000);
    	selectWebElement(makeIVRConfigChanges);
    	Thread.sleep(1000);
        searchIvrConfigRecord(details.getParameter());
        selectWebElement(deleteButton);
        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
        selectWebElement(deleteYesBtn);
        try {
            selectWebElement(deleteNoBtn);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    
    public String verifySuccessMessage(){
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
       else{waitUntilWebElementIsVisible(successmsg);return successmsg.getText();}
    }

    public void clickOnCancelBtn(){
        if(isElementExist(cancelBtn)){selectWebElement(cancelBtn);}
    }
    public void clickOnAddRecord(){
        selectWebElement(addNewIVRConfigRcrdBtn);
    }
    public boolean verifyEditFormContainer(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isElementExist(editFormContainer);
    }
    public boolean verifyDeleteContainer(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isElementExist(deleteContainer);
    }
    public void clickOnEditButton(){
        selectWebElement(editButton);
    }
    public void clickOnDeleteButton(){
        selectWebElement(deleteButton);
    }
    public void clickOnDeleteCancelBtn(){
        selectWebElement(deleteNoBtn);
    }
    public boolean isAddBtnDisplayed() {
    	return addNewIVRConfigRcrdBtn.isDisplayed() && addNewIVRConfigRcrdBtn.isEnabled();
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
    
    public boolean verifylogo() {
		if(isElementExist(IvrCImg))
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

	public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().startsWith("IVR Config")) {
		        f.delete();
		    }
		}
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "IVR Config");
		return Status;
	}
	
	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) throws Exception {
		List<Map<String,String>> UI=getdata(); 
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
		return true;
		else
		return false;
	}
		
	private List<Map<String,String>> getdata() throws Exception{
		int item=Integer.valueOf(items.get(2).getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.get(2).getText());
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
				scrollToElement(headers.get(j));
				Thread.sleep(1000);
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
		int item=Integer.valueOf(items.get(0).getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.get(0).getText());
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
				scrollToElement(headers.get(j));
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
			nextPageIcon.get(0).click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}
	
	public boolean ExporttoExcelWithoutData(IvrConfigDetails ivrConfigDetails) throws Exception {
		searchIvrConfigRecord(ivrConfigDetails.getParameter());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}
	
	public void SortByAscending() {
		selectWebElement(value.get(1));
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(parameter.get(1));
		selectWebElement(parameter.get(1));
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifyArrowMoveForPreviousAndNextPage(int i){
        boolean status=false;
        if(!nextPageIcon.get(i).getAttribute("class").contains("k-state-disabled")){
        int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(2)));
        selectWebElement(nextPageIcon.get(i));
        int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(2)));
        selectWebElement(previousPageIcon);
        int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(2)));
        if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
        }else{
            System.out.println("previous and next page icon disabled");status=true;
        }
        return status;
	}
	
	public boolean verifyArrowMoveForFirstAndLastPage(int i){
        boolean status=false;
        if(!lastPageIcon.get(i).getAttribute("class").contains("k-state-disabled")){
            int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(2)));
            selectWebElement(lastPageIcon.get(i));
            int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(2)));
            selectWebElement(firstPageIcon.get(i));
            int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber.get(2)));
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
    
    public boolean groupby() {
		DragandDrop(Value,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyValue.getText()))
		{return true;}
		else
			return false;		
	}
	public boolean clearAll(IvrConfigDetails details) throws Exception {
		selectWebElement(gridsearchLink);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Parameter");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getParameter());
        selectWebElement(clearall);
		if(searchText.isEnabled())
        	return true;
        else
		return false;
	}
	public boolean verifyclose() {
		selectWebElement(searchClose);
		if(approvedgridcontent.isDisplayed())
			return true;
		else
		return false;
	}
	public void searchwithoutextsearch() {
		selectWebElement(gridsearchLink);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Parameter");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        selectWebElement(searchSearchBtn);	
		selectWebElement(searchClose);		
	}
	
	public boolean verifyinvalidsearchwithwrongdata(IvrConfigDetails ivrConfigDetails) throws Exception {
		searchIvrConfigRecord(ivrConfigDetails.getParameter());
		if(norecords.isDisplayed())
			return true; 
			else
				return false;
	}
	public boolean verifyclearsearch() {
		selectWebElement(clearsearch);
		if(approvedgridcontent.isDisplayed())
			return true;
		else
		return false;
	}
	
	 private ArrayList getHeadersfromTable(List<WebElement> e){
	        ArrayList header=new ArrayList();
	        for(int j=0;j<e.size();j++){
	            scrollToElement(e.get(j));
	            if(!e.get(j).getText().equals("")){header.add(e.get(j).getText());}
	        }
	        return header;
	        }
	
	public boolean verifyApprovedDataTableHeaders() {
		ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Parameter","Value"));
		ArrayList Actual=getHeadersfromTable(approvedDataTableHeaders);
		System.out.println(Actual);
		Collections.sort(Expected);
		Collections.sort(Actual);
		return Actual.equals(Expected);		
	}
	public void selectIvrConfigAuditTrailTab() {
		selectWebElement(ivrConfigTab1);
		selectWebElement(ivrConfigTab.get(1));
		try {
			Thread.sleep(5000);			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}						
	}
	
	public boolean verifyAuditTrailDataTableHeaders() {
	ArrayList<String> Expected=new ArrayList<String>(Arrays.asList(" ","Request Id","Transaction","Function","Status","User Id","Submission DateTime","Maker Comments","Old Values","New Values","Reviewed By","Review DateTime","Checker Comments"));
	ArrayList Actual=getHeadersfromTable(auditTrailTableHeaders);
	System.out.println(Actual);
	System.out.println(Expected);
	Collections.sort(Actual);
	Collections.sort(Expected);	
	return Actual.equals(Expected);
	}
	public void selectMakeIvrConfigChanges() {
			selectWebElement(makeIVRConfigChanges);
			try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
	public boolean verifyAddNewIvrConfigRecordButton() {
		return addNewRecordBtn.isEnabled();
	}
		
	public boolean verifyGoBackButton() {
		return goBackBtn.isDisplayed();
	}
	
	public boolean verifyExportToExcelButton() {
		return exportToExcelBtn.isDisplayed();
	}
	public boolean verifyMakerDataTableHeaders() {
	ArrayList<String> Expected=new ArrayList<String>(Arrays.asList("Parameter","Value"));
	ArrayList Actual=getHeadersfromTable(makerTableHeaders);
	System.out.println(Actual);
	System.out.println(Expected);
	Collections.sort(Actual);
	Collections.sort(Expected);		
    return Actual.equals(Expected);
	}
	
	public boolean getErrorMsg() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0)
		return false;
		else
			return true;
	}
	public boolean VerifyApprovedSectionData(IvrConfigDetails details) throws Exception {
		searchIvrConfigRecordApprovedData(details.getParameter());
		if(norecords.isDisplayed())
			return true; 
			else
				return false;
	}
		
	public boolean addCancelButton(IvrConfigDetails ivrConfigDetails) {
	selectWebElement(ivrConfigTab.get(1));
	selectWebElement(makeIVRConfigChanges);
	String actualitems=items.get(2).getText();
	selectWebElement(addNewRecordBtn);
	waitForJqueryLoad(driver);
    selectWebElement(cancelBtn);
    if(actualitems.equals(items.get(2).getText()))
    	return true;
    else
	return false;	
}
	public void addRecordWithoutParameter(IvrConfigDetails details) {
		selectWebElement(ivrConfigTab.get(1));
		selectWebElement(makeIVRConfigChanges);
		selectWebElement(addNewRecordBtn);
		 try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		waitForJqueryLoad(driver);
		selectWebElement(valueDropdown);
		selectDropdownFromVisibleText(selectValue,details.getValue());
		selectWebElement(saveButton);
		selectWebElement(cancelBtn);		
	}
	public void addRecordWithoutValue(IvrConfigDetails details) {
		selectWebElement(ivrConfigTab.get(1));
		selectWebElement(makeIVRConfigChanges);
		selectWebElement(addNewRecordBtn);
		 try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		waitForJqueryLoad(driver);
		selectWebElement(parameterDropdown);
		selectDropdownFromVisibleText(selectParameter,details.getParameter());
		selectWebElement(saveButton);
		selectWebElement(cancelBtn);	
	}
	public String getSuccessMessage() {
//		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}	
	}
	public void selectRecord() {
		Map<String,String> map = new HashMap<>();
		waitUntilWebElementIsVisible(auditGridContent);
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
		selectWebElement(cols.get(0).findElement(By.id("isEnabled")));	
	}
	
	public void Revert(String comments) throws Exception {
		selectWebElement(revertBtn);
		enterValueToTxtField(revertMakerComments,comments);
		selectWebElement(revertSubmitMakerComments);				
	}
	
	public boolean verifyStatus(String status) {
		try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        return firstRowData.get("Status").equals(status);
	}
	
	private Map<String, String> getFirstRowDatafromTable() {
		Map<String,String> map = new HashMap<>();
        waitUntilWebElementIsVisible(auditGridContent);
        List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
        for(int j=0;j<headers.size();j++){
            scrollToElement(headers.get(j));
            for(int i=0;i<3;i++) {													
                try{map.put(headers.get(j).getText(), cols.get(j).getText());break;}catch (Exception e){e.printStackTrace();}
            }
        }
        return map;
	}
	
	public void sendForAprroval(String comments) throws Exception {
		selectWebElement(sendForApprovalBtn);
		enterValueToTxtField(makerComments, comments);
		selectWebElement(submitMakerComments);		
	}
		
	public void clickOnReject(String comment) throws Exception {
		 selectWebElement(ivrConfigTab.get(1));
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectRecord();
	        clickOn(rejectBtn);
	        selectWebElement(checkerReason);
	        enterValueToTxtField(checkerReason,comment);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        clickOn(approveYesBtn);				
	}
	
	public boolean verifyMessage() {
        return(getSuccessMessage().contains("Record approved successfully. Request ID :"));
	}	
	
	public boolean verifyReviewAuditTrail(String status, String comment) {
		 try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        boolean stat=false;
	        Map<String,String> firstRowData=getFirstRowDatafromTable();
	        if(firstRowData.get("Status").equals(status)){
	            if(firstRowData.get("Checker Comments").equals(comment)){
	            	stat=true;
	            	}
	            else{System.out.println("Data mismatch:"+firstRowData.get("Review Comments")+"\t"+comment);}
	        }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+status);}
	        return stat;
	}
	
	public void clickOnApprove(String comment) throws Exception {
		selectWebElement(ivrConfigTab.get(1));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectRecord();
        clickOn(approveBtn);
        selectWebElement(checkerReason);
        enterValueToTxtField(checkerReason,comment);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOn(approveYesBtn);		
	}

	public boolean verifyApprovedSectionDataafterapproval(IvrConfigDetails IvrConfigDetails) throws Exception {
		searchIvrConfigRecordApprovedData(IvrConfigDetails.getParameter());
		if(rowdata.getText().equals(IvrConfigDetails.getParameter()))
			return true;
		else
		return false;
	}
	
	private void searchIvrConfigRecordApprovedData(String parameter) throws Exception {
		selectWebElement(gridsearchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Parameter");
        Thread.sleep(1000);
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText,parameter);
        selectInvisibleWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(approvedgridcontent);		
	}
	

	public boolean EditCancel(IvrConfigDetails details) throws Exception {
		selectWebElement(ivrConfigTab.get(1));
		Thread.sleep(1000);
		selectWebElement(makeIVRConfigChanges);
		Thread.sleep(1000);
		searchIvrConfigRecord(details.getParameter());
		selectWebElement(editButton);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn);
		if(editrowdata.get(1).getText().equals(details.getParameter()))	
		return true;
		else
			return false;
	}
	
	public boolean DeleteCancel(IvrConfigDetails details) throws Exception {
	selectWebElement(ivrConfigTab.get(1));
	Thread.sleep(1000);
	selectWebElement(makeIVRConfigChanges);
	Thread.sleep(1000);
	searchIvrConfigRecord(details.getParameter());
	selectWebElement(deleteButton);
	waitForJqueryLoad(driver);
	selectWebElement(noBtn);
	if(editrowdata.get(1).getText().equals(details.getParameter()))	
		return true;
		else
			return false;	
	}
	
	public void DeleteRecordWithoutModifyReason(IvrConfigDetails details) throws Exception {
		selectWebElement(ivrConfigTab.get(1));
		Thread.sleep(1000);
		selectWebElement(makeIVRConfigChanges);
		Thread.sleep(1000);	
		searchIvrConfigRecord(details.getParameter());
		Thread.sleep(1000);
		selectWebElement(deleteButton);
		Thread.sleep(1000);
		selectWebElement(deleteReasonTextBox);
		selectWebElement(yesBtn);
		selectWebElement(noBtn);
	}
	
	private Map<String, String> getFirstRowDatafromPreviewPopup() {
		Map<String,String> map = new HashMap<>();
        waitUntilWebElementIsVisible(auditGridContent);
        List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
        List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
        List<WebElement> preview= cols.get(1).findElements(By.tagName("a"));
        preview.get(0).click();
        waitUntilWebElementIsVisible(tgrid);
        List<WebElement> gridrows=tgrid.findElements(By.tagName("tr"));
        List<WebElement> gridheaders = gridrows.get(0).findElements(By.tagName("th"));
        List<WebElement> gridcols=gridrows.get(1).findElements(By.tagName("td"));     
        for(int j=0;j<gridheaders.size();j++){
            scrollToElement(gridheaders.get(j));
            for(int i=0;i<gridcols.size();i++) {
            	System.out.println(gridheaders.get(j).getText());
                try{
                	map.put(gridheaders.get(j).getText(), gridcols.get(j).getText());
                break;
                }
                catch (Exception e){e.printStackTrace();}
            }
        }
        return map;
	}

	public boolean verifyAuditTrail(IvrConfigDetails ivrConfigDetails, String Transaction, String Status) {
		boolean stat=false;
		Map<String,String> firstRowData=getFirstRowDatafromTable();
		Map<String,String> popupRowData=getFirstRowDatafromPreviewPopup();
		if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)) {
			if(firstRowData.get("Status").equalsIgnoreCase(Status)) {
				if(firstRowData.get("Function Name").equalsIgnoreCase("IVR Config")){
					if(Transaction.equals("MakerCreate")){
						Map<String,String> newvalues=new HashMap<>();
                        String[] d=popupRowData.get("New Values").split("\n");
                        for(String e:d){
                            String[]f=e.split(":",2);
                            if(f.length>1){newvalues.put(f[0],f[1]);}
                        }
                        if(verifyNewValues(ivrConfigDetails,newvalues)){
                            stat=true;}
                        else 
                        stat=false;
                   }
					else{System.out.println("Data mismatch");}                            
					}else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"IVR Config");}				
				}else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}		
			}else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}		
		return stat;
	}
	
	private boolean verifyNewValues(IvrConfigDetails details, Map<String, String> newvalues) {
		boolean status=false;
		if (newvalues.get("Parameter").equals(details.getParameter())){
			if (newvalues.get("Value").equals(details.getValue())){
				status=true;
			}
			else {System.out.println("Parameter Data Mismatch");}
		}
		else {System.out.println("Value Data Mismatch");}			
	return status;
	}
			
	public boolean verifyAuditTrailUpdate(IvrConfigDetails details, String Transaction,String Status) {
		boolean stat=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable();
		Map<String,String> popupRowData=getFirstRowDatafromPreviewPopup();


        if(firstRowData.get("Transaction").equalsIgnoreCase(Transaction)){
            if(firstRowData.get("Status").equalsIgnoreCase(Status)){
                if(firstRowData.get("Function Name").equalsIgnoreCase("IVR Config")){
                       if(Transaction.equals("MakerUpdate")){
                           Map<String,String> newvalues=new HashMap<>();
                            String[] d=popupRowData.get("New Values").split("\n");
                            for(String e:d){
                                String[]f=e.split(":",2);
                                if(f.length>1){newvalues.put(f[0],f[1]);}
                            }
                            if(verifyUpdatedNewValues(details,newvalues)){
                                stat=true;}
                            else 
                            stat=false;
                       }
                       else{System.out.println("Data mismatch");}
                }else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"IVR Config");}
            }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+Status);}
        }else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+Transaction);}
        return stat;
	}
	
	
	private boolean verifyUpdatedNewValues(IvrConfigDetails details, Map<String, String> newvalues) {
		boolean status=false;
		if (newvalues.get("Parameter").equals(details.getUpdatedParameter())){
			if (newvalues.get("Value").equals(details.getUpdatedValue())){
				status=true;
			}
			else {System.out.println("Parameter Data Mismatch");}
		}
		else {System.out.println("Value Data Mismatch");}			
	return status;		
	}
	
	public void EditRecordWithoutModifyReason(IvrConfigDetails details) throws Exception {
	 selectWebElement(ivrConfigTab.get(1));
	 Thread.sleep(1000);
	 selectWebElement(makeIVRConfigChanges);
	 Thread.sleep(1000);
//	 searchIvrConfigRecord(details.getParameter());
	 selectWebElement(editButton);
	 waitForJqueryLoad(driver);		
     Thread.sleep(1000);
     enterValueToTxtField(ModifyReasonTextBox, details.getModifyReason());	
     selectWebElement(saveButton);	
     selectWebElement(cancelBtn);
	}				

private Map<String, String> getSecondRowDatafromTable() {
		Map<String,String> map = new HashMap<>();
        waitUntilWebElementIsVisible(auditGridContent);
        List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        List<WebElement> cols=rows.get(2).findElements(By.tagName("td"));
        for(int j=0;j<headers.size();j++){
            scrollToElement(headers.get(j));
            for(int i=0;i<3;i++) {													
                try{map.put(headers.get(j).getText(), cols.get(j).getText());break;}catch (Exception e){e.printStackTrace();}
            }
        }
        return map;
	}
	
    public boolean verifyStatus1(String status) {
	try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    Map<String,String> secondRowData=getSecondRowDatafromTable();
    return secondRowData.get("Status").equals(status);
}	
    
    public void dragColumntoGroup(String columnname) {
        List<WebElement> rows = grid.findElements(By.tagName("tr"));
        List<WebElement> columnHeaders = rows.get(0).findElements(By.tagName("th"));
        for (WebElement ele : columnHeaders) {
            if (ele.getText().equals(columnname)) {
                Actions builder = new Actions(driver);
                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(dragColumnDestination).release(dragColumnDestination).build();
                dragAndDrop.perform();
            }
        }
    }

    public boolean verifyDragColumntoGroup(String colname) {

        return (dragColumnDestination.getText().equals(colname));
    }
    
    
    
}	









	
	

