package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.IntentMappingDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.IntentMappingPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class IntentMappingTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
	public void NavigateToIntentMappingPage() throws Exception {
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        ivrPage.navigateToIntentMappingPage();
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertTrue(IntentMappingPage.isIntentMappingPageDisplayed(), "IntentMapping page assertion failed");
    	screenshot.captureScreen("IntentMappingTest","IntentMappingPage");
    }
	
	/*@Test(priority=1)
	public void IntentMappingPage() {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertTrue(IntentMappingPage.VerifyLogo(),"Logo assertion failed");
        Assert.assertTrue(IntentMappingPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("IntentMappingTest","Maximize");
    	Assert.assertTrue(IntentMappingPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("IntentMappingTest","Minimize");	
    }
	
	@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertTrue(IntentMappingPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertTrue(IntentMappingPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertFalse(IntentMappingPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }*/
    
   @Test(priority=5)
    public void AddNewIntentMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.addNewIntentMappingRecord(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
    }
    
    @Test(priority=6,dependsOnMethods = "AddNewIntentMappingRecord")
    public void AddDuplicateIntentMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.addNewIntentMappingRecord(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Duplicate Record");
    }
    
    @Test(priority=7,dependsOnMethods ="AddNewIntentMappingRecord")
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntentMappingCreate(IntentMappingDetails,"Create"));
    }
    
   /* @Test(priority=8)
    public void AddEmptyRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.addNewEmptyRecord(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Provide a Product", "Add invalid record assertion failed");
    }
    
    @Test(priority=9)
    public void AddRecordWithoutProduct() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.addRecordWithoutProduct(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Provide a Product", "Add invalid record assertion failed");
    }
    
    @Test(priority=10)
    public void AddRecordWithoutSegment() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.addRecordWithoutSegment(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Provide a Segment", "Add invalid record assertion failed");
    }
    
    @Test(priority=11)
    public void AddRecordWithoutLanguage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.addRecordWithoutLanguage(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Provide a Language", "Add invalid record assertion failed");
    }
    
    @Test(priority=12)
    public void AddRecordWithoutIntentTalent() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.addRecordWithoutIntentTalent(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Provide an Intent Talent", "Add invalid record assertion failed");
    }
    
    @Test(priority=13)
    public void AddRecordWithoutVDN() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.addRecordWithoutVDN(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Enter a VDN", "Add invalid record assertion failed");
    }
    
    @Test(priority=14)
    public void VerifyCancelBtnAtAddRecord(){
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.clickOnAddRecord();
        IntentMappingPage.clickOnCancelBtn();
        Assert.assertFalse(IntentMappingPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
	
   /* @Test(priority=15)
    public void EditIntentMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.editIntentMappingRecord(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    
    @Test(priority=16,dependsOnMethods="EditIntentMappingRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntentMappingUpdate(IntentMappingDetails,"Update"));
    }
    
    @Test(priority=17,dependsOnMethods = "EditIntentMappingRecord")
    public void EditWithoutModifyReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.editIntentMappingWithoutModifyReason(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=18,dependsOnMethods = "EditWithoutModifyReasonRecord")
    public void VerifyCancelBtnAtEditRecord() throws Exception{
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.searchIntentMappingRecord("40000");
        Thread.sleep(1000);
        IntentMappingPage.clickOnEditButton();
        IntentMappingPage.clickOnCancelBtn();
        Assert.assertFalse(IntentMappingPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
	
	 @Test(priority=19)
	 public void VerifySearchIsNotEqualTo() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertTrue(IntentMappingPage.verifySearchIsNotEqualTo(IntentMappingDetails.getIntentTalent()));
	}
	
	@Test(priority=20)
	 public void VerifySearchContains() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
       IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
       IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
       Assert.assertTrue(IntentMappingPage.verifySearchContains(IntentMappingDetails.getIntentTalent()));
	}
    
	@Test(priority=21)
	 public void VerifySearchDoesNotContains() throws Exception {
      String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
      Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
      IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
      IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
      Assert.assertTrue(IntentMappingPage.verifySearchDoesNotContains(IntentMappingDetails.getIntentTalent()));
	}
	
	@Test(priority=22)
	 public void VerifySearchStartsWith() throws Exception {
     String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
     Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
     IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
     IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
     Assert.assertTrue(IntentMappingPage.verifySearchStartsWith(IntentMappingDetails.getIntentTalent()));
	}
	
	@Test(priority=23)
	 public void  VerifySearchEndsWith() throws Exception {
    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
    Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
    IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
    IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
    Assert.assertTrue(IntentMappingPage.verifySearchEndsWith(IntentMappingDetails.getIntentTalent()));
	}
	
    @Test(priority=24)
    public void searchPage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertFalse(IntentMappingPage .clearAll(IntentMappingDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("IntentMappingTest", "clearall");
        Assert.assertTrue(IntentMappingPage.verifyclose());
    }
    
    @Test(priority=25)
    public void searchwithoutSearchTextbox() throws IOException {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.searchwithoutextsearch();
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=26)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertTrue(IntentMappingPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=27)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intent Mapping.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
    	Assert.assertTrue(IntentMappingPage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=28)
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.deleteIntentMapingWithoutDeleteReasonRecord(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=29)
    public void VerifyCancelBtnAtDeleteIntentMappingRecord() throws Exception{
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.searchIntentMappingRecord("40000");
        Thread.sleep(1000);
        IntentMappingPage.clickOnDeleteButton();
        IntentMappingPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(IntentMappingPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(priority=30,dependsOnMethods = "VerifyCancelBtnAtDeleteIntentMappingRecord")
    public void DeleteIntentMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.deleteIntentMappingRecord(IntentMappingDetails);
        Assert.assertEquals(IntentMappingPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    
    @Test(priority=31,dependsOnMethods= {"DeleteIntentMappingRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntentMappingdelete(IntentMappingDetails,"Delete"));
    }
    
    @Test(priority=32)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
    	Assert.assertTrue(IntentMappingPage.verifyinvalidsearchwithwrongdata(IntentMappingDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("IntentMappingTest","Invalid Search with wrong data");
        Assert.assertTrue(IntentMappingPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=33)
    public void ExporttoExcelWithoutData() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertTrue(IntentMappingPage.ExporttoExcelWithoutData(IntentMappingDetails));
    }
  
    @Test(priority=34)
    public void SortingByAscending() throws IOException {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intent Mapping (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(IntentMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=35)
    public void SortingByDescending() throws IOException {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intent Mapping (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(IntentMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=36)
    public void GroupBy()
    {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
    	Assert.assertTrue(IntentMappingPage.groupby());
        screenshot.captureScreen("IntentMappingTest", "GroupBy");
    	Assert.assertTrue(IntentMappingPage.groupby());
        screenshot.captureScreen("IntentMappingTest", "AlreadyGroupBy");
    }
    
    @Test(priority=37)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
    	Assert.assertTrue(IntentMappingPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=38)
    public void VerifyArrowMoveForFirstAndLastPage() {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertTrue(IntentMappingPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=39)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertTrue(IntentMappingPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=40)
    public void VerifyNumberOfItemsPerPageSelection() {
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        Assert.assertTrue(IntentMappingPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    
    @Test(priority=41)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        IntentMappingPage IntentMappingPage = PageFactory.createPageInstance(driver, IntentMappingPage.class);
        IntentMappingDetails IntentMappingDetails = new IntentMappingDetails(map);
    	Assert.assertTrue(IntentMappingPage.verifyDatabase(IntentMappingDetails.getQuery()));
    }*/
	
	 @AfterMethod
	    public void afterEachMethod(Method method) throws InterruptedException {
		        Screenshot screenshot=new Screenshot(driver);
		        screenshot.captureScreen("IntentMappingTest",method.getName());
		        driver.navigate().refresh();
		}
}
