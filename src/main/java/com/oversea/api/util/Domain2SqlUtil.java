package com.oversea.api.util;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

public class Domain2SqlUtil<T> {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String notDbColumns[];
	
	public Domain2SqlUtil(String[] notDbColumns){
		this.notDbColumns = notDbColumns;
	}
	
	public Domain2SqlUtil(){
		
	}
	
	public boolean noNeedInsert2Db(String fieldName){
		if(notDbColumns!=null && notDbColumns.length>0){
			for(int j=0; j<notDbColumns.length; j++){
				if(fieldName.equalsIgnoreCase(notDbColumns[j])){
					return true;
				}
			}
		}
		return false;
	}
	
	public  String transfor(T t, String tableName){
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ").append(tableName).append("(");
		StringBuffer filesBuffer = new StringBuffer();
		StringBuffer valuesBuffer = new StringBuffer();
		
		Field fields[] = t.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			
			if(noNeedInsert2Db(fieldName))
				continue;
			
			Object type = field.getType();
			if (fieldName.equals("serialVersionUID") || fieldName.equals("id")) {
				continue;
			}
			field.setAccessible(true);
			Object value;
			
			try {
				value = field.get(t);
			} catch (Exception e) {
				value = "";
			}

			if (value != null) {
				if (type.toString().equals("class java.lang.String")) {
					value ="'"+value.toString().replace("'", "").replace("(", "").replace(")", "").replace("$", "")+"'";
				}else if (type.toString().equals("class java.util.Date")) {
					try {
						value = "'"+sdf.format(value)+"'";
					} catch (Exception e) {
						value = "";
					}
				}
			}
			
			filesBuffer.append(camel2Underscore(fieldName));
			valuesBuffer.append(value); //去掉，

			filesBuffer.append(",");
			valuesBuffer.append(",");

//			System.out.println("fieldName="+fieldName+" type="+type);
		}
		
		//最后一个是逗号的，不要
		if(filesBuffer.toString().endsWith(",")){
			sql.append(filesBuffer.toString().substring(0, filesBuffer.toString().length()-1))
			   .append(") values( ")
			   .append(valuesBuffer.toString().substring(0, valuesBuffer.toString().length()-1))
			   .append(");");
		}else{
			sql.append(filesBuffer).append(") values( ").append(valuesBuffer).append(");");
		}
		
		return sql.toString();
	}

	public String camel2Underscore(String str){
		if(!StringUtils.isEmpty(str)){
			StringBuffer s= new StringBuffer(str);
			for(int i=0; i<s.length(); i++){
				if(Character.isUpperCase(s.charAt(i))){
					String tmp=s.charAt(i)+"";
					s.deleteCharAt(i);
					//System.out.println(i);
					s.insert(i, "_"+tmp.toLowerCase());
				}
			}
			return s.toString();
		}
		return "";
	}
	
	public static void main(String args[]){
//		String str="1234m,1234,ttt,";
//		if(str.endsWith(",")){
//			System.out.println(str.substring(0, str.length()-1));
//		}else{
//			System.out.println(str.substring(0, str.length()));
//		}
		

		
	}
}
