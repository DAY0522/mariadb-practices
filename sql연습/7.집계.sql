--
-- 1) 집계쿼리: select 절에 집계함수(count, max, min, avg, variance, strdev, sum)
--
select avg(salary), sum(salary) from salaries;

-- 2) select 절에 집계함수(그룹함수)가 있는 경우, 어떤 컬럼도 select 절에 올 수 없다.
-- emp_no는 아무 의미가 없다.
-- 오류!
select emp_no, avg(salary)
	from salaries;

-- 3) query 출력
-- 1. from: 접근 테이블 확인 
-- 2. where: 조건에 맞는 row를 확인 선택(임시테이블)
-- 3. 집계(결과테이블)
select avg(salary)
	from salaries
    where emp_no = '10060';
    
-- 4) group by
select avg(salary)
	from salaries
group by emp_no;

-- 5) havaing
-- 집계결과(결과테이블)에서 row를 선택해야 하는 경우 alter
-- 이미 where절은 실행이 되었기 때문에 having절에서 이 조건을 주어야 한다.
-- 예제: 평균월급이 60000 이상인 사원의 사번과 평균 연봉을 출력하세요.
select emp_no, avg(salary)
	from salaries
group by emp_no
	having avg(salary) > 60000
order by avg(salary) asc;

-- 주의) 사번이 10060인 사원의 사번, 평균 월급, 급여, 총합을 출력하세요.

-- 문법적으로 옳지 않음
-- 의미적으로 맞음(where)
select emp_no, avg(salary), sum(salary)
	from salaries
where emp_no = 10060;

-- 문법적으로 옳다.
select emp_no, avg(salary), sum(salary)
	from salaries
group by emp_no
having emp_no = 10060;