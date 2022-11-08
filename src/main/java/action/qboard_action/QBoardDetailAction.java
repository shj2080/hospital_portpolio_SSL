package action.qboard_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;
import vo.QBoardBean;

 public class QBoardDetailAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	ActionForward forward = null;
		 
		//게시글번호
		int qboard_num=Integer.parseInt(request.getParameter("qboard_num"));
		
		//페이지 번호
		String page = request.getParameter("page");
		QBoardDetailService boardDetailService = new QBoardDetailService();
		QBoardBean article = boardDetailService.getArticle(qboard_num);
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
	   	
	   	forward = new ActionForward("/qboard/qboard_view.jsp", false);
   		return forward;

	 }
	 
}
