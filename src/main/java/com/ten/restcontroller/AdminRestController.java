package com.ten.restcontroller;

import com.ten.dto.UserDTO;
import com.ten.model.Role;
import com.ten.model.User;
import com.ten.service.RoleService;
import com.ten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/rest")
public class AdminRestController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminRestController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    /*@GetMapping(value = {"/"})
    public String redirectToLoginPage() {
        return "redirect:/login";
    }

    @GetMapping(value = {"/login"})
    public String showLoginPage() {
        return "login";
    }*/

    @GetMapping("/adminrest")
    public ResponseEntity<List<User>> getRestUsers() {
        return ResponseEntity.ok(userService.listUser());
    }


    @GetMapping(value = {"/admin/edit/{id}"})
    public ResponseEntity<User> editUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.selectUser(id));
    }


    @PostMapping("/admin/create")
    public void postAdd(@RequestBody UserDTO userDTO) {
        User newUser = new User();
        newUser.setLogin(userDTO.getLogin());
        newUser.setPassword(userDTO.getPassword());
        newUser.setRole(userDTO.getRole());
        //newUser.setRoles(getRolesbyID(userDTO.getRole()));
        userService.insertUser(newUser);
    }

    @PostMapping(value = {"/doUpdate"})
    public void updateUser(@RequestBody UserDTO userDTO) {
        //Long id = userDTO.getRole();
        User updatedUser = userService.selectUser(userDTO.getId());//не работаеть юзер гет айди продебжаить в зхроме почему гет айди идет по другим юзерам тоже
        updatedUser.setId(userDTO.getId());
        updatedUser.setLogin(userDTO.getLogin());
        updatedUser.setPassword(userDTO.getPassword());
        updatedUser.setRole(userDTO.getRole());
        userService.updateUser(updatedUser);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }








//
//    @GetMapping(value = {"/user"})
//    public ModelAndView userPage() {
//        Authentication user = SecurityContextHolder.getContext().getAuthentication();
//        String username = user.getName();
//        ModelAndView model = new ModelAndView("user");
//        model.addObject("user", user);
//        return model;
//    }
//
//


//    @GetMapping(value = "/error")
//    public String accessDenied() {
//        return "error";
//    }


}


