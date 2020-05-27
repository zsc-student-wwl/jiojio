package com.example.demo.dao;

import com.example.demo.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OrderDao {
    @Select("select * from order_ where id like '%${id}%'")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "users",many = @Many(select="com.example.demo.dao.UserDao.findUsersByUid",fetchType= FetchType.LAZY)),
            @Result(column = "id",property = "prices",many = @Many(select="com.example.demo.dao.ProductDao.findPricesByPid",fetchType= FetchType.LAZY)),
            @Result(column = "id",property = "products",many = @Many(select="com.example.demo.dao.ProductDao.findProductsByPid",fetchType= FetchType.LAZY)),

    })
    public List<Order> findOrders(@Param(value = "id") long id);

    @Delete("delete from order_ where id = #{id}")
    public void deleteOrder(long id);

    @Delete("delete from oup where oid = #{oid}")
    public void deleteOup(long oid);

    @Insert("insert into order_ (onum) values (#{onum})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public void insert(Order order);

    @Update("update order_ set onum=#{onum} where Id=#{id}")
    public void update(Order order);

    @Delete("delete from oup where oid=#{oid}")
    public void deleteOupByOid(long oid);

    @Insert("insert into oup(uid,pid,oid) values(#{uid},#{pid},#{oid})")
    public void insertOup(Oup oup);

    @Select("select * from order_")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "users",many = @Many(select="com.example.demo.dao.UserDao.findUsersByUid",fetchType= FetchType.LAZY)),
            @Result(column = "id",property = "prices",many = @Many(select="com.example.demo.dao.ProductDao.findPricesByPid",fetchType= FetchType.LAZY)),
            @Result(column = "id",property = "products",many = @Many(select="com.example.demo.dao.ProductDao.findProductsByPid",fetchType= FetchType.LAZY))
    })
    public List<Order> getOrders();

    @Select("select * from order_ where Id=#{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "uid",one=@One(select = "com.example.demo.dao.UserDao.findUidById",fetchType= FetchType.LAZY)),
            @Result(column = "id",property = "pid",one = @One(select="com.example.demo.dao.ProductDao.findPidById",fetchType= FetchType.LAZY))
    })
    public Order findOrderById(long id);

    @Select("select * from order_ where Id in (select oid from oup where uid=#{uid})")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "users",many = @Many(select="com.example.demo.dao.UserDao.findUsersByUid",fetchType= FetchType.LAZY)),
            @Result(column = "id",property = "prices",many = @Many(select="com.example.demo.dao.ProductDao.findPricesByPid",fetchType= FetchType.LAZY)),
            @Result(column = "id",property = "products",many = @Many(select="com.example.demo.dao.ProductDao.findProductsByPid",fetchType= FetchType.LAZY)),

    })
    public List<Order> findOrdersByUid(@Param(value = "uid") long uid);
}
