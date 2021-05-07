package com.tetherfi.test.tmac;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AttributesDetails;
import com.tetherfi.pages.AttributesPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AttributesTest extends BaseTest {

	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
	public void NavigateToAttributesPage() {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HomePage Assertion Failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver, TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "Tmac Page Assertion Failed");
		tmacPage.navigateToAttributesPage();
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.isAttributesPageIsDisplayed(), "Attributes PagenAssertion  failed");
	}

	@Test(priority=1,description="To Verify Attribute Page")
	public void VerifyAttributePage() {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.verifyLogoAndButtonPresence(), "AttributesTest Logo Assertion Failed");
		Assert.assertTrue(attributePage.maximizeWindow(), "fullScreen Assertion Failed");
		screenshot.captureScreen("AttributesTest", "maximize window");
		Assert.assertTrue(attributePage.minimizeWindow(), "Restore Assertion Failed");
		screenshot.captureScreen("AttributesTest", "minimize window");
		Assert.assertTrue(attributePage.VerifyAttributesPageHeader(), "AttributePage Headers Assertion Failed");
	}

	@Test(priority=2,description="To Verify DropDown Coloumns")
	public void verifyDropDownForAllTheColumns() {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.verifyDropDownOfAllHeaders(),"Columns DropDown Assertion failed");
	}

	@Test(priority=3,description="To Verify Grid when Coloumns Header Enable")
	public void VerifyColumnsHeaderEnable() {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.VerifyColumnsHeadersEnable(), "Headers assertion Failed");
	}

	@Test(priority=4,description=" To Verify Grid when Coloumn Header Disabled")
	public void VerifyColumnsHeaderDisable() {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertFalse(attributePage.verifycolumnsHeaderDisabled(), "Headers Assertion Failed");
	}

	@Test(priority=5,description="To Verify Add cancel Button")
	public void VerifyAddCancelButton() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AttributesDetails attributesDetails = new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.addCancelButton(attributesDetails), "Add cancel button assertion failed");
	}

	@Test(priority=6,description="To Verify Add Record with All the Fields Blank ")
	public void VerifyAddRecordWithAllTheFieldsBlank() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AttributesDetails attributesDetails = new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.AddNewAttributesRecordwithAllthefieldsBlank(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Please Provide Name, Category, Priority Level", "VerifyAddRecordWithAllTheFieldsBlank Assertion Failed");
	}

	@Test(priority=7,description="To Verify Add Record Without Name")
	public void VerifyAddRecordWithoutName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AttributesDetails attributesDetails = new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.AddNewAttributesRecordwithOutName(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Please Provide Name", "VerifyAddRecordWithoutName Assertion Failed");
	}

	@Test(priority=8,description=" To Verify Add Record without Category")
	public void VerifyAddRecordWithoutCategory() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AttributesDetails attributesDetails = new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.AddNewAttributesRecordwithOutCategoryListBox(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Please Provide Category", "VerifyAddRecordWithoutCategory Assertion Failed");
	}

	@Test(priority=9,description="To Verify Add Record withOut Priority Level")
	public void VerifyAddRecordWithoutPriorityLevel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AttributesDetails attributesDetails = new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.AddNewAttributesRecordwithOutPriorityListBox(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Please Provide Priority Level", "VerifyAddRecordWithoutPriorityLevel Assertion Failed");
	}

	@Test(priority=10,description="To Verify Add Record with All Valid Fields ")
	public void VerifyAddRecordWithOutIsEnabled() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		AttributesDetails attributesDetails = new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.VerifyAddNewRecordwithAllValidFields(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Record Created Successfully", "VerifyAddRecordWithAllValidFields Assertion Failed");
	}

	@Test(priority=10,description="To Verify Add Record with All Valid Fields ")
	public void VerifyAddRecordWithAllValidFields() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AttributesDetails attributesDetails = new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.VerifyAddNewRecordwithAllValidFields(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Record Created Successfully", "VerifyAddRecordWithAllValidFields Assertion Failed");
	}

	@Test(priority=11,description="To Verify Add Duplicate Values ")
	public void VerifyAddDulicatewithAllValidFields() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
		AttributesDetails attributesDetails = new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.VerifyAddNewRecordwithAllValidFields(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Record Creation Failed, Already Exist", "VerifyAddDulicateAllValidFields Assertion Failed");
	}

	@Test(priority=12,dependsOnMethods="VerifyAddRecordWithAllValidFields",description="To Verify AuditTrail Report for Create")
	public void verifyAuditTrailReportForCreate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		AttributesDetails attributesDetails = new AttributesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map2);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAttributesCreate(attributesDetails, "Create"), "Create Audit Trail Assertion Failed"); 
	}

	@Test(priority=13,description="To Verify Edit Cancel Button")
	public void verifyEditCancelButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Update").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.EditCancel(attributesDetails), "Edit cancel Button Assertion failed");
	}

	@Test(priority=14,description="To Verify Edit Record with out Modify Reason")
	public void verifyEditRecordWithOutModifyReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Update").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.EditRecordWithoutModifyReason(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Please enter the modify reason", "verifyEditRecordWithOutModifyReason Assertion Failed");
	}

	@Test(priority=15,description="To Verify Edit Record ")
	public void verifyEditAttributeRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Update").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.EditRecord(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Record Updated Successfully", "verifyEditRecord Assertion Failed");
	}

	@Test(priority=16,dependsOnMethods="verifyEditAttributeRecord",description="To Verify Audit Trail Report for Update")
	public void verifyAuditTrailReportForUpdate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Update").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map2);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAttributesUpdate(attributesDetails, "Update"), "Update Audit Trail Assertion Failed"); 
	}

	@Test(priority=17,description="To Verify Export Excel Button")
	public void ExportToExcelButton() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.ExportToExcelButton(filePath), "Export Button assertion failed");
	}

	@Test(priority=18,dependsOnMethods="ExportToExcelButton",description="To Verify Exported Data")
	public void verifyExportedData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Attributes.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=19,description="To Verify Export Excel without Data")
	public void verifyExportToExcelWithoutData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.VerifyExportToExcelWithoutData(attributesDetails), "Export to Excel Without Data assertion failed");
	}

	@Test(priority=20,description="To Verify Coloumns Sort By Ascending ")
	public void verifySortByAscending() throws Exception {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.verifySortByAscending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Attributes (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(attributePage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=21,description="To Verify Coloumns Sort By Descending ")
	public void verifySortByDescending() throws Exception {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.verifySortByDescending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Attributes (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(attributePage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=22,description="To Verify Group By Coloumns ")
	public void verifyGroupBy() {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("AttributesTest", "group By Column");
		Assert.assertTrue(attributePage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("AttributesTest", "Already grouped Coloumn");
	}

	@Test(priority=23,description="To Verify arrow Move previous and Next Page ")
	public void verifyArrowMoveForPreviousAndNextPage() throws Exception {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.verifyArrowMoveForPreviousAndNextPage(),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=24,description=" To Verify Arrow Move for First and Last Page")
	public void verifyArrowMoveForFirstAndLastPage() throws Exception {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.verifyArrowMoveForFirstAndLastPage(), "First and Last Page Assertion Failed");
	}

	@Test(priority=25,description="To Verify Total Number of Items Per Page Deatials ")
	public void verifyTotalNumberOfItemsPerPageDetails() {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.verifyTotalNumberOfItemsPerPageDetails(), "Number of Items per Page assertion Failed");
	}

	@Test(priority=26,description="To Verify Number of Items PerPage Selection ")
	public void verifyNumberOfItemsPerPageSelection() {
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.verifyNumberOfItemsPerPage(), "Number of Items per Page Assertion Failed");
	}

	@Test(priority=27,description="To Verify Delete No Button ")
	public void verifyDeleteNoButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.DeleteCancel(attributesDetails), "Delete No Buuton Assertion failed");
	}

	@Test(priority=28,description="To Verify Delete without Delete Reason")
	public void verifyDeleteWithoutDeleteReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.DeleteWithoutReason(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Please enter the delete reason", "verifyWithoutDeleteReason Assertion Failed");
	}

	@Test(priority=29,description="To Vrify Delete Record ")
	public void DeleteAttributesRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.DeleteRecord(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Record Deleted Successfully", "DeleteTmacAuxCodesRecord Assertion Failed");
	}

	@Test(priority=30)//,dependsOnMethods="DeleteAttributesRecord",description="To Verify Audit Trail Report for Delete Record ")
	public void verifyAuditTrailReportForDelete() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map2);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAttributesDelete(attributesDetails, "Delete"), "Delete Audit Trail Assertion Failed"); 
	}

	@Test(priority=31,description="To Verify Search Page ")
	public void searchPage() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.clearAll(attributesDetails),"ClearAll Assertion Failed");
		screenshot.captureScreen("AttributesTest", "clearall");
		Assert.assertTrue(attributePage.verifyclose());
		screenshot.captureScreen("AttributesTest", "SearchClose");
	}

	@Test(priority=32,description="To Verify Search without Search Textbox Data ")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		attributePage.searchwithoutextsearch(attributesDetails);
		Assert.assertEquals(attributePage.VerifyMessage(), "Please enter the text to search or remove the filter", "verifyWithoutDeleteReason Assertion Failed");
	}

	@Test(priority=33,description=" To Verify Search Clear Search Button")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);;
		Assert.assertTrue(attributePage.verifyinvalidsearchwithwrongdata(attributesDetails),"invalidsearchwithwrongdata");
		screenshot.captureScreen("AttributesTest","Invalid Search with wrong data");
		Assert.assertTrue(attributePage.verifyclearsearch(), "Clear All Assertion Failed");
	}

	@Test(priority=34,description="To Verify Search Data Is Not Equal to ")
	public void SearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);;
		Assert.assertTrue(attributePage.verifySearchIsnotEqualTo(attributesDetails.getName()), "Search assertion Failed");
	}

	@Test(priority=35,description="To Verify Search Data Contains ")
	public void SearchContains() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);;
		Assert.assertTrue(attributePage.verifySearchContains(attributesDetails.getName()), "Search assertion Failed");
	}

	@Test(priority=36,description="To Verify Search Data Doesnot Contain ")
	public void SearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);;
		Assert.assertTrue(attributePage.verifySearchDoesNotContains(attributesDetails.getName()), "Search assertion Failed");
	}

	@Test(priority=37,description="To Verify Search Data Starts with ")
	public void SearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);;
		Assert.assertTrue(attributePage.verifySearchStartsWith(attributesDetails.getName()), "Search assertion Failed");
	}

	@Test(priority=38,description="To Verify search Data Ens with ")
	public void SearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.verifySearchEndsWith(attributesDetails.getName()), "Search assertion Failed");
	}

	@Test(priority=39,description="To Verify UI Grid Data with DB")
	public void VerifyDataBase() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributesData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Queries").getTestData().get(0);
		AttributesDetails attributesDetails=new AttributesDetails(map);
		AttributesPage attributePage=PageFactory.createPageInstance(driver, AttributesPage.class);
		Assert.assertTrue(attributePage.verifyDataBase(attributesDetails.getQuery()), "DataBase Assertion failed");
	}

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("AttributesTest", method.getName());
		driver.navigate().refresh();
	}

}
