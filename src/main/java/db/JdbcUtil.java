package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	
	//DB 커넥션 풀
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQLDB");
			con = ds.getConnection();
			con.setAutoCommit(false);
		} catch(Exception e) {
			System.out.println("[JdbcUtil]DB 커넥션 풀 생성 중 예외 : " + e);
		}
		
		return con;
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB 커넥션 풀 닫는 중 예외 : " + e);
		}
	}

	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB PreparedStatement 닫는 중 예외 : " + e);
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB ResultSet 닫는 중 예외 : " + e);
		}
	}
	
	public static void commit (Connection con) {
		try {
			con.commit();
			System.out.println("commit success");
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB commit 중 예외 : " + e);
		}
	}
	
	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("rollback success");
		} catch (Exception e) {
			System.out.println("[JdbcUtil]DB rollback 중 예외 : " + e);
		}
	}
}
