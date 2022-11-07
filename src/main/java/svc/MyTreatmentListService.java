package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;
import java.sql.Connection;
import java.util.ArrayList;

import dao.HospitalDAO;
import vo.MyTreatmentList;
import vo.TreatmentList;

public class MyTreatmentListService {
	//멤버변수
	//생성자
	//메서드
	
	//진료대기리스트
	public ArrayList<MyTreatmentList> getMyTreatmentList(String id){
		ArrayList<MyTreatmentList> myTreatmentList = null;
		
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		
		//2.싱글톤 패턴:UserDAO객체 생성
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		
		//3.DB작업에 사용될 Connection객체를 UserDAO의 멤버변수로 삽입하여 DB 연결
		hospitalDAO.setConnection(con);
		
		myTreatmentList = hospitalDAO.myTreatmentList(id);
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return myTreatmentList;
	}
}
