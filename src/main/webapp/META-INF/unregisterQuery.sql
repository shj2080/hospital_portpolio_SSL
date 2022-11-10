
/* 탈퇴한 회원 정보의 일부를 보관하는 테이블 */
create table leaveMemberList(
                                leave_id varchar(15) primary key,
                                leave_date datetime
);

/* 회원 탈퇴 정보 테이블 */
select * from leaveMemberList;

/* 
회원 탈퇴 과정 순서

1.탈퇴하는 회원의 일부 정보를 탈퇴 유저 테이블에 insert(현재 id와 탈퇴일자 임시저장)
2.회원정보 테이블에서 id를 제외한 데이터들을 update로 날린 후 user_type 설정 (연계된 테이블과 문제 발생하는 것을 막음)
  [회원정보의 id를 참조하는 테이블 - qboard, user_board, treatment, reservation]
3.탈퇴작업 후 세션 모두 삭제(로그아웃 처리됨)
*/

/* 회원탈퇴 테이블에 insert */
insert into leaveMemberList values('testtest', now());

/* 회원정보 테이블에서 id 제외한 데이터 제거, 유저타입 'D'(탈퇴회원 타입) 설정 */
update membertbl set name = '탈퇴회원', id_num = '', password = '', address1 = '',
                     address2 = null, address3 = null, postcode = 0, phone = null, user_type = 'D'
where id = 'testtest';

drop table leaveMemberList;