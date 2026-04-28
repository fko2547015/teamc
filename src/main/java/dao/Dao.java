package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {
	static DataSource ds;
	
	public Connection getConnection() throws Exception{
		InitialContext ic=new InitialContext();
		DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/student");
		return ds.getConnection();
	}

}
