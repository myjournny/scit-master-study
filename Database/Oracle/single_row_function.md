Q2. hr의 사원정보(employees)에서 시급 기준 Top 5 랭킹 리포트를 구성하세요.
```sql
select rownum as rank, id, fullname, job, yy$, hr$, num_yy
from (select
        employee_id as ID,
        LAST_NAME || ', ' || first_name as FULLNAME,
        JOB_ID as JOB, 
        to_char(round(salary / 160), '$999,999') as yy$,
        to_char(round(salary), '$999,999') as mm$,
        to_char(round(salary / 20), '$999,999') as day$,
        to_char(round(salary / 20 / 8), '$999,999') as HR$,
     
trunc(months_between(sysdate,hire_date) / 12) || 'years' as num_yy
from employees
order by hr$ desc)
where rownum <= 5;
Q3. 15~25위 ranking 조회
