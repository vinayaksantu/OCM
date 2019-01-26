package com.tetherfi.pages;

import com.tetherfi.model.report.ReportDetails;
import org.apache.commons.lang3.StringUtils;
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

    @FindBy(css="#advanced .k-select")
    private List<WebElement> selectSearchColumn;

    @FindBy(css="ul[id='1001ColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css="ul[id='1001Criteria_listbox'] li")
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
}