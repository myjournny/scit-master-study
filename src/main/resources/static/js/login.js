/*
* 로그인 시 필요한 검증 작업
*/

$(function () {
	$('#submitBtn').on('click', login);
});

// 2) 회원가입을 위한 나머지 검증작업
function login() {
	let userId = $('#userId').val();
	let userPwd = $('#userPwd').val();

	//길이체크
	if (userId.trim().length < 3 || userId.trim().length > 5) {
		$('#confirmId').css('color', 'red');
		$('#confirmId').html('아이디는 3~5자 이내');

		return false;
	}

	// 비밀번호 길이 체크
	if (userPwd.trim().length < 3 || userPwd.trim().length > 5) {
		$('#confirmPwd').css('color', 'red');
		$('#confirmPwd').html('비밀번호는 3~5자 이내');

		return false;
	}

	return true;

}