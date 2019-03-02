package com.tetherfi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.user.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleBasedAccessManagementPage extends BasePage{

    public RoleBasedAccessManagementPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement roleBasedAccessManagement;
	
	@FindBy(xpath="//i[@class='fa  fa-users']")
    private WebElement rbamImg;
	@FindBy(xpath="//table[@class='k-selectable']/tbody/tr")
    private List<WebElement> tablerecord;
    
    @FindBy(xpath="//span[@class='k-pager-info k-label']")
    private WebElement items;
    
    @FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(id="navbarheader")
	private WebElement header;

    @FindBy(css=".k-grid-content .k-selectable")
    private WebElement gridContent;

    @FindBy(css=".k-loading-img")
    private WebElement loadingImg;

    @FindBy(css="modal-backdrop")
    private WebElement backdropImg;

    @FindBy(id="create")
    private WebElement addNewRoleBasedAccessManagementRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popopContainer;

    @FindBy(css =".k-edit-form-container #RoleName")
    private WebElement roleNameTextBox;

    @FindBy(css=".k-edit-form-container .k-grid-update")
    private WebElement saveBtn;

    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;

    @FindBy(css=".toast-message")
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

    @FindBy(css=".modal-body .form-inline .form-group .k-textbox")
    private List<WebElement> searchText;

    @FindBy(css=".modal-footer .k-primary")
    private WebElement searchBtn;

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
    
	@FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;

    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchclose;
    
    @FindBy(css=".k-grid-content")
    private WebElement gridcontent;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    
    @FindBy(xpath="//table[@class='k-selectable']/tbody/tr/td[2]")
    private WebElement rowdata;
    
    @FindBy(xpath="//a[text()='Role Name']")
    private WebElement rolename;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement droptarget;
    
    @FindBy(xpath="//p[@class='k-reset']")
    private WebElement groupby;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
    private WebElement rolenamegroupby;
    
    @FindBy(css="a[aria-label='Go to the next page']")
    private WebElement nextPageIcon;
   
    @FindBy(css=".k-pager-numbers .k-state-selected")
    private WebElement pageNumber;
    
    @FindBy(css="a[aria-label='Go to the previous page']")
    private WebElement previousPageIcon;
    
    @FindBy(css="a[aria-label='Go to the last page']")
    private WebElement lastPageIcon;
    
    @FindBy(css="a[aria-label='Go to the first page']")
    private WebElement firstPageIcon;
    
    @FindBy(css=".k-pager-sizes .k-icon")
    private WebElement pagerDropdown;
    
    @FindBy(css=".k-animation-container ul li")
    private List<WebElement> pageSizeListBox;
    
    @FindBy(xpath="//span[@class='k-input']")
    private WebElement pagerSize;
    
    @FindBy(css="th a[class='k-header-column-menu']")
    private List<WebElement> headersDropdown;
    
    @FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
    private List<WebElement> headersColumns;
    
    @FindBy(css="th a[class='k-link']")
    private List<WebElement> headersText;								 
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;

	@FindBy(id="roleGrid")
    private WebElement auditGridContent;
    
    @FindBy(id="checkAll")
    private List<WebElement> checkallAccess;
    
    @FindBy(id="checkAllAddAccess")
    private List<WebElement> checkalladdaccess;
    
    @FindBy(id="checkAllEditAccess")
    private List<WebElement> checkalleditaccess;
    
    @FindBy(id="checkAllDeleteAccess")
    private List<WebElement> checkalldeleteaccess;
    
    @FindBy(id="checkAllExportAccess")
    private List<WebElement> checkallexportaccess;
    
    @FindBy(id="checkAllReport")
    private WebElement checkallreport;
    
    @FindBy(id="checkAllReportExportAccess")
    private WebElement checkallreportexportaccess;
    
    @FindBy(id="checkAllDashboard")
    private WebElement checkalldashboard;
    
    @FindBy(id="checkAllOtherApplications")
    private WebElement checkallotherapplication;
    
    @FindBy(xpath="//div[@id='DrillReportNameLbl']/h2")
	private WebElement pagebaseduseraccess;
    
    @FindBy(xpath="//div[@class='modal inmodal fade in' ]/div/div/div/button[@class='close']")
    private WebElement pbuaclose;
    
    @FindBy(id="createone")
    private List<WebElement> saveaccess;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel-changes']")
	private List<WebElement> pbuacancel;
	
	@FindBy(id="myWindowUser_wnd_title")
	private WebElement modifypopup;
    
	@FindBy(css=".toast-info .toast-message")
	private WebElement changesMsg;
	
	@FindBy(xpath="//div[@onclick='saveChangeYes()']")
    private WebElement useryesBtn;
    
    @FindBy(id="ModifyReasonUser")
    private WebElement userModifyReasontxtbox;
    
    @FindBy(xpath="//span[text()='Reports']")
	private WebElement navigatetoreports;
	
	@FindBy(xpath="//span[text()='Dashboards']")
	private WebElement navigatetodashboard;
	
	@FindBy(xpath="//span[text()='Other Applications']")
	private WebElement navigatetootherapplication;							   
    public boolean isRoleBasedAccessManagementPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return roleBasedAccessManagement.isEnabled();
    }

    public void addNewRoleBasedAccessManagementRecord(String roleName) {
        selectWebElement(addNewRoleBasedAccessManagementRecordBtn);
        selectWebElement(roleNameTextBox);
        enterValueToTxtField(roleNameTextBox,roleName);
        selectWebElement(saveBtn);
    }
    public void searchRoleBasedAccessManagementRecord(String roleName) {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Role Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),roleName);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editRoleBasedAccessManagementRecord(String oldrolename, String newrolename, String reason) throws Exception {
		Thread.sleep(2000);				
        searchRoleBasedAccessManagementRecord(oldrolename);
        selectWebElement(editBtn);
        selectWebElement(roleNameTextBox);
        enterValueToTxtField(roleNameTextBox,newrolename);
        enterValueToTxtField(modifyReasonTextBox,reason);
        selectWebElement(saveBtn);
    }
    public void deleteRoleBasedAccessManagementRecord(String oldrolename, String reason) {
        searchRoleBasedAccessManagementRecord(oldrolename);
        selectWebElement(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }
    public boolean verifyNewRecordCreated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordDeleted(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully")){
            return true;
        }else{return false;}
    }

    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
        {return true;}else{return false;}
    }
    
    public boolean isAddBtnDisplayed() {
    	return addNewRoleBasedAccessManagementRecordBtn.isDisplayed() && addNewRoleBasedAccessManagementRecordBtn.isEnabled();
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
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }
	public boolean verifylogo() {
		if(isElementExist(rbamImg)) 
		{return true;}
		else return false;
	}

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

	public boolean addNewCancel(String roleName) {
		String actualitems=items.getText();
		selectWebElement(addNewRoleBasedAccessManagementRecordBtn);
        selectWebElement(roleNameTextBox);
        enterValueToTxtField(roleNameTextBox,roleName);
        selectWebElement(cancelBtn);
        if(actualitems.equals(items.getText()))
        	return true;
        else
		return false;
	}

	public void addinvalidrecord() {
		selectWebElement(addNewRoleBasedAccessManagementRecordBtn);
        selectWebElement(roleNameTextBox);
        selectWebElement(saveBtn);
        selectWebElement(cancelBtn);
	}

	public void DuplicateRecord(UserDetails userDetails) {
		selectWebElement(addNewRoleBasedAccessManagementRecordBtn);
        selectWebElement(roleNameTextBox);
        enterValueToTxtField(roleNameTextBox,userDetails.getRoleName());
        selectWebElement(saveBtn);	
        try {selectWebElement(cancelBtn);
        }
        catch(Exception e){
        	e.printStackTrace();
        	
        }
	}

	public boolean clearAll(String roleName) {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Role Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),roleName);
        selectWebElement(clearall);
        if(searchText.get(0).isEnabled())
        	return true;
        else
		return false;
	}

	public boolean verifyclose() {
		selectWebElement(searchclose);
		if(gridcontent.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean verifyinvalidsearch(UserDetails userDetails) {
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Role Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),userDetails.getRoleName());
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);		
        if(norecords.isDisplayed())
			return true; 
			else
				return false;
	}

	public boolean verifyclearsearch() {
		selectWebElement(clearsearch);
		if(gridcontent.isDisplayed())
			return true;
		else
		return false;
	}

	public boolean editCancel(String roleName, String newrolename, String reason) {
		searchRoleBasedAccessManagementRecord(roleName);
        selectWebElement(editBtn);
        selectWebElement(roleNameTextBox);
        enterValueToTxtField(roleNameTextBox,newrolename);
        enterValueToTxtField(modifyReasonTextBox,reason);	
        selectWebElement(cancelBtn);
        if(rowdata.getText().equals(roleName))
        	return true;
        else
        return false;
	}

	public boolean deleteRecordNoBtn(String roleName, String deleteReason) {
		searchRoleBasedAccessManagementRecord(roleName);
		selectWebElement(deleteBtn);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtField(deleteReasonTextBox,deleteReason);
        selectWebElement(noBtn);
        if(rowdata.getText().equals(roleName))
        	return true;
        else
        	return false;
	}

	public boolean groupby() {
		DragandDrop(rolename,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(rolenamegroupby.getText()))
		{return true;}
		else
			return false;	
	}

	public boolean verifyArrowMoveForPreviousAndNextPage() {
		 boolean status=false;
	     if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
	    	 int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
	    	 selectWebElement(nextPageIcon);
	    	 int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
	    	 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	 selectWebElement(previousPageIcon);
	    	 int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
	    	 if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
	     }else{
	     	System.out.println("previous and next page icon disabled");status=true;
	     }
	     return status;
	}

	public boolean verifyArrowMoveForFirstAndLastPage() {
		boolean status=false;
        if(!lastPageIcon.getAttribute("class").contains("k-state-disabled")){
            int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            selectWebElement(lastPageIcon);
            int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            selectWebElement(firstPageIcon);
            int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
            if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
        }else{
            System.out.println("previous and next page icon disabled");status=true;
        }
        return status;
	}

	public boolean verifyTotalNumberOfItemsPerPageDetails() {
		String item = items.getText();
        return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
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
	                    int totalRows=(gridcontent.findElements(By.tagName("tr")).size());
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

	public boolean verifyDropDownOfAllHeaders() {
		boolean status = false;
        try {
        	for(int i=0;i<3;i++) {
        		WebElement ele =headersDropdown.get(i);
        				scrollToElement(ele);
        				status = false;
        				if (!ele.isDisplayed()) {
        					System.out.println(i + "\n");
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
        					Thread.sleep(1000);
        				}
        		}
        	} catch (Exception e) {
            e.printStackTrace();
        }
        return status;
	}

	public boolean verifycolumnsHeaderEnabled() {
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

	public boolean verifyExportToExcel(String filePath) {
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "Role Based Access Management");
		return Status;
	}

	public boolean verifyexportToExcelSheet(List<Map<String, String>> maplist) {
		List<Map<String,String>> UI=getdata(); 
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
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			selectWebElement(nextPageIcon);
			waitForJqueryLoad(driver);}
		}
			return arr;
	}

	public void SortByAscending() {
		selectWebElement(rolename);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	public void SortByDescending() {
		selectWebElement(rolename);
		selectWebElement(rolename);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public boolean ExporttoExcelWithoutData(UserDetails userDetails) {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Role Name");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),userDetails.getRoleName());
        selectWebElement(searchBtn);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		selectWebElement(exporttoexcel);
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}
	
	public boolean verifyDatabase(String  query) {
		selectWebElement(rolename);
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
	public boolean verifypagebaseduseraccess(UserDetails userDetails) {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(pagebaseduseraccess.isDisplayed())
			return true;
		else
		return false;
	}
	public boolean verifyclosebutton()
	{
		moveToElement(pbuaclose);
		selectWebElement(pbuaclose);
		if(rowdata.isDisplayed())
			return true;
		else
		return false;
	}
	public boolean verifyaccesscheckbox(UserDetails userDetails) {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=false;
		//if(!checkallAccess.isEnabled())
		selectWebElement(checkallAccess.get(0));
		if(checkallAccess.get(0).isEnabled()) {
			selectWebElement(checkalladdaccess.get(0));
				if(checkalladdaccess.get(0).isEnabled()) {
					selectWebElement(checkalleditaccess.get(0));
					if(checkalleditaccess.get(0).isEnabled())
					{
						selectWebElement(checkalldeleteaccess.get(0));
						if(checkalldeleteaccess.get(0).isEnabled())
						{
							selectWebElement(checkallexportaccess.get(0));
							if(checkallexportaccess.get(0).isEnabled())
							{
							Status=true;
							}
						}
				
					}
				}
		}
		return Status;
	}
	
	public boolean verifycancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(checkallAccess.get(0));
		Thread.sleep(500);
		selectWebElement(pbuacancel.get(0));
		Thread.sleep(1000);
		if(checkallAccess.get(0).isSelected())
			return false;
		else
		return true;
	}		
	
	public boolean verifysavechanges() throws Exception {
		waitForJqueryLoad(driver);
		Boolean Status=false;
		selectWebElement(checkallAccess.get(0));
		selectWebElement(saveaccess.get(0));
		if(modifypopup.isDisplayed())
		{	Status=true;
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtField(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
		}
		return Status;
	}

	public boolean verifyunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(pbuacancel.get(0));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
	
	public boolean verifyunsuccessfullsavechanges() throws Exception{
		selectWebElement(saveaccess.get(0));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
	
	public boolean verifyreportsaccesscheckbox(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoreports);
		Boolean Status=false;
		selectWebElement(checkallreport);
		if(checkallreport.isSelected())
		{
			selectWebElement(checkallreportexportaccess);
			if(checkallreportexportaccess.isSelected())
			{
				Status=true;
			}
		}
		return Status;
	}

	public boolean verifyReportscancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetoreports);
		Thread.sleep(1000);
		selectWebElement(checkallreport);
		selectWebElement(pbuacancel.get(1));
		Thread.sleep(1000);
		if(checkallreport.isSelected())
			return false;
		else
		return true;
	}

	public boolean verifyReportssavechanges() throws Exception {
		waitForJqueryLoad(driver);
		Boolean Status=false;
		selectWebElement(checkallreport);
		selectWebElement(saveaccess.get(1));
		if(modifypopup.isDisplayed())
		{	Status=true;
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtField(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
		}
		return Status;
	}

	public boolean verifyreportsunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetoreports);
		selectWebElement(pbuacancel.get(1));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifyreportsunsuccessfullsavechanges() {
		selectWebElement(saveaccess.get(1));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifydashboardaccesscheckbox(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetodashboard);
		Boolean Status=false;
		selectWebElement(checkalldashboard);
		if(checkalldashboard.isSelected())
			{
				Status=true;
			}
		return Status;
	}


	public boolean verifyDashboardcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetodashboard);
		Thread.sleep(1000);
		selectWebElement(checkalldashboard);
		selectWebElement(pbuacancel.get(2));
		Thread.sleep(1000);
		if(checkalldashboard.isSelected())
			return false;
		else
		return true;
	}

	public boolean verifyDashboardsavechanges() throws Exception {
		waitForJqueryLoad(driver);
		Boolean Status=false;
		selectWebElement(checkalldashboard);
		selectWebElement(saveaccess.get(2));
		if(modifypopup.isDisplayed())
		{	Status=true;
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtField(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
		}
		return Status;
	}

	public boolean verifydashboardunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetodashboard);
		selectWebElement(pbuacancel.get(2));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifydashboardunsuccessfullsavechanges() {
		selectWebElement(saveaccess.get(2));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
	
	public boolean verifyotherapplicationaccesscheckbox(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetootherapplication);
		Boolean Status=false;
		selectWebElement(checkallotherapplication);
		if(checkallotherapplication.isSelected())
			{
				Status=true;
			}
		return Status;
	}


	public boolean verifyotherapplicationcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);
		selectWebElement(navigatetootherapplication);
		Thread.sleep(1000);
		selectWebElement(checkallotherapplication);
		selectWebElement(pbuacancel.get(3));
		Thread.sleep(1000);
		if(checkallotherapplication.isSelected())
			return false;
		else
		return true;
	}

	public boolean verifyotherapplicationsavechanges() throws Exception {
		waitForJqueryLoad(driver);
		Boolean Status=false;
		selectWebElement(checkallotherapplication);
		selectWebElement(saveaccess.get(3));
		if(modifypopup.isDisplayed())
		{	Status=true;
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtField(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
		}
		return Status;
	}

	public boolean verifyotherapplicationunsuccessfullcancelchanges(UserDetails userDetails) throws Exception {
		searchRoleBasedAccessManagementRecord(userDetails.getRoleName());
		selectWebElement(rowdata);
		Thread.sleep(1000);	
		selectWebElement(navigatetootherapplication);
		selectWebElement(pbuacancel.get(3));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}

	public boolean verifyotherapplicationunsuccessfullsavechanges() {
		selectWebElement(saveaccess.get(3));
		if(changesMsg.getText().equals("No rows has been changed"))
			return true;
		else
		return false;
	}
}