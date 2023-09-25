package com.ra.web.controller;

import com.ra.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private List<User> data;

    public HomeController() {
        this.data = new ArrayList<>(Arrays.asList(
                new User("admin1", "0987654321", true),
                new User("admin2", "0987654321", false),
                new User("admin3", "0987654321", true),
                new User("admin4", "0987654321", false),
                new User("admin5", "0987654321", true),
                new User("admin6", "0987654321", false)
        ));
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home/home";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "Page title");
        return "home/about";
    }
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("fullName", "Hoàng Văn Trung");
        model.addAttribute("user", new User("admin", "0987654321", false));
        model.addAttribute("data", data);
        return "home/index";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User("default", "0000000000", true));
        return "home/create";
    }
    @PostMapping("/create")
    public String create(User user, Model model) {
        data.add(user);
        return "redirect:/home";
    }
}
