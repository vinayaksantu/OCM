package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.SkillConfigurationDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.SkillConfigurationPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SkillConfigurationTest extends BaseTest{
	
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToSkillConfigurationPage() {
    	 HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
         homePage.navigateToOCMPage();
         OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
         Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
         ocmHomePage.navigateToSkillConfigurationPage();
         SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
         Assert.assertTrue(skillConfigurationPage.isSkillConfigurationPageDisplayed(),"Skill Configuration assertion failed");
    }
    //@Test(priority=1)
    public void SkillConfigurationPage() {
        SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	Assert.assertTrue(skillConfigurationPage.verifylogo(),"Skill Configuration logo assertion failed");
    	Assert.assertTrue(skillConfigurationPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("maximize window","SkillConfigurationTest");
    	Assert.assertTrue(skillConfigurationPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("minimize window","SkillConfigurationTest");
    }
    
    //@Test(priority=6)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyExportToExcel(filePath));
        screenshot.captureScreen("Export Excel", "SkillConfigurationTest");
    }
    //@Test(priority=7)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\SkillConfiguration.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	Assert.assertTrue(skillConfigurationPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen("Export Excel Sheet", "SkillConfigurationTest");
    }
    
    //@Test(priority=8)
    public void ExporttoExcelWithoutData() throws Exception
    {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
        Assert.assertTrue(skillConfigurationPage.ExporttoExcelWithoutData(skillConfigurationDetails));
    }
  
    //@Test(priority=9)
    public void SortingByAscending() throws IOException {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	skillConfigurationPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\SkillConfiguration (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(skillConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    //@Test(priority=10)
    public void SortingByDescending() throws IOException {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	skillConfigurationPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\SkillConfiguration (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(skillConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    //@Test(priority=11)
    public void GroupBy()
    {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	Assert.assertTrue(skillConfigurationPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","SkillConfigurationTest");
    	Assert.assertTrue(skillConfigurationPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","SkillConfigurationTest");
    }
    
    //@Test(priority=12)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	Assert.assertTrue(skillConfigurationPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    //@Test(priority=13)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    //@Test(priority=14)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    //@Test(priority=15)
    public void VerifyNumberOfItemsPerPageSelection() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    //@Test(priority=16)
    public void VerifyDropdownForAllTheColumns() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    //@Test(priority=17)
    public void VerifyColumnsHeaderEnable() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    //@Test(priority=18)
    public void VerifyColumnsHeaderDisable() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertFalse(skillConfigurationPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(ITestResult result, Method method) throws InterruptedException {
    	 if(ITestResult.FAILURE==result.getStatus()){
       		 try{
       			 screenshot.captureScreen(method.getName(),"SkillConfigurationTest");
       		 }
       		catch (Exception e){
       		 System.out.println("Exception while taking screenshot "+e.getMessage());
       		 } 
       		 driver.navigate().refresh();
       		 }    }

}
