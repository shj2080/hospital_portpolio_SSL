window.onload = function() {
	//파일 추가 이벤트 리스너
	document.getElementById("formFileData").addEventListener("change", function(e) {
   		addFile(e.target);
	});
	
}
//아래 함수들에서 사용되는 fileNo, fileArr은 전역변수
//첨부된 파일들을 페이지에 표시하는 함수
function setFileInfo(file) {
	//----------------파일 첨부 시 보여지는 info 구간---------------------///
    // 목록 추가 - 첨부한 파일 이름과 삭제 버튼이 나타남       
    let FormTable = document.getElementById("boardWriteBox");
    
    //첨부파일 리스트를 테이블의 4번째 index에 삽입
    let fileList =  FormTable.insertRow(4);
    
    //셀 추가 - 삭제 버튼
    let fileList_delete_cel = fileList.insertCell();
    //셀 추가 - 첨부파일명
    let fileList_cel = fileList.insertCell();
	
    fileList.id = "file" + fileNo;
    fileList.className = "fileList align-middle";
    
    //취소버튼 생성
    fileList_delete_cel.innerHTML = '<button type="button" class = "btn outline-dark" onclick="deleteFile(' + fileNo + ');"><i class="bi bi-file-earmark-x"></i></button>';
    //파일이름을 textContent 로 대입
    fileList_cel.textContent = file.name;
    
    fileNo++; //파일 번호를 증가
    //----------------파일 첨부 시 보여지는 info 구간---------------------///
}

/* 첨부파일 추가 - 파일 첨부 버튼 클릭 시 */
function addFile(obj){
    let maxFileCnt = 5;   // 첨부파일 최대 개수
    let attFileCnt = document.querySelectorAll('.fileList').length + document.querySelectorAll('.beingFileList').length;    // 기존 추가된 첨부파일 개수
    let remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    let curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수
	
	console.log("addFile 함수 실행됨");
	
    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
    } else {
    ///input 태그에서 추가한 파일 배열 반복문 for ~ of
        for (const file of obj.files) {
            //첨부파일 검증
            if (validation(file)) {
                //파일을 배열에 담기-----//
                let reader = new FileReader();
				reader.onload = function () {
					//파일 배열에 읽은 파일을 추가
				    filesArr.push(file);
				};
				reader.readAsDataURL(file);
				//파일을 배열에 담기-----//
				
				//----파일 첨부 시 보여지는 info 처리함수-----------///
                setFileInfo(file);
                //----파일 첨부 시 보여지는 info 처리함수-----------///
            } else {
                continue;
            }
        }
    }
    // 초기화
    document.querySelector("input[type=file]").value = "";
}

/* 첨부파일 검증 */
function validation(obj){
	const limitFileSize = 10 * 1024 * 1024;
    const fileTypes = ['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'application/haansofthwp', 'application/x-hwp'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > limitFileSize) {
        alert("최대 파일 용량인 10MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
}

/* 첨부파일 삭제 */
function deleteFile(num) {
    let deleteFileRow = document.getElementById("file" + num);
    
    deleteFileRow.parentNode.removeChild(deleteFileRow);
    filesArr[num].is_delete = true;
}


/* 기존 첨부파일 삭제 처리 */
function deleteBeingFile(delBeingElement, file_idx) {

    delBeingElement.closest('tr').remove();
    
    beingDeleteFileArr.push(file_idx);
    console.info(beingDeleteFileArr);
    
    beingFileCnt--;
    //oldFilesArr[num].is_delete = true;
}

//새로운 글 작성인지, 수정인지 체크 (폼 전송 시 체크됨)
function modifyChk() {

	//IE대응 코드
	(event.preventDefault) ? event.preventDefault() : event.returnValue = false; 
	
	//기본 폼액션 (글쓰기)
	let formAction = "userBoardWriteAction.fe";
	
	

	let modifyBtn = document.getElementById("modifyBtn");
	
	let boardForm = document.querySelector("#boardWriteForm");
	let boardFormData = new FormData(boardForm);
	
	//게시글 수정 상태일 때(수정 버튼이 없는 경우)
	if(modifyBtn !== null) {
		formAction = "userBoardModifyAction.fe";
		
		//---기존에 존재한 첨부파일을 삭제한 경우-----//
		//삭제한 파일 index 데이터를 폼에 추가
		for(let i = 0; i < beingDeleteFileArr.length; i++) {
			boardFormData.append("beingDeleteFile_idx", beingDeleteFileArr[i]);
		}
	}
	
	//첨부한 파일들 폼에 추가
	for (var i = 0; i < filesArr.length; i++) {
	    // 삭제되지 않은 파일만 폼데이터에 담기
	    if (!filesArr[i].is_delete) {
	        boardFormData.append("post_file" + i, filesArr[i]);
	    }
	    
	}
	
	//파일 첨부를 위해 사용하던 input 객체 제외 처리(파일을 첨부하지 않아도 있는 것으로 판별되는 문제 수정)
	boardFormData.delete("fileData");
	
	/* key 확인하기 */
	/*
	for (let key of boardFormData.keys()) {
		   console.log(key);
	}
	*/
	/* value 확인하기 */
	/*
	for (let value of boardFormData.values()) {
	      console.log(value);
	}
	*/
	
	//const boardFormDataQueryString = new URLSearchParams(boardFormData).toString();
	
	//fetch() 함수로 서버와 연결
	fetch(formAction,{
		method: "POST",
		body: boardFormData,
		cache: "no-cache",
		headers: {}
	})
	.then(function(response){
		//console.log(response);
		return response.json();
		
		})
	.then(function(data) {
		//console.log(data);
		
		//게시글 작성 성공
		if(data.type == "OK") {
			alert(data.message);
			location.replace("showPost.do?post_no="+data.result.post_no);
		}
		//로그인 안 됨
		else if (data.type == "login") {
			alert(data.message);
			location.replace("login.do");
		}
		//기타 문제
		else {
			alert(data.message);
			location.replace("userBoard.do");
		}
		
	})
	.catch(function(err) {
		console.warn("Fetch Error:" + err);
	});
	
	return false;
};