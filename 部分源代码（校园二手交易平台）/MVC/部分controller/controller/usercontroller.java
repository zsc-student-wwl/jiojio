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

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class usercontroller {


    @Autowired
    Userservice userservice;

    @Autowired
    Booksservice booksservice;

    @Autowired
    Orderservice orderservice;

    //*************************************/admin/add     添加用户 返回/listuser
    @GetMapping(value = "admin/adduser")
    public ModelAndView adduserg(ModelAndView modelAndView, Model model, User user) {
        model.addAttribute("user", user);
        modelAndView.setViewName("admin/adduser");
        return modelAndView;
    }


    @PostMapping(value = "admin/adduser")
    public String adduserp(User user) {
        userservice.insertuser(user);
        return "redirect:/admin/listuser";
    }


    //*********************    /user/list        输出用户列表   返回/listuser
    @GetMapping("admin/listuser")
    public String listuserg(Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        //设置默认每页显示的数据数
        if (pageSize == null) {
            pageSize = 5;
        }
        // System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<User> users = userservice.getalluser();
            //    System.out.println("分页数据："+bookss);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<User> pageInfo = new PageInfo<User>(users, pageSize);
            //4.使用model传参数回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("users", users);
        } finally {

              PageHelper.clearPage();
        }
        return "admin/listuser";
    }


    @PostMapping("admin/listuser")
    public ModelAndView listuserp(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/listuser");
        return modelAndView;
    }



    @GetMapping(value = "admin/deleteuser")
    //,@PathVariable("uid") long uid
    public String deleteuserg(@RequestParam(value = "uid") long uid) {
        this.orderservice.deletelianjiebyuid(uid);
        this.userservice.deuserbyuid(uid);
        return "redirect:admin/listuser";

    }

    //**************************************/admin/edituser     编辑用户，返回/listuser
    @GetMapping(value = "user/edituser")
    public ModelAndView edituserg(ModelAndView modelAndView, @RequestParam(value = "uid", required = false) long uid,
                                  Model model, User user) {
        user = this.userservice.selectuserbyuid(uid);
        model.addAttribute("user", user);
        modelAndView.setViewName("user/edituser");
        return modelAndView;
    }

    @PostMapping(value = "user/edituser")
    public String edituserp(@RequestParam("uid") long uid, User user) {
        this.userservice.updateuser(user);

            return "redirect:/index";

    }




    @RequestMapping(value = "user/editmyuser")
    public  String edituserg( Authentication authentication, Model model, User user,
                              @RequestParam(defaultValue="1",value="pageNum")Integer pageNum,
                              @RequestParam(defaultValue="5",value="pageSize")Integer pageSize) {
        user = this.userservice.selectuserbyusername(authentication.getName());
        model.addAttribute("user", user);
        return "user/editmyuser";
    }


}










