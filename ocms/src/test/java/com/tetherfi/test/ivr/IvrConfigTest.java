package com.tetherfi.test.ivr;

import com.tetherfi.model.ivr.IvrConfigDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrConfigPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class IvrConfigTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToIvrConfigPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        ivrPage.navigateToIvrConfigPage();
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.isIvrConfigPageDisplayed(), "Ivr config page assertion failed");
    	screenshot.captureScreen(driver,"IvrConfigPage","IvrConfigTest");
    }
    
    /*@Test(priority=1)
    public void IvrConfigPage() {
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
    	Assert.assertTrue(ivrConfigPage.verifylogo(),"CallBackAnnouncement logo assertion failed");
    	Assert.assertTrue(ivrConfigPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","IvrConfigTest");
    	Assert.assertTrue(ivrConfigPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","IvrConfigTest");
    }
    
    @Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertFalse(ivrConfigPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void AddNewIvrConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.addNewIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
    	screenshot.captureScreen(driver,"Record Created Successfully","IvrConfigTest");
    }
    
    @Test(priority=6,dependsOnMethods = "AddNewIvrConfigRecord")
    public void AddDuplicateIvrConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.addNewIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nDuplicate Parameter", "Duplicate record assertion failed");
    	screenshot.captureScreen(driver,"Duplicate Record","IvrConfigTest");
    }
    
    @Test(priority=7,dependsOnMethods ="AddNewIvrConfigRecord")
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigCreate(ivrConfigDetails,"Create"));
        screenshot.captureScreen(driver,"VerifyAuditTrialReportForCreate","IvrConfigTest");    
    }
    @Test(priority=8,dependsOnMethods = "AddDuplicateIvrConfigRecord")
    public void AddEmptyConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.addNewIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Parameter, Value", "Add invalid record assertion failed");
        screenshot.captureScreen(driver,"AddEmptyConfigRecord","IvrConfigTest");    
    }
    
    @Test(priority=9,dependsOnMethods = "AddEmptyConfigRecord")
    public void AddInvalidParameterConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(1);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.addNewIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Parameter", "Add invalid record assertion failed");
        screenshot.captureScreen(driver,"AddInvalidParameterConfigRecord","IvrConfigTest");    
    }
    
    @Test(priority=10,dependsOnMethods = "AddInvalidParameterConfigRecord")
    public void AddInvalidValueConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(2);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.addNewIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Value", "Add invalid record assertion failed");
        screenshot.captureScreen(driver,"AddInvalidValueConfigRecord","IvrConfigTest");    
    }
    
    @Test(priority=11,dependsOnMethods = "AddInvalidValueConfigRecord")
    public void VerifyCancelBtnAtAddConfigRecord(){
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.clickOnAddRecord();
        ivrConfigPage.clickOnCancelBtn();
        Assert.assertFalse(ivrConfigPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
        screenshot.captureScreen(driver,"VerifyCancelBtnAtAddConfigRecord","IvrConfigTest");    
	}
	
    @Test(priority=12,dependsOnMethods = "AddInvalidValueConfigRecord")
    public void EditIvrConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        ivrConfigPage.editIvrConfigRecord(IvrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
        screenshot.captureScreen(driver,"EditIvrConfigRecord","IvrConfigTest");    
    }
    
    @Test(priority=13,dependsOnMethods="EditIvrConfigRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigUpdate(IvrConfigDetails,"Update"));
        screenshot.captureScreen(driver, "VerifyAuditTrialReportForUpdate","IvrConfigTest");
    }
    @Test(priority=14)//,dependsOnMethods = "EditIvrConfigRecord")
    public void EditEmptyConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.editIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Parameter, Value", "edit empty record assertion failed");
        screenshot.captureScreen(driver,"EditEmptyConfigRecord","IvrConfigTest");    
    }
    
    @Test(priority=15,dependsOnMethods = "EditEmptyConfigRecord")
    public void EditInvalidParameterConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(1);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.editIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Parameter", "Add invalid record assertion failed");
        screenshot.captureScreen(driver,"EditInvalidParameterConfigRecord","IvrConfigTest");    
    }
    
    @Test(priority=16,dependsOnMethods = "EditInvalidParameterConfigRecord")
    public void EditInvalidValueConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(2);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.editIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Value", "Add invalid record assertion failed");
        screenshot.captureScreen(driver,"EditInvalidValueConfigRecord","IvrConfigTest");    
    }
    
    @Test(priority=17,dependsOnMethods = "EditInvalidValueConfigRecord")
    public void EditWithoutModifyReasonConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(3);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.editIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease enter the modify reason", "empty modify reason record assertion failed");
        screenshot.captureScreen(driver,"EditWithoutModifyReasonConfigRecord","IvrConfigTest");    
    }
    
    @Test(priority=18,dependsOnMethods = "EditWithoutModifyReasonConfigRecord")
    public void VerifyCancelBtnAtEditConfigRecord(){
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.searchIvrConfigRecord("Parameter","Mobile");
        ivrConfigPage.clickOnEditButton();
        ivrConfigPage.clickOnCancelBtn();
        Assert.assertFalse(ivrConfigPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
        screenshot.captureScreen(driver,"EditWithoutModifyReasonConfigRecord","IvrConfigTest");    
    }
    
    @Test(priority=19)
    public void searchPage() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertFalse(ivrConfigPage .clearAll(ivrConfigDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","IvrConfigTest");
        Assert.assertTrue(ivrConfigPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","IvrConfigTest");
    }
    
    @Test(priority=20)
    public void searchwithoutSearchTextbox() throws IOException {
    	IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
    	ivrConfigPage.searchwithoutextsearch();
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease enter the text to search or remove the filter", "Add invalid record assertion failed");
    	screenshot.captureScreen(driver, "searchwithoutSearchTextbox()","IvrConfigTest");
    }
    
    @Test(priority=21)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","IvrConfigTest");
    }
    
    @Test(priority=22)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IVR Config.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
    	Assert.assertTrue(ivrConfigPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","IvrConfigTest");
    } 
    
    @Test(priority=23)//,dependsOnMethods = "ExportToExcelData")
    public void DeleteWithoutDeleteReasonIvrConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        ivrConfigPage.deleteIvrConfigRecord(IvrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=24)//,dependsOnMethods = {"DeleteWithoutDeleteReasonIvrConfigRecord"})
    public void VerifyCancelBtnAtDeleteConfigRecord(){
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.searchIvrConfigRecord("Parameter","Mobile");
        ivrConfigPage.clickOnDeleteButton();
        ivrConfigPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(ivrConfigPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(priority=25,dependsOnMethods = "VerifyCancelBtnAtDeleteConfigRecord")
    public void DeleteIvrConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        ivrConfigPage.deleteIvrConfigRecord(IvrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    
    @Test(priority=26,dependsOnMethods= {"DeleteIvrConfigRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIvrConfigdelete(IvrConfigDetails,"Delete"));
        screenshot.captureScreen(driver,"VerifyAuditTrialReportForUpdate","IvrConfigTest");
    }
    
    @Test(priority=27)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
    	Assert.assertTrue(ivrConfigPage.verifyinvalidsearchwithwrongdata(IvrConfigDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver,"Invalid Search with wrong data", "CallbackAnnouncementTest");
        Assert.assertTrue(ivrConfigPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen( driver,"Clear Search", "FaxApplicationFormTest");
    }
    
    @Test(priority=28)
    public void ExporttoExcelWithoutData() throws Exception
    {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.ExporttoExcelWithoutData(IvrConfigDetails));
        screenshot.captureScreen(driver,"ExporttoExcelWithoutData", "IvrConfigTest");
    }
  
    @Test(priority=29)
    public void SortingByAscending() throws IOException {
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        ivrConfigPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IVR Config (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(ivrConfigPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=30)
    public void SortingByDescending() throws IOException {
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        ivrConfigPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IVR Config (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(ivrConfigPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=31)
    public void GroupBy()
    {
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
    	Assert.assertTrue(ivrConfigPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","IvrConfigTest");
    	Assert.assertTrue(ivrConfigPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","IvrConfigTest");
    }
    
    @Test(priority=32)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
    	Assert.assertTrue(ivrConfigPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=33)
    public void VerifyArrowMoveForFirstAndLastPage() {
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=34)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=35)
    public void VerifyNumberOfItemsPerPageSelection() {
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    */
    
    @Test(priority=36)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
    	Assert.assertTrue(ivrConfigPage.verifyDatabase(IvrConfigDetails.getQuery()));
    }
   
    @AfterMethod
    public void afterEachMethod(ITestResult result, Method method) throws InterruptedException {
      	 if(ITestResult.FAILURE==result.getStatus()){
         		 try{
         			 screenshot.captureScreen(driver,method.getName(),"IvrConfigTest");
         		 }
         		catch (Exception e){
         		 System.out.println("Exception while taking screenshot "+e.getMessage());
         		 } 
         		 driver.navigate().refresh();
      	 }
    }

}
