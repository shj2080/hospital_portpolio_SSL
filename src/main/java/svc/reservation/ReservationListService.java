package svc.reservation;

import dao.ReservationDAO;
import vo.reservation.ReservationListBean;

import java.sql.Connection;
import java.util.List;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

public class ReservationListService {
    
    //특정 회원ID로 예약된 리스트 출력하는 메서드
    public List<ReservationListBean> getReservationList(String userID) {
        List<ReservationListBean> myReservationList = null;

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
    
	//특정 회원ID로 예약된 리스트 출력하는 메서드 - 관리자
    public List<ReservationListBean> getAllReservationList(String userID) {
        List<ReservationListBean> allReservationList = null;

        //커넥션 풀에서 커넥션 객체 얻어오기
        Connection con = getConnection();

        //DAO 객체(싱글톤 패턴)
        ReservationDAO reservationDAO = ReservationDAO.getInstance();

        //DB작업에 사용될 Connection 객체를 DAO 멤버변수에 전달
        reservationDAO.setConnection(con);

        //해당 메서드를 호출하여 처리
        allReservationList = reservationDAO.allReservationList(userID);

        //DB 연결 해제
        close(con);

        return allReservationList;
    }
}
