package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.LoginService;
import vo.ActionForward;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//로그인 서비스 객체 생성
		LoginService loginService = new LoginService();
		
		//로그인 처리 결과를 리턴
		//작업중
		
		return forward;
	}

}
