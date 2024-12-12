--
-- inner join
--

-- 예제1) 현재 근무하고 있는 직원의 이름과 직책을 모두 출력 하세요.
select a.first_name, a.last_name, title
	from employees a, titles b
where a.emp_no = b.emp_no			-- join 조건 (n-1)
	and to_date = '9999-01-01';     -- row 선택 조건

select e.first_name, e.last_name, title
	from employees e join titles t
		on e.emp_no = t.emp_no
where to_date like '9999%';

-- 예제2) 현재 근무하고 있는 직원의 이름과 직책을 모두 출력하되 여성 엔지니어만.
select a.first_name, a.last_name, title
	from employees a, titles b
where a.emp_no = b.emp_no			-- join 조건 (n-1)
	and to_date = '9999-01-01'     -- row 선택 조건 1
    and a.gender ='f'			   -- row 선택 조건 2
    and b.title = 'Engineer';      -- row 선택 조건 3
    
--
-- ANSI / ISO SQL1999 JOIN 표준 문법
--

-- 1) natural join
-- 		조인 대상이 되는 두 테이블에 이름이 같은 공통 컬럼이 있는 경우
-- 		조인 조건을 명시하지 않고 암묵적으로 조인이 된다.
select a.first_name, a.last_name, title
	from employees a natural join titles b
	where to_date = '9999-01-01';     -- row 선택 조건

-- 2) join ~ using
-- natural join의 문제점
select count(*)
	from salaries a join titles b using(emp_no)
where a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01';

-- 3) join ~ on
select count(*)
	from salaries a join titles b on a.emp_no = b.emp_no
where a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01';
    
-- 실습문제1. 현재 직책별 평균 연봉을 큰 순서대로 출력하세요.
select title '직책', avg(salary) '평균연봉'
	from titles t join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by title
order by 2 desc;

-- 실습문제2
-- 현재 직책별 평균 연봉과 직원수를 구하되 직원수가 100명 이상인 직책만 출력
-- projection: 직책, 평균연봉, 직원수
select title '직책', avg(salary) '평균 연봉', count(*) '직원수'
	from titles t join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by title
	having count(*) >= 100
order by 3 desc;

-- 실습문제3
-- 현재 부서별로 직책이 Engineer인 직원들에 대한 평균연봉을 구하세요.
-- projection: 부서이름, 평균연봉
select dept_name '부서이름', avg(salary) '평균연봉'
	from employees e join dept_emp de on e.emp_no = de.emp_no
					 join salaries s on e.emp_no = s.emp_no
                     join departments d on de.dept_no = d.dept_no
group by dept_name
order by 2 desc;