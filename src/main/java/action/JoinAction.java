package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.JoinService;
import vo.ActionForward;
import vo.Member;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String name = request.getParameter("name");
		
		//String id_num = request.getParameter("id_num");
		String front_id_num = request.getParameter("front_id_num");
		String back_id_num = request.getParameter("back_id_num");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		String postcode = request.getParameter("postcode");
		String phone = request.getParameter("phone");
		
		//앞자리 주민번호와 뒷자리 주민번호 조합
		String id_num = front_id_num + "-" + back_id_num;
		
		Member member = new Member(name, id_num, id, password, address1, address2, address3, postcode, phone);
		
		JoinService joinService = new JoinService();
		boolean isUserJoinSuccess = joinService.userJoin(member);
		
		if(isUserJoinSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			PrintWriter out = response.getWriter();
			forward = new ActionForward("joinsucess.jsp", true);//"로그인 폼 보기" 요청(리다이렉트 방식)
			out.println("<script>");
			out.println("alert('회원가입 성공');");
			out.println("</script>");
		}
		return forward;
	}

}

