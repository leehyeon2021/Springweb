DROP database IF EXISTS mydb0303;
CREATE database mydb0303;
USE mydb0303;

CREATE TABLE member(
	id bigint primary key auto_increment, -- 이렇게 넣어도 pk 들어가긴 한다.
    name varchar(255)
);

INSERT INTO member( id , name ) VALUE( 1, 'name 1');
INSERT INTO member( id , name ) VALUE( 2, 'name 2');
INSERT INTO member( id , name ) VALUE( 3, 'name 3');
SELECT * FROM member;