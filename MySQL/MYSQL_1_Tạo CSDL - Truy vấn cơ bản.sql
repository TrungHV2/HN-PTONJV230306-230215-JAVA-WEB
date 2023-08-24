-- Tạo CSDL
CREATE DATABASE new_database;
-- Xóa CSDL
DROP DATABASE new_database;
-- Tạo bảng
use test_db;
CREATE TABLE categories(
	id varchar(36),
    name varchar(250) not null unique,
    status bit(1),
    PRIMARY KEY(id)
);
-- Xóa bảng
DROP TABLE categories;
-- Cập nhật bảng
-- Thêm cột
ALTER TABLE categories
ADD COLUMN description varchar(250) after name;
-- Sửa cột
ALTER TABLE categories
CHANGE COLUMN description mo_ta text;
-- Xóa cột
ALTER TABLE categories
DROP COLUMN mo_ta;

CREATE TABLE products(
	id varchar(36),
    name varchar(250) not null unique,
    price double not null,
    categoryId varchar(36),
    status bit(1),
    primary key(id), -- Khóa chính, xác định một bản ghi ở bảng products là duy nhất
    foreign key(categoryId) references categories(id) -- Khóa ngoại, tham chiếu đến khóa chính bảng categories
);

-- Thêm dữ liệu
INSERT INTO categories VALUES
(UUID(), 'Điện thoại', 1),
(UUID(), 'Laptop', 0),
(UUID(), 'Phụ kiện', 1)
;
INSERT INTO products VALUES
(UUID(), 'Iphone 14', 29000000, '1832b5c8-41bf-11ee-b337-0242ac110002', 1)
;
-- Truy vấn dữ liệu
SELECT 
-- *
p.id, p.name, p.price, c.name as categoryName, 
case 
	when p.status = 1 then 'Hết hàng'
    else 'Còn hàng'
end as status
FROM products p
JOIN categories c ON p.categoryId = c.id
;
SELECT * FROM categories
-- WHERE status = true
-- &&: and
-- ||: or
-- and name like '%thoại%'
ORDER BY name asc
;
-- Cập nhật dữ liệu
UPDATE categories
	SET status = 0
WHERE id = '8c57a7c7-41bc-11ee-b337-0242ac110002';
-- Xóa dữ liệu
DELETE FROM categories
WHERE id = '1832b5c8-41bf-11ee-b337-0242ac110002';
