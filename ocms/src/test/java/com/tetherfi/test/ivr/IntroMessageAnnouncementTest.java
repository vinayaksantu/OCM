package com.tetherfi.test.ivr;

import com.tetherfi.model.ivr.IntroMessageAnnouncementDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IntroMessageAnnouncementPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class IntroMessageAnnouncementTest{
    protected WebDriver driver;
    Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToIntroMessageAnnouncementPage(Method method) throws IOException {
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        System.out.println("Started Executing : "+method.getName());
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        Test t = method.getAnnotation(Test.class);
        Map<String, String> map;
        if(t.groups()[0].equalsIgnoreCase("Checker"))
        map= new ExcelReader(filePath,"Login").getTestData().get(1);
        else
        map= new ExcelReader(filePath,"Login").getTestData().get(0);
        driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        ivrPage.navigateToIntroMessageAnnouncementPage();
        IntroMessageAnnouncementPage introMessageAnnouncementPage= PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
        Assert.assertTrue(introMessageAnnouncementPage.isIntroMessageAnnouncementPageDisplayed(), "Operating hours page assertion failed");
    }
    
    @Test(groups = { "Maker" })
    public void AddNewIntroMessageAnnouncementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);

        IntroMessageAnnouncementPage introMessageAnnouncementPage= PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
        introMessageAnnouncementPage.addNewIntroMessageAnnouncementRecord(introMessageAnnouncementDetails);
        Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(),"Record created successfully","Add New record assertion failed");
        introMessageAnnouncementPage.clickOnGoBack();
        Assert.assertTrue(introMessageAnnouncementPage.verifyAuditTrail(introMessageAnnouncementDetails,"MakerCreate","New"),"Audit trail details failed");
        introMessageAnnouncementPage.taskCompleteAction("Task Complete for Create");
        Assert.assertTrue(introMessageAnnouncementPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(introMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    @Test(groups = { "Checker" },dependsOnMethods="AddNewIntroMessageAnnouncementRecord")
    public void ApproveforAddNewIntroMessageAnnouncementRecord() throws Exception {
        IntroMessageAnnouncementPage introMessageAnnouncementPage= PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
        introMessageAnnouncementPage.clickonApprove("Approve Create");
        Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(introMessageAnnouncementPage.verifyReviewAuditTrail("Approved","Approve Create"));
    }
    @Test(groups = { "Maker" },dependsOnMethods = "ApproveforAddNewIntroMessageAnnouncementRecord")
    public void EditIntroMessageAnnouncementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);

        IntroMessageAnnouncementPage introMessageAnnouncementPage= PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
        introMessageAnnouncementPage.editIntroMessageAnnouncementRecord(introMessageAnnouncementDetails);
        Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(),"Record updated successfully","Edit record assertion failed");
        introMessageAnnouncementPage.clickOnGoBack();
        Assert.assertTrue(introMessageAnnouncementPage.verifyAuditTrail(introMessageAnnouncementDetails,"MakerUpdate","New"),"Audit trail details failed");
        introMessageAnnouncementPage.taskCompleteAction("Task Complete for Edit");
        Assert.assertTrue(introMessageAnnouncementPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(introMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },dependsOnMethods="EditIntroMessageAnnouncementRecord")
    public void ApproveforEditIntroMessageAnnouncementRecord() throws Exception {
        IntroMessageAnnouncementPage introMessageAnnouncementPage= PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
        introMessageAnnouncementPage.clickonApprove("Approve Edit");
        Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(introMessageAnnouncementPage.verifyReviewAuditTrail("Approved","Approve Edit"));
    }
    
    @Test(groups = { "Maker" },dependsOnMethods = "ApproveforEditIntroMessageAnnouncementRecord")
    public void DeleteIntroMessageAnnouncementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);

        IntroMessageAnnouncementPage introMessageAnnouncementPage= PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
        introMessageAnnouncementPage.deleteIntroMessageAnnouncementRecord(introMessageAnnouncementDetails);
        Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(),"Record deleted successfully","Edit record assertion failed");
        introMessageAnnouncementPage.clickOnGoBack();
        Assert.assertTrue(introMessageAnnouncementPage.verifyAuditTrail(introMessageAnnouncementDetails,"MakerDelete","New"),"Audit trail details failed");
        introMessageAnnouncementPage.taskCompleteAction("Task Complete for Delete");
        Assert.assertTrue(introMessageAnnouncementPage.verifyTaskCompleteSuccessMessage(),"Task Complete record assertion failed");
        Assert.assertTrue(introMessageAnnouncementPage.verifyStatus("Approval Pending"),"approal status details failed");
    }
    
    @Test(groups = { "Checker" },dependsOnMethods="DeleteIntroMessageAnnouncementRecord")
    public void ApproveforDeleteIntroMessageAnnouncementRecord() throws Exception {
        IntroMessageAnnouncementPage introMessageAnnouncementPage= PageFactory.createPageInstance(driver,IntroMessageAnnouncementPage.class);
        introMessageAnnouncementPage.clickonApprove("Approve Delete");
        Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(),"All the data has been approved successfully!","Approve record assertion failed");
        Assert.assertTrue(introMessageAnnouncementPage.verifyReviewAuditTrail("Approved","Approve Delete"));
    }
    
    @AfterMethod
    public void afterEachMethod(Method method){
    	screenshot.captureScreen("IntroMessageAnnouncementTest", method.getName());
        driver.navigate().refresh();
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.userLogout();
        driver.close();
        System.out.println("Completed Executing : "+method.getName());
    }
}
