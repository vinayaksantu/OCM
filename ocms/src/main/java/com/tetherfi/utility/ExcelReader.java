package com.tetherfi.utility;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ExcelReader {
    private String filePath;
    static FileInputStream fileInputStream = null;
    private XSSFWorkbook xssfWorkbook = null;
    private XSSFSheet xssfSheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;


    public ExcelReader(String filePath,String sheetName) throws IOException {

        this.filePath = filePath;

        try {

            fileInputStream = new FileInputStream(filePath);
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
            xssfSheet = xssfWorkbook.getSheet(sheetName);
            fileInputStream.close();


        }catch (FileNotFoundException ex){
            System.out.println("Enter valid file name");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ExcelReader(InputStream inputStream) throws IOException {

        this.filePath = filePath;
        try {
            xssfWorkbook = new XSSFWorkbook(inputStream);
            xssfSheet = xssfWorkbook.getSheetAt(0);
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readCellData(int rowNum, int colNum) {

        String value = null;
        row = xssfSheet.getRow(rowNum);
        cell = row.getCell(colNum);

        if(cell!=null) {
            CellType type = cell.getCellTypeEnum();

            if (type == CellType.STRING) {
                value = cell.getStringCellValue();
            } 
            else if (type == CellType.NUMERIC) {
            	if(DateUtil.isCellDateFormatted(cell)){
                   DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                  Date date=cell.getDateCellValue();
                  value=dateFormat.format(date);
            	}
            	else{
            	value = String.valueOf(cell.getRawValue());
				}
            } else if(type == CellType.BOOLEAN){value = String.valueOf(cell.getBooleanCellValue());}
		}else{
            value="";
        }
        return value;
        }

    public Map<String,Integer> getHeaderRowDetails(){
        Map<String,Integer> headerDetails=new HashMap<String,Integer>();
        row=xssfSheet.getRow(0);
        for(int i=0;i<row.getLastCellNum();i++){
            String data=readCellData(row.getRowNum(),i);
            headerDetails.put(data,cell.getColumnIndex());
        }return headerDetails;

    }

    public List<Map<String, String>> getTestData() {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        int rowCount = xssfSheet.getLastRowNum();
        Map<String, String> map;
        int i = 0;
        row = xssfSheet.getRow(0);
        int lastCellNum=row.getLastCellNum();
        for (int k = 1; k <= rowCount; k++) {
            map = new HashMap<String, String>();
            for (int j = 0; j < lastCellNum; j++) {
            	String str=readCellData(k, j);
            	if(str!=null)
            	{
            		str=str.trim();
            	}
            	if(str==null) {
            		str="";
            	}
                map.put(readCellData(i, j), str);
                map.remove(null);
            }
            mapList.add(map);
        }

        return mapList;

    }
    public List<Map<String, String>> getExcelData() {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        int rowCount = xssfSheet.getLastRowNum();
        Map<String, String> map;
        int i = 1;
        row = xssfSheet.getRow(1);
        int lastCellNum=row.getLastCellNum();
        for (int k = 2; k <= rowCount; k++) {
            map = new HashMap<String, String>();
            for (int j = 0; j < lastCellNum; j++) {
            	String str=readCellData(k, j);
            	if(str!=null)
            	{
            		str=str.trim();
            	}
                map.put(readCellData(i, j), str);
            }
            mapList.add(map);
        }

        return mapList;

    }
	public Map<String, String> getAgentSettingsRowData(int from, int to) {
        Map<String, String> map = new HashMap<String, String>();
        row = xssfSheet.getRow(from);
        for (int k = from; k <= to; k++) {
            map.put(readCellData(k,2),readCellData(k,3)+":"+readCellData(k,4)+":"+readCellData(k,5));
        }
        return map;

    }

	
}