package action.qboard_action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class QBoardWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//로그인 체크를 위해 세션값 읽음
		HttpSession session = request.getSession();
        String viewId = (String)session.getAttribute("userID");
        
        //현재 로그인된 상태가 아니면
	  	if(viewId == null) {
  			response.setContentType("text/html;charset=utf-8"); //한글깨짐방지, html문서임을 명시
  			PrintWriter out = response.getWriter();
  			out.println("<script>");
  			out.println("alert('로그인이 필요한 서비스입니다.');");
  			out.println("location.href='login.do';");
  			out.println("</script>");

  		//로그인 상태라면
	  	}else {
	  		forward=new ActionForward("/qboard/qboard_write.jsp",false); //생성자를 통해 뿌려주기
	  	}
	  	
		return forward;
	}

}
