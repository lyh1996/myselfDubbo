package com.lyh.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * @author 633805 LYH
 * @version V1.0
 * @description 对类的描述
 * @create 2019-08-05 8:46
 * @since 1.7
 */
public class RandomLoadBalance implements LoadBalance {
    @Override
    public String select(List<String> repos) {
        int len = repos.size();
        if (len == 0) {
            throw new RuntimeException("未发现注册的服务。");
        }
        Random random = new Random();
        return repos.get(random.nextInt(len));
    }
}
