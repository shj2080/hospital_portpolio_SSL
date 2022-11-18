package action.mypage.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.reservation.ReservationListService;
import vo.ActionForward;
import vo.reservation.ReservationListBean;

import java.io.PrintWriter;
import java.util.List;

public class ReservationSearchListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		//세션에서 로그인된 id 정보와 userType을 가져옴
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("userID");
		String userType = (String)session.getAttribute("userType");

		//현재 로그인된 상태가 아니면
		if(userID == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스입니다.');");
			out.println("location.href='login.do';");
			out.println("</script>");
			//로그인 상태라면
		}else {

			//String searchID = request.getParameter("u_id");
			String searchName = request.getParameter("u_name");
			
			if(!userType.equals("M")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('허가되지 않은 접근입니다.');");
				out.println("history.back();");
				out.println("</script>");
				return forward;
			}

			//예약목록을 불러오는 서비스 객체 생성
			ReservationListService reservationListService = new ReservationListService();
			//ID로 검색시
			//List<ReservationListBean> reservationList = reservationListService.getReservationList(searchID);
			
			//이름으로 검색시
			List<ReservationListBean> reservationList = reservationListService.getReservationNameList(searchName);

			//System.out.println("[DEBUG]reservionList Search 호출 결과 :"+reservationList.toString());


			//가져온 예약목록을 대입
			request.setAttribute("resCheckList",reservationList);

			request.setAttribute("showPage", "admin/reservationCheckList.jsp");
			forward = new ActionForward("mypage/mypageTemplate.jsp", false);
		}
		return forward;
	}

}
