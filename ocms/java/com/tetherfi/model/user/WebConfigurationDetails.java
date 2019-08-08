package com.tetherfi.model.user;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class WebConfigurationDetails {

    private String key;
    private String updatedkey;
    private String value;
    private String comment;
    private String modifyReason;
    private String deleteReason;

    public WebConfigurationDetails(Map<String,String> map){
        key=readKey(map);
        updatedkey=readUpdatedKey(map);
        value=readValue(map);
        comment=readComment(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
    }
    public String readKey(Map<String,String> map){
        String value=map.get("Key");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readUpdatedKey(Map<String,String> map){
        String value=map.get("Updated Key");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readValue(Map<String,String> map){
        String value=map.get("Value");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readComment(Map<String,String> map){
        String value=map.get("Comment");
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

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public String getUpdatedkey() {
        return updatedkey;
    }
}
