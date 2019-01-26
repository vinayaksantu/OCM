package com.tetherfi.model.user;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class ApplicationAccessControlDetails {
    private String userID;
    private String enabled;
    private String locked;
    private String modifyReason;

    public ApplicationAccessControlDetails(Map<String,String> map){
        userID=readUserID(map);
        enabled=readEnabled(map);
        locked=readLocked(map);
        modifyReason=readModifyReason(map);
    }

    private String readUserID(Map<String, String> map) {
        String value=map.get("User ID");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    private String readEnabled(Map<String, String> map) {
        String value=map.get("Enabled");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    private String readLocked(Map<String, String> map) {
        String value=map.get("Locked");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    private String readModifyReason(Map<String, String> map) {
        String value=map.get("Modify Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getUserID() {
        return userID;
    }

    public String getEnabled() {
        return enabled;
    }

    public String getLocked() {
        return locked;
    }

    public String getModifyReason() {
        return modifyReason;
    }
}

