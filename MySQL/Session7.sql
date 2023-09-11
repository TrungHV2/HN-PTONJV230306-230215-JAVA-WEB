-- Thủ tục lấy danh sách danh mục
delimiter //
create procedure sp_categories_select()
begin
	select c1.*, c2.name as parent from categories c1
    left join categories c2 on c1.parentId = c2.id;
end//
delimiter ;
-- Thủ tục lấy danh sách danh mục theo tên
delimiter //
create procedure sp_categories_select_byName(IN txtSearch varchar(250))
begin
	select * from categories where name like concat('%', txtSearch, '%');
end//
delimiter ;
-- Thủ tục lấy danh sách danh mục theo id
delimiter //
create procedure sp_categories_select_byId(IN catId varchar(36))
begin
	select * from categories c where c.id = catId;
end//
delimiter ;
-- Thủ tục thêm mới danh mục
delimiter //
create procedure sp_categories_insert(
	IN catId varchar(36), 
    IN catName varchar(250), 
    IN catParentId varchar(36), 
    IN catStatus bit(1)
)
begin
	insert into categories(id, name, parentId, status) values(catId, catName, catParentId, catStatus);
end//
delimiter ;
-- Thủ tục thêm cập nhật danh mục
delimiter //
create procedure sp_categories_update(
	IN catId varchar(36), 
    IN catName varchar(250), 
    IN catParentId varchar(36), 
    IN catStatus bit(1)
)
begin
	update categories
    set name = catName, parentId = catParentId, status = catStatus
    where id = catId;
end//
delimiter ;
-- Thủ tục thêm xóa danh mục
delimiter //
create procedure sp_categories_delete(
	IN catId varchar(36)
)
begin
	delete from categories where id = catId;
end//
delimiter ;