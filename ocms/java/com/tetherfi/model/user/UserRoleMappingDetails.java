package com.tetherfi.model.user;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class UserRoleMappingDetails {
    private String firstname;
    private String lastname;
    private String bankUserName;
    private String avayaLoginID;
    private String teamName;
    private String profile;
    private String supervisor;
    private String role;
    private String modifyReason;
    private String deleteReason;

    public UserRoleMappingDetails(Map<String,String> map){
        firstname=readFirstName(map);
        lastname=readLastName(map);
        bankUserName=readBankUserName(map);
        avayaLoginID=readAvayaLoginID(map);
        teamName=readTeamName(map);
        role=readRole(map);
        profile=readProfile(map);
        supervisor=readSupervisor(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
    }

    private String readRole(Map<String, String> map) {
        String value=map.get("Role");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;}

    private String readLastName(Map<String, String> map) {
        String value=map.get("Last Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;}

    private String readFirstName(Map<String, String> map) {
        String value=map.get("First Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readBankUserName(Map<String,String> map){
        String value=map.get("Bank User Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readSecurityGroup(Map<String,String> map){
        String value=map.get("Security Group");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readAvayaLoginID(Map<String,String> map){
        String value=map.get("Avaya Login ID");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readTeamName(Map<String,String> map){
        String value=map.get("Team Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readProfile(Map<String,String> map){
        String value=map.get("Profile");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readSupervisor(Map<String,String> map){
        String value=map.get("Supervisor");
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
    public String getBankUserName(){return bankUserName;}
    public String getAvayaLoginID() {return avayaLoginID;}
    public String getProfile() {return profile;}
    public String getSupervisor() {return supervisor;}
    public String getTeamName() {return teamName;}
    public String getModifyReason(){return modifyReason;}
    public String getDeleteReason(){return deleteReason;}
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getRole() {
        return role;
    }

	public String getLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDivision() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDepartment() {
		// TODO Auto-generated method stub
		return null;
	}
}