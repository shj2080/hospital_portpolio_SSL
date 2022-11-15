package action.member_find;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class MemberPwFindFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//id찾기 폼 이동 요청
		request.setAttribute("showPage", "pwFindForm.jsp");
		
		//회원 정보 찾기 템플릿 이동
		forward = new ActionForward("findTemplate.jsp", false);
		return forward;
	}

}
