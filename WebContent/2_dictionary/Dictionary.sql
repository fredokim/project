create table Dictionary_IPE(
	Dictionary_num		number,
	Dictionary_word		varchar2(50),
	Dictionary_content	varchar2(3000)	
);

create table word_ipe(
	num		number,
	word	varchar2(500),
	content	varchar2(3000)
);
select * from WORD_IPE;



insert into word_ipe values(5,'5번단어','내용');

select * from Dictionary_IPE where Dictionary_num = 1

select * from Dictionary_IPE;
insert into Dictionary_IPE values(3,'단어3번','ffffffffffffffffffff');

select * from Dictionary_IPE where Dictionary_num = 1;


create table member_mylinux(
	mylinux_num		number,
	mylinux_word		varchar2(50),
	mylinux_content	varchar2(3000)	
);


select * from member_mylinux;
insert into Dictionary_IPE values(2,'단어2번','ff222222222ff');

drop table word_ipe;


-- 1000 ~ 1999
create table word_ipe(	
	num			number,
	word		varchar2(500),
	content		varchar2(3000)
);
insert into word_ipe values(1016,'단어16번','정보처리내용');
select * from WORD_IPE;

select * from (select rownum rnum, num, word, content from (select num, word, content from word_ipe order by num) ) where rnum>=1 and rnum<=10;

select * from (select rownum rnum, num, word, content from word_ipe order by num) where rnum>=1 and rnum<=10;
select num, word, content from word_ipe order by num

-- 2000 ~ 2999
create table word_sql(
	num			number,
	word		varchar2(500),
	content		varchar2(3000)
);
insert into word_sql values(2021,'단어21번','sql내용');

select * from (select rownum rnum, num, word, content from word_sql) where rnum>=1 and rnum<=10


-- 3000 ~ 3999
create table word_linux(
	num			number, 
	word		varchar2(500),
	content		varchar2(3000)
);
insert into word_linux values(3020,'단어20','리눅스내용');
insert into word_linux values(3021,'단어21','리눅스내용');
insert into word_linux values(3022,'단어22','리눅스내용');
insert into word_linux values(3023,'단어23번','리눅스내용');
insert into word_linux values(3024,'단어24번','리눅스내용');
insert into word_linux values(3025,'단어25번','리눅스내용');
insert into word_linux values(3026,'단어26번','리눅스내용');
insert into word_linux values(3027,'단어27번','리눅스내용');
insert into word_linux values(3015,'단어15번','리눅스내용');
insert into word_linux values(3016,'단어16번','리눅스내용');
insert into word_linux values(3017,'단어17번','리눅스내용');
insert into word_linux values(3018,'단어18번','리눅스내용');
insert into word_linux values(3019,'단어19번','리눅스내용');


select * from word_linux;

-- 4000 ~ 4999
create table word_etc(
	num			number,
	word		varchar2(500),
	content		varchar2(3000)
);
insert into word_etc values(4020,'단어20번','실무용어 내용');
insert into word_etc values(4021,'단어21','실무용어 내용');
insert into word_etc values(4022,'단어22','실무용어 내용');
insert into word_etc values(4009,'단어9번','실무용어 내용');
insert into word_etc values(4010,'단어10번','실무용어 내용');
insert into word_etc values(4011,'단어11번','실무용어 내용');
insert into word_etc values(4012,'단어12번','실무용어 내용');
insert into word_etc values(4013,'단어13번','실무용어 내용');
insert into word_etc values(4014,'단어14번','실무용어 내용');
insert into word_etc values(4015,'단어15번','실무용어 내용');
insert into word_etc values(4016,'단어16번','실무용어 내용');
insert into word_etc values(4017,'단어17번','실무용어 내용');
insert into word_etc values(4018,'단어18번','실무용어 내용');
insert into word_etc values(4019,'단어19번','실무용어 내용');


select * from (select rownum rnum, num, word, content from word_etc)
where rnum>=2 and rnum<=5;



insert into word_ipe values(6,'단어6번','내용','힌트');

select * from word_linux;









