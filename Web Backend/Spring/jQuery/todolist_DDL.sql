-- TODOLIST
-- 2024년 12월 10일

-- 테이블
USE SCIT;
DROP TABLE IF EXISTS scit.todolist;

CREATE TABLE scit.todolist
(
    seqno       INT        AUTO_INCREMENT PRIMARY KEY,
    regdate     DATETIME DEFAULT  CURRENT_TIMESTAMP,
    status      VARCHAR(10)   CHECK (status IN ('완료', '진행', '지연')),
    importance  VARCHAR(10)   CHECK (importance IN ('높음', '보통', '낮음')),
    categories  VARCHAR(6)    DEFAULT '개인'CHECK( categories IN ('개인', '회사')),
    todo        VARCHAR(3000) DEFAULT '할일 없음'
);

 

