--view 
--cart 

INSERT INTO CART(PNO, MEMBERID, QUANTITY, RESULT_YN, REG_DATE) VALUES(2, 'one', 1, '1', SYSDATE)



SELECT * FROM cart_view;

CREATE OR REPLACE VIEW CART_VIEW
(NO, PNO, MEMBERID, MNAME, PNAME, QUANTITY, PSALEPRICE, REG_DATE)
AS 
SELECT C.NO, C.PNO, C.MEMBERID, M.NAME, P.NAME, C.QUANTITY, P.SALEPRICE, C.REG_DATE
FROM CART C LEFT JOIN PRODUCT P ON C.PNO = P.NO
		LEFT JOIN MEMBER M ON M.ID = C.MEMBERID;
	
SELECT NO, PNO, MEMBERID, MNAME, PNAME, QUANTITY, PSALEPRICE, REG_DATE FROM CART_VIEW ORDER BY NO;


SELECT C.NO, C.PNO, P.NAME, C.MEMBERID, M.NAME, C.QUANTITY, P.SALEPRICE, C.REG_DATE
FROM CART C LEFT JOIN PRODUCT P ON C.NO = P.NO
		LEFT JOIN MEMBER M ON M.ID = C.MEMBERID;
-- 신상품
create or replace view new_pro_view
as
select no, name, saleprice, image 
from( select rownum, no, name, saleprice, image 
      from product  
      where del_yn='y' 
      order by reg_date desc)
where  rownum <=4;

select rownum, no, name, saleprice, image 
from( select rownum, no, name, saleprice, image 
      from product  
      where del_yn='y' 
      order by reg_date DESC)
      WHERE rownum <=4;

SELECT rownum, no, name, saleprice, image FROM new_pro_view;


--베스트 
create or replace view best_pro_view
as
select no, name, saleprice, image 
from( select rownum, NO, name, saleprice, image 
      from product  
      where best_yn='y' 
      order by reg_date desc)
where  rownum <=4;

SELECT * FROM best_pro_view;
-------------------------------------------------------------------------------


SELECT * FROM product;

insert into product(name, kind, price, saleprice, margin, content, image) values(
 '크로그다일부츠', '2', '40000', '50000', '10000', '오지니랄 크로그다일부츠 입니다.', 'w2.jpg');
insert into product(name, kind, price, saleprice, margin, content, image, best_yn) values(
 '롱부츠', '2', 40000, 50000, 10000,'따뜻한 롱부츠 입니다.', 'w-28.jpg', 'n');
insert into product(name, kind, price, saleprice, margin, content, image, best_yn) values( 
 '힐', '1', '10000', '12000', '2000', '여성용전용 힐', 'w-26.jpg', 'n');
insert into product(name, kind, price, saleprice, margin, content, image, best_yn) values(
'슬리퍼', '4', '5000', '5500', '500', '편안한 슬리퍼입니다.', 'w-25.jpg', 'y');
insert into product(name, kind, price, saleprice, margin, content, image, best_yn) values(
'회색힐', '1', '10000', '12000', '2000', '여성용전용 힐', 'w9.jpg', 'n');
insert into product(name, kind, price, saleprice, margin, content, image) values(
'여성부츠', '2', '12000', '18000', '6000', '여성용 부츠', 'w4.jpg');
insert into product(name, kind, price, saleprice, margin, content, image, best_yn) values(
 '핑크샌달', '3', '5000', '5500', '500', '사계절용 샌달입니다.', 'w-10.jpg', 'y');
insert into product(name, kind, price, saleprice, margin, content, image, best_yn) values(
 '슬리퍼', '3', '5000', '5500', '500', '편안한 슬리퍼입니다.', 'w11.jpg', 'y');
insert into product(name, kind, price, saleprice, margin, content, image) values(
 '스니커즈', '4', '15000', '20000', '5000', '활동성이 좋은 스니커즈입니다.', 'w1.jpg');
insert into product(name, kind, price, saleprice, margin, content, image, best_yn) values(
 '샌달', '3', '5000', '5500', '500', '사계절용 샌달입니다.', 'w-09.jpg','n');
insert into product(name, kind, price, saleprice, margin, content, image, best_yn) values(
 '스니커즈', '5', '15000', '20000', '5000', '활동성이 좋은 스니커즈입니다.', 'w-05.jpg','n');



--member---------------------------------------------------------
SELECT * FROM MEMBER;

insert into member(id, pwd, name, zip_num, address, phone) values
('one', '1111', '김나리', '133-110', '서울시성동구성수동1가 1번지21호', '017-777-7777');
insert into member(id, pwd, name, zip_num, address, phone) values
('two', '2222', '이백합', '130-120', '서울시송파구잠실2동 리센츠 아파트 201동 505호', '011-123-4567');

INSERT INTO ADDRESS VALUES(1234, '시도', '구군', '동', 'code','5451');
SELECT * FROM ADDRESS WHERE dong LIKE '동';



alter table address 
drop primary key cascade;

CREATE TABLE address (
       zip_num              VARCHAR2(7) NOT NULL,
       sido                 VARCHAR2(30) NULL,
       gugun                VARCHAR2(30) NULL,
       dong                 VARCHAR2(100) NULL,
       zip_code             VARCHAR2(30) NOT NULL,
       bunji                VARCHAR2(30) NULL
);

------------------------
DELETE ORDERs;
select * FROM orders ORDER BY no;
insert into orders(id, ORDER_DATE) values('one', sysdate);
insert into orders(id, ORDER_DATE) values('one', sysdate);
insert into orders(id, ORDER_DATE) values('two', sysdate);

DELETE cart;
SELECT * FROM cart;
UPDATE cart SET result_yn = 2 WHERE NO = 68;

SELECT * FROM ORDER_VIEW2 ORDER BY ono;
----- 
SELECT * FROM orders ORDER BY no;
SELECT * FROM ORDER_DETAIL ORDER BY no;

DELETE order_detail;
DELETE orders;

INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY, RESULT_YN) VALUES(1, 2, 5,'1');
INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY, RESULT_YN) VALUES(2, 4, 3,'1');
INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY, RESULT_YN) VALUES(3, 3, 1,'1');
INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY, RESULT_YN) VALUES(3, 2, 1,'1');
INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY, RESULT_YN) VALUES(3, 6, 2,'1');
INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY, RESULT_YN) VALUES(3, 1, 2,'1');


SELECT * FROM qna;
INSERT INTO qna (SUBJECT,CONTENT,REP,ID,WRITE_DATE) VALUES ('제목', '내용', '답변', 'one', sysdate);

SELECT ONO, ODNO, ORDERDATE, MEMBERID, MNAME,
				 ZIP_NUM, ADDRESS, PHONE, PNO, PNAME, QUANTITY, SALEPRICE, RESULT FROM ORDER_VIEW WHERE MEMBERID = 'one' ORDER BY odno desc;
				 
select max(no) from orders;