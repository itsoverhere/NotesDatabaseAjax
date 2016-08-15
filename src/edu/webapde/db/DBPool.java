package edu.webapde.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class DBPool {
	
	private static DBPool singleton;
	private BasicDataSource dataSource;
	private static String username="root";
	private static String password="1234";
	private static String url="jdbc:mysql://localhost:3306/notesdb";
	
	private DBPool(){
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
	}
	
	public static DBPool getInstance(){
		if(singleton==null){
			singleton= new DBPool();
		}
		return singleton;
	}
	
	public Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
