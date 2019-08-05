package com.lyh;

import com.lyh.proxy.RpcClientProxy;
import com.lyh.registry.IServiceDiscovery;
import com.lyh.registry.ServiceDiscoverImpl;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2019-08-03 15:32
 * @since 1.7
 */
public class ClientDemo {

    public static void main(String[] args) {
        IServiceDiscovery serviceDiscovery = new ServiceDiscoverImpl();
        String url = serviceDiscovery.discover("com.lyh.DubboHello");
        // 根据url地址然后进行远程调用

        // 服务发现 放在动态代理中进行   netty

        RpcClientProxy rpcClientProxy = new RpcClientProxy(serviceDiscovery);

        // 进行远程通信  netty 走的url 服务发现  动态代理
        DubboHello dubboHello = rpcClientProxy.create(DubboHello.class);


        // 走的是远程调用的方式  此时调用的是服务器端的sayHello方法
        System.out.println("返回结果："+dubboHello.sayHello("lyh"));

    }
}
