package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.mypage.MyReservationListAction;
import action.reservation.*;
import action.reservation.ReservationCancelAction;
import vo.ActionForward;

/**
 * Servlet implementation class TreatmentFrontController
 */
@WebServlet("*.treat")
public class ReservationTreatmentFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationTreatmentFrontController() {
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
		//진료예약 시 진료과 선택 페이지 요청
		if(command.equals("/reservationSelectView.treat")) {
			action = new ReservationSelectViewAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("ReservationSelectViewAction ActionForward 예외 : " + e);
			}
		}
		//선택한 진료과로 기본적인 값 세팅
		else if(command.equals("/reservationForm.treat")) {
			action = new ReservationFormAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("ReservationFormAction ActionForward 예외 : " + e);
			}
		}
		//진료 날짜 선택하는 팝업창
		else if(command.equals("/SelectReservationDay.treat")) {
			action = new SelectReservationDayAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("ReservationFormAction ActionForward 예외 : " + e);
			}
		}
		//진료 예약 처리 요청
		else if(command.equals("/reservationInsertModify.treat")) {
			action = new ReservationInsertModifyAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("ReservationInsertModifyAction ActionForward 예외 : " + e);
			}
		}
		//마이페이지 - 내 예약진료 내역 보기(예약 수정, 취소)
		else if(command.equals("/myReservationList.treat")) {
			action = new MyReservationListAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("myReservationListAction ActionForward 예외 : " + e);
			}
		}
		//마이페이지 - 내 예약진료 취소(예약 수정, 취소)
		else if(command.equals("/myReservationCancel.treat")) {
			action = new ReservationCancelAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("ReservationCancelAction ActionForward 예외 : " + e);
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
