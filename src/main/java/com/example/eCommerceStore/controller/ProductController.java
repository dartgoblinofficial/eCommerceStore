package com.example.eCommerceStore.controller;

import com.example.eCommerceStore.dao.CartDAO;
import com.example.eCommerceStore.dao.ProductDAO;
import com.example.eCommerceStore.dao.UserDAO;
import com.example.eCommerceStore.pojo.Product;
import com.example.eCommerceStore.pojo.User;
import com.example.eCommerceStore.security.UserSession;
import com.example.eCommerceStore.service.CartService;
import com.example.eCommerceStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
@Autowired
    ProductService productService;
@Autowired
    ProductDAO productDAO;
@Autowired
    UserDAO userDAO;
@Autowired
    UserSession userSession;
@Autowired
    CartService cartService;
@Autowired
    CartDAO cartDAO;

    @GetMapping("/shop")
    public ModelAndView items(){
        ModelAndView modelAndView = new ModelAndView("shop");
        List<User> userList = userDAO.findById(userSession.getId());
        modelAndView.addObject("userName", userList);
        List<Product> productList =
                (List<Product>) productDAO.findAll();
        modelAndView.addObject("listaProduse", productList);
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView("admin");
        List<Product> productList = productService.findAll();
        modelAndView.addObject("listaProduse",productList);
        return modelAndView;
    }

    @GetMapping("/admin-page")
    public String addItems(@RequestParam("name")String name,
                          @RequestParam("price")Double price,
                          @RequestParam("image") String img,
                          @RequestParam("description")String description) {

        productService.saveProduct(name, img, price, description);

    return "redirect:/admin";
    }

    @DeleteMapping("/admin-page/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id")int id){
        ModelAndView modelAndView = new ModelAndView("admin");
        productService.deleteProductById(id);

        return modelAndView;

    }


}
