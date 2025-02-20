# Board Practice
Spring Security와 JPA를 활용한 회원제 게시판 프로젝트입니다.

## 주요 기능
### 1. 회원 관리
- 회원가입 및 로그인 (Spring Security)
- ID 중복확인 (Ajax)
- 권한별 기능 접근 제어 (ROLE_USER, ROLE_ADMIN)

### 2. 게시판
- 게시글 CRUD
- 페이징 및 검색
- 첨부파일 업로드/다운로드
- 조회수 기능

### 3. 댓글
- Ajax 기반 댓글 목록/등록/수정/삭제
- 게시글별 댓글 관리
- 실시간 댓글 업데이트

## 코드 구조
### 📌 백엔드 (Spring Boot)
- **Config**: Security 설정 및 권한 관리
- **Controller**: Admin, Board, User, Main, Reply 요청 처리
- **DTO**: Board, LoginUserDetails, Reply, User DTO
- **Entity**: Board, User, Reply Entity 클래스
- **Handler**: Custom Login/Logout 핸들러 클래스
- **Repository**: Board, User, Reply Repository
- **Service**: Board, LoginUserDetails, Reply, User Service
- **Util**: FileService, PageNavigation
  
### 📌 프론트엔드 (Thymeleaf & jQuery)
- **CSS**: detail, list, main, user, write 스타일
- **JavaScript**: join, login, paging, pwdCheck, reply, update
- **Templates**:
  - 메인: index, header
  - 게시판: boardlist, boarddetail, boardwrite, boardupdate
  - 회원: join, login, mypage, myinfoupdate
  - 관리자: adminpage

## 개발 환경
- Java 17
- Spring Boot 3.2.1
- Spring Security 6.2.1
- MySQL 8.0
- Thymeleaf
- jQuery 3.7.1

## 실행 영상
