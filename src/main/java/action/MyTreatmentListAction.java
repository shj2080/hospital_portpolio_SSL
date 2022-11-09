package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MyTreatmentListService;
import vo.ActionForward;
import vo.MyTreatmentList;

public class MyTreatmentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		MyTreatmentListService myTreatmentListService = new MyTreatmentListService();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//입력받은 id
		String id = request.getParameter("id");
		
		//현제 로그인되어 있는 id
		HttpSession session = request.getSession();
        String viewId = (String)session.getAttribute("userID");
		
        
        
        if(id.equals(viewId)) {
		ArrayList<MyTreatmentList> myTreatmentList = myTreatmentListService.getMyTreatmentList(id);
		
		System.out.println("[DEBUG]MyTreatmentListAction의 ID 파라미터 값:" + id);
		request.setAttribute("id", id);
		request.setAttribute("myTreatmentList", myTreatmentList);
		request.setAttribute("showPage", "../myTreatmentList.jsp");
		forward = new ActionForward("mypage/mypageTemplate.jsp", false);
        }else {
        	out.println("<script>");
			out.println("alert('아이디를 잘못입력하였습니다..');");
			out.println("history.back();");
			out.println("</script>");
        }
		return forward;
	}

}

