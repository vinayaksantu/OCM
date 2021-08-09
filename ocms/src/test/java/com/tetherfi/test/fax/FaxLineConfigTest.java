package com.tetherfi.test.fax;

import com.tetherfi.model.fax.FaxAutoACKConfigurationDetails;
import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.FaxRoutingConfigurationDetails;
import com.tetherfi.model.fax.FaxSendersDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.FaxAutoACKConfigurationPage;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxRoutingConfigurationPage;
import com.tetherfi.pages.FaxSendersPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
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

public class FaxLineConfigTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	@BeforeClass
	public void NavigateToFaxLineConfigPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("FAX");
		FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
		Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
		faxPage.navigateToFaxLineConfigPage();
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.isFaxLineConfigPageDisplayed(), "FAX page assertion failed");
	}

	@Test(priority=1)
	public void FaxLineConfigPage() {
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifylogo(),"FaxLineConfig logo assertion failed");
		Assert.assertTrue(faxLineConfigPage .maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("FaxLineConfigTest","maximize window");
		Assert.assertTrue(faxLineConfigPage .minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("FaxLineConfigTest","minimize window");
	}

	@Test(priority=2)
	public void AddFaxLineConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.AddCancelRecord(faxLineConfigDetails));
		faxLineConfigPage.addNewFaxLineConfigRecord(faxLineConfigDetails);
		Assert.assertEquals(faxLineConfigPage.getSuccessMessage(), "Record Created Successfully");
	}

	@Test(priority=3)
	public void AddFaxLineConfigRecord1() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.AddCancelRecord(faxLineConfigDetails));
		faxLineConfigPage.addNewFaxLineConfigRecord(faxLineConfigDetails);
		Assert.assertEquals(faxLineConfigPage.getSuccessMessage(), "Record Created Successfully");
	}

	@Test(priority=4)
	public void SendersLink() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifySendersLink(faxLineConfigDetails));
		String filePath1 = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1, "Create").getTestData().get(5);
		FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map1);
		FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
		faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
		Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Created Successfully");
	}

	@Test(priority=5)
	public void AutoAckLink() throws Exception {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("FAX");
		FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
		faxPage.navigateToFaxLineConfigPage();
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifyAutoAckLink(faxLineConfigDetails));
		String filePath1 = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1, "Create").getTestData().get(2);
		FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map1);
		FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
		faxAutoAckConfigurationPage.addNewFaxAutoAckConfigRecord(faxAutoAckConfigurationDetails);
		Assert.assertEquals(faxAutoAckConfigurationPage.getSuccessMessage(), "Record Created Successfully");
	}

	@Test(priority=6)
	public void RoutesLink() throws Exception {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("FAX");
		FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
		faxPage.navigateToFaxLineConfigPage();
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifyRoutesLink(faxLineConfigDetails));
		String filePath1 = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1, "Create").getTestData().get(2);
		FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map1);
		FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
		Assert.assertTrue(faxRoutingConfigurationPage.AddCancelRecord(faxRoutingConfigDetails));
		faxRoutingConfigurationPage.addNewFaxRoutingConfigRecord(faxRoutingConfigDetails);
		Assert.assertEquals(faxRoutingConfigurationPage.getSuccessMessage(), "Record Created Successfully"); 
	}

	@Test(priority=7)
	public void AddDuplicateFaxLineConfigRecord() throws Exception {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("FAX");
		FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
		faxPage.navigateToFaxLineConfigPage();
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.addNewFaxLineConfigRecord(faxLineConfigDetails);
		Assert.assertEquals(faxLineConfigPage.getSuccessMessage(), "Record Creation Failed, Already Exist"); 
	}

	@Test(priority=8)
	public void AddInvalidFaxLineConfigRecordWithoutInput() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.addNewFaxLineConfigRecordwithoutInput(faxLineConfigDetails);
		Assert.assertFalse(faxLineConfigPage.getErrorMessage());
	}

	@Test(priority=9)
	public void AddInvalidFaxLineConfigRecordWithoutFaxLine() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.addNewFaxLineConfigRecordwithoutFaxline(faxLineConfigDetails);
		Assert.assertFalse(faxLineConfigPage.getErrorMessage());
	}

	@Test(priority=10)
	public void AddInvalidFaxLineConfigRecordWithoutFaxLineName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.addNewFaxLineConfigRecordwithoutFaxLineName(faxLineConfigDetails);
		Assert.assertFalse(faxLineConfigPage.getErrorMessage());
	}

	@Test(priority=11)
	public void AddInvalidFaxLineConfigRecordWithoutDescription() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.addNewFaxLineConfigRecordwithoutDescription(faxLineConfigDetails);
		Assert.assertFalse(faxLineConfigPage.getErrorMessage());
	}
	@Test(priority=12)
	public void AddInvalidFaxLineConfigRecordWithoutStatus() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.addNewFaxLineConfigRecordwithoutStatus(faxLineConfigDetails);
		Assert.assertFalse(faxLineConfigPage.getErrorMessage());
	}
	@Test(priority=13)
	public void AddInvalidFaxLineConfigRecordWithoutSendStatus() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.addNewFaxLineConfigRecordwithoutSendStatus(faxLineConfigDetails);
		Assert.assertFalse(faxLineConfigPage.getErrorMessage());
	}

	@Test(priority=14)
	public void AddInvalidFaxLineConfigRecordWithoutRecieveStatus() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.addNewFaxLineConfigRecordwithoutReceiveStatus(faxLineConfigDetails);
		Assert.assertFalse(faxLineConfigPage.getErrorMessage());
	}

	@Test(priority=15)
	public void EditFaxLineConfigCancelRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.editcancel(faxLineConfigDetails));
	}

	@Test(priority=16)
	public void EditInvalidFaxLineConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.editInvalidFaxLineConfig(faxLineConfigDetails);
		Assert.assertFalse(faxLineConfigPage.getErrorMessage());
	}

	@Test(priority=17)
	public void EditFaxLineConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.editFaxLineConfig(faxLineConfigDetails);
		Assert.assertEquals(faxLineConfigPage.getSuccessMessage(),"Record Updated Successfully");
	}

	@Test(priority=18)
	public void searchPage() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertFalse(faxLineConfigPage.clearAll(faxLineConfigDetails),"ClearAll Assertion Failed");
		screenshot.captureScreen("FaxLineConfigTest", "clearall");
		Assert.assertTrue(faxLineConfigPage.verifyclose());
		screenshot.captureScreen("FaxLineConfigTest", "SearchClose");
	}

	@Test(priority=19)
	public void searchwithoutSearchTextbox() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.searchwithoutextsearch(faxLineConfigDetails);
		Assert.assertFalse(faxLineConfigPage.getErrorMessage());
	}

	@Test(priority=20)
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		Assert.assertTrue(faxLineConfigPage.verifyDatabase(faxLineConfigDetails.getQuery()));
	}

	@Test(priority=21)
	public void DeleteCancelFaxLineConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.deletecancelRecord(faxLineConfigDetails));
	}

	@Test(priority=22)
	public void DeleteFaxLineConfigRecordWithoutReason() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.deleteFaxLineConfigwithoutReason(faxLineConfigDetails);
		Assert.assertFalse(faxLineConfigPage.getErrorMessage());

	}

	@Test(dependsOnMethods = {"EditFaxLineConfigRecord"},priority=23)
	public void DeleteFaxLineConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.deleteFaxLineConfig(faxLineConfigDetails);
		Assert.assertEquals(faxLineConfigPage.getSuccessMessage(),"Record Deleted Successfully");
	}


	@Test(priority=24)
	public void SearchClearSearch() throws Exception
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifyinvalidsearchwithwrongdata(faxLineConfigDetails),"invalidsearchwithwrongdata");
		screenshot.captureScreen("FaxLineConfigTest","Invalid Search with wrong data");
		Assert.assertTrue(faxLineConfigPage.verifyclearsearch(), "Clear All Assertion Failed");
	}

	@Test(priority=25)
	public void ExportToExcel() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifyExportToExcel(filePath));
	}

	@Test(priority=26)
	public void ExportToExcelData() throws Exception
	{	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Line Config.xlsx";
	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
	Assert.assertTrue(faxLineConfigPage.verifyexportToExcelSheet(maplist));	
	}

	@Test(priority=27)
	public void ExporttoExcelWithoutData() throws Exception
	{
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		Assert.assertTrue(faxLineConfigPage.ExporttoExcelWithoutData(faxLineConfigDetails));
	}

	@Test(priority=28)
	public void SortingByAscending() throws IOException {
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.SortByAscending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Line Config (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(faxLineConfigPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=29)
	public void SortingByDescending() throws IOException {
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		faxLineConfigPage.SortByDescending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Line Config (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(faxLineConfigPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=30)
	public void GroupBy()
	{
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.groupby());
		screenshot.captureScreen("FaxLineConfigTest", "GroupBy");
		Assert.assertTrue(faxLineConfigPage.groupby());
		screenshot.captureScreen("FaxLineConfigTest", "AlreadyGroupBy");
	}

	@Test(priority=31)
	public void VerifyArrowMoveForPreviousAndNextPage() {
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}

	@Test(priority=32)
	public void VerifyArrowMoveForFirstAndLastPage() {
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=33)
	public void VerifyTotalNumberOfItemsPerPageDetails() {
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=34)
	public void VerifyNumberOfItemsPerPageSelection() {
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}

	@Test(priority=35)
	public void VerifyDropdownForAllTheColumns() {
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=36)
	public void VerifyColumnsHeaderEnable() {
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertTrue(faxLineConfigPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=37)
	public void VerifyColumnsHeaderDisable() {
		FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
		Assert.assertFalse(faxLineConfigPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=38)
	public void VerifyAuditTrialReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyFaxLineConfigCreate(faxLineConfigDetails,"Create"));
	}

	@Test(dependsOnMethods="EditFaxLineConfigRecord",priority=39)
	public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyFaxLineConfigUpdate(faxLineConfigDetails,"Update"));
	}

	@Test(priority=40)
	public void VerifyAuditTrialReportForDelete() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyFaxLineConfigdelete(faxLineConfigDetails,"Delete"));
	}

	@AfterMethod
	public void afterEachMethod(Method method) {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("FaxLineConfigTest",method.getName());
		driver.navigate().refresh();    }
}

