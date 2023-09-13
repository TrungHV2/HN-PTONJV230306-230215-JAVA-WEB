package com.ra.web.service.impl;

import com.ra.web.model.Customer;
import com.ra.web.service.CustomerService;
import com.ra.web.util.MySqlConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<Customer> findAll() {
        List<Customer> data = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnection.open();
            cs = conn.prepareCall("CALL sp_customers_select()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                c.setBirthday(rs.getTimestamp("birthday"));
                c.setAvatar(rs.getString("avatar"));
                data.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public Customer findId(String id) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnection.open();
            cs = conn.prepareCall("CALL sp_customers_select_byId(?)");
            cs.setString(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                c.setBirthday(rs.getTimestamp("birthday"));
                c.setAvatar(rs.getString("avatar"));
                return c;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Customer c) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnection.open();
            cs = conn.prepareCall("CALL sp_customers_select_insert(?,?,?,?,?)");
            cs.registerOutParameter(1, Types.VARCHAR, 36);
            cs.setString(2, c.getName());
            cs.setInt(3, c.getAge());
            cs.setTimestamp(4, c.getBirthday());
            cs.setString(5, c.getAvatar());
            int result = cs.executeUpdate();
            System.out.println("customerId: " + cs.getString(1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(Customer c) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnection.open();
            cs = conn.prepareCall("CALL sp_customers_select_update(?,?,?,?,?)");
            cs.setString(1, c.getId());
            cs.setString(2, c.getName());
            cs.setInt(3, c.getAge());
            cs.setTimestamp(4, c.getBirthday());
            cs.setString(5, c.getAvatar());
            int result = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
