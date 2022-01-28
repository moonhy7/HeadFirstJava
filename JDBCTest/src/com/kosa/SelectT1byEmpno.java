package com.kosa;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectT1byEmpno {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("사번 입력: ");
		int empno = sc.nextInt();

		String runSP = "{ call sp_select_t1_by_empno(?, ?, ?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, empno);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(3, java.sql.Types.DOUBLE);
			callableStatement.registerOutParameter(4, java.sql.Types.DATE);

			try {
				callableStatement.executeQuery();
				
				String name = callableStatement.getString(2);
				double salary = callableStatement.getDouble(3);
				Date createdDate = callableStatement.getDate(4);

				System.out.println();
				System.out.println("name: " + name);
				System.out.println("salary: " + salary);
				System.out.println("createdDate: " + createdDate);
				System.out.println();
				System.out.println("성공");

			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				// System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}

}
