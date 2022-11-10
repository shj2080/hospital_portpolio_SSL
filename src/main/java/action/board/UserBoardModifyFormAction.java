package action.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.board.PostShowService;
import vo.ActionForward;
import vo.User_board;

public class UserBoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//세션에서 정보 가져옴
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
	  			out.println("alert('관리자가 아니면 공지사항 수정이 불가능합니다.');");
	  			out.println("history.back();");
	  			out.println("</script>");
	  		}else {
	  			int post_no = Integer.parseInt(request.getParameter("post_no"));
	  			
	  			PostShowService postShowService = new PostShowService();
	  			User_board modifyData = postShowService.getPost(post_no);
	  			
	  			request.setAttribute("modifyData", modifyData);
	  			forward = new ActionForward("boardWrite.jsp", false);
	  		}
	  	}
		
		return forward;
	}

}
