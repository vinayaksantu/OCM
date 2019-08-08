package com.tetherfi.model.user;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class AgentSkillAssignmentDetails {

   private String username;
   private String skillType;
   private String skillName;
   private String skillLevel;

    public AgentSkillAssignmentDetails(Map<String,String> map){
        username=readUsername(map);
        skillType=readSkillType(map);
        skillName=readSkillName(map);
        skillLevel=readSkillLevel(map);
    }
    private String readUsername(Map<String, String> map) {
        String value=map.get("UserName");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    private String readSkillType(Map<String, String> map) {
        String value=map.get("Skill Type");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    private String readSkillName(Map<String, String> map) {
        String value=map.get("Skill Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    private String readSkillLevel(Map<String, String> map) {
        String value=map.get("Skill Level");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getUsername() {
        return username;
    }

    public String getSkillType() {
        return skillType;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getSkillLevel() {
        return skillLevel;
    }
}
