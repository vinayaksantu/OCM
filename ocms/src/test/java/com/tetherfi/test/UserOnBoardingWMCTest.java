package com.tetherfi.test;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.user.UserOnBoardingDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.UserOnBoardingPage;
import com.tetherfi.pages.UserOnBoardingWMCPage;
import com.tetherfi.pages.UserRoleMappingWMCPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserOnBoardingWMCTest extends BaseTest{

	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToUserOnBoardingPage() throws Exception {
		HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
		ocmHomePage.navigateToUserOnBoardingPage();
		UserOnBoardingWMCPage userOnBoardingWMCPage=PageFactory.createPageInstance(driver,UserOnBoardingWMCPage.class);
		Assert.assertTrue(userOnBoardingWMCPage.isUserOnBoardingPageDisplayed(),"NavigateToUserOnBoardingPage assertion failed");
	}	


	/*@Test(priority=1,description="To Verify the UserOnBoardingPage Logo, Minimize and Maximize Screen")
	public void UserOnBoardingPage() {
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		Assert.assertTrue(userOnBoardingWMCPage.verifylogo(),"UserOnBoarding Mapping logo assertion failed");
		Assert.assertTrue(userOnBoardingWMCPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("UserOnBoardingWMCTest","maximize window");
		Assert.assertTrue(userOnBoardingWMCPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("UserOnBoardingWMCTest","minimize window");
	}

	@Test(priority=2,description="To Verify UserOnBoarding Approved Data Page Headers ")
	public void VerifyUserOnBoardingPageGridColumnHeaders() {
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		Assert.assertTrue(userOnBoardingWMCPage.verifyGridColumnHeaders(),"Approved table assertion failed");
	}

	@Test(priority=3,description="To Verify Dropdown for all the Columns ")
	public void VerifyDropdownForAllTheColumns() {
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		Assert.assertTrue(userOnBoardingWMCPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=4,description="To Verify ColumnsHeaders Presence when Enabled ")
	public void VerifyColumnsHeaderEnable() throws InterruptedException {
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		Assert.assertTrue(userOnBoardingWMCPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=5,description="To Verify the ColumnsHeaders Presence When Disabled")
	public void VerifyColumnsHeaderDisable() {
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		Assert.assertFalse(userOnBoardingWMCPage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
	}*/

	//@Test(priority=6,description="To Verify Add Record Cancel Button ")
	public void VerifyAddCancelButton() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		Assert.assertTrue(userOnBoardingWMCPage.addCancelButton(userOnBoardingDetails), "Add cancel button assertion failed");
	}

	/*@Test(priority=7,description="To Verify the Step Icons and Add Record with all the Fields Blank  ")
	public void VerifyStepIconPresenceandAddRecordWithAlltheFiledsBlank() throws Exception {
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		Assert.assertTrue(userOnBoardingWMCPage.VerifyAddNewUserOnBoardingStepIconsandCreateRecordWithAlltheFieldsBlank(), "Add cancel button assertion failed");
		Assert.assertEquals(userOnBoardingWMCPage.VerifyMessage(),"Please Provide First Name, Last Name, Lan ID, Avaya Login ID, Org. Unit, Profile, Supervisor", "Add cancel button assertion failed");
	}

	@Test(priority=8,description="To Verify Add Record without FirstName ")
	public void VerifyAddRecordWithoutFirstName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		userOnBoardingWMCPage.addNewUserOnBoardingRecordWithoutFirstName(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingWMCPage.VerifyMessage(), "Please Provide First Name","Record Creation Assertion failed");
	}

	@Test(priority=9,description="To Verify Add Record without Last Name ")
	public void VerifyAddRecordWithoutLastName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		userOnBoardingWMCPage.addNewUserOnBoardingRecordWithoutLastName(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingWMCPage.VerifyMessage(), "Please Provide Last Name","Record Creation Assertion failed");
	}

	@Test(priority=10,description="To Verify Add Record without Lan ID ")
	public void VerifyAddRecordWithoutLanID() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		userOnBoardingWMCPage.addNewUserOnBoardingRecordWithoutLanID(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingWMCPage.VerifyMessage(), "Please Provide Lan ID","Record Creation Assertion failed");
	}

	@Test(priority=11,description="To Verify Add Record without Avaya Login ID ")
	public void VerifyAddRecordWithoutAvayaLoginID() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		userOnBoardingWMCPage.addNewUserOnBoardingRecordWithoutAvayaLoginID(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingWMCPage.VerifyMessage(), "Please Provide Avaya Login ID","Record Creation Assertion failed");
	}

	@Test(priority=12,description="To Verify Add Record without Org.Unit ")
	public void VerifyAddRecordWithoutOrgUnit() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		userOnBoardingWMCPage.addNewUserOnBoardingRecordWithoutOrgUnit(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingWMCPage.VerifyMessage(), "Please Provide Org. Unit","Record Creation Assertion failed");
	}

	@Test(priority=13,description="To Verify Add Record without Profile ")
	public void VerifyAddRecordWithoutProfile() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		userOnBoardingWMCPage.addNewUserOnBoardingRecordWithoutProfile(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingWMCPage.VerifyMessage(), "Please Provide Profile, Supervisor","Record Creation Assertion failed");
	}

	//@Test(priority=14,description="To Verify Add Reord to Approve ")
	public void AddNewUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		userOnBoardingWMCPage.addNewUserOnBoardingRecordWithAllValidData(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingWMCPage.VerifyMessage(), "Record Created Successfully","Record Creation Assertion failed");
	}

	//@Test(priority=15,dependsOnMethods = "AddNewUserOnBoardingRecord",description="To Verify AuditTrail Report for Record Create")
	public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingCreate(userOnBoardingDetails, "Create"),"Audit Trail report assertion failed");
	}
	
	@Test(priority=16,description="To Verify EditRecordWithoutModifyReason ")
	public void EditRecordWithoutModifyReason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		userOnBoardingWMCPage.EditRecordWithoutModifyReason(UserOnBoardingDetails);
		Assert.assertEquals(userOnBoardingWMCPage.VerifyMessage(), "Please enter the modify reason","Record Creation Assertion failed");
	}
	
	@Test(priority=17,description="To Verify Edit UserOnBoarding Record ")
	public void EditUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		userOnBoardingWMCPage.editUserOnBoardingRecord(UserOnBoardingDetails);
		Assert.assertEquals(userOnBoardingWMCPage.getSuccessMessage(), "Record Updated Successfully");
	}

	@Test(priority=18,dependsOnMethods = "EditUserOnBoardingRecord",description="To Verify AuditTrailReportForEdit ")
	public void VerifyAuditTrailReportForEdit() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingUpdate(UserOnBoardingDetails,"Update"),"Audit Trail report assertion failed");
	}
	
	@Test(priority=19,description="To Verify DeleteCancelUserOnBoardingRecord  ")
    public void DeleteCancelUserOnBoardingRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
        UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
        Assert.assertTrue(userOnBoardingWMCPage.deleteCancel(UserOnBoardingDetails), "delete record assertion failed");
    }
	
	@Test(priority=20,description="To Verify DeleteUserOnBoardingRecord ")
	public void DeleteUserOnBoardingRecord() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
		 UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
		 UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
		 userOnBoardingWMCPage.deleteUserOnBoardingRecord(UserOnBoardingDetails.getLanID(), UserOnBoardingDetails.getDeleteReason());
	     Assert.assertEquals(userOnBoardingWMCPage.getSuccessMessage(), "Record Deleted Successfully");
	}
    
    @Test(priority=21,dependsOnMethods="DeleteUserOnBoardingRecord",description="To  VerifyAuditTrialReportForDelete")
    public void VerifyAuditTrialReportForDelete() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyUserOnBoardingDelete(UserOnBoardingDetails,"Delete"));
    }*/
    
    @Test(priority=22)
	public void VerifyArrowMoveForPreviousAndNextPage() {
    	UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
	    Assert.assertTrue(userOnBoardingWMCPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}
	    
	@Test(priority=23)
    public void VerifyArrowMoveForFirstAndLastPage() {
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
	    Assert.assertTrue(userOnBoardingWMCPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}
	    
	 //@Test(priority=24)
	 public void VerifyTotalNumberOfItemsPerPageDetails() {
		 UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
	    Assert.assertTrue(userOnBoardingWMCPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}
	    
	//@Test(priority=25)
	public void VerifyNumberOfItemsPerPageSelection() {
		UserOnBoardingWMCPage userOnBoardingWMCPage = PageFactory.createPageInstance(driver, UserOnBoardingWMCPage.class);
	    Assert.assertTrue(userOnBoardingWMCPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}


	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("UserOnBoardingWMCTest",method.getName());
		driver.navigate().refresh();
	}
}
