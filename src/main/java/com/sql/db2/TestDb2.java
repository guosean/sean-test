package com.sql.db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDb2 {
	static String url = "jdbc:db2://10.1.253.203:50000/frontdb";
	static String user = "sui1";
	static String password = "ding-shan";
 public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	test1();
	test2();
}

private static void test2() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	Connection cnn = connect(url, user, password);
	String sql = " select * from USER_OPER_LOG_DETAIL fetch first 100000 rows only ";
	PreparedStatement ps = cnn.prepareStatement(sql );
	ResultSet rs = ps.executeQuery();
	if(null!=rs){
		 long begin = System.currentTimeMillis();
		 long end;
		 System.out.println(begin);
		while(rs.next()){
		}
		end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println("花费时间2:"+(end-begin));
	}
	rs.close();
	ps.close();
	cnn.close();
}

private static Connection connect(final String url,final String user,final String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
	Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
	return DriverManager.getConnection(url, user, password);
}
private static void test1() throws InstantiationException,
		IllegalAccessException, ClassNotFoundException, SQLException {
	Connection cnn = connect(url,user,password);
	String sql = " select * from USER_OPER_LOG_DETAIL ";
	PreparedStatement ps = cnn.prepareStatement(sql );
	ResultSet rs = ps.executeQuery();
	int size = 100000;
	if(null!=rs){
		 int index = 0;
		 long begin = System.currentTimeMillis();
		 long end;
		 System.out.println(begin);
		while(rs.next()){
			index++;
			if(index==size){
				end = System.currentTimeMillis();
				System.out.println(end);
				System.out.println("花费时间1:"+(end-begin));
				break;
			}
		}
	}
	rs.close();
	ps.close();
	cnn.close();
}
}
