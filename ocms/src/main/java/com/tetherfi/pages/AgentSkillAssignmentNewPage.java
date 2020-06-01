package com.tetherfi.pages;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import com.tetherfi.model.tmac.AgentSettingsDetails;

import com.tetherfi.model.user.AgentSkillAssignmentNewDetails;

public class AgentSkillAssignmentNewPage extends BasePage {
	public AgentSkillAssignmentNewPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
    private WebElement AgentSkillAssignment; 
	
	@FindBy(xpath="//i[@class='fas fa-users-cog']")
    private WebElement ASAImg;
	
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
		
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
		 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(id="navbarheader")
	private WebElement header;
	
	@FindBy(id="create")
	private WebElement editAgentListButton;
	
	@FindBy(css=".ibox-title h5")
    private WebElement agentSettings;
	
	@FindBy(css="#tabstripAgtAgent .k-tabstrip-items li")
    private List<WebElement> tabList;
	
	@FindBy(id="create")
    private WebElement addNewAgentSettingsRecordBtn;
	
	@FindBy(css=".k-edit-form-container")
    private WebElement popupContent;
	
	@FindBy(id="UserName")
	private WebElement usernameTextBox;

	@FindBy(id="FirstName")
	private WebElement firstnameTextBox;

	@FindBy(id="LastName")
	private WebElement lastnameTextBox;

	@FindBy(css="span[class^='k-widget k-dropdowntree k-dropdowntree-clearable']")
	private WebElement teamnameDropdown;

	@FindBy(css=".k-treeview-lines li div")
	private List<WebElement> teamList;
	
	@FindBy(css="span[aria-owns='Profile_listbox']")
    private WebElement profileDropdown;

    @FindBy(css="ul[id='Profile_listbox'] li")
    private List<WebElement> profileListBox;
    
    @FindBy(css="span[aria-owns='SupervisorID_listbox']")
    private WebElement supervisorDropdown;

    @FindBy(css="ul[id='SupervisorID_listbox'] li")
    private List<WebElement> supervisorListBox;
    
    @FindBy(id="AvayaLoginID")
    private WebElement avayaLoginIdTextBox;

    @FindBy(css=".k-edit-form-container .form-group .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private List<WebElement> numericTextbox;
    
    @FindBy(css="span[aria-owns='AccessRole_listbox']")
    private WebElement accessroleDropdown;

    @FindBy(css="ul[id='AccessRole_listbox'] li")
    private List<WebElement> accessroleListBox;
    
    @FindBy(id="voice0")
    private WebElement voiceCheckbox;

    @FindBy(id="textchat1")
    private WebElement textChatCheckbox;

    @FindBy(id="audiochat2")
    private WebElement audiochatCheckbox;

    @FindBy(id="videochat3")
    private WebElement videoChatCheckbox;

    @FindBy(id="fax4")
    private WebElement faxCheckbox;

    @FindBy(id="email5")
    private WebElement mailCheckbox;

    @FindBy(id="sms6")
    private WebElement smsCheckbox;
	
	@FindBy(id="faxout7")
    private WebElement faxoutCheckbox;
	
	@FindBy(css="input[title='Total Audio Chat Tabs Allowed']")
	private List<WebElement> totalAudioChatTabsAllowedTextBox;

	@FindBy(css="input[title='Total Video Chat Tabs Allowed']")
	private List<WebElement> totalVideoChatTabsAllowedTextBox;

	@FindBy(css="input[title='Total Voice Tabs Allowed']")
	private List<WebElement> totalVoiceTabsAllowedTextBox;

	@FindBy(css="input[title='Total Text Chat Tabs Allowed']")
	private List<WebElement> totalChatTabsAllowedTextBox;

	@FindBy(css="input[title='Total Email Tabs Allowed']")
	private List<WebElement> totalEmailTabsAllowedTextBox;

	@FindBy(css="input[title='Total Fax Tabs Allowed']")
	private List<WebElement> totalFaxTabsAllowedTextBox;

	@FindBy(css="input[title='Total SMS Tabs Allowed']")
	private List<WebElement> totalSMSTabsAllowedTextBox;
		
    @FindBy(css="input[title='Total Faxout Tabs Allowed']")
	private List<WebElement> totalFaxoutTabsAllowedTextBox;
    
    @FindBy(id="IsVoiceACDAutoACWEnabled")
    private WebElement goToAcwAfterEachAcdCallsCheckbox;

    @FindBy(id="IsVoiceACDAutoAnswerEnabled")
    private WebElement autoAnswerAllAcdCallsCheckbox;  //IsVoiceACDAutoAnswerEnabled, IsVoiceAcdAutoAnswerEnabled

    @FindBy(id="IsVoiceAllAutoACWEnabled")
    private WebElement goToAcwAfterAnyCallsCheckbox;
    
    @FindBy(id="IsHoldVoiceCallOnChatCall")   
    private WebElement holdVoiceCallOnChatCallCheckbox;
    
    @FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
    private WebElement saveBtn;
    
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
    
    @FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
    
    @FindBy(css=".k-grid-content")
    private WebElement gridContent;
    
    @FindBy(xpath="//i[@class='fas fa-sync']")
	private WebElement clearsearch;
    
    @FindBy(xpath="//span[@class='k-input']")
    private WebElement pagerSize;
    
    @FindBy(xpath="//a[text()='User Name']")
    private WebElement UserName;
    
    @FindBy(xpath="//div[@data-role='droptarget']")
    private WebElement droptarget;
    
    @FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[5]")
    private WebElement groupbyUserName;
    
    @FindBy(id="grid")
    private WebElement auditGridContent;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
    
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
    
    @FindBy(css = "#toast-container .toast-error")
    private List<WebElement> errorMsg;
    
    @FindBy(css="#toast-container .toast-message")
    private WebElement successmsg;
    
    @FindBy(xpath="//button[text()='Clear All']")
    private WebElement ClearAll; 
    
    @FindBy(xpath="//a[@class='k-icon k-i-expand']")
    private WebElement pageArrowDropDown;
    
    @FindBy(xpath="//span[text()='Skill List']")
    private WebElement pageArrowSkillList;
    
    @FindBy(xpath="//li[@class='k-item k-state-default k-first k-tab-on-top k-state-active']")
    private List<WebElement> channelTab;
    
    @FindBy(xpath="//div[@class='k-widget k-grid k-display-block']")
    private List<WebElement> arrowGrid;
    
    @FindBy(css = ".k-grid-CustomEdit")
    private WebElement editButton;

    @FindBy(id="popup")
    private WebElement popupContainer;
    
    @FindBy(xpath="//span[text()='×']")
    private List<WebElement> editCloseButton;

    @FindBy(css="#tabstrip_skilledit ul[class='k-tabstrip-items k-reset'] li")
    private List<WebElement> skillsTab;
    
    @FindBy(css="#tabstrip_multi ul[class='k-tabstrip-items k-reset'] li")
    private List<WebElement> multiSkillsTab;

    @FindBy(css="#tabstrip_skilledit ul[role='listbox'] li")
    private List<WebElement> skillsName;
    
    @FindBy(xpath="//select[@id='selectedvoiceSkill']/..")
    private WebElement selectedvoiceSkills;
    
    @FindBy(xpath="//select[@id='selectedtextchatSkill']/..")
    private WebElement selectedtextchatSkills;
    
    @FindBy(xpath="//select[@id='selectedaudiochatSkill']/..")
    private WebElement selectedaudioChatSkills;
    
    @FindBy(xpath="//select[@id='selectedvideochatSkill']/..")
    private WebElement selectedvideoChatSkills;
    
    @FindBy(xpath="//select[@id='selectedfaxSkill']/..")
    private WebElement selectedfaxSkills;
    
    @FindBy(xpath="//select[@id='selectedemailSkill']/..")
    private WebElement selectedmailSkills;
    
    @FindBy(xpath="//select[@id='selectedsmsSkill']/..")
    private WebElement selectedsmssSkills;
    
    @FindBy(xpath="//select[@id='SelectedAgentList']/..")
    private WebElement selectedAgentList;
    
    @FindBy(xpath="//select[@id='selectedvoiceMultiSkill']/..")
    private WebElement selectedvoiceMultiSkill;
    
    @FindBy(xpath="//select[@id='selectedtextchatMultiSkill']/..")
    private WebElement selectedtextChatMultiSkill;
    
    @FindBy(xpath="//select[@id='selectedaudiochatMultiSkill']/..")
    private WebElement selectedaudioChatMultiSkill;
    
    @FindBy(xpath="//select[@id='selectedvideochatMultiSkill']/..")
    private WebElement selectedvideoChatMultiSkill;
    
    @FindBy(xpath="//select[@id='selectedfaxMultiSkill']/..")
    private WebElement selectedFaxMultiSkill;
    
    @FindBy(xpath="//select[@id='selectedemailMultiSkill']/..")
    private WebElement selectedemailMultiSkill;
    
    @FindBy(xpath="//select[@id='selectedsmsMultiSkill']/..")
    private WebElement selectedsmsMultiSkill;

    @FindBy(css="#tabstrip_skilledit div[id='tdrillgrid'] div[data-role='draggable']")
    private List<WebElement> skillsSelected;
    
    @FindBy(css="div[class='swal-modal'] input")
    private WebElement skillLevel;

    @FindBy(css="div[class='swal-modal'] button")
    private WebElement okButton;

    @FindBy(css="button[onclick='saveSkills()']")
    private WebElement skillSaveBtn;
    
    @FindBy(xpath="//a[@class='k-button k-button-icon']")
    private List<WebElement> transferToSelectedList;
    
    @FindBy(xpath="//a[@class='k-button k-button-icon']")
    private List<WebElement> transferAllTo;
    
    @FindBy(id="addedittab")
    private WebElement multiSkillTab;
	
    @FindBy(id="saveQuery")
    private WebElement multiSkillSaveButton;
    
    @FindBy(xpath="//button[text()='Reset']")
    private WebElement ResetButton;
    
    @FindBy(xpath="//span[@aria-owns='TeamID_listbox']")
    private WebElement selectTeam;
    
    @FindBy(css="ul[id='TeamID_listbox'] li")
    private List<WebElement> listofTeams;
    
    @FindBy(css="#multiskill ul[role='listbox'] li")
    private List<WebElement> multiAgentList;
    
    @FindBy(xpath="//button[@id='saveQuery' and text()='Clear']")
    private WebElement multiclear;
    
    @FindBy(xpath="//button[@id='saveQuery' and text()='Save']")
    private WebElement multisave;
    
    @FindBy(css="#tabstrip_multi ul[role='listbox'] li")
    private List<WebElement> multiSkillsName;
    
    @FindBy(xpath="//span[@aria-controls='1001sColumnName_listbox']")
    private WebElement selectSearchColumn;
    
    @FindBy(xpath="//span[@aria-owns='1001sCriteria_listbox']")
    private WebElement condition;
    
    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;
    
    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;
    
    @FindBy(id="yesButton")
    private WebElement yesBtn;
    
    @FindBy(id="voiceSearchBox")
    private WebElement searchVoiceSkills;
    
    @FindBy(id="textchatSearchBox")
    private WebElement searchTextChatSkills;
    
    @FindBy(id="audiochatSearchBox")
    private WebElement searchAudioChatSkills;
    
    @FindBy(id="videochatSearchBox")
    private WebElement searchVideoChatSkills;
    
    @FindBy(id="faxSearchBox")
    private WebElement searchFaxSkills;
    
    @FindBy(id="emailSearchBox")
    private WebElement searchEmailSkills;
    
    @FindBy(id="smsSearchBox")
    private WebElement searchSMSSkills;
    
    @FindBy(id="voiceMultiSearchBox")
	private WebElement multiSearchVoiceSkills;
    
    @FindBy(id="textchatMultiSearchBox")
	private WebElement multiSearchTextChatSkills;
    
    @FindBy(id="audiochatMultiSearchBox")
	private WebElement multiSearchAudioChatSkills;
    
    @FindBy(id="videochatMultiSearchBox")
	private WebElement multiSearchVideoChatSkills;
    
    @FindBy(id="faxMultiSearchBox")
	private WebElement multiSearchFaxSkills;
    
    @FindBy(id="emailMultiSearchBox")
	private WebElement multiSearchEmailSkills;
    
    @FindBy(id="smsMultiSearchBox")
	private WebElement multiSearchSMSSkills;
    
    @FindBy(css="#tabstrip_multi ul[role='listbox'] li")
    private List<WebElement> SearchMultiSkillTabData;
    
    @FindBy(xpath="//div[text()='No records to display']")
	private WebElement norecords;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext']")
    private List<WebElement> SearchSkillsbtn;
    
    @FindBy(xpath="//div[@class='k-multiselect-wrap k-floatwrap']")
    private List<WebElement> skillSearch;
    
    @FindBy(css="ul[id='skill_listbox'] li")
    private List<WebElement> SkillSearchDropDown;
    
    
    
    
    
    
	public boolean isAgentSkillAssignmentPageIsDisplayed() {
		waitForLoad(driver);
		/*try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}*/
		waitForJqueryLoad(driver);
		waitForJqueryLoad(driver);
		return AgentSkillAssignment.isEnabled();
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
	
	public boolean verifyEditAgentListRecordButton() {
		waitForLoad(driver);
		selectWebElement(editAgentListButton);
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		waitForLoad(driver);
        return agentSettings.isEnabled();
		
	}
	
	public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(tabList);
        waitUntilWebElementListIsClickable(tabList);
        for(WebElement ele:tabList){
            if(ele.getText().equalsIgnoreCase(tabname)){ele.click();break;}
        }
    }
	
	 public void selectProfile(String profile, String supervisor){
	        try {  if(profile.equalsIgnoreCase("Agent")){
	            selectWebElement(profileDropdown);
	            selectDropdownFromVisibleText(profileListBox,profile);
	            Thread.sleep(3000);
	            selectWebElement(supervisorDropdown);
	            selectDropdownFromVisibleText(supervisorListBox,supervisor);
	        }else if(profile.equalsIgnoreCase("Supervisor")){
	            selectWebElement(profileDropdown);
	            selectDropdownFromVisibleText(profileListBox,profile);
	            Thread.sleep(3000);
	            selectWebElement(supervisorDropdown);
	            selectDropdownFromVisibleText(supervisorListBox,supervisor);
	        }} catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public void selectFeaturesToBeSelected(String[] features){
	        for(String feature:features){
	            if(feature.equalsIgnoreCase("Voice")&&!voiceCheckbox.isSelected()){selectCheckbox(voiceCheckbox);}
	            else if(feature.equalsIgnoreCase("Email")&&!mailCheckbox.isSelected()){selectCheckbox(mailCheckbox);}
	            else if(feature.equalsIgnoreCase("SMS")&&!smsCheckbox.isSelected()){selectCheckbox(smsCheckbox);}
	            else if(feature.equalsIgnoreCase("TextChat")&&!textChatCheckbox.isSelected()){selectCheckbox(textChatCheckbox);}
	            else if(feature.equalsIgnoreCase("VideoChat")&&!videoChatCheckbox.isSelected()){selectCheckbox(videoChatCheckbox);}
	            else if(feature.equalsIgnoreCase("AudioChat")&&!audiochatCheckbox.isSelected()){selectCheckbox(audiochatCheckbox);}
	            else if(feature.equalsIgnoreCase("Fax")&&!faxCheckbox.isSelected()){selectCheckbox(faxCheckbox);}																									  
	        }
	    }
	 
	 public void selectCheckBox(WebElement ele, boolean value){
			if(value){
	            if(ele.isSelected()){}else{selectCheckbox(ele);}
	        }
	        else{
	            if(ele.isSelected()){selectCheckbox(ele);}
	        }
	    }
	 
	public void addNewAgentSettingsRecord(AgentSettingsDetails details) throws Exception {
        waitForJqueryLoad(driver);
        try {Thread.sleep(5000);
        selectWebElement(addNewAgentSettingsRecordBtn);
        waitUntilWebElementIsVisible(popupContent);

            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        navigateToTab("Info");
        selectWebElement(usernameTextBox);
        enterValueToTxtFieldWithoutClear(usernameTextBox,details.getUsername());
        selectWebElement(numericTextbox.get(0));
        enterValueToTxtFieldWithoutClear(avayaLoginIdTextBox,details.getAvayaLoginID());
        selectWebElement(firstnameTextBox);
        enterValueToTxtFieldWithoutClear(firstnameTextBox,details.getFirstname());
        selectWebElement(lastnameTextBox);
        enterValueToTxtFieldWithoutClear(lastnameTextBox,details.getLastname());
        selectWebElement(teamnameDropdown);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //selectDropdownFromVisibleText(teamNameListBox,details.getTeamName());
        ChooseTeamHeirarchy(details.getTeamName());
        selectProfile(details.getProfile(),details.getSupervisor());
        selectWebElement(accessroleDropdown);
        selectDropdownFromVisibleText(accessroleListBox,details.getAccessRole());
        //selectWebElement(crmnameDropdown);
        //selectDropdownFromVisibleText(crmnameListBox,details.getCrmName());
//        selectWebElement(texttemplatenameDropdown);
//        selectDropdownFromVisibleText(texttemplatenameListBox,details.getTextTemplateName());
        navigateToTab("Channel Count & Features");
        selectFeaturesToBeSelected(details.getFeaturestobeSeleted());
        selectWebElement(numericTextbox.get(1));
        enterValueToTxtFieldWithoutClear(totalVoiceTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVoiceTabs()));
        selectWebElement(numericTextbox.get(2));
        enterValueToTxtFieldWithoutClear(totalChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalChatTabs()));
        selectWebElement(numericTextbox.get(3));
        enterValueToTxtFieldWithoutClear(totalAudioChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalAudioChatTabs()));
        selectWebElement(numericTextbox.get(4));
        enterValueToTxtFieldWithoutClear(totalVideoChatTabsAllowedTextBox.get(1),String.valueOf(details.getTotalVideoChatTabs()));
        selectWebElement(numericTextbox.get(5));
        enterValueToTxtFieldWithoutClear(totalFaxTabsAllowedTextBox.get(1),String.valueOf(details.getTotalFaxTabs()));
        selectWebElement(numericTextbox.get(6));
        enterValueToTxtFieldWithoutClear(totalEmailTabsAllowedTextBox.get(1),String.valueOf(details.getTotalEmailTabs()));
        selectWebElement(numericTextbox.get(7));
        enterValueToTxtFieldWithoutClear(totalSMSTabsAllowedTextBox.get(1),String.valueOf(details.getTotalSMSTabs()));																								
       // selectWebElement(featuresDropdown);
        //selectDropdownFromVisibleText(featuresListBox,details.getFeatures());
        selectCheckBox(autoAnswerAllAcdCallsCheckbox,details.isAutoanswerallACDcalls());
        selectCheckBox(goToAcwAfterEachAcdCallsCheckbox,details.isGotoACWaftereachACDcalls());
        selectCheckBox(goToAcwAfterAnyCallsCheckbox,details.isGotoACWafteranycalls());
        //selectCheckBox(crmEnabledCheckbox,details.iscRMEnabled());
        selectCheckBox(holdVoiceCallOnChatCallCheckbox,details.isHoldVoiceCallOnChatCall());
        //selectCheckBox(secondTextChatAutoAnswerCheckbox,details.isSecondTextChatAutoAnswer());
        //selectCheckBox(textChatAutoACWCheckbox,details.isTextChatAutoACWEnabled());
        //selectCheckBox(textChatAutoAnswerCheckbox,details.isTextChatAutoAnswer());
//        selectCheckBox(manualInCheckbox,details.isManualIn());
//        selectCheckBox(smsOutCheckbox,details.isSMSOut());
        selectWebElement(saveBtn);
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
    
    public String verifySuccessMessage(){
        waitUntilWebElementIsVisible(successmsg);return successmsg.getText();
    }
    
    public boolean verifyExportToExcel(String filePath) {
		final File folder = new File(filePath);
		for (final File f : folder.listFiles()) {
		    if (f.getName().contains("Skill")) {
		        f.delete();
		    }
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(exporttoexcel);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath, "Agent Skill Assignment");
		return Status;
	}
	public boolean verifyexportToExcelSheet(List<Map<String, String>>maplist) {
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
			for(int j=0;j<headers.size();j++) {
				scrollToElement(headers.get(j));
				System.out.println(headers.get(j).getText());
				if(headers.get(j).getText().equals(" ")){
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
    
    public void searchAgentSkillListRecord(String username) throws Exception {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"User Name");
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,username);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    
    public void searchAgentSettingsListRecord(String username) throws Exception {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Lan ID");
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,username);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    
    
    
    public boolean ExporttoExcelWithoutData(AgentSkillAssignmentNewDetails details) throws Exception {
    	searchAgentSkillListRecord(details.getUsername());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		waitUntilWebElementListIsVisible(errorMsg);
		System.out.print(errorMsg.get(0).getText());
		if(errorMsg.get(0).getText().contains("There is no record to export"))
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
	
	public boolean clearAll(AgentSkillAssignmentNewDetails details) throws Exception {
		selectWebElement(searchBtn);
	    selectWebElement(selectSearchCol.get(0));
	    selectDropdownFromVisibleText(columnNameList,"User Name");
	    selectWebElement(selectSearchCol.get(1));
	    selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	    enterValueToTxtField(searchTextBox,details.getUsername());
	    selectWebElement(ClearAll);
			if(searchTextBox.isEnabled())
	        	return true;
	        else
			return false;
		}


	public boolean verifyclose() {
		selectWebElement(searchCloseBtn);
		if(gridContent.isDisplayed())
			return true;
		else
		return false;
	}
	
	public void searchwithoutextsearch() {
		selectWebElement(searchBtn);
		selectWebElement(selectSearchCol.get(0));
	    selectDropdownFromVisibleText(columnNameList,"User Name");
	    selectWebElement(selectSearchCol.get(1));
	    selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	    selectWebElement(searchSearchBtn);
	    selectWebElement(searchCloseBtn);	
	}
	
	public boolean getErrorMsg() {
		waitUntilWebElementListIsVisible(errorMsg);
		if(errorMsg.size()>0)
		return false;
		else
			return true;
	}
	
	
	public boolean verifyArrowDropDown(AgentSkillAssignmentNewDetails details) throws Exception {
		List<Map<String, String>> maplist=getAgetSkillData(details);
		List<Map<String, String>> maplist1=getAgentSettingsdata(details);
		System.out.println(maplist);
		System.out.println(maplist1);
		if(maplist.equals(maplist1))
		return true;
		else
		return false;
	}
	
	public List<Map<String,String>> getAgetSkillData(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
		waitUntilWebElementIsVisible(pageArrowDropDown);
		selectWebElement(pageArrowDropDown);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		waitUntilWebElementIsVisible(arrowGrid.get(0));
		List<WebElement> rows=arrowGrid.get(0).findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		String col=null;
		for(int i=1;i<rows.size();i++) {
			Map<String,String> map = new HashMap<String,String>();
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			for(int j=0;j<headers.size();j++) {
				scrollToElement(headers.get(j));
				System.out.println(headers.get(j).getText());
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			arr.add(map);
	}
		return arr;
}
		
	
	
	public List<Map<String,String>> getAgentSettingsdata(AgentSkillAssignmentNewDetails details) throws Exception{
		verifyEditAgentListRecordButton();
		searchAgentSettingsListRecord(details.getUsername());
		waitUntilWebElementIsVisible(pageArrowDropDown);
		selectWebElement(pageArrowDropDown);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		waitUntilWebElementIsVisible(arrowGrid.get(0));
		List<WebElement> rows=arrowGrid.get(0).findElements(By.tagName("tr"));
		List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
		String col=null;
		for(int i=1;i<rows.size();i++) {
			Map<String,String> map = new HashMap<String,String>();
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			for(int j=0;j<headers.size();j++) {
				scrollToElement(headers.get(j));
				System.out.println(headers.get(j).getText());
					col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			arr.add(map);
	}
		return arr;
}
	
	public void editWithoutSkillAssignToAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        clickOn(skillSaveBtn);
    }
	
	public void SearchVoiceSkillsToAssignAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        enterValueToTxtField(searchVoiceSkills,details.getSkillName());
        
    }
	
	public void SearchTextChatSkillsToAssignAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        enterValueToTxtField(searchTextChatSkills,details.getSkillName());
        
    }
	
	public void SearchAudioChatSkillsToAssignAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        enterValueToTxtField(searchAudioChatSkills,details.getSkillName());
        
    }
	
	public void SearchVideoChatSkillsToAssignAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        enterValueToTxtField(searchVideoChatSkills,details.getSkillName());
        
    }
	
	public void SearchFaxSkillsToAssignAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        enterValueToTxtField(searchFaxSkills,details.getSkillName());
        
    }
	
	public void SearchEmailSkillsToAssignAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        enterValueToTxtField(searchEmailSkills,details.getSkillName());
        
    }
	
	public void SearchSMSSkillsToAssignAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        enterValueToTxtField(searchSMSSkills,details.getSkillName());
        
    }
	
	
	public void SearchVoiceSkillsToAssignForAgentInMultiSkillTab(AgentSkillAssignmentNewDetails details) throws Exception {
		navigateToMultiSkillTab();
        waitForJqueryLoad(driver);
        selectMultiSkillTab(details.getSkillType());
        enterValueToTxtField(multiSearchVoiceSkills,details.getSkillName());
        
    }
	
	public void SearchTextChatSkillsToAssignForAgentInMultiSkillTab(AgentSkillAssignmentNewDetails details) throws Exception {
		navigateToMultiSkillTab();
        waitForJqueryLoad(driver);
        selectMultiSkillTab(details.getSkillType());
        enterValueToTxtField(multiSearchTextChatSkills,details.getSkillName());
        
    }
	
	public void SearchAudioChatSkillsToAssignForAgentInMultiSkillTab(AgentSkillAssignmentNewDetails details) throws Exception {
		navigateToMultiSkillTab();
        waitForJqueryLoad(driver);
        selectMultiSkillTab(details.getSkillType());
        enterValueToTxtField(multiSearchAudioChatSkills,details.getSkillName());
        
    }
	
	public void SearchVideoChatSkillsToAssignForAgentInMultiSkillTab(AgentSkillAssignmentNewDetails details) throws Exception {
		navigateToMultiSkillTab();
        waitForJqueryLoad(driver);
        selectMultiSkillTab(details.getSkillType());
        enterValueToTxtField(multiSearchVideoChatSkills,details.getSkillName());
        
    }
	
	public void SearchFaxSkillsToAssignForAgentInMultiSkillTab(AgentSkillAssignmentNewDetails details) throws Exception {
		navigateToMultiSkillTab();
        waitForJqueryLoad(driver);
        selectMultiSkillTab(details.getSkillType());
        enterValueToTxtField(multiSearchFaxSkills,details.getSkillName());
        
    }
	
	public void SearchEmailSkillsToAssignForAgentInMultiSkillTab(AgentSkillAssignmentNewDetails details) throws Exception {
		navigateToMultiSkillTab();
        waitForJqueryLoad(driver);
        selectMultiSkillTab(details.getSkillType());
        enterValueToTxtField(multiSearchEmailSkills,details.getSkillName());
        
    }
	
	public void SearchSMSSkillsToAssignForAgentInMultiSkillTab(AgentSkillAssignmentNewDetails details) throws Exception {
		navigateToMultiSkillTab();
        waitForJqueryLoad(driver);
        selectMultiSkillTab(details.getSkillType());
        enterValueToTxtField(multiSearchSMSSkills,details.getSkillName());
        
    }
	public void resetAssignedSkillsToAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectSkillName(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(ResetButton);
    }
	
	public boolean EditAgentListCloseButton(AgentSkillAssignmentNewDetails details) throws Exception {
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(editCloseButton.get(1));
        selectWebElement(editCloseButton.get(1));
        if(gridContent.isDisplayed())
			return true;
		else
		    return false;
    }
	
	
	public void assignVoiceSkillToAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectSkillName(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignTextChatSkillToAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectSkillName(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignAudioChatSkillToAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectSkillName(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignVideoChatSkillToAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectSkillName(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignFaxSkillToAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectSkillName(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignEmailSkillToAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectSkillName(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignSMSSkillToAgent(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectSkillName(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignVoiceSkillToAgentByDragandDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectVoiceSkillNameForDragAndDrop(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignTextChatSkillToAgentByDragandDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectTextChatSkillNameForDragAndDrop(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignAudioChatSkillToAgentByDragandDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectAudioChatSkillNameForDragAndDrop(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignVideoChatSkillToAgentByDragandDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectVideoChatSkillNameForDragAndDrop(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignFaxSkillToAgentByDragandDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectFaxSkillNameForDragAndDrop(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignEmailSkillToAgentByDragandDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectEmailSkillNameForDragAndDrop(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	public void assignSMSSkillToAgentByDragandDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		searchAgentSkillListRecord(details.getUsername());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContainer);
        selectSkillTab(details.getSkillType());
        selectSMSSkillNameForDragAndDrop(details.getSkillName());
        enterValueToTxtField(skillLevel,details.getSkillLevel());
        selectWebElement(okButton);
        clickOn(skillSaveBtn);
    }
	
	 public void selectSkillName(String skillName){
	        for(WebElement ele:skillsName){
	            if(ele.getText().equalsIgnoreCase(skillName)){
	                selectWebElement(ele);
	                selectWebElement(transferToSelectedList.get(1));
                    break;     
	            }
	        }
	    }
	 
	 public void selectVoiceSkillNameForDragAndDrop(String skillName){
	        for(WebElement ele:skillsName){
	            if(ele.getText().equalsIgnoreCase(skillName)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedvoiceSkills)
	            	.release()
	            	.build()
	            	.perform();
                 break;     
	            }
	        }
	    }
	 
	 public void selectTextChatSkillNameForDragAndDrop(String skillName){
	        for(WebElement ele:skillsName){
	            if(ele.getText().equalsIgnoreCase(skillName)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedtextchatSkills)
	            	.release()
	            	.build()
	            	.perform();
              break;     
	            }
	        }
	    }
	 
	 public void selectAudioChatSkillNameForDragAndDrop(String skillName){
	        for(WebElement ele:skillsName){
	            if(ele.getText().equalsIgnoreCase(skillName)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedaudioChatSkills)
	            	.release()
	            	.build()
	            	.perform();
              break;     
	            }
	        }
	    }
	 
	 public void selectVideoChatSkillNameForDragAndDrop(String skillName){
	        for(WebElement ele:skillsName){
	            if(ele.getText().equalsIgnoreCase(skillName)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedvideoChatSkills)
	            	.release()
	            	.build()
	            	.perform();
              break;     
	            }
	        }
	    }
	 
	 public void selectFaxSkillNameForDragAndDrop(String skillName){
	        for(WebElement ele:skillsName){
	            if(ele.getText().equalsIgnoreCase(skillName)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedfaxSkills)
	            	.release()
	            	.build()
	            	.perform();
              break;     
	            }
	        }
	    }
	 
	 public void selectEmailSkillNameForDragAndDrop(String skillName){
	        for(WebElement ele:skillsName){
	            if(ele.getText().equalsIgnoreCase(skillName)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedmailSkills)
	            	.release()
	            	.build()
	            	.perform();
              break;     
	            }
	        }
	    }
	 
	 public void selectSMSSkillNameForDragAndDrop(String skillName){
	        for(WebElement ele:skillsName){
	            if(ele.getText().equalsIgnoreCase(skillName)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedsmssSkills)
	            	.release()
	            	.build()
	            	.perform();
              break;     
	            }
	        }
	    }
	 
	 
	 
	 public void selectSkillTab(String skillType){
	        for(WebElement ele:skillsTab){
	            if(ele.getText().equalsIgnoreCase(skillType)){
	                selectWebElement(ele);break;
	            }
	        }
	    }
	 
	 public void selectMultiSkillTab(String skillType){
	        for(WebElement ele:multiSkillsTab){
	        	scrollToElement(ele);
	            if(ele.getText().equalsIgnoreCase(skillType)){
	                selectWebElement(ele);break;
	            }
	        }
	    }
	 
	 public boolean verifyRecordUpdated(){
	        waitUntilWebElementIsVisible(successmsg);
	        if(successmsg.getText().contains("Skills Assigned Successfully"))
	        {return true;}else{return false;}
	    }
	 
	 public boolean verifyResetButton(AgentSkillAssignmentNewDetails details){
		    waitForJqueryLoad(driver);
		    selectSkillTab(details.getSkillType());
		    boolean status=false;
	        for(WebElement ele:skillsName) {
	            if(ele.getText().equalsIgnoreCase(details.getSkillName()))
	                status= true;
	        }
			return status;
	        
	    }
	 
	 public boolean verifySearchedSkillData(AgentSkillAssignmentNewDetails details){
		    waitForJqueryLoad(driver);
		    selectSkillTab(details.getSkillType());
		    boolean status=false;
	        for(WebElement ele:skillsName) {
	        	System.out.println(ele.getText());
	            if(ele.getText().equalsIgnoreCase(details.getSkillName()))
	                status= true;
	        }
			return status;
	        
	    }
	 
	 public boolean verifySearchedMultiTabSkillData(AgentSkillAssignmentNewDetails details){
		    waitForJqueryLoad(driver);
		    selectMultiSkillTab(details.getSkillType());
		    boolean status=false;
	        for(WebElement ele:SearchMultiSkillTabData) {
	        	System.out.println(ele.getText());
	            if(ele.getText().equalsIgnoreCase(details.getSkillName()))
	                status= true;
	        }
			return status;
	        
	    }
	 
	 public void navigateToMultiSkillTab(){
	        waitUntilWebElementIsVisible(multiSkillTab);
	        waitUntilWebElementIsClickable(multiSkillTab);
	        multiSkillTab.click();
	        waitForJqueryLoad(driver);
	      
	    }
	 
	 public void verifyMultiSkillSaveButton() {
		  waitForJqueryLoad(driver);
		  selectWebElement(multiSkillSaveButton);
	 }
	 
	 public void verifyMultiSkillClear(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgent(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
	     selectmultiSkillName(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multiclear);
		 selectWebElement(multiclear);
		 
	 }
	 
	 public void verifyMultiSkillAssignVoiceChannel(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgent(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
	     selectmultiSkillName(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignTextChatChannel(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgent(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
	     selectmultiSkillName(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignAudioChatChannel(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgent(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
	     selectmultiSkillName(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignVideoChatChannel(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgent(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
	     selectmultiSkillName(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignFaxChannel(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgent(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
	     selectmultiSkillName(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignEmailChannel(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgent(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
	     selectmultiSkillName(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignSMSChannel(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgent(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
	     selectmultiSkillName(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignVoiceChannelForDragAndDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgentForDragAndDrop(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
		 selectmultiVoiceSkillNameForDragAndDrop(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignTextChatChannelForDragAndDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgentForDragAndDrop(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
		 selectmultiTextChatSkillNameForDragAndDrop(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignAudioChatChannelForDragAndDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgentForDragAndDrop(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
		 selectmultiAudioChatSkillNameForDragAndDrop(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignVideoChatChannelForDragAndDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgentForDragAndDrop(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
		 selectmultiVideoChatSkillNameForDragAndDrop(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignFaxChannelForDragAndDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgentForDragAndDrop(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
		 selectmultiFaxSkillNameForDragAndDrop(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignEmailChannelForDragAndDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgentForDragAndDrop(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
		 selectmultiEmailSkillNameForDragAndDrop(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyMultiSkillAssignSMSChannelForDragAndDrop(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectAgentForDragAndDrop(details.getAgentList());
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
		 selectmultiSMSSkillNameForDragAndDrop(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void verifyTransferAllToButtonAssigMultiSkills(AgentSkillAssignmentNewDetails details) throws Exception {
		 selectWebElement(selectTeam);
		 selectDropdownFromVisibleText(listofTeams,details.getTeamName());
		 selectWebElement(transferAllTo.get(0));
		 waitForJqueryLoad(driver);
		 selectMultiSkillTab(details.getSkillType());
		 selectmultiSkillNameForTransferAllTo(details.getMultipleSkills(),details.getMultipleSkillLevels());
	     scrollToElement(multisave);
		 selectWebElement(multisave);
		 
	 }
	 
	 public void selectAgent(String agent){
	        String[] a=agent.split(",");
	        for(String agentName:a) {
	            for (WebElement ele : multiAgentList) {
	                if (ele.getText().contains(agentName)) {
	                	selectWebElement(ele);
	                	selectWebElement(transferToSelectedList.get(0));
	                    break;
	                }
	            }
	        }
	        
	    }
	 
	 public void selectAgentForDragAndDrop(String agent){
	        String[] a=agent.split(",");
	        for(String agentName:a) {
	            for (WebElement ele : multiAgentList) {
	                if (ele.getText().contains(agentName)) {
	                	Actions act=new Actions(driver);
		            	act.clickAndHold(ele)
		            	.pause(Duration.ofSeconds(2))
		            	.moveToElement(selectedAgentList)
		            	.release()
		            	.build()
		            	.perform();		               
	                    break;
	                }
	            }
	        }
	        
	    }
	 public void selectMultipleSkills(String skill){
	        String[] a=skill.split(",");
	        for(String skillName:a) {
	            for (WebElement ele : multiAgentList) {
	                if (ele.getText().contains(skillName)) {
	                	selectWebElement(ele);
	                	selectWebElement(transferToSelectedList.get(0));
	                    break;
	                }
	            }
	        }
	        
	    }
	 
	 public void selectmultiSkillName(String skillName, String skLevel) throws Exception{
	        String[] a=skillName.split(",");
	        String[] b=skLevel.split(",");
	        for(String skill:a){
	        for(WebElement ele:multiSkillsName){
	            if(ele.getText().equalsIgnoreCase(skill)){
	            	selectWebElement(ele);
	            	selectWebElement(transferToSelectedList.get(3));
	                break;
	            }
	        }
	        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
	        selectWebElement(okButton);
	        }
	    }
	 
	 public void selectmultiVoiceSkillNameForDragAndDrop(String skillName, String skLevel) throws Exception{
	        String[] a=skillName.split(",");
	        String[] b=skLevel.split(",");
	        for(String skill:a){
	        for(WebElement ele:multiSkillsName){
	            if(ele.getText().equalsIgnoreCase(skill)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedvoiceMultiSkill)
	            	.release()
	            	.build()
	            	.perform();;
	                break;
	            }
	        }
	        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
	        selectWebElement(okButton);
	        }
	    }
	 
	 public void selectmultiTextChatSkillNameForDragAndDrop(String skillName, String skLevel) throws Exception{
	        String[] a=skillName.split(",");
	        String[] b=skLevel.split(",");
	        for(String skill:a){
	        for(WebElement ele:multiSkillsName){
	            if(ele.getText().equalsIgnoreCase(skill)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedtextChatMultiSkill)
	            	.release()
	            	.build()
	            	.perform();;
	                break;
	            }
	        }
	        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
	        selectWebElement(okButton);
	        }
	    }
	 
	 public void selectmultiAudioChatSkillNameForDragAndDrop(String skillName, String skLevel) throws Exception{
	        String[] a=skillName.split(",");
	        String[] b=skLevel.split(",");
	        for(String skill:a){
	        for(WebElement ele:multiSkillsName){
	            if(ele.getText().equalsIgnoreCase(skill)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedaudioChatMultiSkill)
	            	.release()
	            	.build()
	            	.perform();;
	                break;
	            }
	        }
	        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
	        selectWebElement(okButton);
	        }
	    }
	 
	 public void selectmultiVideoChatSkillNameForDragAndDrop(String skillName, String skLevel) throws Exception{
	        String[] a=skillName.split(",");
	        String[] b=skLevel.split(",");
	        for(String skill:a){
	        for(WebElement ele:multiSkillsName){
	            if(ele.getText().equalsIgnoreCase(skill)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedvideoChatMultiSkill)
	            	.release()
	            	.build()
	            	.perform();;
	                break;
	            }
	        }
	        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
	        selectWebElement(okButton);
	        }
	    }
	 
	 public void selectmultiFaxSkillNameForDragAndDrop(String skillName, String skLevel) throws Exception{
	        String[] a=skillName.split(",");
	        String[] b=skLevel.split(",");
	        for(String skill:a){
	        for(WebElement ele:multiSkillsName){
	            if(ele.getText().equalsIgnoreCase(skill)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedFaxMultiSkill)
	            	.release()
	            	.build()
	            	.perform();;
	                break;
	            }
	        }
	        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
	        selectWebElement(okButton);
	        }
	    }
	 
	 public void selectmultiEmailSkillNameForDragAndDrop(String skillName, String skLevel) throws Exception{
	        String[] a=skillName.split(",");
	        String[] b=skLevel.split(",");
	        for(String skill:a){
	        for(WebElement ele:multiSkillsName){
	            if(ele.getText().equalsIgnoreCase(skill)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedemailMultiSkill)
	            	.release()
	            	.build()
	            	.perform();;
	                break;
	            }
	        }
	        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
	        selectWebElement(okButton);
	        }
	    }
	 public void selectmultiSMSSkillNameForDragAndDrop(String skillName, String skLevel) throws Exception{
	        String[] a=skillName.split(",");
	        String[] b=skLevel.split(",");
	        for(String skill:a){
	        for(WebElement ele:multiSkillsName){
	            if(ele.getText().equalsIgnoreCase(skill)){
	            	Actions act=new Actions(driver);
	            	act.clickAndHold(ele)
	            	.pause(Duration.ofSeconds(2))
	            	.moveToElement(selectedsmsMultiSkill)
	            	.release()
	            	.build()
	            	.perform();;
	                break;
	            }
	        }
	        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
	        selectWebElement(okButton);
	        }
	    }
	 
	 public void selectmultiSkillNameForTransferAllTo(String skillName, String skLevel) throws Exception{
	        String[] a=skillName.split(",");
	        String[] b=skLevel.split(",");
	        for(String skill:a){
	        for(WebElement ele:multiSkillsName){
	            if(ele.getText().equalsIgnoreCase(skill)){
	            	selectWebElement(ele);
	            	selectWebElement(transferToSelectedList.get(1));
	                break;
	            }
	        }
	        enterValueToTxtField(skillLevel,b[Arrays.asList(a).indexOf(skill)]);
	        selectWebElement(okButton);
	        }
	    }
	 
	 public boolean verifyMultiSkillClearButton(AgentSkillAssignmentNewDetails details){
		    waitForJqueryLoad(driver);
		    selectSkillTab(details.getSkillType());
		    boolean status=false;
		    String skills=details.getMultipleSkills();
		    String[] a=skills.split(",");
		    for(String skill:a) {
	        for(WebElement ele:multiSkillsName) {
	            if(ele.getText().equalsIgnoreCase(skill))
	                status= true;
	        }
			  
	    }
		    return status;

}
	 
	 public void searchAgentSettingsRecord(String name) throws Exception  {
	        selectWebElement(searchBtn);
	        selectWebElement(selectSearchCol.get(0));
	        selectDropdownFromVisibleText(columnNameList,"Lan ID");
	        selectWebElement(selectSearchCol.get(1));
	        Thread.sleep(1000);
	        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
	        enterValueToTxtField(searchTextBox,name);
	        selectWebElement(searchSearchBtn);
	        waitUntilLoadingImageDisapper(driver);
	        waitUntilWebElementIsVisible(gridContent);
	    }
	 
	 
	 public void DeleteAgentSettingsRecord(AgentSettingsDetails details) throws Exception {
		    searchAgentSettingsRecord(details.getUsername());
	        Thread.sleep(3000);
	        btnClick(deleteBtn);
	        selectWebElement(deleteReasonTextBox);
	        enterValueToTxtFieldWithoutClear(deleteReasonTextBox,details.getDeleteReason());
	        selectWebElement(yesBtn);
	        }
	 
	 public boolean verifySearchIsnotEqualTo(String username) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("User Name", username);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"User Name");
		 Thread.sleep(1000);
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
		 enterValueToTxtField(searchTextBox,username);		
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
	 
	 public boolean verifySearchContains(String username) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("User Name", username);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"User Name");
		 Thread.sleep(1000);
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		 enterValueToTxtField(searchTextBox,username);		
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
	 
	 public boolean verifySearchDoesNotContains(String username) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("User Name", username);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"User Name");
		 Thread.sleep(1000);
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not Contain");
		 enterValueToTxtField(searchTextBox,username);		
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
	 
	 public boolean verifySearchStartsWith(String username) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("User Name", username);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"User Name");
		 Thread.sleep(1000);
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
		 enterValueToTxtField(searchTextBox,username);		
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
	 
	 public boolean verifySearchEndsWith(String username) throws Exception {
		 boolean Status=false;
		 Map<String,String>map=new HashMap<String,String>();
		 map.put("User Name", username);
		 selectWebElement(searchBtn);
		 selectWebElement(selectSearchColumn);
		 selectDropdownFromVisibleText(columnNameList,"User Name");
		 Thread.sleep(1000);
		 selectWebElement(condition);
		 selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
		 enterValueToTxtField(searchTextBox,username);		
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

			waitUntilWebElementIsVisible(auditGridContent);
			List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String col=null;
				for(int j=1;j<headers.size();j++){
					scrollToElement(headers.get(j));
					/*if(headers.get(j).getText().equals("Last Changed On")){
						col=cols.get(j).getText().substring(11);
						}
					else*/
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
	 
	 public boolean verifyinvalidsearchwithwrongdata(AgentSkillAssignmentNewDetails details) throws Exception {
		 searchAgentSkillListRecord(details.getUsername());
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
	 
	 public void verifySkillSearchWithoutSkill() {
		 waitForJqueryLoad(driver);
		 selectWebElement(SearchSkillsbtn.get(0));
	 }
	 
	 public void verifySkillSearchClearWithoutSkill() {
		 waitForJqueryLoad(driver);
		 selectWebElement(SearchSkillsbtn.get(1));
		 
	 }
	 
	 
	 
	 public boolean SearchAssignedSkillRecord(AgentSkillAssignmentNewDetails details) throws Exception {
		 boolean status=false;
		 selectWebElement(skillSearch.get(0));
		 System.out.println(details.getSkillName());
		 selectDropdownFromVisibleText(SkillSearchDropDown,details.getSkillName());
		 selectWebElement(SearchSkillsbtn.get(0));
			waitForJqueryLoad(driver);
			waitForJqueryLoad(driver);
			waitUntilWebElementIsVisible(pageArrowDropDown);
			selectWebElement(pageArrowDropDown);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			selectWebElement(pageArrowSkillList);
			waitUntilWebElementIsVisible(arrowGrid.get(1));
			List<WebElement> rows=arrowGrid.get(1).findElements(By.tagName("tr"));
			for(int i=1;i<rows.size();i++) {
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
					String col=cols.get(i).getText();
					if(col.equals(details.getSkillName()))
						status=true;
					break;
				}
		
			return status;
	}
			
	 public boolean ClearSearchAssignedSkillRecords(AgentSkillAssignmentNewDetails details) {
		 selectWebElement(skillSearch.get(0));
		 System.out.println(details.getSkillName());
		 selectDropdownFromVisibleText(SkillSearchDropDown,details.getSkillName());
		 selectWebElement(SearchSkillsbtn.get(0));
			waitForJqueryLoad(driver);
			waitForJqueryLoad(driver);
			waitUntilWebElementIsVisible(pageArrowDropDown);
			selectWebElement(SearchSkillsbtn.get(1));
			waitForJqueryLoad(driver);
			if(skillSearch.get(0).isSelected())
	        	return true;
	        else
			return false;
			
	 }
	 
	 public String verifyErrorMessage() {
			waitUntilWebElementListIsVisible(errorMsg);
				return errorMsg.get(0).getText();
		}
	 
}
