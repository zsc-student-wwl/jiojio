package com.demo.dao;


import com.demo.model.Books;
import com.demo.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface Userdao {


    @Insert("insert into User(username,password,phone,role) values(#{username},#{password},#{phone},'USER')")
    @Options(useGeneratedKeys=true, keyProperty="uid", keyColumn="uid")
    public void insertuser(User user);


    @Select("select * from user ")
    public List<User> getalluser();


    @Select("select * from user where username=#{username}")
    public  User selectuserbyusername( String username);


    @Delete("delete   from user where uid=#{uid}")
    public void deuserbyuid(long uid);


    @Select("select * from user where uid=#{uid}")
    public  User selectuserbyuid( Long uid);


    @Update("update user set username=#{username},password=#{password},phone=#{phone} where uid=#{uid}")
    public void updateuser(User user);



    @Select("select * from user where username like '%${username}%'")
    public List<User> selectuserlike(@Param(value="username") String username);



    @Select("select username from user where uid=#{uid}")
    public String selectusernamebyuid(long uid);




    @Select("select uid from lianjie where oid = #{oid}")
    public long selectuidbyid(long oid);




    @Select("select username from user where uid in (select uid from lianjie where oid=#{id})")
    public String selectuserbyoid(long id);

}
