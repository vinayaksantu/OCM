package com.tetherfi.pages;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import  java.util.List;

import com.tetherfi.model.chat.CallBackManagementDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FeeWaiverPage extends BasePage {
    public FeeWaiverPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".fa-search")
    private WebElement searchBtn;

    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchCol;

    @FindBy(css = "ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css = "#1001sFormDropDown .k-i-arrow-60-down")
    private List<WebElement> selectSearchCriteria;

    @FindBy(css = "ul[id='1001sCriteria_listbox'] li")
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

    @FindBy(css = ".k-grid-content")
    private WebElement gridContent;

    @FindBy(css = ".ibox-title h5")
    private WebElement feeWaiver;

    @FindBy(css = ".k-button-icontext")//0-open 1-close 2-search
    private List<WebElement> changeStatusSearchList;

    @FindBy(css = "#start")
    private List<WebElement> startDate;

    @FindBy(css = "#end")
    private List<WebElement> endDate;

    @FindBy(css = "span[aria-owns='categories_listbox']")
    private WebElement statusDropdown;

    @FindBy(css = "ul[id='categories_listbox'] li")
    private List<WebElement> statusListBox;

    @FindBy(css = "#checkAllRow")
    private List<WebElement> checkAllRowBox;

    @FindBy(css = ".k-i-calendar")
    private List<WebElement> calendar;

    @FindBy(css = ".k-nav-fast")
    private List<WebElement> calendarTitle;

    @FindBy(css = ".k-i-clock")
    private List<WebElement> selectTimeDropdwn;

    @FindBy(css = "ul[id='start_timeview'] li")
    private List<WebElement> selectStartTimeListBox;

    @FindBy(css = "ul[id='end_timeview'] li")
    private List<WebElement> selectEndTimeListBox;


    @FindBy(css = ".k-i-arrow-60-left")
    private List<WebElement> leftArrowBtn;

    @FindBy(css = ".k-month")
    private List<WebElement> monthsTable;

    @FindBy(css=".checkbox")
    private List<WebElement> checkbox;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css = "#toast-container .toast-error")
    private List<WebElement> errorMsg;
    
    @FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement exporttoexcel;

    public boolean isFeeWaiverPageDisplayed() throws InterruptedException {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return feeWaiver.isEnabled();
    }

    public void changeStatusOpen(CallBackManagementDetails callBackManagementDetails) throws ParseException {
        selectWebElement(calendar.get(0));
        chooseDate(callBackManagementDetails.getStartDate(),0);
        Date dateParsed = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(callBackManagementDetails.getStartDate());
        String time = new SimpleDateFormat("HH:mm:ss").format(dateParsed);
        selectWebElement(selectTimeDropdwn.get(0));
       selectDropdownFromVisibleText(selectStartTimeListBox,time);
        selectWebElement(calendar.get(1));
        chooseDate(callBackManagementDetails.getEndDate(),1);
        Date dateParsed1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(callBackManagementDetails.getEndDate());
        String time1 = new SimpleDateFormat("HH:mm:ss").format(dateParsed1);
        selectWebElement(selectTimeDropdwn.get(1));
        selectDropdownFromVisibleText(selectEndTimeListBox,time1);
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,callBackManagementDetails.getStatus());
        selectWebElement(changeStatusSearchList.get(2));
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
        selectCheckbox(checkbox.get(0));
        selectWebElement(changeStatusSearchList.get(0));
    }
    public void changeStatusClosed(CallBackManagementDetails callBackManagementDetails) throws ParseException {
        selectWebElement(calendar.get(0));
        chooseDate(callBackManagementDetails.getStartDate(),0);
        Date dateParsed = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(callBackManagementDetails.getStartDate());
        String time = new SimpleDateFormat("HH:mm:ss").format(dateParsed);
        selectWebElement(selectTimeDropdwn.get(0));
        selectDropdownFromVisibleText(selectStartTimeListBox,time);
        selectWebElement(calendar.get(1));
        chooseDate(callBackManagementDetails.getEndDate(),1);
        Date dateParsed1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(callBackManagementDetails.getEndDate());
        String time1 = new SimpleDateFormat("HH:mm:ss").format(dateParsed1);
        selectWebElement(selectTimeDropdwn.get(1));
        selectDropdownFromVisibleText(selectEndTimeListBox,time1);
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,callBackManagementDetails.getStatus());
        selectWebElement(changeStatusSearchList.get(2));
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
        selectCheckbox(checkbox.get(0));
        selectWebElement(changeStatusSearchList.get(1));

    }

    public boolean verifyStatusChanged(){
      //  waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Successfully Updated"))
        {return true;}else{return false;}
    }

    public void chooseDate(String date,int index) throws ParseException {
        Date dateParsed = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(date);
        String inputDate = new SimpleDateFormat("MMMM YYYY").format(dateParsed);
        String day = new SimpleDateFormat("dd").format(dateParsed);
        if (day.startsWith("0")) {
             day = new SimpleDateFormat("d").format(dateParsed);
             }
        String capturedDate = calendarTitle.get(0).getText();
        while (!inputDate.equalsIgnoreCase(capturedDate)) {
            selectWebElement(leftArrowBtn.get(index));
            capturedDate = calendarTitle.get(index).getText();
        }
        List<WebElement> rows = monthsTable.get(index).findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(day)) {
                    if (!(cell.getAttribute("class").contains("k-other-month"))) {
                        selectWebElement(cell);
                        break;
                    }
                }
            }


        }

    }
    
    public boolean isExportBtnDisplayed() {
    	return exporttoexcel.isDisplayed() && exporttoexcel.isEnabled();
    }
    
}