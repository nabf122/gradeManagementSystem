package com.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	Connection conn;
	
	public void connect() {
		String dbconnUrl = "jdbc:mysql://192.168.101.23:13306/db명?serverTimezone=UTC";
		String user = "root";
		String password = "system";
		
		try {
			conn = DriverManager.getConnection(dbconnUrl, user, password);			
		} catch (SQLException e) {
			System.out.println("DB 연결 오류 : "+ e.getStackTrace());
		}

	}
}
