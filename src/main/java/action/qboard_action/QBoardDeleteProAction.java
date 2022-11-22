package action.qboard_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;


public class QBoardDeleteProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{	 

		ActionForward forward = null;
		
		int qboard_num = -1;
		try {
			//게시글 번호를 받아와서 int 타입으로 변환
			qboard_num = Integer.parseInt(request.getParameter("qboard_num"));
		} catch (NumberFormatException e) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('비정상적인 접근입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
		//머물던 페이지 번호를 가져옴
		String nowPage = request.getParameter("page");
		QBoardDeleteProService boardDeleteProService = new QBoardDeleteProService();
		
		//게시글 삭제 서비스로 DAO에 접근, DB에서 게시글을 삭제 처리함
		boolean isDeleteSuccess = boardDeleteProService.removeArticle(qboard_num);

		//삭제 성공 여부에 따른 동작 결정
		if(!isDeleteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		else{
			forward = new ActionForward("qboardList.qna?page=" + nowPage, true);
		}
		
		return forward;
	}

}
