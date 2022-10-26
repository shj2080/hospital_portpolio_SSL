package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.HospitalDAO;
import vo.TreatmentList;

public class TreatmentListSearchService {
	//멤버변수
		//생성자
		//메서드
	
		
		//회원가입
		public ArrayList<TreatmentList> getSearchTreatmentList(String speciality){
			
			//1.커넥션 풀에서 Connection객체 얻어와
			Connection con = getConnection();
			//2.싱글톤 패턴:UserDAO객체 생성
			HospitalDAO hospitalDAO = HospitalDAO.getInstance();
			//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
			hospitalDAO.setConnection(con);
			
			//ArrayList<TreatmentList> treatmentList = hospitalDAO.selectTreatmentList();
			ArrayList<TreatmentList> treatmentList = hospitalDAO.treatmentListSearch(speciality);
			
			
			//4.해제//Connection객체 해제
			close(con);
			
			return treatmentList;
		}
}
