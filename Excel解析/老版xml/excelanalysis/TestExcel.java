/******************************************************************
 * TestExcel.java
 * Copyright 2018 by GNNT Company. All Rights Reserved.
 * CreateDate��2018��10��30��
 * Author��Liupeng
 * Version��F1.0.0.0
 ******************************************************************/

package excelanalysis;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <b>�޸ļ�¼��</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018��10��30��
 * </li>
 * </p>
 * 
 * <b>��˵����</b>
 * <p> 
 * 
 * </p>
 */
public class TestExcel {
	public static void main(String[] args) {
		//excel �ļ���
		File file= new File("");
		try {
			//����
			String[] t = new String[]{"commodityId","status"};
			//Excel .xls  ���ݽ���   ֻ�����ϰ棨xml��
			List<Map<String, Object>> l = ExcelUtils.importExcel(file, t);
			System.out.println("Excel.size==="+l.size());
			for (int i=0;i<l.size();i++) {
	    		StringBuffer msg=new StringBuffer(i+2+"��");
	        	String commodityId = l.get(i).get("commodityId").toString();
	        	String status = l.get(i).get("status").toString();
			}
		}catch(Exception e){
				e.printStackTrace();
		}
		
	}
}
