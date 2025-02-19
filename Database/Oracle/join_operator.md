# Oracle Join 실습

Oracle의 Join 연산자를 활용한 다양한 실습 기록입니다.

## Q1. HR 세션에서 하기 대상인원에 대한 요구사항을 출력하세요.

*대상 직원명(first_name) : Susan, Pat, Adam
*대상 직원들이 현재 어디에서 근무하고 있는지에 대한 리포트
→리포트: 사원번호, 사원명, 직무ID, 연봉, 부서명, 도시, 국가, 대륙

```sql
select
    e.employee_ID as 사원번호,
    e.first_name as 사원명,
    e.JOB_ID as 직무ID,
    to_char(e.salary * 12 * 1300,'L999,999,999') as 연봉,
    d.department_name as 부서명, -- d.location_id, loc.location_id,
    loc.city as 도시,
    co.country_name as 국가,
    re.region_name as 대륙
from employees e, departments d, locations loc, countries co, regions re
where e.department_id = d.department_id
and d.location_id = loc.location_id
and loc.country_id = co.country_id
and co.region_id = re.region_id
and e.first_name in('Susan','Pat','Adam');
```

![oracle join q1 result](/images/oracle_join_q1_result.png)

## Q2. HR세션에서 join을 사용하지 않고 하기 요구사항을 확인하세요. (→outer join 필요성 확인)

1)직원 중 부서 배치가 안 된 사람은 누구인지 확인하시오.

```sql
select * from employees
where department_id is null;
```

![oracle join q2 result1](/images/oracle_join_q2_result.png)

2)부서 중 직원이 한 명도 배치되지 않는 부서명을 확인하시오.

```sql
select department_name from departments 
where department_id in(
select department_id from departments
minus 
select department_id from employees);
```

![oracle join q2 result2](/images/oracle_join_q2_result2.png)

## Q3. outer join을 활용하여 하기 요구사항을 확인시오.

1)직원 리스트와 함꼐 직원 중 부서배치가 안 된 사람은 누구인지 확인하시오.

<표준> LEFT JOIN 
```sql
SELECT *
FROM employees e
left join departments d on e.department_id = d.department_id;
<Oracle> left join
SELECT *
FROM employees e, departments d
where e.department_id = d.department_id(+); //오라클만의 키워드
```

![oracle join q3 result1](/images/oracle_join_q3_result.png)

2)부서 리스트와 함께 부서 중 직원이 한 명도 배치되지 않는 부서명도 함께 확인하시오.

```sql
SELECT *
FROM employees e
full join departments d on e.department_id = d.department_id;
```

![oracle join q3 result2](/images/oracle_join_q3_result2.png)

## Q4. HR 세션에서 현재 우리회사 부서가 진출해 있는 도시 현황 총계를 출력하세요.

```sql
SELECT 
COUNT(DISTINCT CASE WHEN d.department_id IS NOT NULL THEN l.city END) AS "부서가 있는 도시 수(O)",
COUNT(DISTINCT CASE WHEN d.department_id IS NULL THEN l.city END) AS "부서가 없는 도시 수(X)"
FROM locations l
LEFT JOIN departments d ON l.location_id = d.location_id;
```

![oracle join q4 result](/images/oracle_join_q4_result.png)
