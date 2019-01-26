package com.tetherfi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TmacPopupPage extends BasePage {

    public TmacPopupPage(WebDriver driver){super(driver);}

    @FindBy(id="div_main")
    private WebElement main;

    @FindBy(id="btn_change_status")
    private WebElement changeStatus;

    @FindBy(id="divAuxStatus")
    private WebElement status;

    @FindBy(id="agentstatus")
    private WebElement agentStatus;

    @FindBy(css="ul[id='auxstatuslist'] li")
    private List<WebElement> statusList;

    @FindBy(id="main_logout_btn")
    private WebElement logoutBtn;

    @FindBy(css=".js-modal-confirm")
    private WebElement confirmLogoutbtn;

    @FindBy(id="btn_make_call")
    private WebElement callBtn;

    @FindBy(id="agentname")
    private WebElement agentName;

    @FindBy(id="btn_timer")
    private WebElement timerBtn;

    @FindBy(id="divTimer")
    private WebElement timer;

    @FindBy(id="wallboardGrid_main")
    private WebElement wallboard;

    @FindBy(css="ul li[id^='li_main']")
    private WebElement mainTab;

    @FindBy(css="ul li[id^='li_1']")
    private List<WebElement> tabs;

    @FindBy(css="input[id^='textCallStatus']")
    private WebElement authStatus;
    @FindBy(css="input[id^='textChatAuthType']")
    private WebElement authLevel;

    @FindBy(css="input[id^='textChatName1']")
    private WebElement custName;
    @FindBy(css="input[id^='textChatCIF1']")
    private WebElement custCIN;
    @FindBy(css="input[id^='textChatSegment1']")
    private WebElement custSegmentCode;
    @FindBy(css="input[id^='textChatSubSegment1']")
    private WebElement custSubSegmentCode;
    @FindBy(css="input[id^='textChatEmail1']")
    private WebElement custEmail;
    @FindBy(css="input[id^='textChatIntent1']")
    private WebElement custIntent;
    @FindBy(css="input[id^='textChatReghone1']")
    private WebElement custRegMobile;
    @FindBy(css="input[id^='textChatChannel1']")
    private WebElement custChannel;
    @FindBy(css="input[id^='textChatQueueTime1']")
    private WebElement custQueueTime;
    @FindBy(css="input[id^='textPageTag1']")
    private WebElement custLastPageVisited;

    @FindBy(css="a[id^='btnTextChat_Disconnect1']")
    private List<WebElement> endChat;

    @FindBy(css=".uk-notify-message")
    private WebElement msg;

    @FindBy(css="a[id^='btnAnswer1']")
    private WebElement receiveCallBtn;

    @FindBy(css="a[id^='btnDrop1']")
    private WebElement dropCallBtn;

    @FindBy(id="sendText")
    private WebElement sendDataTextbox;

    public boolean isTmacPopUpDisplayed(){
    waitForLoad(driver);
    selectWebElement(mainTab);
    return main.isDisplayed();
    }
    public void userLogout(){
    if(!getCurrentStatus().equals(" 10-Logout")){
        changeStatus(" 10-Logout");
    /*selectWebElement(changeStatus);
    selectDropdownFromVisibleTextContains(statusList, "7 - Project");}
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/}
        selectWebElement(logoutBtn);
    selectWebElement(confirmLogoutbtn);
    waitForLoad(driver);
    switchBackToParentWindow();
    }

    public String getCurrentStatus(){
        waitUntilWebElementIsVisible(status);
        return status.getText();
    }
    public String getAgentStatus(){
        waitUntilWebElementIsVisible(agentStatus);
        return agentStatus.getText();
    }
    public void changeStatus(String status){
        if(!getCurrentStatus().equals(status)){
        selectWebElement(changeStatus);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            selectDropdownFromVisibleTextContains(statusList,status);
        waitForJqueryLoad(driver);
    }}
    public boolean verifyLogoutBtnEnabled(){
        if(logoutBtn.getAttribute("class").contains("disabled"))
        return false;
        else return true;
    }
    public boolean verifyCallBtnEnabled(){
        if(callBtn.getAttribute("class").contains("disabled"))
            return false;
        else return true;
    }
    public String getAgentName(){
        waitUntilWebElementIsVisible(agentName);
        return agentName.getText();
    }
    public boolean verifyStationID(String stationId){
        String a[]=getAgentName().split("/");
        if(a[1].equals(stationId)){return true;}else{return false;}
    }
    public boolean verifyTimerDisplayed(){
        return(timerBtn.isEnabled()&&timerBtn.isDisplayed());
    }

    public void closeTmacWithoutLogout(){
        switchBackToParentWindow();
        driver.navigate().refresh();
    }
    public boolean verifyChatInitiatedIsInQueue(){
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(Tabs.get(1));
        boolean status=false;
        List<Map<String,String>> wallboardDetails=getDataFromWallboard();
        for(Map<String,String> map:wallboardDetails){
            if(Integer.valueOf(map.get("CIQ"))>0){status=true;
            System.out.println("Chat Initiated for the skill "+map.get("Skill")+" is in queue");break;}
        }
        return status;
    }
    public List<Map<String,String>> getDataFromWallboard(){
        List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
        Map<String,String> map;
        List<WebElement> rows=wallboard.findElements(By.tagName("tr"));
        List<WebElement> headers=rows.get(0).findElements(By.tagName("th"));
        for(int i=1;i<rows.size();i++){
            waitForWebElementsIgnoringStaleException(rows);
            List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
            map=new HashMap<String,String>();
            waitForWebElementsIgnoringStaleException(cols);
            waitForWebElementsIgnoringStaleException(headers);
            for(int j=0;j<cols.size();j++){
            map.put(headers.get(j).getText(),cols.get(j).getText());
            }
            mapList.add(map);
        }
        return mapList;
    }
    public boolean verifyCustomerDetails(Map<String,String> details){
        waitUntilWebElementListIsVisible(tabs);
        Map<String,String> custdetails=getCustomerDetails();
        if(details.get("Name").equals(custdetails.get("Name")))
        {}else{ System.out.println("Customer name mismatch "+details.get("Name")+":"+custdetails.get("Name"));return false; }
        if(details.get("CIN").split("/")[0].equals(custCIN.getAttribute("value")))
        {}else{ System.out.println("CIN mismatch"+details.get("CIN").split("/")[0]+":"+custdetails.get("CIN"));return false; }
        if(details.get("Segment").equals(custdetails.get("Segment")))
        {}else{ System.out.println("SegmentCode mismatch"+details.get("Segment")+":"+custdetails.get("Segment"));return false; }
        if(details.get("Email").equals(custdetails.get("Email"))){}
        else{ System.out.println("Email mismatch"+details.get("Email")+":"+custdetails.get("Email"));return false; }
        if(details.get("Topic").equals(custdetails.get("Topic"))){}
        else{ System.out.println("Customer Intent mismatch"+details.get("Topic")+":"+custdetails.get("Topic"));return false; }
        if(details.get("Phone").equals(custdetails.get("Phone"))){}
        else{ System.out.println("Customer Reg mobile mismatch"+details.get("Phone")+":"+custdetails.get("Phone"));return false; }
        if(details.get("Channel").equals(custdetails.get("Channel"))){}
        else{ System.out.println("Customer Channel mismatch"+details.get("Channel")+":"+custdetails.get("Channel"));return false; }
        return true;
    }
    public void clickOnEndChat(){
        if(endChat.size()>0){selectWebElement(endChat.get(0));}
    }
    public Map<String,String> getCustomerDetails(){
        Map<String,String> cust=new HashMap<String,String>();
        cust.put("Name",custName.getAttribute("value"));
        cust.put("CIN",custCIN.getAttribute("value"));
        cust.put("Segment",custSegmentCode.getAttribute("value")+"/"+custSubSegmentCode.getAttribute("value"));
        cust.put("Email",custEmail.getAttribute("value"));
        cust.put("Topic",custIntent.getAttribute("value"));
        cust.put("Phone",custRegMobile.getAttribute("value"));
        cust.put("Channel",custChannel.getAttribute("value"));
        return cust;
    }
    public boolean verifyCrmPopUpUrl(String url){
        waitUntilNewTabIsOpen(3);
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        if(Tabs.size()>2){
        waitForLoad(driver);
        driver.switchTo().window(Tabs.get(Tabs.size()-1));
        System.out.println("CRM URL comparision:"+driver.getCurrentUrl()+"\n"+url);
        return driver.getCurrentUrl().contains(url);}
        else{
            System.out.println("No CRM pop up displayed");return false;
        }
    }
    public void closeIservePopUpUrl(){
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        if(Tabs.size()>2){
        driver.switchTo().window(Tabs.get(Tabs.size()-1));
        driver.close();
        driver.switchTo().window(Tabs.get(Tabs.size()-2));}
    }
    public void switchToTmacPopup(){
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        if(Tabs.size()>2){
            driver.switchTo().window(Tabs.get(Tabs.size()-2));}
    }

    public String getMessage(){
        waitUntilWebElementIsVisible(msg);
        //waitUntilTextToBePresentInWebElement(msg,"Chat disconnected by AgentX");
        return msg.getText();
    }
    public void receiveCall(){
        try {
        Thread.sleep(15000);
        selectWebElement(receiveCallBtn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void sendDataToIservePopUpUrl(){
        waitUntilNewTabIsOpen(3);
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        if(Tabs.size()>2){
            waitForLoad(driver);
            driver.switchTo().window(Tabs.get(Tabs.size()-1));
            System.out.println("AgentID:"+driver.getCurrentUrl().split("//&")[1]);
            String a= driver.getCurrentUrl().split("//&")[1];
            a="{\""+a.split("=")[0]+"\":\""+a.split("=")[1]+"\"}";
            System.out.println(a);
            try {
            enterValueToTxtField(sendDataTextbox,a);
            Thread.sleep(5000);
            sendDataTextbox.sendKeys(Keys.ENTER);
            Thread.sleep(10000);
            enterValueToTxtField(sendDataTextbox,"getlast");
            Thread.sleep(5000);
            sendDataTextbox.sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }driver.close();
            driver.switchTo().window(Tabs.get(Tabs.size()-2));
        }
        else{
            System.out.println("No iserve pop up displayed");
        }
    }
    public void dropCall(){
        try {selectWebElement(changeStatus);
            selectWebElement(changeStatus);
        selectWebElement(dropCallBtn);
            Thread.sleep(3000);
            ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(Tabs.get(2));
            driver.close();
            driver.switchTo().window(Tabs.get(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void closeiServepopup(){
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(Tabs.get(2));
        driver.close();
        driver.switchTo().window(Tabs.get(1));
    }
}
