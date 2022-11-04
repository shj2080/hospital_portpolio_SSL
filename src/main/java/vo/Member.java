package vo;

import util.SHA256;

public class Member {
	private String name;
	private String id_num;
	private String id;
	private String password;
	private String address1;
	private String address2;
	private String address3;
	private String postcode;
	private String phone;
	private String userType;

	//기본 생성자
	public Member() {}

	//로그인 시 사용하는 정보
	public Member(String id, String password) {
		super();
		this.id = id;
		//생성자에서 비밀번호 암호화
		this.password = SHA256.encodeSHA256(password);
	}

	//모든 회원정보(유저타입 제외)
	public Member(String name, String id_num, String id, String password, String address1, String address2,
			String address3, String postcode, String phone) {
		super();
		this.name = name;
		this.id_num = id_num;
		this.id = id;
		//생성자에서 비밀번호 암호화
		this.password = SHA256.encodeSHA256(password);
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.postcode = postcode;
		this.phone = phone;
	}
	
	//모든 회원정보(유저타입 포함 초기화 필요 시)
	public Member(String name, String id_num, String id, String password, String address1, String address2,
			String address3, String postcode, String phone, String userType) {
		super();
		this.name = name;
		this.id_num = id_num;
		this.id = id;
		//생성자에서 비밀번호 암호화
		this.password = SHA256.encodeSHA256(password);
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.postcode = postcode;
		this.phone = phone;
		this.userType = userType;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId_num() {
		return id_num;
	}
	public void setId_num(String id_num) {
		this.id_num = id_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
