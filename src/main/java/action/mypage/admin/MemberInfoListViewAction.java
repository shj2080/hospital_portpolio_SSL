package action.mypage.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.MemberInfoListViewService;
import vo.ActionForward;

public class MemberInfoListViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//세션에서 필요한 정보들을 가져옴
		HttpSession session = request.getSession();
	    String viewId = (String)session.getAttribute("userID");
	    String userType = (String)session.getAttribute("userType"); //유저등급 (M:관리자, N:일반회원)
	    
		//현재 로그인된 상태가 아니면
	  	if(viewId == null) {
  			response.setContentType("text/html;charset=utf-8");
  			PrintWriter out = response.getWriter();
  			out.println("<script>");
  			out.println("alert('로그인이 필요한 서비스입니다.');");
  			out.println("location.href='login.do';");
  			out.println("</script>");
  		//로그인 상태라면
	  	}else {
	  		//접속중인 계정이 관리자인지 체크
	  		if(!userType.equals("M")) {
	  			response.setContentType("text/html;charset=utf-8");
	  			PrintWriter out = response.getWriter();
	  			out.println("<script>");
	  			out.println("alert('허용되지 않은 접근입니다!');");
	  			out.println("history.back();");
	  			out.println("</script>");
	  			return forward;
	  		}
	  		
	  		//멤버정보 조회 서비스 객체 생성
	  		MemberInfoListViewService memberInfoListViewService = new MemberInfoListViewService();
	  		
	  	}
	  	
		return forward;
	}

}
