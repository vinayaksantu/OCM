package com.tetherfi.test.ivr;

import com.tetherfi.model.ivr.IvrConfigDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrConfigPage;
import com.tetherfi.pages.IvrConfigPageWMC;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class IvrConfigTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToIvrConfigPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("IVR");
		IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
		Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
		ivrPage.navigateToIvrConfigPage();
		IvrConfigPageWMC ivrConfigPageWMC = PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.isIvrConfigPageDisplayed(), "Ivr config page assertion failed");
		screenshot.captureScreen(driver,"IvrConfigPageWMC","IvrConfigTest");
	}

	@Test(priority=1)
	public void IvrConfigPage() {
		IvrConfigPageWMC ivrConfigPageWMC = PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.verifylogo(),"CallBackAnnouncement logo assertion failed");
		Assert.assertTrue(ivrConfigPageWMC.maximizewindow(),"Fullscreen Assertion Failed"); 
		Assert.assertTrue(ivrConfigPageWMC.minimizewindow(), "Restored Assertion Failed");
	}

	@Test(priority=2)
	public void VerifyDropdownForAllTheColumns() {
		IvrConfigPageWMC ivrConfigPageWMC = PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=3)
	public void VerifyColumnsHeaderEnable() {
		IvrConfigPageWMC ivrConfigPageWMC = PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=4)
	public void VerifyColumnsHeaderDisable() {
		IvrConfigPageWMC ivrConfigPageWMC = PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		Assert.assertFalse(ivrConfigPageWMC.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=5)
	public void AddNewIvrConfigRecord() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC = PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.addNewIvrConfigRecord(ivrConfigDetails);
		Assert.assertEquals(ivrConfigPageWMC.getSuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
	}

	@Test(priority=6)
	public void AddDuplicateIvrConfigRecord() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC = PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.addNewIvrConfigRecord(ivrConfigDetails);
		Assert.assertEquals(ivrConfigPageWMC.verifySuccessMessage(),"Duplicate Parameter", "Duplicate record assertion failed");
	}

	@Test(priority=7)//,dependsOnMethods ="AddNewIvrConfigRecord")
	public void VerifyAuditTrialReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyIvrConfigCreate(ivrConfigDetails,"Create"), "Audit Trail Report assertion failed");
	}

	@Test(priority=8)
	public void AddNewEmptyConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
		IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.addEmptyConfigRecord(ivrConfigDetails);
		Assert.assertEquals(ivrConfigPageWMC.verifySuccessMessage(), "Please Provide Parameter, Value", "Add invalid Record assertion falied");	
	}

	@Test(priority=9)
	public void AddInvalidParameterConfigRecord() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(1);
		IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.addNewIvrConfigRecord(ivrConfigDetails);
		Assert.assertEquals(ivrConfigPageWMC.verifySuccessMessage(),"Please Provide Parameter", "Add invalid record assertion failed");
	}

	@Test(priority=10)
	public void AddInvalidValueConfigRecord() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(2);
		IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.addNewIvrConfigRecord(ivrConfigDetails);
		Assert.assertEquals(ivrConfigPageWMC.verifySuccessMessage(),"Please Provide Value", "Add invalid record assertion failed");
	}

	@Test(priority=11)
	public void VerifyCancelBtnAtAddConfigRecord(){
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.clickOnAddRecord();
		ivrConfigPageWMC.clickOnCancelBtn();
		Assert.assertFalse(ivrConfigPageWMC.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}

	@Test(priority=12)
	public void EditIvrConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.editIvrConfigRecord(IvrConfigDetails);
		Assert.assertEquals(ivrConfigPageWMC.getSuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
	}

	@Test(priority=13)//,dependsOnMethods="EditIvrConfigRecord")
	public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyIvrConfigUpdate(IvrConfigDetails,"Update"));
	}

	@Test(priority=14)
	public void EditIvrConfigRecordWithoutModifyReason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
		IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.EditIvrConfigRecordWithoutModifyReason(ivrConfigDetails);
		Assert.assertEquals(ivrConfigPageWMC.getSuccessMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
	}

	@Test(priority=15)
	public void VerifyCancelBtnAtEditConfigRecord() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.clickOnEditButton(IvrConfigDetails);
		ivrConfigPageWMC.clickOnCancelBtn();
		Assert.assertFalse(ivrConfigPageWMC.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
	}

	@Test(priority=16)
	public void searchPage() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		Assert.assertFalse(ivrConfigPageWMC.clearAll(ivrConfigDetails),"ClearAll Assertion Failed");
		Assert.assertTrue(ivrConfigPageWMC.verifyclose());
	}

	@Test(priority=17)
	public void searchwithoutSearchTextbox() throws IOException {
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.searchwithoutextsearch();
		Assert.assertEquals(ivrConfigPageWMC.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}

	@Test(priority=18)
	public void ExportToExcel() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.verifyExportToExcel(filePath));
	}

	@Test(priority=19)
	public void ExportToExcelData() throws Exception{	
    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IVR Config.xlsx";
	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
	Assert.assertTrue(ivrConfigPageWMC.verifyexportToExcelSheet(maplist));	
	}

	@Test(priority=20)
	public void DeleteWithoutDeleteReasonIvrConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.DeleteRecordWithoutModifyReason(IvrConfigDetails);
		Assert.assertEquals(ivrConfigPageWMC.verifySuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
	}

	@Test(priority=21)
	public void VerifyCancelBtnAtDeleteConfigRecord() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.clickOnDeleteButton(IvrConfigDetails);
		ivrConfigPageWMC.clickOnDeleteCancelBtn();
		Assert.assertFalse(ivrConfigPageWMC.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=22)
	public void DeleteIvrConfigRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.deleteIvrConfigRecord(IvrConfigDetails);
		Assert.assertEquals(ivrConfigPageWMC.getSuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
	}

	@Test(priority=23)//,dependsOnMethods= {"DeleteIvrConfigRecord"})
	public void VerifyAuditTrialReportForDelete() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyIvrConfigdelete(IvrConfigDetails,"Delete"));
	}

	@Test(priority=24)
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.verifyinvalidsearchwithwrongdata(IvrConfigDetails),"invalidsearchwithwrongdata");
		Assert.assertTrue(ivrConfigPageWMC.verifyclearsearch(), "Clear All Assertion Failed");
	}

	@Test(priority=25)
	public void ExporttoExcelWithoutData() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.ExporttoExcelWithoutData(IvrConfigDetails));
	}

	@Test(priority=26)//, dependsOnMethods="ExportToExcel")
	public void SortingByAscending() throws IOException {
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.SortByAscending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IVR Config.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(ivrConfigPageWMC.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=27)//, dependsOnMethods="ExportToExcel")
	public void SortingByDescending() throws IOException {
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		ivrConfigPageWMC.SortByDescending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\IVR Config (3).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(ivrConfigPageWMC.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=28)
	public void GroupBy(){
		IvrConfigPageWMC ivrConfigPageWMC= PageFactory.createPageInstance(driver,IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.groupby());
	}

	@Test(priority=29)
	public void VerifyArrowMoveForPreviousAndNextPage() {
		IvrConfigPageWMC ivrConfigPageWMC= PageFactory.createPageInstance(driver,IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}

	@Test(priority=30)
	public void VerifyArrowMoveForFirstAndLastPage() {
		IvrConfigPageWMC ivrConfigPageWMC= PageFactory.createPageInstance(driver,IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=31)
	public void VerifyTotalNumberOfItemsPerPageDetails() {
		IvrConfigPageWMC ivrConfigPageWMC= PageFactory.createPageInstance(driver,IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=32)
	public void VerifyNumberOfItemsPerPageSelection() {
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		Assert.assertTrue(ivrConfigPageWMC.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}

	@Test(priority=33)
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
		IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
		Assert.assertTrue(ivrConfigPageWMC.verifyDatabase(IvrConfigDetails.getQuery()));
	}

	@Test(priority=34)
    public void VerifySearchIsNotEqualTo() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
        Assert.assertTrue(ivrConfigPageWMC.verifySearchIsNotEqualTo(ivrConfigDetails.getParameter()), "Search Assertion failed");
    }
	
	@Test(priority=35)
    public void VerifySearchContains() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
        Assert.assertTrue(ivrConfigPageWMC.verifySearchContains(ivrConfigDetails.getParameter()), "Contains Assertion Failed");
    }

	@Test(priority=36)
    public void VerifySearchDoesNotContains() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
        Assert.assertTrue(ivrConfigPageWMC.verifySearchDoesNotContains(ivrConfigDetails.getParameter()), "Search Assertion failed");
    }
	
    @Test(priority=37)
    public void VerifySearchStartsWith() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
        Assert.assertTrue(ivrConfigPageWMC.verifySearchStartsWith(ivrConfigDetails.getParameter()), "Search Assertion Failed");
    }
    
    @Test(priority=38)
    public void VerifySearchEndsWith() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);
        IvrConfigPageWMC ivrConfigPageWMC=PageFactory.createPageInstance(driver, IvrConfigPageWMC.class);
        Assert.assertTrue(ivrConfigPageWMC.verifySearchEndsWith(ivrConfigDetails.getParameter()), "Search Assertion Failed");
    }
	
	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("IVRConfigTest",method.getName());
		driver.navigate().refresh();
	}

}
