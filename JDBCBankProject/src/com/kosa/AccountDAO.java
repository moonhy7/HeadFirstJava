package com.kosa;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import oracle.jdbc.OracleTypes;

public class AccountDAO implements AccountDAOInterface { 

	private Connection conn = DBConnection.getConnection();
	private Statement stmt;
	private ResultSet rs;

	public void accountInsert(AccountVO newAccount) {
		String runSP = "{ call accounts_pack.accounts_insert(?, ?, ?) }";

		try {
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, newAccount.getAno());
			callableStatement.setString(2, newAccount.getOwner());
			callableStatement.setDouble(3, newAccount.getBalance());
			callableStatement.executeUpdate();
			System.out.println("성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void accountList() {
		String runSP = "{ call accounts_pack.accounts_select_all(?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.execute();

			ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

			while (resultSet.next()) {
				System.out.print(resultSet.getString(1) + "   ");
				System.out.print(resultSet.getString(2) + "   ");
				System.out.println(resultSet.getDouble(3));
			}
			System.out.println();

		} catch (SQLException e) {
			System.out.println("프로시저에서 에러 발생!");
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public void accountPlusUpdate(AccountVO account) {
		String runSP = "{ call accounts_pack.accounts_plus_update(?, ?) }";

		try {
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, account.getAno());
			callableStatement.setDouble(2, account.getBalance());
			callableStatement.executeUpdate();
			System.out.println("성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void accountMinusUpdate(AccountVO account) {
		String runSP = "{ call accounts_pack.accounts_minus_update(?, ?) }";

		try {
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, account.getAno());
			callableStatement.setDouble(2, account.getBalance());
			callableStatement.executeUpdate();
			System.out.println("성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public boolean accountFindOne(String ano) {
		String runSP = "{ ? = call accounts_pack.is_account_exists(?) }";

		boolean isExist = false;

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.registerOutParameter(1, Types.VARCHAR); // 에러
			callableStatement.setString(2, ano);
			callableStatement.executeUpdate();
			String accountExist = callableStatement.getString(1);
			System.out.println(accountExist);
			if (accountExist.equals("true")) {
				isExist = true;
			} else {
				isExist = false;
			}
		} catch (SQLException e) {
			System.out.println("프로시저에서 에러 발생!");
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		
		return isExist;
	}

}
