package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxAddressBookDetails;
import com.tetherfi.model.ivr.AdhocOptionEnhancementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.user.CepEventMappingDetails;
import com.tetherfi.model.user.TdmThresholdConfigDetails;

import com.tetherfi.pages.TdmThresholdConfigPage;
import com.tetherfi.pages.AdhocOptionEnhancementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class TdmThresholdConfigTest extends BaseTest {
Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
	public void NavigatetoTdmThresholdConfigPage() {
		 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMPage();
	     OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
	     Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
	     ocmHomePage.navigateToTdmThresholdConfigPage();
	     TdmThresholdConfigPage tdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
	     Assert.assertTrue(tdmThresholdConfigPage.isTdmThresholdConfigPageDisplayed(), "TDM Threshold Config page assertion failed");
	}
	
	@Test(priority=1)
    public void TdmThresholdConfigPage() {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
    	Assert.assertTrue(TdmThresholdConfigPage.verifylogo(),"FaxSenderslogo assertion failed");
    	Assert.assertTrue(TdmThresholdConfigPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("TdmThresholdConfigBookTest","maximize window");
    	Assert.assertTrue(TdmThresholdConfigPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("TdmThresholdConfigBookTest","minimize window");
    }
	
	@Test(priority=2)
	public void VerifyDropdownForAllTheColumns() {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		Assert.assertTrue(TdmThresholdConfigPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}
	    
	@Test(priority=3)
	public void VerifyColumnsHeaderEnable() {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		Assert.assertTrue(TdmThresholdConfigPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}
	    
	@Test(priority=4)
	public void VerifyColumnsHeaderDisable() {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		Assert.assertFalse(TdmThresholdConfigPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}
	
	@Test(priority=5)
	public void VerifyTeamsAssignToAgentswithDataBase() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
        TdmThresholdConfigDetails tdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.verifyDatabase(tdmThresholdConfigDetails.getQuery()));
	}
	
	@Test(priority=6)
    public void ExportToExcel() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertTrue(TdmThresholdConfigPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=7,enabled=false)
    public void ExportToExcelData() throws Exception{	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\TDM Threshold Configuration.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
    	Assert.assertTrue(TdmThresholdConfigPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=8)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertTrue(TdmThresholdConfigPage.verifySearchIsNotEqualTo(TdmThresholdConfigDetails.getTeamName()));
    }
    
    @Test(priority=9)
    public void VerifySearchContains() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(1);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertTrue(TdmThresholdConfigPage.verifySearchContains(TdmThresholdConfigDetails.getTeamName())); 
    }
    
    @Test(priority=10)
    public void VerifySearchDoesNotContains() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(1);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertTrue(TdmThresholdConfigPage.verifySearchDoesNotContains(TdmThresholdConfigDetails.getTeamName())); 
    }
    
    @Test(priority=11)
    public void VerifySearchStartsWith() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(2);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertTrue(TdmThresholdConfigPage.verifySearchStartsWith(TdmThresholdConfigDetails.getTeamName())); 
    }
    
    @Test(priority=12)
    public void VerifySearchEndsWith() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(3);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertTrue(TdmThresholdConfigPage.verifySearchEndsWith(TdmThresholdConfigDetails.getTeamName())); 
    }
    
    @Test(priority=13)
    public void SearchClearSearch() throws Exception
    {
  	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(1);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertTrue(TdmThresholdConfigPage.verifyinvalidsearchwithwrongdata(TdmThresholdConfigDetails), "InvalidSearchAssertionFailed");
        screenshot.captureScreen("TdmThresholdConfigTest", "Invalid Search");
        Assert.assertTrue(TdmThresholdConfigPage.verifyclearsearch(), "ClearSearch Assertion Failed");
    }
    
    @Test(priority=14)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertFalse(TdmThresholdConfigPage.clearAll(TdmThresholdConfigDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("TdmThresholdConfigTest","clearall");
        Assert.assertTrue(TdmThresholdConfigPage.verifyclose());
    }
    
    @Test(priority=15)
    public void ExporttoExcelWithoutData() throws Exception
    {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Search").getTestData().get(1);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.ExporttoExcelWithoutData(TdmThresholdConfigDetails));
    }
  
    @Test(priority=16,enabled=false)
    public void SortingByAscending() throws IOException {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		TdmThresholdConfigPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\TDM Threshold Configuration (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(TdmThresholdConfigPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=17,enabled=false)
    public void SortingByDescending() throws IOException {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		TdmThresholdConfigPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\TDM Threshold Configuration (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(TdmThresholdConfigPage.verifyexportToExcelSheet(maplist));
    }
	
    @Test(priority=18)
    public void GroupBy()
    {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
    	Assert.assertTrue(TdmThresholdConfigPage.groupby());
        screenshot.captureScreen("TdmThresholdConfigTest", "GroupBy");
    	Assert.assertTrue(TdmThresholdConfigPage.groupby());
        screenshot.captureScreen("TdmThresholdConfigTest", "AlreadyGroupBy");
    }
    
    @Test(priority=19)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
    	Assert.assertTrue(TdmThresholdConfigPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=20)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertTrue(TdmThresholdConfigPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    @Test(priority=21)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertTrue(TdmThresholdConfigPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=22)
    public void VerifyNumberOfItemsPerPageSelection() {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
        Assert.assertTrue(TdmThresholdConfigPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=23)
    public void searchwithoutSearchTextbox() throws IOException {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		TdmThresholdConfigPage.searchwithoutextsearch();
        Assert.assertEquals(TdmThresholdConfigPage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
	
	@Test(priority=24)
	public void VerifyTdmThresholdConfigThresholdDetailsPage() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPage(TdmThresholdConfigDetails),"threshold popup displayed");
        Assert.assertTrue(TdmThresholdConfigPage.verifyaddNewRowButton());
        Assert.assertTrue(TdmThresholdConfigPage.verifysaveButton());
        Assert.assertTrue(TdmThresholdConfigPage.verifycancelbutton());
	}
    
	@Test(priority=25)
	public void VerifyTdmThresholdConfigLabelsEnabled() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPage(TdmThresholdConfigDetails),"threshold popup displayed");
        Assert.assertTrue(TdmThresholdConfigPage.verifyPopupColumnsHeaderEnabled(),"columns enabled assertion failed");
	}
	
	@Test(priority=26)
	public void VerifyTdmThresholdConfigLabelsDiabled() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPage(TdmThresholdConfigDetails),"threshold popup displayed");
        Assert.assertFalse(TdmThresholdConfigPage.verifyPopupColumnsHeaderDisabled(),"columns enabled assertion failed");
	}
	
	//@Test(priority=27)
	public void VerifyAuxCodeswithTMACAuxCodesModule() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPage(TdmThresholdConfigDetails),"threshold popup displayed");
        
	}
	
	@Test(priority=28)
	public void VerifySaveChangesButtonWithoutUpdate() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPageSaveChanges(TdmThresholdConfigDetails);
        Assert.assertEquals(TdmThresholdConfigPage.VerifyInfoMessage(), "No rows has been changed", "Save Changes Buttons Assertion Failed");
	}
	
	@Test(priority=29)
	public void VerifySaveChangesButtonAfterAddNewRow() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPageSaveChangesAfterAddRow(TdmThresholdConfigDetails);
        Assert.assertEquals(TdmThresholdConfigPage.VerifyErrorMessage(), "Please Provide Aux Code From, Aux Code To", "Save Changes Buttons Assertion Failed");
	}
	
	@Test(priority=30)
	public void VerifySaveChangesButtonAfterAddNewRowandAuxCodeFrom() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPageSaveChangesAfterAddRowandAuxCodesFrom(TdmThresholdConfigDetails);
        Assert.assertEquals(TdmThresholdConfigPage.VerifyErrorMessage(), "Please Provide Aux Code To", "Save Changes Buttons Assertion Failed");
	}
	
	@Test(priority=31)
	public void VerifyAuxCodeToWhenAuxCodeFromisONCallHold() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.VerifyAuxCodeToWhenAuxCodeFromisONCallHold(TdmThresholdConfigDetails), "AuxCodeToAssertion Failed");
	}
	
	@Test(priority=32)
	public void VerifyDefaultValueofThreshHold() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.VerifyDefaultValueofThreshhold(TdmThresholdConfigDetails), "AuxCodeToAssertion Failed");
	}
	
	@Test(priority=33)
	public void VerifyDefaultValueofDelete() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.VerifyDefaultValueofDelete(TdmThresholdConfigDetails), "AuxCodeToAssertion Failed");
	}
	
	@Test(priority=34)
	public void VerifyDefaultValueofAllowStatusChange() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.VerifyDefaultValueofAllowstatusChange(TdmThresholdConfigDetails), "AuxCodeToAssertion Failed");
	}
	
	@Test(priority=35)
	public void VerifyDefaultValueofAllowNotification() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.VerifyDefaultValueofAllowstatusChange(TdmThresholdConfigDetails), "AuxCodeToAssertion Failed");
	}
	
	@Test(priority=36)
	public void VerifyAvailableAuxCodeToForOnCallHold() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        TdmThresholdConfigPage.VerifyAvalableAuxCodeToforOnCallHold(TdmThresholdConfigDetails);
        Assert.assertEquals(TdmThresholdConfigPage.VerifyErrorMessage(), "Please select different Aux code", "Available Aux Code assertion failed");
	}
	
	@Test(priority=37)
	public void addNewRow() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPage(TdmThresholdConfigDetails),"threshold popup displayed");
        TdmThresholdConfigPage.addNewRow(TdmThresholdConfigDetails);
        Assert.assertEquals(TdmThresholdConfigPage.getSuccessMessage(),"Record Creation Successfully");
	}
	
	@Test(priority=38,enabled=false)//dependsOnMethods = "addNewRow")
    public void VerifyAuditTrailReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyTdmThresholdConfigCreate(TdmThresholdConfigDetails,"Create"),"Audit Trail report assertion failed");
    }
	
	@Test(priority=39)
	public void UpdateNewRow() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPage(TdmThresholdConfigDetails),"threshold popup displayed");
        TdmThresholdConfigPage.updateNewRow(TdmThresholdConfigDetails);
        Assert.assertEquals(TdmThresholdConfigPage.getSuccessMessage(),"Record Updated Successfully");
	}
	
	@Test(priority=40)//,dependsOnMethods = "UpdateNewRow")
    public void VerifyAuditTrailReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyTdmThresholdConfigUpdate(TdmThresholdConfigDetails,"Update"),"Audit Trail report assertion failed");
    }
	
	@Test(priority=41)
	public void DeleteNewRow() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage = PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
        Assert.assertTrue(TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPage(TdmThresholdConfigDetails),"threshold popup displayed");
        TdmThresholdConfigPage.deleteNewRow(TdmThresholdConfigDetails);
        Assert.assertEquals(TdmThresholdConfigPage.getSuccessMessage(),"Record Updated Successfully");
	}
	
	@Test(priority=42)//dependsOnMethods = "DeleteNewRow")
    public void VerifyAuditTrailReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TdmThresholdConfigDetails TdmThresholdConfigDetails = new TdmThresholdConfigDetails(map);
	    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyTdmThresholdConfigDelete(TdmThresholdConfigDetails,"Update"),"Audit Trail report assertion failed");
    }
    
	@Test(priority=43)
	public void VerifyUpdateExistingRecordBySelectingMultipleRecords() throws Exception {
		TdmThresholdConfigPage TdmThresholdConfigPage= PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		TdmThresholdConfigDetails TdmThresholdConfigDetails=new TdmThresholdConfigDetails(map);
		Assert.assertTrue(TdmThresholdConfigPage.verifyTdmThresholdConfigThresholdDetailsPageBySelectingMultipleRecords(TdmThresholdConfigDetails),"threshold popup displayed");
		TdmThresholdConfigPage.addNewRowForMultipleRecords(TdmThresholdConfigDetails);
		Assert.assertEquals(TdmThresholdConfigPage.getSuccessMessage(),"Record Updated Successfully");
	}
	
    @Test(priority=44)
    public void verifyThresholdDetailsPageDataAfterMultiRecordUpdate() throws Exception {
    	TdmThresholdConfigPage TdmThresholdConfigPage= PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		TdmThresholdConfigDetails TdmThresholdConfigDetails=new TdmThresholdConfigDetails(map);
		Assert.assertTrue(TdmThresholdConfigPage.verifyThresholdDetailsPageDataAfterMultiRecordUpdate(TdmThresholdConfigDetails), "MultipleRecord updating Assertion Failed");
    }
    
    @Test(priority=45)
    public void verifyAddRulesButtonWithoutSelectingRecord() throws Exception {
    	TdmThresholdConfigPage TdmThresholdConfigPage= PageFactory.createPageInstance(driver, TdmThresholdConfigPage.class);
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TdmThresholdConfigData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		TdmThresholdConfigDetails TdmThresholdConfigDetails=new TdmThresholdConfigDetails(map);
		TdmThresholdConfigPage.VerifyAddRulesButtonwithoutSelectingRecord();
		Assert.assertEquals(TdmThresholdConfigPage.getSuccessMessage(),"There are no records selected");
    }
    
	
	@AfterMethod
	    public void afterEachMethod(Method method) {
	    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("TdmThresholdConfigTest",method.getName());
	        driver.navigate().refresh();    }
}
