package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.mypage.admin.MemberInfoListViewAction;
import action.mypage.admin.ReservationCheckListAction;
import action.mypage.admin.ReservationSearchListAction;
import action.reservation.TreatmentAddConfirmAction;
import vo.ActionForward;

/**
 * Servlet implementation class AdminFrontController
 */
@WebServlet("*.ad")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFrontController() {
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
		//회원의 진료 예약 조회 후 관리자가 진료테이블에 추가할 수 있는 화면을 제공하는 액션
		if(command.equals("/reservationCheckList.ad")) {
			action = new ReservationCheckListAction();

			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("ReservationCheckListAction ActionForward 예외 : " + e);
			}
		}
		//관리자의 진료예약 조회 화면에서 회원 id를 검색하는 액션
		else if (command.equals("/reservationMemberSearch.ad")) {
			action = new ReservationSearchListAction();

			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("ReservationSearchListAction ActionForward 예외 : " + e);
			}
		}
		//진료확인 버튼을 눌렀을 때 진료테이블에 추가하는 액션
		else if (command.equals("/treatmentAddConfirm.ad")) {
			action = new TreatmentAddConfirmAction();

			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("TreatmentAddConfirmAction ActionForward 예외 => ");
				e.printStackTrace();
			}
		}
		//전체 회원 정보를 조회하는 액션
		else if (command.equals("/memberListView.ad")) {
			action = new MemberInfoListViewAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("MemberInfoListViewAction ActionForward 예외 : " + e);
			}
		}

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
