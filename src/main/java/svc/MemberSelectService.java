package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.HospitalDAO;
import vo.Member;


public class MemberSelectService {
	//특정 회원 정보 조회(id)
	public Member selectMemberInfo(String id) {
		Member member = null;
		
		//커넥션 풀에서 커넥션 객체 얻어오기
		Connection con = getConnection();
		
		//DAO 객체(싱글톤 패턴)
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		
		//DB작업에 사용될 Connection 객체를 DAO 멤버변수에 전달
		hospitalDAO.setConnection(con);
		
		//해당 메서드를 호출하여 처리
		member = hospitalDAO.selectMemberInfo(id);
		
		//DB 연결 해제
		close(con);
		
		return member;
	}
	//특정 회원 정보 조회(id조회)
	public Member select_idFind(String name, String id_num) {
		Member member = null;
		
		//커넥션 풀에서 커넥션 객체 얻어오기
		Connection con = getConnection();
		
		//DAO 객체(싱글톤 패턴)
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		
		//DB작업에 사용될 Connection 객체를 DAO 멤버변수에 전달
		hospitalDAO.setConnection(con);
		
		//해당 메서드를 호출하여 처리
		member = hospitalDAO.select_idFind(name, id_num);
		
		//DB 연결 해제
		close(con);
		
		return member;
	}
}
