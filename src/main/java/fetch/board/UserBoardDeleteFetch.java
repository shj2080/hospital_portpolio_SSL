package fetch.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fetch.FetchAction;
import svc.board.PostShowService;
import svc.board.UserBoardDeleteService;
import vo.AttachFileBean;
import vo.User_board;
import vo.fetch.FetchForward;

public class UserBoardDeleteFetch implements FetchAction<Boolean> {

	@Override
	public FetchForward<Boolean> executeResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FetchForward<Boolean> fetch = null;
		String alertMessage = null;
		String type = null;
		
		boolean deleteResult = false;
		
		//로그인 상태 체크를 위해 세션의 아이디를 가져옴
		HttpSession session = request.getSession();
	    String viewId = (String)session.getAttribute("userID");
	    String userType = (String)session.getAttribute("userType");
	    
	    int post_no = -1; //초기화
		try {
			post_no = Integer.parseInt(request.getParameter("post_no"));
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException 발생 : " + e);
  			alertMessage = "비정상적인 접근입니다.";
  			type = "FAIL";
  			fetch = new FetchForward<Boolean>(deleteResult, alertMessage, type);
  			return fetch;
		}
	    
		//현재 로그인된 상태면서 관리자인 경우
		if(viewId != null && userType.equals("M")) {
			
			//받아온 파라미터로 게시판 bean 객체 설정
			User_board userBoard = new User_board();
			userBoard.setId(viewId);
			userBoard.setPost_no(post_no);
			
			UserBoardDeleteService userBoardDeleteService = new UserBoardDeleteService();
			PostShowService postShowService = new PostShowService();
			
			//파일 정보를 가져와서 List에 담음
			List<AttachFileBean> attachFiles = postShowService.getAttachFileData(post_no);
			deleteResult = userBoardDeleteService.deletePost(request, userBoard, attachFiles);
			
			if(deleteResult) {
	  			alertMessage = "게시글이 삭제되었습니다.";
	  			type = "OK";
			} else {
	  			alertMessage = "게시글 삭제를 실패했습니다.";
	  			type = "FAIL";
			}
		}else {
  			alertMessage = "삭제 권한이 없습니다.";
  			type = "grade";
		}
		
		fetch = new FetchForward<Boolean>(deleteResult, alertMessage, type);
		
		return fetch;
	}

}
