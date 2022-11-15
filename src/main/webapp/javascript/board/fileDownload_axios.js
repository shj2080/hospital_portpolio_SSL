 //파일다운로드 함수
fileDown = function fileDown(file_code) {
	
	const fileURL = "fileDown.fe";
	
	axios({
		url:fileURL,
		method: "GET",
		responseType:"blob",
		data: {
			file_idx: file_code
		}
	}).then(function(response) {
	
	//서버에서 전달받은 바이너리 데이터를 blob로 변환
	const blob = new Blob([response.data]);
	
	//blob을 사용해 객체 URL을 생성합니다.
    const fileObjectUrl = window.URL.createObjectURL(blob);
    
	//blob 객체 URL을 설정할 링크 생성
	const link = document.createElement("a");
	link.href = fileObjectUrl;
	link.style.display = "none";
	
	//-----다운로드 파일 이름을 추출하는 함수-------------
    const extractDownloadFilename = function(response) {
		 //서버에서 UTF-8로 넘긴 파일명을 디코딩
        const disposition = response.headers["content-disposition"];
        const fileName = decodeURI(
        disposition
            .match(/filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/)[1]
            .replace(/['"]/g, "")
        );
        return fileName;
    };
    //--------------다운로드 파일 이름 추출 함수-----
    
	//다운로드 파일 이름 지정
	link.download = extractDownloadFilename(response);
	
    //body에 추가 후 강제 click 이벤트 발생
    document.body.appendChild(link);
    link.click();
    link.remove();
    
    //리소스 해제
    window.URL.revokeObjectURL(fileObjectUrl);
    
	});
}