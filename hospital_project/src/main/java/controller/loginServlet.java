package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginProcess")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 방식 설정
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//Servlet에서 세션 만들 경우 아래 방식 사용
		HttpSession session = request.getSession();
		
		//login DAO 처리
		//if(login.dao(id, password)) {
		if(id.equals("java") && password.equals("test")) {
			session.setAttribute("userID", id);
			response.sendRedirect("loginOK.jsp");
		}else {
			response.sendRedirect("loginFail.html");
		}
		
		
		//response 컨텐츠 타입 정의 및 인코딩 설정
		response.setContentType("text/html;charset=UTF-8");
	}

}
