package com.example.demo.dao;


import com.example.demo.model.Authority;
import com.example.demo.model.UserAuthority;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AuthorityDao {

    @Select("select * from Authority where Id=#{id}")
    public Authority findById(long id);

    @Delete("delete from UserAuthority where UserId=#{userId}")
    public void deleteUserAuthorityByUserId(long userId);

    @Insert("insert into Authority(Name) values(#{name})")
    public void insert(Authority auth);

    @Insert("insert into UserAuthority(UserId,AuthorityId) values(#{userId},#{authorityId})")
    public void insertUserAuthority(UserAuthority ua);

    @Select("select * from Authority where Id in (select AuthorityId from UserAuthority where UserId=#{userId})")
    public List<Authority> findByUserId(long userId);
}
