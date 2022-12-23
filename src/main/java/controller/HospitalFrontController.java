package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.IndexPageAction;
import action.JoinAction;
import action.LoginFormAction;
import action.LogoutAction;
import action.MyTreatmentListAction;
import action.TreatmentListAction;
import action.TreatmentListSearchAction;
import action.board.PostShowAcrion;
import action.board.UserBoardModifyFormAction;
import action.board.UserboardShowAcrion;
import action.board.UserboardWriteFormAcrion;
import action.member_find.MemberIdFindAction;
import action.member_find.MemberIdFindFormAction;
import action.member_find.MemberPwFindFormAction;
import action.member_find.MemberPwUpdateAction;
import action.member_find.MemberpwFindAction;
import action.mypage.MemberInfoModifyAction;
import action.mypage.MemberInfoModifyFormAction;
import action.mypage.MemberUnregisterAction;
import action.mypage.MemberUnregisterCheckingAction;
import action.mypage.MyTreatmentListCheckAction;
import action.mypage.MypageMainAction;
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
		
		//index 처리 액션
		if(command.equals("/index.do")) {
			action = new IndexPageAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("IndexPageAction ActionForward 예외 : " + e);
			}
		}
		
		//로그인 이동
		else if(command.equals("/login.do")) {
			action = new LoginFormAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("loginForm ActionForward 예외 : " + e);
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
				System.out.println("TreatmentListSearchAction ActionForward 예외 : " + e);
			}
		}
		//마이페이지 진료내역
		else if (command.equals("/MyTratmentList.do")) {
			action = new MyTreatmentListAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("MyTreatmentListAction ActionForward 예외 : " + e);
			}
		}
		//마이페이지 진료내역 id체크 접근
		else if(command.equals("/myTratmentListCheck.do")) {
			action = new MyTreatmentListCheckAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				System.out.println("MyTreatmentListCheckAction ActionForward 예외 : " + e);
			}
		}
		//---------------------------------------------------------------
		else if(command.equals("/userBoard.do")) {//'게시판 보기' 요청이면
			action = new UserboardShowAcrion();//게시판 글 목록 불러오는 Action
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}					
		}
		
		else if(command.equals("/userBoardWrite.do")) {//'글쓰기 폼 보기' 요청이면
			//허가받지 않은 유저가 글쓰기 가능한 문제 해결을 위해 Action 사용
			action = new UserboardWriteFormAcrion();//게시판 글 목록 불러오는 Action
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}		
			//request.setAttribute("showPage", "boardWrite.jsp");
			//forward = new ActionForward("boardWrite.jsp", false);
			//forward = new ActionForward("userBoard.jsp", false);	//반드시 디스패치 방식으로 포워딩					
		}
		
		//'글수정 폼' 요청이면
		else if(command.equals("/userBoardModifyFormAction.do")) {
			action  = new UserBoardModifyFormAction();			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}

		else if(command.equals("/showPost.do")) {//'게시글 보기' 요청이면
			action = new PostShowAcrion();//게시판 글 목록 불러오는 Action
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		//회원 탈퇴안내창 이동 처리
		else if(command.equals("/UnResisterChecking.do")) {//'회원 탈퇴 확인' 요청이면
			action = new MemberUnregisterCheckingAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		//회원 탈퇴 요청 처리
		else if(command.equals("/memberUnResister.do")) {//'회원 탈퇴' 요청이면
			action = new MemberUnregisterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		//회원 아이디 찾기 폼 이동
		else if(command.equals("/find_member/idFindForm.do")) {//'회원 아이디 찾기 폼' 요청이면
			action = new MemberIdFindFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		//회원 아이디 찾기 요청
		else if(command.equals("/find_member/idFind.do")) {//'회원 아이디 찾기' 요청이면
			action = new MemberIdFindAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		
		//'회원 비밀번호 찾기 폼' 요청이면
		else if(command.equals("/find_member/pwFindForm.do")) {
			action = new MemberPwFindFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		
		//회원 비밀번호 찾기 요청
		else if(command.equals("/find_member/pwFind.do")) {//'회원 비밀번호 찾기' 요청이면
			action = new MemberpwFindAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		
		//회원 비밀번호 찾기 요청
		else if(command.equals("/find_member/pwUpdate.do")) {//'회원 비밀번호 찾기' 요청이면
			action = new MemberPwUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}

		//병원소개 - 일반 페이지 이동
		else if(command.equals("/hospital_introduct.do")) {

			try {
				request.setAttribute("showPage", "hospital_introduct.jsp");
				forward = new ActionForward("introduce/introduceTemplate.jsp", false);
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		//의료진소개 - 일반 페이지 이동
		else if(command.equals("/medical_staff.do")) {

			try {
				request.setAttribute("showPage", "medical_staff.jsp");
				forward = new ActionForward("introduce/introduceTemplate.jsp", false);
			} catch (Exception e) {				
				e.printStackTrace();
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
