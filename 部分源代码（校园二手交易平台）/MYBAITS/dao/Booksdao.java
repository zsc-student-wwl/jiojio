package com.demo.dao;

import com.demo.model.Books;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;



@Mapper
@Component
public interface Booksdao {

    @Select("select * from books ")
    public List<Books> getallbooks();


    @Select("select * from books where bid=#{bid}")
    public Books selectbooksbybid(Long bid);

    @Insert("insert into Books(booksname,booksurl,bookszuozhe,booksprice,booksnum) " +
            "values(#{booksname},#{booksurl},#{bookszuozhe},#{booksprice},#{booksnum})")
    @Options(useGeneratedKeys=true, keyProperty="bid", keyColumn="bid")
    public void addbooks(Books books);



//    @Update("update books set booksname=#{booksname},booksurl=#{booksurl},bookszuozhe=#{bookszuozhe} ,booksprice=#{booksprice},booksnum=#{booksnum}where bid=#{bid}")
//    public void updatebooks(Books books);


    @Update("update books set booksname=#{booksname},booksurl=#{booksurl},bookszuozhe=#{bookszuozhe} ,booksprice=#{booksprice},booksnum=#{booksnum} where bid=#{bid}")
   public void updatebooks(Books books);




    @Delete("delete   from books where bid=#{bid}")
    public void deuserbybid(Long bid);


    @Select("select booksprice from books where bid=#{bid}")
    public double  selectbookspricebybid(Long bid);


    @Select("select booksname from books where bid=#{bid}")
    public String  selectbooksnamebybid(Long bid);



    @Select("select bid from lianjie where oid = #{oid}")
    public long selectbidbyid(long oid);


    @Select("select booksname from books where bid in (select bid from lianjie where oid=#{oid})")
    public String selectbooksbylid(long oid);

       @Select("select booksprice from books where bid in (select bid from lianjie where oid=#{id})")
   public double selectbookspricebyoid(long oid);
}
