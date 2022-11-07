package action.board;


import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.UserBoardWriteService;
import vo.ActionForward;
import vo.User_board;

public class UserBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//int post_no ; 게시글 번호는 자동 생성
		String id = request.getParameter ("id");
		
		//날짜 형식 지정
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//현재 날짜와 시간
		String post_date = simpleDateFormat.format(new Date());
		
		//게시글 비밀번호
		String post_pwd =request.getParameter("post_pwd");
		//게시글 제목
		String post_subject = request.getParameter("post_subject");
		//게시글 내용
		String post_text = request.getParameter("post_text");
		//첨부파일?
		String post_file = request.getParameter("post_file");
		
		//공지사항 구분 (체크된 경우 값 1이 들어옴)
		int post_notice = -1;
		
		try {
			post_notice = Integer.parseInt(request.getParameter("post_notice"));
		}catch(Exception e){
			System.out.println("[Info]일반 게시글로 작성됨.");
		}
		
		User_board userboard = new User_board(); //기본생성자
		
		//각각의 정보 세팅
		userboard.setId(id);
		userboard.setPost_date(post_date);
		userboard.setPost_pwd(post_pwd);
		userboard.setPost_subject(post_subject);
		userboard.setPost_text(post_text);
		userboard.setPost_file(post_file);
		
		if(post_notice > 0) {
			userboard.setPost_notice(post_notice);
		}
		
		//게시글 작성 서비스 객체 생성 및 관련 메서드 호출
		UserBoardWriteService userBoardWriteService =new UserBoardWriteService();
		boolean isWriteSuccess = userBoardWriteService.writeAction(userboard);
		
		//게시글 작성 성공 여부 파악
		if(isWriteSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글 등록 실패');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글이 성공적으로 등록되었습니다.');");
			out.println("location.replace('userBoard.do');");
			out.println("</script>");
			//forward = new ActionForward("userBoard.do", true);//"게시판 보기" 요청(리다이렉트 방식)
		}
		
		return forward;
	}

}
