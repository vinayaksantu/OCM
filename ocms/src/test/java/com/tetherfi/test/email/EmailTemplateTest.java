package com.tetherfi.test.email;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.email.EmailTemplateDetails;
import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.TmacUserDetails;
import com.tetherfi.pages.EmailPage;
import com.tetherfi.pages.EmailTemplatePage;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;

import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class EmailTemplateTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
    
	@BeforeMethod
    public void NavigateToEmailTemplatePage(Method method) throws InterruptedException, Exception {
    	HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
	    homePage.navigateToOCMPage();
	    OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
	        ocmHomePage.navigateToTab("Email");
	        EmailPage EmailPage = PageFactory.createPageInstance(driver,EmailPage.class);
	        Assert.assertTrue(EmailPage.isEmailPageDisplayed(),"Email page assertion failed");
	        EmailPage.navigateToEmailTemplatesPage();
	        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
	        Assert.assertTrue(EmailTemplatePage.isEmailTemplatePageDisplayed(),"Email Template page assertion failed");
      }
    
   /* @Test(priority=1)
	public void EmailTemplatePage() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Assert.assertTrue(EmailTemplatePage.VerifyLogo(),"Logo assertion failed");
        Assert.assertTrue(EmailTemplatePage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("EmailTemplateTest","Maximize");
    	Assert.assertTrue(EmailTemplatePage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("EmailTemplateTest","Minimize");	
    }
	
    @Test(priority=2)
    public void VerifyDepartementPage() throws InterruptedException {
    	EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Thread.sleep(1000);
        Assert.assertTrue(EmailTemplatePage.isDepartmentTabDisplayed(), "Department Page assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyGroupPage() throws InterruptedException {
    	EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Thread.sleep(1000);
        Assert.assertTrue(EmailTemplatePage.isGroupTabDisplayed(), "Group Page assertion failed");
    }    
    
    @Test(priority=4)
    public void VerifyDepartmentsDropdownForAllTheColumns() throws Exception {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Thread.sleep(1000);
        Assert.assertTrue(EmailTemplatePage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=5)
    public void VerifyDepartmentsColumnsHeaderEnable() throws Exception {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Thread.sleep(1000);
        Assert.assertTrue(EmailTemplatePage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=6)
    public void VerifyDepartmentsColumnsHeaderDisable() throws Exception {
    	 EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
         EmailTemplatePage.navigateToTab("Departments");
         Thread.sleep(1000);
         Assert.assertFalse(EmailTemplatePage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=7)
    public void VerifyGroupsDropdownForAllTheColumns() throws Exception {
    	EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Thread.sleep(2000);
        Assert.assertTrue(EmailTemplatePage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=8)
    public void VerifyGroupsColumnsHeaderEnable() throws Exception {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Thread.sleep(2000);
        Assert.assertTrue(EmailTemplatePage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=9)
    public void VerifyGroupsColumnsHeaderDisable() throws Exception {
    	 EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
    	 EmailTemplatePage.navigateToTab("Groups");
         Thread.sleep(2000);
         Assert.assertFalse(EmailTemplatePage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=10)
    public void VerifyMainsDropdownForAllTheColumns() throws Exception {
    	EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Assert.assertTrue(EmailTemplatePage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=11)
    public void VerifyMainsColumnsHeaderEnable() throws Exception {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Assert.assertTrue(EmailTemplatePage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=12)
    public void VerifyMainsColumnsHeaderDisable() throws Exception {
    	 EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
         Assert.assertFalse(EmailTemplatePage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
	
    @Test(priority=13)
    public void VerifyDepartmentArrowMoveForPreviousAndNextPage() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.verifyArrowMoveForPreviousAndNextPage(0),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=14)
    public void VerifyDepartmentArrowMoveForFirstAndLastPage() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.verifyArrowMoveForFirstAndLastPage(0),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=15)
    public void VerifyDepartmentTotalNumberOfItemsPerPageDetails() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.verifyTotalNumberOfItemsPerPageDetails(0),"item per page assertion failed");
    }
    
    @Test(priority=16)
    public void VerifyDepartmentNumberOfItemsPerPageSelection() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.verifyNumberOfItemsPerPage(0),"item per page assertion failed");
    }
    
    @Test(priority=17)
    public void VerifyGroupsArrowMoveForPreviousAndNextPage() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.verifyArrowMoveForPreviousAndNextPage(1),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=18)
    public void VerifyGroupsArrowMoveForFirstAndLastPage() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.verifyArrowMoveForFirstAndLastPage(1),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=19)
    public void VerifyGroupsTotalNumberOfItemsPerPageDetails() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.verifyTotalNumberOfItemsPerPageDetails(1),"item per page assertion failed");
    }
    
    @Test(priority=20)
    public void VerifyGroupsNumberOfItemsPerPageSelection() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.verifyNumberOfItemsPerPage(1),"item per page assertion failed");
    }
    
    @Test(priority=21)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Assert.assertTrue(EmailTemplatePage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=22)
    public void VerifyArrowMoveForFirstAndLastPage() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Assert.assertTrue(EmailTemplatePage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=23)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Assert.assertTrue(EmailTemplatePage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
    @Test(priority=24)
    public void VerifyNumberOfItemsPerPageSelection() {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Assert.assertTrue(EmailTemplatePage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=25)
    public void VerifyDepartmentDragAndDrop() throws Exception {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Thread.sleep(2000);
        EmailTemplatePage.dragDepartmentColumntoGroup("Enabled");
        Assert.assertTrue(EmailTemplatePage.verifyDragColumntoGroup("Enabled", 0),"drag and drop assertion failed");
    }
    @Test(priority=26)
    public void VerifyDepartmentDragAndDropofAlreadyGroupedHeader() throws Exception {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Thread.sleep(2000);
        EmailTemplatePage.dragDepartmentColumntoGroup("Enabled");
        EmailTemplatePage.dragDepartmentColumntoGroup("Enabled");
        Assert.assertTrue(EmailTemplatePage.verifyDragColumntoGroup("Enabled",0),"drag and drop assertion failed");
    }
    
   @Test(priority=27)
    public void VerifyGroupsDragAndDrop() throws Exception {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Thread.sleep(2000);
        EmailTemplatePage.dragGroupsColumntoGroup("Enabled");
        Assert.assertTrue(EmailTemplatePage.verifyDragColumntoGroup("Enabled",1),"drag and drop assertion failed");
    }
    @Test(priority=28)
    public void VerifyGroupsDragAndDropofAlreadyGroupedHeader() throws Exception {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Thread.sleep(2000);
        EmailTemplatePage.dragGroupsColumntoGroup("Enabled");
        EmailTemplatePage.dragGroupsColumntoGroup("Enabled");
        Assert.assertTrue(EmailTemplatePage.verifyDragColumntoGroup("Enabled",1),"drag and drop assertion failed");
    }
    
    @Test(priority=29)
    public void VerifyDragAndDrop() throws Exception {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Thread.sleep(2000);
        EmailTemplatePage.dragColumntoGroup("Enabled");
        Assert.assertTrue(EmailTemplatePage.verifyDragColumntoGroup("Enabled",2),"drag and drop assertion failed");
    }
    @Test(priority=30)
    public void VerifyDragAndDropofAlreadyGroupedHeader() throws Exception {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Thread.sleep(2000);
        EmailTemplatePage.dragColumntoGroup("Enabled");
        EmailTemplatePage.dragColumntoGroup("Enabled");
        Assert.assertTrue(EmailTemplatePage.verifyDragColumntoGroup("Enabled",2),"drag and drop assertion failed");
    }
    
    @Test(priority=31)
    public void DepartmentExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.verifyDepartmentExportToExcel(filePath));
    }
    
    @Test(priority=32)
    public void DepartmentExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\EmailTemplateDepartment.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.verifyexportToExcelDepartmentSheet(maplist));	
    } 
    
    @Test(priority=33)
    public void DepatmentSortingByAscending() throws IOException {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.SortByDepartmentAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\EmailTemplateDepartment (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(EmailTemplatePage.verifyexportToExcelDepartmentSheet(maplist));
    }
    
    @Test(priority=34)
    public void DepartmentSortingByDescending() throws IOException {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.SortByDepartmentDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\EmailTemplateDepartment (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(EmailTemplatePage.verifyexportToExcelDepartmentSheet(maplist));
    }
    
    @Test(priority=35)
    public void GroupsExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.verifyGroupsExportToExcel(filePath));
    }
    
    @Test(priority=36)
    public void GroupsExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\EmailTemplateGroup.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.verifyexportToExcelGroupsSheet(maplist));	
    } 
    
    @Test(priority=37)
    public void GroupsSortingByAscending() throws IOException {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.SortByGroupAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\EmailTemplateGroup (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(EmailTemplatePage.verifyexportToExcelGroupsSheet(maplist));
    }
    
    @Test(priority=38)
    public void GroupsSortingByDescending() throws IOException {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.SortByGroupDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\EmailTemplateGroup (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(EmailTemplatePage.verifyexportToExcelGroupsSheet(maplist));
    }
    
    @Test(priority=39)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Assert.assertTrue(EmailTemplatePage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=40)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\EmailTemplate.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        Assert.assertTrue(EmailTemplatePage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=41)
    public void SortingByAscending() throws IOException {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\EmailTemplate (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(EmailTemplatePage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=42)
    public void SortingByDescending() throws IOException {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\EmailTemplate (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(EmailTemplatePage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=43)
    public void DepartmentExporttoExcelWithoutData() throws Exception
    {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.DepartmentExporttoExcelWithoutData(EmailTemplateDetails));
    }
    
    @Test(priority=44)
    public void GroupExporttoExcelWithoutData() throws Exception
    {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.GroupExporttoExcelWithoutData(EmailTemplateDetails));
    }
    
    @Test(priority=45)
    public void ExporttoExcelWithoutData() throws Exception
    {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        Assert.assertTrue(EmailTemplatePage.ExporttoExcelWithoutData(EmailTemplateDetails));
    }
    
    @Test(priority=46)
    public void SearchDepartmentClearSearch() throws Exception
    {
 	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.verifyinvalidDepartmentsearchwithwrongdata(EmailTemplateDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("EmailTemplateTest","Invalid Search with wrong data");
        Assert.assertTrue(EmailTemplatePage.verifyDepartmentclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=47)
    public void SearchGroupClearSearch() throws Exception
    {
 	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.verifyinvalidGroupsearchwithwrongdata(EmailTemplateDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("EmailTemplateTest","Invalid Search with wrong data");
        Assert.assertTrue(EmailTemplatePage.verifyGroupclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=48)
    public void SearchClearSearch() throws Exception
    {
 	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        Assert.assertTrue(EmailTemplatePage.verifyinvalidsearchwithwrongdata(EmailTemplateDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("EmailTemplateTest","Invalid Search with wrong data");
        Assert.assertTrue(EmailTemplatePage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=49)
    public void searchDepartmentPage() throws Exception {
  	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertFalse(EmailTemplatePage.departmentclearAll(EmailTemplateDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("EmailTemplateTest", "clearall");
        Assert.assertTrue(EmailTemplatePage.verifyDepartmentclose());
    }
    
    @Test(priority=50)
    public void searchDepartmentwithoutSearchTextbox() throws IOException {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.searchwithoutextsearch();
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=51)
    public void searchGroupsPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertFalse(EmailTemplatePage.GroupclearAll(EmailTemplateDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("EmailTemplateTest", "clearall");
        Assert.assertTrue(EmailTemplatePage.verifyGroupclose());
    }
    
    @Test(priority=52)
    public void searchGroupwithoutSearchTextbox() throws IOException {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.searchwithoutextsearch();
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=53)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        Assert.assertFalse(EmailTemplatePage.clearAll(EmailTemplateDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("EmailTemplateTest", "clearall");
        Assert.assertTrue(EmailTemplatePage.verifyclose());
    }
    
    @Test(priority=54)
    public void searchwithoutSearchTextbox() throws IOException {
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.searchwithoutextsearch();
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
   
    @Test(priority=55)
    public void AddNewDepartmentRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.isDepartmentTabDisplayed());
        EmailTemplatePage.addNewDepartmentRecord(EmailTemplateDetails);
        Assert.assertTrue(EmailTemplatePage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    @Test(priority=56)//,dependsOnMethods ="AddNewDepartmentRecord")
    public void AddDuplicateDepartmentRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.addNewDepartmentRecord(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Duplicate Name");
    }
    
    @Test(priority=57)//,dependsOnMethods ="AddNewDepartmentRecord")
    public void VerifyAuditTrialReportForCreateDepartment() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplateDepartmentCreate(EmailTemplateDetails,"Create"));
    }
    
    @Test(priority=58)
    public void AddEmptyDepartmentRecord() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.addNewEmptyRecord(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Name, Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=59)
    public void AddRecordWithoutDepartment() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.addRecordWithoutDepartment(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=60)
    public void AddRecordWithoutEnabled() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.addRecordWithoutEnabled(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=61)
    public void VerifyCancelBtnAtAddDepartmentRecord(){
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.clickOnAddDepartmentRecord();
        EmailTemplatePage.clickOnCancelBtn();
        Assert.assertTrue(EmailTemplatePage.verifyDepartmentGrid(), "Cancel Btn at Add record assertion failed");
	}
    
    @Test(priority=62)//,dependsOnMethods = "AddNewDepartmentRecord")
    public void AddNewGroupsRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.isGroupTabDisplayed());
        EmailTemplatePage.addNewGroupRecord(EmailTemplateDetails);
        Assert.assertTrue(EmailTemplatePage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    @Test(priority=63)//,dependsOnMethods = "AddNewGroupsRecord")
    public void AddDuplicateGroupsRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.addNewGroupRecord(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Duplicate Name");
    }
    
    @Test(priority=64)//,dependsOnMethods ="AddNewGroupsRecord")
    public void VerifyAuditTrialReportForCreateGroup() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplateGroupCreate(EmailTemplateDetails,"Create"));
    }
    
    @Test(priority=65)
    public void AddEmptyGroupRecord() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.addNewEmptyGroupRecord(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Department Name, Name, Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=66)
    public void AddRecordWithoutDepartmentName() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.addRecordWithoutDepartmentName(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Department Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=67)
    public void AddRecordWithoutName() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.addRecordWithoutName(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=68)
    public void AddRecordWithoutGroupEnabled() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.addRecordWithoutGroupEnabled(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=69)
    public void VerifyCancelBtnAtAddGroupsRecord(){
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.clickOnAddGroupRecord();
        EmailTemplatePage.clickOnCancelBtn();
        Assert.assertTrue(EmailTemplatePage.verifyGroupGrid(), "Cancel Btn at Add record assertion failed");
	}
    */
    @Test(priority=70)//,dependsOnMethods = "AddNewGroupsRecord")
    public void AddNewEmailTemplateRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.addNewEmailTemplateRecord(EmailTemplateDetails);
        Assert.assertTrue(EmailTemplatePage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    @Test(priority=71)//,dependsOnMethods = "AddNewEmailTemplateRecord")
    public void AddDuplicateEmailTemplateRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.addNewEmailTemplateRecord(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Duplicate Name");
    }
    
    @Test(priority=72)//,dependsOnMethods ="AddNewEmailTemplateRecord")
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplateCreate(EmailTemplateDetails,"Create"));
    }
    
    @Test(priority=73)
    public void AddEmptyEmailTemplateRecord() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.addNewEmptyEmailTemplateRecord(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Department Name, Group Name, Name, Enabled, Text, Start Time, End Time", "Add invalid record assertion failed");
    }
    
    @Test(priority=74)
    public void AddRecordWithoutEmailDepartmentName() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.addRecordWithoutEmailDepartmentName(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Department Name, Group Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=75)
    public void AddRecordWithoutGroupsName() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.addRecordWithoutGroupName(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Group Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=76)
    public void AddRecordWithoutEmailTemplateName() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.addRecordWithoutEmailName(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=77)
    public void AddRecordWithoutSubject() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.addRecordWithoutSubject(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Subject", "Add invalid record assertion failed");
    }
    
    @Test(priority=78)
    public void AddRecordWithoutEmailEnabled() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.addRecordWithoutEmailEnabled(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=79)
    public void AddRecordWithoutType() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.addRecordWithoutType(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"Please Provide Type", "Add invalid record assertion failed");
    }
    
    @Test(priority=80)
    public void AddRecordWithoutBody() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails = new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.addRecordWithoutBody(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"×\nPlease Provide Enabled", "Add invalid record assertion failed");
    }
    
    @Test(priority=81)
    public void VerifyCancelBtnAtAddEmailTemplateRecord(){
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.clickOnAddRecord();
        EmailTemplatePage.clickOnCancelBtn();
        Assert.assertTrue(EmailTemplatePage.verifyEmailGrid(), "Cancel Btn at Add record assertion failed");
	}
    
    
    
    
   /* @Test(priority=69,groups= {"OCM"})//,dependsOnMethods = "AddNewEmailTemplateRecord")
    public void EditEmailTemplateRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.editEmailTemplateRecord(EmailTemplateDetails);
        Assert.assertTrue(EmailTemplatePage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    @Test(priority=70,groups= {"OCM"},dependsOnMethods="EditEmailTemplateRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplateUpdate(EmailTemplateDetails,"Update"));
    }
    
    @Test(priority=71,groups= {"OCM"})
    public void EditWithoutModifyReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.editEmailTemplateWithoutModifyReason(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"×\nPlease enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=72,groups= {"OCM"})
    public void VerifyCancelBtnAtEditRecord() throws Exception{
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.searchEmailTemplateRecord("PalakEmailTemplate");
        Thread.sleep(1000);
        EmailTemplatePage .clickOnEditButton();
        EmailTemplatePage .clickOnCancelBtn();
        Assert.assertFalse(EmailTemplatePage .verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=74,groups= {"OCM"})//,dependsOnMethods = "AddNewEmailTemplateRecord")
    public void EditGroupRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.isGroupTabDisplayed());
        EmailTemplatePage.editGroupRecord(EmailTemplateDetails);
        Assert.assertTrue(EmailTemplatePage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    @Test(priority=75,groups= {"OCM"},dependsOnMethods="EditGroupRecord")
    public void VerifyAuditTrialReportForGroupUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplateGroupUpdate(EmailTemplateDetails,"Update"));
    }
    
    @Test(priority=76,groups= {"OCM"})
    public void EditWithoutModifyReasonGroupRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditGroup").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.editEmailTemplateGroupWithoutModifyReason(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"×\nPlease enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=77,groups= {"OCM"})
    public void VerifyCancelBtnAtEditGroupRecord() throws Exception{
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.searchGroupRecord("GrpPalak");
        Thread.sleep(1000);
        EmailTemplatePage .clickOnEditButton();
        EmailTemplatePage .clickOnCancelBtn();
        Assert.assertFalse(EmailTemplatePage .verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=78,groups= {"OCM"})
    public void VerifyUpdateGroupRecordInMain() throws Exception {
  	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"EditGroup").getTestData().get(0);
         EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
         EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
         EmailTemplatePage.clickOnAddRecord();
         Assert.assertFalse(EmailTemplatePage.VerifyGroupDropdown(EmailTemplateDetails));
     }
    
    @Test(priority=80,groups= {"OCM"})
    public void EditDepartmentRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.isDepartmentTabDisplayed());
        EmailTemplatePage.editDepartmentRecord(EmailTemplateDetails);
        Assert.assertTrue(EmailTemplatePage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    @Test(priority=81,groups= {"OCM"},dependsOnMethods="EditDepartmentRecord")
    public void VerifyAuditTrialReportForDepartmentUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplateDepartmentUpdate(EmailTemplateDetails,"Update"));
    }
       
    @Test(priority=82,groups= {"OCM"})
    public void EditWithoutModifyReasonDepartmentRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.editEmailDepartmentWithoutModifyReason(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"×\nPlease enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=83,groups= {"OCM"})
    public void VerifyCancelBtnAtEditDepartmentRecord() throws Exception{
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver, EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.searchDepartmentRecord("DeptPalak");
        Thread.sleep(1000);
        EmailTemplatePage .clickOnEditButton();
        EmailTemplatePage .clickOnCancelBtn();
        Assert.assertFalse(EmailTemplatePage .verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
   
   @Test(priority=84,groups= {"OCM"})
   public void VerifyUpdateDepartmentRecordInGroup() throws IOException {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
       EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
       EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
       EmailTemplatePage.navigateToTab("Groups");
       EmailTemplatePage.clickOnAddGroupRecord();
       Assert.assertFalse(EmailTemplatePage.VerifyDepartmentDropdown(EmailTemplateDetails));
   }
   
   @Test(priority=85,groups= {"OCM"})
   public void VerifyUpdateDepartmentRecordInMain() throws IOException {
 	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.clickOnAddRecord();
        Assert.assertFalse(EmailTemplatePage.VerifyDepartmentDropdown(EmailTemplateDetails));
    }
       
   
    @Test(priority=92,groups= {"OCM"})
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.deleteEmailTempalateWithoutDeleteReasonRecord(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"×\nPlease enter the delete reason","empty delete reason record assertion failed");
    }
    
    @Test(priority=93,groups= {"OCM"})
    public void VerifyCancelBtnAtDeleteEmailTemplateRecord() throws Exception{
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.searchEmailTemplateRecord("PalakEmailTemplate");
        Thread.sleep(1000);
        EmailTemplatePage.clickOnDeleteButton();
        EmailTemplatePage.clickOnDeleteCancelBtn();
        Assert.assertFalse(EmailTemplatePage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
   
    @Test(priority=94,groups= {"OCM"})//dependsOnMethods = "EditEmailTemplateRecord")
    public void DeleteEmailTemplateRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.deleteEmailTemplateRecord(EmailTemplateDetails.getName(),EmailTemplateDetails.getDeleteReason());
        Assert.assertTrue(EmailTemplatePage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    
    @Test(priority=95,groups= {"OCM"},dependsOnMethods= {"DeleteEmailTemplateRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyEmailTemplatedelete(EmailTemplateDetails,"Delete"));
    }
    
    @Test(priority=97,groups= {"OCM"})
    public void DeleteGroupWithoutDeleteReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.deleteGroupsWithoutDeleteReasonRecord(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"×\nPlease enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=98,groups= {"OCM"})
    public void VerifyCancelBtnAtDeleteGroupRecord() throws Exception{
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        EmailTemplatePage.searchGroupRecord("GrpPalak");
        Thread.sleep(1000);
        EmailTemplatePage.clickOnDeleteButton();
        EmailTemplatePage.clickOnDeleteCancelBtn();
        Assert.assertFalse(EmailTemplatePage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    
    @Test(priority=99,groups= {"OCM"})//dependsOnMethods = {"EditGroupRecord","DeleteEmailTemplateRecord"})
    public void DeleteGroupRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Groups");
        Assert.assertTrue(EmailTemplatePage.isGroupTabDisplayed());
        EmailTemplatePage.deleteGroupRecord(EmailTemplateDetails.getGroupName(),EmailTemplateDetails.getDeleteReason());
        Assert.assertTrue(EmailTemplatePage.verifyRecordDeleted(),"Delete record assertion failed");
    }
     
    @Test(priority=100,groups= {"OCM"})//,dependsOnMethods= {"DeleteGroupRecord"})
    public void VerifyAuditTrialReportForGroupDelete() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyGroupdelete(EmailTemplateDetails,"Delete"));
    }
    
    @Test(priority=101,groups= {"OCM"})
    public void VerifyDeleteGroupRecordInMain() throws Exception {
  	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
         EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
         EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
         EmailTemplatePage.clickOnAddRecord();
         Assert.assertFalse(EmailTemplatePage.VerifyGroupDropdown(EmailTemplateDetails));
     }
    
   @Test(priority=102,groups= {"OCM"})
    public void DeleteDepartmentWithoutDeleteReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.deleteDepartmentWithoutDeleteReasonRecord(EmailTemplateDetails);
        Assert.assertEquals(EmailTemplatePage.getMessage(),"×\nPlease enter the delete reason","empty delete reason record assertion failed");
    }
   
    @Test(priority=103,groups= {"OCM"})
    public void VerifyCancelBtnAtDeleteDepartmentRecord() throws Exception{
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        EmailTemplatePage.searchDepartmentRecord("DeptPalak");
        Thread.sleep(1000);
        EmailTemplatePage.clickOnDeleteButton();
        EmailTemplatePage.clickOnDeleteCancelBtn();
        Assert.assertFalse(EmailTemplatePage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    
   @Test(priority=104,groups= {"OCM"})//dependsOnMethods = {"EditDepartmentRecord","DeleteGroupRecord","DeleteEmailTemplateRecord"})
    public void DeleteDepartmentRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.navigateToTab("Departments");
        Assert.assertTrue(EmailTemplatePage.isDepartmentTabDisplayed());
        EmailTemplatePage.deleteDepartmentRecord(EmailTemplateDetails.getDepartmentName(),EmailTemplateDetails.getDeleteReason());
        Assert.assertTrue(EmailTemplatePage.verifyRecordDeleted(),"Delete record assertion failed");
    }
   @Test(priority=105,groups= {"OCM"},dependsOnMethods= {"DeleteDepartmentRecord"})
   public void VerifyAuditTrialReportForDepartmentDelete() throws Exception {
   	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
       HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
       homePage.navigateToOCMReportsPage();
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
       String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
       Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map1);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDepartmentdelete(EmailTemplateDetails,"Delete"));
   }
   
   @Test(priority=106,groups= {"OCM"})
   public void VerifyDeleteDepartmentRecordInGroup() throws IOException {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
       EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
       EmailTemplatePage.navigateToTab("Groups");
       EmailTemplatePage.clickOnAddGroupRecord();
       Assert.assertFalse(EmailTemplatePage.VerifyDepartmentDropdown(EmailTemplateDetails));
   }
   
   @Test(priority=107,groups= {"OCM"})
   public void VerifyDeleteDepartmentRecordInMain() throws IOException {
 	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
        EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
        EmailTemplatePage.clickOnAddRecord();
        Assert.assertFalse(EmailTemplatePage.VerifyDepartmentDropdown(EmailTemplateDetails));
    }
    
   @Test(priority=111,groups= {"OCM"})
   public void DepartmentDatabase() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
       EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
       EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
       EmailTemplatePage.navigateToTab("Departments");
       Assert.assertTrue(EmailTemplatePage.verifyDepartmentDatabase(EmailTemplateDetails.getQuery()));
   }
   
   @Test(priority=112,groups= {"OCM"})
   public void GroupDatabase() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(1);
       EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
       EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
       EmailTemplatePage.navigateToTab("Groups");
       Assert.assertTrue(EmailTemplatePage.verifyGroupDatabase(EmailTemplateDetails.getQuery()));
   }
   
   @Test(priority=113,groups= {"OCM"})
   public void Database() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\EmailTemplateData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(2);
       EmailTemplateDetails EmailTemplateDetails= new EmailTemplateDetails(map);
       EmailTemplatePage EmailTemplatePage = PageFactory.createPageInstance(driver,EmailTemplatePage.class);
       Assert.assertTrue(EmailTemplatePage.verifyDatabase(EmailTemplateDetails.getQuery()));
   }*/
   
    @AfterMethod
	 public void close(Method method){
    	Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("FaxLineConfigTest",method.getName());
        driver.navigate().refresh();    
        }
	
}
