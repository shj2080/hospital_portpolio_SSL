package fetch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FetchAction<T> {
	//boolean 결과를 리턴하는 메서드
	public default boolean ProcessResult(HttpServletRequest request, HttpServletResponse response) throws Exception {return false;};
	
	//특정 결과를 리턴하는 메서드
	public default T ProcessResultData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
}
