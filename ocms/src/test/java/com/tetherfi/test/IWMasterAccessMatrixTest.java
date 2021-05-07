package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.TmacAuxCodesDetails;
import com.tetherfi.model.user.IWMasterAccessMatrixDetails;
import com.tetherfi.model.user.UserRoleMappingDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IWMasterAccessMatrixPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacAuxCodesPage;
import com.tetherfi.pages.UserRoleMappingWMCPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class IWMasterAccessMatrixTest extends BaseTest {

	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToUserMappingPage() {
		HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
		ocmHomePage.navigateToIwMasterAccessMatrixPage();
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.isIWMasterAccessPageDisplayed(),"IW Master Access Matrix Page assertion failed");
	}

	@Test(priority=1,description="To Verify IW Master Access Matrix Page")
	public void IWMasterAccessMatrixPage() {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifyLogo(), "IWMasterAccessMatrixTest Logo Assertion Failed");
		Assert.assertTrue(iWMasterAccessMatrixPage.maximizeWindow(), "fullScreen Assertion Failed");
		screenshot.captureScreen("IWMasterAccessMatrixTest", "maximize window");
		Assert.assertTrue(iWMasterAccessMatrixPage.minimizeWindow(), "Restore Assertion Failed");
		screenshot.captureScreen("IWMasterAccessMatrixTest", "minimize window");
	}

	@Test(priority=2,description="To Verify Dropdown for All the Coloumns")
	public void verifyDropDownForAllTheColumns() {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifyDropDownOfAllHeaders(), "Dropdown for all the Columns Assertion Failed");
	}

	@Test(priority=3,description="To Verify Coloumn Headers Enabled is Displayed in Grid")
	public void verifyColoumnsHeaderEnabled() {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifycolumnsHeaderEnabled(), "Columns Headers Enabled Assertion Failed");
	}

	@Test(priority=4,description="To Verify Coloumn Headers Disabled is Not Displayed in Grid") 
	public void verifyColumnsHeaderDisabled() {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertFalse(iWMasterAccessMatrixPage.verifycolumnsHeaderDisabled(), "Columns Headers Disabled Assertion Failed");
	}
	
	@Test(priority=5,description="To Verify Add Role Based Access Management Record ")
	public void VerifyAddRoleBasedAccessManagementRecordButton() throws Exception {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.VerifyAddRolebasedAccessManagementRecord(), "VerifyAddRoleBasedAccessManagementRecordButton Assertion Failed");
	}
	
	@Test(priority=6,description="To Verify IW Master Componenets Window")
	public void VerifyIWMasterComponentsWindow() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(1);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.VerifyIWMasterComponentsWindowPresence(iWMasterAccessMatrixDetails), "VerifyIWMasterComponentsWindow Assertion Failed");
	}
	
	@Test(priority=7,description="To Verify IW Master Component window Cancel Button")
	public void VerifyIWMasterComponentsWindowCancelButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(1);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.VerifyIWMasterComponentsWindowCancelButton(iWMasterAccessMatrixDetails), "VerifyIWMasterComponentsWindow Assertion Failed");
	}
	
	@Test(priority=8,description="To Verify Update IW Master Components with out Modify Reason")
	public void VerifyUpdateIWMasterComponentsWindowwithoutModifyReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(1);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		iWMasterAccessMatrixPage.VerifyUpdateIWMasterComponentWithOutModifyReason(iWMasterAccessMatrixDetails);
		Assert.assertEquals(iWMasterAccessMatrixPage.VerifyMessage(), "Please enter the modify reason", "VerifyUpdateIWMasterComponentsWindowwithoutModifyReason Assertion Failed");
	}
	
	@Test(priority=9,description="To Verify Group By Coloumn Header and Group Already Grouped coloumn")
	public void verifyGroupBy() {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("IW Master Access Matrix", "group By Column");
		Assert.assertTrue(iWMasterAccessMatrixPage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("IW Master Access Matrix", "Already grouped Coloumn");
	}

	@Test(priority=10,description="To Verify Arrow Move For Previous and NextPage")
	public void verifyArrowMoveForPreviousAndNextPage() {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifyArrowMoveForPreviousAndNextPage(),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=11,description="To verify Arrow move for first and Last Page")
	public void verifyArrowMoveForFirstAndLastPage() {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifyArrowMoveForFirstAndLastPage(), "First and Last Page Assertion Failed");
	}
	
	@Test(priority=12,description="To Verify Total Number of Items Per paga Deatils")
	public void verifyTotalNumberOfItemsPerPageDetails() {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifyTotalNumberOfItemsPerPageDetails(), "Number of Items per Page assertion Failed");
	}

	@Test(priority=13,description="To Verify Number of Items per Page Selection")
	public void verifyNumberOfItemsPerPageSelection() {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifyNumberOfItemsPerPage(), "Number of Items per Page Assertion Failed");
	}

	@Test(priority=14,description="To Verify Export to Excel Button")
	public void ExportToExcelButton() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.ExportToExcelButton(filePath), "Export Button assertion failed");
	}

	@Test(priority=15,description="To Verify Export to Excel Data",dependsOnMethods="ExportToExcelButton")
	public void verifyExportedData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IW Master Access Matrix.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=16,description="To Verify Sort By Ascending")
	public void verifySortByAscending() throws Exception {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		iWMasterAccessMatrixPage.verifySortByAscending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IW Master Access Matrix (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(iWMasterAccessMatrixPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=17,description="To Verify Sort By Descending")
	public void verifySortByDescending() throws Exception {
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		iWMasterAccessMatrixPage.verifySortByDescending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IW Master Access Matrix (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(iWMasterAccessMatrixPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=18,description="To Verify Export to Excel without Data")
	public void verifyExportToExcelWithoutData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(0);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.VerifyExportToExcelWithoutData(iWMasterAccessMatrixDetails), "Export to Excel Without Data assertion failed");
	}

	@Test(priority=19,description="To Verify Search is Not Equal To")
	public void SearchIsNotEqualTo() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(1);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifySearchIsnotEqualTo(iWMasterAccessMatrixDetails.getRoleName()), "Search assertion Failed");
	}

	@Test(priority=20,description="To Verify Search Contains")
	public void SearchContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(2);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifySearchContains(iWMasterAccessMatrixDetails.getRoleName()), "Search assertion Failed");
	}

	@Test(priority=21,description="To verify Search Does not Contains")
	public void SearchDoesNotContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(3);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifySearchDoesNotContains(iWMasterAccessMatrixDetails.getRoleName()), "Search assertion Failed");
	}

	@Test(priority=22,description="To Verify Search Starts with")
	public void SearchStartsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(4);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifySearchStartsWith(iWMasterAccessMatrixDetails.getRoleName()), "Search assertion Failed");
	}

	@Test(priority=23,description="To Verify Search Ends with")
	public void SearchEndsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(5);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifySearchEndsWith(iWMasterAccessMatrixDetails.getRoleName()), "Search assertion Failed");
	}

	@Test(priority=24,description="To Verify Search Page")
	public void searchPage() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TestData").getTestData().get(0);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertFalse(iWMasterAccessMatrixPage.clearAll(iWMasterAccessMatrixDetails),"ClearAll Assertion Failed");
		screenshot.captureScreen("IWMasterAccessMatrixTest", "clearall");
		Assert.assertTrue(iWMasterAccessMatrixPage.verifyclose());
		screenshot.captureScreen("IWMasterAccessMatrixTest", "SearchClose");
	}

	@Test(priority=25,description="To verify Search without Search Text Box")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TestData").getTestData().get(0);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		iWMasterAccessMatrixPage.searchwithoutextsearch(iWMasterAccessMatrixDetails);
		Assert.assertEquals(iWMasterAccessMatrixPage.VerifyMessage(), "Please enter the text to search or remove the filter", "searchwithoutSearchTextbox Assertion Failed");
	}

	@Test(priority=26,description="To Verify Search Clear Search")
	public void SearchClearSearch() throws Exception
	{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IWMasterAccessMatrixData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TestData").getTestData().get(0);
		IWMasterAccessMatrixDetails iWMasterAccessMatrixDetails=new IWMasterAccessMatrixDetails(map);
		IWMasterAccessMatrixPage iWMasterAccessMatrixPage=PageFactory.createPageInstance(driver,IWMasterAccessMatrixPage.class);
		Assert.assertTrue(iWMasterAccessMatrixPage.verifyinvalidsearchwithwrongdata(iWMasterAccessMatrixDetails ),"invalidsearchwithwrongdata");
		screenshot.captureScreen("IWMasterAccessMatrixTest","Invalid Search with wrong data");
		Assert.assertTrue(iWMasterAccessMatrixPage.verifyclearsearch(), "Clear All Assertion Failed");
	}

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("IWMasterAccessMatrixTest", method.getName());
		driver.navigate().refresh();
	}

}
