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
		File file= new File("C:\\Users\\jw\\Desktop\\ttli\\jiex.xls");
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
	        	System.out.println("commodityId==="+commodityId);
	        	System.out.println("status==="+status);
			}
		}catch(Exception e){
				e.printStackTrace();
		}
	}
}
