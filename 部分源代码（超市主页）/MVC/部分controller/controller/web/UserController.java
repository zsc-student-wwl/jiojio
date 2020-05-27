package com.example.demo.web;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.seivice.AuthorityService;
import com.example.demo.seivice.OrderService;
import com.example.demo.seivice.ProductService;
import com.example.demo.seivice.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/userinfo")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/productDetails")
    public String productDetails(@RequestParam("id") long id,Model model,Authentication authentication) {
        try{
            String uName = authentication.getName();
            Product product = this.productService.getProductById(id);
            model.addAttribute("product", product);
            model.addAttribute("uName",uName);
            return "userinfo/productDetails";
        }catch(NullPointerException e){
                return "login";
        }

    }

    @PostMapping("/productDetails")
    public String doproductDetails(Product product, Authentication authentication,Model model){
        if (product.getNum()==null ) {
            model.addAttribute("product", this.productService.getProductById(product.getId()));
            model.addAttribute("errorMsg", "请填写购买数量！");
            model.addAttribute("uName",authentication.getName());
            return "userinfo/productDetails";
        }
        if(this.productService.getProductById(product.getId()).getNum() < product.getNum()){
            model.addAttribute("product", this.productService.getProductById(product.getId()));
            model.addAttribute("errorMsg","商品数量超出库存！");
            model.addAttribute("uName",authentication.getName());
            return  "userinfo/productDetails";
        }
        else{
            Product p= this.productService.getProductById(product.getId());
            long n=p.getNum()-product.getNum();
            p.setNum(n);
            this.productService.updateProduct(p);
            String uu = authentication.getName();
            User user = this.userService.getUserByUsername(uu);
            Order order = new Order();
            order.setOnum(product.getNum());
            this.orderService.addOrder(order, user.getId(), product.getId());
            return "redirect:/userinfo/orderDetails";
        }
    }

    @GetMapping("/myself")
    public String myself(Model model, Authentication authentication) {
        try{
            String uName = authentication.getName();
            User user = this.userService.getUserByUsername(uName);
            model.addAttribute("user",user);
            model.addAttribute("uName",uName);
            return "userinfo/myself";
        }catch(NullPointerException e){
            return "login";
        }
    }

    @GetMapping("/modifyMyself")
    public String modifyMyself(@RequestParam("id") long id, Model model,Authentication authentication,HttpServletRequest request) {
        try{
            String uName = authentication.getName();
            User user = this.userService.getUserById(id);
            model.addAttribute("user", user);
            return "userinfo/modifyMyself";
        }catch(NullPointerException e){
            return "login";
        }
    }

    @PostMapping("/modifyMyself")
    public String doModifyMyself(User user,Model model,HttpServletRequest request) {
        if (user.getUsername().trim().equals("") || user.getPassword().trim().equals("")|| request.getParameter("repassword").trim().equals("")) {
            model.addAttribute("user", this.userService.getUserById(user.getId()));
            model.addAttribute("errorMsg", "用户名或密码不能为空！");
            return "userinfo/modifyMyself";
        }
        if(!user.getPassword().equals(request.getParameter("repassword"))){
            model.addAttribute("user", this.userService.getUserById(user.getId()));
            model.addAttribute("errorMsg", "输入的密码不相同！");
            return "userinfo/modifyMyself";
        }
        if (this.userService.getUserByUsername(user.getUsername()) != null) {
            if (user.getUsername().equals(this.userService.getUserById(user.getId()).getUsername())) {
                user.setAuthorities(this.userService.getUserByUserId(user.getId()).getAuthorities());
                this.userService.registerUser(user);
                return "redirect:/userinfo/myself";
            }else {
                model.addAttribute("errorMsg", "该用户已存在，请重新输入！");
                return "userinfo/modifyMyself";
            }
        } else {
            user.setAuthorities(this.userService.getUserByUserId(user.getId()).getAuthorities());
            userService.registerUser(user);
            return "redirect:/userinfo/myself";
        }
    }

    @GetMapping(value="/orderDetails")
    public String orderDetails(@RequestParam(value="id",required = false,defaultValue = "") Long id, Model model,
                               @RequestParam(value="page",required = false,defaultValue = "1") int page
                        ,Authentication authentication){
        try{
            String uName = authentication.getName();
            long kid = this.userService.getUserByUsername(uName).getId();
            PageHelper.startPage(page, 8);
            PageHelper.orderBy("id desc");
            List<Order> orders = this.orderService.findOrdersByUid(kid);
            PageInfo<Order> pageInfo = new PageInfo<>(orders);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("orders", orders);
            model.addAttribute("uName",uName);
            return "userinfo/orderDetails";
        }catch(NullPointerException e){
            return "login";
        }
    }
}
