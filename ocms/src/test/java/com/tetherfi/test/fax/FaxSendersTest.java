package com.tetherfi.test.fax;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.FaxSendersDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class FaxSendersTest extends BaseTest {
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
    public void NavigateToFaxSendersPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxSendersPage();
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.isFaxSendersPageDisplayed(), "FAX page assertion failed");
    }

    @Test(priority=1)
    public void FaxSendersPage() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	Assert.assertTrue(faxSendersPage.verifylogo(),"FaxSenderslogo assertion failed");
    	Assert.assertTrue(faxSendersPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","FaxSendersTest");
    	Assert.assertTrue(faxSendersPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","FaxSendersTest");
    }
    
    //@Test
    public void AddFaxSendersRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);

        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
        Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Created Successfully");
    }
    //@Test(dependsOnMethods = {"AddFaxSendersRecord"})
    public void EditFaxSendersRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);

        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.editFaxSendersRecord(faxSendersDetails);
        Assert.assertEquals(faxSendersPage.getSuccessMessage(),"Record Updated Successfully");
    }

    //@Test(dependsOnMethods = {"EditFaxSendersRecord"})
    public void DeleteFaxSendersRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);

        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.deleteFaxSendersRecord(faxSendersDetails);
        Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Deleted Successfully");

    }
    @Test(priority=17)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","FaxSendersTest");
    }
    
    @Test(priority=18)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Senders.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	Assert.assertTrue(faxSendersPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","FaxSendersTest");
    }
    
    @Test(priority=26)
    public void ExporttoExcelWithoutData() throws Exception
    {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        Assert.assertTrue(faxSendersPage.ExporttoExcelWithoutData(faxSendersDetails));
        screenshot.captureScreen( driver,"ExporttoExcelWithoutData", "FaxSendersTest");
    }
    @Test(priority=27)
    public void SortingByAscending() throws IOException {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Senders (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxSendersPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=28)
    public void SortingByDescending() throws IOException {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Senders (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxSendersPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=29)
    public void GroupBy()
    {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	Assert.assertTrue(faxSendersPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","FaxSendersTest");
    	Assert.assertTrue(faxSendersPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","FaxSendersTest");
    }
    
    @Test(priority=30)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	Assert.assertTrue(faxSendersPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyArrowMoveForFirstAndLastPage() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=33)
    public void VerifyNumberOfItemsPerPageSelection() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=34)
    public void VerifyDropdownForAllTheColumns() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=35)
    public void VerifyColumnsHeaderEnable() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=36)
    public void VerifyColumnsHeaderDisable() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertFalse(faxSendersPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
    @AfterClass
    public void DeleteFaxLineConfigRecord() throws IOException {
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
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
        faxLineConfigPage.deleteFaxLineConfig(faxLineConfigDetails);
        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(),"Record Deleted Successfully");
    }
}


