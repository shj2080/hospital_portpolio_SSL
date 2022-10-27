package action.reservation;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.reservation.ReservationInsertService;
import vo.ActionForward;
import vo.TreatmentBean;

public class ReservationInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

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
			String treatmentDay = request.getParameter("treatmentDay");
			int treatmentHour_int = Integer.parseInt(request.getParameter("treatmentHour"));
			int treatmentMinute_int = Integer.parseInt(request.getParameter("treatmentMinute"));
			int speciality_code = Integer.parseInt(request.getParameter("speciality_code"));
			int doctor_code = Integer.parseInt(request.getParameter("doctor_code"));
			
			String treatmentHour = null;
			String treatmentMinute = null;
			
			if(treatmentHour_int < 10) {
				treatmentHour = "0" + treatmentHour_int;
			}else {
				treatmentHour = String.valueOf(treatmentHour_int);
			}
			
			if(treatmentMinute_int < 10) {
				treatmentMinute = "0" + treatmentMinute_int;
			}else {
				treatmentMinute = String.valueOf(treatmentMinute_int);
			}
			
			String treatmentTime = treatmentHour + ":" + treatmentMinute + ":" + "00";
			String treatmentDate_tmp = treatmentDay + " " + treatmentTime;
			
			System.out.println("[DEBUG]<ReservationInsertAction>treatmentDate_tmp값:" + treatmentDate_tmp);
			Timestamp treatment_date = Timestamp.valueOf(treatmentDate_tmp);
			
			String phone = (String)session.getAttribute("userPhone");
			
			TreatmentBean treatmentBean = new TreatmentBean(speciality_code, doctor_code, viewId, treatment_date, phone);
					
			ReservationInsertService reservationInsertService = new ReservationInsertService();
			int isReservation = reservationInsertService.insertReservationTreatment(treatmentBean);
			
			//진료예약 insert 성공시
			if(isReservation > 0) {
				request.setAttribute("treatmentDate", treatment_date);
				
				forward = new ActionForward("/reservation/reservation_OK.jsp", false);
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('진료예약을 실패했습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
	  	}
	  	
		return forward;
	}

}
