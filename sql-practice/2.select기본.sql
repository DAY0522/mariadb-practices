--
-- select 기본
--

-- 예제1. departments 테이블에서 모든 컬럼 출력
select * from departments;

-- projection
-- 예제2. employees 테이블에서 직원의 전체이름, 성별, 입사일 출력
select first_name, gender, hire_date
	from employees;
    
-- as(alias)
-- 예제3. employees 테이블에서 직원의 이름,  성별, 입사일을 출력
select first_name as '이름',
	gender as '성별',
    hire_date as '입사일'
from employees;

-- 예제4. employees 테이블에서 직원의 전체이름, 성별, 입사일을 출력
select concat(first_name, ' ', last_name) as '이름',
	gender as '성별',
    hire_date as '입사일'
from employees;

-- distinct
-- 예제:  titles 테이블에서 모든 직급의 이름 출력
select title
from titles;

-- 예제: titles 테이블에서 직급은 어떤 것들이 있는지 직급이름을 한 번씩만 출력
select distinct(title)
from titles;

-- 비교연산자
-- 예제5. employees 테이블에서 1991년 이전에 입사한 직원의 이름, 성별, 입사일 출력
select first_name,
	gender,
    hire_date
from employees
where hire_date < '1991-01-01';

-- 논리연산자
-- 예제: employees 테이블에서 1989년 이전에 입사한 여직원의 이름, 입사일을 출력
select first_name,
	gender,
    hire_date
from employees
where hire_date < '1991-01-01'
	and gender = 'f';

-- in 연산자
-- 예제7: dept_emp 테이블에서 부서 번호가 d005나 d009에 속한 사원의 사번, 부서번호 출력
select emp_no, dept_no
	from dept_emp
where dept_no = 'd005'
	or dept_no = 'd009';

select emp_no, dept_no
	from dept_emp
where dept_no in ('d005', 'd009');

-- like 검색
-- 예제8: employees 테이블에서 1989년에 입사한 직원의 이름, 입사일 출력
select first_name, hire_date
	from employees
where '1989-01-01' <= hire_date
	and hire_date <= '1989-12-31';
    
select first_name, hire_date
	from employees
where hire_date between '1989-01-01' and '1989-12-31';

select first_name, hire_date
	from employees
where hire_date like '1989-%';

--
-- order by
--

-- 예제 9: employees 테이블에서 직원의 이름, 성별, 입사일을 입사일 순으로 출력
select first_name, gender, hire_date
	from employees
order by hire_date;

-- 예제 10: salaries 테이블에서 2001년 월급을 가장 높은순으로 사번, 월급순으로 출력
select emp_no, salary
from salaries
where from_date like '2001%'
	and to_date like '2001%'
order by salary desc;

-- 예제11: 직원의 사번과 월급을 사번(asc), 월급(desc) 순으로 출력
select emp_no, salary
from salaries
where from_date like '2001%'
	and to_date like '2001%'
order by emp_no, salary desc;

