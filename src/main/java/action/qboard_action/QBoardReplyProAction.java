package action.qboard_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.qboard_svc.*;
import vo.ActionForward;
import vo.QBoardBean;

public class QBoardReplyProAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	ActionForward forward = null;
		 	
		 	//현재 페이지
		    String nowPage = request.getParameter("page");
		 	QBoardBean article = new QBoardBean();  		
		 	
		 	//작성자 ID 받아옴
		 	article.setMEM_ID(request.getParameter("loginID"));
		 	//article.setQBOARD_NUM(Integer.parseInt(request.getParameter("qboard_num"))); //게시글 번호
		 	article.setQBOARD_SUBJECT(request.getParameter("QBOARD_SUBJECT"));	//제목
		 	article.setQBOARD_CONTENT(request.getParameter("QBOARD_CONTENT"));	//내용

		 	article.setQBOARD_RE_REF(Integer.parseInt(request.getParameter("QBOARD_RE_REF")));	//답글 달릴 부모글
		 	article.setQBOARD_RE_LEV(Integer.parseInt(request.getParameter("QBOARD_RE_LEV")));	//답글 깊이
		 	article.setQBOARD_RE_SEQ(Integer.parseInt(request.getParameter("QBOARD_RE_SEQ")));	//답글 순서
		 	QBoardReplyProService boardReplyProService = new QBoardReplyProService();
		 	boolean isReplySuccess = boardReplyProService.replyArticle(article); //답글 작성 서비스에서 DAO접근 메서드 호출
		 	
		 	//답글 작성 성공 여부 판단
	   		if(isReplySuccess){
	   			forward = new ActionForward("qboardList.qna?page=" + nowPage, true);
	   		}
	   		else{
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out = response.getWriter();
	   			out.println("<script>");
	   			out.println("alert('답글 작성을 실패했습니다. 잠시 후 다시 시도해 주세요.')");
	   			out.println("history.back()");
	   			out.println("</script>");
	   		}
	   		
	   		return forward;
	   		
	}  	
	 
}
