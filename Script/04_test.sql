SELECT NAME, USERID, PWD, EMAIL, PHONE, ADMIN, JOINDATE FROM MEMBER;

INSERT INTO MEMBER VALUES('박규영','parkgy','1234','pgy@gmail.com','010-1111-2222',1, sysdate);

SELECT NAME, USERID, PWD, EMAIL, PHONE, ADMIN, JOINDATE FROM MEMBER WHERE userid='somi';

UPDATE MEMBER SET name = '문채원', pwd='5678', email='mcw@naver.com', phone='010-5656-5656', admin=0, joindate='2020-08-20' WHERE userID='parkgy';

DELETE FROM MEMBER WHERE USERID = 'parkgy';


--------------------------------
---- TRIGGER----
--------------------------------

CREATE TABLE tri_test(
	NO NUMBER PRIMARY KEY,
	txt varchar2(20)
);

CREATE SEQUENCE tri_test_seq
	START WITH 1
	INCREMENT BY 1;
	
--트리거를 이용하여 자동으로 번호입력되도록
CREATE OR REPLACE TRIGGER tri_test_autoseq
BEFORE INSERT ON tri_test --테이블 인서트전에 수행하고 인서트처리
FOR EACH ROW 
BEGIN 
	IF inserting AND :NEW.NO IS NULL THEN --인서트다음에 입력될 새로운(new) 값(no)가 없다면
		SELECT tri_test_seq.nextval INTO :NEW.NO --no에 넣어주겠다
		FROM dual;
	END IF;
END;

SELECT * FROM TRI_TEST;
INSERT INTO tri_test VALUES(NULL, 'test');
INSERT INTO tri_test(txt) VALUES('test2');



-----------------------------------------------------------------------------------
SELECT * FROM PRODUCT;


SELECT CODE,NAME,PRICE,PICTUREURL,DESCRIPTION FROM PRODUCT;

SELECT CODE,NAME,PRICE,PICTUREURL,DESCRIPTION FROM PRODUCT WHERE code = 1;


SELECT NUM, PASS, NAME, EMAIL, TITLE, CONTENT, READCOUNT, WRITEDATE FROM BOARD;
INSERT INTO BOARD(PASS, NAME, EMAIL, TITLE, CONTENT) VALUES (?, ?, ?, ?, ?);
UPDATE BOARD SET PASS = ?, NAME = ? , EMAIL = ?, TITLE = ?, CONTENT = ? WHERE NUM = ?;

SELECT * FROM board;
DELETE FROM board WHERE num=33;