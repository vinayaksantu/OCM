package com.tetherfi.model.user;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class SkillConfigurationDetails {

    private String skillID;
    private String skillName;
    private String skillExtension;
    private String skillPriority;
    private String timeout;
    private String acceptedSL;
    private String enabled;
    private String modifyReason;
    private String deleteReason;
    private String updatedSkillName;
    private String updatedEnabled;
    private String Query;

    public SkillConfigurationDetails(Map<String,String> map){
        skillID=readSkillID(map);
        skillName=readSkillName(map);
        skillExtension=readSkillExtension(map);
        skillPriority=readSkillPriority(map);
        timeout=readTimeout(map);
        acceptedSL=readAccesptedSL(map);
        enabled=readEnabled(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        updatedSkillName=readUpdatedSkillName(map);
        updatedEnabled=readUpdatedEnabled(map);
        Query=readQuery(map);
    }

    private String readQuery(Map<String, String> map) {
    	String value=map.get("Query");
		return value;
	}

	private String readUpdatedEnabled(Map<String, String> map) {
        String value=map.get("Updated Enabled");
		return value;
	}

	private String readUpdatedSkillName(Map<String, String> map) {
        String value=map.get("Updated Skill Name");
		return value;
	}

	private String readSkillPriority(Map<String, String> map) {
        String value=map.get("Skill Priority");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= "Low";
        }
        return value;
    }

    private String readAccesptedSL(Map<String, String> map) {
        String value=map.get("Accepted SL");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= "00:00:00";
        }
        return value;
    }
    private String readTimeout(Map<String, String> map) {
        String value=map.get("Timeout");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= "00:00:00";
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

    private String readEnabled(Map<String, String> map) {
        String value=map.get("Enabled");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;}

    private String readSkillExtension(Map<String, String> map) {
        String value=map.get("Skill Extension");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;}

    private String readSkillName(Map<String, String> map) {
        String value=map.get("Skill Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;}

    private String readSkillID(Map<String, String> map) {
        String value=map.get("Skill ID");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String getSkillID() {
        return skillID;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getSkillExtension() {
        return skillExtension;
    }

    public String getEnabled() {
        return enabled;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public String getTimeout() { return timeout;}

    public String getAcceptedSL() { return acceptedSL;}

    public String getSkillPriority() {
        return skillPriority;
    }

	public String getUpdatedSkillName() {
		return updatedSkillName;
	}

	public String getUpdatedEnabled() {
		return updatedEnabled;
	}

	public String getQuery() {
		return Query;
	}
}
