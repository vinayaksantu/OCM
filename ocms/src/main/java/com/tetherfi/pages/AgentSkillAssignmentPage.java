package com.tetherfi.pages;

import com.tetherfi.model.user.AgentSkillAssignmentDetails;
import com.tetherfi.model.user.SkillConfigurationDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentSkillAssignmentPage extends BasePage {

    public AgentSkillAssignmentPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement AgentSkillAssignment;

    @FindBy(css=".k-tabstrip-items li")
    private List<WebElement> tabList;

    @FindBy(id="addedittab")
    private WebElement multiSkillTab;

    @FindBy(css = ".k-grid-CustomEdit")
    private WebElement editButton;

    @FindBy(id="popup")
    private WebElement popupContainer;

    @FindBy(css="#tabstrip_skilledit ul[class='k-tabstrip-items k-reset'] li")
    private List<WebElement> skillsTab;

    @FindBy(css="#tabstrip_skilledit ul[role='listbox'] li")
    private List<WebElement> skillsName;

    @FindBy(css="#tabstrip_skilledit div[id='tdrillgrid'] div[data-role='draggable']")
    private List<WebElement> skillsSelected;

    @FindBy(css="#tabstrip_multi ul[class='k-tabstrip-items k-reset'] li")
    private List<WebElement> multiskillsTab;

    @FindBy(css="#tabstrip_multi ul[role='listbox'] li")
    private List<WebElement> multiskillsName;

    @FindBy(css="#tabstrip_multi div[id='mdrillgrid'] div[data-role='draggable']")
    private List<WebElement> multiskillsSelected;

    @FindBy(css="div[class='swal-modal'] input")
    private WebElement skillLevel;

    @FindBy(css="div[class='swal-modal'] button")
    private WebElement okButton;

    @FindBy(css="button[onclick='saveSkills()']")
    private WebElement saveBtn;

    @FindBy(css="button[onclick='saveMultiSkills()']")
    private WebElement saveBtn1;

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

    @FindBy(css = "#1001sCloseButton .k-i-close")
    private WebElement searchRemoveFilterBtn;

    @FindBy(css = ".modal-footer .k-button")
    private WebElement searchCloseBtn;

    @FindBy(css = ".modal-footer .button-danger-theme")
    private WebElement searchClearAllBtn;

    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;

    @FindBy(css=".k-grid-content")
    private WebElement gridContent;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css = "#toast-container .toast-error")
    private List<WebElement> errorMsg;

    @FindBy(css="#progress ul[class='k-reset k-list'] li")
    private List<WebElement> agentList;

    @FindBy(css="#progress div[data-role='draggable']")
    private List<WebElement> agentSelected;
    
    @FindBy(xpath="//button[text()='Export to Excel']")
    private WebElement exporttoexcel;
    
    @FindBy(xpath="//button[text()='Edit Agent List']")
    private WebElement editagentList;
    
    @FindBy(xpath="//i[@class='fas fa-users-cog']")
    private WebElement ASAImg;
    
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
    
    @FindBy(id="tGrid")
    private WebElement auditGridContent;
    
    @FindBy(css="ul[id='1002sColumnName_listbox'] li")
    private List<WebElement> columnNameListtwo;
    
    @FindBy(css="ul[id='1002sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwntwo;
    
    @FindBy(xpath="//a[text()='User Name']")
    private WebElement UserName;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement droptarget;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[5]")
    private WebElement groupbyUserName;
    
    @FindBy(xpath="//tbody/tr/td[2]")
    private WebElement rowdata;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement clearall;
    
    @FindBy(xpath="//button[text()='Close']")
    private WebElement searchClose;
    
    @FindBy(xpath="//div[text()='No records to display']")
    private WebElement norecords;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
    private WebElement clearsearch;
    
    @FindBy(xpath="//a[@class='k-button k-bare k-button-icon k-window-action']")
    private List<WebElement> closebtn;
    
    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    public boolean isAgentSkillAssignmentPageDisplayed() {
        waitForLoad(driver);
        //waitForJqueryLoad(driver);
        return AgentSkillAssignment.isEnabled();
    }
    public void navigateToMultiSkillTab(){
        waitUntilWebElementIsVisible(multiSkillTab);
        waitUntilWebElementIsClickable(multiSkillTab);
        multiSkillTab.click();
        waitForJqueryLoad(driver);
    }
    public void searchAgentListRecord(String username) {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Name");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,username);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }

    public void assignSkillToAgent(AgentSkillAssignmentDetails details) {
        searchAgentListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        waitUntilWebElementListIsVisible(skillsSelected);
        selectSkillName(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(saveBtn);
    }
    public void assignSkillToMultipleAgent(AgentSkillAssignmentDetails details) {
        selectAgent(details.getUsername());
        selectmultiSkillTab(details.getSkillType());
        waitUntilWebElementListIsVisible(multiskillsSelected);
        selectmultiSkillName(details.getSkillName(),details.getSkillLevel());
        clickOn(saveBtn1);
    }
    public void selectSkillTab(String skillType){
        for(WebElement ele:skillsTab){
            if(ele.getText().equalsIgnoreCase(skillType)){
                selectWebElement(ele);break;
            }
        }
    }
    public void selectmultiSkillTab(String skillType){
        for(WebElement ele:multiskillsTab){
            if(ele.getText().equalsIgnoreCase(skillType)){
                selectWebElement(ele);break;
            }
        }
    }
    public void selectSkillName(String skillName){
        for(WebElement ele:skillsName){
            if(ele.getText().equalsIgnoreCase(skillName)){
                Actions builder = new Actions(driver);
                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(ele).release(skillsSelected.get(1)).build();
                dragAndDrop.perform();
                break;
            }
        }
    }
    public void selectmultiSkillName(String skillName, String skLevel){
        String[] a=skillName.split(",");
        String[] b=skLevel.split(",");
        for(String skill:a){
        for(WebElement ele:multiskillsName){
            if(ele.getText().equalsIgnoreCase(skill)){
                Actions builder = new Actions(driver);
                Action dragAndDrop = builder.clickAndHold(ele).moveToElement(ele).release(multiskillsSelected.get(1)).build();
                dragAndDrop.perform();
                break;
            }
        }
        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
        selectWebElement(okButton);
        }
    }
    public void selectAgent(String agent){
        String[] a=agent.split(",");
        for(String agentName:a) {
            for (WebElement ele : agentList) {
                if (ele.getText().contains(agentName)) {
                    Actions builder = new Actions(driver);
                    Action dragAndDrop = builder.clickAndHold(ele).moveToElement(ele).release(agentSelected.get(1)).build();
                    dragAndDrop.perform();
                    break;
                }
            }
        }
    }
    public boolean verifyRecordUpdated(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        waitUntilWebElementIsVisible(successmsg);
        if(successmsg.getText().contains("Skills Assigned Successfully"))
        {return true;}else{return false;}
    }
    
    public boolean isAddBtnDisplayed() {
    	return editagentList.isDisplayed() && editagentList.isEnabled();
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
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }
    public boolean verifylogo() {
		if(isElementExist(ASAImg))
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
		    if (f.getName().startsWith("Agent Skill Assignment")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Agent Skill Assignement");
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
	public boolean ExporttoExcelWithoutData(AgentSkillAssignmentDetails details) {
		searchAgentListRecord(details.getUsername());
		waitForJqueryLoad(driver);
		selectWebElement(exporttoexcel);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}
	
	public void SortByAscending() {
		selectWebElement(UserName);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(UserName);
		selectWebElement(UserName);
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
		DragandDrop(UserName,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyUserName.getText()))
		{return true;}
		else
			return false;		
	}
    
    public Boolean VerifySkill(String SkillName){
    	Boolean Status=false;
    	selectmultiSkillTab("Voice Skills");
    	for(WebElement ele:multiskillsName){
            if(ele.getText().equalsIgnoreCase(SkillName))
            	Status= true;
            }
    	return Status;
    	
    	
    }
}
