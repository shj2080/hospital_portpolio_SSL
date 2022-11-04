package vo;


public class User_board {
	private int post_no;
	private String post_date;
	private String post_pwd;
	private String post_subject;
	private String post_text;
	private String post_file;
	
	private String id;
	
	public User_board() {
		super();
	}
	
	
	//게시판 목록 보기용 생성자
	public User_board(int post_no, String post_subject, String id, String post_date) {
		super();
		this.post_no = post_no;
		this.post_subject = post_subject;
		this.id = id;
		this.post_date = post_date;
	}
	
	//게시글 보기 용 생성자
	public User_board(int post_no, String post_subject, String id, String post_date, String post_text) {
		super();
		this.post_no = post_no;
		this.post_subject = post_subject;
		this.id = id;
		this.post_date = post_date;
		this.post_text = post_text;
	}

	//글쓰기 저장용 생성자
	public User_board(int post_no, String post_date, String post_pwd, String post_subject,
			String post_text, String post_file) {
		super();
		this.post_no = post_no;
		this.post_date = post_date;
		this.post_pwd = post_pwd;
		this.post_subject = post_subject;
		this.post_text = post_text;
		this.post_file = post_file;
	}




	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public String getPost_date() {
		return post_date;
	}

	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}

	public String getPost_pwd() {
		return post_pwd;
	}

	public void setPost_pwd(String post_pwd) {
		this.post_pwd = post_pwd;
	}

	public String getPost_subject() {
		return post_subject;
	}

	public void setPost_subject(String post_subject) {
		this.post_subject = post_subject;
	}

	public String getPost_text() {
		return post_text;
	}

	public void setPost_text(String post_text) {
		this.post_text = post_text;
	}

	public String getPost_file() {
		return post_file;
	}

	public void setPost_file(String post_file) {
		this.post_file = post_file;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
