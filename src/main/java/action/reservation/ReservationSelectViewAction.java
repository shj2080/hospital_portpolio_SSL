package action.reservation;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.reservation.ReservationSelectViewService;
import vo.ActionForward;
import vo.Speciality;

public class ReservationSelectViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//로그인 상태 체크를 위해 세션의 아이디를 가져옴
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
	  		//진료과 정보 가져오는 서비스 호출
	  		ReservationSelectViewService reservationSelectViewService = new ReservationSelectViewService();
	  		List<Speciality> speciality_list = reservationSelectViewService.select_SpecialityInfoList();
	  		
	  		if(speciality_list != null) {
	  			request.setAttribute("specialityList", speciality_list);
	  			
	  			forward = new ActionForward("reservation/reservationView.jsp", false);
	  		} else {
	  			response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('DB접근 오류입니다. 관리자에게 문의하세요.');");
				out.println("history.back();");
				out.println("</script>");
	  		}
	  		
	  	}
		
		return forward;
	}

}
