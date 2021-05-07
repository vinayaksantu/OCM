package com.tetherfi.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){super(driver);}

    @FindBy(id="login-page")
    private WebElement loginForm;

    @FindBy(css="span[aria-owns='loginDomainList_listbox']")
    private WebElement domainDropDown;

    @FindBy(css="ul[id='loginDomainList_listbox'] li")
    private List<WebElement> domainListbox;

    @FindBy(id="UserName")
    private WebElement username;

    @FindBy(id="Password")
    private WebElement password;
    
    @FindBy(id="Email")
    private WebElement email;

    @FindBy(css="button[onclick='onLogin()']")
    private WebElement loginBtn;

    @FindBy(id="details-button")
    private WebElement advButton;
    
    @FindBy(id="proceed-link")
    private WebElement proLink;
    
    
    public boolean isLoginPageDisplayed(){
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return loginForm.isEnabled();
    }

    public void login(String user, String pass, String emailId) throws Exception{
    	try {
    		selectWebElement(username);
    		enterValueToTxtField(username,user);
    		//selectWebElement(email);
    		//enterValueToTxtField(email,emailId);
    		selectWebElement(password);
    		enterValueToTxtField(password,pass);
    		clickOn(loginBtn);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }

    public void overrideSecurityConcern() {
		try {
			advButton.click();
			proLink.click();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
    
    public void openNewWindow(){
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		}
    
    public void launchUrl() {
		driver.get("http://Administrator:Paytm@654321@172.16.2.61:47700/IWIVRUI#");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
	}
    


}
