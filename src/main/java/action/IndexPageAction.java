package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.board.UserboardShowService;
import vo.ActionForward;
import vo.User_board;

public class IndexPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//DB작업이 필요한 부분은 여기에 작성
		UserboardShowService UserboardShowService = new UserboardShowService();
		
		//5줄로 제한하여 index에 출력
		ArrayList<User_board> boardList= UserboardShowService.getLimitBoardList(5);
		
		request.setAttribute("boardList", boardList);
		//request.setAttribute("showPage", "index.jsp");
		//--------------------------
		
		//index.jsp로 이동
		forward = new ActionForward("index.jsp", false);
		
		return forward;
	}

}
