package svc.qboard_svc;

import java.sql.Connection;

import dao.QBoardDAO;
import vo.QBoardBean;

import static db.JdbcUtil.*;

public class QBoardDetailService {

	public QBoardBean getArticle(int qboard_num) throws Exception{
		// TODO Auto-generated method stub
		
		QBoardBean article = null;
		Connection con = getConnection();
		QBoardDAO boardDAO = QBoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateReadCount(qboard_num); //조회수 업데이트
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = boardDAO.selectArticle(qboard_num);
		close(con);
		return article;
		
	}

}
