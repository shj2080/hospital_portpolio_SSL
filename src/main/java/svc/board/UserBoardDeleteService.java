package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.User_boardDAO;
import vo.User_board;

public class UserBoardDeleteService {

	public boolean deletePost(User_board userBoard) {
		boolean result = false;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();//바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		//2.싱글톤 패턴:UserDAO객체 생성
		User_boardDAO user_boardDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 DAO의 멤버변수로 삽입하여 DB 연결
		user_boardDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/	
		int deleteResult = user_boardDAO.deletePost(userBoard);
		
		/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
		 * (select제외)----*/
		if(deleteResult > 0) {
			commit(con);
			System.out.println("[debug]게시글 삭제 확인.");
			result = true;
		}else {
			rollback(con);
			System.out.println("[debug]게시글 삭제 실패");
		}	
		
		//4.해제
		close(con);//Connection객체 해제	
		
		return result;
	}

}
