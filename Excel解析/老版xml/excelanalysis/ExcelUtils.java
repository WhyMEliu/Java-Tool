package excelanalysis;


import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtils {

	public static void main(String[] args) {
	}
	
	/**
    * 导入Excel
    * 
    * @param clazz
    * @param xls
    * @return
    * @throws Exception
    */
    @SuppressWarnings("rawtypes")
    public static List importExcel(File file, String[] params) throws Exception {
    	FileInputStream xls = new FileInputStream(file);
    	try {
    		 // 取得Excel
            HSSFWorkbook wb = new HSSFWorkbook(xls);
            HSSFSheet sheet = wb.getSheetAt(0);
            // 行循环
            List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>(sheet.getPhysicalNumberOfRows() * 2);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            	// 数据模型
            	Map<String, Object> m = new HashMap<>();
                int nullCount = 0;
                Exception nullError = null;
                Map<String, Object> map = new HashMap<>();
                int ct = 0;
                for (int j = 0; j < params.length; j++) {
                	HSSFCell c = sheet.getRow(i).getCell(j);
                	if(c!=null && !c.toString().trim().equals("")){
                		map.put(params[j], getCellStringValue(c));
                	}
                	else{
                		map.put(params[j], "");ct++;                		
                	}
				}
                if(ct<params.length)
                	modelList.add(map);
                
            }
            return modelList;

        } finally {
            xls.close();
        }
    }
    public static String getCellStringValue(HSSFCell cell) {  
        String cellValue = "";      
        switch (cell.getCellType()) {      
        case HSSFCell.CELL_TYPE_STRING://字符串类型
            cellValue = cell.getStringCellValue();      
            if(cellValue.trim().equals("")||cellValue.trim().length()<=0)      
                cellValue=" ";      
            break;      
        case HSSFCell.CELL_TYPE_NUMERIC: //数值类型   
        	NumberFormat nf = NumberFormat.getInstance();
        	cellValue = nf.format(cell.getNumericCellValue());
        	//这种方法对于自动加".0"的数字可直接解决
        	//但如果是科学计数法的数字就转换成了带逗号的，例如：12345678912345的科学计数法是1.23457E+13，经过这个格式化后就变成了字符串“12,345,678,912,345”，这也并不是想要的结果，所以要将逗号去掉
        	if (cellValue.indexOf(",") >= 0) {
        		cellValue = cellValue.replace(",", "");
        	}
            break;      
        case HSSFCell.CELL_TYPE_FORMULA:  //公式   
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);      
            cellValue = String.valueOf(cell.getNumericCellValue());      
            break;      
        case HSSFCell.CELL_TYPE_BLANK:      
            cellValue=" ";      
            break;      
        case HSSFCell.CELL_TYPE_BOOLEAN:      
            break;      
        case HSSFCell.CELL_TYPE_ERROR:      
            break;      
        default:      
            break;      
        }      
        return cellValue;      
    } 
}
