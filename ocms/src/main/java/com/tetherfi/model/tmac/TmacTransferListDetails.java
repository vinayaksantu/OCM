package com.tetherfi.model.tmac;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class TmacTransferListDetails {
    private String name;
    private String agentExtension;
    private String type;
    private String deleteReason;
    private String skillName;
    private String vdn;
    private String skillNameUpdate;
    private String experrmsg;
    private String updName;
    private String updAgtExt;
    private String updType;
    private String modReason;
    private String query;
    private String experrmsg2;
    private String updateVdn;
    private int incAgtExt;

    public TmacTransferListDetails(Map<String, String> map) {
        name=readName(map);
        agentExtension=readAgentExtension(map);
        type=readType(map);
        deleteReason=readDeleteReason(map);
        skillName=readSkillName(map);
        vdn=readVdn(map);
        skillNameUpdate=readSkillNameUpdate(map);
        experrmsg=readExpErrMsg(map);
        updName=readUpdName(map);
        updAgtExt=readUpdAgtExt(map);
        updType=readUpdType(map);
        modReason=readModReason(map);
        query=readQuery(map);
        experrmsg2=readExpErrMsg2(map);
        updateVdn=readUpdVdn(map);
        incAgtExt=readIncAgtExt(map);
    }
    
    private int readIncAgtExt(Map<String, String> map) {
    	int value=0;
    	try {
    		value=Integer.parseInt(map.get("Inc Agt Ext"));
    	}catch(Exception e) {
    		value=0;
    	}
		return value;
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}
    private String readSkillNameUpdate(Map<String, String> map) {
    	String value=map.get("Update Skill Name");
		return value;
	}
	public String readName(Map<String,String> map){
        String value=map.get("Name");
        return value;
    }
    public String readAgentExtension(Map<String,String> map){
        String value=map.get("agent extension");
        return value;
    }
    public String readType(Map<String,String> map){
        String value=map.get("type");
        return value;
    }
    public String readExpErrMsg(Map<String,String> map){
        String value=map.get("Exp Err Msg");
        return value;
    }
    public String readExpErrMsg2(Map<String,String> map){
        String value=map.get("Exp Err Msg 2");
        return value;
    }
    public String readDeleteReason(Map<String,String> map){
        String value=map.get("Del Reason");
        return value;
    }
    
    public String readSkillName(Map<String,String> map){
        String value=map.get("Skill Name");
        return value;
    }
    public String readVdn(Map<String,String> map){
        String value=map.get("VDN");
        return value;
    }
    public String readUpdVdn(Map<String,String> map){
        String value=map.get("Update VDN");
        return value;
    }
    public String readUpdName(Map<String,String> map){
    	String value=map.get("Update Name");
        return value;
    }
    public String readUpdAgtExt(Map<String,String> map){
    	String value=map.get("Update Agt Ext");
        return value;
    }
    public String readUpdType(Map<String,String> map){
    	String value=map.get("Update Type");
        return value;
    }
    public String readModReason(Map<String,String> map){
    	String value=map.get("Mod Reason");
        return value;
    }
    
    public String getName(){return name;}
    public String getAgentExtension(){return agentExtension;}
    public String getType(){return type;}
    public String getDeleteReason(){return deleteReason;}
    public String getSkillName(){return skillName;}
    public String getVdn(){return vdn;}
	public String getSkillNameUpdate() { return skillNameUpdate; }
	public String getExpErrMsg() { return experrmsg; }
	public String getUpdName() { return updName; }
	public String getUpdAgtExt() { return updAgtExt; }
	public String getUpdType() { return updType; }
	public String getModReason() { return modReason; }
	public String getQuery() {return query;}
	public String getExpErrMsg2() { return experrmsg2; }
	public String getUpdVdn(){return updateVdn;}
	public int getIncAgtExt(){return incAgtExt;}
}