package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.JoinAction;
import action.LoginAction;
import action.LogoutAction;
import action.TreatmentListAction;
import action.TreatmentListSearchAction;
import action.mypage.MemberInfoModifyAction;
import action.mypage.MemberInfoModifyFormAction;
import action.mypage.MypageMainAction;
import action.reservation.ReservationSelectViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class HospitalFrontController
 */
@WebServlet("*.do")
public class HospitalFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalFrontController() {
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
		
		Action action = null; //Action 인터페이스
		ActionForward forward = null;
		
		//2. 비즈니스 로직 구분------------------------------------------------
		
		//로그인 이동
		if(command.equals("/login.do")) {
			forward = new ActionForward("login.jsp", false);
		}
		//로그인 프로세스
		else if(command.equals("/loginProcess.do")) {
			action = new LoginAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("login ActionForward 예외 : " + e);
			}
		}
		//로그아웃 요청
		else if(command.equals("/logout.do")) {
			action = new LogoutAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("login ActionForward 예외 : " + e);
			}
		}
		//마이페이지 요청
		else if(command.equals("/mypage.do")) {
			action = new MypageMainAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("MypageMain ActionForward 예외 : " + e);
			}
		}
		//회원정보 수정 폼 보기 요청 - 로그인한 회원의 정보로 세팅됨(비밀번호 제외)
		else if(command.equals("/memberInfoModifyForm.do")) {
			action = new  MemberInfoModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
			  System.out.println("memberInfoModifyForm(회원정보수정 폼 보기) ActionForward 예외 : " + e);
			}
		}
		else if(command.equals("/memberInfoModifyAction.do")) {
			action = new  MemberInfoModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("memberInfoModifyAction(회원정보수정 보기) ActionForward 예외 : " + e);
			}
		}
			 
		//회원가입 요청
		else if(command.equals("/join.do")) {
			action = new JoinAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("JoinAction ActionForward 예외 : " + e);
			}
		}
		//목록을 불러오는 요청
		else if(command.equals("/treatmentList.do")) {
			action = new TreatmentListAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("TreatmentListAction ActionForward 예외 : " + e);
			}
		}
		
		else if(command.equals("/treatmentListSearch.do")) {
			action = new TreatmentListSearchAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("treatmentListSearch ActionForward 예외 : " + e);
			}
		}
		
		//---------------------------------------------------------------
		
		/****************************************************************************
		 * 포워딩
		*****************************************************************************/
		if(forward != null) {
			if(forward.isRedirect()) { //true : Redirect - 새요청
				response.sendRedirect(forward.getPath()); // 응답객체 - 새요청
			} else { //false : 디스패치 - 기존요청
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}
}
