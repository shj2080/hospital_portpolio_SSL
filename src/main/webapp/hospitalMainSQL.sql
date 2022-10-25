/*회원 테이블 생성 */
create table membertbl(
   name varchar(20) not null, /* 회원이름 */
   id_num varchar(20) unique,  /* 주민등록번호 */
   id varchar(15) primary key,      /* 아이디(기본키) */
   password varchar(64) not null,   /* 비밀번호 (SHA256 방식으로 암호화된 비밀번호를 저장하기 위해 컬럼크기 64지정)*/
   address1 Nvarchar(60) not null,    /* 주소 */
   address2 Nvarchar(60) not null,    /* 주소 */
   address3 Nvarchar(60) not null,    /* 주소 */
   postcode int not null,
   phone varchar(14)       /* 전화번호 */
);

/* 회원가입 비밀번호 허용 자리수 (20?) */
/* 회원정보 테스트 데이터 */
insert into membertbl values('홍길동', '960101-1234567', 'test001', 'eb508df11dd58cf4bb4e8ed2c5629c2d6fcb6455913c1e0e3ce2cd11a9cd7e20', '대구 달서구 달구벌대로', '테스트', '테스트데이터', 0, '010-0000-0000');

/* 암호화 비밀번호 저장을 위해 컬럼크기 변경 */
alter table membertbl
modify password varchar(64) not null;

/* 테스트데이터 업데이트 */
update membertbl set password = 'eb508df11dd58cf4bb4e8ed2c5629c2d6fcb6455913c1e0e3ce2cd11a9cd7e20' where id = 'test001';
commit;

/* 테이블 문제 발생 시 삭제 */
drop table membertbl;

/*memberTBL 테이블 생성 확인*/
select * from membertbl;

/*진료과 테이블 생성*/
/*진료과 코드, 진료과 이름*/
create table speciality(
   speciality_code int auto_increment primary key, 
   speciality_name varchar(20)
);

/* 진료과 샘플데이터 */
insert into speciality(speciality_code, speciality_name)

/*테이블 문제 발생 시 삭제*/
drop table speciality;

/*speciality 테이블 생성 확인*/
select * from speciality;

/*의료진 테이블 생성
의사 코드,의사 이름, 진료과 코드*/
create table doctor(
   doctor_code int auto_increment primary key, /* 의사 코드 */
   doctor_name varchar(20) not null, 	/* 의사 이름 */
   speciality_code int REFERENCES speciality(speciality_code) /* 진료과 코드 */
);

/* 의료진 샘플 데이터 */
insert into doctor(doctor_name, speciality_code) values('', 1);

/*테이블 문제 발생 시 삭제*/
drop table doctor;

/*doctor 테이블 생성 확인*/
select * from doctor;

/*진료 테이블 생성*/
/*진료코드 , 진료과 코드, 의사 코드, 아이디, 회원 이름, 진료 날짜 및 시간, 전화번호*/
create table treatment(
    treatment_code int auto_increment,  /* 진료코드 */
    speciality_code int not null,  /* 진료과 코드 */
    doctor_code int,        /* 의사코드 */
    id varchar(15) not null,     /* 아이디 */
    treatment_date date not null,    /* 진료 날짜 및 시간 */
    phone varchar(14),   /* 전화번호 */

    primary key(treatment_code),
    foreign key(id) REFERENCES membertbl(id),
    foreign key(speciality_code) REFERENCES speciality(speciality_code),
    foreign key(doctor_code) REFERENCES doctor(doctor_code)
);

/*테이블 문제 발생 시 삭제*/
drop table treatment;

/*treatment 테이블 생성 확인*/
select * from treatment;