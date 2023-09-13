package com.ra.web.controller;

import com.ra.web.model.Customer;
import com.ra.web.service.CustomerService;
import com.ra.web.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "CustomersController", value = "/customers")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50
)
public class CustomersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        CustomerService service = new CustomerServiceImpl();
        if (action == null || action.equals("index")) {
            List<Customer> data = service.findAll();
            req.setAttribute("data", data);
            req.getRequestDispatcher("WEB-INF/views/customers/index.jsp").forward(req, resp);
        }
        if (action != null && action.equals("create")) {
            req.getRequestDispatcher("WEB-INF/views/customers/create.jsp").forward(req, resp);
        }
        if (action != null && action.equals("edit")) {
            String id = req.getParameter("id");
            Customer customer = service.findId(id);
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("WEB-INF/views/customers/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        // Lấy thông tin từ form
        String txtName = req.getParameter("txtName");
        int txtAge = Integer.parseInt(req.getParameter("txtAge"));
        // Xử lý ngày tháng
        Timestamp txtBirthday = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            txtBirthday = new Timestamp(format.parse(req.getParameter("txtBirthday")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Xử lý ảnh
        String txtAvatar = req.getParameter("txtAvatar");
        // Lấy file trong request gửi lên
        Part file = req.getPart("avatar");
        if (file.getSize() > 0) {
            // Bóc tách tên file để lưu trữ
            String fileName = extractFileName(file);
            // Tạo đường dẫn thư mục lưu file
            String path = getServletContext().getRealPath("") + File.separator + "uploads";
            File folder = new File(path);
            if (!folder.exists())
                folder.mkdir();
            // Lưu file vào thư mục
            file.write(path + File.separator + fileName);
            txtAvatar = "..\\uploads" + File.separator + fileName;
        }
        // Tạo đối tượng
        Customer customer = new Customer();
        customer.setName(txtName);
        customer.setAge(txtAge);
        customer.setBirthday(txtBirthday);
        customer.setAvatar(txtAvatar);
        // Xủ lý dữ liệu
        CustomerService service = new CustomerServiceImpl();
        if (action != null && action.equals("edit")) {
            String txtId = req.getParameter("txtId");
            customer.setId(txtId);
            service.edit(customer);
        }
        if (action != null && action.equals("create")) {
            service.add(customer);
        }
        resp.sendRedirect("/customers");
    }

    private String extractFileName(Part part) {
        String content = part.getHeader("content-disposition");
        String[] items = content.split(";");
        for (String s : items) {
            //filename="abc.jpg"
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
