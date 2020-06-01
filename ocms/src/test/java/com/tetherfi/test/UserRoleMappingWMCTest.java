package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.pages.UserRoleMappingWMCPage;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.user.UserRoleMappingDetails;
import com.tetherfi.pages.AgentSkillAssignmentNewPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IntroMessageAnnouncementWMCPage;
import com.tetherfi.pages.NewUserRoleMappingPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserRoleMappingWMCTest extends BaseTest{

	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToUserMappingPage() {
    	 HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
         homePage.navigateToOCMPage();
         OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
         Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
         ocmHomePage.navigateToUserRoleMappingPage();
         UserRoleMappingWMCPage userRoleMappingWMCPage=PageFactory.createPageInstance(driver,UserRoleMappingWMCPage.class);
         Assert.assertTrue(userRoleMappingWMCPage.isUserRoleMappingMappingPageDisplayed(),"User Role Mapping assertion failed");
    }
    
    @Test(priority=1)
    public void UserRoleMappingWMCPage() {
    	UserRoleMappingWMCPage userRoleMappingWMCPage=PageFactory.createPageInstance(driver,UserRoleMappingWMCPage.class);
    	Assert.assertTrue(userRoleMappingWMCPage.verifyLogo(), "UserRoleMappingWMCTest Logo Assertion Failed");
    	Assert.assertTrue(userRoleMappingWMCPage.maximizeWindow(), "fullScreen Assertion Failed");
    	screenshot.captureScreen("UserRoleMappingWMCTest", "maximize window");
    	Assert.assertTrue(userRoleMappingWMCPage.minimizeWindow(), "Restore Assertion Failed");
    	screenshot.captureScreen("UserRoleMappingWMCTest", "minimize window");
    }
    
    @Test(priority=2)
    public void verifyDropDownForAllTheColumns() {
    	UserRoleMappingWMCPage userRoleMappingWMCPage=PageFactory.createPageInstance(driver,UserRoleMappingWMCPage.class);
    	Assert.assertTrue(userRoleMappingWMCPage.verifyDropDownOfAllHeaders(),"Columns DropDown Assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
    	UserRoleMappingWMCPage userRoleMappingWMCPage=PageFactory.createPageInstance(driver,UserRoleMappingWMCPage.class);
    	Assert.assertTrue(userRoleMappingWMCPage.VerifyColumnsHeadersEnable(), "Headers assertion Failed");
    }
    
    //@Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
    	UserRoleMappingWMCPage userRoleMappingWMCPage=PageFactory.createPageInstance(driver,UserRoleMappingWMCPage.class);
    	Assert.assertFalse(userRoleMappingWMCPage.verifycolumnsHeaderDisabled(), "Headers Assertion Failed");
    }
    
    @Test(priority=5)
    public void VerifyAddCancelButton() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
        Assert.assertTrue(userRoleMappingWMCPage.addCancelButton(UserRoleMappingDetails), "Add cancel button assertion failed");
    }
    
    @Test(priority=6)
    public void VerifyAddRecordwithoutFirstName() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.addwithoutFirstName(UserRoleMappingDetails);
        Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg());
    }
    
    @Test(priority=7)
    public void VerifyAddRecordWithoutLastName() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.addwithoutLastName(UserRoleMappingDetails);
        Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg());
    }
    
    @Test(priority=8)
    public void VerifyAddRecordWithoutLanId() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.addwithoutLanID(UserRoleMappingDetails);
        Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg());
    }
    
    @Test(priority=9)
    public void VerifyAddRecordWithoutLoginID() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.addwithoutLoginID(UserRoleMappingDetails);
        Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg());
    }
    
    @Test(priority=10)
    public void VerifyAddRecordWithoutOrgUnit() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.addwithoutOrgUnit(UserRoleMappingDetails);
        Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg());
    }
    
    @Test(priority=11)
    public void VerifyAddRecordWithoutProfile() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.addwithoutProfile(UserRoleMappingDetails);
        Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg());
    }
    
    @Test(priority=12)
    public void VerifyAddRecordWithoutSupervisor() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.addwithoutSupervisor(UserRoleMappingDetails);
        Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg());
    }
    
    @Test(priority=13)
    public void VerifyAddRecordWithoutRole() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.addwithoutRole(UserRoleMappingDetails);
        Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg());
    }
    
    @Test(priority=14)
    public void VerifyNewAddRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.addNewUserRoleMappingRecord(UserRoleMappingDetails);
        Assert.assertEquals(userRoleMappingWMCPage.verifySuccessMessage(), "Record Created Successfully", "Record assertion Failed");
    }
    
    @Test(priority=15)
    public void VerifyAuditTrailReportForCreate() throws Exception {
  		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
  	    Map<String, String> map = new ExcelReader(filePath,"Create1").getTestData().get(0);
  	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
  	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
  	    homePage.navigateToOCMReportsPage();
  	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
  	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
  	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
  	    ReportDetails reportDetails= new ReportDetails(map1);
  	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingCreate(UserRoleMappingDetails, "Create"),"Audit Trail report assertion failed");
      }
    
    @Test(priority=16)
    public void VerifyAddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.addNewUserRoleMappingRecord(UserRoleMappingDetails);
	    Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg());
    }
    
    @Test(priority=17)
	public void EditCancelUserRoleMappingRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
        Assert.assertTrue(userRoleMappingWMCPage.EditCancel(UserRoleMappingDetails), "Edit Cancel assertion Failed");
	}
    
    @Test(priority=18)
    public void EditRecordWithoutModifyReason() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Update1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.EditRecordWithoutModifyReason(UserRoleMappingDetails);
        Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg(),"Invalid Record Assertion failed");
    }
    
    @Test(priority=19)
    public void VerfyEditRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Update1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.editUserRoleMappingRecord(UserRoleMappingDetails);
        Assert.assertEquals(userRoleMappingWMCPage.verifySuccessMessage(), "Record Updated Successfully", "Update assertion failed");
    }
    
    @Test(priority=20)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Update1").getTestData().get(0);	
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingUpdate(UserRoleMappingDetails,"Update"));
    }
    
    @Test(priority=21)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
        Assert.assertTrue(userRoleMappingWMCPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=22)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Role Mapping.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);;
        Assert.assertTrue(userRoleMappingWMCPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=23)
    public void VerifyExportToExcelWithoutData() throws Exception {
    	UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Invalid1").getTestData().get(0);
        UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        Assert.assertTrue(userRoleMappingWMCPage.ExporttoExcelWithoutData(UserRoleMappingDetails));
       }
    
   @Test(priority=24)
    public void SortingByAscending() throws IOException {
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Role Mapping (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(userRoleMappingWMCPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=25)
    public void SortingByDescending() throws IOException {
    	UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
    	userRoleMappingWMCPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Role Mapping (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(userRoleMappingWMCPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=26)
    public void VerifyDragAndDrop() {
    	UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
    	userRoleMappingWMCPage.dragColumntoGroup("First Name");
        Assert.assertTrue(userRoleMappingWMCPage.verifyDragColumntoGroup("First Name"),"drag and drop assertion failed");
    }
    
    @Test(priority=27)
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
    	UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
    	userRoleMappingWMCPage.dragColumntoGroup("Last Name");
    	userRoleMappingWMCPage.dragColumntoGroup("Last Name");
        Assert.assertTrue(userRoleMappingWMCPage.verifyDragColumntoGroup("Last Name"),"drag and drop assertion failed");
    }
    
    @Test(priority=28)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
        Assert.assertFalse(userRoleMappingWMCPage.clearAll(UserRoleMappingDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("UserRoleMappingTest", "clearall");
        Assert.assertTrue(userRoleMappingWMCPage.verifyclose());
        screenshot.captureScreen("UserRoleMappingTest", "SearchClose");
    }
    
    @Test(priority=29)
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    userRoleMappingWMCPage.searchwithoutextsearch(UserRoleMappingDetails);
    	Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg());
    }
    
    @Test(priority=30)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Invalid1").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	    UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
        Assert.assertTrue(userRoleMappingWMCPage.verifyinvalidsearchwithwrongdata(UserRoleMappingDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("UserRoleMappingTest","Invalid Search with wrong data");
        Assert.assertTrue(userRoleMappingWMCPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=31)
	public void VerifyArrowMoveForPreviousAndNextPage() {
    	UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    Assert.assertTrue(userRoleMappingWMCPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}
	    
	@Test(priority=32)
    public void VerifyArrowMoveForFirstAndLastPage() {
		UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    Assert.assertTrue(userRoleMappingWMCPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}
	    
	 @Test(priority=33)
	 public void VerifyTotalNumberOfItemsPerPageDetails() {
		UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    Assert.assertTrue(userRoleMappingWMCPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}
	    
	@Test(priority=34)
	public void VerifyNumberOfItemsPerPageSelection() {
		UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	    Assert.assertTrue(userRoleMappingWMCPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=35)
	public void DeleteCancelUserRoleMappingRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
	     Map<String, String> map = new ExcelReader(filePath, "Delete1").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	     Assert.assertTrue(userRoleMappingWMCPage.DeleteCancel(UserRoleMappingDetails), "Delete Cancel assertion Failed");
	}
	
	@Test(priority=36)
    public void DeleteWithoutModifyReasonRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete1").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	     userRoleMappingWMCPage.DeleteRecordWithoutModifyReason(UserRoleMappingDetails);
	     Assert.assertFalse(userRoleMappingWMCPage.getErrorMsg(),"Invalid Record Assertion failed");
    }
	
	@Test(priority=37)
    public void DeleteRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete1").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
	     userRoleMappingWMCPage.DeleteUserRoleMappingRecord(UserRoleMappingDetails);
	     Assert.assertEquals(userRoleMappingWMCPage.verifySuccessMessage(), "Record Deleted Successfully", "Update assertion failed");
     }
	
	@Test(priority=38)
    public void VerifyAuditTrailReportForApprove() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete1").getTestData().get(0);
	     UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyUserRoleMappingDelete(UserRoleMappingDetails, "Delete"),"Audit Trail report assertion failed");
    }
	
	@Test(priority=39)
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid1").getTestData().get(0);
		UserRoleMappingDetails UserRoleMappingDetails=new UserRoleMappingDetails(map);
		UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
		Assert.assertTrue(userRoleMappingWMCPage.verifySearchIsnotEqualTo(UserRoleMappingDetails.getSupervisor()));
	}
	
	@Test(priority=40)
	public void VerifySearchContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid1").getTestData().get(1);
		UserRoleMappingDetails UserRoleMappingDetails=new UserRoleMappingDetails(map);
		UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
		Assert.assertTrue(userRoleMappingWMCPage.verifySearchContains(UserRoleMappingDetails.getSupervisor()));
	}
	
	@Test(priority=41)
	public void VerifySearchDoesNotContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid1").getTestData().get(1);
		UserRoleMappingDetails UserRoleMappingDetails=new UserRoleMappingDetails(map);
		UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
		Assert.assertTrue(userRoleMappingWMCPage.verifySearchDoesNotContains(UserRoleMappingDetails.getSupervisor()));
	}
    
	@Test(priority=42)
	public void VerifySearchStartsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid1").getTestData().get(2);
		UserRoleMappingDetails UserRoleMappingDetails=new UserRoleMappingDetails(map);
		UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
		Assert.assertTrue(userRoleMappingWMCPage.verifySearchStartsWith(UserRoleMappingDetails.getSupervisor()));
	}
	
	@Test(priority=43)
	public void VerifySearchEndsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid1").getTestData().get(3);
		UserRoleMappingDetails UserRoleMappingDetails=new UserRoleMappingDetails(map);
		UserRoleMappingWMCPage userRoleMappingWMCPage = PageFactory.createPageInstance(driver, UserRoleMappingWMCPage.class);
		Assert.assertTrue(userRoleMappingWMCPage.verifySearchEndsWith(UserRoleMappingDetails.getSupervisor()));
	
	}
	 
    
    
    
    @AfterMethod
    public void afterEachMethod(Method method){
    	Screenshot screenshot=new Screenshot(driver);
    	screenshot.captureScreen("UserRoleMappingWMCTest", method.getName());
    	driver.navigate().refresh();
    }
}
