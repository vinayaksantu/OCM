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

import com.tetherfi.model.user.TdmThresholdConfigDetails;

public class TdmThresholdConfigPage extends BasePage{
	public TdmThresholdConfigPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css=".ibox-title h5")
    private WebElement TdmThresholdConfig;
	
	@FindBy(css="#DrillReportNameLbl")
	private WebElement TdmThresholdConfigThresholdPopup;
	
	@FindBy(xpath="//i[@class='fas fa-tasks']")
	private WebElement DMImg;
	    
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
		
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
		 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(id="userGrid")
	private WebElement auditGridContent;
		
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
	
	@FindBy(css="#gridDetails th a[class='k-header-column-menu']")
	private List<WebElement> popupHeadersDropdown;
	    
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
	
	@FindBy(css=".k-grid-content")
    private WebElement gridContent;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	@FindBy(xpath="//a[text()='Team Name']")
	private WebElement TeamName;
	
	@FindBy(css = "#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;
	
	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
		    
	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
	private WebElement groupbyTeamName;
	
	@FindBy(css = ".fa-search")
	private WebElement searchBtn;

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
	    
	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;
	 
	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;
		    
	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;
		    
	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;
		    
	@FindBy(xpath="//i[@class='fas fa-sync']")
	private WebElement clearsearch;
	
	@FindBy(id="create")
	private WebElement addButton;
	
	@FindBy(id="createone")
	private WebElement popupSaveBtn;
	
	@FindBy(css=".k-grid-cancel-changes")
    private WebElement cancelBtn;
	
	@FindBy(xpath="span[text()='×']")
	private WebElement closeBtn;
	
	@FindBy(css="span[aria-owns='AuxCodeFrom_listbox']")
	private WebElement auxCodeFromDropDown;
	
	@FindBy(css="ul[id='AuxCodeFrom_listbox'] li")
	private List<WebElement> auxCodeFromListbox;
	
	@FindBy(css="span[aria-owns='AuxCodeTo_listbox']")
	private WebElement auxCodeToDropDown;
	

	@FindBy(css=".editableFiled k-edit-cell")
	private WebElement editableCell;
	
	@FindBy(css="ul[id='AuxCodeTo_listbox'] li")
	private List<WebElement> auxCodeToListbox;
	
	@FindBy(id="Threshold")
	private WebElement thresholdTextbox;
	
	@FindBy(id="ModifyReasonUser")
	private  WebElement modifyReasonTextBox;
	
	@FindBy(id="IsStatusChange")
	private WebElement statusChange;
	
	@FindBy(id="IsDeleted")
	private WebElement deleteChange;
	
	@FindBy(id="AllowNotification")
	private WebElement allowNotification;

	@FindBy(id="yesButton")
	private List<WebElement> saveBtn;
	
	

	public boolean isTdmThresholdConfigPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return TdmThresholdConfig.isEnabled();
	}
	
	public boolean verifylogo() {
		if(isElementExist(DMImg))
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
	    
	    public boolean verifyExportToExcel(String filePath) {
			final File folder = new File(filePath);
			for (final File f : folder.listFiles()) {
			    if (f.getName().startsWith("TDM Threshold Configuration")) {
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
			Boolean Status=verifyExportPageFileDownload(filePath, "TDM Threshold Configuration");
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
					scrollToElement(headers.get(j));
					System.out.println(headers.get(j).getText());
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
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
			}
				return arr;
		}
		
		public boolean ExporttoExcelWithoutData(TdmThresholdConfigDetails TdmThresholdConfigDetails ) throws Exception {
			searchTdmThresholdConfig(TdmThresholdConfigDetails.getTeamName());
			waitForJqueryLoad(driver);
			Thread.sleep(1000);
			selectWebElement(exporttoexcel);
			if(errorMsg.get(0).getText().equals("There is no record to export"))
				return true;
			else
			return false;
		}
		
		public void searchTdmThresholdConfig(String teamName) throws Exception {
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Team Name");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	        enterValueToTxtField(searchTextBox,teamName);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
		}

		public void SortByAscending() {
			selectWebElement(TeamName);
			selectWebElement(exporttoexcel);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void SortByDescending() {
			selectWebElement(TeamName);
			selectWebElement(TeamName);
			selectWebElement(exporttoexcel);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public boolean groupby() {
			DragandDrop(TeamName,droptarget);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(groupby.getText().split(": ")[1].equals(groupbyTeamName.getText()))
			{return true;}
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
		public boolean verifyPopupColumnsHeaderDisabled() {
			boolean status = false;
			try{
				for(WebElement ele:popupHeadersDropdown) {
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
								System.out.println(ele1.getText());
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
		public boolean verifyPopupColumnsHeaderEnabled(){
			boolean status=false;
			try{
				for(WebElement ele:popupHeadersDropdown) {
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
		
		public void searchwithoutextsearch() {
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Team Name");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	        selectWebElement(searchSearchBtn);	
			selectWebElement(searchClose);		
		}
		
		public String getMessage() {
			if(errorMsg.size()>0){return errorMsg.get(0).getText();}
	        else {
	        waitUntilWebElementIsVisible(successmsg);
	         return successmsg.getText();}
		}
		
		public String getSuccessMessage() {
			if(successmsg.isDisplayed()){
				return successmsg.getText();}
	        else {
	        	return errorMsg.get(0).getText();}
		}
		
		public boolean clearAll(TdmThresholdConfigDetails TdmThresholdConfigDetails) throws Exception {
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Team Name");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	        enterValueToTxtField(searchTextBox,TdmThresholdConfigDetails.getTeamName());
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
		
		public boolean verifyinvalidsearchwithwrongdata(TdmThresholdConfigDetails details) throws Exception {
			searchTdmThresholdConfig(details.getTeamName());
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

		public boolean verifyTdmThresholdConfigThresholdDetailsPage(TdmThresholdConfigDetails details) throws Exception {
			searchTdmThresholdConfig(details.getTeamName());
            List<WebElement> row=gridContent.findElements(By.tagName("tr"));
            List<WebElement>column=row.get(0).findElements(By.tagName("td"));
            Thread.sleep(1000);
			selectWebElement(column.get(2));
			return TdmThresholdConfigThresholdPopup.isEnabled();
		}

		public boolean verifyaddNewRowButton() {
			waitForJqueryLoad(driver);
			return addButton.isEnabled();
		}

		public boolean verifysaveButton() {
			waitForJqueryLoad(driver);
			return popupSaveBtn.isEnabled();
		}

		public boolean verifycancelbutton() {
			waitForJqueryLoad(driver);
			return cancelBtn.isEnabled();
		}

		public boolean verifyCloseButton() {
			waitForJqueryLoad(driver);
			moveToElement(closeBtn);
			closeBtn.click();
			waitForJqueryLoad(driver);
			return gridContent.isDisplayed();
		}

		public void addNewRow(TdmThresholdConfigDetails details) throws Exception {
			selectWebElement(addButton);
			selectWebElement(auxCodeFromDropDown);
			selectDropdownFromVisibleText(auxCodeFromListbox,details.getAuxCodeFrom());
			driver.findElement(By.xpath("//*[@id='gridDetails']/div[3]/table/tbody/tr/td[4]")).click();
			selectWebElement(auxCodeToDropDown);
			selectDropdownFromVisibleText(auxCodeToListbox,details.getAuxCodeTo());	
			driver.findElement(By.xpath("//*[@id='gridDetails']/div[3]/table/tbody/tr/td[5]")).click();
			enterValueToTxtField(thresholdTextbox,details.getThreshold());
			driver.findElement(By.xpath("//*[@id='gridDetails']/div[3]/table/tbody/tr/td[7]")).click();
			statusChange.click();
			driver.findElement(By.xpath("//*[@id='gridDetails']/div[3]/table/tbody/tr/td[8]")).click();
			allowNotification.click();
			selectWebElement(popupSaveBtn);
	        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
	        selectWebElement(saveBtn.get(1));
		}
		
		
		public void updateNewRow(TdmThresholdConfigDetails details) throws Exception {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='gridDetails']/div[3]/table/tbody/tr/td[7]")).click();
			statusChange.click();
			driver.findElement(By.xpath("//*[@id='gridDetails']/div[3]/table/tbody/tr/td[8]")).click();
			allowNotification.click();
			selectWebElement(popupSaveBtn);
	        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
	        selectWebElement(saveBtn.get(1));
		}
		
		public void deleteNewRow(TdmThresholdConfigDetails details) throws Exception {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='gridDetails']/div[3]/table/tbody/tr/td[6]")).click();
			deleteChange.click();
			selectWebElement(popupSaveBtn);
	        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
	        selectWebElement(saveBtn.get(1));
		}

		public boolean verifyDatabase(String query) {
			selectWebElement(TeamName);
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
					if(headers.get(j).getText().equals("Team Name")){
						col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}}
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

		public boolean verifySearchIsNotEqualTo(String teamName) throws Exception {
			Boolean Status=false;
			Map<String, String> map=new HashMap<String,String>() ;
			map.put("Team Name", teamName);
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Team Name");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
	        enterValueToTxtField(searchTextBox,teamName);		
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

		public boolean verifySearchContains(String teamName) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Team Name");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
	        enterValueToTxtField(searchTextBox,teamName);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("Team Name").toUpperCase().contains(teamName.toUpperCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}

		public boolean verifySearchDoesNotContains(String teamName) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Team Name");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not contain");
	        enterValueToTxtField(searchTextBox,teamName);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(!map1.get("Team Name").toLowerCase().contains(teamName.toLowerCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}

		public boolean verifySearchStartsWith(String teamName) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Team Name");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
	        enterValueToTxtField(searchTextBox,teamName);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("Team Name").toLowerCase().startsWith(teamName.toLowerCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}

		public boolean verifySearchEndsWith(String teamName) throws Exception {
			Boolean Status=false;
			selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Team Name");
	        selectWebElement(selectSearchCol.get(1));
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
	        enterValueToTxtField(searchTextBox,teamName);		
	        selectWebElement(searchSearchBtn);
	        waitUntilWebElementIsVisible(gridContent);
	        List<Map<String,String>> UI=gettable(); 
	        for (Map<String,String> map1: UI)
	        {   	
				if(map1.get("Team Name").toUpperCase().endsWith(teamName.toUpperCase()))
	        	Status= true;
	        	else 
	        		Status= false;
		}
	        return Status;
		}
}
