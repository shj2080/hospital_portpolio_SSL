package svc.mypage;

import dao.HospitalDAO;
import dao.ReservationDAO;
import vo.ReservationBean;
import vo.mypage.MyReservationListBean;

import java.sql.Connection;
import java.util.List;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

public class MyReservationListService {

    public List<MyReservationListBean> getMyReservationList(String userID) {
        List<MyReservationListBean> myReservationList = null;

        //커넥션 풀에서 커넥션 객체 얻어오기
        Connection con = getConnection();

        //DAO 객체(싱글톤 패턴)
        ReservationDAO reservationDAO = ReservationDAO.getInstance();

        //DB작업에 사용될 Connection 객체를 DAO 멤버변수에 전달
        reservationDAO.setConnection(con);

        //해당 메서드를 호출하여 처리
        myReservationList = reservationDAO.myReservationList(userID);

        //DB 연결 해제
        close(con);


        return myReservationList;
    }
}
