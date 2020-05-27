package com.example.demo.seivice;

import com.example.demo.dao.ProductDao;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> getProducts(){return this.productDao.getAllProducts();}

    public int getTotal(){return this.productDao.getTotal();}

    public Product getProductById(long id){return this.productDao.getProductById(id);}

    public long getNumById(long id,long num){return this.productDao.getNumById(id);}

    public Product getProductByName(String name){return this.productDao.getProductByName(name);}

    public void addProduct(Product product){this.productDao.add(product);}

    public void updateProduct(Product product){this.productDao.update(product);}

    public void deleteProduct(long id){this.productDao.delete(id);}

    public List<Product> getAllProducts(String name){
        if(name==null) name = "";
        return this.productDao.getProductByname(name);
    }

    public double getPriceById(long id){return this.productDao.getPriceById(id);}
}
