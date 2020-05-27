package com.demo.config;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class payconfig {
    @Bean
public BestPayService bestPayService(){
    AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAppId("*****");
        aliPayConfig.setPrivateKey("******" );
        aliPayConfig.setAliPayPublicKey("*****");
        aliPayConfig.setReturnUrl("http://120.24.70.157:6666/user/ordersadd");
    aliPayConfig.setNotifyUrl("http://120.24.70.157:6666/user/ordersadd");
//        aliPayConfig.setPayTypeEnum();


    BestPayServiceImpl bestPayService = new BestPayServiceImpl();
    //   bestPayService.setWxPayConfig(wxPayConfig);
    bestPayService.setAliPayConfig(aliPayConfig);
    return bestPayService;
}

}
