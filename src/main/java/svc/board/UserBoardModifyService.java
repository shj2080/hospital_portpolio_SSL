package svc.board;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.User_boardDAO;
import util.FileDeleteUtil;
import vo.AttachFileBean;
import vo.User_board;

public class UserBoardModifyService {

	//첨부파일 없는 일반적인 게시글
	public int modifyPost(User_board userBoard) {
		int modifyResult = 0;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();//바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		//2.싱글톤 패턴:UserDAO객체 생성
		User_boardDAO user_boardDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 DAO의 멤버변수로 삽입하여 DB 연결
		user_boardDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		modifyResult = user_boardDAO.modifyPost(userBoard);
		
		/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
		 * (select제외)----*/
		if(modifyResult > 0) {
			commit(con);
			System.out.println("[debug]게시글 수정 확인.");
		}else {
			rollback(con);
			System.out.println("[debug]게시글 수정 실패");
		}	
		
		//4.해제
		close(con);//Connection객체 해제	
		
		return modifyResult;
	}
	
	//첨부파일 있는 게시글
	public int modifyPost(HttpServletRequest request, User_board userBoard, ArrayList<AttachFileBean> attachFiles) {
		int modifyResult = 0;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();//바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		//2.싱글톤 패턴:UserDAO객체 생성
		User_boardDAO user_boardDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 DAO의 멤버변수로 삽입하여 DB 연결
		user_boardDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/	
		
		//게시글 먼저 수정
		modifyResult = user_boardDAO.modifyPost(userBoard);
		
		//첨부파일 추가 작업 진행
		//게시글에 첨부된 파일정보 insert
		int fileDataWrite = user_boardDAO.insertAttachFileData(attachFiles);
		
		/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
		 * (select제외)----*/
		if(modifyResult > 0 && fileDataWrite > 0) {
			commit(con);
			System.out.println("[debug]게시글 수정 확인.");
		}else {
			rollback(con);
			FileDeleteUtil fileDeleteUtil = new FileDeleteUtil();
			fileDeleteUtil.FailPostFileDeleteProcess(request, userBoard, attachFiles);
			System.out.println("[debug]게시글 수정 실패");
		}	
		
		//4.해제
		close(con);//Connection객체 해제	
		
		return modifyResult;
	}

}
