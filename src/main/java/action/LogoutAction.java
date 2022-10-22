package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		HttpSession session = request.getSession();
		
		//기존 세션을 삭제 (세션에 저장된 값 전부 삭제됨)
		if(session.getAttribute("userID") != null) {
			session.invalidate();
		}

		forward = new ActionForward(request.getRequestURI(), true);

		return forward;
	}

}
