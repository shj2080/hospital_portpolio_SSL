/*notice 게시판 SQL*/
/*게시판 테이블 작성*/
create table user_board(
	post_no INT NOT NULL,
	id varchar(20) NOT NULL,
	post_date datetime NOT NULL,
	post_pwd NVARCHAR(12),
	post_subject NVARCHAR(50) NOT NULL,
	post_text TEXT,
	post_file CHAR(1) NOT NULL DEFAULT('N') CHECK(post_file IN ('Y','N'),
	
	PRIMARY KEY (post_no),
 	foreign key(id) REFERENCES membertbl(id) ON DELETE CASCADE ON UPDATE CASCADE

);

select * from user_board;

/* Mysql 제약조건 확인(user_board 테이블) */
select * from information_schema.table_constraints where table_name in ('user_board');

/* 제약조건 삭제 */
alter table user_board
drop constraint user_board_chk_1;

/* 게시판 컬럼형식 datetime 변환 */
alter table user_board
modify post_date datetime NOT NULL;

/* 게시판 첨부파일 컬럼 형식 변환 */
alter table user_board
modify post_file CHAR(1);

/* 게시판 수정 sql */
update user_board post_date = ?, post_pwd = ?, post_subject = ?, post_text = ?, post_file = ?
where id = ? AND post_no = ?;

/* 게시판 특정 게시글 삭제 sql */
delete from user_board where post_no = ? AND id = ? AND post_pwd = ?;

/* 게시판 관련 SQL */
select * from user_board;

select post_no, post_subject ,id, post_date from membertbl natural join user_board order by post_no desc;
									
select post_no, post_subject, id, post_date, post_text from user_board where post_no = 1;