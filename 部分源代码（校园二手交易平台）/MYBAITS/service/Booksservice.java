package com.demo.service;


import com.demo.dao.Booksdao;
import com.demo.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Booksservice {
    @Autowired
    private Booksdao booksdao;        //	注意此处别调用service类的方法（要使用接口），不然会报错java.lang.StackOverflowError: null

    public List<Books> getallbooks(){return  this.booksdao.getallbooks();}

    public Books selectbooksbybid(Long bid){return  this.booksdao.selectbooksbybid(bid); }

    public void addbooks(Books books){this.booksdao.addbooks(books);}

    public void updatebooks(Books books){this.booksdao.updatebooks(books);}

    public void deuserbybid(long bid){this.booksdao.deuserbybid(bid);}

    public double  selectbookspricebybid(long bid){return this.booksdao.selectbookspricebybid(bid);}

    public String  selectbooksnamebybid(Long bid){return  this.booksdao.selectbooksnamebybid(bid);}
}
