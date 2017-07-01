package com.oversea.api.util;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.oversea.api.annotation.DBResource;
import com.oversea.api.test.DemoTestCase;

public class WorkBookUtils {

	public static void initDBResource(@SuppressWarnings("rawtypes") Class clazz) throws Exception{
		@SuppressWarnings("unchecked")
		DBResource dbresource = (DBResource)clazz.getAnnotation(DBResource.class);
		if(dbresource != null){
			InputStream is = DemoTestCase.class.getClassLoader().getResourceAsStream(dbresource.value());
			Workbook book = Workbook.getWorkbook(is);
			Sheet sheet = book.getSheet(0);
			initSheetData(sheet);
			
			if(book.getSheets().length > 1){
				for(int i = 1; i < book.getSheets().length; i++){
					Sheet tableSheet = book.getSheet(i);
					exportSheetData(tableSheet);
				}
			}
			book.close();			
		}
	}
	
	public static void clearDBResource(@SuppressWarnings("rawtypes") Class clazz) throws Exception{
		@SuppressWarnings("unchecked")
		DBResource dbresource = (DBResource)clazz.getAnnotation(DBResource.class);
		if(dbresource != null){
			InputStream is = DemoTestCase.class.getClassLoader().getResourceAsStream(dbresource.value());
			Workbook book = Workbook.getWorkbook(is);
			Sheet sheet = book.getSheet(0);
			initSheetData(sheet);
			book.close();			
		}
	}
	
	private static void initSheetData(Sheet initSheet){
		for (int i = 0; i < initSheet.getRows(); i++) {
//			Cell cell = initSheet.getCell(i,0);
			//取的当前行
			Cell[] cell = initSheet.getRow(i);
			//只取第一列
			String result = cell[0].getContents();
			System.out.println(result);
			DBUtils.execute(result);
		}	
	}

	private static void exportSheetData(Sheet tableSheet) {
		for(int row = 1; row < tableSheet.getRows(); row++){
			StringBuilder fields = new StringBuilder();
			StringBuilder values = new StringBuilder();
			StringBuilder sql = new StringBuilder();
			for(int col = 0; col < tableSheet.getColumns(); col++){
				String field = tableSheet.getCell(col, 0).getContents();
				String value = tableSheet.getCell(col, row).getContents();
				if(value == null || value.length() == 0 || value.equalsIgnoreCase("NULL")){
					continue;
				}
				if(col > 0){
					fields.append(",");
					values.append(",");
				}
				fields.append(field);
				
				if(value.toLowerCase().contains("date_sub") 
				  ||value.toLowerCase().contains("date_add")
				  ||value.toLowerCase().contains("now()")
				  ||value.toLowerCase().contains("current_date")) {
					values.append(value);
				}else{
					values.append("'").append(value).append("'");
				}
			}
			sql.append("INSERT INTO ").append(tableSheet.getName())
				.append("(").append(fields).append(") values (")
				.append(values).append(");");
			System.out.println(sql.toString());
			try{
				DBUtils.execute(sql.toString());
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}

}
