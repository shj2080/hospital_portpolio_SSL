/*
 * CRUD 게시판
 */
package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.AttachFileBean;
import vo.User_board;

public class User_boardDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/*
	 * public User_boardDAO() { try { String dbURL
	 * ="jdbc:mysql://localhost:3306/shoes_shoppingmall"; String dbID ="java";
	 * String dbPassword ="java"; Class.forName("com.mysql.cj.jdbc.Driver"); con =
	 * DriverManager.getConnection(dbURL,dbID,dbPassword); }catch (Exception e) {
	 * e.printStackTrace(); } }
	 */	
	
	//1.생성자는 무조건 private
		private User_boardDAO(){}
		
		private static User_boardDAO user_boardDAO;
		//static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 UserDAO객체를 1개만 만들도록 하기 위해
		public static User_boardDAO getInstance(){
			if(user_boardDAO == null) {//UserDAO객체가 없으면
				user_boardDAO = new User_boardDAO();//객체 생성
			}
			
			return user_boardDAO;//기존 UserDAO객체의 주소 리턴
		}
		/************************************************************/
		
		public void setConnection(Connection con){//Connection객체를 받아 DB 연결
			this.con=con;
		}	
	
	//삽입되는 게시글번호 체크 (C)
	public int insertPostnoCheck() {
		//post_no 세팅
		int post_no = 0;
		
		String sql = "select ifnull(max(post_no),0)+1 as post_no from user_board";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				post_no = rs.getInt(1);
			}
			
		} catch (Exception e) {			
			System.out.println("[User_boardDAO] post_no 불러오기 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		}	
		return post_no;
	}//C
		
	//삽입 (C)
	public int insertPost(User_board userboard) {
		int writeCount = 0;
		
		String sql2 = "select ifnull(max(post_no),0)+1 as post_no from user_board";
		//post_no 세팅
		int post_no = 0 ;
		try {
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				post_no = rs.getInt(1);
				userboard.setPost_no(post_no);
			}
			
		} catch (Exception e) {			
			System.out.println("[User_boardDAO] post_no 불러오기 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		}	
		
		String sql = "insert into user_board"
				+ "(post_no, id, post_date,"
				+ "post_subject,post_text, isAttachFile) "
				+ "values(?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			
			//쿼리문에서 ?에 대입될 값 지정
			pstmt.setInt(1, post_no);				
			pstmt.setString(2, userboard.getId());	
			pstmt.setString(3, userboard.getPost_date());					
			pstmt.setString(4, userboard.getPost_subject());			
			pstmt.setString(5, userboard.getPost_text());			
			pstmt.setString(6, userboard.getIsAttachFile());	
			
			writeCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음			
			
		} catch (Exception e) {			
			System.out.println("[User_boardDAO] write 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return writeCount;
	}
	
	//조회 (R)
	public ArrayList<User_board> showList(){
		String sql = "select post_no, post_subject ,id, post_date from user_board order by post_no desc ";
		ArrayList<User_board> list = new ArrayList<User_board>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User_board ub = new User_board(
							rs.getInt("post_no"),
							rs.getString("post_subject"),
							rs.getString("id"),
							rs.getString("post_date") );
				list.add(ub); 
			}
			
		}catch (SQLException e) {
			System.out.println("[User_boardDAO] showList() 에러 : "+e);
		}finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	//전체 게시글 수 count 조회 (R)
	public int countTotalPosts(){
		int totalPosts = 0;
		
		String sql = "select count(*) from user_board";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalPosts = rs.getInt(1); 
			}
			
		}catch (SQLException e) {
			System.out.println("[User_boardDAO] countTotalPosts() 에러 : "+e);
		}finally {
			close(pstmt);
			close(rs);
		}
		return totalPosts;
	}

	//게시글 목록 수 제한하여 조회 (R)
	public ArrayList<User_board> showLimitList(int limitRow){
		String sql = "select post_no, post_subject ,id, post_date from user_board order by post_no desc limit ?";
		ArrayList<User_board> list = new ArrayList<User_board>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, limitRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User_board ub = new User_board(
						rs.getInt("post_no"),
						rs.getString("post_subject"),
						rs.getString("id"),
						rs.getString("post_date") );
				list.add(ub); 
			}
			
		}catch (SQLException e) {
			System.out.println("[User_boardDAO] showLimitList() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	//게시글 목록 수 제한하여 조회 (R)
	public ArrayList<User_board> showLimitBoardListDetail(int currentPage, int boardLimit){
		String sql = "select post_no, post_subject ,id, post_date from user_board order by post_no desc limit ?, ?";
		ArrayList<User_board> list = new ArrayList<User_board>();
		
		//현재 페이지 설정
		currentPage = (currentPage-1) * 10;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, currentPage);
			pstmt.setInt(2, boardLimit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User_board ub = new User_board(
						rs.getInt("post_no"),
						rs.getString("post_subject"),
						rs.getString("id"),
						rs.getString("post_date") );
				list.add(ub); 
			}
			
		}catch (SQLException e) {
			System.out.println("[User_boardDAO] showLimitBoardList() 에러 : "+e);
		}finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	//조회 (R)
	public User_board showPost(int post_no){
		String sql = "select post_no, post_subject, id, post_date, post_text, isAttachFile from user_board where post_no = ?";
		User_board ub = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_no);;
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ub = new User_board();
				ub.setPost_no(rs.getInt("post_no"));
				ub.setPost_subject(rs.getString("post_subject"));
				ub.setId(rs.getString("id"));
				ub.setPost_date(rs.getString("post_date"));
				ub.setPost_text(rs.getString("post_text"));
				ub.setIsAttachFile(rs.getString("isAttachFile"));
			}
			
		}catch (SQLException e) {
			System.out.println("[User_boardDAO] showPost() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return ub;
	}
	//수정 (U)
	public int modifyPost(User_board userBoard) {
		int modifyCount = 0;
		
		String sql = "update user_board set post_subject = ?, post_text = ?, isAttachFile = ?";
		sql += " where id = ? AND post_no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			//쿼리문에서 ?에 대입될 값 지정				
			pstmt.setString(1, userBoard.getPost_subject());			
			pstmt.setString(2, userBoard.getPost_text());			
			pstmt.setString(3, userBoard.getIsAttachFile());
			pstmt.setString(4, userBoard.getId());
			pstmt.setInt(5, userBoard.getPost_no());
			
			modifyCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음			
			
		} catch (Exception e) {			
			System.out.println("[User_boardDAO] modifyPost 에러:"+ e);
		} finally {
			close(pstmt);
		}
		
		return modifyCount;
	}
	//삭제 (D)
	
	public int deletePost(User_board userBoard) {
		int deleteCount = 0;
		
		String sql = "delete from user_board where post_no = ? AND id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			//쿼리문에서 ?에 대입될 값 지정
			pstmt.setInt(1, userBoard.getPost_no());					
			pstmt.setString(2, userBoard.getId());			
			
			deleteCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음			
			
		} catch (Exception e) {			
			System.out.println("[User_boardDAO] deletePost 에러:"+ e);
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	
	//첨부파일 데이터 추가
	public int insertAttachFileData(ArrayList<AttachFileBean> attachFiles) {
		int writeCount = 0;
		
		String sql = "insert into attach_tb(board_idx, original_name, save_name, size) values(?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			//향상된 for문으로 쿼리문 값을 변경하며 insert
			for(AttachFileBean attachFile:attachFiles) {
				//쿼리문에서 ?에 대입될 값 지정
				pstmt.setInt(1, attachFile.getBoard_idx());				
				pstmt.setString(2, attachFile.getOriginal_name());	
				pstmt.setString(3, attachFile.getSave_name());					
				pstmt.setLong(4, attachFile.getSize());
				
				writeCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음			
			}
		} catch (Exception e) {			
			System.out.println("[User_boardDAO] AttachFileData Write 에러:"+ e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return writeCount;
	}
	
	//첨부파일 목록 조회
	public ArrayList<AttachFileBean> showAttachFileData(int post_no) {
		
		String sql = "select * from attach_tb where board_idx = ?";
		ArrayList<AttachFileBean> attachFiles = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				attachFiles = new ArrayList<AttachFileBean>();
				
				
				do {
					AttachFileBean attachFileBean = new AttachFileBean(
							rs.getInt("file_idx"),
							rs.getInt("board_idx"),
							rs.getString("original_name"),
							rs.getString("save_name"),
							rs.getInt("size"),
							rs.getString("insert_time"));
					attachFiles.add(attachFileBean);
				}while(rs.next());
			}
			
		}catch (SQLException e) {
			System.out.println("[User_boardDAO] showAttachFileData() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return attachFiles;
	}
	
	//특정 첨부파일의 정보 조회(기본키 = file_idx)
	public AttachFileBean getAttachFileData(AttachFileBean attachFileBean) {
		
		String sql = "select * from attach_tb where file_idx = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, attachFileBean.getFile_idx());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				attachFileBean.setSave_name(rs.getString("save_name"));
				attachFileBean.setOriginal_name(rs.getString("original_name"));
				attachFileBean.setBoard_idx(rs.getInt("board_idx"));
				attachFileBean.setSize(rs.getLong("size"));
			}
			
		}catch (SQLException e) {
			System.out.println("[User_boardDAO] showAttachFileData() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return attachFileBean;
	}
	
	//게시글에 첨부된 파일정보 삭제 [파일정보 테이블]
	public int deleteAttachFileData(User_board userBoard) {
		int deleteCount = 0;
		
		String sql = "delete from attach_tb where board_idx = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			//쿼리문에서 ?에 대입될 값 지정
			pstmt.setInt(1, userBoard.getPost_no());	
			
			deleteCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음			
			
		} catch (Exception e) {			
			System.out.println("[User_boardDAO] deleteAttachFileData 에러:"+ e);
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

	//특정 게시글번호의 특정 파일번호 첨부된 파일정보 삭제 [파일정보 테이블] - deleteService
	public int deleteFileIdxAttachFileData(User_board userBoard, List<AttachFileBean> attachFiles) {
		int deleteCount = 0;
		
		String sql = "delete from attach_tb where board_idx = ? AND file_idx = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			//쿼리문에서 ?에 대입될 값 지정
			pstmt.setInt(1, userBoard.getPost_no());
			
			for (int i = 0; i < attachFiles.size(); i++) {
				pstmt.setInt(2, attachFiles.get(i).getFile_idx());
				deleteCount = pstmt.executeUpdate();	//업데이트를 성공하면 1을 리턴받음	
			}

			
		} catch (Exception e) {			
			System.out.println("[User_boardDAO] deleteFileIdxAttachFileData 에러:"+ e);
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	
	//특정 파일 번호배열로 첨부파일들을 가져옴
	public ArrayList<AttachFileBean> showFileIdxAttachFileData(int[] files_idx) {
		String sql = "select * from attach_tb where file_idx = ?";
		ArrayList<AttachFileBean> attachFiles = new ArrayList<AttachFileBean>();
		
		try {
			pstmt = con.prepareStatement(sql);
			
			for(int i = 0; i < files_idx.length; i++) {
				pstmt.setInt(1, files_idx[i]);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					AttachFileBean attachFileBean = new AttachFileBean(
							rs.getInt("file_idx"),
							rs.getInt("board_idx"),
							rs.getString("original_name"),
							rs.getString("save_name"),
							rs.getInt("size"),
							rs.getString("insert_time"));
					attachFiles.add(attachFileBean);
				}
			}
			

		}catch (SQLException e) {
			System.out.println("[User_boardDAO] showFileIdxAttachFileData() 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return attachFiles;
	}
	
	

}
