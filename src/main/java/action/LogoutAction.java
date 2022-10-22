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

		//header에서 이전 페이지 정보를 가져옴
		String referer = request.getHeader("Referer");
		System.out.println("[DEBUG]logoutAction Referer 헤더 정보 : " + referer);
		
		forward = new ActionForward(referer, true);

		return forward;
	}

}
