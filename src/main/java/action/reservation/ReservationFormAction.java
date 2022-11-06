package action.reservation;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.reservation.ReservationFormService;
import vo.ActionForward;
import vo.Doctor;
import vo.reservation.ReservationBean;
import vo.Speciality;

public class ReservationFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//디버깅 코드 (debug)
		System.out.println("[DEBUG] specialityCode : " + request.getParameter("specialityCode"));
		System.out.println("[DEBUG] modifyState : " + request.getParameter("modifyState"));
		
		//진료과 코드 (진료과 이름과 의사관련 정보 얻기 위해 사용)
		int speciality_code = Integer.parseInt(request.getParameter("specialityCode"));
		
		//예약 수정을 확인하기 위한 파라메터
		String modifyState = request.getParameter("modifyState");
		
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
	  		//===폼 공통 부분===
	  		
	  		//진료예약폼 서비스 객체 생성
	  		ReservationFormService reservationFormService = new ReservationFormService();
	  		
	  		//진료과 코드에 해당하는 진료과 이름을 DB에서 가져옴
	  		Speciality speciality = reservationFormService.selectSpeciality(speciality_code);
	  		
	  		//선택한 진료과 코드에 해당하는 의사 명단을 DB에서 가져옴
	  		List<Doctor> doctorList = reservationFormService.selectDoctorInfo(speciality_code);
	  		
	  		//해당 진료과에 의사가 없는 경우 오류 출력 후 이전 페이지로
	  		if(doctorList == null) {
	  			response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('해당 진료과에 등록된 의사가 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				
				return forward; //아래 구문이 실행되는 것을 막기 위해 리턴
	  		}
	  		
	  		//진료과 코드로 얻어온 진료과 이름을 request영역에 셋팅
	  		request.setAttribute("speciality_nameAttr", speciality.getSpeciality_name());
	  		
	  		//의사 코드와 진료과 코드로 얻어온 의사 이름을 request영역에 셋팅
	  		request.setAttribute("doctorList", doctorList);
	  		
	  		
	  		//===폼 공통 부분===
	  		
	  		
	  		//예약수정 파라미터가 없다면 기본값으로 초기화된 폼으로 진행하지만
	  		//예약 진료를 수정하는 파라미터가 있다면 예약코드값으로 정보를 받아와 폼에 대입해야함
	  		if(modifyState != null && !modifyState.equals("")) {
	  			//예약 코드를 넘겨받음
	  			int reservation_code = Integer.parseInt(request.getParameter("reservation_code"));

				  //예약정보
	  			ReservationBean reservation = reservationFormService.selectReservationInfo(reservation_code);
				
	  			DateTimeFormatter defaultHourFormat = DateTimeFormatter.ofPattern("H");
	  			DateTimeFormatter defaultMinuteFormat = DateTimeFormatter.ofPattern("m");
	  			DateTimeFormatter defaultDayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  			DateTimeFormatter defaultDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	  			
	  			LocalDateTime reservationDate_datetime = LocalDateTime.parse(reservation.getReservation_date(), defaultDateFormat);
	  			String reservationHour = reservationDate_datetime.format(defaultHourFormat);
	  			String reservationMinute = reservationDate_datetime.format(defaultMinuteFormat);
	  			reservation.setReservation_date(reservationDate_datetime.format(defaultDayFormat));
	  			
	  			System.out.println("[DEBUG]reservationHour : " + reservationHour);
	  			System.out.println("[DEBUG]reservationMinute : " + reservationMinute);

			    //불러온 예약 정보의 id로 회원의 이름을 구함
				String resUserName = reservationFormService.selectMemberNameInfo(reservation.getId()).getName();

	  			//불러온 예약 정보를 폼에 대입
				request.setAttribute("resUserName", resUserName);
	  			request.setAttribute("resBean", reservation);
	  			request.setAttribute("resHour", reservationHour);
	  			request.setAttribute("resMinute", reservationMinute);
	  		}
	  		
	  		forward = new ActionForward("reservation/reservationForm.jsp", false);
	  	}
		
		return forward;
	}

}
