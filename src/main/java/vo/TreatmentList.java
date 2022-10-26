package vo;

import util.SHA256;

public class TreatmentList {
	private String treatment_date;
	private String name;
	private String doctor_name;
	private String speciality_name;

	//기본 생성자
	public TreatmentList() {}

	//모든 회원정보
	public TreatmentList(String treatment_date, String name, String doctor_name, String speciality_name) {
		super();
		this.treatment_date = treatment_date;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
