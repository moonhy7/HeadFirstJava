package com.kosa;
//DAO.java

import java.sql.*;
import java.util.ArrayList;

public class DAO {

	// Connection은 데이터베이스와 연결하는 객체이다.
	Connection conn = null;
	// ResultSet : 실행한 쿼리문의 값을 받는 객체
	ResultSet rs = null;
	Statement st = null; // 그냥 가져오는거
	// PreparedStatement는 쿼리문에 ?를 사용해서 추가로 ?에 변수를 할당해 줄수 있도록 하는 객체
	PreparedStatement ps = null; // ?넣어서 집어넣는거

	// 생성자
	public DAO() {

		try {
			String user = "user03";
			String pw = "xxxxxxAt21cc";
			String url = "jdbc:oracle:thin:@edudb_high?TNS_ADMIN=C:\\\\Dev211\\\\OracleWallet\\\\Wallet_edudb";

			// jdbc drive를 등록하는 과정
			// class.forName을 호출하면 Driver가 자기자신을 초기화하여 DriverManager에 등록한다.
			// 즉, 개발자가 따로 관리하지 않는 static 객체들이 알아서 DriverManager에 등록되는 것이다.
			// 그래서 Class.forName()을 호출하고 나서 어떤 인자로도 전달하지 않고 바로 getConnection()을 호출해도 드라이버가 찾아진다.
			
			// Driver Class를 로딩하면 객체가 생성되고, DriverManager에 등록된다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection으로 db와 연결 (객체 생성)
			conn = DriverManager.getConnection(url, user, pw);

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}

	// 사용하지 않는 자원이 유지 되기 때문에 자원이 낭비된다.
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
			// PrparedStatment객체 생성, 인자로 sql문이 주어짐
			ps = conn.prepareStatement(sql);
			ps.setString(1, data.name);
			ps.setInt(2, data.age);
			// executeUpdate : insert, delete, update와 같이 값을 받아오지 않는 쿼리문 실행
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
			// 쿼리문을 db에 넘김, 온전한 문자열 대입
			st = conn.createStatement();

			String sql = "SELECT * FROM CRUD_TABLE ORDER BY AGE ASC";
			//rs:ResultSet은 실행한 쿼리문의 결과 값을 받아들이다.
			rs = st.executeQuery(sql);

			// 받은 결과값을 출력
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