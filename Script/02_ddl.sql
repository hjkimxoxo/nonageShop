DROP TABLE product;
DROP TABLE MEMBER;
DROP TABLE cart;
DROP TABLE ORDER_detail;
DROP TABLE qna;
DROP TABLE address;
DROP TABLE worker;
DROP TABLE orders;
DROP TABLE order_detail;

-- 접속자 확인
SELECT USER FROM DUAL;

/* 상품
 * no 시퀀스 생성
 *  */
CREATE TABLE product (
	no NUMBER(5) NOT NULL PRIMARY KEY, /* 상품번호 */
	name VARCHAR(100), /* 상품이름 */
	kind CHAR(1), /* 상품종류 */
	price NUMBER, /* 원가 */
	saleprice NUMBER, /* 판매가 */
	margin NUMBER, /* 수익 */
	content VARCHAR2(1000), /* 상품내용 */
	image VARCHAR(100) DEFAULT 'default.jpg', /* 상품이미지 */
	del_yn CHAR(1) DEFAULT 'y', /* 상품삭제여부 */
	best_yn CHAR(1) DEFAULT 'n', /* 베스트상품여부 */
	reg_date DATE DEFAULT sysdate /* 등록일 */
);

/* 회원 */
CREATE TABLE member (
	id VARCHAR2(20) NOT NULL PRIMARY KEY, /* 회원아이디 */
	pwd VARCHAR(20), /* 회원암호 */
	name VARCHAR(100), /* 회원이름 */
	email VARCHAR2(40), /* 회원이메일 */
	zip_num CHAR(7), /* 우편번호 */
	address VARCHAR2(100), /* 주소 */
	phone CHAR(13), /* 전화번호 */
	leave_yn CHAR(1) DEFAULT 'y', /* 탈퇴여부 */
	join_date DATE DEFAULT sysdate/* 가입일 */
);

/* 장바구니 
 * no sequence */
CREATE TABLE cart (
	no NUMBER(5) NOT NULL, /* 장바구니번호 */
	pno NUMBER(5), /* 상품번호 */
	memberId VARCHAR2(20), /* 회원아이디 */
	quantity NUMBER(5), /* 수량 */
	result_yn CHAR(1), /* 처리완료여부 */
	reg_date DATE /* 등록일 */
);


/* 주문 */
CREATE TABLE orders (
	no NUMBER(5) NOT NULL PRIMARY KEY , /* 주문번호 */
	id VARCHAR2(20), /* 주문아이디 */
	order_date DATE /* 주문일 */
);


/* 주문상세 
 * no sequence */
CREATE TABLE order_detail (
	no NUMBER(5) NOT NULL PRIMARY KEY , /* 주문상세번호 */
	oNo NUMBER(5), /* 주문번호 */
	pNo NUMBER(5), /* 상품번호 */
	quantity NUMBER(5), /* 주문수량 */
	result_yn CHAR(1) /* 처리완료여부 */
);


/* QNA 게시판
 * no sequence */
CREATE TABLE qna (
	no NUMBER(5) NOT NULL PRIMARY KEY , /* 번호 */
	subject VARCHAR(100), /* 제목 */
	content VARCHAR2(1000), /* 내용 */
	rep VARCHAR2(1000), /* 답변 */
	id VARCHAR2(20), /* 작성자아이디 */
	rep_yn CHAR(1) DEFAULT '1', /* 답변여부 */
	write_date DATE DEFAULT sysdate /* 작성일 */
);

/* 주소 */
CREATE TABLE address (
	zip_num CHAR(7), /* 우편번호 */
	sido VARCHAR(100), /* 시도 */
	gugun VARCHAR(100), /* 구군 */
	dong VARCHAR(100), /* 동 */
	zip_code VARCHAR(100), /* 우편코드 */
	bunji VARCHAR(100) /* 번지 */
);

/* 관리자 */
CREATE TABLE worker (
	id VARCHAR2(20) NOT NULL PRIMARY KEY, /* 아이디 */
	pwd VARCHAR(20), /* 암호 */
	name VARCHAR(100), /* 이름 */
	phone CHAR(13) /* 전화번호 */
);


--------------------------------------------

ALTER TABLE cart
	ADD
		CONSTRAINT FK_product_TO_cart
		FOREIGN KEY (
			pno
		)
		REFERENCES product (
			no
		);

ALTER TABLE cart
	ADD
		CONSTRAINT FK_member_TO_cart
		FOREIGN KEY (
			memberId
		)
		REFERENCES member (
			id
		);

ALTER TABLE orders
	ADD
		CONSTRAINT FK_member_TO_orders
		FOREIGN KEY (
			id
		)
		REFERENCES member (
			id
		);

ALTER TABLE order_detail
	ADD
		CONSTRAINT FK_orders_TO_order_detail
		FOREIGN KEY (
			oNo
		)
		REFERENCES orders (
			no
		);

ALTER TABLE order_detail
	ADD
		CONSTRAINT FK_product_TO_order_detail
		FOREIGN KEY (
			pNo
		)
		REFERENCES product (
			no
		);
		
	
	
/*****************************************
시퀀스 생성 
******************************************/
-- product(no), 
CREATE SEQUENCE PRODUCT_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_PRODUCT_NO_SEQ
BEFORE INSERT ON PRODUCT
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT PRODUCT_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 


--cart(no),
CREATE SEQUENCE CART_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_CART_NO_SEQ
BEFORE INSERT ON CART
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT CART_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 

DROP SEQUENCE CART_NO_SEQ;
--- orders
CREATE SEQUENCE ORDERS_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_ORDERS_NO_SEQ
BEFORE INSERT ON ORDERS
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT ORDERS_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 

DROP SEQUENCE ORDERS_NO_SEQ;
DROP SEQUENCE ORDER_DETAIL_NO_SEQ;
--order_detail(no),
CREATE SEQUENCE ORDER_DETAIL_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_ORDER_DETAIL_NO_SEQ
BEFORE INSERT ON ORDER_DETAIL
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT ORDER_DETAIL_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 
DROP SEQUENCE ORDER_DETAIL_NO_SEQ;
--qna(no)
CREATE SEQUENCE QNA_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_QNA_NO_SEQ
BEFORE INSERT ON QNA
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT QNA_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 

-------

