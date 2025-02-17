## Q2. hr의 사원정보(employees)에서 시급 기준 Top 5 랭킹 리포트를 구성하세요.
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


## Q3. 15~25위 ranking 조회

```sql
select *
from(select rownum as rank, 사번, 성명, 직무, 연봉, 시급$, "시급(원)", 근무년차, 입사일
from(select
    employee_id as 사번,
    last_name || '. ' || first_name as 성명,
    job_id as 직무,
    to_char(salary * 12,'$999,999') as 연봉,
    to_char(round(salary / 160),'$999,999') as 시급$,
    to_char(round(salary / 160 * 1350),'L999,999')as "시급(원)",
    trunc(months_between(sysdate,hire_date) / 12) || ' years' as 근무년차,
    hire_date as 입사일
from employees
order by 시급$ desc))
where rank between 15 and 25;
