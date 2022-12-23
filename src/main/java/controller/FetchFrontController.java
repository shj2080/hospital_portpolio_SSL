package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import fetch.FetchAction;
import fetch.LoginCheckingAction;
import fetch.board.UserBoardDeleteFetch;
import fetch.board.UserBoardModifyFetch;
import fetch.board.UserBoardWriteFetch;
import fetch.board.getAttachFileFetch;
import vo.AttachFileBean;
import vo.User_board;
import vo.fetch.FetchForward;

/**
 * Servlet implementation class HospitalFrontController
 */
@WebServlet("*.fe")
public class FetchFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchFrontController() {
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
		
		//Fetch 인터페이스
		
		
		//2. 비즈니스 로직 구분------------------------------------------------
		
		//로그인 프로세스
		if(command.equals("/loginProcess.fe")) {
			FetchAction<Boolean> fetchAction = new LoginCheckingAction();

			try {
				FetchForward<Boolean> fetch = fetchAction.executeResult(request, response);
				
				//값을 출력
				response.setContentType("application/json; charset=UTF-8");
				ObjectMapper mapper = new ObjectMapper();
		
				response.getWriter().write(mapper.writeValueAsString(fetch));
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}

		//게시판 삭제 프로세스
		
		else if(command.equals("/userBoardDelete.fe")) {
			FetchAction<Boolean> fetchAction = new UserBoardDeleteFetch();

			try {
				FetchForward<Boolean> fetch = fetchAction.executeResult(request, response);
				
				//값을 출력
				response.setContentType("application/json; charset=UTF-8");
				ObjectMapper mapper = new ObjectMapper();
		
				response.getWriter().write(mapper.writeValueAsString(fetch));
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/userBoardWriteAction.fe")) {//'글쓰기 처리' 요청이면
			FetchAction<User_board> fetchAction  = new UserBoardWriteFetch();		
			
			try {
				FetchForward<User_board> fetch = fetchAction.executeResult(request,response);
				
				//값을 출력
				response.setContentType("application/json; charset=UTF-8");
				ObjectMapper mapper = new ObjectMapper();
		
				//System.out.println("JSON String 처리:"+mapper.writeValueAsString(fetch));
				response.getWriter().print(mapper.writeValueAsString(fetch));
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		//'글수정' 요청이면
		else if(command.equals("/userBoardModifyAction.fe")) {
			FetchAction<User_board> fetchAction  = new UserBoardModifyFetch();		
			
			try {
				FetchForward<User_board> fetch = fetchAction.executeResult(request,response);
				
				//값을 출력
				response.setContentType("application/json; charset=UTF-8");
				ObjectMapper mapper = new ObjectMapper();

				//System.out.println("JSON String 처리:"+mapper.writeValueAsString(fetch));
				response.getWriter().print(mapper.writeValueAsString(fetch));
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		//'파일다운' 요청이면
		else if(command.equals("/fileDown.fe")) {
			FetchAction<AttachFileBean> fetchAction = new getAttachFileFetch();
			try {
				FetchForward<AttachFileBean> fetch = fetchAction.executeResult(request, response);
				
				return;
				
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
	}
}
