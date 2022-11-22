package action.qboard_action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;
import vo.QBoardBean;


public class QBoardReplyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
	 	ActionForward forward = null;
	 	
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
	  			out.println("alert('관리자가 아니면 답글을 다실 수 없습니다.');");
	  			out.println("history.back();");
	  			out.println("</script>");
	  			return forward;
	  		}
	  		
	  		//현재 게시판 페이지 수 받아옴
	   		String nowPage = request.getParameter("page");
	   		int qboard_num=Integer.parseInt(request.getParameter("qboard_num")); //답글을 달려는 글의 borad_num값을 받아서 borad_num에 담음
	   		   		
	   		QBoardDetailService boardDetailService = new QBoardDetailService();
	   		QBoardBean article=boardDetailService.getArticle(qboard_num);
	   		
	   		//게시글 관련
	   		request.setAttribute("article", article);
	   		
	   		//페이지 관련
	   		request.setAttribute("page", nowPage);
	   		forward = new ActionForward("/qboard/qboard_reply.jsp", false);
	  	}
   		return forward;
	   		
	}
	 
}
