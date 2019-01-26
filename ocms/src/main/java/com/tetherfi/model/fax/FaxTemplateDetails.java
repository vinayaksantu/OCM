package com.tetherfi.model.fax;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.Map;

public class FaxTemplateDetails {
    private String templateName;
    private String custom;
    private String uploadfiles;
    private String logoOrHtml;
    private String filename;
    private String body;
    private String modifyReason;
    private String deleteReason;

    public FaxTemplateDetails(Map<String,String> map) {
        templateName=readTemplateName(map);
        custom=readCustom(map);
        uploadfiles=readUploadFiles(map);
        logoOrHtml=readLogoOrHtml(map);
        filename=readFileName(map);
        body=readBody(map);
        deleteReason=readDeleteReason(map);
        modifyReason=readModifyReason(map);
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

    public String readUploadFiles(Map<String,String> map){
        String value=map.get("Upload Files");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readLogoOrHtml(Map<String,String> map){
        String value=map.get("Logo/HTML");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readFileName(Map<String,String> map){
        String value=map.get("Upload FileName");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readBody(Map<String,String> map){
        String value=map.get("Body");
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
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getModifyReason() {
        return modifyReason;
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

    public String getUploadfiles() {
        return uploadfiles;
    }

    public String getLogoOrHtml() {
        return logoOrHtml;
    }

    public String getFilename() {
        return filename;
    }

    public String getBody() {
        return body;
    }
}
