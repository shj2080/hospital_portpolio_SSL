package svc.mypage;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.HospitalDAO;

public class MemberUnregisterService {

	public int insertLeaveMemberInfo(String viewId) {
		//커넥션 풀에서 커넥션 객체 얻어오기
        Connection con = getConnection();

        //DAO 객체(싱글톤 패턴)
        HospitalDAO hospitalDAO = HospitalDAO.getInstance();

        //DB작업에 사용될 Connection 객체를 DAO 멤버변수에 전달
        hospitalDAO.setConnection(con);

        //해당 메서드를 호출하여 처리

        int result = hospitalDAO.insertLeaveMemberInfo(viewId);

        /*-(update,delete,insert)성공하면 commit 실패하면 rollback
         * (select제외)----*/
        if(result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        //DB 연결 해제
        close(con);

        return result;
		
	}

	public int updateLeaveMemberInfo(String viewId) {
		//커넥션 풀에서 커넥션 객체 얻어오기
        Connection con = getConnection();

        //DAO 객체(싱글톤 패턴)
        HospitalDAO hospitalDAO = HospitalDAO.getInstance();

        //DB작업에 사용될 Connection 객체를 DAO 멤버변수에 전달
        hospitalDAO.setConnection(con);

        //해당 메서드를 호출하여 처리

        int result = hospitalDAO.leaveMemberDeleteInfo(viewId);

        /*-(update,delete,insert)성공하면 commit 실패하면 rollback
         * (select제외)----*/
        if(result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        //DB 연결 해제
        close(con);

        return result;
	}
	
}
