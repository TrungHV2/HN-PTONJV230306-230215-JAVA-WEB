package com.ra.web.service.impl;

import com.ra.web.model.Category;
import com.ra.web.service.CategoryService;
import com.ra.web.util.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceProcImpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        List<Category> data = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        try {
            // Mở kết nối
            conn = MySqlConnection.open();
            // Tạo câu truy vấn
            cs = conn.prepareCall("CALL sp_categories_select()");
            // Thực thi
            ResultSet rs = cs.executeQuery();
            // Xử lý lấy dữ liệu trong ResetSet
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getString("id"));
                category.setName(rs.getString("name"));
                category.setParentId(rs.getString("parentId"));
                category.setStatus(rs.getBoolean("status"));
                category.setParent(rs.getString("parent"));
                data.add(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                cs.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public List<Category> findByName(String key) {
        List<Category> data = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        try {
            // Mở kết nối
            conn = MySqlConnection.open();
            // Tạo câu truy vấn
            cs = conn.prepareCall("CALL sp_categories_select_byName(?)");
            // Truyền tham số
            cs.setString(1, "%"+key+"%");
            // Thực thi
            ResultSet rs = cs.executeQuery();
            // Xử lý lấy dữ liệu trong ResetSet
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getString("id"));
                category.setName(rs.getString("name"));
                category.setParentId(rs.getString("parentId"));
                category.setStatus(rs.getBoolean("status"));
                data.add(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                cs.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public Category findId(String id) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            // Mở kết nối
            conn = MySqlConnection.open();
            // Tạo câu truy vấn
            cs = conn.prepareCall("CALL sp_categories_select_byId(?)");
            // Truyền tham số
            cs.setString(1, id);
            // Thực thi
            ResultSet rs = cs.executeQuery();
            // Xử lý lấy dữ liệu trong ResetSet
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getString("id"));
                category.setName(rs.getString("name"));
                category.setParentId(rs.getString("parentId"));
                category.setStatus(rs.getBoolean("status"));
                return category;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                cs.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void add(Category category) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            // Mở kết nối
            conn = MySqlConnection.open();
            // Tạo câu truy vấn
            cs = conn.prepareCall("CALL sp_categories_insert(?,?,?,?)");
            // Truyền tham số
            cs.setString(1, category.getId());
            cs.setString(2, category.getName());
            cs.setString(3, category.getParentId());
            cs.setBoolean(4, category.isStatus());
            // Thực thi
            int result = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                cs.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void edit(Category category) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            // Mở kết nối
            conn = MySqlConnection.open();
            // Tạo câu truy vấn
            cs = conn.prepareCall("CALL sp_categories_update(?,?,?,?)");
            // Truyền tham số
            cs.setString(1, category.getId());
            cs.setString(2, category.getName());
            cs.setString(3, category.getParentId());
            cs.setBoolean(4, category.isStatus());
            // Thực thi
            int result = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                cs.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
