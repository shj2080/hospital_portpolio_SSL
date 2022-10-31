package action.reservation;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.reservation.ReservationFormService;
import vo.ActionForward;
import vo.Doctor;
import vo.Speciality;

public class ReservationFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//진료과 코드 (진료과 이름과 의사관련 정보 얻기 위해 사용)
		int speciality_code = Integer.parseInt(request.getParameter("specialityCode"));
		
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
	  		}
	  		
	  		//진료과 코드로 얻어온 진료과 이름을 request영역에 셋팅
	  		request.setAttribute("speciality_nameAttr", speciality.getSpeciality_name());
	  		
	  		//의사 코드와 진료과 코드로 얻어온 의사 이름을 request영역에 셋팅
	  		request.setAttribute("doctorList", doctorList);
	  		
	  		forward = new ActionForward("reservation/reservationForm.jsp", false);
	  	}
		
		return forward;
	}

}
