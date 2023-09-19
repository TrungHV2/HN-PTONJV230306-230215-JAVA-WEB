package com.ra.web.repository.impl;

import com.ra.web.model.Customer;
import com.ra.web.repository.CustomerRepository;
import com.ra.web.util.MySqlConnect;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                c.setBirthday(rs.getTimestamp("birthday"));
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
}
