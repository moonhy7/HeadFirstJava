package com.kosa;


import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class SelectT1byEnameLike {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("사원 이름: ");
		String ename = sc.next();

		String runSP = "{ call sp_select_t1_by_ename_like(?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setString(1, ename);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			System.out.println();
			
			try {
				callableStatement.execute();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
				
				while (resultSet.next()) {
					int empno = resultSet.getInt(1);
					ename = resultSet.getString(2);
					BigDecimal salary = resultSet.getBigDecimal(3);
					Date createdDate = resultSet.getDate(4);
					System.out.println("empno: " + empno);
					System.out.println("name: " + ename);
					System.out.println("salary: " + salary);
					System.out.println("createdDate: " + createdDate);
					System.out.println();
				}
				
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
