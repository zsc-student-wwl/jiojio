package com.example.demo.dao;


import com.example.demo.model.Authority;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    @Select("select * from User")
    public List<User> getAllUsers();

    @Select("select * from User where username like '%${username}%'")
    public List<User> getUserByUsername(@Param(value="username") String username);

    @Select("select * from User where userName like '%${username}%'")
    @Results({
            @Result(column="Id",property="id"),
            @Result(column="Id",property="authorities",many=@Many(select="com.example.demo.dao.AuthorityDao.findByUserId",fetchType= FetchType.LAZY))
    })
    public List<User> findUsers(@Param(value="username") String username);

    @Select("select * from User where Id=#{id}")
    public User findUserById(long id);

    @Select("select * from User where Username = #{username}")
    @Results({
            @Result(column="Id",property="id"),
            @Result(column="Id",property="authorities",many=@Many(select="com.example.demo.dao.AuthorityDao.findByUserId",fetchType= FetchType.LAZY))
    })
    public User findUserByUsername(String username);

    @Select("select * from User where id = #{id}")
    @Results({
            @Result(column="Id",property="id"),
            @Result(column="Id",property="authorities",many=@Many(select="com.example.demo.dao.AuthorityDao.findByUserId",fetchType= FetchType.LAZY))
    })
    public User findUserByUserId(long id);
    /**
     * 通过user.getId()获取自增长id
     * @param user
     */
    @Insert("insert into User(Username,Password,name,sex) values(#{username},#{password},#{name},#{sex})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public void insert(User user);

    @Update("update User set Username=#{username},Password=#{password},name=#{name},sex=#{sex} where Id=#{id}")
    public void update(User user);

    @Delete("delete from Userauthority where userId=#{id}")
    public void deleteUa(long id);

    @Delete("delete from User where Id=#{id}")
    public void delete(long id);

    @Select("select * from User where Id in (select AuthorityId from UserAuthority where UserId=#{userId})")
    public List<Authority> findByUserId(long uid);

    @Select("select username from User where Id in (select uid from oup where oid=#{id})")
    public List<User> findUsersByUid(long id);

    @Select("select uid from oup where oid = #{oid}")
    public long findUidById(long oid);
}
