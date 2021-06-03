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

public class UserOnBoardingImportUserTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
    public void NavigateToNewUserOnBoardingPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToUserOnBoardingPage();
        UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
        Assert.assertTrue(userOnBoardingPage.isUserOnBoardingPageDisplayed(), "NavigateToNewUserOnBoardingPage Page assertion failed");
    }

	@Test(priority=1,description="To Verify Import User PopUp")
	public void VerifyImportUserPopUP() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserPopUp(), "Verify Import UserPopUP assertion failed");
	}

	@Test(priority=2,description="To Verify Import User with Each Mandatory Field Blank")
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

	@Test(priority=3,description="Verify Import User with ALL the Fields Blank")
	public void VerifyImportUserwithALLtheFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(1);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithAllFieldsBlank(userOnBoardingDetails), "Verify Import User with All the Fields Blank Assertion Failed");
	}

	@Test(priority=4,description="Verify Import User with Only One File at Time")
	public void VerifyImportUserwithOnlyOneFileatTime() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithOnlyOneFileatTime(userOnBoardingDetails), "Verify Import User with Only One File at Time Assertion Failed");
	}

	@Test(priority=5,description="To Verify Import User with Non Mandatory Fields Blank")
	public void VerifyImportUserwithNonMandatoryFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithAllNonMandatoryFieldsBlank(userOnBoardingDetails), "Verify Import User with All Non Mandatory Fields Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithAccessRoleandRoleFieldsBlank(userOnBoardingDetails), "Verify Import User with Access Role and Role Fields Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithCRMandTextChatFieldsBlank(userOnBoardingDetails), "Verify Import User with CRM and TextChat Fields Blank Assertion Failed");
	}

	@Test(priority=6,dependsOnMethods="VerifyImportUserwithNonMandatoryFieldsBlank",description="To  Revert Imported Records")
	public void VerifyRevertAllNonMandatoryRecords() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectallNonmandatoryFieldsRecord();
		userOnBoardingPage.Revert("revert");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Reverted"),"Approval Status Details Failed");
	}

	@Test(priority=7,description="Verify Import User with Duplicate AvayaLoginId")
	public void VerifyImportUserwithDuplicateAvayaLoginId() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(3);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import User with Duplicate AvayaLoginId Assertion Failed");
	}

	//@Test(priority=8,description="Verify Import User with DuplicateLanId")//Bug Record is Inserted with duplicate LanID[PRDOCM-64134]
	public void VerifyImportUserwithDuplicateLanId() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(4);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import User with DuplicateLanId Assertion Failed");
	}

	@Test(priority=9,description="To Verify Import Supervisor with EachLevel")
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

	@Test(priority=10)//,dependsOnMethods="VerifyImportSupervisorwithEachLevel",description="To  Revert Imported Records   ")
	public void VerifyRevertAllSupervisorRecords() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectallSupervisorRecord();
		userOnBoardingPage.Revert("revert");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Reverted"),"Approval Status Details Failed");
	}

	//@Test(priority=11,description="Verify Import Users with Contry OrgUnit with Div Supervisor")//Bug Importing is successful 
	public void VerifyImportUserswithCountryOrgUnitwithDivSupervisor() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(5);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Users with Contry OrgUnit with Div Supervisor Assertion Failed");
	}

	//@Test(priority=12,description="Verify Import User with Comma Separated Values InRoleColumn")//Bug Failed to Import
	public void VerifyImportUserwithCommaSeparatedValuesInRoleColumn() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(6);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import User with CommaSeparated Values In Role Column Assertion Failed");
	}

	//@Test(priority=13,description="Verify Import Valid  Multiple UserDetails ChannelCount and Features")//Revert and Reject is not happening for the Multiple Imported Records
	public void VerifyImportValidMultipleUserDetailsChannelCountandFeatures() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(7);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify ImportValid Multiple UserDetails ChannelCount and Features Assertion Failed");
	}

	//@Test(priority=14,description="Verify Import Multiple UserDetails ChannelCount and Features with DuplicateLanID")//Bug:Importing is successfully with duplicate Lan ID
	public void VerifyImportMultipleUserDetailsChannelCountandFeatureswithDuplicateLanID() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(8);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Multiple UserDetails ChannelCount and FeatureswithDuplicateLanID Assertion Failed");
	}

	//@Test(priority=15,description="Verify Import Multiple UserDetails ChannelCount and Features with DuplicateAvayaLoginID")
	public void VerifyImportMultipleUserDetailsChannelCountandFeatureswithDuplicateAvayaLoginID() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(9);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Multiple UserDetails ChannelCount and Features with DuplicateAvayaLoginID Assertion Failed");
	}

	//@Test(priority=16,description=" To Verify Import Multiple User with Each MandatoryFieldsBlank")
	public void VerifyImportMultipleUserwithEachMandatoryFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(10);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportMultipleUserwithFirstNameBlank(userOnBoardingDetails), "Verify Import Multiple User with FirstName Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportMultipleUserwithAllFieldsBlank(userOnBoardingDetails), "Verify Import Multiple User with AllFields Blank Assertion Failed");
	}

	@Test(priority=17,description="To Verify Import with Invalid Scenarios")
	public void VerifyImportwithInvalidScenarios() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserDetailswithInvalidOrgUnit(userOnBoardingDetails), "Verify Import User with Invalid Org Unit Assertion Failed");
		//Assert.assertTrue(userOnBoardingPage.VerifyImportUserDetailswithNewColumn(userOnBoardingDetails), "Verify Import User with New Column Assertion Failed");//Bug:Import is successfull
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserDetailswithUpdatedColumn(userOnBoardingDetails), "Verify Import User with Updated column name Assertion Failed");
	}

	//@Test(priority=18,description="To Verify Import User Profile Pictures with out Attribute")//Bug:Record is Imported successfully
	public void VerifyImportSupervisorasNAwithEachLevel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(11);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportwithoutDivisionLevelSupervisor(userOnBoardingDetails), "Verify Import User with Access Role and Role Fields Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportwithoutDepartmentlevelSupervisor(userOnBoardingDetails), "Verify Import User with CRM and TextChat Fields Blank Assertion Failed");
		Assert.assertTrue(userOnBoardingPage.VerifyImportwithoutTeamLevelSupervisor(userOnBoardingDetails), "Verify Import User with CRM and TextChat Fields Blank Assertion Failed");
	}

	@Test(priority=19,description="Verify Import Valid Agent with TeamLevelSupervisor")
	public void VerifyImportValidAgentwithTeamLevelSupervisor() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(12);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Valid Agent with TeamLevelSupervisor Assertion Failed");
	}

	//@Test(priority=20,description="Verify Import User with Channel Disabled with Count and Features")//Bug:Import is successfull
	public void VerifyImportUserwithChannelDisabledwithCountandFeatures() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(13);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify ImportUser with Channel Disabled with Count and Features Assertion Failed");
	}

	@Test(priority=21,description="Verify Import Agent with Invalid Mandatory Fields")
	public void VerifyImportAgentwithInvalidMandatoryFields() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(14);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithInvalidTextChatGreetingFiles(userOnBoardingDetails), "Verify Import User with Invalid TextChatGreeting Files Assertion Failed");
		//Assert.assertTrue(userOnBoardingPage.VerifyImportUserwithInvalidRoleFile(userOnBoardingDetails), "Verify Import User with Invalid Role File Assertion Failed");//Bug:Record is imorted but Record Count Displayed as Inserted and in Invalid
	}

	@Test(priority=22,description="Verify Import User with Invalid Mandatory Fields")
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

	@Test(priority=23)//,dependsOnMethods="VerifyImportUserwithInvalidMandatoryFields",description="To Revert Imported Records")
	public void VerifyRevertAllImportedSupervisorRecords() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectallImportedRecord();
		userOnBoardingPage.Revert("revert");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Reverted"),"Approval Status Details Failed");
	}




	@AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("UserOnBoardingImportUserTest",method.getName());
	        driver.navigate().refresh();
	}
}
