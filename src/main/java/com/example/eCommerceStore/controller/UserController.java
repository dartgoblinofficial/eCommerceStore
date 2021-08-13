package com.example.eCommerceStore.controller;


import com.example.eCommerceStore.dao.ProductDAO;
import com.example.eCommerceStore.dao.UserDAO;
import com.example.eCommerceStore.pojo.User;
import com.example.eCommerceStore.security.UserSession;
import com.example.eCommerceStore.service.MailService;
import com.example.eCommerceStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    MailService mailService;
    @Autowired
    ProductDAO productDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserService userService;
    @Autowired
    UserSession userSession;
    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView("dashboard");

        List<User> userList = userDAO.findById(userSession.getId());
        modelAndView.addObject("userName", userList);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView registerView() {
    return new ModelAndView("register");
    }

    @RequestMapping(value ="/registration-form", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("email") String email,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("password-again") String password2,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("address") String address) {

        ModelAndView modelAndView = new ModelAndView("register");


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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

    @GetMapping("/login/forgot")
    public ModelAndView modelAndView(){
        return new ModelAndView("forgot");
    }
    @GetMapping("/login/forgot/sendPw")
    public ModelAndView sendSimpleMessage(@RequestParam("emailTo")String to) {
        ModelAndView modelAndView2 = new ModelAndView("forgot");
        if (!userService.checkEmail(to)) {
            String newPassword = userService.generatePassword();
            mailService.sendEmail(newPassword, to);
        }else {
            modelAndView2.addObject("message", "email-ul nu este inregistrat");
            return modelAndView2;
        }
        return new ModelAndView("redirect:/login-page");
    }
}
