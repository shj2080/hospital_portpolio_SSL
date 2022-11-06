package vo.reservation;

public class ReservationListBean {
    private int reservation_code;
    private String reservation_date;
    private String doctor_name;
    private String speciality_name;
    private int doctor_code;
    private int speciality_code;

    private String id;
    private String name;

    //id와 이름값을 얻어오는 부분 추가
    public ReservationListBean(int reservation_code, String reservation_date, String doctor_name, String speciality_name, int doctor_code, int speciality_code, String id, String name) {
        this.reservation_code = reservation_code;
        this.reservation_date = reservation_date;
        this.doctor_name = doctor_name;
        this.speciality_name = speciality_name;
        this.doctor_code = doctor_code;
        this.speciality_code = speciality_code;
        this.id = id;
        this.name = name;
    }

    //기본생성자
    public ReservationListBean() {}

    //멤버변수를 생성시점부터 초기화 하는 생성자(코드 제외)
    public ReservationListBean(String reservation_date, String doctor_name, String speciality_name, int doctor_code,
                               int speciality_code) {
		super();
		this.reservation_date = reservation_date;
		this.doctor_name = doctor_name;
		this.speciality_name = speciality_name;
		this.doctor_code = doctor_code;
		this.speciality_code = speciality_code;
	}
    
    //멤버변수를 생성시점부터 초기화 하는 생성자(코드 포함)
    public ReservationListBean(int reservation_code, String reservation_date, String doctor_name,
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
