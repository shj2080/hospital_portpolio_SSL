package fetch.board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import fetch.FetchAction;
import svc.board.PostShowService;
import svc.board.UserBoardDeleteService;
import svc.board.UserBoardModifyService;
import vo.AttachFileBean;
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
		
		//modifyResult 결과 변수
		int modifyResult = 0;
		
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
	  		
	  		//서버 파일 저장 경로
	  		String saveDir = "/board/notice";
	  		
	  		ServletContext context = request.getServletContext();
	  		String uploadPath = context.getRealPath(saveDir);
	  		
	  		//파일 용량 제한 (10M)
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
	  		
	  		//업로드를 위한 MultipartRequest 정의
	  		MultipartRequest multi = null;
			try {
				multi = new MultipartRequest(request, uploadPath, fileLimitSize, "UTF-8", new DefaultFileRenamePolicy());
			} catch (IOException e) {
				System.out.println("[UserBoardWriteFetch]파일 작업 중 I/O 예외 : " + e);
				alertMessage = "게시글 등록 실패";
				type = "I/O Exception";
				fetch = new FetchForward<User_board>(userBoard, alertMessage, type);
	  			return fetch;
			}

			//날짜 형식 지정
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//현재 날짜와 시간
			String post_date = simpleDateFormat.format(new Date());
			
			//글번호 수정을 위해 필요
	  		int post_no = Integer.parseInt(multi.getParameter("post_no"));
	  		
	  		//id
	  		String id = multi.getParameter ("id");
	  		
			//게시글 비밀번호
			//String post_pwd =request.getParameter("post_pwd");
			//게시글 제목
			String post_subject = multi.getParameter("post_subject");
			//게시글 내용
			String post_text = multi.getParameter("post_text");
			//첨부파일 여부
			String isAttachFile = multi.getParameter("isAttachFile");
			
			//게시판 bean 생성
			userBoard = new User_board(post_no, post_date, post_subject, post_text, isAttachFile, id);
			
			//게시글 수정을 위한 서비스(DAO에서 수정작업을 위해 호출됨)
			UserBoardModifyService userBoardModifyService = new UserBoardModifyService();
			
			//기존 첨부파일 삭제 체크
			String[] beingDeleteFile_idx_str = multi.getParameterValues("beingDeleteFile_idx");
			
			//기존 첨부파일에서 삭제된 idx 번호를 int타입으로 변환★★★★
			if(beingDeleteFile_idx_str != null && beingDeleteFile_idx_str.length != 0) {
				//String배열을 int배열로 변경
				int[] beingDeleteFile_idx = new int[beingDeleteFile_idx_str.length];
				
				for(int i = 0; i < beingDeleteFile_idx_str.length; i++) {
					beingDeleteFile_idx[i] = Integer.parseInt(beingDeleteFile_idx_str[i]);
				}
				
				PostShowService postShowService = new PostShowService();
					
				//파일번호로 첨부파일 데이터 검색 bean
				List<AttachFileBean> postAttachFileData = postShowService.getFileIdxAttachFileData(beingDeleteFile_idx);
				
				//기존 첨부파일과 테이블 데이터 삭제 실행
				UserBoardDeleteService userBoardDeleteService = new UserBoardDeleteService();
				boolean beingDeleteFile_result = userBoardDeleteService.deleteAttachFileInPost(request, userBoard, postAttachFileData);
				
				if(beingDeleteFile_result) {
					System.out.println("[debug]기존 첨부파일 삭제 성공");
				}else {
					System.out.println("[debug]기존 첨부파일 삭제 실패!");
				}
			}

			//type = "file"인 name들을 얻어와 (filename1, filename2 순으로 가져옴)
			Enumeration<?> post_files = multi.getFileNames();
			
			//파일이 있다면
			if(post_files.hasMoreElements()) {
				//첨부파일이 있다면 
				userBoard.setIsAttachFile("Y");
				
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
							post_no,								//등록될 게시글 번호
							serverFileNameStr,							//서버상의 파일명
							origFileNameStr,							//작성자가 올린 파일명
							multi.getFile(uploadFiles).length());		//업로드한 파일 사이즈(Byte 단위)

					//첨부파일정보 List에 add
					attachFiles.add(attachFileBean);
				}
				
				
				System.out.println("[debug]post_no:" + post_no);
		  		System.out.println("[debug]id:" + id);
		  		System.out.println("[debug]post_subject:" + post_subject);
		  		System.out.println("[debug]post_text:" + post_text);
		  		System.out.println("[debug]isAttachFile:" + isAttachFile);
		  		
		  		//서비스 메서드 호출(파일첨부시)
				System.out.println("[debug]첨부파일 있는 게시글 감지");
				
				modifyResult = userBoardModifyService.modifyPost(request, userBoard, attachFiles);
			}else {
				//첨부파일이 없다면 
				userBoard.setIsAttachFile("N");

				//서비스 메서드 호출(첨부파일 없는 게시글)
				System.out.println("[debug]첨부파일 없는 게시글 감지");
				modifyResult = userBoardModifyService.modifyPost(userBoard);
			}
			
			//게시글 작성 성공 여부 파악
			if(modifyResult <= 0) {
	  			alertMessage = "게시글 수정 실패";
	  			type = "FAIL";
			}else {
	  			alertMessage = "게시글이 성공적으로 수정되었습니다.";
	  			type = "OK";
			}
	  	}
				
		fetch = new FetchForward<User_board>(userBoard, alertMessage, type);
		
		return fetch;
	}

}
