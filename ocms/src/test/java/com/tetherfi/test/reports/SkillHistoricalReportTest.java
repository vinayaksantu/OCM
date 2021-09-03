package com.tetherfi.test.reports;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.user.DashboardColorCodeConfigDetails;
import com.tetherfi.pages.DashboardColorCodeConfigPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.AgentHistoricalReportPage;
import com.tetherfi.pages.AgentSkillHistoricalReportPage;
import com.tetherfi.pages.AgentSkillHistoricalReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SkillHistoricalReportTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToOcmReportsPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
	}

	@Test(priority=1)
	public void ShowOCMSkillhistoricalReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	}      
	@Test(priority=2)
	public void ShowOcmSkillhistoricalReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}  
	@Test(priority=3)
	public void ScheduleOCMSkillhistoricalReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}  
	@Test(priority=4)
	public void ExportOcmSkillhistoricalReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=5,dependsOnMethods ="ExportOcmSkillhistoricalReport")
	public void ViewDownloadedOcmSkillhistoricalReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}   

	//Sheet Name is "OCM Skill Historical Repor" should be "OCM Skill Historical Report" time being
	@Test(priority=6,dependsOnMethods ="ViewDownloadedOcmSkillhistoricalReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmSkillHistoricalReportInReportsDownloadPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Skill Historical Repor"));	
	}  

	@Test(priority=7)
	public void ShowOcmAgentSkillhistoricalReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 
	@Test(priority=8)
	public void ShowOcmAgentSkillhistoricalReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}  
	@Test(priority=9)
	public void ExportOcmAgentSkillhistoricalReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 
	@Test(priority=10)
	public void ViewDownloadedOcmAgentSkillhistoricalReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}

	//Sheet Name is "OCM Skill Historical Repor" should be "OCM Skill Historical Report" time being
	@Test(priority=11,dependsOnMethods ="ViewDownloadedOcmAgentSkillhistoricalReportInReportsDownloadPageDateRange",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmSkillHistoricalReportInReportsDownloadPageDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Skill Historical Repor"));	
	} 
	
	@Test(priority=12)
	public void ScheduleOcmAgentSkillhistoricalReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=13,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteWithoutDeleteReasonRecordinReportsDownloadforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
	}

	@Test(priority=14,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownloadDeleteBtnForDateRange() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	@Test(priority=15,description="Delete Record at Reports download Button")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}
	@Test(priority=16)
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
	}
	@Test(priority=17)
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);             
		Assert.assertTrue(skillHistoricalPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMAgentSkillhistoricalReport","Maximize");
		Assert.assertTrue(skillHistoricalPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMAgentSkillhistoricalReport","Minimize");	
	} 
	@Test(priority=18)
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}   
	@Test(priority=19)
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
	@Test(priority=20)
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertFalse(skillHistoricalPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	} 
	@Test(priority=21)
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}    
	@Test(priority=22)
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}  
	@Test(priority=23)
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}  
	@Test(priority=24)
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	} 
	
	@Test(priority=25)
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyExportToExcel(filePath1));
	}

	@Test(priority=26,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMSkillHistoricalReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=27)
	public void SchedulereportinskillHistoricalPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		skillHistoricalPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(skillHistoricalPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}

	@Test(priority=28)
	public void ExportToExcelForAgentSkillhistoricalReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		skillHistoricalPage.exportToExcel();
		Assert.assertTrue(skillHistoricalPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=29,dependsOnMethods ="ExportToExcelForAgentSkillhistoricalReport")
	public void ViewDownloadedOcmAgentSkillhistoricalReportInReportsDownloadPageinAgentSkillhistoricalPg() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		skillHistoricalPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
	}      

	@Test(priority=30,dependsOnMethods ="ViewDownloadedOcmAgentSkillhistoricalReportInReportsDownloadPageinAgentSkillhistoricalPg",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmSkillHistoricalReportInReportsDownloadPageInMainPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Skill Historical Repor"));	
	}

	@Test(priority=31)
	public void VerifySearchByFeatureForAgentSkillhistoricalReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
		Assert.assertTrue(skillHistoricalPage.verifySearchByTextbox(reportDetails));
	}

	@Test(priority=32)
	public void VerifySearchEqualsToSkillhistoricalReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}     
	@Test(priority=33)
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		skillHistoricalPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(skillHistoricalPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}  
	
	@Test(priority=34)
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	} 
	
	@Test(priority=35)
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifySearchContains(reportDetails.getSearchStr()));
	}
	
	@Test(priority=36)
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	} 
	
	@Test(priority=37)
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	@Test(priority=38)
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=39)
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=40)
	public void verifyAdvancedSearchinreportpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(skillHistoricalPage.verifyAdvanceSearch(reportDetails));            
	}
	
	@Test(priority=41)
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);  
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	} 

	@Test(priority=42)
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.groupby());
		screenshot.captureScreen("OCMAgentSkillhistoricalReport", "GroupBy");
		Assert.assertTrue(skillHistoricalPage.groupby());
		screenshot.captureScreen("OCMAgentSkillhistoricalReport", "AlreadyGroupBy");
	}	

	@Test(priority=43)
	public void verifyAdvancedSearchAndCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(skillHistoricalPage.advancedSearchAndCriteria(reportDetails));   	
	}

	@Test(priority=44)
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(skillHistoricalPage.advancedSearchORCriteria(reportDetails));
	}

	@Test(priority=45,description="To verify Advance Search Not Equal To")
	public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyAdvanceSearchNotEqualsTo(reportDetails)); 
	}

	@Test(priority=46,description="To verify Advance Search Contains")
	public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyAdvanceSearchContains(reportDetails)); 
	}

	@Test(priority=47,description="To verify Advance Search DoesNot Contains")
	public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyAdvanceSearchDoesNotContains(reportDetails)); 
	}

	@Test(priority=48,description="To verify Advance Search Starts With")
	public void verifyAdvancedSearchinreportpageSearchStartswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyAdvanceSearchStartsWith(reportDetails)); 
	}

	@Test(priority=49,description="To verify Advance Search Ends With")
	public void verifyAdvancedSearchinreportpageSearchEndswith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyAdvanceSearchEndsWith(reportDetails)); 
	}


	@Test(priority=50,description="To verify Select Date feature")
	public void verifySelectdateFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DrillDown").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifySelectDateFeature(reportDetails)); 
	}

	/*@Test(priority=51,description="To verify Select Date feature")
	public void verifySelectIntervalFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DrillDown").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		OCMReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifySelectIntervalFeature(reportDetails)); 
	}

	@Test(priority=52,description="To Verify Arrow move for Previous and Next page for Drill Down One")
	public void VerifyArrowMoveForPreviousAndNextPageForDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyArrowMoveForPreviousAndNextPageForDrillDownOne(reportDetails),"arrow move for previous and next page assertion failed");
	} 
	@Test(priority=53,description="To Verify Arrow move for first and last page for Drill Down One")
	public void VerifyArrowMoveForFirstAndLastPageFoDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyArrowMoveForFirstAndLastPageForDrillDownOne(reportDetails),"arrow move for first and last page assertion failed");
	} 

	@Test(priority=54,description="To Verify Total Number of Items Per Page Details for Drill Down One")
	public void VerifyTotalNumberOfItemsPerPageDetailsFoDrillDownOne() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyTotalNumberOfItemsPerPageDetailsForDrillDownOne(),"item per page assertion failed");
	}  

	@Test(priority=55,description="To Verify Arrow move for Previous and Next page for Drill Down two")
	public void VerifyArrowMoveForPreviousAndNextPageForDrillDowntwo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyArrowMoveForPreviousAndNextPageForDrillDowntwo(reportDetails),"arrow move for previous and next page assertion failed");
	} 
	@Test(priority=56,description="To Verify Arrow move for first and last page for Drill Down two")
	public void VerifyArrowMoveForFirstAndLastPageFoDrillDowntwo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyArrowMoveForFirstAndLastPageForDrillDowntwo(reportDetails),"arrow move for first and last page assertion failed");
	} 

	@Test(priority=57,description="To Verify Total Number of Items Per Page Details for Drill Down two")
	public void VerifyTotalNumberOfItemsPerPageDetailsFoDrillDowntwo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifyTotalNumberOfItemsPerPageDetailsForDrillDowntwo(),"item per page assertion failed");
	}

	@Test(priority=58,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage skillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		Assert.assertTrue(skillHistoricalPage.verifySorting(),"item per page assertion failed");
	}


	@Test(priority=59, description="To verify report data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		AgentSkillHistoricalReportPage agtSkillHistoricalPage=PageFactory.createPageInstance(driver,AgentSkillHistoricalReportPage.class);
		agtSkillHistoricalPage.sortAscSkillName();
		Assert.assertTrue(agtSkillHistoricalPage.verifyDatabase(reportDetails.getQuery(), reportDetails),"Main Report Data Mismatch");
		System.out.println("Main Report Data Match Successfull");
		List<String> skillList = new ArrayList<>();
		skillList = agtSkillHistoricalPage.getSkills();
		System.out.println(skillList);
		int k=0;
		for(int i=0;i<skillList.size();i++) {
			if(k==10){
				agtSkillHistoricalPage.goToNextPage();
				k=k-10;
			}
			agtSkillHistoricalPage.clickOnSkillIdRowOnMainReport(k);
			Assert.assertTrue(agtSkillHistoricalPage.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, skillList.get(i)),"Drill Grid One data mismatch for Skill Id " + skillList.get(i));
			System.out.println("Drill Grid One data match successfull for Skill Id " + skillList.get(i));
			k++;
			Thread.sleep(1000);
		}
		List<String> skillDates = new ArrayList<>();
		for(int i=0;i<skillList.size();i++) {
			agtSkillHistoricalPage.clickOnSkillIdRowOnMainReport(i);
			Thread.sleep(1000);
			skillDates = agtSkillHistoricalPage.getSkillDates();
			//System.out.println(skillDates);
			k=0;
			for(int j=0;j<skillDates.size();j++) {
				if(k==10){
					agtSkillHistoricalPage.goToNextPageDrillOne();
					k=k-10;
				}
				agtSkillHistoricalPage.clickOnDateRowOnDrillOneReport(k);
				Assert.assertTrue(agtSkillHistoricalPage.verifyDatabaseDrillGridTwo(reportDetails.getQueryDrillGridTwo(), reportDetails, skillDates.get(j), skillList.get(i)),"Drill Grid Two data mismatch for Skill Id " + skillList.get(i) + " and Date " + skillDates.get(j));
				System.out.println("Drill Grid Two data match successfull for Skill Id " + skillList.get(i) + " and Date " + skillDates.get(j));
				k++;
				Thread.sleep(1000);
			}
			agtSkillHistoricalPage.closeDrillOneReport();
			Thread.sleep(1000);
		}
	}*/





	@AfterMethod
	public void afterEachMethod(Method method) {
		screenshot.captureScreen("SkillHistoricalReportTest", method.getName());
		driver.navigate().refresh();
	}

}
