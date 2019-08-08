package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChatCallbackDashboardPage extends BasePage {

    public ChatCallbackDashboardPage(WebDriver driver){super(driver);}

    @FindBy(id="chatcallback")
    private WebElement chatcallbackTab;

    @FindBy(id="totalchatCallbacks")
    private WebElement totalchatCallbacks;

    @FindBy(id="chatpendingCallbacks")
    private WebElement chatpendingCallbacks;

    @FindBy(id="chatclosedCallbacks")
    private WebElement chatclosedCallbacks;

    @FindBy(id="chatassignedCallbacks")
    private WebElement chatassignedCallbacks;

    @FindBy(id="chatAvgSlaTime")
    private WebElement chatAvgSlaTime;

    @FindBy(id="chatCallbackAHT")
    private WebElement chatCallbackAHT;

    @FindBy(id="chatcallbackSummaryChart")
    private WebElement chatcallbackSummaryChart;

    @FindBy(id="ChatCallbackGrid")
    private WebElement chatCallbackGrid;

    public boolean isChatCallbackDashboardPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return chatcallbackTab.isEnabled();
    }

    public boolean verifyChatCallBackDashboradPageContents(){
        boolean status=false;
        if(totalchatCallbacks.isDisplayed()){
            if(chatpendingCallbacks.isDisplayed()){
                if(chatclosedCallbacks.isDisplayed()){
                    if(chatassignedCallbacks.isDisplayed()){
                        if(chatAvgSlaTime.isDisplayed()){
                            if(chatCallbackAHT.isDisplayed()){
                                if(chatcallbackSummaryChart.isDisplayed()){
                                    if(chatCallbackGrid.isDisplayed()){
                                        status=true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    return status;}

}
