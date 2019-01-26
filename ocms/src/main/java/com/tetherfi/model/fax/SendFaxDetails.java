package com.tetherfi.model.fax;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class SendFaxDetails {
    private String recipientNumber;
    private String faxLine;
    private String dateAndTimeToSchedule;
    private String sendnow;
    private String template;
    private String fileupload;
    private String modifyReason;
    private String deleteReason;

    public SendFaxDetails(Map<String,String> map) {
        faxLine=readFaxline(map);
        recipientNumber=readRecipientNumber(map);
        dateAndTimeToSchedule=readDateAndTimeToSchedule(map);
        sendnow=readSendNow(map);
        template=readTemplate(map);
        fileupload=readFileUpload(map);
        deleteReason=readDeleteReason(map);
        modifyReason=readModifyReason(map);
    }

    private String readSendNow(Map<String, String> map) {
        String value=map.get("Send Immediately");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readFaxline(Map<String,String> map){
        String value=map.get("FaxLine");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readRecipientNumber(Map<String,String> map){
        String value=map.get("RecipientNumber");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readDateAndTimeToSchedule(Map<String,String> map){
        String value=map.get("DateAndTimeToSchedule");
        if(value==null||value.equalsIgnoreCase("random.str")){
            Date dNow = new Date( );
            Calendar c = Calendar.getInstance();
            c.setTime(dNow);
            c.add(Calendar.HOUR,2);//add 2 hours
            SimpleDateFormat ft =new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
            value=ft.format(c.getTime());
        }
        return value;
    }

    public String readTemplate(Map<String,String> map){
        String value=map.get("Template");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
public String readFileUpload(Map<String,String> map){
    String value=map.get("FileUpload");
    if(value==null||value.equalsIgnoreCase("random.str")){
        value= RandomStringUtils.randomAlphabetic(7);
    }
    return value;
}

    public String readModifyReason(Map<String,String> map){
        String value=map.get("Modify Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String readDeleteReason(Map<String,String> map){
        String value=map.get("Delete Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getFaxLine() {
        return faxLine;
    }



    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {

        return deleteReason;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public String getDateAndTimeToSchedule() {
        return dateAndTimeToSchedule;
    }

    public String getTemplate() {
        return template;
    }

    public String getFileupload() {
        return fileupload;
    }

    public String getSendnow() {
        return sendnow;
    }
}
