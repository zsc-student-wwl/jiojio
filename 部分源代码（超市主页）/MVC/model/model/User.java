package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String sex;
    private List<Authority> authorities = new ArrayList<Authority>();

    public User(){
        Authority authority = new Authority();
        authority.setId((long) 1);
        authority.setName("ROLE_USER");
        this.authorities.add(authority);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<Authority>getAuthorities() {
        return this.authorities;
    }
    //获取本用户的匿名名称，留言的时候使用
    public String getAnonymousName(){
        if(null==username)
            return null;

        if(username.length()<=1)
            return "*";

        if(username.length()==2)
            return username.substring(0,1) +"*";

        char[] cs =username.toCharArray();
        for (int i = 1; i < cs.length-1; i++) {
            cs[i]='*';
        }
        return new String(cs);

    }

    @Override
    public  String toString(){
        return this.username;
    }
}
