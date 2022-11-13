//전화번호 자동 하이픈
const autoHyphen = function(phoneNumber) {
	phoneNumber.value = phoneNumber.value.replace(/[^0-9]/g, '')
	.replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
}