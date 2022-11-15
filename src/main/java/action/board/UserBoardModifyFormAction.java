package action.board;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.board.PostShowService;
import vo.ActionForward;
import vo.AttachFileBean;
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
	  			
	  			//게시글의 정보를 가져오기 위해 DB에 접근하는 서비스 생성 후 정보 가져옴
	  			PostShowService postShowService = new PostShowService();
	  			User_board modifyData = postShowService.getPost(post_no);
	  			
	  			//첨부파일 정보 가져옴
	  			List<AttachFileBean> attachFiles = postShowService.getAttachFileData(post_no);
	  			
	  			//첨부파일 정보
	  			request.setAttribute("attachFiles", attachFiles);
	  			
	  			request.setAttribute("modifyData", modifyData);
	  			forward = new ActionForward("boardWrite.jsp", false);
	  		}
	  	}
		
		return forward;
	}

}
