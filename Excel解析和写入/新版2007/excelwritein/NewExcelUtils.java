package excelwritein;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <b>修改记录：</b> 
 * <p>
 * <li>
 * 
 *                        ---- LP 2018年10月30日
 * </li>
 * </p>
 * 
 * <b>类说明：excel写入</b>
 * <p> 
 * 
 * </p>
 */
public class NewExcelUtils {
	
	private XSSFWorkbook workbook;
	  private XSSFSheet sheet;

	  public NewExcelUtils()
	  {
	    this.workbook = new XSSFWorkbook();
	  }

	  private XSSFSheet getSheet()
	  {
	    if (this.sheet == null) {
	      synchronized (this) {
	        if (this.sheet == null) {
	          this.sheet = this.workbook.createSheet();
	        }
	      }
	    }
	    return this.sheet;
	  }

	  public void setSheetName(String sheetName)
	  {
	    XSSFSheet getSheet = this.workbook.getSheet(sheetName);
	    if (getSheet == null) {
	      getSheet = this.workbook.createSheet(sheetName);
	    }
	    this.sheet = getSheet;
	  }

	  public void setSheetIndex(int index)
	  {
	    XSSFSheet getSheet = this.workbook.getSheetAt(index);
	    if (getSheet == null) {
	      getSheet = this.workbook.getSheetAt(index - 1);
	      if (getSheet == null) {
	        throw new IllegalArgumentException("当前没有[" + index + "]页码");
	      }
	      getSheet = this.workbook.createSheet();
	    }
	    this.sheet = getSheet;
	  }

	  public int getTotalRow()
	  {
	    return getSheet().getPhysicalNumberOfRows();
	  }

	  public void writeRow(List<Object[]> list)
	  {
	    if ((list == null) || (list.size() <= 0)) {
	      return;
	    }

	    int totalRow = getTotalRow();

	    for (int i = 0; i < list.size(); i++)
	      modifyExcelRow((Object[])list.get(i), totalRow + i);
	  }

	  public void writeRow(Object[] cellVaueArray)
	  {
	    modifyExcelRow(cellVaueArray, getTotalRow());
	  }

	  public void modifyExcelRow(Object[] cellVaueArray, int rowNumber)
	  {
	    XSSFRow row = getSheet().createRow(rowNumber);

	    if ((cellVaueArray == null) || (cellVaueArray.length <= 0)) {
	      return;
	    }

	    for (int j = 0; j < cellVaueArray.length; j++) {
	      if (cellVaueArray[j] == null)
	      {
	        continue;
	      }

	      XSSFCell cell = row.createCell(j);

	      Class clazz = cellVaueArray[j].getClass();

	      if (isIntergrated(clazz, String.class))
	        cell.setCellValue((String)cellVaueArray[j]);
	      else if ((isIntergrated(clazz, Long.TYPE)) || 
	        (isIntergrated(clazz, Long.class)))
	        cell.setCellValue(((Long)cellVaueArray[j]).longValue());
	      else if ((isIntergrated(clazz, Integer.TYPE)) || 
	        (isIntergrated(clazz, Integer.class)) || 
	        (isIntergrated(clazz, Short.TYPE)) || 
	        (isIntergrated(clazz, Short.class)))
	        cell.setCellValue(((Integer)cellVaueArray[j]).intValue());
	      else if ((isIntergrated(clazz, Double.TYPE)) || 
	        (isIntergrated(clazz, Double.class)) || 
	        (isIntergrated(clazz, Float.TYPE)) || 
	        (isIntergrated(clazz, Float.class)))
	        cell.setCellValue(((Double)cellVaueArray[j]).doubleValue());
	      else if (isIntergrated(clazz, Date.class))
	        cell.setCellValue((Date)cellVaueArray[j]);
	      else
	        cell.setCellValue(cellVaueArray[j].toString());
	    }
	  }

	  public void outputExcelToFile(String excelURI)
	    throws IOException
	  {
	    FileOutputStream out = null;
	    try
	    {
	      out = new FileOutputStream(excelURI);

	      outputExcel(out);
	    } catch (FileNotFoundException e) {
	      throw e;
	    } finally {
	      if (out != null) {
	        try {
	          out.flush();
	        } catch (Exception localException) {
	        }
	        try {
	          out.close();
	        } catch (Exception localException1) {
	        }
	        out = null;
	      }
	    }
	  }

	  public void outputExcel(OutputStream outputStream) throws IOException
	  {
	    this.workbook.write(outputStream);
	  }
	  
	  
	  public static boolean isIntergrated(Class<?> clazz, Class<?> interfazz)
	  {
	    Class[] cs = clazz.getInterfaces();
	    if ((cs != null) && (cs.length > 0)) {
	      for (Class objc : cs) {
	        if (objc == interfazz) {
	          return true;
	        }

	        if (isIntergrated(objc, interfazz)) {
	          return true;
	        }
	      }
	    }

	    return false;
	  }
}
