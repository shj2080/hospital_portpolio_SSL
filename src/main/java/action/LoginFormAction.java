package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;

public class LoginFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//로그인 상태 체크를 위해 세션의 아이디를 가져옴
		HttpSession session = request.getSession();
	    String viewId = (String)session.getAttribute("userID");
		
	    //로그인이 되어 있지 않다면 로그인폼을 출력, 되어있다면 메인페이지로 이동
	    if(viewId == null) {
	    	forward = new ActionForward("login.jsp", false);
	    }else {
	    	forward = new ActionForward("index.jsp", false);
	    }
		
		return forward;
	}

}
