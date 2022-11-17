 //파일다운로드 함수
 async function fileDown(file_code) {
	
	//서버에 요청하여 해당 첨부파일의 정보를 얻어 파일을 다운로드함(await => 파일데이터가 다 넘어올 때 까지 대기)
	const fileInitData = await fetch("fileDown.fe",{
		method: "POST",
		body: "file_idx="+file_code,
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"}
	}).then(function(response) {return response;});
	
	const fileDataBlob = await fileInitData.blob();

	//다운로드 되는 파일이름을 가져옴
	const disposition = fileInitData.headers.get("Content-Disposition");

	let fileName = "file";
	
	//파일 이름을 서버 응답헤더에서 가져옴
    if(disposition && disposition.indexOf('attachment') !== -1) {
    //파일 이름 정규식으로 파일이름을 가져옴
    	const filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
    	const matches = filenameRegex.exec(disposition);
    	if (matches != null && matches[1]) {
    		fileName = matches[1].replace(/['"]/g, '');
    	} 
    }
    //서버에서 UTF-8로 넘긴 파일명을 디코딩처리
    fileName =  decodeURI(fileName);
	
    if (window.navigator && window.navigator.msSaveOrOpenBlob) {
	    //IE에서 blob를 다운로드할 때 사용되는 동작
	    window.navigator.msSaveBlob(fileDataBlob, fileName);
    } else {
		// 가상 링크 DOM 만들어서 다운로드 실행
		const url = URL.createObjectURL(fileDataBlob);
		const a = document.createElement("a");
		a.href = url;
		a.download = fileName;
		document.body.appendChild(a);
		a.click();
		window.URL.revokeObjectURL(url);
	}
}