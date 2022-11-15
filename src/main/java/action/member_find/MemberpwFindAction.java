package action.member_find;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.MemberSelectService;
import vo.ActionForward;
import vo.Member;

public class MemberpwFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//이름
		String name = request.getParameter("u_name");
		
		//입력된 주민번호를 합침
		String id_num = request.getParameter("front_id_num") + "-" + request.getParameter("back_id_num");
		
		String id = request.getParameter("u_id");
		
		MemberSelectService memberSelectService = new MemberSelectService();
		Member member = memberSelectService.select_pwFind(name, id_num, id);
		
		if(member != null) {
			System.out.println("비밀번호 찾기 결과 비밀번호 발견됨.");
			
			request.setAttribute("findPW", member.getPassword());
			request.setAttribute("showPage", "pwFindResult.jsp");
			forward = new ActionForward("findTemplate.jsp", false);
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('일치하는 회원을 찾지 못했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
