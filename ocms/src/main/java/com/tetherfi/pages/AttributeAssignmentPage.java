package com.tetherfi.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.tmac.AttributeAssignmentDetails;
import com.tetherfi.model.user.AgentSkillAssignmentNewDetails;

public class AttributeAssignmentPage extends BasePage {

	public AttributeAssignmentPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
	private WebElement attributeAssignment;

	@FindBy(xpath="//i[@class='far fa-plus-square']")
	private WebElement AttributesAssignmentLogo;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullScreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement restore;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(xpath="//span[text()='Select a Team']")
	private WebElement selectTeamDropDown;

	@FindBy(xpath="//label[@for='AgentList']")
	private WebElement agentListLabel;

	@FindBy(xpath="//label[@for='SelectedAgentList']")
	private WebElement selectedAgentListLabel;

	@FindBy(css="ul[id='tabstrip_multi_ul'] li")
	private List<WebElement> AttributeCategories;

	@FindBy(xpath="//i[@class='navbar-icons fal fa-desktop']")
	private WebElement ocmTab;

	@FindBy(css="a[href$='/Attributes/Index']")
	private WebElement attributes;

	@FindBy(css=".ibox-title h5")
	private WebElement attributesTitle;

	@FindBy(xpath="//button[@id='create']")
	private WebElement addNewAttributesRecordButton;

	@FindBy(xpath="//span[@aria-owns='Category_listbox']")
	private WebElement CategoryListBox;

	@FindBy(css="ul[id='Category_listbox'] li")
	private List<WebElement> categoryListBoxDropDown;

	@FindBy(css="ul[id='tabstrip_multi_ul'] li")
	private List<WebElement> AttributesAssignmentCategories;

	@FindBy(xpath="//button[text()='View Assignment']")
	private WebElement viewAssignmentButton;

	@FindBy(xpath="//button[text()='Save']")
	private WebElement saveButton;

	@FindBy(xpath="//button[text()='Clear']")
	private WebElement clearButton;

	@FindBy(css="#multiskill ul[role='listbox'] li")
	private List<WebElement> multiAgentList;

	@FindBy(css="#tabstrip_multi ul[role='listbox'] li")
	private List<WebElement> multipleAttributes;


	@FindBy(xpath="//a[@class='k-button k-button-icon']")
	private List<WebElement> transferToSelectedList;

	@FindBy(xpath="//span[@aria-owns='TeamID_listbox']")
	private WebElement selectTeam;

	@FindBy(css="ul[id='TeamID_listbox'] li")
	private List<WebElement> listofTeams;

	@FindBy(xpath="//select[@id='AgentList']/option")
	private List<WebElement> multipleSelectedAgentList;

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(css=".toast-message")
	private WebElement successmsg;

	@FindBy(xpath="//*[@id=\"multiskill\"]/div/div[1]/div[2]/ul/li")
	private List<WebElement> agentList;

	@FindBy(xpath="//*[@id=\"multiskill\"]/div/div[2]/div[1]/ul/li")
	private List<WebElement> selectedAgentList;

	@FindBy(xpath="//span[contains(text(),'Select a Team')]")
	private WebElement selectTeamLabel;

	@FindBy(css="#tabstrip_multi ul[class='k-tabstrip-items k-reset'] li")
	private List<WebElement> attributeTabs;

	@FindBy(css="div[class='swal-modal'] input")
	private WebElement attributeLevel;

	@FindBy(css="div[class='swal-modal'] button")
	private WebElement okButton;

	@FindBy(xpath="//h3[text()='Agent Attribute Details']")
	private WebElement AgentattributeDetailsHeader;

	@FindBy(xpath="//div[@id='export']")
	private WebElement ExporttoExcel;

	@FindBy(xpath="//input[@placeholder='Search...']")
	private WebElement searchAttributeDetails;

	@FindBy(xpath="//a[normalize-space()='Attribute Name']")
	private WebElement AttributeNameColumn;

	@FindBy(xpath="//a[normalize-space()='Attribute Level']")
	private WebElement AttributeLevelColumn;

	@FindBy(xpath="//div[@id='popupGoalMap']//span[@aria-hidden='true'][normalize-space()='×']")
	private WebElement AttributesDetailsCloseButton;

	@FindBy(xpath="//th[@class='k-header k-with-icon']")
	private List<WebElement> attributesPageHeader;

	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;

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

	@FindBy(xpath="//*[@id=\"goalmappinggird\"]/div[4]/span[1]/span/span/span[1]")
	private WebElement pagerSize;

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private List<WebElement> items;

	@FindBy(css=".k-grid-content ")
	private WebElement gridContent;

	@FindBy(xpath = "//a[@class='k-button k-button-icontext k-grid-edit']")
	private WebElement editButton;

	@FindBy(xpath="//input[@id='channelMultiSearchBox']")
	private WebElement channelAttributeSearchBox;

	@FindBy(xpath="//input[@id='intentMultiSearchBox']")
	private WebElement intentAttributeSearchBox;

	@FindBy(xpath="//input[@id='packageMultiSearchBox']")
	private WebElement packageAttributeSearchBox;

	@FindBy(xpath="//input[@id='customertypeMultiSearchBox']")
	private WebElement customerTypeAttributeSearchBox;

	@FindBy(xpath="//input[@id='ageMultiSearchBox']")
	private WebElement ageAttributeSearchBox;

	@FindBy(xpath="//input[@id='genderMultiSearchBox']")
	private WebElement genderAttributeSearchBox;

	@FindBy(xpath="//input[@id='locationMultiSearchBox']")
	private WebElement loactionAttributeSearchBox;

	@FindBy(xpath="//input[@id='sentimentMultiSearchBox']")
	private WebElement sentimentAttributeSearchBox;

	@FindBy(xpath="//input[@id='languageMultiSearchBox']")
	private WebElement languageAttributeSearchBox;

	@FindBy(css="#tabstrip_multi ul[role='listbox'] li")
	private List<WebElement> SearchAttributeTabData;









	public boolean isAttributeAssignmentPageIsDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return attributeAssignment.isEnabled();
	}

	public boolean verifyLogo() {
		if(isElementExist(AttributesAssignmentLogo))
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

	public void VerifyAttributeAssignmentPage() {
		waitForJqueryLoad(driver);
	}

	public void navigateToAttributesPageCategeoryDropDownValues(){
		waitForJqueryLoad(driver);
		selectWebElement(ocmTab);
		waitForLoad(driver);
		waitUntilWebElementIsClickable(attributes);
		selectWebElement(attributes);
		waitUntilWebElementIsVisible(attributesTitle);
		waitForJqueryLoad(driver);
		selectWebElement(addNewAttributesRecordButton);
		waitForJqueryLoad(driver);
		selectWebElement(CategoryListBox);
	}


	public boolean VerifyAttributeAssignmentPageCategories() {
		waitForJqueryLoad(driver);
		List<String>AttributeAssignmentModuleCategoriesTab = new ArrayList<>();
		for(int i=0;i<AttributesAssignmentCategories.size();i++) {
			waitUntilWebElementIsVisible(AttributesAssignmentCategories.get(i));
			String AttributecategoryValues=AttributesAssignmentCategories.get(i).getText();
			AttributeAssignmentModuleCategoriesTab.add(AttributecategoryValues);
		}
		navigateToAttributesPageCategeoryDropDownValues();
		List<String>AttributeModuleCategories = new ArrayList<>();
		for(int i=0;i<categoryListBoxDropDown.size();i++) {
			waitUntilWebElementIsVisible(categoryListBoxDropDown.get(i));
			String categoryValues=categoryListBoxDropDown.get(i).getText();
			AttributeModuleCategories.add(categoryValues);
		}
		System.out.println(AttributeAssignmentModuleCategoriesTab);
		System.out.println(AttributeModuleCategories);
		if(AttributeAssignmentModuleCategoriesTab.equals(AttributeModuleCategories))
			return true;
		else
			return false;
	}

	public boolean VerifythePresenceOfAttributesAssignmentPage() {
		waitForJqueryLoad(driver);
		boolean status=false;
		if(selectTeamDropDown.isEnabled()) {
			if(agentListLabel.isDisplayed()) {
				if(selectedAgentListLabel.isDisplayed()) {
					if(viewAssignmentButton.isEnabled()) {
						if(saveButton.isEnabled()) {
							if(clearButton.isEnabled()) {
								status=true;
							}
							else {System.out.println("clearButton is Disabled ");}
						}
						else {System.out.println(" saveButton is Disabled");}
					}
					else {System.out.println("viewAssignmentButton is Disabled ");}
				}
				else {System.out.println("selectedAgentListLabel is not Displayed ");}
			}
			else {System.out.println(" agentListLabel is not Displayed");}
		}
		else {System.out.println(" selectTeamDropDown is Disabled");}

		return status;
	}

	public void selectAgent(String agent){
		System.out.println(agent);
		String[] a=agent.split(",");
		for(String agentName:a) {
			for (WebElement ele : agentList) {                
				scrollToElement(ele);
				if (ele.getText().contains(agentName)) {
					selectWebElement(ele);
					selectWebElement(transferToSelectedList.get(0));
					break;
				}
			}
		}

	}

	public void selectSelectedAgent(String agent){
		System.out.println(agent);
		String[] a=agent.split(",");
		for(String agentName:a) {
			for (WebElement ele : selectedAgentList) {
				scrollToElement(ele);
				if (ele.getText().contains(agentName)) {
					selectWebElement(ele);
					selectWebElement(transferToSelectedList.get(1));
					break;
				}
			}
		}

	}

	public boolean verifyTransferToANDFromButton(AttributeAssignmentDetails details) throws Exception {
		selectWebElement(selectTeam);
		selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		Thread.sleep(2000);
		selectAgent(details.getAgentList());
		waitForJqueryLoad(driver);
		List<String>actualselectedAgentList = new ArrayList<>();
		for(int i=0;i<selectedAgentList.size();i++) {
			waitUntilWebElementIsVisible(selectedAgentList.get(i));
			String selectedAgentValues=selectedAgentList.get(i).getText();
			actualselectedAgentList.add(selectedAgentValues);
		}
		List<String>ExpectedAgentList = new ArrayList<>();
		String[] a=details.getAgentList().split(",");
		for(String agentName:a) {
			ExpectedAgentList.add(agentName);
		}
		System.out.println(actualselectedAgentList);
		System.out.println(ExpectedAgentList);
		selectSelectedAgent(details.getAgentList());
		List<String>actualAgentList = new ArrayList<>();
		for(String agentName:a) {
			for (WebElement ele : agentList) {
				scrollToElement(ele);
				if (ele.getText().contains(agentName)) {
					actualAgentList.add(ele.getText());
					break;
				}
			}
		}
		System.out.println(actualAgentList);
		System.out.println(ExpectedAgentList);
		boolean status=false;
		if(actualselectedAgentList.equals(ExpectedAgentList)) {
			if(actualAgentList.equals(ExpectedAgentList)) {
				status=true;
			}
			else {System.out.println("Transfer From Button Data Mismatch");}
		}
		else {System.out.println("Transfer To Button Data Mismatch");}
		return status;
	}

	public boolean verifyBulkTransferToANDFromButton(AttributeAssignmentDetails details) throws Exception {
		selectWebElement(selectTeam);
		selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		Thread.sleep(2000);
		List<String>AgentListBeforeTransferTo = new ArrayList<>();
		for(int i=0;i<agentList.size();i++) {
			waitUntilWebElementIsVisible(agentList.get(i));
			String selectedAgentValues=agentList.get(i).getText();
			AgentListBeforeTransferTo.add(selectedAgentValues);
		}
		selectWebElement(transferToSelectedList.get(0));
		List<String>SelectedAgentList = new ArrayList<>();
		for(int i=0;i<selectedAgentList.size();i++) {
			waitUntilWebElementIsVisible(selectedAgentList.get(i));
			String selectedAgentValues=selectedAgentList.get(i).getText();
			SelectedAgentList.add(selectedAgentValues);
		}
		selectWebElement(transferToSelectedList.get(0));
		List<String>AgentListAfterTransferFrom = new ArrayList<>();
		for(int i=0;i<agentList.size();i++) {
			waitUntilWebElementIsVisible(agentList.get(i));
			String selectedAgentValues=agentList.get(i).getText();
			AgentListAfterTransferFrom.add(selectedAgentValues);
		}
		boolean status=false;
		if(AgentListBeforeTransferTo.equals(SelectedAgentList)) {
			if(SelectedAgentList.equals(AgentListAfterTransferFrom)) {
				status=true;
			}
			else {System.out.println("Transfer From Data Mismatch");}
		}
		else {System.out.println("Transfer To Data Mismatch");}
		return status;
	}





	public String VerifyMessage() {
		waitForJqueryLoad(driver);
		if(successmsg.isDisplayed())
			return successmsg.getText();
		else{return errorMsg.get(0).getText();}
	}

	public void VerifySaveButtonwithoutSelectingAgents() {
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(saveButton);
		selectWebElement(saveButton);
	}

	public void VerifySaveButtonwithoutSelectingAttributes(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(selectTeam);
		selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		Thread.sleep(3000);
		selectAgent(details.getAgentList());
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(saveButton);
		selectWebElement(saveButton);
	}

	public boolean VerifyClearButton(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(selectTeam);
		selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		Thread.sleep(2000);
		selectAgent(details.getAgentList());
		waitForJqueryLoad(driver);
		selectWebElement(clearButton);
		if(selectTeamLabel.isDisplayed())
			return true;
		else
			return false;
	}

	public boolean verifyTeamNameswithDataBase(String query) {
		waitForJqueryLoad(driver);
		selectWebElement(selectTeam);
		List<String>database=database1(query);
		System.out.println(query);
		System.out.println(database);
		List<String>UI=getTeamNames();
		System.out.println(UI);
		if(UI.equals(database))
			return true;
		else
			return false;

	}

	public List<String> getTeamNames() {
		List<String>arr = new ArrayList<>();
		for(int i=0;i<listofTeams.size();i++) {
			String teamName=listofTeams.get(i).getText();
			arr.add(teamName);
			arr.remove("");
		}
		Collections.sort(arr, String.CASE_INSENSITIVE_ORDER);
		return arr;
	}

	public void selectAttributeTab(String attributeType){
		for(WebElement ele:attributeTabs){
			scrollToElement(ele);
			if(ele.getText().equalsIgnoreCase(attributeType)){
				selectWebElement(ele);break;
			}
		}
	}

	public void selectmultipleAttributes(String attributeName, String attributesLevel) throws Exception{
		String[] a=attributeName.split(",");
		String[] b=attributesLevel.split(",");
		for(String attribute:a){
			System.out.println(a+"daaaaaaaaata");
			for(WebElement ele:multipleAttributes){
				if(ele.getText().equalsIgnoreCase(attribute)){
					selectWebElement(ele);
					selectWebElement(transferToSelectedList.get(3));
					break;
				}
			}
			enterValueToTxtField(attributeLevel,b[Arrays.asList(a).indexOf(attribute)]);
			selectWebElement(okButton);
		}
	}

	public void verifyAssignAttributesfromTab(AttributeAssignmentDetails details) throws Exception {
		selectWebElement(selectTeam);
		selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		Thread.sleep(3000);
		selectAgent(details.getAgentList());
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		waitForJqueryLoad(driver);
		System.out.printf(details.getMultipleAttributes(),details.getMultipleAttributeLevels());
		selectmultipleAttributes(details.getMultipleAttributes(),details.getMultipleAttributeLevels());
		scrollToElement(saveButton);
		selectWebElement(saveButton); 
	}

	public boolean VerifyPresenceofAgentAttributesDetails() {
		waitForJqueryLoad(driver);
		selectWebElement(viewAssignmentButton);
		waitForJqueryLoad(driver);
		boolean status=false;
		if(AgentattributeDetailsHeader.isDisplayed()) {
			if(ExporttoExcel.isEnabled()) {
				if(searchAttributeDetails.isDisplayed()){
					if(AttributeNameColumn.isDisplayed()) {
						if(AttributeLevelColumn.isDisplayed()) {
							if(AttributesDetailsCloseButton.isEnabled()) {
								status=true;
							}
							else {System.out.println("AttributesDetailsCloseButton is Disabled");}
						}
						else {System.out.println("AttributeLevelColumn is not Displayed");}
					}
					else {System.out.println("AttributeNameColumn is not Displayed");}
				}
				else {System.out.println("searchAttributeDetails is not Displayed");}
			}
			else {System.out.println("ExporttoExcel is Disabled");}
		}
		else {System.out.println("AgentattributeDetailsHeader is not Displayed");}
		return status;
	}

	public boolean ExportToExcelButton(String filePath) {
		waitForJqueryLoad(driver);
		selectWebElement(viewAssignmentButton);
		waitForJqueryLoad(driver);
		final File folder=new File(filePath);
		for(final File f : folder.listFiles()) {
			if(f.getName().startsWith("AgentAttributeDetails")) {
				f.delete();
			}
		}
		selectWebElement(ExporttoExcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath,"AgentAttributeDetails");
		return Status;
	}

	public boolean verifyDropDownOfAllHeaders() {
		waitForJqueryLoad(driver);
		selectWebElement(viewAssignmentButton);
		waitForJqueryLoad(driver);
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
		waitForJqueryLoad(driver);
		selectWebElement(viewAssignmentButton);
		waitForJqueryLoad(driver);
		boolean status=false;
		WebElement ele=headersDropdown.get(2);
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
		waitForJqueryLoad(driver);
		selectWebElement(viewAssignmentButton);
		waitForJqueryLoad(driver);
		boolean status = false;
		WebElement ele = headersDropdown.get(2);
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

	public boolean verifyArrowMoveForPreviousAndNextPage() throws Exception{
		waitForJqueryLoad(driver);
		scrollToElement(viewAssignmentButton);
		selectWebElement(viewAssignmentButton);
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
		scrollToElement(viewAssignmentButton);
		selectWebElement(viewAssignmentButton);
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
		selectWebElement(viewAssignmentButton);
		waitForJqueryLoad(driver);
		String item = items.get(0).getText();
		return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
	}

	public boolean verifyNumberOfItemsPerPage() {
		waitForJqueryLoad(driver);
		selectWebElement(viewAssignmentButton);
		waitForJqueryLoad(driver);
		boolean status = false;
        try {
                int item = Integer.valueOf(items.get(0).getText().split("of ")[1].split(" items")[0]);
                selectWebElement(pagerDropdown);
                Thread.sleep(1500);
                for (int i = 0; i < pageSizeListBox.size(); i++) {
                    if(Integer.valueOf(pageSizeListBox.get(i).getText())>item){continue;}
                    selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
                    waitForJqueryLoad(driver);
                    int totalItems = Integer.valueOf(items.get(0).getText().split("of ")[1].split(" items")[0]);
                    int pagersize = Integer.valueOf(pagerSize.getText());
                    int pages = (totalItems % pagersize == 0) ? item / pagersize : item / pagersize+1;
                    int totalRows=(gridContent.findElements(By.cssSelector("#goalmappinggird .k-master-row")).size());
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

	public void SearchChannelCategoryToAssignForAgentInAttributesTab(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		enterValueToTxtField(channelAttributeSearchBox,details.getAttributeName());
	}

	public void SearchIntentCategoryToAssignForAgentInAttributesTab(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		enterValueToTxtField(intentAttributeSearchBox,details.getAttributeName());
	}

	public void SearchPackageCategoryToAssignForAgentInAttributesTab(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		enterValueToTxtField(packageAttributeSearchBox,details.getAttributeName());
	}

	public void SearchCustomerTypeCategoryToAssignForAgentInAttributesTab(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		enterValueToTxtField(customerTypeAttributeSearchBox,details.getAttributeName());
	}

	public void SearchAgeCategoryToAssignForAgentInAttributesTab(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		enterValueToTxtField(ageAttributeSearchBox,details.getAttributeName());
	}

	public void SearchGenderCategoryToAssignForAgentInAttributesTab(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		enterValueToTxtField(genderAttributeSearchBox,details.getAttributeName());
	}

	public void SearchLocationCategoryToAssignForAgentInAttributesTab(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		enterValueToTxtField(loactionAttributeSearchBox,details.getAttributeName());
	}

	public void SearchSentimentCategoryToAssignForAgentInAttributesTab(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		enterValueToTxtField(sentimentAttributeSearchBox,details.getAttributeName());
	}

	public void SearchLanguageCategoryToAssignForAgentInAttributesTab(AttributeAssignmentDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		enterValueToTxtField(languageAttributeSearchBox,details.getAttributeName());
	}

	public boolean verifySearchedAttributeData(AttributeAssignmentDetails details){
		waitForJqueryLoad(driver);
		selectAttributeTab(details.getAttributeType());
		boolean status=false;
		for(WebElement ele:SearchAttributeTabData) {
			System.out.println(ele.getText());
			if(ele.getText().equalsIgnoreCase(details.getAttributeName()))
				status= true;
		}
		return status;

	}


}
