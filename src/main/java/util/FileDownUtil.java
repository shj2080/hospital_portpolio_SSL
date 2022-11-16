package util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.AttachFileBean;

public class FileDownUtil {

	//파일 다운로드 메서드
	public void FileWebDownload(HttpServletRequest request, HttpServletResponse response, AttachFileBean attachFileBean) throws Exception {
		
		String savePath = "/board/notice"; //서버에 파일이 업로드된 폴더명 지정
		//이 때, 절대경로 기준의 진짜 경로를 얻어와야 한다.
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath); //예)C:\\board\\notice
		
		//서버에 저장된 파일이름
		String serverFileName = attachFileBean.getSave_name();
		String origFileName = attachFileBean.getOriginal_name();
		
		//다운로드할 파일 경로 설정
		String attachFilePath = sDownloadPath + File.separator + serverFileName;
		
		//파일 객체 생성
		File attachFile = new File(attachFilePath);

		//유형 확인 : 읽어올 경로의 파일유형 -> 페이지 생성할 때 타입을 설정해야 한다.
		//다운로드할 파일의 MIME타입을 얻어와(MIME타입 : image, html, txt...)
		//※ Multipurpose Internet Mail Extensions
		String sMimeType = request.getServletContext().getMimeType(attachFilePath);
		System.out.println("sMimeType(Mime타입 유형)>>>" + sMimeType);

		//지정되지 않은 MIME타입 유형은 예외 발생
		if (sMimeType == null) { //다운로드할 파일의 MIME타입이 제대로 반환되지 않으면
			sMimeType = "application/octet-stream";	//기본 MIME타입으로 이진파일을 위한 기본값으로 지정
		}

		//파일다운로드 시작-유형을 지정해준다.
		//응답할 데이터의 MIME타입을 다운로드할 파일의 MIME타입으로 지정
		response.setContentType(sMimeType); //원래는 "text/html"인데 -> 다운로드할 파일의 MIME타입으로 수정

		//브라우저 사용자 에이전트 확인
		String agent = request.getHeader("User-Agent");
	
		//브라우저에 맞게 다운로드 파일을 인코딩
		String downloadFileName = URLEncoder.encode(origFileName, "UTF-8").replaceAll("\\+", "%20");
		//boolean ieBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);
		
		//한글 파일명이 깨지지 않도록 "UTF-8"로 처리 후 공백부분이 "+"문자로 변경되므로 다시 공백문자(%20)으로 변경해 줘야 함"
		/*
		if (ieBrowser) {
			downloadFileName = URLEncoder.encode(origFileName, "UTF-8").replaceAll("\\+", "%20");
		} else {	////MS사의 IE이거나 Trident이 아니면(예, 크롬이면) 한글 파일명이 깨지지 않도록 파일명을 UTF-8 인코딩 형태의 바이트로 받아서 "ISO-8859-1"로 문자열 생성해줘야 함
			downloadFileName = new String(origFileName.getBytes("UTF-8"), "ISO-8859-1");
			//downloadFileName = URLEncoder.encode(origFileName, "UTF-8");
		}
		*/
		
		//웹 브라우저에서 해석되어 실행되는 파일들(jpg, html 등)도 다운로드 박스가 출력되도록 처리하는 방법
		//헤더 정보 설정 시 "Content-Disposition"값을 "attachment"로 설정하면 모든 파일에 대해서 다운로드 박스가 실행됨
		response.setHeader("Content-Disposition", "attachment; filename=" + downloadFileName);
		response.setHeader("Content-Transfer-Encoding", "binary;");
		
		//파일을 write (다운로드 실행) - file 객체로 FileInputStream 객체를 생성
		//실제 파일 다운로드 역할을 하는 "바이트 기반 출력 스트림 객체" 생성
		try(FileInputStream fis = new FileInputStream(attachFile);
				ServletOutputStream sos = response.getOutputStream();	){

			//한 번에 읽어들일 버퍼
			byte[] b = new byte[10*1024*1024]; //10M
			
			/*
			바이트 배열 b의 인덱스 0부터 한번에 최대 b.length만큼 읽어온다.
			이 때, 파일이 위치한 곳에 연결된 FileInputStream(=in)에서 읽되 끝(-1) 전까지 while문을 돈다.
			*/
			int data = 0;

			/*
			(★자세한 설명 : 입력스트림(in)으로부터 파일에서 b.length개 바이트(=4M 바이트)만큼 읽어
			바이트 배열인 b의 인덱스0부터 b.length개만큼 저장 후
			실제 읽은 바이트 수를 리턴하여 변수 data에 저장.
			
			read()는 입력스트림으로부터 더 이상 읽을 데이터가 없으면 -1 리턴
			
			즉, 배열에서 저장이 시작되는 인덱스를 지정할 수 있고
			한 번에 읽어들이는 바이트 수를 조절할 수 있다.
			*/
			while((data=(fis.read(b, 0, b.length))) != -1){	 //읽어올 게 더 이상 없으면 -1을 리턴하면서 while문을 빠져나감             
				//b배열에 있는 데이터의 0번째부터 최대 data만큼 출려한다.
				sos.write(b, 0, data);		             
			}

			//사용한 버퍼 비움 (자원해제)
			sos.flush();
	    } catch(Exception e) {
	    	//예외 발생 시 예외를 던짐
	    	throw e;
	    }
		
	}
}
