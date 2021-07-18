package com.example.eCommerceStore.dao;

import com.example.eCommerceStore.pojo.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {

}
