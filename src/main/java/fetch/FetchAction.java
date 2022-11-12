package fetch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FetchAction {
	//단순 boolean 리턴 작업
	public default boolean ProcessResult(HttpServletRequest request, HttpServletResponse response) throws Exception{return false;}
}
