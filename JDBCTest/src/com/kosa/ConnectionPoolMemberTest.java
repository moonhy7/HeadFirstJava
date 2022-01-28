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
			System.out.println("Oracle ����̹� �ε� ����");

			// Ŀ�ؼ� Ǯ ����
			cp = new ConnectionPool(url, user, pwd, initialCons, maxCons, block, timeout);
			System.out.println("ConnectionPool ����...");

			// Ŀ�ؼ� Ǯ�κ��� �ϳ��� Ŀ�ؼ� ������
			conn = cp.getConnection();

			stmt = conn.createStatement();
			System.out.println("Statement ���� ����");

			String query = "INSERT INTO t1 VALUES (50, 'IT', 'SEOUL')";
			System.out.println(query);
			stmt.executeUpdate(query);

			String query2 = "SELECT * FROM t1";
			System.out.println(query2);

			rs = stmt.executeQuery(query2);

			while (rs.next()) {
				System.out.print("�μ���ȣ>>" + rs.getInt("deptno"));
				System.out.print("�μ��̸�>>" + rs.getString("dname"));
				System.out.print("����>>" + rs.getString("loc"));				
			}

			rs.close();
			stmt.close();

			// Connection�� ������ �ȵ�
			// conn.close();
			// Ŀ�ؼ� Ǯ���� ����� Ŀ�ؼ��� ������
			cp.releaseConnection(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
