package com.ten.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.ten.model.Role;
import com.ten.model.User;
import com.ten.service.RoleService;
import com.ten.service.UserService;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = {"/"})
    public String redirectToLoginPage() {
        return "redirect:/login";
    }

    @GetMapping(value = {"/login"})
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        return "admin";
    }

    @GetMapping(value = {"/admin/edit/{id}"})
    public ModelAndView editUser(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("edit");
        //model.addObject("user", userService.selectUser(id));
        return model;
    }


    @GetMapping(value = {"/user"})
    public ModelAndView userPage() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String username = user.getName();
        ModelAndView model = new ModelAndView("user");
        model.addObject("user", user);
        return model;
    }

    @GetMapping(value = "/error")
    public String accessDenied() {
        return "error";
    }

}


