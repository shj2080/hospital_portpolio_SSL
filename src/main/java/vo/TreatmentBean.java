package vo;

import java.sql.Timestamp;

public class TreatmentBean {
	private int treatment_code;
	private int speciality_code;
	private int doctor_code;
	private String id;
	private Timestamp treatment_date;
	private String phone;
	
	public TreatmentBean() {}

	public TreatmentBean(int treatment_code, int speciality_code, int doctor_code, String id, Timestamp treatment_date,
			String phone) {
		super();
		this.treatment_code = treatment_code;
		this.speciality_code = speciality_code;
		this.doctor_code = doctor_code;
		this.id = id;
		this.treatment_date = treatment_date;
		this.phone = phone;
	}
	
	public TreatmentBean(int speciality_code, int doctor_code, String id, Timestamp treatment_date, String phone) {
		super();
		this.speciality_code = speciality_code;
		this.doctor_code = doctor_code;
		this.id = id;
		this.treatment_date = treatment_date;
		this.phone = phone;
	}

	//get~set 메서드
	public int getTreatment_code() {
		return treatment_code;
	}

	public void setTreatment_code(int treatment_code) {
		this.treatment_code = treatment_code;
	}

	public int getSpeciality_code() {
		return speciality_code;
	}

	public void setSpeciality_code(int speciality_code) {
		this.speciality_code = speciality_code;
	}

	public int getDoctor_code() {
		return doctor_code;
	}

	public void setDoctor_code(int doctor_code) {
		this.doctor_code = doctor_code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getTreatment_date() {
		return treatment_date;
	}

	public void setTreatment_date(Timestamp treatment_date) {
		this.treatment_date = treatment_date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
