DROP database IF EXISTS practice5;
CREATE database practice5;
USE practice5;
CREATE TABLE book(
	bid int primary key auto_increment,
    bname varchar(100),
    bwriter varchar(50),
    bpub varchar(50)
);
INSERT INTO book( bname, bwriter, bpub ) VALUES('홍길동전' , '허균', '조선');
INSERT INTO book( bname, bwriter, bpub ) VALUES('허난설헌시집', '허난설헌', '허균출판');
SELECT*FROM book;