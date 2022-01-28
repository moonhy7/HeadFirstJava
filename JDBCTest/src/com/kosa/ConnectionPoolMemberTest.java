package com.kosa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPoolMemberTest {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private static final String user = "ace";
	private static final String pwd = "me";
	private static final int initialCons = 5;
	private static final int maxCons = 20;
	private static final boolean block = true;
	private static final long timeout = 10000;

	public static void main(String[] args) {
		Connection conn;
		Statement stmt;
		ResultSet rs;
		ConnectionPool cp;

		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");

			// 커넥션 풀 생성
			cp = new ConnectionPool(url, user, pwd, initialCons, maxCons, block, timeout);
			System.out.println("ConnectionPool 생성...");

			// 커넥션 풀로부터 하나의 커넥션 가져옴
			conn = cp.getConnection();

			stmt = conn.createStatement();
			System.out.println("Statement 생성 성공");

			String query = "INSERT INTO t1 VALUES (50, 'IT', 'SEOUL')";
			System.out.println(query);
			stmt.executeUpdate(query);

			String query2 = "SELECT * FROM t1";
			System.out.println(query2);

			rs = stmt.executeQuery(query2);

			while (rs.next()) {
				System.out.print("부서번호>>" + rs.getInt("deptno"));
				System.out.print("부서이름>>" + rs.getString("dname"));
				System.out.print("지역>>" + rs.getString("loc"));				
			}

			rs.close();
			stmt.close();

			// Connection을 닫으면 안됨
			// conn.close();
			// 커넥션 풀에게 사용한 커넥션을 돌려줌
			cp.releaseConnection(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
