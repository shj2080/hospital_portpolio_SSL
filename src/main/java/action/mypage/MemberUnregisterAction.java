package action.mypage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.LoginService;
import svc.MemberSelectService;
import svc.mypage.MemberUnregisterService;
import util.SHA256;
import vo.ActionForward;
import vo.Member;

public class MemberUnregisterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//로그인 상태 체크를 위해 세션의 아이디를 가져옴
		HttpSession session = request.getSession();
	    String viewId = (String)session.getAttribute("userID");
	    
	    //입력된 비밀번호를 변수에 세팅
		String inputPassword = request.getParameter("password");
	    
	    //로그인이 되어 있지 않다면 메인페이지 이동(비정상적인 접근으로 간주), 되어있다면 작업 진행
	    if(viewId != null) {
	    	
	    	//비밀번호 비교를 위해 현재 접속중인 ID와 입력한 패스워드를 실은 Member객체 생성(생성자에서 비밀번호 암호화됨)
	    	Member memberPwChk = new Member(viewId, inputPassword);
	    	LoginService loginService = new LoginService();
	    	
	    	//입력한 비밀번호가 현재 회원의 비밀번호와 일치하는 경우에만 다음 작업 실행
	    	if (loginService.getLoginResult(memberPwChk)) {
	    		int result_insert = 0;
	    		int result_update = 0;
	    		
	    		
	    		MemberUnregisterService memberUnregisterService = new MemberUnregisterService();
	    		//탈퇴요청한 회원 id를 탈퇴유저 정보 테이블에 insert
	    		result_insert = memberUnregisterService.insertLeaveMemberInfo(viewId);
	    		//다른 테이블의 데이터는 유지 한 채 탈퇴요청한 회원의 id를 제외한 데이터를 모두 삭제하며 유저 타입을 'D'로 설정
	    		result_update = memberUnregisterService.updateLeaveMemberInfo(viewId);
	    		
	    		//탈퇴처리가 정상 처리된 경우
	    		if (result_insert > 0 && result_update > 0) {
	    					
	    			//로그아웃 실행 (세션 값 모두 삭제)
	    			session.invalidate();
	    			
	    			//탈퇴완료 페이지 이동
	    			forward = new ActionForward("mypage/unregisterFinish.jsp", true);
	    		} else {
	    			response.setContentType("text/html;charset=utf-8");
		  			PrintWriter out = response.getWriter();
		  			out.println("<script>");
		  			out.println("alert('탈퇴 처리 요청 중 예외가 발생했습니다. 잠시 후 다시 시도해주세요.');");
		  			out.println("history.back();");
		  			out.println("</script>");
	    		}
	    		
	    	}else {
	    		response.setContentType("text/html;charset=utf-8");
	  			PrintWriter out = response.getWriter();
	  			out.println("<script>");
	  			out.println("alert('비밀번호가 일치하지 않습니다.');");
	  			out.println("history.back();");
	  			out.println("</script>");
	    	}

	    }else {
	    	response.setContentType("text/html;charset=utf-8");
  			PrintWriter out = response.getWriter();
  			out.println("<script>");
  			out.println("alert('비정상적인 접근입니다.');");
  			out.println("location.replace='index.do';");
  			out.println("</script>");
	    }
		
		return forward;
	}

}
