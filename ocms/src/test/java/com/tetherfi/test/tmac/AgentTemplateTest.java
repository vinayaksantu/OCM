package com.tetherfi.test.tmac;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.AgentTemplateDetails;
import com.tetherfi.pages.AgentTemplatePage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AgentTemplateTest extends BaseTest {

Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
	public void NavigateToAgentTemplatePage() {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HomePage Assertion Failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver, TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "Tmac Page Assertion Failed");
		tmacPage.navigateToAgentTemplatePage();
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.isAgentemplatePageIsDisplayed(), "AgentTemplatePage Assertion  failed");
	}
/************************************************************Template Tab******************************************************************************/	
	@Test(priority=1,description="To Verify Agent Template Page")
	public void VerifyAgentTemplatePageTemplateTab() {
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.verifyLogo(), "AgentTemplate Logo Assertion Failed");
		Assert.assertTrue(agentTemplatePage.maximizeWindow(), "fullScreen Assertion Failed");
		screenshot.captureScreen("AgentTemplateTest", "maximize window");
		Assert.assertTrue(agentTemplatePage.minimizeWindow(), "Restore Assertion Failed");
		screenshot.captureScreen("AgentTemplateTest", "minimize window");
		Assert.assertTrue(agentTemplatePage.VerifyAgnetTemplatePageTemplateTabHeader(), "AgentTemplatePageTemplateTab Headers Assertion Failed");
	}
	
	@Test(priority=2,description="To Verify DropDown Coloumns")
	public void verifyDropDownForAllTheColumns() {
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.verifyDropDownOfAllHeaders(),"Columns DropDown Assertion failed");
	}

	@Test(priority=3,description="To Verify Grid when Coloumns Header Enable")
	public void VerifyColumnsHeaderEnable() {
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.VerifyColumnsHeadersEnable(), "Headers assertion Failed");
	}

	@Test(priority=4,description=" To Verify Grid when Coloumn Header Disabled")
	public void VerifyColumnsHeaderDisable() {
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertFalse(agentTemplatePage.verifycolumnsHeaderDisabled(), "Headers Assertion Failed");
	}
	
	@Test(priority=5,description="To Verify Export Excel Button")
	public void ExportToExcelButton() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.ExportToExcelButton(filePath,0), "Export Button assertion failed");
	}

	@Test(priority=6)//,dependsOnMethods="ExportToExcelButton",description="To Verify Exported Data")
	public void verifyExportedData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.VerifyExportToExcelSheet(maplist,0), "Export Data Assertion failed");
	}

	@Test(priority=7,description="To Verify Export Excel without Data")
	public void verifyExportToExcelWithoutData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.VerifyExportToExcelWithoutData(AgentTemplateDetails,0), "Export to Excel Without Data assertion failed");
	}
	
	@Test(priority=8,description="To Verify Coloumns Sort By Ascending ")
	public void verifySortByAscending() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.verifySortByAscending(0);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,0), "Export Data Assertion failed");
	}

	@Test(priority=9,description="To Verify Coloumns Sort By Descending ")
	public void verifySortByDescending() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.verifySortByDescending(0);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,0), "Export Data Assertion failed");
	}

	@Test(priority=10,description="To Verify Group By Coloumns ")
	public void verifyGroupBy() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.groupBy(0), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "group By Column");
		Assert.assertTrue(AgentTemplatePage.groupBy(0), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "Already grouped Coloumn");
	}

	@Test(priority=11,description="To Verify arrow Move previous and Next Page ")
	public void verifyArrowMoveForPreviousAndNextPage() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForPreviousAndNextPage(0),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=12,description=" To Verify Arrow Move for First and Last Page")
	public void verifyArrowMoveForFirstAndLastPage() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForFirstAndLastPage(0), "First and Last Page Assertion Failed");
	}

	@Test(priority=13,description="To Verify Total Number of Items Per Page Deatials ")
	public void verifyTotalNumberOfItemsPerPageDetails() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.verifyTotalNumberOfItemsPerPageDetails(0), "Number of Items per Page assertion Failed");
	}

	@Test(priority=14,description="To Verify Number of Items PerPage Selection ")
	public void verifyNumberOfItemsPerPageSelection() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.verifyNumberOfItemsPerPage(0), "Number of Items per Page Assertion Failed");
	}
	
	@Test(priority=15,description="To Verify Search Page ")
	public void searchPage() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.clearAll(AgentTemplateDetails,0),"ClearAll Assertion Failed");
		screenshot.captureScreen("AgentTemplateTest", "clearall");
		Assert.assertTrue(AgentTemplatePage.verifyclose(0));
		screenshot.captureScreen("AgentTemplateTest", "SearchClose");
	}

	@Test(priority=16,description="To Verify Search without Search Textbox Data ")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.searchwithoutextsearch(AgentTemplateDetails);
		Assert.assertEquals(AgentTemplatePage.VerifyMessage(), "Please enter the text to search or remove the filter", "verifyWithoutDeleteReason Assertion Failed");
	}

	@Test(priority=17,description=" To Verify Search Clear Search Button")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.verifyinvalidsearchwithwrongdata(AgentTemplateDetails,0),"invalidsearchwithwrongdata");
		screenshot.captureScreen("AgentTemplateTest","Invalid Search with wrong data");
		Assert.assertTrue(AgentTemplatePage.verifyclearsearch(0), "Clear All Assertion Failed");
	}
	
	@Test(priority=18,description="To Verify Search Data Is Not Equal to ")
	public void SearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.verifySearchIsnotEqualTo(AgentTemplateDetails.getTemplatename(),0), "Search assertion Failed");
	}

	@Test(priority=19,description="To Verify Search Data Contains ")
	public void SearchContains() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.verifySearchContains(AgentTemplateDetails.getTemplatename(),0), "Search assertion Failed");
	}

	@Test(priority=20,description="To Verify Search Data Doesnot Contain ")
	public void SearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.verifySearchDoesNotContains(AgentTemplateDetails.getTemplatename(),0), "Search assertion Failed");
	}

	@Test(priority=21,description="To Verify Search Data Starts with ")
	public void SearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.verifySearchStartsWith(AgentTemplateDetails.getTemplatename(),0), "Search assertion Failed");
	}

	@Test(priority=22,description="To Verify search Data Ens with ")
	public void SearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(AgentTemplatePage.verifySearchEndsWith(AgentTemplateDetails.getTemplatename(),0), "Search assertion Failed");
	}

/************************************************************Templates Screen Tab******************************************************************************/

	@Test(priority=23,description="To Verify Export Excel Button")
	public void ExportToExcelButtonInTemplateScreenTab() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(agentTemplatePage.ExportToExcelButton(filePath,1), "Export Button assertion failed");
	}

	@Test(priority=24,dependsOnMethods="ExportToExcelButtonInTemplateScreenTab",description="To Verify Exported Data")
	public void verifyExportedDataInTemplateScreenTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(agentTemplatePage.VerifyExportToExcelSheet(maplist,1), "Export Data Assertion failed");
	}
	
	@Test(priority=25,description="To Verify Export Excel without Data")
	public void verifyExportToExcelWithoutDataInTemplateScreenTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelWithoutData(AgentTemplateDetails,1), "Export to Excel Without Data assertion failed");
	}
	
	@Test(priority=26,description="To Verify Coloumns Sort By Ascending ")
	public void verifySortByAscendingInTemplateScreenTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		AgentTemplatePage.verifySortByAscending(1);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,1), "Export Data Assertion failed");
	}

	@Test(priority=27,description="To Verify Coloumns Sort By Descending ")
	public void verifySortByDescendingInTemplateScreenTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		AgentTemplatePage.verifySortByDescending(1);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,1), "Export Data Assertion failed");
	}

	@Test(priority=28,description="To Verify Group By Coloumns ")
	public void verifyGroupByInTemplateScreenTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.groupByInTemplateScreenTab(1), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "group By Column");
		Assert.assertTrue(AgentTemplatePage.groupByInTemplateScreenTab(1), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "Already grouped Coloumn");
	}
	
	@Test(priority=29,description="To Verify arrow Move previous and Next Page ")
	public void verifyArrowMoveForPreviousAndNextPageInTemplateScreenTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForPreviousAndNextPage(1),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=30,description=" To Verify Arrow Move for First and Last Page")
	public void verifyArrowMoveForFirstAndLastPageInTemplateScreenTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForFirstAndLastPage(1), "First and Last Page Assertion Failed");
	}

	@Test(priority=31,description="To Verify Total Number of Items Per Page Deatials ")
	public void verifyTotalNumberOfItemsPerPageDetailsInTemplateScreenTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.verifyTotalNumberOfItemsPerPageDetails(1), "Number of Items per Page assertion Failed");
	}

	@Test(priority=32,description="To Verify Number of Items PerPage Selection ")
	public void verifyNumberOfItemsPerPageSelectionInTemplateScreenTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.verifyNumberOfItemsPerPage(1), "Number of Items per Page Assertion Failed");
	}
	
	@Test(priority=33,description="To Verify Search Page ")
	public void searchPageTemplateScreenTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.clearAll(AgentTemplateDetails,1),"ClearAll Assertion Failed");
		screenshot.captureScreen("AgentTemplateTest", "clearall");
		Assert.assertTrue(AgentTemplatePage.verifyclose(1));
		screenshot.captureScreen("AgentTemplateTest", "SearchClose");
	}

	@Test(priority=34,description="To Verify Search without Search Textbox Data ")
	public void searchwithoutSearchTextboxTemplateScreenTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		AgentTemplatePage.searchwithoutextsearch(AgentTemplateDetails);
		Assert.assertEquals(AgentTemplatePage.VerifyMessage(), "Please enter the text to search or remove the filter", "verifyWithoutDeleteReason Assertion Failed");
	}

	@Test(priority=35,description=" To Verify Search Clear Search Button")
	public void SearchClearSearchTemplateScreenTab() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.verifyinvalidsearchwithwrongdata(AgentTemplateDetails,1),"invalidsearchwithwrongdata");
		screenshot.captureScreen("AgentTemplateTest","Invalid Search with wrong data");
		Assert.assertTrue(AgentTemplatePage.verifyclearsearch(1), "Clear All Assertion Failed");
	}
	
	@Test(priority=36,description="To Verify Search Data Is Not Equal to ")
	public void SearchIsNotEqualToTemplateScreenTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchIsnotEqualTo(AgentTemplateDetails.getTemplatename(),1), "Search assertion Failed");
	}

	@Test(priority=37,description="To Verify Search Data Contains ")
	public void SearchContainsTemplateScreenTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchContains(AgentTemplateDetails.getTemplatename(),1), "Search assertion Failed");
	}

	@Test(priority=38,description="To Verify Search Data Doesnot Contain ")
	public void SearchDoesNotContainsTemplateScreenTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchDoesNotContains(AgentTemplateDetails.getTemplatename(),1), "Search assertion Failed");
	}

	@Test(priority=39,description="To Verify Search Data Starts with ")
	public void SearchStartsWithTemplateScreenTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchStartsWith(AgentTemplateDetails.getTemplatename(),1), "Search assertion Failed");
	}

	@Test(priority=40,description="To Verify search Data Ens with ")
	public void SearchEndsWithTemplateScreenTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchEndsWith(AgentTemplateDetails.getTemplatename(),1), "Search assertion Failed");
	}
	
/************************************************************Templates Screen Widgets Tab******************************************************************************/	
	
	@Test(priority=41,description="To Verify Export Excel Button")
	public void ExportToExcelButtonInTemplateScreenWidgetsTab() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(agentTemplatePage.ExportToExcelButton(filePath,2), "Export Button assertion failed");
	}
	
	@Test(priority=42,dependsOnMethods="ExportToExcelButtonInTemplateScreenWidgetsTab",description="To Verify Exported Data")
	public void verifyExportedDataInTemplateScreenWidgetsTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(agentTemplatePage.VerifyExportToExcelSheet(maplist,2), "Export Data Assertion failed");
	}
	
	@Test(priority=43,description="To Verify Export Excel without Data")
	public void verifyExportToExcelWithoutDataInTemplateScreenWidgetsTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelWithoutDataInTemplateWidgetScreenTab(AgentTemplateDetails,2), "Export to Excel Without Data assertion failed");
	}
	
	@Test(priority=44,description="To Verify Coloumns Sort By Ascending ")
	public void verifySortByAscendingInTemplateWidgetsScreenTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		AgentTemplatePage.verifySortByAscendingInTemplateWidgetsScreenTab(2);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,2), "Export Data Assertion failed");
	}

	@Test(priority=45,description="To Verify Coloumns Sort By Descending ")
	public void verifySortByDescendingInTemplateWidgetsScreenTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		AgentTemplatePage.verifySortByDescendingInTemplateWidgetsScreenTab(2);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,2), "Export Data Assertion failed");
	}
	
	@Test(priority=46,description="To Verify Group By Coloumns ")
	public void verifyGroupByInTemplateWidgetsScreenTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.groupByInTemplateWidgetScreenTab(2), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "group By Column");
		Assert.assertTrue(AgentTemplatePage.groupByInTemplateWidgetScreenTab(2), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "Already grouped Coloumn");
	}
	
	@Test(priority=47,description="To Verify arrow Move previous and Next Page ")
	public void verifyArrowMoveForPreviousAndNextPageInTemplateWidgetsScreenTTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForPreviousAndNextPage(2),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=48,description=" To Verify Arrow Move for First and Last Page")
	public void verifyArrowMoveForFirstAndLastPageInTemplateWidgetsScreenTTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForFirstAndLastPage(2), "First and Last Page Assertion Failed");
	}

	@Test(priority=49,description="To Verify Total Number of Items Per Page Deatials ")
	public void verifyTotalNumberOfItemsPerPageDetailsInTemplateWidgetsScreenTTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.verifyTotalNumberOfItemsPerPageDetails(2), "Number of Items per Page assertion Failed");
	}

	@Test(priority=50,description="To Verify Number of Items PerPage Selection ")
	public void verifyNumberOfItemsPerPageSelectionInTemplateWidgetsScreenTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.verifyNumberOfItemsPerPage(2), "Number of Items per Page Assertion Failed");
	}
	
	@Test(priority=51,description="To Verify Search Page ")
	public void searchPageTemplateWidgetsScreenTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.clearAllInScreenWidgetScreentab(AgentTemplateDetails,2),"ClearAll Assertion Failed");
		screenshot.captureScreen("AgentTemplateTest", "clearall");
		Assert.assertTrue(AgentTemplatePage.verifyclose(2));
		screenshot.captureScreen("AgentTemplateTest", "SearchClose");
	}
	
	@Test(priority=52,description="To Verify Search without Search Textbox Data ")
	public void searchwithoutSearchTextboxTemplateScreenWidgetsTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		AgentTemplatePage.searchwithoutextsearchInTemplateScreenWidgetsTab(AgentTemplateDetails);
		Assert.assertEquals(AgentTemplatePage.VerifyMessage(), "Please enter the text to search or remove the filter", "verifyWithoutDeleteReason Assertion Failed");
	}
	
	@Test(priority=53,description=" To Verify Search Clear Search Button")
	public void SearchClearSearchTemplateScreenWidgetsTab() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.verifyinvalidsearchwithwrongdataInTemplateScreenWidgetsTab(AgentTemplateDetails,2),"invalidsearchwithwrongdata");
		screenshot.captureScreen("AgentTemplateTest","Invalid Search with wrong data");
		Assert.assertTrue(AgentTemplatePage.verifyclearsearch(2), "Clear All Assertion Failed");
	}
	
	@Test(priority=54,description="To Verify Search Data Is Not Equal to ")
	public void SearchIsNotEqualToInTemplateScreenWidgetsTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchIsnotEqualToInTemplateWidgetsScreenTab(AgentTemplateDetails.getScreenname(),2), "Search assertion Failed");
	}

	@Test(priority=55,description="To Verify Search Data Contains ")
	public void SearchContainsInTemplateScreenWidgetsTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchContainsInTemplateWidgetsScreenTab(AgentTemplateDetails.getScreenname(),2), "Search assertion Failed");
	}

	@Test(priority=56,description="To Verify Search Data Doesnot Contain ")
	public void SearchDoesNotContainsInTemplateScreenWidgetsTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchDoesNotContainsInTemplateWidgetsScreenTab(AgentTemplateDetails.getScreenname(),2), "Search assertion Failed");
	}

	@Test(priority=57,description="To Verify Search Data Starts with ")
	public void SearchStartsWithInTemplateScreenWidgetsTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchStartsWithInTemplateWidgetsScreenTab(AgentTemplateDetails.getScreenname(),2), "Search assertion Failed");
	}

	@Test(priority=58,description="To Verify search Data Ens with ")
	public void SearchEndsWithInTemplateScreenWidgetsTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenWidgetsTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchEndsWithInTemplateWidgetsScreenTab(AgentTemplateDetails.getScreenname(),2), "Search assertion Failed");
	}

/************************************************************Template AuxCodes Tab******************************************************************************/
	
	@Test(priority=59,description="To Verify Export Excel Button")
	public void ExportToExcelButtonInTemplateAuxCodesTab() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(agentTemplatePage.ExportToExcelButton(filePath,3), "Export Button assertion failed");
	}

	@Test(priority=60,dependsOnMethods="ExportToExcelButtonInTemplateAuxCodesTab",description="To Verify Exported Data")
	public void verifyExportedDataInTemplateAuxCodesTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(agentTemplatePage.VerifyExportToExcelSheet(maplist,3), "Export Data Assertion failed");
	}
	
	@Test(priority=61,description="To Verify Export Excel without Data")
	public void verifyExportToExcelWithoutDataInTemplateAuxCodesTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelWithoutData(AgentTemplateDetails,3), "Export to Excel Without Data assertion failed");
	}
	
	@Test(priority=62,description="To Verify Coloumns Sort By Ascending ")
	public void verifySortByAscendingInTemplateAuxCodesTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		AgentTemplatePage.verifySortByAscending(3);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,3), "Export Data Assertion failed");
	}

	@Test(priority=63,description="To Verify Coloumns Sort By Descending ")
	public void verifySortByDescendingInTemplateAuxCodesTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		AgentTemplatePage.verifySortByDescending(3);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,3), "Export Data Assertion failed");
	}

	@Test(priority=64,description="To Verify Group By Coloumns ")
	public void verifyGroupByInTemplateAuxCodesTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.groupByInTemplateAuxCodesTab(3), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "group By Column");
		Assert.assertTrue(AgentTemplatePage.groupByInTemplateAuxCodesTab(3), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "Already grouped Coloumn");
	}
	
	@Test(priority=65,description="To Verify arrow Move previous and Next Page ")
	public void verifyArrowMoveForPreviousAndNextPageInTemplateAuxCodesTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForPreviousAndNextPage(3),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=66,description=" To Verify Arrow Move for First and Last Page")
	public void verifyArrowMoveForFirstAndLastPageInTemplateAuxCodesTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForFirstAndLastPage(3), "First and Last Page Assertion Failed");
	}

	@Test(priority=67,description="To Verify Total Number of Items Per Page Deatials ")
	public void verifyTotalNumberOfItemsPerPageDetailsInTemplateAuxCodesTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.verifyTotalNumberOfItemsPerPageDetails(3), "Number of Items per Page assertion Failed");
	}

	@Test(priority=68,description="To Verify Number of Items PerPage Selection ")
	public void verifyNumberOfItemsPerPageSelectionInTemplateAuxCodesTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.verifyNumberOfItemsPerPage(3), "Number of Items per Page Assertion Failed");
	}
	
	@Test(priority=69,description="To Verify Search Page ")
	public void searchPageTemplateAuxCodesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.clearAll(AgentTemplateDetails,3),"ClearAll Assertion Failed");
		screenshot.captureScreen("AgentTemplateTest", "clearall");
		Assert.assertTrue(AgentTemplatePage.verifyclose(3));
		screenshot.captureScreen("AgentTemplateTest", "SearchClose");
	}

	@Test(priority=70,description="To Verify Search without Search Textbox Data ")
	public void searchwithoutSearchTextboxTemplateAuxCodesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateScreenTab();
		AgentTemplatePage.searchwithoutextsearch(AgentTemplateDetails);
		Assert.assertEquals(AgentTemplatePage.VerifyMessage(), "Please enter the text to search or remove the filter", "verifyWithoutDeleteReason Assertion Failed");
	}

	@Test(priority=71,description=" To Verify Search Clear Search Button")
	public void SearchClearSearchTemplateAuxCodesTab() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.verifyinvalidsearchwithwrongdata(AgentTemplateDetails,3),"invalidsearchwithwrongdata");
		screenshot.captureScreen("AgentTemplateTest","Invalid Search with wrong data");
		Assert.assertTrue(AgentTemplatePage.verifyclearsearch(3), "Clear All Assertion Failed");
	}
	
	@Test(priority=72,description="To Verify Search Data Is Not Equal to ")
	public void SearchIsNotEqualToTemplateAuxCodesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchIsnotEqualTo(AgentTemplateDetails.getTemplatename(),3), "Search assertion Failed");
	}

	@Test(priority=73,description="To Verify Search Data Contains ")
	public void SearchContainsTemplateAuxCodesab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchContains(AgentTemplateDetails.getTemplatename(),3), "Search assertion Failed");
	}

	@Test(priority=74,description="To Verify Search Data Doesnot Contain ")
	public void SearchDoesNotContainsTemplateAuxCodesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchDoesNotContains(AgentTemplateDetails.getTemplatename(),3), "Search assertion Failed");
	}

	@Test(priority=75,description="To Verify Search Data Starts with ")
	public void SearchStartsWithTemplateAuxCodesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchStartsWith(AgentTemplateDetails.getTemplatename(),3), "Search assertion Failed");
	}

	@Test(priority=76,description="To Verify search Data Ens with ")
	public void SearchEndsWithTemplateAuxCodesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateAuxCodesTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchEndsWith(AgentTemplateDetails.getTemplatename(),3), "Search assertion Failed");
	}
	
/************************************************************Template Operating Hours Tab******************************************************************************/
	
	@Test(priority=77,description="To Verify Export Excel Button")//Bug:Export File is not happening
	public void ExportToExcelButtonInTemplateOpHoursTab() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(agentTemplatePage.ExportToExcelButton(filePath,4), "Export Button assertion failed");
	}

	@Test(priority=78,dependsOnMethods="ExportToExcelButtonInTemplateOpHoursTab",description="To Verify Exported Data")
	public void verifyExportedDataInTemplateOpHoursTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(agentTemplatePage.VerifyExportToExcelSheet(maplist,4), "Export Data Assertion failed");
	}
	
	@Test(priority=79,description="To Verify Export Excel without Data")
	public void verifyExportToExcelWithoutDataInTemplateOpHoursTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelWithoutData(AgentTemplateDetails,4), "Export to Excel Without Data assertion failed");
	}
	
	@Test(priority=80,description="To Verify Coloumns Sort By Ascending ")
	public void verifySortByAscendingInTemplateOpHoursTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		AgentTemplatePage.verifySortByAscending(4);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,4), "Export Data Assertion failed");
	}

	@Test(priority=81,description="To Verify Coloumns Sort By Descending ")
	public void verifySortByDescendingInTemplateOpHoursTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		AgentTemplatePage.verifySortByDescending(4);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,4), "Export Data Assertion failed");
	}

	@Test(priority=82,description="To Verify Group By Coloumns ")
	public void verifyGroupByInTemplateOpHoursTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.groupByInTemplateOpHoursTab(4), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "group By Column");
		Assert.assertTrue(AgentTemplatePage.groupByInTemplateOpHoursTab(4), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "Already grouped Coloumn");
	}
	
	@Test(priority=83,description="To Verify arrow Move previous and Next Page ")
	public void verifyArrowMoveForPreviousAndNextPageInTemplateOpHoursTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForPreviousAndNextPage(4),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=84,description=" To Verify Arrow Move for First and Last Page")
	public void verifyArrowMoveForFirstAndLastPageInTemplateOpHoursTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForFirstAndLastPage(4), "First and Last Page Assertion Failed");
	}

	@Test(priority=85,description="To Verify Total Number of Items Per Page Deatials ")
	public void verifyTotalNumberOfItemsPerPageDetailsInTemplateOpHoursTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.verifyTotalNumberOfItemsPerPageDetails(4), "Number of Items per Page assertion Failed");
	}

	@Test(priority=86,description="To Verify Number of Items PerPage Selection ")
	public void verifyNumberOfItemsPerPageSelectionInTemplateOpHoursTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.verifyNumberOfItemsPerPage(4), "Number of Items per Page Assertion Failed");
	}
	
	@Test(priority=87,description="To Verify Search Page ")
	public void searchPageTemplateOpHoursTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.clearAll(AgentTemplateDetails,4),"ClearAll Assertion Failed");
		screenshot.captureScreen("AgentTemplateTest", "clearall");
		Assert.assertTrue(AgentTemplatePage.verifyclose(4));
		screenshot.captureScreen("AgentTemplateTest", "SearchClose");
	}

	@Test(priority=88,description="To Verify Search without Search Textbox Data ")
	public void searchwithoutSearchTextboxTemplateOpHoursTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		AgentTemplatePage.searchwithoutextsearch(AgentTemplateDetails);
		Assert.assertEquals(AgentTemplatePage.VerifyMessage(), "Please enter the text to search or remove the filter", "verifyWithoutDeleteReason Assertion Failed");
	}

	@Test(priority=89,description=" To Verify Search Clear Search Button")
	public void SearchClearSearchTemplateOpHoursTab() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.verifyinvalidsearchwithwrongdata(AgentTemplateDetails,4),"invalidsearchwithwrongdata");
		screenshot.captureScreen("AgentTemplateTest","Invalid Search with wrong data");
		Assert.assertTrue(AgentTemplatePage.verifyclearsearch(4), "Clear All Assertion Failed");
	}
	
	@Test(priority=90,description="To Verify Search Data Is Not Equal to ")
	public void SearchIsNotEqualToTemplateOpHoursTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchIsnotEqualTo(AgentTemplateDetails.getTemplatename(),4), "Search assertion Failed");
	}

	@Test(priority=91,description="To Verify Search Data Contains ")
	public void SearchContainsTemplateOpHoursTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchContains(AgentTemplateDetails.getTemplatename(),4), "Search assertion Failed");
	}

	@Test(priority=92,description="To Verify Search Data Doesnot Contain ")
	public void SearchDoesNotContainsTemplateOpHoursTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchDoesNotContains(AgentTemplateDetails.getTemplatename(),4), "Search assertion Failed");
	}

	@Test(priority=93,description="To Verify Search Data Starts with ")
	public void SearchStartsWithTemplateOpHoursTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchStartsWith(AgentTemplateDetails.getTemplatename(),4), "Search assertion Failed");
	}

	@Test(priority=94,description="To Verify search Data Ens with ")
	public void SearchEndsWithTemplateOpHoursTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateOpHoursTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchEndsWith(AgentTemplateDetails.getTemplatename(),4), "Search assertion Failed");
	}
	
/************************************************************************************Template Features Tab**************************************************************************/
	
	@Test(priority=95,description="To Verify Export Excel Button")
	public void ExportToExcelButtonInTemplateFeaturesTab() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(agentTemplatePage.ExportToExcelButton(filePath,5), "Export Button assertion failed");
	}

	@Test(priority=96,dependsOnMethods="ExportToExcelButtonInTemplateFeaturesTab",description="To Verify Exported Data")
	public void verifyExportedDataInTemplateFeaturesTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(agentTemplatePage.VerifyExportToExcelSheet(maplist,5), "Export Data Assertion failed");
	}
	
	//@Test(priority=97,description="To Verify Export Excel without Data")//Bug:import is happening without data
	public void verifyExportToExcelWithoutDataInTemplateFeaturesTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelWithoutData(AgentTemplateDetails,5), "Export to Excel Without Data assertion failed");
	}
	
	@Test(priority=98,description="To Verify Coloumns Sort By Ascending ")
	public void verifySortByAscendingInTemplateFeaturesTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		AgentTemplatePage.verifySortByAscending(5);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,5), "Export Data Assertion failed");
	}

	@Test(priority=99,description="To Verify Coloumns Sort By Descending ")
	public void verifySortByDescendingInTemplateFeaturesTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		AgentTemplatePage.verifySortByDescending(5);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Templates (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentTemplatePage.VerifyExportToExcelSheet(maplist,5), "Export Data Assertion failed");
	}
	
	@Test(priority=100,description="To Verify Group By Coloumns ")
	public void verifyGroupByInTemplateFeaturesTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.groupByInTemplateFeaturesTab(5), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "group By Column");
		Assert.assertTrue(AgentTemplatePage.groupByInTemplateFeaturesTab(5), "Group By Assertion failed");
		screenshot.captureScreen("AgentTemplateTest", "Already grouped Coloumn");
	}
	
	@Test(priority=101,description="To Verify arrow Move previous and Next Page ")
	public void verifyArrowMoveForPreviousAndNextPageInTemplateFeaturesTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForPreviousAndNextPage(5),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=102,description=" To Verify Arrow Move for First and Last Page")
	public void verifyArrowMoveForFirstAndLastPageInTemplateFeaturesTab() throws Exception {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.verifyArrowMoveForFirstAndLastPage(5), "First and Last Page Assertion Failed");
	}

	@Test(priority=103,description="To Verify Total Number of Items Per Page Deatials ")
	public void verifyTotalNumberOfItemsPerPageDetailsInTemplateFeaturesTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.verifyTotalNumberOfItemsPerPageDetails(5), "Number of Items per Page assertion Failed");
	}

	@Test(priority=104,description="To Verify Number of Items PerPage Selection ")
	public void verifyNumberOfItemsPerPageSelectionInTemplateFeaturesTab() {
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.verifyNumberOfItemsPerPage(5), "Number of Items per Page Assertion Failed");
	}
	
	@Test(priority=105,description="To Verify Search Page ")
	public void searchPageTemplateFeaturesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.clearAll(AgentTemplateDetails,5),"ClearAll Assertion Failed");
		screenshot.captureScreen("AgentTemplateTest", "clearall");
		Assert.assertTrue(AgentTemplatePage.verifyclose(5));
		screenshot.captureScreen("AgentTemplateTest", "SearchClose");
	}

	@Test(priority=106,description="To Verify Search without Search Textbox Data ")
	public void searchwithoutSearchTextboxTemplateFeaturesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		AgentTemplatePage.searchwithoutextsearch(AgentTemplateDetails);
		Assert.assertEquals(AgentTemplatePage.VerifyMessage(), "Please enter the text to search or remove the filter", "verifyWithoutDeleteReason Assertion Failed");
	}

	@Test(priority=107,description=" To Verify Search Clear Search Button")
	public void SearchClearSearchTemplateFeaturesTab() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.verifyinvalidsearchwithwrongdata(AgentTemplateDetails,5),"invalidsearchwithwrongdata");
		screenshot.captureScreen("AgentTemplateTest","Invalid Search with wrong data");
		Assert.assertTrue(AgentTemplatePage.verifyclearsearch(5), "Clear All Assertion Failed");
	}
	
	@Test(priority=108,description="To Verify Search Data Is Not Equal to ")
	public void SearchIsNotEqualToTemplateFeaturesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchIsnotEqualTo(AgentTemplateDetails.getTemplatename(),5), "Search assertion Failed");
	}

	@Test(priority=109,description="To Verify Search Data Contains ")
	public void SearchContainsTemplateFeaturesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchContains(AgentTemplateDetails.getTemplatename(),5), "Search assertion Failed");
	}

	@Test(priority=110,description="To Verify Search Data Doesnot Contain ")
	public void SearchDoesNotContainsTemplateFeaturesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchDoesNotContains(AgentTemplateDetails.getTemplatename(),5), "Search assertion Failed");
	}

	@Test(priority=111,description="To Verify Search Data Starts with ")
	public void SearchStartsWithTemplateFeaturesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchStartsWith(AgentTemplateDetails.getTemplatename(),5), "Search assertion Failed");
	}

	@Test(priority=112,description="To Verify search Data Ens with ")
	public void SearchEndsWithTemplateFeaturesTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage AgentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		AgentTemplatePage.NavigateToTemplateFeaturesTab();
		Assert.assertTrue(AgentTemplatePage.verifySearchEndsWith(AgentTemplateDetails.getTemplatename(),5), "Search assertion Failed");
	}
	
/***************************************************************End**********************************************************************************/
	
	
	
	
	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("TemplatesTest", method.getName());
		driver.navigate().refresh();
	}
}
