/* 초기 테이블 생성 SQL */
/*회원 테이블 생성 */
create table membertbl(
   name varchar(20) not null, /* 회원이름 */
   id_num varchar(20) unique,  /* 주민등록번호 */
   id varchar(15) primary key,      /* 아이디(기본키) */
   password varchar(64) not null,   /* 비밀번호 (SHA256 방식으로 암호화된 비밀번호를 저장하기 위해 컬럼크기 64지정)*/
   address1 Nvarchar(60) not null,    /* 주소 */
   address2 Nvarchar(60),    /* 주소 */
   address3 Nvarchar(60),    /* 주소 */
   postcode int not null,
   phone varchar(13),       /* 전화번호 */
   user_type char(1) not null default 'N' CHECK (user_type IN('N','M','D'))
);
/* 관리자 식별할 컬럼? */
/* 일반회원 : N, 관리자 : M, 탈퇴회원 : D */

/* 탈퇴한 회원 정보의 일부를 보관하는 테이블 */
create table leaveMemberList(
	leave_id varchar(15) primary key,
	leave_date datetime
);

/*진료과 테이블 생성*/
/*진료과 코드, 진료과 이름*/
create table speciality(
   speciality_code int auto_increment primary key, 
   speciality_name varchar(20)
);

/* 진료과 초기데이터 */
insert into speciality(speciality_name) values('내과');
insert into speciality(speciality_name) values('외과');
insert into speciality(speciality_name) values('산부인과');
insert into speciality(speciality_name) values('영상의학과');
insert into speciality(speciality_name) values('마취통증의학과');

/*의료진 테이블 생성
의사 코드,의사 이름, 진료과 코드*/
create table doctor(
   doctor_code int auto_increment primary key, /* 의사 코드 */
   doctor_name varchar(20) not null, 	/* 의사 이름 */
   speciality_code int REFERENCES speciality(speciality_code) /* 진료과 코드 */
);

/* 테스트를 위한 의료진 테이블 정보 입력 */
/*
 * 진료과 코드별 진료과 안내
 * 1 : 내과, 2 : 외과, 3 : 산부인과, 4 : 영상의학과 5 : 마취통증의학과 
 */
insert into doctor(doctor_name,speciality_code)
values('김철수',1);
insert into doctor(doctor_name,speciality_code)
values('이재용',1);
insert into doctor(doctor_name,speciality_code)
values('이희망',2);
insert into doctor(doctor_name,speciality_code)
values('오소리',2);
insert into doctor(doctor_name,speciality_code)
values('김바다',3);
insert into doctor(doctor_name,speciality_code)
values('장보고',3);
insert into doctor(doctor_name,speciality_code)
values('전영훈',4);
insert into doctor(doctor_name,speciality_code)
values('황보창민',4);
insert into doctor(doctor_name,speciality_code)
values('송호진',4);
insert into doctor(doctor_name,speciality_code)
values('이순신',5);
insert into doctor(doctor_name,speciality_code)
values('김주사',5);

/*진료 테이블 생성*/
/*진료코드(PK) , 진료과 코드, 의사 코드, 아이디, 회원 이름, 진료 날짜 및 시간, 전화번호,회원이름 */
/* (의사코드,진료과코드,진료날짜 및 시간) - UNIQUE */
create table treatment(
    treatment_code int auto_increment,  /* 진료코드 */
    speciality_code int not null,  /* 진료과 코드 */
    doctor_code int,        /* 의사코드 */
    id varchar(15) not null,     /* 아이디 */
    treatment_date datetime not null,    /* 진료 날짜 및 시간 */
    phone varchar(13),   /* 전화번호 */
	u_name varchar(20) not null, /* 회원이름 */
    primary key(treatment_code),
    constraint uq_treament_key UNIQUE KEY(speciality_code, doctor_code, treatment_date),
    foreign key(id) REFERENCES membertbl(id),
    foreign key(speciality_code) REFERENCES speciality(speciality_code),
    foreign key(doctor_code) REFERENCES doctor(doctor_code)
);

/* 예약진료 테이블 정의 */
/* 예약번호(PK), 예약날짜, 유저id, 전화번호, 의사코드, 진료과코드, 진료상태, 회원이름 */
CREATE TABLE reservation (
	reservation_code INT NOT NULL AUTO_INCREMENT,
	speciality_code INT not NULL,
	doctor_code INT not NULL,
	id VARCHAR(15) not NULL,
	reservation_date DATETIME not NULL,
	phone VARCHAR(13) not NULL,
	treatment_status CHAR(1) not null default 'N' check (treatment_status in ('Y', 'N')),
	u_name varchar(20) not null,
	PRIMARY KEY (reservation_code),
	constraint uq_reservation UNIQUE KEY(reservation_date, doctor_code, speciality_code)
);

/*게시판 테이블 작성*/
create table user_board(
	post_no INT NOT NULL,
	id varchar(20) NOT NULL,
	post_date datetime NOT NULL,
	post_subject NVARCHAR(50) NOT NULL,
	post_text TEXT,
	isAttachFile CHAR(1) NOT NULL DEFAULT('N') CHECK(isAttachFile IN ('Y','N')),
	
	PRIMARY KEY (post_no),
 	foreign key(id) REFERENCES membertbl(id)
);

/* 문의게시판 */
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