package fetch.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fetch.FetchAction;
import svc.board.UserBoardDeleteService;
import vo.User_board;

public class UserBoardDeleteFetch implements FetchAction {

	@Override
	public boolean ProcessResult(HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean result = false;
		
		//로그인 상태 체크를 위해 세션의 아이디를 가져옴
		HttpSession session = request.getSession();
	    String viewId = (String)session.getAttribute("userID");
	    String userType = (String)session.getAttribute("userType");
	    
	    int post_no = Integer.parseInt(request.getParameter("post_no"));
	    
		//현재 로그인된 상태면서 관리자인 경우
		if(viewId != null && userType.equals("M")) {
			
			//받아온 파라미터로 게시판 bean 객체 설정
			User_board userBoard = new User_board();
			userBoard.setId(viewId);
			userBoard.setPost_no(post_no);
			
			UserBoardDeleteService userBoardDeleteService = new UserBoardDeleteService();
			result = userBoardDeleteService.deletePost(userBoard);
			
		}
		
		return result;
	}

}
