//숫자만 허용하는 입력칸
const numberOnly = function(numVal) {
	numVal.value = numVal.value.replace(/[^0-9]/g, '');
}