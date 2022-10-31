package vo;

public class Speciality {
	private int speciality_code;
	private String speciality_name;
	
	public Speciality() {}

	//진료과, 진료과명
	public Speciality(int speciality_code, String speciality_name) {
		super();
		this.speciality_code = speciality_code;
		this.speciality_name = speciality_name;
	}



	public int getSpeciality_code() {
		return speciality_code;
	}

	public void setSpeciality_code(int speciality_code) {
		this.speciality_code = speciality_code;
	}

	public String getSpeciality_name() {
		return speciality_name;
	}

	public void setSpeciality_name(String speciality_name) {
		this.speciality_name = speciality_name;
	}
	
	
}
