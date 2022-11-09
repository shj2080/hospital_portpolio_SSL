package action.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class MemberUnregisterCheckingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		request.setAttribute("showPage", "unregisterCheck.jsp");
		
		forward = new ActionForward("mypage/mypageTemplate.jsp", false);
		return forward;
	}

}
