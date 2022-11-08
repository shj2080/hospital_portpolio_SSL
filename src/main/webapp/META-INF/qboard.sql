---문의게시판
CREATE TABLE qboard(
   QBOARD_NUM INT,     /* 글 번호 */
    MEM_ID VARCHAR(20) NOT NULL, /* 회원ID */
   QBOARD_SUBJECT VARCHAR(50) NOT NULL, /*제목*/
   QBOARD_CONTENT VARCHAR(2000), /*내용*/
   QBOARD_RE_REF INT NOT NULL, /* 부모글번호*/
   QBOARD_RE_LEV INT NOT NULL, /* 답글 깊이*/
   QBOARD_RE_SEQ INT NOT NULL, /*답글 순서(카운트)*/
   QBOARD_READCOUNT INT NOT NULL DEFAULT 0,   /*조회수*/
   QBOARD_DATE DATETIME NOT NULL,   /*작성일자*/
   
   PRIMARY KEY(QBOARD_NUM)
);

--AUTO_INCREMNET 테이블당 한개밖에 안되서 , QAN_NUM과 QNA_RE_LEV를 DAO에서 함께 증가시켜준다.
--따라서 둘다 DAO에서 증가시켜줌
--AUTO_INCREMNET 를 했다면 ALTER TABLE '테이블명' AUTO_INCREMENT=1;
--set SQL_SAFE_UPDATES =0; //SAFE풀고 
--SET @COUNT = 0; 
--UPDATE '테이블명' SET '[AUTO_INCREMNET 열이름]'=@count:=@count+1; //비어있는 숫자를 다시 정렬시켜준다 

insert into qboard values(1,'test','아','아',1,0,0,5,'2022/10/23');
select max(qboard_num) from qboard;
select * from qboard;


drop table qboard;