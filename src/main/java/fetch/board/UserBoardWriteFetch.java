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
	  		
	  		
	  		//서버 파일 저장 경로
	  		String saveDir = "/board/notice";
	  		
	  		ServletContext context = request.getServletContext();
	  		String uploadPath = context.getRealPath(saveDir);
	  		
	  		//파일 용량 제한 (10M)
	  		int fileLimitSize = 10 * 1024 * 1024;
	  		
	  		/*-★★1. 폴더 만들기 추가-----------------------------------------------------------*/
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
	  		
	  		//업로드를 위한 MultipartRequest 정의
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
			
			//게시글 제목
			String post_subject = multi.getParameter("post_subject");
			//게시글 내용
			String post_text = multi.getParameter("post_text");
			
			//String isAttachFile = multi.getParameter("isAttachFile");
			//System.out.println("[debug]isAttachFile(isAttachFile):"+isAttachFile);
			
			userboard = new User_board(); //기본생성자
			
			//각각의 정보 세팅
			userboard.setId(id);
			userboard.setPost_date(post_date);
			userboard.setPost_subject(post_subject);
			userboard.setPost_text(post_text);
			//userboard.setPost_file(serverFileName);
			
			//작성 성공 여부 판별하는 boolean
			boolean isWriteSuccess = false;
			
			//type = "file"인 name들을 얻어와 (filename1, filename2 순으로 가져옴)
			Enumeration<?> post_files = multi.getFileNames();
			
			//게시글 작성 서비스 객체 생성
			UserBoardWriteService userBoardWriteService = new UserBoardWriteService();
			
			//첨부파일이 있는지 확인
			if(post_files.hasMoreElements()) {
				//----
				String uploadFiles = null;
				String serverFileNameStr = null;
				String origFileNameStr = null;
				//----
				
				//첨부파일 정보 기록을 위한 Bean 객체와 List 생성
				ArrayList<AttachFileBean> attachFiles = new ArrayList<AttachFileBean>();
				
				//객체 내부의 값만 바꿀 경우 List에 넣을 때 문제 발생함
				//AttachFileBean attachFileBean = new AttachFileBean();
				AttachFileBean attachFileBean = null;
				
				//등록될 게시글 번호를 체크함
				int insert_post_no = userBoardWriteService.insertPostnoCheck();
				
				//파일이 있는 동안 계속 반복
				while(post_files.hasMoreElements()) {
					uploadFiles = (String)post_files.nextElement();
					
					//서버상의 파일이름과 작성자가 첨부한 파일명 구함
					serverFileNameStr = multi.getFilesystemName(uploadFiles); //input File 타입의 요소에서 이름 받아옴
					origFileNameStr = multi.getOriginalFileName(uploadFiles);
					
					//null값이 온 경우
					
					 if(serverFileNameStr == null){
						 System.out.println("[debug]파일 업로드 작업 중 null 감지됨.");
						 continue;
					 }
					 
					//게시글에 첨부파일들의 정보를 받아 삽입
					//기존값이 덮어씌워지는 문제 해결을 위해 생성자를 사용하여 새로운 객체 만듬
					attachFileBean = new AttachFileBean(
							insert_post_no,								//등록될 게시글 번호
							serverFileNameStr,							//서버상의 파일명
							origFileNameStr,							//작성자가 올린 파일명
							multi.getFile(uploadFiles).length());		//업로드한 파일 사이즈(Byte 단위)

					//첨부파일정보 List에 add
					attachFiles.add(attachFileBean);
				}
				
				//첨부파일이 있으므로 Y로 세팅
				userboard.setIsAttachFile("Y");
				
				//서비스 메서드 호출(파일첨부시)
				System.out.println("[debug]첨부파일 있는 게시글 감지");
				isWriteSuccess = userBoardWriteService.writeAction(request, userboard, attachFiles);
			}else {
				
				//첨부파일이 없으므로 N으로 세팅
				userboard.setIsAttachFile("Y");
				
				//서비스 메서드 호출(첨부파일 없는 게시글)
				System.out.println("[debug]첨부파일 없는 게시글 감지");
				isWriteSuccess = userBoardWriteService.writeAction(userboard);
			}
			
			//게시글 작성 성공 여부 파악
			if(isWriteSuccess == false) {
	  			alertMessage = "게시글 등록 실패";
	  			type = "FAIL";
			}else {
	  			alertMessage = "게시글이 성공적으로 등록되었습니다.";
	  			type = "OK";
			}
	  	}
		fetch = new FetchForward<User_board>(userboard, alertMessage, type);
		
		return fetch;
	}

}
