package com.example.demo.web;

import com.example.demo.model.Authority;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.seivice.AuthorityService;
import com.example.demo.seivice.ProductService;
import com.example.demo.seivice.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("errorMsg", "登录失败，用户名或者密码错误！");
        return "login";
    }

    @GetMapping(value={"/index","/",""})
    public String mainpage(@RequestParam(value="name",required = false,defaultValue = "") String name, Model model,
                           @RequestParam(value="page",required = false,defaultValue = "1") int page, Authentication authentication){
        try{
            String uName = authentication.getName();
            PageHelper.startPage(page,12);
            List<Product> products= this.productService.getAllProducts(name);
            PageInfo<Product> pageInfo = new PageInfo<>(products);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("products",products);
            model.addAttribute("uName",uName);
            return "index";
        }catch(NullPointerException e){
            PageHelper.startPage(page,12);
            List<Product> products= this.productService.getAllProducts(name);
            PageInfo<Product> pageInfo = new PageInfo<>(products);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("products",products);
            return "index";
        }
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doregister(User user, Model model, HttpServletRequest request){
        if (user.getUsername().trim().equals("") || user.getPassword().trim().equals("")|| request.getParameter("repassword").trim().equals("")) {
            model.addAttribute("errorMsg", "用户名或密码不能为空！");
            return "register";
        }
        if(!user.getPassword().equals(request.getParameter("repassword"))){
            model.addAttribute("errorMsg", "输入的密码不相同！");
            return "register";
        }
        if (this.userService.getUserByUsername(user.getUsername()) != null) {
            model.addAttribute("errorMsg", "该用户已存在，请重新输入！");
            return "register";
        } else {
            List<Authority> authorities = new ArrayList<>();
            authorities.add(authorityService.getAuthorityById(2));
            user.setAuthorities(authorities);
            userService.registerUser(user);
            return "redirect:/login";
        }
    }
}
