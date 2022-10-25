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
			System.out.println("[HospitalDAO] selectLoginInfo 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
		
		return checkLoginResult;
	}
	
	//해당 id의 id와 이름을 가져오는 메서드
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
			System.out.println("[HospitalDAO] selectUserInfo 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
				
		return userInfo;
	}
	
	//입력받은 정보를 membertbl테이블에 insert하는 메서드
	//회원가입 - membertbl
		public boolean join(Member member) {
			int joinCount = 0;
			boolean joinSuccess = false;
			
			String sql="insert into membertbl(name,id_num,id,password,address1,address2,address3,postcode,phone) ";
				sql += " values(?,?,?,?,?,?,?,?,?)";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getName());				
				pstmt.setString(2, member.getId_num());				
				pstmt.setString(3, member.getId());				
				pstmt.setString(4, member.getPassword());				
				pstmt.setString(5, member.getAddress1());				
				pstmt.setString(6, member.getAddress2());				
				pstmt.setString(7, member.getAddress3());				
				pstmt.setString(8, member.getPostcode());				
				pstmt.setString(9, member.getPhone());				
				
				joinCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음	
				
				if(joinCount > 0) {
					joinSuccess = true;
				}
				
			} catch (Exception e) {			
				System.out.println("[HospitalDAO] join 에러:"+ e);
			} finally {
				//close(rs);
				close(pstmt);
			}	
			return joinSuccess;
		}
	
	public Member selectMemberInfo(String id) {
		Member userInfo = null;
		
		//쿼리문
		String sql = "select * from membertbl where id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userInfo = new Member();
				
				userInfo.setName(rs.getString("name"));
				userInfo.setId_num(rs.getString("id_num"));
				userInfo.setId(rs.getString("id"));
				//userInfo.setPassword(rs.getString("password"));
				userInfo.setAddress1(rs.getString("address1"));
				userInfo.setAddress2(rs.getString("address2"));
				userInfo.setAddress3(rs.getString("address3"));
				userInfo.setPostcode(rs.getString("postcode"));
				userInfo.setPhone(rs.getString("phone"));
			} 
			
		} catch(Exception e) {
			System.out.println("[HospitalDAO] selectMemberInfo 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
		
		return userInfo;
	}

    public int updateMemberInfo(Member member) {
		int result = 0;

		String sql = "update membertbl set name = ?,";
		sql += " address1 = ?, address2 = ?, address3 = ?, postcode = ?, phone = ?";

		//쿼리문 분기(비밀번호 아무것도 입력안한 경우)
		if(member.getPassword() != null) {
			sql += ",password = ?";
		}
		sql += " where id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getAddress1());
			pstmt.setString(3, member.getAddress2());
			pstmt.setString(4, member.getAddress3());
			pstmt.setString(5, member.getPostcode());
			pstmt.setString(6, member.getPhone());

			//비밀번호 입력 안한 경우 수정하지 않음
			if(member.getPassword() != null) {
				pstmt.setString(7, member.getPassword());
				pstmt.setString(8, member.getId());
			} else {
				pstmt.setString(7, member.getId());
			}

			result = pstmt.executeUpdate();

		} catch(Exception e) {
			System.out.println("[HospitalDAO] updateMemberInfo 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
		}

		return result;
    }
}
