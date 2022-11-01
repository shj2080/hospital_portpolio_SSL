package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MyTreatmentListService;
import svc.TreatmentListSearchService;
import svc.TreatmentListService;
import vo.ActionForward;
import vo.MyTreatmentList;
import vo.TreatmentList;

public class MyTreatmentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		MyTreatmentListService myTreatmentListService = new MyTreatmentListService();
		
		String id = request.getParameter("id");
		
		ArrayList<MyTreatmentList> myTreatmentList = myTreatmentListService.getMyTreatmentList(id);
		
		System.out.println("[DEBUG]MyTreatmentListAction의 ID 파라미터 값:" + id);
		
		System.out.println("[DEBUG]MyTreatmentListAction의 myTreatmentList :" + myTreatmentList.isEmpty());
		request.setAttribute("id", id);
		request.setAttribute("myTreatmentList", myTreatmentList);
		forward = new ActionForward("myTreatmentList.jsp", false);
		
		return forward;
	}

}

