package com.tetherfi.model.ivr;

import java.util.Map;

public class MenuDescriptionMappingDetails {
	private String MenuID;
	private String MenuName;
	private String Intent;
	private String ModifyReason;
	private String DeleteReason;
	private String query;
	private String UpdatedMenuName;
	
	public MenuDescriptionMappingDetails(Map<String, String> map) {
		MenuID=readMenuID(map);
		MenuName=readMenuName(map);
		Intent=readIntent(map);
		ModifyReason=readModifyReason(map);
		DeleteReason=readDeleteReaosn(map);
		query=readQuery(map);
		UpdatedMenuName=readUpdatedMenuName(map);
	}

	private String readUpdatedMenuName(Map<String, String> map) {
		String value=map.get("Updated MenuName");
		return value;
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readMenuID(Map<String, String> map) {
		String value=map.get("Menu ID");
		return value;
	}

	private String readMenuName(Map<String, String> map) {
		String value=map.get("Menu Name");
		return value;
	}

	private String readIntent(Map<String, String> map) {
		String value=map.get("Intent");
		return value;
	}

	private String readModifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}

	private String readDeleteReaosn(Map<String, String> map) {
		String value=map.get("Delete Reason");
		return value;
	}

	public String getMenuID() {
		return MenuID;
	}

	public String getQuery() {
		return query;
	}

	public String getMenuName() {
		return MenuName;
	}

	public String getIntent() {
		return Intent;
	}

	public String getUpdatedMenuName() {
		return UpdatedMenuName;
	}

	public String getModifyReason() {
		return ModifyReason;
	}

	public String getDeleteReason() {
		return DeleteReason;
	}

}
