package com.tetherfi.model.ivr;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class AdhocOptionEnhancementDetails {

    private String searchColumn;
    private String searchValue;
    private String promotionalNumber;
    private String promotionalDescription;
    private String language;
    private String directTransferEnabled;
    private String promotionNameWavFile;
    private String promotionDetailsWavFile;
    private String intent;
    private String status;
    private String modifyReason;
    private String deleteReason;

    public AdhocOptionEnhancementDetails(Map<String, String> map){
        searchColumn=readSearchColumn(map);
        searchValue=readSearchValue(map);
    promotionalNumber=readPromotionalNumber(map);
    promotionalDescription=readPromotionalDescription(map);
    language=readLanguage(map);
    directTransferEnabled=readDirectTransferEnabled(map);
    promotionNameWavFile=readPromotionNameWavFile(map);
        promotionDetailsWavFile=readPromotiondetailsWavFile(map);
        intent=readIntent(map);
        status=readStatus(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
    }
    private String readSearchValue(Map<String, String> map) {
        String value=map.get("Search Value");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;
    }
    private String readSearchColumn(Map<String, String> map) {
        String value=map.get("Search Column");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;
    }
    private String readStatus(Map<String, String> map) {
        String value=map.get("Status");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;}

    private String readIntent(Map<String, String> map) {
        String value=map.get("Intent");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;}

    private String readPromotiondetailsWavFile(Map<String, String> map) {
        String value=map.get("Promotion Details Wav File");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;}

    private String readPromotionNameWavFile(Map<String, String> map) {
        String value=map.get("Promotion Name Wav File");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;
    }

    private String readDirectTransferEnabled(Map<String, String> map) {
        String value=map.get("Direct Transfer Enabled");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;
    }

    private String readLanguage(Map<String, String> map) {
        String value=map.get("Language");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;
    }

    private String readPromotionalDescription(Map<String, String> map) {
        String value=map.get("Promotion Description");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;
    }

    private String readPromotionalNumber(Map<String, String> map) {
        String value=map.get("Promotion Number");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;
    }

    private String readDeleteReason(Map<String, String> map) {
        String value=map.get("Delete Reason");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;
    }

    private String readModifyReason(Map<String, String> map) {
        String value=map.get("Modify Reason");
        try{if(value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }}catch (NullPointerException e){value="";}
        return value;
    }

    public String getPromotionalNumber() {
        return promotionalNumber;
    }

    public String getPromotionalDescription() {
        return promotionalDescription;
    }

    public String getLanguage() {
        return language;
    }

    public String getDirectTransferEnabled() {
        return directTransferEnabled;
    }

    public String getPromotionNameWavFile() {
        return promotionNameWavFile;
    }

    public String getPromotionDetailsWavFile() {
        return promotionDetailsWavFile;
    }

    public String getIntent() {
        return intent;
    }

    public String getStatus() {
        return status;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public String getSearchColumn() {
        return searchColumn;
    }

    public String getSearchValue() {
        return searchValue;
    }
}
