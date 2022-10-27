package svc.reservation;

//DB 연결을 위한 패키지 내 모든 static 메서드를 미리 메모리에 올림 -> 바로 호출가능한 상태
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.HospitalDAO;
import vo.Doctor;
import vo.Speciality;

public class ReservationFormService {

	public Speciality selectSpeciality(int speciality_code) {
		Speciality speciality = null;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:UserDAO객체 생성
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		hospitalDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		
		speciality = hospitalDAO.selectSpeciality(speciality_code);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		
		//4.해제
		close(con);//Connection객체 해제
		
		return speciality;
	}

	public ArrayList<Doctor> selectDoctorInfo(int speciality_code) {
		ArrayList<Doctor> doctorList = null;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:UserDAO객체 생성
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		hospitalDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		
		//의사정보 얻어오기
		doctorList = hospitalDAO.selectDoctorInfo(speciality_code);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		
		//4.해제
		close(con);//Connection객체 해제
		
		return doctorList;
	}

}
