package com.tetherfi.pages;

import com.tetherfi.model.ivr.IntroMessageAnnouncementDetails;
import com.tetherfi.utility.FileUploader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntroMessageAnnouncementPage extends BasePage {

    public IntroMessageAnnouncementPage(WebDriver driver){super(driver);}

    @FindBy(css = "#gridDiv2 .fa-search")
    private WebElement searchBtn;

    @FindBy(css = ".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchCol;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css = "#1001sFormDropDown .k-i-arrow-60-down")
    private List<WebElement> selectSearchCriteria;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchCriteriaDropDwn;
    @FindBy(id = "1001sTextToSearch")
    private WebElement searchTextBox;

    @FindBy(css = "#1001sAddButton .k-i-add")
    private WebElement searchAddCriteriaBtn;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css=".ibox-title h5")
    private WebElement introMessageAnnouncement;

    @FindBy(id="makeChanges")
    private WebElement makeIntroMessageAnnouncementChanges;

    @FindBy(css="#create")
    private WebElement addNewIntroMessageAnnouncementRcrdBtn;

    @FindBy(id="goToAuditTrail")
    private WebElement goBackBtn;

    @FindBy(id="taskComplete")
    private WebElement taskCompleteBtn;

    @FindBy(id="MakerComments")
    private WebElement makerComments;

    @FindBy(id="submitMakerComment")
    private WebElement taskCompleteBtnAtMakerCommentsPopUp;

    @FindBy(css=".k-grid")
    private WebElement gridContent;

    @FindBy(css="#drillGrid .k-grid-content")
    private WebElement gridContent1;

    @FindBy(css="span[aria-owns='functionalityId_listbox']")
    private WebElement funtionalityDropdown;

    @FindBy(css="ul[id='functionalityId_listbox'] li")
    private List<WebElement> functionalityListBox;

    @FindBy(css="span[aria-owns='languageId_listbox']")
    private WebElement languageDropdown;

    @FindBy(css="ul[id='languageId_listbox'] li")
    private List<WebElement> languageListBox;

    @FindBy(css="span[aria-owns='Status_listbox']")
    private WebElement statusDropdown;

    @FindBy(css="ul[id='Status_listbox'] li")
    private List<WebElement> statusListBox;

    @FindBy(css="span[aria-owns='Interrupt_listbox']")
    private WebElement interruptDropdown;

    @FindBy(css="ul[id='Interrupt_listbox'] li")
    private List<WebElement> interruptListBox;

    @FindBy(id="StartDateTime")
    private WebElement startDateTime;

    @FindBy(id="EndDateTime")
    private WebElement endDateTime;

    @FindBy(css=".k-upload-button")
    private WebElement selectFile;

    @FindBy(css=".k-grid-update")
    private WebElement saveBtn;

    @FindBy(css = ".k-grid-CustomDelete")
    private WebElement deleteButton;

    @FindBy(id = "ModifyReason1")
    private WebElement deleteReasonTextBox;

    @FindBy(id = "yesButton")
    private WebElement deleteYesBtn;

    @FindBy(css = "#toast-container .toast-error")
    private List<WebElement> errorMsg;


    @FindBy(css = ".modal-footer .button-theme")
    private WebElement searchSearchBtn;
    @FindBy(css = ".k-grid-edit")
    private WebElement editButton;
    @FindBy(id = "ModifyReason")
    private WebElement ModifyReasonTextBox;

    @FindBy(css="#lanid b")
    private WebElement loggedInUserName;

    @FindBy(id="Approve")
    private WebElement approveBtn;

    @FindBy(id="Reject")
    private WebElement rejectBtn;

    @FindBy(id="CheckerReason")
    private WebElement checkerReason;

    @FindBy(id="approveButton")
    private WebElement yesBtn;

    @FindBy(id="rejectButton")
    private WebElement noBtn;

    public boolean isIntroMessageAnnouncementPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return introMessageAnnouncement.isEnabled();
    }
    public void addNewIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails details) {
        selectWebElement(makeIntroMessageAnnouncementChanges);
        waitForLoad(driver);waitForJqueryLoad(driver);
        selectWebElement(addNewIntroMessageAnnouncementRcrdBtn);
        waitForJqueryLoad(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectWebElement(funtionalityDropdown);
        selectDropdownFromVisibleText(functionalityListBox,details.getFunctionality());
        selectWebElement(languageDropdown);
        selectDropdownFromVisibleText(languageListBox,details.getLanguage());
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,details.getStatus());
        selectWebElement(interruptDropdown);
        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
        selectWebElement(startDateTime);
        enterValueToTxtField(startDateTime,details.getStartDateTime());
        selectWebElement(endDateTime);
        enterValueToTxtField(endDateTime,details.getEndDateTime());
        clickOnUsingActionClass(selectFile);
        //Auto It script to load wave file
        FileUploader upload= new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\welcome.wav");
        selectWebElement(saveBtn);
    }
    public void taskCompleteAction(String comment){
        selectWebElement(makeIntroMessageAnnouncementChanges);
        waitForLoad(driver);waitForJqueryLoad(driver);
        selectWebElement(taskCompleteBtn);
        enterValueToTxtField(makerComments,comment);
        clickOn(taskCompleteBtnAtMakerCommentsPopUp);
    }
    public void searchIntroMessageAnnouncementRecord(String functionality) {
        selectWebElement(searchBtn);
        selectWebElement(selectSearchCol.get(0));
        selectDropdownFromVisibleText(columnNameList,"Functionality");
        selectWebElement(selectSearchCol.get(1));
        selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
        enterValueToTxtField(searchTextBox,functionality);
        selectWebElement(searchSearchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(gridContent1);
    }

    public void editIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails details) {
        selectWebElement(makeIntroMessageAnnouncementChanges);
        waitForJqueryLoad(driver);
        searchIntroMessageAnnouncementRecord(details.getFunctionality());
        selectWebElement(editButton);
        waitForJqueryLoad(driver);
        selectWebElement(statusDropdown);
        selectDropdownFromVisibleText(statusListBox,details.getStatus());
        selectWebElement(interruptDropdown);
        selectDropdownFromVisibleText(interruptListBox,details.getInterrupt());
        selectWebElement(startDateTime);
        enterValueToTxtField(startDateTime,details.getStartDateTime());
        selectWebElement(endDateTime);
        enterValueToTxtField(endDateTime,details.getEndDateTime());
        clickOnUsingActionClass(selectFile);
        //Auto It script to load wave file
        FileUploader upload= new FileUploader();
        upload.uploadFile(System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\welcome.wav");
        selectWebElement(ModifyReasonTextBox);
        enterValueToTxtField(ModifyReasonTextBox,details.getModifyReason());
        selectWebElement(saveBtn);
    }
    public void deleteIntroMessageAnnouncementRecord(IntroMessageAnnouncementDetails details) {
        selectWebElement(makeIntroMessageAnnouncementChanges);
        waitForJqueryLoad(driver);
        searchIntroMessageAnnouncementRecord(details.getFunctionality());
        selectWebElement(deleteButton);
        enterValueToTxtField(deleteReasonTextBox,details.getDeleteReason());
        selectWebElement(deleteYesBtn);
    }
    public String verifySuccessMessage(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return errorMsg.get(0).getText();}
        else return successmsg.getText();
    }
    public boolean verifyTaskCompleteSuccessMessage(){
        return(verifySuccessMessage().contains("Record submission for approval success. Your Request ID is :"));
    }
    public void clickOnGoBack(){
        waitForJqueryLoad(driver);
        selectWebElement(goBackBtn);
        waitForJqueryLoad(driver);
    }
    private Map<String,String> getFirstRowDatafromTable(){
        Map<String,String> map = new HashMap<>();
        List<WebElement> rows=gridContent.findElements(By.tagName("tr"));
        List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
        List<WebElement> cols=rows.get(1).findElements(By.tagName("td"));
        for(int j=0;j<headers.size();j++){
            scrollToElement(headers.get(j));
            map.put(headers.get(j).getText(),cols.get(j).getText());
        }
        return map;
    }
    public boolean verifyAuditTrail(IntroMessageAnnouncementDetails details,String transaction, String status){
        boolean stat=false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:");
        String date = simpleDateFormat.format(new Date());
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        if(firstRowData.get("Transaction").equalsIgnoreCase(transaction)){
            if(firstRowData.get("Status").equalsIgnoreCase(status)){
                if(firstRowData.get("Function").equalsIgnoreCase("IvrIntroductoryMessageAnnouncement")){
                    if(firstRowData.get("Submission DateTime").contains(date)){
                    if(transaction.equals("MakerCreate")||transaction.equals("MakerUpdate")){
                        Map<String,String> newvalues=new HashMap<>();
                        String[] d=firstRowData.get("New Values").split("\n");
                        for(String e:d){
                            String[]f=e.split(":",2);
                            newvalues.put(f[0],f[1]);
                        }
                        if(verifyNewValues(details,newvalues)){
                        stat=true;}
                    }else{stat=true;}
                    }else{System.out.println("Data mismatch:"+firstRowData.get("Submission DateTime")+"\t"+date);}
                }else{System.out.println("Data mismatch:"+firstRowData.get("Function")+"\t"+"IvrIntroductoryMessageAnnouncement");}
            }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+status);}
        }else{System.out.println("Data mismatch:"+firstRowData.get("Transaction")+"\t"+transaction);}
    return stat;}
    public boolean verifyNewValues(IntroMessageAnnouncementDetails details, Map<String,String> newvalues){
        boolean stat=false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:");
            String date = simpleDateFormat.format(new Date());
            if(newvalues.get("Functionality").equals(details.getFunctionality())){
                if(newvalues.get("Language").equals(details.getLanguage())){
                    if(newvalues.get("StartDateTime").equals(details.getStartDateTime())){
                        if(newvalues.get("EndDateTime").equals(details.getEndDateTime())){
                            if(newvalues.get("Interrupt").equals(details.getInterrupt())){
                                if(newvalues.get("Status").equals(details.getStatus())){
                                   // if(newvalues.get("LastChangedOn").contains(date)){
                                        if(newvalues.get("LastChangedBy").equals(loggedInUserName.getText())){
                                            stat=true;
                                        }else{System.out.println("data mismatch"+newvalues.get("LastChangedBy")+"\t"+loggedInUserName.getText());}
                                   // }else{System.out.println("data mismatch"+newvalues.get("LastChangedOn")+"\t"+date);}
                                }else{System.out.println("data mismatch"+newvalues.get("Status")+"\t"+details.getStatus());}
                            }else{System.out.println("data mismatch"+newvalues.get("Interrupt")+"\t"+details.getInterrupt());}
                        }else{System.out.println("data mismatch"+newvalues.get("EndDateTime")+"\t"+details.getEndDateTime());}
                    }else{System.out.println("data mismatch"+newvalues.get("StartDateTime")+"\t"+details.getStartDateTime());}
                }else{System.out.println("data mismatch"+newvalues.get("Language")+"\t"+details.getLanguage());}
            }else{System.out.println("data mismatch"+newvalues.get("Language")+"\t"+details.getLanguage());}
        return stat;
    }
    public void clickonApprove(String comment){
        clickOn(approveBtn);
        waitForJqueryLoad(driver);
        selectWebElement(checkerReason);
        enterValueToTxtField(checkerReason,comment);
        clickOn(yesBtn);
    }
    public boolean verifyReviewAuditTrail(String status,String comment){
        boolean stat=false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:");
        String date = simpleDateFormat.format(new Date());
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        if(firstRowData.get("Status").equals(status)){
            if(firstRowData.get("Checker Comments").equals(comment)){
                if (firstRowData.get("Review DateTime").contains(date)){
                    stat=true;
                }else{System.out.println("Data mismatch:"+firstRowData.get("Review DateTime")+"\t"+date);}
            }else{System.out.println("Data mismatch:"+firstRowData.get("Checker Comments")+"\t"+comment);}
        }else{System.out.println("Data mismatch:"+firstRowData.get("Status")+"\t"+status);}
        return stat;
    }
    public boolean verifyStatus(String status){
        Map<String,String> firstRowData=getFirstRowDatafromTable();
        return firstRowData.get("Status").equals(status);
    }
}
