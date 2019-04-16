package com.tetherfi.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class ChatAPI {
    private WebDriver driver;

    @FindBy(css=".col-md-12 #btnStart")
    private WebElement startBtn;

    @FindBy(css=".col-md-12 #btnEnd")
    private WebElement endBtn;

    @FindBy(css=".col-md-12 #msg")
    private WebElement msgText;

    @FindBy(css=".col-md-2 #btnSend")
    private WebElement sendBtn;

    public ChatAPI(WebDriver driver){this.driver=driver;PageFactory.initElements(driver,this);}

    public void open(String url){
        //((JavascriptExecutor)driver).executeScript("window.open()");
        switchToWindow(0);
        driver.get(url);
        waitForLoad(driver);
    }
    public void initiateChat(){
        selectWebElement(startBtn);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void sendChatMessage(String msg){
        enterValueToTxtField(msgText,msg);
        selectWebElement(sendBtn);
    }
    public void end(){
        selectWebElement(endBtn);
    }
    public void switchToNewWindow(){
        String oldTab=driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
    }
    public void switchBackToParentWindow(){
        String newTab=driver.getWindowHandle();
        ArrayList<String> oldTab = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        oldTab.remove(newTab);
        driver.switchTo().window(oldTab.get(0));
    }
    public void switchToWindow(int i){
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(Tabs.get(i));
    }
    public void waitUntilWebElementIsVisible(WebElement webElement) {
        {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
            webDriverWait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(webElement));
        }

    }
    public void waitUntilWebElementIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }
    public void waitForWebElementIgnoringStaleException(WebElement element)
    {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForWebElementIgnoringElementNotInteractableException(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        webDriverWait.ignoring(ElementNotInteractableException.class).until(ExpectedConditions.elementToBeClickable(element));
    }
    public void selectWebElement(WebElement webElement) {
        scrollToElement(webElement);
        waitUntilWebElementIsVisible(webElement);
        waitUntilWebElementIsClickable(webElement);
        waitForWebElementIgnoringStaleException(webElement);
        waitForWebElementIgnoringElementNotInteractableException(webElement);
        webElement.click();
    }
    public void enterValueToTxtField(WebElement webElement, String value) {
        waitUntilWebElementIsVisible(webElement);
        waitUntilWebElementIsClickable(webElement);
        webElement.clear();
        webElement.sendKeys(value);
    }
    public void waitForLoad(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            ExpectedCondition<Boolean> pageLoadCondition = new
                    ExpectedCondition<Boolean>()
                    {
                        public Boolean apply(WebDriver driver)
                        {
                            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                        }
                    };
            wait.until(pageLoadCondition);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
