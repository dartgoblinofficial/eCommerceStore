package com.example.eCommerceStore.service;

import com.example.eCommerceStore.dao.UserDAO;
import com.example.eCommerceStore.pojo.Cart;
import com.example.eCommerceStore.pojo.User;
import com.example.eCommerceStore.security.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserSession userSession;

    public List<User> findByEmail(String email){
        return userDAO.findByEmail(email);
    }

    public boolean checkEmail(String email){
        List<User> emailList = userDAO.findByEmail(email);
        if (emailList.size()>0){
            return false;
        }
        return true;
    }

    public boolean register(String email,
                            String username,
                            String password,
                            String password2,
                            String firstName,
                            String lastName,
                            String phone,
                            String address) {
        if (email.isEmpty() || username.isEmpty() || password.isEmpty() ||
                password2.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
                phone.isEmpty() || address.isEmpty()){
            return false;
        }else if (!password.equals(password2)) {
            return false;
        }else {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setAddress(address);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setPw(password);
            userDAO.save(user);

            return true;
        }
    }

    public boolean login(String username, String password) {
        List<User> userList = userDAO.findByUsername(username);

        if (userList.size() == 0 || userList.size() > 1) {
            return false;
        }
        if (userList.size() == 1) {
            User userFound = userList.get(0);
            if (!userFound.getPw().equals(password)) {
                return false;
            } else {
                userSession.setId(userFound.getId());
                return true;
            }
        }
        return false;
    }

}
