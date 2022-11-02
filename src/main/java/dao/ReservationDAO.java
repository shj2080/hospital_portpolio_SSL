package dao;

//DB 연결을 위해 관련 패키지 import
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Doctor;
import vo.Member;
import vo.MyTreatmentList;
import vo.ReservationBean;
import vo.Speciality;
import vo.TreatmentBean;
import vo.TreatmentList;

public class ReservationDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//싱글톤 패턴 : DAO 객체 하나만 생성되도록 처리
	
	//생성자
	private ReservationDAO() {}
	
	private static ReservationDAO reservationDAO;
	
	//객체가 없는 경우 생성
	public static ReservationDAO getInstance() {
		
		if(reservationDAO == null) { //DAO 객체가 없는 경우
			reservationDAO = new ReservationDAO(); //객체 생성
		}
		
		return reservationDAO; //객체 주소 반환
	}
	/**********************************************************/
	
	//커넥션 설정
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//예약된 본인의 진료내역 불러오는 DAO 메서드
	public ArrayList<ReservationBean> myReservationList(String id) {
		ArrayList<ReservationBean> reservationList = null;
		ReservationBean reservation = null;
		
		String sql = "select reservation_code , reservation_date, doctor_name, speciality_name";
		sql += " from reservation r LEFT JOIN membertbl m ON r.id = m.id";
		sql += " LEFT JOIN speciality spec ON r.speciality_code = spec.speciality_code";
		sql += " LEFT JOIN doctor d ON r.doctor_code = d.doctor_code";
		sql += " WHERE r.id = ? order by reservation_date asc";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
					
			if(rs.next()){
				reservationList = new ArrayList<ReservationBean>();
						
						do {
							reservation = new ReservationBean(0, 0, 0, id, null, null);
							//ArrayList에 추가
							reservationList.add(reservation);
						} while (rs.next());
						
					}
					
				} catch (Exception e) {
					System.out.println("myReservationList() 메서드 예외 발생 : " + e); //예외종류 + 예외메세지
				} finally {
					close(rs);
					close(pstmt);
				}
		return reservationList;
	}
	
}
