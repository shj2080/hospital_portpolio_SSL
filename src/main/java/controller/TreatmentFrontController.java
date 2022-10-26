package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.treatment.ReservationFormAction;
import vo.ActionForward;

/**
 * Servlet implementation class TreatmentFrontController
 */
@WebServlet("*.treat")
public class TreatmentFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TreatmentFrontController() {
        super();
        // TODO Auto-generated constructor stub
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
		//진료예약 요청
		if(command.equals("/reservationForm.treat")) {
			action = new ReservationFormAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("ReservationFormAction ActionForward 예외 : " + e);
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
