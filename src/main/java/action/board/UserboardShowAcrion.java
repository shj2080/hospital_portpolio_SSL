package action.board;

import java.util.ArrayList;
import java.util.List;

import static util.BoardPaging.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.UserboardShowService;
import vo.ActionForward;
import vo.PageInfo;
import vo.User_board;

public class UserboardShowAcrion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserboardShowService userboardShowService = new UserboardShowService();
		
		//ArrayList<User_board> boardList= usserboardShowService.getBoardList();
		
		//------------페이지 처리------------------//
		
		//게시판 입장 시 기본 페이지값
		int currentPage = 1;
		
		//페이지 파라미터가 있는 경우 해당 페이지값 들어옴
		if(request.getParameter("page") != null && !request.getParameter("page").equals("")) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//한 페이지에 표시될 페이지 개수
		int displayPageNum = 10;
		
		//한 페이지에 출력될 게시글 수
		int boardLimit = 10;
		
		//전체 게시글 수
		int totalPosts = userboardShowService.getCountPosts();
	
		//전체 페이지
		int totalPages = totalPages(totalPosts, boardLimit);
		
		//페이지 값 시작
		int startPage = startPage(currentPage, displayPageNum);
		
		//끝 페이지
		int endPage = endPage(currentPage, displayPageNum, totalPages);
		
		//이전, 다음
		boolean prevPage = hasPrev(startPage);
		boolean nextPage = hasNext(endPage, totalPages);
		
		
		PageInfo pageInfo = new PageInfo(currentPage, totalPages, startPage, endPage, totalPosts, boardLimit);
		//------------페이지 처리------------------//
		
		//게시글 목록
		List<User_board> boardList = userboardShowService.getLimitBoardListDetail(currentPage, boardLimit);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("hasPrev", prevPage);
		request.setAttribute("hasNext", nextPage);
		request.setAttribute("showPage", "boardList.jsp");
		
		//forward = new ActionForward("menuTemplate.jsp", false);
		
		//return forward;
		return new ActionForward("boardList.jsp?page="+currentPage, false);
		
	}

}
