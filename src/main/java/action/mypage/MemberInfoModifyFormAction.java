package action.mypage;

import svc.MemberInfoModifyFormService;
import vo.ActionForward;
import vo.Member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;

public class MemberInfoModifyFormAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;

        Member member = null; // 회원 정보를 담을 자바빈
        
        //회원수정 폼 서비스 객체 생성
        MemberInfoModifyFormService memberInfoModifyService = new MemberInfoModifyFormService();

        HttpSession session = request.getSession();
        String viewId = (String)session.getAttribute("userID");
        
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
	  		
	        member = memberInfoModifyService.selectMemberInfo(viewId);
	        
	        //주민번호 앞, 뒤자리 분리
	        request.setAttribute("front_id_num", member.getId_num().split("-")[0]);
	        request.setAttribute("back_id_num", member.getId_num().split("-")[1]);
	        
	        request.setAttribute("memberInfo", member);
	        request.setAttribute("showPage", "memberInfoModifyForm.jsp");
	        
	        forward = new ActionForward("mypage/mypageTemplate.jsp", false);
	  	}
        return forward;
    }
}
