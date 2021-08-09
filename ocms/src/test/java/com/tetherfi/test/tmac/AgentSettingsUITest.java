package com.tetherfi.test.tmac;

import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.model.user.UserOnBoardingDetails;
import com.tetherfi.pages.AgentSettingsNewDesignPage;
import com.tetherfi.pages.AgentSettingsPageWMC;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.pages.UserOnBoardingPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.FTPServer;
import com.tetherfi.utility.JSONReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class AgentSettingsUITest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
	public void NavigateToAgentSettingsPage() throws Exception {
		HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
		tmacPage.navigateToAgentSettingsPage();
		AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
		Assert.assertTrue(agentSettingsPage.isAgentSettingsPageDisplayed(),"Agent Settings page assertion failed");
		driver.navigate().refresh();
	}

	@Test(priority=1)
	public void VerifyAgentSettingsModuleDisplay() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		Assert.assertEquals(agentSettingsPage.getHeaderText(), "Agent Settings", "Agent settings Module text assertion failed");
		Assert.assertTrue(agentSettingsPage.verifySearchLink(), "search link assertion failed");
		Assert.assertTrue(agentSettingsPage.verifyClearSearchLink(), "clear search link assertion failed");
		Assert.assertTrue(agentSettingsPage.verifyFullScreenLink(), "full screen link assertion failed");
		Assert.assertTrue(agentSettingsPage.verifyAgentSettingsTabsDisplayed(), "Agent Settings tab assertion failed");
	}

	@Test(priority=2)
	public void VerifyAgentSettingsApprovedDataPage() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		Assert.assertTrue(agentSettingsPage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
	}

	@Test(priority=3)
	public void VerifyAgentSettingsAuditTrailDataPage() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		Assert.assertTrue(agentSettingsPage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
	}

	@Test(priority=4)
	public void VerifyMakeAgentSettingsChangeButton() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifyAddNewAgentSettingsRecordButton(), "add new Agent Settings record button assertion failed");
		Assert.assertTrue(agentSettingsPage.verifyGoBackButton(), "Go back button assertion failed");
		Assert.assertTrue(agentSettingsPage.verifyExportToExcelButton(), "export to excel button assertion failed");
	}

	@Test(priority=5)
	public void VerifyAddNewAgentSettingsButton() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		agentSettingsPage.selectAddNewAgentSettings();
		Assert.assertTrue(agentSettingsPage.verifyAddNewPopupContents(),"Add new pop up content assertion failed");
	}

	@Test(priority=6,description="To Verify Search Page ")
	public void searchPage() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		Assert.assertFalse(agentSettingsPage.clearAll(agentSettingsDetails),"ClearAll Assertion Failed");
		screenshot.captureScreen("AgentSettingsUITest", "clearall");
		Assert.assertTrue(agentSettingsPage.verifyclose());
		screenshot.captureScreen("AgentSettingsUITest", "SearchClose");
	}

	@Test(priority=7,description="To Verify search without SearchTextbox ")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.searchwithoutextsearch(agentSettingsDetails);
		Assert.assertFalse(agentSettingsPage.getErrorMsg());
	}

	@Test(priority=8,description="To Verify SearchClearSearch ")
	public void SearchClearSearch() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		Assert.assertTrue(agentSettingsPage.verifyApprovedSectionData(agentSettingsDetails ),"invalidsearchwithwrongdata");
		screenshot.captureScreen("UserOnBoardingUITest","Invalid Search with wrong data");
		Assert.assertTrue(agentSettingsPage.verifyclearsearch(), "Clear All Assertion Failed");
	}

	@Test(priority=9)
	public void VerifyFullScreenButton() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifyPageFullScrnd(),"full screen assertion failed");
	}

	@Test(priority=10)
	public void VerifyExportToExcel() {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifyExportToExcel(filePath),"export to excel assertion failed");
	}

	//@Test(priority=11,description="To Verify Exported data  ")//Drill Down Data is exporting
	public void ExportToExcelData() throws Exception{	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Settings.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifyexportToExcelSheet(maplist));	
	}

	@Test(priority=12,description="To Verify Export To Excel WithoutData ")
	public void VerifyExportToExcelWithoutData() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.ExporttoExcelWithoutData(agentSettingsDetails));
	}

	@Test(priority=13)
	public void VerifyDragAndDrop() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		agentSettingsPage.dragColumntoGroup("Lan ID");
		Assert.assertTrue(agentSettingsPage.verifyDragColumntoGroup("Lan ID"),"drag and drop assertion failed");
	}

	@Test(priority=14)
	public void VerifyDragAndDropofAlreadyGroupedHeader() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		agentSettingsPage.dragColumntoGroup("Lan ID");
		agentSettingsPage.dragColumntoGroup("Lan ID");
		Assert.assertTrue(agentSettingsPage.verifyDragColumntoGroup("Lan ID"),"drag and drop assertion failed");
	}

	@Test(priority=15)
	public void VerifyDropdownForAllTheColumns() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=16)
	public void VerifyColumnsHeaderEnable() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=17)
	public void VerifyColumnsHeaderDisable() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertFalse(agentSettingsPage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
	}

	@Test(priority=18)
	public void VerifyArrowMoveForPreviousAndNextPage() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
	}

	@Test(priority=19)
	public void VerifyArrowMoveForFirstAndLastPage() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
	}

	@Test(priority=20)
	public void VerifyTotalNumberOfItemsPerPageDetails() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
	}

	@Test(priority=21)
	public void VerifyNumberOfItemsPerPageSelection() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
	}

	@Test(priority=22)
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(3);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifySearchIsNotEqualTo(agentSettingsDetails.getFirstname()));
	}

	@Test(priority=22)
	public void VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(4);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifySearchContains(agentSettingsDetails.getFirstname()));
	}

	@Test(priority=23)
	public void VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(5);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifySearchDoesNotContains(agentSettingsDetails.getFirstname()));
	}

	@Test(priority=24)
	public void VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(6);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifySearchStartsWith(agentSettingsDetails.getFirstname()));
	}

	@Test(priority=25)
	public void VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(7);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		Assert.assertTrue(agentSettingsPage.verifySearchEndsWith(agentSettingsDetails.getFirstname()));
	}

	@Test(priority=26)
	public void VerifyMandatoryFieldWarningMessage() {
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.selectAgentSettingsAuditTrailTab();
		agentSettingsPage.selectMakeAgentSettingsChanges();
		agentSettingsPage.selectAddNewAgentSettings();
		agentSettingsPage.clickOnSave();
		Assert.assertEquals(agentSettingsPage.VerifyMessage(),"Please Provide Lan ID, Avaya Login ID, First Name, Last Name, Org. Unit, Profile, Supervisor", "Mandatory field record assertion failed");
	}

	@Test(priority=27)
	public void VerifyAddRecordWithoutLanID() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.addNewAgentSettingsRecordWithOutLanID(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.VerifyMessage(), "Please Provide Lan ID","Record Creation Assertion failed");
	}

	@Test(priority=28)
	public void VerifyAddRecordWithoutAvayaLoginID() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.addNewAgentSettingsRecordWithOutAvayaLoginID(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.VerifyMessage(), "Please Provide Avaya Login ID","Record Creation Assertion failed");
	}

	@Test(priority=29)
	public void VerifyAddRecordWithoutFirstName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.addNewAgentSettingsRecordWithoutFirstName(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.VerifyMessage(), "Please Provide First Name","Record Creation Assertion failed");
	}

	@Test(priority=31)
	public void VerifyAddRecordWithoutLastName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.addNewAgentSettingsRecordWithoutLastName(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.VerifyMessage(), "Please Provide Last Name","Record Creation Assertion failed");
	}

	@Test(priority=31)
	public void VerifyAddRecordWithoutOrgUnitProfileandSupervisor() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.addNewAgentSettingsRecordWithoutOrgUnitProfileAndSupervisor(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.VerifyMessage(), "Please Provide Org. Unit, Profile, Supervisor","Record Creation Assertion failed");
	}









	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen( "AgentSettingsUITest",method.getName());
		driver.navigate().refresh();
	}
}
