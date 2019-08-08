package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VoiceAgentServiceQueueDashboardPage extends BasePage {

    public VoiceAgentServiceQueueDashboardPage(WebDriver driver){super(driver);}

    @FindBy(id = "bcmsrealtimequeue")
    private WebElement bcmsTab;

    @FindBy(id = "bcmsStaff")
    private WebElement agentsLoggedIn;

    @FindBy(id = "bcmsAvail")
    private WebElement agentsAvailable;

    @FindBy(id = "bcmsciq")
    private WebElement interactionsInQueue;

    @FindBy(id = "bcmsInteractions")
    private WebElement activeInteractions;

    @FindBy(id = "bcmsacdInteractionsummary")
    private WebElement totalACDInteractions;

    @FindBy(id = "bcmsabandonedInteractions")
    private WebElement abondonedInteractions;

    @FindBy(id = "bcmsgrid")
    private WebElement bcmsgrid;

    public boolean isVoiceAgentServiceQueueDashboardPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return bcmsTab.isEnabled();
    }

    public boolean verifyDashboardPageContents() {
        boolean status = false;
        if (agentsLoggedIn.isDisplayed()) {
            if (agentsAvailable.isDisplayed()) {
                if (interactionsInQueue.isDisplayed()) {
                    if (activeInteractions.isDisplayed()) {
                        if (totalACDInteractions.isDisplayed()) {
                            if (abondonedInteractions.isDisplayed()) {
                                if (bcmsgrid.isDisplayed()) {
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
