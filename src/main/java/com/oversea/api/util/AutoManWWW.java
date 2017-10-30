package com.oversea.api.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

//===================================================================================
//特价促销domain
//===================================================================================
public class AutoManWWW {

	private static Connection conn;

	//JDCB
	private static String driver = "com.mysql.jdbc.Driver";
//	private static String url = "jdbc:mysql://122.225.114.18/core?useUnicode=true&characterEncoding=utf8&noAccessToProcedureBodies=true";
//	private static String user = "nvshen";
//	private static String password = "ns321";
	

	
	private static String url = "jdbc:mysql://122.225.114.20/oversea?useUnicode=true&characterEncoding=utf8&noAccessToProcedureBodies=true";
	private static String user = "dev";
	private static String password = "dev@)#)";
	//请根据自己的项目的实际情况做替换              
	private static String locatin_project = "/Users/xuzhizheng/Documents/githaihuspace/oversea-common/";
	private static String tableName = "bundle_goods_sell";
	private static String domainName = "BundleGoodsSell";
	private static String primaryKey = "id";//小写
	private static String prefix="";
	
	//设置DAO/Manager前缀，系统会自动在相应的功能模块中追加代码(例如UserManager就填user,注意第一个字母小写)
	//将会对应BDDAO/BDDAOImpl/BDManager/BDManaerImpl
//	/Users/ouburi/workspace/java/luna/commons_2.2/src/main/java/com/nvshen/common/domain/AccessUrl.java
	//下面这些不用替换
	private static String location_sqlmaps = "src/main/resources/sqlmaps/";
	private static String locaton_domain   = "src/main/java/com/oversea/common/domain/goods/";
	
	private static String location_dao = "src/main/java/com/oversea/common/dao/goods/";
	private static String location_dao_impl = "src/main/java/com/oversea/common/dao/goods/impl/";

	private static String package_domain = "com.oversea.common.domain.goods"+(prefix.length()>0?("."+prefix):"");
	private static String package_dao = "com.oversea.common.dao.goods."+(prefix.length()>0?("."+prefix):"");
	private static String package_dao_impl = "com.oversea.dao.goods."+(prefix.length()>0?(prefix+"."):"")+"impl";

	public static void main(String args[]) throws FileNotFoundException, SQLException, IOException,
			ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println("Start to generator code");
		domainGen();
		System.out.println("End to generator code");
	}

	public static void domainGen() throws SQLException, FileNotFoundException, IOException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {

		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url, user, password);

		domainName = toCaptial(domainName);

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from " + tableName + " where 1=2");
		ResultSetMetaData metaData = rs.getMetaData();

		
		generatorDomain(metaData);
		generatorSQLMap(metaData);
		generatorDAO();
		generatorDAOImpl();
		conn.close();
	}

	public static String appendTab(int numOfTab) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<numOfTab; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}
	
	public static  String appendEnter(int numOfEnter) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<numOfEnter; i++) {
			sb.append("\n");
		}
		return sb.toString();
	} 
	
	public static void generatorDAO() throws IOException {
		OutputStream outStream = new FileOutputStream(locatin_project + location_dao + domainName + "DAO.java",
				true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
		StringBuffer DAO = new StringBuffer();
		String parameterClass = package_domain + "." + domainName;
		
		DAO.append("package ").append(package_dao).append(";").append(appendEnter(2));
		DAO.append("import ").append(parameterClass).append(";").append(appendEnter(1));
		DAO.append("import java.util.List;").append(appendEnter(1));
		DAO.append("public interface ").append(domainName).append("DAO {").append(appendEnter(1));
		DAO.append(appendTab(1)).append("public void add").append(domainName)
			.append("("+domainName+" "+toLowerCaptial(domainName)+");").append(appendEnter(1));
		
		DAO.append(appendTab(1)).append("public void update").append(domainName).append("By").append(toCaptial(toCamel(primaryKey)))
			.append("("+domainName+" "+toLowerCaptial(domainName)+");").append(appendEnter(1));
		
		DAO.append(appendTab(1)).append("public void update").append(domainName).append("ByDynamic")
			.append("("+domainName+" "+toLowerCaptial(domainName)+");").append(appendEnter(1));

		DAO.append(appendTab(1)).append("public List<").append(domainName).append("> ").append("get").append(domainName)
				.append("By").append(toCaptial(toCamel(primaryKey))).append("(Long id);").append(appendEnter(1));
		DAO.append(appendTab(1)).append("public int count").append(domainName).append("By").append(toCaptial(toCamel(primaryKey)))
			.append("(Long id);").append(appendEnter(1));
		
		DAO.append("}");
		bw.write(DAO.toString());
		bw.close();
		System.out.println("Generator " + locatin_project + location_dao + domainName + "DAO successful");
	}

	public static void generatorDAOImpl() throws IOException {
		OutputStream outStream = new FileOutputStream(locatin_project + location_dao_impl + domainName
				+ "DAOImpl.java", true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
		StringBuffer DAOImpl = new StringBuffer();
		String parameterClass = package_domain + "." + domainName;

		DAOImpl.append("package ").append(package_dao_impl).append(";").append(appendEnter(2));
		DAOImpl.append("import ").append(parameterClass).append(";").append(appendEnter(1));
		DAOImpl.append("import java.util.List;").append(appendEnter(1));
		DAOImpl.append("import ").append(package_dao).append(".").append(domainName).append("DAO;").append(appendEnter(1));
		DAOImpl.append("import com.oversea.common.dao.BaseDao;").append(appendEnter(2));

		DAOImpl.append("public class ").append(domainName).append("DAOImpl extends BaseDao implements ").append(domainName).append("DAO {").append(appendEnter(1));
		//add
		DAOImpl.append(appendTab(1)).append("public void add").append(domainName)
			.append("("+domainName+" "+toLowerCaptial(domainName)+") {").append(appendEnter(1))
			.append(appendTab(2)).append("getSqlSession().insert(\"add" + domainName +"\","+toLowerCaptial(domainName)+");").append(appendEnter(1))
			.append("}").append(appendEnter(1));

		DAOImpl.append(appendTab(1)).append("public void update").append(domainName).append("By").append(toCaptial(toCamel(primaryKey)))
			.append("("+domainName+" "+toLowerCaptial(domainName)+") {").append(appendEnter(1))
			.append(appendTab(2)).append("getSqlSession().update(\"update" + domainName +"By"+toCaptial(toCamel(primaryKey))+"\","+toLowerCaptial(domainName)+");").append(appendEnter(1))
			.append("}").append(appendEnter(1));

		DAOImpl.append(appendTab(1)).append("public void update").append(domainName).append("ByDynamic")
			.append("("+domainName+" "+toLowerCaptial(domainName)+") {").append(appendEnter(1))
			.append(appendTab(2)).append("getSqlSession().update(\"update" + domainName +"ByDynamic\","+toLowerCaptial(domainName)+");").append(appendEnter(1))
			.append("}").append(appendEnter(1));
		
		
		DAOImpl.append(appendTab(1)).append("public List<").append(domainName).append("> ").append("get").append(domainName)
			.append("By").append(toCaptial(toCamel(primaryKey))).append("(Long id) {").append(appendEnter(1))
			.append(appendTab(2)).append("return getSqlSession().selectList(\"get" + domainName +"By"+toCaptial(toCamel(primaryKey))+"\", id);").append(appendEnter(1))
			.append("}").append(appendEnter(1));
			
		DAOImpl.append(appendTab(1)).append("public int count").append(domainName).append("By").append(toCaptial(toCamel(primaryKey)))
			.append("(Long id) {").append(appendEnter(1))
			.append(appendTab(2)).append("return (Integer)getSqlSession().selectOne(\"count" + domainName +"By"+toCaptial(toCamel(primaryKey))+"\",id);").append(appendEnter(1))
			.append("}").append(appendEnter(1));
		
		DAOImpl.append("}");
		bw.write(DAOImpl.toString());
		bw.close();
		System.out.println("Generator " + locatin_project + location_dao_impl + domainName
				+ "DAOImpl successful");
	}



	public static void generatorDomain(ResultSetMetaData metaData) throws SQLException, IOException {
		OutputStream outStream = new FileOutputStream(locatin_project + locaton_domain + domainName + ".java", true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
		StringBuffer head = new StringBuffer();
		StringBuffer define = new StringBuffer();
		StringBuffer getSet = new StringBuffer();

		genHeader(head, package_domain, "domain");
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			String fieldName = toCamel(metaData.getColumnName(i).toLowerCase());
			//	        	String fieldName = metaData.getColumnName(i).toLowerCase();
			if ("BIGINT".equals(metaData.getColumnTypeName(i))) {
				define.append(tf8AppendEOF(1, "private Long " + fieldName, 1));
				getSet.append(tf8Append(1, "public Long get" + toCaptial(fieldName) + "() {", 1))
						.append(tf8AppendEOF(2, "return " + fieldName, 1))
						.append(tf8Append(1, "}", 2))
						.append(tf8Append(1, "public void set" + toCaptial(fieldName) + "(Long " + fieldName + ") {", 1))
						.append(tf8AppendEOF(2, "this." + fieldName + "=" + fieldName, 1)).append(tf8Append(1, "}", 1));
			} else if ("INT".equals(metaData.getColumnTypeName(i))) {
				define.append(tf8AppendEOF(1, "private Integer " + fieldName, 1));
				getSet.append(tf8Append(1, "public Integer get" + toCaptial(fieldName) + "() {", 1))
						.append(tf8AppendEOF(2, "return " + fieldName, 1))
						.append(tf8Append(1, "}", 2))
						.append(tf8Append(1,
								"public void set" + toCaptial(fieldName) + "(Integer " + fieldName + ") {", 1))
						.append(tf8AppendEOF(2, "this." + fieldName + "=" + fieldName, 1)).append(tf8Append(1, "}", 1));
			} else if ("FLOAT".equals(metaData.getColumnTypeName(i))) {
				define.append(tf8AppendEOF(1, "private Float " + fieldName, 1));
				getSet.append(tf8Append(1, "public Float get" + toCaptial(fieldName) + "() {", 1))
						.append(tf8AppendEOF(2, "return " + fieldName, 1))
						.append(tf8Append(1, "}", 2))
						.append(tf8Append(1, "public void set" + toCaptial(fieldName) + "(Float " + fieldName + ") {",
								1)).append(tf8AppendEOF(2, "this." + fieldName + "=" + fieldName, 1))
						.append(tf8Append(1, "}", 1));
			} else if ("DATE".equals(metaData.getColumnTypeName(i))) {
				define.append(tf8AppendEOF(1, "private Date " + fieldName, 1));
				getSet.append(tf8Append(1, "public Date get" + toCaptial(fieldName) + "() {", 1))
						.append(tf8AppendEOF(2, "return " + fieldName, 1))
						.append(tf8Append(1, "}", 2))
						.append(tf8Append(1, "public void set" + toCaptial(fieldName) + "(Date " + fieldName + ") {", 1))
						.append(tf8AppendEOF(2, "this." + fieldName + "=" + fieldName, 1)).append(tf8Append(1, "}", 1));
				if (head.indexOf("import java.util.Date") < 0) {
					head.append("import java.util.Date;\n");
				}
			} else if ("DATETIME".equals(metaData.getColumnTypeName(i))) {
				define.append(tf8AppendEOF(1, "private Date " + fieldName, 1));
				getSet.append(tf8Append(1, "public Date get" + toCaptial(fieldName) + "() {", 1))
						.append(tf8AppendEOF(2, "return " + fieldName, 1))
						.append(tf8Append(1, "}", 2))
						.append(tf8Append(1, "public void set" + toCaptial(fieldName) + "(Date " + fieldName + ") {", 1))
						.append(tf8AppendEOF(2, "this." + fieldName + "=" + fieldName, 1)).append(tf8Append(1, "}", 1));
				if (head.indexOf("import java.util.Date") < 0) {
					head.append("import java.util.Date;\n");
				}
			} else {
				define.append(tf8AppendEOF(1, "private String " + fieldName, 1));
				getSet.append(tf8Append(1, "public String get" + toCaptial(fieldName) + "() {", 1))
						.append(tf8AppendEOF(2, "return " + fieldName, 1))
						.append(tf8Append(1, "}", 2))
						.append(tf8Append(1, "public void set" + toCaptial(fieldName) + "(String " + fieldName + ") {",
								1)).append(tf8AppendEOF(2, "this." + fieldName + "=" + fieldName, 1))
						.append(tf8Append(1, "}", 1));
			}
		}

		head.append("public class " + domainName + "{\n");
		bw.write(head.toString());
		bw.write(define.toString());
		bw.write(getSet.toString());
		bw.write("}");
		bw.close();
		System.out.println("Generator " + locatin_project + locaton_domain + domainName + ".java successful");
	}

	public static void generatorSQLMap(ResultSetMetaData metaData) throws SQLException, IOException {
		OutputStream outStream = new FileOutputStream(locatin_project + location_sqlmaps + domainName + "SQL.xml", true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
		StringBuffer head = new StringBuffer();
		StringBuffer resultMap = new StringBuffer();
		StringBuffer insertHead = new StringBuffer();
		StringBuffer insertMid = new StringBuffer();
		StringBuffer insertTail = new StringBuffer();
		StringBuffer insertField = new StringBuffer();
		StringBuffer insertValue = new StringBuffer();
		StringBuffer update = new StringBuffer();
		StringBuffer countByPrimaryKey = new StringBuffer();
		StringBuffer getByPrimaryKey = new StringBuffer();
		StringBuffer updateDynamic = new StringBuffer();
		StringBuffer BaseColumnList = new StringBuffer();
		//Additional拓展

		String parameterType = "\"" + package_domain + "." + domainName + "\"";

		genHeader(head, package_domain, "sqlMap");
		//1.namespace
		head.append("<mapper namespace=\"" + domainName + "SQL\">").append(appendEnter(1));
		//2.resultMap
		resultMap.append("<resultMap id=\"" + domainName + "\"	type=" + parameterType + ">").append(appendEnter(1));
		//3.insert
		insertHead.append("<insert id=\"add" + domainName + "\"  parameterType=" + parameterType + ">").append(appendEnter(1));
		insertHead.append(tf8Append(1, "INSERT INTO " + tableName + " (", 1));
		//4.update
		update.append("<update id=\"update" + domainName + "By" + toCaptial(toCamel(primaryKey)) + "\"  parameterType="+ parameterType + ">").append(appendEnter(1))
		      .append(tf8Append(1, "update " + tableName + " set", 1));
		
		updateDynamic.append("<update id=\"update" + domainName + "ByDynamic\"  parameterType="+ parameterType + ">").append(appendEnter(1))
			  .append(tf8Append(1, "update " + tableName + " <set>", 1));
		
		String comma = ",";
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			String fieldName = toCamel(metaData.getColumnName(i).toLowerCase());
			//	        	String fieldName = metaData.getColumnName(i).toLowerCase();	            
			String colName = metaData.getColumnName(i).toLowerCase();
			resultMap.append(appendTab(1)).append("<result  property=\"" + fieldName + "\"  column=\"" + colName + "\"/>").append(appendEnter(1));

			//主键不更新,也不需要插入自动生成
			if (!colName.equals(primaryKey.toLowerCase())) {
				if (i == columnCount) {
					comma = "";
				}
				
				if ("FLOAT".equals(metaData.getColumnTypeName(i))) {					
					update.append(tf8Append(2, "round("+colName+",2)=#{" + fieldName + "}" + comma, 1));
					updateDynamic.append(tf8Append(2, "<if test=\"" + fieldName + "!= null\">", 1))
								.append(tf8Append(4, "round("+colName+",2)=#{" + fieldName + "}" + comma, 1))
								.append(tf8Append(2,"</if>",1));
				}else{
					update.append(tf8Append(2, colName + "=#{" + fieldName + "}" + comma, 1));
					updateDynamic.append(tf8Append(2, "<if test=\"" + fieldName + "!= null\">", 1))
								.append(tf8Append(4, colName + "=#{" + fieldName + "}" + comma, 1))
								.append(tf8Append(2,"</if>",1));
				}
				
				if (insertField.length() > 0) {
					insertField.append(tf8Append(0, ",", 1));
				}
				insertField.append(tf8Append(2, colName, 0));

				if (insertValue.length() > 0) {
					insertValue.append(tf8Append(0, ",", 1));
				}
				insertValue.append(tf8Append(2, "#{" + fieldName + "}", 0));
			}
		}
		resultMap.append("</resultMap>").append(appendEnter(2));
		
		BaseColumnList.append("<sql id=\""+domainName+"_Column_List\">").append(appendEnter(1));
		BaseColumnList.append(appendTab(2)).append(primaryKey).append(",").append(appendEnter(1));
		BaseColumnList.append(insertField).append(appendEnter(1));
		BaseColumnList.append("</sql>").append(appendEnter(2));
		
		insertMid.append(appendEnter(1)).append(") VALUES (").append(appendEnter(1));
		insertTail.append(")").append(appendEnter(1))
				//.append("<selectKey resultClass=\"java.lang.Long\" keyProperty=\"")
				//.append(primaryKey).append("\">").append("SELECT LAST_INSERT_ID()").append("</selectKey>").append(appendEnter(1))
				.append("</insert>").append(appendEnter(3));

		update.append(tf8Append(1, "where " + primaryKey + "=#{" + toCamel(primaryKey) + "}", 1)).append("</update>")
				.append(appendEnter(3));

		
		
		//5.countByID
				countByPrimaryKey.append("<select id=\"count" + domainName + "By" + toCaptial(toCamel(primaryKey))
								+ "\"  parameterType=\"java.lang.Long\"").append(" resultType=\"java.lang.Integer\">")
						.append(appendEnter(1))
						.append("<![CDATA[").append(appendEnter(1))
						.append(tf8Append(1, "select count(1) from " + tableName + " where ", 0))
						.append(primaryKey + "=#{" + toCamel(primaryKey) + "}").append(appendEnter(1))
						.append("]]>").append(appendEnter(1))
						.append("</select>").append(appendEnter(2));
		
		//6.getByID
		getByPrimaryKey.append("<select id=\"get" + domainName + "By" + toCaptial(toCamel(primaryKey))
						+ "\"  parameterType=\"java.lang.Long\"").append(" resultMap=\"").append(domainName)
				.append("\">").append(appendEnter(1))
				.append(tf8Append(1, "select <include refid=\""+domainName+"_Column_List\"/>" , 1))
				.append(" from " + tableName + " where ")
				.append(primaryKey + "=#{" + toCamel(primaryKey) + "}").append(appendEnter(1))
				.append("</select>").append(appendEnter(2));

		updateDynamic.append("</set>").append(appendEnter(2));
		updateDynamic.append(tf8Append(1, "where " + primaryKey + "=#{" + toCamel(primaryKey) + "}", 1)).append("</update>")
		.append(appendEnter(3));
		
		bw.write(head.toString());
		bw.write(resultMap.toString());
		bw.write(BaseColumnList.toString());
		
		bw.write(insertHead.toString());
		bw.write(insertField.toString());
		bw.write(insertMid.toString());
		bw.write(insertValue.toString());
		bw.write(insertTail.toString());
		
		bw.write(update.toString());
		bw.write(updateDynamic.toString());
		bw.write(countByPrimaryKey.toString());
		bw.write(getByPrimaryKey.toString());
		bw.write("</mapper>");
		bw.close();
		System.out.println("Generator " + locatin_project + location_sqlmaps + domainName + "SQL.xml successful");
	}

	private static void genHeader(StringBuffer head, String package_domain, String param) {
		if ("domain".equals(param)) {
			head.append(tf8AppendEOF(0, "package " + package_domain, 2));

		}
		if ("sqlMap".equals(param)) {
			head.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append("\n")
					.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >")
					.append("\n\n\n");
		}
	}
	
	
	

	public static String toCamel(String columnName) {
		String[] str = columnName.split("_");
		String field = null;
		for (int i = 0; i < str.length; i++) {
			if (i == 0) {
				field = str[0];
			} else {
				field += toCaptial(str[i]);
			}
		}
		return field;
	}

	public static StringBuffer tf8AppendEOF(int numOfTabs, String buffer, int numOfEnters) {
		StringBuffer tabs = new StringBuffer();
		for (int i = 0; i < numOfTabs; i++) {
			tabs.append("\t");
		}

		StringBuffer enters = new StringBuffer();
		for (int i = 0; i < numOfEnters; i++) {
			enters.append("\n");
		}

		StringBuffer EOF = new StringBuffer();
		EOF.append(";");

		return tabs.append(buffer).append(EOF).append(enters);
	}

	public static StringBuffer tf8Append(int numOfTabs, String buffer, int numOfEnters) {
		StringBuffer tabs = new StringBuffer();
		for (int i = 0; i < numOfTabs; i++) {
			tabs.append("\t");
		}

		StringBuffer enters = new StringBuffer();
		for (int i = 0; i < numOfEnters; i++) {
			enters.append("\n");
		}

		return tabs.append(buffer).append(enters);
	}

	public static String toCaptial(String fieldName) {
		return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	public static String toLowerCaptial(String fieldName) {
		return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
	}

}
