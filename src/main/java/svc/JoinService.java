package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.*;
import vo.*;


public class JoinService {
	//멤버변수
	//생성자
	//메서드
	
	//회원가입
	public boolean userJoin(Member member){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:UserDAO객체 생성
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		hospitalDAO.setConnection(con);
		
		
		boolean isJoinResult = false;
		int isJoinCount = 0;
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		isJoinResult = hospitalDAO.join(member);
		
		if(isJoinResult == true) {
			isJoinCount++;
		}
		
		boolean isUserJoinResult = false;
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		if(isJoinCount>0) {//회원가입에 성공하면
			isUserJoinResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return isUserJoinResult;
	}
}

