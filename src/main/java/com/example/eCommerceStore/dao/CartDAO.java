package com.example.eCommerceStore.dao;

import com.example.eCommerceStore.pojo.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartDAO extends CrudRepository<Cart, Integer> {
}
