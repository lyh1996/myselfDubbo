package com.lyh;

import com.lyh.register.IRegisterCenter;
import com.lyh.register.RegisterCenterImpl;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2019-08-03 15:30
 * @since 1.7
 */
public class ServiceDemo {
    public static void main(String[] args) {

        DubboHello dubboHello = new DubboHelloImpl();
        IRegisterCenter registerCenter = new RegisterCenterImpl();
        RpcService rpcService = new RpcService(registerCenter, "127.0.0.1:8888");
        rpcService.bind(dubboHello);
        rpcService.publisher();
    }
}
