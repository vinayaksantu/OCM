package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.user.UserOnBoardingDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.UserOnBoardingPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserOnBoardingImportUserTest {

	protected WebDriver driver;
	@BeforeMethod
	public void NavigateToUserOnBoardingPage(Method method) throws Exception {
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		}catch (Exception e){
			PageFactory.reset();
			driver.close();
			e.printStackTrace();
		}
		System.out.println("Started Executing : "+method.getName());
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		Test t = method.getAnnotation(Test.class);
		Map<String, String> map;
		if(t.groups()[0].equalsIgnoreCase("Checker"))
			map= new ExcelReader(filePath,"Login").getTestData().get(1);
		else
			map= new ExcelReader(filePath,"Login").getTestData().get(0);
		try{driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}catch (TimeoutException e){e.printStackTrace();driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
		if(map.get("LoginType").equals("Custom")){
			LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
			Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page not loaded");
			loginPage.login(map.get("Username"),map.get("Password"),map.get("EmailId"));
			Thread.sleep(5000);
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToUserOnBoardingPage();
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.isUserOnBoardingPageDisplayed(), "NavigateToNewUserOnBoardingPage Page assertion failed");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test(groups= {"Maker"},priority=1,description="To Verify Import User PopUp")
	public void VerifyImportUserPopUP() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserPopUp(), "Verify Import UserPopUP assertion failed");
	}

	@Test(groups= {"Maker"},priority=2,description="To Verify Import User with Each Mandatory Field Blank")
	public void VerifyImportUserwithEachMandatoryFieldBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithLanIDBlank(userOnBoardingDetails), "Verify Import User with FirstName Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithFirstNameBlank(userOnBoardingDetails), "Verify Import User with LanID Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithLastNameBlank(userOnBoardingDetails), "Verify Import Userwith LastName Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithAvayaLoginIDBlank(userOnBoardingDetails), "Verify Import User with AvayaLoginID Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithProfileBlank(userOnBoardingDetails), "Verify Import User with Profile Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithOrgUnitBlank(userOnBoardingDetails), "Verify Import User with OrgUnit Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithSupervisorLanIDBlank(userOnBoardingDetails), "Verify Import User with SupervisorLanID Blank Assertion Failed");
	}

	@Test(groups= {"Maker"},priority=3,description="Verify Import User with ALL the Fields Blank")
	public void VerifyImportUserwithALLtheFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(1);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithAllFieldsBlank(userOnBoardingDetails), "Verify Import User with All the Fields Blank Assertion Failed");
	}

	@Test(groups= {"Maker"},priority=4,description="Verify Import User with Only One File at Time")
	public void VerifyImportUserwithOnlyOneFileatTime() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithOnlyOneFileatTime(userOnBoardingDetails), "Verify Import User with Only One File at Time Assertion Failed");
	}

	@Test(groups= {"Maker"},priority=5,description="To Verify Import User with Non Mandatory Fields Blank")
	public void VerifyImportUserwithNonMandatoryFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithAllNonMandatoryFieldsBlank(userOnBoardingDetails), "Verify Import User with All Non Mandatory Fields Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithAccessRoleandRoleFieldsBlank(userOnBoardingDetails), "Verify Import User with Access Role and Role Fields Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithCRMandTextChatFieldsBlank(userOnBoardingDetails), "Verify Import User with CRM and TextChat Fields Blank Assertion Failed");
	}

	@Test(groups = { "Maker" },priority=6,dependsOnMethods="VerifyImportUserwithNonMandatoryFieldsBlank",description="To  Revert Imported Records")
	public void VerifyRevertAllNonMandatoryRecords() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectallNonmandatoryFieldsRecord();
		userOnBoardingPage.Revert("revert");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Reverted"),"Approval Status Details Failed");
	}

	@Test(groups= {"Maker"},priority=7,description="Verify Import User with Duplicate AvayaLoginId")
	public void VerifyImportUserwithDuplicateAvayaLoginId() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(3);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import User with Duplicate AvayaLoginId Assertion Failed");
	}

	//@Test(groups= {"Maker"},priority=8,description="Verify Import User with DuplicateLanId")//Bug Record is Inserted with duplicate LanID[PRDOCM-64134]
	public void VerifyImportUserwithDuplicateLanId() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(4);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import User with DuplicateLanId Assertion Failed");
	}

	@Test(groups= {"Maker"},priority=9,description="To Verify Import Supervisor with EachLevel")
	public void VerifyImportSupervisorwithEachLevel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportCountryLevelSupervisor(userOnBoardingDetails), "Verify Import CountryLevel Supervisor Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportDivisionLevelSupervisor(userOnBoardingDetails), "Verify Import Division Level Supervisor Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportDepartmentlevelSupervisor(userOnBoardingDetails), "Verify Import Department level Supervisor Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportTeamLevelSupervisor(userOnBoardingDetails), "Verify Import TeamLevel Supervisor Assertion Failed");
	}

	@Test(groups = { "Maker" },priority=10)//,dependsOnMethods="VerifyImportSupervisorwithEachLevel",description="To  Revert Imported Records   ")
	public void VerifyRevertAllSupervisorRecords() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectallSupervisorRecord();
		userOnBoardingPage.Revert("revert");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Reverted"),"Approval Status Details Failed");
	}

	//@Test(groups= {"Maker"},priority=11,description="Verify Import Users with Contry OrgUnit with Div Supervisor")//Bug Importing is successful 
	public void VerifyImportUserswithContryOrgUnitwithDivSupervisor() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(5);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Users with Contry OrgUnit with Div Supervisor Assertion Failed");
	}

	//@Test(groups= {"Maker"},priority=12,description="Verify Import User with Comma Separated Values InRoleColumn")//Bug Failed to Import
	public void VerifyImportUserwithCommaSeparatedValuesInRoleColumn() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(6);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import User with CommaSeparated Values In Role Column Assertion Failed");
	}

	//@Test(groups= {"Maker"},priority=13,description="Verify Import Valid  Multiple UserDetails ChannelCount and Features")//Revert and Reject is not happening for the Multiple Imported Records
	public void VerifyImportValidMultipleUserDetailsChannelCountandFeatures() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(7);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify ImportValid Multiple UserDetails ChannelCount and Features Assertion Failed");
	}

	//@Test(groups= {"Maker"},priority=14,description="Verify Import Multiple UserDetails ChannelCount and Features with DuplicateLanID")//Bug:Importing is successfully with duplicate Lan ID
	public void VerifyImportMultipleUserDetailsChannelCountandFeatureswithDuplicateLanID() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(8);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Multiple UserDetails ChannelCount and FeatureswithDuplicateLanID Assertion Failed");
	}

	//@Test(groups= {"Maker"},priority=15,description="Verify Import Multiple UserDetails ChannelCount and Features with DuplicateAvayaLoginID")
	public void VerifyImportMultipleUserDetailsChannelCountandFeatureswithDuplicateAvayaLoginID() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(9);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Multiple UserDetails ChannelCount and Features with DuplicateAvayaLoginID Assertion Failed");
	}

	//@Test(groups= {"Maker"},priority=16,description=" To Verify Import Multiple User with Each MandatoryFieldsBlank")
	public void VerifyImportMultipleUserwithEachMandatoryFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(10);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportMultipleUserwithFirstNameBlank(userOnBoardingDetails), "Verify Import Multiple User with FirstName Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportMultipleUserwithAllFieldsBlank(userOnBoardingDetails), "Verify Import Multiple User with AllFields Blank Assertion Failed");
	}

	@Test(groups= {"Maker"},priority=17,description="To Verify Import with Invalid Scenarios")
	public void VerifyImportwithInvalidScenarios() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserDetailswithInvalidOrgUnit(userOnBoardingDetails), "Verify Import User with Invalid Org Unit Assertion Failed");
		//Assert.assertTrue(userOnBoardingPage.VerifyImportUserDetailswithNewColumn(userOnBoardingDetails), "Verify Import User with New Column Assertion Failed");//Bug:Import is successfull
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserDetailswithUpdatedColumn(userOnBoardingDetails), "Verify Import User with Updated column name Assertion Failed");
	}

	//@Test(groups= {"Maker"},priority=18,description="To Verify Import User Profile Pictures with out Attribute")//Bug:Record is Imported successfully
	public void VerifyImportSupervisorasNAwithEachLevel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(11);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportwithoutDivisionLevelSupervisor(userOnBoardingDetails), "Verify Import User with Access Role and Role Fields Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportwithoutDepartmentlevelSupervisor(userOnBoardingDetails), "Verify Import User with CRM and TextChat Fields Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportwithoutTeamLevelSupervisor(userOnBoardingDetails), "Verify Import User with CRM and TextChat Fields Blank Assertion Failed");
	}

	@Test(groups= {"Maker"},priority=19,description="Verify Import Valid Agent with TeamLevelSupervisor")
	public void VerifyImportValidAgentwithTeamLevelSupervisor() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(12);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Valid Agent with TeamLevelSupervisor Assertion Failed");
	}

	//@Test(groups= {"Maker"},priority=20,description="Verify Import User with Channel Disabled with Count and Features")//Bug:Import is successfull
	public void VerifyImportUserwithChannelDisabledwithCountandFeatures() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(13);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify ImportUser with Channel Disabled with Count and Features Assertion Failed");
	}

	@Test(groups= {"Maker"},priority=21,description="Verify Import Agent with Invalid Mandatory Fields")
	public void VerifyImportAgentwithInvalidMandatoryFields() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(14);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithInvalidTextChatGreetingFiles(userOnBoardingDetails), "Verify Import User with Invalid TextChatGreeting Files Assertion Failed");
		//Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithInvalidRoleFile(userOnBoardingDetails), "Verify Import User with Invalid Role File Assertion Failed");//Bug:Record is imorted but Record Count Displayed as Inserted and in Invalid
	}

	@Test(groups= {"Maker"},priority=22,description="Verify Import User with Invalid Mandatory Fields")
	public void VerifyImportUserwithInvalidMandatoryFields() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(15);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithInvalidProfileFile(userOnBoardingDetails), "Verify Import User with Invalid ProfileFile Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithInvalidRoleAccessFile(userOnBoardingDetails), "Verify Import User with Invalid RoleAccessFile Assertion Failed");
		//Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithAlphabetsasAvayaLoginID(userOnBoardingDetails), "Verify Import User with Alphabets as AvayaLoginID Assertion Failed");//Bug:Records are created with alphabetavayalogin ID
		//Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithSpecialCharactersasAvayaLoginID(userOnBoardingDetails), "Verify Import User with Special Characters as AvayaLoginID Assertion Failed");//Bug:Records are created with special charector avayaLoginID
	}

	@Test(groups = { "Maker" },priority=23)//,dependsOnMethods="VerifyImportUserwithInvalidMandatoryFields",description="To Revert Imported Records")
	public void VerifyRevertAllImportedSupervisorRecords() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectallImportedRecord();
		userOnBoardingPage.Revert("revert");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Reverted"),"Approval Status Details Failed");
	}


	@Test(groups= {"Maker"},priority=24,description="Verify Import Valid UserDetails ChannelCount and Features To APPROVE")
	public void VerifyImportValidUserDetailsChannelCountandFeaturesToAPPROVE() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Valid UserDetails ChannelCount and Features To APPROVE Assertion Failed");
	}

	@Test(groups = { "Maker" },priority=25,dependsOnMethods="VerifyImportValidUserDetailsChannelCountandFeaturesToAPPROVE",description="To Verify AuditTrail Data for AddNewUserOnBoardingRecord ")
	public void VerifyAuditTrailDataForImportedUserOnBoardingRecord() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		Assert.assertTrue(userOnBoardingPage.verifyAuditTrail(userOnBoardingDetails, "MakerImport", "New"), "Audit trail details failed");
	}

	@Test(groups = { "Maker" },priority=26,dependsOnMethods="VerifyAuditTrailDataForImportedUserOnBoardingRecord",description="To Verify SendForApproval for Imported UserOnBoarding ")
	public void VerifySendForApprovalForImportrdserOnBoarding() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}


	@Test(groups = { "Checker" },priority=27,dependsOnMethods="VerifySendForApprovalForImportrdserOnBoarding",description="To Verify Checker Approve for Imported UserOnBoardingRecord")
	public void ApproveforAddNewUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonApprove("Approve Create");
		Assert.assertTrue(userOnBoardingPage.verifyMessage());
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Create"));
	}


	@Test(groups= {"Maker"},priority=28,description="Edited Record to Verify Revert")
	public void EditImportedUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.editUserOnBoardingRecord(UserOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.verifySuccessMessage(), "Record Updated Successfully");
	}

	@Test(groups = { "Maker" },priority=29)//,dependsOnMethods="EditUserOnBoardingRecord",description="To Verify AuditTrailData for Edited UserOnBoardingRecord ")
	public void VerifyAuditTrailDataForEditUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		Assert.assertTrue(userOnBoardingPage.verifyAuditTrailUpdate(UserOnBoardingDetails, "MakerUpdate", "New"), "Audit trail details failed");
	}

	@Test(groups = { "Maker" },priority=30,dependsOnMethods="VerifyAuditTrailDataForEditUserOnBoardingRecord",description="To Verify VerifySendForApprovalForEditRejectRecordforApprove")
	public void VerifySendForApprovalForEditedRecordforApprove() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approal status details failed");
	}

	@Test(groups = { "Checker" },priority=36,dependsOnMethods="VerifySendForApprovalForEditedRecordforApprove",description="To Verify ApproveforEditedUserOnBoardingRecord")
	public void ApproveforEditedUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage=PageFactory.createPageInstance(driver,UserOnBoardingPage.class);
		userOnBoardingPage.clickonApprove("Approve Edit");
		Assert.assertTrue(userOnBoardingPage.verifyMessage(),"Approve record assertion failed");
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Edit"));
	}

	@Test(groups= {"Maker"},priority=31,description="To Verify DeleteUserOnBoardingRecord ")
	public void DeleteUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.deleteUserOnBoardingRecord(UserOnBoardingDetails.getLanID(), UserOnBoardingDetails.getDeleteReason());
		Assert.assertEquals(userOnBoardingPage.getSuccessMessage(), "Record Deleted Successfully");
	}

	@Test(priority=32,groups = { "Maker" },dependsOnMethods="DeleteUserOnBoardingRecord",description="To VerifyAuditTrailDataForDeleteUserOnBoardingRecord ")
	public void VerifyAuditTrailDataForDeleteUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		Assert.assertTrue(userOnBoardingPage.verifyAuditTrailDelete(UserOnBoardingDetails, "MakerDelete", "New"), "Audit trail details failed");
	}

	@Test(groups = { "Maker" },priority=33,dependsOnMethods="VerifyAuditTrailDataForDeleteUserOnBoardingRecord",description="To VerifySendForApprovalForDeleteRecord ")
	public void VerifySendForApprovalForDeleteRecord() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}

	@Test(priority=34,groups = { "Checker" },dependsOnMethods="VerifySendForApprovalForDeleteRecord",description="To Verify ApproveforDeleteUserOnBoardingRecord ")
	public void ApproveforDeleteUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonApprove("Approve Deleted");
		Assert.assertTrue(userOnBoardingPage.verifyMessage(),"Approve record assertion failed");
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
	}

















	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("UserOnBoardingImportUserTest",method.getName());
		driver.navigate().refresh();
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.userLogout();
		driver.close();
		System.out.println("Completed Executing : "+method.getName());
	}
}
