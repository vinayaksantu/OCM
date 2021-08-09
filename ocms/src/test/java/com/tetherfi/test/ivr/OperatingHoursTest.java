package com.tetherfi.test.ivr;

import com.tetherfi.model.ivr.OperatingHoursDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class OperatingHoursTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	@BeforeClass
	public void NavigateToOperatingHoursPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("IVR");
		IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
		Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
		screenshot.captureScreen("IVR Page", "OperatingHoursTest");
		ivrPage.navigateToOperatingHoursPage();
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.isOperatingHoursPageDisplayed(), "Operating hours page assertion failed");
		screenshot.captureScreen("OperatingHoursTest","Operating Hours Page");
	}

	@Test(priority=1)
	public void OperatingHoursPage() {
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		Assert.assertTrue( operatingHoursPage.verifylogo(),"Operating Hours logo assertion failed");
		Assert.assertTrue(operatingHoursPage .maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OperatingHoursTest","maximize window");
		Assert.assertTrue(operatingHoursPage .minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OperatingHoursTest","minimize window");
	}

	@Test(priority=2)
	public void AddNewOperatingHoursRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.addnewOperatingHoursCancel(operatingHoursDetails), "cancel assertion failed");
		screenshot.captureScreen("OperatingHoursTest","Add Cancel");
		operatingHoursPage.addNewOperatingHoursRecord(operatingHoursDetails);
		Assert.assertTrue(operatingHoursPage.verifyNewRecordCreated(), "Add New record assertion failed");
	}

	@Test(priority=3)
	public void AddAlldaysOperatingHoursRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		operatingHoursPage.addNewAllDaysOperatingHoursRecord(operatingHoursDetails);
		Assert.assertEquals(operatingHoursPage.getSuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
	}

	@Test(priority=4)
	public void AddLeavingFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		operatingHoursPage.LeavingAllFieldsBlank(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
	}

	@Test(priority=5)
	public void AddLeavingVDNBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		operatingHoursPage.LeavingVDNBlank(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
	}

	@Test(priority=6)
	public void AddLeavingWeekDayFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		operatingHoursPage.LeavingWeekDayBlank(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
	}

	@Test(priority=7)
	public void AddLeavingStartTimeFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		operatingHoursPage.LeavingStartTimeBlank(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
	}

	@Test(priority=8)
	public void AddLeavingEndTimeFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);	
		operatingHoursPage.LeavingEndTimeBlank(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
	}

	@Test(priority=9)
	public void AddLeavingBypassPublicHolidayFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);	
		operatingHoursPage.LeavingBypassPublicHolidayBlank(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
	}

	@Test(priority=10)
	public void AddDuplicateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		operatingHoursPage.addNewAllDaysOperatingHoursRecord(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg(), "Add New record assertion failed");
	}

	@Test(priority=11)
	public void AddInvalidVDNRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(5);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		operatingHoursPage.addVDNvaluelessthanfour(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg(), "Add New record assertion failed");
	}

	@Test(priority=12)
	public void AddInvalidStartTimeRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		operatingHoursPage.addStarttimegreaterthanEndtime(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg(), "Add New record assertion failed");
	}

	@Test(priority=13)
	public void AddInvalidStartAndEndTimeRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath, "Create").getTestData().get(3);
		OperatingHoursDetails operatingHoursDetails1 = new OperatingHoursDetails(map1);
		OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
		operatingHoursPage.addStarttimesameasEndtime(operatingHoursDetails1);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg(), "Add New record assertion failed");
	}

	@Test(priority=14)
	public void EditOperatingHoursCancelRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.editOperatingHoursCancelbtn(operatingHoursDetails));
	}
	
	@Test(priority=15)
	public void EditRecordWithoutReason() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		operatingHoursPage.editRecordWithoutReason(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg(),"Edit record assertion failed");
	}

	@Test(priority=16)
	public void EditOperatingHoursRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		operatingHoursPage.editOperatingHoursRecord(operatingHoursDetails);
		Assert.assertTrue(operatingHoursPage.verifyRecordUpdated(),"Edit record assertion failed");
	}

	@Test(priority=16)
	public void EditInvalidRecord() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		operatingHoursPage.editInvalidRecord(operatingHoursDetails);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg(),"Edit record assertion failed");
		screenshot.captureScreen("OperatingHoursTest","Time is Same");
		driver.navigate().refresh();
		Map<String, String> map1 = new ExcelReader(filePath,"Edit").getTestData().get(2);
		OperatingHoursDetails operatingHoursDetails1=new OperatingHoursDetails(map1);
		operatingHoursPage.editInvalidRecord(operatingHoursDetails1);
		Assert.assertFalse(operatingHoursPage.verifyErrorMsg(),"Edit record assertion failed");
		screenshot.captureScreen("OperatingHoursTest","Start Time is greater than End Time");	
	}

	

	@Test(priority=18)
	public void searchPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertFalse(operatingHoursPage.clearAll(operatingHoursDetails),"ClearAll Assertion Failed");
		screenshot.captureScreen("OperatingHoursTest", "clearall");
		Assert.assertTrue(operatingHoursPage.verifyclose());
		screenshot.captureScreen("OperatingHoursTest", "SearchClose");
	}

	@Test(priority=19)
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(0);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifySearchIsNotEqualTo(operatingHoursDetails.getWeekDay()));
	}

	@Test(priority=20)
	public void VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifySearchContains(operatingHoursDetails.getWeekDay()));
	}

	@Test(priority=21)
	public void VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(2);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifySearchDoesNotContains(operatingHoursDetails.getWeekDay()));
	}

	@Test(priority=22)
	public void VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(3);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifySearchStartsWith(operatingHoursDetails.getWeekDay()));
	}

	@Test(priority=23)
	public void VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(4);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifySearchEndsWith(operatingHoursDetails.getWeekDay()));
	}

	@Test(priority=24)
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(0);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifyinvalidsearchwithwrongdata(operatingHoursDetails),"invalidsearchwithwrongdata");
		screenshot.captureScreen("OperatingHoursTest","Invalid Search with wrong data");
		Assert.assertTrue(operatingHoursPage.verifyclearsearch(), "Clear All Assertion Failed");
	}

	@Test(priority=25)
	public void ExportToExcel() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifyExportToExcel(filePath));
	}

	@Test(priority=26)
	public void ExportToExcelData() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Operating Hours.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifyexportToExcelSheet(maplist));	
	}

	@Test(priority=27)
	public void ExporttoExcelWithoutData() throws Exception{
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Search").getTestData().get(1);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		Assert.assertTrue(operatingHoursPage.ExporttoExcelWithoutData(operatingHoursDetails));
	}

	@Test(priority=28)
	public void SortingByAscending() throws IOException {
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		operatingHoursPage.SortByAscending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Operating Hours (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(operatingHoursPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=29)
	public void SortingByDescending() throws IOException {
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		operatingHoursPage.SortByDescending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Operating Hours (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(operatingHoursPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=30)
	public void GroupBy(){
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.groupby());
		screenshot.captureScreen("OperatingHoursTest", "GroupBy");
		Assert.assertTrue(operatingHoursPage.groupby());
		screenshot.captureScreen("OperatingHoursTest", "AlreadyGroupBy");
	}

	@Test(priority=31)
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}

	@Test(priority=32)
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=33)
	public void VerifyTotalNumberOfItemsPerPageDetails() {
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=34)
	public void VerifyNumberOfItemsPerPageSelection() {
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}

	@Test(priority=35)
	public void VerifyDropdownForAllTheColumns() {
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=36)
	public void VerifyColumnsHeaderEnable() {
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=37)
	public void VerifyColumnsHeaderDisable() {
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertFalse(operatingHoursPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=38)
	public void DeleteWithoutDeleteReason() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		Assert.assertTrue(operatingHoursPage.verifydeleteNo(operatingHoursDetails));
	}

	@Test(priority=39)
	public void DeleteOperatinghoursRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		operatingHoursPage.deleteOperatingHoursRecord(operatingHoursDetails);
		Assert.assertTrue(operatingHoursPage.verifyRecordDeleted(),"delete record assertion failed");
	}

	@Test(priority=40)
	public void database() throws Exception{
		OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
		OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
		Assert.assertTrue(operatingHoursPage.verifyDatabase(operatingHoursDetails.getQuery()));
	}

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OperatingHoursTest",method.getName());
		driver.navigate().refresh();
	}   

	
	
}

