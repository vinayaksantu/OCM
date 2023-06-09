package com.tetherfi.model.user;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class UserRoleMappingDetails {
    private String firstname;
    private String lastname;
    private String bankUserName;
    private String agentID;
    private String teamName;
    private String profile;
    private String supervisor;
    private String role;
    private String modifyReason;
    private String deleteReason;
    private String UpdatedFirstName;
    private String OrgUnit;
    private String pbxID;
    private String query;

    public UserRoleMappingDetails(Map<String,String> map){
        firstname=readFirstName(map);
        lastname=readLastName(map);
        bankUserName=readBankUserName(map);
        agentID=readAgentID(map);
        teamName=readTeamName(map);
        role=readRole(map);
        profile=readProfile(map);
        supervisor=readSupervisor(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        UpdatedFirstName=readUpdatedFirstName(map);
        OrgUnit=readOrgUnit(map);
        pbxID=readPBXID(map);
        query=readQuery(map);
    }

    private String readPBXID(Map<String, String> map) {
    	String value=map.get("PBX ID");
        return value;
	}

	private String readQuery(Map<String, String> map) {
    	String value=map.get("Query");
        return value;
	}

	private String readOrgUnit(Map<String, String> map) {
    	String value=map.get("OrgUnit");
        return value;
	}

	private String readUpdatedFirstName(Map<String, String> map) {
    	String value=map.get("Updated First Name");
        return value;
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
        String value=map.get("User Name");
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
    public String readAgentID(Map<String,String> map){
        String value=map.get("Agent ID");
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
    public String getAgentID() {return agentID;}
    public String getPBXID() {return pbxID;}
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

	public String getUpdatedFirstname() {
	return UpdatedFirstName;
	}

	public String getOrgUnit() {
		return OrgUnit;
	}

	public String getQuery() {
		return query;
	}
}