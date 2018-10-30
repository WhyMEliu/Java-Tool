/******************************************************************
 * TestExcel.java
 * Copyright 2018 by GNNT Company. All Rights Reserved.
 * CreateDate：2018年10月30日
 * Author：Liupeng
 * Version：F1.0.0.0
 ******************************************************************/

package excelanalysis;

import java.io.File;
import java.util.List;
import java.util.Map;

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
public class TestExcel {
	public static void main(String[] args) {
		//excel 文件流
		File file= new File("");
		try {
			//别名
			String[] t = new String[]{"commodityId","status"};
			//Excel .xls  数据解析   只限于老版（xml）
			List<Map<String, Object>> l = ExcelUtils.importExcel(file, t);
			System.out.println("Excel.size==="+l.size());
			for (int i=0;i<l.size();i++) {
	    		StringBuffer msg=new StringBuffer(i+2+"行");
	        	String commodityId = l.get(i).get("commodityId").toString();
	        	String status = l.get(i).get("status").toString();
			}
		}catch(Exception e){
				e.printStackTrace();
		}
		
	}
}
