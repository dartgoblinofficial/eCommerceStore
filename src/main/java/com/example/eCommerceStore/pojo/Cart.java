package com.example.eCommerceStore.pojo;


import javax.persistence.*;

@Entity
@Table (name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

}
