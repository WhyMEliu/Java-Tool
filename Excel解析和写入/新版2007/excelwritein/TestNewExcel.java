/******************************************************************
 * TestNewExcel.java
 * Copyright 2018 by GNNT Company. All Rights Reserved.
 * CreateDate：2018年10月30日
 * Author：Liupeng
 * Version：F1.0.0.0
 ******************************************************************/

package excelwritein;

import java.io.IOException;
import java.util.List;


/**
 * <b>修改记录：</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018年10月30日
 * </li>
 * </p>
 * 
 * <b>类说明：</b>
 * <p> 
 * 
 * </p>
 */
public class TestNewExcel {
	public static void main(String[] args) {
		try {
			NewExcelUtils excelWritein = new NewExcelUtils();
			excelWritein.setSheetIndex(0);
			Object[] cellVaueArray = {1,2,3,4};
			excelWritein.modifyExcelRow(cellVaueArray, 1);
			excelWritein.outputExcelToFile("C:\\Users\\jw\\Desktop\\ttli\\ssss.xlsx");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
