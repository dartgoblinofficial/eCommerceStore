package com.example.eCommerceStore.controller;


import com.example.eCommerceStore.dao.CartDAO;
import com.example.eCommerceStore.dao.ProductDAO;
import com.example.eCommerceStore.dao.UserDAO;
import com.example.eCommerceStore.pojo.Cart;
import com.example.eCommerceStore.pojo.Product;
import com.example.eCommerceStore.pojo.User;
import com.example.eCommerceStore.security.UserSession;
import com.example.eCommerceStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    UserSession userSession;
    @Autowired
    UserDAO userDAO;
    @Autowired
    CartService cartService;
    @Autowired
    CartDAO cartDAO;
    @Autowired
    ProductDAO productDAO;


    @GetMapping("/cart")
    public ModelAndView cart(){
        ModelAndView modelAndView = new ModelAndView("cart");
        List<User> userFound = userDAO.findById(userSession.getId());
        List<Product> productList = userFound.get(0).getCart().getProducts();
        modelAndView.addObject("shoppingCart", productList);

        return modelAndView;



    }

    @RequestMapping(value = "addToCart/{id}", method = RequestMethod.GET)
    public String addToCart(@PathVariable("id")int id){
        Optional<Product> productList = productDAO.findById(id);
        Product product = productList.get();
        cartService.addToCart(product);


        return "redirect:/cart";
    }


}
