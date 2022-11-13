package vo;

public class AttachFileBean {
	private int file_idx;
	private int board_idx;
	private String original_name;
	private String save_name;
	private long size;
	private String insert_time;
	
	//기본
	public AttachFileBean() {
		super();
	}
	
	//파일번호와 업로드시간 제외
	public AttachFileBean(int board_idx, String original_name, String save_name, long size) {
		super();
		this.board_idx = board_idx;
		this.original_name = original_name;
		this.save_name = save_name;
		this.size = size;
	}
	
	//모든 정보를 가져오는 생성자
	public AttachFileBean(int file_idx, int board_idx, String original_name, String save_name, long size,
			String insert_time) {
		super();
		this.file_idx = file_idx;
		this.board_idx = board_idx;
		this.original_name = original_name;
		this.save_name = save_name;
		this.size = size;
		this.insert_time = insert_time;
	}

	public int getFile_idx() {
		return file_idx;
	}

	public void setFile_idx(int file_idx) {
		this.file_idx = file_idx;
	}

	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public String getOriginal_name() {
		return original_name;
	}

	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}

	public String getSave_name() {
		return save_name;
	}

	public void setSave_name(String save_name) {
		this.save_name = save_name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}
	
	
}
