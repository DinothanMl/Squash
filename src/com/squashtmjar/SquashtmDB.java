package com.squashtmjar;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class SquashtmDB {
	
	private int localPort = 3306;
	final private String host = "jdbc:mysql://127.0.0.1:"+localPort+"/squashtm";
	Properties prop = new Properties();
    InputStream input = null;
	
	private Connection connect = null;
	private Statement statement = null;
	
	//getter - Statement
	public Statement getStatement() {
		return statement;
	}

	//setter - Statement
	public Statement setStatement(Statement statement) {
		this.statement = statement;
		return statement;
	}
	
	public Statement dbConnect() throws IOException, ClassNotFoundException, SQLException {
		
		// load a properties file
		input = new FileInputStream("/home/auxenta/eclipse-workspace/SquashTMJar/src/com/squashtmjar/Config.properties");
		prop.load(input);
		Class.forName("com.mysql.jdbc.Driver");
		
		connect = (Connection) DriverManager.getConnection(host, prop.getProperty("user"), prop.getProperty("password"));
		System.out.println("Connection to database established!");
		return setStatement((Statement) connect.createStatement());
	}

}
