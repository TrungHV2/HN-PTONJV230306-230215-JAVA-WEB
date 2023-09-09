package com.ra.web.service.impl;

import com.ra.web.model.Category;
import com.ra.web.service.CategoryService;
import com.ra.web.util.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public void add(Category category) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // Mở kết nối
            conn = MySqlConnection.open();
            // Tạo câu truy vấn
            ps = conn.prepareStatement("INSERT INTO categories VALUES(?,?,?,?)");
            // Truyền tham số
            ps.setString(1, category.getId());
            ps.setString(2, category.getName());
            ps.setString(3, category.getParentId());
            ps.setBoolean(4, category.isStatus());
            // Thực thi
            int result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                ps.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // Mở kết nối
            conn = MySqlConnection.open();
            // Tạo câu truy vấn
            ps = conn.prepareStatement("SELECT * FROM categories");
            // Thực thi
            ResultSet rs = ps.executeQuery();
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
                ps.close();
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
        PreparedStatement ps = null;
        try {
            // Mở kết nối
            conn = MySqlConnection.open();
            // Tạo câu truy vấn
            ps = conn.prepareStatement("SELECT * FROM categories c WHERE c.name like ?");
                // Truyền tham số
            ps.setString(1, "%"+key+"%");
            // Thực thi
            ResultSet rs = ps.executeQuery();
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
                ps.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return data;
    }


}
