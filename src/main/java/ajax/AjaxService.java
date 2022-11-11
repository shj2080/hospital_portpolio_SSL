package ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AjaxService {
	public boolean ProcessResult(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
