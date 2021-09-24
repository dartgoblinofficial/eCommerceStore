package com.example.eCommerceStore.dao;

import com.example.eCommerceStore.pojo.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CartItemDAO extends CrudRepository<CartItem, Integer> {
    List<CartItem> findByUserId(int id);
    List<CartItem> deleteByUserId(int id);


}
