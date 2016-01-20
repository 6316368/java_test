package org.test.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.rdp.generator.model.db.Table;
import org.rdp.generator.utils.PropertiesProvider;

public class Test {
	private static Connection connection;
	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(PropertiesProvider.getProperty("jdbc.driver"));
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(PropertiesProvider.getProperty("jdbc.url"), PropertiesProvider.getProperty("jdbc.username"), PropertiesProvider.getProperty("jdbc.password"));
		}
		return connection;
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection conn = getConnection();
		DatabaseMetaData dbMetaData = conn.getMetaData();
		  System.out.println("数据库已知的用户: "+ dbMetaData.getUserName());   
          System.out.println("数据库的系统函数的逗号分隔列表: "+ dbMetaData.getSystemFunctions());   
          System.out.println("数据库的时间和日期函数的逗号分隔列表: "+ dbMetaData.getTimeDateFunctions());   
          System.out.println("数据库的字符串函数的逗号分隔列表: "+ dbMetaData.getStringFunctions());   
          System.out.println("数据库供应商用于 'schema' 的首选术语: "+ dbMetaData.getSchemaTerm());   
          System.out.println("数据库URL: " + dbMetaData.getURL());   
          System.out.println("是否允许只读:" + dbMetaData.isReadOnly());   
          System.out.println("数据库的产品名称:" + dbMetaData.getDatabaseProductName());   
          System.out.println("数据库的版本:" + dbMetaData.getDatabaseProductVersion());   
          System.out.println("驱动程序的名称:" + dbMetaData.getDriverName());   
          System.out.println("驱动程序的版本:" + dbMetaData.getDriverVersion());  
		ResultSet tables = dbMetaData.getTables("ssi2bbs", null, "PUB_CUSTOMER",  new String[]{"TABLE"});   
	    System.out.println(tables);//oracle.jdbc.driver.OracleResultSetImpl@401369
	    while(tables.next()) { 
	    	 String tname=tables.getString(3); //获取表名
	    	 System.out.println(tname);
	    }
	}
	
	
}
