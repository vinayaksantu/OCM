package com.tetherfi.pages;

import com.tetherfi.model.ivr.FaxApplicationFormDetails;
import com.tetherfi.model.ivr.IvrConfigDetails;
import com.tetherfi.model.ivr.VipListManagementDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IvrConfigPage extends BasePage {

    public IvrConfigPage(WebDriver driver) {
        super(driver);
    }

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

    @FindBy(css=".ibox-title h5")
    private WebElement ivrConfig;
    
    @FindBy(xpath="//i[@class='fas fa-cog']")
	private WebElement IvrCImg;

    @FindBy(css="#create")
    private WebElement addNewIVRConfigRcrdBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css="span[aria-owns='Parameter_listbox']")
    private WebElement parameterDropdown;

    @FindBy(css="#Parameter-list .k-list-optionlabel")
    private List<WebElement> selectParameter;

    @FindBy(css="ul[id='Parameter_listbox'] li")
    private List<WebElement> parameterListBox;

    @FindBy(css="span[aria-owns='Value_listbox']")
    private WebElement valueDropdown;

    @FindBy(css="#Value-list .k-list-optionlabel")
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
	private WebElement pagerSize;
	    
	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;
	    
	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupby;
	    
	@FindBy(css="a[aria-label='Go to the first page']")
	private WebElement firstPageIcon;
	    
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
	    
	@FindBy(xpath="//i[@class='fas fa-sync']")
	private WebElement clearsearch;
	
	@FindBy(css=".k-pager-numbers .k-state-selected")
	private WebElement pageNumber;
	
	@FindBy(xpath="//a[text()='Parameter']")
	private WebElement parameter;
		    
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
		    
	@FindBy(xpath="//tbody/tr/td[6]")
	private WebElement rowdata;
		    
	@FindBy(xpath="//span[@class='k-icon k-i-arrow-60-right k-menu-expand-arrow']")
	private WebElement coloumnarrow;
		    
	@FindBy(id="tGrid")
	private WebElement auditGridContent;
		    
	@FindBy(xpath="//a[text()='Wav File']")
	private WebElement WavFile;

    public boolean isIvrConfigPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return ivrConfig.isEnabled();
    }
    public void addNewIvrConfigRecord(IvrConfigDetails details) {
        selectWebElement(addNewIVRConfigRcrdBtn);
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
        try {
        	selectWebElement(cancelBtn);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    public void searchIvrConfigRecord(String column,String value) throws Exception {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,column);
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,value);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editIvrConfigRecord(IvrConfigDetails details) throws Exception {
        searchIvrConfigRecord(details.getSearchColumn(),details.getSearchValue());
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
    public void deleteIvrConfigRecord(IvrConfigDetails details) throws Exception {
        searchIvrConfigRecord(details.getSearchColumn(),details.getSearchValue());
        selectWebElement(deleteButton);
        enterValueToTxtField(deleteReasonTextBox,details.getDeleteReason());
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
	public boolean ExporttoExcelWithoutData(IvrConfigDetails ivrConfigDetails) throws Exception {
		searchIvrConfigRecord("Parameter",ivrConfigDetails.getParameter());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}
	
	public void SortByAscending() {
		selectWebElement(parameter);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(parameter);
		selectWebElement(parameter);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Parameter");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,details.getParameter());
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
	public void searchwithoutextsearch() {
		selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Parameter");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        selectWebElement(searchSearchBtn);	
		selectWebElement(searchClose);		
	}
	public boolean verifyinvalidsearchwithwrongdata(IvrConfigDetails ivrConfigDetails) throws Exception {
		searchIvrConfigRecord("Parameter",ivrConfigDetails.getParameter());
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
	
}
