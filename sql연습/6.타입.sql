-- cast
select '12345' + 10 , concat('12345', 10), cast('12345' as int) + 10 from dual;
select date_format(cast('2024-12-10' as date), '%Y년 %m월 %d일');