package com.ra.web.repository.impl;

import com.ra.web.model.Customer;
import com.ra.web.repository.CustomerRepository;
import com.ra.web.util.MySqlConnect;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
//@Primary
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnect.open();
            cs = conn.prepareCall("CALL sp_customers_select()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                c.setBirthday(new Date(rs.getTimestamp("birthday").getTime()));
                c.setAvatar(rs.getString("avatar"));
                customers.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                cs.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return customers;
    }

    @Override
    public Customer findId(String id) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnect.open();
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
        } finally {
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
    public void add(Customer cus) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnect.open();
            cs = conn.prepareCall("CALL sp_customers_select_insert(?,?,?,?,?)");
            cs.setString(1, cus.getId());
            cs.setString(2, cus.getName());
            cs.setInt(3, cus.getAge());
            cs.setTimestamp(4, new Timestamp(cus.getBirthday().getTime()));
            cs.setString(5, cus.getAvatar());
            cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                cs.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void edit(Customer cus) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = MySqlConnect.open();
            cs = conn.prepareCall("CALL sp_customers_select_update(?,?,?,?,?)");
            cs.setString(1, cus.getId());
            cs.setString(2, cus.getName());
            cs.setInt(3, cus.getAge());
            cs.setTimestamp(4, new Timestamp(cus.getBirthday().getTime()));
            cs.setString(5, cus.getAvatar());
            cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                cs.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
