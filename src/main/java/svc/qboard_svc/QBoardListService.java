package svc.qboard_svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.QBoardDAO;
import vo.QBoardBean;

public class QBoardListService {

	public int getListCount() throws Exception{		
		//페이지 카운트 정보
		int listCount = 0;
		Connection con = getConnection();
		
		QBoardDAO boardDAO = QBoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		listCount = boardDAO.selectListCount();
		
		close(con);
		
		return listCount;
		
	}

	public ArrayList<QBoardBean> getArticleList(int page, int limit) throws Exception{
		//게시글 목록
		ArrayList<QBoardBean> articleList = null;
		Connection con = getConnection();
		QBoardDAO boardDAO = QBoardDAO.getInstance();
		boardDAO.setConnection(con);
		//게시글 목록 받아옴(페이지 내 표시되는 글 제한)
		articleList = boardDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}

}
