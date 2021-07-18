package com.example.eCommerceStore.pojo;


import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String spring;
    private String summer;
    private String autumn;
    private String winter;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpring() {
        return spring;
    }

    public void setSpring(String spring) {
        this.spring = spring;
    }

    public String getSummer() {
        return summer;
    }

    public void setSummer(String summer) {
        this.summer = summer;
    }

    public String getAutumn() {
        return autumn;
    }

    public void setAutumn(String autumn) {
        this.autumn = autumn;
    }

    public String getWinter() {
        return winter;
    }

    public void setWinter(String winter) {
        this.winter = winter;
    }
}
