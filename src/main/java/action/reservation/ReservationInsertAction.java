package action.reservation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.reservation.ReservationInsertService;
import vo.ActionForward;

public class ReservationInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		String treatmentDay = request.getParameter("treatmentDay");
		String treatmentHour = request.getParameter("treatmentHour");
		String treatmentMinute = request.getParameter("treatmentMinute");
		String name = request.getParameter("name");
		String speciality_code = request.getParameter("speciality_code");
		String doctor_code = request.getParameter(speciality_code);
		
		ReservationInsertService reservationInsertService = new ReservationInsertService();
		
		return forward;
	}

}
