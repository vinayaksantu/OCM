package com.tetherfi.test.chat;

import com.tetherfi.model.chat.ChatIntentSkillMappingDetails;
import com.tetherfi.model.ivr.IntentMappingDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class ChatIntentSkillMappingTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToChatIntentSkillMappingPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("Chat");
        ChatPage chatPage = PageFactory.createPageInstance(driver,ChatPage.class);
        Assert.assertTrue(chatPage.isChatPageDisplayed(),"chat page assertion failed");
        chatPage.navigateToChatIntentSkillMappingPage();
        ChatIntentSkillMappingPage intentSkillMappingPage = PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
        Assert.assertTrue(intentSkillMappingPage.isChatIntentSkillMappingPageDisplayed(),"Intent skill mapping page assertion failed");
    }
    
    /*@Test(priority=1)
	public void ChatIntentSkillMappingPage() {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        Assert.assertTrue(ChatIntentSkillMappingPage.VerifyLogo(),"Logo assertion failed");
        Assert.assertTrue(ChatIntentSkillMappingPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("ChatIntentSkillMappingTest","Maximize");
    	Assert.assertTrue(ChatIntentSkillMappingPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("ChatIntentSkillMappingTest","Minimize");	
    }
	
	@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        Assert.assertTrue(ChatIntentSkillMappingPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        Assert.assertTrue(ChatIntentSkillMappingPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        Assert.assertFalse(ChatIntentSkillMappingPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void AddNewChatIntentSkillMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatIntentSkillMappingDetails intentSkillMappingDetails=new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage intentSkillMappingPage = PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
        intentSkillMappingPage.addNewChatIntentSkillMappingRecord(intentSkillMappingDetails);
        Assert.assertEquals(intentSkillMappingPage.getMessage(),"Record Created Successfully","Add New record assertion failed");
    }
    
   @Test(priority=6)//,dependsOnMethods = "AddNewChatIntentSkillMappingRecord")
    public void AddDuplicateChatIntentSkillMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.addNewChatIntentSkillMappingRecord(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Record Creation Failed, Already Exist");
    }
    
    @Test(priority=7)//,dependsOnMethods ="AddNewChatIntentSkillMappingRecord")
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatIntentSkillMappingCreate(ChatIntentSkillMappingDetails,"Create"));
    }
    
    @Test(priority=8)
    public void AddEmptyRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.addNewEmptyRecord(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Please Provide Skill, Intent, Language, Channel", "Add invalid record assertion failed");
    }
    
    @Test(priority=9)
    public void AddRecordWithoutSkill() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.addRecordWithoutSkill(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Please Provide Skill", "Add invalid record assertion failed");
    }
    
    @Test(priority=10)
    public void AddRecordWithoutIntent() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.addRecordWithoutIntent(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Please Provide Intent", "Add invalid record assertion failed");
    }
    
    @Test(priority=11)
    public void AddRecordWithoutLanguage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.addRecordWithoutLanguage(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Please Provide Language", "Add invalid record assertion failed");
    }
    
    @Test(priority=12)
    public void AddRecordWithoutChannel() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.addRecordWithoutChannel(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Please Provide Channel", "Add invalid record assertion failed");
    }
    
    @Test(priority=13)
    public void VerifyCancelBtnAtAddRecord(){
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.clickOnAddRecord();
        ChatIntentSkillMappingPage.clickOnCancelBtn();
        Assert.assertFalse(ChatIntentSkillMappingPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
    
    @Test(priority=14)//,dependsOnMethods = "EditChatIntentSkillMappingRecord")
    public void EditWithoutModifyReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.editChatIntentSkillMappingWithoutModifyReason(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }*/
    
   @Test(priority=15)
    public void VerifyCancelBtnAtEditRecord() throws Exception{
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.searchChatIntentSkillMappingRecord("L1");
        Thread.sleep(1000);
        ChatIntentSkillMappingPage.clickOnEditButton();
        ChatIntentSkillMappingPage.clickOnCancelBtn();
        Assert.assertFalse(ChatIntentSkillMappingPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    /*@Test(priority=16)//,dependsOnMethods = "AddNewChatIntentSkillMappingRecord")
    public void EditChatIntentSkillMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ChatIntentSkillMappingDetails intentSkillMappingDetails=new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage intentSkillMappingPage = PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
        intentSkillMappingPage.editIntentSkillMappingRecord(intentSkillMappingDetails);
        Assert.assertEquals(intentSkillMappingPage.getMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    
    @Test(priority=17)//,dependsOnMethods="EditChatIntentSkillMappingRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatIntentSkillMappingUpdate(ChatIntentSkillMappingDetails,"Update"));
    }
    
   @Test(priority=18)
    public void searchPage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        Assert.assertFalse(ChatIntentSkillMappingPage .clearAll(ChatIntentSkillMappingDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("ChatIntentSkillMappingTest", "clearall");
        Assert.assertTrue(ChatIntentSkillMappingPage.verifyclose());
   }
   @Test(priority=19)
   public void VerifySearchIsNotEqualTo() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
   	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
       ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
       Assert.assertTrue(ChatIntentSkillMappingPage.verifySearchIsNotEqualTo(ChatIntentSkillMappingDetails.getIntent()));
   }
   
   @Test(priority=20)
   public void VerifySearchContains() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
   	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
       ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
       ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
       Assert.assertTrue(ChatIntentSkillMappingPage.verifySearchContains(ChatIntentSkillMappingDetails.getIntent()));
   }
   
   @Test(priority=21)
   public void VerifySearchDoesNotContains() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
   	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
       ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
       ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
       Assert.assertTrue(ChatIntentSkillMappingPage.verifySearchDoesNotContains(ChatIntentSkillMappingDetails.getIntent()));
   }
   
   @Test(priority=22)
   public void VerifySearchStartsWith() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
   	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
       ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
       ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
       Assert.assertTrue(ChatIntentSkillMappingPage.verifySearchStartsWith(ChatIntentSkillMappingDetails.getIntent()));
   }
   
   @Test(priority=23)
   public void VerifySearchEndsWith() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
   	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
       ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
       ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
       Assert.assertTrue(ChatIntentSkillMappingPage.verifySearchEndsWith(ChatIntentSkillMappingDetails.getIntent()));
   }
    
   @Test(priority=24)
    public void searchwithoutSearchTextbox() throws IOException {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.searchwithoutextsearch();
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=25)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        Assert.assertTrue(ChatIntentSkillMappingPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=26)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Intent Skill Mapping.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
    	Assert.assertTrue(ChatIntentSkillMappingPage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=27)
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.deleteIntentMapingWithoutDeleteReasonRecord(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=28)
    public void VerifyCancelBtnAtDeleteChatIntentSkillMappingRecord() throws Exception{
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.searchChatIntentSkillMappingRecord("L4");
        Thread.sleep(1000);
        ChatIntentSkillMappingPage.clickOnDeleteButton();
        ChatIntentSkillMappingPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(ChatIntentSkillMappingPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    
    @Test(priority=29)//,dependsOnMethods = "EditIntentSkillMappingRecord")
    public void DeleteIntentSkillMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatIntentSkillMappingDetails intentSkillMappingDetails=new ChatIntentSkillMappingDetails(map);

        ChatIntentSkillMappingPage intentSkillMappingPage = PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
        intentSkillMappingPage.deleteIntentSkillMappingRecord(intentSkillMappingDetails.getSegment(),intentSkillMappingDetails.getDeleteReason());
        Assert.assertEquals(intentSkillMappingPage.getMessage(),"Record Deleted Successfully","Delete record assertion failed");
    }
    
    @Test(priority=30)//,dependsOnMethods= {"DeleteIntentSkillMappingRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        ChatIntentSkillMappingDetails intentSkillMappingDetails=new ChatIntentSkillMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatIntentSkillMappingdelete(intentSkillMappingDetails,"Delete"));
    }
    
    @Test(priority=31)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
    	Assert.assertTrue(ChatIntentSkillMappingPage.verifyinvalidsearchwithwrongdata(ChatIntentSkillMappingDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("ChatIntentSkillMappingTest","Invalid Search with wrong data");
        Assert.assertTrue(ChatIntentSkillMappingPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=32)
    public void ExporttoExcelWithoutData() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        Assert.assertTrue(ChatIntentSkillMappingPage.ExporttoExcelWithoutData(ChatIntentSkillMappingDetails));
    }
  
   @Test(priority=33)
    public void SortingByAscending() throws IOException {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Intent Skill Mapping (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(ChatIntentSkillMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=34)
    public void SortingByDescending() throws IOException {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Intent Skill Mapping (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(ChatIntentSkillMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=35)
    public void GroupBy()
    {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
    	Assert.assertTrue(ChatIntentSkillMappingPage.groupby());
        screenshot.captureScreen("ChatIntentSkillMappingTest", "GroupBy");
    	Assert.assertTrue(ChatIntentSkillMappingPage.groupby());
        screenshot.captureScreen("ChatIntentSkillMappingTest", "AlreadyGroupBy");
    }
    
    @Test(priority=36)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
    	Assert.assertTrue(ChatIntentSkillMappingPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=37)
    public void VerifyArrowMoveForFirstAndLastPage() {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        Assert.assertTrue(ChatIntentSkillMappingPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=38)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        Assert.assertTrue(ChatIntentSkillMappingPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
   @Test(priority=39)
    public void VerifyNumberOfItemsPerPageSelection() {
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        Assert.assertTrue(ChatIntentSkillMappingPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=40)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
    	Assert.assertTrue(ChatIntentSkillMappingPage.verifyDatabase(ChatIntentSkillMappingDetails.getQuery()));
    }
    
    /*@Test(priority=41)
    public void AddRecordWithoutSegment() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.addRecordWithoutSegment(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Record Created Successfully", "Add invalid record assertion failed");
    }
    @Test(priority=42)
    public void AddRecordWithoutSubSegment() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.addRecordWithoutSubSegment(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Record Created Successfully", "Add invalid record assertion failed");
    }
    
    @Test(priority=43)
    public void AddRecordWithoutCustEntType() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatIntentSkillMappingDetails ChatIntentSkillMappingDetails = new ChatIntentSkillMappingDetails(map);
        ChatIntentSkillMappingPage ChatIntentSkillMappingPage = PageFactory.createPageInstance(driver, ChatIntentSkillMappingPage.class);
        ChatIntentSkillMappingPage.addRecordWithoutCustomEntType(ChatIntentSkillMappingDetails);
        Assert.assertEquals(ChatIntentSkillMappingPage.getMessage(),"Record Created Successfully", "Add invalid record assertion failed");
    }*/
    
	 @AfterMethod
	    public void afterEachMethod(Method method) throws InterruptedException {
		        Screenshot screenshot=new Screenshot(driver);
		        screenshot.captureScreen("ChatIntentSkillMappingTest",method.getName());
		        driver.navigate().refresh();
		}
}
