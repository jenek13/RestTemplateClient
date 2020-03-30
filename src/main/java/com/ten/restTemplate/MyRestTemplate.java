package com.ten.restTemplate;

import com.ten.model.Role;
import com.ten.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Repository
@Transactional
public class MyRestTemplate {

    public List<User> selectAllUsers() {
        final String uri = "http://localhost:8081/server/getAllUsers";
        RestTemplate restTemplate = new RestTemplate();
        List<User> result = restTemplate.getForObject(uri, List.class);
        return  result;
        // верно я поняла что в URI должен быть адрес сервера? занчит на сервер приложении нужен еще один рест контроллер
        ////        с методом у которого ендпоинт напрмиер /employees и этот мтеод в котроллере будет дергать USerServiSe а тот UserDao
        //нужно прописать на сервере порт 8081
    }

     public void removeUser(Long id) {
         RestTemplate restTemplate = new RestTemplate();
         String url = "http://localhost:8081/server/delete/{id}";
         restTemplate.delete(url, id);
     }

     public void addUser(User user) {
         RestTemplate restTemplate = new RestTemplate();
         String uri = "http://localhost:8081/server/create";
         restTemplate.postForEntity(uri, user, User.class);
         //restTemplate.postForObject(uri, user,User.class);
     }

    public void updateUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8081/server/doUpdate";
        restTemplate.postForEntity(uri, user, User.class);
        //restTemplate.postForObject(uri, user,User.class);
    }



     public User getUserById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8081/server/getUserById/{id}";
        return restTemplate.getForObject(uri, User.class, id);
     }

    public Role getRoleById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8081/server/getRoleById/{id}";
        return restTemplate.getForObject(uri, Role.class, id);
    }
    //ВОТ метод выше должен быть аналогом страого мтеода в классе дао
//    public Role getById(long id) {
//        return em.find(Role.class, id);
//    }







    public User getUserByLogin(String login) {
        //тут надо назанчить юри с адресом метода в рест окнтрллере на сервере
        //тут нужно вызвать рест контроллер на сервре спец мтеод который примет стринг и будет вызвать метод в дао
        final String uri = "http://localhost:8081/server/getAllUsers";
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(uri, User.class);
        return user;
    }
}
