# 극장 예제 (Array)
Java의 2차원 배열을 활용한 극장 좌석 예약 프로그램 실습 코드입니다.

## 프로그램 설명
- `Theater`, `Theater_Method` 두 클래스로 기능 분리
- 2차원 배열을 활용한 좌석 관리 (7x7 좌석)
- 메인 메뉴 구성: 좌석 확인, 좌석 예약, 종료
- 캡슐화를 통한 데이터 은닉 (private 배열 사용)

## 주요 기능
### 1. 좌석 현황 확인
- 좌석 배치도 출력 
- 예약 상태 표시 (0: 빈좌석, 1: 예약됨)

### 2. 좌석 예약 처리
- 예약할 좌석 수 입력
- 행/열 번호로 좌석 지정
- 중복 예약 검증
 - 예약 가능: "예약되었습니다" 출력
 - 예약 불가: "x행x열은 예약된 좌석입니다" 출력

## 코드 구조
### Theater 클래스
- 메인 실행 클래스
- 메뉴 선택에 따른 기능 실행

### Theater_Method 클래스
- 좌석 배열 관리 (private int[][] seats)
- 좌석 예약 및 조회 기능 구현 

## 실행 결과
![Theater](/images/Theater.gif)
