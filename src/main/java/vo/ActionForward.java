package vo;
//컨트롤러에서 비지니스 로직을 처리하고 뷰 페이지로 포워딩할 때 포워딩에 필요한 정보를 저장하는 클래스
public class ActionForward {
	//이동할 뷰 페이지의 URL
	private String path = null;
	
	//포워딩방식(디스패치나 리다이렉트) : false이면 디스패치(기존요청), true이면 리다이렉트(새요청)
	private boolean redirect = false;

	//매개변수가 없는 생성자 : 기본생성자
	public ActionForward() {}
	
	//매개변수가 있는 생성자
	public ActionForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}
	
	//경로
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean isRedirect() {
		return redirect;
	}
	
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
}
