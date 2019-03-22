package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.HolidayListDetails;
import com.tetherfi.model.ivr.OperatingHoursDetails;
import com.tetherfi.pages.HolidayListPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OperatingHoursPage;
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
        screenshot.captureScreen("Holiday List Page","HolidayListTest");
    }
	
	@Test(priority=1)
	public void HolidayListPage() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	Assert.assertTrue(holidaylistPage.verifylogo(),"Holiday List logo assertion failed");
    	Assert.assertTrue(holidaylistPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("maximize window","HolidayListTest");
    	Assert.assertTrue(holidaylistPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("minimize window","HolidayListTest");
	}
	
	//@Test(priority=2)
	public void addNewHolidayListRecord() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.addnewHolidayListCancel(holidayListDetails), "HolidayList cancel assertion failed");
    	screenshot.captureScreen("Add Cancel","HolidayListTest");
    	holidaylistPage.addNewHolidayList(holidayListDetails);
        Assert.assertTrue(holidaylistPage.verifymessage(), "HolidayList Record creation assertion failed" );
    	screenshot.captureScreen("Record Created Successfully","HolidayListTest");
	}
	
	//@Test(priority=3,dependsOnMethods = ("addNewHolidayListRecord"))
	public void Add() throws Exception{
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        holidaylistPage.addNewHolidayList(holidayListDetails);
        Assert.assertTrue(holidaylistPage.verifymessage(), "HolidayList Record creation assertion failed" );
    	screenshot.captureScreen("Record Created Successfully for Different VDN","HolidayListTest");
    	Map<String, String> map1 = new ExcelReader(filePath, "Create").getTestData().get(2);
        HolidayListDetails holidayListDetails1=new HolidayListDetails (map1);
        holidaylistPage.addNewHolidayList(holidayListDetails1);
        Assert.assertTrue(holidaylistPage.verifymessage(), "HolidayList Record creation assertion failed" );
    	screenshot.captureScreen("Record Created Successfully for Different Announced Holiday","HolidayListTest");
    	Map<String, String> map2 = new ExcelReader(filePath, "Create").getTestData().get(3);
        HolidayListDetails holidayListDetails2=new HolidayListDetails (map2);
        holidaylistPage.addNewHolidayList(holidayListDetails2);
        Assert.assertTrue(holidaylistPage.verifymessage(), "HolidayList Record creation assertion failed" );
    	screenshot.captureScreen("Record Created Successfully for Same Announced Holiday,Same VDN but different Date ","HolidayListTest");
    	Map<String, String> map3 = new ExcelReader(filePath, "Create").getTestData().get(4);
        HolidayListDetails holidayListDetails3=new HolidayListDetails (map3);
        holidaylistPage.addNewHolidayList(holidayListDetails3);
        Assert.assertFalse(holidaylistPage.verifymessage(), "HolidayList Record creation assertion failed" );
    	screenshot.captureScreen("Record Created Successfully for Different Announced Holiday but same date,same VDN","HolidayListTest");
    	Map<String, String> map4 = new ExcelReader(filePath, "Create").getTestData().get(5);
        HolidayListDetails holidayListDetails4=new HolidayListDetails (map4);
        holidaylistPage.addNewHolidayList(holidayListDetails4);
        Assert.assertFalse(holidaylistPage.verifymessage(), "HolidayList Record creation assertion failed" );
    	screenshot.captureScreen("Record Created Successfully for Different different start and end date,same time,same VDN","HolidayListTest");
    	Map<String, String> map5 = new ExcelReader(filePath, "Create").getTestData().get(6);
        HolidayListDetails holidayListDetails5=new HolidayListDetails (map5);
        holidaylistPage.addNewHolidayList(holidayListDetails5);
        Assert.assertTrue(holidaylistPage.verifymessage(), "HolidayList Record creation assertion failed" );
    	screenshot.captureScreen("Record Created Successfully for Different different start and end date,same time","HolidayListTest");
	}
	
	//@Test(priority=4,dependsOnMethods = ("addNewHolidayListRecord"))
  	public void AddDuplicateRecord() throws Exception {
  		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);	
        holidaylistPage.addDuplicateRecord(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage(), "HolidayList Duplicate Record assertion failed" );
    	screenshot.captureScreen("Duplicate Record","HolidayListTest");
  	}
	
	//@Test(priority=5)
	public void AddLeavingFieldsBlank() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);	
        holidaylistPage.LeavingAllFieldsBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
    	screenshot.captureScreen("LeavingAllFieldsBlank","HolidayListTest");
    	holidaylistPage.LeavingAnnouncedHolidayBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
    	screenshot.captureScreen("LeavingAnnouncedHolidayBlank","HolidayListTest");
    	holidaylistPage.LeavingStartDateBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
    	screenshot.captureScreen("LeavingStartDateBlank","HolidayListTest");
    	holidaylistPage.LeavingStartTimeBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
    	screenshot.captureScreen("LeavingStartTimeBlank","HolidayListTest");
    	holidaylistPage.LeavingEndDateBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
    	screenshot.captureScreen("LeavingEndDateBlank","HolidayListTest");
    	holidaylistPage.LeavingEndTimeBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
    	screenshot.captureScreen("LeavingEndTimeBlank","HolidayListTest");
    	holidaylistPage.LeavingVDNBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
    	screenshot.captureScreen("LeavingVDNBlank","HolidayListTest");
	}
	
	//@Test(priority=6)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyExportToExcel(filePath));
    }
    
    //@Test(priority=7)
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    Assert.assertTrue(holidaylistPage.verifyexportToExcelSheet(maplist));	
    }
    
  
    //@Test(priority=8,dependsOnMethods = ("addNewHolidayListRecord"))
  	public void editHolidayListRecord() throws Exception {
  		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
  		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
  		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.editHolidaylistCancelbtn(holidayListDetails));
      	screenshot.captureScreen(driver, "edit Cancel","HolidayListTest");
      	holidaylistPage.editHolidayListRecord(holidayListDetails);
        Assert.assertTrue(holidaylistPage.verifymessage(), "HolidayList Record updation assertion failed" );
      	screenshot.captureScreen(driver, "Record Updated Successfully","HolidayListTest");
  	}
  	
  	//@Test(priority=9,dependsOnMethods = ("editHolidayListRecord"))
  	public void editLeavingFielsBlank() throws Exception {
  		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
  		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
  		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
    	holidaylistPage.EditLeavingModifyReasonBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
    	screenshot.captureScreen("EditLeavingModifyReasonBlank","HolidayListTest");
  	}
  	
  	//@Test(priority=10,dependsOnMethods = ("editHolidayListRecord"))
    public void DeleteHolidayListRecord() throws IOException {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.verifydeleteNo(holidayListDetails));
        screenshot.captureScreen(driver, "delete No","HolidayListTest");
        holidaylistPage.deleteHolidayListRecord(holidayListDetails);
        Assert.assertTrue(holidaylistPage.verifymessage(),"delete record assertion failed");
        screenshot.captureScreen(driver, "Verify Record Deleted", "HolidayListTest");
        }
    
	//@Test(priority=11)
    public void GroupBy()
    {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	Assert.assertTrue(holidaylistPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","HolidayListTest");
    	Assert.assertTrue(holidaylistPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","HolidayListTest");
    }
    
    //@Test(priority=12)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	Assert.assertTrue(holidaylistPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    //@Test(priority=13)
    public void VerifyArrowMoveForFirstAndLastPage() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    ////@Test(priority=14)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    //@Test(priority=15)
    public void VerifyNumberOfItemsPerPageSelection() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    //@Test(priority=15)
    public void VerifyDropdownForAllTheColumns() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    //@Test(priority=16)
    public void VerifyColumnsHeaderEnable() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    //@Test(priority=17)
    public void VerifyColumnsHeaderDisable() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertFalse(holidaylistPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    //@Test(priority=18)
    public void SortingByAscending() throws IOException {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        holidaylistPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(holidaylistPage.verifyexportToExcelSheet(maplist));
    }
    //@Test(priority=19)
    public void SortingByDescending() throws IOException {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        holidaylistPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(holidaylistPage.verifyexportToExcelSheet(maplist));
    }
    //@Test(priority=20)
    public void ExporttoExcelWithoutData() throws Exception
    {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.ExporttoExcelWithoutData(holidayListDetails));
    }
    
    //@Test(priority=21)
    public void database() throws Exception
    {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage .verifyDatabase(holidayListDetails.getQuery()));
   
    }
	
  //@Test(priority=22)
    public void searchPage() throws IOException {
    	HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertFalse(holidaylistPage.clearAll(holidayListDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","HolidayListTest");
        Assert.assertTrue(holidaylistPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","HolidayListTest");
    }
    
    @Test(priority=23)
    public void SearchClearSearch() throws Exception
    {
    	HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.verifyinvalidsearchwithwrongdata(holidayListDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("Invalid Search with wrong data", "HolidayListTest");
        Assert.assertTrue(holidaylistPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen( "Clear Search", "HolidayListTest");
    }
	
	@AfterMethod
    public void afterEachMethod(ITestResult result,Method method){
   	 if(ITestResult.FAILURE==result.getStatus()){
   		 try{
   			 screenshot.captureScreen(driver, method.getName(),"HolidayListTest");
   		 }
   		catch (Exception e){
   		 System.out.println("Exception while taking screenshot "+e.getMessage());
   		 } 
   		 driver.navigate().refresh();
   		 }
   		
    }
}
