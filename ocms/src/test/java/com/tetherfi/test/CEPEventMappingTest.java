package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.user.CepEventMappingDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.CepEventMappingPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class CEPEventMappingTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToCepEventMappingPage() {
    	 HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
         homePage.navigateToOCMPage();
         OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
         Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
         ocmHomePage.navigateToCepEventMappingPage();
         CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
         Assert.assertTrue(CepEventMappingPage.isCepEventMappingPageDisplayed(),"Cep Event Mapping assertion failed");
    }
    
    /*@Test(priority=1)
    public void CepEventMappingPage() {
        CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.VerifyLogo(),"Cep Event Mapping logo assertion failed");
    	Assert.assertTrue(CepEventMappingPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("CepEventMappingTest","maximize window");
    	Assert.assertTrue(CepEventMappingPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("CepEventMappingTest","minimize window");
    }
    
  	@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
        Assert.assertTrue(CepEventMappingPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
        Assert.assertTrue(CepEventMappingPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
        Assert.assertFalse(CepEventMappingPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }*/
    
    @Test(priority=5)
    public void AddCepEventMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
        CepEventMappingPage.addNewCepEventMappingRecord(CepEventMappingDetails);
        Assert.assertEquals(CepEventMappingPage.getSuccessMessage(),"Record Created Successfully","Add New record assertion failed");
    }
    
    /*@Test(priority=6)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyCepEventMappingCreate(CepEventMappingDetails,"Create"));
    }
    
    @Test(priority=7)
    public void DuplicateRecord() throws Exception {
    	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
         CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
     	 CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
         CepEventMappingPage.addNewCepEventMappingRecord(CepEventMappingDetails);
         Assert.assertEquals(CepEventMappingPage.getMessage(),"Duplicate CEP Event","Duplicate record assertion failed");
    }
    
    @Test(priority=8)
    public void VerifyCancelBtnAtAddRecord(){
    	 CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	 CepEventMappingPage.clickOnAddRecord();
    	 CepEventMappingPage.clickOnCancelBtn();
    	 Assert.assertFalse(CepEventMappingPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
    
    @Test(priority=9)
    public void AddRecordWithoutCEPEvent() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
        CepEventMappingPage.addNewRecordWithoutCEPEvent(CepEventMappingDetails);
        Assert.assertEquals(CepEventMappingPage.getMessage(),"Please Provide CEP Event","Invalid record assertion failed");
    }
    
    @Test(priority=10)
    public void AddRecordWithoutDescription() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
        CepEventMappingPage.addNewRecordWithoutDescription(CepEventMappingDetails);
        Assert.assertEquals(CepEventMappingPage.getMessage(),"Please Provide Description","Invalid record assertion failed");
    }
    
    @Test(priority=11)
    public void AddRecordWithoutBins() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
        CepEventMappingPage.addNewRecordWithoutBins(CepEventMappingDetails);
        Assert.assertEquals(CepEventMappingPage.getMessage(),"Please Provide BINS","Invalid record assertion failed");
    }
    
    @Test(priority=12)
    public void AddRecordWithoutTransferFlag() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
        CepEventMappingPage.addNewRecordWithoutTransferFlag(CepEventMappingDetails);
        Assert.assertEquals(CepEventMappingPage.getMessage(),"Please Provide Transfer flag","Invalid record assertion failed");
    }
    
    @Test(priority=13)
    public void AddRecordWithoutIntent() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
        CepEventMappingPage.addNewRecordWithoutIntent(CepEventMappingDetails);
        Assert.assertEquals(CepEventMappingPage.getMessage(),"Please provide a valid Intent","Invalid record assertion failed");
    }*/
    
    @Test(priority=14)
    public void EditCepEventMappingRecord() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.editCepEventMappingRecord(CepEventMappingDetails);
        Assert.assertEquals(CepEventMappingPage.getSuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    
   	/*@Test(priority=15)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyCepEventMappingUpdate(CepEventMappingDetails,"Update"));
    }
  
   	@Test(priority=16)
    public void EditWithoutModifyReasonRecord() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.editCepEventMappingRecordWithoutModifyReason(CepEventMappingDetails);
        Assert.assertEquals(CepEventMappingPage.getMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=17)
    public void VerifyCancelBtnAtEditRecord() throws Exception{
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.searchCepEventMapping("1919");
    	Thread.sleep(3000);
    	CepEventMappingPage.clickOnEditButton();
    	CepEventMappingPage.clickOnCancelBtn();
        Assert.assertFalse(CepEventMappingPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=18)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.verifySearchIsNotEqualTo(CepEventMappingDetails.getDescription()));
    }
    @Test(priority=19)
    public void  VerifySearchContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.verifySearchContains(CepEventMappingDetails.getDescription()));
    }
    
    @Test(priority=20)
    public void  VerifySearchDoesNotContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.verifySearchDoesNotContains(CepEventMappingDetails.getDescription()));
    }
    
    @Test(priority=21)
    public void  VerifySearchStartsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.verifySearchStartsWith(CepEventMappingDetails.getDescription()));
    }
    @Test(priority=22)
    public void  VerifySearchEndsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
    	CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.verifySearchEndsWith(CepEventMappingDetails.getDescription()));
    }
    
    @Test(priority=23)
    public void searchPage() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertFalse(CepEventMappingPage.clearAll(CepEventMappingDetails),"ClearAll Assertion Failed");
    	screenshot.captureScreen(driver, "clearall","CepEventMappingTest");
    	Assert.assertTrue(CepEventMappingPage.verifyclose());
    }
       
   */
   @Test(priority=24)
   public void ExportToExcel() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
	   CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
	   Assert.assertTrue(CepEventMappingPage.verifyExportToExcel(filePath));
   }
    
   	@Test(priority=25)
   	public void ExportToExcelData() throws Exception{	
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\CEP Event Mapping.xlsx";
   		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
   		CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
   		Assert.assertTrue(CepEventMappingPage.verifyexportToExcelSheet(maplist));	
   	}
    
    @Test(priority=26)
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.deleteCEPEventWithoutDeleteReasonRecord(CepEventMappingDetails);
    	Assert.assertEquals(CepEventMappingPage.getMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    
    @Test(priority=27)
    public void VerifyCancelBtnAtDeleteCEPEventMappingRecord() throws Exception{
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.searchCepEventMapping("1919");
    	Thread.sleep(1000);
    	CepEventMappingPage.clickOnDeleteButton();
    	CepEventMappingPage.clickOnDeleteCancelBtn();
    	Assert.assertFalse(CepEventMappingPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }*/

    @Test(priority=28)
    public void DeleteRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.deleteCEPEventMappingRecord(CepEventMappingDetails);
    	Assert.assertEquals(CepEventMappingPage.getSuccessMessage(),"Record Deleted Successfully","Delete record assertion failed");
    }
    
    /*@Test(priority=29)
    public void VerifyAuditTrialReportForDelete() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
    	homePage.navigateToOCMReportsPage();
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
    	Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map1);
    	ocmReportsPage.showReport(reportDetails);
    	Assert.assertTrue(ocmReportsPage.verifyCepEventMappingdelete(CepEventMappingDetails,"Delete"));
    }
    
    @Test(priority=30)
    public void SearchClearSearch() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.verifyinvalidsearchwithwrongdata(CepEventMappingDetails), "InvalidSearchAssertionFailed");
    	screenshot.captureScreen("CepEventMappingTest", "Invalid Search");
    	Assert.assertTrue(CepEventMappingPage.verifyclearsearch(), "ClearSearch Assertion Failed");
    	screenshot.captureScreen("CepEventMappingTest", "Clear Search");
    }
    
    @Test(priority=31)
    public void ExporttoExcelWithoutData() throws Exception{
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
    	CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	Assert.assertTrue(CepEventMappingPage.ExporttoExcelWithoutData(CepEventMappingDetails));
    }
  
    @Test(priority=32)
    public void SortingByAscending() throws IOException {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Cep Event Mapping (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(CepEventMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=33)
    public void SortingByDescending() throws IOException {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Cep Event Mapping (2).xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	Assert.assertTrue(CepEventMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=34)
    public void GroupBy() {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.groupby());
    	screenshot.captureScreen("CepEventMappingTest", "GroupBy");
    	Assert.assertTrue(CepEventMappingPage.groupby());
    	screenshot.captureScreen("CepEventMappingTest", "AlreadyGroupBy");
    }
    
    @Test(priority=35)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=36)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=37)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=38)
    public void VerifyNumberOfItemsPerPageSelection() {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	Assert.assertTrue(CepEventMappingPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=39)
    public void searchwithoutSearchTextbox() throws IOException {
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	CepEventMappingPage.searchwithoutextsearch();
    	Assert.assertEquals(CepEventMappingPage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=40)
    public void database() throws Exception{
    	CepEventMappingPage CepEventMappingPage=PageFactory.createPageInstance(driver,CepEventMappingPage.class);
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CepEventMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
    	CepEventMappingDetails CepEventMappingDetails = new CepEventMappingDetails(map);
    	Assert.assertTrue(CepEventMappingPage.verifyDatabase(CepEventMappingDetails.getQuery()));
    }*/
    
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("CEPEventMappingTest",method.getName());
        driver.navigate().refresh();
    }

}
