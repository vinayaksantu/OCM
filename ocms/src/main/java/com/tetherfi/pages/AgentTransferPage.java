package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.ivr.AgentTransferDetails;
import com.tetherfi.model.ivr.HolidayListDetails;

public class AgentTransferPage extends BasePage{

	public AgentTransferPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(css=".ibox-title h5")
    private WebElement agentTransfer;
	
	@FindBy(css="#create")
	private WebElement addNewAgentTransferBtn;
	
	@FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;
	
	@FindBy(css = ".k-grid-edit")
    private WebElement editButton;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	@FindBy(xpath="//i[@class='fas fa-exchange']")
	private WebElement AtImg;
	
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
	
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
	 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(xpath="//span[@class='k-input']")
    private WebElement pagerSize;
    
    @FindBy(css="a[aria-label='Go to the next page']")
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
    
    @FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
    private List<WebElement> tablerecord;
   
    @FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
    
    @FindBy(id="navbarheader")
	private WebElement header;
    
    @FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
    private WebElement coloumnarrow;
    
    @FindBy(id="ModifyReason")
    private WebElement ModifyReasonTxtbox;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement droptarget;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
    private WebElement groupbyMenuId;
    
    @FindBy(css=".k-grid-content")
    private WebElement gridContent;
    
    @FindBy(id="tGrid")
    private WebElement auditGridContent;
    
    @FindBy(css="#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;
    
    @FindBy(className="toast-message")
    private WebElement successmsg;
    
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
    
    @FindBy(xpath="//tbody/tr/td[4]")
    private WebElement rowdata;
	
    @FindBy(id="ModifyReason1")
    private WebElement deletereasontextbox;
    
    @FindBy(id="yesButton")
    private WebElement yesbtn;
    
    @FindBy(id="noButton")
    private WebElement nobtn;
    
    @FindBy(xpath="//a[text()='Menu ID']")
    private WebElement menuid;
    
    @FindBy(xpath="//input[@id='IMD_MENU_ID']/..")
    private WebElement menuIdTextbox;
    
    @FindBy(xpath="//input[@id='IMD_MENU_ID']")
    private WebElement menuIdTextboxdata;
    
    @FindBy(id="VDN_NUM")
    private WebElement vdnTextbox;
    
    @FindBy(id="VIP_VDN_NUM")
    private WebElement vipVdnTextbox;
    
    @FindBy(id="OPTION")
    private WebElement optionTextbox;
    
    @FindBy(id="DNIS")
    private WebElement DNISTextbox;
    
    @FindBy(id="VDN_DESC")
    private WebElement vdnDescriptionTextbox;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
    private WebElement cancelbtn;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
    private WebElement savebtn;
    
    @FindBy(xpath="//a[text()='VDN Description']")
    private WebElement VdnDescription;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
    private WebElement clearsearch;
    

	public boolean isAgentTransferPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return agentTransfer.isEnabled();
	}

	public boolean isAddBtnDisplayed() {
    	return addNewAgentTransferBtn.isDisplayed() && addNewAgentTransferBtn.isEnabled();
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
		if(isElementExist(AtImg))
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
	        boolean status = false;
	        WebElement ele = headersDropdown.get(0);
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
	        WebElement ele= headersDropdown.get(0);
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
	    
	    public boolean groupby() {
			DragandDrop(menuid,droptarget);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(groupby.getText().split(": ")[1].equals(groupbyMenuId.getText()))
			{return true;}
			else
				return false;		
		}
		public boolean verifyArrowMoveForPreviousAndNextPage(){
	        boolean status=false;
	        if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
	        int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
	        selectWebElement(nextPageIcon);
	        waitForJqueryLoad(driver);
	        int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
	        selectWebElement(previousPageIcon);
	        waitForJqueryLoad(driver);
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
	            waitForJqueryLoad(driver);
	            int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
	            selectWebElement(firstPageIcon);
	            waitForJqueryLoad(driver);
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

		public boolean addCancel(AgentTransferDetails details) throws Exception {
			String actualitems=items.getText();
			Thread.sleep(1000);
			selectWebElement(addNewAgentTransferBtn);
			selectWebElement(menuIdTextbox);
			enterValueToTxtFieldWithoutClear(menuIdTextboxdata,details.getMenuId());
			enterValueToTxtField(vdnTextbox,details.getVdn());
			enterValueToTxtField(vipVdnTextbox,details.getVipVdn());
			enterValueToTxtField(optionTextbox,details.getOption());
			enterValueToTxtField(DNISTextbox,details.getDNIS());
			enterValueToTxtField(vdnDescriptionTextbox,details.getVdnDescription());
			selectWebElement(cancelbtn);
			if(actualitems.equals(items.getText()))
				return true;
			else
				return false;
		}

		public void addNewAgentTransferRecord(AgentTransferDetails details) throws Exception {
			selectWebElement(addNewAgentTransferBtn);
			selectWebElement(menuIdTextbox);
			enterValueToTxtFieldWithoutClear(menuIdTextboxdata,details.getMenuId());
			enterValueToTxtField(vdnTextbox,details.getVdn());
			enterValueToTxtField(vipVdnTextbox,details.getVipVdn());
			enterValueToTxtField(optionTextbox,details.getOption());
			enterValueToTxtField(DNISTextbox,details.getDNIS());
			enterValueToTxtField(vdnDescriptionTextbox,details.getVdnDescription());
			selectWebElement(savebtn);
			
		}

		public boolean verifymessage() {
				waitUntilWebElementIsVisible(successmsg);
		    	if(successmsg.getText().contains("Successfully"))
				return true;
				else
				{return false;}
		}
		
		public String getSuccessMessage() {
			//		waitForJqueryLoad(driver);
			if(successmsg.isDisplayed())
				return successmsg.getText();
			else{return errorMsg.get(0).getText();}	
		}
		

		public void addRecordWithoutMenuId(AgentTransferDetails details) throws Exception {
			selectWebElement(addNewAgentTransferBtn);
			enterValueToTxtField(vdnTextbox,details.getVdn());
			enterValueToTxtField(vipVdnTextbox,details.getVipVdn());
			enterValueToTxtField(optionTextbox,details.getOption());
			enterValueToTxtField(DNISTextbox,details.getDNIS());
			enterValueToTxtField(vdnDescriptionTextbox,details.getVdnDescription());
			selectWebElement(savebtn);
			selectWebElement(cancelbtn);
			
		}

		public void addRecordWithoutVDN(AgentTransferDetails details) throws Exception {
			selectWebElement(addNewAgentTransferBtn);
			selectWebElement(menuIdTextbox);
			enterValueToTxtFieldWithoutClear(menuIdTextboxdata,details.getMenuId());
			enterValueToTxtField(vipVdnTextbox,details.getVipVdn());
			enterValueToTxtField(optionTextbox,details.getOption());
			enterValueToTxtField(DNISTextbox,details.getDNIS());
			enterValueToTxtField(vdnDescriptionTextbox,details.getVdnDescription());
			selectWebElement(savebtn);
			selectWebElement(cancelbtn);			
		}

		public void addRecordWithoutOption(AgentTransferDetails details) throws Exception {
			selectWebElement(addNewAgentTransferBtn);
			selectWebElement(menuIdTextbox);
			enterValueToTxtFieldWithoutClear(menuIdTextboxdata,details.getMenuId());
			enterValueToTxtField(vdnTextbox,details.getVdn());
			enterValueToTxtField(vipVdnTextbox,details.getVipVdn());
			enterValueToTxtField(DNISTextbox,details.getDNIS());
			enterValueToTxtField(vdnDescriptionTextbox,details.getVdnDescription());
			selectWebElement(savebtn);
			selectWebElement(cancelbtn);			
		}

		public void addRecordWithoutVDNDescription(AgentTransferDetails details) throws Exception {
			selectWebElement(addNewAgentTransferBtn);
			selectWebElement(menuIdTextbox);
			enterValueToTxtFieldWithoutClear(menuIdTextboxdata,details.getMenuId());
			enterValueToTxtField(vdnTextbox,details.getVdn());
			enterValueToTxtField(vipVdnTextbox,details.getVipVdn());
			enterValueToTxtField(optionTextbox,details.getOption());
			enterValueToTxtField(DNISTextbox,details.getDNIS());
			selectWebElement(savebtn);
			selectWebElement(cancelbtn);			
		}
		
		public boolean verifySearchIsNotEqualTo(String vdndescription) throws Exception {
			Boolean Status=false;
			Map<String, String> map=new HashMap<String,String>() ;
			map.put("VDN Description", vdndescription);
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"VDN Description");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
	        enterValueToTxtField(searchTextBox,vdndescription);		
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
		
		public boolean verifySearchContains(String vdndescription) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"VDN Description");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
	        enterValueToTxtField(searchTextBox,vdndescription);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("VDN Description").toUpperCase().contains(vdndescription.toUpperCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		public boolean verifySearchDoesNotContains(String vdndescription) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"VDN Description");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
	        enterValueToTxtField(searchTextBox,vdndescription);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(!map1.get("VDN Description").toLowerCase().contains(vdndescription.toLowerCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		
		public boolean verifySearchStartsWith(String vdndescription) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"VDN Description");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
	        enterValueToTxtField(searchTextBox,vdndescription);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("VDN Description").toLowerCase().startsWith(vdndescription.toLowerCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
		
		public boolean verifySearchEndsWith(String vdndescription) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"VDN Description");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
	        enterValueToTxtField(searchTextBox,vdndescription);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("VDN Description").toUpperCase().endsWith(vdndescription.toUpperCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}

		public boolean editCancelbtn(AgentTransferDetails details) throws Exception {
			searchAgentTransfer(details.getVdnDescription());
			selectWebElement(editButton);
			Thread.sleep(1000);
			enterValueToTxtField(vdnDescriptionTextbox,details.getUpdateVdnDescription());
			enterValueToTxtField(ModifyReasonTxtbox,details.getModifyReason());
			selectWebElement(cancelbtn);
			if(rowdata.getText().equals(details.getVdnDescription()))
				return true;
			else
			return false;
		}

		private void searchAgentTransfer(String vdnDescription) throws Exception {
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"VDN Description");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	        enterValueToTxtField(searchTextBox,vdnDescription);
	        selectWebElement(searchSearchBtn);
	        waitForJqueryLoad(driver);
	        waitUntilWebElementIsVisible(gridContent);			
		}

		public void editAgentTransferRecord(AgentTransferDetails details) throws Exception {
			searchAgentTransfer(details.getVdnDescription());
			selectWebElement(editButton);
			Thread.sleep(1000);
			enterValueToTxtField(vdnDescriptionTextbox,details.getUpdateVdnDescription());
			enterValueToTxtFieldWithoutClear(ModifyReasonTxtbox,details.getModifyReason());
			selectWebElement(savebtn);
			
		}
		
		public boolean verifyExportToExcel(String filepath) {
	    	final File folder = new File(filepath);
			for (final File f : folder.listFiles()) {
			    if (f.getName().startsWith("Agent Transfer")) {
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
			Boolean Status=verifyExportPageFileDownload(filepath, "Agent Transfer");
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
		
		public void SortByAscending() {
			selectWebElement(VdnDescription);
			waitForJqueryLoad(driver);
			selectWebElement(exporttoexcel);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void SortByDescending() {
			selectWebElement(VdnDescription);
			waitForJqueryLoad(driver);
			selectWebElement(VdnDescription);
			selectWebElement(exporttoexcel);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public boolean clearAll(AgentTransferDetails details) throws Exception {
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"VDN Description");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	        enterValueToTxtField(searchTextBox,details.getVdnDescription());
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
		
		public boolean verifyinvalidsearchwithwrongdata(AgentTransferDetails details) throws Exception {
			searchAgentTransfer(details.getVdnDescription());
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

		public boolean verifydeleteNo(AgentTransferDetails details) throws Exception {
			searchAgentTransfer(details.getVdnDescription());
			selectWebElement(deleteButton);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			enterValueToTxtField(deletereasontextbox,details.getDeleteReason());
			selectWebElement(nobtn);
			if(rowdata.getText().equals(details.getVdnDescription()))
					return true;
			else
			return false;
		}

		public void deleteAgentTransferRecord(AgentTransferDetails details) throws Exception {
			searchAgentTransfer(details.getVdnDescription());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			selectWebElement(deleteButton);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			enterValueToTxtFieldWithoutClear(deletereasontextbox,details.getDeleteReason());
			selectWebElement(yesbtn);				
		}
		
		public boolean ExporttoExcelWithoutData(AgentTransferDetails details) throws Exception {
			searchAgentTransfer(details.getVdnDescription());
			waitForJqueryLoad(driver);
			selectWebElement(exporttoexcel);
			if(errorMsg.get(0).getText().equals("There is no record to export"))
				return true;
			else
			return false;
		}
		
		public boolean verifyDatabase(String  query) throws Exception {
			List<Map<String,String>> database=database(query);
			selectWebElement(VdnDescription);
			waitForJqueryLoad(driver);
			System.out.println(database);
			List<Map<String,String>> UI=gettable(); 
			System.out.println(UI);
			if(UI.equals(database))
				return true;
			else
				return false;
		}

		public List<Map<String, String>> gettable() throws Exception {
			Thread.sleep(2000);
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

		public boolean verifyErrorMessage() {
			if (errorMsg.size()>0)
				return false;
			else 
				return true;
		}	
}
