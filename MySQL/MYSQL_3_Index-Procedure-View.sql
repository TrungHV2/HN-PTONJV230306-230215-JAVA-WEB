-- Hiển thị số lượng đã bán của các sản phẩm
-- Cập nhật cột total trên bảng orders theo dữ liệu từ bảng orderDetails
CREATE INDEX idx_price_name ON products (price, name);

DROP INDEX idx_price_name ON products;
-- Tạo thủ tục
DELIMITER //
CREATE PROCEDURE PROC_SELECT_PRODUCTS(IN txtSearch varchar(250), OUT total int)
BEGIN
	-- SELECT COUNT(*) INTO total FROM products WHERE name like concat('%', txtSearch, '%');
    SET total = (SELECT COUNT(*) FROM products WHERE name like concat('%', txtSearch, '%'));
    -- Các câu lệnh
END//
DELIMITER ;
-- Xóa thủ tục
DROP PROCEDURE PROC_SELECT_PRODUCTS;
-- Thực thi thủ tục
call PROC_SELECT_PRODUCTS('cơm', @total);
select @total;



-- TẠO VIEW
-- Hiển thị số lượng đã bán của các sản phẩm
CREATE VIEW VW_TOTAL_ORDER
AS
SELECT p.id, p.name, SUM(od.quantity) as total
FROM orderDetails od 
RIGHT JOIN products p ON od.productId = p.id
GROUP BY p.id, p.name
ORDER BY total DESC;
-- Xóa view
DROP VIEW VW_TOTAL_ORDER;
-- Lấy dữ liệu từ view
select * from VW_TOTAL_ORDER;





-- 3.Hiển thị tên và giá của các sản phẩm có giá cao nhất như sau (productName, price)
SELECT name, price
FROM products
WHERE price = (
    SELECT MAX(price) FROM products);
-- 4.Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách đó như sau (cName, pName)
select c.name as cName,
p.name as pName
from orders o 
join customers c on c.id = o.customerId
join orderDetails od on o.id = od.orderId
join products p on p.id = od.productId
group by c.name,p.name;
-- 5.Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào như sau:  (cName)
select c.name
from customers c
left join orders o on c.id = o.customerId
where o.id is null;
-- 6.Hiển thị chi tiết của từng hóa đơn như sau (oId, oDate, odQty, pName, pPrice)
select o.id, o.created, od.quantity, p.name, p.price
from orders o
join orderDetails od on o.id = od.orderId
join products p on p.id = od.productId;
-- 7.Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn 
-- (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. 
-- Giá bán của từng loại được tính = odQTY*pPrice) như sau (oId, oDate, Total)
select o.id,o.created, sum(od.quantity * p.price) as total
from orders o
join orderDetails od on o.id = od.orderId
join products p on p.id = od.productId
group by o.id,o.created;


-- CSDL Cinema
create database ra_cinema;
use ra_cinema;
create table tblPhim(
	PhimID int primary key auto_increment,
    Ten_phim varchar(30),
    Loai_phim varchar(25),
    Thoi_gian int
);
create table tblPhong(
	PhongID int primary key auto_increment,
    Ten_phong varchar(20),
    Trang_thai tinyint
);
create table tblGhe(
	GheID int primary key auto_increment,
    PhongID int,
    So_ghe varchar(10),
    foreign key (PhongID) references tblPhong(PhongID)
);
create table tblVe(
	PhimID int,
    GheID int,
    Ngay_chieu datetime,
    Trang_thai varchar(20),
    foreign key (PhimID) references tblPhim(PhimID),
    foreign key (GheID) references tblGhe(GheID)
);
insert into tblPhim(Ten_phim,Loai_phim,Thoi_gian) values
('Em bé Hà Nội', 'Tâm lý', 90),
('Nhiệm vụ bất khả thi', 'Hành động', 100),
('Dị nhân', 'Viễn tưởng', 90),
('Cuốn theo chiều gió', 'Tình cảm', 120)
;
insert into tblPhong(Ten_phong,Trang_thai) values
('Phòng chiếu 1', 1),
('Phòng chiếu 2', 1),
('Phòng chiếu 3', 0)
;
insert into tblGhe(PhongID,So_ghe) values
(1, 'A3'),
(1, 'B5'),
(2, 'A7'),
(2, 'D1'),
(3, 'T2')
;
insert into tblVe values
(1, 1, '2023/10/20', 'Đã bán'),
(1, 3, '2023/11/20', 'Đã bán'),
(1, 4, '2023/12/23', 'Đã bán'),
(2, 1, '2023/02/14', 'Đã bán'),
(3, 1, '2023/02/14', 'Đã bán'),
(2, 5, '2023/03/08', 'Chưa bán'),
(2, 3, '2023/03/08', 'Chưa bán')
;
-- 2.Hiển thị danh sách các phim (chú ý: danh sách phải được sắp xếp theo trường Thoi_gian)
SELECT * FROM tblPhim order by Thoi_gian desc;
-- 3.Hiển thị Ten_phim có thời gian chiếu dài nhất
SELECT Ten_phim
FROM tblPhim
ORDER BY Thoi_gian DESC
LIMIT 1;
-- 4.Hiển thị Ten_Phim có thời gian chiếu ngắn nhất
SELECT Ten_phim
FROM tblPhim
WHERE Thoi_gian = (
    SELECT MIN(Thoi_gian)
    FROM tblPhim
);
-- 5.Hiển thị danh sách So_Ghe mà bắt đầu bằng chữ ‘A’
SELECT So_ghe
FROM tblGhe
WHERE So_ghe LIKE 'A%';
-- 6.Sửa cột Trang_thai của bảng tblPhong sang kiểu nvarchar(25)
alter table tblPhong
modify column Trang_thai varchar(30);
-- 7.Cập nhật giá trị cột Trang_thai của bảng tblPhong theo các luật sau:
-- Nếu Trang_thai=0 thì gán Trang_thai=’Đang sửa’
-- Nếu Trang_thai=1 thì gán Trang_thai=’Đang sử dụng’
-- Nếu Trang_thai=null thì gán Trang_thai=’Unknow’
UPDATE tblPhong	
SET Trang_thai="đang sửa"	
WHERE Trang_thai='0';	
UPDATE tblPhong	
SET Trang_thai="đang sử dụng"	
WHERE Trang_thai='1';	
UPDATE tblPhong	
SET Trang_thai="Unknow"	
WHERE Trang_thai is null;
select * from tblPhong;
-- 8.Hiển thị danh sách tên phim mà có độ dài >15 và < 25 ký tự
select Ten_phim
from tblPhim
where LENGTH(Ten_phim) > 15 AND LENGTH(Ten_phim) < 25;
-- 9.Hiển thị Ten_Phong và Trang_Thai trong bảng tblPhong trong 1 cột với tiêu đề ‘Trạng thái phòng chiếu’
SELECT concat(Ten_phong, '-', Trang_thai) as 'Trạng thái phòng chiếu' FROM tblPhong;
