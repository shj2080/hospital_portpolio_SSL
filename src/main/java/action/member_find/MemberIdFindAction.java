package action.member_find;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.MemberSelectService;
import vo.ActionForward;
import vo.Member;

public class MemberIdFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//이름
		String name = request.getParameter("u_name");
		
		//입력된 주민번호를 합침
		String id_num = request.getParameter("front_id_num") + "-" + request.getParameter("back_id_num");
		
		MemberSelectService memberSelectService = new MemberSelectService();
		Member member = memberSelectService.select_idFind(name, id_num);
		
		if(member != null) {
			System.out.println("아이디 찾기 결과 아이디 발견됨.");
			
			request.setAttribute("findID", member.getId());
			request.setAttribute("showPage", "idFindResult.jsp");
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
