package com.tetherfi.model.email;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

public class EmailTemplatesDetails {

	    
	    private String templateName;
	    private String enabled;
	    private String text;
	    private String type;
	    private String modifyReason;
	    private  String deleteReason;
	    private String UpdatedSubject;
	    private String Updatedtext;
	    private String Query;
	    private String Subject;
	   

	    public EmailTemplatesDetails(Map<String,String> map){
	        
	        templateName=readTemplateName(map);	        
	        enabled=readEnabled(map);
	        text=readText(map);
	        type=readType(map);
	        modifyReason=readModifyReason(map);
	        deleteReason=readDeleteReason(map);
	        UpdatedSubject=readUpdatedsubject(map);
	        Updatedtext=readUpdatedtext(map);
	        Query=readQuery(map);
	        Subject=readSubject(map);	        
	    }

	   	private String readSubject(Map<String, String> map) {
	    	String value=map.get("Subject");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(20);
	        }
	        return value;
		}

		private String readQuery(Map<String, String> map) {
	    	String value=map.get("Query");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
		}

		private String readUpdatedsubject(Map<String, String> map) {
	    	String value=map.get("Updated Subject");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(20);
	        }
	        return value;
		}

		private String readUpdatedtext(Map<String, String> map) {
	    	String value=map.get("Updated Text");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(20);
	        }
	        return value;
		}

	    
	    private String readTemplateName(Map<String, String> map) {
	        String value=map.get("Template Name");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
	    }

	    private String readEnabled(Map<String, String> map) {
	        String value=map.get("Enabled");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
	    }

	    private String readText(Map<String, String> map) {
	        String value=map.get("Text");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(10);
	        }
	        return value;
	    }

	    private String readType(Map<String, String> map) {
	        String value=map.get("Type");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value= RandomStringUtils.randomAlphabetic(7);
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

	       
	    public String getTemplateName() {
	        return templateName;
	    }

	    public String getEnabled() {
	        return enabled;
	    }

	    public String getText() {
	        return text;
	    }

	    public String getModifyReason() {
	        return modifyReason;
	    }

	    public String getDeleteReason() {
	        return deleteReason;
	    }
			
		public String getUpdatedSubject() {
			return UpdatedSubject;
		}

		public String getUpdatedtext() {
			return Updatedtext;
		}

		public String getQuery() {
			return Query;
		}

		public String getSubject() {
			return Subject;
		}

		public String getType() {
			return type;
		}
	

}

