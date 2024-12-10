--
-- join
--

-- 예제: 이름이 'parto hitomi'인 직원의 현재 직책을 구하세요.
select emp_no
	from employees
where concat(first_name, ' ', last_name) = 'parto hitomi';

-- 11052
select title
	from titles
where emp_no = 11052;

-- from employees, titles 와 같이 join 할 경우
-- cartesian product가 발생
select a.emp_no, a.first_name, a.last_name, b.title
	from employees a, titles b
where a.emp_no = b.emp_no -- join condition (equi join) 
	and concat(first_name, ' ', last_name) = 'parto hitomi'; -- row select
