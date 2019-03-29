package com.tetherfi.pages;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.FaxRoutingConfigurationDetails;
import com.tetherfi.model.fax.FaxSendersDetails;
import com.tetherfi.model.fax.SendFaxDetails;
import com.tetherfi.model.ivr.AdhocOptionEnhancementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AgentTeamMgmtDetails;
import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import com.tetherfi.model.tmac.WaitTimeColorConfigDetails;
import com.tetherfi.model.tmac.WorkCodeListDetails;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OCMReportsPage extends BasePage {

    public OCMReportsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "OCMReportsli")
    private WebElement ocmReportsTab;

    @FindBy(css=".ibox-title h5")
    private WebElement ocmReportsManager;

    @FindBy(id = "form")
    private WebElement formContents;

    @FindBy(id="grid")
    private WebElement auditGridContent;
    
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

    @FindBy(css=".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchColumn;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchTypeList;

    @FindBy(css="#advanced .k-textbox")
    private List<WebElement> searchText;

    @FindBy(css = ".k-Show")
    private List<WebElement> showReportBtn;

    @FindBy(id = "grid")
    private WebElement gridBoxContent;

    @FindBy(id = "reportNameLbl")
    private WebElement reportnameLbl;

    @FindBy(id="ExportReportBtn")
    private WebElement exportReportBtn;

    @FindBy(id="showDownloadedReportBtn")
    private WebElement viewDownloadedReport;

    @FindBy(id="ScheduleReportBtn")
    private WebElement scheduleReport;

    @FindBy(id="clearAllSearch")
    private WebElement clearAll;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css="#toast-container .toast-error")
    private List<WebElement> errorMsg;

    //Reports Download page web elements
    @FindBy(id="notifications")
    private WebElement notificationIcon;

    @FindBy(id="nav")
    private WebElement notificationPan;

    @FindBy(css="ul[id='nav'] li a div")
    private List<WebElement> notificationReportNameList;

    @FindBy(css="ul[id='nav'] li span")
    private List<WebElement> notificationReportGeneratedOnList;

    @FindBy(css=".k-grid-content table[role='grid']")
    private WebElement gridTable;

    @FindBy(css=".k-grid-excel")
    private WebElement exportPage;

    @FindBy(css="button[onclick='onSelectExportAll()']")
    private WebElement exportToCSV;

    @FindBy(css=".k-i-download")
    private WebElement viewDownloadedReportAtShowReportsPage;

    @FindBy(css=".k-i-clock")
    private WebElement scheduledReportatShowReportsPage;

    @FindBy(css=".k-pager-info")
    private WebElement pagerInfo;

    @FindBy(css=".k-pager-sizes .k-input")
    private WebElement pagerSize;

    @FindBy(css=".k-pager-sizes .k-icon")
    private WebElement pagerDropdown;

    @FindBy(css=".k-animation-container ul li")
    private List<WebElement> pageSizeListBox;

    @FindBy(css="a[aria-label='Go to the next page']")
    private WebElement nextPageIcon;

    @FindBy(css=".ibox-content")
    private WebElement gridContent;

    @FindBy(css=".k-i-more-vertical")
    private List<WebElement> headersDropdown;

    @FindBy(css=".k-sort-asc")
    private List<WebElement> sortAscending;

    @FindBy(css=".k-sort-desc")
    private List<WebElement> sortDescending;

    @FindBy(css=".k-columns-item")
    private List<WebElement> columns;

    @FindBy(id="autoCompleteTextbox")
    private WebElement searchByTextBox;

    @FindBy(css="ul[id='autoCompleteTextbox_listbox'] li")
    private List<WebElement> searchByListBox;

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

    @FindBy(css=".fa-refresh")
    private WebElement refreshBtn;

    @FindBy(css=".k-grid-Download")
    private WebElement downloadreportBtn;

    @FindBy(css = ".k-pager-last")
    private WebElement goToLastPage;

    @FindBy(css = ".k-state-selected")
    private WebElement pageNumber;

    @FindBy(css=".k-grouping-header")
    private WebElement groupingHeader;

    @FindBy(css=".k-group-indicator")
    private List<WebElement> groupname;

    @FindBy(css=".k-group-indicator .k-bare")
    private List<WebElement> groupClose;

    @FindBy(css=".k-grid-pdf")
    private WebElement exportToPDF;
    
    @FindBy(id="1001sAddButton")
    private WebElement plusbutton;
    
    @FindBy(xpath="//label[@for='1001sRadioAND']")
    private WebElement andradiobtn;
    
    @FindBy(css="ul[id='1002sColumnName_listbox'] li")
    private List<WebElement> columnNameListtwo;
    
    @FindBy(css="ul[id='1002sCriteria_listbox'] li")
    private List<WebElement> searchTypeListtwo;
    
    @FindBy(id="1002sTextToSearch")
    private WebElement searchTextBoxtwo;
    
    public boolean isShowButtonsDisplayed() {
    	return showReportBtn.get(0).isDisplayed() && showReportBtn.get(1).isDisplayed() && showReportBtn.get(0).isEnabled() && showReportBtn.get(1).isEnabled();    	
    }
    
    public boolean isExportBtnDisplayed() {
    	return exportReportBtn.isDisplayed() && exportReportBtn.isEnabled();
    }
    
    public boolean isExportBtnNotDisplayed() {
    	if(exportReportBtn.isDisplayed() && exportReportBtn.isEnabled())
    		return false;
    	else
    		return true;
    }
    
    public boolean isOCMReportPageIsDisplayed() {
        waitForLoad(driver);
       // waitForJqueryLoad(driver);
        return ocmReportsTab.isEnabled();
    }
    public boolean isDownloaderPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return ocmReportsManager.isEnabled();
    }
    public void showReport(ReportDetails details) {
        chooseReport(details);
        if(details.getAdvancedsearch().equalsIgnoreCase("Yes")){chooseAdvancedSearch(details);}
        selectWebElement(showReportBtn.get(0));
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridBoxContent);
    }
    public void chooseAdvancedSearch(ReportDetails details){
    try{selectWebElement(advancedSearchBtn);
    selectWebElement(selectSearchColumn.get(0));
    Thread.sleep(2000);
    selectDropdownFromVisibleText(columnNameList,details.getColname());
    selectWebElement(selectSearchColumn.get(1));
    Thread.sleep(2000);
    selectDropdownFromVisibleText(searchTypeList,details.getColtype());
    enterValueToTxtField(searchText.get(0),details.getSearchStr());
    }catch(Exception e){e.printStackTrace();}}
    public void showReportInNewPage(ReportDetails details) {
        chooseReport(details);
        selectWebElement(showReportBtn.get(1));
        switchToNewWindow();
        waitForLoad(driver);
        waitForJqueryLoad(driver);
      waitUntilWebElementIsVisible(gridBoxContent);
    }
    public void exportReport(ReportDetails details){
        chooseReport(details);
        selectWebElement(exportReportBtn);
    }
    public void scheduleReport(ReportDetails details){
        chooseReport(details);
        selectWebElement(scheduleReport);
    }
    public void viewDownloadedReportInNotificationTab(){
        selectWebElement(viewDownloadedReport);
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        selectWebElement(notificationIcon);
        waitUntilWebElementIsVisible(notificationPan);
    }
    public void viewDownloadedReportInReportsDownloadsPage() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        selectWebElement(viewDownloadedReport);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForLoad(driver);
        waitForJqueryLoad(driver);
    }
    public boolean verifyDownloadedReportNameInNotificationPan(String reportname){
        if(notificationReportNameList.get(0).getText().contains(reportname)){
            return true;}else{
            System.out.println("Wrong Report name:"+notificationReportNameList.get(0).getText());return false;}
        }
    public boolean verifyDownloadedReportGeneratedTime(){
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        if(notificationReportGeneratedOnList.get(0).getText().contains("Generated On : "+date)){
            return true;
        }else {System.out.println("Wrong Report generated Date:"+notificationReportGeneratedOnList.get(0).getText());return false;}
    }
    public boolean verifyDownloadedReportNameAndTimeInReportsDownloadPage(String reportname){
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        Map<String,String> map=getFirstRowDatafromTable();
        if(map.get("Report Name").equalsIgnoreCase(reportname)){
            if(map.get("Report Generated On").contains(date)){return true;}else{System.out.println("Wrong Report Generated Date:"+map.get("Report Generated On"));return false;}
    }else{System.out.println("Wrong Report Name:"+map.get("Report Name"));return false;}
    }
    public boolean verifyReportDisplayed(ReportDetails details) {
        if (reportnameLbl.getText().contains("OCM Reports > " + details.getReportChannel() + " > " + details.getReportName() + " on " + details.getReportDate())) {
            return true;
        } else {
            return false;
        }
    }
    public boolean verifyAuditTrailReportDisplayed(String trans ,String fun) {
    boolean status=false;
    List<Map<String,String>> data=getAllDatafromTable();
    for(Map<String,String> map:data){
        if(map.get("Transaction").equalsIgnoreCase(trans)){
            if(map.get("Function").equalsIgnoreCase(fun)){
                status=true;break;
            }
        }
    }
    return status;
    }
    public boolean verifyReportHeaders(ReportDetails details){
        boolean status=false;
        String[] headers=details.getReportHeaders().split(",");
        List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
        List<WebElement> gridheaders = rows.get(0).findElements(By.tagName("th"));
        for(String head:headers){
            status=false;
            for(WebElement e:gridheaders){
               scrollToElement(e);
                if(e.getText().equals(head)){status=true;break;}
            }
            if(status){}else{System.out.println(head+" header not present in this report");break;}
        }
    return status;
    }
    public boolean verifyDateRangeReportDisplayed(ReportDetails details) {
        if (reportnameLbl.getText().contains("OCM Reports > " + details.getReportChannel() + " > " + details.getReportName() + " from " + details.getStartDate()+" to "+details.getEndDate())){
            return true;
        } else {
            return false;
        }
    }
    public boolean verifyReportExported(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Report Export is Initiated... Notification will be sent once Completed"))
        {return true;}else{return false;}
    }
    public boolean verifyScheduleReport(){
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return (ocmReportsManager.getText().equals("Report Scheduler"));
    }
    
    public void chooseReportChannel(String rptChannel) {
    	waitForJqueryLoad(driver);
    	selectWebElement(reportChannelDropdown);
        selectDropdownFromVisibleText(reportChannelListBox, rptChannel);
    }
    
    public void chooseReportName(String rptName) {
    	waitForJqueryLoad(driver);
    	selectWebElement(reportNameDropdown);
        selectDropdownFromVisibleText(reportNameListbox, rptName);
    }
    
    public void chooseReport(ReportDetails details)
    {
        waitUntilWebElementIsVisible(formContents);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(reportChannelDropdown);
        selectDropdownFromVisibleText(reportChannelListBox, details.getReportChannel());
        waitForJqueryLoad(driver);
        selectWebElement(reportNameDropdown);
        selectDropdownFromVisibleText(reportNameListbox, details.getReportName());
        waitForJqueryLoad(driver);
        selectWebElement(reportTypeDropdown);
        selectDropdownFromVisibleText(reportTypeListbox, details.getReportType());
        if(details.getReportType().equalsIgnoreCase("Single Date"))
        {enterValueToTxtField(reportdate, formatDate(details.getReportDate()));
            reportdate.sendKeys(Keys.TAB);}
        else if(details.getReportType().equalsIgnoreCase("Date Range")){
            enterValueToTxtField(startDate,formatDate(details.getStartDate()));
            startDate.sendKeys(Keys.TAB);
            enterValueToTxtField(endDate,formatDate(details.getEndDate()));
            endDate.sendKeys(Keys.TAB);
        }
    }
    public String formatDate(String date){
        Date dateParsed = null;
        try {
            dateParsed = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String inputDate = new SimpleDateFormat("yyyyMMdd").format(dateParsed);
    return inputDate;
    }
    public void exportPage(){
        emptyDownloadsDirectory(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
        selectWebElement(exportPage);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void exportToCSV(){
        emptyDownloadsDirectory(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
        selectWebElement(exportToCSV);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void viewDownloadedReports(){
        selectWebElement(viewDownloadedReportAtShowReportsPage);
        waitForLoad(driver);
        waitForJqueryLoad(driver);
    }
    public void downloadReport(){
    if(isDownloaderPageDisplayed())
    {try {
    emptyDownloadsDirectory(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
    selectWebElement(downloadreportBtn);
    Thread.sleep(5000);
    } catch (InterruptedException e) {
    e.printStackTrace();
    }
    }}
    public void scheduledReports(){
        selectWebElement(scheduledReportatShowReportsPage);
        waitForLoad(driver);
        waitForJqueryLoad(driver);
    }
    public boolean verifyExportPageFileDownloaded(String reportname){
        return verifyExportPageFileDownload(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles",reportname);
    }
    public boolean verifyReportPageFileDownloaded(){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return verifyExportPageFileDownload(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles","OCMReportDownload-"+date);
    }
    private List<Map<String, String>> getAllDatafromTable(){
        int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.getText());
        int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
        List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();
        for(int i=0;i<=pages;i++){
            List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
            List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
            for(int j=1;j<=rows.size()-1;j++){
                List<WebElement> cols=rows.get(j).findElements(By.tagName("td"));
                Map<String,String> map=new HashMap<>();
                for(int k=0; k<cols.size();k++){
                   map.put(headers.get(k).getText(),cols.get(k).getText());
                }
                maplist.add(map);
                }
            if(i<pages){    nextPageIcon.click();
            waitForJqueryLoad(driver);}
            }
        return maplist;
        }
    private List<String> getColumnDatafromTable(String columnname){
            List<String> list = new ArrayList<>();
            List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
            List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
            int colindex=0;
            for(WebElement e:headers){if(e.getText().equals(columnname)){break;}else{colindex++;}}
            for(int j=1;j<=rows.size()-1;j++){
                List<WebElement> cols=rows.get(j).findElements(By.tagName("td"));
                list.add(cols.get(colindex).getText());
            }
            return list;
    }
    public boolean verifySorting() {
        boolean status=false;
        int items = Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
        int pagersize = Integer.valueOf(pagerSize.getText());
        int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
        for (int i = 0; i <= pages; i++) {
            List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
            List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
            int k=0;
            for(int j=0;j<headers.size();j++){
            if(headers.get(j).getText().equals("")||headers.get(j).getText().equals(" ")){continue;}
            List<String> l1 = getColumnDatafromTable(headers.get(j).getText());
            //System.out.println(l1);
            List<String> temp = l1;
            Collections.sort(temp);
            //System.out.println(temp);
            selectWebElement(headersDropdown.get(k));
            waitForJqueryLoad(driver);
            selectWebElement(sortAscending.get(k));
            waitForJqueryLoad(driver);
            List<String> l2 = getColumnDatafromTable(headers.get(j).getText());
            //System.out.println(l2);
            if (l2.equals(temp)) {/*System.out.println("sorting works fine");*/status = true;}else{status=false;}
            if(status){}else{System.out.println("Ascending sorting failed for column name:"+headers.get(j).getText()+"\n"+l2);break;}
            /*descending sort code*/
            status=false;
            temp = l1;
            Collections.sort(temp,Collections.reverseOrder());
            //System.out.println(temp);
            selectWebElement(headersDropdown.get(k));
            waitForJqueryLoad(driver);
            selectWebElement(sortDescending.get(k));
            waitForJqueryLoad(driver);
            k++;
            List<String> l3 = getColumnDatafromTable(headers.get(j).getText());
            //System.out.println(l3);
            if (l3.equals(temp)) {/*System.out.println("sorting works fine");*/status = true;}
            if(status){}else{System.out.println("Descending sorting failed for column name:"+headers.get(j).getText()+"\n"+l3);break;}
            }
            if(status){}else{break;}
            nextPageIcon.click();
            waitForJqueryLoad(driver);
        }
        return status;
    }
    private String getProperHeadersInGrid(String cname){
        List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        for(WebElement e:headers){if(cname.contains(e.getText())){return e.getText();}}
        return "";
    }
    public boolean verifySearchByTextbox(){
        boolean status=false;
        String colname=searchByTextBox.getAttribute("placeholder").split("Search by ")[1];
        if(colname.contains(".")){colname=colname.split("\\.")[0];}
        colname=getProperHeadersInGrid(colname);
        List<String> searchList=getColumnDatafromTable(colname);
        enterValueToTxtField(searchByTextBox,searchList.get(0));
    try {
        Thread.sleep(3000);
        selectDropdownFromVisibleText(searchByListBox,searchList.get(0));
        Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
        int pagersize=Integer.valueOf(pagerSize.getText());
        int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
        for(int i=0;i<=pages;i++){
        status=false;
        List<String> searchList1=getColumnDatafromTable(colname);
        for(String s:searchList1){
            if(s.equals(searchList.get(0))){status=true;}
            else{System.out.println(s+" does not contain "+searchList.get(0));status=false;break;}
        }
        if(status){}else{break;}
        selectWebElement(nextPageIcon);
        waitForJqueryLoad(driver);
        }
        return status;
    }
    public boolean verifySearchByColumnValue(){
        boolean status=false;
        List<Map<String,String>> table=getAllDatafromTable();
        List<String> colDataFromTable=new ArrayList<>();
        List<String> search=new ArrayList<>();
        for(String key:table.get(0).keySet()){
            if(key.equals("")||key.equals(" ")){continue;}
            String srch=table.get(0).get(key);
            if(srch.equals("")||srch.contains("\n")){srch="test";}
            searchReport(key,"Is equal to",srch);
            for(Map<String,String> m:table){
                if(m.get(key).equalsIgnoreCase(srch)){colDataFromTable.add(m.get(key));}
            }
            if(norecords.size()<=0){
            int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
            int pagersize=Integer.valueOf(pagerSize.getText());
            int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
            for(int i=0;i<=pages;i++){
                status=false;
                List<String> searchList=getColumnDatafromTable(key);
                search.addAll(searchList);
                for(String s:searchList){
                    if(s.equalsIgnoreCase(srch)){status=true;}
                    else{System.out.println(s+" Is not equals To "+srch);status=false;break;}
                }
                if(status){}else{break;}
                selectWebElement(nextPageIcon);
                waitForJqueryLoad(driver);
            }
        //verify the table and list
        if(search.equals(colDataFromTable)){status=true;}else{System.out.println("Search failed in count");System.out.println(search+"\n"+colDataFromTable);status=false;break;}
        //verify the table and list
        }else{System.out.println("no records to verify"); status=true;}
        selectWebElement(refreshBtn);colDataFromTable.clear();search.clear();
        waitForJqueryLoad(driver);
        }
return status;
    }
    private Map<String,String> getFirstRowDatafromTable(){
        Map<String,String> map = new HashMap<>();
        List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
        for(int j=0;j<headers.size();j++){
            map.put(headers.get(j).getText(),cols.get(j).getText());
        }
        return map;
    }
    public void searchReport(String colname, String criteria, String searchString) {
        selectWebElement(searchBtn);
        selectWebElement(searchColDropdown);
        selectDropdownFromVisibleText(searchColListBox,colname);
        selectWebElement(searchCriteriaDropdown);
        selectDropdownFromVisibleText(searchCriteriaListbox,criteria);
        if(searchTextBox.isDisplayed())
        {enterValueToTxtField(searchTextBox,searchString);}
        else {enterValueToTxtField(dateTimeSearchTextBox,searchString);}
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
    }
    public boolean verifySearchContainsColumnValue(){
        boolean status=false;
        List<Map<String,String>> table=getAllDatafromTable();
        List<String> colDataFromTable=new ArrayList<>();
        List<String> search=new ArrayList<>();
        for(String key:table.get(0).keySet()){
            if(key.contains("Duration")||key.contains("Time")||key.equals("")||key.equals(" ")){continue;}
            String srch=table.get(0).get(key);
            if(srch.equals("")||srch.contains("\n")){srch="test";}
            searchReport(key,"Contains",srch);
            for(Map<String,String> m:table){
                //if(m.get(key).contains(srch)){colDataFromTable.add(m.get(key));}
                if(StringUtils.containsIgnoreCase(m.get(key),srch)){colDataFromTable.add(m.get(key));}
            }
            if(norecords.size()<=0){
                int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
                int pagersize=Integer.valueOf(pagerSize.getText());
                int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
                for(int i=0;i<=pages;i++){
                    status=false;
                    List<String> searchList=getColumnDatafromTable(key);
                    search.addAll(searchList);
                    for(String s:searchList){
                        //if(s.contains(srch)){status=true;}
                        if(StringUtils.containsIgnoreCase(s,srch)){status=true;}
                        else{System.out.println(s+" Is not contains "+srch);status=false;break;}
                    }
                    if(status){}else{break;}
                    selectWebElement(nextPageIcon);
                    waitForJqueryLoad(driver);
                }
                //verify the table and list
                if(search.equals(colDataFromTable)){status=true;}else{System.out.println("Search failed in count");System.out.println(search+"\n"+colDataFromTable);status=false;break;}
                //verify the table and list
            }else{System.out.println("no records to verify"); status=true;}
            selectWebElement(refreshBtn);colDataFromTable.clear();search.clear();
            waitForJqueryLoad(driver);
        }
        return status;
    }
    public boolean verifySearchIsNotEqualsColumnValue(){
        boolean status=false;
        List<Map<String,String>> table=getAllDatafromTable();
        List<String> colDataFromTable=new ArrayList<>();
        List<String> search=new ArrayList<>();
        for(String key:table.get(0).keySet()){
            if(key.equals("")||key.equals(" ")){continue;}
            String srch=table.get(0).get(key);
            if(srch.equals("")||srch.contains("\n")){srch="test";}
            searchReport(key,"Is not equal to",srch);
            for(Map<String,String> m:table){
                if(!m.get(key).equalsIgnoreCase(srch)){colDataFromTable.add(m.get(key));}
            }
            if(norecords.size()<=0){
                int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
                int pagersize=Integer.valueOf(pagerSize.getText());
                int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
                for(int i=0;i<=pages;i++){
                    status=false;
                    List<String> searchList=getColumnDatafromTable(key);
                    search.addAll(searchList);
                    for(String s:searchList){
                        if(!s.equalsIgnoreCase(srch)){status=true;}
                        else{System.out.println(s+" Is equals To "+srch);status=false;break;}
                    }
                    if(status){}else{break;}
                    selectWebElement(nextPageIcon);
                    waitForJqueryLoad(driver);
                }
                //verify the table and list
                if(search.equals(colDataFromTable)){status=true;}else{System.out.println("Search failed in count");System.out.println(search+"\n"+colDataFromTable);status=false;break;}
                //verify the table and list
            }else{System.out.println("no records to verify"); status=true;}
            selectWebElement(refreshBtn);colDataFromTable.clear();search.clear();
            waitForJqueryLoad(driver);
        }
        return status;
    }
    public boolean verifySearchStartsWithColumnValue(){
        boolean status=false;
        List<Map<String,String>> table=getAllDatafromTable();
        List<String> colDataFromTable=new ArrayList<>();
        List<String> search=new ArrayList<>();
        for(String key:table.get(0).keySet()){
            if(key.contains("Duration")||key.contains("Time")||key.equals("")||key.equals(" ")){continue;}
            String srch=table.get(0).get(key);
            if(srch.equals("")||srch.contains("\n")){srch="test";}
            searchReport(key,"Starts with",srch);
            for(Map<String,String> m:table){
                if(StringUtils.startsWithIgnoreCase(m.get(key),srch)){colDataFromTable.add(m.get(key));}
            }
            if(norecords.size()<=0){
                int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
                int pagersize=Integer.valueOf(pagerSize.getText());
                int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
                for(int i=0;i<=pages;i++){
                    status=false;
                    List<String> searchList=getColumnDatafromTable(key);
                    search.addAll(searchList);
                    for(String s:searchList){
                        if(StringUtils.startsWithIgnoreCase(s,srch)){status=true;}
                        else{System.out.println(s+" Is does'nt starts with "+srch);status=false;break;}
                    }
                    if(status){}else{break;}
                    selectWebElement(nextPageIcon);
                    waitForJqueryLoad(driver);
                }
                //verify the table and list
                if(search.equals(colDataFromTable)){status=true;}else{System.out.println("Search failed in count");System.out.println(search+"\n"+colDataFromTable);status=false;break;}
                //verify the table and list
            }else{System.out.println("no records to verify"); status=true;}
            selectWebElement(refreshBtn);colDataFromTable.clear();search.clear();
            waitForJqueryLoad(driver);
        }
        return status;
    }
    public boolean verifySearchEndsWithColumnValue(){
        boolean status=false;
        List<Map<String,String>> table=getAllDatafromTable();
        List<String> colDataFromTable=new ArrayList<>();
        List<String> search=new ArrayList<>();
        for(String key:table.get(0).keySet()){
            if(key.contains("Duration")||key.contains("Time")||key.equals("")||key.equals(" ")){continue;}
            String srch=table.get(0).get(key);
            if(srch.equals("")||srch.contains("\n")){srch="test";}
            searchReport(key,"Ends with",srch);
            for(Map<String,String> m:table){
                if(StringUtils.endsWithIgnoreCase(m.get(key),srch)){colDataFromTable.add(m.get(key));}
            }
            if(norecords.size()<=0){
                int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
                int pagersize=Integer.valueOf(pagerSize.getText());
                int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
                for(int i=0;i<=pages;i++){
                    status=false;
                    List<String> searchList=getColumnDatafromTable(key);
                    search.addAll(searchList);
                    for(String s:searchList){
                        if(StringUtils.endsWithIgnoreCase(s,srch)){status=true;}
                        else{System.out.println(s+" Is does'nt starts with "+srch);status=false;break;}
                    }
                    if(status){}else{break;}
                    selectWebElement(nextPageIcon);
                    waitForJqueryLoad(driver);
                }
                //verify the table and list
                if(search.equals(colDataFromTable)){status=true;}else{System.out.println("Search failed in count");System.out.println(search+"\n"+colDataFromTable);status=false;break;}
                //verify the table and list
            }else{System.out.println("no records to verify"); status=true;}
            selectWebElement(refreshBtn);colDataFromTable.clear();search.clear();
            waitForJqueryLoad(driver);
        }
        return status;
    }
    public boolean verifySearchDoesNotContainsColumnValue(){
        boolean status=false;
        List<Map<String,String>> table=getAllDatafromTable();
        List<String> colDataFromTable=new ArrayList<>();
        List<String> search=new ArrayList<>();
        for(String key:table.get(0).keySet()){
            if(key.contains("Duration")||key.contains("Time")||key.equals("")||key.equals(" ")){continue;}
            String srch=table.get(0).get(key);
            if(srch.equals("")||srch.contains("\n")){srch="test";}
            searchReport(key,"Does not contain",srch);
            for(Map<String,String> m:table){
                //if(m.get(key).contains(srch)){colDataFromTable.add(m.get(key));}
                if(!StringUtils.containsIgnoreCase(m.get(key),srch)){colDataFromTable.add(m.get(key));}
            }
            if(norecords.size()<=0){
                int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
                int pagersize=Integer.valueOf(pagerSize.getText());
                int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
                for(int i=0;i<=pages;i++){
                    status=false;
                    List<String> searchList=getColumnDatafromTable(key);
                    search.addAll(searchList);
                    for(String s:searchList){
                        //if(s.contains(srch)){status=true;}
                        if(!StringUtils.containsIgnoreCase(s,srch)){status=true;}
                        else{System.out.println(s+" Is contains "+srch);status=false;break;}
                    }
                    if(status){}else{break;}
                    selectWebElement(nextPageIcon);
                    waitForJqueryLoad(driver);
                }
                //verify the table and list
                if(search.equals(colDataFromTable)){status=true;}else{System.out.println("Search failed in count");System.out.println(search+"\n"+colDataFromTable);status=false;break;}
                //verify the table and list
            }else{System.out.println("no records to verify"); status=true;}
            selectWebElement(refreshBtn);colDataFromTable.clear();search.clear();
            waitForJqueryLoad(driver);
        }
        return status;
    }

    public boolean verifyNumberOfItemsPerPage() {
        boolean status = false;
        try {
            if (norecords.size() <= 0) {
                int items = Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
                selectWebElement(pagerDropdown);
                Thread.sleep(1500);
                for (int i = 0; i < pageSizeListBox.size(); i++) {
                    if(Integer.valueOf(pageSizeListBox.get(i).getText())>items){continue;}
                    selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
                    waitForJqueryLoad(driver);
                    int totalItems = Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
                    int pagersize = Integer.valueOf(pagerSize.getText());
                    int pages = (totalItems % pagersize == 0) ? items / pagersize : items / pagersize+1;
                    int totalRows=(gridContent.findElements(By.tagName("tr")).size())-1;
                    selectWebElement(goToLastPage);
                    waitForJqueryLoad(driver);
                    int lastPageNumber = Integer.valueOf(pageNumber.getText());
                    if (items == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
                        status = true;
                    } else {System.out.println(items+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
                        status = false;
                        break;
                    }selectWebElement(pagerDropdown);Thread.sleep(1500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return status;
    }
    public void closeGrouping(){
        if(!groupingHeader.equals("Drag a column header and drop it here to group by that column")){
            for(int i=0;i<groupname.size();i++){
                String groupedName=groupname.get(i).getText();
                selectWebElement(groupClose.get(i));
            }
        }
    }
    public void enableColumns(){
        selectWebElement(headersDropdown.get(1));
        selectWebElement(columns.get(0));
        selectWebElement(columns.get(0).findElements(By.tagName("input")).get(0));
        selectWebElement(refreshBtn);
    }
    public void dragandDropColumns(String col1, String col2)
    {
        List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        WebElement column1=null,column2=null;
        for(WebElement e:headers){
            if(e.getText().equals(col1)){column1=e;}
            if(e.getText().equals(col2)){column2=e;}
        }
        Actions builder = new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(column1).moveToElement(column2).release(column2).build();
        dragAndDrop.perform();
    }
    public void exportToPDF(){
        emptyDownloadsDirectory(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
        selectWebElement(exportToPDF);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean verifySortingForAllDataDisplayed(){
        boolean status=false;
        List<Map<String,String>> table=getAllDatafromTable();
        List<String> colDataFromTable=new ArrayList<>();
        List<String> sort=new ArrayList<>();
        List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        int k=0;
        for(int j=0;j<headers.size();j++){
            String key=headers.get(j).getText();
            for(Map<String,String> m:table){
                colDataFromTable.add(m.get(key));
            }
            selectWebElement(refreshBtn);
            if(key.equals("")||key.equals(" ")){continue;}
            selectWebElement(headersDropdown.get(k));
            waitForJqueryLoad(driver);
            selectWebElement(sortAscending.get(k));
            waitForJqueryLoad(driver);
            Collections.sort(colDataFromTable);
            int items=Integer.valueOf(pagerInfo.getText().split("of ")[1].split(" items")[0]);
            int pagersize=Integer.valueOf(pagerSize.getText());
            int pages=(items%pagersize==0)?items/pagersize-1:items/pagersize;
            for(int i=0;i<=pages;i++){
            List<String> sortList=getColumnDatafromTable(key);
            sort.addAll(sortList);
            selectWebElement(nextPageIcon);
            waitForJqueryLoad(driver);
            }
            if(sort.equals(colDataFromTable)){status=true;}else{System.out.println("Ascending Sort failed for column "+key);System.out.println(sort+"\n"+colDataFromTable);status=false;break;}
            selectWebElement(refreshBtn);sort.clear();
            waitForJqueryLoad(driver);
//Descending sort starts from here
            selectWebElement(headersDropdown.get(k));
            waitForJqueryLoad(driver);
            selectWebElement(sortDescending.get(k));
            waitForJqueryLoad(driver);
            k++;
            Collections.sort(colDataFromTable,Collections.reverseOrder());
            for(int i=0;i<=pages;i++){
                List<String> sortList=getColumnDatafromTable(key);
                sort.addAll(sortList);
                selectWebElement(nextPageIcon);
                waitForJqueryLoad(driver);
            }
            if(sort.equals(colDataFromTable)){status=true;}else{System.out.println("Descending Sort failed for column "+key);System.out.println(sort+"\n"+colDataFromTable);status=false;break;}
            selectWebElement(refreshBtn);colDataFromTable.clear();sort.clear();
            waitForJqueryLoad(driver);
        }
        return status;
    }

	public boolean isReportListDisplayed() {
		selectWebElement(reportNameDropdown);
		waitForJqueryLoad(driver);
		if(reportNameListbox.size()>0)
			return false;
		else
			return true;
	}
	
	public void booleansearchnew(String Name, String Transaction) throws Exception
	{
		selectWebElement(searchBtn);
		waitForJqueryLoad(driver);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Transaction");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchTextBox,Transaction);
        selectWebElement(plusbutton);
        moveToElement(andradiobtn);
        selectWebElement(andradiobtn);
        Thread.sleep(2000);
        selectWebElement(selectSearchColumn.get(2));
        selectDropdownFromVisibleTextContains(columnNameListtwo,"New Value");
        selectWebElement(selectSearchColumn.get(3));
        selectDropdownFromVisibleText(searchTypeListtwo,"Contains");
        enterValueToTxtField(searchTextBoxtwo,Name);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
	}
	
	public void booleansearchold(String Name, String Transaction) throws Exception
	{
		selectWebElement(searchBtn);
		waitForJqueryLoad(driver);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Transaction");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchTextBox,Transaction);
        selectWebElement(plusbutton);
        moveToElement(andradiobtn);
        selectWebElement(andradiobtn);
        Thread.sleep(2000);
        selectWebElement(selectSearchColumn.get(2));
        selectDropdownFromVisibleTextContains(columnNameListtwo,"Old Value");
        selectWebElement(selectSearchColumn.get(3));
        selectDropdownFromVisibleText(searchTypeListtwo,"Contains");
        enterValueToTxtField(searchTextBoxtwo,Name);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent);
	}

	public Boolean verifyTmacBroadcastMsgCreate(TmacBroadCastMsgDetails details, String Transaction) throws Exception {
		booleansearchnew(details.getMessage(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> newvalues=new HashMap<>();
		String[]d=firstRowData.get("New Values").split("\n");
		for(String e:d) {
			System.out.println(e);
			String f[]=e.split(":",2);
			if(f.length>1)
			newvalues.put(f[0], f[1]);
		}
			if(newvalues.get("TeamName").equals(details.getTeamName()))
			{
				if(newvalues.get("Message").equals(details.getMessage()))
				{
					if(newvalues.get("Status").equals(details.getStatus()))
						Status= true;
					else {System.out.println("Status data mismatch");}
				}
				else {System.out.println("Message data mismatch");}
			}
			else {System.out.println("TeamName data mismatch");	}
			return Status;
		}

	public Boolean verifyTmacBroadcastMsgUpdate(TmacBroadCastMsgDetails details, String Transaction) throws Exception {
		booleansearchnew(details.getUpdatedMessage(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
        if(firstRowData.containsKey("Old Values")) {
        	Map<String,String> oldvalues=new HashMap<>();
    		String[]d=firstRowData.get("Old Values").split("\n");
    		for(String e:d) {
    			System.out.println(e);
    			String f[]=e.split(":",2);
    			if(f.length>1)
    				oldvalues.put(f[0], f[1]);
    		}
    		if(oldvalues.get("TeamName").equals(details.getTeamName())){
    			if(oldvalues.get("Message").equals(details.getMessage())){
    				if(oldvalues.get("Status").equals(details.getStatus()))
    					if(firstRowData.containsKey("New Values")) {
    		    			Map<String,String> newvalues=new HashMap<>();
    		        		String[]d1=firstRowData.get("New Values").split("\n");
    		        		for(String e:d1) {
    		        			System.out.println(e);
    		        			String f[]=e.split(":",2);
    		        			if(f.length>1)
    		        				newvalues.put(f[0], f[1]);
    		        		}
    		        		if(newvalues.get("TeamName").equals(details.getTeamName())){
    		        			if(newvalues.get("Message").equals(details.getUpdatedMessage())){
    		        				if(newvalues.get("Status").equals(details.getStatus())) {
    		        					if(newvalues.get("Modify Reason").equals(details.getModifyReason())) {
    		        						if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getModifyReason()))
    		        		        			Status=true;
    		        		        		else System.out.println("Change reason data mismatch");
    		        					}
    		        					else System.out.println("Modify reason data mismatch");
    		        				}
    		        				else {System.out.println("Status data mismatch");}
    		        			}
    		        			else {System.out.println("Message data mismatch");}
    		        			}
    		        		else {System.out.println("TeamName data mismatch");	}
    		        		
    		    		}
    		    		else {System.out.println("New values data mismatch");}
    				else {System.out.println("Status data mismatch");}
    			}
    			else {System.out.println("Message data mismatch");}
    			}
    		else {System.out.println("TeamName data mismatch");	}
    		
    	}
        else {System.out.println("Old values data mismatch");}
        return Status;
	}
	
	private Map<String,String> getFirstRowDatafromTable1(){
        Map<String,String> map = new HashMap<>();
        waitUntilWebElementIsVisible(auditGridContent);
        List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
        for(int j=0;j<headers.size();j++){
            scrollToElement(headers.get(j));
            map.put(headers.get(j).getText(),cols.get(j).getText());
        }
        return map;
	}

	public boolean verifywaitTimeColorConfigCreate(WaitTimeColorConfigDetails details,String Transaction) throws Exception {
		booleansearchnew(details.getColorcode(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> newvalues=new HashMap<>();
		String[]d=firstRowData.get("New Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			newvalues.put(f[0], f[1]);
		}
			if(newvalues.get("ColorCode").equals(details.getColorcode()))
			{
				if(newvalues.get("Color").equals(details.getColorcode()))
				{
					if(newvalues.get("StartTime").equals(details.getStartTime()))
					{
						if(newvalues.get("EndTime").equals(details.getEndTime()))
							Status= true;
						else {System.out.println("End Time  data mismatch");}
					}
					else {System.out.println("Start Time data mismatch");}
				}
				else {System.out.println("Color data mismatch");}
			}
			else {System.out.println("Color Code data mismatch");}
			return Status;
	}

	public boolean verifywaitTimeColorConfigUpdate(WaitTimeColorConfigDetails details,String Transaction) throws Exception {
		booleansearchnew(details.getUpdatedColorCode(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
        if(firstRowData.containsKey("Old Values")) {
        	Map<String,String> oldvalues=new HashMap<>();
    		String[]d=firstRowData.get("Old Values").split("\n");
    		for(String e:d) {
    			System.out.println(e);
    			String f[]=e.split(":",2);
    			if(f.length>1)
    				oldvalues.put(f[0], f[1]);
    		}
    		if(oldvalues.get("Color").equals(details.getColorcode())){
    			if(oldvalues.get("ColorCode").equals(details.getColorcode())){
    				if(oldvalues.get("StartTime").equals(details.getStartTime())) {
    					if(oldvalues.get("EndTime").equals(details.getEndTime())){
    						if(firstRowData.containsKey("New Values")) {
    							Map<String,String> newvalues=new HashMap<>();
    							String[]d1=firstRowData.get("New Values").split("\n");
    							for(String e:d1) {
    								String f[]=e.split(":",2);
    								if(f.length>1)
    		        				newvalues.put(f[0], f[1]);
    							}
    							if(newvalues.get("Color").equals(details.getUpdatedColorCode())){
    								if(newvalues.get("ColorCode").equals(details.getUpdatedColorCode())){
    									if(newvalues.get("StartTime").equals(details.getUpdatedStartTime())) {
    										if(newvalues.get("EndTime").equals(details.getEndTime())){
    											if(newvalues.get("Modify Reason").equals(details.getModifyReason())) {
    												if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getModifyReason()))
    													Status=true;
    												else System.out.println("Change reason data mismatch");
    											}
    											else System.out.println("Modify reason data mismatch");
    										}
    										else System.out.println("End Time data mismatch");
    		        				}
    		        				else {System.out.println("Start Time data mismatch");}
    		        			}
    		        			else {System.out.println("Colorcode data mismatch");}
    		        			}
    		        		else {System.out.println("Color data mismatch");	}	
    		    		}
    		    		else {System.out.println("New values data mismatch");}
    					}
    				else {System.out.println("End Time data mismatch");}
    			}
    			else {System.out.println("Start Time data mismatch");}
    			}
    		else {System.out.println("Color Code data mismatch");	}	
    	}
    	else System.out.println("Color data mismatch");
        }
    else {System.out.println("Old values data mismatch");}
    return Status;
	}

	public boolean verifywaitTimeColorConfigdelete(WaitTimeColorConfigDetails details,String Transaction) throws Exception {
		booleansearchold(details.getUpdatedColorCode(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> oldvalues=new HashMap<>();
		String[]d=firstRowData.get("Old Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			oldvalues.put(f[0], f[1]);
		}
			if(oldvalues.get("ColorCode").equals(details.getUpdatedColorCode()))
			{
				if(oldvalues.get("Color").equals(details.getUpdatedColorCode()))
				{
					if(oldvalues.get("StartTime").equals(details.getStartTime()))
					{
						if(oldvalues.get("EndTime").equals(details.getEndTime()))
						{
							if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getDeleteReason()))
    		        			Status=true;
    		        		else System.out.println("Change reason data mismatch");
						}
						else {System.out.println("End Time  data mismatch");}
					}
					else {System.out.println("Start Time data mismatch");}
				}
				else {System.out.println("Color data mismatch");}
			}
			else {System.out.println("Color Code data mismatch");}
			return Status;
	}

	public boolean verifyWorkcodeListCreate(WorkCodeListDetails details, String Transaction, WorkCodeListDetails details1) throws Exception {
		booleansearchnew(details.getName(),Transaction);
		String workcode=database(details1.getQuery()).get(0).remove("WorkCode");
		System.out.println(workcode);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> newvalues=new HashMap<>();
		String[]d=firstRowData.get("New Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			newvalues.put(f[0], f[1]);
		}
			if(newvalues.get("WorkLevel").equals(details.getWorkLevel()))
			{
				if(newvalues.get("Name").equals(details.getName()))
				{
					if(newvalues.get("WorkCode").equals(workcode))
						Status= true;
					else {System.out.println("Workcode data mismatch");}
				}
				else {System.out.println("Name data mismatch");}
			}
			else {System.out.println("WorkLevel data mismatch");	}
			return Status;
	}
	public String workcode;
	public boolean verifyWorkcodeListUpdate(WorkCodeListDetails details,String Transaction,WorkCodeListDetails details1) throws Exception {
		booleansearchnew(details.getUpdatedName(),Transaction);
		workcode=database(details1.getQuery()).get(0).remove("WorkCode");
		System.out.println(workcode);
		String teamid=database(details1.getQuery()).get(0).remove("TeamID");
		System.out.println(teamid);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
        if(firstRowData.containsKey("Old Values")) {
        	Map<String,String> oldvalues=new HashMap<>();
    		String[]d=firstRowData.get("Old Values").split("\n");
    		for(String e:d) {
    			System.out.println(e);
    			String f[]=e.split(":",2);
    			if(f.length>1)
    				oldvalues.put(f[0], f[1]);
    		}
    		if(oldvalues.get("WorkLevel").equals(details.getWorkLevel())){
    			if(oldvalues.get("Name").equals(details.getName())){
    				if(oldvalues.get("WorkCode").equals(workcode)) {
    					if(oldvalues.get("TeamID").equals(teamid)){
    						if(firstRowData.containsKey("New Values")) {
    							Map<String,String> newvalues=new HashMap<>();
    							String[]d1=firstRowData.get("New Values").split("\n");
    							for(String e:d1) {
    								String f[]=e.split(":",2);
    								if(f.length>1)
    		        				newvalues.put(f[0], f[1]);
    							}
    							if(newvalues.get("WorkLevel").equals(details.getWorkLevel())){
    								if(newvalues.get("Name").equals(details.getUpdatedName())){
    									if(newvalues.get("WorkCode").equals(workcode)) {
    										if(newvalues.get("TeamID").equals(teamid)){
    											if(newvalues.get("Modify Reason").equals(details.getModifyReason())) {
    												if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getModifyReason()))
    													Status=true;
    												else System.out.println("Change reason data mismatch");
    											}
    											else System.out.println("Modify reason data mismatch");
    										}
    										else System.out.println("TeamID data mismatch");
    		        				}
    		        				else {System.out.println("Workcode data mismatch");}
    		        			}
    		        			else {System.out.println("Name data mismatch");}
    		        			}
    		        		else {System.out.println("WorkLevel data mismatch");	}	
    		    		}
    		    		else {System.out.println("New values data mismatch");}
    					}
    				else {System.out.println("Team Id data mismatch");}
    			}
    			else {System.out.println("WorkCode data mismatch");}
    			}
    		else {System.out.println("Name data mismatch");	}	
    	}
    	else System.out.println("WorkLevel data mismatch");
        }
    else {System.out.println("Old values data mismatch");}
    return Status;
	}

	public boolean verifycodeListdelete(WorkCodeListDetails details, String Transaction,String workcode) throws Exception {
		booleansearchold(details.getUpdatedName(),Transaction);
		System.out.println(workcode);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> oldvalues=new HashMap<>();
		String[]d=firstRowData.get("Old Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			oldvalues.put(f[0], f[1]);
		}
			if(oldvalues.get("WorkLevel").equals(details.getWorkLevel()))
			{
				if(oldvalues.get("Name").equals(details.getUpdatedName()))
				{
					if(oldvalues.get("WorkCode").equals(workcode))
					{
						if(oldvalues.get("ModifyReason").equals(details.getDeleteReason()))
						{
							if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getDeleteReason()))
    		        			Status=true;
    		        		else System.out.println("Change reason data mismatch");
						}
						else {System.out.println("Modify Reason  data mismatch");}
					}
					else {System.out.println("Workcode data mismatch");}
				}
				else {System.out.println("Name data mismatch");}
			}
			else {System.out.println("WOrkLevel data mismatch");}
			return Status;
	}

	public String RunQuery(String query, String Key) {
		return database(query).get(0).remove(Key);
	}

	public boolean verifyAgentTeamMgmtCreate(AgentTeamMgmtDetails details, String Transaction) throws Exception {
		booleansearchnew(details.getTeamName(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> newvalues=new HashMap<>();
		String[]d=firstRowData.get("New Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			newvalues.put(f[0], f[1]);
		}
			if(newvalues.get("LevelHierarchy").equals(details.getLevel()))
			{
				if(newvalues.get("Name").equals(details.getTeamName()))
				{
					Status= true;
				}
				else {System.out.println("Name data mismatch");}
			}
			else {System.out.println("Level Hierarchy data mismatch");}
			return Status;
	}

	public boolean verifyAgentTeamMgmtUpdate(AgentTeamMgmtDetails details, String Transaction, String displayname,AgentTeamMgmtDetails details2) throws Exception {
		booleansearchnew(details.getUpdateTeamName(),Transaction);
		String displayname2=RunQuery(details2.getQuery(),"DisplayName");
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
        if(firstRowData.containsKey("Old Values")) {
        	Map<String,String> oldvalues=new HashMap<>();
    		String[]d=firstRowData.get("Old Values").split("\n");
    		for(String e:d) {
    			System.out.println(e);
    			String f[]=e.split(":",2);
    			if(f.length>1)
    				oldvalues.put(f[0], f[1]);
    		}
    		if(oldvalues.get("LevelHierarchy").equals(details.getLevel())){
    			if(oldvalues.get("Name").equals(details.getTeamName())){
    				if(oldvalues.get("DisplayHierarchy").equals(displayname)) {
    					if(firstRowData.containsKey("New Values")) {
    						Map<String,String> newvalues=new HashMap<>();
    						String[]d1=firstRowData.get("New Values").split("\n");
    						for(String e:d1) {
    							String f[]=e.split(":",2);
    							if(f.length>1)
    		        			newvalues.put(f[0], f[1]);
    						}
    						if(newvalues.get("LevelHierarchy").equals(details.getLevel())){
    							if(newvalues.get("Name").equals(details.getUpdateTeamName())){
    								if(newvalues.get("DisplayHierarchy").equals(displayname2)) {
    									if(newvalues.get("Modify Reason").equals(details.getModifyReason())) {
    										if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getModifyReason()))
    											Status=true;
    										else System.out.println("Change reason data mismatch");
    									}
    									else System.out.println("Modify reason data mismatch");
    								}
    								else System.out.println("Display Hierarchy data mismatch");
    		        				}
    		        			else {System.out.println("Name data mismatch");}
    		        			}
    		        		else {System.out.println("LevelHeirarchy data mismatch");	}	
    		    		}
    		    		else {System.out.println("New values data mismatch");}
    					}
    				else {System.out.println("Display Hierarchy data mismatch");}
    			}
    			else {System.out.println("Name data mismatch");}
    			}
    		else {System.out.println("Level Hierarchy data mismatch");	}	
        }
        else {System.out.println("Old values data mismatch");}
    return Status;
	}

	public boolean verifyAgentTeamMgmtdelete(AgentTeamMgmtDetails details, String Transaction,String displayname) throws Exception {
		booleansearchold(details.getUpdateTeamName(),Transaction);
		System.out.println(displayname);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> oldvalues=new HashMap<>();
		String[]d=firstRowData.get("Old Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			oldvalues.put(f[0], f[1]);
		}
			if(oldvalues.get("LevelHierarchy").equals(details.getLevel()))
			{
				if(oldvalues.get("Name").equals(details.getUpdateTeamName()))
				{
					if(oldvalues.get("DisplayHierarchy").equals(displayname))
					{
						if(oldvalues.get("ModifyReason").equals(details.getDeleteReason()))
						{
							if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getDeleteReason()))
    		        			Status=true;
    		        		else System.out.println("Change reason data mismatch");
						}
						else {System.out.println("Modify Reason  data mismatch");}
					}
					else {System.out.println("DisplayHierarchy data mismatch");}
				}
				else {System.out.println("Name data mismatch");}
			}
			else {System.out.println("LevelHierarchy data mismatch");}
			return Status;
	}

	public boolean verifyAdhocOptionEnhancementCreate(AdhocOptionEnhancementDetails details, String Transaction) throws Exception {
		booleansearchnew(details.getPromotionalDescription(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> newvalues=new HashMap<>();
		String[]d=firstRowData.get("New Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			newvalues.put(f[0], f[1]);
		}
			if(newvalues.get("PromotionNumber").equals(details.getPromotionalNumber()))
			{
				if(newvalues.get("PromotionDescription").equals(details.getPromotionalDescription()))
				{
					if(newvalues.get("Language").equals(details.getLanguage()))
					{
						if(newvalues.get("PromotionNameWavFile").equals(details.getPromotionNameWavFile()))
						{
							if(newvalues.get("PromotionDetailsWavFile").equals(details.getPromotionDetailsWavFile()))
							{
								if(newvalues.get("DirectTransferEnabled").equals(details.getDirectTransferEnabled()))
								{
									if(newvalues.get("Intent").equals(details.getIntent()))
									{
										if(newvalues.get("Status").equals(details.getStatus()))
										{
											Status= true;
										}
										else {System.out.println("Status data mismatch");}
									}
									else {System.out.println("Intent data mismatch");}
								}
								else {System.out.println("Direct TransferEnabled data mismatch");}
							}
							else {System.out.println("PromotionDetailsWavFile data mismatch");}
						}	
						else {System.out.println("romotionNameWavFile data mismatch");}
					}
					else {System.out.println("Language data mismatch");}
				}
				else {System.out.println("PromotionDescription data mismatch");}
			}
			else {System.out.println("PromotionNumber data mismatch");}
			return Status;
	}

	public boolean verifyAdhocOptionEnhancementUpdate(AdhocOptionEnhancementDetails details,String Transaction) throws Exception {
		booleansearchnew(details.getUpdatedPromotiondescription(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
        if(firstRowData.containsKey("Old Values")) {
        	Map<String,String> oldvalues=new HashMap<>();
    		String[]d=firstRowData.get("Old Values").split("\n");
    		for(String e:d) {
    			System.out.println(e);
    			String f[]=e.split(":",2);
    			if(f.length>1)
    				oldvalues.put(f[0], f[1]);
    		}
    		if(oldvalues.get("PromotionNumber").equals(details.getPromotionalNumber())){
    			if(oldvalues.get("PromotionDescription").equals(details.getPromotionalDescription())){
    				if(oldvalues.get("Language").equals(details.getLanguage())) {
    					if(oldvalues.get("DirectTransferEnabled").equals(details.getDirectTransferEnabled())){
    						if(oldvalues.get("PromotionNameWavFile").equals(details.getPromotionNameWavFile())){
    							if(oldvalues.get("PromotionDetailsWavFile").equals(details.getPromotionDetailsWavFile())){
    								if(oldvalues.get("Intent").equals(details.getIntent())){
    									if(oldvalues.get("Status").equals(details.getStatus())){
    										if(firstRowData.containsKey("New Values")) {
    											Map<String,String> newvalues=new HashMap<>();
    											String[]d1=firstRowData.get("New Values").split("\n");
    											for(String e:d1) {
    												String f[]=e.split(":",2);
    												if(f.length>1)
    													newvalues.put(f[0], f[1]);
    											}
    											if(newvalues.get("PromotionNumber").equals(details.getPromotionalNumber())){
    												if(newvalues.get("PromotionDescription").equals(details.getUpdatedPromotiondescription())){
    													if(newvalues.get("Language").equals(details.getLanguage())) {
    														if(newvalues.get("DirectTransferEnabled").equals(details.getDirectTransferEnabled())){
    																if(newvalues.get("PromotionNameWavFile").equals(details.getPromotionNameWavFile())){
    																	if(newvalues.get("PromotionDetailsWavFile").equals(details.getPromotionDetailsWavFile())){
    																		if(newvalues.get("Intent").equals(details.getIntent())){
    																			if(newvalues.get("Status").equals(details.getStatus())) {
    																				if(newvalues.get("ModifyReason").equals(details.getModifyReason())) {
    																					if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getModifyReason()))
    																					Status=true;
    																					else System.out.println("Change reason data mismatch");
    																				}
    																				else System.out.println("ModifyReason data mismatch");
    																			}
    																			else System.out.println("Status data mismtach");
    																		}
    																		else System.out.println("Intent data mismatch");
    																	}
    																	else System.out.println("Promotiondetailswavfile data mismatch");
    																}
    																else System.out.println("PromotionNamewavfile data mismatch");
    															}
    															else System.out.println("DirectTransferEnabled Data mismatch");
    														}
    														else System.out.println("Language data mismatch");
    													}
    													else System.out.println("Promotion description data mismatch");
    												}
    												else System.out.println("Promotion Number data mismatch");	
    											}
    											else System.out.println("Newvalues data mismatch");
    										}
    										else System.out.println("Status data mismatch");
    									}
    									else {System.out.println("Intent data mismatch");}
    								}
    								else {System.out.println("Promotiondetailswavfile data mismatch");}
    		        			}
    							else {System.out.println("PromotionNamewavfile data mismatch");	}	
    						}
    						else {System.out.println("DirectTransferEnabled data mismatch");}
    					}
    					else {System.out.println("Langvage data mismatch");}
    				}
    				else {System.out.println("Promotiondescription data mismatch");}
    			}
    			else {System.out.println("Promotion Number data mismatch");	}	
    		}
    		else {System.out.println("Old values data mismatch");}
    		return Status;
	}
	
	public boolean verifyAdhocOptionEnhancementdelete(AdhocOptionEnhancementDetails details,String Transaction) throws Exception {
		booleansearchold(details.getPromotionalDescription(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> oldvalues=new HashMap<>();
		String[]d=firstRowData.get("Old Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			oldvalues.put(f[0], f[1]);
		}
			if(oldvalues.get("PromotionNumber").equals(details.getPromotionalNumber()))
			{
				if(oldvalues.get("PromotionDescription").equals(details.getPromotionalDescription()))
				{
					if(oldvalues.get("Language").equals(details.getLanguage()))
					{
						if(oldvalues.get("DirectTransferEnabled").equals(details.getDirectTransferEnabled()))
						{
							if(oldvalues.get("PromotionNameWavFile").equals(details.getPromotionNameWavFile())) {
								if(oldvalues.get("PromotionDetailsWavFile").equals(details.getPromotionDetailsWavFile())) {
									if(oldvalues.get("Intent").equals(details.getIntent())) {
										if(oldvalues.get("Status").equals(details.getStatus())){
											if(oldvalues.get("ModifyReason").equals(details.getDeleteReason())) {
												if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getDeleteReason()))
					    		        			Status=true;
					    		        		else System.out.println("Change reason data mismatch");
											}
											else System.out.println("Modify Reason data mismatch");
										}
										else System.out.println("Status data mismatch");
									}
									else System.out.println("Intent data mismtach");
								}
								else System.out.println("PomotionDetails data mismatch");
							}
							else System.out.println("PromotionNameWavFile Data mismatch");
						}
						else {System.out.println("DirectTansferEnabled data mismatch");}
					}
					else {System.out.println("Language data mismatch");}
				}
				else {System.out.println("Promotion Description data mismatch");}
			}
			else {System.out.println("Promotion Number data mismatch");}
			return Status;
	}

	public boolean verifyFaxLineConfigCreate(FaxLineConfigDetails details, String Transaction) throws Exception {
		booleansearchnew(details.getFaxLineName(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> newvalues=new HashMap<>();
		String[]d=firstRowData.get("New Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			newvalues.put(f[0], f[1]);
		}
			if(newvalues.get("DNIS").equals(details.getFaxLine()))
			{
				if(newvalues.get("FaxLineName").equals(details.getFaxLineName()))
				{
					if(newvalues.get("Description").equals(details.getDescription()))
					{
						if(newvalues.get("Enabled").equals(details.getStatus()))
						{
							if(newvalues.get("SendEnabled").equals(details.getSendStatus()))
							{
								if(newvalues.get("RecieveEnabled").equals(details.getReceiveStatus()))
								{
									Status= true;
								}
								else {System.out.println("Recieve Enabled data mismatch");}
							}
							else {System.out.println("Send Enabled data mismatch");}
						}
						else {System.out.println("Enabled data mismatch");}
					}
					else {System.out.println("Description data mismatch");}
				}
				else {System.out.println("FaxLineName data mismatch");}
			}
			else {System.out.println("DNIS data mismatch");	}
			return Status;
	}

	public boolean verifyFaxLineConfigUpdate(FaxLineConfigDetails details, String Transaction) throws Exception {
		booleansearchnew(details.getUpdatedDescription(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
        if(firstRowData.containsKey("Old Values")) {
        	Map<String,String> oldvalues=new HashMap<>();
    		String[]d=firstRowData.get("Old Values").split("\n");
    		for(String e:d) {
    			System.out.println(e);
    			String f[]=e.split(":",2);
    			if(f.length>1)
    				oldvalues.put(f[0], f[1]);
    		}
    		if(oldvalues.get("DNIS").equals(details.getFaxLine())){
    			if(oldvalues.get("Description").equals(details.getDescription())){
    				if(oldvalues.get("FaxLineName").equals(details.getFaxLineName())) {
    					if(oldvalues.get("Enabled").equals(details.getStatus())){
        					if(oldvalues.get("SendEnabled").equals(details.getSendStatus())){
            					if(oldvalues.get("ReceiveEnabled").equals(details.getReceiveStatus())){
            						if(firstRowData.containsKey("New Values")) {
            							Map<String,String> newvalues=new HashMap<>();
            							String[]d1=firstRowData.get("New Values").split("\n");
            							for(String e:d1) {
            								String f[]=e.split(":",2);
            								if(f.length>1)
            									newvalues.put(f[0], f[1]);
            							}
            							if(newvalues.get("DNIS").equals(details.getFaxLine())){
            								if(newvalues.get("FaxLineName").equals(details.getFaxLineName())){
            									if(newvalues.get("Description").equals(details.getUpdatedDescription())) {
            										if(newvalues.get("Enabled").equals(details.getUpdatedStatus())){
                										if(newvalues.get("SendEnabled").equals(details.getUpdatedSendStatus())){
                    										if(newvalues.get("ReceiveEnabled").equals(details.getUpdatedReceiveStatus())){
                    											if(newvalues.get("Modify Reason").equals(details.getModifyReason())) {
                    												if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getModifyReason()))
                    													Status=true;
                    												else System.out.println("Change reason data mismatch");
                    											}
                    											else System.out.println("Modify reason data mismatch");
                    										}
                    										else System.out.println("ReceiveEnabled data mismatch");
                										}
                										else {System.out.println("SendEnabled data mismatch");}
            										}
            										else System.out.println("Enabled data mismatch");
            									}
        										else System.out.println("Description data mismatch");
            								}
            								else {System.out.println("FaxLineName data mismatch");}
            							}
            							else {System.out.println("DNIS data mismatch");	}	
            						}
            						else {System.out.println("New values data mismatch");}
            					}
            					else {System.out.println("RecieveEnabled data mismatch");}
        					}
        					else {System.out.println("SendEnabled data mismatch");}
    					}
    					else {System.out.println("Enabled data mismatch");	}	
    				}
    				else System.out.println("FaxLineNamedata mismatch");
    			}
    			else System.out.println("Description data mismatch");
    		}
    		else System.out.println("DNIS Data Mismatch");
        }
        else {System.out.println("Old values data mismatch");}
    return Status;
	}

	public boolean verifyFaxLineConfigdelete(FaxLineConfigDetails details, String Transaction) throws Exception {
		booleansearchold(details.getFaxLine(),Transaction);
		System.out.println(workcode);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> oldvalues=new HashMap<>();
		String[]d=firstRowData.get("Old Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			oldvalues.put(f[0], f[1]);
		}
			if(oldvalues.get("DNIS").equals(details.getFaxLine()))
			{
				if(oldvalues.get("FaxLineName").equals(details.getFaxLineName()))
				{
					if(oldvalues.get("Description").equals(details.getUpdatedDescription()))
					{
						if(oldvalues.get("Enabled").equals(details.getStatus())){
							if(oldvalues.get("Send Enabled").equals(details.getSendStatus())) {
								if(oldvalues.get("Receive Enabled").equals(details.getReceiveStatus())) {
									if(oldvalues.get("ModifyReason").equals(details.getDeleteReason()))
									{
										if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getDeleteReason()))
											Status=true;
										else System.out.println("Change reason data mismatch");
									}
									else {System.out.println("Modify Reason  data mismatch");}
								}
								else {System.out.println("Receive Enabled data mismatch");}
							}
							else {System.out.println("Send Enabled data mismatch");}
						}
						else {System.out.println("Enabled data mismatch");}
					}
					else {System.out.println("Description data mismatch");}
				}
				else {System.out.println("FaxLine Name data mismatch");}
			}
			else {System.out.println("DNIS data mismatch");}
			return Status;
	}

	public boolean verifyFaxSendersCreate(FaxSendersDetails details, String Transaction) throws Exception {
		booleansearchnew(details.getFaxNumber(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> newvalues=new HashMap<>();
		String[]d=firstRowData.get("New Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			newvalues.put(f[0], f[1]);
		}
			if(newvalues.get("DNIS").equals(details.getFaxLine()))
			{
				if(newvalues.get("Name").equals(details.getName()))
				{
					if(newvalues.get("FaxNumber").equals(details.getFaxNumber()))
					{
						if(newvalues.get("Type").equals(details.getSenderType()))
						{
							Status= true;
						}
						else {System.out.println("Type data mismatch");}
					}
					else {System.out.println("FaxNumber data mismatch");}
				}
				else {System.out.println("Name data mismatch");}
			}
			else {System.out.println("DNIS data mismatch");	}
			return Status;
	}

	public boolean verifySendersUpdate(FaxSendersDetails details, String Transaction) throws Exception {
		booleansearchnew(details.getUpdatedSenderType(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
        if(firstRowData.containsKey("Old Values")) {
        	Map<String,String> oldvalues=new HashMap<>();
    		String[]d=firstRowData.get("Old Values").split("\n");
    		for(String e:d) {
    			System.out.println(e);
    			String f[]=e.split(":",2);
    			if(f.length>1)
    				oldvalues.put(f[0], f[1]);
    		}
    		if(oldvalues.get("DNIS").equals(details.getFaxLine())){
    			if(oldvalues.get("Name").equals(details.getName())) {
        			if(oldvalues.get("FaxNumber").equals(details.getFaxNumber())){
            			if(oldvalues.get("Type").equals(details.getSenderType())){
            				if(firstRowData.containsKey("New Values")) {
            					Map<String,String> newvalues=new HashMap<>();
            					String[]d1=firstRowData.get("New Values").split("\n");
            					for(String e:d1) {
            						String f[]=e.split(":",2);
            						if(f.length>1)
            						newvalues.put(f[0], f[1]);
            					}
            					if(newvalues.get("DNIS").equals(details.getFaxLine())){
            						if(newvalues.get("Name").equals(details.getUpdatedName())){
            							if(newvalues.get("FaxNumber").equals(details.getFaxNumber())){
                							if(newvalues.get("Type").equals(details.getUpdatedSenderType())){
                								if(newvalues.get("Modify Reason").equals(details.getModifyReason())) {
                    								if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getModifyReason()))
                    									Status=true;
                    								else System.out.println("Change reason data mismatch");
                    							}
                    							else System.out.println("Modify reason data mismatch");
                    						}
                    						else System.out.println("Type data mismatch");
                						}
            							else {System.out.println("FaxNumber data mismatch");}
            						}
            						else System.out.println("Name data mismatch");
            					}
            					else {System.out.println("DNIS data mismatch");	}	
            				}
            				else {System.out.println("New values data mismatch");}
            			}
            			else {System.out.println("Type data mismatch");}
        			}
        			else {System.out.println("FaxNumber data mismatch");}
    			}
    			else System.out.println("Name data mismatch");
    		}
    		else System.out.println("DNIS Data Mismatch");
        }
        else {System.out.println("Old values data mismatch");}
    return Status;
	}

	public boolean verifyFaxSendersdelete(FaxSendersDetails details, String Transaction) throws Exception {
		booleansearchold(details.getFaxNumber(),Transaction);	
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> oldvalues=new HashMap<>();
		String[]d=firstRowData.get("Old Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			oldvalues.put(f[0], f[1]);
		}
			if(oldvalues.get("DNIS").equals(details.getFaxLine()))
			{
				if(oldvalues.get("Name").equals(details.getName()))
				{
					if(oldvalues.get("FaxNumber").equals(details.getFaxNumber()))
					{
						if(oldvalues.get("Type").equals(details.getSenderType())) {
							if(oldvalues.get("ModifyReason").equals(details.getDeleteReason())) {
								if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getDeleteReason()))
									Status=true;
								else System.out.println("Change reason data mismatch");
							}
							else {System.out.println("Modify Reason  data mismatch");}
						}
						else {System.out.println("Type data mismatch");}
					}
					else {System.out.println("FaxNumber data mismatch");}
				}
				else {System.out.println("Name data mismatch");}
			}
			else {System.out.println("DNIS data mismatch");}
			return Status;
	}

	public boolean verifyFaxRoutingConfigCreate(FaxRoutingConfigurationDetails details, String Transaction) throws Exception {
		booleansearchnew(details.getIntent(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> newvalues=new HashMap<>();
		String[]d=firstRowData.get("New Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			newvalues.put(f[0], f[1]);
		}
			if(newvalues.get("DNIS").equals(details.getFaxLine()))
			{
				if(newvalues.get("Intent").equals(details.getIntent()))
				{
					if(newvalues.get("RouteType").equals(details.getRouteType()))
					{
						if(newvalues.get("Type").equals(details.getSenderType()))
						{
							if(newvalues.get("RouteData").equals(details.getRouteData()))
							Status= true;
							else {System.out.println("RouteData data mismatch");}
						}
						else {System.out.println("Type data mismatch");}
					}
					else {System.out.println("RouteType data mismatch");}
				}
				else {System.out.println("Intent data mismatch");	}
			}
			else {System.out.println("DNIS data mismatch");	}
			return Status;
	}

	public boolean verifyFaxRoutingConfigUpdate(FaxRoutingConfigurationDetails details, String Transaction) throws Exception {
		booleansearchnew(details.getUpdatedRouteData(),Transaction);
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
        if(firstRowData.containsKey("Old Values")) {
        	Map<String,String> oldvalues=new HashMap<>();
    		String[]d=firstRowData.get("Old Values").split("\n");
    		for(String e:d) {
    			System.out.println(e);
    			String f[]=e.split(":",2);
    			if(f.length>1)
    				oldvalues.put(f[0], f[1]);
    		}
    		if(oldvalues.get("DNIS").equals(details.getFaxLine())){
    			if(oldvalues.get("Type").equals(details.getSenderType())){
    				if(oldvalues.get("Intent").equals(details.getIntent())) {
    					if(oldvalues.get("RouteType").equals(details.getRouteType())){
        					if(oldvalues.get("RouteData").equals(details.getRouteData())){
            					if(firstRowData.containsKey("New Values")) {
            						Map<String,String> newvalues=new HashMap<>();
            						String[]d1=firstRowData.get("New Values").split("\n");
            						for(String e:d1) {
            							String f[]=e.split(":",2);
            							if(f.length>1)
            								newvalues.put(f[0], f[1]);
            						}
            						if(newvalues.get("DNIS").equals(details.getFaxLine())){
            							if(newvalues.get("Type").equals(details.getSenderType())){
            								if(newvalues.get("Intent").equals(details.getIntent())) {
            									if(newvalues.get("RouteType").equals(details.getRouteType())){
                									if(newvalues.get("RouteData").equals(details.getRouteData())){
                    									if(newvalues.get("Modify Reason").equals(details.getModifyReason())) {
                    										if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getModifyReason()))
                    											Status=true;
                    										else System.out.println("Change reason data mismatch");
                    									}
                    									else System.out.println("Modify reason data mismatch");
                    								}
                									else System.out.println("Routedata data mismatch");
                								}
                								else {System.out.println("Routetype data mismatch");}
            								}
            								else System.out.println("Intent data mismatch");
            							}
        								else System.out.println("Type data mismatch");
            						}
            						else {System.out.println("DNIS data mismatch");}
            					}
            					else {System.out.println("New values data mismatch");}
        					}
        					else {System.out.println("RouteData data mismatch");}
    					}
    					else {System.out.println("RouteType data mismatch");	}	
    				}
    				else System.out.println("Intent mismatch");
    			}
    			else System.out.println("Type data mismatch");
    		}
    		else System.out.println("DNIS Data Mismatch");
        }
        else {System.out.println("Old values data mismatch");}
    return Status;
	}

	public boolean verifyFaxRoutingConfigdelete(FaxRoutingConfigurationDetails details, String Transaction) throws Exception {
		booleansearchold(details.getRouteData(),Transaction);	
		Boolean Status=false;
        Map<String,String> firstRowData=getFirstRowDatafromTable1();
		Map<String,String> oldvalues=new HashMap<>();
		String[]d=firstRowData.get("Old Values").split("\n");
		for(String e:d) {
			String f[]=e.split(":",2);
			if(f.length>1)
			oldvalues.put(f[0], f[1]);
		}
			if(oldvalues.get("DNIS").equals(details.getFaxLine()))
			{
				if(oldvalues.get("Type").equals(details.getSenderType()))
				{
					if(oldvalues.get("Intent").equals(details.getIntent()))
					{
						if(oldvalues.get("RouteType").equals(details.getRouteType())) {
							if(oldvalues.get("ModifyReason").equals(details.getRouteData())) {
								if(oldvalues.get("ModifyReason").equals(details.getDeleteReason())) {
									if(firstRowData.get("Change Reason").equalsIgnoreCase(details.getDeleteReason()))
										Status=true;
									else System.out.println("Change reason data mismatch");
								}
								else {System.out.println("Modify Reason  data mismatch");}
							}
							else {System.out.println("RouteData data mismatch");}
						}
						else {System.out.println("RouteType data mismatch");}
					}
					else {System.out.println("Intent data mismatch");}
				}
				else {System.out.println("Type data mismatch");}
			}
			else {System.out.println("DNIS data mismatch");}
			return Status;
	}

	public boolean verifySendFaxCreate(SendFaxDetails sendFaxDetails, String string) {
		// TODO Auto-generated method stub
		return false;
	}
}