package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChatDashboardPage extends BasePage {

    public ChatDashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "chat")
    private WebElement chatTab;

    @FindBy(id = "chatCount")
    private WebElement chatCount;

    @FindBy(id = "transferedChats")
    private WebElement transferedChats;

    @FindBy(id = "chatsConferenced")
    private WebElement chatsConferenced;

    @FindBy(id = "chatAHT")
    private WebElement averageHandleTime;

    @FindBy(id = "chatFCR")
    private WebElement firstCallResolution;

    @FindBy(id = "chatCallback")
    private WebElement chatCallback;

    @FindBy(id = "chatSummaryChart")
    private WebElement chatSummaryChart;

    public boolean isChatDashboardPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return chatTab.isEnabled();
    }

    public boolean verifyChatDashboardPageContents() {
        boolean status = false;
        if (chatCount.isDisplayed()) {
            if (transferedChats.isDisplayed()) {
                if (chatsConferenced.isDisplayed()) {
                    if (firstCallResolution.isDisplayed()) {
                        if (averageHandleTime.isDisplayed()) {
                            if (chatCallback.isDisplayed()) {
                                if (chatSummaryChart.isDisplayed()) {
                                    status = true;
                                }
                            }
                        }
                    }

                }

            }
        }
        return status;
    }
}
