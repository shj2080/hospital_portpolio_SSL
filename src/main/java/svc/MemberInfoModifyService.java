package svc;

import dao.HospitalDAO;
import vo.Member;

import java.sql.Connection;

import static db.JdbcUtil.*;

public class MemberInfoModifyService {
    //멤버변수

    //기본생성자

    //회원수정 결과를 리턴하는 메서드
    public boolean updateMemberInfo(Member member) {
        boolean isMemberModify = false;

        //커넥션 풀에서 커넥션 객체 얻어오기
        Connection con = getConnection();

        //DAO 객체(싱글톤 패턴)
        HospitalDAO hospitalDAO = HospitalDAO.getInstance();

        //DB작업에 사용될 Connection 객체를 DAO 멤버변수에 전달
        hospitalDAO.setConnection(con);

        //해당 메서드를 호출하여 처리

        int result = hospitalDAO.updateMemberInfo(member);

        /*-(update,delete,insert)성공하면 commit 실패하면 rollback
         * (select제외)----*/
        if(result > 0) {
            commit(con);
            isMemberModify = true;
        } else {
            rollback(con);
        }

        //DB 연결 해제
        close(con);

        return isMemberModify;
    }
}
