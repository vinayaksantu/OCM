package com.tetherfi.model.user;

import java.util.Map;

public class CepEventMappingDetails {
	String cepEvent;
	String description;
	String ProductType;
	String Bnis;
	String TransferFlag;
	String Intent;
	String UpdatedDescription;
	String modifyReason;
	String query;
	String deleteReason;

	public CepEventMappingDetails(Map<String, String> map) {
		cepEvent =readCepEvent(map);
		description=readDescription(map);
		ProductType=readProductType(map);
		Bnis=readBnis(map);
		TransferFlag=readTransferFlag(map);
		Intent=readIntent(map);
		UpdatedDescription=readUpdatedDescription(map);
		modifyReason=readModifyReason(map);
		query=readQuery(map);
		deleteReason=readDeleteReason(map);
		
	}

	private String readDeleteReason(Map<String, String> map) {
		String value=map.get("Delete Reason");
		return value;
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readModifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}

	private String readUpdatedDescription(Map<String, String> map) {
		String value=map.get("Updated Description");
		return value;
	}

	private String readIntent(Map<String, String> map) {
		String value=map.get("Intent");
		return value;
	}

	private String readTransferFlag(Map<String, String> map) {
		String value=map.get("Transfer Flag");
		return value;
	}

	private String readBnis(Map<String, String> map) {
		String value=map.get("BNIS");
		return value;
	}

	private String readDescription(Map<String, String> map) {
		String value=map.get("Description");
		return value;
	}

	private String readProductType(Map<String, String> map) {
		String value=map.get("Product Code");
		return value;
	}

	private String readCepEvent(Map<String, String> map) {
		String value=map.get("Cep Event");
				return value;
	}

	public String getCepEvent() {
		return cepEvent;
	}

	public String getDescription() {
		return description;
	}

	public String getProductType() {
		return ProductType;
	}

	public String getBins() {
		return Bnis;
	}

	public String getTransferFlag() {
		return TransferFlag;
	}

	public String getIntent() {
		return Intent;
	}

	public String getUpdatedDescription() {
		return UpdatedDescription;
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
