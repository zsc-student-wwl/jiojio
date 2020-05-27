package com.demo.service;

import com.demo.dao.Userdao;
import com.demo.model.User;

import jdk.nashorn.internal.runtime.options.LoggingOption;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



        @Override
        public UserDetails loadUserByUsername (String s) throws UsernameNotFoundException {
            //从数据库根据用户名获取用户信息
            User userByName = userdao.selectuserbyusername(s);
            //创建一个新的UserDetails对象，最后验证登陆的需要
            UserDetails userDetails = null;
            if (userByName != null) {
                //创建一个集合来存放权限
                Collection<GrantedAuthority> authorities = getAuthorities(userByName);
                //实例化UserDetails对象
                userDetails = new org.springframework.security.core.userdetails.User(s, userByName.getPassword(), true, true, true, true, authorities);
            }
            return userDetails;
        }
        private Collection<GrantedAuthority> getAuthorities (User user){
            List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
            //注意：这里每个权限前面都要加ROLE_。否在最后验证不会通过
            authList.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
            return authList;
        }




    @Transactional
     public void insertuser(User user){

         String  password=new BCryptPasswordEncoder().encode(user.getPassword()) ;
          user.setPassword(password);
         if(user.getUid() == null || user.getUid() <= 0){
             this.userdao.insertuser(user);
         }
         else{
             this.userdao.updateuser(user);
         }
     }

    public List<User> getalluser(){  return  this.userdao.getalluser(); }

    public User selectuserbyusername(String username){

        return  this.userdao.selectuserbyusername(username);}

    public void deuserbyuid(long uid){this.userdao.deuserbyuid(uid);}


    public  User selectuserbyuid( Long uid){ return  this.userdao.selectuserbyuid(uid);}

    public void updateuser(User user){
        String password=new BCryptPasswordEncoder().encode(user.getPassword()) ;
        user.setPassword(password);
        this.userdao.updateuser(user);}

    public  List<User> selectuserlike(String username){return  this.userdao.selectuserlike(username);}

    public String selectusernamebyuid(long uid){return  this.userdao.selectusernamebyuid(uid);}


}
