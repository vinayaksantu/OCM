package com.tetherfi.model.ivr;

import java.util.Map;

public class AgentTransferDetails {
	private String menuId;
	private String vdn;
	private String vipVdn;
	private String option;
	private String DNIS;
	private String VdnDescription;
	private String ModifyReason;
	private String updatedVdnDescription;
	private String DeleteReason;
	private String query;


	public AgentTransferDetails(Map<String, String> map) {
		menuId=readMenuID(map);
		vdn=readVdn(map);
		vipVdn=readVipVdn(map);
		option=readOption(map);
		DNIS=readDNIS(map);
		VdnDescription=readVdnDescription(map);
		ModifyReason=readModifyReason(map);
		updatedVdnDescription=readUpdatedVdnDescription(map);
		DeleteReason=readDeleteReason(map);
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

	private String readUpdatedVdnDescription(Map<String, String> map) {
		String value=map.get("Updated VDNDescription");
		return value;
	}

	private String readModifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}

	private String readVdn(Map<String, String> map) {
		String value=map.get("VDN");
		return value;
	}

	private String readVdnDescription(Map<String, String> map) {
		String value=map.get("VDN Description");
		return value;
	}

	private String readDNIS(Map<String, String> map) {
		String value=map.get("DNIS");
		return value;
	}

	private String readOption(Map<String, String> map) {
		String value=map.get("Option");
		return value;
	}

	private String readVipVdn(Map<String, String> map) {
		String value=map.get("VIP VDN");
		return value;
	}

	
	private String readMenuID(Map<String, String> map) {
		String value=map.get("Menu Id");
		return value;
	}

	public String getMenuId() {
		return menuId;
	}

	public String getVdn() {
		return vdn;
	}

	public String getVipVdn() {
		return vipVdn;
	}

	public String getOption() {
		return option;
	}

	public String getDNIS() {
		return DNIS;
	}
	
	public String getVdnDescription() {
		return VdnDescription;
	}

	public String getModifyReason() {
		return ModifyReason;
	}

	public String getUpdateVdnDescription() {
		return updatedVdnDescription;
	}

	public String getDeleteReason() {
		return DeleteReason;
	}

	public String getQuery() {
		return query;
	}
}
