package com.tetherfi.utility;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReader {
    String filename;

    public JSONReader(String filename) {
        this.filename = filename;
        removeCommentsFromJsonFile(filename);
    }

    private void removeCommentsFromJsonFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            String dataline = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("//")) {
                    String comment = line.split("//")[1];
                    String content = line.replace("//" + comment, "");
                    line = content;
                }
                dataline += line + "\n";
            }
            FileWriter writer = new FileWriter(file);
            writer.write(dataline);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not found");
        } catch (IOException e) {
            System.out.println("File IO exception");
        }
    }
    public boolean getJsonMakerCheckerKeyData() {
        boolean status=false;
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(filename));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);
            JsonNode mkrckrNode = rootNode.path("MakerChecker");
            status=mkrckrNode.asBoolean();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    public Map<String,String> getJsonGridColumnTitleKeyData(String key) {
        Map<String,String> map=new HashMap<>();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(filename));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);
            JsonNode gridColumnNode = rootNode.path("GridColumns");
            Iterator<JsonNode> elements = gridColumnNode.elements();
            while(elements.hasNext()) {
                JsonNode column = elements.next();
                map.put(column.path("Title").asText(),column.path(key).asText());
            }
           // System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
