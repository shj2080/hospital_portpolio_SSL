package action.reservation;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
			
			String treatmentTime = treatmentHour + ":" + treatmentMinute;
			
			String treatment_date = treatmentDay + " " + treatmentTime;
			
			DateTimeFormatter defaultDayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			
			System.out.println("[DEBUG]ReservationInsertAction parse 전 부분 실행됨.");
			LocalDateTime treatmentDate_datetime = LocalDateTime.parse(treatment_date, defaultDayFormat);
			System.out.println("[DEBUG]ReservationInsertAction LocalDateTime parse 후 부분 실행됨.");
			LocalDateTime nowDate = LocalDateTime.now();
			
			System.out.println("[DEBUG]LocalDateTime treatmentDate값 : " + treatmentDate_datetime);
			System.out.println("[DEBUG]LocalDateTime nowDate값 : " + nowDate);
			
			if(treatmentDate_datetime.isBefore(nowDate)) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('현재 날짜나 시간 이전으로 진료를 예약할 수 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				
				return forward; //아래 구문들이 실행되지 않도록 return 시킴(위의 스크립트에서 뒤로가기 하므로 forward 미지정)
			}
			
			String phone = (String)session.getAttribute("userPhone");
			
			TreatmentBean treatmentBean = new TreatmentBean(speciality_code, doctor_code, viewId, treatment_date, phone);
					
			ReservationInsertService reservationInsertService = new ReservationInsertService();
			int isReservation = reservationInsertService.insertReservationTreatment(treatmentBean);
			
			//진료예약 insert 성공시
			if(isReservation > 0) {
				//request.setAttribute("treatmentDate", treatment_date);
				//request.setAttribute("treatmentHour", treatmentHour);
				//request.setAttribute("treatmentMinute", treatmentMinute);
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert(' " + treatmentDay + " " + treatmentHour + "시 " + treatmentMinute + "분 진료예약 되었습니다.');");
				out.println("location.href='treatmentList.do';");
				out.println("</script>");
				
				//forward = new ActionForward("/reservation/reservation_OK.jsp", false);
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
