package com.example.eCommerceStore.dao;

import com.example.eCommerceStore.pojo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO extends CrudRepository<User, Integer> {


    List<User> findByEmail(String email);
    List<User> findByUsername(String username);
    List<User> findById(int id);
}
