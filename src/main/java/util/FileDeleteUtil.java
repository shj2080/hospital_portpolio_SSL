package util;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import vo.AttachFileBean;
import vo.User_board;

public class FileDeleteUtil {

	//게시글의 수정이나 삭제 실패 시 삭제 처리하는 클래스
	public boolean FailPostFileDeleteProcess(HttpServletRequest request, User_board userBoard, List<AttachFileBean> attachFiles) {
		boolean fileDeleteResult = false;
		
		//첨부파일이 있는 경우에만 삭제 실행
		if(attachFiles != null) {
			//--서버에 저장된 파일 삭제 구문--//
			String saveDir = "/board/notice";
			
			//-----파일 저장된 경로 처리------//
			ServletContext context = request.getServletContext();
			String uploadPath = context.getRealPath(saveDir);
			String deleteFilePath = uploadPath + File.separator;
			//-----파일 저장된 경로 처리------//
			
			//파일 접근을 위한 File 객체 선언
			File deleteFile = null;
			
			for(AttachFileBean attachFile:attachFiles) {
				//경로(예) /board/notice/file.jpg
				deleteFile = new File(deleteFilePath + attachFile.getSave_name()); //save_name은 서버상의 실제 파일명
				//파일이 존재하면 삭제
				if(deleteFile.exists()) {
					fileDeleteResult = deleteFile.delete(); //해당 파일을 삭제
				}else {
					System.out.println("Exception:존재하지 않는 파일을 지우려 했습니다.");
				}
			}
			//--서버에 저장된 파일 삭제 구문--//
		}
		
		return fileDeleteResult;
	}
}
