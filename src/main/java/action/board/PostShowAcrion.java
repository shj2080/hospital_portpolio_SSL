package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.PostShowService;
import vo.ActionForward;
import vo.User_board;

public class PostShowAcrion implements Action {


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null ;
		
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		
		PostShowService postShowService = new PostShowService();
		
		User_board showPost = postShowService.getPost(post_no);
		
		request.setAttribute("showPost", showPost);
		request.setAttribute("showPage", "boardView.jsp");
		
		forward = new ActionForward("boardView.jsp", false);
		
		return forward;
		
	}

}
