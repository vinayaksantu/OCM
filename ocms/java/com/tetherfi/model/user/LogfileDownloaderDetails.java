package com.tetherfi.model.user;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class LogfileDownloaderDetails {

    private String module;
    private String folder;
    private String filename;

    public LogfileDownloaderDetails(Map<String,String> map){
        module=readModule(map);
        folder=readFolder(map);
        filename=readFilename(map);
    }

    private String readModule(Map<String, String> map) {
        String value=map.get("Module");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= "";
        }
        return value;
    }
    private String readFolder(Map<String, String> map) {
        String value=map.get("Folder");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= "";
        }
        return value;
    }
    private String readFilename(Map<String, String> map) {
        String value=map.get("Filename");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getModule() {
        return module;
    }

    public String getFolder() {
        return folder;
    }

    public String getFilename() {
        return filename;
    }
}
