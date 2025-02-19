# MySQL 단일행 함수 실습

MySQL의 단일행 함수를 활용한 다양한 실습 기록입니다.

## Q1. city테이블에서 countrycode와 name을 붙이고, 구분자는 ‘:’로 표기

위 결과를 출력할 때 일본의 도시를 한국으로 도시로 변경해서 출력하시오.

```sql
select concat(replace(countrycode,'JPN','KOREA'),':',name) as 'R.O.K' from city
where Countrycode = 'JPN';
```

![mysql single row function q1 result](/images/mysql_singlerow_q1_result.png)

## Q2.
1) 단일행 함수를 활용하여 여행가방(luggage) 비번에 해당하는 랜덤정수 3자리를 만드시오. (단, 매번 실행할 때, 번호는 바뀌도록 구성)

```sql
SELECT LPAD(FLOOR(RAND() * 1000),3,'0') AS random_number;
```

![mysql single row function q2 result](/images/mysql_singlerow_q2_result.png)

2) 한미일(KOR, USA, JPN)에서 인구가 많은 도시 top 10을 출력하시오.

```sql
SELECT 
concat(countrycode,' : ', district,' : ', name) AS `'한미일'에서 인구 top10 도시명`,
concat(format(population,0),'명') as 인구 FROM CITY
where countrycode in('KOR', 'JPN', 'USA')
order by population desc
limit 10;
```

![mysql single row function q2 result2](/images/mysql_singlerow_q2_result2.png)

## Q3. 데이터 구조가 다음과 같을 때, 국가코드와 인구수 데이터를 분리하세요.
![mysql single row function q3 question](/images/mysql_singlerow_q3_question.png)

```sql
Select city_info, left(city_info,3) as 국가코드,
substring(city_info, length(city_info) - instr(reverse(city_info),' ')+2)  AS 인구
from t_city;
```

![mysql single row function q3 result](/images/mysql_singlerow_q3_result.png)

## Q4. 특정 날짜가 주어졌을 때, 해당월 마지막날의 요일을 출력하시오.

```sql
Select
now() as 특정날짜,
date_format(last_day(now()),'%W') as '주어진 날짜기준 해당월 마지막일의 요일'
from dual;
```

![mysql single row function q4 result](/images/mysql_singlerow_q4_result.png)

