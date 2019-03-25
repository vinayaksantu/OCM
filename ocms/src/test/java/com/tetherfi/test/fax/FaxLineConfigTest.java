package com.tetherfi.test.fax;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.ivr.AdhocOptionEnhancementDetails;
import com.tetherfi.pages.AdhocOptionEnhancementPage;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class FaxLineConfigTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToFaxLineConfigPage() {
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
    }

    //@Test(priority=1)
    public void FaxLineConfigPage() {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
    	Assert.assertTrue(faxLineConfigPage.verifylogo(),"FaxLineConfig logo assertion failed");
    	Assert.assertTrue(faxLineConfigPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","FaxLineConfigTest");
    	Assert.assertTrue(faxLineConfigPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","FaxLineConfigTest");
    }
    //@Test
    public void AddFaxLineConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);

        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        faxLineConfigPage.addNewFaxLineConfigRecord(faxLineConfigDetails);
        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(), "Record Created Successfully");
    }
    //@Test(dependsOnMethods = {"AddFaxLineConfigRecord"})
    public void EditFaxLineConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);

        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        faxLineConfigPage.editFaxLineConfig(faxLineConfigDetails);
        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(),"Record Updated Successfully");
    }

    //@Test(dependsOnMethods = {"EditFaxLineConfigRecord"})
    public void DeleteFaxLineConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        faxLineConfigPage.deleteFaxLineConfig(faxLineConfigDetails);
       Assert.assertEquals(faxLineConfigPage.getSuccessMessage(),"Record Deleted Successfully");
    }
    
    //@Test(priority=17)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        Assert.assertTrue(faxLineConfigPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","FaxLineConfigtTest");
    }
    
    //@Test(priority=18)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Line Config.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
    	Assert.assertTrue(faxLineConfigPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","FaxLineConfigTest");
    }
    
    @Test(priority=26)
    public void ExporttoExcelWithoutData() throws Exception
    {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
        Assert.assertTrue(faxLineConfigPage.ExporttoExcelWithoutData(faxLineConfigDetails));
        screenshot.captureScreen( driver,"ExporttoExcelWithoutData", "FaxLineConfigTest");
    }
  
   // @Test(priority=27)
    public void SortingByAscending() throws IOException {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        faxLineConfigPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Line Config (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxLineConfigPage.verifyexportToExcelSheet(maplist));
    }
    
    //@Test(priority=28)
    public void SortingByDescending() throws IOException {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        faxLineConfigPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Line Config (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxLineConfigPage.verifyexportToExcelSheet(maplist));
    }
    
    //@Test(priority=29)
    public void GroupBy()
    {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
    	Assert.assertTrue(faxLineConfigPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","FaxLineConfigTest");
    	Assert.assertTrue(faxLineConfigPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","FaxLineConfigTest");
    }
    
    /*@Test(priority=30)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
    	Assert.assertTrue(faxLineConfigPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyArrowMoveForFirstAndLastPage() {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        Assert.assertTrue(faxLineConfigPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        Assert.assertTrue(faxLineConfigPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=33)
    public void VerifyNumberOfItemsPerPageSelection() {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        Assert.assertTrue(faxLineConfigPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=34)
    public void VerifyDropdownForAllTheColumns() {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        Assert.assertTrue(faxLineConfigPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=35)
    public void VerifyColumnsHeaderEnable() {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        Assert.assertTrue(faxLineConfigPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=36)
    public void VerifyColumnsHeaderDisable() {
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        Assert.assertFalse(faxLineConfigPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }*/
    @AfterMethod
    public void afterEachMethod(Method method) {
        screenshot.captureScreen(driver,method.getName(),"FaxLineConfigTest");
    }
}

