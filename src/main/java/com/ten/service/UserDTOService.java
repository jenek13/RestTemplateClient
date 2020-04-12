package com.ten.service;

import com.ten.dto.UserDTO;
import com.ten.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserDTOService {

    private BasicAuthRestTemplate restTemplate = new BasicAuthRestTemplate("admin", "admin");

    @Value("${server.url}")
    private String uri;

    @Value("${path.selectAllUsers}")
    private String selectAllUsers;

    @Value("${path.removeUser}")
    private String removeUser;

    @Value("${path.addUser}")
    private String addUser;

    @Value("${path.updateUser}")
    private String updateUser;

    @Value("${path.getUserById}")
    private String getUserById;

    @Value("${path.getRoleById}")
    private String getRoleById;

    @Value("${path.getUserByLogin}")
    private String getUserByLogin;

    public List<UserDTO> selectAllUsers() {
        String url = uri.concat(selectAllUsers);
        List<UserDTO> result = restTemplate.getForObject(url, List.class);
        return result;
    }

    public void removeUser(Long id) {
        String url = uri.concat(removeUser);
        restTemplate.delete(url, id);
    }

     public void addUser(UserDTO user) {
         String url = uri.concat(addUser);
         restTemplate.postForEntity(url, user, UserDTO.class);
     }

    public void updateUser(UserDTO user) {
        String url = uri.concat(updateUser);
        restTemplate.postForEntity(url, user, UserDTO.class);
    }

     public UserDTO getUserById(long id) {
        String url = uri.concat(getUserById);
        return restTemplate.getForObject(url, UserDTO.class, id);
     }

    public Role getRoleById(Long id) {
        String url = uri.concat(getRoleById);
        return restTemplate.getForObject(url, Role.class, id);
    }

    public UserDTO getUserByLogin(String login) {
        String url = uri.concat(getUserByLogin);
        return restTemplate.getForObject(url, UserDTO.class, login);
    }
}
