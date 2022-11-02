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

public class HospitalDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//싱글톤 패턴 : DAO 객체 하나만 생성되도록 처리
	
	//생성자
	private HospitalDAO() {}
	
	private static HospitalDAO hospitalDAO;
	
	//객체가 없는 경우 생성
	public static HospitalDAO getInstance() {
		
		if(hospitalDAO == null) { //DAO 객체가 없는 경우
			hospitalDAO = new HospitalDAO(); //객체 생성
		}
		
		return hospitalDAO; //객체 주소 반환
	}
	/**********************************************************/
	
	//커넥션 설정
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//입력한 정보가 DB에 있는 id, password와 일치하는 지 쿼리
	public boolean selectLoginInfo(Member member) {
		boolean checkLoginResult = false;
		
		//쿼리문
		String sql = "select id from membertbl where id = ? AND password = ? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			
			rs = pstmt.executeQuery();
			
			//일치하면 db에서 id 값이 출력됨
			if(rs.next()) {
				checkLoginResult = true;
			} 
			
		} catch(Exception e) {
			System.out.println("[HospitalDAO] selectLoginInfo 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
		
		return checkLoginResult;
	}
	
	//해당 id의 id와 이름을 가져오는 메서드
	public Member selectUserInfo(String id) {
		Member userInfo = null;
		
		//쿼리문
		String sql = "select * from membertbl where id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userInfo = new Member();
				
				//session 영역에 저장할 데이터만 가져와 채움
				userInfo.setId(rs.getString("id"));
				userInfo.setName(rs.getString("name"));
				userInfo.setPhone(rs.getString("phone"));
			} 
			
		} catch(Exception e) {
			System.out.println("[HospitalDAO] selectUserInfo 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
				
		return userInfo;
	}
	
	//입력받은 정보를 membertbl테이블에 insert하는 메서드
	//회원가입 - membertbl
	public boolean join(Member member) {
		int joinCount = 0;
		boolean joinSuccess = false;
		
		String sql="insert into membertbl(name,id_num,id,password,address1,address2,address3,postcode,phone) ";
			sql += " values(?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());				
			pstmt.setString(2, member.getId_num());				
			pstmt.setString(3, member.getId());				
			pstmt.setString(4, member.getPassword());				
			pstmt.setString(5, member.getAddress1());				
			pstmt.setString(6, member.getAddress2());				
			pstmt.setString(7, member.getAddress3());				
			pstmt.setString(8, member.getPostcode());				
			pstmt.setString(9, member.getPhone());				
			
			joinCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음	
			
			if(joinCount > 0) {
				joinSuccess = true;
			}
			
		} catch (Exception e) {			
			System.out.println("[HospitalDAO] join 에러:"+ e);
		} finally {
			//close(rs);
			close(pstmt);
		}	
		return joinSuccess;
	}
	
	//회원의 정보를 가져옴(회원수정폼에서 사용됨) memberInfoModifyForm.jsp
	public Member selectMemberInfo(String id) {
		Member userInfo = null;
		
		//쿼리문
		String sql = "select * from membertbl where id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userInfo = new Member();
				
				userInfo.setName(rs.getString("name"));
				userInfo.setId_num(rs.getString("id_num"));
				userInfo.setId(rs.getString("id"));
				//userInfo.setPassword(rs.getString("password"));
				userInfo.setAddress1(rs.getString("address1"));
				userInfo.setAddress2(rs.getString("address2"));
				userInfo.setAddress3(rs.getString("address3"));
				userInfo.setPostcode(rs.getString("postcode"));
				userInfo.setPhone(rs.getString("phone"));
			} 
			
		} catch(Exception e) {
			System.out.println("[HospitalDAO] selectMemberInfo 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
		
		return userInfo;
	}

    public int updateMemberInfo(Member member) {
		int result = 0;

		String sql = "update membertbl set name = ?,";
		sql += " address1 = ?, address2 = ?, address3 = ?, postcode = ?, phone = ?";

		//쿼리문 분기(비밀번호 아무것도 입력안한 경우)
		if(member.getPassword() != null) {
			sql += ",password = ?";
		}
		
		//수정하려는 회원의 id
		sql += " where id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getAddress1());
			pstmt.setString(3, member.getAddress2());
			pstmt.setString(4, member.getAddress3());
			pstmt.setString(5, member.getPostcode());
			pstmt.setString(6, member.getPhone());

			//비밀번호 입력 안한 경우 수정하지 않음
			if(member.getPassword() != null) {
				pstmt.setString(7, member.getPassword());
				pstmt.setString(8, member.getId());
			} else {
				pstmt.setString(7, member.getId());
			}

			result = pstmt.executeUpdate();

		} catch(Exception e) {
			System.out.println("[HospitalDAO] updateMemberInfo 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
		}

		return result;
    }
    
    //하나의 진료과명을 얻어오는 메서드
	public Speciality selectSpeciality(int speciality_code) {
		Speciality speciality = null;

		String sql = "select * from speciality where speciality_code = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, speciality_code);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				speciality = new Speciality();
				
				speciality.setSpeciality_code(rs.getInt("speciality_code"));
				speciality.setSpeciality_name(rs.getString("speciality_name"));
			}
		} catch(Exception e) {
			System.out.println("[HospitalDAO] selectSpeciality_name 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
		
		return speciality;
	}
	//의사 정보를 얻어오는 메서드
	public ArrayList<Doctor> selectDoctorInfo(int speciality_code) {
		ArrayList<Doctor> doctorInfo = null;
		Doctor doctor = null;
		
		String sql = "select * from doctor where speciality_code = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, speciality_code);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				doctorInfo = new ArrayList<Doctor>();
				
				do {
					doctor = new Doctor(rs.getInt("doctor_code"), rs.getString("doctor_name"), rs.getInt("speciality_code"));
					
					doctorInfo.add(doctor); //받아온 의사 VO들을 ArrayList에 담음
				}while(rs.next());
			}
		} catch(Exception e) {
			System.out.println("[HospitalDAO] selectDoctorInfo 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
		
		return doctorInfo;
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
	
	//원하는 진료과 대기자 명단불러오기 메서드
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
	
	//진료과 테이블의 전체 데이터를 가져옴
	public ArrayList<Speciality> selectSpecialityList() {
		ArrayList<Speciality> specialityList = null;
		Speciality speciality = null;
			
		String sql = "select * from speciality";

		try {
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				specialityList = new ArrayList<Speciality>();
				
				do {
					speciality = new Speciality(rs.getInt("speciality_code"), rs.getString("speciality_name"));
					
					specialityList.add(speciality); //받아온 진료과 VO들을 ArrayList에 담음
				}while(rs.next());
			}
		} catch(Exception e) {
			System.out.println("[HospitalDAO] selectSpecialityList 에러:"+ e);
		} finally { //사용 후 커넥션 해제
			close(pstmt);
			close(rs);
		}
		
		return specialityList;
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
