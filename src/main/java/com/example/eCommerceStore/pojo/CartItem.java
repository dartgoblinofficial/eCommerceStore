package com.example.eCommerceStore.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private int productId;
    private int quantity;
    private double price;
    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;


    public CartItem(Cart cart, int productId, int quantity, double price, User user) {
        this.cart = cart;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
    }
    public CartItem(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
