package com.tetherfi.model.tmac;

import java.util.Map;

public class SlotSchedulerDetails {
	
	String slotGroup;
	String slotType;
	String startOfWeek;
	String endOfWeek;
	String fromTime;
	String toTime;
	String monday;
	String tuesday;
	String wednesday;
	String thursday;
	String friday;
	String satuarday;
	String sunday;
	String slotDescription;
	String updatedMonday;
	String updatedThursady;
	String updatedSlotDescription;
	String modifyReason;
	
	
	public SlotSchedulerDetails(Map<String,String>map) {
		slotGroup=readSlotGroup(map);
		slotType=readSlotType(map);
		startOfWeek=readStartOfWeek(map);
		endOfWeek=readEndOfWeek(map);
		fromTime=readFromTime(map);
		toTime=readToTime(map);
		monday=readMonday(map);
		tuesday=readTuesday(map);
		wednesday=readWednesday(map);
		thursday=readThursday(map);
		friday=readFriday(map);
		satuarday=readSatuarday(map);
		sunday=readSunday(map);
		slotDescription=readSlotDescription(map);
		updatedMonday=readUpdatedMonday(map);
		updatedThursady=readUpdatedThursday(map);
		updatedSlotDescription=readUpdatedDescription(map);
		modifyReason=readModifyReason(map);
	}


	


	private String readModifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}





	private String readUpdatedDescription(Map<String, String> map) {
		String value=map.get("Updated Slot Description");
		return value;
	}





	private String readUpdatedThursday(Map<String, String> map) {
		String value=map.get("Updated Thursday");
		return value;
	}





	private String readUpdatedMonday(Map<String, String> map) {
		String value=map.get("Updated Monday");
		return value;
	}





	private String readSlotGroup(Map<String, String> map) {
		String value=map.get("Slot Group");
		return value;
	}

	private String readSlotType(Map<String, String> map) {
		String value=map.get("Slot Type");
		return value;
	}

	private String readStartOfWeek(Map<String, String> map) {
		String value=map.get("Start Of Week");
		return value;
	}

	private String readEndOfWeek(Map<String, String> map) {
		String value=map.get("End Of Week");
		return value;
	}

	private String readFromTime(Map<String, String> map) {
		String value=map.get("From Time");
		return value;
	}


	private String readToTime(Map<String, String> map) {
		String value=map.get("To Time");
		return value;
	}


	private String readMonday(Map<String, String> map) {
		String value=map.get("Monday");
		return value;
	}


	private String readTuesday(Map<String, String> map) {
		String value=map.get("Tuesday");
		return value;
	}


	private String readWednesday(Map<String, String> map) {
		String value=map.get("Wednesday");
		return value;
	}


	private String readThursday(Map<String, String> map) {
		String value=map.get("Thursday");
		return value;
	}


	private String readFriday(Map<String, String> map) {
		String value=map.get("Friday");
		return value;
	}


	private String readSatuarday(Map<String, String> map) {
		String value=map.get("Saturday");
		return value;
	}


	private String readSunday(Map<String, String> map) {
		String value=map.get("Sunday");
		return value;
	}


	private String readSlotDescription(Map<String, String> map) {
		String value=map.get("Slot Description");
		return value;
	}

	public String getSlotGroup() {return slotGroup;}
	public String getSlotType() {return slotType;}
	public String getStartOfWeek() {return startOfWeek;}
	public String getEndOfWeek() {return endOfWeek;}
	public String getFromTime() {return fromTime;}
	public String getTotime() {return toTime;}
	public String getMonday() {return monday;}
	public String getTuesday() {return tuesday;}
	public String getWednesday() {return wednesday;}
	public String getThursady() {return thursday;}
	public String getFriday() {return friday;}
	public String getSatuarday() {return satuarday;}
	public String getSunday() {return sunday;}
	public String getSlotDescription() {return slotDescription;}
	public String getUpdatedMonday() {return updatedMonday;}
	public String getUpdatedThursady() {return updatedThursady;}
	public String getUpdatedSlotDescription() {return updatedSlotDescription;}
	public String getModifyReason() {return modifyReason;}
}