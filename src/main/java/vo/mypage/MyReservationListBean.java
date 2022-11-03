package vo.mypage;

public class MyReservationListBean {
    private int reservation_code;
    private String reservation_date;
    private String doctor_name;
    private String speciality_name;
    private int doctor_code;
    private int speciality_code;
    
    //기본생성자
    public MyReservationListBean() {}

    //멤버변수를 생성시점부터 초기화 하는 생성자(코드 제외)
    public MyReservationListBean(String reservation_date, String doctor_name, String speciality_name, int doctor_code,
			int speciality_code) {
		super();
		this.reservation_date = reservation_date;
		this.doctor_name = doctor_name;
		this.speciality_name = speciality_name;
		this.doctor_code = doctor_code;
		this.speciality_code = speciality_code;
	}
    
    //멤버변수를 생성시점부터 초기화 하는 생성자(코드 포함)
    public MyReservationListBean(int reservation_code, String reservation_date, String doctor_name,
			String speciality_name, int doctor_code, int speciality_code) {
		super();
		this.reservation_code = reservation_code;
		this.reservation_date = reservation_date;
		this.doctor_name = doctor_name;
		this.speciality_name = speciality_name;
		this.doctor_code = doctor_code;
		this.speciality_code = speciality_code;
	}

	

	public int getReservation_code() {
        return reservation_code;
    }

    public void setReservation_code(int reservation_code) {
        this.reservation_code = reservation_code;
    }

    public String getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
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

	public int getDoctor_code() {
		return doctor_code;
	}

	public void setDoctor_code(int doctor_code) {
		this.doctor_code = doctor_code;
	}

	public int getSpeciality_code() {
		return speciality_code;
	}

	public void setSpeciality_code(int speciality_code) {
		this.speciality_code = speciality_code;
	}
    
}
