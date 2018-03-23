drop table member;

select * from member

drop table word_ipe;
drop table word_sql;
drop table word_linux;
drop table word_etc;


member.setMem_num(rs.getInt("mem_num"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_pass(rs.getString("mem_pass"));
				member.setMem_nickname(rs.getString("mem_nickname"));		
				
				
				
create view word_all as (select * from WORD_IPE
union
select * from word_sql)

select * from word_all




drop table freeboard;
drop table qnaboard;


create table freeboard(




)




create table member(
mem_num		number(10),
mem_id		varchar2(20),
mem_pass	varchar2(30),
mem_nickname	varchar2(30),
mem_rankpoint  number(10) default 0
);

insert into member values(0,'admin','1234','관리자',0);
insert into member values(1,'user1','1234','유저',10);
insert into member values(2,'user2','1234','유저',20);
insert into member values(3,'user3','1234','유저',30);
insert into member values(4,'user4','1234','유저',40);

﻿create table word_ipe(
word_num		number(10) primary key,
word_name		varchar2(50),
word_info	varchar2(300)
);

﻿create table word_sql(
word_num		number(10) primary key,
word_name		varchar2(50),
word_info	varchar2(300)
);

﻿create table word_linux(
word_num		number(10) primary key , 
word_name		varchar2(50),
word_info	varchar2(300)
);

﻿create table word_etc(
word_num		number(10) primary key,
word_name		varchar2(50),
word_info	varchar2(300)
);

select * from WORD_ETC
union
select * from WORD_IPE
where word_name like %1%

select * from word_ipe;
select * from word_sql;
select * from word_linux;
select * from word_etc;

insert into word_ipe values(10001,'가','가는 가이다');
insert into word_ipe values(10002,'나','나는 나이다');
insert into word_ipe values(10003,'다','다는 die다');

insert into word_sql values(20001,'A','A = A');
insert into word_sql values(20002,'B','B = B');
insert into word_sql values(20003,'C','C = C');

insert into word_linux values(30001,'a','a = a');
insert into word_linux values(30002,'b','b = b');
insert into word_linux values(30003,'c','c = c');

insert into word_etc values(40001,'ㄱ','ㄱ은 ㄱ');
insert into word_etc values(40002,'ㄴ','ㄴ은 ㄴ');
insert into word_etc values(40003,'ㄷ','ㄷ은 ㄷ');





