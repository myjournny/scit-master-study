# MySQL Join 실습

MySQL의 Join 연산자를 활용한 다양한 실습 기록입니다.

## 1. Join 연산자를 활용하여 하기내용과 같이 출력하세요.
  (국가코드, 국가명, 지역명. 도시명, 인구)

```sql
select
ct.countrycode as 국가코드,
ctry.name as 국가명,
ct.district as 지역명,
ct.name as 도시명,
ct.population as 인구
from city ct, country ctry
where ct.countrycode = ctry.code;
```

![mysql join q1 result](/images/mysql_join_q1_result.png)

## 2. Join 연산자를 활용하여 하기내용과 같이 출력하세요.
  (국가명, 사용언어, 공식언어유무, 언어사용비율)

```sql
select
ctr.name as 국가명,
ctrl.language as 사용언어,
ctrl.isofficial as 공식언어유무,
ctrl.percentage as 사용비율
from countrylanguage ctrl, country ctr
where ctrl.countrycode = ctr.code;
```

![mysql join q2 result](/images/mysql_join_q2_result.png)

## Q3. 1. 하기 요구사항에 맞는 SQL을 개발하시오.
    -각 국가별 도시 수 현황
    -도시 수가 60~150개 되는 국가 현황

```sql
select ctr.name as 국가명,
       count(ct.name) as 도시수
from country ctr, city ct
where ct.countrycode = ctr.code
group by ctr.name
having count(ct.name) between 60 and 150
order by count(ct.name) desc;
```

![mysql join q3 result](/images/mysql_join_q3_result.png)

## Q4. 3번 출력결과의 총 합계를 추가적으로 출력하시오.

```sql
SELECT 국가명, SUM(도시갯수) AS 도시수
FROM (select ctr.name as 국가명,
       count(ct.name) as 도시갯수
from country ctr, city ct
where ct.countrycode = ctr.code
group by ctr.name
having count(ct.name) between 60 and 150
) AS SUB
GROUP BY 국가명 WITH ROLLUP
ORDER BY 도시수 DESC;
```

![mysql join q4 result](/images/mysql_join_q4_result.png)
