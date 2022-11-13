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
import svc.board.UserBoardWriteService;
import vo.AttachFileBean;
import vo.User_board;
import vo.fetch.FetchForward;

public class UserBoardWriteFetch implements FetchAction<User_board> {

	@Override
	public FetchForward<User_board> executeResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//필요한 변수들 선언
		FetchForward<User_board> fetch = null;
		User_board userboard = null;
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
  			//return resultData;
  		//로그인 상태라면
	  	}else {
		
	  		if(!userType.equals("M")) {
	  			alertMessage = "작성권한이 없습니다.";
	  			type = "grade";
	  			fetch = new FetchForward<User_board>(userboard, alertMessage, type);
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
				System.out.println("[UserBoardWriteFetch]파일 작업 중 I/O 예외 : " + e);
				alertMessage = "게시글 등록 실패";
				type = "I/O Exception";
				fetch = new FetchForward<User_board>(userboard, alertMessage, type);
	  			return fetch;
			}
	  		
			//int post_no ; 게시글 번호는 자동 생성
			String id = multi.getParameter ("id");
			
			//날짜 형식 지정
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//현재 날짜와 시간
			String post_date = simpleDateFormat.format(new Date());
			
			//게시글 비밀번호
			String post_pwd =multi.getParameter("post_pwd");
			//게시글 제목
			String post_subject = multi.getParameter("post_subject");
			//게시글 내용
			String post_text = multi.getParameter("post_text");
			
			//추가 : 실제 서버 파일명들이 담긴 ArrayList
			//ArrayList<String> serverFileName = new ArrayList<String>();
			//업로드 시 사용된 파일명들이 담긴 ArrayList
			//ArrayList<String> origFileName = new ArrayList<String>();
			
			userboard = new User_board(); //기본생성자
			
			//각각의 정보 세팅
			userboard.setId(id);
			userboard.setPost_date(post_date);
			//userboard.setPost_pwd(post_pwd);
			userboard.setPost_subject(post_subject);
			userboard.setPost_text(post_text);
			//userboard.setPost_file(serverFileName);
			//userboard.setPost_file(post_file);
			
			
			boolean isWriteSuccess = false;
			
			Enumeration<?> post_files = multi.getFileNames();//type = "file"인 name들을 얻어와 (filename1, filename2 순으로 가져옴)
			
			//게시글 작성 서비스 객체 생성
			UserBoardWriteService userBoardWriteService = new UserBoardWriteService();
			
			//첫번째 항목에 null이 있어 파일이 있는 것으로 처리되서 버림
			post_files.nextElement();
			
			//첨부파일이 있는지 확인
			if(post_files.hasMoreElements()) {
				//----
				String uploadFiles = null;
				String serverFileNameStr = null;
				String origFileNameStr = null;
				//----
				
				ArrayList<AttachFileBean> attachFiles = new ArrayList<>();
				AttachFileBean attachFileBean = new AttachFileBean();
				
				//파일이 있는 동안 계속 반복
				while(post_files.hasMoreElements()) {
					uploadFiles = (String)post_files.nextElement();
					
					serverFileNameStr = multi.getFilesystemName(uploadFiles);
					origFileNameStr = multi.getOriginalFileName(uploadFiles);
					
					if(serverFileNameStr == null){
						continue;//break를 하지 않는 이유는 1=null, 2=null, 3=file, 4=null 처럼 파일을 중간에 넣었을 경우도 고려해야 하기 때문입니다.
					}
					
					attachFileBean.setBoard_idx(userBoardWriteService.insertPostnoCheck());
					attachFileBean.setSave_name(serverFileNameStr);
					attachFileBean.setOriginal_name(serverFileNameStr);
					attachFileBean.setSize(multi.getFile(uploadFiles).length());
					
					attachFiles.add(attachFileBean);
					//serverFileName.add(multi.getFilesystemName(uploadFiles));	//서버상의 파일이름
					//origFileName.add(multi.getOriginalFileName(uploadFiles));	//사용자가 업로드한 파일이름
				}
				
				//서비스 메서드 호출(파일첨부시)
				isWriteSuccess = userBoardWriteService.writeAction(userboard, attachFiles);
			}else {
				//서비스 메서드 호출(첨부파일 없는 게시글)
				isWriteSuccess = userBoardWriteService.writeAction(userboard);
			}
			
			//게시글 작성 성공 여부 파악
			if(isWriteSuccess == false) {
	  			alertMessage = "게시글 등록 실패";
	  			type = "not";
			}else {
	  			alertMessage = "게시글이 성공적으로 등록되었습니다.";
	  			type = "ok";
			}
	  	}
		fetch = new FetchForward<User_board>(userboard, alertMessage, type);
		
		return fetch;
	}

}
