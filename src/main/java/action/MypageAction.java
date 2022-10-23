package action;

import svc.MypageService;
import vo.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MypageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;

        //마이페이지 서비스 객체 생성
        MypageService mypageService = new MypageService();

        return forward;
    }
}
