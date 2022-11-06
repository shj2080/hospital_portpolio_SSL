package action.reservation;

import action.Action;
import svc.reservation.ReservationFormService;
import svc.treatment.TreatmentAddService;
import vo.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TreatmentAddConfirmAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;

        //예약진료코드를 받아옴
        String reservation_code = request.getParameter("reservation_code");

        ReservationFormService reservationFormService = new ReservationFormService();
        TreatmentAddService treatmentAddService = new TreatmentAddService();


        //임시 이동 경로 - 수정중!!!
        forward = new ActionForward("errorPage/404error.html",true);
        return forward;
    }
}
