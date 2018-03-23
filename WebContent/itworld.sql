
--멤버 테이블 
drop table wordmember;

create table wordmember(--가입 멤버
member_num number default 0,
member_id varchar2(15),
member_pw varchar2(13),
member_nickname varchar2(15),
member_myipe varchar2(4000) default 0 not null,
member_mysql varchar2(4000)default 0 not null,
member_mylinux varchar2(4000)default 0 not null,
member_myetc varchar2(4000)default 0 not null,
member_rankpoint number default 0,
member_mywan varchar2(4000) default 0 not null,
primary key(member_id)
);

drop table word;


delete from wordmember where member_id='yypp'
insert into wordmember values (0, 'yypp', '1234', '똔똔', ' ',' ',' ',' ',0)
select * from wordmember where member_id='yypp'
update wordmember set member_nickname='관리자' where member_id = 'admin';
update wordmember set member_mywan='0' where member_id = 'yypp';
update wordmember set member_myipe='0' where member_id = 'yypp';
update wordmember set member_mysql='0' where member_id = 'yypp';
update wordmember set member_mylinux='0' where member_id = 'yypp';
update wordmember set member_myetc='0' where member_id = 'yypp';
update wordmember set member_rankpoint=0 where member_id = 'yypp'
alter table wordmember rename column member_myiep to member_myipe
select * from wordmember
ALTER TABLE wordmember ADD(member_mywan varchar2(400

alter table wordmember modify(member_myipe varchar2(4000) default 0 not null);
alter table wordmember modify(member_mysql varchar2(4000) default 0 not null);
alter table wordmember modify(member_mylinux varchar2(4000) default 0 not null);
alter table wordmember modify(member_myetc varchar2(4000) default 0 not null);
alter table wordmember modify(member_mywan varchar2(4000) default 0 not null);


--멤버 시퀀스
drop sequence num_seq;
create sequence num_seq
start with 0
increment by 1
maxvalue 1000
minvalue 0


drop table board_comment;

--댓글 테이블
create table board_comment(
comment_num number not null,	--댓글 글번
comment_board number not null,--게시글 글번호 
comment_id varchar2(50),--댓글 작성자 
comment_date date,--댓글 작성일 
comment_parent number,--부모글
comment_content varchar2(500) not null,--댓글 내용
constraint pk_comment primary key(comment_num),
constraint fk_comment foreign key(comment_board) references wordmemberboard2(board_num2)on delete cascade
);
drop sequence comment_seq;
create sequence comment_seq;

drop table wordmemberboard;
--문의 게시판 테이블
create table wordmemberboard(
board_num number,	--글 번호 
board_id varchar2(20),	--작성자
board_subject varchar2(50), --제목
board_content varchar2(2000), --내용
board_file varchar2(50), 	--첨부될 파일명
board_re_ref number,	--답변 글 작성시 참조되는 글의 번호
board_re_lev number,	--답변 글의 깊이 
board_re_seq number,	--답변 글의 순서
board_readcount number, --글의 조회수
board_date date,--글의 작성 날짜
primary key(board_num)
);

select * from wordmemberboard

alter table wordmemberboard
add constraint pk_board
foreign key(board_id) references wordmember(member_id);

drop table wordmemberboard2;
--자유 게시판 테이블
create table wordmemberboard2(
board_num2 number,	--글 번호 
board_id2 varchar2(20),	--작성자
board_subject2 varchar2(50), --제목
board_content2 varchar2(2000), --내용
board_file2 varchar2(50), 	--첨부될 파일명
board_re_ref2 number,	--답변 글 작성시 참조되는 글의 번호
board_re_lev2 number,	--답변 글의 깊이 
board_re_seq2 number,	--답변 글의 순서
board_readcount2 number, --글의 조회수
board_date2 date,--글의 작성 날짜
primary key(board_num2)
);

alter table wordmemberboard2
add constraint pk_board2
foreign key(board_id2) references wordmember(member_id);

select * from wordmemberboard2


-- 정보처리기사 용어 사전 테이블 
create table word_ipe(
	num			number,
	word		varchar2(500),
	content		varchar2(3000)
);

delete from WORD_IPE;
select * from word_ipe
 
-- sql-d 용어 사전 테이블 
create table word_sql(
	num			number,
	word		varchar2(500),
	content		varchar2(3000)
);


-- 리눅스 마스터 용어 사전 테이블 
create table word_linux(
	num			number,
	word		varchar2(500),
	content		varchar2(3000)

);

-- 기타실무용어 사전 테이블 
create table word_etc(
	num			number,
	word		varchar2(500),
	content		varchar2(3000)

);

select * from WORD_ipe;
select * from WORD_sql;
select * from WORD_linux;
select * from WORD_ETC;


-- 전체 테이블 확인하기
SELECT *
FROM tab;


-- 검색 쿼리
select * from wordview where word like '%리눅스%'


-- 검색용 view
create or replace view wordview as
select * from WORD_ipe
union
select * from WORD_SQL
union
select * from WORD_LINUX
union
select * from WORD_ETC



select * from wordmember where member_id ='yypp'
