package com.tetherfi.model.chat;

import java.util.Map;

public class ChatMenuDescriptionDetails {
	private String MenuId;
	private String MenuName;
	private String Intent;
	private String query;
	private String modifyReason;
	private String deleteReason;
	private String UpdatedMenuName;
	

	public ChatMenuDescriptionDetails(Map<String, String> map) {
		MenuId=readMenuId(map);
		MenuName=readMenuName(map);
		Intent=readIntent(map);
		query=readQuery(map);
		modifyReason=readModifyReason(map);
		deleteReason=readDeleteReason(map);
		UpdatedMenuName=readUpdatedMenuName(map);
	}
	
	
	private String readUpdatedMenuName(Map<String, String> map) {
		String value=map.get("Updated MenuName");
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


	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}


	private String readIntent(Map<String, String> map) {
		String value=map.get("Intent");
		return value;
	}


	private String readMenuName(Map<String, String> map) {
		String value=map.get("Menu Name");
		return value;
	}


	private String readMenuId(Map<String, String> map) {
		String value=map.get("Menu Id");
		return value;
	}


	public String getMenuId()
	{
		return MenuId;
	}


	public String getMenuName() {
		return MenuName;
	}


	public String getIntent() {
		return Intent;
	}


	public String getQuery() {
		return query;
	}


	public String getModifyReason() {
		return modifyReason;
	}


	public String getDeleteReason() {
		return deleteReason;
	}


	public String getUpdatedMenuName() {
		return UpdatedMenuName;
	}
	

}
