package com.tetherfi.model.chat;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class ChatIntentSkillMappingDetails {
    private String skill;
    private String intent;
    private String language;
    private String segment;
    private String subSegment;
    private String channel;
    private String custEntType;
    private String skillTimeOut;
    private String sla;
    private String modifyReason;
    private  String deleteReason;
    private String query;
    private String UpdatedSubSegment;
    private String UpdatedSegment;

    public ChatIntentSkillMappingDetails(Map<String,String> map){
        skill=readSkill(map);
        intent=readIntent(map);
        language=readLanguage(map);
        segment=readSegment(map);
        subSegment=readSubSegment(map);
        channel=readChannel(map);
        custEntType=readCustEntType(map);
        skillTimeOut=readSkillTimeOut(map);
        sla=readSLA(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        query=readQuery(map);
        UpdatedSubSegment=readUpdatedSubSegment(map);
        UpdatedSegment=readUpdatedSegment(map);
    }

    private String readUpdatedSegment(Map<String, String> map) {
    	 String value=map.get("Updated Segment");
         if(value==null||value.equalsIgnoreCase("random.str")){
             value= RandomStringUtils.randomNumeric(6);
         }
         return value;
	}

	private String readUpdatedSubSegment(Map<String, String> map) {
		 String value=map.get("Updated SubSegment");
         if(value==null||value.equalsIgnoreCase("random.str")){
             value= RandomStringUtils.randomNumeric(6);
         }
         return value;
	}

	private String readQuery(Map<String, String> map) {
    	 String value=map.get("Query");
         if(value==null||value.equalsIgnoreCase("random.str")){
             value= RandomStringUtils.randomNumeric(6);
         }
         return value;
	}

	private String readCustEntType(Map<String, String> map) {
        String value=map.get("CustEntType");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readSkill(Map<String, String> map) {
        String value=map.get("Skill");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readChatVDN(Map<String, String> map) {
        String value=map.get("Chat VDN");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readChannel(Map<String, String> map) {
        String value=map.get("Channel");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;}

    private String readLanguage(Map<String, String> map) {
        String value=map.get("Language");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;}

    private String readSkillTimeOut(Map<String, String> map) {
        String value=map.get("SkillTimeOut");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;}

    private String readSLA(Map<String, String> map) {
        String value=map.get("SLA");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;}

    private String readIntent(Map<String, String> map) {
        String value=map.get("Intent");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;}

    private String readSubSegment(Map<String, String> map) {
        String value=map.get("Sub Segment");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readSegment(Map<String, String> map) {
        String value=map.get("Segment");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }
    public String readModifyReason(Map<String,String> map){
        String value=map.get("Modify Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
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
    public String getSegment() {
        return segment;
    }

    public String getSubSegment() {
        return subSegment;
    }

    public String getIntent() {
        return intent;
    }

    public String getLanguage() {
        return language;
    }

    public String getCustEntType() {
        return custEntType;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public String getSkill() {
        return skill;
    }

    public String getChannel() {
        return channel;
    }
	
	public String getQuery() {
		return query;
	}

	public String getUpdatedSubSegment() {
		return UpdatedSubSegment;
	}

	public String getUpdatedSegment() {
		return UpdatedSegment;
	}
}
