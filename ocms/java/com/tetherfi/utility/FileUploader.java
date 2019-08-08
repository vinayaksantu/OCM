package com.tetherfi.utility;

import java.io.IOException;

public class FileUploader {

    private String fileUploader=System.getProperty("user.dir") +"\\src\\test\\resources\\FileUpload\\FileUpload.exe";

    public void uploadFile(String file){
        try {
            Thread.sleep(2000);
            Runtime.getRuntime().exec(fileUploader + " " + file);
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
