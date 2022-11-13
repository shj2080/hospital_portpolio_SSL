let fileNo = 0;
let filesArr = new Array();

/* 첨부파일 추가 */
function addFile(obj){
    let maxFileCnt = 5;   // 첨부파일 최대 개수
    let attFileCnt = document.querySelectorAll('.fileList').length;    // 기존 추가된 첨부파일 개수
    let remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    let curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수

    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
    } else {
        for (const file of obj.files) {
            // 첨부파일 검증
            if (validation(file)) {
                // 파일 배열에 담기
                let reader = new FileReader();
                reader.onload = function () {
                    filesArr.push(file);
                };
                reader.readAsDataURL(file);

                // 목록 추가                
                let FormTable = document.getElementById("boardWriteBox");
                let fileList =  FormTable.insertRow(4);
                let fileList_delete_cel = fileList.insertCell();
                let fileList_cel = fileList.insertCell();

                fileList.id = "file" + fileNo;
                fileList.className = "fileList align-middle";
                
                fileList_delete_cel.innerHTML = '<button type="button" class = "btn outline-dark" onclick="deleteFile(' + fileNo + ');"><i class="bi bi-file-earmark-x"></i></button>';
                fileList_cel.innerHTML = file.name;
                
                fileNo++;
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