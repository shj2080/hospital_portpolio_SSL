package action.board;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.PostShowService;
import vo.ActionForward;
import vo.AttachFileBean;
import vo.User_board;

public class PostShowAcrion implements Action {


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null ;
		
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		
		PostShowService postShowService = new PostShowService();
		
		//해당 게시글번호의 게시글을 읽어옴
		User_board showPost = postShowService.getPost(post_no);
		
		List<AttachFileBean> attachFiles = postShowService.getAttachFileData(post_no);
		
		request.setAttribute("showPost", showPost);
		request.setAttribute("attachFiles", attachFiles);
		request.setAttribute("showPage", "boardView.jsp");
		
		forward = new ActionForward("boardView.jsp", false);
		
		return forward;
		
	}

}
