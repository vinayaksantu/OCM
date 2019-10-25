package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.IvrConfigDetails;
import com.tetherfi.model.sms.SmsResponseTemplateDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrConfigPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.SmsResponseTemplatePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class IvrConfigUITest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
	public void NavigateToIvrConfigPage(){
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
	    Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
	    ocmHomePage.navigateToTab("IVR");
	    IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        ivrPage.navigateToIvrConfigPage();
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.isIvrConfigPageDisplayed(), "Ivr config page assertion failed");
    	screenshot.captureScreen(driver,"IvrConfigPage","IvrConfigTest");
		}
	
	/*@Test(priority=1)
	public void IvrConfigPage() {
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		Assert.assertTrue(ivrConfigPage.verifylogo(),"CallBackAnnouncement logo assertion failed");
    	Assert.assertTrue(ivrConfigPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","IvrConfigTest");
    	Assert.assertTrue(ivrConfigPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","IvrConfigTest");
		}		

	@Test(priority=2)
	public void VerifyIvrConfigApprovedDataPage() 	{
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		Assert.assertTrue(ivrConfigPage.verifyApprovedDataTableHeaders(), "Approved data table assertion failed");
		}
	
	@Test(priority=3)
	public void VerifyIvrConfigAuditTrailPage() {
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		Assert.assertTrue(ivrConfigPage.verifyAuditTrailDataTableHeaders(),"Audit Trail Data table assertion failed");		
	}
	
	@Test(priority=4)
	public void VerifyMakeIvrConfigChangesButton() {
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		Assert.assertTrue(ivrConfigPage.verifyAddNewIvrConfigRecordButton(),"Add new Ivr Config Record Button Assertion failed");
		Assert.assertTrue(ivrConfigPage.verifyGoBackButton(),"Go Back button assertion failed");
		Assert.assertTrue(ivrConfigPage.verifyExportToExcelButton(),"Export Excel button assertion failed");
		Assert.assertTrue(ivrConfigPage.verifyMakerDataTableHeaders(), "Data table Assertion failed");
	}

	@Test(priority=5)
	public void VerifyDropDownForAllColumns() {
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		Assert.assertTrue(ivrConfigPage.verifyDropDownOfAllHeaders(),"Columns dropdown assertion failed");		
	}
	
	@Test(priority=6)
	public void VerifyColumnsHeaderEnable() {
		IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		Assert.assertTrue(ivrConfigPage.verifycolumnsHeaderEnabled(),"Columns header assertion failed");		
		}
	
	@Test(priority=7)
	public void VerifyColumnsHeaderDisable() {
		IvrConfigPage ivrConfigPage =PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		Assert.assertFalse(ivrConfigPage.verifycolumnsHeaderDisabled(), "Columns header assertion failed");
		}
	
	@Test(priority=8)
	public void VerfiyArrowMoveForPreviousAndNextPage() {
		IvrConfigPage ivrConfigPage =PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		Assert.assertTrue(ivrConfigPage.verifyArrowMoveForPreviousAndNextPage(),"Arrow move for Previous and next page assertion failed");
		}
	
	@Test(priority=9)
	public void VerifyArrowMoveForFirstAndLastPage() {
		IvrConfigPage ivrConfigPage =PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		Assert.assertTrue(ivrConfigPage.verifyArrowMoveForFirstAndLastPage(), "Arrow move for first and last page assertion failed");	
	}
	
	@Test(priority=10)
	public void VerifyNumberOfItemsPerPage() {
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		Assert.assertTrue(ivrConfigPage.verifyNumberOfItemsPerPage(), "Number of Items per page details assertion failed");		
	}
	
	@Test(priority=11)
	public void VerifyTotalNumberOfItemsPerPage() {
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		Assert.assertTrue(ivrConfigPage.verifyTotalNumberOfItemsPerPageDetails(),"Total number of items per page assertion failes");
	}
	
	@Test(priority=12)
	public void VerifyExportToExcel() throws Exception{
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		Assert.assertTrue(ivrConfigPage.verifyExportToExcel(filePath),"Export to excel Assertion failed");
	}
	
	@Test(priority=13)
	public void VerifyExportToExcelData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IVR Config.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		Assert.assertTrue(ivrConfigPage.verifyexportToExcelSheet(maplist),"Export to excel data assertion failed");
	}
	
	@Test(priority=14)
	public void VerifyExportToExcelWithoutData() throws Exception {
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver,IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xslx";		
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		Assert.assertTrue(ivrConfigPage.ExporttoExcelWithoutData(IvrConfigDetails));	
	}
	
	@Test(priority=15)
	public void VerifySortByAscending() throws IOException {
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();;
		ivrConfigPage.selectMakeIvrConfigChanges();
		ivrConfigPage.SortByAscending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Ivr Config.xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(ivrConfigPage.verifyexportToExcelSheet(maplist));		
	}
	
	@Test(priority=16)
	public void VerifySortByDescending() throws Exception {
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		ivrConfigPage.SortByDescending();
		String filePath=System
				.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IVR Config.xslx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(ivrConfigPage.verifyexportToExcelSheet(maplist));			
	}
	
	@Test(priority=17)
	public void VerifyDragAndDrop() {
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();
		//ivrConfigPage.
	}
	
	@Test(priority=18)
	public void VerifyDragAndDropOfAlreadyGroupedHeader() {
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		ivrConfigPage.selectIvrConfigAuditTrailTab();
		ivrConfigPage.selectMakeIvrConfigChanges();				
	}
	
	@Test(priority=19)
	public void dataBase() throws Exception{
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\IvrConfigData.xslx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		Assert.assertTrue(ivrConfigPage.verifyDatabase(IvrConfigDetails.getQuery()));				
	}
	
	@Test(priority=20)
	public void SearchPage() throws Exception {
	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
	IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver,IvrConfigPage.class);
	Assert.assertFalse(ivrConfigPage.clearAll(IvrConfigDetails),"Ivr Config detail assertion failed");
	screenshot.captureScreen("IvrConfigUITest", "ClearAll");
	Assert.assertTrue(ivrConfigPage.verifyclose());
	screenshot.captureScreen("IvrConfigUITest","SearchClose");
	}
	
	@Test(priority=21)
	public void SearchWithoutSearchTextBox() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
	    ivrConfigPage.searchwithoutextsearch();
	    Assert.assertFalse(ivrConfigPage.getErrorMsg());
	    }
	
	@Test(priority=22)
    public void SearchClearSearch() throws Exception{
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
    	IvrConfigPage ivrConfigPage=PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.VerifyApprovedSectionData(IvrConfigDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("IvrConfigUITest","Invalid Search with wrong data");
        Assert.assertTrue(ivrConfigPage.verifyclearsearch(), "Clear All Assertion Failed");
    }*/
	
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("IvrConfigUITest",method.getName());
	        driver.navigate().refresh();
	}	
}

