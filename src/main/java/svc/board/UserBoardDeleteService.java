package svc.board;

import static db.JdbcUtil.*;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import dao.User_boardDAO;
import vo.AttachFileBean;
import vo.User_board;

public class UserBoardDeleteService {

	//일반적인 게시글 삭제
	public boolean deletePost(HttpServletRequest request, User_board userBoard, List<AttachFileBean> attachFiles) {
		boolean result = false;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();//바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		//2.싱글톤 패턴:UserDAO객체 생성
		User_boardDAO user_boardDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 DAO의 멤버변수로 삽입하여 DB 연결
		user_boardDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/	
		int deleteResult = 0;
		
		//첨부파일이 있는 경우에만 삭제 실행
		if(attachFiles != null) {
			//--서버에 저장된 파일 삭제 구문--//
			//String saveDir = "/board/notice";
			
			//-----파일 저장된 경로 처리------//
			ServletContext context = request.getServletContext();
	  		String uploadPath = context.getInitParameter("board_uploadFiles");
	  		String deleteFilePath = uploadPath + File.separator;
	  		//-----파일 저장된 경로 처리------//
	  		
	  		//파일 접근을 위한 File 객체 선언
	  		File deleteFile = null;
			
	  			for(AttachFileBean attachFile:attachFiles) {
	  				//경로(예) /board/notice/file.jpg
	  				deleteFile = new File(deleteFilePath + attachFile.getSave_name()); //save_name은 서버상의 실제 파일명
	  				//파일이 존재하면 삭제
	  				if(deleteFile.exists()) {
	  					deleteFile.delete(); //해당 파일을 삭제
	  				}else {
	  					System.out.println("Exception:존재하지 않는 파일을 지우려 했습니다.");
	  				}
	  			}
			//--서버에 저장된 파일 삭제 구문--//
			
	  		//게시글에 첨부됐던 파일 정보 삭제
			deleteResult = user_boardDAO.deleteAttachFileData(userBoard);
		}
		
		deleteResult = user_boardDAO.deletePost(userBoard);
		
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
	
	//게시글에 첨부된 파일만 삭제 (게시글 번호와, 받아온 파일 정보로 판별)
	public boolean deleteAttachFileInPost(HttpServletRequest request, User_board userBoard, List<AttachFileBean> attachFiles) {
		boolean result = false;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();//바로 호출해서 사용가능(import static db.JdbcUtil.*;)
		//2.싱글톤 패턴:UserDAO객체 생성
		User_boardDAO user_boardDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 DAO의 멤버변수로 삽입하여 DB 연결
		user_boardDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/	
		int deleteResult = 0;
		
		//첨부파일이 있는 경우에만 삭제 실행
		if(attachFiles != null) {
			//--서버에 저장된 파일 삭제 구문--//
			///String saveDir = "/board/notice";
			
			//-----파일 저장된 경로 처리------//
			ServletContext context = request.getServletContext();
			String uploadPath = context.getInitParameter("board_uploadFiles");
			String deleteFilePath = uploadPath + File.separator;
			//-----파일 저장된 경로 처리------//
			
			//파일 접근을 위한 File 객체 선언
			File deleteFile = null;
			
			for(AttachFileBean attachFile:attachFiles) {
				//경로(예) /board/notice/file.jpg
				deleteFile = new File(deleteFilePath + attachFile.getSave_name()); //save_name은 서버상의 실제 파일명
				//파일이 존재하면 삭제
				if(deleteFile.exists()) {
					deleteFile.delete(); //해당 파일을 삭제
				}else {
					System.out.println("Exception:존재하지 않는 파일을 지우려 했습니다.");
				}
			}
			//--서버에 저장된 파일 삭제 구문--//
			
			//게시글에 첨부됐던 파일 정보 삭제
			deleteResult = user_boardDAO.deleteFileIdxAttachFileData(userBoard, attachFiles);
		}
		
		/*-(update,delete,insert)성공하면 commit, 실패하면 rollback
		 * (select제외)----*/
		if(deleteResult > 0) {
			commit(con);
			System.out.println("[debug]게시글 내 첨부파일 삭제 확인.");
			result = true;
		}else {
			rollback(con);
			System.out.println("[debug]게시글 내 첨부파일 삭제 실패");
		}	
		
		//4.해제
		close(con);//Connection객체 해제	
		
		return result;
	}
	
}
