package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.User_boardDAO;
import vo.AttachFileBean;
import vo.User_board;

public class PostShowService {
	public User_board getPost(int post_no) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:MenuDAO객체 생성
		User_boardDAO ubDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		ubDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		User_board showPost = ubDAO.showPost(post_no);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return showPost;
		
	}

	//특정 게시글 번호의 첨부파일 데이터 모두 가져옴
	public List<AttachFileBean> getAttachFileData(int post_no) {
		ArrayList<AttachFileBean> attachFiles = null;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:MenuDAO객체 생성
		User_boardDAO ubDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		ubDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		attachFiles = ubDAO.showAttachFileData(post_no);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return attachFiles;
	}

	//특정 파일 번호배열로 첨부파일들을 가져옴
	public List<AttachFileBean> getFileIdxAttachFileData(int[] files_idx) {
		ArrayList<AttachFileBean> attachFiles = null;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:MenuDAO객체 생성
		User_boardDAO ubDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		ubDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		attachFiles = ubDAO.showFileIdxAttachFileData(files_idx);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return attachFiles;
	}

	//첨부파일 다운로드를 위해 한 파일의 정보를 가져오는 메서드
	public AttachFileBean getAttachFile(int file_idx) {
		//파일 번호값을 담아 DAO에 전송하기 위한 준비를 함
		AttachFileBean attachFileInfo = new AttachFileBean();
		attachFileInfo.setFile_idx(file_idx);
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:MenuDAO객체 생성
		User_boardDAO ubDAO = User_boardDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		ubDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		attachFileInfo = ubDAO.getAttachFileData(attachFileInfo);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return attachFileInfo;
	}
}
