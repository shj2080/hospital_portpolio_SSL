package action.mypage.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class ReservationCheckListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		
		//구현 중...(임시 이동경로)
		request.setAttribute("showPage", "../errorPage/404error.html");
		forward = new ActionForward("mypage/mypageTemplate.jsp", false);
		
		return forward;
	}

}
