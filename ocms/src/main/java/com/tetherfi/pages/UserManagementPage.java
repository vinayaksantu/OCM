package com.tetherfi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.user.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagementPage extends BasePage {

    private static final String value = null;

	public UserManagementPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement userManagement;
    
    @FindBy(xpath="//i[@class='far fa-users-cog']")
    private WebElement uMImg;

    @FindBy(id="create")
    private WebElement addNewUserManagementRecordBtn;

    @FindBy(css =".k-edit-form-container #UserId")
    private WebElement userIdTextBox;

    @FindBy(css=".k-edit-form-container .k-grid-update")
    private WebElement saveBtn;

    @FindBy(css=".k-grid-cancel")
    private WebElement cancelBtn;
    
    @FindBy(xpath="//div[@class='k-edit-buttons k-state-default']/a[@class='k-button k-button-icontext k-grid-cancel']")
    private WebElement editcancel;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css="#toast-container .toast-error")
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

    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;

    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;

    @FindBy(id = "ModifyReason")
    private WebElement editModifyReasonTextBox;

    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;

    @FindBy(id="yesButton")
    private WebElement yesBtn;
    
    @FindBy(id="noButton")
    private WebElement noBtn;
    
    @FindBy(xpath="//table[@class='k-selectable']/tbody/tr/td[2]")
    private WebElement rowdata;
    
    @FindBy(xpath="//table[@class='k-selectable']/tbody/tr")
    private WebElement tablerow;
    
    @FindBy(id="DrillReportNameLbl")
    private WebElement PageBasedAccess;
    
    @FindBy(id="checkAll")
    private WebElement checkallAccess;
    
    @FindBy(id="checkAll")
    private List <WebElement> accessAll;
    
    @FindBy(tagName="td")
    private List<WebElement> coloums;
    
    @FindBy(id="createone")
    private List <WebElement> saveaccess;
    
    @FindBy(xpath="//div[@onclick='saveChangeYes()']")
    private WebElement useryesBtn;
    
    @FindBy(id="ModifyReasonUser")
    private WebElement userModifyReasontxtbox;
    
    @FindBy(xpath="//div[@class='modal inmodal fade in' ]/div/div/div/button[@class='close']")
    private WebElement pbuaclose;
    
    @FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(id="navbarheader")
	private WebElement header;
	
	@FindBy(xpath="//a[text()='User Id']")
	private WebElement userId;
	
	@FindBy(xpath="//table[@class='k-selectable']/tbody/tr")
	private List<WebElement> tablerecord;
	
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
	
	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;
	
	@FindBy(xpath="//button[text()='Close']")
	private WebElement searchClose;
    
	@FindBy(xpath="//i[@class='fas fa-sync']")
	private WebElement clearsearch;

	@FindBy(css=".k-grid-content")
	private WebElement gridcontent;
	
	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;
	
	@FindBy(xpath="//*[@id='grid']/div[5]/a[3]/span")
	private WebElement nextPageIcon;
	
	@FindBy(id="grid")
	private WebElement auditGridContent;
	
	@FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;
	
	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement droptarget;
	
	@FindBy(xpath="//a[text()='User Id']")
	private WebElement UserId;
	
	@FindBy(css="a[aria-label='Go to the previous page']")
	private WebElement previousPageIcon;

	@FindBy(css=".k-pager-sizes .k-icon")
	private WebElement pagerDropdown;
	
	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;
	
	@FindBy(css="a[aria-label='Go to the last page']")
	private WebElement lastPageIcon;
	
	@FindBy(css=".k-pager-numbers .k-state-selected")
	private WebElement pageNumber;
	
	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;
	
	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;
	
	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;
	
	@FindBy(css="a[aria-label='Go to the first page']")
    private WebElement firstPageIcon;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;
	
	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[3]")
	private WebElement userIdgroupby;
	
	@FindBy(xpath="//span[text()='Reports']")
	private WebElement reportsTab;
	
	@FindBy(xpath="//span[text()='Dashboards']")
	private WebElement dashboardTab;
	
	@FindBy(xpath="//div[@id='gridUserAccessDetails']/div[3]/table/tbody/tr[@role='row']")
	private List <WebElement> adminPagesTableRows;
	
	@FindBy(xpath="//div[@id='gridReportUserAccessDetails']/div[3]/table/tbody/tr[@role='row']")
	private List <WebElement> reportsTableRows;
	
	@FindBy(xpath="//div[@id='gridDashboardAccessDetails']/div[3]/table/tbody/tr[@role='row']")
	private List <WebElement> dashboardTableRows;
	
    public boolean isUserManagementPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return userManagement.isEnabled();
    }
    
    public boolean verifylogo() 
	{
		if(isElementExist(uMImg)) 
		{return true;}
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
	
    public void addNewUserManagementRecord(String userID) {
        selectWebElement(addNewUserManagementRecordBtn);
        selectWebElement(userIdTextBox);
        enterValueToTxtField(userIdTextBox,userID);
        selectWebElement(saveBtn);
    }
    public void editUserManagementRecord(String UserID, String reason,String user) {
        searchUserManagementRecord(user);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editButton);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtField(userIdTextBox,UserID);
        enterValueToTxtField(editModifyReasonTextBox,reason);
        selectWebElement(saveBtn);
        try {
        selectWebElement(editcancel);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    public void searchUserManagementRecord(String UserID) {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),UserID);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
    }
    public void deleteUserManagementRecord(String username, String reason) {
        searchUserManagementRecord(username);
        selectWebElement(deleteBtn);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }
    public boolean verifyNewRecordCreated(){
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Created Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Updated Successfully"))
        {return true;}else{return false;}
    }

    public boolean verifyRecordDeleted(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully")){
            return true;
        }else{return false;}
    }
	public boolean isPageBasedUserAccessPageDisplayed() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		if(PageBasedAccess.isDisplayed())
			return true;
		else
		return false;
	}
	
	public void ProvideAccess(String value, String accessLvl) throws Exception {
		Thread.sleep(1000);
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		for(int i=0;i<adminPagesTableRows.size();i++) {
			System.out.println(adminPagesTableRows.get(i).getText());
			if (adminPagesTableRows.get(i).getText().equals(value))
			{
				WebElement checkAccess=adminPagesTableRows.get(i).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				if(accessLvl.equals("Add")) {
					WebElement AddAccess=adminPagesTableRows.get(i).findElement(By.id("checkAddAccess"));
					selectWebElement(AddAccess);
				}
				if(accessLvl.equals("Edit")) {
					WebElement EditAccess=adminPagesTableRows.get(i).findElement(By.id("checkEditAccess"));
					selectWebElement(EditAccess);
				}
				if(accessLvl.equals("Delete")) {
					WebElement DeleteAccess=adminPagesTableRows.get(i).findElement(By.id("checkDeleteAccess"));
					selectWebElement(DeleteAccess);
				}
				if(accessLvl.equals("Export")) {
					WebElement ExportAccess=adminPagesTableRows.get(i).findElement(By.id("checkExportAccess"));
					selectWebElement(ExportAccess);
				}
				break;
			}
			}
		selectWebElement(saveaccess.get(0));
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
	}
	
	public void ProvideReportAccess(String value, String accessLvl) throws Exception {
		Thread.sleep(2000);
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		navigateToReportsTab();
		Thread.sleep(2000);
		for(int i=0;i<reportsTableRows.size();i++) {
			System.out.println(reportsTableRows.get(i).getText());
			if (reportsTableRows.get(i).getText().equals(value))
			{
				WebElement checkAccess=reportsTableRows.get(i).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				if(accessLvl.equals("Export")) {
					WebElement checkExportAccess=reportsTableRows.get(i).findElement(By.id("checkExportAccess"));
					selectWebElement(checkExportAccess);
				}
				break;
			}
			}
		selectWebElement(saveaccess.get(1));
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
	}
	
	public void clearAccess() throws Exception {
		Thread.sleep(500);
		selectWebElement(checkallAccess);
		if(checkallAccess.isSelected()) {
		selectWebElement(checkallAccess);}
		selectWebElement(saveaccess.get(0));
		try {
			selectWebElement(userModifyReasontxtbox);
			Thread.sleep(1000);
			enterValueToTxtField(userModifyReasontxtbox,"Modified");
			Thread.sleep(1000);
			clickOn(useryesBtn);
		}
		catch(Exception e)
		{
			moveToElement(pbuaclose);
			selectWebElement(pbuaclose);	
		}
		
	}
	
	public boolean addNewCancel(String userId) {
		String actualitems=items.getText();
		selectWebElement(addNewUserManagementRecordBtn);
        selectWebElement(userIdTextBox);
        enterValueToTxtField(userIdTextBox,userId);
        selectWebElement(cancelBtn);
        if(actualitems.equals(items.getText()))
        	return true;
        else
		return false;
	}
	public void addUserManagementRecord(String userID) {
        selectWebElement(addNewUserManagementRecordBtn);
        selectWebElement(userIdTextBox);
        enterValueToTxtField(userIdTextBox,userID);
        selectWebElement(saveBtn);
        selectWebElement(cancelBtn);
	}

	public void addinvalidrecord() {
		selectWebElement(addNewUserManagementRecordBtn);
		selectWebElement(saveBtn);
		selectWebElement(cancelBtn);
	}

	public void DuplicateRecord(String userId) {
		selectWebElement(addNewUserManagementRecordBtn);
        selectWebElement(userIdTextBox);
        enterValueToTxtField(userIdTextBox,userId);
        selectWebElement(saveBtn);
        try {
        selectWebElement(cancelBtn);
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

	public boolean editcancel(String UserID, String reason,String user) {
		searchUserManagementRecord(user);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editButton);
        enterValueToTxtField(userIdTextBox,UserID);
        enterValueToTxtField(editModifyReasonTextBox,reason);
        selectWebElement(cancelBtn);
        if(rowdata.getText().equals(user))
        	return true;
        else
        	return false;
        
	}

	public boolean clearAll(UserDetails userDetails) {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Id");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),userDetails.getUserId());
        selectWebElement(clearall);
        if(searchText.get(0).isEnabled())
        	return true;
        else
		return false;
	}

	public boolean verifyclose() {
		selectWebElement(searchClose);
		if(gridcontent.isDisplayed())
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

	public boolean verifyinvalidsearch(UserDetails userDetails) {
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		searchUserManagementRecord(userDetails.getUserId());
		if(norecords.isDisplayed())
		return true; 
		else
			return false;
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

	public boolean groupby() {
		DragandDrop(UserId,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(userIdgroupby.getText()))
		{return true;}
		else
			return false;	
	}

	public Boolean deleteUserManagementRecordNoBtn(String userId, String deleteReason) {
		searchUserManagementRecord(userId);
        selectWebElement(deleteBtn);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtField(deleteReasonTextBox,deleteReason);
        selectWebElement(noBtn);
        if(rowdata.getText().equals(userId))
        	return true;
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
    public boolean verifyTotalNumberOfItemsPerPageDetails(){
        String item = items.getText();
        return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
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
    public boolean verifyExportToExcel(String filePath) {
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "User Management");
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
			nextPageIcon.click();
			waitForJqueryLoad(driver);}
		}
			return arr;
	}

	public void editInvalidUserManagementRecord(String updatedUserId, String user) {
		searchUserManagementRecord(user);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editButton);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtField(userIdTextBox,updatedUserId);
        selectWebElement(saveBtn);	
        selectWebElement(editcancel);
	}

	public void editInvalidUserManagementRecord2(String reason, String userId2) {
		searchUserManagementRecord(userId2);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(editButton);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        enterValueToTxtField(editModifyReasonTextBox,reason);
        selectWebElement(saveBtn);		
        selectWebElement(editcancel);
	}

	public void SortByAscending() {
		selectWebElement(userId);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(userId);
		selectWebElement(userId);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void navigateToReportsTab() {
		selectWebElement(reportsTab);
	}

	public void clearReportAccess() {
		selectWebElement(accessAll.get(1));
		if(accessAll.get(1).isSelected()) {
			selectWebElement(accessAll.get(1));}
			selectWebElement(saveaccess.get(1));
			try {
				selectWebElement(userModifyReasontxtbox);
				Thread.sleep(1000);
				enterValueToTxtField(userModifyReasontxtbox,"Modified");
				Thread.sleep(1000);
				clickOn(useryesBtn);
			}
			catch(Exception e)
			{
				moveToElement(pbuaclose);
				selectWebElement(pbuaclose);	
			}
	}		
	
	public void clearDashboardAccess() {
		selectWebElement(accessAll.get(2));
		if(accessAll.get(2).isSelected()) {
			selectWebElement(accessAll.get(2));}
			selectWebElement(saveaccess.get(2));
			try {
				selectWebElement(userModifyReasontxtbox);
				Thread.sleep(1000);
				enterValueToTxtField(userModifyReasontxtbox,"Modified");
				Thread.sleep(1000);
				clickOn(useryesBtn);
			}
			catch(Exception e)
			{
				moveToElement(pbuaclose);
				selectWebElement(pbuaclose);	
			}
	}

	public void navigateToDashboardTab() {
		selectWebElement(dashboardTab);
		
	}

	public void ProvideDashboardAccess(String value) throws InterruptedException {
		Thread.sleep(2000);
		selectWebElement(tablerow);
		waitForJqueryLoad(driver);
		navigateToDashboardTab();
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		for(int i=0;i<dashboardTableRows.size();i++) {
			System.out.println(dashboardTableRows.get(i).getText());
			if (dashboardTableRows.get(i).getText().equals(value))
			{
				WebElement checkAccess=dashboardTableRows.get(i).findElement(By.id("checkAccess"));
				selectWebElement(checkAccess);
				break;
			}
			}
		selectWebElement(saveaccess.get(2));
		waitForJqueryLoad(driver);
		selectWebElement(userModifyReasontxtbox);
		Thread.sleep(1000);
		enterValueToTxtField(userModifyReasontxtbox,"Modified");
		Thread.sleep(1000);
		selectWebElement(useryesBtn);
	}
	
}
