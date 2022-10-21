package dao;

//DB 연결을 위해 관련 패키지 import
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Member;

public class HospitalDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//싱글톤 패턴 : DAO 객체 하나만 생성되도록 처리
	
	//생성자
	private HospitalDAO() {}
	
	private static HospitalDAO hospitalDAO;
	
	//객체가 없는 경우 생성
	public static HospitalDAO getInstance() {
		
		if(hospitalDAO == null) { //DAO 객체가 없는 경우
			hospitalDAO = new HospitalDAO(); //객체 생성
		}
		
		return hospitalDAO; //객체 주소 반환
	}
	/**********************************************************/
	
	//커넥션 설정
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//입력한 정보가 DB에 있는 id, password와 일치하는 지 쿼리
	public boolean selectLoginInfo(Member member) {
		boolean checkLoginResult = false;
		
		//쿼리문
		String sql = "select id from membertbl where id = ? AND password = ? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			
			rs = pstmt.executeQuery();
			
			//일치하면 db에서 id 값이 출력됨
			if(rs.next()) {
				checkLoginResult = true;
			} 
			
		} catch(Exception e) {
			
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
		
		return checkLoginResult;
	}
	
	//해당 id의 회원 정보를 가져오는 메서드
	public Member selectUserInfo(String id) {
		Member userInfo = null;
		
		//쿼리문
		String sql = "select * from membertbl where id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userInfo = new Member();
				
				//session 영역에 저장할 데이터만 가져와 채움
				userInfo.setId(rs.getString("id"));
				userInfo.setName(rs.getString("name"));
			} 
			
		} catch(Exception e) {
			
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
				
		return userInfo;
	}
}
