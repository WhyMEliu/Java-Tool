package excelanalysis;

import java.io.File;
import java.util.List;


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
public class TestNewExcel {
	public static void main(String[] args) {
		
		NewExcelUtils excelReader = new NewExcelUtils("C:\\Users\\jw\\Desktop\\ttli\\newjiex.xlsx");
		//��ȡ�ϴ��ļ�һ���ж�����
		int objTotal = excelReader.getTotalRow();
		System.out.println("objTotal:"+objTotal);
		
		//������������
		List<Object[]> allData = excelReader.readPage(0, objTotal);
		System.out.println("allData:"+allData);

		//�ж��Ƿ��п��У�0��ʾ�޿���
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
	 * <b>����˵����</b>
	 * <ul>
	 * �ж��Ƿ��п��У�������򷵻ض�Ӧ���е���������
	 * </ul>
	 * @param params
	 * @return
	 */
	private static int rowIsData(List<Object[]> objPage){
		int flag=0;
		boolean exist=false;
		if(objPage.size()>0){
			//����objPage����
			for(int i=0;i<objPage.size();i++){
				exist=false;
				Object[] params=objPage.get(i);
				//����ÿ�е�ÿ��
				for(int j=0;j<params.length;j++){
					String param=params[j]+"";
					//ֻ��Ҫһ��������˵�����в��ǿ���
					if(!"".equals(param.trim())){
						exist=true;
						break;
					}
				}
				//�п��з��ؿ�����������
				if(!exist){
					flag=i;
					return flag;
				}
			}
		}
		return flag;
	}
}
