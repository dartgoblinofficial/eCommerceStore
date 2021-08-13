package com.example.eCommerceStore.service;

import com.example.eCommerceStore.beans.MailSenderBean;
import com.example.eCommerceStore.dao.UserDAO;
import com.example.eCommerceStore.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    private JavaMailSender emailSender;
    public static final Logger log = LoggerFactory.getLogger(MailService.class);

    public void sendEmail(String newPassword, String emailTo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ghiurgaby@gmail.com");
        message.setTo(emailTo);
        message.setSubject("Test");
        message.setText("Your new password is: " + newPassword);
        emailSender.send(message);
        List<User> user = userDAO.findByEmail(emailTo);
        user.get(0).setPw(newPassword);
        userDAO.save(user.get(0));
        log.info("password changed for user: " + user.get(0).getFirstName());
    }
}
