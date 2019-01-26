package com.tetherfi.model.tmac;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class TmacTransferListDetails {
    private String name;
    private String agentExtension;
    private String type;
    private String updateName;
    private String modifyReason;
    private String deleteReason;
    private String skillId;
    private String skillName;
    private String vdn;
    private String skillIdUpdate;
    private String skillNameUpdate;

    public TmacTransferListDetails(Map<String, String> map) {
        name=readName(map);
        agentExtension=readAgentExtension(map);
        type=readType(map);
        updateName=readupdateName(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        skillIdUpdate=readSkillIdUpdate(map);
        skillName=readSkillName(map);
        vdn=readVdn(map);
        skillId=readSkillId(map);
        skillNameUpdate = readSkillNameUpdate(map);
    }
    private String readSkillNameUpdate(Map<String, String> map) {
    	String value=map.get("updateskill name");
    	if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
		return value;
	}
	public String readName(Map<String,String> map){
        String value=map.get("Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readupdateName(Map<String,String> map){
        String value=map.get("update Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readAgentExtension(Map<String,String> map){
        String value=map.get("agent extension");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readType(Map<String,String> map){
        String value=map.get("type");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readModifyReason(Map<String,String> map){
        String value=map.get("modify reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readDeleteReason(Map<String,String> map){
        String value=map.get("Delete Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readSkillId(Map<String,String> map){
        String value=map.get("skill id");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readSkillName(Map<String,String> map){
        String value=map.get("skill name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readVdn(Map<String,String> map){
        String value=map.get("vdn");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readSkillIdUpdate(Map<String,String> map){
        String value=map.get("update skill id");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String getName() {
        return name;
    }
    public String getAgentExtension(){return agentExtension;}
    public String getType(){return type;}
    public String getUpdateName(){return updateName;}
    public String getModifyReason(){return modifyReason;}
    public String getDeleteReason(){return deleteReason;}
    public String getSkillId(){return skillId;}
    public String getSkillName(){return skillName;}
    public String getVdn(){return vdn;}
    public String getSkillIdUpdate() { return skillIdUpdate; }
	public String getSkillNameUpdate() { return skillNameUpdate; }
}