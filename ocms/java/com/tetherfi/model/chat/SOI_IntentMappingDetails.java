package com.tetherfi.model.chat;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class SOI_IntentMappingDetails {

    private String segment;
    private String subSegment;
    private String intent;
    private String language;
    private String requestType;
    private String chatVDN;
    private String CBAVDN;
    private String custEntType;
    private String modifyReason;
    private  String deleteReason;

    public SOI_IntentMappingDetails(Map<String,String> map){
        segment=readSegment(map);
        subSegment=readSubSegment(map);
        intent=readIntent(map);
        language=readLanguage(map);
        requestType=readRequestType(map);
        chatVDN=readChatVDN(map);
        CBAVDN=readCBAVDN(map);
        custEntType=readCustEntType(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
    }

    private String readCustEntType(Map<String, String> map) {
        String value=map.get("CustEntType");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readCBAVDN(Map<String, String> map) {
        String value=map.get("CBA VDN");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readChatVDN(Map<String, String> map) {
        String value=map.get("Chat VDN");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readRequestType(Map<String, String> map) {
        String value=map.get("Request Type");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;}

    private String readLanguage(Map<String, String> map) {
        String value=map.get("Language");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;}

    private String readIntent(Map<String, String> map) {
        String value=map.get("Intent");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;}

    private String readSubSegment(Map<String, String> map) {
        String value=map.get("Sub Segment");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readSegment(Map<String, String> map) {
        String value=map.get("Segment");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
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
    public String getSegment() {
        return segment;
    }

    public String getSubSegment() {
        return subSegment;
    }

    public String getIntent() {
        return intent;
    }

    public String getLanguage() {
        return language;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getChatVDN() {
        return chatVDN;
    }

    public String getCBAVDN() {
        return CBAVDN;
    }

    public String getCustEntType() {
        return custEntType;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }
}
