package dao.impl;

import dao.face.UserDao;
import dto.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {
	
	// OJDBC 드라이버
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	// DB연결 정보
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "scott";
	private final String PASSWORD = "1234";

	// ODJBC 관련 객체
	private Connection conn = null; // DB 연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // 결과 집합 객체
	
	public UserDaoImpl() {

		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public List<User> selectAll() {
		
		System.out.println("selectAll - 모든 사용자의 정보를 조회");
		System.out.println();
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "";
//		sql += "SELECT * FROM usertest"; // 단순 확인용 *(전체)
		sql += "SELECT idx, userid, name FROM usertest"; // 혹시 모를 DB변동사항을 대비해 모든 컬럼 입력
		sql += " ORDER BY idx";

		List<User> list = new ArrayList<>();

		try {

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while ( rs.next() ) {

				// 조회 결과의 각 행을 데이터를 저장할 객체
				User user = new User();

				user.setIdx( rs.getInt("idx") );
				user.setUserid( rs.getString("userid") );
				user.setName( rs.getString("name") );

				// 행 데이터를 리스트에 추가하기
				list.add(user);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (ps != null && !ps.isClosed()) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 최종 조회 반환
		return list;
		
	}


	@Override
	public int insertUser(User insertUser) {
		String sql = "";
//		sql += "INSERT INTO usertest";
//		sql += " ( idx, userid, name )";
//		sql += " VALUES(userTest_SQ.nextval,?,?)";

		// Auto Increment PK 트리거를 적용할 경우 사용 가능
		sql += "INSERT INTO usertest( userid, name )";
		sql += " VALUES(?,?)";
		
		// 수행 결과 저장 변수
		// SQL코드에 영향 받은 행 수를 저장
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//select는 상관없고, insert/update/delete에서만 사용
//			conn.setAutoCommit(true); // Auto Commit 수행O
			conn.setAutoCommit(false); // Auto Commit 수행X
			
			
			// SQL 수행 객체
			ps = conn.prepareStatement(sql);

			// ? 파라미터 채우기
			ps.setString(1, insertUser.getUserid());
			ps.setString(2, insertUser.getName());
			
			// SQL 수행
			result = ps.executeUpdate();
			
			// 트랜젝션 관리 - commit 수행
			if (result > 0) {
				conn.commit();
			}
			// 기본속성이 rollback이라 else문 필요 없음
			
		} catch (SQLException e) {

			e.printStackTrace();
		
			// aoto commit이 켜져있을때,
			//insert수행 중 예외가 발생하면 rollback할 코드
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {

			try {
				if (ps != null && !ps.isClosed()) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 최종 결과 반환
		return result;
		
	}

	@Override
	public User selectByIdx(int idx) {

		String sql = "";
		sql += "SELECT * FROM usertest";
		sql += " WHERE idx = ?";
		
		// 조회 결과를 저장할 객체
		User result = null;

		try {

			// SQL 수행 객체
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			
			// SQL 수행 결과 저장
			rs = ps.executeQuery();

			while (rs.next()) {

				// 조회 결과의 각 행을 데이터를 저장할 객체
				result = new User();

				result.setIdx(rs.getInt("idx"));
				result.setUserid(rs.getString("userid"));
				result.setName(rs.getString("name"));

			}
		
		} catch (SQLException e) {

			e.printStackTrace();
		
		} finally {

			try {
				if (rs != null && !rs.isClosed()) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (ps != null && !ps.isClosed()) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 최종 결과 반환
		return result;
		
	}



	@Override
	public int deleteByIdx(int idx) {
		
		String sql = "";
		sql += "DELETE FROM usertest";
		sql += " WHERE idx = ?";
		
		// 조회 결과를 저장할 객체
		int result = 0;
		
		try {

			// SQL 수행 객체
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			
			// SQL 수행 결과 저장
			result = ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		
		} finally {

			try {
				if (rs != null && !rs.isClosed()) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (ps != null && !ps.isClosed()) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 최종 결과 반환
		return result;
		
		
	}

	
	
	
	
}
