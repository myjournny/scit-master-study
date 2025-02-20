# Login (Security)
Spring Security와 jQuery를 활용한 로그인/회원가입 웹 화면 실습 코드입니다.

## 주요 기능
### 1. 회원가입
- ID/Password/이름 입력 및 유효성 검사
- Ajax 기반 ID 중복확인
- Spring Security 기반 비밀번호 암호화

### 2. 로그
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
  
![readingnote result](/images/readingnote_result.jpg)

- **구매도서 등록 화면**  

![readingnote result2](/images/readingnote_result2.jpg)

- **독서 노트 등록 화면**

![readingnote result3](/images/readingnote_result3.jpg)

- **독서 노트 보기 화면**

![readingnote result4](/images/readingnote_result4.jpg)
