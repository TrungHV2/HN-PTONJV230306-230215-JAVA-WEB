package com.ra.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/index")
    public String index() {
        // Xử lý

        // Trả về tên view được hiển thị
        return "home";
    }
    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView view = new ModelAndView("about");

        view.addObject("key", "txtValue");

        return view;
    }
}
