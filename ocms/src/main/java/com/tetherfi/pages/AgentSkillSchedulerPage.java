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

import com.tetherfi.model.tmac.SkillTemplateDetails;

public class AgentSkillSchedulerPage extends BasePage{

	public AgentSkillSchedulerPage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(css=".ibox-title h5")
	private WebElement agentSkillScheduler;
	
	@FindBy(xpath="//i[@class='fas fa-users-cog']")
	private WebElement AgentSkillSchedulerLogo;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullScreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement restore;

	@FindBy(id="navbarheader")
	private WebElement header;
	
	@FindBy(xpath="//th[@class='k-header k-with-icon']")
	private List<WebElement> agentSkillSchedulerPageHeader;
	
	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;

	@FindBy(id="export")
	private WebElement exportButton;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(xpath="//span[@class='k-input']")
	private List<WebElement> pagerSize;

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;

	@FindBy(id="taddeditskillgrid")
	private WebElement auditGridContent;
	
	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(css=".toast-message")
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

	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;

	@FindBy(css=".k-grid-content")
	private WebElement gridContent;

	@FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;

	@FindBy(xpath="//a[text()='Template Name']")
	private WebElement gridTemplateName;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement dropTarget;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupBy;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[5]")
	private WebElement groupByTemplateName;

	@FindBy(xpath="//a[@aria-label='Go to the last page']")
	private WebElement lastPageIcon;

	@FindBy(css=".k-pager-numbers .k-state-selected")
	private WebElement pageNumber;

	@FindBy(css="a[aria-label='Go to the first page']")
	private WebElement firstPageIcon;

	@FindBy(css="a[aria-label='Go to the previous page']")
	private WebElement previousPageIcon;

	@FindBy(css=".k-pager-sizes .k-icon")
	private WebElement pagerDropdown;

	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;
	
	@FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//span[@class='k-widget k-textbox k-state-disabled']")
    private List<WebElement> searchText;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//span[@aria-controls='1001sColumnName_listbox']")
    private WebElement selectSearchColumn;
    
    @FindBy(xpath="//span[@aria-owns='1001sCriteria_listbox']")
    private WebElement condition;
	
    @FindBy(xpath="//i[@class='fas fa-sync fa-spin']")
    private WebElement clearsearch;
    
    @FindBy(xpath="//li[@id='addeditskilltab']")
    private WebElement skillSchedulerTab;
    
    @FindBy(css="#taddeditskillgrid #create")
    private WebElement AddNewSkillSchedulerRecord;
	
	
	
	
	
	public boolean isAgentSkillSchedulerPageIsDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return agentSkillScheduler.isEnabled();
	}
	
	public boolean verifyLogo() {
		if(isElementExist(AgentSkillSchedulerLogo))
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
	
	public void NavigateToSkillSchedulerTab() {
		waitForJqueryLoad(driver);
		selectWebElement(skillSchedulerTab);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(AddNewSkillSchedulerRecord);
	}
	
	public boolean VerifyAgentSkillSchedulerPageHeader() {
		waitForJqueryLoad(driver);
		NavigateToSkillSchedulerTab();
		List<String>ActualPageHeadrs = new ArrayList<>();
		for(int i=0;i<agentSkillSchedulerPageHeader.size();i++) {
			if(agentSkillSchedulerPageHeader.get(i).isDisplayed()) {
				String HeaderName=agentSkillSchedulerPageHeader.get(i).getText();
				ActualPageHeadrs.add(HeaderName);
			}
			else {System.out.println("AgentSkillSchedulerPageHeader is not Displayed at Index "+i);}
		}
		List<String>ExpectedPageHeadrs = new ArrayList<>();
		ExpectedPageHeadrs.add("Template Name");
		ExpectedPageHeadrs.add("From Time");
		ExpectedPageHeadrs.add("To Time");
		System.out.println(ActualPageHeadrs);
		System.out.println(ExpectedPageHeadrs);
		if(ActualPageHeadrs.equals(ExpectedPageHeadrs))
			return true;
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

	public boolean ExportToExcelButton(String filePath) {
		final File folder=new File(filePath);
		for(final File f : folder.listFiles()) {
			if(f.getName().startsWith("Agent Skill Scheduler")) {
				f.delete();
			}
		}
		selectWebElement(exportButton);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath,"Agent Skill Scheduler");
		return Status;
	}

	public boolean VerifyExportToExcelSheet(List<Map<String,String>>maplist) throws Exception{
		Thread.sleep(2000);
		List<Map<String,String>>UI=getData();
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
			return true;
		else
			return false;
	}

	private List<Map<String,String>> getData() throws InterruptedException{
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
		System.out.println(item);
		int pagersize=Integer.valueOf(pagerSize.get(1).getText());
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
				for(int j=2;j<headers.size();j++) {
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
	
	public void searchSurveyConfiguration(String templateName) throws Exception {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
		selectDropdownFromVisibleText(columnNameList,"Template Name");
		Thread.sleep(1000);
		selectWebElement(selectSearchCol.get(1));
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,templateName);
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
	}

	public boolean VerifyExportToExcelWithoutData(SkillTemplateDetails details) throws Exception {
		searchSurveyConfiguration(details.getTemplatename());
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}

	public void verifySortByAscending() {
		waitUntilWebElementIsVisible(auditGridContent);
		selectWebElement(gridTemplateName);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifySortByDescending() {
		waitUntilWebElementIsVisible(auditGridContent);
		selectWebElement(gridTemplateName);
		selectWebElement(gridTemplateName);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean groupBy() {
		DragandDrop(gridTemplateName,dropTarget);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();	
		}
		if(groupBy.getText().split(": ")[1].equals(groupByTemplateName.getText()))
			return true;
		else
			return false;

	}

	public boolean verifyArrowMoveForPreviousAndNextPage() throws Exception{
		waitForJqueryLoad(driver);
		boolean status=false;
		if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(nextPageIcon);
			Thread.sleep(1000);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(previousPageIcon);
			Thread.sleep(1000);
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}
	public boolean verifyArrowMoveForFirstAndLastPage() throws Exception{
		waitForJqueryLoad(driver);
		boolean status=false;
		if(!lastPageIcon.getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(lastPageIcon);
			Thread.sleep(1000);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(firstPageIcon);
			Thread.sleep(1000);
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}
	
	public boolean verifyTotalNumberOfItemsPerPageDetails(){
		waitForJqueryLoad(driver);
        String item = items.getText();
        return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
    }
	
	public boolean verifyNumberOfItemsPerPage() {
		waitForJqueryLoad(driver);
        boolean status = false;
        try {
                int item = Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
                selectWebElement(pagerDropdown);
                Thread.sleep(1500);
                for (int i = 0; i < pageSizeListBox.size(); i++) {
                    if(Integer.valueOf(pageSizeListBox.get(i).getText())>item){continue;}
                    selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
                    waitForJqueryLoad(driver);
                    int totalItems = Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
                    int pagersize = Integer.valueOf(pagerSize.get(1).getText());
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
           
        } catch (Exception e) {
            e.printStackTrace();
        } return status;
    }
}