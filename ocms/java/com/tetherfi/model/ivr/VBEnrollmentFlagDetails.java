package com.tetherfi.model.ivr;

import java.util.Map;

public class VBEnrollmentFlagDetails {
	private String DNIS;
	private String EnrollmentFlag;
	private String HotLineName;
	private String ModifyReason;
	private String DeleteReason;
	private String UpdatedHotLineName;
	private String query;

	public VBEnrollmentFlagDetails(Map<String, String> map) {
		DNIS=readDNIS(map);
		EnrollmentFlag=readEnrollmentFlag(map);
		HotLineName=readHotLineName(map);
		ModifyReason=readModifyReaosn(map);
		DeleteReason=readDeleteReason(map);
		UpdatedHotLineName=readUpdatedHotLineName(map);
		query=readQuery(map);
		
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readUpdatedHotLineName(Map<String, String> map) {
		String value=map.get("Updated HotLine Name");
		return value;
	}

	private String readEnrollmentFlag(Map<String, String> map) {
		String value=map.get("Enrollment Flag");
		return value;
	}

	private String readHotLineName(Map<String, String> map) {
		String value=map.get("HotLine Name");
		return value;
	}

	private String readModifyReaosn(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}

	private String readDeleteReason(Map<String, String> map) {
		String value=map.get("Delete Reason");
		return value;
	}

	private String readDNIS(Map<String, String> map) {
		String value=map.get("DNIS");
		return value;
	}

	public String getDNIS() {
		return DNIS;
	}

	public String getHotLineName() {
		return HotLineName;
	}

	public String getEnrollmentFlag() {
		return EnrollmentFlag;
	}

	public String getUpdatedHotLineName() {
		return UpdatedHotLineName;
	}

	public String getModifyReason() {
		return ModifyReason;
	}

	public String getQuery() {
		return query;
	}

	public String getDeleteReason() {
		return DeleteReason;
	}

}
