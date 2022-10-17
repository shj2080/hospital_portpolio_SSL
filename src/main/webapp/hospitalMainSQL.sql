--회원 테이블 생성
--회원이름, 주민등록번호, 아이디, 비밀번호, 주소, 전화번호
create table member_tbl(
   name varchar(20) not null,
   id_num varchar(20), 
   id varchar(15),
   password varchar(20) not null,  
   address varchar(50) not null,   
   phone varchar(14),   
   
   primary key(id_num,id)
);

commit;

--memberTBL 테이블 생성 확인
select * from member_tbl;

--진료과 테이블 생성
--진료과 코드, 진료과 이름
create table speciality(
   speciality_code int primary key, 
   speciality_name varchar(20)
);

drop table speciality;
--speciality 테이블 생성 확인
select * from speciality;

--의료진 테이블 생성
--의사 코드,의사 이름, 진료과 코드
create table doctor(
   doctor_code varchar(5) primary key, /* 의사 코드 */
   doctor_name varchar(20), 	/* 의사 이름 */
   speciality_code int, 	/* 진료과 코드 */
   
   FOREIGN KEY (speciality_code) REFERENCES speciality(speciality_code)
);

drop table doctor;

--doctor 테이블 생성 확인
select * from doctor;

/*
--예약 테이블 생성
--예약코드 --진료코드  --아이디 --회원이름 --진료과 코드 --의사 코드 --예약 날짜 및 시간 --전화번호
create table reservation(
    reservation_code int,   
    treatment_code int,
    id varchar(15),
    name varchar(20) not null, 
    speciality_code varchar(5) not null, 
    doctor_code varchar(20) not null, 
    reservation_date date not null, 
    phone varchar(14), 
    
    primary key(reservation_code, id)
);

--reservation 테이블 생성 확인
select * from reservation;

*/

--진료 테이블 생성
--진료코드 , 진료과 코드, 의사 코드, 아이디, 회원 이름, 진료 날짜 및 시간, 전화번호
create table treatment(
    treatment_code varchar(5), 
    speciality_code int not null, 
     doctor_code varchar(5), 
    id varchar(15), 
   name varchar(20) not null, 
   treatment_date date not null, 
   phone varchar(14), 
   
   primary key(treatment_code, id),
   foreign key(speciality_code) REFERENCES speciality(speciality_code),
   foreign key(doctor_code) REFERENCES doctor(doctor_code)
);

drop table treatment;
--treatment 테이블 생성 확인
select * from treatment;

--진료대기 테이블 생성
--진료코드 ,진료과 코드, 의사 코드, 아이디, 회원 이름
create table treatment_waiting(
	treatment_code varchar(5) primary key,
   speciality_code int not null, 
   doctor_code varchar(5), 
   id varchar(15), 
   name varchar(20) not null, 
   foreign key(treatment_code) REFERENCES treatment(treatment_code),
   foreign key(doctor_code) REFERENCES doctor(doctor_code)
);

drop table treatment_waiting;
--treatment_waiting 테이블 생성 확인
select * from treatment_waiting;