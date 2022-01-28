package com.kosa;
//DAO.java

import java.sql.*;
import java.util.ArrayList;

public class DAO {

	// Connection�� �����ͺ��̽��� �����ϴ� ��ü�̴�.
	Connection conn = null;
	// ResultSet : ������ �������� ���� �޴� ��ü
	ResultSet rs = null;
	Statement st = null; // �׳� �������°�
	// PreparedStatement�� �������� ?�� ����ؼ� �߰��� ?�� ������ �Ҵ��� �ټ� �ֵ��� �ϴ� ��ü
	PreparedStatement ps = null; // ?�־ ����ִ°�

	// ������
	public DAO() {

		try {
			String user = "user03";
			String pw = "xxxxxxAt21cc";
			String url = "jdbc:oracle:thin:@edudb_high?TNS_ADMIN=C:\\\\Dev211\\\\OracleWallet\\\\Wallet_edudb";

			// jdbc drive�� ����ϴ� ����
			// class.forName�� ȣ���ϸ� Driver�� �ڱ��ڽ��� �ʱ�ȭ�Ͽ� DriverManager�� ����Ѵ�.
			// ��, �����ڰ� ���� �������� �ʴ� static ��ü���� �˾Ƽ� DriverManager�� ��ϵǴ� ���̴�.
			// �׷��� Class.forName()�� ȣ���ϰ� ���� � ���ڷε� �������� �ʰ� �ٷ� getConnection()�� ȣ���ص� ����̹��� ã������.
			
			// Driver Class�� �ε��ϸ� ��ü�� �����ǰ�, DriverManager�� ��ϵȴ�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection���� db�� ���� (��ü ����)
			conn = DriverManager.getConnection(url, user, pw);

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB ����̹� �ε� ���� :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB ���ӽ��� : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}

	// ������� �ʴ� �ڿ��� ���� �Ǳ� ������ �ڿ��� ����ȴ�.
	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}

	// Create
	public void insertData(Data data) {
		try {
			String sql = "INSERT INTO CRUD_TABLE(name, age) values(?, ?)";
			// PrparedStatment��ü ����, ���ڷ� sql���� �־���
			ps = conn.prepareStatement(sql);
			ps.setString(1, data.name);
			ps.setInt(2, data.age);
			// executeUpdate : insert, delete, update�� ���� ���� �޾ƿ��� �ʴ� ������ ����
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}

	// Read
	public ArrayList<Data> readData() {
		ArrayList<Data> arr = new ArrayList<Data>();
		System.out.println(arr);
		try {
			// �������� db�� �ѱ�, ������ ���ڿ� ����
			st = conn.createStatement();

			String sql = "SELECT * FROM CRUD_TABLE ORDER BY AGE ASC";
			//rs:ResultSet�� ������ �������� ��� ���� �޾Ƶ��̴�.
			rs = st.executeQuery(sql);

			// ���� ������� ���
			while (rs.next()) {
				arr.add(new Data(rs.getString(1), rs.getInt(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return arr;
	}

	// Update
	public void updateData(Data data) {
		try {
			String sql = "UPDATE CRUD_TABLE SET AGE=? WHERE NAME=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, data.age);
			ps.setString(2, data.name);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}

	// Delete
	public void deleteData(String name) {
		try {

			String sql = "DELETE FROM CRUD_TABLE WHERE NAME=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
}