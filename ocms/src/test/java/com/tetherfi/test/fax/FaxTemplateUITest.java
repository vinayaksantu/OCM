package com.tetherfi.test.fax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxTemplatePage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxTemplateUITest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
    public void NavigateToFaxTemplatePage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxTemplatePage();
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.isFaxTemplatePageDisplayed(), "FAX page assertion failed");
    }
	/*@Test (priority=1)
    public void FaxTemplatePage() {
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
    	Assert.assertTrue(faxTemplatePage.verifylogo(),"FaxLineConfig logo assertion failed");
    	Assert.assertTrue(faxTemplatePage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("FaxTemplateUITest","maximize window");
    	Assert.assertTrue(faxTemplatePage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("FaxTemplateUITest","minimize window");
    }
	@Test(priority=2)
	public void VerifyFaxTemplateApprovedDataPage() 
	{
		FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
     }
       
	@Test(priority=3)
       public void VerifyFaxTemplateAuditTrailDataPage() {
           FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
           faxTemplatePage.selectFaxTemplateAuditTrailTab();
           Assert.assertTrue(faxTemplatePage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
       }
       
	@Test(priority=4)
       public void VerifyMakeFaxTemplateChangeButton() {
           FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
           faxTemplatePage.selectFaxTemplateAuditTrailTab();
           faxTemplatePage.selectMakeFaxTemplateChanges();
           Assert.assertTrue(faxTemplatePage.verifyAddNewFaxTemplatesRecordButton(), "add new Agent Settings record button assertion failed");
           Assert.assertTrue(faxTemplatePage.verifyGoBackButton(), "Go back button assertion failed");
           Assert.assertTrue(faxTemplatePage.verifyExportToExcelButton(), "export to excel button assertion failed");
           Assert.assertTrue(faxTemplatePage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
       }
       
    @Test(priority=5)
    public void VerifyDropdownForAllTheColumns() {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertTrue(faxTemplatePage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
       }
     
    @Test(priority=6)
    public void VerifyColumnsHeaderEnable() {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertTrue(faxTemplatePage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
     }
       
    @Test(priority=7)
    public void VerifyColumnsHeaderDisable() {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertFalse(faxTemplatePage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
      }
    
    @Test(priority=8)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertTrue(faxTemplatePage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=9)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertTrue(faxTemplatePage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    @Test(priority=10)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertTrue(faxTemplatePage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    @Test(priority=11)
    public void VerifyNumberOfItemsPerPageSelection() {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertTrue(faxTemplatePage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    */
    @Test(priority=12)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertTrue(faxTemplatePage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=13)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Template.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        Assert.assertTrue(faxTemplatePage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=14)
    public void VerifyExportToExcelWithoutData() throws Exception {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
        Assert.assertTrue(faxTemplatePage.ExporttoExcelWithoutData(faxTemplateDetails));
       }
    
    @Test(priority=15)
    public void SortingByAscending() throws IOException {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        faxTemplatePage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Template (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxTemplatePage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=16)
    public void SortingByDescending() throws IOException {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        faxTemplatePage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Template (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxTemplatePage.verifyexportToExcelSheet(maplist));
    }
    
    /*@Test(priority=17)
    public void VerifyDragAndDrop() {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        faxTemplatePage.dragColumntoGroup("Template Name");
        Assert.assertTrue(faxTemplatePage.verifyDragColumntoGroup("Template Name"),"drag and drop assertion failed");
    }
    @Test(priority=18)
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        faxTemplatePage.selectFaxTemplateAuditTrailTab();
        faxTemplatePage.selectMakeFaxTemplateChanges();
        faxTemplatePage.dragColumntoGroup("Template Name");
        faxTemplatePage.dragColumntoGroup("Template Name");
        Assert.assertTrue(faxTemplatePage.verifyDragColumntoGroup("Template Name"),"drag and drop assertion failed");
    }
    
    @Test(priority=19)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
    	Assert.assertTrue(faxTemplatePage.verifyDatabase(faxTemplateDetails.getQuery()));
    }
    
    @Test(priority=20)
    public void searchPage() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertFalse(faxTemplatePage.clearAll(faxTemplateDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("FaxTemplateUITest","Clearall");
        Assert.assertTrue(faxTemplatePage.verifyclose());
        screenshot.captureScreen("FaxTemplateUITest","SearchClose");
    }
    @Test(priority=21)
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
    	faxTemplatePage.searchwithoutextsearch(faxTemplateDetails);
    	Assert.assertFalse(faxTemplatePage.getErrorMsg());
    }
    
    @Test(priority=22)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    FaxTemplateDetails faxTemplateDetails = new FaxTemplateDetails(map);
    	FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.verifyApprovedSectionData(faxTemplateDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("FaxTemplateUITest","InvalidSearch");
        Assert.assertTrue(faxTemplatePage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen("FaxTemplateUITest","ClearSearch");    
        }
        */
    
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("FaxTemplateUITest",method.getName());
	        driver.navigate().refresh();
	}
    
}
