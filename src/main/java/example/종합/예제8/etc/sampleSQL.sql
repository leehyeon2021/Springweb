# 1. 데이터베이스 생성
drop database if exists boardservice7;
create database boardservice7;
use boardservice7;

# 2. 테이블 생성
create table board(
	bno int unsigned auto_increment,
    constraint primary key(bno) ,
    bcontent longtext not null , 
    bwriter varchar(30) not null ,
    bdate datetime default now() 
);

# 3. 테이블 샘플 데이터 20개 만들기
INSERT INTO board (bcontent, bwriter) VALUES ('첫 번째 게시글 내용입니다.', '홍길동');
INSERT INTO board (bcontent, bwriter) VALUES ('두 번째 게시글입니다. 반갑습니다.', '김철수');
INSERT INTO board (bcontent, bwriter) VALUES ('오늘 날씨가 정말 좋네요.', '이영희');
INSERT INTO board (bcontent, bwriter) VALUES ('MySQL 연습용 게시글입니다.', '박민수');
INSERT INTO board (bcontent, bwriter) VALUES ('게시판 테스트 중입니다.', '최지은');
INSERT INTO board (bcontent, bwriter) VALUES ('점심 메뉴 추천 받습니다.', '정우성');
INSERT INTO board (bcontent, bwriter) VALUES ('주말에 뭐 하실 건가요?', '한지민');
INSERT INTO board (bcontent, bwriter) VALUES ('SQL 어렵지만 재미있네요.', '장나라');
INSERT INTO board (bcontent, bwriter) VALUES ('프로젝트 진행 상황 공유합니다.', '심사임당');
INSERT INTO board (bcontent, bwriter) VALUES ('에러 해결 방법 아시는 분?', '유재석');
INSERT INTO board (bcontent, bwriter) VALUES ('자유롭게 글 남겨주세요.', '강호동');
INSERT INTO board (bcontent, bwriter) VALUES ('오늘도 열공 중입니다.', '안중근');
INSERT INTO board (bcontent, bwriter) VALUES ('DB 설계가 중요하네요.', '김연아');
INSERT INTO board (bcontent, bwriter) VALUES ('백엔드 공부 중입니다.', '손흥민');
INSERT INTO board (bcontent, bwriter) VALUES ('게시판 CRUD 연습!', '이강인');
INSERT INTO board (bcontent, bwriter) VALUES ('카페 추천 부탁드려요.', '장원영');
INSERT INTO board (bcontent, bwriter) VALUES ('Spring이랑 MySQL 연동 성공!', '안유진');
INSERT INTO board (bcontent, bwriter) VALUES ('야근은 너무 힘들어요...', '장영실');
INSERT INTO board (bcontent, bwriter) VALUES ('코딩하면서 음악 듣는 중', '아이유');
INSERT INTO board (bcontent, bwriter) VALUES ('마지막 샘플 게시글입니다.', '오해원');


