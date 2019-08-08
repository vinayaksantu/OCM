package com.tetherfi.model.fax;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.Map;

public class FaxLineConfigDetails {
    private String faxLine;
    private String prefix;
    private String faxLineName;
    private String description;
    private String status;
    private String sendStatus;
    private String receiveStatus;
    private String modifyReason;
    private String deleteReason;
    private String updatedFaxLine;
    private String updatedSendStatus;
    private String updatedReceiveStatus;
    private String updatedStatus;
    private String updatedDescription;
    private String query;

    public FaxLineConfigDetails(Map<String,String> map) {
        faxLine=readFaxline(map);
        prefix=readPrefix(map);
        faxLineName=readFaxLineName(map);
        description=readDescription(map);
        status=readStatus(map);
        sendStatus=readSendStatus(map);
        receiveStatus=readReceiveStatus(map);
        deleteReason=readDeleteReason(map);
        updatedFaxLine=readUpdatedFaxLine(map);
        updatedStatus=readUpdatedStatus(map);
        updatedSendStatus=readUpdatedSendStatus(map);
        updatedDescription=readUpdatedDescription(map);
        updatedReceiveStatus=readUpdatedReceiveStatus(map);
        modifyReason=readModifyReason(map);
        query=readQuery(map);
    }

    private String readQuery(Map<String, String> map) {
    	String value=map.get("Query");
    	return value;
	}

	private String readUpdatedReceiveStatus(Map<String, String> map) {
    	String value=map.get("Updated ReceiveStatus");
    	return value;
	}

	private String readUpdatedDescription(Map<String, String> map) {
		String value=map.get("Updated Description");
		return value;
	}

	private String readUpdatedSendStatus(Map<String, String> map) {
		String value=map.get("Updated SendStatus");
		return value;
	}

	private String readUpdatedStatus(Map<String, String> map) {
		String value=map.get("Updated Status");		
		return value;
	}

	private String readUpdatedFaxLine(Map<String, String> map) {
		String value=map.get("Updated FaxLine");
		return value;
	}

	public String readFaxline(Map<String,String> map){
        String value=map.get("FaxLine");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readPrefix(Map<String,String> map){
        String value=map.get("Prefix");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readFaxLineName(Map<String,String> map){
        String value=map.get("FaxLineName");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readDescription(Map<String,String> map){
        String value=map.get("Description");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readStatus(Map<String,String> map){
        String value=map.get("Status");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readSendStatus(Map<String,String> map){
        String value=map.get("SendStatus");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readReceiveStatus(Map<String,String> map){
        String value=map.get("ReceiveStatus");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
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
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getFaxLine() {
        return faxLine;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getFaxLineName() {
        return faxLineName;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {

        return deleteReason;
    }

	public String getUpdatedFaxLineName() {
		return updatedFaxLine;
	}

	public String getUpdatedDescription() {
		return updatedDescription;
	}

	public String getUpdatedStatus() {
		return updatedStatus;
	}

	public String getUpdatedSendStatus() {
		return updatedSendStatus;
	}

	public String getUpdatedReceiveStatus() {
		return updatedReceiveStatus;
	}

	public String getQuery() {
		return query;
	}
}
