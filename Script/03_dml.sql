--view 

  
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
