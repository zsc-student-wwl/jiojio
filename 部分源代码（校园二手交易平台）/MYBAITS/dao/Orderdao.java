package com.demo.dao;


import com.demo.model.Books;
import com.demo.model.Lianjie;
import com.demo.model.Orders;
import com.demo.service.Booksservice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface Orderdao {

   @Delete("delete from orders where oid=#{oid}")
    public void deleteorderbyoid(Long oid);
   //deleteorderbyoid删除ORDERS 通过OID会报错，要先删除链接
   @Delete("delete from lianjie where id=#{id}")
    public  void deletelianjiebyid(Long id);
   //删除lianjie通过ID（链接的主键需要先删除ORDERS）
   @Delete("delete from lianjie where oid=#{oid}")
    public void deletelianjiebyoid(long oid);
   //直接通过OID来删除链接不需要删除OID（数量的主键还保留）
    @Delete("delete from lianjie where uid=#{uid}")
    public void deletelianjiebyuid(long uid);
    @Delete("delete from lianjie where bid=#{bid}")
    public void deletelianjiebybid(long bid);
    @Insert("insert into orders (onum) values (#{onum})")
    @Options(useGeneratedKeys=true, keyProperty="oid", keyColumn="oid")
    public void insertordersbyoid(Orders orders);
    @Insert("insert into lianjie(uid,bid,oid) values(#{uid},#{bid},#{oid})")
    public void insertlianjiebyoid(Lianjie lianjie);

    @Update("update orders set onum=#{onum} where oid=#{oid}")
    public void updateordersbyoid(Orders orders);
    @Select("select * from orders ")
    @Results({
            @Result(column = "oid",property = "oid"),
 @Result(column = "oid",property = "users",many = @Many(select="com.demo.dao.Userdao.selectuserbyoid",fetchType= FetchType.LAZY)),
 @Result(column = "oid",property = "bookss",many = @Many(select="com.demo.dao.Booksdao.selectbooksbylid",fetchType= FetchType.LAZY)),
 @Result(column = "oid",property = "booksprice",many = @Many(select="com.demo.dao.Booksdao.selectbookspricebyoid",fetchType= FetchType.EAGER))
})
    public  List<Orders> getallorders();
    //获取所有的orders，传参为空   //返回orders  LIST

    //  @Result(column = "oid",property = "users",many = @Many(select="com.demo.dao.Userdao.selectusernamebyuid",fetchType= FetchType.LAZY)),
    //column为数据库字段名字，property为实体类属性名字
    @Select("select * from orders where oid =#{id}")
    @Results({
            @Result(column = "oid",property = "oid"),
            @Result(column = "oid",property = "uid",one = @One(select="com.demo.dao.Userdao.selectuidbyid",fetchType= FetchType.LAZY)),
            @Result(column = "oid",property = "bid",one = @One(select="com.demo.dao.Booksdao.selectbidbyid",fetchType= FetchType.LAZY)),
    })
    public  Orders getordersbyoid(@Param(value = "oid") Long oid);
   //通过oid来获取orders  ,返回OREDERS 实体类     一对一

    @Select("select * from orders where oid in (select oid from lianjie where uid=#{uid})")
    @Results({
            @Result(column = "oid",property = "oid"),
            @Result(column = "oid",property = "users",many = @Many(select="com.demo.dao.Userdao.selectuserbyoid",fetchType= FetchType.LAZY)),
            @Result(column = "oid",property = "bookss",many = @Many(select="com.demo.dao.Booksdao.selectbooksbylid",fetchType= FetchType.LAZY)),
            @Result(column = "oid",property = "booksprice",many = @Many(select="com.demo.dao.Booksdao.selectbookspricebyoid",fetchType= FetchType.EAGER))
    })
    public List<Orders>  getordersbyuid(@Param(value = "uid") Long uid);
    //通过user的uid来获取orders            返回ORDERS  LIST


    @Select("select * from orders where oid like '%${oid}%'")
    @Results({
            @Result(column = "oid",property = "oid"),
            @Result(column = "oid",property = "users",many = @Many(select="com.demo.dao.Userdao.selectuserbyoid",fetchType= FetchType.LAZY)),
            @Result(column = "oid",property = "bookss",many = @Many(select="com.demo.dao.Booksdao.selectbooksbylid",fetchType= FetchType.LAZY)),
            @Result(column = "oid",property = "booksprice",many = @Many(select="com.demo.dao.Booksdao.selectbookspricebyoid",fetchType= FetchType.EAGER))
    })
    public List<Orders> getorderlikeoid(@Param(value = "oid") Long oid);
     //通过像OID来获取orders            返回ORDERS  LIST


    @Update("update lianjie set pay=2 where oid=#{oid}")
    public void  updatelianjiepaybyorder(Orders orders);

    @Select("select * from lianjie where oid=#{oid}")
    public Lianjie getlianjiebyoid(@Param(value = "oid") Long oid);

    @Update("update lianjie set pay=2 where oid=#{oid}")
    public void  updatelianjiepaybylianjie(Lianjie lianjie);

    @Update("update orders set pay=2 where oid=#{oid}")
    public void  updateorderpaybyoid(long oid);

}
