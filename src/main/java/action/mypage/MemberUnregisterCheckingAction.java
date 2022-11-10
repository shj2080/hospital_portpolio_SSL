package action.mypage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class MemberUnregisterCheckingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		//로그인 상태 체크를 위해 세션의 아이디를 가져옴
		HttpSession session = request.getSession();
	    String viewId = (String)session.getAttribute("userID");
		
	    //로그인이 되어 있지 않다면 로그인 폼 이동, 되어있다면 작업 진행
	    if(viewId != null) {
	    	request.setAttribute("showPage", "unregisterCheck.jsp");
			
			forward = new ActionForward("mypage/mypageTemplate.jsp", false);
	    }else {
	    	response.setContentType("text/html;charset=utf-8");
  			PrintWriter out = response.getWriter();
  			out.println("<script>");
  			out.println("alert('로그인이 필요한 서비스입니다.');");
  			out.println("location.href='login.do';");
  			out.println("</script>");
	    }
		
		return forward;
	}

}
