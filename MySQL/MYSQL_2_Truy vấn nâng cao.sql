use test_db;
drop table users;
create table users(
	id varchar(36) primary key,
    username varchar(250) not null unique,
    email varchar(250) not null unique,
    phone varchar(50) null,
    age int,
	created datetime default(convert_tz(now(), '+00:00', '+07:00')),
    check (age >= 18 and age <= 25)
);
select * from users;
insert into users(id, username, email, phone, age) values
(uuid(), 'customer', 'customer@gmail.com', null, 18)
;
-- Lấy ngày giờ hiện tại với múi giờ chỉ định
SELECT convert_tz(now(), @@global.time_zone, '+07:00') as CurrUtcDateAndTime ;
alter table categories
add column parentId varchar(36) after name;
select * from categories;
-- 9e019694-4255-11ee-9256-0242ac110002
-- 9e01a2ed-4255-11ee-9256-0242ac110002
update products set categoryId = null where id = '9e019694-4255-11ee-9256-0242ac110002';
update products set categoryId = null where id = '9e01a2ed-4255-11ee-9256-0242ac110002';
update categories set parentId = '60b443e0-4256-11ee-9256-0242ac110002' 
where id = '07265d9f-424f-11ee-9256-0242ac110002';
update categories set parentId = '60b443e0-4256-11ee-9256-0242ac110002' 
where id = '0b4e4133-424f-11ee-9256-0242ac110002';
-- Truy vấn
-- INNER JOIN
select 
-- count(*)
p.*, c.name as categoryName 
from products p
join categories c on p.categoryId = c.id;

-- LEFT JOIN
select 
-- count(*)
p.*, c.name as categoryName 
from products p
left join categories c on p.categoryId = c.id;
-- RIGHT JOIN
select 
-- count(*)
p.*, c.name as categoryName 
from products p
right join categories c on p.categoryId = c.id;
-- FULL OUTER JOIN
select 
-- count(*)
p.*, c.name as categoryName 
from products p
right join categories c on p.categoryId = c.id
union
select 
-- count(*)
p.*, c.name as categoryName 
from products p
left join categories c on p.categoryId = c.id;
-- SELF JOIN
select 
c1.id, c1.name, 
case
	when c2.name is not null then c2.name
    else 'Danh mục gốc'
end as parent
from categories c1
left join categories c2 on c1.parentId = c2.id;




-- Tạo CSDL Quản lý bán hàng
create table customers(
	id varchar(36) primary key,
    name varchar(250) not null,
    age int not null
);
create table orders(
	id varchar(36) primary key,
    customerId varchar(36),
    foreign key (customerId) references customers(id),
    created datetime default(convert_tz(now(), '+00:00', '+07:00')),
    total double
);
create table orderDetails(
	orderId varchar(36) not null,
    foreign key (orderId) references orders(id),
    productId varchar(36) not null,
    foreign key (productId) references products(id),
    quantity int not null,
    primary key (orderId, productId)
);

insert into customers values
('C001', 'Nguyễn Văn A', 20),
('C002', 'Nguyễn Văn B', 35),
('C003', 'Nguyễn Văn C', 18),
('C004', 'Nguyễn Văn D', 40)
;
insert into orders(id, customerId) values
('HD01', 'C001'),
('HD02', 'C002'),
('HD03', 'C003'),
('HD04', 'C001')
;
insert into orderDetails values
	('HD01', '9e01a599-4255-11ee-9256-0242ac110002', 5),
	('HD01', '9e01a673-4255-11ee-9256-0242ac110002', 3),
	('HD02', '81e41c83-4256-11ee-9256-0242ac110002', 1),
	('HD02', '81e52c58-4256-11ee-9256-0242ac110002', 2),
	('HD02', '81e533c6-4256-11ee-9256-0242ac110002', 1),
	('HD03', '81e411a8-4256-11ee-9256-0242ac110002', 1),
	('HD04', '81e40e05-4256-11ee-9256-0242ac110002', 1),
	('HD04', '81e41b1b-4256-11ee-9256-0242ac110002', 1)
;

-- Thống kê tổng số đơn hàng đã mua của các khách hàng
select 
c.id, c.name, count(o.id) as total
from orders o
right join customers c on o.customerId = c.id
group by c.id, c.name
-- having total >= 2
;

-- Thống kê tổng số tiền hàng đã mua của khách hàng, hiển thị theo tổng tiền giảm dần
select a.customerId, cus.name, sum(a.total) as total
from (select 
o.id, o.customerId, o.created, sum(od.quantity * p.price) as total
from orders o
join orderDetails od on o.id = od.orderId
join products p on od.productId = p.id
group by o.id, o.customerId, o.created
) a
right join customers cus on a.customerId = cus.id
group by a.customerId, cus.name
order by total asc
;
-- Hiển thị số lượng đã bán của các sản phẩm
-- Cập nhật cột total trên bảng orders theo dữ liệu từ bảng orderDetails












