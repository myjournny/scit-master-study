# Login (Security)
Spring Security와 jQuery를 활용한 로그인/회원가입 웹 화면 실습 코드입니다.

## 주요 기능
### 1. 회원가입
- ID/Password/이름 입력 및 유효성 검사
- Ajax 기반 ID 중복확인
- Spring Security 기반 비밀번호 암호화

### 2. 로그인
- Spring Security 커스텀 로그인
- ID/Password 유효성 검사
- 로그인 실패 시 에러 메시지 표시

### 3. 권한관리
- ROLE_USER/ROLE_ADMIN 권한 분리
- 인증된 사용자 접근 제어
- 로그인/로그아웃 상태에 따른 메뉴 변경

## 코드 구조
### 📌 백엔드 (Spring Boot)
- **UserEntity/UserDTO**: 회원 정보 객체
- **LoginUserDetails**: Spring Security 사용자 정보
- **SecurityConfig**: 설정 및 권한 관리
- **UserRepository**: JPA 리포지토리 인터페이스
- **UserService**: 회원 관리 서비스
- **LoginUserDetailsService**: Spring Security 사용자 인증 서비스
- **UserController**: 회원 관련 요청 처리
- **MainController**: 메인 페이지 라우팅
  
### 📌 프론트엔드 (Thymeleaf & jQuery)
- **index.html**: 메인 페이지
- **join.html**: Ajax 기반 회원가입 처 
- **login.html**: Security 커스텀 로그인
- **jQuery**: Ajax 통신 및 이벤트 처리

## 실행 화면
- **메인 화면**

![login result](/images/login_result.png)

- **회원가입 화면**
 
![login result2](/images/login_result2.png)

***아이디가 3~5자 이내가 아닐 경우 빨강색 글씨 표시**

![login result3](/images/login_result3.png)

***중복확인 클릭 시 아이디가 중복인 경우 화면**

![login result4](/images/login_result4.png)

***중복확인 클릭 시 중복 아닌 경우 화면**

![login result5](/images/login_result5.png)

***중복확인을 안 하고 회원가입 버튼을 클릭한 경우 알림창**

![login result6](/images/login_result6.png)

***비밀번호가 3~5자가 아닌 상태에서 회원가입 버튼 클릭할 경우 알림창**

![login result7](/images/login_result7.png)

***이름을 입력하지 않은 경우 알림창**

![login result8](/images/login_result8.png)

- **로그인 화면**

![login result9](/images/login_result9.png)

***아이디 3~5자가 아닌 경우**

![login result10](/images/login_result10.png)

***비밀번호 3~5자가 아닌 경우**

![login result11](/images/login_result11.png)

***아이디/비밀번호가 틀린 경우**

![login result12](/images/login_result12.png)

- **로그인 성공 후 화면**

![login result13](/images/login_result13.png)
