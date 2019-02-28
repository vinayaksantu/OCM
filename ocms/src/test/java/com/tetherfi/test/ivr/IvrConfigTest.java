package com.tetherfi.test.ivr;

import com.tetherfi.model.ivr.IvrConfigDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrConfigPage;
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

public class IvrConfigTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToIvrConfigPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        ivrPage.navigateToIvrConfigPage();
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.isIvrConfigPageDisplayed(), "Ivr config page assertion failed");
    }
    @Test
    public void AddNewIvrConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);

        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.addNewIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewIvrConfigRecord")
    public void AddDuplicateIvrConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);

        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.addNewIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nDuplicate Parameter", "Duplicate record assertion failed");
    }
    @Test(dependsOnMethods = "AddDuplicateIvrConfigRecord")
    public void AddEmptyConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);

        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.addNewIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Parameter, Value", "Add invalid record assertion failed");
    }
    @Test(dependsOnMethods = "AddEmptyConfigRecord")
    public void AddInvalidParameterConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(1);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);

        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.addNewIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Parameter", "Add invalid record assertion failed");
    }
    @Test(dependsOnMethods = "AddInvalidParameterConfigRecord")
    public void AddInvalidValueConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(2);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);

        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.addNewIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Value", "Add invalid record assertion failed");
    }
    @Test(dependsOnMethods = "AddInvalidValueConfigRecord")
    public void VerifyCancelBtnAtAddConfigRecord(){
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.clickOnAddRecord();
        ivrConfigPage.clickOnCancelBtn();
        Assert.assertFalse(ivrConfigPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
    }
    @Test(dependsOnMethods = "AddInvalidValueConfigRecord")
    public void EditIvrConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);

        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        ivrConfigPage.editIvrConfigRecord(IvrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditIvrConfigRecord")
    public void EditEmptyConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(0);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);

        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.editIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Parameter, Value", "edit empty record assertion failed");
    }
    @Test(dependsOnMethods = "EditEmptyConfigRecord")
    public void EditInvalidParameterConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(1);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);

        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.editIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Parameter", "Add invalid record assertion failed");
    }
    @Test(dependsOnMethods = "EditInvalidParameterConfigRecord")
    public void EditInvalidValueConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(2);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);

        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.editIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease Provide Value", "Add invalid record assertion failed");
    }
    @Test(dependsOnMethods = "EditInvalidValueConfigRecord")
    public void EditWithoutModifyReasonConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Invalid").getTestData().get(3);
        IvrConfigDetails ivrConfigDetails = new IvrConfigDetails(map);

        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.editIvrConfigRecord(ivrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease enter the modify reason", "empty modify reason record assertion failed");
    }
    @Test(dependsOnMethods = "EditWithoutModifyReasonConfigRecord")
    public void VerifyCancelBtnAtEditConfigRecord(){
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.searchIvrConfigRecord("Parameter","Mobile");
        ivrConfigPage.clickOnEditButton();
        ivrConfigPage.clickOnCancelBtn();
        Assert.assertFalse(ivrConfigPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    @Test(dependsOnMethods = "VerifyCancelBtnAtEditConfigRecord")
    public void DeleteWithoutDeleteReasonIvrConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        ivrConfigPage.deleteIvrConfigRecord(IvrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"×\nPlease enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(dependsOnMethods = {"DeleteWithoutDeleteReasonIvrConfigRecord"})
    public void VerifyCancelBtnAtDeleteConfigRecord(){
        IvrConfigPage ivrConfigPage = PageFactory.createPageInstance(driver, IvrConfigPage.class);
        ivrConfigPage.searchIvrConfigRecord("Parameter","Mobile");
        ivrConfigPage.clickOnDeleteButton();
        ivrConfigPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(ivrConfigPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(dependsOnMethods = "VerifyCancelBtnAtDeleteConfigRecord")
    public void DeleteIvrConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IvrConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        IvrConfigDetails IvrConfigDetails=new IvrConfigDetails(map);
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        ivrConfigPage.deleteIvrConfigRecord(IvrConfigDetails);
        Assert.assertEquals(ivrConfigPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    @Test
    public void VerifyFullScreen(){
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        Assert.assertTrue(ivrConfigPage.verifyPageFullScreen());
    }
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	screenshot.captureScreen(driver, "", method.getName());
        IvrConfigPage ivrConfigPage= PageFactory.createPageInstance(driver,IvrConfigPage.class);
        ivrConfigPage.clickMinimiseScrnBtn();
        ivrConfigPage.clickOnCancelBtn();
        if(method.getName().equals("DeleteWithoutDeleteReasonIvrConfigRecord"))
        {ivrConfigPage.clickOnDeleteCancelBtn();}
        Thread.sleep(1500);
    }

}
