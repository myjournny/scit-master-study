# Todo List (jQuery)
jQuery와 Spring Boot를 활용한 할 일 관리 웹 화면 실습 코드입니다.

## 기능 설명
- Todo 항목 등록/조회/삭제 기능 구현
- jQuery를 활용한 동적 이벤트 처리
- Spring Boot & JPA 기반 백엔드 연동

## 주요 기능
### 1. Todo 등록
- 작성일/상태/중요도/분류/내용 입력
- jQuery form validation
- 입력값 유효성 검사

### 2. Todo 조회
- 전체 목록 조회
- 중요도/분류별 필터링
- 날짜순 정렬 표시

### 3. Todo 삭제
- 개별 항목 삭제 기능
- jQuery 이벤트 처리

## 코드 구조
### Entity & DTO
- TodoEntity: JPA 엔티티 클래스
- TodoDTO: 데이터 전송 객체

### Controller
- MainController: 메인 페이지 라우팅
- TodoController: Todo CRUD 처리

### Repository & Service
- TodoRepository: JPA 리포지토리 인터페이스
- TodoService: 비즈니스 로직 처리

## 실행 화면
- 첫 화면

![todolist result](/images/todolist_result.png)

- To do list

![todolist result2](/images/todolist_result2.png)


