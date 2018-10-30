package excelanalysis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
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
 * <b>类说明：</b>
 * <p> 
 * 
 * </p>
 */
public class NewExcelUtils {
	  private XSSFWorkbook workbook;
	  private XSSFSheet sheet;

	  public NewExcelUtils(String excelURI)
	  {
	    if ((excelURI == null) || (excelURI.trim().length() <= 0)) {
	      throw new IllegalArgumentException("传入路径为空");
	    }

	    InputStream inputStream = null;
	    try
	    {
	      inputStream = new FileInputStream(excelURI);

	      this.workbook = new XSSFWorkbook(inputStream);
	    } catch (FileNotFoundException e) {
	    	//文件不存在
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	//读取文件失败
	    	e.printStackTrace();
	    } finally {
	      if (inputStream != null)
	        try {
	          inputStream.close();
	        }
	        catch (Exception localException)
	        {
	        }
	    }
	  }

	  public NewExcelUtils(InputStream inputStream)
	  {
	    try
	    {
	      this.workbook = new XSSFWorkbook(inputStream);
	    } catch (IOException e) {
	    	//读取文件失败
	    	e.printStackTrace();
	    }
	  }

	  public Object readPoint(int rowNum, int cellNum)
	  {
	    int totalRow = getTotalRow();

	    if (rowNum >= totalRow) {
	      return "";
	    }

	    XSSFRow rows = getSheet().getRow(rowNum);

	    return getValue(rows.getCell(cellNum));
	  }

	  public Object[] readCell(int cellNum)
	  {
	    int totalRow = getTotalRow();

	    Object[] result = new Object[totalRow];

	    for (int i = 0; i < totalRow; i++) {
	      result[i] = readPoint(i, cellNum);
	    }
	    return result;
	  }

	  public Object[] readRow(int rowNum)
	  {
	    int totalRow = getTotalRow();

	    if (rowNum >= totalRow) {
	      return new String[0];
	    }

	    XSSFRow row = getSheet().getRow(rowNum);

	    int cellNum = getSheet().getRow(rowNum).getPhysicalNumberOfCells();

	    Object[] result = new Object[cellNum];

	    for (int i = 0; i < cellNum; i++)
	    {
	      result[i] = getValue(row.getCell(i));
	    }
	    return result;
	  }

	  public List<Object[]> readPage(int startRowIndex, int endRowIndex)
	  {
	    if (startRowIndex < 0) {
	      startRowIndex = 0;
	    }

	    int totalRow = getTotalRow();

	    if ((endRowIndex < 0) || (endRowIndex > totalRow)) {
	      endRowIndex = totalRow;
	    }

	    List result = new ArrayList();

	    Object[] cells = null;

	    for (int i = startRowIndex; i < endRowIndex; i++)
	    {
	      XSSFRow row = getSheet().getRow(i);

	      if (row == null) {
	        cells = new Object[0];
	        result.add(cells);
	      }
	      else
	      {
	        int cellNum = row.getPhysicalNumberOfCells();

	        cells = new Object[cellNum];

	        for (short j = 0; j < cellNum; j = (short)(j + 1))
	        {
	          cells[j] = getValue(row.getCell(j));
	        }
	        result.add(cells);
	      }
	    }
	    return result;
	  }

	  private Object getValue(XSSFCell cell)
	  {
	    if (cell == null) {
	      return "";
	    }

	    switch (cell.getCellType()) {
	    case 3:
	      return "";
	    case 5:
	      return "";
	    case 1:
	      return cell.getStringCellValue();
	    case 4:
	      return Boolean.valueOf(cell.getBooleanCellValue());
	    case 0:
	      if (HSSFDateUtil.isCellDateFormatted(cell)) {
	        return cell.getDateCellValue();
	      }
	      return cell.getRawValue();
	    case 2:
	      try
	      {
	        String value = String.valueOf(cell.getNumericCellValue());

	        if (value.indexOf("E") != -1) {
	          BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
	          bd.setScale(2);
	          value = bd.toString();
	        }
	        if (!value.equals("NaN")) break;
	        value = cell.getStringCellValue().toString();
	      }
	      catch (Exception e) {
	        return cell.getRawValue();
	      }
	    }
	    return cell.getRawValue();
	  }

	  private XSSFSheet getSheet()
	  {
	    if (this.sheet == null) {
	      synchronized (this) {
	        if (this.sheet == null) {
	          this.sheet = this.workbook.getSheetAt(0);
	        }
	      }
	    }
	    return this.sheet;
	  }

	  public void setSheetName(String sheetName)
	  {
	    XSSFSheet getSheet = this.workbook.getSheet(sheetName);
	    if (getSheet == null) {
	      throw new IllegalArgumentException("将要获取的页面[" + sheetName + "]不存在");
	    }
	    this.sheet = getSheet;
	  }

	  public void setSheetIndex(int index)
	  {
	    XSSFSheet getSheet = this.workbook.getSheetAt(index);
	    if (getSheet == null) {
	      throw new IllegalArgumentException("将要获取的页面下标[" + index + "]不存在");
	    }
	    this.sheet = getSheet;
	  }

	  public int getTotalRow()
	  {
	    return getSheet().getPhysicalNumberOfRows();
	  }
}
