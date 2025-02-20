/*
* 개인정보 수정 시 필요한 검증 작업
*/

$(function () {
	$('#submitBtn').on('click', validation);
});

// 2) 개인정소 수정을 검증작업
function validation() {
	let userPwd = $('#userPwd').val();

	// 비밀번호 길이 체크
	if (userPwd.trim().length < 3 || userPwd.trim().length > 5) {
		$('#confirmPwd').css('color', 'red');
		$('#confirmPwd').html('비밀번호는 3~5자 이내');

		return false;
	}

	return true;

}