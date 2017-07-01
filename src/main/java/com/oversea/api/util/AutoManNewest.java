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

public class AutoManNewest {

	private static Connection conn;
    
    //JDCB
    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://122.225.114.19/core?useUnicode=true&characterEncoding=utf8&noAccessToProcedureBodies=true";
    private static String user="dev";
    private static String password="dev@)#)";


    //请根据自己的项目的实际情况做替换
    private static String locatin_project="/Users/ouburi/workspace/java/oversea/oversea-api/";
    private static String locatin_manager="/Users/ouburi/workspace/java/oversea/oversea-api/";
    
    private static String tableName="product";
    private static String domainName="Product";		
    private static String primaryKey="id";//小写
    
    private static String location_jsp="src/main/webapp/";
    private static String location_controller="src/main/java/com/oversea/controller/";
    private static String jspTitle="产品列表";
    private static String tableCaption="产品列表";
    private static String RequestMappingPath="product";
    
    //设置DAO/Manager前缀，系统会自动在相应的功能模块中追加代码(例如UserManager就填user,注意第一个字母小写)
    //将会对应BDDAO/BDDAOImpl/BDManager/BDManaerImpl
    private static String DaoAndManager_prefix="product";

    //下面这些不用替换
    private static String location_sqlmaps="src/main/resources/sqlmaps/";	    
    private static String locaton_domain  ="src/main/java/com/oversea/admin/domain/";
    private static String location_manager="src/main/java/com/oversea/admin/manager/";	
    
    private static String location_manager_impl="src/main/java/com/oversea/admin/manager/impl/";		 
    private static String location_dao         ="src/main/java/com/oversea/admin/dao/";
    private static String location_dao_impl    ="src/main/java/com/oversea/admin/dao/impl/";	    
       
    
    private static String package_domain="com.oversea.admin.domain";


    public static void main(String args[]) throws FileNotFoundException, SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    	System.out.println("Start to generator code");
    	domainGen();
    	System.out.println("End to generator code");
    }

    public static void domainGen() throws SQLException, FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url, user, password);

        domainName = toCaptial(domainName);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + tableName + " where 1=2");
        ResultSetMetaData metaData = rs.getMetaData();
        
//        generatorDomain(metaData);
        generatorSQLMap(metaData);
//        generatorJsp(metaData);
//        generatorDAO();
//        generatorDAOImpl();
        //generatorManager();
        //generatorManagerImpl();
//        generatorController(metaData);
        conn.close();
    }
    
    public static void generatorController(ResultSetMetaData metaData) throws SQLException, IOException{
        OutputStream outStream = new FileOutputStream(locatin_project + location_controller+ DaoAndManager_prefix + "Controller", true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
        StringBuffer Controller = new StringBuffer();	 
        
        int columnCount = metaData.getColumnCount();
        String FilterID="";
        String FormatDatePrefix="if("; 
        String FormatDate=""; 
        for (int i = 1; i <= columnCount; i++) {
            String fieldName = toCamel(metaData.getColumnName(i).toLowerCase());
//            String fieldName = metaData.getColumnName(i).toLowerCase();	            
//            String colName=metaData.getColumnName(i).toLowerCase();
            //如果主键不是ID，那么要过滤掉jqgrid自动生成的ID
            if(!"id".equals(primaryKey)){
            	FilterID="||\"id\".equals(name)";
            }
            //日期类要进行转换
            if ("DATE".equals(metaData.getColumnTypeName(i))|| "DATETIME".equals(metaData.getColumnTypeName(i))) {	  
            	//if("start_time".equals(name)||"end_time".equals(name)){
            	if(FormatDate!=null&&FormatDate.length()>0){
            		FormatDate+="||\""+fieldName+"\".equals(name)";
            	}
            	else{
            		FormatDate="\""+fieldName+"\".equals(name)";
            	}
            }
            
        }
        
        
        if(FormatDate!=null&&FormatDate.length()>0){
        	FormatDate=FormatDatePrefix+FormatDate+"){"+"\n";
        	FormatDate+="\t\t\t\t"+"if(!StringUtils.isEmpty(value)){"+"\n";
        	FormatDate+="\t\t\t\t\t\t"+"map.put(name, format.parse(value));"+"\n";
        	FormatDate+="\t\t\t\t\t\t"+"continue;"+"\n";
        	FormatDate+="\t\t\t\t\t"+"}"+"\n";
        	FormatDate+="\t\t\t\t"+"}"+"\n";
        }
        Controller.append("\t").append("@RequestMapping(\"save").append(domainName).append("\")").append("\n")
        			.append("\t").append("public String save").append(domainName).append("(HttpServletRequest request, HttpServletResponse response) throws Exception {").append("\n")
        			.append("\n")
        			.append("\t").append("String result=\"success\";").append("\n")
        			.append("\t").append("Long id=0l;").append("\n")
        			.append("\t").append("try{").append("\n")
        			.append("\t\t").append("String operMethod=\"\";").append("\n")
        			.append("\t\t").append("HashMap map = new HashMap();").append("\n")
        			.append("\t\t").append("request.getParameterNames();").append("\n")
        			.append("\t\t").append("SimpleDateFormat format= new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");").append("\n")
        			.append("\n")
        			.append("\t\t").append("//获取请求参数").append("\n")
        			.append("\t\t").append("Enumeration pNames=request.getParameterNames();").append("\n")
        			.append("\t\t").append("while(pNames.hasMoreElements()){").append("\n")
        			.append("\t\t\t").append("String name=(String)pNames.nextElement();").append("\n")
        			.append("\t\t\t").append("String value=request.getParameter(name);").append("\n")
        			.append("\t\t\t").append("//时间戳就不要了").append("\n")
        			.append("\t\t\t").append("if(\"v\".equals(name)").append(FilterID).append("){").append("\n")
        			.append("\t\t\t\t").append("continue;").append("\n")
        			.append("\t\t\t").append("}").append("\n")
        			.append("\t\t\t").append("else if(\"oper\".equals(name)){").append("\n")
        			.append("\t\t\t\t").append("operMethod=value;").append("\n")
        			.append("\t\t\t").append("}").append("\n")
        			.append("\t\t\t").append("else{").append("\n")
        			.append("\t\t\t\t").append("//日期转换").append("\n")	        			
        			.append("\t\t\t\t").append(FormatDate)
        			.append("\t\t\t\t").append("else if(!StringUtils.isEmpty(value)){").append("\n")	        
        			.append("\t\t\t\t\t").append("map.put(name, value);").append("\n")
        			.append("\t\t\t\t").append("}").append("\n")
        			.append("\t\t\t").append("}").append("\n")
        			.append("\t\t").append("}").append("\n")
        			.append("\n")
        			.append("\t\t").append("if(operMethod.equals(\"edit\")){").append("\n")
        			.append("\t\t\t").append(DaoAndManager_prefix).append("Manager.update").append(domainName).append("(map);").append("\n")
        			.append("\t\t").append("}else{").append("\n")
        			.append("\t\t\t").append("id =").append(DaoAndManager_prefix).append("Manager.add").append(domainName).append("(map);").append("\n")
        			.append("\t\t").append("}").append("\n")
        			.append("\t").append("}").append("\n")
        			.append("\t").append("catch (ManagerException e) {").append("\n")
        			.append("\t\t").append("log.error(\"save").append(domainName).append("() Error:\"+ e.getMessage());").append("\n")
        			.append("\t\t").append("result=\"error\";").append("\n")
        			.append("\t").append("}").append("\n")
        			.append("\t\t").append("response.setContentType(\"text/html; charset=UTF-8\");").append("\n")
        			.append("\t\t").append("JSONObject json = new JSONObject();").append("\n")
        			.append("\t\t").append("json.put(\"result\", result);").append("\n")
        			.append("\t\t").append("json.put(\"id\",id);").append("\n")
        			.append("\t\t").append("response.getWriter().print(json.toString());").append("\n")
        			
        			.append("\t\t").append("return null;").append("\n")
        			.append("\t").append("} ").append("\n\n\n");
        			
        
        Controller.append("\t").append("@RequestMapping(\"get").append(domainName).append("ByCondition\")").append("\n")
        		.append("\t").append("public String get").append(domainName).append("ByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {").append("\n")
        		.append("\n")
        		.append("\t\t").append("int pageSize = 10;").append("\n")
        		.append("\t\t").append("int pageNo = 1;").append("\n")
        		.append("\t\t").append("String filters=\"\";").append("\n")	      
        		.append("\t\t").append("String sidx=\"").append(primaryKey).append("\";").append("\n")
        		.append("\t\t").append("String sord=\"desc\";").append("\n")
        		.append("\t\t").append("String oper=\"\";").append("\n")
		        		
        		
        		.append("\t\t").append("HashMap map = new HashMap();").append("\n")
        		.append("\t\t").append("if(request.getParameter(\"filters\")!=null){").append("\n")
        		.append("\t\t\t").append("filters=request.getParameter(\"filters\");").append("\n")	
        		.append("\t\t\t").append("map=DateJsonValueProcessor.getQueryMap(filters);").append("\n")		        		
        		.append("\t\t").append("}").append("\n")	
        		
        		.append("\t\t").append("if ( !StringUtils.isEmpty(request.getParameter(\"sidx\"))){").append("\n")
        		.append("\t\t\t").append("sidx=request.getParameter(\"sidx\");").append("\n")
        		.append("\t\t").append("}").append("\n")

        		.append("\t\t").append("if ( !StringUtils.isEmpty(request.getParameter(\"sidx\"))  && !StringUtils.isEmpty(request.getParameter(\"sord\")) ){").append("\n")
        		.append("\t\t\t").append("sord=request.getParameter(\"sord\");").append("\n")
        		.append("\t\t").append("}").append("\n")		
        		
        		
        		.append("\t\t").append("sidx=DateJsonValueProcessor.camel2Underscore(sidx);").append("\n")
        		
        		.append("\t\t").append("map.put(\"sidx\",sidx);").append("\n")
        		.append("\t\t").append("map.put(\"sord\",sord);").append("\n\n")	
        		
.append("\t\t").append("if ( request.getParameter(\"").append(primaryKey).append("\")!=null ){").append("\n")
.append("\t\t\t").append("map.put(\"").append(primaryKey).append("\",request.getParameter(\"").append(primaryKey).append("\"));").append("\n")
.append("\t\t").append("}").append("\n")	        		
        		
        		.append("\t\t").append("if ( request.getParameter(\"page\")!=null )").append("\n")	
        		.append("\t\t\t").append("pageNo = Integer.parseInt(request.getParameter(\"page\"));").append("\n\n")	
	
        		.append("\t\t").append("if ( request.getParameter(\"rows\")!=null )").append("\n")	
        		.append("\t\t\t").append("pageSize = Integer.parseInt(request.getParameter(\"rows\"));").append("\n\n")		     
        		
        		.append("\t\t").append("if ( request.getParameter(\"oper\")!=null )").append("\n")	
        		.append("\t\t\t").append("map.put(\"oper\",request.getParameter(\"oper\"));").append("\n\n")		        		
        		
        		.append("\t\t").append("int totalRecord =")
        			.append(DaoAndManager_prefix).append("Manager.count").append(domainName).append("ByCondition(map);").append("\n")	
        		.append("\t\t").append("PageBean<").append(domainName).append("> pageBean = new PageBean<").append(domainName).append(">(pageSize, pageNo, totalRecord);").append("\n\n")	
        		.append("\t\t").append("map.put(\"start\", pageBean.getStartNum());").append("\n")
        		.append("\t\t").append("map.put(\"end\", pageBean.getPageSize());").append("\n")
        		.append("\t\t").append("List<").append(domainName).append("> ")
        			.append(domainName.substring(0, 1).toLowerCase()).append(domainName.substring(1)).append("List =")
        			.append(DaoAndManager_prefix).append("Manager.get").append(domainName).append("ByCondition(map);").append("\n")
        		.append("\t\t").append("pageBean.setList(").append(domainName.substring(0, 1).toLowerCase()).append(domainName.substring(1)).append("List);").append("\n")
        		
        		.append("\t\t").append("if(!StringUtils.isEmpty((String) map.get(\"oper\")) && map.get(\"oper\").equals(\"excel\")) {").append("\n")
        		.append("\t\t\t").append("ExcelUtil<").append(domainName).append("> excelUtil = new ExcelUtil<").append(domainName).append(">();").append("\n")
        		.append("\t\t\t").append("excelUtil.exportExcel(\""+tableCaption+"\", pageBean, response);").append("\n")
        		.append("\t\t").append("}else{").append("\n")
        		.append("\t\t\t").append("DateJsonValueProcessor.writeJSON(pageBean,response);").append("\n")
        		.append("\t\t").append("}").append("\n")
        		.append("\t\t").append("return null;").append("\n")
        		.append("\t").append("} ").append("\n\n\n");
    		
        bw.write(Controller.toString());	        
        bw.close();	  
        System.out.println("Generator "+locatin_project + location_controller+ DaoAndManager_prefix + "Controller successful");
    }	    

    public static void generatorDAO() throws IOException{
        OutputStream outStream = new FileOutputStream(locatin_manager + location_dao+ DaoAndManager_prefix + "DAO", true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
        StringBuffer DAO = new StringBuffer();	 
        
        DAO.append("\t").append("public Long add").append(domainName).append("(HashMap map);").append("\n");
        DAO.append("\t").append("public void update").append(domainName).append("(HashMap map);").append("\n");	 
        DAO.append("\t").append("public List<").append(domainName).append("> ").append("get").append(domainName).append("ByCondition").append("(HashMap map);").append("\n");	
        DAO.append("\t").append("public int count").append(domainName).append("ByCondition").append("(HashMap map);").append("\n");	
        
        bw.write(DAO.toString());	        
        bw.close();	  
        System.out.println("Generator "+locatin_manager + location_dao+ DaoAndManager_prefix + "DAO successful");
    }
    
    public static void generatorDAOImpl() throws IOException{
        OutputStream outStream = new FileOutputStream(locatin_manager + location_dao_impl+ DaoAndManager_prefix + "DAOImpl", true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
        StringBuffer DAOImpl = new StringBuffer();	 
        
        DAOImpl.append("\t").append("public Long add").append(domainName).append("(HashMap map) {").append("\n")
        	.append("\t\t").append("return (Long) getSqlMapClientTemplate().insert(\"add").append(domainName).append("\", map);").append("\n")
        	.append("\t").append("}").append("\n\n");
        	
        DAOImpl.append("\t").append("public void update").append(domainName).append("(HashMap map) {").append("\n")
        	.append("\t\t").append("getSqlMapClientTemplate().insert(\"update").append(domainName).append("\", map);").append("\n")
        	.append("\t").append("}").append("\n\n");
        
        
        DAOImpl.append("\t").append("public List<").append(domainName).append("> ").append("get").append(domainName).append("ByCondition").append("(HashMap map) {").append("\n")
        	.append("\t\t").append("return getSqlMapClientTemplate().queryForList(\"get").append(domainName).append("ByCondition").append("\", map);").append("\n")
        	.append("\t").append("}").append("\n\n");
        
        DAOImpl.append("\t").append("public int count").append(domainName).append("ByCondition").append("(HashMap map) {").append("\n")	
        	.append("\t\t").append("return (Integer)getSqlMapClientTemplate().queryForObject(\"count").append(domainName).append("ByCondition").append("\", map);").append("\n")
        	.append("\t").append("}").append("\n\n");
        
        bw.write(DAOImpl.toString());	        
        bw.close();	  
        System.out.println("Generator "+locatin_manager + location_dao_impl+ DaoAndManager_prefix + "DAOImpl successful");
    }	    
    
    public static void generatorManager() throws IOException{
        OutputStream outStream = new FileOutputStream(locatin_manager + location_manager+ DaoAndManager_prefix + "Manager", true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
        StringBuffer Manager = new StringBuffer();	 
        
        Manager.append("\t").append("public Long add").append(domainName).append("(HashMap map) throws ManagerException;").append("\n");
        Manager.append("\t").append("public void update").append(domainName).append("(HashMap map) throws ManagerException;").append("\n");	 
        Manager.append("\t").append("public List<").append(domainName).append("> ").append("get").append(domainName).append("ByCondition").append("(HashMap map) throws ManagerException;").append("\n");	
        Manager.append("\t").append("public int count").append(domainName).append("ByCondition").append("(HashMap map) throws ManagerException;").append("\n");		        
        
        bw.write(Manager.toString());	        
        bw.close();	  
        System.out.println("Generator "+locatin_manager + location_manager+ DaoAndManager_prefix + "Manager successful");
    }	    
    
    public static void generatorManagerImpl() throws IOException{
        OutputStream outStream = new FileOutputStream(locatin_manager + location_manager_impl+ DaoAndManager_prefix + "ManagerImpl", true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
        StringBuffer ManagerImpl = new StringBuffer();	 
        
        ManagerImpl.append("\t").append("public Long add").append(domainName).append("(HashMap map) throws ManagerException {").append("\n")
        			.append("\t\t").append("try {").append("\n")
        			.append("\t\t\t").append("return ").append(DaoAndManager_prefix).append("DAO.").append("add").append(domainName).append("(map);").append("\n")
        			.append("\t\t").append("} catch (Exception e) {").append("\n")
        			.append("\t\t\t").append("throw new ManagerException(ErrorCode.DB_ERROR, e);").append("\n")
        			.append("\t\t").append("}").append("\n")
        			.append("\t").append("}").append("\n\n");
        
        ManagerImpl.append("\t").append("public void update").append(domainName).append("(HashMap map) throws ManagerException {").append("\n")
        			.append("\t\t").append("try {").append("\n")
        			.append("\t\t\t").append(DaoAndManager_prefix).append("DAO.").append("update").append(domainName).append("(map);").append("\n")
        			.append("\t\t").append("} catch (Exception e) {").append("\n")
        			.append("\t\t\t").append("throw new ManagerException(ErrorCode.DB_ERROR, e);").append("\n")
        			.append("\t\t").append("}").append("\n")
        			.append("\t").append("}").append("\n\n");
        			
        ManagerImpl.append("\t").append("public List<").append(domainName).append("> ").append("get").append(domainName).append("ByCondition").append("(HashMap map) throws ManagerException {").append("\n")
        			.append("\t\t").append("try {").append("\n")
        			.append("\t\t\t").append("return ").append(DaoAndManager_prefix).append("DAO.").append("get").append(domainName).append("ByCondition").append("(map);").append("\n")
        			.append("\t\t").append("} catch (Exception e) {").append("\n")
        			.append("\t\t\t").append("throw new ManagerException(ErrorCode.DB_ERROR, e);").append("\n")
        			.append("\t\t").append("}").append("\n")
        			.append("\t").append("}").append("\n\n");	
        
        ManagerImpl.append("\t").append("public int count").append(domainName).append("ByCondition").append("(HashMap map) throws ManagerException {").append("\n")
        			.append("\t\t").append("try {").append("\n")
        			.append("\t\t\t").append("return ").append(DaoAndManager_prefix).append("DAO.").append("count").append(domainName).append("ByCondition").append("(map);").append("\n")
        			.append("\t\t").append("} catch (Exception e) {").append("\n")
        			.append("\t\t\t").append("throw new ManagerException(ErrorCode.DB_ERROR, e);").append("\n")
        			.append("\t\t").append("}").append("\n")
        			.append("\t").append("}").append("\n\n");			        
        
        bw.write(ManagerImpl.toString());	        
        bw.close();	  
        System.out.println("Generator "+locatin_manager + location_manager_impl+ DaoAndManager_prefix + "ManagerImpl successful");
    }	    
    
    public static void generatorJsp(ResultSetMetaData metaData) throws SQLException, IOException{
        OutputStream outStream = new FileOutputStream(locatin_project + location_jsp+ domainName + ".jsp", true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
        StringBuffer head = new StringBuffer();
        StringBuffer pageContent = new StringBuffer();	        
        StringBuffer colModel = new StringBuffer();
        StringBuffer addFunc = new StringBuffer();
        StringBuffer editFunc = new StringBuffer();
        StringBuffer toolsBar = new StringBuffer();
        StringBuffer exportBar = new StringBuffer();
        
        genHeader(head, package_domain,"jsp");	 
        
        int columnCount = metaData.getColumnCount();
        String hasNextComma=",";
        for (int i = 1; i <= columnCount; i++) {
            String fieldName = toCamel(metaData.getColumnName(i).toLowerCase());
//        	String fieldName = metaData.getColumnName(i).toLowerCase();
            String colName=metaData.getColumnName(i).toLowerCase();
            
            //最后一行不需要,
            if(i==columnCount){
            	hasNextComma="";
            }
            
            //主键不更新
            if(colName.equals(primaryKey)){
            	colModel.append("\t\t\t").append("{name:\"").append(fieldName).append("\", index:\"")
            			.append(fieldName).append("\", editable:true, width:100,editoptions:{readonly:true}}")
            			.append(hasNextComma)
            			.append("\n");
            }
            else if ("DATE".equals(metaData.getColumnTypeName(i))|| "DATETIME".equals(metaData.getColumnTypeName(i))) {
            	colModel.append("\t\t\t").append("{name:\"").append(fieldName).append("\", index:\"").append(fieldName).append("\", editable:true, width:100,").append("\n")
	            	.append("\t\t\t\t").append("formatter:'date',formoptions:{elmsuffix:'(*)'},").append("\n") 
	            	.append("\t\t\t\t\t").append("formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'},").append("\n")
	            	.append("\t\t\t\t\t").append("editoptions:{size:50, dataInit:function(el){").append("\n")
	            	.append("\t\t\t\t\t\t").append("$(el).datetimepicker(global_datetime_parameter);").append("\n")
	            	.append("\t\t\t\t\t").append("},").append("\n") 
	            	.append("\t\t\t\t").append("},").append("\n") 		            	
	            	.append("\t\t\t\t").append("searchoptions : {").append("\n")   
	            	.append("\t\t\t\t\t").append("dataInit : function(el) {").append("\n")   
                    .append("\t\t\t\t\t\t").append("$(el).datetimepicker(global_datetime_parameter);").append("\n")   
                    .append("\t\t\t\t\t").append("}").append("\n")
                    .append("\t\t\t\t").append("}").append("\n")	                    
	            	.append("\t\t\t").append("}")
	            	.append(hasNextComma)
	            	.append("\n");  
            }
            else{
            	colModel.append("\t\t\t").append("{name:\"").append(fieldName).append("\", index:\"")
            			.append(fieldName).append("\", editable:true, width:100}")
            			.append(hasNextComma)
            			.append("\n");
            }
        }	 
        colModel.append("\t").append("],").append("\n")
        		.append("\t").append("viewrecords: true,").append("\n")
        		.append("\t").append("multiselect: false,").append("\n")
                .append("\t").append("rownumbers: true,").append("\n")                                                             
                .append("\t").append("rowNum: 10,").append("\n")                                                                      
                .append("\t").append("rowList: [5,10,20,50,100],").append("\n")                                                            
                .append("\t").append("prmNames: {search: \"search\"},").append("\n")                                                    
                .append("\t").append("jsonReader: {").append("\n")                                                                        
                .append("\t\t").append("root:\"list\",     	 //包含实际数据的数组").append("\n") 
                .append("\t\t").append("page:\"currentPage\",  //当前页").append("\n")     
                .append("\t\t").append("total:\"totalPages\",  //总页数").append("\n")                                              
                .append("\t\t").append("records: \"totalRows\",//查询出的记录数").append("\n")           
                .append("\t\t").append("repeatitems : false").append("\n")                                                            
                .append("\t").append("},").append("\n")                                                                                 
                .append("\t").append("pager:\"").append(domainName).append("Pager\",").append("\n")                                                               
                .append("\t").append("caption: \"").append(tableCaption).append("\",").append("\n")                                                             
                .append("\t").append("hidegrid: false").append("\n")        
                .append("});").append("\n\n"); 	        		
        
        
    	addFunc.append("var add").append(domainName).append("=function(){").append("\n")	
				.append("\t").append("jQuery(\"#").append(domainName).append("Table\").jqGrid('editGridRow','new',{").append("\n")	
				.append("\t\t").append("addCaption: \"正在添加数据中\",").append("\n")	
				.append("\t\t").append("editCaption: \"正在编辑选中行\",").append("\n")	
				.append("\t\t").append("bSubmit: \"确认\",").append("\n")	
				.append("\t\t").append("bCancel: \"取消\",").append("\n")	
				.append("\t\t").append("processData: \"Processing...\",").append("\n")	
				.append("\t\t").append("closeAfterAdd:true,").append("\n")	
				.append("\t\t").append("closeAfterEdit:true,").append("\n")	
				.append("\t\t").append("closeOnEscape:true,").append("\n")
				.append("\t\t").append("reloadAfterSubmit:false,").append("\n")
				.append("\t\t").append("url : \"${pageContext.request.contextPath}/").append(RequestMappingPath).append("/save").append(domainName).append("?v=\"+v,").append("\n")
	    	.append("\t\t").append("afterComplete: function (response, postdata, formid) {").append("\n")	
		    .append("\t\t\t").append("//取服务器返回信息").append("\n")	
			.append("\t\t\t").append("var ret =eval('(' + response.responseText + ')');").append("\n")	
			.append("\t\t\t").append("var newId='';").append("\n")	
			.append("\t\t\t").append("var oper='';").append("\n")	
			.append("\t\t\t").append("//取客户端提交的数据").append("\n")	
			.append("\t\t\t").append("$.each(postdata,function(key, value){").append("\n")	
			.append("\t\t\t\t").append("if(key=='").append(primaryKey).append("'){").append("\n")	
			.append("\t\t\t\t\t").append("newId=value;").append("\n")	
			.append("\t\t\t\t").append("}").append("\n")
			.append("\t\t\t\t").append("if(key=='oper'){").append("\n")
			.append("\t\t\t\t\t").append("oper=value;").append("\n")
			.append("\t\t\t\t").append("}").append("\n")
			.append("\t\t\t").append("});").append("\n")
			.append("\t\t\t").append("if(oper=='edit'){").append("\n")
			.append("\t\t\t\t").append("var msg='修改记录失败';").append("\n")
			.append("\t\t\t\t").append("if(ret.result==\"success\"){").append("\n")
			.append("\t\t\t\t\t").append("msg='修改记录成功';").append("\n")
			.append("\t\t\t\t\t").append("//jQuery(\"#").append(domainName).append("Table\").jqGrid('setGridParam',{url:\"${pageContext.request.contextPath}/").append(RequestMappingPath).append("/get").append(domainName).append("ByCondition?v=\"+v});").append("\n")
			.append("\t\t\t\t\t").append("//jQuery(\"#").append(domainName).append("Table\").jqGrid('setGridParam', {postData:{").append(primaryKey).append(":newId}}).trigger('reloadGrid');").append("\n")				
			.append("\t\t\t\t").append("}").append("\n")
			.append("\t\t\t\t").append("alert(msg);	").append("\n")			
			.append("\t\t\t").append("}").append("\n")
			.append("\t\t\t").append("else{").append("\n")
			.append("\t\t\t\t").append("var msg='保存记录失败';").append("\n")
			.append("\t\t\t\t").append("if(ret.result==\"success\"){").append("\n")
			.append("\t\t\t\t\t").append("newId=ret.id;").append("\n")
			.append("\t\t\t\t\t").append("msg='保存记录成功';").append("\n")
			.append("\t\t\t\t\t").append("jQuery(\"#").append(domainName).append("Table\").jqGrid('setGridParam',{url:\"${pageContext.request.contextPath}/").append(RequestMappingPath).append("/get").append(domainName).append("ByCondition?v=\"+v});").append("\n")
			.append("\t\t\t\t\t").append("jQuery(\"#").append(domainName).append("Table\").jqGrid('setGridParam', {postData:{").append(primaryKey).append(":newId}}).trigger('reloadGrid');").append("\n")							
			.append("\t\t\t\t").append("}").append("\n")
			.append("\t\t\t\t").append("alert(msg);").append("\n")				
			.append("\t\t\t").append("}").append("\n")
			.append("\t\t").append("}").append("\n")					
				.append("\t").append("});").append("\n")	 
				.append("}").append("\n\n");
    
    	
    	editFunc.append("var edit").append(domainName).append("=function(){").append("\n")
		    	.append("var row = jQuery(\"#").append(domainName).append("Table\").jqGrid('getGridParam','selrow');").append("\n")	
		    	.append("if( row != null ) jQuery(\"#").append(domainName).append("Table\").jqGrid('editGridRow',row,{").append("\n")
				.append("\t\t").append("addCaption: \"正在添加数据中\",").append("\n")	
				.append("\t\t").append("editCaption: \"正在编辑选中行\",").append("\n")	
				.append("\t\t").append("bSubmit: \"确认\",").append("\n")	
				.append("\t\t").append("bCancel: \"取消\",").append("\n")	
				.append("\t\t").append("processData: \"Processing...\",").append("\n")	
				.append("\t\t").append("closeAfterAdd:true,").append("\n")	
				.append("\t\t").append("closeAfterEdit:true,").append("\n")	
				.append("\t\t").append("closeOnEscape:true,").append("\n")	
				.append("\t\t").append("reloadAfterSubmit:true,").append("\n")	
				.append("\t\t").append("width: 800,").append("\n")	
				.append("\t\t").append("height: 'auto',").append("\n")	
				.append("\t\t").append("url : \"${pageContext.request.contextPath}/").append(RequestMappingPath).append("/save").append(domainName).append("?v=\"+v,").append("\n")
	    	.append("\t\t").append("afterComplete: function (response, postdata, formid) {").append("\n")	
		    .append("\t\t\t").append("//取服务器返回信息").append("\n")	
			.append("\t\t\t").append("var ret =eval('(' + response.responseText + ')');").append("\n")	
			.append("\t\t\t").append("var newId='';").append("\n")	
			.append("\t\t\t").append("var oper='';").append("\n")	
			.append("\t\t\t").append("//取客户端提交的数据").append("\n")	
			.append("\t\t\t").append("$.each(postdata,function(key, value){").append("\n")	
			.append("\t\t\t\t").append("if(key=='").append(primaryKey).append("'){").append("\n")	
			.append("\t\t\t\t\t").append("newId=value;").append("\n")	
			.append("\t\t\t\t").append("}").append("\n")
			.append("\t\t\t\t").append("if(key=='oper'){").append("\n")
			.append("\t\t\t\t\t").append("oper=value;").append("\n")
			.append("\t\t\t\t").append("}").append("\n")
			.append("\t\t\t").append("});").append("\n")
			.append("\t\t\t").append("if(oper=='edit'){").append("\n")
			.append("\t\t\t\t").append("var msg='修改记录失败';").append("\n")
			.append("\t\t\t\t").append("if(ret.result==\"success\"){").append("\n")
			.append("\t\t\t\t\t").append("msg='修改记录成功';").append("\n")
			.append("\t\t\t\t\t").append("//jQuery(\"#").append(domainName).append("Table\").jqGrid('setGridParam',{url:\"${pageContext.request.contextPath}/").append(RequestMappingPath).append("/get").append(domainName).append("ByCondition?v=\"+v});").append("\n")
			.append("\t\t\t\t\t").append("//jQuery(\"#").append(domainName).append("Table\").jqGrid('setGridParam', {postData:{").append(primaryKey).append(":newId}}).trigger('reloadGrid');").append("\n")				
			.append("\t\t\t\t").append("}").append("\n")
			.append("\t\t\t\t").append("alert(msg);	").append("\n")			
			.append("\t\t\t").append("}").append("\n")
			.append("\t\t\t").append("else{").append("\n")
			.append("\t\t\t\t").append("var msg='保存记录失败';").append("\n")
			.append("\t\t\t\t").append("if(ret.result==\"success\"){").append("\n")
			.append("\t\t\t\t\t").append("newId=ret.id;").append("\n")
			.append("\t\t\t\t\t").append("msg='保存记录成功';").append("\n")
			.append("\t\t\t\t\t").append("jQuery(\"#").append(domainName).append("Table\").jqGrid('setGridParam',{url:\"${pageContext.request.contextPath}/").append(RequestMappingPath).append("/get").append(domainName).append("ByCondition?v=\"+v});").append("\n")
			.append("\t\t\t\t\t").append("jQuery(\"#").append(domainName).append("Table\").jqGrid('setGridParam', {postData:{").append(primaryKey).append(":newId}}).trigger('reloadGrid');").append("\n")							
			.append("\t\t\t\t").append("}").append("\n")
			.append("\t\t\t\t").append("alert(msg);").append("\n")				
			.append("\t\t\t").append("}").append("\n")
			.append("\t\t").append("}").append("\n")					
				.append("\t").append("});").append("\n")	
				.append("\t").append("else alert(\"Please Select Row\");").append("\n")
				.append("}").append("\n\n");
    	
    	
    	//定义工具条
    	toolsBar.append("\t").append("$(\"#").append(domainName).append("Table\").jqGrid('navGrid','#").append(domainName).append("Pager',").append("\n")
				.append("\t\t").append("{del:false,").append("\n")
				.append("\t\t").append("add:true,").append("\n")
				.append("\t\t").append("addtext:'新增',").append("\n")
				.append("\t\t").append("edit:true,").append("\n")
				.append("\t\t").append("edittext:'编辑',").append("\n")
				.append("\t\t").append("search:false,").append("\n")
				.append("\t\t").append("addfunc:add").append(domainName).append(",").append("\n")
				.append("\t\t").append("editfunc:edit").append(domainName).append(",").append("\n")
				.append("\t\t").append("}); ").append("\n")
				.append("\t").append("$(\"#").append(domainName).append("Table\").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});").append("\n");
    	//导出excel
    	exportBar.append("\t").append("$(\"#").append(domainName).append("Table\").jqGrid('navButtonAdd','#").append(domainName).append("Pager',{").append("\n")
    			 .append("\t\t").append("caption:\"导出excel\",").append("\n")
    			 .append("\t\t").append("onClickButton : function () {").append("\n")
    			 .append("\t\t\t").append("$(\"#").append(domainName).append("Table\").jqGrid('excelExport',{\"url\":\"${pageContext.request.contextPath}/").append(RequestMappingPath).append("/get").append(domainName).append("ByCondition?v=\"+v});").append("\n")
    			 .append("\t\t").append("}").append("\n")
    			 .append("\t").append("});").append("\n")
    			 .append("}").append("\n");
    			 
    	getPage(pageContent);
        bw.write(head.toString());
        bw.write(colModel.toString());
        bw.write(addFunc.toString());
        bw.write(editFunc.toString());	  
        bw.write(toolsBar.toString());	 
        bw.write(exportBar.toString());	 
        bw.write("</script>\n\n");	 
        bw.write(pageContent.toString());	        
        bw.close();	  
        System.out.println("Generator "+locatin_project + location_jsp+ domainName + ".jsp successful");
    }
    
    public static void generatorDomain(ResultSetMetaData metaData) throws SQLException, IOException{
        OutputStream outStream = new FileOutputStream(locatin_manager + locaton_domain+ domainName + ".java", true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
        StringBuffer head = new StringBuffer();
        StringBuffer define = new StringBuffer();	        
        StringBuffer getSet = new StringBuffer();
        
        genHeader(head, package_domain,"domain");	    	
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String fieldName = toCamel(metaData.getColumnName(i).toLowerCase());
//        	String fieldName = metaData.getColumnName(i).toLowerCase();
            if ("BIGINT".equals(metaData.getColumnTypeName(i))) {
            	define.append("    private Long " + fieldName + ";\n");
            	getSet.append("    public Long get" + toCaptial(fieldName) + "() {\n").append("        return " + fieldName + ";\n").append("    }\n\n").append("    public void set" + toCaptial(fieldName) + "(Long " + fieldName + ") {\n").append("        this." + fieldName + "=" + fieldName + ";\n").append("    }\n");
            } 
            else if ("INT".equals(metaData.getColumnTypeName(i))) {
            	define.append("    private Integer " + fieldName + ";\n");
            	getSet.append("    public Integer get" + toCaptial(fieldName) + "() {\n").append("        return " + fieldName + ";\n").append("    }\n\n").append("    public void set" + toCaptial(fieldName) + "(Integer " + fieldName + ") {\n").append("        this." + fieldName + "=" + fieldName + ";\n").append("    }\n");
            }	
            else if ("FLOAT".equals(metaData.getColumnTypeName(i))) {
            	define.append("    private Float " + fieldName + ";\n");
            	getSet.append("    public Float get" + toCaptial(fieldName) + "() {\n").append("        return " + fieldName + ";\n").append("    }\n\n").append("    public void set" + toCaptial(fieldName) + "(Float " + fieldName + ") {\n").append("        this." + fieldName + "=" + fieldName + ";\n").append("    }\n");
            }	            
            else if ("DATE".equals(metaData.getColumnTypeName(i))) {
            	define.append("    private Date " + fieldName + ";\n");
            	getSet.append("    public Date get" + toCaptial(fieldName) + "() {\n").append("        return " + fieldName + ";\n").append("    }\n\n").append("    public void set" + toCaptial(fieldName) + "(Date " + fieldName + ") {\n").append("        this." + fieldName + "=" + fieldName + ";\n").append("    }\n");
            	if(head.indexOf("import java.util.Date")<0){
	            	head.append("import java.util.Date;\n");	            		
            	}
            } 
            else if ("DATETIME".equals(metaData.getColumnTypeName(i))) {
            	define.append("    private Date " + fieldName + ";\n");
            	getSet.append("    public Date get" + toCaptial(fieldName) + "() {\n").append("        return " + fieldName + ";\n").append("    }\n\n").append("    public void set" + toCaptial(fieldName) + "(Date " + fieldName + ") {\n").append("        this." + fieldName + "=" + fieldName + ";\n").append("    }\n");
            	if(head.indexOf("import java.util.Date")<0){
	            	head.append("import java.util.Date;\n");	            		
            	}
            }	            
            else {
            	define.append("    private String " + fieldName + ";\n");
            	getSet.append("    public String get" + toCaptial(fieldName) + "() {\n").append("        return " + fieldName + ";\n").append("    }\n\n").append("    public void set" + toCaptial(fieldName) + "(String " + fieldName + ") {\n").append("        this." + fieldName + "=" + fieldName + ";\n").append("    }\n");
            }
        }	 
        
        head.append("public class "+domainName+ "{\n");
        bw.write(head.toString());
        bw.write(define.toString());	        
        bw.write(getSet.toString());
        bw.write("}");
        bw.close();	        
        System.out.println("Generator "+locatin_manager + locaton_domain+ domainName + ".java successful");
    }
    
    
    public static void generatorSQLMap(ResultSetMetaData metaData) throws SQLException, IOException{
        OutputStream outStream = new FileOutputStream(locatin_manager + location_sqlmaps+ domainName + "SQL.xml", true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
        StringBuffer head = new StringBuffer();
        StringBuffer resultMap = new StringBuffer();	
        StringBuffer insertHead = new StringBuffer();
        StringBuffer insertMid = new StringBuffer();	
        StringBuffer insertTail = new StringBuffer();	  
        StringBuffer insertField = new StringBuffer();
        StringBuffer insertValue = new StringBuffer();
        StringBuffer update = new StringBuffer();	     
        StringBuffer countByCondition = new StringBuffer();	 
        StringBuffer countField = new StringBuffer();	 
        StringBuffer getByCondition = new StringBuffer();	 
        StringBuffer getField = new StringBuffer();	 	      
        //Additional拓展
        
        
        genHeader(head, package_domain,"sqlMap");	  
        //1.namespace
        head.append("<sqlMap namespace=\"").append(domainName).append("SQL\">").append("\n");
        //2.resultMap
        resultMap.append("<resultMap id=\"").append(domainName).append("\"    type=\"").append(package_domain).append(".").append(domainName).append("\">").append("\n");
        //3.insert
        insertHead.append("<insert id=\"add").append(domainName).append("\"  parameterType=\"java.util.HashMap\">").append("\n");
        insertHead.append("INSERT INTO ").append(tableName).append(" ( ").append("\n")
                  .append("<trim prefix=\"\" prefixOverrides=\",\">  ").append("\n");
        //4.update
        update.append("<update id=\"update").append(domainName).append("\"  parameterType=\"java.util.HashMap\">").append("\n");
        update.append("update ").append(tableName).append("\n").append("<set>").append("\n");
        //5.countByCondition
        countByCondition.append("<select id=\"count").append(domainName).append("ByCondition").append("\"  parameterType=\"java.util.HashMap\"")
        				.append(" resultType=\"java.lang.Integer\">").append("\n")
        				.append("\t select count(1) from ").append(tableName).append("\n").append("<where>").append("\n");
        //6.getByCondition
        getByCondition.append("<select id=\"get").append(domainName).append("ByCondition").append("\"  parameterType=\"java.util.HashMap\"")
        			  .append(" resultMap=\"").append(domainName).append("\">").append("\n")
        			  .append("select ").append(primaryKey).append("\n");
        

        
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String fieldName = toCamel(metaData.getColumnName(i).toLowerCase());
//        	String fieldName = metaData.getColumnName(i).toLowerCase();	            
            String colName=metaData.getColumnName(i).toLowerCase();
            resultMap.append("\t<result  property=\"").append(fieldName).append("\"  column=\"").append(colName).append("\"/>").append("\n");
            

            
            //主键不更新,也不需要插入自动生成
            if(!colName.equals(primaryKey)){
            	update.append("\t<if test=\"").append(fieldName).append("!=null\">").append("\n").append("\t\t").append(colName).append("=").append("#{").append(fieldName).append("}").append(",").append("\n").append("\t</if>").append("\n");	            	
            	insertField.append("\t<if test=\"").append(fieldName).append("!= null\" >").append(colName).append(",").append("</if>").append("\n");
            	insertValue.append("\t<if test=\"").append(fieldName).append("!= null\" >").append("#{").append(fieldName).append("}").append(",").append("</if>").append("\n");	            
            	getByCondition.append("\t\t").append(",").append(colName).append("\n");
            }
            //float要加上round(col,2)才能匹配
            if ("FLOAT".equals(metaData.getColumnTypeName(i))) {
            	countField.append("\t<if test=\"").append(fieldName).append("!=null\">").append("\n\t\t").append(" and ").append("round(").append(colName).append(",2)=").append("#{").append(fieldName).append("}").append("\n").append("\t</if>").append("\n");
	              getField.append("\t<if test=\"").append(fieldName).append("!=null\">").append("\n\t\t").append(" and ").append("round(").append(colName).append(",2)=").append("#{").append(fieldName).append("}").append("\n").append("\t</if>").append("\n");	            	
            }else{	            
	            countField.append("\t<if test=\"").append(fieldName).append("!=null\">").append("\n\t\t").append(" and ").append(colName).append("=").append("#{").append(fieldName).append("}").append("\n").append("\t</if>").append("\n");
	            getField.append("\t<if test=\"").append(fieldName).append("!=null\">").append("\n\t\t").append(" and ").append(colName).append("=").append("#{").append(fieldName).append("}").append("\n").append("\t</if>").append("\n");
            }
        }	 
		resultMap.append("</resultMap>").append("\n\n\n");
		insertMid.append(") VALUES (").append("\n");  
		insertTail.append("</trim>").append("\n")
					.append("<selectKey resultType=\"java.lang.Long\" keyProperty=\"").append(primaryKey).append("\">")
					.append("SELECT LAST_INSERT_ID()")
					.append("</selectKey>").append("\n")
		.append("</insert>").append("\n\n\n");
		update.append("</set>").append("\n").append("where ").append(primaryKey).append("=").append("#{").append(primaryKey).append("}").append("\n").append("</update>").append("\n\n\n");
		countField.append("</where>").append("\n").append("</select>").append("\n\n\n");
		getByCondition.append("from ").append(tableName).append("\n").append("<where>").append("\n");
		getField.append("</where>").append("\n")
				.append("<if test=\"sidx!= null\">").append("\n")
		        .append("order by").append("\t").append("$sidx$ $sord$").append("\n")
		     	.append("</if>").append("\n")
 
		.append("limit #{start}, #{end}").append("\n").append("</select>").append("\n\n\n");
		
        bw.write(head.toString());
        bw.write(resultMap.toString());	        
        bw.write(insertHead.toString());
        bw.write(insertField.toString());
        bw.write(insertMid.toString());
        bw.write(insertValue.toString());
        bw.write(insertTail.toString());
        bw.write(update.toString());
        bw.write(countByCondition.toString());
        bw.write(countField.toString());	    
        bw.write(getByCondition.toString());	 
        bw.write(getField.toString());	 
        bw.write("</sqlMap>");
        bw.close();	       
        System.out.println("Generator "+locatin_manager + location_sqlmaps+ domainName + "SQL.xml successful");	        
    }	    
    
    public static void getPage(StringBuffer pageContent){
    	pageContent.append("<table style='vertical-align:top'>").append("\n")
    	.append("<tr>").append("\n")
    	.append("\t<td style='vertical-align:top'>").append("\n")
    	.append("\t\t<table id=\"").append(domainName).append("Table\"></table>").append("\n")
    	.append("\t\t<div id=\"").append(domainName).append("Pager\"></div>").append("\n")
    	.append("\t</td>").append("\n")
    	.append("</tr>").append("\n")
    	.append("</table>").append("\n")
    	.append("</body>").append("\n")
    	.append("</html>").append("\n");	    	
    }
    
    private static void genHeader(StringBuffer head, String package_domain, String param) {
    	if("domain".equals(param)){
    		head.append("package " + package_domain + ";").append("\n\n");
    	}
    	if("sqlMap".equals(param)){
    		head.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\n")
    			.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >").append("\n");
    	}
    	if("jsp".equals(param)){
    		head.append("<%@ page isELIgnored=\"false\"%>").append("\n")
    		.append("<%@ page contentType=\"text/html; charset=UTF-8\"%>").append("\n")
    		.append("<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>").append("\n")
    		.append("<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\" %>").append("\n")
    		.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">").append("\n")
    		.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">").append("\n")
    		.append("<head>").append("\n")
    		.append("\t\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${cdn}/js/jqgrid/themes/redmond/jquery-ui-1.8.2.custom.css\" />").append("\n")
    		.append("\t\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${cdn}/js/jqgrid/themes/ui.jqgrid.css\" />").append("\n")
    		.append("\t\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${cdn}/css/jquery-ui-timepicker-addon.css\" />").append("\n")
    		.append("\n")		
    		.append("\t\t<script type=\"text/javascript\"  src=\"${cdn}/js/jquery-1.6.3.js\"></script>").append("\n")
    		.append("\t\t<script type=\"text/javascript\"  src=\"${cdn}/js/jqgrid/jquery-ui-1.8.2.custom.min.js\"></script>").append("\n")
    		.append("\t\t<script type=\"text/javascript\"  src=\"${cdn}/js/jqgrid/grid.locale-en.js\"></script>").append("\n")
    		.append("\t\t<script type=\"text/javascript\"  src=\"${cdn}/js/jqgrid/jquery.jqGrid.min.js\"></script>").append("\n")
    		.append("\t\t<script type=\"text/javascript\"  src=\"${cdn}/js/jqgrid/jquery-ui-timepicker-addon.js\"></script>").append("\n")
    		.append("<title>").append(jspTitle).append("</title>").append("\n")
    		.append("</head>").append("\n")
    		.append("<body>").append("\n")
    		.append("<script>").append("\n\n")
    		
    		.append("var global_datetime_parameter={").append("\n")
			.append("\t").append("closeText: \"完成\",").append("\n")
			.append("\t").append("currentText: \"今天\",").append("\n")
			.append("\t").append("showButtonPanel: true,").append("\n")
			.append("\t").append("prevText: \"前一月\",").append("\n")
			.append("\t").append("nextText: \"下一月\",").append("\n")
			.append("\t").append("changeMonth: true,").append("\n")
			.append("\t").append("changeYear: true,").append("\n")
			.append("\t").append("dateFormat: \"yy-mm-dd\",").append("\n")
			.append("\t").append("dayNames: [\"星期日\", \"星期一\", \"星期二\", \"星期三\", \"星期四\", \"星期五\", \"星期六\"],").append("\n")
			.append("\t").append(" dayNamesMin: [\"日\", \"一\", \"二\", \"三\", \"四\", \"五\", \"六\"],").append("\n")
			.append("\t").append("monthNames: [\"一月\",\"二月\",\"三月\",\"四月\",\"五月\",\"六月\",\"七月\",\"八月\",\"九月\",\"十月\",\"十一月\",\"十二月\"],").append("\n")
			.append("\t").append("monthNamesShort: [\"一月\",\"二月\",\"三月\",\"四月\",\"五月\",\"六月\",\"七月\",\"八月\",\"九月\",\"十月\",\"十一月\",\"十二月\"],").append("\n")
			.append("\t").append("timeFormat: \"HH:mm:ss\",").append("\n")
			.append("\t").append("showSecond: true,").append("\n")
			.append("\t").append("timeText:\"时间\",").append("\n")
			.append("\t").append("hourText:\"时\",").append("\n")
			.append("\t").append(" minuteText:\"分\",").append("\n")
			.append("\t").append("secondText:\"秒\"};").append("\n")
    		
			.append("$(document).ready(function(){").append("\n")  
			.append("\t").append("get").append(domainName).append("();").append("\n") 
			.append("});").append("\n\n\n") 	 
			
    		.append("function get").append(domainName).append("(){").append("\n")	
	    	.append("var myDate = new Date();").append("\n")	
	    	.append("var v =myDate.toLocaleTimeString();").append("\n")	
	    	.append("\n")	
	    	.append("\t").append("$(\"#").append(domainName).append("Table\").jqGrid({").append("\n")	                                                             
	    	.append("\t\t").append("url: \"${pageContext.request.contextPath}/").append(RequestMappingPath).append("/get").append(domainName).append("ByCondition?v=\"+v,").append("\n")	                                            
	    	.append("\t\t").append("datatype: \"json\",").append("\n")	                                                                       
	    	.append("\t\t").append("mtype: \"GET\",").append("\n")	       
	    	.append("\t\t").append("height: 200,").append("\n")	                                                                     
	    	.append("\t\t").append("width: 1500,").append("\n")	 
	    	.append("\t\t").append("shrinkToFit:false,").append("\n")	 
	    	.append("\t\t").append("//自定义表头colNames:['编号','商家编号','广告描述','展示位置','广告链接','开始时间', '结束时间','展示次数','用户访问次数','页面访问次数'] ,").append("\n")	                                                                  
	    	.append("\t\t").append("colModel: [").append("\n");
    		
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

    public static String toCaptial(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }	    
    
    public static String toLowerCaptial(String fieldName) {
        return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
    }	 
    
}
