package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonString;

import fetch.FetchAction;
import fetch.LoginCheckingAction;
import fetch.board.UserBoardDeleteFetch;

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
		FetchAction fetchAction = null;
		
		//2. 비즈니스 로직 구분------------------------------------------------
		
		//로그인 프로세스
		if(command.equals("/loginProcess.fe")) {
			fetchAction = new LoginCheckingAction();

			boolean result = false;
			try {
				result = fetchAction.ProcessResult(request,response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}

			//JSON 반환값 설정
			Map<String, Boolean> data = new HashMap<String, Boolean>();
			data.put("result", result);
			
			//값을 출력
			response.setContentType("application/json; charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
	
			response.getWriter().write(mapper.writeValueAsString(data));
		}

		//게시판 삭제 프로세스
		
		else if(command.equals("/userBoardDelete.fe")) {
			fetchAction = new UserBoardDeleteFetch();
			
			boolean result = false;
			try {
				result = fetchAction.ProcessResult(request,response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			
			//JSON 반환값 설정
			Map<String, Boolean> data = new HashMap<String, Boolean>();
			data.put("result", result);
			
			//값을 출력
			response.setContentType("application/json; charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
	
			response.getWriter().write(mapper.writeValueAsString(data));
		}
		
		
	}
}
