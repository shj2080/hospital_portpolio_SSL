package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class IndexPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//DB작업이 필요한 부분은 여기에 작성
		
		//--------------------------
		
		//index.jsp로 이동
		forward = new ActionForward("index.jsp", true);
		
		return forward;
	}

}
