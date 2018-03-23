drop table wordmember;

create table wordmember(--가입 멤버
member_num number default 0,
member_id varchar2(15),
member_pw varchar2(13),
member_nickname varchar2(15),
member_mylinux varchar2(15),
member_mysql varchar2(15),
member_myeip varchar2(15),
member_myetc varchar2(15),
member_rankpoint number default 0,
primary key(member_id)
);


create table wordmemberboard(--문의 게시판
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

alter table wordmemberboard
add constraint pk_board
foreign key(board_id) references wordmember(member_id);


create table freeboard(--자유 게시판 
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

insert into freeboard values(1,1,1,1,1,1,1,1,1,sysdate);

alter table freeboard
add constraint pk_board2
foreign key(board_id2) references wordmember(member_id);

drop sequence num_seq;
create sequence num_seq
start with 0
increment by 1
maxvalue 1000
minvalue 0

create table word(
word  varchar2(200), —단어
info varchar2(1000), —단어 설명
hint varchar2(200), — 단어 힌트 
word_number number,
primary key(word_number)
);




