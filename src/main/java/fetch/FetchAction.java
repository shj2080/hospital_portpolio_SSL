package fetch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.fetch.FetchForward;

public interface FetchAction<T> {
	//결과를 Map타입으로 리턴 (제네릭 형식)
	//Map<K, V> ProcessResultMap(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//결과를 리턴(제네릭 형식)
	FetchForward<T> executeResult (HttpServletRequest request, HttpServletResponse response) throws Exception;
}