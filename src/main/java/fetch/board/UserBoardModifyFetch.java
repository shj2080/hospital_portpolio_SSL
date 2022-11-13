package fetch.board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import fetch.FetchAction;
import svc.board.UserBoardModifyService;
import vo.User_board;
import vo.fetch.FetchForward;

public class UserBoardModifyFetch implements FetchAction<User_board> {

	@Override
	public FetchForward<User_board> executeResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//필요한 변수들 선언
		FetchForward<User_board> fetch = null;
		User_board userBoard = null;
		String type = null;
		String alertMessage = null;
		
		//로그인 상태 체크를 위해 세션의 아이디를 가져옴
		HttpSession session = request.getSession();
	    String viewId = (String)session.getAttribute("userID");
	    String userType = (String)session.getAttribute("userType");
	    
	    //현재 로그인된 상태가 아니면
		if(viewId == null) {
  			alertMessage = "로그인이 필요한 서비스입니다.";
  			type = "login";
  			fetch = new FetchForward<User_board>(userBoard, alertMessage, type);
  			return fetch;
  		//로그인 상태라면
	  	}else {
		
	  		if(!userType.equals("M")) {
	  			alertMessage = "작성권한이 없습니다.";
	  			type = "grade";
	  			fetch = new FetchForward<User_board>(userBoard, alertMessage, type);
	  			return fetch;
	  		}
	  		
	  		
	  		String saveDir = "/board/notice";
	  		
	  		ServletContext context = request.getServletContext();
	  		String uploadPath = context.getRealPath(saveDir);
	  		
	  		int fileLimitSize = 10 * 1024 * 1024;
	  		
	  		/*-★★1. upload 폴더 만들기 대신 추가함-----------------------------------------------------------*/
	  		/*
	  		File.mkdir() : 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우, 생성 불가
	  			(예) C:\base\want : want 디렉토리를 만들고자 하는데, base 디렉토리가 없는 경우, 생성 불가

	  			
	  		File.mkdirs() : 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우, 상위 디렉토리까지 생성
	  			(예) C:\base\want : want 디렉토리를 만들고자 하는데, base 디렉토리가 없는 경우, base 디렉토리까지 생성
	  		*/
	  			File dir = new File(uploadPath);
	  			if(!dir.exists()) {	//파일 또는 폴더가 존재하면 true
	  				dir.mkdirs();	//upload 폴더가 존재하지 않으면 해당 위치에 만들어줌.
	  			}
	  		/*-----------------------------------------------------------------------------------------*/
	  		
	  		MultipartRequest multi = null;
			try {
				multi = new MultipartRequest(request, uploadPath, fileLimitSize, "UTF-8", new DefaultFileRenamePolicy());
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
	  		
	  		//글번호 수정을 위해 필요
	  		int post_no = Integer.parseInt(multi.getParameter("post_no"));
	  		
	  		//id
	  		String id = multi.getParameter ("id");
			
	  		
			//날짜 형식 지정
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//현재 날짜와 시간
			String post_date = simpleDateFormat.format(new Date());
			
			//게시글 비밀번호
			//String post_pwd =request.getParameter("post_pwd");
			//게시글 제목
			String post_subject = multi.getParameter("post_subject");
			//게시글 내용
			String post_text = multi.getParameter("post_text");
			
			//추가 : 실제 서버 파일명들이 담긴 ArrayList
			ArrayList<String> serverFileName = new ArrayList<String>();
			//업로드 시 사용된 파일명들이 담긴 ArrayList
			ArrayList<String> origFileName = new ArrayList<String>();
			
			Enumeration<?> post_files = multi.getFileNames();//type = "file"인 name들을 얻어와 (filename1, filename2 순으로 가져옴)
			
			String uploadFiles;
			
			while(post_files.hasMoreElements()) {
				uploadFiles = (String)post_files.nextElement();
				
				String serverFileNameStr = multi.getFilesystemName(uploadFiles);
				if(serverFileNameStr == null){
					continue;//break를 하지 않는 이유는 1=null, 2=null, 3=file, 4=null 처럼 파일을 중간에 넣었을 경우도 고려해야 하기 때문입니다.
				}
				
				serverFileName.add(multi.getFilesystemName(uploadFiles));	//서버상의 파일이름
				origFileName.add(multi.getOriginalFileName(uploadFiles));	//사용자가 업로드한 파일이름
			}
			
			//첨부파일? (임시처리.. 반드시 수정)
			String post_file = "N";
			
			System.out.println("[debug]post_no:" + post_no);
	  		System.out.println("[debug]id:" + id);
	  		System.out.println("[debug]post_subject:" + post_subject);
	  		System.out.println("[debug]post_text:" + post_text);
	  		System.out.println("[debug]post_file:" + post_file);
			
	  		userBoard = new User_board(post_no, post_date, post_subject, post_text, post_file, id);
	  				
	  		//게시글 수정을 위한 서비스(DAO에서 수정작업을 위해 호출됨)
			UserBoardModifyService userBoardModifyService = new UserBoardModifyService();
			int modifyResult = userBoardModifyService.modifyPost(userBoard);
			
			
			//게시글 작성 성공 여부 파악
			if(modifyResult <= 0) {
	  			alertMessage = "게시글 수정 실패";
	  			type = "not";
			}else {
	  			alertMessage = "게시글이 성공적으로 수정되었습니다.";
	  			type = "ok";
			}
	  	}
				
		fetch = new FetchForward<User_board>(userBoard, alertMessage, type);
		
		return fetch;
	}

}
