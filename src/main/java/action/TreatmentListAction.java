package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.TreatmentListService;
import vo.ActionForward;
import vo.TreatmentList;

public class TreatmentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		TreatmentListService treatmentListService = new TreatmentListService();
		
		ArrayList<TreatmentList> treatmentList = treatmentListService.getTreatmentList();
		
		request.setAttribute("treatmentList", treatmentList);
		forward = new ActionForward("treatmentList.jsp", false);
		
		return forward;
	}

}

