//검색 버튼 누를 경우 실행되는 함수
function memberSearch() {
    let u_id = document.getElementById("u_id");

    if(!u_id.value) {
        alert("검색할 회원 ID를 입력해주세요!");
        u_id.focus();
        return false;
    }

}