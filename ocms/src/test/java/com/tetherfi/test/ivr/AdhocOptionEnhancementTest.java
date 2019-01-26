package com.tetherfi.test.ivr;

import com.tetherfi.model.ivr.AdhocOptionEnhancementDetails;
import com.tetherfi.pages.AdhocOptionEnhancementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
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
import java.util.Map;

public class AdhocOptionEnhancementTest extends BaseTest {
	Screenshot screenshot=new Screenshot();
    @BeforeMethod
    public void NavigateToAdhocOptionEnhancementPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        ivrPage.navigateToAdhocOptionEnhancementPage();
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.isAdhocOptionEnhancementPageDisplayed(), "adhoc option enhancement page assertion failed");
    }
    @Test
    public void AddNewAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);

        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewAdhocOptionEnhancementRecord")
    public void AddDuplicateAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);

        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"×\nDuplicate Record", "Duplicate record assertion failed");
    }
    @Test(dependsOnMethods = "AddDuplicateAdhocOptionEnhancementRecord")
    public void AddEmptyAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);

        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"×\nPlease Provide Promotion Number, Promotion Description, Language, Direct Transfer Enabled, Intent, Status", "Empty record assertion failed");
    }
    @Test(dependsOnMethods = "AddEmptyAdhocOptionEnhancementRecord")
    public void VerifyCancelButtonAtAddAdhocOptionEnhancementRecord() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.clickonAddNewRecord();
        adhocOptionEnhancementPage.clickOnCancelBtn();
        Assert.assertFalse(adhocOptionEnhancementPage.verifyEditFormContainer(),"Cancel Btn at Add record assertion failed");
    }
    @Test(dependsOnMethods = "VerifyCancelButtonAtAddAdhocOptionEnhancementRecord")
    public void EditAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);

        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.editAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditAdhocOptionEnhancementRecord")
    public void EditEmptyAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);

        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.editAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"×\nPlease Provide Promotion Description, Intent","Edit empty record assertion failed");
    }
    @Test(dependsOnMethods = "EditEmptyAdhocOptionEnhancementRecord")
    public void EditWithoutModifyReasonAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);

        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.editAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"×\nPlease enter the modify reason","Edit without modify reason record assertion failed");
    }
    @Test(dependsOnMethods = "EditWithoutModifyReasonAdhocOptionEnhancementRecord")
    public void VerifyCancelButtonAtEditAdhocOptionEnhancementRecord() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.searchAdhocOptionEnhancementRecord("Promotion Number","16");
        adhocOptionEnhancementPage.clickOnEditButton();
        adhocOptionEnhancementPage.clickOnCancelBtn();
        Assert.assertFalse(adhocOptionEnhancementPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    @Test(dependsOnMethods = {"VerifyCancelButtonAtEditAdhocOptionEnhancementRecord"})
    public void DeleteWithoutDeleteReasonInAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);

        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.deleteAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"×\nPlease enter the delete reason","delete record assertion failed");
    }
    @Test(dependsOnMethods = {"DeleteWithoutDeleteReasonInAdhocOptionEnhancementRecord"})
    public void VerifyCancelButtonInDeleteAdhocOptionEnhancementRecord() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.searchAdhocOptionEnhancementRecord("Promotion Number","16");
        adhocOptionEnhancementPage.clickOnDeleteButton();
        adhocOptionEnhancementPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(adhocOptionEnhancementPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(dependsOnMethods = {"VerifyCancelButtonInDeleteAdhocOptionEnhancementRecord"})
    public void DeleteAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);

        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.deleteAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    @Test
    public void VerifyFullScreen(){
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyPageFullScreen());
    }
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
        screenshot.captureScreen(driver, "", method.getName());
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.clickMinimiseScrnBtn();
        adhocOptionEnhancementPage.clickOnCancelBtn();
        if(method.getName().equals("DeleteWithoutDeleteReasonInAdhocOptionEnhancementRecord"))
        {adhocOptionEnhancementPage.clickOnDeleteCancelBtn();}
        Thread.sleep(1500);
    }
}
