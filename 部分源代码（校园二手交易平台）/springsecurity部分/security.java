package com.demo.config;

import com.demo.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {

    private static final String KEY = "com.demo";

    @Autowired
    Userservice userservice;

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userservice).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable();
                http.authorizeRequests().antMatchers("/index","/loginindex","/ttt").permitAll()                                //允许所有人访问的目录
                                      .antMatchers("/user/**").access("hasAnyRole('ADMIN','USER')")  ;            //只允许特定的人访问的目录
                http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");                                         //只允许admin访问
                http.formLogin().loginPage("/loginindex").successForwardUrl("/");                                                       //允许使用post访问，访问成功之后的页面
                http.logout().logoutSuccessUrl("/index");                                                                               //注销功能成功之后的页面
//                      .and().exceptionHandling().accessDeniedPage("/403");                                                          //没有权限访问会报错
    }
}
