package svc;

//DB 연결을 위한 패키지 내 모든 static 메서드를 미리 메모리에 올림 -> 바로 호출가능한 상태
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.HospitalDAO;

public class LoginService {
	//멤버변수
	
	//기본생성자
	
	//메서드
	public boolean getLoginResult() {
		//커넥션 풀에서 커넥션 객체 얻어오기
		Connection con = getConnection();
		
		//DAO 객체(싱글톤 패턴)
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		
		//DB작업에 사용될 Connection 객체를 DAO 멤버변수에 전달
		hospitalDAO.setConnection(con);
		
		//해당 메서드를 호출하여 처리
		//--처리 필요--
		
		//DB 연결 해제
		close(con);
		
		return false;
	}
}
