package com.tetherfi.model.ivr;

import java.util.Map;

public class HolidayListDetails {
	private String vdn;
    private String announcedHoliday;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String modifyReason;
    private String deleteReason;
    private String query;
    private String updatedAnnouncedHoliday;
    
    public HolidayListDetails(Map<String,String> map){
        vdn=readVdn(map);
        announcedHoliday=readAnnouncedHoliday(map);
        startDate=readStartDate(map);
        endDate=readEndDate(map);
        startTime=readStartTime(map);
        endTime=readEndTime(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        query=readQuery(map);
        updatedAnnouncedHoliday=readUpdatedAnnouncedHoliday(map);
    }

	private String readUpdatedAnnouncedHoliday(Map<String, String> map) {
		 String value=map.get("Updated Announced Holiday");
			return value;
	}

	private String readQuery(Map<String, String> map) {
		 String value=map.get("Query");
		return value;
	}

	private String readEndDate(Map<String, String> map) {
		 String value=map.get("End Date");
	     return value;
	}

	private String readStartDate(Map<String, String> map) {
		String value=map.get("Start Date");
	     return value;
	}

	private String readAnnouncedHoliday(Map<String, String> map) {
		String value=map.get("Announced Holiday");
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

	private String readEndTime(Map<String, String> map) {
		String value=map.get("End Time");
	     return value;
	}

	private String readStartTime(Map<String, String> map) {
		String value=map.get("Start Time");
	     return value;
	}

	private String readVdn(Map<String, String> map) {
		String value=map.get("VDN");
	     return value;
	}
	
	public String getVdn() {
        return vdn;
    }
    public String getAnnouncedHoliday(){return announcedHoliday;}
    public String getStartTime(){return startTime;}
    public String getEndTime(){return endTime;}
    public String getStartDate(){return startDate;}
    public String getEndDate(){return endDate;}
    public String getModifyReason(){return modifyReason;}
    public String getDeleteReason(){return deleteReason;}

	public String getQuery() {
		return query;
	}

	public String getUpdatedAnnouncedHoliday() {
		return updatedAnnouncedHoliday;
	}
	

}
