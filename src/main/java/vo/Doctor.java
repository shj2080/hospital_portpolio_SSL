package vo;

public class Doctor {
	private int doctor_code;
	private String doctor_name;
	private int speciality_code;
	
	public Doctor() {}
	
	//get~set 메서드
	public int getDoctor_code() {
		return doctor_code;
	}

	public void setDoctor_code(int doctor_code) {
		this.doctor_code = doctor_code;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public int getSpeciality_code() {
		return speciality_code;
	}

	public void setSpeciality_code(int speciality_code) {
		this.speciality_code = speciality_code;
	}
}
