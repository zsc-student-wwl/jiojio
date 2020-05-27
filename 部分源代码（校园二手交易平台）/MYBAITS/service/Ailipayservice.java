package com.demo.service;


import com.demo.dao.alipaydao;
import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.enums.BestPayPlatformEnum;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import javafx.animation.PauseTransition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


//修改支付金额：1.AILLservice修改setorder    2.test controller修改传入参数   3.修改方法中的参数  4.orderscontroller中修改传染参数  5.修改方法参数  6.修改前端参数

@Slf4j
@Service
public class Ailipayservice implements alipaydao {
    @Autowired
    private BestPayService bestPayService;
    @Override
    public PayResponse create(String orderid, double amount) {
        PayResponse payResponse=new PayResponse();
        PayRequest payRequest=new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.ALIPAY_PC);      //支付类型为支付宝_PC支付
        payRequest.setOrderId(orderid);                            //设置订单ID为传入的订单ID
        payRequest.setOrderName("WWL的项目，支付金额不是给本人的");                     //支付宝支付页面的支付的订单的名字
       payRequest.setOrderAmount(0.01);       //支付金额为（可以根据数据库的内容传递由于是用到别人的APPID，所以）
     //   payRequest.setOrderAmount(amount);
        payResponse= bestPayService.pay(payRequest);    //
        return  payResponse;
    }

    @Override                          //处理异步通知（微信或者阿里后台要发送给某个网站（公网）告诉他成功了，
    // 如果没有处理，则会一直发送消息给规定的地址）
    public void asyncNotify(String date) {
        PayResponse payResponse= bestPayService.asyncNotify(date);
//        if(payResponse.getPayPlatformEnum()== BestPayPlatformEnum.ALIPAY){
//            return "success";
//        }else if (payResponse.getPayPlatformEnum()==BestPayPlatformEnum.WX){
//            return "<xml>\n"+
//                    " <return_code><![CDATA[SUCCESS]]></return_code>\n"+
//                    " <return_msg><![CDATA[OK]]></return_msg>\n"+
//                    "</xml>";
//        } throw  new RuntimeException("异步通知错误的平台！！");

    }
}
