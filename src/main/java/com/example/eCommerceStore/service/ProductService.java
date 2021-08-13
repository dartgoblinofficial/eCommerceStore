package com.example.eCommerceStore.service;

import com.example.eCommerceStore.dao.ProductDAO;
import com.example.eCommerceStore.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;
    public static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public Product saveProduct(String name, String image, Double price, String description){
        Product product = new Product();
        product.setName(name);
        product.setImage(image);
        product.setPrice(price);
        product.setDescription(description);
        productDAO.save(product);
        log.info("new product created");
        return product;


    }

    public int deleteProductById(int id) {
        productDAO.deleteById(id);
        return id;

    }


    public List<Product> findAll() {
        List<Product> productList = (List<Product>) productDAO.findAll();
        return productList;
    }
}
