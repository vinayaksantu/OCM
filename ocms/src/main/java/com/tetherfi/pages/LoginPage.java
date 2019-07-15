package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(css="button[onclick='onLogin()']")
    private WebElement loginBtn;

    public boolean isLoginPageDisplayed(){
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return loginForm.isEnabled();
    }

    public void login(String user, String pass) throws Exception{
        try {
            selectWebElement(username);
            enterValueToTxtField(username,user);
            selectWebElement(password);
            enterValueToTxtField(password,pass);
            clickOn(loginBtn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
