package com.tetherfi.model.ivr;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class IntroMessageAnnouncementDetails {
    private String functionality;
    private String language;
    private String status;
    private String interrupt;
    private String startDateTime;
    private String endDateTime;
    private String modifyReason;
    private String deleteReason;
    private String Query;
    private String HotLine;
    private String Wavefile;

    public IntroMessageAnnouncementDetails(Map<String,String> map){
        functionality=readFunctionality(map);
        language=readLanguage(map);
        status=readStatus(map);
        interrupt=readInterrupt(map);
        startDateTime=readStartDateTime(map);
        endDateTime=readEndDateTime(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        Query=readQuery(map);
        HotLine=readHotLine(map);
        Wavefile=readWaveFile(map);
        		
    }

    private String readWaveFile(Map<String, String> map) {
    	String value=map.get("Wave File");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}

	private String readHotLine(Map<String, String> map) {
    	String value=map.get("HotLine");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}

	private String readQuery(Map<String, String> map) {
    	String value=map.get("Query");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}

	private String readEndDateTime(Map<String, String> map) {
        String value=map.get("End Date Time");
        if(value==null||value.equalsIgnoreCase("random.str")){
            Date dNow = new Date( );
            Calendar c = Calendar.getInstance();
            c.setTime(dNow);
            c.add(Calendar.DATE,5);//adding 5 days
            SimpleDateFormat ft =new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
            value=ft.format(c.getTime());
        }
        return value;}

    private String readStartDateTime(Map<String, String> map) {
        String value=map.get("Start Date Time");
        if(value==null||value.equalsIgnoreCase("random.str")){
            Date dNow = new Date( );
            Calendar c = Calendar.getInstance();
            c.setTime(dNow);
            c.add(Calendar.HOUR,1);
            SimpleDateFormat ft =new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
            value=ft.format(c.getTime());
        }
        return value;}

    private String readInterrupt(Map<String,String> map) {
        String value=map.get("Interrupt");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readStatus(Map<String, String> map) {
        String value=map.get("Status");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    private String readLanguage(Map<String, String> map) {
        String value=map.get("Language");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readFunctionality(Map<String, String> map) {
        String value=map.get("Functionality");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
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

    public String getFunctionality() {
        return functionality;
    }

    public String getLanguage() {
        return language;
    }

    public String getStatus() {
        return status;
    }

    public String getInterrupt() {
        return interrupt;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

	public String getQuery() {
		return Query;
	}

	public String getHotLine() {
		return HotLine;
	}

	public String getWavFile() {
		return Wavefile;
	}
}
