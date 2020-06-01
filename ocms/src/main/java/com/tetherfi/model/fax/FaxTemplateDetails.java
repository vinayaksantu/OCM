package com.tetherfi.model.fax;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.Map;

public class FaxTemplateDetails {
    private String templateName;
    private String templateType;
    private String custom;
    private String updatedFileName;
    private String content;
    private String updatedContent;
    private String Html;
    private String filename;
    private String body;
    private String modifyReason;
    private String deleteReason;
    private String logo;
    private String uploadDoc;
    private String updatedBody;
    private String updatedCustom;
    private String updatedHtml;
    private String updatedLogo;
    private String updatedUploadDoc;
    private String query;
    private String pdfFile;
    
    public FaxTemplateDetails(Map<String,String> map) {
        templateName=readTemplateName(map);
        templateType=readTemplateType(map);
        custom=readCustom(map);
        Html=readHtml(map);
        content=readContent(map);
        updatedContent=readUpdatedContent(map);
        filename=readFileName(map);
        body=readBody(map);
        deleteReason=readDeleteReason(map);
        modifyReason=readModifyReason(map);
        logo=readLogo(map);
        uploadDoc=readUploadDoc(map);
        updatedBody=readUpdatedBody(map);
        updatedFileName=readUpdatedFileName(map);
        updatedCustom=readUpdatedCustom(map);
        updatedHtml=readUpdatedHtml(map);
        updatedLogo=readUpdatedLogo(map);
        updatedUploadDoc=readUpdatedUploadDoc(map);
        query=readQuery(map);
        pdfFile=readPdfFile(map);
    }

    private String readPdfFile(Map<String, String> map) {
    	String value=map.get("PDF File");
		return value;
	}

	private String readTemplateType(Map<String, String> map) {
		String value=map.get("Template Type");
		if(value==null){
            value= "santu";}
		return value;
	}

	private String readContent(Map<String, String> map) {
		String value=map.get("Content");
		return value;
	}
	
	private String readUpdatedContent(Map<String, String>map) {
		String value=map.get("UpdatedContent");
		return value;
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readUpdatedUploadDoc(Map<String, String> map) {
    	String value=map.get("Updated UploadDoc");
    	return value;
	}

	private String readUpdatedLogo(Map<String, String> map) {
		String value=map.get("Updated Logo");
		return value;
	}

	private String readUpdatedHtml(Map<String, String> map) {
		String value=map.get("Updated Html");
		return value;
	}

	private String readUpdatedCustom(Map<String, String> map) {
    	String value=map.get("Updated Custom");
    	return value;
	}

	private String readUpdatedFileName(Map<String, String> map) {
    	 String value=map.get("Updated Upload FileName");
         if(value==null){
             value= "";}
         return value;

	}

	private String readUpdatedBody(Map<String, String> map) {
    	 String value=map.get("Updated Body");
         if(value==null){
             value= "";}
         return value;
	}

	private String readUploadDoc(Map<String, String> map) {
        String value=map.get("UploadDoc");
		return value;
	}

	private String readLogo(Map<String, String> map) {
        String value=map.get("Logo");
		return value;
	}

	private String readTemplateName(Map<String, String> map) {
        String value=map.get("Template Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readCustom(Map<String,String> map){
        String value=map.get("Custom");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readHtml(Map<String,String> map){
        String value=map.get("Html");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readFileName(Map<String,String> map){
        String value=map.get("FileName");  
        if(value==null){
            value= "";}
        return value;
    }
    public String readBody(Map<String,String> map){
        String value=map.get("Body");
        if(value==null){
            value= "";}
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

    public String getModifyReason() {
        return modifyReason;
    }
    
    public String getTemplatetype() {
    	return templateType;
    }

    public String getDeleteReason() {

        return deleteReason;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getCustom() {
        return custom;
    }

    public String getHtml() {
        return Html;
    }
    
    public String getContent() {
    	return content;
    }
    
    public String getUpdatedContent() {
    	return updatedContent;
    }

    public String getFilename() {
        return filename;
    }

    public String getBody() {
        return body;
    }

	public String getLogo() {
		return logo;
	}

	public String getUploadDoc() {
		return uploadDoc;
	}

	public String getUpdatedBody() {
		return updatedBody;
	}

	public String getUpdatedFilename() {
		return updatedFileName;
	}

	public String getUpdatedCustom() {
		return updatedCustom;
	}

	public String getUpdatedHtml() {
		return updatedHtml;
	}

	public String getUpdatedLogo() {
		return updatedLogo;
	}
	
	public String getUpdatedUploadDoc() {
		return updatedUploadDoc;
	}

	public String getQuery() {
		return query;
	}
	
	public String getPDFFile() {
		return pdfFile;
	}
}

