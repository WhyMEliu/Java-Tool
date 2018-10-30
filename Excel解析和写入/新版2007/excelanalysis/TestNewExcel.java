package excelanalysis;

import java.io.File;
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
		
		NewExcelUtils excelReader = new NewExcelUtils("C:\\Users\\jw\\Desktop\\ttli\\newjiex.xlsx");
		//获取上传文件一共有多少行
		int objTotal = excelReader.getTotalRow();
		System.out.println("objTotal:"+objTotal);
		
		//接收所有数据
		List<Object[]> allData = excelReader.readPage(0, objTotal);
		System.out.println("allData:"+allData);

		//判断是否有空行，0表示无空行
		int isData=rowIsData(allData);
		if(isData==0&&objTotal!=1){
			for (int i = 1; i < objTotal; i++) {
				Object[] o=allData.get(i);
				System.out.println(o[0].toString());
				System.out.println(o[1].toString());
			}
		}
	}
		
	/**
	 * 
	 * <b>方法说明：</b>
	 * <ul>
	 * 判断是否有空行，如果有则返回对应空行的所在行数
	 * </ul>
	 * @param params
	 * @return
	 */
	private static int rowIsData(List<Object[]> objPage){
		int flag=0;
		boolean exist=false;
		if(objPage.size()>0){
			//遍历objPage集合
			for(int i=0;i<objPage.size();i++){
				exist=false;
				Object[] params=objPage.get(i);
				//遍历每行的每列
				for(int j=0;j<params.length;j++){
					String param=params[j]+"";
					//只有要一列有数据说明该行不是空行
					if(!"".equals(param.trim())){
						exist=true;
						break;
					}
				}
				//有空行返回空行所在行数
				if(!exist){
					flag=i;
					return flag;
				}
			}
		}
		return flag;
	}
}
