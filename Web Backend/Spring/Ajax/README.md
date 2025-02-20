# Reading Note (Ajax)
jQuery와 Ajax를 활용한 개인 독서 노트 관리 웹 화면 실습 코드입니다.

## 주요 기능
### 1. 도서 등
- 제목/저자/출판사/구매일/가격 입력
- jQuery Ajax 비동기 처리
- 실시간 목록 갱신

### 2. 도서 조회/검색
- 도서 조회/검색
- 제목/저자/출판사별 검색
- Ajax 기반 실시간 결과 반영

### 3. 독서 노트
- 읽기 상태 선택
- 독서 시작일 기록
- 독서 감상평 작성

## 코드 구조
### 📌 백엔드 (Spring Boot)
- **BookDTO/ReadingNoteDTO**: 데이터 전송 객체
- **BookService/ReadingNoteService**: 비즈니스 로직 처리
- **BookController**: Ajax 요청 처리 컨트롤러
- **ReadingController**: 독서 노트 관리 컨트롤러
- **MainController**: 메인 페이지 라우팅
- 
### 📌 프론트엔드 (Thymeleaf & jQuery)
- **index.html**: 메인 페이지
- **bookRegist.html**: Ajax 기반 도서 등록/관리 
- **readingView/Write.html**: 독서 노트 화면
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
