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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = {"/listUsers", "", "/"})
    public String listUsers(@RequestParam(value = "username", required = false, defaultValue = "") String username,
                            Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        PageHelper.startPage(page, 8);
        List<User> users = this.userService.getAllUsers(username);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("users", users);
        return "admins/listUsers";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "admins/addUser";
    }

    @PostMapping("/addUser")
    public String doAddUser(User user, Long authorityId, Model model) {
        if (user.getUsername().trim().equals("") || user.getPassword().trim().equals("")) {
            model.addAttribute("errorMsg", "用户名或密码不能为空！");
            return "admins/addUser";
        }
        if (this.userService.getUserByUsername(user.getUsername()) != null) {
            model.addAttribute("errorMsg", "该用户已存在，请重新输入！");
            return "admins/addUser";
        } else {
            this.userService.addUser(user, authorityId);
            return "redirect:/admins/listUsers";
        }
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") long id) {
        this.userService.deleteUserAuthority(id);
        this.userService.deleteUser(id);
        return "redirect:/admins/listUsers";
    }

    @GetMapping("/modifyUser")
    public String modifyUser(@RequestParam("id") long id, Model model) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admins/modifyUser";
    }

    @PostMapping("/modifyUser")
    public String doModifyUser(User user, Long authorityId, Model model) {
        if (user.getUsername().trim().equals("") || user.getPassword().trim().equals("")) {
            model.addAttribute("errorMsg", "用户名或密码不能为空！");
            return "admins/modifyUser";
        }
        if (this.userService.getUserByUsername(user.getUsername()) != null) {
            if (user.getUsername().equals(this.userService.getUserById(user.getId()).getUsername())) {
                this.userService.addUser(user, authorityId);
                return "redirect:/admins/listUsers";
            }else {
                model.addAttribute("errorMsg", "该用户已存在，请重新输入！");
                return "admins/modifyUser";
            }
        }
         else {
            this.userService.addUser(user, authorityId);
            return "redirect:/admins/listUsers";
        }
    }

    @GetMapping(value="/listProducts")
    public String listProducts(@RequestParam(value="name",required = false,defaultValue = "") String name,Model model, @RequestParam(value="page",required = false,defaultValue = "1") int page){
        PageHelper.startPage(page,8);
        List<Product> products= this.productService.getAllProducts(name);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("products",products);
        return "admins/listProducts";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        model.addAttribute("product",new Product());
        return "admins/addProduct";
    }

    @PostMapping("/addProduct")
    public String doAddProduct(Product product, HttpServletRequest request,Model model){
        if(product.getName().trim().equals("") || product.getNum()==null ||  product.getPrice()==null){
            model.addAttribute("errorMsg","输入不能为空，请重新输入！");
            return  "admins/addProduct";
        }
        if(this.productService. getProductByName(product.getName())!=null){
            model.addAttribute("errorMsg","该商品已存在，请重新输入！");
            return  "admins/addProduct";
        }
        else {
            this.productService.addProduct(product);
            return "redirect:/admins/listProducts";
        }
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") long id){
        this.productService.deleteProduct(id);
        return "redirect:/admins/listProducts";
    }

    @GetMapping("/modifyProduct")
    public String modifyProduct(@RequestParam("id") long id,Model model){
        Product product = this.productService.getProductById(id);
        model.addAttribute("product",product);
        return "admins/modifyProduct";
    }

    @PostMapping("/modifyProduct")
    public String doModifyProduct(Product product, HttpServletRequest request,Model model){
        if(product.getName().trim().equals("") || product.getNum()==null ||  product.getPrice()==null){
            model.addAttribute("errorMsg","输入不能为空，请重新输入！");
            return  "admins/modifyProduct";
        }
        else {
            this.productService.updateProduct(product);
            return "redirect:/admins/listProducts";
        }
    }

    @GetMapping(value="/listOrders")
    public String listOrders(@RequestParam(value="id",required = false,defaultValue = "") Long id, Model model, @RequestParam(value="page",required = false,defaultValue = "1") int page){
        PageHelper.startPage(page,8);
        PageHelper.orderBy("id desc");
        List<Order> orders= this.orderService.getAllOrders(id);

        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("orders",orders);
        return "admins/listOrders";
    }

    @GetMapping("/deleteOrder")
    public String deleteOrder(@RequestParam("id") long id){
        this.orderService.deleteOup(id);
        this.orderService.deleteOrder(id);
        return "redirect:/admins/listOrders";
    }

    @GetMapping("/addOrder")
    public String addOrder(Model model){
        model.addAttribute("order",new Order());
        return "admins/addOrder";
    }

    @PostMapping("/addOrder")
    public String doAddOrder(Order order, Long uid,Long pid,Model model){
        if(order.getUid()==null || order.getPid()==null ||  order.getOnum()==null){
            model.addAttribute("errorMsg","输入不能为空，请重新输入！");
            return  "admins/addOrder";
        }
        if(this.userService.getUserById(order.getUid())==null || this.productService.getProductById(order.getPid()) ==null){
            model.addAttribute("errorMsg","该ID不存在，请查询后再输入！");
            return  "admins/addOrder";
        }
        if(this.productService.getProductById(order.getPid()).getNum() < order.getOnum()){
            model.addAttribute("errorMsg","商品数量超出库存！");
            return  "admins/addOrder";
        }
        else {
            this.orderService.addOrder(order, uid, pid);
            Product product= this.productService.getProductById(order.getPid());
            long n=this.productService.getProductById(order.getPid()).getNum()-order.getOnum();
            System.out.println(n);
            product.setNum(n);
            this.productService.updateProduct(product);
            return "redirect:/admins/listOrders";
        }
    }

    @GetMapping("/modifyOrder")
    public String modifyOrder(@RequestParam("id") long id,Model model){
        Order order = this.orderService.getOrderById(id);
        model.addAttribute("order",order);
        long fnum = this.orderService.getOrderById(id).getOnum();
        model.addAttribute("fnum",fnum);
        return "admins/modifyOrder";
    }

    @PostMapping("/modifyOrder")
    public String doModifyOrder(Order order, Long uid, Long pid, Model model,HttpServletRequest request){
        if(order.getUid()==null || order.getPid()==null ||  order.getOnum()==null){
            model.addAttribute("errorMsg","输入不能为空，请重新输入！");
            return  "admins/addOrder";
        }
        if(this.userService.getUserById(order.getUid())==null || this.productService.getProductById(order.getPid()) ==null){
            model.addAttribute("errorMsg","该ID不存在，请查询后再输入！");
            return  "admins/addOrder";
        }
        if(this.productService.getProductById(order.getPid()).getNum() < order.getOnum()){
            model.addAttribute("errorMsg","输入商品数量已超出库存！");
            return  "admins/addOrder";
        }
        else {
            this.orderService.addOrder(order, uid, pid);
            Product product= this.productService.getProductById(order.getPid());
            long n=this.productService.getProductById(order.getPid()).getNum()-order.getOnum()+Long.parseLong(request.getParameter("fnum"));
            product.setNum(n);
            this.productService.updateProduct(product);
            return "redirect:/admins/listOrders";

        }
    }
}
