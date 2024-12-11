-- 테이블 조인(JOIN) SQL 문제입니다.

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select s.emp_no,
	   concat(e.first_name, ' ', e.last_name) as 'name',
       s.salary
	from salaries s join employees e
    on s.emp_no = e.emp_no
where to_date like '9999%'
order by salary desc;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select e.emp_no '사번', concat(e.first_name, ' ', e.last_name) as '이름', title as '직책' 
	from employees e join titles t
    on e.emp_no = t.emp_no
order by 2 asc;

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요.
select e.emp_no '사번', concat(e.first_name, ' ', e.last_name) '이름', dept_name '부서'
	from dept_emp de join employees e on de.emp_no = e.emp_no
				     join departments d on de.dept_no = d.dept_no
order by 2 asc;

-- 문제4.
-- 현재 근무중인 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select e.emp_no '사번', concat(first_name, ' ', last_name) '이름', salary, title, dept_name
	from employees e join titles t on e.emp_no = t.emp_no
					 join salaries s on e.emp_no = s.emp_no
                     join dept_emp de on e.emp_no = de.emp_no
                     join departments d on de.dept_no = d.dept_no
	where s.to_date = '9999-01-01'
		and t.to_date = '9999-01-01'
        and de.to_date = '9999-01-01'
order by 2 asc;
				

-- 문제5.
-- 'Technique Leader'의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요.
-- (현재 'Technique Leader'의 직책으로 근무하는 사원은 고려하지 않습니다.)
select distinct e.emp_no '사번', concat(first_name, ' ', last_name) '이름'
	from employees e
		join titles t on e.emp_no = t.emp_no
where title = 'Technique Leader'
	and to_date not like '9999%';

-- 문제6.
-- 직원 이름(last_name) 중에서 S로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select first_name, last_name, dept_name, title
	from employees e
		join dept_emp de on e.emp_no = de.emp_no
        join departments d on de.dept_no = d.dept_no
        join titles t on e.emp_no = t.emp_no
where e.first_name like 'S%';

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40,000 이상인 사원들의 사번, 이름, 급여 그리고 타이틀을 급여가 큰 순서대로 출력하세요.
select e.emp_no, first_name, last_name, salary, title
	from employees e
		join titles t on e.emp_no = t.emp_no
        join salaries s on e.emp_no = s.emp_no
where title = 'Engineer'
	and salary >= 40000
    and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
order by salary desc;

-- 문제8.
-- 현재, 평균급여가 50,000이 넘는 직책을 직책과 평균급여을 평균급여가 큰 순서대로 출력하세요.
select title '직책', avg(salary) '평균급여'
	from titles t
		join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by title
having avg(salary) > 50000
order by avg(salary) desc;

-- 문제9.
-- 현재, 부서별 평균급여을 평균급여가 큰 순서대로 부서명과 평균연봉을 출력 하세요.
select dept_name, avg(salary)
	from employees e 
		join dept_emp de on e.emp_no = de.emp_no
        join departments d on de.dept_no = d.dept_no
        join salaries s on s.emp_no = e.emp_no
where s.to_date = '9999-01-01'
	and de.to_date = '9999-01-01'
group by dept_name
order by avg(salary) desc;

-- 문제10.
-- 현재, 직책별 평균급여를 평균급여가 큰 직책 순서대로 직책명과 그 평균연봉을 출력 하세요.
select title, avg(salary)
	from salaries s
		join titles t on s.emp_no = t.emp_no
where t.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by title
order by avg(salary) desc