package vo.fetch;

public class FetchForward<T> {
	private T result; //받은 데이터
	private String message; //전달할 메시지
	
	private String type; //실패 시 기록
	public FetchForward() {}
	
	public FetchForward(T result, String message, String type) {
		this.result = result;
		this.message = message;
		this.type = type;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
