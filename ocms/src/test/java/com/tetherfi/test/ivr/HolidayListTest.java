package com.tetherfi.test.ivr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.HolidayListDetails;
import com.tetherfi.model.tmac.WorkCodeListDetails;
import com.tetherfi.pages.HolidayListPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.WorkCodeListPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class HolidayListTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
    public void NavigateToHolidayListPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        screenshot.captureScreen(driver, "IVR Page","HolidayListTest");
        ivrPage.navigateToHolidayListPage();
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.isHolidayListPageDisplayed(), "Holiday List page assertion failed");
        screenshot.captureScreen(driver, "Holiday List Page","HolidayListTest");
    }
	
	//@Test(priority=1)
	public void HolidayListPage() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	Assert.assertTrue(holidaylistPage.verifylogo(),"Holiday List logo assertion failed");
    	Assert.assertTrue(holidaylistPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver, "maximize window","HolidayListTest");
    	Assert.assertTrue(holidaylistPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver, "minimize window","HolidayListTest");
	}
	
	//@Test(priority=2)
	public void addNewHolidayListRecord() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.addnewHolidayListCancel(holidayListDetails), "HolidayList cancel assertion failed");
    	screenshot.captureScreen(driver, "Add Cancel","HolidayListTest");
    	holidaylistPage.addNewHolidayList(holidayListDetails);
        Assert.assertTrue(holidaylistPage.verifymessage(), "HolidayList Record creation assertion failed" );
    	screenshot.captureScreen(driver, "Record Created Successfully","HolidayListTest");
	}
	
	//@Test(priority=7)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyExportToExcel(filePath));
    }
    
    //@Test(priority=8)
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    Assert.assertTrue(holidaylistPage.verifyexportToExcelSheet(maplist));	
    }
    
	////@Test(priority=10)
    public void GroupBy()
    {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	Assert.assertTrue(holidaylistPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","HolidayListTest");
    	Assert.assertTrue(holidaylistPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","HolidayListTest");
    }
    
    //@Test(priority=13)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	Assert.assertTrue(holidaylistPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    //@Test(priority=14)
    public void VerifyArrowMoveForFirstAndLastPage() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    ////@Test(priority=15)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    //@Test(priority=16)
    public void VerifyNumberOfItemsPerPageSelection() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    //@Test(priority=17)
    public void VerifyDropdownForAllTheColumns() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    //@Test(priority=18)
    public void VerifyColumnsHeaderEnable() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    //@Test(priority=19)
    public void VerifyColumnsHeaderDisable() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertFalse(holidaylistPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    //@Test(priority=20)
    public void SortingByAscending() throws IOException {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        holidaylistPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(holidaylistPage.verifyexportToExcelSheet(maplist));
    }
    //@Test(priority=21)
    public void SortingByDescending() throws IOException {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        holidaylistPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(holidaylistPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=22)
    public void ExporttoExcelWithoutData() throws Exception
    {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.ExporttoExcelWithoutData(holidayListDetails));
    }
	
	
	@AfterMethod
    public void afterEachMethod(ITestResult result){
   	 if(ITestResult.FAILURE==result.getStatus()){
   		 try{
   			 screenshot.captureScreen(driver, "failed","TmacBroadCastMsgTest");
   		 }
   		catch (Exception e){
   		 System.out.println("Exception while taking screenshot "+e.getMessage());
   		 } 
   		 driver.navigate().refresh();
   		 }
   		
    }
}
