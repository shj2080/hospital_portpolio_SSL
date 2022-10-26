package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.TreatmentListSearchService;
import svc.TreatmentListService;
import vo.ActionForward;
import vo.TreatmentList;

public class TreatmentListSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		TreatmentListSearchService treatmentListSearchService = new TreatmentListSearchService();
		
		String speciality = request.getParameter("speciality");
		
		ArrayList<TreatmentList> treatmentList = treatmentListSearchService.getSearchTreatmentList(speciality);
		
		System.out.println("[DEBUG]TreatmentListSearchAction의 speciality 파라미터 값:" + speciality);
		request.setAttribute("speciality", speciality);
		request.setAttribute("treatmentList", treatmentList);
		forward = new ActionForward("treatmentList.jsp", false);
		
		return forward;
	}

}

