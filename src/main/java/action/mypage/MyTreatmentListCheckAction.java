package action.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class MyTreatmentListCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//템플릿에 출력될 페이지 변경
		request.setAttribute("showPage", "../myTreatmentListCheck.jsp");
		forward = new ActionForward("mypage/mypageTemplate.jsp", false);
		
		return forward;
	}

}
