package com.tetherfi.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class OcmthresholdReportPage extends BasePage {

	public OcmthresholdReportPage(WebDriver driver) {
		super(driver);
	}

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
	
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
	
	@FindBy(css=".k-pager-sizes .k-icon")
	private WebElement pagerDropdown;
	
	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;
	
	@FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize;
	
	@FindBy(css=".k-grid-content")
	private WebElement gridContent;
	
	@FindBy(xpath="//a[text()='Agent Name']")
    private WebElement AgentName;
	
	@FindBy(css = "span[aria-owns='reportchannel_listbox']")
    private WebElement reportChannelDropdown;

    @FindBy(css = "ul[id='reportchannel_listbox'] li")
    private List<WebElement> reportChannelListBox;

    @FindBy(css = "span[aria-owns='reportname_listbox']")
    private WebElement reportNameDropdown;

    @FindBy(css = "ul[id='reportname_listbox'] li")
    private List<WebElement> reportNameListbox;

    @FindBy(css = "span[aria-owns='reporttype_listbox']")
    private WebElement reportTypeDropdown;

    @FindBy(css = "ul[id='reporttype_listbox'] li")
    private List<WebElement> reportTypeListbox;

    @FindBy(id = "singledate")
    private WebElement reportdate;

    @FindBy(id="startdate")
    private WebElement startDate;

    @FindBy(id="enddate")
    private WebElement endDate;

    @FindBy(id="searchRadioGroup")
    private WebElement advancedSearchBtn;

    @FindBy(id="tGrid")
	private WebElement auditGridContent;
	
	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
    private WebElement exporttoexcel;
	
	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearall;
	
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
    
    public void SortByAscending() {
		selectWebElement(AgentName);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SortByDescending() {
		selectWebElement(AgentName);
		selectWebElement(AgentName);
		selectWebElement(exporttoexcel);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clearAll() throws Exception {
		selectWebElement(reportChannelDropdown);
        selectWebElement(reportNameDropdown);
        selectWebElement(reportTypeDropdown);
        selectWebElement(reportdate);
        selectWebElement(startDate);
        selectWebElement(endDate);
        selectWebElement(clearall);	
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
}
