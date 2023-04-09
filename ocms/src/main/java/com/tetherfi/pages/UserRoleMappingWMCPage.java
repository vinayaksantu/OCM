package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.user.UserRoleMappingDetails;

public class UserRoleMappingWMCPage extends BasePage {

	public UserRoleMappingWMCPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
    private WebElement userRoleMapping;
	
	@FindBy(xpath="//i[@class='fa  fa-users']")
	private WebElement IMAImage;
	
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
	
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullScreen;
	
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement restore;
	
	@FindBy(id="navbarheader")
	private WebElement header;
	
	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;
	
	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;
	
	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;
	
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
	
	@FindBy(id="create")
    private WebElement addNewUserRoleMappingRecordBtn;
	
	@FindBy(css=".k-grid-cancel")
	private WebElement cancelBtn;
	
	@FindBy(id="UserName")
    private WebElement usernameTextBox;

    @FindBy(id="FirstName")
    private WebElement firstnameTextBox;

    @FindBy(id="LastName")
    private WebElement lastnameTextBox;
    
    @FindBy(css=".form-group .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private WebElement avayaLoginIDTextbox;

    @FindBy(css=".k-edit-form-container .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private WebElement editAvayaLoginIDTextbox;
    
    @FindBy(css="span[class='k-widget k-dropdowntree k-dropdowntree-clearable']")
    private WebElement TeamNameDropDown;
    
    @FindBy(css="span[aria-owns='Profile_listbox']")
    private WebElement profilesDropdown;

    @FindBy(css="ul[id='Profile_listbox'] li")
    private List<WebElement> profilesListBox;
    
    @FindBy(css="#panelSupervisor .k-dropdown .k-select")
    private WebElement supervisorDropdown;

    @FindBy(css="span[aria-owns='SupervisorID_listbox']")
    private WebElement editsupervisorDropdown;

    @FindBy(css="ul[id='SupervisorID_listbox'] li")
    private List<WebElement> supervisorListBox;

    @FindBy(css=".k-edit-form-container .k-multiselect")
    private WebElement RoleDropDown;

    @FindBy(css="ul[id=Role_listbox] li")
    private List<WebElement> RoleListBox;
    
    @FindBy(css=".k-edit-buttons .k-grid-update")
    private WebElement editFormSaveBtn;
    
    @FindBy(css=".k-treeview-lines li div")
    private List<WebElement> teamList;
    
    @FindBy(css="#toast-container .toast-error .toast-message")
    private List<WebElement> errorMsg;
    
    @FindBy(css="#toast-container .toast-message")
	private WebElement successmsg;
    
    @FindBy(css="#gridDiv2 .search-link")
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
    private WebElement popupSearchBtn;
    
    @FindBy(css=".k-grid-edit")
    private WebElement editBtn;
    
    @FindBy(css="#drillGrid tbody tr td")
    private List<WebElement> editrowdata;
    
    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;
    
    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;

    @FindBy(id="yesButton")
    private WebElement yesBtn;
    
    @FindBy(id="noButton")
    private WebElement noBtn;
    
    @FindBy(css="#drillGrid .k-grid-content")
    private WebElement gridContent;
	
    @FindBy(css=".k-edit-form-container #ModifyReason")
    private  WebElement modifyReasonTextBox;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
    
    @FindBy(css=".k-pager-sizes .k-input")
    private WebElement pagerSize;
    
    @FindBy(id = "drillGrid")
    private WebElement grid;
    
    @FindBy(css="a[aria-label='Go to the next page']")
    private WebElement nextPageIcon;
    
    @FindBy(xpath="//a[text()='First Name']")
    private WebElement FirstName;
    
    @FindBy(css = "#drillGrid .k-grouping-header")
    private WebElement dragColumnDestination;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
    private WebElement clearsearch;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
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
    
    @FindBy(xpath="//span[@aria-controls='1001sColumnName_listbox']")
    private WebElement SelectSearchColumn;
    
    @FindBy(xpath="//span[@aria-owns='1001sCriteria_listbox']")
    private WebElement condition;
    
    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;
    
    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;
    
    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
    
    @FindBy(id="grid")
    private WebElement auditGridContent;
    
    @FindBy(css="#userGrid")
    private WebElement auditGrid;
    
	
	
	
	
	
	
	
	public boolean isUserRoleMappingMappingPageDisplayed() {
		waitForLoad(driver);
        waitForJqueryLoad(driver);
		return userRoleMapping.isEnabled();
	}
	
	public boolean verifyLogo() {
		if(isElementExist(IMAImage))
		 {return true;}
		 else
		 {return false;}
	}
	
	public boolean maximizeWindow() {
		selectWebElement(maximize);
		waitForJqueryLoad(driver);
		if(fullScreen.isEnabled())
		{return true;}
		else
		{return false;}
	}
	
	public boolean minimizeWindow() {
		selectWebElement(restore);
		waitForJqueryLoad(driver);
		if(header.isDisplayed())
		{return true;}
		else
		{return false;}	
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
	
	public boolean VerifyColumnsHeadersEnable() {
		boolean status=false;
		WebElement ele=headersDropdown.get(0);
		if(ele.isDisplayed()) {
			try {
				selectWebElement(ele);
				Thread.sleep(1000);
				selectWebElement(headersColumns.get(2));
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=3; i<headersColumns.size(); i++) {
				WebElement checkbox=headersColumns.get(i).findElement(By.tagName("input"));
				checkbox.click();
				if(checkbox.isSelected()) { 
				}
			else {
				checkbox.click();
			}
		    for(WebElement ele1 : headersText) {
		    	if(ele1.getText().equals(headersColumns.get(i).getText())) {
		    		status=true;
		    		break;
		    	}
		    }
		    	if(status) {
		    	}
		    	else {
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
	
	public boolean addCancelButton(UserRoleMappingDetails UserRoleMappingDetails) {
		
		String actualitems=items.getText();
		selectWebElement(addNewUserRoleMappingRecordBtn);
		waitForJqueryLoad(driver);
        selectWebElement(cancelBtn);
        if(actualitems.equals(items.getText()))
        	return true;
        else
		return false;
	}
	
	public void addNewUserRoleMappingRecord(UserRoleMappingDetails details) throws Exception {
        selectWebElement(addNewUserRoleMappingRecordBtn);
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtField(lastnameTextBox,details.getLastname());
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getBankUserName());
        //enterValueToTxtFieldWithoutClear(editAvayaLoginIDTextbox,details.getAvayaLoginID());
        selectWebElement(TeamNameDropDown);
        Thread.sleep(3000);
        ChooseTeamHeirarchy(details.getTeamName());
        editProfile(details.getProfile(),details.getSupervisor());        
        selectWebElement(RoleDropDown);
        selectDropdownFromVisibleText(RoleListBox,details.getRole());
        btnClick(editFormSaveBtn);
    }
	
	public String verifySuccessMessage(){
        waitUntilWebElementIsVisible(successmsg);return successmsg.getText();
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
	
    public void editProfile(String profile, String supervisor) throws Exception{
        selectWebElement(profilesDropdown);
        selectDropdownFromVisibleText(profilesListBox,profile);
        Thread.sleep(1000);
        editSupervisor(supervisor);
    }
    
    public void editSupervisor(String supervisor) throws Exception{
        selectWebElement(editsupervisorDropdown);
        Thread.sleep(1000);
        for(WebElement ele: supervisorListBox){
            try{if(ele.getText().equalsIgnoreCase(supervisor)){ele.click();break;}}catch(Exception e){
                System.out.println("element not found");
            }
        }
    }
    
    public void addwithoutFirstName(UserRoleMappingDetails details) throws Exception {
    	selectWebElement(addNewUserRoleMappingRecordBtn);
        selectWebElement(lastnameTextBox);
        enterValueToTxtField(lastnameTextBox,details.getLastname());
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getBankUserName());
       // enterValueToTxtFieldWithoutClear(editAvayaLoginIDTextbox,details.getAvayaLoginID());
        selectWebElement(TeamNameDropDown);
        try {
        	Thread.sleep(1000);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        ChooseTeamHeirarchy(details.getTeamName());
        editProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(RoleDropDown);
        selectDropdownFromVisibleText(RoleListBox,details.getRole());
        btnClick(editFormSaveBtn);
        selectWebElement(cancelBtn);
	}
    
	public void addwithoutLastName(UserRoleMappingDetails details) throws Exception {
		selectWebElement(addNewUserRoleMappingRecordBtn);
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getFirstname());
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getBankUserName());
       // enterValueToTxtFieldWithoutClear(editAvayaLoginIDTextbox,details.getAvayaLoginID());
        selectWebElement(TeamNameDropDown);
        try {
        	Thread.sleep(1000);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        ChooseTeamHeirarchy(details.getTeamName());
        editProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(RoleDropDown);
        selectDropdownFromVisibleText(RoleListBox,details.getRole());
        btnClick(editFormSaveBtn);
        selectWebElement(cancelBtn);		
	}
	
	public void addwithoutLanID(UserRoleMappingDetails details) throws Exception {
		selectWebElement(addNewUserRoleMappingRecordBtn);
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtField(lastnameTextBox,details.getLastname());
       // enterValueToTxtFieldWithoutClear(editAvayaLoginIDTextbox,details.getAvayaLoginID());
        selectWebElement(TeamNameDropDown);
        try {
        	Thread.sleep(1000);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        ChooseTeamHeirarchy(details.getTeamName());
        editProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(RoleDropDown);
        selectDropdownFromVisibleText(RoleListBox,details.getRole());
        btnClick(editFormSaveBtn);
        selectWebElement(cancelBtn);		
	}
	
	public void addwithoutLoginID(UserRoleMappingDetails details) throws Exception {
		selectWebElement(addNewUserRoleMappingRecordBtn);
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtField(lastnameTextBox,details.getLastname());
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getBankUserName());
        selectWebElement(TeamNameDropDown);
        try {
        	Thread.sleep(1000);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        ChooseTeamHeirarchy(details.getTeamName());
        editProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(RoleDropDown);
        selectDropdownFromVisibleText(RoleListBox,details.getRole());
        btnClick(editFormSaveBtn);
        selectWebElement(cancelBtn);		
	}
	
	public void addwithoutProfile(UserRoleMappingDetails details) throws Exception {
		selectWebElement(addNewUserRoleMappingRecordBtn);
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtField(lastnameTextBox,details.getLastname());
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getBankUserName());
       // enterValueToTxtFieldWithoutClear(editAvayaLoginIDTextbox,details.getAvayaLoginID());
        selectWebElement(TeamNameDropDown);
        try {
        	Thread.sleep(1000);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        ChooseTeamHeirarchy(details.getTeamName());
        //editProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(RoleDropDown);
        selectDropdownFromVisibleText(RoleListBox,details.getRole());
        btnClick(editFormSaveBtn);
        selectWebElement(cancelBtn);		
	}
	
	public void addwithoutSupervisor(UserRoleMappingDetails details) throws Exception {
		selectWebElement(addNewUserRoleMappingRecordBtn);
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtField(lastnameTextBox,details.getLastname());
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getBankUserName());
       // enterValueToTxtFieldWithoutClear(editAvayaLoginIDTextbox,details.getAvayaLoginID());
        selectWebElement(profilesDropdown);
        selectWebElement(TeamNameDropDown);
        try {
        	Thread.sleep(1000);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        ChooseTeamHeirarchy(details.getTeamName());
        //editProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(RoleDropDown);
        selectDropdownFromVisibleText(RoleListBox,details.getRole());
        btnClick(editFormSaveBtn);
        selectWebElement(cancelBtn);		
	}
	
	public void addwithoutRole(UserRoleMappingDetails details) throws Exception {
		selectWebElement(addNewUserRoleMappingRecordBtn);
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtField(lastnameTextBox,details.getLastname());
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getBankUserName());
       // enterValueToTxtFieldWithoutClear(editAvayaLoginIDTextbox,details.getAvayaLoginID());
        selectWebElement(TeamNameDropDown);
        try {
        	Thread.sleep(1000);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        ChooseTeamHeirarchy(details.getTeamName());
        editProfile(details.getProfile(),details.getSupervisor());
        btnClick(editFormSaveBtn);
        selectWebElement(cancelBtn);		
	}
	
	public void addwithoutOrgUnit(UserRoleMappingDetails details) throws Exception {
		selectWebElement(addNewUserRoleMappingRecordBtn);
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtField(lastnameTextBox,details.getLastname());
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getBankUserName());
       // enterValueToTxtFieldWithoutClear(editAvayaLoginIDTextbox,details.getAvayaLoginID());
        editProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(RoleDropDown);
        selectDropdownFromVisibleText(RoleListBox,details.getRole());
        btnClick(editFormSaveBtn);
        selectWebElement(cancelBtn);		
	}
	
	public boolean getErrorMsg() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0)
		return false;
		else
			return true;
	}
	
	public void searchUserRoleMappingRecord(String bankUsername) throws Exception  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Lan ID");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),bankUsername);
        selectWebElement(popupSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
	
	public boolean EditCancel(UserRoleMappingDetails details) throws Exception {
		waitForJqueryLoad(driver);
		searchUserRoleMappingRecord(details.getBankUserName());
		selectWebElement(editBtn);
		waitForJqueryLoad(driver);
		selectWebElement(cancelBtn);
		if(editrowdata.get(3).getText().equals(details.getBankUserName()))
			return true;
		else
		return false;
	}
	
	public void EditRecordWithoutModifyReason(UserRoleMappingDetails details) throws Exception {
		searchUserRoleMappingRecord(details.getBankUserName());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(firstnameTextBox);
        enterValueToTxtField(firstnameTextBox,details.getUpdatedFirstname());
        btnClick(editFormSaveBtn);			
	}
	
	 public void editUserRoleMappingRecord(UserRoleMappingDetails details) throws Exception {
	        searchUserRoleMappingRecord(details.getBankUserName());
	        selectWebElement(editBtn);
	        waitForJqueryLoad(driver);
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        selectWebElement(firstnameTextBox);
	        enterValueToTxtField(firstnameTextBox,details.getUpdatedFirstname());
	        enterValueToTxtFieldWithoutClear(modifyReasonTextBox,details.getModifyReason());
	        btnClick(editFormSaveBtn);
	    }
	 
	 public boolean verifyExportToExcel(String filePath) {
			final File folder = new File(filePath);
			for (final File f : folder.listFiles()) {
			    if (f.getName().startsWith("User Role Mapping")) {
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
			Boolean Status=verifyExportPageFileDownload(filePath, "User Role Mapping");
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
			waitUntilWebElementIsVisible(grid);
			List<WebElement> rows=grid.findElements(By.tagName("tr"));
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
		
		public boolean ExporttoExcelWithoutData(UserRoleMappingDetails details) throws Exception {
			searchUserRoleMappingRecord(details.getBankUserName());
			waitForJqueryLoad(driver);
			Thread.sleep(1000);
			selectWebElement(exporttoexcel);
			waitUntilWebElementListIsVisible(errorMsg);
			if(errorMsg.get(0).getText().equals("There is no record to export"))
				return true;
			else
			return false;
		}
		
		public void SortByAscending() {
			selectWebElement(FirstName);
			selectWebElement(exporttoexcel);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void SortByDescending() {
			selectWebElement(FirstName);
			selectWebElement(FirstName);
			selectWebElement(exporttoexcel);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
	    
	    public boolean clearAll(UserRoleMappingDetails details) throws Exception {
			selectWebElement(searchLink);
	        selectWebElement(selectSearchColumn.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Lan ID");
	        selectWebElement(selectSearchColumn.get(1));
	        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
	        enterValueToTxtField(searchText.get(0),details.getBankUserName());
		    selectWebElement(clearall);
				if(searchText.get(0).isEnabled())
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

		public void searchwithoutextsearch(UserRoleMappingDetails details) {
			selectWebElement(searchLink);
	        selectWebElement(selectSearchColumn.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Lan ID");
	        selectWebElement(selectSearchColumn.get(1));
	        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
	        selectWebElement(popupSearchBtn);	
	        selectWebElement(searchClose);
		}


		public boolean verifyclearsearch() {
			selectWebElement(clearsearch);
			if(gridContent.isDisplayed())
				return true;
			else
			return false;
		}
	
		public boolean verifyinvalidsearchwithwrongdata(UserRoleMappingDetails details) throws Exception {
			searchUserRoleMappingRecord(details.getBankUserName());
			if(norecords.isDisplayed())
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
	
	    public boolean DeleteCancel(UserRoleMappingDetails details) throws Exception {
			searchUserRoleMappingRecord(details.getBankUserName());
			selectWebElement(deleteBtn);
			waitForJqueryLoad(driver);
			selectWebElement(noBtn);
			if(editrowdata.get(3).getText().equals(details.getBankUserName()))
				return true;
			else
			return false;
		}
	    
	    public void DeleteRecordWithoutModifyReason(UserRoleMappingDetails details) throws Exception {
			searchUserRoleMappingRecord(details.getBankUserName());
			Thread.sleep(1000);
			selectWebElement(deleteBtn);
			Thread.sleep(1000);
	        selectWebElement(deleteReasonTextBox);
	        selectWebElement(yesBtn);		
	        selectWebElement(noBtn);
		}
	    
	    public void DeleteUserRoleMappingRecord(UserRoleMappingDetails details) throws Exception {
			searchUserRoleMappingRecord(details.getBankUserName());
			Thread.sleep(1000);
			selectWebElement(deleteBtn);
			Thread.sleep(1000);
	        selectWebElement(deleteReasonTextBox);
	        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
	        selectWebElement(yesBtn);				
		}
	    
	    public boolean verifySearchIsnotEqualTo(String supervisorname) throws Exception {
			 boolean Status=false;
			 Map<String,String>map=new HashMap<String,String>();
			 map.put("Supervisor Name", supervisorname);
			 selectWebElement(searchLink);
			 selectWebElement(SelectSearchColumn);
			 selectDropdownFromVisibleText(columnNameList,"Supervisor Name");
			 Thread.sleep(1000);
			 selectWebElement(condition);
			 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
			 enterValueToTxtField(searchTextBox,supervisorname);		
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
		 
		 public boolean verifySearchContains(String supervisorname) throws Exception {
			 boolean Status=false;
			 Map<String,String>map=new HashMap<String,String>();
			 map.put("Supervisor Name", supervisorname);
			 selectWebElement(searchLink);
			 selectWebElement(SelectSearchColumn);
			 selectDropdownFromVisibleText(columnNameList,"Supervisor Name");
			 Thread.sleep(1000);
			 selectWebElement(condition);
			 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
			 enterValueToTxtField(searchTextBox,supervisorname);		
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
		 
		 public boolean verifySearchDoesNotContains(String supervisorname) throws Exception {
			 boolean Status=false;
			 Map<String,String>map=new HashMap<String,String>();
			 map.put("Supervisor Name", supervisorname);
			 selectWebElement(searchLink);
			 selectWebElement(SelectSearchColumn);
			 selectDropdownFromVisibleText(columnNameList,"Supervisor Name");
			 Thread.sleep(1000);
			 selectWebElement(condition);
			 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not Contain");
			 enterValueToTxtField(searchTextBox,supervisorname);		
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
		 
		 public boolean verifySearchStartsWith(String supervisorname) throws Exception {
			 boolean Status=false;
			 Map<String,String>map=new HashMap<String,String>();
			 map.put("Supervisor Name", supervisorname);
			 selectWebElement(searchLink);
			 selectWebElement(SelectSearchColumn);
			 selectDropdownFromVisibleText(columnNameList,"Supervisor Name");
			 Thread.sleep(1000);
			 selectWebElement(condition);
			 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
			 enterValueToTxtField(searchTextBox,supervisorname);		
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
		 
		 public boolean verifySearchEndsWith(String supervisorname) throws Exception {
			 boolean Status=false;
			 Map<String,String>map=new HashMap<String,String>();
			 map.put("Supervisor Name", supervisorname);
			 selectWebElement(searchLink);
			 selectWebElement(SelectSearchColumn);
			 selectDropdownFromVisibleText(columnNameList,"Supervisor Name");
			 Thread.sleep(1000);
			 selectWebElement(condition);
			 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
			 enterValueToTxtField(searchTextBox,supervisorname);		
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
		 public List<Map<String, String>> gettable() {
				int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
		        int pagersize=Integer.valueOf(pagerSize.getText());
		        int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
				List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
				for(int k=0;k<=pages;k++){

				waitUntilWebElementIsVisible(auditGrid);
				List<WebElement> rows=auditGrid.findElements(By.tagName("tr"));
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
					nextPageIcon.click();
					waitForJqueryLoad(driver);}
				}
					return arr;
			}
	
	
	
	
}
