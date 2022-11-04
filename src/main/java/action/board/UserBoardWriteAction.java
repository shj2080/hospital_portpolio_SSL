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
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String post_date = simpleDateFormat.format(new Date());
		
		String post_pwd =request.getParameter("post_pwd");
		String post_subject = request.getParameter("post_subject");
		String post_text = request.getParameter("post_text");
		String post_file = request.getParameter("post_file");
		
		User_board userboard = new User_board();
		userboard.setId(id);
		userboard.setPost_date(post_date);
		userboard.setPost_pwd(post_pwd);
		userboard.setPost_subject(post_subject);
		userboard.setPost_text(post_text);
		userboard.setPost_file(post_file);
		
		UserBoardWriteService userBoardWriteService =new UserBoardWriteService();
		boolean isWriteSuccess = userBoardWriteService.writeAction(userboard);
		
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
