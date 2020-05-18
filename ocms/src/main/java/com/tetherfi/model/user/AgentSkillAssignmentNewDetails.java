package com.tetherfi.model.user;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

public class AgentSkillAssignmentNewDetails {
	private String username;
	   private String skillType;
	   private String skillName;
	   private String skillLevel;
	   private String teamName;
	   private String agentList;
	   private String multipleSkills;
	   private String multipleSkillLevels;

	    public AgentSkillAssignmentNewDetails(Map<String,String> map){
	        username=readUsername(map);
	        skillType=readSkillType(map);
	        skillName=readSkillName(map);
	        skillLevel=readSkillLevel(map);
	        teamName=readTeamname(map);
	        agentList=readAgentList(map);
	        multipleSkills=readMultipleSkills(map);
	        multipleSkillLevels=readMultipleSkillLevels(map);
	    }
	    private String readMultipleSkillLevels(Map<String, String> map) {
	    	String value=map.get("MultipleSkillLevels");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value= RandomStringUtils.randomAlphabetic(10);
	        }
	        return value;
		}
		private String readMultipleSkills(Map<String, String> map) {
	    	String value=map.get("MultipleSkills");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value= RandomStringUtils.randomAlphabetic(10);
	        }
	        return value;
		}
		private String readAgentList(Map<String, String> map) {
	    	String value=map.get("AgentList");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value= RandomStringUtils.randomAlphabetic(10);
	        }
	        return value;
		}
		private String readTeamname(Map<String, String> map) {
	    	 String value=map.get("TeamName");
		        if(value==null||value.equalsIgnoreCase("random.str")){
		            value= RandomStringUtils.randomAlphabetic(10);
		        }
		        return value;
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
	    
	    public String getTeamName() {
	    	return teamName;
	    }
	    
	    public String getAgentList() {
	    	return agentList;
	    }
	    
	    public String getMultipleSkills() {
	    	return multipleSkills;
	    }
	    
	    public String getMultipleSkillLevels() {
	    	return multipleSkillLevels;
	    }

}
