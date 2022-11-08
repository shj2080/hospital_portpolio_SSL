package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.qboard_action.*;
import vo.ActionForward;

@WebServlet("*.qna")
public class QBoardFrontController extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public QBoardFrontController() {
        super();
    }
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;

		//게시글 쓰기 폼
		if(command.equals("/qboardWriteForm.qna")){
			action = new QBoardWriteFormAction();
			forward=new ActionForward("/qboard/qboard_write.jsp",false); //생성자를 통해 뿌려주기
			//forward.setPath("/board/board_write.jsp"); 
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		//게시글 쓰기
		else if(command.equals("/qboardWritePro.qna")){
			action  = new QBoardWriteProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//게시판 리스트
		else if(command.equals("/qboardList.qna")){
			action = new QBoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//게시글 디테일
		else if(command.equals("/qboardDetail.qna")){
			action = new QBoardDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//답글 처리 폼 요청
		else if(command.equals("/qboardReplyForm.qna")){
			action = new QBoardReplyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//답글 처리 요청
		else if(command.equals("/qboardReplyPro.qna")){
			action = new QBoardReplyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//수정 폼 요청
		else if(command.equals("/qboardModifyForm.qna")){
			action = new QBoardModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//수정처리 요청
		else if(command.equals("/qboardModifyPro.qna")){
			action = new QBoardModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//삭제처리 요청
		else if(command.equals("/qboardDeletePro.qna")){
			action = new QBoardDeleteProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		/***************************포워딩****************************/
		if(forward != null){
			
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath()); 
			}else{
				RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
			
		}
		
	}
	
	//get요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}  	

	//post요청
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}   
	
}
