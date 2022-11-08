package action.reservation;

import action.Action;
import svc.reservation.ReservationFormService;
import svc.reservation.ReservationModifyService;
import svc.treatment.TreatmentAddService;
import vo.ActionForward;
import vo.reservation.ReservationBean;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TreatmentAddConfirmAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        
        int reservation_code = -1;
        try {
        	//예약진료코드를 받아옴
        	reservation_code = Integer.parseInt(request.getParameter("reservation_code"));
        }catch(Exception e) {
        	System.out.println("진료테이블 추가 작업 중 예외 발생-reservation_code가 undefined");
        	forward = new ActionForward("index.do", true);
        	return forward; //실행 중지
        }
        
        System.out.println("reservation_code : " + reservation_code);
        //필요한 서비스객체 생성
        ReservationFormService reservationFormService = new ReservationFormService();
        TreatmentAddService treatmentAddService = new TreatmentAddService();
        ReservationModifyService reservationModifyService = new ReservationModifyService();
        
        //받아온 예약코드로 예약진료정보를 담은 bean객체 만듬
        ReservationBean reservationInfo = reservationFormService.selectReservationInfo(reservation_code);

        //진료확인이 된 예약을 진료테이블에 insert하려 하는 경우
        if(reservationInfo.getTreatment_status().equals("Y")) {
        	response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('진료확인 작업 중 문제가 발생하였습니다. 이미 진료확인된 예약이 아닌지 확인해주세요.');");
			out.println("history.back();");
			out.println("</script>");
			
			return forward; //아래 구문들이 실행되지 않도록 return 시킴(위의 스크립트에서 뒤로가기 하므로 forward 미지정)
        }
        
        //받아온 예약목록을 진료테이블에 insert함
        int result = treatmentAddService.insertTreatment_Reservation(reservationInfo);
        
        if (result > 0) {
        	//진료확인 플래그 "Y"
        	reservationInfo.setTreatment_status("Y");
        	//reservation 테이블 데이터 업데이트(예약테이블 treatment_status 업데이트)
        	int statusUpdate =  reservationModifyService.modifyReservationTreatment(reservationInfo);
        	
        	if(statusUpdate > 0) {
        		response.setContentType("text/html;charset=UTF-8");
    			PrintWriter out = response.getWriter();
    			
    			out.println("<script>");
    			out.println("alert('예약된 진료 이행 확인이 완료되었습니다.');");
    			out.println("location.replace('reservationMemberSearch.ad?u_id="+ reservationInfo.getId() +"')");
    			out.println("</script>");
        		System.out.println("treatment_status 갱신 성공");
        	}else {
        		System.out.println("[ERROR]treatment_status 갱신 실패");
        	}
        }else {
        	response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('진료확인 작업 중 문제가 발생하였습니다. 이미 진료확인된 예약이 아닌지 확인해주세요.');");
			out.println("history.back();");
			out.println("</script>");
			
			return forward; //아래 구문들이 실행되지 않도록 return 시킴(위의 스크립트에서 뒤로가기 하므로 forward 미지정)
        }
        
		/*
		 * request.setAttribute("showPage", "admin/reservationCheckList.jsp");
		 * 
		 * //예약진료내역 페이지로 이동 forward = new
		 * ActionForward("mypage/mypageTemplate.jsp",false);
		 */
        return forward;
    }
}
