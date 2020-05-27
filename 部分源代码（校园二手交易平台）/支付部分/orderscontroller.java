package com.demo.controller;


import com.demo.model.Books;
import com.demo.model.Orders;
import com.demo.model.User;
import com.demo.service.Booksservice;
import com.demo.service.Orderservice;
import com.demo.service.Userservice;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class orderscontroller {
    @Autowired
    Orderservice orderservice;

    @Autowired
    Userservice userservice;

    @Autowired
    Booksservice booksservice;

    @RequestMapping("admin/listorders")
    public String listordersg( Model model, @RequestParam(value="oid",required = false,defaultValue = "")Long oid, @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                               @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){
        if(pageNum==null || pageNum<=0){  pageNum = 1;  }
        if(pageSize == null){ pageSize = 5;  }
        PageHelper.startPage(pageNum,pageSize);

        try {
            List<Orders>orderss=this.orderservice.getallorders(oid);
     PageInfo<Orders> pageInfo = new PageInfo<Orders>(orderss,pageSize);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("orderss",orderss);
        }finally {
              PageHelper.clearPage();
        }
        return  "admin/listorders";
    }



    @GetMapping("admin/deleteorders")
    public String deleteordersg(@RequestParam(value = "oid") long oid) {


        this.orderservice.deletelianjiebyoid(oid);
        this.orderservice.deleteorderbyoid(oid);
        return "redirect:/admin/listorders";

    }


    @GetMapping("user/ordersget")
   // public String ordersget(@RequestParam(value = "oid") long oid,Model model,@RequestParam(value = "amount") double amount){
       public String ordersget(@RequestParam(value = "oid") long oid,Model model){
        model.addAttribute("lianjie",this.orderservice.getlianjiebyoid(oid));
        if( this.orderservice.getlianjiebyoid(oid) ==null){
            return "index";
        }else {
            this.orderservice.updateorderpaybyoid(oid);
       //    return "redirect:/user/ttt?orderid="+oid+"&&amount="+amount;
     // <!--<a   style="color:red"  th:href="@{'/user/ordersget?oid='+${o.oid}}+'&&amount='+${o.booksprice*o.onum}"  th:if="${o.pay}==1">去支付</a>-->
           return "redirect:/user/ttt?orderid="+oid;
        }

    }



    @GetMapping("user/deleteorders")
    public String deleteorders(@RequestParam(value = "oid") long oid) {

        this.orderservice.deletelianjiebyoid(oid);
        this.orderservice.deleteorderbyoid(oid);
        return "redirect:/user/ordersadd";
    }



    @GetMapping("user/ordersadd")
    public String oredrsaddg( Model model,Authentication authentication) {
        String username= authentication.getName();
        if(username==null) {
            return "loginindex";
        }
        else {
            User user= this.userservice.selectuserbyusername(username);
            List<Orders> orders=   this.orderservice.getordersbyuid(user.getUid());
            model.addAttribute("orders", orders);
            return "user/ordersadd";
        }
    }

    @PostMapping("user/ordersadd")
    public String oredrsaddp(Orders orders, Long uid,Long bid,Model model){

        this.orderservice.addorders(orders,uid,bid);
        Books books=this.booksservice.selectbooksbybid(orders.getBid());
        long n =this.booksservice.selectbooksbybid(orders.getBid()).getBooksnum()-orders.getOnum();
        books.setBooksnum(n);
        this.booksservice.updatebooks(books);
        return "user/ordersadd";
    }
}
