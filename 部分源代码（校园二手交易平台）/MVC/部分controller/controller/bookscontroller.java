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
public class bookscontroller {

    @Autowired
    Booksservice booksservice;


    @Autowired
    Orderservice orderservice;

    @Autowired
    Userservice userservice;



    @RequestMapping(value={"/index","/",""})
    public String indexg( Model model,@RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                          @RequestParam(defaultValue="2",value="pageSize")Integer pageSize){
        if(pageNum==null || pageNum<=0){
            pageNum = 1;
        }
        //设置默认每页显示的数据数
        if(pageSize == null){
            pageSize = 1;
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum,pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Books> bookss=booksservice.getallbooks();
            //    System.out.println("分页数据："+bookss);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Books> pageInfo = new PageInfo<Books>(bookss,pageSize);
            //4.使用model传参数回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("bookss",bookss);
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            //  PageHelper.clearPage();
        }
        return  "index";
    }

    @GetMapping(value="admin/listbooks")
    public String listbooksg( Model model,@RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                              @RequestParam(defaultValue="3",value="pageSize")Integer pageSize){
        if(pageNum==null || pageNum<=0){
            pageNum = 1;
        }
        //设置默认每页显示的数据数
        if(pageSize == null){
            pageSize = 1;
        }
        // System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum,pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Books> bookss=booksservice.getallbooks();
            //    System.out.println("分页数据："+bookss);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Books> pageInfo = new PageInfo<Books>(bookss,pageSize);
            //4.使用model传参数回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("bookss",bookss);
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            //  PageHelper.clearPage();
        }
        return  "admin/listbooks";
    }


    @GetMapping(value = "admin/addbooks")
    public String addbooksg(ModelAndView modelAndView,Books books,Model model){


        model.addAttribute("books",books);
    //    modelAndView.setViewName("/admin/addbooks");
        return  "admin/addbooks";

    }
    @PostMapping(value = "admin/addbooks")
    public String addbooksp(Books books){
        this.booksservice.addbooks(books);
        return "redirect:/admin/listbooks";
    }


    //**************************************/admin/edituser     编辑BOOK，返回/listBooks
    @GetMapping(value = "admin/editbooks")
    public  ModelAndView edituserg(ModelAndView modelAndView,@RequestParam(value = "bid",required = false)long bid,
                                   Model model, Books books)
    {
        books=this.booksservice.selectbooksbybid(bid);
        model.addAttribute("books",books);
        modelAndView.setViewName("admin/editbooks");
        return  modelAndView;
    }

    @PostMapping(value = "admin/editbooks")
    public  String edituserp(@RequestParam("bid")long bid,Books books) {
        this.booksservice.updatebooks(books);
        return  "redirect:/admin/listbooks";
    }



    @GetMapping(value = "admin/deletebooks")
    //,@PathVariable("uid") long uid
    public String deletebooksg(@RequestParam(value = "bid" )long bid){
        this.orderservice.deletelianjiebybid(bid);
        this.booksservice.deuserbybid(bid);
        return  "redirect:/admin/listbooks";
    }


    @GetMapping("user/bookssadd")
    public String bookssaddg(@RequestParam("bid") long bid, Model model) {

        Books books = this.booksservice.selectbooksbybid(bid);
        model.addAttribute("books", books);
        return "user/bookssadd";
    }

    @PostMapping("user/bookssadd")
    public String bookssaddp(Books books, HttpServletRequest request, Model model, Authentication authentication) {{
        if (books.getBooksnum() == null) {
            model.addAttribute("books", this.booksservice.selectbooksbybid(books.getBid()));
            model.addAttribute("errorMsg", "请填写购买数量！");
            return "user/bookssadd";
        }
        if (this.booksservice.selectbooksbybid(books.getBid()).getBooksnum() < books.getBooksnum()) {
            model.addAttribute("books", this.booksservice.selectbooksbybid(books.getBid()));
            model.addAttribute("errorMsg", "商品数量超出库存！");
            return "user/bookssadd";
        } else {
            Books book1 = this.booksservice.selectbooksbybid(books.getBid());
            long n = book1.getBooksnum() - books.getBooksnum();
            System.out.println(n);
            book1.setBooksnum(n);
            this.booksservice.updatebooks(book1);
            String uu = authentication.getName();
            User user = this.userservice.selectuserbyusername(uu);
            Orders order = new Orders();
            order.setOnum(books.getBooksnum());
            this.orderservice.addorders(order, user.getUid(), book1.getBid());
            //   System.out.println("SSSSS:::::" + user.getUid());
            return "redirect:/user/ordersadd";
        }
    }
    }

}
