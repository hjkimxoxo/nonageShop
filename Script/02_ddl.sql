CREATE TABLE MEMBER(
	name varchar2(20),
	userid varchar2(10),
	pwd varchar2(10),
	email varchar2(20),
	phone char(13),
	admin number(1) DEFAULT 0, --0사용자, 1관리자
	joinDate date DEFAULT sysdate,  
	PRIMARY KEY(userid)
);

SELECT * FROM USER_tables;

--테이블 컬럼 조회
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_name = 'MEMBER';

--제약조건 검색
SELECT * FROM USER_CONSTRAINTS WHERE table_name = 'MEMBER';

ALTER TABLE MEMBER MODIFY(name varchar2(20));




-------------------------------------trigger--------------------------------------

DELETE product;

CREATE TABLE product(
	code NUMBER(5) PRIMARY KEY,
	name varchar2(100),
	price NUMBER(8),
	pictureurl varchar(50),
	description varchar(1000)
);

DROP SEQUENCE product_seq;

CREATE SEQUENCE PRODUCT_SEQ
	START WITH 1
	INCREMENT BY 1;
	
--트리거를 이용하여 자동으로 번호입력되도록
CREATE OR REPLACE TRIGGER TRI_PRODUCT_CODE_SEQ
BEFORE INSERT ON PRODUCT --테이블 인서트전에 수행하고 인서트처리
FOR EACH ROW 
BEGIN 
	IF INSERTING AND :NEW.CODE IS NULL THEN --인서트다음에 입력될 새로운(NEW) 값(CODE)가 없다면
		SELECT PRODUCT_SEQ.NEXTVAL INTO :NEW.CODE --CODE에 넣어주겠다
		FROM DUAL;
	END IF;
END;

SELECT * FROM TRI_TEST;
INSERT INTO tri_test VALUES(NULL, 'test');
INSERT INTO tri_test(txt) VALUES('test2');


------------------------------------------------------------------------------------------
CREATE TABLE board(
	num number(5) PRIMARY KEY, 
	pass varchar2(30), 
	name varchar2(30),
	email varchar2(30),
	title varchar2(50),
	content varchar2(1000),
	readcount NUMBER(4) DEFAULT 0,
	writedate DATE DEFAULT sysdate
);

DROP SEQUENCE BOARD_SEQ;
DROP TRIGGER TRI_BOARD_NUM_AUTOSEQ;

CREATE SEQUENCE BOARD_SEQ
   START WITH 1
   INCREMENT BY 1;
   
CREATE OR REPLACE TRIGGER TRI_BOARD_NUM_AUTOSEQ
BEFORE INSERT ON BOARD --테이블 인서트전에 수행하고 인서트처리
FOR EACH ROW
BEGIN 
   IF Inserting AND :NEW.NUM IS NULL THEN
      SELECT BOARD_SEQ.NEXTVAL INTO :NEW.NUM FROM DUAL;
   END IF;
END;

DELETE board;
SELECT * FROM board;

