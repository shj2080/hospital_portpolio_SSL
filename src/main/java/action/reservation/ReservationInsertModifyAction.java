package action.reservation;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.reservation.ReservationInsertService;
import svc.reservation.ReservationModifyService;
import vo.ActionForward;
import vo.reservation.ReservationBean;

public class ReservationInsertModifyAction implements Action {

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
			
			//진료과
			int speciality_code = Integer.parseInt(request.getParameter("speciality_code"));
			//의사
			int doctor_code = Integer.parseInt(request.getParameter("doctor_code"));
			
			//수정상태 확인하는 파라미터(마이페이지 에서 수정요청이 들어온 경우)
			String modify_status = request.getParameter("modifyState_hidden");

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
			
			//날짜 비교를 위해
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
			
			//예약정보가 담기는 Bean
			ReservationBean reservationBean = null;
					
			//예약 성공 여부가 담기는 변수 (1: 성공, 0:실패)
			int isReservation = 0;
			
			//이동하는 위치(자바스크립트)
			String moveLocation = null;
			
			//update 일 경우
			if(modify_status != null && !modify_status.equals("")) {
				//예약코드(수정할 경우 필요)
				int reservation_code = Integer.parseInt(request.getParameter("reservation_code_hidden"));
				//유저ID
				String resUserID = request.getParameter("resUserID");
				//예약진료상태(Y : 진료받음, N : 진료받기 전 또는 진료받지 않음)
				String treatment_status = request.getParameter("resTreatStatus");
				
				/*
				 * if(treatment_status.equals("Y")) {
				 * response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
				 * response.getWriter();
				 * 
				 * out.println("<script>");
				 * out.println("alert('진료확인이 된 예약은 일정을 수정할 수 없습니다!');");
				 * out.println("history.back();"); out.println("</script>"); }
				 */
				
				//예약코드를 포함한 생성자를 사용하여 초기화
				reservationBean = new ReservationBean(reservation_code, speciality_code, doctor_code, resUserID, reservation_date, phone, treatment_status);
				
				System.out.println("[DEBUG]ReservationInsertModifyAction (modify) 구문 실행됨.");
				
				ReservationModifyService reservationModifyService = new ReservationModifyService();
				isReservation = reservationModifyService.modifyReservationTreatment(reservationBean);
				
				//이동 위치 지정
				moveLocation = "reservationMemberSearch.ad?u_id=" + resUserID; //예약 진료 내역(수정했던 회원ID로 검색)
			}else { //insert 일 경우
				reservationBean = new ReservationBean(speciality_code, doctor_code, viewId, reservation_date, phone);
				System.out.println("[DEBUG]ReservationInsertModifyAction (insert) 구문 실행됨.");
				ReservationInsertService reservationInsertService = new ReservationInsertService();
				isReservation = reservationInsertService.insertReservationTreatment(reservationBean);
				
				moveLocation = "treatmentList.do"; //진료 대기자 리스트
			}
			
			
			//진료예약 insert 또는 modify(update) 성공시
			if(isReservation > 0) {
				//request.setAttribute("reservationDate", reservation_date);
				//request.setAttribute("reservationHour", reservationHour);
				//request.setAttribute("reservationMinute", reservationMinute);
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert(' " + reservationDay + " " + reservationHour + "시 " + reservationMinute + "분 진료예약 되었습니다.');");
				out.println("location.replace('"+ moveLocation +"');");
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
