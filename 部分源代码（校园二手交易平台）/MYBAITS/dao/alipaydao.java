package com.demo.dao;
//1.定义接口，里面有创建方法
//2.实现service类的一些参数的封装
//3.在controller中使用




import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Mapper
@Component
public interface alipaydao {
    PayResponse create(String orderid, double amount);
    //创建支付（订单号，支付金额（设为为0.01））
    public void asyncNotify(String date);
    //支付成功之后的异步回调方法
}
