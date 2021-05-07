package com.tetherfi.model.tmac;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

public class AttributeAssignmentDetails {


	private String attributeType;
	private String attributeName;
	private String attributeLevel;
	private String teamName;
	private String agentList;
	private String multipleAttributes;
	private String multipleAttributeLevels;
	private String query;




	public AttributeAssignmentDetails(Map<String,String> map){
		attributeType=readAttributeType(map);
		attributeName=readAttributeName(map);
		attributeLevel=readAttributeLevel(map);
		teamName=readTeamname(map);
		agentList=readAgentList(map);
		multipleAttributes=readMultipleAttributes(map);
		multipleAttributeLevels=readMultipleAttributeLevels(map);
		query=readQuery(map);





	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readMultipleAttributeLevels(Map<String, String> map) {
		String value=map.get("MultipleAttributesLevels");
		if(value==null||value.equalsIgnoreCase("random.str")){
			value= RandomStringUtils.randomAlphabetic(10);
		}
		return value;
	}
	private String readMultipleAttributes(Map<String, String> map) {
		String value=map.get("MultipleAttributes");
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

	private String readAttributeType(Map<String, String> map) {
		String value=map.get("Attribute Type");
		if(value==null||value.equalsIgnoreCase("random.str")){
			value= RandomStringUtils.randomAlphabetic(10);
		}
		return value;
	}
	private String readAttributeName(Map<String, String> map) {
		String value=map.get("Attribute Name");
		if(value==null||value.equalsIgnoreCase("random.str")){
			value= RandomStringUtils.randomAlphabetic(10);
		}
		return value;
	}
	private String readAttributeLevel(Map<String, String> map) {
		String value=map.get("Attribute Level");
		if(value==null||value.equalsIgnoreCase("random.str")){
			value= RandomStringUtils.randomAlphabetic(10);
		}
		return value;
	}



	public String getAttributeType() {
		return attributeType;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getAttributeLevel() {
		return attributeLevel;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getAgentList() {
		return agentList;
	}

	public String getMultipleAttributes() {
		return multipleAttributes;
	}

	public String getMultipleAttributeLevels() {
		return multipleAttributeLevels;
	}


	
	
	public String getQuery() {
		return query;
	}


}
