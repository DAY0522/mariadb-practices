--
-- subquery
--

select (select 1+1 from dual) from dual;
-- insert into t1 values(null, (select(max(no) + 1 from t1))

-- 2) from 절의 서브쿼리 (임시테이블)
select now() as n, sysdate() as s, 3+1 as r from dual;
select a.n, a.r
	from (select now() as n, sysdate() as s, 3 + 1 as r from dual) a;
    
--
-- 3) where 절의 서브쿼리
--

-- 예) 현재 Fai Bale이 근무하는 부서에서 근무하는 직원의 사번, 전체 이름을 출력
select b.dept_no
	from employees a, dept_emp b
where a.emp_no = b.emp_no
	and b.to_date = '9999-01-01'
    and concat(a.first_name, ' ', a.last_name) = 'Fai Bale';
    
-- d004
select *
	from employees a, dept_emp b
    where a.emp_no = b.emp_no
		and b.to_date = '9999-01-01'
        and b.dept_no = 'd004';
        
select *
	from employees a, dept_emp b
    where a.emp_no = b.emp_no
		and b.to_date = '9999-01-01'
        and b.dept_no = (select b.dept_no
							from employees a, dept_emp b
						where a.emp_no = b.emp_no
							and b.to_date = '9999-01-01'
							and concat(a.first_name, ' ', a.last_name) = 'Fai Bale');

-- 3-1) 단일행 연산자: =, >, <, >=, <=, <>, !=
-- 실습문제1
-- 현재 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름과 급여를 출력하세요.

select concat(first_name, ' ', last_name) '이름', salary '급여' 
	from employees e join salaries s on e.emp_no = s.emp_no
where s.to_date = '9999-01-01'
	and s.salary < (select avg(salary)
						from salaries
					where to_date = '9999-01-01')
order by salary desc;

-- 실습문제2
-- 현재 직책별 평균급여 중에 가장 적은 평균급여의 직책 이름과 그 평균급여를 출력하세요.
select title, avg(salary) as avg_s
	from salaries s join titles t on s.emp_no = t.emp_no
where s.to_date = '9999-01-01'
	and t.to_date = '9999-01-01'
group by t.title
order by avg_s
limit 1;

select title '직책', min(avg_s) '평균급여'
	from (select title, avg(salary) as avg_s
			from salaries s join titles t on s.emp_no = t.emp_no
		  where s.to_date = '9999-01-01'
			and t.to_date = '9999-01-01'
		  group by t.title) as title_avg;

-- 3-1) 복수행 연산자: in, not in, 비교연산자 any
-- 1. =any: in
-- 2. >any, >=any: 최솟값
-- 3. <=any, <any: 최댓값
-- 4. <>any, !=any: not in

-- all 사용법
-- 1. =all: (x)
-- 2. >all, >=all: 최댓값
-- 3. <all, <=all: 최소값
-- 4. <>all, !=all

-- 실습문제3
-- 현재 급여가 50000 이상인 직원의 이름과 급여를 출력하세요.
-- 둘리 60000
-- 마이콜 55000

-- sol01
select concat(first_name, ' ', last_name) '이름', salary '급여'
	from employees e join salaries s on e.emp_no = s.emp_no
where s.to_date = '9999-01-01'
	and s.salary >= 50000
order by s.salary asc;
    
-- sol02
select concat(first_name, ' ', last_name) '이름', salary '급여'
	from employees e join salaries s on e.emp_no = s.emp_no
where s.to_date = '9999-01-01'
	and (e.emp_no, s.salary) in (select emp_no, salary
									from salaries
								 where salary >= 50000)
order by s.salary asc; 

-- 실습문제4:
-- 현재 각 부서별 최고급여를 받는 직원의 이름, 부서이름, 급여를 출력해주세요.
select first_name, last_name, d.dept_name, max(salary)
	from employees e join dept_emp de on e.emp_no = de.emp_no
					join departments d on de.dept_no = d.dept_no
                    join salaries s on e.emp_no = s.emp_no
group by d.dept_name;

select a.dept_no, max(b.salary)
	from dept_emp a, salaries b
	where a.emp_no = b.emp_no
		and a.to_date = '9999-01-01'
        and b.to_date = '9999-01-01'
group by a.dept_no;

select c.dept_name, a.first_name, d.salary
	from employees a,
    dept_emp b,
    departments c,
    salaries d
where a.emp_no = b.emp_no
	and b.dept_no = c.dept_no
    and a.emp_no = d.emp_no
    and b.to_date = '9999-01-01'
    and d.to_date = '9999-01-01'
    and (b.dept_no, d.salary) in (select a.dept_no, max(b.salary)
									from dept_emp a, salaries b
									where a.emp_no = b.emp_no
										and a.to_date = '9999-01-01'
										and b.to_date = '9999-01-01'
								  group by a.dept_no);

select a.dept_name, c.first_name, d.salary 
	from departments a, dept_emp b, employees c, salaries d, 
		 (select a.dept_no, max(b.salary) as max_salary
			from dept_emp a, salaries b 
		 where a.emp_no = b.emp_no and a.to_date = '9999-01-01-' and b.to_date = '9999-01-01' 
		 group by a.dept_no) e
		 where a.dept_no = b.dept_no 
			and b.emp_no = c.emp_no 
			and c.emp_no = d.emp_no 
			and b.dept_no = e.dept_no 
			and b.to_date = '9999-01-01'
            and d.to_date = '9999-01-01' 
			and d.salary = e.max_salary;