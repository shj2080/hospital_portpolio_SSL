package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginService;
import vo.ActionForward;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//로그인 서비스 객체 생성
		LoginService loginService = new LoginService();
		
		//로그인 처리 결과를 리턴
		boolean loginResult = loginService.getLoginResult(request.getParameter("id"), request.getParameter("password"));
		
		//로그인 후 이동 위치 (성공 시 지정 페이지로 이동)
		if(loginResult) {
			HttpSession session = request.getSession();
			
			session.setAttribute("userID", request.getParameter("id"));
			
			forward = new ActionForward("index.jsp", true);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('아이디나 비밀번호가 일치하지 않습니다.')");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
