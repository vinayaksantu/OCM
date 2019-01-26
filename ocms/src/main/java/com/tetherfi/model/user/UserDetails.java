package com.tetherfi.model.user;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class UserDetails {
    private String userId;
    private String roleName;
    private String updateRoleName;
    private String modifyReason;
    private String deleteReason;

    public UserDetails(String roleName){
        this.roleName=roleName;
        updateRoleName=RandomStringUtils.randomAlphabetic(7);
        modifyReason=RandomStringUtils.randomAlphabetic(10);
        deleteReason=RandomStringUtils.randomAlphabetic(10);
    }
    public UserDetails(){
        this(RandomStringUtils.randomAlphabetic(7));
    }
    public UserDetails(Map<String,String> map){
        userId=readUserId(map);
        roleName=readRoleName(map);
        updateRoleName=readUpdatedRoleName(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
    }
    public String readUserId(Map<String,String> map){
        String value=map.get("User ID");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readRoleName(Map<String,String> map){
        String value=map.get("Role Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readUpdatedRoleName(Map<String,String> map){
        String value=map.get("Updated Role Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
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
    public String getUserId(){return userId;}
    public String getRoleName(){return roleName;}
    public String getUpdateRoleName(){return updateRoleName;}
    public String getModifyReason(){return modifyReason;}
    public String getDeleteReason(){return deleteReason;}
}
