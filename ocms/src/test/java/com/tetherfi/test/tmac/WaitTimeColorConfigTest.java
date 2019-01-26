package com.tetherfi.test.tmac;

import com.tetherfi.model.tmac.WaitTimeColorConfigDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WaitTimeColorConfigTest extends BaseTest {
	Screenshot screenshot=new Screenshot();

    @BeforeMethod
    public void NavigateToWaitTimeColorConfigPage() {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
        screenshot.captureScreen(driver, "TMAC Page","WaitTimeColorConfigTest");
        tmacPage.navigateToWaitTimeColorConfigPage();
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.isWaitTimeColorConfigPageDisplayed(),"Wait time color config page assertion failed");
        screenshot.captureScreen(driver, "Wait Time Color Config Page","WaitTimeColorConfigTest");
    }
    @Test (priority=1)
    public void WaitTimeColorConfigPage()
    {
    	WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
    	Assert.assertTrue(waitTimeColorConfigPage.verifylogo(),"Wait Time ColorConfig logo assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.verifyStartDurationLabel(), "Start Duration label assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.verifyEndDurationLabel(), "End Duration Label assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.verifyColorLabel(), "Color Label assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.verifyColorCodeLabel(), "Color Code label assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.verifyLastChangedByLabel(), "Last ChangedBy assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.verifyLastChangedOnLabel(), "Last ChangedOn assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.verifygridcontent(),"Grid Container assertion failed");
        //Assert.assertTrue(waitTimeColorConfigPage.verifyColordisable(), "Color disable assertion failed");
    	//screenshot.captureScreen(driver, "Disable","WaitTimeColorConfigTest");
    	Assert.assertTrue(waitTimeColorConfigPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver, "Maximize Window","WaitTimeColorConfigTest");
    	Assert.assertTrue(waitTimeColorConfigPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver, "Minimize Window","WaitTimeColorConfigTest");
    }
   @Test(priority=2)
    public void AddNewWaitTimeColorConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.addNewCancel(waitTimeColorConfigDetails));
        screenshot.captureScreen(driver, "add cancel","WaitTimeColorConfigTest");
        waitTimeColorConfigPage.addNewWaitTimeColorConfigRecord(waitTimeColorConfigDetails);
        Assert.assertEquals(waitTimeColorConfigPage.getMessage(),"Record Created Successfully","Add New record assertion failed");
        screenshot.captureScreen(driver, "Record Created Successfully","WaitTimeColorConfigTest");
    }
   @Test(priority=3)
    public void EditWaitTimeColorConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.editcancel(waitTimeColorConfigDetails));
        screenshot.captureScreen(driver, "edit cancel","WaitTimeColorConfigTest");
        waitTimeColorConfigPage.editWaitTimeColorConfigRecord(waitTimeColorConfigDetails);
        Assert.assertEquals(waitTimeColorConfigPage.getMessage(),"Record updated successfully","Edit record assertion failed");
        screenshot.captureScreen(driver, "Record Updated successfully","WaitTimeColorConfigTest");
    }
   //@Test(priority=4)
   public void searchPage() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertFalse(waitTimeColorConfigPage.clearAll(waitTimeColorConfigDetails),"ClearAll Assertion Failed");
       screenshot.captureScreen(driver, "clearall","WaitTimeColorConfigTest");
       Assert.assertTrue(waitTimeColorConfigPage.verifyclose());
       screenshot.captureScreen(driver, "SearchClose","WaitTimeColorConfigTest");
       }
   
   @Test(priority=5)
   public void SearchClearSearch() throws Exception
   {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyinvalidsearch(waitTimeColorConfigDetails), "InvalidSearchAssertionFailed");
       screenshot.captureScreen(driver, "Invalid Search", "WaitTimeColorConfigTest");
       Assert.assertTrue(waitTimeColorConfigPage.verifyclearsearch(), "Clear All Assertion Failed");
       screenshot.captureScreen(driver, "Clear Search", "WaitTimeColorConfigTest");
   }
   @Test(priority=6)
   public void ExportToExcel() throws Exception
   {
   	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyExportToExcel(filePath));
   }
   
   @Test(priority=7)
   public void ExportToExcelData() throws Exception
   {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Wait Time Color Config.xlsx";
   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
   WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   Assert.assertTrue(waitTimeColorConfigPage.verifyexportToExcelSheet(maplist));
   	
   }
   
   @Test(priority=8)
    public void DeleteRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        waitTimeColorConfigPage.deleteWaitTimeColorConfigRecord(waitTimeColorConfigDetails.getStartTime(),waitTimeColorConfigDetails.getDeleteReason());
        Assert.assertEquals(waitTimeColorConfigPage.getMessage(),"Record Deleted Successfully","Delete record assertion failed");
    }
   
   @Test(priority=9)
   public void database() throws Exception {
   	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   	Assert.assertTrue(waitTimeColorConfigPage.verifyDatabase(waitTimeColorConfigDetails.getQuery()));
   }
   
   @Test(priority=1)
   public void GroupBy()
   {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   		Assert.assertTrue(waitTimeColorConfigPage.groupby());
   	
   }

    @AfterMethod
    public void afterEachMethod(ITestResult result) {
    	if(ITestResult.FAILURE==result.getStatus()){
   		 try{
   			 screenshot.captureScreen(driver, result.getName(),"AgentTeamMgmtTest");
   		 }
   		catch (Exception e){
   		 System.out.println("Exception while taking screenshot "+e.getMessage());
   		 } 
   		 driver.navigate().refresh();
   		 }
   	
       }
}
