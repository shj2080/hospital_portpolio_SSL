package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil { //모든 메서드가 static : 객체 생성없이
	
	//DB 커넥션 풀에서 Connection객체를 얻어와 반환
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQLDB");
			con = ds.getConnection();
			con.setAutoCommit(false);
		} catch(Exception e) {
			System.out.println("[JdbcUtil]DB Connection Pool 생성 중 예외 : " + e);
		}
		
		return con;
	}
	
	//2.Connection객체를 닫아주는 메서드
	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB Connection Pool 닫는 중 예외 : " + e);
		}
	}

	//3.Statement객체를 닫아주는 메서드
	public static void close(Statement stmt){
		
		try {
			stmt.close();
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB Statement 닫는 중 예외 : " + e);
		}
		
	}

	//4.PreparedStatement객체를 닫아주는 메서드
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB PreparedStatement 닫는 중 예외 : " + e);
		}
	}
	
	//5.ResultSet객체를 닫아주는 메서드
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB ResultSet 닫는 중 예외 : " + e);
		}
	}
	
	/*----------------------------------------------------------------------------*/
	//6. 트랜잭션 중에 실행된 작업들을 '완료'시키는 메서드
	public static void commit (Connection con) {
		try {
			con.commit();
			System.out.println("commit success");
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB commit 중 예외 : " + e);
		}
	}
	
	//7. 트랜잭션 중에 실행된 작업들을 '취소'시키는 메서드
	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("rollback success");
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB rollback 중 예외 : " + e);
		}
	}
}
