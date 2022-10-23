package controller;

import action.Action;
import vo.ActionForward;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        if(command.equals("/BoardWrite.bo")) {

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
