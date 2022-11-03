package svc.reservation;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReservationDAO;

public class MyReservationCancelService {

	public int deleteReservation(int reservation_code, String userID) {
		int result = 0;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		
		//2.싱글톤 패턴:DAO객체 생성
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		
		//3.DB작업에 사용될 Connection객체를 DAO의 멤버변수로 삽입하여 DB 연결
		reservationDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		result = reservationDAO.deleteReservationTreatment(reservation_code, userID);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		if(result > 0) {
            commit(con);
        } else {
            rollback(con);
        }
		
		//4.해제
		close(con);//Connection객체 해제
				
		return result;
	}

}
