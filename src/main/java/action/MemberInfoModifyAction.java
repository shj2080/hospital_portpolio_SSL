package action;

import svc.MemberInfoModifyService;
import vo.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberInfoModifyAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;

        //마이페이지 서비스 객체 생성
        MemberInfoModifyService memberInfoModifyService = new MemberInfoModifyService();

        //테스트중..
        request.setAttribute("showPage", "mypage/memberInfoModify.jsp");
        
        forward = new ActionForward("mypage/memberTemplate.jsp", false);
        return forward;
    }
}
