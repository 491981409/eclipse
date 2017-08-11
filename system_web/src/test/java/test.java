import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class test {

	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		Map<String,Object> data = null;
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
	        InputStream is = new FileInputStream("C:\\Users\\Administrator\\Desktop\\aaa.xls");  
	        // 2、声明工作簿对象  
	        Workbook rwb = Workbook.getWorkbook(is);  
	        // 3、获得工作簿的个数,对应于一个excel中的工作表个数  
	        rwb.getNumberOfSheets();  
	  
	        Sheet oFirstSheet = rwb.getSheet(0);// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称  
//	        System.out.println("工作表名称：" + oFirstSheet.getName());  
	        int rows = oFirstSheet.getRows();//获取工作表中的总行数  
	        int columns = oFirstSheet.getColumns();//获取工作表中的总列数  
	        
	        for (int i = 0; i < rows; i++) {  
	            for (int j = 0; j < columns; j++) {  
	                Cell oCell= oFirstSheet.getCell(j,i);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
	                System.out.println(oCell.getContents()+"\r\n");  
	                String precoess = oCell.getContents();
	                data.put("", precoess);
	            }  
	            result.add(data);
	        }  
	    
	}

}
