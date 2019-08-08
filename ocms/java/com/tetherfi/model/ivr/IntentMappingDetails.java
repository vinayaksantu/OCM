package com.tetherfi.model.ivr;

import java.util.Map;

public class IntentMappingDetails {
	private String VDN;
	private String query;
	private String UpdatedVDN;
	private String DeleteReason;
	private String ModifyReason;
	private String Product;
	private String Segment;
	private String Language;
	private String IntentTalent;

	public IntentMappingDetails(Map<String, String> map) {
		VDN=readVDN(map);
		query=readQuery(map);
		UpdatedVDN=readUpdatedVDN(map);
		DeleteReason=readDeleteReason(map);
		ModifyReason=readModifyReason(map);
		Product=readProduct(map);
		Segment=readSegment(map);
		Language=readLanguage(map);
		IntentTalent=readIntentTalent(map);	
	}

	private String readVDN(Map<String, String> map) {
		String value=map.get("VDN");
		return value;
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readUpdatedVDN(Map<String, String> map) {
		String value=map.get("Updated VDN");
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

	private String readProduct(Map<String, String> map) {
		String value=map.get("Product");
		return value;
	}

	private String readSegment(Map<String, String> map) {
		String value=map.get("Segment");
		return value;
	}

	private String readLanguage(Map<String, String> map) {
		String value=map.get("Language");
		return value;
	}

	private String readIntentTalent(Map<String, String> map) {
		String value=map.get("Intent Talent");
		return value;
	}

	public String getVDN() {
		return VDN;
	}

	public String getQuery() {
		return query;
	}

	public String getModifyReason() {
		return ModifyReason;
	}

	public String getDeleteReason() {
		return DeleteReason;
	}

	public String getUpdatedVDN() {
		return UpdatedVDN;
	}

	public String getProduct() {
		return Product;
	}

	public String getSegment() {
		return Segment;
	}

	public String getLanguage() {
		return Language;
	}

	public String getIntentTalent() {
		return IntentTalent;
	}

}
