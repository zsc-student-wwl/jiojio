package com.example.demo.seivice;

import com.example.demo.dao.AuthorityDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Authority;
import com.example.demo.model.User;
import com.example.demo.model.UserAuthority;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao dao;

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  this.dao.findUserByUsername(username);
        if(user ==null) throw new UsernameNotFoundException("用户名或者密码错误");

        List<String> roles = new ArrayList<String>();
        for(Authority auth: user.getAuthorities()){
            roles.add(auth.getName());
        }

        org.springframework.security.core.userdetails.User userDetail =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(roles,',')));
        return userDetail;

    }

    public List<User> getUsers(String name){
        if(name==null) name = "";
        return this.dao.findUsers(name);
    }

    public User getUserById(long id){
        return this.dao.findUserById(id);
    }
    public List<User> getUser(){
        return this.dao.getAllUsers();
    }
    public User getUserByUsername(String username){
        return this.dao.findUserByUsername(username);
    }
    public User getUserByUserId(long id){
        return this.dao.findUserByUserId(id);
    }
    public List<User> getUserByUsern(String username){
        return this.dao.getUserByUsername(username);
    }
    public List<User> getAllUsers(String username){
        if(username==null) username = "";
        return this.dao.findUsers(username);
    }

    @Transactional
    public void addUser(User user, Long authorityId){
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        if(user.getId() == null || user.getId() <= 0){
            this.dao.insert(user);
        }
        else{
            this.dao.update(user);
        }

        if(authorityId != null){
            UserAuthority userAuthority = new UserAuthority();
            userAuthority.setUserId(user.getId());
            userAuthority.setAuthorityId(authorityId);

            this.authorityDao.deleteUserAuthorityByUserId(user.getId());
            this.authorityDao.insertUserAuthority(userAuthority);
        }
    }

    public void deleteUser(long id){
        this.dao.delete(id);
    }

    public void deleteUserAuthority(long id){
        this.dao.deleteUa(id);
    }

    @Transactional
    public void registerUser(User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);

        if(user.getId() == null || user.getId() <= 0){
            this.dao.insert(user);
        }
        else{
            this.dao.update(user);
        }

        for(Authority auth:user.getAuthorities()){
            UserAuthority ua = new UserAuthority();
            ua.setUserId(user.getId());
            ua.setAuthorityId(auth.getId());

            this.authorityDao.insertUserAuthority(ua);
        }
    }
}
