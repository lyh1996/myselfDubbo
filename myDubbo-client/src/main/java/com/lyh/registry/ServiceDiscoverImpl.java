package com.lyh.registry;

import com.lyh.loadbalance.LoadBalance;
import com.lyh.loadbalance.RandomLoadBalance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2019-08-05 8:28
 * @since 1.7
 */
public class ServiceDiscoverImpl implements IServiceDiscovery {

    List<String> repos = new ArrayList<>();

    private CuratorFramework curatorFramework;

    public ServiceDiscoverImpl() {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONNNECTION_STR).sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10)).build();
        curatorFramework.start();
    }

    @Override
    public String discover(String serviceName) {
        String path = ZkConfig.ZK_REGISTER_PATH + "/" +serviceName;
        try {
            // 得到所有的ip地址
            repos = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 通过zk节点的Watch机制实现监听的功能
        registerWatch(path);

        // 负载均衡算法   随机   dubbo中拥有四种
        LoadBalance loadBalance = new RandomLoadBalance();

        return loadBalance.select(repos);
    }

    private void registerWatch(final String path) {
        PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = (curatorFramework, pathChildrenCacheEvent) -> repos = curatorFramework.getChildren().forPath(path);

        childrenCache.getListenable().addListener(pathChildrenCacheListener);

        try {
            childrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册PathChild Watcher 异常 :" + e.toString());
        }
    }

}
