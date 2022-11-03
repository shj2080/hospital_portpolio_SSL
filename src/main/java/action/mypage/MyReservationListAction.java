package action.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.mypage.MyReservationListService;
import vo.ActionForward;
import vo.ReservationBean;
import vo.mypage.MyReservationListBean;

import java.io.PrintWriter;
import java.util.List;

public class MyReservationListAction implements Action {

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
			//서비스 객체 생성
			MyReservationListService myReservationListService = new MyReservationListService();
			List<MyReservationListBean> myReservationInfo = myReservationListService.getMyReservationList(viewId);

			//request에 List 담음
			request.setAttribute("myReservationList", myReservationInfo);

			//템플릿에 표시되는 페이지 변경
			request.setAttribute("showPage", "myReservationList.jsp");

			forward = new ActionForward("mypage/mypageTemplate.jsp", false);
		}
		return forward;
	}

}
