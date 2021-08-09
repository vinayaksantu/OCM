package com.tetherfi.model.tmac;

import java.util.Map;

public class SkillTemplateDetails {

	private String templateName;
	private String orgUnit;
	private String skillType;
	private String skills;
	private String skillLevels;
	private String modifyReason;
	private String deleteReason;
	private String query;


	public SkillTemplateDetails(Map<String,String> map){
		templateName=readTemplatename(map);	
		orgUnit=readOrgUnit(map);
		skillType=readSkilltype(map);
		skills=readSkills(map);
		skillLevels=readSkillLevels(map);
		modifyReason=readModifyReason(map);
		deleteReason=readDeleteReason(map);
		query=readQuery(map);
	}





	private String readModifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}





	private String readDeleteReason(Map<String, String> map) {
		String value=map.get("Delete Reason");
		return value;
	}





	private String readSkillLevels(Map<String, String> map) {
		String value=map.get("Skill Levels");
		return value;
	}





	private String readSkills(Map<String, String> map) {
		String value=map.get("Skills");
		return value;
	}

	private String readSkilltype(Map<String, String> map) {
		String value=map.get("Skill Type");
		return value;
	}

	private String readOrgUnit(Map<String, String> map) {
		String value=map.get("OrgUnit");
		return value;
	}


	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}


	private String readTemplatename(Map<String, String> map) {
		String value=map.get("Template Name");
		return value;
	}

	public String getTemplatename() {
		return templateName;
	}

	public String getOrgUnit() {
		return orgUnit;
	}
	
	public String getSkillType() {
		return skillType;
	}
	
	public String getSkills() {
		return skills;
	}
	
	public String getSkillLevels() {
		return skillLevels;
	}
	
	public String getModifyReason() {
		return modifyReason;
	}

	public String getQuery() {
		return query;
	}
	
	public String getDeleteReason() {
		return deleteReason;
	}



}
