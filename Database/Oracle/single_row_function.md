# Oracle 단일행 함수 실습

## 목차
1. [시급 기준 Top 5 랭킹](#q1-시급-기준-top-5-랭킹)
2. [15~25위 랭킹 조회](#q2-15-25위-ranking-조회)
3. [특정 순위 시급 조회](#q3-최저-시급기준으로-ranking-조회5-8위-3위-13위-23위)
4. [입사 요일 통계](#q4-hr의-employees를-활용하여-가장-입사를-많이-한-요일정보를-확인하세요)
5. [부서별 급여 순위](#q5-hremployees를-활용하여-하기-요구사항을-출력하세요)
6. [조건부 급여 인상](#q6-hremployees를-활용하여-하기-요구사항을-출력하세요)

## Q1. 시급 기준 Top 5 랭킹
### 문제
hr의 사원정보(employees)에서 시급 기준 Top 5 랭킹 리포트를 구성하세요.

### 코드
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
```

![oracle_single row function_q1 result](/images/oracle_singlerow_q1_result.png)

## Q2. 15~25위 ranking 조회

### 코드
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
```

![oracle_single row function_q2 result](/images/oracle_singlerow_q2_result.png)


## Q3. 최저 시급기준으로 ranking 조회(5~8위, 3위, 13위, 23위)
### 코드
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
    to_char(hire_date,'yyyy-mm-dd') as 입사일
from employees
order by 시급 desc))
where rank = 3 or rank between 5 and 8 or rank = 13 or rank = 23;
```

![oracle_single row function_q3 result](/images/oracle_singlerow_q3_result.png)

## Q4. hr의 employees를 활용하여 가장 입사를 많이 한 요일정보를 확인하세요.
### 코드
```sql
select
to_char(hire_date,'day') as 입사요일,
count(*) as 빈도
from employees
group by to_char(hire_date,'day')
order by 2 desc;
```

![oracle_single row function_q4 result](/images/oracle_singlerow_q4_result.png)

## Q5. hr.employees를 활용하여 하기 요구사항을 출력하세요.
*salary 기준으로 ranking 출력하되 각 부서별로 순위를 부여하세요.
```sql
select employee_id,first_name,salary,department_id,
rank() over(partition by department_id order by salary desc) as rank_sal,
dense_rank() over(partition by department_id order by salary desc) as dense_rank_sal,
row_number() over(partition by department_id order by salary desc) as rownumber_sal
from employees
order by department_id asc, salary desc;
```

![oracle_single row function_q5 result](/images/oracle_singlerow_q5_result.png)

## Q6. hr.employees를 활용하여 하기 요구사항을 출력하세요.
*요구사항-1: 30번과 50번 부서 소속 직원에서 급여 10% 인상
*요구사항-2: 전 사원 기준 set 출력 or 인상된 직원 set 출력

1) 전사원 기준 set 출력
```sql
select
    first_name,
    department_id,
    salary,
    case 
    when department_id in(30,50) then salary * 1.1
    else salary
    end as increased_pay,
    
    case 
    when department_id in(30,50) then '+10%'
    else '--'
    end as stat
from employees
order by salary desc;
```
![oracle_single row function_q6 result1](/images/oracle_singlerow_q6_result1.png)

2) 인상된 직원 set 출력
```sql
select
    first_name,
    department_id,salary,
    salary*1.1as increased_pay,
    '+10%' as STAT
from employees
where department_id in(30,50)
order by salary desc;
```

![oracle_single row function_q6 result2](/images/oracle_singlerow_q6_result2.png)
