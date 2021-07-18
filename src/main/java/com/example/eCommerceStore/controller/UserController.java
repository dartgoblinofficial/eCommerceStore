package com.example.eCommerceStore.controller;


import com.example.eCommerceStore.dao.ProductDAO;
import com.example.eCommerceStore.dao.UserDAO;
import com.example.eCommerceStore.pojo.Product;
import com.example.eCommerceStore.pojo.User;
import com.example.eCommerceStore.security.UserSession;
import com.example.eCommerceStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserService userService;
    @Autowired
    UserSession userSession;

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView("dashboard");

        List<User> userList = userDAO.findById(userSession.getId());
        modelAndView.addObject("userName", userList);
        List<Product> productList =
                (List<Product>) productDAO.findAll();
        modelAndView.addObject("listaProduse", productList);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView registerView() {
    return new ModelAndView("register");
    }

    @GetMapping("/registration-form")
    public ModelAndView register(@RequestParam("email") String email,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("password-again") String password2,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("address") String address) {

        ModelAndView modelAndView = new ModelAndView("register");


        //Verificam daca email-ul este deja inregistrat
        if (!userService.checkEmail(email)) {
            modelAndView.addObject("message", "Email deja inregistrat!");
            return modelAndView;
        }
        if (!userService.register(email, username, password, password2, firstName, lastName, phone, address)) {
            modelAndView.addObject("message", "Ceva nu a mers bine!");
            return modelAndView;
        }
        return new ModelAndView("redirect:/login-page");

    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView("login");
        if (!userService.login(username, password)) {
            modelAndView.addObject("message", "username sau parola gresite!");
            return modelAndView;

        }

        if (username==null || password==null){
            modelAndView.addObject("message","Completeaza campurile!");
            return modelAndView;

        }else {
            return new ModelAndView("redirect:/dashboard");
        }

    }
    @GetMapping("/login-page")
    public ModelAndView loginView(){
        return new ModelAndView("login");
    }
}
