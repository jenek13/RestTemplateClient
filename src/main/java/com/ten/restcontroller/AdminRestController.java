package com.ten.restcontroller;

import com.ten.dto.UserDTO;
import com.ten.service.UserDTOService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class AdminRestController {

    private final UserDTOService userDTOService;

    public AdminRestController(UserDTOService userDTOService) {
        this.userDTOService = userDTOService;
    }

    @GetMapping("/adminrest")
    public ResponseEntity<List<UserDTO>> getRestUsers() {
        return ResponseEntity.ok(userDTOService.selectAllUsers());
    }

    @GetMapping(value = {"/admin/edit/{id}"})
    public ResponseEntity<UserDTO> editUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userDTOService.getUserById(id));
    }

    @PostMapping("/admin/create")
    public void postAdd(@RequestBody UserDTO userDTO) {
        UserDTO newUser = new UserDTO();
        newUser.setLogin(userDTO.getLogin());
        newUser.setPassword(userDTO.getPassword());
        newUser.setRole(userDTO.getRole());
        userDTOService.addUser(newUser);
    }

    @PostMapping(value = {"/doUpdate"})
    public void updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userDTOService.getUserById(userDTO.getId());
        updatedUser.setId(userDTO.getId());
        updatedUser.setLogin(userDTO.getLogin());
        updatedUser.setPassword(userDTO.getPassword());
        updatedUser.setRole(userDTO.getRole());
        userDTOService.updateUser(updatedUser);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userDTOService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


