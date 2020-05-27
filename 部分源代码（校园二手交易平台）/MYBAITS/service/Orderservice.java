package com.demo.service;

import com.demo.dao.Orderdao;
import com.demo.model.Lianjie;
import com.demo.model.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Orderservice {
    @Autowired
    Orderdao orderdao;
    public List<Orders> getallorders(Long oid ){
        if(oid ==null) {return this.orderdao.getallorders();}
        else{ return this.orderdao.getorderlikeoid(oid);}
    }
    public void deleteorderbyoid(long oid){this.orderdao.deleteorderbyoid(oid);}
    public List<Orders> getordersbyuid(long uid){return this.orderdao.getordersbyuid(uid);}
    public void deletelianjiebyoid(long oid){this.orderdao.deletelianjiebyoid(oid);}
    @Transactional
    public void addorders(Orders orders, Long uid,Long bid) {
        if(orders.getOid()== null || orders.getOid() <= 0){
            this.orderdao.insertordersbyoid(orders);
        }
        else{
           this.orderdao.updateordersbyoid(orders);
        }
        if(uid != null && bid !=null){
           Lianjie lianjie=new Lianjie();
            lianjie.setOid(orders.getOid());
            lianjie.setBid(bid);
            lianjie.setUid(uid);
            this.orderdao.deletelianjiebyoid(orders.getOid());
           // this.orderDao.deleteOupByOid(order.getId());
            this.orderdao.insertlianjiebyoid(lianjie);
        }
    }

    public Orders getordersbyoid(long oid){
        return  this.orderdao.getordersbyoid(oid);

    }


    public void deletelianjiebyuid(long uid){ this.orderdao.deletelianjiebyuid(uid);}

    public  void deletelianjiebyid(Long id){this.orderdao.deletelianjiebyid(id);}

    public void deletelianjiebybid(long bid){this.orderdao.deletelianjiebybid(bid);}


     public  void  updatelianjiepaybyorder(Orders orders){this.orderdao.updatelianjiepaybyorder(orders);}

    public Lianjie getlianjiebyoid(@Param(value = "oid") Long oid){return  this.orderdao.getlianjiebyoid(oid);}

    public void  updatelianjiepaybylianjie(Lianjie lianjie){this.orderdao.updatelianjiepaybylianjie(lianjie);}

    public void  updateorderpaybyoid(long oid){this.orderdao.updateorderpaybyoid(oid);}


}
