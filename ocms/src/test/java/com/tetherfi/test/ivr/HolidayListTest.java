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
													
import com.tetherfi.pages.HolidayListPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class HolidayListTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
    public void NavigateToHolidayListPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        ivrPage.navigateToHolidayListPage();
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.isHolidayListPageDisplayed(), "Holiday List page assertion failed");
    }
	
	@Test(priority=1)
	public void HolidayListPage() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	Assert.assertTrue(holidaylistPage.verifylogo(),"Holiday List logo assertion failed");
    	Assert.assertTrue(holidaylistPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("HolidayListTest","maximize window");
    	Assert.assertTrue(holidaylistPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("HolidayListTest","minimize window");
	}
	
	@Test(priority=2)
	public void addNewHolidayListCancelRecord() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.addnewHolidayListCancel(holidayListDetails), "HolidayList cancel assertion failed");
	}
	
	@Test(priority=3)
	public void addNewHolidayListRecord() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);  
    	holidaylistPage.addNewHolidayList(holidayListDetails);
        Assert.assertEquals(holidaylistPage.getSuccessMessage(),"Record Created Successfully", "HolidayList Record creation assertion failed" );
	}
	
	@Test(priority=4)//,dependsOnMethods = ("addNewHolidayListRecord"))
	public void AddDifferntVDNWithSameHoliday() throws Exception{
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        holidaylistPage.addNewHolidayList(holidayListDetails);
        Assert.assertEquals(holidaylistPage.getSuccessMessage(),"Record Created Successfully", "HolidayList Record creation assertion failed" );
	}
            
	@Test(priority=5)
	public void AddDifferentAnnouncedHolidayWithSameVDN() throws Exception{
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath, "Create").getTestData().get(2);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map1);
        holidaylistPage.addNewHolidayList(holidayListDetails);
        Assert.assertEquals(holidaylistPage.getSuccessMessage(),"Record Created Successfully", "HolidayList Record creation assertion failed" );
	}
	
	@Test(priority=6)
	public void AddSameHolidaySameVDNdifferentDate() throws Exception{
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        holidaylistPage.addNewHolidayList(holidayListDetails);
        Assert.assertEquals(holidaylistPage.getSuccessMessage(),"Record Created Successfully","HolidayList Record creation assertion failed" );
	}
	
	@Test(priority=7)
	public void AddDifferentHolidaySameDateSameVDN() throws Exception{
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map3 = new ExcelReader(filePath, "Create").getTestData().get(4);
        HolidayListDetails holidayListDetails3=new HolidayListDetails (map3);
        holidaylistPage.addNewHolidayList(holidayListDetails3);
        Assert.assertFalse(holidaylistPage.verifymessage(), "HolidayList Record creation assertion failed" );
	}
	
	@Test(priority=8)
	public void AddDifferentDatesSameTimeSameVDN() throws Exception{
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map4 = new ExcelReader(filePath, "Create").getTestData().get(5);
        HolidayListDetails holidayListDetails4=new HolidayListDetails (map4);
        holidaylistPage.addNewHolidayList(holidayListDetails4);
        Assert.assertEquals(holidaylistPage.getSuccessMessage(),"Record Created Successfully", "HolidayList Record creation assertion failed" );
	}
	
	@Test(priority=9)
	public void AddSameDatesSameTime() throws Exception{
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map5 = new ExcelReader(filePath, "Create").getTestData().get(6);
        HolidayListDetails holidayListDetails5=new HolidayListDetails (map5);
        holidaylistPage.addNewHolidayList(holidayListDetails5);
        Assert.assertFalse(holidaylistPage.verifymessage(), "HolidayList Record creation assertion failed" );
	}
	
	@Test(priority=10)//,dependsOnMethods = ("addNewHolidayListRecord"))
  	public void AddDuplicateRecord() throws Exception {
  		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);	
        holidaylistPage.addDuplicateRecord(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage(), "HolidayList Duplicate Record assertion failed" );
  	}
	
	@Test(priority=11)
	public void AddEmptyRecord() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);	
        holidaylistPage.AddEmptyRecord(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
	}
        
	@Test(priority=12)
    public void AddWithoutAnnuncedHoliday() throws Exception {
    	HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);	    	
        holidaylistPage.LeavingAnnouncedHolidayBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
	}
	
	@Test(priority=13)
	public void AddWithoutStartDate() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);	
        holidaylistPage.LeavingStartDateBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
	}
	
	@Test(priority=14)
	public void AddWithoutStartTime() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);	
        holidaylistPage.LeavingStartTimeBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
	}
	
	@Test(priority=15)
	public void AddWithoutEndDate() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);	
        holidaylistPage.LeavingEndDateBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
	}
	
	//@Test(priority=16)
	public void AddWithoutEndTime() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);	
        holidaylistPage.LeavingEndTimeBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
	}
	
	@Test(priority=17)
	public void AddWithoutVDN() throws Exception {
		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);	
        holidaylistPage.LeavingVDNBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
	}
	
	@Test(priority=18)
    public void ExportToExcel() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyExportToExcel(filePath));
   
	}
    
    @Test(priority=19)
    public void ExportToExcelData() throws Exception{
    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    Assert.assertTrue(holidaylistPage.verifyexportToExcelSheet(maplist));	
    }	 
	
    @Test(priority=20)
    public void GroupBy(){
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	Assert.assertTrue(holidaylistPage.groupby());
        screenshot.captureScreen("HolidayListTest", "GroupBy");
    	Assert.assertTrue(holidaylistPage.groupby());
        screenshot.captureScreen("HolidayListTest", "AlreadyGroupBy");
    }
    
    @Test(priority=21)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	Assert.assertTrue(holidaylistPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=22)
    public void VerifyArrowMoveForFirstAndLastPage() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=23)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=24)
    public void VerifyNumberOfItemsPerPageSelection() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=25)
    public void VerifyDropdownForAllTheColumns() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=26)
    public void VerifyColumnsHeaderEnable() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertTrue(holidaylistPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=27)
    public void VerifyColumnsHeaderDisable() {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        Assert.assertFalse(holidaylistPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=28)
    public void SortingByAscending() throws IOException {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        holidaylistPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(holidaylistPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=29)
    public void SortingByDescending() throws IOException {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        holidaylistPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(holidaylistPage.verifyexportToExcelSheet(maplist));
    }
  
   /* @Test(priority=30)//,dependsOnMethods = ("addNewHolidayListRecord"))
  	public void editHolidayListRecord() throws Exception {
  		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
  		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
  		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.editHolidaylistCancelbtn(holidayListDetails));
      	screenshot.captureScreen("HolidayListTest", "edit Cancel");
      	holidaylistPage.editHolidayListRecord(holidayListDetails);
        Assert.assertTrue(holidaylistPage.verifymessage(), "HolidayList Record updation assertion failed" );
  	}
  	
  	/*@Test(priority=31)
  	public void editLeavingFielsBlank() throws Exception {
  		HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
  		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
  		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
    	holidaylistPage.EditLeavingModifyReasonBlank(holidayListDetails);
        Assert.assertFalse(holidaylistPage.verifymessage());
  	}
  	
  	@Test(priority=32,dependsOnMethods = ("editHolidayListRecord"))
    public void DeleteHolidayListRecord() throws Exception {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Holiday List.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.verifydeleteNo(holidayListDetails));
        screenshot.captureScreen("HolidayListTest", "delete No");
        holidaylistPage.deleteHolidayListRecord(holidayListDetails);
        Assert.assertTrue(holidaylistPage.verifymessage(),"delete record assertion failed");
      }
    
    @Test(priority=33)
    public void ExporttoExcelWithoutData() throws Exception {
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage.ExporttoExcelWithoutData(holidayListDetails));
    }
    
    @Test(priority=34)
    public void database() throws Exception{
        HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);
        Assert.assertTrue(holidaylistPage .verifyDatabase(holidayListDetails.getQuery()));
   
    }
	
    @Test(priority=35)
    public void searchPage() throws Exception {
    	HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);  
        Assert.assertFalse(holidaylistPage.clearAll(holidayListDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("HolidayListTest", "clearall");
        Assert.assertTrue(holidaylistPage.verifyclose());
        screenshot.captureScreen("HolidayListTest", "SearchClose");
    }
    
    @Test(priority=36)
    public void SearchClearSearch() throws Exception{
    	HolidayListPage holidaylistPage = PageFactory.createPageInstance(driver, HolidayListPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HolidayListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        HolidayListDetails holidayListDetails=new HolidayListDetails (map);  	
        Assert.assertTrue(holidaylistPage.verifyinvalidsearchwithwrongdata(holidayListDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("HolidayListTest","Invalid Search with wrong data");
        Assert.assertTrue(holidaylistPage.verifyclearsearch(), "Clear All Assertion Failed");
    }*/
	
	@AfterMethod
    public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("HolidayListTest",method.getName());
        driver.navigate().refresh();
	}
}
	
