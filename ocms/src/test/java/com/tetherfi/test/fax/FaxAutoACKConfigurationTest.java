package com.tetherfi.test.fax;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxAutoACKConfigurationDetails;
import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.pages.FaxAutoACKConfigurationPage;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxAutoACKConfigurationTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);

	//@BeforeClass
    public void AddFaxLineConfigRecord() throws IOException {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxLineConfigPage();
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        Assert.assertTrue(faxLineConfigPage.isFaxLineConfigPageDisplayed(), "FAX page assertion failed");
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
        faxLineConfigPage.addNewFaxLineConfigRecord(faxLineConfigDetails);
        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(), "Record Created Successfully");
    }
    @BeforeMethod
    public void NavigateToFaxAutoACKConfigurationPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxAutoACKConfigurationPage();
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.isFaxAutoACKConfigurationPageDisplayed(), "FAX page assertion failed");
    }

    //@Test(priority=1)
    public void FaxAutoACKConfigurationPage() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
    	Assert.assertTrue(faxAutoAckConfigurationPage.verifylogo(),"FaxSenderslogo assertion failed");
    	Assert.assertTrue(faxAutoAckConfigurationPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","FaxAutoACKConfigurationTest");
    	Assert.assertTrue(faxAutoAckConfigurationPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","FaxAutoACKConfigurationTest");
    }
    
    //@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    //@Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    //@Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertFalse(faxAutoAckConfigurationPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test
    public void GroupBy()
    {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
    	Assert.assertTrue(faxAutoAckConfigurationPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","FaxLineConfigTest");
    	Assert.assertTrue(faxAutoAckConfigurationPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","FaxLineConfigTest");
    }
    
    //@Test(priority=17)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","FaxLineConfigtTest");
    }
    
    //@Test(priority=18)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Auto ACK Configuration.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
    	Assert.assertTrue(faxAutoAckConfigurationPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","FaxLineConfigTest");
    }
    
    //@Test(priority=26)
    public void ExporttoExcelWithoutData() throws Exception
    {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails = new FaxAutoACKConfigurationDetails(map);
        Assert.assertTrue(faxAutoAckConfigurationPage.ExporttoExcelWithoutData(faxAutoAckConfigurationDetails));
        screenshot.captureScreen( driver,"ExporttoExcelWithoutData", "FaxLineConfigTest");
    }
  
    //@Test(priority=27)
    public void SortingByAscending() throws IOException {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Auto ACK Configuration (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    //@Test(priority=28)
    public void SortingByDescending() throws IOException {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Auto ACK Configuration (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    //@Test(priority=30)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
    	Assert.assertTrue(faxAutoAckConfigurationPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    //@Test(priority=31)
    public void VerifyArrowMoveForFirstAndLastPage() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    //@Test(priority=32)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    //@Test(priority=33)
    public void VerifyNumberOfItemsPerPageSelection() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
}
