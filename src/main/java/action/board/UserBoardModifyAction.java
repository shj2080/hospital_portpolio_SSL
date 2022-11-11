package action.board;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.board.UserBoardModifyService;
import vo.ActionForward;
import vo.User_board;

public class UserBoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//로그인 상태 체크를 위해 세션의 아이디를 가져옴
		HttpSession session = request.getSession();
	    String viewId = (String)session.getAttribute("userID");
	    String userType = (String)session.getAttribute("userType");
	    
	    //현재 로그인된 상태가 아니면
		if(viewId == null) {
  			response.setContentType("text/html;charset=utf-8");
  			PrintWriter out = response.getWriter();
  			out.println("<script>");
  			out.println("alert('로그인이 필요한 서비스입니다.');");
  			out.println("location.href='login.do';");
  			out.println("</script>");
  		//로그인 상태라면
	  	}else {
		
	  		if(!userType.equals("M")) {
	  			response.setContentType("text/html;charset=utf-8");
	  			PrintWriter out = response.getWriter();
	  			out.println("<script>");
	  			out.println("alert('작성권한이 없습니다.');");
	  			out.println("history.back();");
	  			out.println("</script>");
	  			return forward;
	  		}
	  		
	  		
	  		//글번호 수정을 위해 필요
	  		int post_no = Integer.parseInt(request.getParameter("post_no"));
	  		
	  		//id
	  		String id = request.getParameter ("id");
			
	  		
			//날짜 형식 지정
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//현재 날짜와 시간
			String post_date = simpleDateFormat.format(new Date());
			
			//게시글 비밀번호
			//String post_pwd =request.getParameter("post_pwd");
			//게시글 제목
			String post_subject = request.getParameter("post_subject");
			//게시글 내용
			String post_text = request.getParameter("post_text");
			//첨부파일?
			String post_file = request.getParameter("post_file");
			
			System.out.println("[debug]post_no:" + post_no);
	  		System.out.println("[debug]id:" + id);
	  		System.out.println("[debug]post_subject:" + post_subject);
	  		System.out.println("[debug]post_text:" + post_text);
	  		System.out.println("[debug]post_file:" + post_file);
			
	  		User_board userBoard = new User_board(post_no, post_date, post_subject, post_text, post_file, id);
	  				
	  		//게시글 수정을 위한 서비스(DAO에서 수정작업을 위해 호출됨)
			UserBoardModifyService userBoardModifyService = new UserBoardModifyService();
			int modifyResult = userBoardModifyService.modifyPost(userBoard);
			
			
			//게시글 작성 성공 여부 파악
			if(modifyResult <= 0) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글 수정 실패');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글이 성공적으로 수정되었습니다.');");
				out.println("location.replace('userBoard.do');");
				out.println("</script>");
			}
	  	}
		
		
		return forward;
	}

}
