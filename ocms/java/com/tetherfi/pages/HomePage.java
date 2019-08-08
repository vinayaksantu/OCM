package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;

import java.util.List;
import java.util.Map;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){super(driver);}

    @FindBy(css=".navbar-header span")
    private WebElement ocmIconImg;

    @FindBy(id="profiledropdown")
    private WebElement profileDropDown;

    @FindBy(css=".btn-primary")
    private List<WebElement> profileDropdownbtns;

    @FindBy(css=".middle-box H2")
    private WebElement welcomeMsg;

    /*@FindBy(id="OCMli")
    private WebElement ocmTab;*/
    
    @FindBy(xpath="//a[@class='OCM-sidebar-toggle white-color']")
    private WebElement ocmTab;

    @FindBy(id="OCMReportsli")
    private WebElement ocmreportsTab;

    @FindBy(css="button[onclick='continueDashboard()']")
    private List <WebElement> continueToDashboardBtn;

    public void userLogout(){
        waitUntilWebElementIsClickable(profileDropDown);
        selectWebElement(profileDropDown);
        waitUntilWebElementIsClickable(profileDropdownbtns.get(1));
        selectWebElement(profileDropdownbtns.get(1));
    }

    public boolean verifyHomePageTitle(){
        if(getPageTitle().equalsIgnoreCase("OCM | Dashboard")){return true;}else{return false;}
    }

    public boolean verifyWelcomeMsg(){
        waitUntilWebElementIsVisible(welcomeMsg);
        if(getTextFromWebElement(welcomeMsg).contains("Welcome to Tetherfi OCM")){return true;}else{return false;}
    }

    public void navigateToOCMPage(){
        selectWebElement(ocmTab);
		waitForLoad(driver);
    }

    public void navigateToOCMReportsPage(){
        selectWebElement(ocmreportsTab);
    }

    public void navigateToOcmIconImg(){selectWebElement(ocmIconImg);}

    public void navigateToDashBoard(){selectWebElement(continueToDashboardBtn.get(0));}
    
    public void openOCM()  {
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
            String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
            Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(3);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
                Thread.sleep(5000);
            }
      
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }}

	public boolean noDashboardAccess() {
		if(continueToDashboardBtn.size()>0)
			return false;
		else
			return true;
	}

}
