package com.tetherfi.model.tmac;

import java.util.Map;

public class AttributesDetails {

	private String name;
	private String category;
	private String priorityLevel;
	private String isEnabled;
	private String updatedPriorityLevel;
	private String modifyReason;
	private String deleteReason;
	private String query;


	public AttributesDetails(Map<String,String> map){
		name=readName(map);
		category=readCategory(map);
		priorityLevel=readPriorityLevel(map);
		isEnabled=readIsEnabled(map);
		updatedPriorityLevel=readUpdatedPriorityLevel(map);
		modifyReason=readModifyReason(map);
		deleteReason=readDeleteReason(map);
		query=readQuery(map);
	}


	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}


	private String readDeleteReason(Map<String, String> map) {
		String value=map.get("Delete Reason");
		return value;
	}


	private String readModifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}


	private String readUpdatedPriorityLevel(Map<String, String> map) {
		String value=map.get("Updated Priority Level");
		return value;
	}


	private String readIsEnabled(Map<String, String> map) {
		String value=map.get("IsEnabled");
		return value;
	}


	private String readPriorityLevel(Map<String, String> map) {
		String value=map.get("Priority Level");
		return value;
	}


	private String readCategory(Map<String, String> map) {
		String value=map.get("Category");
		return value;
	}


	private String readName(Map<String, String> map) {
		String value=map.get("Name");
		return value;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getPriorityLevel() {
		return priorityLevel;
	}

	public String getisEnabled() {
		return isEnabled;
	}

	public String getUpdatedPriorityLevel() {
		return updatedPriorityLevel;
	}

	public String getModifyReason() {
		return modifyReason;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public String getQuery() {
		return query;
	}
}
