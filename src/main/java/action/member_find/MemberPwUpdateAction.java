package action.member_find;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.MemberPwUpdateService;
import util.SHA256;
import vo.ActionForward;

import java.io.PrintWriter;

public class MemberPwUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String password = SHA256.encodeSHA256(request.getParameter("password"));
		String id = request.getParameter("u_id");
		
		MemberPwUpdateService memberPwUpdateService = new MemberPwUpdateService();

		boolean isMemberModify = memberPwUpdateService.updateMemberpw(password, id);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if(isMemberModify) {
			out.println("<script>");
			out.println("alert('비밀번호 수정을 완료했습니다.');");
			out.println("location.replace('../login.jsp');");
			out.println("</script>");
			
			
			//자바스크립트로 메세지창 띄우려면 아래 코드 주석
			//forward = new ActionForward("index.jsp", true);
		}else {
			out.println("<script>");
			out.println("alert('비밀번호 수정을 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}

		return forward;
	}

}
