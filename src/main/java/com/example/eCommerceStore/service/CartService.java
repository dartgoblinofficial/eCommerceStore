package com.example.eCommerceStore.service;


import com.example.eCommerceStore.dao.CartDAO;
import com.example.eCommerceStore.dao.ProductDAO;
import com.example.eCommerceStore.dao.UserDAO;
import com.example.eCommerceStore.pojo.Cart;
import com.example.eCommerceStore.pojo.Product;
import com.example.eCommerceStore.pojo.User;
import com.example.eCommerceStore.security.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    UserSession userSession;
    @Autowired
    UserDAO userDAO;
    public static final Logger log = LoggerFactory.getLogger(CartService.class);

    public Cart addToCart(Product product){
        Cart cart = new Cart();
        List<User> userFound = userDAO.findById(userSession.getId());
        userFound.get(0).setCart(cart);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        cart.setProducts(productList);
        log.info("product added in the "+userFound.get(0).getUsername()+"'s shopping cart!");
        return cart;
    }
}
