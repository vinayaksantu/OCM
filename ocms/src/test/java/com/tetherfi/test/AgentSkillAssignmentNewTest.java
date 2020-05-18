package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.model.tmac.TmacAuxCodesDetails;
import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import com.tetherfi.model.user.AgentSkillAssignmentDetails;
import com.tetherfi.model.user.AgentSkillAssignmentNewDetails;
import com.tetherfi.pages.AdhocOptionEnhancementPage;
import com.tetherfi.pages.AgentSkillAssignmentNewPage;
import com.tetherfi.pages.AgentSkillAssignmentPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.TmacBroadCastMsgPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AgentSkillAssignmentNewTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
	public void NavigateToAgentSkillAssignmentPage() {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "HomePage Assertion Failed");
		ocmHomePage.navigateToAgentSkillAssignmentPage();
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		Assert.assertTrue(agentSkillAssignmentNewPage.isAgentSkillAssignmentPageIsDisplayed(), "Agent Skill Assignment assertion Failed ");
	}
	
	
	@Test(priority=1)
    public void AgentSkillAssignmentPage() {
        AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
    	Assert.assertTrue(agentSkillAssignmentNewPage.verifylogo(),"Skill Configuration logo assertion failed");
    	Assert.assertTrue(agentSkillAssignmentNewPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("maximize window","AgentSkillAssignmentNewTest");
    	Assert.assertTrue(agentSkillAssignmentNewPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("minimize window","AgentSkillAssignmentNewTest");
    }
	
	@Test(priority=2)
	public void verifyEditAgentSkillButton() {
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyEditAgentListRecordButton(), "Edit Agent Skill Assertion Failed");
	}
	
	@Test(priority=3)
	public void VerifyAddNewRecordInAgentSettings() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.verifyEditAgentListRecordButton();
		agentSkillAssignmentNewPage.addNewAgentSettingsRecord(agentSettingsDetails);
		Assert.assertEquals(agentSkillAssignmentNewPage.verifySuccessMessage(), "Record Created Successfully","Create Assertion failed");
	}
	
	@Test(priority=4)
	public void VerifyArrowDropDownData() throws Exception {
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentDetails=new AgentSkillAssignmentNewDetails(map);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyArrowDropDown(agentSkillAssignmentDetails));
	}
	
	@Test(priority=5)
    public void EditAgentListRecordWithoutAssigningSkills() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.editWithoutSkillAssignToAgent(agentSkillAssignmentNewDetails);
		Assert.assertEquals(agentSkillAssignmentNewPage.verifyErrorMessage(),"×\nCannot remove all the skills, Minimum 1 skill should be assigned to agent", "Search Assigned assertion Failed");
    }
	
	@Test(priority=6)
    public void SearchAgentSkillsToEditAgentSkillForVoiceSkillsChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchVoiceSkillsToAssignAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=7)
    public void SearchAgentSkillsToEditAgentSkillForTextChatSkillsChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchTextChatSkillsToAssignAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=8)
    public void SearchAgentSkillsToEditAgentSkillForAudioChatSkillsChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchAudioChatSkillsToAssignAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=9)
    public void SearchAgentSkillsToEditAgentSkillForVideoChatSkillsChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(3);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchVideoChatSkillsToAssignAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=10)
    public void SearchAgentSkillsToEditAgentSkillForFaxSkillsChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(4);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchFaxSkillsToAssignAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=11)
    public void SearchAgentSkillsToEditAgentSkillForEmailSkillsChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(5);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchEmailSkillsToAssignAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=12)
    public void SearchAgentSkillsToEditAgentSkillForSMSSkillsChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(6);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchSMSSkillsToAssignAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
    @Test(priority=13)
    public void EditAgentListRecordResetButton() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.resetAssignedSkillsToAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyResetButton(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
        Assert.assertTrue(agentSkillAssignmentNewPage.EditAgentListCloseButton(agentSkillAssignmentNewDetails), "Edit Close Button Assertion Failed");
    }
	
	
	@Test(priority=14)
    public void EditAgentListRecordwithVoiceSkills() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignVoiceSkillToAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
	
	@Test(priority=15)
    public void EditAgentListRecordwithTextChatSkills() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignTextChatSkillToAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
	
	@Test(priority=16)
    public void EditAgentListRecordwithAudioChatSkills() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignAudioChatSkillToAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
	
	@Test(priority=17)
    public void EditAgentListRecordwithVideoChatSkills() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(3);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignVideoChatSkillToAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
	
	@Test(priority=18)
    public void EditAgentListRecordwithFaxSkills() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(4);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignFaxSkillToAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
	
	@Test(priority=19)
    public void EditAgentListRecordwithEmailSkills() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(5);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignEmailSkillToAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
	
	@Test(priority=20)
    public void EditAgentListRecordwithSMSSkills() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(6);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignSMSSkillToAgent(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    
    @Test(priority=21)
    public void EditAgentListRecordwithVoiceSkillsByDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(8);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignVoiceSkillToAgentByDragandDrop(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    
    @Test(priority=22)
    public void EditAgentListRecordwithTextChatSkillsByDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(9);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignTextChatSkillToAgentByDragandDrop(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    
    @Test(priority=23)
    public void EditAgentListRecordwithAudioChatSkillsByDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(10);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignAudioChatSkillToAgentByDragandDrop(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    
    @Test(priority=24)
    public void EditAgentListRecordwithVideoChatSkillsByDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(11);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignVideoChatSkillToAgentByDragandDrop(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    
    @Test(priority=25)
    public void EditAgentListRecordwithFaxSkillsByDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(12);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignFaxSkillToAgentByDragandDrop(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    
    @Test(priority=26)
    public void EditAgentListRecordwithEmailSkillsByDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(13);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignEmailSkillToAgentByDragandDrop(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    
    @Test(priority=27)
    public void EditAgentListRecordwithSMSSkillsByDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(14);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.assignSMSSkillToAgentByDragandDrop(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    
	 /*@Test(priority=28)
	 public void verifyAuditTrailReportForUpdate() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
    	 Map<String, String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
    	 AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
    	 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
         homePage.navigateToOCMReportsPage();
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
         String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
         Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map2);
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(ocmReportsPage.verifyAgentSkillAssignUpdate(agentSkillAssignmentNewDetails, "Update"), "Update Audit Trail Assertion Failed"); 
     }*/
    
	@Test(priority=29)
	public void verifyMultiSkillSaveButtonWithOutAssigningSkills() throws Exception {
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillSaveButton();
		Assert.assertEquals(agentSkillAssignmentNewPage.verifyErrorMessage(),"×\nAt least one agent is required", "Search Assigned assertion Failed");
	}
	
	@Test(priority=30)
    public void SearchVoiceSkillsToEditForMultiAgentSkillTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchVoiceSkillsToAssignForAgentInMultiSkillTab(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedMultiTabSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=31)
    public void SearchTextChatSkillsToEditForMultiAgentSkillTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchTextChatSkillsToAssignForAgentInMultiSkillTab(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedMultiTabSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=32)
    public void SearchAudioChatSkillsToEditForMultiAgentSkillTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchAudioChatSkillsToAssignForAgentInMultiSkillTab(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedMultiTabSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=33)
    public void SearchVideoChatSkillsToEditForMultiAgentSkillTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(3);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchVideoChatSkillsToAssignForAgentInMultiSkillTab(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedMultiTabSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=34)
    public void SearchFaxSkillsToEditForMultiAgentSkillTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(4);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchFaxSkillsToAssignForAgentInMultiSkillTab(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedMultiTabSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=35)
    public void SearchEmailSkillsToEditForMultiAgentSkillTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(5);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchEmailSkillsToAssignForAgentInMultiSkillTab(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedMultiTabSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=36)
    public void SearchSMSSkillsToEditForMultiAgentSkillTab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(6);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.SearchSMSSkillsToAssignForAgentInMultiSkillTab(agentSkillAssignmentNewDetails);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchedMultiTabSkillData(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=37)
	public void verifyMultiSkillClearButton() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillClear(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyMultiSkillClearButton(agentSkillAssignmentNewDetails),"Assign skill assertion failed");
	}
	
	@Test(priority=38)
	public void verifyAssignMultiSkillFromVoiceChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignVoiceChannel(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=39)
	public void verifyAssignMultiSkillFromTextChatChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(1);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignTextChatChannel(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=40)
	public void verifyAssignMultiSkillFromAudioChatChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(2);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignAudioChatChannel(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=41)
	public void verifyAssignMultiSkillFromVideoChatChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(3);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignVideoChatChannel(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=42)
	public void verifyAssignMultiSkillFromFaxChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(4);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignFaxChannel(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=43)
	public void verifyAssignMultiSkillFromEmailChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(5);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignEmailChannel(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=44)
	public void verifyAssignMultiSkillFromSMSChannel() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(6);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignSMSChannel(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=45)
	public void verifyAssignMultiSkillFromVoiceChannelWithDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(8);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignVoiceChannelForDragAndDrop(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=46)
	public void verifyAssignMultiSkillFromTextChatChannelWithDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(9);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignTextChatChannelForDragAndDrop(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=47)
	public void verifyAssignMultiSkillFromAudioChatChannelWithDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(10);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignAudioChatChannelForDragAndDrop(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=48)
	public void verifyAssignMultiSkillFromVideoChatChannelWithDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(11);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignVideoChatChannelForDragAndDrop(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=49)
	public void verifyAssignMultiSkillFromFaxChannelWithDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(12);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignFaxChannelForDragAndDrop(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=50)
	public void verifyAssignMultiSkillFromEmailChannelWithDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(13);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignEmailChannelForDragAndDrop(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=51)
	public void verifyAssignMultiSkillFromSMSChannelWithDragAndDrop() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(14);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyMultiSkillAssignSMSChannelForDragAndDrop(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	@Test(priority=52)
	public void verifyTransferAllToButtonToAssignSkills() throws Exception  {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(7);
		AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.navigateToMultiSkillTab();
		agentSkillAssignmentNewPage.verifyTransferAllToButtonAssigMultiSkills(agentSkillAssignmentNewDetails);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyRecordUpdated(),"Assign skill assertion failed");
	}
	
	 /*@Test(priority=53)
	 public void verifyAuditTrailReportForMultiSkillUpdate() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
    	 Map<String, String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
    	 AgentSkillAssignmentNewDetails agentSkillAssignmentNewDetails=new AgentSkillAssignmentNewDetails(map);
    	 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
         homePage.navigateToOCMReportsPage();
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
         String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
         Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map2);
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(ocmReportsPage.verifyMultiAgentSkillAssignUpdate(agentSkillAssignmentNewDetails, "Update"), "Update Audit Trail Assertion Failed"); 
     }*/
	
	@Test(priority=54)
    public void ExportToExcel() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyExportToExcel(filePath));
    }
    
    /*@Test(priority=55)
    public void ExportToExcelData() throws Exception {	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Skill Assignment.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestDataForAgentSettings();
    	AgentSkillAssignmentNewPage agentSkillAssignmentNewPage = PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
    	Assert.assertTrue(agentSkillAssignmentNewPage.verifyexportToExcelSheet(maplist));	
    }*/
    
    @Test(priority=56)
    public void ExporttoExcelWithoutData() throws Exception {
    	AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(1);
        AgentSkillAssignmentNewDetails agentSkillAssignmentDetails= new AgentSkillAssignmentNewDetails(map);
        Assert.assertTrue(agentSkillAssignmentNewPage.ExporttoExcelWithoutData(agentSkillAssignmentDetails));
    }
  
    //@Test(priority=57)
    public void SortingByAscending() throws IOException {
    	AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
    	agentSkillAssignmentNewPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Skill Assignment (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyexportToExcelSheet(maplist));
    }
    
    //@Test(priority=58)
    public void SortingByDescending() throws IOException {
    	AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
    	agentSkillAssignmentNewPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Skill Assignment (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyexportToExcelSheet(maplist));
    }
    
	@Test(priority=59)
	public void SearchPage() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		Assert.assertFalse(agentSkillAssignmentNewPage.clearAll(agentSkillAssignmentDetails), "Search Page assertion Failed");
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyclose(), "Search Page assertion Failed");
	}
	
	@Test(priority=60)
	public void SearchClearSearch() throws Exception {
		String filePath=System.getProperty("user.dir")+ "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(4);
		AgentSkillAssignmentNewDetails agentSkillAssignmentDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		Assert.assertTrue(agentSkillAssignmentNewPage.verifyinvalidsearchwithwrongdata(agentSkillAssignmentDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("AgentSkillAssignmentNewTest","Invalid Search with wrong data");
        Assert.assertTrue(agentSkillAssignmentNewPage.verifyclearsearch(), "Clear All Assertion Failed");
	}
	
	@Test(priority=61)
	public void SearchWithOutSearchText() {
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.searchwithoutextsearch();
		Assert.assertFalse(agentSkillAssignmentNewPage.getErrorMsg(), "Search Without Textassertion Failed");
	}
	
	@Test(priority=62)
	public void SearchAssignedAgentSkillButtonWithoutSkill(){
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.verifySkillSearchWithoutSkill();
		Assert.assertEquals(agentSkillAssignmentNewPage.verifyErrorMessage(),"×\nPlease select skill to search", "Search Assigned assertion Failed");
		agentSkillAssignmentNewPage.verifySkillSearchClearWithoutSkill();
		Assert.assertEquals(agentSkillAssignmentNewPage.verifyErrorMessage(),"×\nNothing to clear!", "Search Assigned assertion Failed");
	}
	
	@Test(priority=63)
	public void SearchAssignedSkillsRecords() throws Throwable {
		String filePath=System.getProperty("user.dir")+ "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		Assert.assertTrue(agentSkillAssignmentNewPage.SearchAssignedSkillRecord(agentSkillAssignmentDetails), "Search assertion Failed");
	}
	
	@Test(priority=64)
	public void SearchClearforAssignedSkillRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+ "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		AgentSkillAssignmentNewDetails agentSkillAssignmentDetails=new AgentSkillAssignmentNewDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		Assert.assertFalse(agentSkillAssignmentNewPage.ClearSearchAssignedSkillRecords(agentSkillAssignmentDetails), "Search Page assertion Failed");
	}
	
	@Test(priority=65)
	public void GroupBy(){
	    AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.groupby());
	    screenshot.captureScreen(driver, "GroupBy","AgentSkillAssignmentNewTest");
	    Assert.assertTrue(agentSkillAssignmentNewPage.groupby());
	    screenshot.captureScreen(driver, "AlreadyGroupBy","AgentSkillAssignmentNewTest");
	}
	    
	@Test(priority=66)
	public void VerifyArrowMoveForPreviousAndNextPage() {
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}
	    
	@Test(priority=67)
    public void VerifyArrowMoveForFirstAndLastPage() {
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}
	    
	 @Test(priority=68)
	 public void VerifyTotalNumberOfItemsPerPageDetails() {
		 AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}
	    
	@Test(priority=69)
	public void VerifyNumberOfItemsPerPageSelection() {
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	    
	@Test(priority=70)
	public void VerifyDropdownForAllTheColumns() {
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}
	    
	@Test(priority=71)
	public void VerifyColumnsHeaderEnable() {
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}
	    
	@Test(priority=72)
	public void VerifyColumnsHeaderDisable() {
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentNewPage.class);
	    Assert.assertFalse(agentSkillAssignmentNewPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}
	
	@Test(priority=73)
	public void VerifyDeleteNewRecordInAgentSettings() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
		agentSkillAssignmentNewPage.verifyEditAgentListRecordButton();
		agentSkillAssignmentNewPage.DeleteAgentSettingsRecord(agentSettingsDetails);
		Assert.assertEquals(agentSkillAssignmentNewPage.verifySuccessMessage(), "Record Deleted Successfully","Delete Assertion failed");
	}
	
	@Test(priority=74)
	public void VerifySearchIsNotEqualTo() throws Exception{
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
	    AgentSkillAssignmentNewDetails agentSkillAssignmentDetails=new AgentSkillAssignmentNewDetails(map);
	    AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchIsnotEqualTo(agentSkillAssignmentDetails.getUsername()), "Search assertion Failed");    
	 }
	
	@Test(priority=75)
	public void SearchContains() throws Exception{
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
	    AgentSkillAssignmentNewDetails agentSkillAssignmentDetails=new AgentSkillAssignmentNewDetails(map);
	    AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchContains(agentSkillAssignmentDetails.getUsername()), "Search assertion Failed");    
	 }
	
	@Test(priority=76)
	public void SearchDoesNotContains() throws Exception{
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
	    AgentSkillAssignmentNewDetails agentSkillAssignmentDetails=new AgentSkillAssignmentNewDetails(map);
	    AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchDoesNotContains(agentSkillAssignmentDetails.getUsername()), "Search assertion Failed");    
	 }
	
	@Test(priority=77)
	public void SearchStartsWith() throws Exception{
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
	    AgentSkillAssignmentNewDetails agentSkillAssignmentDetails=new AgentSkillAssignmentNewDetails(map);
	    AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchStartsWith(agentSkillAssignmentDetails.getUsername()), "Search assertion Failed");    
	 }
	
	@Test(priority=78)
	public void SearchEndsWith() throws Exception{
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSkillAssignmentNewData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
	    AgentSkillAssignmentNewDetails agentSkillAssignmentDetails=new AgentSkillAssignmentNewDetails(map);
	    AgentSkillAssignmentNewPage agentSkillAssignmentNewPage=PageFactory.createPageInstance(driver, AgentSkillAssignmentNewPage.class);
	    Assert.assertTrue(agentSkillAssignmentNewPage.verifySearchEndsWith(agentSkillAssignmentDetails.getUsername()), "Search assertion Failed");    
	 }
	
	@AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("AgentSkillAssignmentNewTest",method.getName());
        driver.navigate().refresh();
    }

}
