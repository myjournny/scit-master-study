function formCheck() {
    /* 아이디 체크 */
    let userid = document.querySelector('#userid');
    let name = document.querySelector('#name');
    let userpw = document.querySelector('#userpw');
    let checkpw = document.querySelector('#checkpw');
    let emaillocal = document.querySelector('#emaillocal');
    let emaildomain = document.querySelector('#emaildomain');
    let phonenumberstart = document.querySelector('#phonenumberstart');
    let phonenumbermid = document.querySelector('#phonenumbermid');
    let phonenumberend = document.querySelector('#phonenumberend');
    let carrier = document.getElementsByName('carrier');


    if (userid.value.length < 3 || userid.value.length > 12) {
        alert('아이디(ID)는 3~12자리로 입력해 주세요.');
        return false;
    }

    /* 이름 체크 */
    if (name.value.length < 2 || name.value.length > 4) {
        alert('이름은 2~4자리로 입력해 주세요');
        return false;
    }

    /* 패스워드 체크 */
    if (userpw.value.length < 5 || userpw.value.length > 8 || checkpw.value.length < 5 || checkpw.value.length > 8) {
        alert('비밀번호는 5~8자리로 입력해 주세요');
        return false;
    }

    if (userpw.value != checkpw.value) {
        alert('비밀번호가 일치하지 않습니다.');
        return false;
    }

    /* 필수항목 체크(이메일) */

    if (emaillocal.value == null || emaillocal.value == '' || emaildomain.value == null || emaildomain.value == '') {
        alert('이메일을 입력해 주세요.');
        return false;
    }

    /* 필수항목 체크(휴대폰) */
    if (phonenumbermid.value == null || phonenumbermid.value == '' || phonenumberend.value == null || phonenumberend.value == ''
        || phonenumberstart.value == '') {
        alert('휴대폰 번호를 입력해 주세요.');
        return false;
    }

    let chk = false;
    for (let i = 0; i < carrier.length; i++) {
        if (carrier[i].checked == true) {
            chk = true;
        }
    }
    if (chk == false) {
        alert("통신사를 선택해 주세요.");
        return false;
    }
    return true;
}

function setDomain() {
    /* 이메일 선택박스에서 선택시 입력태그에 값 넣기 */
    let sel = document.querySelector('#selectDomain');
    let domain = document.querySelector('#emaildomain');

    domain.value = sel.value;

}

