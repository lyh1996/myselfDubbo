package com.lyh;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2019-08-03 15:28
 * @since 1.7
 */

@RpcAnnotation(interfaceClass = DubboHello.class)
public class DubboHelloImpl implements DubboHello {

    @Override
    public String sayHello(String msg) {
        return "From service :" + msg;
    }
}
