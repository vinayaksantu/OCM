package com.tetherfi.test.ivr;



import com.tetherfi.model.ivr.OperatingHoursDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class OperatingHoursTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToOperatingHoursPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        screenshot.captureScreen("IVR Page", "OperatingHoursTest");
        ivrPage.navigateToOperatingHoursPage();
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.isOperatingHoursPageDisplayed(), "Operating hours page assertion failed");
        screenshot.captureScreen("OperatingHoursTest","Operating Hours Page");
    }
    @Test(priority=1)
    public void OperatingHoursPage() {
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
    	Assert.assertTrue( operatingHoursPage.verifylogo(),"Operating Hours logo assertion failed");
    	Assert.assertTrue(operatingHoursPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","OperatingHoursTest");
    	Assert.assertTrue(operatingHoursPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","OperatingHoursTest");
    }

   @Test(priority=2)
    public void AddNewOperatingHoursRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.addnewOperatingHoursCancel(operatingHoursDetails), "cancel assertion failed");
    	screenshot.captureScreen(driver,"Add Cancel","OperatingHoursTest");
        operatingHoursPage.addNewOperatingHoursRecord(operatingHoursDetails);
        Assert.assertTrue(operatingHoursPage.verifyNewRecordCreated(), "Add New record assertion failed");
    	screenshot.captureScreen(driver,"Single record Created","OperatingHoursTest");
    }
    
   @Test(priority=3)
    public void AddAlldaysOperatingHoursRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
        operatingHoursPage.addNewAllDaysOperatingHoursRecord(operatingHoursDetails);
        Assert.assertTrue(operatingHoursPage.verifyNewRecordCreated(), "Add New record assertion failed");
        screenshot.captureScreen(driver,"Multiple record Created","OperatingHoursTest");
    }
    
    @Test(priority=4)
	public void AddLeavingFieldsBlank() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
        operatingHoursPage.LeavingAllFieldsBlank(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
    }
    
    @Test(priority=5)
   	public void AddLeavingVDNBlankBlank() throws Exception {
       	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
    	operatingHoursPage.LeavingVDNBlank(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
    }
    
    @Test(priority=6)
   	public void AddLeavingWeekDayFieldsBlank() throws Exception {
       	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
    	operatingHoursPage.LeavingWeekDayBlank(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
    	screenshot.captureScreen(driver,"LeavingWeekdayBlank","OperatingHoursTest");
    }
    @Test(priority=7)
   	public void AddLeavingStartTimeFieldsBlank() throws Exception {
       	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
    	operatingHoursPage.LeavingStartTimeBlank(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
    	screenshot.captureScreen(driver,"LeavingStartTimeBlank","OperatingHoursTest");
    }
    @Test(priority=8)
   	public void AddLeavingEndTimeFieldsBlank() throws Exception {
       	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);	
    	operatingHoursPage.LeavingEndTimeBlank(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
    	screenshot.captureScreen(driver,"LeavingEndTimeBlank","OperatingHoursTest");
    }
    @Test(priority=9)
   	public void AddLeavingBypassPublicHolidayFieldsBlank() throws Exception {
       	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);	
    	operatingHoursPage.LeavingBypassPublicHolidayBlank(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg());
    	screenshot.captureScreen(driver,"LeavingBypassPublicHolidayBlank","OperatingHoursTest");
	}
	
	@Test(priority=10)
	public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
        operatingHoursPage.addNewAllDaysOperatingHoursRecord(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg(), "Add New record assertion failed");
        screenshot.captureScreen(driver,"Duplicate","OperatingHoursTest");
	}
	
	@Test(priority=11)
	public void AddInvalidVDNRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
        operatingHoursPage.addVDNvaluelessthanfour(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg(), "Add New record assertion failed");
        screenshot.captureScreen(driver,"addVDNvaluelessthanfour","OperatingHoursTest");
     }
	
	@Test(priority=12)
	public void AddInvalidStartTimeRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
        operatingHoursPage.addStarttimegreaterthanEndtime(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg(), "Add New record assertion failed");
        screenshot.captureScreen(driver,"addStarttimegreaterthanEndtime","OperatingHoursTest");
	}
	@Test(priority=13)
	public void AddInvalidStartAndEndTimeRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath, "Create").getTestData().get(3);
        OperatingHoursDetails operatingHoursDetails1 = new OperatingHoursDetails(map1);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
        operatingHoursPage.addStarttimesameasEndtime(operatingHoursDetails1);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg(), "Add New record assertion failed");
        screenshot.captureScreen(driver,"addStarttimesameasEndtime","OperatingHoursTest");
	}
	
    @Test(priority=14)
    public void EditOperatingHoursCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.editOperatingHoursCancelbtn(operatingHoursDetails));
        screenshot.captureScreen(driver,"Edit Cancel", "OperatingHoursTest");
        Thread.sleep(1000);
    }
    @Test(priority=15)
    public void EditOperatingHoursRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        operatingHoursPage.editOperatingHoursRecord(operatingHoursDetails);
        Assert.assertTrue(operatingHoursPage.verifyRecordUpdated(),"Edit record assertion failed");
        screenshot.captureScreen(driver,"Record Updated", "OperatingHoursTest");
    }
    
    @Test(priority=16)
    public void EditInvalidRecord() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
    	OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
    	OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
    	operatingHoursPage.editInvalidRecord(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg(),"Edit record assertion failed");
        screenshot.captureScreen("Time is Same", "OperatingHoursTest");
        driver.navigate().refresh();
        Map<String, String> map1 = new ExcelReader(filePath,"Edit").getTestData().get(2);
    	OperatingHoursDetails operatingHoursDetails1=new OperatingHoursDetails(map1);
        operatingHoursPage.editInvalidRecord(operatingHoursDetails1);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg(),"Edit record assertion failed");
        screenshot.captureScreen(driver,"Start Time is greater than End Time", "OperatingHoursTest");	
    }
    
    @Test(priority=17)
    public void EditRecordWithoutReason() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
    	OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
    	OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
    	operatingHoursPage.editRecordWithoutReason(operatingHoursDetails);
        Assert.assertFalse(operatingHoursPage.verifyErrorMsg(),"Edit record assertion failed");
        screenshot.captureScreen(driver,"Edit Record without Reason", "OperatingHoursTest");
    }
    
    @Test(priority=18)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
    	OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
    	OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertFalse(operatingHoursPage.clearAll(operatingHoursDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","OperatingHoursTest");
        Assert.assertTrue(operatingHoursPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","OperatingHoursTest");
    }
    
    @Test(priority=19)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
    	OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.verifyinvalidsearchwithwrongdata(operatingHoursDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver,"Invalid Search with wrong data", "OperatingHoursTest");
        Assert.assertTrue(operatingHoursPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen(driver,"Clear Search", "OperatingHoursTest");
    }
    
    @Test(priority=20)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel", "OperatingHoursTest");
    }
    
  //  @Test(priority=21)[pagination Depends on Window Size]
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Operating Hours.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
    Assert.assertTrue(operatingHoursPage.verifyexportToExcelSheet(maplist));	
    screenshot.captureScreen(driver,"Export Excel Sheet", "OperatingHoursTest");
    }
    
    //@Test(priority=22)[pagination Depends on Window Size]
    public void ExporttoExcelWithoutData() throws Exception
    {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        Assert.assertTrue(operatingHoursPage.ExporttoExcelWithoutData(operatingHoursDetails));
    }
  
    //@Test(priority=23)
    public void SortingByAscending() throws IOException {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        operatingHoursPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Operating Hours (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(operatingHoursPage.verifyexportToExcelSheet(maplist));
    }
   // @Test(priority=24)
    public void SortingByDescending() throws IOException {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        operatingHoursPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Operating Hours (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(operatingHoursPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=25)
    public void GroupBy()
    {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
    	Assert.assertTrue(operatingHoursPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","HolidayListTest");
    	Assert.assertTrue(operatingHoursPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","OperatingHoursTest");
    }
    
    @Test(priority=26)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
    	Assert.assertTrue(operatingHoursPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=27)
    public void VerifyArrowMoveForFirstAndLastPage() {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    @Test(priority=28)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=29)
    public void VerifyNumberOfItemsPerPageSelection() {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=30)
    public void VerifyDropdownForAllTheColumns() {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyColumnsHeaderEnable() {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyColumnsHeaderDisable() {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertFalse(operatingHoursPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=33)
    public void DeleteNoRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
    	OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.verifydeleteNo(operatingHoursDetails));
        screenshot.captureScreen(driver,"Delete No", "OperatingHoursTest");
    }
    
    @Test(priority=34)
    public void DeleteAgentTeamManagementRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
    	OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        operatingHoursPage.deleteOperatingHoursRecord(operatingHoursDetails);
        Assert.assertTrue(operatingHoursPage.verifyRecordDeleted(),"delete record assertion failed");
        screenshot.captureScreen(driver,"Record Deleted", "OperatingHoursTest");
    }
    
    @Test(priority=35)
    public void database() throws Exception
    {
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        Assert.assertTrue(operatingHoursPage.verifyDatabase(operatingHoursDetails.getQuery()));
    }
    
    @AfterMethod
    public void afterEachMethod(ITestResult result, Method method) throws InterruptedException {
    	 if(ITestResult.FAILURE==result.getStatus()){
       		 try{
       			 screenshot.captureScreen(method.getName(),"HolidayListTest");
       		 }
       		catch (Exception e){
       		 System.out.println("Exception while taking screenshot "+e.getMessage());
       		 } 
       		 driver.navigate().refresh();
       		 }    
       		 }


}

