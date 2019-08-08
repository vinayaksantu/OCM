package com.tetherfi.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WebConfigReader {
    String filename;
    public WebConfigReader(String filename){this.filename=filename;}

    public String getKeyValue(String key){
        String value="";
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("<add key=\""+key)) {
                value=line.split("value=\"")[1].split("\"")[0];break;
            }
        }
        return value;
    }
}
