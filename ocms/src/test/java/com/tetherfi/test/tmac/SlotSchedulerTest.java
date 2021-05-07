package com.tetherfi.test.tmac;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.SlotSchedulerDetails;
import com.tetherfi.model.tmac.TmacAuxCodesDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.SlotSchedulerPage;
import com.tetherfi.pages.TmacAuxCodesPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SlotSchedulerTest extends BaseTest {

	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod()
	public void NavigateToTmaxAuxCodesPage() {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HomePage Assertion Failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver, TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "Tmac Page Assertion Failed");
		tmacPage.navigateToSlotSchedulerPage();
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.isSlotScheduelrPageIsDisplayed(), "Tmac Aux Codes Assertion  failed");
	}

	@Test(priority=1,description="To Verify Presence of SlotScheduler Page")
	public void SlotSchedulerPage() {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifylogo(), "SlotScheduler Logo Assertion failed");
		Assert.assertTrue(slotschedulerPage.VerifySlotSchedulerPageButtonPresence(), "VerifySlotSchedulerPageButtonPresence Assertion failed");
		//Assert.assertTrue(slotschedulerPage.verifygridcontent(), "GridContent Assertion failed");
		Assert.assertTrue(slotschedulerPage.maximizewindow(), "Maximize Assertrion failed");
		screenshot.captureScreen("SlotSchedulerTest", "Maximize Window");
		Assert.assertTrue(slotschedulerPage.minimizewindow(), "Minimize Assertion failed");
		screenshot.captureScreen("SlotSchedulerTest", "Minimize Window");
	}

	@Test(priority=2,description="To Verify Presence of labels in Add SlotScheduler PopUp and Cancel Button")
	public void VerifyAddSlotSchedulerPopUpLabelsandCancelButton() throws Exception {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyAddSlotSchedulerPopUpLabelsandCancelButton(), "VerifyAddSlotSchedulerPopUpLabelsandCancelButton Assertion failed");
	}

	@Test(priority=3,description="To Verify Add SlotScheduler without SlotGroup")
	public void VerifyAddSlotSchedulerWithoutSlotGroup() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithoutSlotGroup(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Please Provide Slot Group", "VerifyAddSlotSchedulerWithoutSlotGroup Assertion Failed");
	}

	@Test(priority=4,description="To Verify Add SlotScheduler without SlotType")
	public void VerifyAddSlotSchedulerWithoutSlotType() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithoutSlotType(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Please Provide Slot Type", "VerifyAddSlotSchedulerWithoutSlotType Assertion Failed");
	}

	@Test(priority=5,description="To Verify Add SlotScheduler without StartOfWeek")
	public void VerifyAddSlotSchedulerWithoutStartOfWeek() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithoutStartOfWeek(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Enter Valid Start Date", "VerifyAddSlotSchedulerWithoutStartOfWeek Assertion Failed");
	}

	@Test(priority=6,description="To Verify Add SlotScheduler without EndOfWeek")
	public void VerifyAddSlotSchedulerWithoutEndOfWeek() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithoutEndOfWeek(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Enter Valid End Date", "VerifyAddSlotSchedulerWithoutEndOfWeek Assertion Failed");
	}

	@Test(priority=7,description="To Verify Add SlotScheduler without FromTime")
	public void VerifyAddSlotSchedulerWithoutFromTime() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithoutFromTime(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Enter a FromTime", "VerifyAddSlotSchedulerWithoutFromTime Assertion Failed");
	}

	@Test(priority=8,description="To Verify Add SlotScheduler without ToTime")
	public void VerifyAddSlotSchedulerWithoutToTime() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithoutToTime(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Enter a ToTime", "VerifyAddSlotSchedulerWithoutToTime Assertion Failed");
	}

	@Test(priority=9,description="To Verify Add SlotScheduler without SlotDescription")
	public void VerifyAddSlotSchedulerWithoutSlotDescription() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithoutSlotDescription(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Please Provide Slot Description", "VerifyAddSlotSchedulerWithoutSlotDescription Assertion Failed");
	}

	@Test(priority=10,description="To Verify Add SlotScheduler without Slot Group,SlotType and SlotDescription")
	public void VerifyAddSlotSchedulerWithoutSlotgroupSlotTypeandSlotDescription() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithoutSlotGroupSlotTypeandSlotDescription(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Please Provide Slot Group, Slot Type, Slot Description", "VerifyAddSlotSchedulerWithoutSlotgroupSlotTypeandSlotDescription Assertion Failed");
	}

	@Test(priority=11,description="To Verify Add SlotScheduler without SlotType and SlotDescription")
	public void VerifyAddSlotSchedulerWithoutSlotTypeandSlotDescription() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithoutSlotTypeandSlotDescription(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Please Provide Slot Type, Slot Description", "VerifyAddSlotSchedulerWithoutSlotTypeandSlotDescription Assertion Failed");
	}

	@Test(priority=12,description="To Verify Add SlotScheduler When FromTime and To Time are Same")
	public void VerifyAddSlotSchedulerWhenFromTimeandToTimeareSame() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(1);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifyAddSlotSchedulerWithValidData(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "FromTime is same as ToTime", "VerifyAddSlotSchedulerWhenFromTimeandToTimeareSame Assertion Failed");
	}

	@Test(priority=13,description="To Verify Deafult and RoundUpValue of Monday Field ")
	public void VerifyDeafultandRoundUpValueofMonday() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(2);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyDefaultValueOfMondayFieldandDecimalValueRoundUp(slotSchedulerDetails), "VerifyDeafultandRoundUpValueofMonday Assertion Failed");
	}

	@Test(priority=14,description="To Verify Deafult and RoundUpValue of Tuesday Field")
	public void VerifyDeafultandRoundUpValueofTuesday() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(2);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyDefaultValueOfTuesdayFieldandDecimalValueRoundUp(slotSchedulerDetails), "VerifyDeafultandRoundUpValueofMonday Assertion Failed");
	}

	@Test(priority=15,description="To Verify Deafult and RoundUpValue of Wednesday Field")
	public void VerifyDeafultandRoundUpValueofWednesday() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(2);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyDefaultValueOfWednesdayFieldandDecimalValueRoundUp(slotSchedulerDetails), "VerifyDeafultandRoundUpValueofMonday Assertion Failed");
	}

	@Test(priority=16,description="To Verify Deafult and RoundUpValue of Thursday Field")
	public void VerifyDeafultandRoundUpValueofThursday() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(2);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyDefaultValueOfThursdayFieldandDecimalValueRoundUp(slotSchedulerDetails), "VerifyDeafultandRoundUpValueofMonday Assertion Failed");
	}

	@Test(priority=17,description="To Verify Deafult and RoundUpValue of Friday Field")
	public void VerifyDeafultandRoundUpValueofFriday() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(2);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyDefaultValueOfFridayFieldandDecimalValueRoundUp(slotSchedulerDetails), "VerifyDeafultandRoundUpValueofMonday Assertion Failed");
	}

	@Test(priority=18,description="To Verify Deafult and RoundUpValue of Saturday Field")
	public void VerifyDeafultandRoundUpValueofSaturday() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(2);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyDefaultValueOfSaturdayFieldandDecimalValueRoundUp(slotSchedulerDetails), "VerifyDeafultandRoundUpValueofMonday Assertion Failed");
	}

	@Test(priority=19,description="To Verify Deafult and RoundUpValue of Sunday Field")
	public void VerifyDeafultandRoundUpValueofSunday() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(2);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyDefaultValueOfSundayFieldandDecimalValueRoundUp(slotSchedulerDetails), "VerifyDeafultandRoundUpValueofMonday Assertion Failed");
	}

	@Test(priority=20,description="To Verify Add SlotScheduler with All Valid Fields")
	public void VerifyAddSlotSchedulerWithAllValidFields() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithAllValidFields(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.verifySuccessMessage(), "Record Created Successfully", "Record Creation assertion Failed");
	}

	@Test(priority=21,dependsOnMethods="VerifyAddSlotSchedulerWithAllValidFields",description=" To verify AuditTrailReport For Slot Scheduler Create")
	public void verifyAuditTrailReportForCreate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map2);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifySlotSchedulerCreate(slotSchedulerDetails, "Create"), "Create Audit Trail Assertion Failed"); 
	}

	@Test(priority=22,description="To Verify GridData after Successfull RecordCreation")
	public void verifyGridDataafterSuccessfullRecordCreation() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Create").getTestData().subList(0, 1);
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifyGridData(maplist,slotSchedulerDetails), "Export Data Assertion failed");
	}

	@Test(priority=23,description="To Verify Add Duplicate SlotScheduler with All Valid Fields")
	public void VerifyAddDuplicateSlotSchedulerWithAllValidFields() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifySlotSchedulerWithAllValidFields(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Record already exists for selected range", "Duplicate Record Creation assertion Failed");
	}

	@Test(priority=24,description="To Verify Edit SlotScheduler without Modify Reason")
	public void VerifyEditslotSchedulerWithoutModifyReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifyEditSlotSchedulerWithoutModifyReason(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Please enter the modify reason", "Verify Edit slotScheduler Without ModifyReason assertion Failed");
	}

	@Test(priority=25,description="To Verify Edit slotScheduler With AllValidFields")
	public void VerifyEditslotSchedulerWithAllValidFields() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.VerifyEditSlotSchedulerWithValidFields(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.verifySuccessMessage(), "Record Updated Successfully", "Record Updation assertion Failed");
	}

	@Test(priority=26,dependsOnMethods="VerifyEditslotSchedulerWithAllValidFields",description="To verifyAuditTrailReportFor Slot Scheduler Update")
	public void verifyAuditTrailReportForUpdate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map2);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifySlotSchedulerUpdate(slotSchedulerDetails, "Update"), "Create Audit Trail Assertion Failed"); 
	}

	@Test(priority=27,description="To Verify ExportToExcelButton")
	public void ExportToExcelButton() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.ExportToExcelButton(filePath), "Export Button assertion failed");
	}

	@Test(priority=28,dependsOnMethods="ExportToExcelButton",description="To Verify verifyExportedData")
	public void verifyExportedData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Slot Scheduler.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=29,description="To verifyExportToExcelWithoutData")
	public void verifyExportToExcelWithoutData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyExportToExcelWithoutData(slotSchedulerDetails), "Export to Excel Without Data assertion failed");
	}

	@Test(priority=30,description="To Verify SortByAscending")
	public void verifySortByAscending() throws Exception {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.verifySortByAscending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Slot Scheduler (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(slotschedulerPage.verifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=31,description="To verifySortByDescending")
	public void verifySortByDescending() throws Exception {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.verifySortByDescending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Slot Scheduler (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(slotschedulerPage.verifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=32,description="To verify GroupBy")
	public void verifyGroupBy() {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("Slot Scheduler", "group By Column");
		Assert.assertTrue(slotschedulerPage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("Slot Scheduler", "Already grouped Coloumn");
	}

	@Test(priority=33,description="To verifyArrowMoveForPreviousAndNextPage")
	public void verifyArrowMoveForPreviousAndNextPage() {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifyArrowMoveForPreviousAndNextPage(),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=34,description="To verifyArrowMoveForFirstAndLastPage")
	public void verifyArrowMoveForFirstAndLastPage() {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifyArrowMoveForFirstAndLastPage(), "First and Last Page Assertion Failed");
	}

	@Test(priority=35,description="To verifyTotalNumberOfItemsPerPageDetails")
	public void verifyTotalNumberOfItemsPerPageDetails() {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifyTotalNumberOfItemsPerPageDetails(), "Number of Items per Page assertion Failed");
	}

	@Test(priority=36,description="To verifyNumberOfItemsPerPageSelection")
	public void verifyNumberOfItemsPerPageSelection() {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifyNumberOfItemsPerPage(), "Number of Items per Page Assertion Failed");
	}

	@Test(priority=37,description="To verifyDropDownForAllTheColumns")
	public void verifyDropDownForAllTheColumns() {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifyDropDownOfAllHeaders(), "Dropdown for all the Columns Assertion Failed");
	}

	@Test(priority=38,description="To verifyColoumnsHeader Enabled")
	public void verifyColoumnsHeaderEnabled() {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifycolumnsHeaderEnabled(), "Columns Headers Enabled Assertion Failed");
	}

	@Test(priority=39,description="To verifyColumnsHeader Disabled") 
	public void verifyColumnsHeaderDisabled() {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertFalse(slotschedulerPage.verifycolumnsHeaderDisabled(), "Columns Headers Disabled Assertion Failed");
	}


	@Test(priority=40,description="To verifyDeleteNoButton")
	public void verifyDeleteNoButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.DeleteCancel(slotSchedulerDetails), "Delete No Buuton Assertion failed");
	}

	@Test(priority=41,description="To verifyDeleteWithoutDeleteReason")
	public void verifyDeleteWithoutDeleteReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.DeleteRecordWithOutDeleteReason(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.VerifyMessage(), "Please enter the delete reason", "Verify Edit slotScheduler Without ModifyReason assertion Failed");
	}

	@Test(priority=42,description="To verify Delete Slot Scheduler Record")
	public void verifyDeleteRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		slotschedulerPage.DeleteRecord(slotSchedulerDetails);
		Assert.assertEquals(slotschedulerPage.verifySuccessMessage(), "Record Deleted Successfully", "Record Creation assertion Failed");
	}

	@Test(priority=43,dependsOnMethods="verifyDeleteRecord",description="To verifyAuditTrailReportForDelete")
	public void verifyAuditTrailReportForDelete() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map2);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifySlotSchedulerdelete(slotSchedulerDetails, "Delete"), "Create Audit Trail Assertion Failed"); 
	}

	@Test(priority=44,description="To Verify SearchIsNotEqualTo")
	public void SearchIsNotEqualTo() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifySearchIsnotEqualTo(slotSchedulerDetails.getSlotDescription()), "Search assertion Failed");
	}

	@Test(priority=45,description="To Verify SearchContains")
	public void SearchContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(2);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifySearchContains(slotSchedulerDetails.getSlotDescription()), "Search assertion Failed");
	}

	@Test(priority=46,description="To Verify SearchDoesNotContains")
	public void SearchDoesNotContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(2);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifySearchDoesNotContains(slotSchedulerDetails.getSlotDescription()), "Search assertion Failed");
	}

	@Test(priority=47,description="To Verify SearchStartsWith")
	public void SearchStartsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(3);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifySearchStartsWith(slotSchedulerDetails.getSlotDescription()), "Search assertion Failed");
	}

	@Test(priority=48,description="To Verify SearchEndsWith")
	public void SearchEndsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SlotSchedulerData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(4);
		SlotSchedulerDetails slotSchedulerDetails=new SlotSchedulerDetails(map);
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.verifySearchEndsWith(slotSchedulerDetails.getSlotDescription()), "Search assertion Failed");
	}
	
	@Test(priority=49,description="To Verify SlotOccupancyTab PresenceOfButtons")
	public void VerifySlotOccupancyTabPresenceOfButtons() {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyPresenceOfButtonsInSlotOccupencyTab(), "VerifySlotOccupancyTabPresenceOfButtons");
	}
	
	@Test(priority=50,description="To Verify SlotOccupancyTab PreviousButton")
	public void VerifySlotOccupancyTabPreviousButton() throws ParseException {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyPreviousButton(), "VerifySlotOccupancyTabPreviousButton");
	}
	
	@Test(priority=51,description="To Verify SlotOccupancyTab NextButton")
	public void VerifySlotOccupancyTabNextButton() throws ParseException {
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.VerifyNextButton(), "VerifySlotOccupancyTabNextButton");
	}
	
	@Test(priority=52,description="To Verify ExportPDF Button")
	public void VerifyExportPDFButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		SlotSchedulerPage slotschedulerPage=PageFactory.createPageInstance(driver, SlotSchedulerPage.class);
		Assert.assertTrue(slotschedulerPage.ExportPDFButton(filePath), "Export PDF Button assertion failed");
	}









	@AfterMethod()
	public void AfterEachMethod(Method method) {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("SlotSchedulerTest", method.getName());
		driver.navigate().refresh();
	}
}
