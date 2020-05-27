package com.demo.controller;

import com.demo.model.User;
import com.demo.service.Ailipayservice;
import com.demo.service.Userservice;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller

public class testcontroller {
    @Autowired
    Userservice userservice;
    @Autowired
    Ailipayservice ailipayservice;


    @RequestMapping("user/ttt")
//    public ModelAndView tests(User users, Model mode,
//                        @RequestParam(value = "orderid") String orderid,@RequestParam(value = "amount") double amount){
    public ModelAndView tests(User users, Model mode,
                              @RequestParam(value = "orderid") String orderid){
//        users = this.userservice.selectuserbyuid((long)6);
        mode.addAttribute("users",users);


        Map<String,String> map=new HashMap();
     //  PayResponse  payResponse= ailipayservice.create(orderid,amount);
       PayResponse  payResponse= ailipayservice.create(orderid,0.01);
       map.put("body",payResponse.getBody());
        ModelAndView modelAndView=new ModelAndView("/user/orderget",map);
       mode.addAttribute("body",payResponse.getBody());
        return modelAndView;
    }
}
