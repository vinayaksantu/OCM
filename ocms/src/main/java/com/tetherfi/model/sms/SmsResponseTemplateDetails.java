package com.tetherfi.model.sms;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class SmsResponseTemplateDetails {
    private String text;
    private String intent;
    private String enable;
    private String source;
    private String alertCode;
    private String appID;
    private String messageDescription;
    private String iCOMTemplateID;
    private String modifyReason;
    private String deleteReason;
    private String UpdatedMessageDescription;

    public SmsResponseTemplateDetails(Map<String,String> map){
        text=readText(map);
        intent=readIntent(map);
        enable=readEnable(map);
        source=readSource(map);
        alertCode=readAlertCode(map);
        appID=readAppID(map);
        messageDescription=readMessageDescription(map);
        iCOMTemplateID=readIcomTemplateID(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        UpdatedMessageDescription=readUpdatedMessageDescription(map);
    }

    private String readUpdatedMessageDescription(Map<String, String> map) {
    	 String value=map.get("Updated Message Description");
         if(value==null||value.equalsIgnoreCase("random.str")){
             value= RandomStringUtils.randomAlphabetic(10);
         }
         return value;
	}

	private String readIcomTemplateID(Map<String, String> map) {
        String value=map.get("IcomTemplateID");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readMessageDescription(Map<String, String> map) {
        String value=map.get("Message Description");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readAppID(Map<String, String> map) {
        String value=map.get("AppID");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readAlertCode(Map<String, String> map) {
        String value=map.get("AlertCode");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readSource(Map<String, String> map) {
        String value=map.get("Source");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readEnable(Map<String, String> map) {
        String value=map.get("Enable");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readIntent(Map<String, String> map) {
        String value=map.get("Intent");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readText(Map<String, String> map) {
        String value=map.get("Text");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String readModifyReason(Map<String,String> map){
        String value=map.get("Modify Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
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

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public String getText() {
        return text;
    }

    public String getIntent() {
        return intent;
    }

    public String getEnable() {
        return enable;
    }

    public String getSource() {
        return source;
    }

    public String getAlertCode() {
        return alertCode;
    }

    public String getAppID() {
        return appID;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public String getiCOMTemplateID() {
        return iCOMTemplateID;
    }

	public String getUpdatedMessageDescription() {
		return UpdatedMessageDescription;
	}

	public String getQuery() {
		// TODO Auto-generated method stub
		return null;
	}
}
