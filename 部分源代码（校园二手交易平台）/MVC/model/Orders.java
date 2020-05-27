package com.demo.model;

import java.util.List;

public class Orders {
    Long oid;
    Long onum;
    List<User> users;
    List<Books> bookss;
    Long uid;
    Long bid;
    Long id;
    double booksprice;
    Long pay;

    public Long getPay() {
        return pay;
    }

    public void setPay(Long pay) {
        this.pay = pay;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getOnum() {
        return onum;
    }

    public void setOnum(Long onum) {
        this.onum = onum;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Books> getBookss() {
        return bookss;
    }

    public void setBookss(List<Books> bookss) {
        this.bookss = bookss;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBooksprice() {
        return booksprice;
    }

    public void setBooksprice(double booksprice) {
        this.booksprice = booksprice;
    }
}
