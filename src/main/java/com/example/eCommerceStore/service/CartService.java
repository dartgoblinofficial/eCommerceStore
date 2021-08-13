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
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    UserSession userSession;
    @Autowired
    UserDAO userDAO;
    @Autowired
    CartDAO cartDAO;
    public static final Logger log = LoggerFactory.getLogger(CartService.class);

    public void addToCart(Product product) {

        List<User> userFound = userDAO.findById(userSession.getId());
        //Optional<Cart> cartFound = cartDAO.findById(userFound.get(0).getCart().getId());
            List<Product> productList = new ArrayList<Product>();
            productList.add(product);
            try{
                userFound.get(0).getCart().setProducts(productList);
                int quantity = userFound.get(0).getCart().getQuantity();//a cui cantitate este?
                quantity++;
                userFound.get(0).getCart().setQuantity(quantity);
                userDAO.save(userFound.get(0));
                log.info(product.getName() + " added in the " + userFound.get(0).getUsername() + "'s shopping cart! Qty: " + quantity+", CartID: "+userFound.get(0).getCart().getId()+" , Products: "+userFound.get(0).getCart().getProducts().toString());

            } catch (NullPointerException e){
                log.info("am primit un null pointer");
            }
        }
    }
