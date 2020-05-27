package com.example.demo.dao;

import com.example.demo.model.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductDao {

    @Select("select * from Product")
    public List<Product> getAllProducts();

    @Select("select count(*) from Product")
    public int getTotal();

    @Select("select * from Product where id = #{id}")
    public Product getProductById(long id);

    @Select("select num from Product where id = #{id}")
    public long getNumById(long id);

    @Select("select * from Product where name = #{name}")
    public Product getProductByName(String name);

    @Insert("insert into Product(name,num,price,img) values(#{name},#{num},#{price},#{img})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public void add(Product product);

    @Update("update Product set name = #{name},num =#{num},price =#{price},img =#{img}   where id = #{id}")
    public void update(Product product);

    @Delete("delete from Product where id = #{id}")
    public void delete(long id);

    @Select("select * from Product where name like '%${name}%'")
    public List<Product> getProductByname(@Param(value="name") String name);

    @Select("select name from product where id=#{pid}")
    public String findPnameByPid(long pid);

    @Select("select price from product where id=#{pid}")
    public String findPpriceByPid(long pid);

    @Select("select name from Product where Id in (select pid from oup where oid=#{id})")
    public List<Product> findProductsByPid(long id);

    @Select("select price from Product where Id in (select pid from oup where oid=#{id})")
    public double findPricesByPid(long id);

    @Select("select price from Product where id = #{id}")
    public double getPriceById(long id);

    @Select("select pid from oup where oid = #{oid}")
    public long findPidById(long id);
}
