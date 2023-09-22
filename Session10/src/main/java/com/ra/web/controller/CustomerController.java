package com.ra.web.controller;

import com.ra.web.model.Customer;
import com.ra.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ServletContext context;
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("key", "value");
        modelMap.addAttribute("data", customerService.findAll());
        return "customer/index";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, ModelMap modelMap) {
        modelMap.addAttribute("cus", customerService.findId(id));
       return "customer/edit";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute Customer cus, ModelMap modelMap) {
        // Chỉ định thư mục lưu
        String pathFolder = "/uploads/";
        String path = context.getRealPath("/WEB-INF/") + pathFolder;
        File folder = new File(path);
        if (!folder.exists()) folder.mkdir();

        if (!cus.getImage().isEmpty()) {
            Path fileUploaded = Paths.get(path + cus.getImage().getOriginalFilename());
            try {
                Files.write(fileUploaded, cus.getImage().getBytes());
                cus.setAvatar(pathFolder + cus.getImage().getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customerService.edit(cus);
        return "redirect:/customer";
    }
}
