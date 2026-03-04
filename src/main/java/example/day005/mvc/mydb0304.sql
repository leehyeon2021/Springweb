DROP database IF EXISTS mydb0304;
CREATE database mydb0304;
USE mydb0304;
CREATE TABLE exam(
	eno int primary key auto_increment,
    ename varchar(255)
);
INSERT INTO exam( ename ) VALUES( '허난설헌' );
INSERT INTO exam( ename ) VALUES( '홍길동' );
INSERT INTO exam( ename ) VALUES( '김만덕' );
SELECT*FROM exam;