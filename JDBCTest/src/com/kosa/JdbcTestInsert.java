package com.kosa;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTestInsert {

	public static void main(String args[]) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		conn = DBConnection.getConnection();
		conn.setAutoCommit(false);

		try {
			pstmt = conn.prepareStatement("insert into t1 (EMPLOYEE_ID, FIRST_NAME) values (?, ?)");
			stmt = conn.createStatement();

			pstmt.setInt(1, 1500); 
			pstmt.setString(2, "LESLIE"); 
			pstmt.execute();
			conn.commit();
			// stmt.executeUpdate("truncate table t1");

			pstmt.setInt(1, 507);
			pstmt.setString(2, "MARSHA");
			pstmt.execute();
			conn.commit();
			// stmt.executeUpdate("truncate table t1");
			
			System.out.println("입력 성공!");
		} finally {
			
			if (pstmt != null)
				pstmt.close();
		}

	}

}