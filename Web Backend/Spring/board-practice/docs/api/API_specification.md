# API 명세서 (2024-12-26)

## 1. 첫화면
| 기능 | 인증범위 | 요청 URL | Method | Controller |
|------|----------|-----------|---------|------------|
| 첫화면 | permitAll | / | GET | MainController |

## 2. 회원
| 기능 | 인증범위 | 요청 URL | Method | Controller |
|------|----------|-----------|---------|------------|
| 회원가입 화면 | permitAll | /user/join | GET | UserController |
| 아이디 중복확인 | permitAll | /user/idCheck | POST | UserController |
| 회원가입 처리 | permitAll | /user/joinProc | POST | UserController |
| 로그인 화면 | permitAll | /user/login | GET | UserController |
| 로그인 처리 | permitAll | /user/loginProc | POST | SecurityConfig |
| 로그아웃 | ROLE_USER, ROLE_ADMIN | /user/logout | GET | SecurityConfig |
| 개인정보 수정 | ROLE_USER, ROLE_ADMIN | 보류 | GET | UserController |

## 3. 게시판
| 기능 | 인증범위 | 요청 URL | Method | Controller |
|------|----------|-----------|---------|------------|
| 게시글 목록조회 | permitAll | /board/boardList | GET | BoardController |
| 게시글 쓰기화면 | ROLE_USER, ROLE_ADMIN | /board/boardWrite | GET | BoardController |
| 게시글 쓰기처리 | ROLE_USER, ROLE_ADMIN | /board/boardWrite | POST | BoardController |
| 첨부파일 다운로드 | ROLE_USER, ROLE_ADMIN | /board/download | GET | BoardController |
| 게시글 상세조회 | permitAll | /board/boardDetail | GET | BoardController |
| 게시글 수정화면 | ROLE_USER, ROLE_ADMIN | /board/boardUpdate | GET | BoardController |
| 게시글 수정처리 | ROLE_USER, ROLE_ADMIN | /board/boardUpdate | POST | BoardController |
| 게시글 삭제 | ROLE_USER, ROLE_ADMIN | /board/boardDelete | GET | BoardController |

## 4. 댓글 (Ajax)
| 기능 | 인증범위 | 요청 URL | Method | 처리방식 |
|------|----------|-----------|---------|----------|
| 댓글 목록 | permitAll | /reply/replyList | GET | Ajax |
| 댓글 등록 | ROLE_USER, ROLE_ADMIN | /reply/replyWrite | POST | Ajax |
| 댓글 삭제 | ROLE_USER, ROLE_ADMIN | /reply/replyDelete | GET | Ajax |
| 댓글 수정 | ROLE_USER, ROLE_ADMIN | /reply/replyUpdate | POST | Ajax |

## 5. 관리자
| 기능 | 인증범위 | 요청 URL | Method | Controller |
|------|----------|-----------|---------|------------|
| 관리자 페이지 | ROLE_ADMIN | /admin | GET | AdminController |
