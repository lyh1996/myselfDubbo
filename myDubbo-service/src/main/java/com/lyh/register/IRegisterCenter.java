package com.lyh.register;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2019-08-03 16:12
 * @since 1.7
 */
public interface IRegisterCenter {

    /**
     * 服务名称和ip注册到zk
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName, String serviceAddress);
}
