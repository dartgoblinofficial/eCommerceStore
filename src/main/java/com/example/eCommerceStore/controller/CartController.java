package com.example.eCommerceStore.controller;


import com.example.eCommerceStore.dao.CartDAO;
import com.example.eCommerceStore.pojo.Cart;
import com.example.eCommerceStore.pojo.Product;
import com.example.eCommerceStore.pojo.User;
import com.example.eCommerceStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    User user;


    @GetMapping("/cart")
    public ModelAndView cart(){
        ModelAndView modelAndView = new ModelAndView("cart");
        List<Product> productList = user.getCart().getProducts();
        modelAndView.addObject("shoppingList", productList);

        return modelAndView;
    }


}
