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
import vo.ReservationBean;
import vo.TreatmentBean;

public class ReservationInsertAction_bak implements Action {

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
			String reservationDay = request.getParameter("reservationDay");
			//비교를 위해 시간과 분을 int타입으로 변환
			int reservationHour_int = Integer.parseInt(request.getParameter("reservationHour"));
			int reservationMinute_int = Integer.parseInt(request.getParameter("reservationMinute"));
			int speciality_code = Integer.parseInt(request.getParameter("speciality_code"));
			int doctor_code = Integer.parseInt(request.getParameter("doctor_code"));
			
			String reservationHour = null;
			String reservationMinute = null;
			
			if(reservationHour_int < 10) {
				reservationHour = "0" + reservationHour_int;
			}else {
				reservationHour = String.valueOf(reservationHour_int);
			}
			
			if(reservationMinute_int < 10) {
				reservationMinute = "0" + reservationMinute_int;
			}else {
				reservationMinute = String.valueOf(reservationMinute_int);
			}
			
			String reservationTime = reservationHour + ":" + reservationMinute;
			
			String reservation_date = reservationDay + " " + reservationTime;
			
			DateTimeFormatter defaultDayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			
			System.out.println("[DEBUG]ReservationInsertAction parse 전 부분 실행됨.");
			LocalDateTime treatmentDate_datetime = LocalDateTime.parse(reservation_date, defaultDayFormat);
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
			
			ReservationBean reservationBean = new ReservationBean(speciality_code, doctor_code, viewId, reservation_date, phone);
			//TreatmentBean treatmentBean = new TreatmentBean(speciality_code, doctor_code, viewId, reservation_date, phone);
					
			ReservationInsertService reservationInsertService = new ReservationInsertService();
			int isReservation = reservationInsertService.insertReservationTreatment(reservationBean);
			
			//진료예약 insert 성공시
			if(isReservation > 0) {
				//request.setAttribute("reservationDate", reservation_date);
				//request.setAttribute("reservationHour", reservationHour);
				//request.setAttribute("reservationMinute", reservationMinute);
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert(' " + reservationDay + " " + reservationHour + "시 " + reservationMinute + "분 진료예약 되었습니다.');");
				out.println("location.href='treatmentList.do';");
				out.println("</script>");
				
				//forward = new ActionForward("/reservation/reservation_OK.jsp", false);
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('요청하신 진료날짜로 이미 예약된 회원이 있어 진료예약을 할 수 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
	  	}
	  	
		return forward;
	}

}
