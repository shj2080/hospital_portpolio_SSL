 //파일다운로드 함수
 async function fileDown(file_code) {
	
	const fileInitData = await fetch("fileDown.fe",{
		method: "POST",
		body: "file_idx="+file_code,
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"}
	}).then(function(response) {return response;});
	-
	const fileDataBlob = await fileInitData.blob();

	//다운로드 되는 파일이름을 가져옴
	const disposition = fileInitData.headers.get("Content-Disposition");

	let fileName = "file";
	
    if(disposition && disposition.indexOf('attachment') !== -1) {
    //파일 이름 정규식
    	const filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
    	const matches = filenameRegex.exec(disposition);
    	if (matches != null && matches[1]) {
    		fileName = matches[1].replace(/['"]/g, '');
    	} 
    }
    //서버에서 UTF-8로 넘긴 파일명을 디코딩
    fileName =  decodeURI(fileName);
	    
	// 가상 링크 DOM 만들어서 다운로드 실행
	const url = URL.createObjectURL(await fileDataBlob);
	const a = document.createElement("a");
	a.href = url;
	a.download = fileName;
	document.body.appendChild(a);
	a.click();
	window.URL.revokeObjectURL(url);
}