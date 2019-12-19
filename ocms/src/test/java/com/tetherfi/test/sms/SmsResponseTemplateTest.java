package com.tetherfi.test.sms;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.sms.SmsResponseTemplateDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.SmsPage;
import com.tetherfi.pages.SmsResponseTemplatePage;
import com.tetherfi.pages.SmsResponseTemplatePageWMC;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SmsResponseTemplateTest extends BaseTest{
Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
    public void NavigateToSmsResponseTemplatePage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("SMS");
        SmsPage smsPage = PageFactory.createPageInstance(driver, SmsPage.class);
        Assert.assertTrue(smsPage.isSMSPageDisplayed(), "SMS Page assertion failed");
        smsPage.navigateToSMSResponseTemplatePage();
        SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC = PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);
        Assert.assertTrue(SmsResponseTemplatePageWMC.isSMSResponseTemplatePageDisplayed(), "SMS Response Template Page assertion failed");
    }
	
	@Test(priority=1)
	public void SmsResponseTemplatePage() {
		SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC = PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);
		Assert.assertTrue(SmsResponseTemplatePageWMC.verifylogo(),"SMS Page logo assertion failed");
		Assert.assertTrue(SmsResponseTemplatePageWMC.maximizewindow(),"Fullscreen Assertion Failed"); 
		Assert.assertTrue(SmsResponseTemplatePageWMC.minimizewindow(), "Restored Assertion Failed");
	}
    	
	@Test(priority=2)
	public void VerifyDropDownForAllColumns() {
		SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);
		Assert.assertTrue(SmsResponseTemplatePageWMC.verifyDropDownOfAllHeaders(),"Columns DropDown Assertion Failed"); 	
	}
    
	@Test(priority=3)
	public void VerifyColumnsHeaderEnable() {
		SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);	
		Assert.assertTrue(SmsResponseTemplatePageWMC.verifycolumnsHeaderEnabled(),"Columns Headers Assertion Failed");	
	}
    
	@Test(priority=4)
	public void VerifyColumnsHeaderDisable() {
		SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);	
		Assert.assertFalse(SmsResponseTemplatePageWMC.verifycolumnsHeaderDisabled(),"Columns Headers Assertion Failed");	   	
	}

	@Test(priority=5)
	public void AddNewSMSRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);		
		SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);	
		SmsResponseTemplatePageWMC.addNewSmsResponseTemplateRecord(SmsResponseTemplateDetails);
		Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(),"Record Created Successfully"); 
	}

    @Test(priority=6)
    public void AddDuplicateSMSRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);		
        SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);	
        SmsResponseTemplatePageWMC.addNewSmsResponseTemplateRecord(SmsResponseTemplateDetails);
        Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(),"Duplicate ICOM Template ID", "Duplicate Record Create Assertion Failed"); 	  	
    }
    
    @Test(priority=7)
    public void VerifyAuditTrialReportForCreate() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);	
    	SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
    	HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
    	homePage.navigateToOCMReportsPage();
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
    	Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map1);
    	ocmReportsPage.showReport(reportDetails);
    	Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateCreate(SmsResponseTemplateDetails,"Create"), "Audit Trail Report assertion failed");	 	
    }
    
    @Test(priority=8)
    public void VerifyAddEmptyRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);	   
    	SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);		
    	SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
    	SmsResponseTemplatePageWMC.AddEmptyRecord(SmsResponseTemplateDetails);	
    	Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(), "Please provide input for Text, Intent, Enable, ICOM Template ID", "Add Empty Record Assertion Failed");
    }
    
   @Test(priority=9)
   public void AddNewRecordWithoutText() throws Exception {
	   String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);	   
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);		
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);   
	   SmsResponseTemplatePageWMC.addNewSmsResponseTemplateRecordWithoutText(SmsResponseTemplateDetails);
	   Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(), "Please provide input for Text", "Add New Record without Text Assertion Failed");	   
   }
   
   @Test(priority=10)
   public void AddNewRecordWithoutIntent() throws Exception {
    String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
    Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);	   
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);		
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);     
	   SmsResponseTemplatePageWMC.addNewSmsResponseTemplateRecordWithoutIntent(SmsResponseTemplateDetails);
	   Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(), "Please provide input for Intent", "Add New Record without Intent Assertion Failed");	   
   }
   
   @Test(priority=11)
   public void AddNewRecordWithoutEnable() throws Exception {
	   String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);	   
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);		
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);  
	   SmsResponseTemplatePageWMC.addNewSmsResponseTemplateRecordWithoutEnable(SmsResponseTemplateDetails);
	   Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(), "Please provide input for Enable", "Add New Record Without Enable Assertion Failed");	   
   }
   
   @Test(priority=12)
   public void AddNewRecordWithoutICOMTemplateID() throws Exception {
	   String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);	   
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);		
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);   
	   SmsResponseTemplatePageWMC.addNewSmsResponseTemplateRecordWithoutIcomTemplateID(SmsResponseTemplateDetails);
	   Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(), "Please provide input for ICOM Template ID", "Add New ICOM TemplateID Record Assertion Failed");   
   }

   @Test(priority=13)
   public void VerifyCancelBtnAtAddnewRecord() throws Exception {
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			   
	   SmsResponseTemplatePageWMC.clickOnAddNewRecord();
	   SmsResponseTemplatePageWMC.clickOnCancelBtn();
	   Assert.assertFalse(SmsResponseTemplatePageWMC.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");	   
   }
   
   @Test(priority=14,dependsOnMethods="AddNewSMSRecord") 
   public void EditSmsResponseTemplateRecord() throws Exception {
	   String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);	
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);		  
	   SmsResponseTemplatePageWMC.EditSmsResponseTemplateRecord(SmsResponseTemplateDetails);
	   Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(), "Record updated successfully","Edit record assertion failed");;
   }
   
   @Test(priority=15)//, dependsOnMethods="EditSmsResponseTemplateRecord")
   public void VerifyAuditTrialReportForUpdate() throws Exception {
	   String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	   HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	   homePage.navigateToOCMReportsPage();
	   OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	   String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	   Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	   ReportDetails reportDetails= new ReportDetails(map1);
	   ocmReportsPage.showReport(reportDetails);
	   Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateUpdate(SmsResponseTemplateDetails,"Update"));      
   }
   
   @Test(priority=16,dependsOnMethods="EditSmsResponseTemplateRecord") 
   public void EditSmsTemplateRecordWithoutModifyReason() throws Exception {
	   String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);		
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);	 
	   SmsResponseTemplatePageWMC.EditRecordWithoutModifyReason(SmsResponseTemplateDetails);
	   Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(), "Please enter the modify reason", "empty modify reason record assertion failed");
   }
   
   @Test(priority=17)//,dependsOnMethods="EditSmsResponseTemplateRecord")
   public void VerifyCancelBtnAtEditSmsResponseTemplateRecord() throws Exception {
	   String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";   
	   Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);	
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	   SmsResponseTemplatePageWMC.clickOnEditButton(SmsResponseTemplateDetails);
	   SmsResponseTemplatePageWMC.clickOnCancelBtn();
	   Assert.assertFalse(SmsResponseTemplatePageWMC.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");	   
   }
 
   @Test(priority=18)  
   public void searchPage() throws Exception {
	   String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx"; 
	   Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);	    
	   Assert.assertFalse(SmsResponseTemplatePageWMC.clearAll(SmsResponseTemplateDetails), "Clear All Assertion Failed");
	   Assert.assertTrue(SmsResponseTemplatePageWMC.verifyclose());	   
   }
  
   @Test(priority=19)	
   public void searchwithoutSearchTextbox() {  
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);	
	   SmsResponseTemplatePageWMC.searchwithoutextsearch();
	   Assert.assertEquals(SmsResponseTemplatePageWMC.verifySuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");	 
   }
 
   //@Test(priority=20)
   public void ExportToExcel() {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);
	   Assert.assertTrue(SmsResponseTemplatePageWMC.verifyExportToExcel(filePath));	 
   }
 
   //@Test(priority=21)
   public void ExportToExcelData() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\SMS Response Template.xlsx";
	   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);	   
	   Assert.assertTrue(SmsResponseTemplatePageWMC.verifyexportToExcelSheet(maplist));	   
   }
   
   @Test(priority=22)
   public void DeleteRecordWithoutDeleteReason() throws Exception {
	   String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);	
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);	
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);		
	   SmsResponseTemplatePageWMC.DeleteSmsResponseTemplateRecordWithoutDeleteReason(SmsResponseTemplateDetails);
	   Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(), "Please enter the delete reason", "Delete Record Assertion Failed");
   }

   @Test(priority=23)
   public void VerifyCancelBtnAtDeleteConfigRecord() throws Exception {
	   String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);	
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);	
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			   
	   SmsResponseTemplatePageWMC.clickOnDeleteButton(SmsResponseTemplateDetails);
	   SmsResponseTemplatePageWMC.clickOnDeleteCancelBtn();
	   Assert.assertFalse(SmsResponseTemplatePageWMC.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
   }
   
   @Test(priority=24)
   public void DeleteSmsResponseTemplateRecord() throws Exception {
	   String filePath=System.getProperty("user.dir")+"\\src\\test\\\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	   SmsResponseTemplateDetails SmsResponseTemplateDetails=new SmsResponseTemplateDetails(map);
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			   
	   SmsResponseTemplatePageWMC.DeleteSmsResponseTemplateRecord(SmsResponseTemplateDetails);
	   Assert.assertEquals(SmsResponseTemplatePageWMC.getSuccessMessage(), "Record deleted successfully", "Delete Record Assertion failed");  
   }
   
   @Test(priority=25)//, dependsOnMethods="DeleteSmsResponseTemplateRecord")
   public void VerifyAuditTrialReportForDelete() throws Exception {
	   String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
       HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
       homePage.navigateToOCMReportsPage();
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
       String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
       Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map1);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifySmsResponseTemplateDelete(SmsResponseTemplateDetails, "Delete"));      
   }
   
   @Test(priority=26)
   public void SearchClearSearch() throws Exception {
	   String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);   
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			  
	   Assert.assertTrue(SmsResponseTemplatePageWMC.verifyinvalidsearchwithwrongdata(SmsResponseTemplateDetails), "Invalid Record search Assertion Failed");
   }

   @Test(priority=27)
   public void ExporttoExcelWithoutData() throws Exception {
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			     
	   String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);	
	   SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
	   Assert.assertTrue(SmsResponseTemplatePageWMC.ExporttoExcelWithoutData(SmsResponseTemplateDetails));	
   }
   
   @Test(priority=28)
   public void SortByAscending() throws IOException {
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			     
	   SmsResponseTemplatePageWMC.SortByAscending();
	   String filePath=System.getProperty("user.dir") +"\\src\\test\\resources\\DownloadedFiles\\SMS Response Template.xlsx";
	   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	   Assert.assertTrue(SmsResponseTemplatePageWMC.verifyexportToExcelSheet(maplist));   
   }

   @Test(priority=29)
   public void SortByDescending() throws IOException{
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			     
	   SmsResponseTemplatePageWMC.SortByDescending();
	   String filePath=System.getProperty("user.dir") +"\\src\\test\\resources\\DownloadedFiles\\SMS Response Template.xlsx";
	   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	   Assert.assertTrue(SmsResponseTemplatePageWMC.verifyexportToExcelSheet(maplist));   	   
   }
   
   @Test(priority=30)
   public void GroupBy() {
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			     
	   Assert.assertTrue(SmsResponseTemplatePageWMC.groupby(), "Group By Assertion Failed"); 
   }

   @Test(priority=31)
   public void VerifyArrowMoveForPreviousAndNextPage() {
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			     
	   Assert.assertTrue(SmsResponseTemplatePageWMC.verifyArrowMoveForPreviousAndNextPage(), "Arrow move for Previous and Next page Assertion failed");	   
   }
   
   @Test(priority=32)
   public void VerifyArrowMoveForFirstAndLastPage() {
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			     
	   Assert.assertTrue(SmsResponseTemplatePageWMC.verifyArrowMoveForFirstAndLastPage(), "Arrow move for First and last page assertion failed");	   
   }
   
   @Test(priority=33)
   public void VerifyTotalNumberOfItemsPerPageDetails() {
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			     
	   Assert.assertTrue(SmsResponseTemplatePageWMC.verifyTotalNumberOfItemsPerPageDetails());	   
   }
   
   @Test(priority=34)
   public void VerifyNumberOfItemsPerPageSelection() {
	   SmsResponseTemplatePageWMC SmsResponseTemplatePageWMC=PageFactory.createPageInstance(driver, SmsResponseTemplatePageWMC.class);			      
	   Assert.assertTrue(SmsResponseTemplatePageWMC.verifyNumberOfItemsPerPage(), "Number of items per page assertion failed");
   }
       
     @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("SmsResponseTemplateTest",method.getName());
	        driver.navigate().refresh();
	}	
}
