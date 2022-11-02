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
	
	private static ReservationDAO hospitalDAO;
	
	//객체가 없는 경우 생성
	public static ReservationDAO getInstance() {
		
		if(hospitalDAO == null) { //DAO 객체가 없는 경우
			hospitalDAO = new ReservationDAO(); //객체 생성
		}
		
		return hospitalDAO; //객체 주소 반환
	}
	/**********************************************************/
	
	//커넥션 설정
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//회원들의 진료/예약정보를 불러올 메서드
	public ArrayList<TreatmentList> selectTreatmentList() {
		ArrayList<TreatmentList> selectTreatmentList = null;
		
		/*
		String sql = "select treatment_date, name, doctor_name, speciality_name from" +
				  " doctor natural join speciality join treatment USING(speciality_code) join membertbl USING(id)"+
						 "where treatment_date > now() order by treatment_date asc";
		 */
		String sql = "select treatment_date, name, doctor_name, speciality_name";
		sql += " from treatment t LEFT JOIN membertbl m ON t.id = m.id";
		sql += " LEFT JOIN speciality spec ON t.speciality_code = spec.speciality_code";
		sql += " LEFT JOIN doctor d ON t.doctor_code = d.doctor_code";
		sql += " WHERE treatment_date > now() order by treatment_date asc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
					
			if(rs.next()){
				selectTreatmentList = new ArrayList<TreatmentList>();
						
						do {
							TreatmentList treatment = new TreatmentList(
															rs.getString("treatment_date"),
															rs.getString("name"),
															rs.getString("doctor_name"),
															rs.getString("speciality_name")
									);
							selectTreatmentList.add(treatment);
						} while (rs.next());
						
					}
					
				} catch (Exception e) {
					System.out.println("slectTreatmentList() 메서드 예외 발생 : " + e); //예외종류 + 예외메세지
				} finally {
					close(rs);
					close(pstmt);
				}
		return selectTreatmentList;
	}
	
	//원하는 진료과로 예약 대기자 명단불러오기 메서드
	public ArrayList<TreatmentList> treatmentListSearch(String speciality_name) {
		ArrayList<TreatmentList> treatmentListSearch = null;
		
		String sql = "select treatment_date, name, doctor_name, speciality_name";
		sql += " from treatment t LEFT JOIN membertbl m ON t.id = m.id";
		sql += " LEFT JOIN speciality spec ON t.speciality_code = spec.speciality_code";
		sql += " LEFT JOIN doctor d ON t.doctor_code = d.doctor_code";
		sql += " WHERE treatment_date > now() AND speciality_name = ? order by treatment_date asc;";
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, speciality_name);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				treatmentListSearch = new ArrayList<TreatmentList>();
				
				do {
					TreatmentList treatmentList = new TreatmentList(
										rs.getString("treatment_date"),
										rs.getString("name"),
										rs.getString("doctor_name"),
										rs.getString("speciality_name")
									);
					treatmentListSearch.add(treatmentList);
				} while (rs.next());
				
			}
			
		} catch (Exception e) {
			System.out.println("treatmentListSearch() 메서드 예외 발생 : " + e); //예외종류 + 예외메세지
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return treatmentListSearch;
	}
	public int insertReservationTreatment(ReservationBean reservationBean) {
		int insertResult = 0;
		
		String sql = "insert into reservation(speciality_code, doctor_code, id, reservation_date, phone)";
		sql += " values(?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			
			//?안에 들어갈 값 세팅
			pstmt.setInt(1, reservationBean.getSpeciality_code());
			pstmt.setInt(2, reservationBean.getDoctor_code());
			pstmt.setString(3, reservationBean.getId());
			pstmt.setString(4, reservationBean.getReservation_date());
			pstmt.setString(5, reservationBean.getPhone());
			
			insertResult = pstmt.executeUpdate();

		} catch(Exception e) {
			System.out.println("[HospitalDAO] insertReservationTreatment 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
		}
		
		return insertResult;
	}
	
	public ArrayList<MyTreatmentList> myTreatmentList(String id) {
		ArrayList<MyTreatmentList> myTreatmentList = null;
		MyTreatmentList mytreatmentList = null;
		
		/*
		String sql = "select treatment_date, name, doctor_name, speciality_name from" +
				  " doctor natural join speciality join treatment USING(speciality_code) join membertbl USING(id)"+
						 "where treatment_date > now() order by treatment_date asc";
		 */
		String sql = "select treatment_date, doctor_name, speciality_name";
			   sql += " from treatment t LEFT JOIN membertbl m ON t.id = m.id";
			   sql += " LEFT JOIN speciality spec ON t.speciality_code = spec.speciality_code";
			   sql += " LEFT JOIN doctor d ON t.doctor_code = d.doctor_code";
			   sql += " WHERE t.id = ? order by treatment_date asc";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
					
			if(rs.next()){
				myTreatmentList = new ArrayList<MyTreatmentList>();
						
						do {
							mytreatmentList = new MyTreatmentList(rs.getString("treatment_date"),
												rs.getString("speciality_name"),
												rs.getString("doctor_name"));
							//ArrayList에 추가
							myTreatmentList.add(mytreatmentList);
						} while (rs.next());
						
					}
					
				} catch (Exception e) {
					System.out.println("myTreatmentList() 메서드 예외 발생 : " + e); //예외종류 + 예외메세지
				} finally {
					close(rs);
					close(pstmt);
				}
		return myTreatmentList;
	}
	
}
