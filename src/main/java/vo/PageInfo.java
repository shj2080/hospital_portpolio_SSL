package vo;

//페이지 관리 자바빈
public class PageInfo {
	
	private int currentPage; //현재 페이지
	private int totalPages;	//전체 페이지 수
	private int startPage;	//시작 페이지
	private int endPage;	//끝 페이지
	private int totalPosts;	//전체 글 수
	private int boardLimit; //한번에 표시할 게시글 수 제한
	
	//기본생성자
	public PageInfo() {}

	//전체 생성자
	public PageInfo(int currentPage, int totalPages, int startPage, int endPage, int totalPosts, int boardLimit) {
		super();
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totalPosts = totalPosts;
		this.boardLimit = boardLimit;
	}
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	

	public void setCurrentPage(int page) {
		this.currentPage = page;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int maxPage) {
		this.totalPages = maxPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getTotalPosts() {
		return totalPosts;
	}
	
	public void setTotalPosts(int listCount) {
		this.totalPosts = listCount;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}
	
}
