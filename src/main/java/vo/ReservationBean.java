package vo;

public class ReservationBean {
	private int reservation_code;
	private int speciality_code;
	private int doctor_code;
	private String id;
	private String reservation_date;
	private String phone;
	
	//기본 생성자
	public ReservationBean() {}

	//예약코드 포함한 전체 생성자
	public ReservationBean(int reservation_code, int speciality_code, int doctor_code, String id, String reservation_date, String phone) {
		this.reservation_code = reservation_code;
		this.speciality_code = speciality_code;
		this.doctor_code = doctor_code;
		this.id = id;
		this.reservation_date = reservation_date;
		this.phone = phone;
	}

	//예약코드 제외한 전체 생성자
	public ReservationBean(int speciality_code, int doctor_code, String id, String reservation_date, String phone) {
		this.speciality_code = speciality_code;
		this.doctor_code = doctor_code;
		this.id = id;
		this.reservation_date = reservation_date;
		this.phone = phone;
	}
	
	//get~set 메서드

	public int getReservation_code() {
		return reservation_code;
	}

	public void setReservation_code(int reservation_code) {
		this.reservation_code = reservation_code;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
	}
	
}
