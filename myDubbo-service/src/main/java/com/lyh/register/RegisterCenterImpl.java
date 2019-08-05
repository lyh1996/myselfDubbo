package com.lyh.register;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2019-08-03 16:16
 * @since 1.7
 */
public class RegisterCenterImpl implements IRegisterCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONNECTION_STR).sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10)).build();
        curatorFramework.start();
    }

    /**
     * com.lyh.DubboHello
     *    ip
     * @param serviceName
     * @param serviceAddress
     */
    @Override
    public void register(String serviceName, String serviceAddress) {

        // 构建关于服务名称的地址    /registry/com.lyh.DubboHello
        String servicePath = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;
        try {
            // 使用持久化节点  /registry/com.lyh.DubboHello
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePath, "0".getBytes());
            }

            // 使用临时地址
            // /registry/com.lyh.DubboHello/serviceAddress1
            // /registry/com.lyh.DubboHello/serviceAddress2
            // /registry/com.lyh.DubboHello/serviceAddress3
            String addressPath = servicePath + "/" + serviceAddress;
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath, "0".getBytes());

            System.out.println("服务注册成功:" + rsNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
