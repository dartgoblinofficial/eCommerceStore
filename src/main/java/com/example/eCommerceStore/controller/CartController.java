package com.example.eCommerceStore.controller;


import com.example.eCommerceStore.dao.CartItemDAO;
import com.example.eCommerceStore.dao.ProductDAO;
import com.example.eCommerceStore.dao.UserDAO;
import com.example.eCommerceStore.pojo.CartItem;
import com.example.eCommerceStore.pojo.Product;
import com.example.eCommerceStore.security.UserSession;
import com.example.eCommerceStore.service.CartService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class CartController {

    @Autowired
    UserSession userSession;
    @Autowired
    UserDAO userDAO;
    @Autowired
    CartService cartService;
    @Autowired
    CartItemDAO cartItemDAO;
    @Autowired
    ProductDAO productDAO;

    //Afisarea produselor din cos
    @GetMapping("/cart")
    public ModelAndView cart(){
        ModelAndView modelAndView = new ModelAndView("cart");
        List<CartItem> cartItems = (List<CartItem>) cartItemDAO.findByUserId(userSession.getId());
        List<Product> productList = new ArrayList<>();
        double totalSum=0;

        //calculeaza pretul total
        for (int i = 0; i<cartItems.size(); i++){
           Optional<Product> productFound = productDAO.findById(cartItems.get(i).getProductId());
           totalSum+=productFound.get().getPrice()*cartItems.get(i).getQuantity();
           productList.add(productFound.get());

        }
        //removes duplicates from the list
        Set<Product> set = new HashSet<Product>(productList);
        productList.clear();
        productList.addAll(set);



        modelAndView.addObject("sum", totalSum);
        modelAndView.addObject("shoppingCart", productList);
        modelAndView.addObject("cartItems", cartItems);
        return modelAndView;



    }

    @RequestMapping(value = "addToCart/{id}", method = RequestMethod.GET)
    public String addToCart(@PathVariable("id")int id){
        Optional<Product> productList = productDAO.findById(id);
        Product product = productList.get();
        cartService.addToCart(product);
        return "redirect:/shop";
    }

    @GetMapping("update")
    @NotNull
    public String updateCart(@RequestParam("qtys") int[] values){

        cartService.updateCart(values);
        return "redirect:/cart";
    }

    @GetMapping("finish")
    public String finishOrder(){
        cartService.deleteCart(userSession.getId());
        return "redirect:/dashboard";
    }


}
