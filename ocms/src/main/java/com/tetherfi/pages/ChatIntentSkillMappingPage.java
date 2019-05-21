package com.tetherfi.pages;

import com.tetherfi.model.chat.ChatIntentSkillMappingDetails;
import com.tetherfi.model.ivr.IntentMappingDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatIntentSkillMappingPage extends BasePage {

    public ChatIntentSkillMappingPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement intentSkillMapping;
    
    @FindBy(xpath="//i[@class='fas fa-bars']")
	private WebElement CISMImg;

    @FindBy(id="create")
    private WebElement addNewIntentSkillMappingRecordBtn;

    @FindBy(css=".k-edit-form-container")
    private WebElement popupContent;

    @FindBy(css="span[aria-owns='ChatVdn_listbox']")
    private WebElement skillDropdown;

    @FindBy(css="ul[id='ChatVdn_listbox'] li")
    private List<WebElement> skillListBox;

    @FindBy(id="Segment")
    private WebElement segment;

    @FindBy(id="SubSegment")
    private WebElement subSegmnent;

    @FindBy(id="Intent")
    private WebElement intent;

    @FindBy(css="span[aria-owns='Language_listbox']")
    private WebElement languageDropdown;

    @FindBy(css="ul[id='Language_listbox'] li")
    private List<WebElement> languageListBox;

    @FindBy(id="RequestType")
    private WebElement requestType;

    @FindBy(css=".k-edit-form-container .form-group .k-numerictextbox .k-numeric-wrap .k-formatted-value")
    private List<WebElement> textbox;

    @FindBy(css="span[aria-owns='RequestType_listbox']")
    private WebElement channelDropdown;

    @FindBy(css="ul[id='RequestType_listbox'] li")
    private List<WebElement> channelListbox;

    @FindBy(id="ChatVdn")
    private WebElement chatVDN;

    @FindBy(id="CbaVdn")
    private WebElement cBAVDN;

    @FindBy(id="CustEntType")
    private WebElement custEntType;

    @FindBy(css=".k-edit-form-container .k-grid-update")
    private WebElement saveBtn;

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

    @FindBy(css="#tGrid .k-grid-content")
    private WebElement gridContent;

    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;

    @FindBy(css=".form-group #ModifyReason1")
    private  WebElement deleteReasonTextBox;

    @FindBy(css=".k-grid-edit")
    private WebElement editBtn;

    @FindBy(css=".k-edit-form-container #ModifyReason")
    private  WebElement modifyReasonTextBox;

    @FindBy(id="yesButton")
    private WebElement yesBtn;

    @FindBy(id="noButton")
    private WebElement noBtn;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
    
	@FindBy(css=".k-grid-cancel")
	private WebElement cancelBtn;

	@FindBy(css=".k-edit-form-container")
	private WebElement editFormContainer;

	@FindBy(css="div[style='display: block; z-index: 10002; opacity: 0.5;']")
	private WebElement deleteContainer;
	   
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
		    
	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[5]")
	private WebElement groupbyLanguage;
		
	@FindBy(xpath="//a[text()='Language']")
	private WebElement Language;
		    
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
		
	@FindBy(css="a[aria-label='Go to the last page']")
	private WebElement lastPageIcon;
			    
	@FindBy(css=".k-pager-sizes .k-icon")
	private WebElement pagerDropdown;
	
	@FindBy(css=".k-grid-update")
	private WebElement saveButton;
			    
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
	
	@FindBy(xpath="//a[text()='Intent']")
	private WebElement Intent;

    public boolean isChatIntentSkillMappingPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return intentSkillMapping.isEnabled();
    }

    public boolean VerifyLogo() {
    	if(CISMImg.isDisplayed())
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
		    if (f.getName().startsWith("Chat Intent Skill Mapping")) {
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
		Boolean Status=verifyExportPageFileDownload(filePath, "Chat Intent Skill Mapping");
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
				if(headers.get(j).getText().equals("Insert Date Time")){
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
				col=cols.get(j).getText();
				map.put(headers.get(j).getText(),col);
			}
			map.remove("");
			map.remove("Insert Date Time");
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
		DragandDrop(Language,droptarget);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(groupby.getText().split(": ")[1].equals(groupbyLanguage.getText()))
		{return true;}
		else
			return false;		
	}
	public boolean clearAll(ChatIntentSkillMappingDetails details) {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Segment");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),details.getSegment());
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
	public void searchwithoutextsearch() {
		selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Segment");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        selectWebElement(searchBtn);	
		selectWebElement(searchClose);		
	}
	public boolean verifyinvalidsearchwithwrongdata(ChatIntentSkillMappingDetails chatIntentSkillMappingDetails) {
		searchChatIntentSkillMappingRecord(chatIntentSkillMappingDetails.getSegment());
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
	
	public boolean ExporttoExcelWithoutData(ChatIntentSkillMappingDetails chatIntentSkillMappingDetails ) throws Exception {
		searchChatIntentSkillMappingRecord(chatIntentSkillMappingDetails.getSegment());
		waitForJqueryLoad(driver);
		Thread.sleep(1000);
		selectWebElement(exporttoexcel);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
		return false;
	}

	public void SortByAscending() {
		selectWebElement(Intent);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(Intent);
		selectWebElement(Intent);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public void addNewChatIntentSkillMappingRecord(ChatIntentSkillMappingDetails details){
        selectWebElement(addNewIntentSkillMappingRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(skillDropdown);
        selectDropdownFromVisibleText(skillListBox,details.getSkill());
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(segment);
        enterValueToTxtField(segment,details.getSegment());
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getSubSegment());
        selectWebElement(channelDropdown);
        selectDropdownFromVisibleText(channelListbox,details.getChannel());
        selectWebElement(custEntType);
        enterValueToTxtField(custEntType,details.getCustEntType());
        btnClick(saveBtn);
    }
    public void searchChatIntentSkillMappingRecord(String segment)  {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Segment");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),segment);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public void editIntentSkillMappingRecord(ChatIntentSkillMappingDetails details) {
        searchChatIntentSkillMappingRecord(details.getSegment());
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        selectWebElement(segment);
        enterValueToTxtField(segment,details.getUpdatedSegment());
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getUpdatedSubSegment());
        enterValueToTxtField(modifyReasonTextBox,details.getModifyReason());
        btnClick(saveBtn);
    }
    public void deleteIntentSkillMappingRecord(String segment, String reason) {
        searchChatIntentSkillMappingRecord(segment);
        btnClick(deleteBtn);
        selectWebElement(deleteReasonTextBox);
        enterValueToTxtField(deleteReasonTextBox,reason);
        selectWebElement(yesBtn);
    }

    public String getMessage(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        else {return successmsg.getText();}
    }
    public boolean isAddBtnDisplayed() {
    	return addNewIntentSkillMappingRecordBtn.isDisplayed() && addNewIntentSkillMappingRecordBtn.isEnabled();
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

	public void addRecordWithoutChannel(ChatIntentSkillMappingDetails details) {
		selectWebElement(addNewIntentSkillMappingRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(skillDropdown);
        selectDropdownFromVisibleText(skillListBox,details.getSkill());
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(segment);
        enterValueToTxtField(segment,details.getSegment());
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getSubSegment());
        selectWebElement(custEntType);
        enterValueToTxtField(custEntType,details.getCustEntType());
        btnClick(saveBtn);		
        selectWebElement(cancelBtn);
	}

	public void addRecordWithoutSkill(ChatIntentSkillMappingDetails details) {
		selectWebElement(addNewIntentSkillMappingRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(segment);
        enterValueToTxtField(segment,details.getSegment());
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getSubSegment());
        selectWebElement(channelDropdown);
        selectDropdownFromVisibleText(channelListbox,details.getChannel());
        selectWebElement(custEntType);
        enterValueToTxtField(custEntType,details.getCustEntType());
        btnClick(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public void addRecordWithoutIntent(ChatIntentSkillMappingDetails details) {
		selectWebElement(addNewIntentSkillMappingRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(skillDropdown);
        selectDropdownFromVisibleText(skillListBox,details.getSkill());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(segment);
        enterValueToTxtField(segment,details.getSegment());
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getSubSegment());
        selectWebElement(channelDropdown);
        selectDropdownFromVisibleText(channelListbox,details.getChannel());
        selectWebElement(custEntType);
        enterValueToTxtField(custEntType,details.getCustEntType());
        btnClick(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public void addRecordWithoutLanguage(ChatIntentSkillMappingDetails details) {
		selectWebElement(addNewIntentSkillMappingRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        selectWebElement(skillDropdown);
        selectDropdownFromVisibleText(skillListBox,details.getSkill());
        selectWebElement(intent);
        enterValueToTxtField(intent,details.getIntent());
        selectWebElement(segment);
        enterValueToTxtField(segment,details.getSegment());
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getSubSegment());
        selectWebElement(channelDropdown);
        selectDropdownFromVisibleText(channelListbox,details.getChannel());
        selectWebElement(custEntType);
        enterValueToTxtField(custEntType,details.getCustEntType());
        btnClick(saveBtn);
        selectWebElement(cancelBtn);
	}

	public void addNewEmptyRecord(ChatIntentSkillMappingDetails chatIntentSkillMappingDetails) {
		selectWebElement(addNewIntentSkillMappingRecordBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(popupContent);
        btnClick(saveBtn);	
        selectWebElement(cancelBtn);
	}
	
	public void clickOnAddRecord() {
		selectWebElement(addNewIntentSkillMappingRecordBtn);
	}
	public void clickOnCancelBtn() {
		selectWebElement(cancelBtn);
	}
	public boolean verifyEditFormContainer() {
		  try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        return isElementExist(editFormContainer);
	    
	}

	public void editChatIntentSkillMappingWithoutModifyReason(ChatIntentSkillMappingDetails details) {
		searchChatIntentSkillMappingRecord(details.getUpdatedSegment());
        waitForJqueryLoad(driver);
        selectWebElement(editBtn);
        waitForJqueryLoad(driver);
        selectWebElement(segment);
        enterValueToTxtField(segment,details.getUpdatedSegment());
        selectWebElement(subSegmnent);
        enterValueToTxtField(subSegmnent,details.getUpdatedSubSegment());
        btnClick(saveBtn);	
        selectWebElement(cancelBtn);
	}

	public void clickOnEditButton() {
        selectWebElement(editBtn);
		
	}

	public void deleteIntentMapingWithoutDeleteReasonRecord(ChatIntentSkillMappingDetails chatIntentSkillMappingDetails) throws Exception {
		searchChatIntentSkillMappingRecord(chatIntentSkillMappingDetails.getSegment());
		Thread.sleep(1000);
        selectWebElement(deleteBtn);
        selectWebElement(yesBtn);	
        selectWebElement(noBtn);			

	}
	public void clickOnDeleteButton() {
        selectWebElement(deleteBtn);
		
	}
	public void clickOnDeleteCancelBtn() {
        selectWebElement(noBtn);		
	}
	
	public boolean verifyDeleteContainer() {
		 try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        return isElementExist(deleteContainer);
	}

	public void addRecordWithoutSegment(ChatIntentSkillMappingDetails details) {
		 selectWebElement(addNewIntentSkillMappingRecordBtn);
	        waitForJqueryLoad(driver);
	        waitUntilWebElementIsVisible(popupContent);
	        selectWebElement(skillDropdown);
	        selectDropdownFromVisibleText(skillListBox,details.getSkill());
	        selectWebElement(intent);
	        enterValueToTxtField(intent,details.getIntent());
	        selectWebElement(languageDropdown);
	        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
	        selectWebElement(subSegmnent);
	        enterValueToTxtField(subSegmnent,details.getSubSegment());
	        selectWebElement(channelDropdown);
	        selectDropdownFromVisibleText(channelListbox,details.getChannel());
	        selectWebElement(custEntType);
	        enterValueToTxtField(custEntType,details.getCustEntType());
	        btnClick(saveBtn);		
	}

	public void addRecordWithoutSubSegment(ChatIntentSkillMappingDetails details) {
		 selectWebElement(addNewIntentSkillMappingRecordBtn);
	        waitForJqueryLoad(driver);
	        waitUntilWebElementIsVisible(popupContent);
	        selectWebElement(skillDropdown);
	        selectDropdownFromVisibleText(skillListBox,details.getSkill());
	        selectWebElement(intent);
	        enterValueToTxtField(intent,details.getIntent());
	        selectWebElement(languageDropdown);
	        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
	        selectWebElement(segment);
	        enterValueToTxtField(segment,details.getSegment());
	        selectWebElement(channelDropdown);
	        selectDropdownFromVisibleText(channelListbox,details.getChannel());
	        selectWebElement(custEntType);
	        enterValueToTxtField(custEntType,details.getCustEntType());
	        btnClick(saveBtn);		
	}

	public void addRecordWithoutCustomEntType(ChatIntentSkillMappingDetails details) {
		 selectWebElement(addNewIntentSkillMappingRecordBtn);
	        waitForJqueryLoad(driver);
	        waitUntilWebElementIsVisible(popupContent);
	        selectWebElement(skillDropdown);
	        selectDropdownFromVisibleText(skillListBox,details.getSkill());
	        selectWebElement(intent);
	        enterValueToTxtField(intent,details.getIntent());
	        selectWebElement(languageDropdown);
	        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
	        selectWebElement(segment);
	        enterValueToTxtField(segment,details.getSegment());
	        selectWebElement(subSegmnent);
	        enterValueToTxtField(subSegmnent,details.getSubSegment());
	        selectWebElement(channelDropdown);
	        selectDropdownFromVisibleText(channelListbox,details.getChannel());
	        btnClick(saveBtn);		
	}
		
}
