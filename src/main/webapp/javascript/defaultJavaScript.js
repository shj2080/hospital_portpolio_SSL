//중복 submit 방지
let doubleSubmitFlag = false;

function doubleSubmitCheck() {
	if(doubleSubmitFlag) {
		return doubleSubmitFlag;
	}else {
		doubleSubmitFlag = true;
		return false;
	}
}