/*
* 회원정보 수정 시 필요한 검증 작업
*/

let pwdCheck = false;	// 비번, 비번확인

$(function () {
	$('#userPwd').on('focus', function () {
		$('#userPwdCheck').val('');
	});

	$('#submitBtn').on('click', update);
});

// 2) 회원정보 수정을 위한 검증작업
function update() {
	let userPwd = $('#userPwd').val();

	// 비밀번호 길이 체크
	if (userPwd.trim().length < 3 || userPwd.trim().length > 5) {
		$('#confirmPwd').css('color', 'red');
		$('#confirmPwd').html('비밀번호는 3~5자 이내');
		pwdCheck = false;
		return false;
	}

	let userPwdCheck = $('#userPwdCheck').val();
	// 비밀번호와 체크값을 확인
	if (userPwd.trim() != userPwdCheck.trim()) {
		$('#confirmPwd').css('color', 'red');
		$('#confirmPwd').html('비밀번호와 확인값을 같은 값으로');
		pwdCheck = false;
		return false;
	}

	// 이메일 입력 체크
	let email = $('#email').val();
	if (email.trim().length < 1) {
		$('#confirmEmail').css('color', 'red');
		$('#confirmEmail').html('이메일 입력');

		return false;
	}
	
	return true;

}
