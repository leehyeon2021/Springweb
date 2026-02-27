#[1] 데이터베이스 생성 
drop database if exists boardservice9;
create database boardservice9;
use boardservice9;
#[2] 테이블 생성 
create table board(
        bno int auto_increment , 
        constraint primary key( bno ),
    bcontent longtext not null , 
    bwriter varchar(30) not null ,
    bdate datetime default now()
);
#[3] 테이블 샘플 데이터 10개 ( AI 활용 ) 
INSERT INTO board (bcontent, bwriter) VALUES ('게시글 내용 1입니다.', '김철수');
INSERT INTO board (bcontent, bwriter) VALUES ('게시글 내용 2입니다.', '이영희');
INSERT INTO board (bcontent, bwriter) VALUES ('게시글 내용 3입니다.', '박민수');
INSERT INTO board (bcontent, bwriter) VALUES ('게시글 내용 4입니다.', '최유진');
INSERT INTO board (bcontent, bwriter) VALUES ('게시글 내용 5입니다.', '정지훈');
INSERT INTO board (bcontent, bwriter) VALUES ('게시글 내용 6입니다.', '강호동');
INSERT INTO board (bcontent, bwriter) VALUES ('게시글 내용 7입니다.', '유재석');
INSERT INTO board (bcontent, bwriter) VALUES ('게시글 내용 8입니다.', '신동엽');
INSERT INTO board (bcontent, bwriter) VALUES ('게시글 내용 9입니다.', '송중기');
INSERT INTO board (bcontent, bwriter) VALUES ('게시글 내용 10입니다.', '전지현');
select * from board;