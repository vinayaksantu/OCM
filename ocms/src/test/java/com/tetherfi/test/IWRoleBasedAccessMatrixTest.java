package com.tetherfi.test;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.IWRoleBasedAccessMatrixDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IWRoleBasedAccessMatrixPage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class IWRoleBasedAccessMatrixTest extends BaseTest {
	
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToIWRoleBasedAccessMatrixPage() {
		HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
		ocmHomePage.navigateToIwRoleBasedAccessMatrixPage();
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.isIWRoleBasedAccessMatrixPageDisplayed(),"IW RoleBased  Access Matrix Page assertion failed");
	}
	
	@Test(priority=1,description="To Verify IW Role Based Access Matrix Page")
	public void IWRoleBasedAccessMatrixPage() {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifyLogo(), "IWRoleBasedAccessMatrixTest Logo Assertion Failed");
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.maximizeWindow(), "fullScreen Assertion Failed");
		screenshot.captureScreen("IWRoleBasedAccessMatrixTest", "maximize window");
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.minimizeWindow(), "Restore Assertion Failed");
		screenshot.captureScreen("IWRoleBasedAccessMatrixTest", "minimize window");
	}
	
	@Test(priority=2,description="To Verify DropDownFor All the Coloumns")
	public void verifyDropDownForAllTheColumns() {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifyDropDownOfAllHeaders(), "Dropdown for all the Columns Assertion Failed");
	}

	@Test(priority=3,description="To Verify Coloumns Header Enabled")
	public void verifyColoumnsHeaderEnabled() {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifycolumnsHeaderEnabled(), "Columns Headers Enabled Assertion Failed");
	}

	@Test(priority=4,description="To verify Coloumns Header Disabled") 
	public void verifyColumnsHeaderDisabled() {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertFalse(iWRoleBasedAccessMatrixPage.verifycolumnsHeaderDisabled(), "Columns Headers Disabled Assertion Failed");
	}
	
	@Test(priority=5,description="To Verify Add Role Based Access Management Record Button")
	public void VerifyAddRoleBasedAccessManagementRecordButton() throws Exception {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyAddRolebasedAccessManagementRecord(), "VerifyAddRoleBasedAccessManagementRecordButton Assertion Failed");
	}
	
	@Test(priority=6,description="To Verify IW Access ComponentsWindow")
	public void VerifyIWAccessComponentsWindow() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(1);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyIWComponentsWindowPresence(iWRoleBasedAccessMatrixDetails), "VerifyIWMasterComponentsWindow Assertion Failed");		
	}
	
	@Test(priority=7,description="To Verify Save Changes Button without Update")
	public void VerifySaveChangesButtonwithoutUpdate() throws Exception  {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(1);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		iWRoleBasedAccessMatrixPage.VerifySaveChnagesButton(iWRoleBasedAccessMatrixDetails);
		Assert.assertEquals(iWRoleBasedAccessMatrixPage.VerifyMessage(), "No rows has been changed", "VerifySaveChangesButtonwithoutUpdate Assertion Failed");
	}
	
	/*@Test(priority=8,description="Navigate to IW")
	public void NavigateTOIW()  {
        try {
        	LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
        	loginPage.openNewWindow();
            loginPage.launchUrl();
            IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
            Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyIWPage(), "NavigateTOIW Assertion failed");
             }
        catch (Exception e){
            PageFactory.reset();
           // driver.close();
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    }
	
	/*@Test(priority=9,description="To Verify Add New Iw Call Flow")
	public void AddNewIWCallFlow() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		iWRoleBasedAccessMatrixPage.ADDNewIWCallFlow(iWRoleBasedAccessMatrixDetails);
		Assert.assertEquals(iWRoleBasedAccessMatrixPage.VerifySuccessMessage(), "DNIS details saved/updated.\nX", "Add Schedule With with valid Assertion Failed");
	}
	
	@Test(priority=10,description="To Verify IW Reflection when copy CheckBox Unchecked")
	public void VerifyCopyCheckBoxWhenUncheckedInOCMwitIW() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyCopyCheckBoxUnchecked(iWRoleBasedAccessMatrixDetails), "VerifyCopyCheckBoxWhenUncheckedwitIW Assertion Failed");
		iWRoleBasedAccessMatrixPage.CopyCallFlowInIW(iWRoleBasedAccessMatrixDetails);
		Assert.assertEquals(iWRoleBasedAccessMatrixPage.VerifyErrorMessage(), "Cannot copy this callflow. Current callflow is in readonly state\nX", "VerifyCopyCheckBoxWhenUncheckedwitIW Assertion Failed");
	}
	
	@Test(priority=11,description="To Verify IW Reflection when copy CheckBox checked")
	public void VerifyCopyCheckBoxWhencheckedInOCMwitIW() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyCopyCheckBoxchecked(iWRoleBasedAccessMatrixDetails), "VerifyCopyCheckBoxWhenUncheckedwitIW Assertion Failed");
		iWRoleBasedAccessMatrixPage.CopyCallFlowInIW(iWRoleBasedAccessMatrixDetails);
		Assert.assertEquals(iWRoleBasedAccessMatrixPage.VerifySuccessMessage(), "NTTData flow successfully copied.\nX", "VerifyCopyCheckBoxWhenUncheckedwitIW Assertion Failed");
	}
	
	@Test(priority=12,description="To Verify IW Reflection when Import Call Flow CheckBox Unchecked")
	public void VerifyImportCallFlowCheckBoxWhenUncheckedInOCMwitIW() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyImportCallFlowCheckBoxUnchecked(iWRoleBasedAccessMatrixDetails), "VerifyCopyCheckBoxWhenUncheckedwitIW Assertion Failed");
		iWRoleBasedAccessMatrixPage.ImportCallFlowInIW(iWRoleBasedAccessMatrixDetails);
		Assert.assertEquals(iWRoleBasedAccessMatrixPage.VerifyErrorMessage(), "Cannot import this callflow. Current callflow is in readonly state\nX", "VerifyCopyCheckBoxWhenUncheckedwitIW Assertion Failed");
	}
	
	@Test(priority=13,description="To Verify IW Reflection when Import Call Flow CheckBox checked")
	public void VerifyImportCheckBoxWhencheckedInOCMwitIW() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyImportCheckBoxcheckedForImportingCallFlow(iWRoleBasedAccessMatrixDetails), "VerifyImportCheckBoxcheckedForImportingCallFlow Assertion Failed");
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyImportCheckBoxcheckedForCurrentCallFlow(iWRoleBasedAccessMatrixDetails), "VerifyImportCheckBoxcheckedForCurrentCallFlow Assertion Failed");
		iWRoleBasedAccessMatrixPage.ImportCallFlowInIW(iWRoleBasedAccessMatrixDetails);
		Assert.assertEquals(iWRoleBasedAccessMatrixPage.VerifySuccessMessage(), "Flow NTTData imported and over-written successfully.\nX", "VerifyCopyCheckBoxWhenUncheckedwitIW Assertion Failed");
	}
	
	@Test(priority=14,description="To Verify IW Reflection when copy CheckBox checked")
	public void VerifyModuleNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyModuleNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyModuleNodeAccess Assertion Failed");
	}
	
	@Test(priority=15,description="To Verify IW Reflection when Announcement Node CheckBox checked")
	public void VerifyAnnouncementNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyAnnouncementNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyAnnouncementNodeAccess Assertion Failed");
	}
	
	@Test(priority=16,description="To Verify IW Reflection when Entry Point Node CheckBox checked")
	public void VerifyEntryPointNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyEntryPoinNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyEntryPointNodeAccess Assertion Failed");
	}
	
	@Test(priority=17,description="To Verify IW Reflection when Menu Node  CheckBox checked")
	public void VerifyMenuNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyMenuNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyMenuNodeAccess Assertion Failed");
	}
	
	@Test(priority=18,description="To Verify IW Reflection when copy CheckBox checked")
	public void VerifyAssignmentNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyAssignmentNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyAssignmentNodeAccess Assertion Failed");
	}
	
	@Test(priority=19,description="To Verify IW Reflection when Conditional Node CheckBox checked")
	public void VerifyConditionalNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyConditionalNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyConditionalNodeAccess Assertion Failed");
	}
	
	@Test(priority=20,description="To Verify IW Reflection when Disconnect Node CheckBox checked")
	public void VerifyDisConnectNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyDisconnectNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyDisConnectNodeAccess Assertion Failed");
	}
	
	@Test(priority=21,description="To Verify IW Reflection when Agent Node CheckBox checked")
	public void VerifyAgentNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyAgentNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyAgentNodeAccess Assertion Failed");
	}
	
	@Test(priority=22,description="To Verify IW Reflection when Exit Point Node CheckBox checked")
	public void VerifyExitPointNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyExitPointNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyExitPointNodeAccess Assertion Failed");
	}
	
	@Test(priority=23,description="To Verify IW Reflection when Prompt and Collect Node CheckBox checked")
	public void VerifyPromptandCollectNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyPromptandColletNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyPromptandCollectNodeAccess Assertion Failed");
	}
	
	@Test(priority=24,description="To Verify IW Reflection when Flow Connecter Node CheckBox checked")
	public void VerifyFlowConnecterNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyFlowConnecterNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyFlowConnecterNodeAccess Assertion Failed");
	}
	
	@Test(priority=25,description="To Verify IW Reflection when Return Node CheckBox checked")
	public void VerifyReturnNodeAccess() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyReturnNodeAccess(iWRoleBasedAccessMatrixDetails), "VerifyReturnNodeAccess Assertion Failed");
	}
	
	@Test(priority=26,description="To Verify IW Reflection when Build Call Flow CheckBox Unchecked")
	public void VerifyBuildCallFlowCheckBoxWhenUncheckedInOCMwithIW() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"IWCallFlow").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyBuildCallFlowCheckBoxUnchecked(iWRoleBasedAccessMatrixDetails), "VerifyCopyCheckBoxWhenUncheckedwitIW Assertion Failed");
		iWRoleBasedAccessMatrixPage.BuildCallFlowInIW(iWRoleBasedAccessMatrixDetails);
		Assert.assertEquals(iWRoleBasedAccessMatrixPage.VerifyErrorMessage(), "Cannot build this callflow. Current callflow is in readonly state\nX", "VerifyCopyCheckBoxWhenUncheckedwitIW Assertion Failed");
	}*/
	
	@Test(priority=27,description="To Verify Group By Coloumn Header and Group Already Grouped coloumn")
	public void verifyGroupBy() {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("IW Role Based Access Matrix", "group By Column");
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("IW Role Based Access Matrix", "Already grouped Coloumn");
	}

	@Test(priority=28,description="To Verify Arrow Move For Previous and Next Page")
	public void verifyArrowMoveForPreviousAndNextPage() {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifyArrowMoveForPreviousAndNextPage(),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=29,description="To Verify Arrow Move For First and LastPage")
	public void verifyArrowMoveForFirstAndLastPage() {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifyArrowMoveForFirstAndLastPage(), "First and Last Page Assertion Failed");
	}
	
	@Test(priority=30,description="To Verify Total Number of Items Per Page Details")
	public void verifyTotalNumberOfItemsPerPageDetails() {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifyTotalNumberOfItemsPerPageDetails(), "Number of Items per Page assertion Failed");
	}

	@Test(priority=31,description="To Verify Number of Items Per Page Selection")
	public void verifyNumberOfItemsPerPageSelection() {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifyNumberOfItemsPerPage(), "Number of Items per Page Assertion Failed");
	}

	@Test(priority=32,description="To Verify Export to Excel Button")
	public void ExportToExcelButton() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.ExportToExcelButton(filePath), "Export Button assertion failed");
	}

	@Test(priority=33,description="To Verify Exported Data")//,dependsOnMethods="ExportToExcelButton")
	public void verifyExportedData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IW Role Based Access Matrix.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=34,description="To Verify Sort By Ascending")
	public void verifySortByAscending() throws Exception {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		iWRoleBasedAccessMatrixPage.verifySortByAscending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IW Role Based Access Matrix (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=35,description="To Verify Sort By Descending")
	public void verifySortByDescending() throws Exception {
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		iWRoleBasedAccessMatrixPage.verifySortByDescending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IW Role Based Access Matrix (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}
	
	@Test(priority=36,description="To Verify Export Excel with out Data")
	public void verifyExportToExcelWithoutData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.VerifyExportToExcelWithoutData(iWRoleBasedAccessMatrixDetails), "Export to Excel Without Data assertion failed");
	}
	
	@Test(priority=37,description="To Verify Search Is Not Equal To")
	public void SearchIsNotEqualTo() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(1);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifySearchIsnotEqualTo(iWRoleBasedAccessMatrixDetails.getRoleName()), "Search assertion Failed");
	}

	@Test(priority=38,description="To Verify Search Contains")
	public void SearchContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(2);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifySearchContains(iWRoleBasedAccessMatrixDetails.getRoleName()), "Search assertion Failed");
	}

	@Test(priority=39,description="To Verify Search DoesNotContains ")
	public void SearchDoesNotContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(3);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifySearchDoesNotContains(iWRoleBasedAccessMatrixDetails.getRoleName()), "Search assertion Failed");
	}

	@Test(priority=40,description="To Verify Search Starts with")
	public void SearchStartsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(4);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifySearchStartsWith(iWRoleBasedAccessMatrixDetails.getRoleName()), "Search assertion Failed");
	}

	@Test(priority=41,description="To Verify Search Ends With")
	public void SearchEndsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"TestData").getTestData().get(5);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifySearchEndsWith(iWRoleBasedAccessMatrixDetails.getRoleName()), "Search assertion Failed");
	}
	
	@Test(priority=42,description="To Verify Search Page")
	public void searchPage() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TestData").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertFalse(iWRoleBasedAccessMatrixPage.clearAll(iWRoleBasedAccessMatrixDetails),"ClearAll Assertion Failed");
		screenshot.captureScreen("IWRoleBasedAccessMatrixTest", "clearall");
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifyclose());
		screenshot.captureScreen("IWRoleBasedAccessMatrixTest", "SearchClose");
	}
	
	@Test(priority=43,description="To Verify Search without Search text Box")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TestData").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		iWRoleBasedAccessMatrixPage.searchwithoutextsearch(iWRoleBasedAccessMatrixDetails);
		Assert.assertEquals(iWRoleBasedAccessMatrixPage.VerifyMessage(), "Please enter the text to search or remove the filter", "searchwithoutSearchTextbox Assertion Failed");
	}

	@Test(priority=44,description="To Verify Search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IWRoleBasedAccessMatrixData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TestData").getTestData().get(0);
		IWRoleBasedAccessMatrixDetails iWRoleBasedAccessMatrixDetails=new IWRoleBasedAccessMatrixDetails(map);
		IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifyinvalidsearchwithwrongdata(iWRoleBasedAccessMatrixDetails),"invalidsearchwithwrongdata");
		screenshot.captureScreen("IWRoleBasedAccessMatrixTest","Invalid Search with wrong data");
		Assert.assertTrue(iWRoleBasedAccessMatrixPage.verifyclearsearch(), "Clear All Assertion Failed");
	}	
	
	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("IWRoleBasedAccessMatrixTest", method.getName());
		 IWRoleBasedAccessMatrixPage iWRoleBasedAccessMatrixPage=PageFactory.createPageInstance(driver,IWRoleBasedAccessMatrixPage.class);
         iWRoleBasedAccessMatrixPage.switchToWindow(0);
		driver.navigate().refresh();
	}

}
