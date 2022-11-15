package action.mypage.admin;

import action.Action;
import vo.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservationCheckListAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;

        //-----
        
        //-----
        
        request.setAttribute("showPage", "admin/reservationCheckList.jsp");
        forward = new ActionForward("mypage/mypageTemplate.jsp", false);

        return forward;
    }
}
