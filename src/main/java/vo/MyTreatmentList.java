package vo;

public class MyTreatmentList {
	private String treatment_date;
	private String reservation_date;
	private String doctor_name;
	private String speciality_name;

	//기본 생성자
	public MyTreatmentList() {}

	//모든 회원정보
	public MyTreatmentList(String treatment_date, String doctor_name, String speciality_name) {
		super();
		this.treatment_date = treatment_date;
		this.doctor_name = doctor_name;
		this.speciality_name = speciality_name;
	}
	
	//메서드
	public String getTreatment_date() {
		return treatment_date;
	}

	public void setTreatment_date(String treatment_date) {
		this.treatment_date = treatment_date;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getSpeciality_name() {
		return speciality_name;
	}

	public void setSpeciality_name(String speciality_name) {
		this.speciality_name = speciality_name;
	}

	public String getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
	}
}
