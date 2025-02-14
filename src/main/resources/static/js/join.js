/*
* 회원가입시 필요한 검증 작업
*/

let idCheck = false;
let pwdCheck = false;	// 비번, 비번확인

$(function () {
	$('#userId').on('keyup', confirmId);
	$('#userPwd').on('focus', function () {
		$('#userPwdCheck').val('');
	});

	$('#submitBtn').on('click', join);
});

// 2) 회원가입을 위한 나머지 검증작업
function join() {
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

	// 이름 입력 체크
	let userName = $('#userName').val();
	if (userName.trim().length < 1) {
		$('#confirmUserName').css('color', 'red');
		$('#confirmUserName').html('실명 입력');

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

// 1) 아이디 검증 (길이 + 중복확인)
function confirmId() {
	let userId = $('#userId').val();

	//길이체크
	if (userId.trim().length < 3 || userId.trim().length > 5) {
		$('#confirmId').css('color', 'red');
		$('#confirmId').html('아이디는 3~5자 이내');

		return;
	}

	$('#confirmId').html('');	//없어도 됨
	// 아이디 중복확인
	$.ajax({
		url: '/user/idCheck'
		, method: 'POST'
		, data: { "userId": userId }
		, success: function (resp) { //resp가 true이면 회원가입 가능
			if (resp) {
				$('#confirmId').css('color', 'blue');
				$('#confirmId').html('가입 가능한 아이디입니다.');
				idCheck = true;
			} else {
				$('#confirmId').css('color', 'red');
				$('#confirmId').html('사용 불가능한 아이디입니다.');
				idCheck = false;
			}
		}
	});

}