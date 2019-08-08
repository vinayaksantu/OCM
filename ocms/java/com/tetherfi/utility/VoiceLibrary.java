package com.tetherfi.utility;

import java.io.IOException;

public class VoiceLibrary {

    private String voiceLibraryTester = System.getProperty("user.dir") + "\\src\\test\\resources\\VoiceLibrary\\tsapi_phone\\VoiceLibraryTester.exe";
    private String loginAES = System.getProperty("user.dir") + "\\src\\test\\resources\\VoiceLibrary\\AutoIT\\LoginAES.exe";
    private String loginAESClick = System.getProperty("user.dir") + "\\src\\test\\resources\\VoiceLibrary\\AutoIT\\LoginAESClick.exe";
    private String loginCallingStation = System.getProperty("user.dir") + "\\src\\test\\resources\\VoiceLibrary\\AutoIT\\LoginCallingStation.exe";
    private String loginCallingStationClick = System.getProperty("user.dir") + "\\src\\test\\resources\\VoiceLibrary\\AutoIT\\LoginCallingStationClick.exe";
    private String clickOkOnSuccess = System.getProperty("user.dir") + "\\src\\test\\resources\\VoiceLibrary\\AutoIT\\ClickOkOnSuccess.exe";
    private String initiateCall = System.getProperty("user.dir") + "\\src\\test\\resources\\VoiceLibrary\\AutoIT\\InitiateCall.exe";
    private String dailList = System.getProperty("user.dir") + "\\src\\test\\resources\\VoiceLibrary\\AutoIT\\ClickOnDialList.exe";
    private String stopDialling = System.getProperty("user.dir") + "\\src\\test\\resources\\VoiceLibrary\\AutoIT\\StopDialling.exe";
    private String logout = System.getProperty("user.dir") + "\\src\\test\\resources\\VoiceLibrary\\AutoIT\\Logout.exe";

    public void open() {
        try {
            Runtime.getRuntime().exec("cmd /c Start /max "+voiceLibraryTester+" /F");
            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void loginAES(String aes, String user, String pass) {
        try {
            Thread.sleep(2000);
            Runtime.getRuntime().exec(loginAES + " " + aes + " " + user + " " + pass);
            Thread.sleep(5000);
            Runtime.getRuntime().exec(loginAESClick);
            Runtime.getRuntime().exec(loginAESClick);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loginCallingStation(String agentid, String station) {
        try {
            Thread.sleep(2000);
            Runtime.getRuntime().exec(loginCallingStation + " " + agentid + " " + station);
            Thread.sleep(5000);
            Runtime.getRuntime().exec(loginCallingStationClick);
            Runtime.getRuntime().exec(loginCallingStationClick);
            Thread.sleep(5000);
            Runtime.getRuntime().exec(clickOkOnSuccess);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initiateCall(String stationorvdn) {
        try {
            Thread.sleep(2000);
            Runtime.getRuntime().exec(initiateCall + " " + stationorvdn);
            Thread.sleep(5000);
            Runtime.getRuntime().exec(dailList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stopDialling() {
        try {
            Runtime.getRuntime().exec(stopDialling);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logout() {
        try {
            Runtime.getRuntime().exec(logout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            Runtime.getRuntime().exec("cmd /c taskkill /IM VoiceLibraryTester.exe /F");
            // Runtime.getRuntime().exec(voiceLibraryTester);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}