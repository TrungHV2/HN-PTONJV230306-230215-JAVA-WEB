package com.ra.web.controller;

import com.ra.web.model.Customer;
import com.ra.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = {"/index", ""})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("home/index");
        // Add các giá trị cần truyền ra view nếu có
        List<Customer> data = customerService.findAll();
        view.addObject("data", data);
        view.addObject("fullName", "Hoàng Văn Trung");
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable String id) {
        ModelAndView view = new ModelAndView("home/edit");
        view.addObject("id", id);
        view.addObject("customer", new Customer());
        return view;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute Customer customer) {
        ModelAndView view = new ModelAndView("home/result");
        customer.setId(UUID.randomUUID().toString());
        view.addObject("customer", customer);
        return view;
    }
}
