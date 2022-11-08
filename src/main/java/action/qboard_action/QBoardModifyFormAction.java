package action.qboard_action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;
import vo.QBoardBean;

public class QBoardModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
	 	ActionForward forward = null;
	 	
	 	HttpSession session = request.getSession();
        String viewId = (String)session.getAttribute("userID");
        
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
			int qboard_num=Integer.parseInt(request.getParameter("qboard_num"));
			QBoardDetailService boardDetailService = new QBoardDetailService();	
		   	QBoardBean article = boardDetailService.getArticle(qboard_num);
		   	
		   	request.setAttribute("article", article);
		   	
		   	forward = new ActionForward("/qboard/qboard_modify.jsp", false);
	  	}
   		
	  	return forward;
	   		
	 }
	 
}
