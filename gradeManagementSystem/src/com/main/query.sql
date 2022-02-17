// db 생성
create database insa;

// 관리자 테이블 생성
create table insa.manager (
no INT(11) NOT NULL AUTO_INCREMENT,
id VARCHAR(20) NOT NULL,
name VARCHAR(20) NOT NULL,
password VARCHAR(500),
email VARCHAR(50),
phone VARCHAR(20),
use_flg VARCHAR(20) DEFAULT '1',
insert_date DATETIME,
update_date DATETIME,
CONSTRAINT manager_PK PRIMARY KEY(no, id)
);

// 학과 테이블 생성
create table insa.user_dept (
no INT(11) NOT NULL AUTO_INCREMENT,
dept_name VARCHAR(50) NOT NULL,
insert_date DATETIME,
update_date DATETIME,
CONSTRAINT user_dept_PK PRIMARY KEY(no)
);

insert into insa.user_dept (dept_name, insert_date) values ('이과', sysdate());
insert into insa.user_dept (dept_name, insert_date) values ('문과', sysdate());

// 학생 테이블 생성
create table insa.user_info (
no INT(11) NOT NULL AUTO_INCREMENT,
dept_no INT,
id VARCHAR(20) NOT NULL,
name VARCHAR(20) NOT NULL,
password VARCHAR(500),
email VARCHAR(50),
phone VARCHAR(20),
auth VARCHAR(20),
use_flg VARCHAR(20) DEFAULT '1',
insert_date DATETIME,
update_date DATETIME,
CONSTRAINT user_info_PK PRIMARY KEY(no, id),
FOREIGN KEY (dept_no) REFERENCES user_dept(no) ON UPDATE CASCADE
);

// 성적 테이블 생성
create table insa.user_grades (
no INT(11) NOT NULL AUTO_INCREMENT,
user_no INT,
kor INT(20),
eng INT(20),
mat INT(20),
sci INT(20),
insert_date DATETIME,
update_date DATETIME,
CONSTRAINT user_grades_PK PRIMARY KEY(no),
FOREIGN KEY (user_no) REFERENCES user_info(no) ON UPDATE CASCADE
);