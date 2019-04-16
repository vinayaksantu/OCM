package com.tetherfi.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.fax.SendFaxDetails;

public class OCMFaxSentDetailsReportPage extends BasePage {
	
	public OCMFaxSentDetailsReportPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".fa-search")
    private WebElement searchBtn;

    @FindBy(css = "span[aria-controls='1001sColumnName_listbox']")
    private WebElement searchColDropdown;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> searchColListBox;

    @FindBy(css = "span[aria-owns='1001sCriteria_listbox']")
    private WebElement searchCriteriaDropdown;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaListbox;

    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;

    @FindBy(id="1001sMaskedTextToSearch")
    private WebElement dateTimeSearchTextBox;

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

    @FindBy(css=".k-grid-norecords-template")
    private List<WebElement> norecords;
    
    @FindBy(css=".ibox-content")
    private WebElement gridContent;
    
    @FindBy(id="grid")
    private WebElement auditGridContent;
    
    @FindBy(xpath="//button[@class='k-button' and @title='Search']")
    private WebElement popupsearch;
    
    @FindBy(css ="span[aria-controls='853sColumnName_listbox']")
    private WebElement popupsearchColDropdown;
    
    @FindBy(css="ul[id='1360sColumnName_listbox'] li")
    private List<WebElement> popupsearchColListBox;

    @FindBy(css = "span[aria-owns='1360sCriteria_listbox']")
    private WebElement popupsearchCriteriaDropdown;

    @FindBy(css="ul[id='1360sCriteria_listbox'] li")
    private List<WebElement> popupsearchCriteriaListbox;

    @FindBy(id = "1360sTextToSearch")
    private WebElement popupsearchTextBox;	
    
    @FindBy(xpath="//div[@class='modal-footer']/button[@title='Search']")
    private WebElement popupsearchSearchBtn;
    
    @FindBy(id="gridDivSearchOne")
    private WebElement popupgrid;
    
    @FindBy(xpath="//div[@id='gridDrillOne']/div[4]")
    private WebElement grid;
    		


    
    public void searchReport(String SearchTextBox) {
        selectWebElement(searchBtn);
        selectWebElement(searchColDropdown);
        selectDropdownFromVisibleText(searchColListBox,"Sender Number");
        selectWebElement(searchCriteriaDropdown);
        selectDropdownFromVisibleText(searchCriteriaListbox,"Is equal to");
        enterValueToTxtField(searchTextBox,SearchTextBox);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    
    public boolean VerifySendStatus(SendFaxDetails details) {
    	Boolean status =false;
    	searchReport(details.getFaxLine());
		List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
		selectWebElement(rows.get(1)); 		
		List<WebElement> row=popupgrid.findElements(By.tagName("tr"));
		List<WebElement> headers = row.get(0).findElements(By.tagName("th"));
		String col=null;
			List<WebElement> cols=grid.findElements(By.tagName("td"));
			for(int j=0;j<headers.size();j++) {
				if(headers.get(j).getText().equals("Send Status")){
					col=cols.get(j).getText();
					if(col.equals("SendSuccess"))
						status=true;
					else 
						status=false;
				}
			}	
		return status;
    }
    
}
