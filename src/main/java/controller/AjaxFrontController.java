package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.LoginAction;
import action.LoginFormAction;
import action.LogoutAction;
import ajax.AjaxService;
import ajax.LoginCheckingService;
import svc.LoginService;
import vo.ActionForward;
import vo.Member;

/**
 * Servlet implementation class HospitalFrontController
 */
@WebServlet("*.aj")
public class AjaxFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxFrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//모든 요청을 제어하는 메서드를 호출함
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//모든 요청을 제어하는 메서드를 호출함
		doProcess(request, response);
	}

	//모든 요청을 제어하는 메서드
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //POST 인코딩 설정
		
		//1. 요청 파악
		String requestURI = request.getRequestURI();
		
		//현재 ContextPath 파악
		String contextPath = request.getContextPath();
		
		//들어온 요청명 파악 (URI에서 contextPath길이만큼 잘라낸 나머지 문자열)
		String command = requestURI.substring(contextPath.length());
		
		//Ajax 인터페이스
		AjaxService ajaxService = null;
		
		//2. 비즈니스 로직 구분------------------------------------------------
		
		
		//로그인 프로세스
		if(command.equals("/loginProcess.aj")) {
			ajaxService = new LoginCheckingService();

			boolean result = false;
			try {
				result = ajaxService.ProcessResult(request,response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}

			//값을 출력
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print(result);
		}
		
	}
}
