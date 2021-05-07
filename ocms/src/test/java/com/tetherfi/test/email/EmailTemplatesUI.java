package com.tetherfi.test.email;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.email.EmailTemplateDetails;
import com.tetherfi.model.email.EmailTemplatesDetails;
import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.TmacUserDetails;

import com.tetherfi.pages.EmailPage;
import com.tetherfi.pages.EmailTemplatePage;
import com.tetherfi.pages.EmailTemplatesPage;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.RoleBasedAccessManagementPage;
import com.tetherfi.pages.EmailTemplatesPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class EmailTemplatesUI extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
    
	@BeforeClass
    public void NavigateToEmailTemplatePage() throws InterruptedException, Exception {
    	HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
	    homePage.navigateToOCMPage();
	    OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
	        ocmHomePage.navigateToTab("Email");
	        EmailPage EmailPage = PageFactory.createPageInstance(driver,EmailPage.class);
	        Assert.assertTrue(EmailPage.isEmailPageDisplayed(),"Email page assertion failed");
	        EmailPage.navigateToEmailTemplatesPage();
	        EmailTemplatesPage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatesPage.class);
	        Assert.assertTrue(EmailTemplatePage.isEmailTemplatesPageDisplayed(),"Email Templates page assertion failed");
      }
	@Test(priority=1,description="Maximize and Minimize window")
    public void EmailTemplatesPage() {
		EmailTemplatesPage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatesPage.class);
    	Assert.assertTrue(EmailTemplatePage.VerifyLogo(),"Email Templates assertion failed");
    	Assert.assertTrue(EmailTemplatePage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("EmailTemplatePage","maximize window");
    	Assert.assertTrue(EmailTemplatePage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("EmailTemplatePage","minimize window");
    }
	
	@Test(priority=2,description="To verify Email Templates Approved Data Tab column headers")
	public void VerifyEmailTemplateApprovedDataPage() {
		EmailTemplatesPage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatesPage.class);
        Assert.assertTrue(EmailTemplatePage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
     }
	
	@Test(priority=3,description="To verify Email Templates Audit Trail tab headers")
	public void VerifyEmailTemplateAuditTrailTabPage() {
		EmailTemplatesPage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatesPage.class);
		EmailTemplatePage.selectEmailTemplatesAuditTrailTab();
		Assert.assertTrue(EmailTemplatePage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
	}
	@Test(priority=4,description="To verify the buttons in Make Email Template changes")
    public void VerifyMakeEmailTemplateChangeButton() {
		EmailTemplatesPage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatesPage.class);
		EmailTemplatePage.selectEmailTemplatesAuditTrailTab();
		EmailTemplatePage.selectMakeEmailTemplateChanges();
		Assert.assertTrue(EmailTemplatePage.verifyAddNewEmailTemplateChangesRecordButton(), "add EmailTemplates assertion failed");
        Assert.assertTrue(EmailTemplatePage.verifyGoBackButton(), "Go back button assertion failed");
        Assert.assertTrue(EmailTemplatePage.verifyExportToExcelButton(), "export to excel button assertion failed");
        Assert.assertTrue(EmailTemplatePage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
       }
	@Test(priority=5,description="To verify the Columns in Make Email Template changes")
    public void VerifyDropdownForAllTheColumns() {
		EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
		EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        Assert.assertTrue(EmailTemplatesPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
       }
     
    @Test(priority=6,description="To verify enable Columns in Make Email Template changes")
    public void VerifyColumnsHeaderEnable() throws InterruptedException {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        Assert.assertTrue(EmailTemplatesPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
     }
       
    @Test(priority=7,description="To verify disable Columns in Make Email Template changes")
    public void VerifyColumnsHeaderDisable() {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        Assert.assertFalse(EmailTemplatesPage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
      }
    
    @Test(priority=8,description="To verify Arrow move for previous and next page in Make Email Template changes")
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        Assert.assertTrue(EmailTemplatesPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=9,description="To verify Arrow move for first and last page in Make Email Template changes")
    public void VerifyArrowMoveForFirstAndLastPage() {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        Assert.assertTrue(EmailTemplatesPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    @Test(priority=10,description="To verify total number of items per page in Make Email Template changes")
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        Assert.assertTrue(EmailTemplatesPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
	@Test(priority=11,description="To verify number of items per page in Make Email Template changes")
    public void VerifyNumberOfItemsPerPageSelection() {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        Assert.assertTrue(EmailTemplatesPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=12,description="Verify Export to excel")
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        Assert.assertTrue(EmailTemplatesPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=13,description="Verify the exported excel sheet")
    public void VerifyExportedPage() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Email Templates.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        Assert.assertTrue(EmailTemplatesPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=14,description="Verify export to excel wihtout data")
    public void VerifyExportToExcelWithoutData() throws Exception {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
    	EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
        Assert.assertTrue(EmailTemplatesPage.ExporttoExcelWithoutData(detailspage));
       }
    
   @Test(priority=15,description="Sort by Ascending order")
    public void SortingByAscending() throws IOException {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        EmailTemplatesPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Email Templates (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(EmailTemplatesPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=16,description="Sort by descending order")
    public void SortingByDescending() throws IOException {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        EmailTemplatesPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Email Templates (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(EmailTemplatesPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=17,description="Verify Drag and Drop for a column")
    public void VerifyDragAndDrop() {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        EmailTemplatesPage.dragColumntoGroup("Template Name");
        Assert.assertTrue(EmailTemplatesPage.verifyDragColumntoGroup("Template Name"),"drag and drop assertion failed");
    }
    @Test(priority=18,description="Verify Drag and Drop for already grouped column")
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        EmailTemplatesPage.selectEmailTemplatesAuditTrailTab();
        EmailTemplatesPage.selectMakeEmailTemplateChanges();
        EmailTemplatesPage.dragColumntoGroup("Template Name");
        EmailTemplatesPage.dragColumntoGroup("Template Name");
        Assert.assertTrue(EmailTemplatesPage.verifyDragColumntoGroup("Template Name"),"drag and drop assertion failed");
    }
  /* not automated as there are some conflicts in db table 
   * @Test(priority=19)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
    	EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
    	Assert.assertTrue(EmailTemplatesPage.verifyDatabase(detailspage.getQuery()));
    }*/
    
    @Test(priority=20,description="To verify clearall and searchclose in search Panel")
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        Assert.assertFalse(EmailTemplatesPage.clearAll(detailspage),"ClearAll Assertion Failed");
        screenshot.captureScreen("EmailTemplatesUITest", "clearall");
        Assert.assertTrue(EmailTemplatesPage.verifyclose());
        screenshot.captureScreen("EmailTemplatesUITest", "SearchClose");
    }
    
    
    @Test(priority=21,description="Search without Textbox")
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
    	EmailTemplatesPage.searchwithoutextsearch(detailspage);
    	Assert.assertEquals(EmailTemplatesPage.getErrorMsg(),"Please enter the text to search or remove the filter");
    }
    
    @Test(priority=22,description="Verify clear search")
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\EmailTemplatesData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	EmailTemplatesDetails detailspage = new EmailTemplatesDetails(map);
    	EmailTemplatesPage EmailTemplatesPage = PageFactory.createPageInstance(driver, EmailTemplatesPage.class);
        Assert.assertTrue(EmailTemplatesPage.verifyApprovedSectionData(detailspage ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("EmailTemplates","Invalid Search with wrong data");
        Assert.assertTrue(EmailTemplatesPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
	
	@AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("EmailTemplatesUITest",method.getName());
	        driver.navigate().refresh();
	}
}