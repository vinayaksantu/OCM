package com.tetherfi.model.ivr;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class IvrConfigDetails {

    private String searchColumn;
    private String searchValue;
    private String parameter;
    private String parametervalue;
    private String modifyReason;
    private String deleteReason;

    public IvrConfigDetails(Map<String,String> map){
        searchColumn=readSearchColumn(map);
        searchValue=readSearchValue(map);
        parameter=readParameter(map);
        parametervalue=readValue(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
    }
    private String readSearchValue(Map<String, String> map) {
        String value=map.get("Search Value");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    private String readSearchColumn(Map<String, String> map) {
        String value=map.get("Search Column");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
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

    private String readValue(Map<String, String> map) {
        String value=map.get("Value");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    private String readParameter(Map<String, String> map) {
        String value=map.get("Parameter");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String getParameter() {
        return parameter;
    }

    public String getValue() {
        return parametervalue;
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
