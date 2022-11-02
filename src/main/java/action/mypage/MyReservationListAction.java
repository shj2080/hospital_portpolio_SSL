package action.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class MyReservationListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//구현중...
		
		
		//템플릿에 표시되는 페이지 변경
		request.setAttribute("showPage", "myReservationList.jsp");
		
		forward = new ActionForward("mypage/mypageTemplate.jsp", false);
		
		return forward;
	}

}
