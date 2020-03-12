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
	
	@Test (priority=1)
    public void FaxTemplatePage() {
        FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
    	Assert.assertTrue(faxTemplatePage.verifylogo(),"FaxLineConfig logo assertion failed");
    	Assert.assertTrue(faxTemplatePage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("FaxTemplateUITest","maximize window");
    	Assert.assertTrue(faxTemplatePage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("FaxTemplateUITest","minimize window");
    }
	
	@Test(priority=2)
	public void VerifyFaxTemplateApprovedDataPage() {
		FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
        Assert.assertTrue(faxTemplatePage.verifyApprovedDataHeader(),"Approved table assertion failed");
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
		Assert.assertTrue(faxTemplatePage.verifyAddNewFaxTemplateRecordButton(), "add new FaxTemplate record button assertion failed");
		Assert.assertTrue(faxTemplatePage.verifyGoBackButton(), "Go back button assertion failed");
		Assert.assertTrue(faxTemplatePage.verifyExportToExcelButton(), "export to excel button assertion failed");
		Assert.assertTrue(faxTemplatePage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
	}
	
	@Test(priority=5)
	public void VerifyDropDownForAllTheColumns() {
		FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifyDropDownOfAllHeaders(), "Columns DropDowns assertion failed");
	}
	
	@Test(priority=6)
	public void VerifyColumnsHeaderEnable() {
		FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifycolumnsHeaderEnabled(), "Columns Header Enable Assertion Failed");
	}
	
	@Test(priority=7)
	public void VerifyColumnsHeaderdisable() {
		FaxTemplatePage faxTemplatePage = PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertFalse(faxTemplatePage.verifycolumnsHeaderDisbaled(), "Columns Header disable Assertion Failed");
	}
	
	@Test(priority=8)
     public void VerifyArrowMoveForPreviousAndNextPage() {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifyArrowMoveForPreviousAndNextPage(2), "Arrow Moves for Previous and Next Page Assertion Failed");
	}
	
	@Test(priority=9)
	public void VerifyArrowMoveForFirstAndLastPage() {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifyArrowMoveForFirstAndLastPage(2), "Arrow Moves for First and Last page assertion Failed");
	}
	
	@Test(priority=10)
	public void VerifyToatalNumberOfItemsPerPageDetails() {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifyTotalNumberOfItemsPerPageDetails(3), "Number of Items assertion Failed");
	}
	
	@Test(priority=11)
	public void VerifyNumberOfItemsPerPageSelection() {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifyNumberOfItemsPerPage(2), "Number of Items Per Page Selection assertion failed");
	}
	
	@Test(priority=12)
	public void ExportToExcel() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifyExportToExcel(filePath), "Export to Excel Assertion Failed");
	}
	
	@Test(priority=13)
	public void ExportToExcelData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Template.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=14)
	public void ExportToExcelWithoutData() throws Exception {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		Assert.assertTrue(faxTemplatePage.ExporttoExcelWithoutData(faxTemplateDetails));
	}
	
	@Test(priority=15)
	public void SortByAscending() throws IOException{
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		faxTemplatePage.SortByAscending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Template (1).xlsx";
		List<Map<String, String>> maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(faxTemplatePage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=16)
	public void SortByDescending() throws IOException{
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		faxTemplatePage.SortByDescending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Template (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(faxTemplatePage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=17)
	public void  VerifyDragAndDrop() {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		faxTemplatePage.dragColumntoGroup("Template Name");
		Assert.assertTrue(faxTemplatePage.verifyDragColumntoGroup("Template Name"), "Drag and drop Assertion Failed");
	}
	
	@Test(priority=18)
	public void  verifyDragAndDropAlreadyGroupedHeader() {
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		faxTemplatePage.dragColumntoGroup("Template Name");
		faxTemplatePage.dragColumntoGroup("Template Name");
		Assert.assertTrue(faxTemplatePage.verifyDragColumntoGroup("Template Name"), "Drag and drop Assertion Failed");
	}
	
	@Test(priority=19)
	public void dataBase() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String>map=new ExcelReader(filePath,"Queries").getTestData().get(0);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		Assert.assertTrue(faxTemplatePage.verifyDatabase(faxTemplateDetails.getQuery()), "dataBase assertion Failed");
	}
	
	@Test(priority=20)
	public void SearchPage() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		Assert.assertFalse(faxTemplatePage.clearAll(faxTemplateDetails), "ClearAll Assertion Failed");
		screenshot.captureScreen("FaxTemplteUITest", "clearAll");
		Assert.assertTrue(faxTemplatePage.verifyclose());
		screenshot.captureScreen("FaxTemplateUITest","verifyclose");
		
	}
	
	@Test(priority=21)
	public void SearchWithoutSearchTextBox() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.searchwithoutextsearch(faxTemplateDetails);
		Assert.assertFalse(faxTemplatePage.getErrorMsg());
	}
	
	@Test(priority=22)
	public void SearchClearSearch() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		Assert.assertTrue(faxTemplatePage.verifyApprovedSectionData(faxTemplateDetails), "Approved Data Search assertion failed");
	    screenshot.captureScreen("FaxtTemplateUITest", "Invalid Search With Wrong data");
	    Assert.assertTrue(faxTemplatePage.verifyclearsearch(), "Clear Search assertion failed");
	}
	
	@Test(priority=23)
	public void  VerifySearchIsNotEqualTo() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(2);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifySearchIsNotEqualTo(faxTemplateDetails.getTemplateName()));
		}
	
	@Test(priority=24)
	public void  VerifySearchContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(3);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifySearchContains(faxTemplateDetails.getTemplateName()));
		}
	
	@Test(priority=25)
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(3);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifySearchDoesNotContains(faxTemplateDetails.getTemplateName()));
		}
	
	@Test(priority=26)
	public void  VerifySearchStartsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(4);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifySearchStartsWith(faxTemplateDetails.getTemplateName()));
		}
	
	@Test(priority=27)
	public void  VerifySearchEndsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(5);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver, FaxTemplatePage.class);
		faxTemplatePage.selectFaxTemplateAuditTrailTab();
		faxTemplatePage.selectMakeFaxTemplateChanges();
		Assert.assertTrue(faxTemplatePage.verifySearchEndsWith(faxTemplateDetails.getTemplateName()));
		}
	
	@AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("FaxTemplateUITest",method.getName());
	        driver.navigate().refresh();
	}

}
	