package com.tetherfi.model.fax;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class FaxAutoACKConfigurationDetails {

    private String faxLine;
    private String name;
    private String senderType;
    private String status;
    private String template;
    private String modifyReason;
    private String deleteReason;
    private String updatedStatus;
    private String query;

    public FaxAutoACKConfigurationDetails(Map<String, String> map) {
        faxLine = readFaxline(map);
        name = readName(map);
        status = readStatus(map);
        senderType = readSenderType(map);
        template=readTemplate(map);
        deleteReason = readDeleteReason(map);
        modifyReason = readModifyReason(map);
        updatedStatus=readUpdatedStatus(map);
        query=readQuery(map);
    }
    

    private String readQuery(Map<String, String> map) {
    	 String value = map.get("Query");
 		return value;
	}


	private String readUpdatedStatus(Map<String, String> map) {
        String value = map.get("Updated Status");
		return value;
	}

	public String readFaxline(Map<String, String> map) {
        String value = map.get("FaxLine");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readName(Map<String, String> map) {
        String value = map.get("Name");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readStatus(Map<String, String> map) {
        String value = map.get("Status");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readTemplate(Map<String, String> map) {
        String value = map.get("Template");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readSenderType(Map<String, String> map) {
        String value = map.get("Sender Type");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readModifyReason(Map<String, String> map) {
        String value = map.get("Modify Reason");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String readDeleteReason(Map<String, String> map) {
        String value = map.get("Delete Reason");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getFaxLine() {
        return faxLine;
    }

    public String getName() {
        return name;
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

    public String getStatus() {
        return status;
    }

    public String getTemplate() {
        return template;
    }

	public String getUpdatedStatus() {
		return updatedStatus;
	}

	public String getQuery() {
		return query;
	}
}