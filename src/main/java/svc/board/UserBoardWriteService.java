package svc.board;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.User_boardDAO;
import vo.AttachFileBean;
import vo.User_board;

public class UserBoardWriteService {

	//작성될 게시글번호 체크
	public int insertPostnoCheck() {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();//바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		//2.싱글톤 패턴:UserDAO객체 생성
		User_boardDAO user_boardDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 DAO의 멤버변수로 삽입하여 DB 연결
		user_boardDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		int post_no = user_boardDAO.insertPostnoCheck();

		/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
		 * (select제외)----*/

		
		//4.해제
		close(con);//Connection객체 해제		
		
		return post_no;
	}
	
	//일반적인 게시글 작성
	public boolean writeAction(User_board userboard) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();//바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		//2.싱글톤 패턴:UserDAO객체 생성
		User_boardDAO user_boardDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		user_boardDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		int writeSuccess = user_boardDAO.insertPost(userboard);
		
		boolean isWriteResult = false;
		/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
		 * (select제외)----*/
		if(writeSuccess > 0) {//
			isWriteResult = true;
			commit(con);
		}else {
			rollback(con);
		}	
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return isWriteResult;
	}

	//첨부파일이 있는 게시글 작성
	public boolean writeAction(User_board userboard, ArrayList<AttachFileBean> attachFiles) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();//바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		//2.싱글톤 패턴:UserDAO객체 생성
		User_boardDAO user_boardDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		user_boardDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/
		//게시글 먼저 insert
		int writeSuccess = user_boardDAO.insertPost(userboard);
		//게시글에 첨부된 파일정보 insert
		int fileDataWrite = user_boardDAO.insertAttachFileData(attachFiles);
		
		boolean isWriteResult = false;
		/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
		 * (select제외)----*/
		if(writeSuccess > 0 && fileDataWrite > 0) {//게시글 작성과 파일 업로드가 모두 성공한 경우
			isWriteResult = true;
			commit(con);
		}else {
			rollback(con);
		}	
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return isWriteResult;
	}
}
