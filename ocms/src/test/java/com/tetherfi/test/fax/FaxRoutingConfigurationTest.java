package com.tetherfi.test.fax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxRoutingConfigurationPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxRoutingConfigurationTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToFaxLineConfigPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxRoutingConfigurationPage();
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.isFaxRoutingConfigurationPageDisplayed(), "FAX page assertion failed");
    }
    
    //@Test(priority=1)
    public void FaxRoutingConfigurationPage() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.verifylogo(),"FaxLineConfig logo assertion failed");
    	Assert.assertTrue(faxRoutingConfigurationPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","FaxRoutingConfigurationTest");
    	Assert.assertTrue(faxRoutingConfigurationPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","FaxRoutingConfigurationTest");
    }
    
    @Test(priority=17)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","FaxRoutingConfigurationTest");
    }
    
    @Test(priority=18)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Routing Configuration.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","FaxRoutingConfigurationTest");
    }
    //@Test(priority=26)
    public void ExporttoExcelWithoutData() throws Exception
    {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
        Assert.assertTrue(faxRoutingConfigurationPage.ExporttoExcelWithoutData(faxLineConfigDetails));
        screenshot.captureScreen( driver,"ExporttoExcelWithoutData", "FaxRoutingConfigurationTest");
    }
  
    @Test(priority=27)
    public void SortingByAscending() throws IOException {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Routing Configuration (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxRoutingConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=28)
    public void SortingByDescending() throws IOException {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Routing Configuration (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxRoutingConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    //@Test(priority=29)
    public void GroupBy()
    {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","FaxRoutingConfigurationTest");
    	Assert.assertTrue(faxRoutingConfigurationPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","FaxRoutingConfigurationTest");
    }
    
    //@Test(priority=30)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    //@Test(priority=31)
    public void VerifyArrowMoveForFirstAndLastPage() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    //@Test(priority=32)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    //@Test(priority=33)
    public void VerifyNumberOfItemsPerPageSelection() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    //@Test(priority=34)
    public void VerifyDropdownForAllTheColumns() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    //@Test(priority=35)
    public void VerifyColumnsHeaderEnable() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    //@Test(priority=36)
    public void VerifyColumnsHeaderDisable() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertFalse(faxRoutingConfigurationPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) {
        screenshot.captureScreen(driver,method.getName(),"FaxLineConfigTest");
    }
}
