package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.tetherfi.model.user.DeskManagerDetails;

import com.tetherfi.pages.DeskManagerPage;

import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class DeskManagerTest extends BaseTest {
Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
	public void NavigatetoDeskManagerPage() {
		 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMPage();
	     OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
	     Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
	     ocmHomePage.navigateToDeskManagerPage();
	     DeskManagerPage deskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
	     Assert.assertTrue(deskManagerPage.isDeskManagerPageDisplayed(), "Desk Manager page assertion failed");
	}
	
	/*@Test(priority=1)
    public void DeskManagerPage() {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
    	Assert.assertTrue(DeskManagerPage.verifylogo(),"FaxSenderslogo assertion failed");
    	Assert.assertTrue(DeskManagerPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("DeskManagerBookTest","maximize window");
    	Assert.assertTrue(DeskManagerPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("DeskManagerBookTest","minimize window");
    }
	@Test(priority=2)
	    public void VerifyDropdownForAllTheColumns() {
			DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
			Assert.assertTrue(DeskManagerPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	    }
	    
	@Test(priority=3)
	    public void VerifyColumnsHeaderEnable() {
			DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
			Assert.assertTrue(DeskManagerPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	    }
	    
	@Test(priority=4)
	    public void VerifyColumnsHeaderDisable() {
			DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
			Assert.assertFalse(DeskManagerPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	    }
	
	
	
	@Test(priority=19)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
        Assert.assertTrue(DeskManagerPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=20)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Desk Manager.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
    	Assert.assertTrue(DeskManagerPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=25)
    public void SearchClearSearch() throws Exception
    {
  	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DeskManagerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        DeskManagerDetails deskManagerDetails = new DeskManagerDetails(map);
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
        Assert.assertTrue(DeskManagerPage.verifyinvalidsearchwithwrongdata(deskManagerDetails), "InvalidSearchAssertionFailed");
        screenshot.captureScreen("DeskManagerTest", "Invalid Search");
        Assert.assertTrue(DeskManagerPage.verifyclearsearch(), "ClearSearch Assertion Failed");
    }
    
    @Test(priority=26)
    public void ExporttoExcelWithoutData() throws Exception
    {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\DeskManagerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        DeskManagerDetails deskManagerDetails = new DeskManagerDetails(map);
        Assert.assertTrue(DeskManagerPage.ExporttoExcelWithoutData(deskManagerDetails));
    }
  
    @Test(priority=27)
    public void SortingByAscending() throws IOException {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
		DeskManagerPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Desk Manager (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(DeskManagerPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=28)
    public void SortingByDescending() throws IOException {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
		DeskManagerPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Desk Manager (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(DeskManagerPage.verifyexportToExcelSheet(maplist));
    }
	
    @Test(priority=29)
    public void GroupBy()
    {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
    	Assert.assertTrue(DeskManagerPage.groupby());
        screenshot.captureScreen("DeskManagerTest", "GroupBy");
    	Assert.assertTrue(DeskManagerPage.groupby());
        screenshot.captureScreen("DeskManagerTest", "AlreadyGroupBy");
    }
    
    @Test(priority=30)
    public void VerifyArrowMoveForPreviousAndNextPage() {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
    	Assert.assertTrue(DeskManagerPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyArrowMoveForFirstAndLastPage() {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
        Assert.assertTrue(DeskManagerPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    @Test(priority=32)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
        Assert.assertTrue(DeskManagerPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=33)
    public void VerifyNumberOfItemsPerPageSelection() {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
        Assert.assertTrue(DeskManagerPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=34)
    public void searchwithoutSearchTextbox() throws IOException {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
		DeskManagerPage.searchwithoutextsearch();
        Assert.assertEquals(DeskManagerPage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }*/
	
	//@Test()
	public void VerifyDeskManagerThresholdDetailsPage() throws Exception {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DeskManagerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        DeskManagerDetails deskManagerDetails = new DeskManagerDetails(map);
        Assert.assertTrue(DeskManagerPage.verifyDeskManagerThresholdDetailsPage(deskManagerDetails),"threshold popup displayed");
        Assert.assertTrue(DeskManagerPage.verifyaddNewRowButton());
        Assert.assertTrue(DeskManagerPage.verifysaveButton());
        Assert.assertTrue(DeskManagerPage.verifycancelbutton());
	}
    
	@Test()
	public void VerifyDeskManagerLabels() throws Exception {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DeskManagerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        DeskManagerDetails deskManagerDetails = new DeskManagerDetails(map);
        Assert.assertTrue(DeskManagerPage.verifyDeskManagerThresholdDetailsPage(deskManagerDetails),"threshold popup displayed");
        Assert.assertTrue(DeskManagerPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}
	
	@Test()
	public void addNewRow() throws Exception {
		DeskManagerPage DeskManagerPage = PageFactory.createPageInstance(driver, DeskManagerPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DeskManagerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        DeskManagerDetails deskManagerDetails = new DeskManagerDetails(map);
        Assert.assertTrue(DeskManagerPage.verifyDeskManagerThresholdDetailsPage(deskManagerDetails),"threshold popup displayed");
        DeskManagerPage.addNewRow(deskManagerDetails);
	}
	
	@AfterMethod
	    public void afterEachMethod(Method method) {
	    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("DeskManagerTest",method.getName());
	        driver.navigate().refresh();    }
}
