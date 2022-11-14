package fetch.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fetch.FetchAction;
import svc.board.PostShowService;
import util.FileDownUtil;
import vo.AttachFileBean;
import vo.fetch.FetchForward;

public class getAttachFileFetch implements FetchAction<AttachFileBean> {

	@Override
	public FetchForward<AttachFileBean> executeResult(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		//int 초기화
		int file_idx = -1;
		try {
			file_idx = Integer.parseInt(request.getParameter("file_idx"));
		} catch (NumberFormatException e) {
			System.out.println("다운로드 처리중 file_idx NumberFormatException 예외 발생:" + e);
			//FetchForward<AttachFileBean> fetch = new FetchForward<AttachFileBean>(null, null, null);
		}
		
		//파일번호로 파일 정보를 받아옴
		PostShowService postShowService = new PostShowService();
		AttachFileBean attachFile = postShowService.getAttachFile(file_idx);
		
		//파일 다운로드 처리를 위한 객체 생성
		FileDownUtil fileDownUtil = new FileDownUtil();
		
		//실제 파일다운로드가 수행되는 클래스
		fileDownUtil.FileWebDownload(request, response, attachFile);
		
		return null;
	}

}
