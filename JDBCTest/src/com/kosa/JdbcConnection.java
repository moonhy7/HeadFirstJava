
package com.kosa;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class JdbcConnection {
 
	@SuppressWarnings("unused")
	public static void main(String args[]) throws SQLException {

//		OracleDataSource ods = new OracleDataSource();

//		/* Thin driver */
//
//		// 1
//		ods.setURL("jdbc:oracle:thin:@localhost:1521/xepdb1");
//		ods.setUser("ace");
//		ods.setPassword("me");
//		Connection conn1 = ods.getConnection();
//
//		DatabaseMetaData meta = conn1.getMetaData();
//		System.out.println("JDBC driver version is " + meta.getDriverVersion());
//
//		// 2
//		ods.setURL("jdbc:oracle:thin:ace/me@localhost:1521/xepdb1");
//		Connection conn2 = ods.getConnection();
//
//		/* Oracle Call Interface (OCI) driver */
//
//		// 1
//		ods.setURL("jdbc:oracle:oci8:@mydb");
//		ods.setUser("ace");
//		ods.setPassword("me");
//		Connection conn3 = ods.getConnection();
//
//		System.out.println("oci 드라이버로 접속 성공");
//		
//		// 2
//		ods.setURL("jdbc:oracle:oci8:ace/me@mydb");
//		Connection conn4 = ods.getConnection();
//
//		/* 설정 파일 + 싱글턴 패턴 활용 접속 */

		Connection conn5 = DBConnection.getConnection();
	}

}
