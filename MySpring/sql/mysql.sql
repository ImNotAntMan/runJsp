use webdb;
create table tblmember (
	m_id varchar(50) not null primary key,-- 아이디 
	m_passwd varchar(256) not null, 
	m_name varchar(50) not null, -- 성명
	m_rdate datetime not null default sysdate(),
	m_udate datetime not null default sysdate()
);

select * from tblmember;
ALTER TABLE tblmember ADD m_passwd VARCHAR(256) NOT NULL AFTER m_id;
update tblmember set m_passwd = '1234';
/* 상품 테이블 */
create table tblproduct (
	p_code int not null primary key auto_increment ,
	p_name varchar(444) not null,
	p_price int not null,
	p_rdate datetime not null default sysdate(),
	p_udate datetime not null default sysdate()
);
alter table auto_increment = 1001;

/* 장바구니 main */

create table tblcartmain (
	cm_code int not null primary key auto_increment,
	m_id varchar(50) not null,
	cm_rdate datetime not null default sysdate(),
	cm_udate datetime not null default sysdate(),
	foreign key (m_id) references tblmember(m_id)
);

/* 장바구니 서브 */

create table tblcartsub (
	cs_code int not null primary key auto_increment,
	cm_code int not null,
	p_code int not null,
	cs_cnt int not null, 
	cs_rdate datetime not null default sysdate(),
	cs_udate datetime not null default sysdate(),
	foreign key (cm_code) references tblcartmain(cm_code),
	foreign key (p_code) references tblproduct(p_code)
);

drop table tblcartsub;

create table tblordermain (
	om_code int not null primary key auto_increment,
	m_id varchar(50) not null,
	om_rdate datetime not null default sysdate(),
	om_udate datetime not null default sysdate(),
	foreign key (m_id) references tblmember(m_id)
);

/* 주문 서브 */

create table tblordersub(
	os_code int not null primary key auto_increment,
	om_code int not null,
	p_code int not null,
	os_cnt int not null, 
	os_rdate datetime not null default sysdate(),
	os_udate datetime not null default sysdate(),
	foreign key (om_code) references tblordermain(om_code),
	foreign key (p_code) references tblproduct(p_code) 
);

insert into tblmember(m_id, m_name) values ('tiger', '이순신');
insert into tblmember(m_id, m_name) values ('lion', '홍범');
insert into tblmember(m_id, m_name) values ('mycat', '도돌이');
select * from tblmember;

insert into tblproduct(p_name, p_price) values('냉장고', 300);
insert into tblproduct(p_name, p_price) values('선풍기', 200);
insert into tblproduct(p_name, p_price) values('에어컨', 400);
insert into tblproduct(p_name, p_price) values('장수막걸리', 1300);
insert into tblproduct(p_name, p_price) values('상당산성 막걸리', 1900);
select * from tblproduct;

insert into tblcartmain(m_id) values ('tiger');
insert into tblcartmain(m_id) values ('lion');
select * from tblcartmain;

insert into tblcartsub(cm_code, p_code, cs_cnt) values(1001,1001,1);
insert into tblcartsub(cm_code, p_code, cs_cnt) values(1001,1004,4);
insert into tblcartsub(cm_code, p_code, cs_cnt) values(1001,1005,3);
insert into tblcartsub(cm_code, p_code, cs_cnt) values(1002,1002,1);
insert into tblcartsub(cm_code, p_code, cs_cnt) values(1002,1003,4);
insert into tblcartsub(cm_code, p_code, cs_cnt) values(1002,1005,3);
select * from tblcartsub;


/* tiger의 장바구니 내역 조회 */

select m.m_name, p.p_name, p.p_price, cm.cm_code, cm.m_id, cs.p_code, cs.cs_cnt,
	p.p_price * cs.cs_cnt as subtotal
	from tblcartmain cm, tblcartsub cs, tblproduct p, tblmember as m  
	where cm.cm_code = cs.cm_code 
	and p.p_code = cs.p_code 
	and m.m_id = cm.m_id 
	and cm.m_id = 'tiger';
	
/* tiger의 장바구니 합계 */

select sum(p.p_price * cs.cs_cnt)  Total
	from tblcartmain cm, tblcartsub cs, tblproduct p   
	where cm.cm_code = cs.cm_code 
	and p.p_code = cs.p_code 
	and cm.m_id = 'tiger';

	
/* 장바구니 tiger의 모든 내용을 구매 */
	
insert into tblordermain(m_id) values('tiger');

insert into tblordersub (om_code, p_code, os_cnt) 
	select 1002, p_code, cs_cnt from tblcartsub 
	where cm_code = (select cm_code from tblcartmain where m_id='tiger');
	
/* tiger 가 가장 최근 구매한 내용 */
select om_code from tblordermain where m_id = 'tiger'
	order by om_code desc limit 1;

select om.om_code, om.m_id, os.p_code, os.os_cnt ,
	p.p_price * os.os_cnt as subtotal
	from tblordermain om, tblordersub os, tblproduct p  
	where om.om_code = os.om_code
	and p.p_code = os.p_code 
	and om.om_code = 
	(select om_code from tblordermain where m_id = 'tiger'
	order by om_code desc limit 1);

/* tiger 가 최근에 구매한 금액 총 합계 조회 */
	
select sum(p.p_price * os.os_cnt) total
	from tblordermain om, tblordersub os, tblproduct p  
	where om.om_code = os.om_code
	and p.p_code = os.p_code 
	and om.om_code = 
	(select om_code from tblordermain where m_id = 'tiger'
	order by om_code desc limit 1);
	
/* lion의 장바구니 내역 조회 */

select m.m_name, p.p_name, p.p_price, cm.cm_code, cm.m_id, cs.p_code, cs.cs_cnt,
	p.p_price * cs.cs_cnt as subtotal
	from tblcartmain cm, tblcartsub cs, tblproduct p, tblmember as m  
	where cm.cm_code = cs.cm_code 
	and p.p_code = cs.p_code 
	and m.m_id = cm.m_id 
	and cm.m_id = 'lion';
	
/* lion의 장바구니 합계 */

select sum(p.p_price * cs.cs_cnt)  Total
	from tblcartmain cm, tblcartsub cs, tblproduct p   
	where cm.cm_code = cs.cm_code 
	and p.p_code = cs.p_code 
	and cm.m_id = 'lion';

	
/* 장바구니 lion의 모든 내용을 구매 */
	
insert into tblordermain(m_id) values('lion');

insert into tblordersub (om_code, p_code, os_cnt) 
	select 1001, p_code, cs_cnt from tblcartsub 
	where cm_code = (select cm_code from tblcartmain where m_id='lion');
	
/* lion이 가장 최근 구매한 내용 */
select om_code from tblordermain where m_id = 'lion'
	order by om_code desc limit 1;

select om.om_code, om.m_id, os.p_code, os.os_cnt ,
	p.p_price * os.os_cnt as subtotal
	from tblordermain om, tblordersub os, tblproduct p  
	where om.om_code = os.om_code
	and p.p_code = os.p_code 
	and om.om_code = 
	(select om_code from tblordermain where m_id = 'lion'
	order by om_code desc limit 1);

/* lion이 최근에 구매한 금액 총 합계 조회 */
	
select sum(p.p_price * os.os_cnt) total
	from tblordermain om, tblordersub os, tblproduct p  
	where om.om_code = os.om_code
	and p.p_code = os.p_code 
	and om.om_code = 
	(select om_code from tblordermain where m_id = 'lion'
	order by om_code desc limit 1);

desc tblcartsub;
desc tblcartmain;
	
delete from tblordersub;
delete from tblordermain;
delete from tblcartsub;
delete from tblcartmain;

insert into tblboard(b_subject, b_contents, b_name) 
	select b_subject, b_contents, b_name from tblboard;
insert into tblnotice(n_subject, n_contents, n_name)
	select n_subject, n_contents, n_name from tblnotice;
select count(*) count from tblnotice;	
select count(*) count from tblboard;

select * from tblboard order by b_num desc limit 10 offset 21; //3452