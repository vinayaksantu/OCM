package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.UserRoleMappingDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.NewUserRoleMappingPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserRoleMappingUITest extends BaseTest{
Screenshot screenshot=new Screenshot(driver);
	
	@BeforeClass
    public void NavigateToNewUserRoleMappingPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToUserRoleMappingPage();
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.isUserRoleMappingPageDisplayed(), "SMS Response Template Page assertion failed");
    }
	
	@Test(priority=1)
    public void UserRoleMappingPage() {
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
    	Assert.assertTrue(NewUserRoleMappingPage.verifylogo(),"User Role Mapping logo assertion failed");
    	Assert.assertTrue(NewUserRoleMappingPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("UserRoleMappingUITest","maximize window");
    	Assert.assertTrue(NewUserRoleMappingPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("UserRoleMappingUITest","minimize window");
    }
	@Test(priority=2)
	public void VerifyUserRoleMappingApprovedDataPage() {
        NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
     }
       
	@Test(priority=3)
    public void VerifyUserRoleMappingAuditTrailDataPage() {
		NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
		NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        Assert.assertTrue(NewUserRoleMappingPage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
       }
	
	@Test(priority=4)
    public void VerifyMakeUserRoleMappingChangeButton() {
		NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
		NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
		NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
		Assert.assertTrue(NewUserRoleMappingPage.verifyAddNewUserRoleMappingRecordButton(), "add new Agent Settings record button assertion failed");
        Assert.assertTrue(NewUserRoleMappingPage.verifyGoBackButton(), "Go back button assertion failed");
        Assert.assertTrue(NewUserRoleMappingPage.verifyExportToExcelButton(), "export to excel button assertion failed");
        Assert.assertTrue(NewUserRoleMappingPage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
       }
       
    @Test(priority=5)
    public void VerifyDropdownForAllTheColumns() {
		NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
		NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        Assert.assertTrue(NewUserRoleMappingPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
       }
     
    @Test(priority=6)
    public void VerifyColumnsHeaderEnable() throws InterruptedException {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        Assert.assertTrue(NewUserRoleMappingPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
     }
       
    @Test(priority=7)
    public void VerifyColumnsHeaderDisable() {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        Assert.assertFalse(NewUserRoleMappingPage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
      }
    
    @Test(priority=8)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        Assert.assertTrue(NewUserRoleMappingPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=9)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        Assert.assertTrue(NewUserRoleMappingPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    @Test(priority=10)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        Assert.assertTrue(NewUserRoleMappingPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
	@Test(priority=11)
    public void VerifyNumberOfItemsPerPageSelection() {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        Assert.assertTrue(NewUserRoleMappingPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=12)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        Assert.assertTrue(NewUserRoleMappingPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=13)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Role Mapping.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        Assert.assertTrue(NewUserRoleMappingPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=14)
    public void VerifyExportToExcelWithoutData() throws Exception {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
        Assert.assertTrue(NewUserRoleMappingPage.ExporttoExcelWithoutData(UserRoleMappingDetails));
       }
    
   @Test(priority=15)
    public void SortingByAscending() throws IOException {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        NewUserRoleMappingPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Role Mapping (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(NewUserRoleMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=16)
    public void SortingByDescending() throws IOException {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        NewUserRoleMappingPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Role Mapping (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(NewUserRoleMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=17)
    public void VerifyDragAndDrop() {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        NewUserRoleMappingPage.dragColumntoGroup("First Name");
        Assert.assertTrue(NewUserRoleMappingPage.verifyDragColumntoGroup("First Name"),"drag and drop assertion failed");
    }
    @Test(priority=18)
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
        NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
        NewUserRoleMappingPage.dragColumntoGroup("Last Name");
        NewUserRoleMappingPage.dragColumntoGroup("Last Name");
        Assert.assertTrue(NewUserRoleMappingPage.verifyDragColumntoGroup("Last Name"),"drag and drop assertion failed");
    }
    
    //@Test(priority=19)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
    	Assert.assertTrue(NewUserRoleMappingPage.verifyDatabase(UserRoleMappingDetails.getQuery()));
    }
    
    @Test(priority=20)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertFalse(NewUserRoleMappingPage.clearAll(UserRoleMappingDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("UserRoleMappingUITest", "clearall");
        Assert.assertTrue(NewUserRoleMappingPage.verifyclose());
        screenshot.captureScreen("UserRoleMappingUITest", "SearchClose");
    }
    
    @Test(priority=21)
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
    	NewUserRoleMappingPage.searchwithoutextsearch(UserRoleMappingDetails);
    	Assert.assertFalse(NewUserRoleMappingPage.getErrorMsg());
    }
    
    @Test(priority=22)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    UserRoleMappingDetails UserRoleMappingDetails = new UserRoleMappingDetails(map);
    	NewUserRoleMappingPage NewUserRoleMappingPage = PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
        Assert.assertTrue(NewUserRoleMappingPage.verifyApprovedSectionData(UserRoleMappingDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("UserRoleMappingUITest","Invalid Search with wrong data");
        Assert.assertTrue(NewUserRoleMappingPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
	
	@Test(priority=23)
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		UserRoleMappingDetails UserRoleMappingDetails=new UserRoleMappingDetails(map);
		NewUserRoleMappingPage NewUserRoleMappingPage=PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
		NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
		NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
		Assert.assertTrue(NewUserRoleMappingPage.verifySearchIsNotEqualTo(UserRoleMappingDetails.getFirstname()));
	}
	
	@Test(priority=24)
	public void VerifySearchContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
		UserRoleMappingDetails UserRoleMappingDetails=new UserRoleMappingDetails(map);
		NewUserRoleMappingPage NewUserRoleMappingPage=PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
		NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
		NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
		Assert.assertTrue(NewUserRoleMappingPage.verifySearchContains(UserRoleMappingDetails.getFirstname()));
	}
	
	@Test(priority=25)
	public void VerifySearchDoesNotContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
		UserRoleMappingDetails UserRoleMappingDetails=new UserRoleMappingDetails(map);
		NewUserRoleMappingPage NewUserRoleMappingPage=PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
		NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
		NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
		Assert.assertTrue(NewUserRoleMappingPage.verifySearchDoesNotContains(UserRoleMappingDetails.getFirstname()));
	}
    
	@Test(priority=26)
	public void VerifySearchStartsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(2);
		UserRoleMappingDetails UserRoleMappingDetails=new UserRoleMappingDetails(map);
		NewUserRoleMappingPage NewUserRoleMappingPage=PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
		NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
		NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
		Assert.assertTrue(NewUserRoleMappingPage.verifySearchStartsWith(UserRoleMappingDetails.getFirstname()));
	}
	
	@Test(priority=27)
	public void VerifySearchEndsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(3);
		UserRoleMappingDetails UserRoleMappingDetails=new UserRoleMappingDetails(map);
		NewUserRoleMappingPage NewUserRoleMappingPage=PageFactory.createPageInstance(driver, NewUserRoleMappingPage.class);
		NewUserRoleMappingPage.selectUserRoleMappingAuditTrailTab();
		NewUserRoleMappingPage.selectMakeUserRoleMappingChanges();
		Assert.assertTrue(NewUserRoleMappingPage.verifySearchEndsWith(UserRoleMappingDetails.getFirstname()));
	
	}
	
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("UserRoleMappingUITest",method.getName());
	        driver.navigate().refresh();
	}

}

	
