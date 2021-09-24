package com.example.eCommerceStore.service;


import com.example.eCommerceStore.dao.CartItemDAO;
import com.example.eCommerceStore.dao.UserDAO;
import com.example.eCommerceStore.pojo.CartItem;
import com.example.eCommerceStore.pojo.Product;
import com.example.eCommerceStore.pojo.User;
import com.example.eCommerceStore.security.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartService {
    @Autowired
    UserSession userSession;
    @Autowired
    UserDAO userDAO;
    @Autowired
    CartItemDAO cartItemDAO;
    public static final Logger log = LoggerFactory.getLogger(CartService.class);

    //verifica daca produsul exista deja in table CartItems
    public boolean itemExists(Product product){
       List<CartItem> cartItemFound = cartItemDAO.findByUserId(userSession.getId());
        for (int i = 0; i < cartItemFound.size() ; i++) {
            if (product.getId().equals(cartItemFound.get(i).getProductId())){
                log.info("produsul cu id-ul "+product.getId()+" exista in cartItems");
                return true;

            }
            return false;
        }
        return false;
    }

    public void addToCart(Product product) {

        List<User> userFound = userDAO.findById(userSession.getId());
        List<Product> productList = new ArrayList<Product>();
        productList.add(product);
            try{
                int quantity=1;
                if (!itemExists(product)){
                    //creates new CartItem
                    CartItem cartItem = new CartItem(userFound.get(0).getCart(),product.getId(), quantity, product.getPrice(), userFound.get(0));
                    cartItemDAO.save(cartItem);

                    log.info(product.getName() + " added in the " + userFound.get(0).getUsername()
                            + "'s shopping cart! Qty: " + quantity+", CartID: "
                            +userFound.get(0).getCart().getId());
                }else {
                    //update existent CartItem quantity in db
                     Optional<CartItem> cartItemsList= cartItemDAO.findById(product.getId());
                     cartItemsList.get().setQuantity(cartItemsList.get().getQuantity()+1);

                    cartItemDAO.save(cartItemsList.get());
                }


            } catch (NullPointerException e){
                log.info("am primit un null pointer");
            }
        }


        public void updateCart(int[] qty){
            //aduce din bd produsele userului logat
            List<CartItem> cartItemList = cartItemDAO.findByUserId(userSession.getId());

            //atribuie fiecarui produs cantitatea primita din Array
            for (int i = 0; i < cartItemList.size(); i++) {
                cartItemList.get(i).setQuantity(qty[i]);
                log.info("update qty for: "+ cartItemList.get(i)+ " new qty: "+ cartItemList.get(i).getQuantity());
                cartItemDAO.save(cartItemList.get(i));

            }

        }
    @Transactional
    public void deleteCart(int id) {
        cartItemDAO.deleteByUserId(id);
    }
}
