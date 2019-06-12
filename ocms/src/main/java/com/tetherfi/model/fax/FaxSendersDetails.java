package com.tetherfi.model.fax;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class FaxSendersDetails {
    private String faxLine;
    private String name;
    private String faxNumber;
    private String senderType;
    private String modifyReason;
    private String deleteReason;
    private String updatedName;
    private String updatedSenderType;
    private String query;

    public FaxSendersDetails(Map<String,String> map) {
        faxLine=readFaxline(map);
        name=readName(map);
        faxNumber=readFaxNumber(map);
        senderType=readSenderType(map);
        deleteReason=readDeleteReason(map);
        modifyReason=readModifyReason(map);
        updatedName=readUpdatedName(map);
        updatedSenderType=readUpdatedSenderType(map);
        query=readQuery(map);
    }

    private String readQuery(Map<String, String> map) {
    	String value=map.get("Query");
		return value;
	}

	private String readUpdatedSenderType(Map<String, String> map) {
    	String value=map.get("Updated SenderType");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
	}

	private String readUpdatedName(Map<String, String> map) {
		String value=map.get("Updated Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
	}

	public String readFaxline(Map<String,String> map){
        String value=map.get("FaxLine");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readName(Map<String,String> map){
        String value=map.get("Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readFaxNumber(Map<String,String> map){
        String value=map.get("FaxNumber");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readSenderType(Map<String,String> map){
        String value=map.get("SenderType");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }



    public String readModifyReason(Map<String,String> map){
        String value=map.get("Modify Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String readDeleteReason(Map<String,String> map){
        String value=map.get("Delete Reason");
        return value;
    }

    public String getFaxLine() {
        return faxLine;
    }

    public String getName() {
        return name;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getSenderType() {
        return senderType;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {

        return deleteReason;
    }

	public String getUpdatedName() {
		return updatedName;
	}

	public String getUpdatedSenderType() {
		return updatedSenderType;
	}

	public String getQuery() {
		return query;
	}
}

