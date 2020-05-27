package com.example.demo.seivice;


import com.example.demo.dao.OrderDao;
import com.example.demo.model.Order;
import com.example.demo.model.Oup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public List<Order> getAllOrders(Long id ){
        if(id ==null) {return this.orderDao.getOrders();}
        else{ return this.orderDao.findOrders(id);}
    }

    public void deleteOrder(long id){this.orderDao.deleteOrder(id);}
    public List<Order> findOrdersByUid(long id){return this.orderDao.findOrdersByUid(id);}
    public void deleteOup(long id){this.orderDao.deleteOup(id);}

    @Transactional
    public void addOrder(Order order, Long uid,Long pid){
        if(order.getId() == null || order.getId() <= 0){
            this.orderDao.insert(order);
        }
        else{
            this.orderDao.update(order);
        }

        if(uid != null && pid !=null){
            Oup oup = new Oup();
            oup.setOid(order.getId());
            oup.setUid(uid);
            oup.setPid(pid);

            this.orderDao.deleteOupByOid(order.getId());
            this.orderDao.insertOup(oup);
        }
    }

    public Order getOrderById(long id){
        return this.orderDao.findOrderById(id);
    }
}
