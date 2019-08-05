package com.lyh.registry;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2019-08-05 8:28
 * @since 1.7
 */
public interface IServiceDiscovery {

    /**
     * 根据服务名称得到ip地址
     * @param serviceName
     * @return
     */
    String discover(String serviceName);

}
