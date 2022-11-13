/* 첨부파일 관리 테이블 */
CREATE TABLE attach_tb (
    file_idx INT NOT NULL AUTO_INCREMENT COMMENT '파일 번호 (PK)',
    board_idx INT NOT NULL COMMENT '게시글 번호 (FK)',
    original_name VARCHAR(260) NOT NULL COMMENT '원본 파일명',
    save_name VARCHAR(200) NOT NULL COMMENT '저장 파일명',
    size INT NOT NULL COMMENT '파일 크기',
    insert_time DATETIME NOT NULL DEFAULT NOW() COMMENT '등록일',
    PRIMARY KEY (file_idx)
) comment '첨부 파일';

/* 첨부파일 데이터 확인 */
select * from attach_tb;

drop table attach_tb;
delete from attach_tb;

/* 특정 게시글의 첨부파일 데이터 확인(User_boardDAO) */
select * from attach_tb where board_idx = ?;

/* 첨부파일 데이터 등록 */
insert into attach_tb(board_idx, original_name, save_name, size) values(?,?,?,?,?);