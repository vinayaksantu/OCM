package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.UserOnBoardingDetails;
import com.tetherfi.model.user.UserRoleMappingDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.NewUserRoleMappingPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.UserOnBoardingPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserOnBoardingUITest extends BaseTest {

Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
    public void NavigateToNewUserOnBoardingPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToUserOnBoardingPage();
        UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
        Assert.assertTrue(userOnBoardingPage.isUserOnBoardingPageDisplayed(), "NavigateToNewUserOnBoardingPage Page assertion failed");
    }
	
	@Test(priority=1,description="To Verify the UserOnBoardingPage Logo, Minimize and Maximize Screen")
	public void UserOnBoardingPage() {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.verifylogo(),"User On Boarding logo assertion failed");
		Assert.assertTrue(userOnBoardingPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("UserOnBoardingUITest","maximize window");
		Assert.assertTrue(userOnBoardingPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("UserOnBoardingUITest","minimize window");
	}

	@Test(priority=2,description="To Verify UserOnBoarding Approved Data Page Headers ")
	public void VerifyUserOnBoardingApprovedDataPage() {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
	}

	@Test(priority=3,description="Verify UserOn Boarding AuditTrailPage Headers ")
	public void VerifyUserOnBoardingAuditTrailDataPage() {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		Assert.assertTrue(userOnBoardingPage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
	}
	
	@Test(priority=4,description="To Verify MakeUserOnBoardingChanges Button ")
    public void VerifyMakeUserOnBoardingChangesButton() {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
		Assert.assertTrue(userOnBoardingPage.verifyAddNewUserOnBoardingRecordButton(), "add new User On Boarding Record button assertion failed");
        Assert.assertTrue(userOnBoardingPage.verifyGoBackButton(), "Go back button assertion failed");
        Assert.assertTrue(userOnBoardingPage.verifyExportToExcelButton(), "export to excel button assertion failed");
        Assert.assertTrue(userOnBoardingPage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
       }
	
	@Test(priority=5,description="To Verify Dropdown for all the Columns ")
    public void VerifyDropdownForAllTheColumns() {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertTrue(userOnBoardingPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
       }

    @Test(priority=6,description="To Verify ColumnsHeaders Presence when Enabled ")
    public void VerifyColumnsHeaderEnable() throws InterruptedException {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertTrue(userOnBoardingPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
     }

    @Test(priority=7,description="To Verify the ColumnsHeaders Presence When Disabled")
    public void VerifyColumnsHeaderDisable() {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertFalse(userOnBoardingPage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
      }

    @Test(priority=8,description="To Verify ArrowMoveForPrevious And Next Page ")
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertTrue(userOnBoardingPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=9,description="To Verify Arrow Move for First and Last Page ")
    public void VerifyArrowMoveForFirstAndLastPage() {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertTrue(userOnBoardingPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    @Test(priority=10,description="To VerifyTotalNumberOfItemsPerPageDetails ")
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertTrue(userOnBoardingPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }

	@Test(priority=11,description="To VerifyNumberOfItemsPerPageSelection ")
    public void VerifyNumberOfItemsPerPageSelection() {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertTrue(userOnBoardingPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }

    @Test(priority=12,description="To Verify Export to Excel button")
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertTrue(userOnBoardingPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=13,description="To Verify Exported data  ")
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Onboarding.xlsx";
    	System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Onboarding.xlsx");
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        Assert.assertTrue(userOnBoardingPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=14,description="To Verify Export To Excel WithoutData ")
    public void VerifyExportToExcelWithoutData() throws Exception {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
    	UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
        Assert.assertTrue(userOnBoardingPage.ExporttoExcelWithoutData(userOnBoardingDetails));
       }
    
    @Test(priority=15,description="To Verify Sorting By Ascending")
    public void SortingByAscending() throws Exception {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
		userOnBoardingPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Onboarding (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(userOnBoardingPage.verifyexportToExcelSheet(maplist));
    }

    @Test(priority=16,description="To Verify Sorting By Descending ")
    public void SortingByDescending() throws Exception {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
		userOnBoardingPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Onboarding (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(userOnBoardingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=17,description="To Verify Drag and Drop Columns ")
    public void VerifyDragAndDrop() {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
		userOnBoardingPage.dragColumntoGroup("First Name");
        Assert.assertTrue(userOnBoardingPage.verifyDragColumntoGroup("First Name"),"drag and drop assertion failed");
    }
    @Test(priority=18,description="To Verify Drag and drop Already Grouped Header ")
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
    	UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectMakeUserOnBoardingChanges();
		userOnBoardingPage.dragColumntoGroup("Last Name");
		userOnBoardingPage.dragColumntoGroup("Last Name");
        Assert.assertTrue(userOnBoardingPage.verifyDragColumntoGroup("Last Name"),"drag and drop assertion failed");
    }
    
    @Test(priority=20,description="To Verify Search Page ")
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
	    UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
        Assert.assertFalse(userOnBoardingPage.clearAll(userOnBoardingDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("UserOnBoardingUITest", "clearall");
        Assert.assertTrue(userOnBoardingPage.verifyclose());
        screenshot.captureScreen("UserOnBoardingUITest", "SearchClose");
    }

    @Test(priority=21,description="To Verify search without SearchTextbox ")
    public void searchwithoutSearchTextbox() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
	    UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
	    userOnBoardingPage.searchwithoutextsearch(userOnBoardingDetails);
    	Assert.assertFalse(userOnBoardingPage.getErrorMsg());
    }

    @Test(priority=22,description="To Verify SearchClearSearch ")
    public void SearchClearSearch() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
    	UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
	    UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
        Assert.assertTrue(userOnBoardingPage.verifyApprovedSectionData(userOnBoardingDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("UserOnBoardingUITest","Invalid Search with wrong data");
        Assert.assertTrue(userOnBoardingPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=23,description="To Verify Search Is NotEqual To")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
	    UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
	    userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
	    userOnBoardingPage.selectMakeUserOnBoardingChanges();
		Assert.assertTrue(userOnBoardingPage.verifySearchIsNotEqualTo(userOnBoardingDetails.getFirstname()));
	}

	@Test(priority=24,description="To Verify Search Contains ")
	public void VerifySearchContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
	    UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
	    userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
	    userOnBoardingPage.selectMakeUserOnBoardingChanges();
		Assert.assertTrue(userOnBoardingPage.verifySearchContains(userOnBoardingDetails.getFirstname()));
	}

	@Test(priority=25,description=" To Verify Search Does Not Contains")
	public void VerifySearchDoesNotContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
	    UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
	    userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
	    userOnBoardingPage.selectMakeUserOnBoardingChanges();
		Assert.assertTrue(userOnBoardingPage.verifySearchDoesNotContains(userOnBoardingDetails.getFirstname()));
	}

	@Test(priority=26,description="To Verify Search Starts With ")
	public void VerifySearchStartsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
	    UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
	    userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
	    userOnBoardingPage.selectMakeUserOnBoardingChanges();
		Assert.assertTrue(userOnBoardingPage.verifySearchStartsWith(userOnBoardingDetails.getFirstname()));
	}

	@Test(priority=27,description="To Verify Search Ends With  ")
	public void VerifySearchEndsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(3);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
	    UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
	    userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
	    userOnBoardingPage.selectMakeUserOnBoardingChanges();
		Assert.assertTrue(userOnBoardingPage.verifySearchEndsWith(userOnBoardingDetails.getFirstname()));

	}
	
	@Test(groups= {"Maker"},priority=28,description="To Verify Add Record Cancel Button ")
	public void VerifyAddCancelButton() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.addCancelButton(userOnBoardingDetails), "Add cancel button assertion failed");
	}

	@Test(groups= {"Maker"},priority=29,description="To Verify the Step Icons and Add Record with all the Fields Blank  ")
	public void VerifyStepIconPresenceandAddRecordWithAlltheFiledsBlank() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyAddNewUserOnBoardingStepIconsandCreateRecordWithAlltheFieldsBlank(), "Add cancel button assertion failed");
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(),"Please Provide First Name, Last Name, Lan ID, Avaya Login ID, Org. Unit, Profile, Supervisor", "Add cancel button assertion failed");
	}

	@Test(groups= {"Maker"},priority=30,description="To Verify Add Record without FirstName ")
	public void VerifyAddRecordWithoutFirstName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.addNewUserOnBoardingRecordWithoutFirstName(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Please Provide First Name","Record Creation Assertion failed");
	}

	@Test(groups= {"Maker"},priority=31,description="To Verify Add Record without Last Name ")
	public void VerifyAddRecordWithoutLastName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.addNewUserOnBoardingRecordWithoutLastName(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Please Provide Last Name","Record Creation Assertion failed");
	}

	@Test(groups= {"Maker"},priority=32,description="To Verify Add Record without Lan ID ")
	public void VerifyAddRecordWithoutLanID() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.addNewUserOnBoardingRecordWithoutLanID(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Please Provide Lan ID","Record Creation Assertion failed");
	}

	@Test(groups= {"Maker"},priority=33,description="To Verify Add Record without Avaya Login ID ")
	public void VerifyAddRecordWithoutAvayaLoginID() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.addNewUserOnBoardingRecordWithoutAvayaLoginID(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Please Provide Avaya Login ID","Record Creation Assertion failed");
	}

	@Test(groups= {"Maker"},priority=34,description="To Verify Add Record without Org.Unit ")
	public void VerifyAddRecordWithoutOrgUnit() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.addNewUserOnBoardingRecordWithoutOrgUnit(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Please Provide Org. Unit","Record Creation Assertion failed");
	}

	@Test(groups= {"Maker"},priority=35,description="To Verify Add Record without Profile ")
	public void VerifyAddRecordWithoutProfile() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.addNewUserOnBoardingRecordWithoutProfile(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Please Provide Profile, Supervisor","Record Creation Assertion failed");
	}
	
	
	
	@AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("UserOnBoardingUITest",method.getName());
	        driver.navigate().refresh();
	}
}
