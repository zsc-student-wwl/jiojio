package com.example.demo.model;

import java.util.List;

public class Order {
    private Long id;
    private Long onum;
    private List<User> users;
    private List<Product> products;
    private double prices;
    private Long pid;
    private Long uid;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOnum() {
        return onum;
    }

    public void setOnum(Long onum) {
        this.onum = onum;
    }
}
