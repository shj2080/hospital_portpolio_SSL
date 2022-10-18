package vo;

public class Member {
	private String name;
	private String id_num;
	private String id;
	private String password;
	private String address;
	private String phone;
	
	public Member(String name, String id_num, String id, String password, String address, String phone) {
		super();
		this.name = name;
		this.id_num = id_num;
		this.id = id;
		this.password = password;
		this.address = address;
		this.phone = phone;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
