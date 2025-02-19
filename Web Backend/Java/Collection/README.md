# 유저 관리 시스템 (Collection)
Java의 Collection Framework를 활용한 유저 관리 시스템 실습 코드입니다.

## 프로그램 설명
- `User`, `User_Method`, `User_Main` 클래스로 기능 분리
- ArrayList를 활용한 유저 정보 관리
- Collections.sort를 활용한 이름순 정렬
- 캡슐화를 통한 데이터 은닉 (private 필드 사용)

## 주요 기능
### 1. 유저 정보 등록
- ID, 비밀번호, 이름, 연락처, 주소 입력
- ID 중복 검사 구현
- ArrayList에 유저 정보 저장

### 2. 유저 정보 조회
- ID/비밀번호 인증 후 정보 조회
- 일치하는 유저 정보 출력
- 미등록/불일치 시 오류 메시지 출력

### 3. 전체 정보 조회
- Comparator 인터페이스 구현
- 이름 기준 오름차순 정렬
- 전체 유저 목록 출력

## 코드 구조
### User 클래스
- 유저 정보 데이터 모델
- private 필드와 getter/setter 구현
- toString() 메서드 오버라이드

### User_Method 클래스
- ArrayList로 유저 목록 관리
- 유저 등록/조회 기능 구현
- NameComparator 내부 클래스 구현

### User_Main 클래스
- 메인 실행 클래스
- 메뉴 기반 사용자 인터페이스

## 실행 화면
![User](/images/User.gif)
